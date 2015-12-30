package com.uxuan.buffer;

import org.junit.Before;
import org.junit.Test;

import java.nio.ByteBuffer;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.*;

/**
 * @author liuzhen
 *         <p/>
 *         Email liuxing521a@163.com
 *         CreatedTime 2015-12-29 22:02:00
 */
public class UXBuilderTest {

    private ThreadLocalRandom random;

    @Before
    public void setUP() {
        random = ThreadLocalRandom.current();
    }

    @Test
    public void testPrep() throws Exception {
        UXBuilder uXBuilder = UXBuilder.allocate(4);
        uXBuilder.prep(2);
        assertEquals(4, uXBuilder.toByteBuffer().capacity());

        uXBuilder.prep(4);
        assertEquals(4, uXBuilder.toByteBuffer().capacity());

        uXBuilder.prep(5);
        assertEquals(8, uXBuilder.toByteBuffer().capacity());

        uXBuilder.prep(30);
        assertEquals(32, uXBuilder.toByteBuffer().capacity());
    }

    @Test
    public void testGrow() throws Exception {
        UXBuilder uXBuilder = UXBuilder.allocate(4);

        ByteBuffer old = uXBuilder.toByteBuffer();
        old.put("me".getBytes());

        ByteBuffer buf = uXBuilder.grow(old);
        assertEquals(8, buf.capacity());

        assertEquals(old.position(), buf.position());
    }

    @Test
    public void testReadDouble() throws Exception {
        double dv = random.nextDouble(0.00, Double.MAX_VALUE);
        UXBuilder buffer = UXBuilder.allocate(4);
        buffer.writeDouble(dv).finishWirte();

        assertEquals(dv, buffer.readDouble(), 0.001D);
    }

    @Test
    public void testReadDouble1() throws Exception {
        double dv = random.nextDouble(0.00, Double.MAX_VALUE);
        UXBuilder buffer = UXBuilder.allocate(4);
        buffer.writeInt64(1L).writeInt8(4);
        buffer.writeDouble(0, dv).finishWirte();

        assertEquals(dv, buffer.readDouble(0), 0.001D);
    }

    @Test
    public void testReadString() throws Exception {
        String str = UUID.randomUUID().toString();
        UXBuilder buffer = UXBuilder.allocate(4);
        buffer.writeString(str).finishWirte();

        assertEquals(str, buffer.readString());
    }

    @Test
    public void testReadBytes() throws Exception {
        byte[] bs = UUID.randomUUID().toString().getBytes();
        UXBuilder buffer = UXBuilder.allocate(4);
        buffer.writeByte(bs).finishWirte();

        assertEquals(bs, buffer.readBytes());
    }

}
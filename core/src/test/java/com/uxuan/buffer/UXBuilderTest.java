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
 * Email liuxing521a@163.com
 * CreatedTime 2015-12-29 22:02:00
 */
public class UXBuilderTest {

    private ThreadLocalRandom random;

    @Before
    public void setUP() {
        random = ThreadLocalRandom.current();
    }

    @Test
    public void testPrep() throws Exception {
        IoBuf uXBuilder = IoBuf.allocate(4);
        uXBuilder.prep(2);
        assertEquals(4, uXBuilder.toBuffer().capacity());

        uXBuilder.prep(4);
        assertEquals(4, uXBuilder.toBuffer().capacity());

        uXBuilder.prep(5);
        assertEquals(8, uXBuilder.toBuffer().capacity());

        uXBuilder.prep(30);
        assertEquals(32, uXBuilder.toBuffer().capacity());
    }

    @Test
    public void testGrow() throws Exception {
        IoBuf uXBuilder = IoBuf.allocate(4);

        ByteBuffer old = uXBuilder.toBuffer();
        old.put("me".getBytes());

        ByteBuffer buf = uXBuilder.grow(old);
        assertEquals(8, buf.capacity());

        assertEquals(old.position(), buf.position());
    }

    @Test
    public void testBool() throws Exception {
        boolean dv = random.nextBoolean();
        IoBuf buffer = IoBuf.allocate(4);
        buffer.writeBool(dv).finishWirte();

        assertEquals(dv, buffer.readBool());
    }

    @Test
    public void testBoolIndex() throws Exception {
        boolean dv = random.nextBoolean();
        IoBuf buffer = IoBuf.allocate(4);
        buffer.writeInt16(1).writeInt64(4);
        buffer.writeBool(3, dv).finishWirte();

        assertEquals(dv, buffer.readBool(3));
    }

    @Test
    public void testChar() throws Exception {
        char dv = (char)random.nextInt(Byte.MAX_VALUE);
        IoBuf buffer = IoBuf.allocate(4);
        buffer.writeChar(dv).finishWirte();

        assertEquals(dv, buffer.readChar());
    }

    @Test
    public void testCharIndex() throws Exception {
        char dv = (char)random.nextInt(Byte.MAX_VALUE);
        IoBuf buffer = IoBuf.allocate(4);
        buffer.writeInt16(1).writeInt64(4);
        buffer.writeChar(3, dv).finishWirte();

        assertEquals(dv, buffer.readChar(3));
    }

    @Test
    public void testInt8() throws Exception {
        byte dv = (byte)random.nextInt(Byte.MAX_VALUE);
        IoBuf buffer = IoBuf.allocate(4);
        buffer.writeInt8(dv).finishWirte();

        assertEquals(dv, buffer.readInt8());
    }

    @Test
    public void testInt8Index() throws Exception {
        byte dv = (byte)random.nextInt(Byte.MAX_VALUE);
        IoBuf buffer = IoBuf.allocate(4);
        buffer.writeInt16(1).writeInt64(4);
        buffer.writeInt8(3, dv).finishWirte();

        assertEquals(dv, buffer.readInt8(3));
    }


    @Test
    public void testInt16() throws Exception {
        short dv = (short)random.nextInt(Short.MAX_VALUE);
        IoBuf buffer = IoBuf.allocate(4);
        buffer.writeInt16(dv).finishWirte();

        assertEquals(dv, buffer.readInt16());
    }

    @Test
    public void testInt16Index() throws Exception {
        short dv = (short)random.nextInt(Short.MAX_VALUE);
        IoBuf buffer = IoBuf.allocate(4);
        buffer.writeInt16(1).writeInt64(4);
        buffer.writeInt16(3, dv).finishWirte();

        assertEquals(dv, buffer.readInt16(3));
    }

    @Test
    public void testInt32() throws Exception {
        int dv = random.nextInt(Integer.MAX_VALUE);
        IoBuf buffer = IoBuf.allocate(4);
        buffer.writeInt(dv).finishWirte();

        assertEquals(dv, buffer.readInt());
    }

    @Test
    public void testInt32Index() throws Exception {
        int dv = random.nextInt(Integer.MAX_VALUE);
        IoBuf buffer = IoBuf.allocate(4);
        buffer.writeInt(1).writeInt64(4);
        buffer.writeInt(3, dv).finishWirte();

        assertEquals(dv, buffer.readInt(3));
    }

    @Test
    public void testInt64() throws Exception {
        long dv = random.nextLong(Long.MAX_VALUE);
        IoBuf buffer = IoBuf.allocate(4);
        buffer.writeInt64(dv).finishWirte();

        assertEquals(dv, buffer.readInt64());
    }

    @Test
    public void testInt64Index() throws Exception {
        long dv = random.nextLong(Long.MAX_VALUE);
        IoBuf buffer = IoBuf.allocate(4);
        buffer.writeInt64(1L).writeInt64(4);
        buffer.writeInt64(3, dv).finishWirte();

        assertEquals(dv, buffer.readInt64(3));
    }

    @Test
    public void testFloat() throws Exception {
        float dv = random.nextFloat();
        IoBuf buffer = IoBuf.allocate(4);
        buffer.writeFloat(dv).finishWirte();

        assertEquals(dv, buffer.readFloat(), 0.001F);
    }

    @Test
    public void testFloatIndex() throws Exception {
        float dv = random.nextFloat();
        IoBuf buffer = IoBuf.allocate(4);
        buffer.writeInt64(1L).writeInt8(4);
        buffer.writeFloat(2, dv).finishWirte();

        assertEquals(dv, buffer.readFloat(2), 0.001F);
    }

    @Test
    public void tesDouble() throws Exception {
        double dv = random.nextDouble(0.00, Double.MAX_VALUE);
        IoBuf buffer = IoBuf.allocate(4);
        buffer.writeDouble(dv).finishWirte();

        assertEquals(dv, buffer.readDouble(), 0.001D);
    }

    @Test
    public void tesDoubleIndex() throws Exception {
        double dv = random.nextDouble(0.00, Double.MAX_VALUE);
        IoBuf buffer = IoBuf.allocate(4);
        buffer.writeInt64(1L).writeInt16(4);
        buffer.writeDouble(2, dv).finishWirte();

        assertEquals(dv, buffer.readDouble(2), 0.001D);
    }

    @Test
    public void testString() throws Exception {
        String str = UUID.randomUUID().toString();
        IoBuf buffer = IoBuf.allocate(4);
        buffer.writeString(str).finishWirte();

        assertEquals(str, buffer.readString());
    }

    @Test
    public void tesBytes() throws Exception {
        byte[] bs = UUID.randomUUID().toString().getBytes();
        IoBuf buffer = IoBuf.allocate(4);
        buffer.writeByte(bs).finishWirte();

        assertArrayEquals(bs, buffer.readBytes());
    }

}
package org.itas.buffer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class UBubferBuilder {
	
	/** byte order */
	private ByteOrder order;
	/** java.nio.ByteBuffer  */
	private ByteBuffer buffer;

	public static UBubferBuilder allocate(int capacity) {
		return new UBubferBuilder(capacity, ByteOrder.LITTLE_ENDIAN);
	}

	public static UBubferBuilder allocate(int capacity, ByteOrder order) {
		return new UBubferBuilder(capacity, order);
	}

	public static UBubferBuilder wrap(byte[] bytes) {
		int capacity = bytes.length;
		if (capacity <= 0)
			capacity = 4;

		UBubferBuilder builder = allocate(capacity);
		builder.buffer = ByteBuffer.wrap(bytes);
		return builder;
	}

	public static UBubferBuilder wrap(ByteBuffer buffer) {
		int capacity = buffer.capacity();
		if (capacity <= 0)
			capacity = 4;

		UBubferBuilder builder = allocate(capacity);
		builder.buffer = buffer;
		return builder;
	}

	private UBubferBuilder(int size, ByteOrder order) {
		this.order = order;
		if (size <= 0)
			size = 4;
		this.buffer = newByteBuffer(size);
	}
	
	public ByteBuffer toByteBuffer() {
		return buffer;
	}

	public byte[] toByteArray() {
		ByteBuffer cur = buffer.duplicate();
		cur.flip();

		byte[] bytes = new byte[cur.limit()];
		cur.get(bytes);

		return bytes;
	}

	public boolean readBool() {
		byte value = buffer.get();
		return (value == 0) ? false : true;
	}

	public byte readByte() {
		return buffer.get();
	}

	public void addBytes(byte[] bs) {
		prep(bs.length);
		buffer.put(bs);
	}
	
	public void addByte(byte value) {
		prep(1);
		buffer.put(value);
	}

	public void addShort(short value) {
		prep(2);
		buffer.putShort(value);
	}

	public void addInt(int value) {
		prep(4);
		buffer.putInt(value);
	}

	public void addLong(long value) {
		prep(8);
		buffer.putLong(value);
	}

	public void addFloat(float value) {
		prep(4);
		buffer.putFloat(value);
	}

	public void addDouble(double value) {
		prep(8);
		buffer.putDouble(value);
	}
	
	public void replaceByte(int index, byte value) {
		buffer.put(index, value);
	}

	public void replaceShort(int index, short value) {
		buffer.putShort(index, value);
	}

	public void replaceInt(int index, int value) {
		buffer.putInt(index, value);
	}

	public void replaceLong(int index, long value) {
		buffer.putLong(index, value);
	}

	public void replaceFloat(int index, float value) {
		buffer.putFloat(index, value);
	}

	public void replaceDouble(int index, double value) {
		buffer.putDouble(index, value);
	}
	
	public int position() {
		return buffer.position();
	}
	
	private void prep(int size) {
		while (buffer.position() + size < buffer.capacity()) {
			buffer = growByteBuffer(buffer);
		}
	}

	private ByteBuffer growByteBuffer(ByteBuffer bb) {
		int old_buf_size = bb.capacity();
		int new_buf_size = old_buf_size << 1;

		if (new_buf_size > Short.MAX_VALUE)  {
			throw new AssertionError("BubferBuilder: cannot grow buffer beyond 2 gigabytes.");
		}
		
		bb.position(0);
		ByteBuffer nbb = newByteBuffer(new_buf_size);
		nbb.position(new_buf_size - old_buf_size);
		nbb.put(bb);
		return nbb;
	}

	private ByteBuffer newByteBuffer(int capacity) {
		ByteBuffer newbb = ByteBuffer.allocate(capacity);
		newbb.order(order);

		return newbb;
	}
}

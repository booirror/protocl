package com.uxuan.buffer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 自动增长buffer
 *
 * @author  liuzhen
 * Email liuxing521a@163.com
 * CreateTime 2015-12-30 21:32:12
 */
public final class IoBuf {
	
	/**字符编码*/
	private static Charset CHARSET = Charset.forName("UTF-8"); 
	
	/** buffer最大容量*/
	private static final int MAX_BUF_SIZE = 64*1024*1024;
	
	/** byte order */
	private ByteOrder order;
	/** java.nio.ByteBuffer  */
	private ByteBuffer buffer;

	public static IoBuf allocate(int capacity) {
		return new IoBuf(capacity, ByteOrder.LITTLE_ENDIAN);
	}

	public static IoBuf allocate(int capacity, ByteOrder order) {
		return new IoBuf(capacity, order);
	}

	public static IoBuf wrap(byte[] bytes) {
		return wrap(ByteBuffer.wrap(bytes));
	}

	public static IoBuf wrap(ByteBuffer buffer) {
		IoBuf builder = allocate(buffer.capacity());
		builder.order = buffer.order();
		builder.buffer = buffer;
		return builder;
	}

	private IoBuf(int size, ByteOrder order) {
		this.order = order;
		if (size <= 0)
			size = 4;
		this.buffer = newBuf(size);
	}


	/**
	 * 转成ByteBuffer
	 *
	 * @return {@link java.nio.ByteBuffer}
     */
	public ByteBuffer toBuffer() {
		return buffer.duplicate();
	}

	/**
	 * 转成byte数组
	 *
	 * @return byte数组
     */
	public byte[] toArray() {
		ByteBuffer cur = buffer.duplicate();
		cur.flip();

		byte[] bytes = new byte[cur.limit()];
		cur.get(bytes);

		return bytes;
	}

	public IoBuf finishWirte() {
		buffer.flip();
		return this;
	}

	/**
	 * 读取boolean值
	 *
	 * @return this buffer
	 */
	public boolean readBool() {
		byte value = readInt8();
		return value == 1 ? true : false;
	}

	/**
	 * 指定位置读取boolean值
	 *
	 * @param index 读取位置
	 * @return this buffer
	 */
	public boolean readBool(int index) {
		byte value = readInt8(index);
		return value == 1 ? true : false;
	}

	/**
	 * 读取char值
	 *
	 * @return this buffer
	 */
	public char readChar() {
		return buffer.getChar();
	}

	/**
	 * 指定位置读取char值
	 *
	 * @param index 读取位置
	 * @return this buffer
	 */
	public char readChar(int index) {
		return buffer.getChar(index);
	}

	/**
	 * 读取byte值
	 *
	 * @return this buffer
	 */
	public byte readInt8() {
		return buffer.get();
	}

	/**
	 * 指定位置读取byte值
	 *
	 * @param index 读取位置
	 * @return this buffer
	 */
	public byte readInt8(int index) {
		return buffer.get(index);
	}

	/**
	 * 读取short值
	 *
	 * @return this buffer
	 */
	public short readInt16() {
		return buffer.getShort();
	}

	/**
	 * 指定位置读取short值
	 *
	 * @param index 读取位置
	 * @return this buffer
	 */
	public short readInt16(int index) {
		return buffer.getShort(index);
	}

	/**
	 * 读取int值
	 *
	 * @return this buffer
	 */
	public int readInt() {
		return buffer.getInt();
	}

	/**
	 * 指定位置读取int值
	 *
	 * @param index 读取位置
	 * @return this buffer
	 */
	public int readInt(int index) {
		return buffer.getInt(index);
	}

	/**
	 * 读取long值
	 *
	 * @return this buffer
	 */
	public long readInt64() {
		return buffer.getLong();
	}

	/**
	 * 指定位置读取long值
	 *
	 * @param index 读取位置
	 * @return this buffer
	 */
	public long readInt64(int index) {
		return buffer.getLong(index);
	}

	/**
	 * 读取float值
	 *
	 * @return this buffer
	 */
	public float readFloat() {
		return buffer.getFloat();
	}

	/**
	 * 指定位置读取float值
	 *
	 * @param index 读取位置
	 * @return this buffer
     */
	public float readFloat(int index) {
		return buffer.getFloat(index);
	}

	/**
	 * 读取double值
	 *
	 * @return 读取的double值
     */
	public double readDouble() {
		return buffer.getDouble();
	}

	/**
	 * 指定位置读取double值
	 *
	 * @param index 要读取的位置
	 * @return 读取的double值
     */
	public double readDouble(int index) {
		return buffer.getDouble(index);
	}

	/**
	 * 读取字符串
	 *
	 * <p>根据写入时加入的长度来读取一个完整的字符串</p>
	 * @return 读取后的字符串
     */
	public String readString() {
		final byte[] bs = readBytes();
		if (bs == null || bs.length == 0) {
			return null;
		}

		return new String(bs, CHARSET);
	}

	/**
	 * 读取字节数组
	 *
	 * <p>根据写入时加入的写入的字节数组长度来读取字节长度</p>
	 * @return 读取的字节数组
     */
	public byte[] readBytes() {
		int len = buffer.getInt();
		return readBytes(len);
	}

	/**
	 * 读取指定长度的字节数组
	 *
	 * @param len  要读取数组长度
	 * @return 读取的自己数组
     */
	public byte[] readBytes(int len) {
		if (len == 0) {
			return null;
		}
		
		byte[] bs = new byte[len];
		buffer.get(bs);
		return bs;
	}

	@SuppressWarnings("unchecked")
	protected <T> List<T> readArray(Class<?> clazz) {
		int len = readInt16();

		List<Object> datas = new ArrayList<Object>(len);
		for (int i = 0; i < len; i++) {
			if (clazz == Boolean.class) {
				datas.add(readBool());
			} else if (clazz == Character.class) {
				datas.add(readChar());
			} else if (clazz == Byte.class) {
				datas.add(readInt8());
			} else if (clazz == Short.class) {
				datas.add(readInt16());
			} else if (clazz == Integer.class) {
				datas.add(readInt());
			} else if (clazz == Long.class) {
				datas.add(readInt64());
			} else if (clazz == Float.class) {
				datas.add(readFloat());
			} else if (clazz == Double.class) {
				datas.add(readDouble());
			}else if (clazz == String.class) {
				datas.add(readString());
			} else if (Message.class.isAssignableFrom(clazz)) {
				Message data = Message.getMsg((Class<? extends Message>)clazz);
				datas.add(data.readMsg(this));
			} else {
				throw new RuntimeException("unsupport Type:" + clazz.getName());
			}
		}

		return (List<T>) datas;
	}
	
	/**
	 * 写入boolen值
	 *
	 * @param data 写入布尔值
	 * @return this buffer
     */
	public IoBuf writeBool(boolean data) {
		this.writeInt8(data ? 1 : 0);
		return this;
	}

	/**
	 * 指定位置写如boolean值
	 *
	 * @param index 写入的位置
	 * @param data 写入布尔值
	 * @return this buffer
     */
	public IoBuf writeBool(int index, boolean data) {
		this.writeInt8(index, data ? 1 : 0);
		return this;
	}

	/**
	 * 写字符
	 *
	 * @param data 写入的字符
	 * @return this buffer
     */
	public IoBuf writeChar(char data) {
		prep(2);
		buffer.putChar(data);
		return this;
	}

	/**
	 * 指定位置写字符
	 *
	 * @param index 要写位置
	 * @param data 写入字符
	 * @return this buffer
     */
	public IoBuf writeChar(int index, char data) {
		buffer.putChar(index, data);
		return this;
	}

	/**
	 * 写入一个字节
	 *
	 * @param data 要写入数据
	 * @return this buffer
     */
	public IoBuf writeInt8(int data) {
		prep(1);
		buffer.put((byte)data);
		return this;
	}

	/**
	 * 写入一个字节
	 *
	 * @param index 写入的位置
	 * @param data 要写入数据
     * @return this buffer
     */
	public IoBuf writeInt8(int index, int data) {
		buffer.put(index, (byte)data);
		return this;
	}

	/**
	 * 写入short值
	 *
	 * @param data 要写入short值
	 * @return this buffer
     */
	public IoBuf writeInt16(int data) {
		prep(2);
		buffer.putShort((short)data);
		return this;
	}

	/**
	 * 指定位置写入short值
	 *
	 * @param index 要写入位置
	 * @param data 要写入short值
     * @return this buffer
     */
	public IoBuf writeInt16(int index, int data) {
		buffer.putShort(index, (short)data);
		return this;
	}

	/**
	 * 写入int值
	 *
	 * @param data 要写入int值
	 * @return this buffer
     */
	public IoBuf writeInt(int data) {
		prep(4);
		buffer.putInt(data);
		return this;
	}

	/**
	 * 指定位置写入int值
	 *
	 * @param index 要写入位置
	 * @param data 要写入的int值
     * @return this buffer
     */
	public IoBuf writeInt(int index, int data) {
		buffer.putInt(index, data);
		return this;
	}

	/**
	 * 写入long值
	 *
	 * @param data 写入long值
	 * @return this buffer
     */
	public IoBuf writeInt64(long data) {
		prep(8);
		buffer.putLong(data);
		return this;
	}

	/**
	 * 指定位置写入long值
	 *
	 * @param index 要写入的位置
	 * @param data 写入long值
     * @return this buffer
     */
	public IoBuf writeInt64(int index, long data) {
		buffer.putLong(index, data);
		return this;
	}

	/**
	 * 写入float值
	 *
	 * @param data 要写入float值
	 * @return this buffer
     */
	public IoBuf writeFloat(float data) {
		prep(4);
		buffer.putFloat(data);
		return this;
	}

	/**
	 * 指定位置写入float
	 *
	 * @param index 要写入的位置
	 * @param data 要写入的float值
     * @return this buffer
     */
	public IoBuf writeFloat(int index, float data) {
		buffer.putFloat(index, data);
		return this;
	}

	/**
	 * 写入double值
	 *
	 * @param data 要写入的double值
	 * @return this buffer
     */
	public IoBuf writeDouble(double data) {
		prep(8);
		buffer.putDouble(data);
		return this;
	}

	/**
	 * 指定位置写入double值
	 *
	 * @param index 要写入的位置
	 * @param data 要写入的double值
     * @return this buffer
     */
	public IoBuf writeDouble(int index, double data) {
		buffer.putDouble(index, data);
		return this;
	}

	/**
	 * 写入字符串
	 *
	 * @param data 要写入的字符串
	 * @return this buffer
     */
	public IoBuf writeString(String data) {
		return writeByte(data.getBytes(CHARSET));
	}

	/**
	 * 写入字节数组
	 *
	 * @param data 要写的自己数组
	 * @return this buffer
     */
	public IoBuf writeByte(byte[] data) {
		final int len = data.length;
		prep(len + 4);
		buffer.putInt(len).put(data);
		return this;
	}
	
	public IoBuf writeArray(List<?> datas) {
		if (datas == null) {
			datas = Collections.emptyList();
		}

		writeInt16(datas.size());
		for (Object data : datas) {
			if (data instanceof Boolean) {
				writeBool((Boolean)data);
			} else if (data instanceof Character) {
				writeChar((Character)data);
			} else if (data instanceof Byte) {
				writeInt8((Byte)data);
			} else if (data instanceof Short) {
				writeInt16((Short) data);
			} else if (data instanceof Integer) {
				writeInt((Integer) data);
			} else if (data instanceof Long) {
				writeInt64((Long) data);
			} else if (data instanceof Float) {
				writeFloat((Float) data);
			} else if (data instanceof Double) {
				writeDouble((Double) data);
			} else if (data instanceof String) {
				writeString((String) data);
			} else if (data instanceof Message) {
				((Message) data).writeMsg(this);
			} else {
				throw new RuntimeException("unsupport Type:" + data.getClass().getName());
			}
		}
		
		return this;
	}

	/**
	 * 检查处理增长
	 *
	 * <p>如果新加入的长度大于原buffer空间，自动自动增长一倍空间；继续检查，
	 * 一直到新空间可以容纳要插入的数据</p>
	 *
	 * @param size 要存入缓存数据长度
     */
	protected void prep(int size) {
		while (buffer.position() + size > buffer.capacity()) {
			buffer = grow(buffer);
		}
	}

	/**
	 * 增长buffer容量
	 *
	 * <p>自动增长buffer容量,默认增长一倍；当增长值大于 {@link #MAX_BUF_SIZE}时，
	 *  抛出错误</p>
	 *
	 * @param buffer 原来buffer
	 * @return 增长后新buffer
     */
	protected ByteBuffer grow(ByteBuffer buffer) {
		int old_buf_size = buffer.capacity();
		int new_buf_size = old_buf_size << 1;

		if (new_buf_size > MAX_BUF_SIZE)  {
			throw new AssertionError("can't grow buffer beyond 64M.");
		}

		buffer.flip();
		ByteBuffer nbb = newBuf(new_buf_size);
		nbb.put(buffer);

		return nbb;
	}

	/**
	 * 创建新buf
	 *
	 * @param capacity buf容量
	 * @return 创建的buf
     */
	private ByteBuffer newBuf(int capacity) {
		ByteBuffer newbb = ByteBuffer.allocate(capacity);
		newbb.order(order);

		return newbb;
	}
}

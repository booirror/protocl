package com.uxuan.buffer;

import java.lang.reflect.Constructor;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.*;

/**
 * message
 *
 * @author  liuzhen
 * Email liuxing521a@163.com
 * CreateTime 201６-01-04 20:32:12
 */
public abstract class Message {

	private static final Charset encoding = Charset.forName("UTF-8");

	protected Message() {
	}

//
//	public abstract Message readMessage(UBubferBuilder buf);
//
//	public abstract void writeMessage(UBubferBuilder buf);
//
//	public abstract byte[] toByteArray();
//
//	public abstract ByteBuffer toByteBuffer();
//
//
//
//	protected byte[] readBytes(UBubferBuilder buf, int len) {
//		byte[] bs = new byte[len];
//		buf.get(bs);
//
//		return bs;
//	}
//
//	protected boolean readBool(UBubferBuilder buf) {
//		return buf.get() == 1;
//	}
//
//	protected byte readInt8(UBubferBuilder buf) {
//		return buf.get();
//	}
//
//	protected short readInt16(UBubferBuilder buf) {
//		return buf.getShort();
//	}
//
//	protected int readInt(UBubferBuilder buf) {
//		return buf.getInt();
//	}
//
//	protected long readInt64(UBubferBuilder buf) {
//		return buf.getLong();
//	}
//
//	protected float readFloat(UBubferBuilder buf) {
//		return buf.getFloat();
//	}
//
//	protected double readDouble(UBubferBuilder buf) {
//		return buf.getDouble();
//	}
//
//	protected String readString(UBubferBuilder buf) {
//		final int len = readInt16(buf);
//		return new String(readBytes(buf, len), encoding);
//	}
//
//	@SuppressWarnings("unchecked")
//	protected <T> List<T> readArray(Class<?> clazz, UBubferBuilder buf) {
//		short len = readInt16(buf);
//
//		List<Object> dataList = new ArrayList<>(len);
//		for(int i = 0; i < len; i ++) {
//			if (clazz == Byte.class) {
//				dataList.add(readInt8(buf));
//			} else if (clazz == Short.class) {
//				dataList.add(readInt16(buf));
//			} else if (clazz == Integer.class) {
//				dataList.add(readInt(buf));
//			} else if (clazz == Long.class) {
//				dataList.add(readInt64(buf));
//			} else if (clazz == String.class) {
//				dataList.add(readString(buf));
//			} else if (Message.class.isAssignableFrom(clazz)) {
//				Message data = newInstance(clazz);
//				data.readMsg(buf);
//				dataList.add(data);
//			} else {
//				throw new RuntimeException("unkown message Type:" + clazz.getName());
//			}
//		}
//
//		return (List<T>) dataList;
//	}
//
//
//
//
//	// ====================================================
//	// write byte to UBubferBuilder
//	// ====================================================
//	public abstract void writeMsg(UBubferBuilder builder);
//
//	protected void writeBytes(UBubferBuilder builder, byte[] values) {
//		builder.addBytes(values);
//	}
//
//	protected void writeBool(UBubferBuilder builder, boolean value) {
//		byte bs = (byte)(value ? 1 : 0);
//		builder.addByte(bs);
//	}
//
//	protected void writeInt8(UBubferBuilder builder, byte value) {
//		builder.addByte(value);
//	}
//
//	protected void writeInt16(UBubferBuilder builder, short value) {
//		builder.addShort(value);
//	}
//
//	protected void writeInt(UBubferBuilder builder, int value) {
//		builder.addInt(value);
//	}
//
//	protected void writeInt64(UBubferBuilder builder, long value) {
//		builder.addLong(value);
//	}
//
//	protected void writeFloat(UBubferBuilder builder, float value) {
//		builder.addFloat(value);
//	}
//
//	protected void writeDouble(UBubferBuilder builder, double value) {
//		builder.addDouble(value);
//	}
//
//	protected void writeString(UBubferBuilder builder, String data) {
//		if (data == null) {
//			data = "";
//		}
//
//		final byte[] bytes = data.getBytes(encoding);
//		writeInt16(builder, (short)bytes.length);
//		writeBytes(builder, bytes);
//	}
//
//	protected void writeArray(UXBuilder builder, List<?> dataList) {
//		if (dataList == null) {
//			dataList = Collections.emptyList();
//		}
//
//		writeInt16(builder, (short)dataList.size());
//		for(Object data : dataList) {
//			if (data instanceof Byte) {
//				writeInt8(builder, (Byte)data);
//			} else if (data instanceof Short) {
//				writeInt16(builder, (Short)data);
//			} else if (data instanceof Integer) {
//				writeInt(builder, (Integer)data);
//			} else if (data instanceof Long) {
//				writeInt64(builder, (Long)data);
//			} else if (data instanceof String) {
//				writeString(builder, (String)data);
//			} else if (data instanceof Message) {
//				((Message)data).writeMsg(builder);
//			} else {
//				throw new RuntimeException("unkown message Type:" + data.getClass().getName());
//			}
//		}
//	}
//
//	private Message newInstance(Class<?> classType) {
//		try {
//			Constructor<?> constructor = classType.getDeclaredConstructor();
//			if (!constructor.isAccessible()) {
//				constructor.setAccessible(true);
//			}
//
//			return (Message) constructor.newInstance();
//		} catch (Exception e) {
//			throw new RuntimeException("创建实列失败", e);
//		}
//	}
}

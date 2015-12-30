//package com.uxuan.buffer;
//
//import java.lang.reflect.Constructor;
//import java.nio.ByteBuffer;
//import java.nio.charset.Charset;
//import java.util.*;
//
//public abstract class UMessage {
//
//	private static final Charset encoding = Charset.forName("UTF-8");
//
//	protected UMessage() {
//	}
//
//
//	public abstract UMessage readMessage(UBubferBuilder buf);
//
//	public abstract void writeMessage(UBubferBuilder buf);
//
//	public abstract byte[] toByteArray();
//
//	public abstract ByteBuffer toByteBuffer();
//
//	interface ReadAble {
//
//		boolean readBool(UBubferBuilder buf);
//
//		char readChar(UBubferBuilder buf);
//
//		byte readInt8(UBubferBuilder buf);
//
//		short readInt16(UBubferBuilder buf);
//
//		int readInt(UBubferBuilder buf);
//
//		int readInt32(UBubferBuilder buf);
//
//		long readInt64(UBubferBuilder buf);
//
//		float readFloat(UBubferBuilder buf);
//
//		double readDouble(UBubferBuilder buf);
//
//		String readString(UBubferBuilder buf);
//
//		<T> Set<T> readSet(UBubferBuilder buf);
//
//		<T> List<T> readArray(UBubferBuilder buf);
//
//		<K, V> Map<K, V> readMap(UBubferBuilder buf);
//	}
//
//	interface WriteAble {
//
//		void writeBool(UBubferBuilder buf);
//
//		char writeChar(UBubferBuilder buf);
//
//		byte writeInt8(UBubferBuilder buf);
//
//		short writeInt16(UBubferBuilder buf);
//
//		int writeInt(UBubferBuilder buf);
//
//		int writeInt32(UBubferBuilder buf);
//
//		long writeInt64(UBubferBuilder buf);
//
//		float writeFloat(UBubferBuilder buf);
//
//		double writeDouble(UBubferBuilder buf);
//
//		String writeString(UBubferBuilder buf);
//
//		<T> Set<T> writeSet(UBubferBuilder buf);
//
//		<T> List<T> writeArray(UBubferBuilder buf);
//
//		<K, V> Map<K, V> writeMap(UBubferBuilder buf);
//	}
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
//			} else if (UMessage.class.isAssignableFrom(clazz)) {
//				UMessage data = newInstance(clazz);
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
//	protected void writeArray(UBubferBuilder builder, List<?> dataList) {
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
//			} else if (data instanceof UMessage) {
//				((UMessage)data).writeMsg(builder);
//			} else {
//				throw new RuntimeException("unkown message Type:" + data.getClass().getName());
//			}
//		}
//	}
//
//	private UMessage newInstance(Class<?> classType) {
//		try {
//			Constructor<?> constructor = classType.getDeclaredConstructor();
//			if (!constructor.isAccessible()) {
//				constructor.setAccessible(true);
//			}
//
//			return (UMessage) constructor.newInstance();
//		} catch (Exception e) {
//			throw new RuntimeException("创建实列失败", e);
//		}
//	}
//}

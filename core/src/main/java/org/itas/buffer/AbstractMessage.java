package org.itas.buffer;

import java.lang.reflect.Constructor;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AbstractMessage {
	
	private static final Charset encoding = Charset.forName("UTF-8");
	
	protected AbstractMessage() {
	}
	
	/**
	 * <p>事件后版半部分</p>
	 * @return
	 */
	public abstract byte SUFFIX();

	// ====================================================
	// read byte from ByteBuffer
	// ====================================================
	protected abstract void fromByteBuffer(ByteBuffer buf);
	
	protected abstract AbstractMessage readMessage(ByteBuffer buf);
	
	protected byte[] readBytes(ByteBuffer buf, int len) {
		byte[] bs = new byte[len];
		buf.get(bs);
		
		return bs;
	}
	
	protected boolean readBool(ByteBuffer buf) {
		return buf.get() == 1;
	}

	protected byte readInt8(ByteBuffer buf) {
		return buf.get();
	}
	
	protected short readInt16(ByteBuffer buf) {
		return buf.getShort();
	}

	protected int readInt(ByteBuffer buf) {
		return buf.getInt();
	}
	
	protected long readInt64(ByteBuffer buf) {
		return buf.getLong();
	}
	
	protected String readString(ByteBuffer buf) {
		final int len = readInt16(buf);
		return new String(readBytes(buf, len), encoding);
	}
	
	@SuppressWarnings("unchecked")
	protected <T> List<T> readArray(Class<?> clazz, ByteBuffer buf) {
		short len = readInt16(buf); 

		List<Object> dataList = new ArrayList<>(len);
		for(int i = 0; i < len; i ++) {
			if (clazz == Byte.class) {
				dataList.add(readInt8(buf));
			} else if (clazz == Short.class) {
				dataList.add(readInt16(buf));
			} else if (clazz == Integer.class) {
				dataList.add(readInt(buf));
			} else if (clazz == Long.class) {
				dataList.add(readInt64(buf));
			} else if (clazz == String.class) {
				dataList.add(readString(buf));
			} else if (AbstractMessage.class.isAssignableFrom(clazz)) {
				AbstractMessage data = newInstance(clazz);
				data.readMessage(buf);
				dataList.add(data);
			} else {
				throw new RuntimeException("unkown message Type:" + clazz.getName());
			}
		}
		
		return (List<T>) dataList;
	}
	


	
	// ====================================================
	// write byte to byteBuffer
	// ====================================================
	protected abstract void toByteBuffer(BubferBuilder builder);
	
	protected abstract void writeMessage(BubferBuilder builder);
	
	protected void writeBytes(BubferBuilder builder, byte[] values) {
		builder.addBytes(values);
	}
	
	protected void writeBool(BubferBuilder builder, boolean value) {
		byte bs = (byte)(value ? 1 : 0);
		builder.addByte(bs);
	}
	
	protected void writeInt8(BubferBuilder builder, byte value) {
		builder.addByte(value);
	}
	
	protected void writeInt16(BubferBuilder builder, short value) {
		builder.addShort(value);
	}

	protected void writeInt(BubferBuilder builder, int value) {
		builder.addInt(value);
	}

	protected void writeInt64(BubferBuilder builder, long value) {
		builder.addLong(value);
	}
	
	protected void writeString(BubferBuilder builder, String data) { 
		if (data == null) {
			data = "";
		}
		
		final byte[] bytes = data.getBytes(encoding);
		writeInt16(builder, (short)bytes.length);
		writeBytes(builder, bytes);
	}
	
	protected void writeArray(BubferBuilder builder, List<?> dataList) { 
		if (dataList == null) {
			dataList = Collections.emptyList();
		}
		
		writeInt16(builder, (short)dataList.size());
		for(Object data : dataList) { 
			if (data instanceof Byte) {
				writeInt8(builder, (Byte)data);
			} else if (data instanceof Short) {
				writeInt16(builder, (Short)data);
			} else if (data instanceof Integer) {
				writeInt(builder, (Integer)data);
			} else if (data instanceof Long) {
				writeInt64(builder, (Long)data);
			} else if (data instanceof String) {
				writeString(builder, (String)data);
			} else if (data instanceof AbstractMessage) {
				((AbstractMessage)data).writeMessage(builder);
			} else {
				throw new RuntimeException("unkown message Type:" + data.getClass().getName());
			}
		} 
	}
	
	private AbstractMessage newInstance(Class<?> classType) {
		try {
			Constructor<?> constructor = classType.getDeclaredConstructor();
			if (!constructor.isAccessible()) {
				constructor.setAccessible(true);
			}
			
			return (AbstractMessage) constructor.newInstance();
		} catch (Exception e) {
			throw new RuntimeException("创建实列失败", e);
		}
	}
}

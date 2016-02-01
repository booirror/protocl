package com.uxuan.protocl.buffer;

import java.lang.reflect.Constructor;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

/**
 * message
 *
 * @author liuzhen Email liuxing521a@163.com CreateTime 201６-01-04 20:32:12
 */
public abstract class Message {

	private static final Map<Class<?>, Message> CACHED = new HashMap<Class<?>, Message>();
	
	protected Message() {
	}

    public byte[] toArray() {
        return toIoBuf().toArray();
    }

    public ByteBuffer toBuffer() {
        return toIoBuf().toBuffer();
    }
    
    public abstract IoBuf toIoBuf();
	
    public abstract Message parse(byte[] bs);

    public abstract Message parse(ByteBuffer buf);
    
    public abstract Message parse(IoBuf buf);

	public abstract Message clone();
	
	public static Message getMessage(Class<?> clazz) {
		Message message = CACHED.get(clazz);
		if (message == null) {
			message = newInstance(clazz);
			CACHED.putIfAbsent(clazz, message);
		}
		
		return message.clone();
	}
	
	@SuppressWarnings("unchecked")
	private static Message newInstance(Class<?> classType) {
		try {
			Class<? extends Message> typs = (Class<? extends Message>)classType;
			Constructor<? extends Message> constructor = typs.getDeclaredConstructor();
			if (!constructor.isAccessible()) {
				constructor.setAccessible(true);
			}

			return constructor.newInstance();
		} catch (Exception e) {
			throw new RuntimeException("创建实列失败", e);
		}
	}

}

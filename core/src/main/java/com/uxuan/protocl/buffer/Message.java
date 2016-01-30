package com.uxuan.protocl.buffer;

/**
 * message
 *
 * @author liuzhen 
 * Email liuxing521a@163.com 
 * CreateTime 201６-01-04 20:32:12
 */
public abstract class Message {
	
//	static ConcurrentMap<Class<?>, Message> CACHED = new ConcurrentHashMap<Class<?>, Message>();
//
//	protected Message() {
//	}
//
//	public abstract Message readMsg(IoBuf buf);
//
//	public abstract void writeMsg(IoBuf buf);
//
//	public abstract byte[] toArray();
//
//	public abstract ByteBuffer toBuffer();
//	
//	public abstract Message clone();
//	
//	static Message getMsg(Class<? extends Message> clazz) {
//		Message module = CACHED.get(clazz);
//		if (module == null) {
//			module = newInstance(clazz);
//			CACHED.putIfAbsent(clazz, module);
//		}
//		
//		return module.clone();
//	}
//
//	private static Message newInstance(Class<?> classType) {
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

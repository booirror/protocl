package com.uxuan.protocl.buffer;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public interface Eum {

	static final Map<Class<?>, Map<Integer, Enum<?>>> MAP = new HashMap<>();
	
	public int value();
	
	@SuppressWarnings("unchecked")
	public static <T extends Enum<T>> T getEnum(Class<?> clazz, Integer key) {
		if (!Eum.class.isAssignableFrom(clazz)) {
			throw new RuntimeException("unto transform enum must implements Eum...");
		}
		
		Map<Integer, Enum<?>> enumMap = MAP.get(clazz);
		if (enumMap == null) {
			Map<Integer, Enum<?>> tmpMap = new HashMap<>();
			EnumSet<T> set = EnumSet.allOf((Class<T>)clazz);
			for (T e : set) {
				Eum en = (Eum)e;
				tmpMap.put(en.value(), e);
			}
			
			MAP.putIfAbsent(clazz, (enumMap = tmpMap));
		}
		
		return (T) enumMap.get(key);
	}
}

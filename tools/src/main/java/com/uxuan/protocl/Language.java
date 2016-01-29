package com.uxuan.protocl;

/**
 * 定义支持类型
 * 
 * @author liuzhen
 * Email liuxing521a@163.com
 * CreateTime 2016年1月10日 下午8:02:40
 */
public interface Language {
	
	/**
	 * boolean
	 * 
	 * @param wrap 是否获取包装类型
	 * @return 返回boolean 定义类型
	 */
	String BOOL(boolean wrap);
	
	/**
	 * 字节
	 * 
	 * @param wrap 是否获取包装类型
	 * @return 返回字节 定义类型
	 */
	String INT8(boolean wrap);

	/**
	 * 短整型
	 * 
	 * @param wrap 是否获取包装类型
	 * @return 返回短整型 定义类型
	 */
	String INT16(boolean wrap);

	/**
	 * 整型
	 * 
	 * @param wrap 是否获取包装类型
	 * @return 返回整型 定义类型
	 */
	String INT32(boolean wrap);

	/**
	 * 长整型
	 * 
	 * @param wrap 是否获取包装类型
	 * @return 返回长整型 定义类型
	 */
	String INT64(boolean wrap);

	/**
	 * 单精度
	 * 
	 * @param wrap 是否获取包装类型
	 * @return 返回单精度 定义类型
	 */
	String FLOAT(boolean wrap);
	
	/**
	 * 双精度
	 * 
	 * @param wrap 是否获取包装类型
	 * @return 返回双精度 定义类型
	 */
	String DOUBLE(boolean wrap);
	
	/**
	 * 字符串
	 * 
	 * @return 返回字符串 定义类型
	 */
	String STRING();
	
	/**
	 * 有序集合
	 * 
	 * @return 返回有序集合 定义类型
	 */
	String ARRAY();
	
	/**
	 * 无序集合
	 * 
	 * @return 返回无序集合 定义类型
	 */
	String SET();
	
	/**
	 * 字典
	 * 
	 * @return 返回关键字查找 字典类型
	 */
	String MAP();
	
}

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
	 * @return 返回boolean 定义类型
	 */
	Attribute BOOL();
	
	/**
	 * 字节
	 * 
	 * @return 返回字节 定义类型
	 */
	Attribute INT8();

	/**
	 * 短整型
	 * 
	 * @return 返回短整型 定义类型
	 */
	Attribute INT16();

	/**
	 * 整型
	 * 
	 * @return 返回整型 定义类型
	 */
	Attribute INT32();

	/**
	 * 长整型
	 * 
	 * @return 返回长整型 定义类型
	 */
	Attribute INT64();

	/**
	 * 单精度
	 * 
	 * @return 返回单精度 定义类型
	 */
	Attribute FLOAT32();
	
	/**
	 * 双精度
	 * 
	 * @return 返回双精度 定义类型
	 */
	Attribute FLOAT64();
	
	/**
	 * 字符串
	 * 
	 * @return 返回字符串 定义类型
	 */
	Attribute STRING();
	
	/**
	 * 枚举类型
	 * 
	 * @param wrap
	 * @return
	 */
	Attribute ENUM();
	
	/**
	 * 消息类型
	 * 
	 * @param wrap
	 * @return
	 */
	Attribute MESSAGE();
	
	/**
	 * 有序集合
	 * 
	 * @return 返回有序集合 定义类型
	 */
	Attribute ARRAY();
	
	/**
	 * 无序集合
	 * 
	 * @return 返回无序集合 定义类型
	 */
	Attribute SET();
	
	/**
	 * 字典
	 * 
	 * @return 返回关键字查找 字典类型
	 */
	Attribute MAP();
	
}

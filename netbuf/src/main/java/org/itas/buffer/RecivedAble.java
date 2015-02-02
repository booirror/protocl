package org.itas.buffer;

import java.nio.ByteBuffer;

/**
 * 解析接收数据接口
 * @author liuzhen(liuxing521a@gmail.com)
 * @crateTime 2015年2月2日下午3:26:31
 */
public interface RecivedAble {
	
	/**
	 * <p>解析接收到数据<p>
	 * @param buf 接受到的字节流
	 * @return 解析后的对象
	 */
	abstract RecivedAble fromBuffer(ByteBuffer buf);
	
}

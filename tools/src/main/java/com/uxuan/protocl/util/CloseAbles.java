package com.uxuan.protocl.util;

import java.io.Closeable;
import java.io.IOException;

public final class CloseAbles {

	/**
	 * 关闭
	 * 
	 * @param closeAbles
	 */
	public static void close(Closeable...closeAbles) {
		for (Closeable closeAble : closeAbles) {
			close(closeAble);
		}
	}
	
	/**
	 * 关闭
	 * 
	 * @param closeAble
	 */
	public static void close(Closeable closeAble) {
		if (closeAble != null) {
			try {
				closeAble.close();
			} catch (IOException e) {
				
			}
		}
	}
	
}

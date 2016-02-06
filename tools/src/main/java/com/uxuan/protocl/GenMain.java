package com.uxuan.protocl;

import com.uxuan.protocl.module.Protocl;
import com.uxuan.protocl.module.ProtoclFile;
import com.uxuan.soty.parser.ProtoclParser;

public class GenMain {

	public static void main(String[] args) throws Exception {
//
//		String srcPath = "build/classes";// args[0];
		String sdesPath = "./java";// args[1];
//		String cdesPath = "./cpp";
		
		ProtoclParser parser = new ProtoclParser();
		//Protocl protocl =  parser.parse("./build");
		
//		for (ProtoclFile pro : protocl.getProtocls()) {
//			JavaFile javaFile = new JavaFile(pro);
//			javaFile.autoGenMsg(sdesPath);
//		}
	}
}

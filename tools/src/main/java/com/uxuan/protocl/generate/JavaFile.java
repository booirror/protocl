package com.uxuan.protocl.generate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import com.uxuan.protocl.module.ProtoclFile;
import com.uxuan.protocl.module.ProtoclMsg;

public class JavaFile {

	/** 消息文件*/
	private ProtoclFile file;

	public JavaFile(ProtoclFile protoclFile) {
		this.file = protoclFile;
		file.addImport("com.uxuan.protocl.buffer.IoBuf");
		file.addImport("java.nio.ByteBuffer");
	}
	
	public void autoGenMsg(String desPath) throws IOException {
		System.out.println("java begin.");
		StringBuffer msgBuffer = new StringBuffer();
		
		msgBuffer.append("package ").append(file.getPkg()).append(";");
		msgBuffer.append("\n");
		
		for (String imp : file.getImps()) {
			msgBuffer.append("\n");
			msgBuffer.append("import ").append(imp).append(";");
		}
		
		msgBuffer.append("\n\n");
		msgBuffer.append("public class ").append(file.getName()).append(" {");
		
	
		
		
		for (ProtoclMsg msg : file.getMessages()) {
			JavaMsg javaMsg = new JavaMsg(msg);
			
			msgBuffer.append(javaMsg.autuGen());
		}
		msgBuffer.append("\n\n");
		msgBuffer.append(String.format("private %s() {\n}", file.getName()));
		
		msgBuffer.append("\n\n");
		msgBuffer.append("}");
		
		String dir = desPath + "/" + file.getPkg().replace('.', '/');
		
		File wfile = new File(dir);
		if (!wfile.exists()) {
			wfile.mkdirs();
		}
		
		wfile = new File(dir + '/' + file.getName() + ".java");
		System.out.println(wfile.getAbsolutePath());
		if (wfile.exists()) {
			wfile.delete();
		}
		wfile.createNewFile();
		
		try (FileOutputStream output = new FileOutputStream(wfile);
			 OutputStreamWriter outWrite = new OutputStreamWriter(output)) {
			 outWrite.write(msgBuffer.toString());
			 outWrite.flush();
		}
		
		System.out.println("java end.");
	}
	
}

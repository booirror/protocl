package com.godwan.code.lang;

import static com.godwan.code.util.Strings.firstCharLowerCase;
import static com.godwan.code.util.Strings.firstCharUpCase;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import com.godwan.code.ClassInfo;
import com.godwan.code.FileInfo;
import com.godwan.code.util.MsgStatus;

public class JavaFile {

	private FileInfo fileInfo;

	public JavaFile(FileInfo fileInfo) {
		this.fileInfo = fileInfo;
	}

	public void genJava(String desPath) throws IOException {
		System.out.println("java begin.");
		
		StringBuffer javaBuf = new StringBuffer();
		
		StringBuffer msgBuf = new StringBuffer();
		StringBuffer dispatch = new StringBuffer();
		StringBuffer abstrBuf = new StringBuffer();
		
		javaBuf.append("package net;");

		javaBuf.append("\n\n");
		if (fileInfo.hasArray()) {
			javaBuf.append("import java.util.List;\n");
			javaBuf.append("import java.util.LinkedList;\n");
		}
		javaBuf.append("import io.netty.buffer.ByteBuf;\n");
		javaBuf.append("import net.itas.core.io.nio.Message;\n");
		javaBuf.append(String.format("import static net.%sEvent.*;", fileInfo.getFileName(true)));

		javaBuf.append("\n\n");
		javaBuf.append(String.format("public class %s {", fileInfo.getFileName(true)));
		
		javaBuf.append("\n\n");
		javaBuf.append("\tprivate ");
		javaBuf.append(fileInfo.getFileName(true));
		javaBuf.append("() {\n\t} ");
		
		msgBuf.append("package net;\n\n");
		msgBuf.append("import net.itas.core.io.nio.Message;\n\n");
		msgBuf.append("import net.itas.core.User;\n\n");
		msgBuf.append("import net.");
		msgBuf.append(fileInfo.getFileName(true));
		msgBuf.append(".*;\n\n");
		msgBuf.append(String.format("public abstract class %sEvent extends net.itas.core.dispatch.Handle", fileInfo.getFileName(true)));
		msgBuf.append(" {");
		
		msgBuf.append("\n\n\t");
		msgBuf.append("static final short Event_");
		msgBuf.append(fileInfo.getFileName(true));
		msgBuf.append(" = 0x" );
		msgBuf.append(fileInfo.getHeXClassIndex());
		msgBuf.append(";");
		
		
		msgBuf.append("\n\n\t");
		msgBuf.append("@Override");
		msgBuf.append("\n\t");
		msgBuf.append("public final short getEventNum() {");
		msgBuf.append("\n\t\treturn Event_");
		msgBuf.append(fileInfo.getFileName(true));
		msgBuf.append(";\n\t}");

		dispatch.append("\n\n\t@Override\n\tpublic final void dispatch(User user, Message message) throws Exception {");
		dispatch.append("\n\t\tswitch(message.getMethod()) {");
		
		
		JavaStruct struct;
		for (ClassInfo classInfo : fileInfo.getClassList()) {
			struct = new JavaStruct(classInfo);

			javaBuf.append("\n\n");
			javaBuf.append(struct.getClazz());

			msgBuf.append(struct.getMessage());
			
			abstrBuf.append(getDispatchMethod(classInfo));
			
			dispatch.append(getDispatch(classInfo));
		}
		
		
		dispatch.append("\n\t\t}");
		dispatch.append("\n\t}");
		msgBuf.append(abstrBuf.toString());
		msgBuf.append(dispatch.toString());
		msgBuf.append("\n\n}");
		
		javaBuf.append("\n\n");
		javaBuf.append("}");

		File java = new File(desPath + "\\" + fileInfo.getFileName(true) + ".java");
		java.delete();
		java.createNewFile();
		
		File msg = new File(desPath + "\\" + fileInfo.getFileName(true) + "Event.java");
		msg.delete();
		msg.createNewFile();

		OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(java));
		out.write(javaBuf.toString());
		out.flush();
		out.close();

		out = new OutputStreamWriter(new FileOutputStream(msg));
		out.write(msgBuf.toString());

		out.flush();
		out.close();


		System.out.println("java end.");
	}
	
	private String getDispatch(ClassInfo classInfo) {
		if (classInfo.contains(MsgStatus.CLIENT_TO_SERVER)) {
			StringBuffer dispatch = new StringBuffer();
			dispatch.append("\n\t\tcase Event_");
			dispatch.append(classInfo.getClassName(true));
			dispatch.append(": \n\t\t\t");
			dispatch.append(firstCharLowerCase(classInfo.getClassName(false)));
			dispatch.append("(user, ");
			dispatch.append(firstCharUpCase(classInfo.getClassName(false)));
			dispatch.append(".parseFrom(message.getBuffer())); \n\t\t\tbreak;");
			
			return dispatch.toString();
		}
		
		return "";
	}
	
	private String getDispatchMethod(ClassInfo classInfo) {
		if (classInfo.contains(MsgStatus.CLIENT_TO_SERVER)) {
			StringBuffer abstrBuf = new StringBuffer();
			abstrBuf.append("\n\n\t");
			abstrBuf.append("public abstract void ");
			abstrBuf.append(firstCharLowerCase(classInfo.getClassName(false)));
			abstrBuf.append("(User user, ");
			abstrBuf.append(firstCharUpCase(classInfo.getClassName(false)));
			abstrBuf.append(" request) throws Exception;");
			
			return abstrBuf.toString();
		}
		
		return "";
	}
}

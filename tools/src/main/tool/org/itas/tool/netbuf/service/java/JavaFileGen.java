package org.itas.tool.netbuf.service.java;

import static org.itas.tool.netbuf.util.StringUtils.firstCharLowerCase;
import static org.itas.tool.netbuf.util.StringUtils.firstCharUpCase;
import static org.itas.tool.netbuf.util.StringUtils.nextLine;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.itas.tool.netbuf.MsgBody;
import org.itas.tool.netbuf.MsgFile;
import org.itas.tool.netbuf.util.MsgStatus;

public class JavaFileGen {

	/**
	 * 消息文件
	 */
	private MsgFile msgFile;

	public JavaFileGen(MsgFile msgFile) {
		this.msgFile = msgFile;
	}

	public void autoGenMsg(String desPath) throws IOException {
		System.out.println("java begin.");
		StringBuffer msgBuffer = new StringBuffer();
		
		msgBuffer.append(javaPackageName());
		
		msgBuffer.append(javaMsgImports());
		
		msgBuffer.append(nextLine(2, 0));
		msgBuffer.append(String.format("public class %s {", msgFile.getMsgFileName(true)));
		
		msgBuffer.append(nextLine(2, 1));
		msgBuffer.append(String.format("static final byte PREFIX = %s;", msgFile.getFileHexOrder()));
		
		msgBuffer.append(nextLine(2, 1));
		msgBuffer.append(String.format("private %s() {", msgFile.getMsgFileName(true)));
		msgBuffer.append(nextLine(1, 1));
		msgBuffer.append("} ");
		
		
		JavaStruct struct;
		for (MsgBody classInfo : msgFile.getMsgBodys()) {
			struct = new JavaStruct(classInfo);
			
			msgBuffer.append(nextLine(2, 0));
			msgBuffer.append(struct.autoGenStruct());
		}
		
		
		msgBuffer.append(nextLine(2, 0));
		msgBuffer.append("}");
		
		File file = new File(desPath + '/' + msgFile.getPackageName());
		if (!file.exists()) {
			file.mkdirs();
		}
		
		file = new File(desPath + '/' + msgFile.getPackageName() + '/' + msgFile.getMsgFileName(true) + ".java");
		if (file.exists()) {
			file.delete();
		}
		file.createNewFile();
		
		try (FileOutputStream output = new FileOutputStream(file);
			 OutputStreamWriter outWrite = new OutputStreamWriter(output)) {
			 outWrite.write(msgBuffer.toString());
			 outWrite.flush();
		}
		
		System.out.println("java end.");
	}
	
	public void autoGenEvent(String desPath) throws IOException {
		System.out.println("java begin.");
		
		
		
		StringBuffer eventBuffer = new StringBuffer();
		eventBuffer.append(javaPackageName());
		eventBuffer.append(String.format("public abstract class %sEvent<T> extends org.itas.buffer.Dispatch {", msgFile.getMsgFileName(true)));
		

		StringBuffer dispatch = new StringBuffer();
		dispatch.append(nextLine(2, 1));
		dispatch.append("@Override");
		dispatch.append(nextLine(1, 1));
		dispatch.append("public final void dispatch(T value, org.itas.buffer.RecivedAble request) throws Exception {");
		dispatch.append(nextLine(1, 2));
		dispatch.append("switch(request.SUFFIX()) {");
		
		StringBuffer abstrBuf = new StringBuffer();
		
		JavaStruct struct;
		for (MsgBody classInfo : msgFile.getMsgBodys()) {
			struct = new JavaStruct(classInfo);

			abstrBuf.append(struct.autoGenEventMethod());
			
			dispatch.append(getDispatch(classInfo));
		}
		
		dispatch.append(nextLine(1, 2));
		dispatch.append("}");
		dispatch.append(nextLine(1, 1));
		dispatch.append("}");
		
		eventBuffer.append(abstrBuf.toString());
		eventBuffer.append(dispatch.toString());
		eventBuffer.append("\n\n}");
		
		
		File msg = new File(desPath + '/' + msgFile.getPackageName() + '/' + msgFile.getMsgFileName(true) + "Event.java");
		msg.delete();
		msg.createNewFile();


		OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(msg));
		out.write(eventBuffer.toString());

		out.flush();
		out.close();


		System.out.println("java end.");
	}
	
	private String getDispatch(MsgBody classInfo) {
		if (classInfo.isTypeExist(MsgStatus.CLIENT_TO_SERVER)) {
			StringBuffer dispatch = new StringBuffer();
			
			dispatch.append(nextLine(1, 2));
			dispatch.append("case Event_");
			dispatch.append(classInfo.getMsgName(true));
			dispatch.append(": \n\t\t\t");
			dispatch.append(firstCharLowerCase(classInfo.getMsgName(false)));
			dispatch.append("(user, ");
			dispatch.append(firstCharUpCase(classInfo.getMsgName(false)));
			dispatch.append(".parseFrom(message.getBuffer())); \n\t\t\tbreak;");
			
			return dispatch.toString();
		}
		
		return "";
	}
	
	private String javaPackageName() {
		return String.format("package %s;", msgFile.getPackageName().replace('/', '.'));
	}
	
	private String javaMsgImports() {
		StringBuilder builder = new StringBuilder();
		
		builder.append(nextLine(1, 0));
		for (String impt : msgFile.getImports()) {
			builder.append(nextLine(1, 0));
			builder.append(String.format("import %s;", impt.replace('/', '.')));
		}
		
		return builder.toString();
	}
}

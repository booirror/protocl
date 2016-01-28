package com.uxuan.protocl.generate;

import static com.uxuan.protocl.util.StringUtils.nextLine;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;

import com.uxuan.protocl.MsgBody;
import com.uxuan.protocl.MsgField;
import com.uxuan.protocl.MsgFile;

public class JavaFileGen {

	/** 消息文件*/
	private MsgFile msgFile;
	
	/**
	 * 外部导入的包
	 */
	private Set<String> imports;

	public JavaFileGen(MsgFile msgFile) {
		this.msgFile = msgFile;
		this.imports = new HashSet<>();
		
		checkImports();
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
		System.out.println(file.getAbsolutePath());
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
		
		eventBuffer.append(nextLine(1, 0));
		eventBuffer.append(eventImports());
		
		eventBuffer.append(nextLine(2, 0));
		eventBuffer.append(String.format("public abstract class %sEvent<T> extends Service<T> {", msgFile.getMsgFileName(true)));
		

		StringBuffer dispatch = new StringBuffer();
		dispatch.append(nextLine(2, 1));
		dispatch.append("@Override");
		dispatch.append(nextLine(1, 1));
		dispatch.append("public final void dispatch(T data, byte suffix, ByteBuffer buf) throws Exception {");
		dispatch.append(nextLine(1, 2));
		dispatch.append("switch(suffix) {");
		
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
		
		eventBuffer.append(defPREFIXMethod());
		eventBuffer.append(dispatch.toString());
		eventBuffer.append(abstrBuf.toString());
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
	
	String getDispatch(MsgBody classInfo) {
		StringBuffer dispatch = new StringBuffer();

		if (classInfo.isTypeExist(MsgStatus.CLIENT_TO_SERVER)) {
			dispatch.append(nextLine(1, 2));
			dispatch.append(String.format("case %s.SUFFIX:", classInfo.getMsgName(true)));
			dispatch.append(nextLine(1, 3));
			dispatch.append(String.format("%s(data, %s.fromBuffer(buf));", classInfo.getMsgName(false), classInfo.getMsgName(true)));
			dispatch.append(nextLine(1, 3));
			dispatch.append("break;");
		}
		
		return dispatch.toString();
	}
	
	String defPREFIXMethod() {
		StringBuilder builder = new StringBuilder();

		builder.append(nextLine(2, 1));
		builder.append("@Override");
		builder.append(nextLine(1, 1));
		builder.append("public final byte PREFIX() {");
		builder.append(nextLine(1, 2));
		builder.append(String.format("return %s.PREFIX;", msgFile.getMsgFileName(true)));
		builder.append(nextLine(1, 1));
		builder.append("}");
		
		return builder.toString();
	}
	
	private String javaPackageName() {
		return String.format("package %s;", msgFile.getPackageName().replace('/', '.'));
	}
	
	private String javaMsgImports() {
		StringBuilder builder = new StringBuilder();
		
		builder.append(nextLine(1, 0));
		for (String impt : msgFile.getImports()) {
			builder.append(nextLine(1, 0));
			builder.append(String.format("import %s.*;", impt.replace('/', '.')));
		}
		
		
		for (String impt : imports) {
			builder.append(nextLine(1, 0));
			builder.append(String.format("import %s;", impt.replace('/', '.')));
		}
		
		return builder.toString();
	}
	
	private String eventImports() {
		StringBuilder builder = new StringBuilder();
		
		for (String impt : eventImports) {
			builder.append(nextLine(1, 0));
			builder.append(String.format("import %s;", impt.replace('/', '.')));
		}
		
		return builder.toString();
	}
	
	private void checkImports() {
		imports.add("java/nio/ByteBuffer");
		imports.add("org/itas/buffer/BubferBuilder");
		imports.add("org/itas/buffer/AbstractMessage");

		eventImports.add("org/itas/buffer/Service");
		eventImports.add("java/nio/ByteBuffer");
		
		for (MsgBody body : msgFile.getMsgBodys()) {
			if (body.isTypeExist(MsgStatus.CLIENT_TO_SERVER)) {
				imports.add("org/itas/buffer/RecivedAble");
				eventImports.add(msgFile.getPackageName() + "/" + msgFile.getMsgFileName(true) + "/*");
			}
			
			if (body.isTypeExist(MsgStatus.CLIENT_TO_SERVER)) {
				imports.add("org/itas/buffer/SendAble");
			}
			
			for (MsgField field : body.getMsgFields()) {
				if (field.getDefClassType() == MsgFiledType.FieldType.VECTOR) {
					imports.add("java/util/List");
					imports.add("java/util/ArrayList");
				}
			}
		}
	}
}

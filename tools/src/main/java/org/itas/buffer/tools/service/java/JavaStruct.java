package org.itas.buffer.tools.service.java;

import static org.itas.buffer.tools.util.StringUtils.firstCharUpCase;
import static org.itas.buffer.tools.util.StringUtils.nextLine;

import org.itas.buffer.tools.MsgBody;
import org.itas.buffer.tools.MsgField;
import org.itas.buffer.tools.MsgFiledType.FieldType;
import org.itas.buffer.tools.util.MsgStatus;

public class JavaStruct {
	
	private MsgBody classInfo;
	
	public JavaStruct(MsgBody clazz) {
		this.classInfo = clazz;
	}
	
	public String autoGenStruct() {
		StringBuffer classdBuf = new StringBuffer();
		
		classdBuf.append(defineClassBegin());
		
		classdBuf.append(messageHexOrder());

		classdBuf.append(defineField());

		classdBuf.append(getConstructor());

		classdBuf.append(basicOperateMethod());
	        
		classdBuf.append(toByteBufferMethod());
		
		classdBuf.append(fromByteBufferMethod());

		classdBuf.append(readMessageMethod());

		classdBuf.append(writeMessageMethod());
	        
		classdBuf.append(newInstanceMethod());

		classdBuf.append(recivedAbleMethod());

		classdBuf.append(sendAbleMethod());

		classdBuf.append(toStringMethod());

		classdBuf.append(defineClassEnd());
	        
        return classdBuf.toString();
	}
	
	public String autoGenEventMethod() {
		if (classInfo.isTypeExist(MsgStatus.CLIENT_TO_SERVER)) {
			StringBuffer buffer = new StringBuffer();

			buffer.append(nextLine(2, 1));
			buffer.append(String.format("public abstract void %s(T value, %s request);", classInfo.getMsgName(false), classInfo.getMsgName(true)));
		}

		return "";
	}
	
	private String messageHexOrder() {
		if (classInfo.isTypeExist(MsgStatus.CLIENT_TO_SERVER) || classInfo.isTypeExist(MsgStatus.SERVER_TO_CLIENT)) {
			StringBuffer buffer = new StringBuffer();

			buffer.append(nextLine(2, 1));
			buffer.append(String.format("static final byte SUFFIX = %s;", classInfo.getHexOrder()));
			return buffer.toString();
		}

		return "";
	}
	
	private String defineClassBegin() {
		StringBuffer buffer = new StringBuffer();
		
		buffer.append(String.format("public static class %s extends org.itas.buffer.AbstractMessage", classInfo.getMsgName(true)));

		if (classInfo.isTypeExist(MsgStatus.SERVER_TO_CLIENT) || classInfo.isTypeExist(MsgStatus.CLIENT_TO_SERVER)) {
			buffer.append(" implements  ");
			if (classInfo.isTypeExist(MsgStatus.CLIENT_TO_SERVER)) {
				buffer.append(" org.itas.buffer.RecivedAble,");
			}
			if (classInfo.isTypeExist(MsgStatus.SERVER_TO_CLIENT)) {
				buffer.append(" org.itas.buffer.SendAble,");
			}
			
			buffer.deleteCharAt(buffer.length() - 1);
		}
		
		buffer.append("{");
		return buffer.toString();
	}
	
	private String defineClassEnd() {
		return "\n}";
	}
	
	private String defineField() {
		StringBuffer fieldBuf = new StringBuffer();
		fieldBuf.append(nextLine(2, 0));
		
		JavaFieldGen javaField;
		for (MsgField field : classInfo.getMsgFields()) {
			javaField = new JavaFieldGen(classInfo, field);
			
			fieldBuf.append(nextLine(1, 1));
			fieldBuf.append(javaField.defendField());
		}
		
		return fieldBuf.toString();
	}
	
	private String getConstructor() {
		StringBuffer consBuf = new StringBuffer();
		
		consBuf.append(nextLine(2, 1));
		consBuf.append(String.format("private %s() {", classInfo.getMsgName(true)));

		consBuf.append(nextLine(1, 1));
		consBuf.append("}");

		return consBuf.toString();
	}
	
	private String basicOperateMethod() {
		StringBuffer methodBuf = new StringBuffer();

		JavaFieldGen javaField;
		for (MsgField field : classInfo.getMsgFields()) {
			javaField = new JavaFieldGen(classInfo, field);
			
			methodBuf.append(nextLine(2, 0));
			methodBuf.append(javaField.getMethod());

			methodBuf.append(nextLine(2, 0));
			methodBuf.append(javaField.setMethod());
			
			if (field.getDefClassType() == FieldType.VECTOR) {
				methodBuf.append(nextLine(2, 0));
				methodBuf.append(javaField.addMethod());

				methodBuf.append(nextLine(2, 0));
				methodBuf.append(javaField.addAllMethod());
				
				methodBuf.append(nextLine(2, 0));
				methodBuf.append(javaField.setIndexMethod());
			}
		}
		
		return methodBuf.toString();
	}
	
	private String fromByteBufferMethod() {
		StringBuffer methodBuffer = new StringBuffer();

		methodBuffer.append(nextLine(2, 1));
		methodBuffer.append("@Override");
		methodBuffer.append(nextLine(1, 1));
		methodBuffer.append(String.format("protected %s fromByteBuffer(java.nio.ByteBuffer buf) {", classInfo.getMsgName(true)));
		
		JavaFieldGen javaField;
		for (MsgField field : classInfo.getMsgFields()) {
			javaField = new JavaFieldGen(classInfo, field);
			
			methodBuffer.append(nextLine(1, 2));
			methodBuffer.append(String.format("this.%s = ", field.getDefFieldName()));
			if (field.getDefClassType() == FieldType.VECTOR) {
				methodBuffer.append(String.format("readArray(buf, %s.class", javaField.getGenericType()));
	        } else if (field.getDefClassType() == FieldType.MESSAGE) {
	        	methodBuffer.append(String.format("read%s(buf);", firstCharUpCase(field.getDefFieldName())));
	        } else {
	        	methodBuffer.append(String.format("%s.fromBuffer(buf);", firstCharUpCase(javaField.getDefineType())));
	        }
		}
		
		methodBuffer.append(nextLine(2, 2));
		methodBuffer.append("return this;");

		methodBuffer.append(nextLine(1, 1));
		methodBuffer.append("}");
		
		return methodBuffer.toString();
	}
	
	private String toByteBufferMethod() {
		StringBuffer methodBuffer = new StringBuffer();
		
		methodBuffer.append(nextLine(2, 1));
		methodBuffer.append("@Override");
		methodBuffer.append(nextLine(1, 1));
		methodBuffer.append("public void toByteBuffer(org.itas.buffer.BubferBuilder builder) {");
		
		for (MsgField field : classInfo.getMsgFields()) {
			methodBuffer.append(nextLine(1, 2));
			
			if (field.getDefClassType() == FieldType.VECTOR) {
				methodBuffer.append(String.format("writeArray(builder, %s);", field.getDefFieldName()));
			} else if (field.getDefClassType() == FieldType.MESSAGE) {
				methodBuffer.append(String.format("writeMessage(builder, %s);", field.getDefFieldName()));
			} else {
				methodBuffer.append(String.format("write%s(builder, , %s);", firstCharUpCase(field.getDefClassType().def()), field.getDefFieldName()));
			}
		}
		 
		methodBuffer.append(nextLine(1, 1));
		methodBuffer.append("}");
		return methodBuffer.toString();
	}
	
	private String readMessageMethod() {
		StringBuffer buildBuf = new StringBuffer();
		
		buildBuf.append(nextLine(2, 1));
		buildBuf.append("@Override");
		buildBuf.append(nextLine(1, 1));
		buildBuf.append(String.format("protected %s  readMessage(java.nio.ByteBuffer buf) {", classInfo.getMsgName(true)));

		buildBuf.append(nextLine(1, 2));
		buildBuf.append(String.format("return %s.fromBuffer(buf);", classInfo.getMsgName(true)));

		buildBuf.append(nextLine(1, 1));
		buildBuf.append("}");
		
		return buildBuf.toString(); 
	}
	
	private String writeMessageMethod() {
		StringBuffer buildBuf = new StringBuffer();
		
		buildBuf.append(nextLine(2, 1));
		buildBuf.append("@Override");
		buildBuf.append(nextLine(1, 1));
		buildBuf.append("protected void  writeMessage(org.itas.buffer.BubferBuilder builder) {");

		buildBuf.append(nextLine(1, 2));
		buildBuf.append("toByteBuffer(builder);");

		buildBuf.append(nextLine(1, 1));
		buildBuf.append("}");
		
		return buildBuf.toString(); 
	}
	
	private String newInstanceMethod() {
		StringBuffer buildBuf = new StringBuffer();
		
		buildBuf.append(nextLine(2, 1));
		buildBuf.append(String.format("public static %s newBuilder() {", classInfo.getMsgName(true)));

		buildBuf.append(nextLine(1, 2));
		buildBuf.append(String.format("return new %s();", classInfo.getMsgName(true)));

		buildBuf.append(nextLine(1, 1));
		buildBuf.append("}");
		
		return buildBuf.toString(); 
	}
	
	private String recivedAbleMethod() {
		if (!classInfo.isTypeExist(MsgStatus.CLIENT_TO_SERVER)) {
			return "";
		}
		
		StringBuffer buildBuf = new StringBuffer();

		buildBuf.append(nextLine(2, 1));
		buildBuf.append(String.format("public static %s fromBuffer(java.nio.ByteBuffer buf) {", classInfo.getMsgName(true)));
		
		buildBuf.append(nextLine(1, 2));
		buildBuf.append(String.format("%s data = new %s();", classInfo.getMsgName(true), classInfo.getMsgName(true)));
		
		buildBuf.append(nextLine(1, 2));
		buildBuf.append("data.readMessage(buf);");
		
		buildBuf.append(nextLine(1, 2));
		buildBuf.append("return data;");
		
		buildBuf.append(nextLine(1, 1));
		buildBuf.append("}");
		
		return buildBuf.toString(); 
	}
	
	
	private String sendAbleMethod() {
		if (!classInfo.isTypeExist(MsgStatus.SERVER_TO_CLIENT)) {
			return "";
		}
		
		StringBuffer buffer = new StringBuffer();
		
		buffer.append(nextLine(2, 1));
		buffer.append("@Override");
		buffer.append(nextLine(1, 1));
		buffer.append("public java.nio.ByteBuffer toBuffer() {");
		
		buffer.append(nextLine(1, 2));
		buffer.append("org.itas.buffer.BubferBuilder builder = new org.itas.buffer.BubferBuilder(64);");
		buffer.append(nextLine(2, 2));
		buffer.append("builder.addShort((short)0);");
		buffer.append(nextLine(1, 2));
		buffer.append("builder.addShort((short)((PREFIX << 8) | SUFFIX));");
		
		buffer.append(nextLine(1, 2));
		buffer.append("toByteBuffer(builder);");

		buffer.append(nextLine(2, 2));
		buffer.append("java.nio.ByteBuffer buf = builder.toBuffer();");
		buffer.append(nextLine(1, 2));
		buffer.append("builder.replaceShort(0, (short)buf.position());");
		buffer.append(nextLine(1, 2));
		buffer.append("buf.flip();");
		
		buffer.append("return buf;");
		buffer.append(nextLine(1, 1));
		buffer.append("}");
	
		return buffer.toString();
	}
	
	private String toStringMethod() {
		StringBuffer strBuf = new StringBuffer();
		
		
		strBuf.append(nextLine(2, 1));
		strBuf.append("@Override");
		
		strBuf.append(nextLine(1, 1));
		strBuf.append("public String toString() {");
		
		strBuf.append(nextLine(1, 2));
		strBuf.append("StringBuffer strBuf = new StringBuffer();");
		
		strBuf.append(nextLine(1, 2));
		strBuf.append("strBuf.append(\"").append(classInfo.getMsgName(true)).append("{\");");
		
		JavaFieldGen javaField;
		for (MsgField field : classInfo.getMsgFields()) {
			javaField = new JavaFieldGen(classInfo, field);
			
			if (field.getDefClassType() == FieldType.VECTOR) {
				strBuf.append(nextLine(1, 2));
				strBuf.append(String.format("if (%s  != null) {", field.getDefFieldName()));
				
				strBuf.append(nextLine(1, 3));
				strBuf.append("strBuf.append(\"\\n").append(field.getDefFieldName()).append(":\");");

				strBuf.append(nextLine(1, 3));
				strBuf.append(String.format("for (%s data : %s) {", javaField.getGenericType(), field.getDefFieldName()));

				strBuf.append(nextLine(1, 4));
				strBuf.append("strBuf.append(data).append(\",\\n\");");
				
				strBuf.append(nextLine(1, 3));
				strBuf.append("}");

				strBuf.append(nextLine(1, 2));
				strBuf.append("}");

			} else {
				strBuf.append(nextLine(1, 2));
				strBuf.append("strBuf.append(\"\\n\\t");
				strBuf.append(field.getDefFieldName());
				strBuf.append(":\").append(");
				strBuf.append(field.getDefFieldName());
				strBuf.append(");");
			}
		 }
		
		strBuf.append(nextLine(1, 2));
		strBuf.append("strBuf.append(\"\\n}\");");
		 
		strBuf.append(nextLine(2, 2));
		strBuf.append("return strBuf.toString();");
		
		strBuf.append(nextLine(1, 1));
		strBuf.append("}");
		return strBuf.toString();
	}
	

}

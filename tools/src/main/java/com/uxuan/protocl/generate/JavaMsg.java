package com.uxuan.protocl.generate;

import com.uxuan.protocl.ProtoclAttr;
import com.uxuan.protocl.ProtoclMsg;

public class JavaMsg {
	
	private ProtoclMsg protoclMsg;
	
	public JavaMsg(ProtoclMsg protoclMsg) {
		this.protoclMsg = protoclMsg;
	}
	
	public String autuGen() {
		return protoclMsg.isEnum() ? genEnum() : genMsg();
	}
	
	private String genEnum() {
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("\n\n");
		buffer.append(String.format("public static enum %s {", protoclMsg.getName()));
		
		for (ProtoclAttr attr : protoclMsg.getAttributes()) {
			JavaAttr javaAttr = new JavaAttr(attr);
			buffer.append(javaAttr.genAttribute());
		}
		
		buffer.append("\n");
		buffer.append(";");
		buffer.append("\n\n\t");
		buffer.append(String.format("private %s() {\n\t}", protoclMsg.getName()));
		
		buffer.append("\n\n\t");
		buffer.append("public abstract int value();");
		
		buffer.append("\n\n");
		buffer.append("}");
		
        return buffer.toString();
	}
	
	private String genMsg() {
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("\n\n");
		buffer.append(String.format("public static class %s extends com.uxuan.protocl.buffer.Message {", 
			protoclMsg.getName()));
		

		StringBuffer fieldBuf = new StringBuffer();
		StringBuffer methdBuf = new StringBuffer();
		
		for (ProtoclAttr attr : protoclMsg.getAttributes()) {
			JavaAttr javaAttr = new JavaAttr(attr);
			fieldBuf.append(javaAttr.genAttribute());
			methdBuf.append(javaAttr.genMethod());
		}
		
		buffer.append("\n");
		buffer.append(fieldBuf.toString());
		
		buffer.append("\n\n\t");
		buffer.append(String.format("private %s() {\n\t}", protoclMsg.getName()));
	        
		buffer.append(methdBuf.toString());
//		classdBuf.append(readMessageMethod());

//		classdBuf.append(writeMessageMethod());
	        
//		classdBuf.append(newInstanceMethod());

//		classdBuf.append(recivedAbleMethod());

//		classdBuf.append(sendAbleMethod());

//		classdBuf.append(toStringMethod());
	        
		buffer.append("\n");
		buffer.append("}");
		
        return buffer.toString();
	}
	
//	
//	String readMessageMethod() {
//		StringBuffer buffer = new StringBuffer();
//
//		buffer.append(nextLine(2, 1));
//		buffer.append("@Override");
//		buffer.append(nextLine(1, 1));
//		buffer.append(String.format("public %s readMsg(ByteBuffer buf) {", classInfo.getMsgName(true)));
//		
//		JavaFieldGen javaField;
//		for (MsgField field : classInfo.getMsgFields()) {
//			javaField = new JavaFieldGen(classInfo, field);
//			
//			buffer.append(nextLine(1, 2));
//			if (field.getDefClassType() == FieldType.VECTOR) {
//				buffer.append(String.format("this.%s = readArray(%s.class, buf);", 
//						field.getDefFieldName(), javaField.getVectorGenericClassTypeName()));
//	        } else if (field.getDefClassType() == FieldType.MESSAGE) {
//	        	buffer.append(String.format("this.%s = %s.newBuilder().readMsg(buf);", 
//	        			field.getDefFieldName(), firstCharUpCase(field.getDefFieldName())));
//	        } else {
//	        	buffer.append(String.format("this.%s = read%s(buf);", 
//	        			field.getDefFieldName(), firstCharUpCase(field.getDefClassTypeName())));
//	        }
//		}
//		
//		buffer.append(nextLine(1, 2));
//		buffer.append("return this;");
//
//		buffer.append(nextLine(1, 1));
//		buffer.append("}");
//		
//		return buffer.toString(); 
//	}
//	
//	String writeMessageMethod() {
//		StringBuffer buffer = new StringBuffer();
//		
//		buffer.append(nextLine(2, 1));
//		buffer.append("@Override");
//		buffer.append(nextLine(1, 1));
//		buffer.append("public void writeMsg(BubferBuilder builder) {");
//		
//		for (MsgField field : classInfo.getMsgFields()) {
//			
//			buffer.append(nextLine(1, 2));
//			if (field.getDefClassType() == FieldType.VECTOR) {
//				buffer.append(String.format("writeArray(builder, this.%s);", field.getDefFieldName()));
//	        } else if (field.getDefClassType() == FieldType.MESSAGE) {
//	        	buffer.append(String.format("this.%s.writeMsg(builder);", field.getDefFieldName()));
//	        } else {
//	        	buffer.append(String.format("write%s(builder, this.%s);", firstCharUpCase(field.getDefClassTypeName()), field.getDefFieldName()));
//	        }
//		}
//		
//		buffer.append(nextLine(1, 1));
//		buffer.append("}");
//		
//		
//		return buffer.toString(); 
//	}
//	
//	String newInstanceMethod() {
//		StringBuffer buildBuf = new StringBuffer();
//		
//		buildBuf.append(nextLine(2, 1));
//		buildBuf.append(String.format("public static %s newBuilder() {", classInfo.getMsgName(true)));
//
//		buildBuf.append(nextLine(1, 2));
//		buildBuf.append(String.format("return new %s();", classInfo.getMsgName(true)));
//
//		buildBuf.append(nextLine(1, 1));
//		buildBuf.append("}");
//		
//		return buildBuf.toString(); 
//	}
//	
//	String recivedAbleMethod() {
//		if (!classInfo.isTypeExist(MsgStatus.CLIENT_TO_SERVER)) {
//			return "";
//		}
//		
//		StringBuffer buildBuf = new StringBuffer();
//
//		buildBuf.append(nextLine(2, 1));
//		buildBuf.append(String.format("public static %s fromBuffer(ByteBuffer buf) {", classInfo.getMsgName(true)));
//		
//		buildBuf.append(nextLine(1, 2));
//		buildBuf.append("return newBuilder().readMsg(buf);");
//		
//		buildBuf.append(nextLine(1, 1));
//		buildBuf.append("}");
//		
//		return buildBuf.toString(); 
//	}
//	
//	
//	String sendAbleMethod() {
//		if (!classInfo.isTypeExist(MsgStatus.SERVER_TO_CLIENT)) {
//			return "";
//		}
//		
//		StringBuffer buffer = new StringBuffer();
//		
//		buffer.append(nextLine(2, 1));
//		buffer.append("@Override");
//		buffer.append(nextLine(1, 1));
//		buffer.append("public ByteBuffer toBuffer() {");
//		
//		buffer.append(nextLine(1, 2));
//		buffer.append("BubferBuilder builder = new BubferBuilder(64);");
//		buffer.append(nextLine(2, 2));
//		buffer.append("builder.addShort((short)0);");
//		buffer.append(nextLine(1, 2));
//		buffer.append("builder.addShort((short)((PREFIX << 8) | SUFFIX));");
//		
//		buffer.append(nextLine(1, 2));
//		buffer.append("writeMsg(builder);");
//
//		buffer.append(nextLine(2, 2));
//		buffer.append("ByteBuffer buf = builder.toBuffer();");
//		buffer.append(nextLine(1, 2));
//		buffer.append("builder.replaceShort(0, (short)buf.position());");
//		buffer.append(nextLine(1, 2));
//		buffer.append("buf.flip();");
//		
//		buffer.append(nextLine(2, 2));
//		buffer.append("return buf;");
//		buffer.append(nextLine(1, 1));
//		buffer.append("}");
//	
//		return buffer.toString();
//	}
//	
//	private String toStringMethod() {
//		StringBuffer strBuf = new StringBuffer();
//		
//		
//		strBuf.append(nextLine(2, 1));
//		strBuf.append("@Override");
//		
//		strBuf.append(nextLine(1, 1));
//		strBuf.append("public String toString() {");
//		
//		strBuf.append(nextLine(1, 2));
//		strBuf.append("StringBuffer strBuf = new StringBuffer();");
//		
//		strBuf.append(nextLine(2, 2));
//		strBuf.append("strBuf.append(\"").append(classInfo.getMsgName(true)).append("{\");");
//		
//		JavaFieldGen javaField;
//		for (MsgField field : classInfo.getMsgFields()) {
//			javaField = new JavaFieldGen(classInfo, field);
//			
//			if (field.getDefClassType() == FieldType.VECTOR) {
//				strBuf.append(nextLine(1, 2));
//				strBuf.append(String.format("if (%s  != null) {", field.getDefFieldName()));
//				
//				strBuf.append(nextLine(1, 3));
//				strBuf.append("strBuf.append(\"\\n").append(field.getDefFieldName()).append(":\");");
//
//				strBuf.append(nextLine(1, 3));
//				strBuf.append(String.format("for (%s data : %s) {", javaField.getVectorGenericClassTypeName(), field.getDefFieldName()));
//
//				strBuf.append(nextLine(1, 4));
//				strBuf.append("strBuf.append(data).append(\",\\n\");");
//				
//				strBuf.append(nextLine(1, 3));
//				strBuf.append("}");
//
//				strBuf.append(nextLine(1, 2));
//				strBuf.append("}");
//
//			} else {
//				strBuf.append(nextLine(1, 2));
//				strBuf.append("strBuf.append(\"\\n\\t");
//				strBuf.append(field.getDefFieldName());
//				strBuf.append(":\").append(");
//				strBuf.append(field.getDefFieldName());
//				strBuf.append(");");
//			}
//		 }
//		
//		strBuf.append(nextLine(1, 2));
//		strBuf.append("strBuf.append(\"\\n}\");");
//		 
//		strBuf.append(nextLine(2, 2));
//		strBuf.append("return strBuf.toString();");
//		
//		strBuf.append(nextLine(1, 1));
//		strBuf.append("}");
//		return strBuf.toString();
//	}
	
//	@Override
//	public String toString() {
//		return classInfo.getMsgName(true);
//	}

}

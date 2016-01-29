//package com.uxuan.protocl.generate;
//
//import static com.uxuan.protocl.util.StringUtils.firstCharUpCase;
//import static com.uxuan.protocl.util.StringUtils.nextLine;
//
//import com.uxuan.protocl.MsgBody;
//import com.uxuan.protocl.MsgField;
//import com.uxuan.protocl.MsgFiledType.FieldType;
//import com.uxuan.protocl.util.MsgStatus;
//
//public class JavaStruct {
//	
//	private MsgBody classInfo;
//	
//	public JavaStruct(MsgBody clazz) {
//		this.classInfo = clazz;
//	}
//	
//	public String autoGenStruct() {
//		StringBuffer classdBuf = new StringBuffer();
//		
//		classdBuf.append(defineClassBegin());
//		
//		classdBuf.append(messageHexOrder());
//
//		classdBuf.append(defineField());
//
//		classdBuf.append(defConstructor());
//
//		classdBuf.append(defSUFFIXMethod());
//
//		classdBuf.append(defGetSetAddMethod());
//	        
//		classdBuf.append(readMessageMethod());
//
//		classdBuf.append(writeMessageMethod());
//	        
//		classdBuf.append(newInstanceMethod());
//
//		classdBuf.append(recivedAbleMethod());
//
//		classdBuf.append(sendAbleMethod());
//
//		classdBuf.append(toStringMethod());
//
//		classdBuf.append(defineClassEnd());
//	        
//        return classdBuf.toString();
//	}
//	
//	public String autoGenEventMethod() {
//		StringBuffer buffer = new StringBuffer();
//
//		if (classInfo.isTypeExist(MsgStatus.CLIENT_TO_SERVER)) {
//
//			buffer.append(nextLine(2, 1));
//			buffer.append(String.format("public abstract void %s(T value, %s request);", classInfo.getMsgName(false), classInfo.getMsgName(true)));
//		}
//
//		return buffer.toString();
//	}
//	
//	private String messageHexOrder() {
//		if (classInfo.isTypeExist(MsgStatus.CLIENT_TO_SERVER) || classInfo.isTypeExist(MsgStatus.SERVER_TO_CLIENT)) {
//			StringBuffer buffer = new StringBuffer();
//
//			buffer.append(nextLine(2, 1));
//			buffer.append(String.format("static final byte SUFFIX = %s;", classInfo.getHexOrder()));
//			return buffer.toString();
//		}
//
//		return "";
//	}
//	
//	private String defineClassBegin() {
//		StringBuffer buffer = new StringBuffer();
//		
//		buffer.append(String.format("public static class %s extends AbstractMessage", classInfo.getMsgName(true)));
//
//		if (classInfo.isTypeExist(MsgStatus.SERVER_TO_CLIENT) || classInfo.isTypeExist(MsgStatus.CLIENT_TO_SERVER)) {
//			buffer.append(" implements");
//			if (classInfo.isTypeExist(MsgStatus.CLIENT_TO_SERVER)) {
//				buffer.append(" RecivedAble,");
//			}
//			if (classInfo.isTypeExist(MsgStatus.SERVER_TO_CLIENT)) {
//				buffer.append(" SendAble,");
//			}
//			
//			buffer.deleteCharAt(buffer.length() - 1);
//		}
//		
//		buffer.append("{");
//		return buffer.toString();
//	}
//	
//	private String defineClassEnd() {
//		return "\n}";
//	}
//	
//	private String defineField() {
//		StringBuffer fieldBuf = new StringBuffer();
//		fieldBuf.append(nextLine(1, 0));
//		
//		JavaFieldGen javaField;
//		for (MsgField field : classInfo.getMsgFields()) {
//			javaField = new JavaFieldGen(classInfo, field);
//			
//			fieldBuf.append(nextLine(1, 1));
//			fieldBuf.append(javaField.defendField());
//		}
//		
//		return fieldBuf.toString();
//	}
//	
//	private String defConstructor() {
//		StringBuffer consBuf = new StringBuffer();
//		
//		consBuf.append(nextLine(2, 1));
//		consBuf.append(String.format("private %s() {", classInfo.getMsgName(true)));
//
//		consBuf.append(nextLine(1, 1));
//		consBuf.append("}");
//
//		return consBuf.toString();
//	}
//	
//	private String defGetSetAddMethod() {
//		StringBuffer methodBuf = new StringBuffer();
//
//		JavaFieldGen javaField;
//		for (MsgField field : classInfo.getMsgFields()) {
//			javaField = new JavaFieldGen(classInfo, field);
//			
//			methodBuf.append(nextLine(2, 0));
//			methodBuf.append(javaField.getMethod());
//
//			methodBuf.append(nextLine(2, 0));
//			methodBuf.append(javaField.setMethod());
//			
//			if (field.getDefClassType() == FieldType.VECTOR) {
//				methodBuf.append(nextLine(2, 0));
//				methodBuf.append(javaField.addVectorMethod());
//
//				methodBuf.append(nextLine(2, 0));
//				methodBuf.append(javaField.addAllVectorMethod());
//				
//				methodBuf.append(nextLine(2, 0));
//				methodBuf.append(javaField.setVectorMethod());
//			}
//		}
//		
//		return methodBuf.toString();
//	}
//	
//	String defSUFFIXMethod() {
//		StringBuilder builder = new StringBuilder();
//
//		if (classInfo.isTypeExist(MsgStatus.CLIENT_TO_SERVER) || classInfo.isTypeExist(MsgStatus.SERVER_TO_CLIENT)) {
//			builder.append(nextLine(2, 1));
//			builder.append("@Override");
//			builder.append(nextLine(1, 1));
//			builder.append("public final byte SUFFIX() {");
//			builder.append(nextLine(1, 2));
//			builder.append("return SUFFIX;");
//			builder.append(nextLine(1, 1));
//			builder.append("}");
//		}
//		
//		return builder.toString();
//	}
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
//	
//	@Override
//	public String toString() {
//		return classInfo.getMsgName(true);
//	}
//
//}

package com.uxuan.protocl.generate;

import static com.uxuan.protocl.util.StringUtils.firstCharUpCase;

import com.uxuan.protocl.SupportType;
import com.uxuan.protocl.SupportType.FieldType;
import com.uxuan.protocl.module.ProtoclAttr;
import com.uxuan.protocl.module.ProtoclMsg;

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
		buffer.append(String.format("public static enum %s implements com.uxuan.protocl.buffer.Eum {", protoclMsg.getName()));
		
		for (ProtoclAttr attr : protoclMsg.getAttributes()) {
			JavaAttr javaAttr = new JavaAttr(attr);
			buffer.append(javaAttr.genAttribute());
		}
		
		buffer.append("\n\t");
		buffer.append(";");
		buffer.append("\n\n\t");
		buffer.append(String.format("private %s() {\n\t}", protoclMsg.getName()));
		
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
		
		//属性生成
		buffer.append("\n");
		buffer.append(fieldBuf.toString());
		
		//构造函数
		buffer.append("\n\n\t");
		buffer.append(String.format("private %s() {\n\t}", protoclMsg.getName()));
	        
		//属性操作方法
		buffer.append(methdBuf.toString());
		
		// 转IoBuf
		buffer.append(genToIoBufMethod());
		// 转byteBuffer
		buffer.append(genToByteBufMethod());
		// 转byte array
		buffer.append(genToByteArrayMethod());
		
		// 从Iobuf解析
		buffer.append(genParseIoBufMethod());
		// 从byteBuffer 解析
		buffer.append(genParseByteBufferMethod());
		// 从byte Array 解析
		buffer.append(genParseArrayMethod());
	        
		// newBuilder方法
		buffer.append(genNewBuilderMethod());
		
		// clone方法
		buffer.append(genCloneMethod());
		
		// 静态从Iobuf解析
		buffer.append(genStaticParseFromIoBuf());
		// 静态从byteBuffer 解析
		buffer.append(genStaticParseFromByteBuffer());
		// 静态从byte Array 解析
		buffer.append(genStaticParseByteArray());

//		classdBuf.append(recivedAbleMethod());

//		classdBuf.append(sendAbleMethod());

//		classdBuf.append(toStringMethod());
	        
		buffer.append("\n");
		buffer.append("}");
		
        return buffer.toString();
	}
	
	private String genToIoBufMethod() {
		StringBuilder buf = new StringBuilder();
		
		buf.append("\n\n\t");
		buf.append("@Override");
		buf.append("\n\t");
		buf.append("public IoBuf toIoBuf() {");
		buf.append("\n\t\t");
		buf.append("IoBuf buf = IoBuf.allocate(64);");
		
		for (ProtoclAttr attr : protoclMsg.getAttributes()) {
			buf.append("\n\t\t");
			
			FieldType fieldType = SupportType.isType(attr.getDefType());
			if (FieldType.ENUM == fieldType) {
				buf.append("if (").append(attr.getAttrName()).append(" != null) {");
				buf.append("\n\t\t\t");
				buf.append("buf.writeInt8(1);");
				buf.append("\n\t\t\t");
				buf.append("buf.writeEnum(this.").append(attr.getAttrName()).append(");");
				buf.append("\n\t\t");
				buf.append("} else {");
				buf.append("\n\t\t\t");
				buf.append("buf.writeInt8(0);");
				buf.append("\n\t\t");
				buf.append("}");
				buf.append("buf.writeEnum(this.").append(attr.getAttrName()).append(");");
			} else if (FieldType.FIXED == fieldType) {
				buf.append(String.format("buf.write%s(this.%s);", firstCharUpCase(attr.getDefType().getDefType()), attr.getAttrName()));
			} else {
				buf.append("if (").append(attr.getAttrName()).append(" != null) {");
				buf.append("\n\t\t\t");
				buf.append("buf.writeInt8(2);");
				buf.append("\n\t\t\t");
				buf.append("buf.writeByte(this.").append(attr.getAttrName()).append(".toArray());");
				buf.append("\n\t\t");
				buf.append("} else {");
				buf.append("\n\t\t\t");
				buf.append("buf.writeInt8(0);");
				buf.append("\n\t\t");
				buf.append("}");
			}
		}
		
		buf.append("\n\t\t");
		buf.append("return buf;");
		buf.append("\n\t");
		buf.append("}");
		
		
		return buf.toString(); 
	}
	
	
	private String genToByteBufMethod() {
		StringBuffer buf = new StringBuffer();

		buf.append("\n\n\t");
		buf.append("@Override");
		buf.append("\n\t");
		buf.append("public ByteBuffer toBuffer() {");
		buf.append("\n\t\t");
		buf.append("return toIoBuf().toBuffer();");
		buf.append("\n\t");
		buf.append("}");
		
		return buf.toString(); 
	}
	
	private String genToByteArrayMethod() {
		StringBuffer buf = new StringBuffer();

		buf.append("\n\n\t");
		buf.append("@Override");
		buf.append("\n\t");
		buf.append("public byte[] toArray() {");
		buf.append("\n\t\t");
		buf.append("return toIoBuf().toArray();");
		buf.append("\n\t");
		buf.append("}");
		
		return buf.toString(); 
	}
	
	private String genParseIoBufMethod() {
		StringBuffer buf = new StringBuffer();

		buf.append("\n\n\t");
		buf.append("@Override");
		buf.append("\n\t");
		buf.append(String.format(" public %s parse(IoBuf buf) {", protoclMsg.getName()));
		
		int index = 0;
		for (ProtoclAttr attr : protoclMsg.getAttributes()) {
			buf.append("\n\t\t");
			index ++;
			
			FieldType fieldType = SupportType.isType(attr.getDefType());
			if (FieldType.FIXED == fieldType) {
				buf.append("this.").append(attr.getAttrName()).append(" = buf.read").append(firstCharUpCase(attr.getDefType().getDefType())).append("();");
				continue;
			} 
			
			buf.append("int types" + index + " = buf.readInt8();");
			buf.append("\n\t\t");
			buf.append("if (types" + index + " == 1) {");
			buf.append("\n\t\t\t");
			buf.append("this.").append(attr.getAttrName()).append(" = buf.readEnum(").append(attr.getDefType().getDefType()).append(".class, buf.readInt32());");
			buf.append("\n\t\t");
			buf.append("} else if (types" + index + " == 2) {");
			buf.append("\n\t\t\t");
			buf.append("this.").append(attr.getAttrName()).append(" = com.uxuan.protocl.buffer.Message.getMessage(").append(attr.getDefType().getDefType()).append(").parse(buf)");
			buf.append("\n\t\t");
			buf.append("}");
			
			
		}
		
		buf.append("\n\t");
		buf.append("}");
		
		return buf.toString(); 
	}
	
	private String genParseByteBufferMethod() {
		StringBuffer buf = new StringBuffer();

		buf.append("\n\n\t");
		buf.append("@Override");
		buf.append("\n\t");
		buf.append(String.format(" public %s parse(ByteBuffer buf) {", protoclMsg.getName()));
		buf.append("\n\t\t");
		buf.append("return parse(IoBuf.wrap(buf));");
		buf.append("\n\t");
		buf.append("}");
		
		return buf.toString(); 
	}
	
	private String genParseArrayMethod() {
		StringBuffer buf = new StringBuffer();

		buf.append("\n\n\t");
		buf.append("@Override");
		buf.append("\n\t");
		buf.append(String.format(" public %s parse(byte[] bytes) {", protoclMsg.getName()));
		buf.append("\n\t\t");
		buf.append("return parse(IoBuf.wrap(bytes));");
		buf.append("\n\t");
		buf.append("}");
		
		return buf.toString(); 
	}
	
	String genNewBuilderMethod() {
		StringBuffer buildBuf = new StringBuffer();
		
		buildBuf.append("\n\n\t");
		buildBuf.append(String.format("public static %s newBuilder() {", protoclMsg.getName()));
		
		buildBuf.append("\n\t\t");
		buildBuf.append(String.format("return new %s();", protoclMsg.getName()));
		
		buildBuf.append("\n\t");
		buildBuf.append("}");
		
		return buildBuf.toString(); 
	}
	
	String genCloneMethod() {
		StringBuffer buildBuf = new StringBuffer();
		
		buildBuf.append("\n\n\t");
		buildBuf.append(String.format("public %s clone() {", protoclMsg.getName()));

		buildBuf.append("\n\t\t");
		buildBuf.append("return newBuilder();");

		buildBuf.append("\n\t");
		buildBuf.append("}");
		
		return buildBuf.toString(); 
	}
	
	String genStaticParseByteArray() {
		StringBuffer buildBuf = new StringBuffer();
		
		buildBuf.append("\n\n\t");
		buildBuf.append(String.format("public static %s parseFrom(byte[] bs) {", protoclMsg.getName()));
		
		buildBuf.append("\n\t\t");
		buildBuf.append("return newBuilder().parse(bs);");
		
		buildBuf.append("\n\t");
		buildBuf.append("}");
		
		return buildBuf.toString(); 
	}
	
	String genStaticParseFromByteBuffer() {
		StringBuffer buildBuf = new StringBuffer();
		
		buildBuf.append("\n\n\t");
		buildBuf.append(String.format("public static %s parseFrom(ByteBuffer buf) {", protoclMsg.getName()));
		
		buildBuf.append("\n\t\t");
		buildBuf.append("return newBuilder().parse(buf);");
		
		buildBuf.append("\n\t");
		buildBuf.append("}");
		
		return buildBuf.toString(); 
	}
	
	String genStaticParseFromIoBuf() {
		StringBuffer buildBuf = new StringBuffer();
		
		buildBuf.append("\n\n\t");
		buildBuf.append(String.format("public static %s parseFrom(IoBuf buf) {", protoclMsg.getName()));
		
		buildBuf.append("\n\t\t");
		buildBuf.append("return newBuilder().parse(buf);");
		
		buildBuf.append("\n\t");
		buildBuf.append("}");
		
		return buildBuf.toString(); 
	}
	
	
//	private String toStringMethod() {
//		StringBuffer buf = new StringBuffer();
//		
//		buf.append("\n\n\t");
//		buf.append("@Override");
//		
//		buf.append("\n\t");
//		buf.append("public String toString() {");
//		
//		buf.append("\n\t\t");
//		buf.append("StringBuffer strBuf = new StringBuffer();");
//		
//		buf.append("\n\n\t\t");
//		buf.append("strBuf.append(\"").append(protoclMsg.getName()).append("{\");");
//		
//		for (ProtoclAttr field : protoclMsg.getAttributes()) {
//			
//			if (field.getDefClassType() == FieldType.VECTOR) {
//				buf.append("\n\t\t");
//				buf.append(String.format("if (%s  != null) {", field.getDefFieldName()));
//				
//				buf.append("\n\t\t\t");
//				buf.append("strBuf.append(\"\\n").append(field.getDefFieldName()).append(":\");");
//
//				buf.append("\n\t\t\t");
//				buf.append(String.format("for (%s data : %s) {", javaField.getVectorGenericClassTypeName(), field.getDefFieldName()));
//
//				buf.append("\n\t\t\t\t");
//				buf.append("strBuf.append(data).append(\",\\n\");");
//				
//				buf.append("\n\t\t\t");
//				buf.append("}");
//
//				buf.append("\n\t\t");
//				buf.append("}");
//
//			} else {
//				buf.append("\n\t\t");
//				buf.append("strBuf.append(\"\\n\\t");
//				buf.append(field.getDefFieldName());
//				buf.append(":\").append(");
//				buf.append(field.getDefFieldName());
//				buf.append(");");
//			}
//		 }
//		
//		buf.append("\n\t\t");
//		buf.append("strBuf.append(\"\\n}\");");
//		 
//		buf.append("\n\n\t\t");
//		buf.append("return strBuf.toString();");
//		
//		buf.append("\n\t");
//		buf.append("}");
//		return buf.toString();
//	}
	

}

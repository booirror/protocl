package com.uxuan.protocl.generate;

import static com.uxuan.protocl.util.StringUtils.firstCharUpCase;

import java.util.List;

import com.uxuan.protocl.ProtoclAttr;
import com.uxuan.protocl.ProtoclAttr.AttrType;
import com.uxuan.protocl.SupportType;

public class JavaAttr extends SupportType {

	/** 消息body*/
	private final ProtoclAttr attr;
	
	/** 生成*/
	private final AttrGen attrGen;
	
	public JavaAttr(ProtoclAttr attr) {
		super(JavaLanguage.class);
		this.attr = attr;
		this.attrGen = attr.isEnum() ?
			new EnumAttr() : new MsgAttr();
	}
	
	interface AttrGen {
		
		String genAttribute();
		
		String genMethod();
	}
	
	public String genAttribute() {
		return attrGen.genAttribute();
	}
	
	public String genMethod() {
		return attrGen.genMethod();
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(genAttribute());
		builder.append(genMethod());
		
		return builder.toString();
	}
	
	private class EnumAttr implements AttrGen {

		public String genAttribute() {
			StringBuilder buf = new StringBuilder();
			
			buf.append("\n\t");
			buf.append(attr.getAttrName()).append(" {");
			buf.append("\n\t\t");
			buf.append("public int value() {");
			buf.append("\n\t\t\t");
			buf.append("return ").append(attr.getAttrIndex()).append(";");
			buf.append("\n\t\t");
			buf.append("}");
			buf.append("\n\t");
			buf.append("},");
			
			return buf.toString();
		}
		
		public String genMethod() {
			return "";
		}
		
		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append(genAttribute());
			builder.append(genMethod());
			
			return builder.toString();
		}
	}
	
	private class MsgAttr implements AttrGen {

		public String genAttribute() {
			StringBuilder buf = new StringBuilder();
			
			buf.append("\n\t");
			buf.append(String.format("private %s %s;", getFullDefinType(), attr.getAttrName()));
			
			return  buf.toString();
		}
		
		public String genMethod() {
			StringBuilder builder = new StringBuilder();
			builder.append(genGetMethod());
			builder.append(genSetMethod());
			
			List<String> genericList = attr.getDefType().getGenericTypes();
			if (genericList.size() == 1) {
				builder.append(genAddMethod());
				builder.append(genAddAllMethod());
				if ("java.util.List".equals(definBasicType())) {
					builder.append(genSetIndexMethod());
				}
			} 

			if (genericList.size() == 2){
				builder.append(genPutMethod());
				builder.append(genPutAllMethod());
			}
			
			return builder.toString();
		}
		
		/***
		 * 定义属性名
		 * 
		 * @return
		 */
		private String getDefName() {
			return attr.getAttrName();
		}
		
		/**
		 * 定义属性类型
		 * 
		 * @return
		 */
		private String getFullDefinType() {
			AttrType type = attr.getDefType();
			
			if (type.getGenericTypes().isEmpty()) {
				return basicType(type.getDefType());
			} 
			
			if (type.getGenericTypes().size() == 1) {
				return String.format("%s<%s>", 
					basicType(type.getDefType()), getGenericType(0));
			} 

			return String.format("%s<%s, %s>", 
				basicType(type.getDefType()), getGenericType(0), getGenericType(1));
		}
		
		private String definBasicType() {
			return basicType(attr.getDefType().getDefType());
		}
		
		private String definWrapType() {
			return wrapType(attr.getDefType().getDefType());
		}
		
		private String getGenericType(int index) {
			return wrapType(attr.getDefType().getGenericTypes().get(index));
		}
		
		private String getMsgName() {
			return attr.getMsgName();
		}

		private String genGetMethod() {
			StringBuffer methodBuf = new StringBuffer();
			
			methodBuf.append("\n\n\t");
			methodBuf.append(String.format("public %s get%s() {", getFullDefinType(), firstCharUpCase(getDefName())));
			
			methodBuf.append("\n\t\t");
			methodBuf.append(String.format("return this.%s;", getDefName()));
			
			methodBuf.append("\n\t");
			methodBuf.append("}");
			
			return methodBuf.toString();
		}
		
		private String genSetMethod() {
			StringBuffer methodBuf = new StringBuffer();
			
			methodBuf.append("\n\n\t");
			methodBuf.append(String.format("public %s set%s(%s value) {", 
					getMsgName(), firstCharUpCase(getDefName()), getFullDefinType()));
			
			methodBuf.append("\n\t\t");
			methodBuf.append(String.format("this.%s = value;", getDefName()));
			
			methodBuf.append("\n\t\t");
			methodBuf.append("return this;");
			
			methodBuf.append("\n\t");
			methodBuf.append("}");
			
			return methodBuf.toString();
		}
		
		private String genAddMethod() {
			StringBuffer methodBuf = new StringBuffer();
			
			methodBuf.append("\n\n\t");
			methodBuf.append(String.format("public %s add%s(%s value) {", 
					getMsgName(), firstCharUpCase(getDefName()), getGenericType(0)));
			
			methodBuf.append("\n\t\t");
			methodBuf.append(String.format("if (this.%s == null) {", getDefName()));
			methodBuf.append("\n\t\t\t");
			methodBuf.append(String.format("this.%s = new %s<>();", getDefName(), definWrapType()));
			methodBuf.append("\n\t\t");
			methodBuf.append("}");
			
			methodBuf.append("\n\t\t");
			methodBuf.append(String.format("this.%s.add(value);", getDefName()));
			methodBuf.append("\n\t\t");
			methodBuf.append("return this;");
			
			methodBuf.append("\n\t");
			methodBuf.append("}");
			
			return methodBuf.toString();
		}
		
		private String genSetIndexMethod() {
			StringBuffer methodBuf = new StringBuffer();
			
			methodBuf.append("\n\n\t");
			methodBuf.append(String.format("public %s set%s(int index, %s value) {", 
					getMsgName(), firstCharUpCase(getDefName()), getGenericType(0)));
			
			methodBuf.append("\n\t\t");
			methodBuf.append(String.format("this.%s.set(index, value);", getDefName()));
			
			methodBuf.append("\n\t\t");
			methodBuf.append("return this;");
			
			methodBuf.append("\n\t");
			methodBuf.append("}");
			
			return methodBuf.toString();
		}
		
		private String genAddAllMethod() {
			StringBuffer methodBuf = new StringBuffer();
				
			methodBuf.append("\n\n\t");
			methodBuf.append(String.format("public %s addAll%s(%s value) {", 
					getMsgName(), firstCharUpCase(getDefName()), getFullDefinType()));
			
			methodBuf.append("\n\t\t");
			methodBuf.append(String.format("if (this.%s == null) {", getDefName()));
			methodBuf.append("\n\t\t\t");
			methodBuf.append(String.format("this.%s = new %s<>();", getDefName(), definWrapType()));
			methodBuf.append("\n\t\t");
			methodBuf.append("}");
			
			methodBuf.append("\n\t\t");
			methodBuf.append(String.format("this.%s.addAll(value);", getDefName()));
			methodBuf.append("\n\t\t");
			methodBuf.append("return this;");

			methodBuf.append("\n\t");
			methodBuf.append("}");
				
			return methodBuf.toString();
		}
		
		private String genPutMethod() {
			StringBuffer methodBuf = new StringBuffer();
			
			methodBuf.append("\n\n\t");
			methodBuf.append(String.format("public %s put%s(%s key, %s value) {", 
					getMsgName(), firstCharUpCase(getDefName()), getGenericType(0), getGenericType(1)));
			
			methodBuf.append("\n\t\t");
			methodBuf.append(String.format("if (this.%s == null) {", getDefName()));
			methodBuf.append("\n\t\t\t");
			methodBuf.append(String.format("this.%s = new %s<>();", getDefName(), definWrapType()));
			methodBuf.append("\n\t\t");
			methodBuf.append("}");
			
			methodBuf.append("\n\t\t");
			methodBuf.append(String.format("this.%s.put(key, value);", getDefName()));
			methodBuf.append("\n\t\t");
			methodBuf.append("return this;");
			
			methodBuf.append("\n\t");
			methodBuf.append("}");
			
			return methodBuf.toString();
		}
		
		private String genPutAllMethod() {
			StringBuffer methodBuf = new StringBuffer();
				
			methodBuf.append("\n\n\t");
			methodBuf.append(String.format("public %s addAll%s(%s value) {", 
					getMsgName(), firstCharUpCase(getDefName()), getFullDefinType()));
			
			methodBuf.append("\n\t\t");
			methodBuf.append(String.format("if (this.%s == null) {", getDefName()));
			methodBuf.append("\n\t\t\t");
			methodBuf.append(String.format("this.%s = new %s<>();", getDefName(), definWrapType()));
			methodBuf.append("\n\t\t");
			methodBuf.append("}");
			
			methodBuf.append("\n\t\t");
			methodBuf.append(String.format("this.%s.putAll(value);", getDefName()));
			methodBuf.append("\n\t\t");
			methodBuf.append("return this;");

			methodBuf.append("\n\t");
			methodBuf.append("}");
				
			return methodBuf.toString();
		}
		
		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append(genAttribute());
			builder.append(genMethod());
			
			return builder.toString();
		}
	}
}

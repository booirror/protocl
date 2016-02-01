package com.uxuan.protocl.generate;

import com.uxuan.protocl.AttrType;
import com.uxuan.protocl.Attribute;
import com.uxuan.protocl.Language;

public class JavaLanguage implements Language {
	
	private static final Attribute ATTRBOOL = Attribute.newBuilder().setType(AttrType.BOOL).setBase("boolean").setWrap("Boolean").build();
	private static final Attribute ATTRINT8 = Attribute.newBuilder().setType(AttrType.INT8).setBase("byte").setWrap("Byte").build();
	private static final Attribute ATTRINT16 = Attribute.newBuilder().setType(AttrType.INT16).setBase("short").setWrap("Short").build();
	private static final Attribute ATTRINT32 = Attribute.newBuilder().setType(AttrType.INT32).setBase("int").setWrap("Integer").build();
	private static final Attribute ATTRINT64 = Attribute.newBuilder().setType(AttrType.INT64).setBase("long").setWrap("Long").build();
	private static final Attribute FLOAT32 = Attribute.newBuilder().setType(AttrType.FLOAT32).setBase("float").setWrap("Float").build();
	private static final Attribute FLOAT64 = Attribute.newBuilder().setType(AttrType.FLOAT64).setBase("double").setWrap("Double").build();
	private static final Attribute STRING = Attribute.newBuilder().setType(AttrType.STRING).setBase("String").setWrap("String").build();
	private static final Attribute ARRAY = Attribute.newBuilder().setType(AttrType.ARRAY).setBase("java.util.List").setWrap("java.util.List").setCreate("java.util.ArrayList").build();
	private static final Attribute SET = Attribute.newBuilder().setType(AttrType.SET).setBase("java.util.Set").setWrap("java.util.Set").setCreate("java.util.HashSet").build();
	private static final Attribute MAP = Attribute.newBuilder().setType(AttrType.SET).setBase("java.util.Map").setWrap("java.util.Map").setCreate("java.util.HashMap").build();

	@Override
	public Attribute BOOL() {
		return ATTRBOOL;
	}

	@Override
	public Attribute INT8() {
		return ATTRINT8;
	}

	@Override
	public Attribute INT16() {
		return ATTRINT16;
	}

	@Override
	public Attribute INT32() {
		return ATTRINT32;
	}

	@Override
	public Attribute INT64() {
		return ATTRINT64;
	}

	@Override
	public Attribute FLOAT32() {
		return FLOAT32;
	}

	@Override
	public Attribute FLOAT64() {
		return FLOAT64;
	}

	@Override
	public Attribute STRING() {
		return STRING;
	}

	@Override
	public Attribute ENUM() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Attribute MESSAGE() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Attribute ARRAY() {
		return ARRAY;
	}

	@Override
	public Attribute SET() {
		return SET;
	}

	@Override
	public Attribute MAP() {
		return MAP;
	}

}

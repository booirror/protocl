//package com.uxuan.protocl;
//
//import junit.framework.Assert;
//
//import org.junit.Test;
//
//import com.uxuan.protocl.Finder;
//import com.uxuan.protocl.MsgField;
//import com.uxuan.protocl.MsgFiledType;
//
//public class TestMsgField {
//
//	@Test
//	public void testFiledParse() {
//		MsgField field;
//		
//		field = new MsgField(Finder.matcher("int8 value"));
//		Assert.assertEquals(MsgFiledType.FieldType.INT8, field.getDefClassType());
//		Assert.assertEquals("int8", field.getDefClassTypeName());
//		Assert.assertEquals(null, field.getDefGenericClassType());
//		Assert.assertEquals(null, field.getDefGenericClassTypeName());
//		Assert.assertEquals("value", field.getDefFieldName());
//
//		field = new MsgField(Finder.matcher("int16 value"));
//		Assert.assertEquals(MsgFiledType.FieldType.INT16, field.getDefClassType());
//		Assert.assertEquals("int16", field.getDefClassTypeName());
//		Assert.assertEquals(null, field.getDefGenericClassType());
//		Assert.assertEquals(null, field.getDefGenericClassTypeName());
//		Assert.assertEquals("value", field.getDefFieldName());
//		
//		field = new MsgField(Finder.matcher("int value"));
//		Assert.assertEquals(MsgFiledType.FieldType.INT, field.getDefClassType());
//		Assert.assertEquals("int", field.getDefClassTypeName());
//		Assert.assertEquals(null, field.getDefGenericClassType());
//		Assert.assertEquals(null, field.getDefGenericClassTypeName());
//		Assert.assertEquals("value", field.getDefFieldName());
//		
//		field = new MsgField(Finder.matcher("int64 value"));
//		Assert.assertEquals(MsgFiledType.FieldType.INT64, field.getDefClassType());
//		Assert.assertEquals("int64", field.getDefClassTypeName());
//		Assert.assertEquals(null, field.getDefGenericClassType());
//		Assert.assertEquals(null, field.getDefGenericClassTypeName());
//		Assert.assertEquals("value", field.getDefFieldName());
//		
//		field = new MsgField(Finder.matcher("bool value"));
//		Assert.assertEquals(MsgFiledType.FieldType.BOOL, field.getDefClassType());
//		Assert.assertEquals("bool", field.getDefClassTypeName());
//		Assert.assertEquals(null, field.getDefGenericClassType());
//		Assert.assertEquals(null, field.getDefGenericClassTypeName());
//		Assert.assertEquals("value", field.getDefFieldName());
//		
//		field = new MsgField(Finder.matcher("float value"));
//		Assert.assertEquals(MsgFiledType.FieldType.FLOAT, field.getDefClassType());
//		Assert.assertEquals("float", field.getDefClassTypeName());
//		Assert.assertEquals(null, field.getDefGenericClassType());
//		Assert.assertEquals(null, field.getDefGenericClassTypeName());
//		Assert.assertEquals("value", field.getDefFieldName());
//		
//		field = new MsgField(Finder.matcher("double value"));
//		Assert.assertEquals(MsgFiledType.FieldType.DOUBLE, field.getDefClassType());
//		Assert.assertEquals("double", field.getDefClassTypeName());
//		Assert.assertEquals(null, field.getDefGenericClassType());
//		Assert.assertEquals(null, field.getDefGenericClassTypeName());
//		Assert.assertEquals("value", field.getDefFieldName());
//		
//		field = new MsgField(Finder.matcher("string value"));
//		Assert.assertEquals(MsgFiledType.FieldType.STRING, field.getDefClassType());
//		Assert.assertEquals("string", field.getDefClassTypeName());
//		Assert.assertEquals(null, field.getDefGenericClassType());
//		Assert.assertEquals(null, field.getDefGenericClassTypeName());
//		Assert.assertEquals("value", field.getDefFieldName());
//
//		field = new MsgField(Finder.matcher("vector<MyTaskObjective> value"));
//		Assert.assertEquals(MsgFiledType.FieldType.VECTOR, field.getDefClassType());
//		Assert.assertEquals("vector", field.getDefClassTypeName());
//		Assert.assertEquals(MsgFiledType.FieldType.MESSAGE, field.getDefGenericClassType());
//		Assert.assertEquals("MyTaskObjective", field.getDefGenericClassTypeName());
//		Assert.assertEquals("value", field.getDefFieldName());
//		
//		field = new MsgField(Finder.matcher("vector<int8> value"));
//		Assert.assertEquals(MsgFiledType.FieldType.VECTOR, field.getDefClassType());
//		Assert.assertEquals("vector", field.getDefClassTypeName());
//		Assert.assertEquals(MsgFiledType.FieldType.INT8, field.getDefGenericClassType());
//		Assert.assertEquals("int8", field.getDefGenericClassTypeName());
//		Assert.assertEquals("value", field.getDefFieldName());
////		string taskId;							
////		int8 status;								
////		string taskRid;							
////		vector<MyTaskObjective> objective;		
//	}
//	
//}

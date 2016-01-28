package com.uxuan.buffer.java;

import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.uxuan.buffer.GenService;
import com.uxuan.buffer.MsgBody;
import com.uxuan.buffer.MsgField;
import com.uxuan.buffer.MsgFiledType.FieldType;
import com.uxuan.buffer.java.JavaFieldGen;

public class TestJavaFieldGen {

	private GenService service;
	
	private List<MsgBody> msgBodys;
	
	private List<MsgField> fields;
	
	@Before
	public void setUP() throws Exception {
		service = new GenService();
		service.initialize("./bin/");
		
		msgBodys = service.msgFile().get(0).getMsgBodys();
		fields = msgBodys.get(1).getMsgFields();
	}
	
	
	@Test
	public void testFieldType() {
		MsgField field;
		JavaFieldGen javaField;
		
		{
			field = fields.get(0);
			javaField = new JavaFieldGen(msgBodys.get(1), field);
			
			Assert.assertEquals(FieldType.BOOL, field.getDefClassType());
			Assert.assertEquals("bool", field.getDefClassTypeName());
			
			Assert.assertEquals(null, field.getDefGenericClassType());
			Assert.assertEquals(null, field.getDefGenericClassTypeName());
			
			Assert.assertEquals("merry", field.getDefFieldName());
			
			Assert.assertEquals(null, javaField.getVectorGenericClassTypeName());
			Assert.assertEquals("boolean", javaField.getWholeDefineClassTypeName());
		}
		
		
		{
			field = fields.get(1);
			javaField = new JavaFieldGen(msgBodys.get(1), field);
			
			Assert.assertEquals(FieldType.INT8, field.getDefClassType());
			Assert.assertEquals("int8", field.getDefClassTypeName());
			
			Assert.assertEquals(null, field.getDefGenericClassType());
			Assert.assertEquals(null, field.getDefGenericClassTypeName());

			Assert.assertEquals("chirld", field.getDefFieldName());
			
			Assert.assertEquals(null, javaField.getVectorGenericClassTypeName());
			Assert.assertEquals("byte", javaField.getWholeDefineClassTypeName());
		}

		{
			field = fields.get(2);
			javaField = new JavaFieldGen(msgBodys.get(1), field);
			
			Assert.assertEquals(FieldType.INT16, field.getDefClassType());
			Assert.assertEquals("int16", field.getDefClassTypeName());
			
			Assert.assertEquals(null, field.getDefGenericClassType());
			Assert.assertEquals(null, field.getDefGenericClassTypeName());
			
			Assert.assertEquals("age", field.getDefFieldName());
			
			Assert.assertEquals(null, javaField.getVectorGenericClassTypeName());
			Assert.assertEquals("short", javaField.getWholeDefineClassTypeName());
		}
		
		{
			field = fields.get(3);
			javaField = new JavaFieldGen(msgBodys.get(1), field);
			
			Assert.assertEquals(FieldType.INT, field.getDefClassType());
			Assert.assertEquals("int", field.getDefClassTypeName());
			
			Assert.assertEquals(null, field.getDefGenericClassType());
			Assert.assertEquals(null, field.getDefGenericClassTypeName());
			
			Assert.assertEquals("whifes", field.getDefFieldName());
			
			Assert.assertEquals(null, javaField.getVectorGenericClassTypeName());
			Assert.assertEquals("int", javaField.getWholeDefineClassTypeName());
		}
		
		{
			field = fields.get(4);
			javaField = new JavaFieldGen(msgBodys.get(1), field);
			
			Assert.assertEquals(FieldType.INT64, field.getDefClassType());
			Assert.assertEquals("int64", field.getDefClassTypeName());
			
			Assert.assertEquals(null, field.getDefGenericClassType());
			Assert.assertEquals(null, field.getDefGenericClassTypeName());
			
			Assert.assertEquals("phoneNum", field.getDefFieldName());
			
			Assert.assertEquals(null, javaField.getVectorGenericClassTypeName());
			Assert.assertEquals("long", javaField.getWholeDefineClassTypeName());
		}

		{
			field = fields.get(5);
			javaField = new JavaFieldGen(msgBodys.get(1), field);
			
			Assert.assertEquals(FieldType.FLOAT, field.getDefClassType());
			Assert.assertEquals("float", field.getDefClassTypeName());
			
			Assert.assertEquals(null, field.getDefGenericClassType());
			Assert.assertEquals(null, field.getDefGenericClassTypeName());

			Assert.assertEquals("credit", field.getDefFieldName());
			
			Assert.assertEquals(null, javaField.getVectorGenericClassTypeName());
			Assert.assertEquals("float", javaField.getWholeDefineClassTypeName());
		}
		
		{
			field = fields.get(6);
			javaField = new JavaFieldGen(msgBodys.get(1), field);
			
			Assert.assertEquals(FieldType.DOUBLE, field.getDefClassType());
			Assert.assertEquals("double", field.getDefClassTypeName());
			
			Assert.assertEquals(null, field.getDefGenericClassType());
			Assert.assertEquals(null, field.getDefGenericClassTypeName());
			
			Assert.assertEquals("money", field.getDefFieldName());

			Assert.assertEquals(null, javaField.getVectorGenericClassTypeName());
			Assert.assertEquals("double", javaField.getWholeDefineClassTypeName());
		}
		
		{
			field = fields.get(7);
			javaField = new JavaFieldGen(msgBodys.get(1), field);
			
			Assert.assertEquals(FieldType.STRING, field.getDefClassType());
			Assert.assertEquals("string", field.getDefClassTypeName());
			
			Assert.assertEquals(null, field.getDefGenericClassType());
			Assert.assertEquals(null, field.getDefGenericClassTypeName());
			
			Assert.assertEquals("nickname", field.getDefFieldName());

			Assert.assertEquals(null, javaField.getVectorGenericClassTypeName());
			Assert.assertEquals("String", javaField.getWholeDefineClassTypeName());
		}
		

		{
			field = fields.get(8);
			javaField = new JavaFieldGen(msgBodys.get(1), field);
			
			Assert.assertEquals(FieldType.VECTOR, field.getDefClassType());
			Assert.assertEquals("vector", field.getDefClassTypeName());
			
			Assert.assertEquals(FieldType.BOOL, field.getDefGenericClassType());
			Assert.assertEquals("bool", field.getDefGenericClassTypeName());
			
			Assert.assertEquals("chirldMerry", field.getDefFieldName());

			Assert.assertEquals("Boolean", javaField.getVectorGenericClassTypeName());
			Assert.assertEquals("List<Boolean>", javaField.getWholeDefineClassTypeName());
		}

		{
			field = fields.get(9);
			javaField = new JavaFieldGen(msgBodys.get(1), field);
			
			Assert.assertEquals(FieldType.VECTOR, field.getDefClassType());
			Assert.assertEquals("vector", field.getDefClassTypeName());
			
			Assert.assertEquals(FieldType.INT8, field.getDefGenericClassType());
			Assert.assertEquals("int8", field.getDefGenericClassTypeName());
			
			Assert.assertEquals("chirldAges", field.getDefFieldName());

			Assert.assertEquals("Byte", javaField.getVectorGenericClassTypeName());
			Assert.assertEquals("List<Byte>", javaField.getWholeDefineClassTypeName());
		}
		
		{
			field = fields.get(10);
			javaField = new JavaFieldGen(msgBodys.get(1), field);
			
			Assert.assertEquals(FieldType.VECTOR, field.getDefClassType());
			Assert.assertEquals("vector", field.getDefClassTypeName());
			
			Assert.assertEquals(FieldType.STRING, field.getDefGenericClassType());
			Assert.assertEquals("string", field.getDefGenericClassTypeName());

			Assert.assertEquals("chirldNames", field.getDefFieldName());

			Assert.assertEquals("String", javaField.getVectorGenericClassTypeName());
			Assert.assertEquals("List<String>", javaField.getWholeDefineClassTypeName());
		}
		
		{
			field = fields.get(11);
			javaField = new JavaFieldGen(msgBodys.get(1), field);
			
			Assert.assertEquals(FieldType.VECTOR, field.getDefClassType());
			Assert.assertEquals("vector", field.getDefClassTypeName());
			
			Assert.assertEquals(FieldType.MESSAGE, field.getDefGenericClassType());
			Assert.assertEquals("Wife", field.getDefGenericClassTypeName());
			
			Assert.assertEquals("wifs", field.getDefFieldName());
			
			Assert.assertEquals("Wife", javaField.getVectorGenericClassTypeName());
			Assert.assertEquals("List<Wife>", javaField.getWholeDefineClassTypeName());
		}
		
		{
			field = fields.get(12);
			javaField = new JavaFieldGen(msgBodys.get(1), field);
			
			Assert.assertEquals(FieldType.MESSAGE, field.getDefClassType());
			Assert.assertEquals("Wife", field.getDefClassTypeName());
			
			Assert.assertEquals(null, field.getDefGenericClassType());
			Assert.assertEquals(null, field.getDefGenericClassTypeName());
			
			Assert.assertEquals("wife", field.getDefFieldName());
			
			Assert.assertEquals(null, javaField.getVectorGenericClassTypeName());
			Assert.assertEquals("Wife", javaField.getWholeDefineClassTypeName());
		}
	}
	
	@Test
	public void testFieldDefineClass() {
		JavaFieldGen javaField;
		String getMethod;
		String setMethod;
		String addVectorMethod;
		String addAllVectorMethod;
		String setVectorMethod;
		
		{	// test defined field
			javaField = new JavaFieldGen(msgBodys.get(1), fields.get(0));
			
			Assert.assertEquals("private boolean merry;", javaField.defendField());
			
			getMethod = "\tpublic boolean getMerry() {" +
						"\n\t\treturn this.merry;" +
						"\n\t}";
			Assert.assertEquals(getMethod, javaField.getMethod());
			
			setMethod = "\tpublic Player setMerry(boolean data) {" +
						"\n\t\tthis.merry = data;" +
						"\n\t\treturn this;" +
						"\n\t}";
			Assert.assertEquals(setMethod, javaField.setMethod());
			
			Assert.assertEquals("", javaField.addVectorMethod());
			
			Assert.assertEquals("", javaField.addAllVectorMethod());
			
			Assert.assertEquals("", javaField.setVectorMethod());
			
		}

		
		{
			javaField = new JavaFieldGen(msgBodys.get(1), fields.get(1));
			
			Assert.assertEquals("private byte chirld;", javaField.defendField());
			getMethod = "\tpublic byte getChirld() {" +
						"\n\t\treturn this.chirld;" +
						"\n\t}";
			Assert.assertEquals(getMethod, javaField.getMethod());
			
			setMethod = "\tpublic Player setChirld(byte data) {" +
						"\n\t\tthis.chirld = data;" +
						"\n\t\treturn this;" +
						"\n\t}";
			Assert.assertEquals(setMethod, javaField.setMethod());
			
			Assert.assertEquals("", javaField.addVectorMethod());
			
			Assert.assertEquals("", javaField.addAllVectorMethod());
			
			Assert.assertEquals("", javaField.setVectorMethod());
		}


		{
			javaField = new JavaFieldGen(msgBodys.get(1), fields.get(2));
			
			Assert.assertEquals("private short age;", javaField.defendField());
			getMethod = "\tpublic short getAge() {" +
						"\n\t\treturn this.age;" +
						"\n\t}";
			Assert.assertEquals(getMethod, javaField.getMethod());
			
			setMethod = "\tpublic Player setAge(short data) {" +
						"\n\t\tthis.age = data;" +
						"\n\t\treturn this;" +
						"\n\t}";
			Assert.assertEquals(setMethod, javaField.setMethod());
			
			Assert.assertEquals("", javaField.addVectorMethod());
			
			Assert.assertEquals("", javaField.addAllVectorMethod());
			
			Assert.assertEquals("", javaField.setVectorMethod());
		}
		
		{
			javaField = new JavaFieldGen(msgBodys.get(1), fields.get(3));
			
			Assert.assertEquals("private int whifes;", javaField.defendField());
			getMethod = "\tpublic int getWhifes() {" +
						"\n\t\treturn this.whifes;" +
						"\n\t}";
			Assert.assertEquals(getMethod, javaField.getMethod());
			
			setMethod = "\tpublic Player setWhifes(int data) {" +
						"\n\t\tthis.whifes = data;" +
						"\n\t\treturn this;" +
						"\n\t}";
			Assert.assertEquals(setMethod, javaField.setMethod());
			
			Assert.assertEquals("", javaField.addVectorMethod());
			
			Assert.assertEquals("", javaField.addAllVectorMethod());
			
			Assert.assertEquals("", javaField.setVectorMethod());
		}
		
		{
			javaField = new JavaFieldGen(msgBodys.get(1), fields.get(4));
			
			Assert.assertEquals("private long phoneNum;", javaField.defendField());
			getMethod = "\tpublic long getPhoneNum() {" +
						"\n\t\treturn this.phoneNum;" +
						"\n\t}";
			Assert.assertEquals(getMethod, javaField.getMethod());
			
			setMethod = "\tpublic Player setPhoneNum(long data) {" +
						"\n\t\tthis.phoneNum = data;" +
						"\n\t\treturn this;" +
						"\n\t}";
			Assert.assertEquals(setMethod, javaField.setMethod());
			
			Assert.assertEquals("", javaField.addVectorMethod());
			
			Assert.assertEquals("", javaField.addAllVectorMethod());
			
			Assert.assertEquals("", javaField.setVectorMethod());
		}

		{
			javaField = new JavaFieldGen(msgBodys.get(1), fields.get(5));
			
			Assert.assertEquals("private float credit;", javaField.defendField());
			getMethod = "\tpublic float getCredit() {" +
						"\n\t\treturn this.credit;" +
						"\n\t}";
			Assert.assertEquals(getMethod, javaField.getMethod());
			
			setMethod = "\tpublic Player setCredit(float data) {" +
						"\n\t\tthis.credit = data;" +
						"\n\t\treturn this;" +
						"\n\t}";
			Assert.assertEquals(setMethod, javaField.setMethod());
			
			Assert.assertEquals("", javaField.addVectorMethod());
			
			Assert.assertEquals("", javaField.addAllVectorMethod());
			
			Assert.assertEquals("", javaField.setVectorMethod());
		}
		
		{
			javaField = new JavaFieldGen(msgBodys.get(1), fields.get(6));
			
			Assert.assertEquals("private double money;", javaField.defendField());
			getMethod = "\tpublic double getMoney() {" +
						"\n\t\treturn this.money;" +
						"\n\t}";
			Assert.assertEquals(getMethod, javaField.getMethod());
			
			setMethod = "\tpublic Player setMoney(double data) {" +
						"\n\t\tthis.money = data;" +
						"\n\t\treturn this;" +
						"\n\t}";
			Assert.assertEquals(setMethod, javaField.setMethod());
			
			Assert.assertEquals("", javaField.addVectorMethod());
			
			Assert.assertEquals("", javaField.addAllVectorMethod());
			
			Assert.assertEquals("", javaField.setVectorMethod());
		}

		{
			javaField = new JavaFieldGen(msgBodys.get(1), fields.get(7));
			
			Assert.assertEquals("private String nickname;", javaField.defendField());
			getMethod = "\tpublic String getNickname() {" +
						"\n\t\treturn this.nickname;" +
						"\n\t}";
			Assert.assertEquals(getMethod, javaField.getMethod());
			
			setMethod = "\tpublic Player setNickname(String data) {" +
						"\n\t\tthis.nickname = data;" +
						"\n\t\treturn this;" +
						"\n\t}";
			Assert.assertEquals(setMethod, javaField.setMethod());
			
			Assert.assertEquals("", javaField.addVectorMethod());
			
			Assert.assertEquals("", javaField.addAllVectorMethod());
			
			Assert.assertEquals("", javaField.setVectorMethod());
		}
		

		{
			javaField = new JavaFieldGen(msgBodys.get(1), fields.get(8));
			
			Assert.assertEquals("private List<Boolean> chirldMerry;", javaField.defendField());
			

			getMethod = "\tpublic List<Boolean> getChirldMerry() {" +
						"\n\t\treturn this.chirldMerry;" +
						"\n\t}";
			Assert.assertEquals(getMethod, javaField.getMethod());
			
			setMethod = "\tpublic Player setChirldMerry(List<Boolean> data) {" +
						"\n\t\tthis.chirldMerry = data;" +
						"\n\t\treturn this;" +
						"\n\t}";
			Assert.assertEquals(setMethod, javaField.setMethod());
			
			addVectorMethod = "\tpublic Player addChirldMerry(Boolean data) {" +
							"\n\t\tif (this.chirldMerry == null) {" +
							"\n\t\t\tthis.chirldMerry = new ArrayList<>();" +
							"\n\t\t}" +
							"\n\t\tthis.chirldMerry.add(data);" +
							"\n\t\treturn this;" +
							"\n\t}";
			Assert.assertEquals(addVectorMethod, javaField.addVectorMethod());
			
			addAllVectorMethod = "\tpublic Player addAllChirldMerry(List<Boolean> datas) {" +
							"\n\t\tif (this.chirldMerry == null) {" +
							"\n\t\t\tthis.chirldMerry = new ArrayList<>();" +
							"\n\t\t}" +
							"\n\t\tthis.chirldMerry.addAll(datas);" +
							"\n\t\treturn this;" +
							"\n\t}";
			Assert.assertEquals(addAllVectorMethod, javaField.addAllVectorMethod());
			
			setVectorMethod = "\tpublic Player setChirldMerry(int index, Boolean data) {" +
							"\n\t\tthis.chirldMerry.set(index, data);" +
							"\n\t\treturn this;" +
							"\n\t}";
			Assert.assertEquals(setVectorMethod, javaField.setVectorMethod());
		}

		{
			javaField = new JavaFieldGen(msgBodys.get(1), fields.get(9));
			
			Assert.assertEquals("private List<Byte> chirldAges;", javaField.defendField());
			

			getMethod = "\tpublic List<Byte> getChirldAges() {" +
						"\n\t\treturn this.chirldAges;" +
						"\n\t}";
			Assert.assertEquals(getMethod, javaField.getMethod());
			
			setMethod = "\tpublic Player setChirldAges(List<Byte> data) {" +
						"\n\t\tthis.chirldAges = data;" +
						"\n\t\treturn this;" +
						"\n\t}";
			Assert.assertEquals(setMethod, javaField.setMethod());
			
			addVectorMethod = "\tpublic Player addChirldAges(Byte data) {" +
							"\n\t\tif (this.chirldAges == null) {" +
							"\n\t\t\tthis.chirldAges = new ArrayList<>();" +
							"\n\t\t}" +
							"\n\t\tthis.chirldAges.add(data);" +
							"\n\t\treturn this;" +
							"\n\t}";
			Assert.assertEquals(addVectorMethod, javaField.addVectorMethod());
			
			addAllVectorMethod = "\tpublic Player addAllChirldAges(List<Byte> datas) {" +
							"\n\t\tif (this.chirldAges == null) {" +
							"\n\t\t\tthis.chirldAges = new ArrayList<>();" +
							"\n\t\t}" +
							"\n\t\tthis.chirldAges.addAll(datas);" +
							"\n\t\treturn this;" +
							"\n\t}";
			Assert.assertEquals(addAllVectorMethod, javaField.addAllVectorMethod());
			
			setVectorMethod = "\tpublic Player setChirldAges(int index, Byte data) {" +
							"\n\t\tthis.chirldAges.set(index, data);" +
							"\n\t\treturn this;" +
							"\n\t}";
			Assert.assertEquals(setVectorMethod, javaField.setVectorMethod());
		}
		
		{
			javaField = new JavaFieldGen(msgBodys.get(1), fields.get(10));
			
			Assert.assertEquals("private List<String> chirldNames;", javaField.defendField());

			getMethod = "\tpublic List<String> getChirldNames() {" +
						"\n\t\treturn this.chirldNames;" +
						"\n\t}";
			Assert.assertEquals(getMethod, javaField.getMethod());
			
			setMethod = "\tpublic Player setChirldNames(List<String> data) {" +
						"\n\t\tthis.chirldNames = data;" +
						"\n\t\treturn this;" +
						"\n\t}";
			Assert.assertEquals(setMethod, javaField.setMethod());
			
			addVectorMethod = "\tpublic Player addChirldNames(String data) {" +
							"\n\t\tif (this.chirldNames == null) {" +
							"\n\t\t\tthis.chirldNames = new ArrayList<>();" +
							"\n\t\t}" +
							"\n\t\tthis.chirldNames.add(data);" +
							"\n\t\treturn this;" +
							"\n\t}";
			Assert.assertEquals(addVectorMethod, javaField.addVectorMethod());
			
			addAllVectorMethod = "\tpublic Player addAllChirldNames(List<String> datas) {" +
							"\n\t\tif (this.chirldNames == null) {" +
							"\n\t\t\tthis.chirldNames = new ArrayList<>();" +
							"\n\t\t}" +
							"\n\t\tthis.chirldNames.addAll(datas);" +
							"\n\t\treturn this;" +
							"\n\t}";
			Assert.assertEquals(addAllVectorMethod, javaField.addAllVectorMethod());
			
			setVectorMethod = "\tpublic Player setChirldNames(int index, String data) {" +
							"\n\t\tthis.chirldNames.set(index, data);" +
							"\n\t\treturn this;" +
							"\n\t}";
			Assert.assertEquals(setVectorMethod, javaField.setVectorMethod());
		}
		
		{
			javaField = new JavaFieldGen(msgBodys.get(1), fields.get(11));
			
			Assert.assertEquals("private List<Wife> wifs;", javaField.defendField());

			getMethod = "\tpublic List<Wife> getWifs() {" +
						"\n\t\treturn this.wifs;" +
						"\n\t}";
			Assert.assertEquals(getMethod, javaField.getMethod());
			
			setMethod = "\tpublic Player setWifs(List<Wife> data) {" +
						"\n\t\tthis.wifs = data;" +
						"\n\t\treturn this;" +
						"\n\t}";
			Assert.assertEquals(setMethod, javaField.setMethod());
			
			addVectorMethod = "\tpublic Player addWifs(Wife data) {" +
							"\n\t\tif (this.wifs == null) {" +
							"\n\t\t\tthis.wifs = new ArrayList<>();" +
							"\n\t\t}" +
							"\n\t\tthis.wifs.add(data);" +
							"\n\t\treturn this;" +
							"\n\t}";
			Assert.assertEquals(addVectorMethod, javaField.addVectorMethod());
			
			addAllVectorMethod = "\tpublic Player addAllWifs(List<Wife> datas) {" +
							"\n\t\tif (this.wifs == null) {" +
							"\n\t\t\tthis.wifs = new ArrayList<>();" +
							"\n\t\t}" +
							"\n\t\tthis.wifs.addAll(datas);" +
							"\n\t\treturn this;" +
							"\n\t}";
			Assert.assertEquals(addAllVectorMethod, javaField.addAllVectorMethod());
			
			setVectorMethod = "\tpublic Player setWifs(int index, Wife data) {" +
							"\n\t\tthis.wifs.set(index, data);" +
							"\n\t\treturn this;" +
							"\n\t}";
			Assert.assertEquals(setVectorMethod, javaField.setVectorMethod());
		}
		
		{	// test defined field
			javaField = new JavaFieldGen(msgBodys.get(1), fields.get(12));
			
			Assert.assertEquals("private Wife wife;", javaField.defendField());
			
			getMethod = "\tpublic Wife getWife() {" +
						"\n\t\treturn this.wife;" +
						"\n\t}";
			Assert.assertEquals(getMethod, javaField.getMethod());
			
			setMethod = "\tpublic Player setWife(Wife data) {" +
						"\n\t\tthis.wife = data;" +
						"\n\t\treturn this;" +
						"\n\t}";
			Assert.assertEquals(setMethod, javaField.setMethod());
			
			Assert.assertEquals("", javaField.addVectorMethod());
			
			Assert.assertEquals("", javaField.addAllVectorMethod());
			
			Assert.assertEquals("", javaField.setVectorMethod());
		}
	}
	
	
	@Test
	public void testDispatch() {
//		JavaFileGen javaFile = new JavaFileGen(service.msgFile().get(0));
		
//		javaFile.getDispatch(classInfo)
		
//		Assert.assertEquals("", struct.());
//		javaFile = new JavaStruct(msgBodys.get(4));
	}
}

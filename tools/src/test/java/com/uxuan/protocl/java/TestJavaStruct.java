//package com.uxuan.protocl.java;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import junit.framework.Assert;
//
//import org.junit.Before;
//import org.junit.Test;
//
//import com.uxuan.protocl.GenService;
//import com.uxuan.protocl.MsgBody;
//import com.uxuan.protocl.generate.JavaStruct;
//
//public class TestJavaStruct {
//
//	private List<JavaStruct> structs;
//	
//	
//	@Before
//	public void setUP() throws Exception {
//		GenService service = new GenService();
//		service.initialize("./bin/");
//		
//		structs = new ArrayList<>();
//		for (MsgBody body : service.msgFile().get(0).getMsgBodys()) {
//			structs.add(new JavaStruct(body));
//		}
//	}
//	
//	@Test
//	public void testNewInstance() {
//		String method = "\n\n\tpublic static Player newBuilder() {" +
//						"\n\t\treturn new Player();" +
//						"\n\t}";
//		
//		Assert.assertEquals(method, structs.get(1).newInstanceMethod());
//	}
//	
//	@Test
//	public void testFromBuffer() {
//		String method = "\n\n\tpublic static PlayerRequest fromBuffer(ByteBuffer buf) {" +
//						"\n\t\treturn newBuilder().readMsg(buf);" +
//						"\n\t}";
//		
//		Assert.assertEquals(method, structs.get(2).recivedAbleMethod());
//	}
//	
//	@Test
//	public void testToBUffer() {
//		String method = "\n\n\t@Override" +
//						"\n\tpublic ByteBuffer toBuffer() {" +
//						"\n\t\tBubferBuilder builder = new BubferBuilder(64);" +
//			
//						"\n\n\t\tbuilder.addShort((short)0);" +
//						"\n\t\tbuilder.addShort((short)((PREFIX << 8) | SUFFIX));" +
//						"\n\t\twriteMsg(builder);" +
//								
//						"\n\n\t\tByteBuffer buf = builder.toBuffer();" +
//						"\n\t\tbuilder.replaceShort(0, (short)buf.position());" +
//						"\n\t\tbuf.flip();" +
//									
//						"\n\n\t\treturn buf;" +
//						"\n\t}";
//		
//		Assert.assertEquals(method, structs.get(3).sendAbleMethod());
//	}
//	
//	@Test
//	public void testReadMessage() {
//		String method = "\n\n\t@Override"+ 
//				"\n\tpublic Player readMsg(ByteBuffer buf) {" + 
//				"\n\t\tthis.merry = readBool(buf);" +
//				"\n\t\tthis.chirld = readInt8(buf);" +
//				"\n\t\tthis.age = readInt16(buf);" +
//				"\n\t\tthis.whifes = readInt(buf);" +
//				"\n\t\tthis.phoneNum = readInt64(buf);" +
//				"\n\t\tthis.credit = readFloat(buf);" +
//				"\n\t\tthis.money = readDouble(buf);" +
//				"\n\t\tthis.nickname = readString(buf);" +
//				"\n\t\tthis.chirldMerry = readArray(Boolean.class, buf);" +
//				"\n\t\tthis.chirldAges = readArray(Byte.class, buf);" +
//				"\n\t\tthis.chirldNames = readArray(String.class, buf);" +
//				"\n\t\tthis.wifs = readArray(Wife.class, buf);" +
//				"\n\t\tthis.wife = Wife.newBuilder().readMsg(buf);" +
//				"\n\t\treturn this;" +
//				"\n\t}";
//		Assert.assertEquals(method, structs.get(1).readMessageMethod());
//	}
//	
//	@Test
//	public void testWriteMessage() {
//		String method = "\n\n\t@Override"+ 
//						"\n\tpublic void writeMsg(BubferBuilder builder) {" + 
//						"\n\t\twriteBool(builder, this.merry);" +
//						"\n\t\twriteInt8(builder, this.chirld);" +
//						"\n\t\twriteInt16(builder, this.age);" +
//						"\n\t\twriteInt(builder, this.whifes);" +
//						"\n\t\twriteInt64(builder, this.phoneNum);" +
//						"\n\t\twriteFloat(builder, this.credit);" +
//						"\n\t\twriteDouble(builder, this.money);" +
//						"\n\t\twriteString(builder, this.nickname);" +
//						"\n\t\twriteArray(builder, this.chirldMerry);" +
//						"\n\t\twriteArray(builder, this.chirldAges);" +
//						"\n\t\twriteArray(builder, this.chirldNames);" +
//						"\n\t\twriteArray(builder, this.wifs);" +
//						"\n\t\tthis.wife.writeMsg(builder);" +
//						"\n\t}";
//		Assert.assertEquals(method, structs.get(1).writeMessageMethod());
//	}
//	
//	@Test
//	public void testSUFFIX() {
//		String method = "\n\n\t@Override" + 
//						"\n\tpublic final byte SUFFIX() {" +
//						"\n\t\treturn SUFFIX;" +
//						"\n\t}";
//		
//		Assert.assertEquals(method, structs.get(2).defSUFFIXMethod());
//	}
//	
//	@Test
//	public void testAutoGenEvent() {
//		String method;
//		
//		method = "\n\n\tpublic abstract void playerRequest(T value, PlayerRequest request);";
//		Assert.assertEquals(method, structs.get(2).autoGenEventMethod());
//		
//		method = "\n\n\tpublic abstract void playerInfo(T value, PlayerInfo request);";
//		Assert.assertEquals(method, structs.get(4).autoGenEventMethod());
//	}
//}

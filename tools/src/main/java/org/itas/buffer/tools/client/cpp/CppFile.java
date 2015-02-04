//package org.itas.buffer.tools.client.cpp;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.OutputStreamWriter;
//
//import org.itas.buffer.tools.MsgBody;
//import org.itas.buffer.tools.MsgFile;
//
//public class CppFile {
//
//	private MsgFile fileInfo;
//
//	public CppFile(MsgFile fileInfo) {
//		this.fileInfo = fileInfo;
//	}
//
//	public void genCpp(String desPath) throws IOException {
//		System.out.println("cpp begin.");
//
//		StringBuffer pk_h_buf = new StringBuffer();
//		StringBuffer pk_cpp_buf = new StringBuffer();
//
//		StringBuffer msg_h_buf = new StringBuffer();
//		StringBuffer msg_cpp_buf = new StringBuffer();
//
//		pk_h_buf.append("\n#pragma  once \n\n");
//
//		pk_h_buf.append("#include <vector> \n\n");
//		pk_h_buf.append("#include \"NetDef.h\" \n\n");
//		pk_h_buf.append("using namespace std;\n\n");
//
//		pk_h_buf.append("namespace pk{\n\n");
//
//		msg_h_buf.append("\n#pragma  once \n\n");
//
//		msg_h_buf.append("#include <vector> \n\n");
//		msg_h_buf.append("using namespace std;\n\n");
//
//		msg_cpp_buf.append("#include \"NetDef.h\" \n\n");
//		msg_cpp_buf.append("#include \"message.h\" \n\n");
//		msg_cpp_buf.append("#include \"package.h\" \n\n");
//
//		pk_cpp_buf.append("#include \"NetDef.h\" \n\n");
//		pk_cpp_buf.append("#include \"package.h\" \n\n");
//		pk_cpp_buf.append("#include \"message.h\" \n\n");
//
//		pk_cpp_buf.append("\nnamespace pk{\n\n");
//
//		msg_cpp_buf.append("void DoMessage_" + fileInfo.getMsgFileName(false) + "(char* data)\n");
//		msg_cpp_buf.append("{\n");
//
//		msg_cpp_buf.append("\tBEING_DEAL();\n");
//
//		CppStruct cppStruct;
//		for (MsgBody classInfo : fileInfo.getMsgBodys()) {
//			cppStruct = new CppStruct(classInfo);
//			pk_h_buf.append(cppStruct.getPk_H());
//			pk_cpp_buf.append(cppStruct.getPk_Cpp());
//			msg_h_buf.append(cppStruct.getMsg_H());
//			msg_cpp_buf.append(cppStruct.getMsg_Cpp());
//		}
//
//		msg_cpp_buf.append("\tEND_DEAL();\n");
//		msg_cpp_buf.append("}\n");
//
//		pk_h_buf.append("\n};\n\n");
//		pk_cpp_buf.append("\n};\n\n");
//
//		File pk_h = new File(desPath + "\\pk_" + fileInfo.getMsgFileName(false) + ".h");
//		pk_h.delete();
//		pk_h.createNewFile();
//
//		File pk_cpp = new File(desPath + "\\pk_" + fileInfo.getMsgFileName(false) + ".cpp");
//		pk_cpp.delete();
//		pk_cpp.createNewFile();
//
//		File msg_h = new File(desPath + "\\msg_" + fileInfo.getMsgFileName(false) + ".h");
//		msg_h.delete();
//		msg_h.createNewFile();
//
//		File msg_cpp = new File(desPath + "\\msg_" + fileInfo.getMsgFileName(false) + ".cpp");
//		msg_cpp.delete();
//		msg_cpp.createNewFile();
//
//		OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(pk_h));
//		out.write(pk_h_buf.toString());
//
//		out.flush();
//		out.close();
//
//		out = new OutputStreamWriter(new FileOutputStream(pk_cpp));
//		out.write(pk_cpp_buf.toString());
//
//		out.flush();
//		out.close();
//
//		out = new OutputStreamWriter(new FileOutputStream(msg_h));
//		out.write(msg_h_buf.toString());
//
//		out.flush();
//		out.close();
//
//		out = new OutputStreamWriter(new FileOutputStream(msg_cpp));
//		out.write(msg_cpp_buf.toString());
//
//		out.flush();
//		out.close();
//
//		System.out.println("cpp end.");
//	}
//}

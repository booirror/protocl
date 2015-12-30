package com.uxuan.buffer.tools.client.cpp;

import com.uxuan.buffer.tools.MsgBody;
import com.uxuan.buffer.tools.MsgField;
import com.uxuan.buffer.tools.MsgFiledType.FieldType;
import com.uxuan.buffer.tools.util.MsgStatus;

public class CppStruct {

	private MsgBody clazz;

	public CppStruct(MsgBody clazz) {
		this.clazz = clazz;
	}

	public String getPk_H() {
		StringBuffer pk_h_buf = new StringBuffer();
		pk_h_buf.append("struct ");
		pk_h_buf.append(clazz.getMsgName(false));
		pk_h_buf.append("\n{\n");

		CppField cppField;
		for (MsgField fieldInfo : clazz.getMsgFields()) {
			cppField = new CppField(fieldInfo);
			
			pk_h_buf.append("\t");
			pk_h_buf.append(cppField.getWholeDefineClassTypeName());
			pk_h_buf.append(" ");
			pk_h_buf.append(fieldInfo.getDefFieldName());

			pk_h_buf.append(";\n");
		}

		if (clazz.isTypeExist(MsgStatus.CLIENT_TO_SERVER)) {
			pk_h_buf.append("\tvoid Send(int type);\n");
		}

		pk_h_buf.append("};\n");

		pk_h_buf.append("void Write");
		pk_h_buf.append(clazz.getMsgName(false));
		pk_h_buf.append("(char*& buf,");
		pk_h_buf.append(clazz.getMsgName(false));
		pk_h_buf.append("& value);\n");

		if (clazz.isTypeExist(MsgStatus.SERVER_TO_CLIENT)) {
			pk_h_buf.append("void On");
			pk_h_buf.append(clazz.getMsgName(false));
			pk_h_buf.append("(");
			pk_h_buf.append(clazz.getMsgName(false));
			pk_h_buf.append("* value);\n");
		}

		pk_h_buf.append("void Read");
		pk_h_buf.append(clazz.getMsgName(false));
		pk_h_buf.append("(char*& buf,");
		pk_h_buf.append(clazz.getMsgName(false));
		pk_h_buf.append("& value);\n");

		pk_h_buf.append("\n");

		return pk_h_buf.toString();
	}

	public String getPk_Cpp() {
		StringBuffer pk_cpp_buf = new StringBuffer();

		pk_cpp_buf.append("void Write");
		pk_cpp_buf.append(clazz.getMsgName(false));
		pk_cpp_buf.append("(char*& buf,");
		pk_cpp_buf.append(clazz.getMsgName(false));
		pk_cpp_buf.append("& value)\n");
		pk_cpp_buf.append("{\n");

		CppField cppField;
		for (MsgField fieldInfo : clazz.getMsgFields()) {
			cppField = new CppField(fieldInfo);
			pk_cpp_buf.append("\t");

			if (fieldInfo.getDefClassType() == FieldType.VECTOR) {
				pk_cpp_buf.append("WriteArray(buf,");

				pk_cpp_buf.append(cppField.getVectorGenericClassTypeName());
				pk_cpp_buf.append(",");
			} else {
				pk_cpp_buf.append("Write");
				pk_cpp_buf.append(fieldInfo.getDefClassTypeName());
				pk_cpp_buf.append("(buf,");
			}

			pk_cpp_buf.append("value.");
			pk_cpp_buf.append(fieldInfo.getDefFieldName());

			pk_cpp_buf.append(");\n");
		}

		pk_cpp_buf.append("}\n");

		if (clazz.isTypeExist(MsgStatus.CLIENT_TO_SERVER)) {
			pk_cpp_buf.append("void ");
			pk_cpp_buf.append(clazz.getMsgName(false));
			pk_cpp_buf.append("::Send(int type)");
			pk_cpp_buf.append("{\n");

			pk_cpp_buf.append("\tBeginSend(");
			pk_cpp_buf.append(clazz.getMsgName(false));
			pk_cpp_buf.append(");\n");

			pk_cpp_buf.append("\tWrite");
			pk_cpp_buf.append(clazz.getMsgName(false));

			pk_cpp_buf.append("(buf,*this);\n");

			pk_cpp_buf.append("\tEndSend(type);\n");

			pk_cpp_buf.append("}\n");
		}

		pk_cpp_buf.append("void Read");
		pk_cpp_buf.append(clazz.getMsgName(false));
		pk_cpp_buf.append("(char*& buf,");
		pk_cpp_buf.append(clazz.getMsgName(false));
		pk_cpp_buf.append("& value)\n");
		pk_cpp_buf.append("{\n");

		for (MsgField fieldInfo : clazz.getMsgFields()) {
			cppField = new CppField(fieldInfo);
			
			pk_cpp_buf.append("\t");

			if (fieldInfo.getDefClassType() == FieldType.VECTOR) {
				pk_cpp_buf.append("ReadArray(buf,");

				pk_cpp_buf.append(cppField.getVectorGenericClassTypeName());
				pk_cpp_buf.append(",");
			} else {
				pk_cpp_buf.append("Read");
				pk_cpp_buf.append(fieldInfo.getDefClassTypeName());
				pk_cpp_buf.append("(buf,");
			}

			pk_cpp_buf.append("value.");
			pk_cpp_buf.append(fieldInfo.getDefFieldName());

			pk_cpp_buf.append(");\n");
		}

		pk_cpp_buf.append("}\n");

		return pk_cpp_buf.toString();
	}

	public String getMsg_H() {
		StringBuffer msg_h_buf = new StringBuffer();
		
		if (clazz.isTypeExist(MsgStatus.CLIENT_TO_SERVER) || clazz.isTypeExist(MsgStatus.SERVER_TO_CLIENT)) {
			msg_h_buf.append("const int MSG_");
			msg_h_buf.append(clazz.getMsgName(false));
			msg_h_buf.append("\t\t\t= ");
			msg_h_buf.append(clazz.getHexOrder());
			msg_h_buf.append(";\n");
		}
		
		return msg_h_buf.toString();
	}

	public String getMsg_Cpp() {
		StringBuffer msg_cpp_buf = new StringBuffer();
		
		if (clazz.isTypeExist(MsgStatus.SERVER_TO_CLIENT)) {
			msg_cpp_buf.append("\t\tCMD_DEAL(");
			msg_cpp_buf.append(clazz.getMsgName(false));
			msg_cpp_buf.append(");\n ");
		}
		
		return msg_cpp_buf.toString();
	}

}

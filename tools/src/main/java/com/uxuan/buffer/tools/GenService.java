package com.uxuan.buffer.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.uxuan.buffer.tools.Finder.MsgPat;
import com.uxuan.buffer.tools.client.cpp.CppFile;
import com.uxuan.buffer.tools.service.java.JavaFileGen;
import com.uxuan.buffer.tools.util.FileUtils;
import com.uxuan.buffer.tools.util.MsgStatus;

public class GenService {
	
	/**
	 * 协议文件对应编号
	 */
	private byte fileOrder;
	
	/**
	 * 协议文件内message body对应编号
	 */
	private byte msgOrder;

	/**
	 * 所有解析协议文件
	 */
	private List<MsgFile> msgFiles;
	
	public GenService() {
		fileOrder = 0;
		msgOrder = 1;
		msgFiles = new ArrayList<>();
	}

	public void initialize(String srcPath) throws Exception {
		List<File> files = FileUtils.getFiles(srcPath, "*.msg");
		if (Objects.isNull(files)) {
			throw new NullPointerException("can't find any files in path:[" + srcPath + "]");
		}

		for (File file : files) {
			fileOrder ++;
			
			MsgFile msgFile = new MsgFile(file, fileOrder);
			msgFiles.add(msgFile);

			try (FileInputStream fileHanlde = new FileInputStream(file);
				 InputStreamReader inReader = new InputStreamReader(fileHanlde, "UTF-8");
				 BufferedReader reader = new BufferedReader(inReader)) {

				String line;
				MsgBody msgBody = null;
				while ((line = reader.readLine()) != null) {
					Finder finder = Finder.matcher(line);

					if (finder == null) {
						// do nothing
					} else if (finder.pat == MsgPat.PACKAGENAME) {
						msgFile.setPackageName(delSign(line, ";", "package"));
					} else if (finder.pat == MsgPat.IMPORTFILES) {
						msgFile.addImport(delSign(line, ";", "import"));
					} else if (finder.pat == MsgPat.MESSAGEDEF) {
						checkOutOfBounds();
						
						msgBody = new MsgBody(msgOrder, finder.group(1));
						msgBody.addAllMsgType(getMsgType(finder));

						msgFile.addMsgBody(msgBody);
					} else if (finder.pat == MsgPat.COTENTNOTES) {
						// do nothing
					} else if (finder.pat == MsgPat.MESSAGEFIELD) {
						msgBody.addField(new MsgField(finder));
					} else if (finder.pat == MsgPat.MESSAGEEND) {
						// do Nothing
					} 
				}
			} 
		}
	}
	
	public void generate(String sdistPath, String cdistPath) throws IOException {
		JavaFileGen javaFile;
		CppFile cppFile;
		for (MsgFile msgFile : msgFiles) {
			javaFile = new JavaFileGen(msgFile);
			javaFile.autoGenMsg(sdistPath);
			javaFile.autoGenEvent(sdistPath);

			cppFile = new CppFile(msgFile);
			cppFile.genCpp(cdistPath);
		}
	}
	
	public List<MsgFile> msgFile() {
		return msgFiles;
	}

	private List<MsgStatus> getMsgType(Finder finder) {
		List<MsgStatus> msgList = new ArrayList<MsgStatus>(2);

		if (finder.group(2).equals("<")) {
			msgList.add(MsgStatus.SERVER_TO_CLIENT);
		}

		if (finder.group(4).equals(">")) {
			msgList.add(MsgStatus.CLIENT_TO_SERVER);
		}
		
		if (!msgList.isEmpty()) {
			msgOrder ++;
		}

		return msgList;
	}
	
	
	private String delSign(String str, String...signs) {
		for (String sign : signs) {
			str = str.replace(sign, "");
		}
		
		return str.trim();
	}
	
	private void checkOutOfBounds() {
		if (msgOrder > 0xFF)
		throw new RuntimeException("max size:0xFF, current:" + Integer.toBinaryString(msgOrder));
	}

}
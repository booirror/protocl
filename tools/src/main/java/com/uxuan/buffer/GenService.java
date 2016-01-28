package com.uxuan.buffer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.uxuan.buffer.Finder.MsgPat;
import com.uxuan.buffer.java.JavaFileGen;
import com.uxuan.buffer.util.FileUtils;

public class GenService {

	/** 所有解析协议文件*/
	private List<MsgFile> msgFiles;
	
	public GenService() {
		msgFiles = new ArrayList<>();
	}

	public void initialize(String srcPath) throws Exception {
		List<File> files = FileUtils.getFiles(srcPath, "*.msg");
		if (Objects.isNull(files)) {
			throw new NullPointerException("can't find any files in path:[" + srcPath + "]");
		}

		for (File file : files) {
			MsgFile msgFile = new MsgFile(file);
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
						msgBody = new MsgBody(finder.group(1));

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
		for (MsgFile msgFile : msgFiles) {
			javaFile = new JavaFileGen(msgFile);
			javaFile.autoGenMsg(sdistPath);
			javaFile.autoGenEvent(sdistPath);
		}
	}
	
	public List<MsgFile> msgFile() {
		return msgFiles;
	}
	
	private String delSign(String str, String...signs) {
		for (String sign : signs) {
			str = str.replace(sign, "");
		}
		
		return str.trim();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		msgFiles.forEach(msg->{
			builder.append(msg.toString());
		});
		
		return builder.toString();
	}

}
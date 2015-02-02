package com.godwan.code;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.godwan.code.lang.CppFile;
import com.godwan.code.lang.JavaFile;
import com.godwan.code.util.MsgStatus;

public class FileList {

	private byte xClassIndex = 0x00;
	private short xMethodIndex = 0x00;

	private int patCount = 0;
	private Pattern[] pats = new Pattern[4];
	private int[] patType = new int[4];

	private List<FileInfo> classList = new LinkedList<FileInfo>();

	public void initialize(String srcPath, String distPath) throws Exception {
		InitPattern();

		loadFile(srcPath);
		
		
		JavaFile javaFile;
		CppFile cppFile;
		for (FileInfo fileInfo : classList) {
			javaFile = new JavaFile(fileInfo);
			javaFile.genJava(distPath + "\\java\\net");

			cppFile = new CppFile(fileInfo);
			cppFile.genCpp(distPath + "\\cpp");
		}
	}

	private void InitPattern() {
		patCount = 0;

		PatPush("^[ \t]*struct[ \t]+(\\w+)[ \t]*([<]?)([-]?)([>]?)[ \t]*", 0);
		PatPush("^[ \t]*//", 1);
		PatPush("(\\w+)[ \t]*(<(\\w+)>)?[ \t]*(\\w+)", 2);
		PatPush("^[ \t]*}", 3);
	}

	private void PatPush(String patStr, int type) {
		pats[patCount] = Pattern.compile(patStr);
		patType[patCount] = type;
		patCount += 1;
	}

	private void loadFile(String srcPath) throws Exception {
		List<File> fileList = FileService.getFiles(srcPath, "*.h");
		if (fileList == null) {
			System.err.print("Can't find any files in '" + srcPath + "'.");
			return;
		}

		String fileKey;
		FileInfo fileInfo;
		ClassInfo classInfo = null;
		for (File file : fileList) {

			xClassIndex ++;
			xMethodIndex = 1;
			fileKey = file.getName();
			fileKey = fileKey.substring(0, fileKey.length() - 2);
			fileInfo = new FileInfo(fileKey, xClassIndex);
			classList.add(fileInfo);

			BufferedReader reader = null;
			try {
				FileInputStream fileHanlde = new FileInputStream(file);
				reader = new BufferedReader(new InputStreamReader(fileHanlde,
						"UTF-8"));

				String line;
				while ((line = reader.readLine()) != null) {
					int perType = -1;
					Matcher matcher = null;

					for (int i = 0; i < patCount; i++) {
						if ((matcher = pats[i].matcher(line)).find()) {
							perType = patType[i];
							break;
						}
					}


					if (perType == 0) {
						if (xMethodIndex > 255) {
							System.err.println("max 255, yours=" + xMethodIndex);
							return;
						}
						
						classInfo = new ClassInfo(fileInfo, xMethodIndex);
						classInfo.setClassName(matcher.group(1));
						classInfo.addAllMsgType(getMsgType(matcher));
						if (!classInfo.getMessageTypes().isEmpty()) {
							xMethodIndex ++;
						}

						fileInfo.addClassList(classInfo);
					} else if (perType == 1) {
						// do nothing
					} else if (perType == 2) {
						FieldInfo fieldInfo = new FieldInfo();
						fieldInfo.setClassType(matcher.group(1));
						fieldInfo.setGenericName(matcher.group(3));
						fieldInfo.setFieldName(matcher.group(4));

						classInfo.addField(fieldInfo);
					} else if (perType == 3) {
						// do Nothing
					}
				}
			} finally {
				if (reader != null) {
					try {
						reader.close();
					} catch (IOException e) {
					}
				}
			}
		}
	}
	
	private List<MsgStatus> getMsgType(Matcher matcher) {
		List<MsgStatus> msgList = new ArrayList<MsgStatus>(2);

		if (matcher.group(2).equals("<")) {
			msgList.add(MsgStatus.SERVER_TO_CLIENT);
		}

		if (matcher.group(4).equals(">")) {
			msgList.add(MsgStatus.CLIENT_TO_SERVER);
		}

		return msgList;
	}

}
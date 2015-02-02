package com.godwan.code;

import static com.godwan.code.util.CodeUtil.firstCharUpCase;

import java.util.LinkedList;
import java.util.List;



public class FileInfo {
	
	private String fileName;
	
	private byte xClassIndex;

	private List<ClassInfo> classList;
	
	public FileInfo(String fileName, byte xClassIndex) {
		this.fileName = fileName;
		this.xClassIndex = xClassIndex;
		this.classList = new LinkedList<ClassInfo>();
	}

	public String getFileName(boolean isFirstUpCase) {
		if (isFirstUpCase) {
			return firstCharUpCase(fileName);
		} else {
			return fileName;
		}
	}

	public void setxClassIndex(byte xClassIndex) {
		this.xClassIndex = xClassIndex;
	}

	public List<ClassInfo> getClassList() {
		return classList;
	}

	public void addClassList(ClassInfo classInfo) {
		this.classList.add(classInfo);
	}
	
	public byte getXClassIndex() {
		return xClassIndex;
	}
	
	public String getHeXClassIndex() {
		String calzz = Integer.toHexString(xClassIndex);
		if (calzz.length() < 2) {
			calzz = "0" + calzz;
		} 
		
		return calzz;
	}
	
	public boolean hasArray() {
		for (ClassInfo info : classList) {
			if (info.hasArray()) {
				return true;
			}
		}
		
		return false;
	}
	
}
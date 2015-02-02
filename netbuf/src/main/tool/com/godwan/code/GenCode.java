package com.godwan.code;



public class GenCode {

	public static void main(String[] args) throws Exception {

		String srcPath = "webapp/def";// args[0];
		String desPath = ".";// args[1];
		
//			String srcPath = "E:\\source\\duobao\\protocl\\webapp\\def";// ARGS[0];
//			String desPath = "E:\\source\\duobao\\protocl";// args[1];
		FileList fileList = new FileList();

		fileList.initialize(srcPath, desPath);
	}
}

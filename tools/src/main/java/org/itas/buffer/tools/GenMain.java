package org.itas.buffer.tools;




public class GenMain {

	public static void main(String[] args) throws Exception {
//
		String srcPath = "./bin/";// args[0];
		String desPath = ".";// args[1];
		
//			String srcPath = "E:\\source\\duobao\\protocl\\webapp\\def";// ARGS[0];
//			String desPath = "E:\\source\\duobao\\protocl";// args[1];
		GenService fileList = new GenService();
		fileList.initialize(srcPath);
		
		fileList.generate(desPath);
	}
}

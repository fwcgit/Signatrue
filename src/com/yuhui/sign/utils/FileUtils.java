package com.yuhui.sign.utils;

import java.io.File;

public class FileUtils {
	
	public static String dirPath;
	public static String dirOldPath;
	
	public static void createSaveDir(File file) {
		
		File dirFile = new File(file+"pdf\\");
		File dirOldFile = new File(file+"pdf\\oldpdf");
		
		if(!dirFile.exists()){
			dirFile.mkdirs();
		}
		
		if(!dirOldFile.exists()){
			dirOldFile.mkdirs();
		}
		
		dirPath    = dirFile.getPath()+"\\";
		dirOldPath = dirOldFile.getPath()+"\\";
		
		System.err.println("合同文件存储路径目录"+ dirPath);
		System.err.println("原始合同文件存储路径目录"+ dirOldPath);
	
	} 
}

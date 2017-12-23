package com.yuhui.sign;

import java.io.File;

public class WebApp {
	
	private static WebApp webApp = new WebApp();
	
	public static WebApp getInstance() {
		return webApp;
	}
	
	public String dirPath;

	public void init() {
		
		selectDisk();
		
	}
	
	private void selectDisk() {
		
		File[] roots = File.listRoots();
		
		long size = 0;
		int index = 0;
		
		for(int i = 0 ; i < roots.length ; i++) {
			
			File file = roots[i];
			
			if(size < file.getFreeSpace()) {
				size = file.getFreeSpace();
				index = i;
			}
			
		}
		
		createSaveDir(roots[index]);
		System.err.println("最大磁盘为"+roots[index]);
		System.err.println("磁盘空间为"+ (size / 1024 / 1024 / 1024)+"GB");
		
	}
	
	
	private void createSaveDir(File file) {
		File dirFile = new File(file+"pdf\\");
		dirFile.mkdirs();
		dirPath = dirFile.getPath()+"\\";
		System.err.println("文件存储路径目录"+ dirPath);
	
	}
}

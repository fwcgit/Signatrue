package com.yuhui.sign;

import java.io.File;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.yuhui.sign.api.SignServer;
import com.yuhui.sign.db.CheckDb;
import com.yuhui.sign.db.DataBaseOpt;
import com.yuhui.sign.download.DownloadServer;
import com.yuhui.sign.utils.FileUtils;

public class WebApp implements ServletContextListener {
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
		System.out.println("webapp Destroyed");
		
		ServletContextListener.super.contextDestroyed(sce);
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		
		init();
		
		ServletContextListener.super.contextInitialized(sce);
	}



	public void init() {
		
		selectDisk();
		
		DataBaseOpt.getInstance();
		
		CheckDb.getInstance().startTimer();
		
		SignServer.getInstance().startSignServer();
		
		DownloadServer.getInstance().exectuteDownloadServer();
		
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
		
		FileUtils.createSaveDir(roots[index]);
		
		System.err.println("最大磁盘为"+roots[index]);
		System.err.println("磁盘空间为"+ (size / 1024 / 1024 / 1024)+"GB");
		
	}
	
	

}

package com.yuhui.sign.download;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DownloadServer implements Runnable {
	
	private static DownloadServer downloadServer = new DownloadServer();
	
	public static DownloadServer getInstance() {
		return downloadServer;
	}
	
	ExecutorService executorService = Executors.newFixedThreadPool(10);
	
	Thread thread;
	
	boolean isRun;
	
	public void exectuteDownloadServer() {
		
		if(isRun) return;
		
		isRun = true;
		
		thread = new Thread(this);
		thread.start();
		
		System.err.println("任务队列已启动等待下载任务");
	}
	
	public void stopDownloadServer() {
		
		if(isRun) {
			isRun = false;
			thread = null;
		}
		
	}

	@Override
	public void run() {
		
		while(isRun) {
			
			DownloadInfo info = DownloadTask.getInstance().consume();
			
			if(null != info) {
				
				executorService.execute(new Download(info));
			}
			
		}
		
	}
	
	
}

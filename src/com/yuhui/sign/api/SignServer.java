package com.yuhui.sign.api;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.yuhui.sign.bean.LoanInfo;

public class SignServer implements Runnable {
	
	private static SignServer signServer = new SignServer();
	public 	static SignServer getInstance(){ return signServer;}
	private SignServer(){}

	ExecutorService executorService = Executors.newFixedThreadPool(10);
	
	Thread thread;
	
	boolean isRun;
	
	public void startSignServer(){
		
		isRun  = true;
		thread = new Thread(this);
		thread.start();
		
		System.out.println("启动签名服务，等待签名任务。。。。。");
	}
	
	public void stopSignServer(){
		
		if(null != thread){
			isRun = false;
			thread = null;
		}
		
		executorService.shutdown();
		
		System.out.println("停止签名服务");
		
	}
	
	@Override
	public void run() {
		
		if(isRun){
			
			LoanInfo loanInfo = SignTask.getInstance().getSignTast();
			
			if(null != loanInfo){
				executorService.execute(new SignPdf(loanInfo));
			}
			
		}
	}

}

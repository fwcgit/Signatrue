package com.yuhui.sign.db;

import java.util.Timer;
import java.util.TimerTask;

public class CheckDb {
	private long periodTime = 60 * 60 * 1000;
	private Timer timer;
	
	private static CheckDb checkDb;
	
	public static CheckDb getInstance(){
		return checkDb == null ? checkDb = new CheckDb() : checkDb;
	}
	
	public void startTimer(){
		
		if(null != timer){
			timer.cancel();
			timer = null;
		}
		
		timer = new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				
				System.err.println("-------CheckDb------------");
				DataBaseOpt.getInstance().query("select COUNT(*) from user");
			}
		}, 1000, periodTime);
	}
	
	public void testEvent(String str){
		System.err.println("event = "+str);
	}
	
}

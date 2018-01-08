package com.yuhui.sign.api;

import java.util.LinkedList;

import com.yuhui.sign.bean.LoanInfo;

public class SignTask {
	
	private static SignTask signTask = new SignTask();
	
	public static SignTask getInstance(){return signTask;}
	
	private SignTask(){}
	
	private final int MAX_SIZE = 100;
	
	LinkedList<LoanInfo> list = new LinkedList<LoanInfo>();
	
	public void addSignTask(LoanInfo loanInfo){
		
		synchronized (list) {
			
			if(list.size() < MAX_SIZE){
				
				list.add(loanInfo);
				
			}else{
				
				try {
					list.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
			
			list.notifyAll();
			
		}
		
	}
	
	public LoanInfo getSignTast(){
		
		synchronized (list) {
			
			if(list.size() > 0){
				
				return list.pop();
				
			}else{
				
				try {
					list.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			list.notifyAll();
			
			return null;
		}
		
	}
	
}

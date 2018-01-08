package com.yuhui.sign.download;

import java.util.LinkedList;

public class DownloadTask {
	
	private static DownloadTask downloadTask = new DownloadTask();
	
	public static DownloadTask getInstance() {
		return downloadTask;
	}
	
	private final int MAX_SIZE = 100;
	
	private LinkedList<DownloadInfo> list = new LinkedList<DownloadInfo>(); 
	
	private DownloadTask(){}
    // 生产num个产品  
    public void produce(DownloadInfo info)  
    {  
        // 同步代码段  
        synchronized (list)  
        {  
 
        	if(list.size() < MAX_SIZE) {
        		
        		 list.add(info);  
        		 
        	}else {
        		
        		 try  
                 {  
                 	if(list.size() >= MAX_SIZE) {
                 		 // 由于条件不满足，生产阻塞  
                         list.wait();  
                 	}
                   
                 }catch (InterruptedException e)  
                 {  
                     e.printStackTrace();  
                 }  
        		 
        	}
        	
            list.notifyAll();  
        }  
    }  
  
    // 消费num个产品  
    public DownloadInfo consume()  
    {  
        // 同步代码段  
        synchronized (list)  
        {  
          
        	if(list.size() > 0) {
        		
        		 return list.pop();  
        		 
        	}else {
        		
        		 try {
                 	
        			 // 由于条件不满足，消费阻塞  
                     list.wait();
                     
                 } catch (InterruptedException e) {
                	 
                     e.printStackTrace();  
                     
                 }  
        		 
        	}
           
             list.notifyAll();
 
            return null;  
          
        }  
    }


  
}

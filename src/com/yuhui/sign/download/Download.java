package com.yuhui.sign.download;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.yuhui.sign.db.DataBaseOpt;

public class Download implements Runnable {

	
	private DownloadInfo info;
	
	
	public Download(DownloadInfo info) {
		super();
		this.info = info;
		
		System.err.println("开始下载任务"+info.toString());
	}



	@Override
	public void run() {
		
		downloadFile();
	}
	
	private void downloadFile() {
		
		URL url = null;
		
		URLConnection conn = null;
		
		FileOutputStream fos = null;
		
		InputStream is = null;
		
		long fileLength = -1;
		int readLength = -1;
		long length = 0;
		
		try {
			
			url = new URL(info.url);
			conn = url.openConnection();
			
			File file = new File(info.path);
			fos = new FileOutputStream(file);
			is = conn.getInputStream();
			
			fileLength = conn.getContentLength();
			readLength = -1;
			length = 0;
			byte[] buff = new byte[10 * 1024];
			
			while((readLength = is.read(buff)) > 0) {
				
				length+=readLength;
				
				fos.write(buff, 0, readLength);
			}
			
			fos.flush();
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			if(null != is) {
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(null != fos) {
				try {
					fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(length == fileLength) {
				System.out.println("下载完成"+info.fileName);
				completeDownload();
			}else {
				System.out.println("下载失败"+info.fileName);
			}
			
		}
		
	}
	
	private void completeDownload() {
		
		long time = System.currentTimeMillis();
		
		DataBaseOpt.getInstance().insertDown(info.fileName, info.path, "", info.phone, info.idcard,"1231231" ,time);
	}
	
}

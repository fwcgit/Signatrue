package com.yuhui.sign.download;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.util.TextUtils;

import com.junziqian.api.bean.Signatory;
import com.junziqian.api.common.IdentityType;
import com.junziqian.api.request.PresFileLinkRequest;
import com.junziqian.api.response.SignLinkResponse;
import com.junziqian.api.util.LogUtils;
import com.yuhui.sign.api.JunziqianClientInit;
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
		
		String link = getUrlLink();
		if(!TextUtils.isEmpty(link)){
			downloadFile(link);
		}
		
	}
	
	private void downloadFile(String linkUrl) {
		
		PresFileLinkRequest request = new PresFileLinkRequest(); 
		request.setApplyNo(info.applyNo); //签约编号
		Signatory signatory = new Signatory(); //签约人信息 
		signatory.setFullName(info.fullName);
		signatory.setIdentityCard(info.idcard); 
		signatory.setSignatoryIdentityType(IdentityType.IDCARD); request.setSignatory(signatory);
		SignLinkResponse response = JunziqianClientInit.getClient().presFileLink(request);
		
		URL url = null;
		
		HttpURLConnection conn = null;
		
		FileOutputStream fos = null;
		
		InputStream is = null;
		
		boolean isFail = false;
		int readLength = -1;
		
		try {
			
			url = new URL(response.getLink());
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept-Encoding", "identity");
			
			File file = new File(info.path);
			if(file.exists()) {
				System.out.println("文件已经存在");
				return;
			}
			fos = new FileOutputStream(file);
			is = conn.getInputStream();
			
			readLength = -1;
			byte[] buff = new byte[10 * 1024];
			
			while((readLength = is.read(buff)) > 0) {
				fos.write(buff, 0, readLength);
			}
			
			fos.flush();
			
			isFail = false;
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
			
			isFail = true;
			
		} catch (IOException e) {
			e.printStackTrace();
			
			isFail = true;
			
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
			
			if(!isFail) {
				System.out.println("下载完成"+info.fileName);
				completeDownload();
			}else {
				downloadFileFail();
			}
			
		}
		
	}
	
	private void downloadFileFail(){
		
		File file = new File(info.path);
		if(file.exists()){
			file.delete();
		}
		
		long time = System.currentTimeMillis();
		
		DataBaseOpt.getInstance().insertDown(info.fileName, info.path, "", info.phone, info.idcard,info.applyNo ,0,info.type,time);
		
		System.out.println("下载失败"+info.fileName);
	}
	
	private String getUrlLink(){
		
		String link = "";
		
		PresFileLinkRequest request = new PresFileLinkRequest(); request.setApplyNo(info.applyNo); //签约编号
		Signatory signatory = new Signatory(); //签约人信息 signatory.setFullName("张三");
		signatory.setIdentityCard(info.idcard); signatory.setSignatoryIdentityType(IdentityType.IDCARD); request.setSignatory(signatory);
		SignLinkResponse response = JunziqianClientInit.getClient().presFileLink(request); LogUtils.logResponse(response);
		
		if(response.isSuccess()){
			link = response.getLink();
		}
		
		return link;
	}
	
	private void completeDownload() {
		
		long time = System.currentTimeMillis();
		
		DataBaseOpt.getInstance().insertDown(info.fileName, info.path, "", info.phone, info.idcard,info.applyNo ,1,info.type,time);
	}
	
}

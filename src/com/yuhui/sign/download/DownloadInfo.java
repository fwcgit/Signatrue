package com.yuhui.sign.download;

import com.yuhui.sign.bean.LoanInfo;

public class DownloadInfo {

	public String url;
	
	public String fileName;
	
	public String phone;
	
	public String idcard;
	
	public String path;
	
	public LoanInfo info;

	@Override
	public String toString() {
		return "DownloadInfo [url=" + url + ", fileName=" + fileName + "]";
	}
	
	
}

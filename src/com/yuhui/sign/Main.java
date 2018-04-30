package com.yuhui.sign;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.yuhui.sign.api.SignServer;
import com.yuhui.sign.api.SignTask;
import com.yuhui.sign.bean.LoanInfo;
import com.yuhui.sign.db.CheckDb;
import com.yuhui.sign.db.DataBaseOpt;
import com.yuhui.sign.download.DownloadServer;
import com.yuhui.sign.http.CallBackLoanInfo;
import com.yuhui.sign.pdf.CreatePdf;

//测试环境key和接口地址：
//appKey : fe575856fe6826a2
//appSecrete : fdbf3c35fe575856fe6826a20195f06b
//services_url：http://sandbox.api.junziqian.com/services
//测试环境官网企业账户登陆地址:  http://sandbox.junziqian.com/login/loginPage
//用户名：yuweizichan@www.bccto.me
//密码：abc123
//
//沙箱测试环境需在hosts加五条记录（正式环境不需要加hosts），文件地址为C:\Windows\System32\drivers\etc\hosts
//
//219.153.5.242 sandbox.junziqian.com
//219.153.5.242 sandbox.api.junziqian.com
//219.153.5.242 sandbox.htb.ebaoquan.org
//219.153.5.242 sandbox.ebq.bz
//219.153.5.242 sandbox.account.ebaoquan.org
//企业名称：禹徽资产    营业执照号：120108019554456   邮箱：yuweizichan@www.bccto.me


public class Main {

	
	public static void main(String[] args) {
		
//		new CreatePdf().createPdfFile();
//		new SignPdf().signPdf();
		
//		
//		String[] dUrl = new String[] {
//				"https://ss3.baidu.com/9fo3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=0b1159175382b2b7b89f3fc401adcb0a/d009b3de9c82d15866ea98a28a0a19d8bc3e42bd.jpg",
//				"https://ss3.baidu.com/9fo3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=baf5cbe38f18367ab28979dd1e718b68/0b46f21fbe096b63fc80589a05338744eaf8ac5a.jpg",
//				"https://ss2.baidu.com/-vo3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=bb7b6ef0f71f4134ff37037e151f95c1/c995d143ad4bd1136e95442150afa40f4bfb054c.jpg",
//				"https://ss3.baidu.com/9fo3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=e1365008de0735fa8ef048b9ae510f9f/060828381f30e924f2b76e2246086e061d95f796.jpg",
//				"https://ss2.baidu.com/-vo3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=6765daaa3c2ac65c78056073cbf0b21d/3b292df5e0fe9925e1d36f9a3da85edf8cb17118.jpg",
//				"https://ss1.baidu.com/9vo3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=128c859c871001e9513c120f880e7b06/a71ea8d3fd1f4134264ac90b2f1f95cad1c85e42.jpg",
//				"http://imgsrc.baidu.com/image/c0%3Dshijue1%2C0%2C0%2C294%2C40/sign=6572cde373310a55d029d6b7df2c29dc/b219ebc4b74543a91062addc14178a82b90114a0.jpg",
//				"http://img1.imgtn.bdimg.com/it/u=1167088769,847502684&fm=200&gp=0.jpg",
//				"http://img4.duitang.com/uploads/item/201408/30/20140830185456_Eijik.jpeg",
//				"http://imgsrc.baidu.com/image/c0%3Dshijue1%2C0%2C0%2C294%2C40/sign=4c3b2306ba51f819e5280b09b2dd2098/8718367adab44aed5b5a1bc9b91c8701a18bfb83.jpg",
//				"http://imgsrc.baidu.com/image/c0%3Dshijue1%2C0%2C0%2C294%2C40/sign=e47fdec09682d158af8f51f2e86373ad/cdbf6c81800a19d88948dc2b39fa828ba61e4650.jpg",
//				"http://imgsrc.baidu.com/image/c0%3Dshijue1%2C0%2C0%2C294%2C40/sign=e05fa110566034a83defb0c2a37a2321/5fdf8db1cb1349547059c0755c4e9258d1094a5f.jpg"
//				
//		};
//		
//		
//		DownloadServer.getInstance().exectuteDownloadServer();
//
//		for(int i = 0 ; i < dUrl.length ; i++) {
//			
//			String url = dUrl[i];
//			
//			DownloadInfo info = new DownloadInfo();
//			
//			info.fileName = i+".jpg";
//			
//			info.phone = "123456789"+i;
//			
//			info.idcard = "32398293428"+i;
//			
//			info.path = WebApp.getInstance().dirPath+info.fileName;
//			
//			info.url = url;
//			
//			
//			DownloadTask.getInstance().produce(info);
//		}
		
		
//		System.out.println(DataBaseOpt.getInstance().query("select * from download"));
		
		
//		DataBaseOpt.getInstance();
		
//		CheckDb.getInstance().startTimer();
//		
//		SignServer.getInstance().startSignServer();
//		
//		DownloadServer.getInstance().exectuteDownloadServer();
//		
//		LoanInfo loanInfo = new LoanInfo();
//		loanInfo.name = "张三";
//		loanInfo.phone = "322234444343";
//		loanInfo.idcard = "320929329832983923";
//		DataBaseOpt.getInstance().insertLoanInfo(loanInfo.name, loanInfo.phone, loanInfo.idcard, "", "", 0, System.currentTimeMillis());
//		
//		SignTask.getInstance().addSignTask(loanInfo);
//		
//		DownloadInfo downloadInfo = new DownloadInfo();
//		downloadInfo.applyNo  = "32928038042";
//		downloadInfo.fileName = "32928038042";
//		downloadInfo.fullName = "张三";
//		downloadInfo.idcard   = "320929329832983923";
//		DownloadTask.getInstance().produce(downloadInfo);
		
//		String json  = DataBaseOpt.getInstance().query("select phone from loaninfo where applyno = '" + "APL950648301110312960"+"'");
//		System.out.println(json);
		

		
		DataBaseOpt.getInstance();
		
		CheckDb.getInstance().startTimer();
		
		SignServer.getInstance().startSignServer();
		
		DownloadServer.getInstance().exectuteDownloadServer();
		
		DataBaseOpt.getInstance().getHeTongNumber();
		
//		String bodyStr = "{\r\n" + 
//				"	\"module\": \"getborrow-by-id\",\r\n" + 
//				"	\"errorCode\": 0,\r\n" + 
//				"	\"errorMsg\": \"\",\r\n" + 
//				"	\"prompt\": false,\r\n" + 
//				"	\"result\": {\r\n" + 
//				"		\"borrowId\": 11,\r\n" + 
//				"		\"accountId\": 270027,\r\n" + 
//				"		\"idCard\": \"513823198812230029\",\r\n" + 
//				"		\"cellphone\": \"13795532123\",\r\n" + 
//				"		\"name\": \"周泳霖\",\r\n" + 
//				"		\"address\":\"sh\",\r\n" + 
//				"		\"email\":\"dss@QQ.com\",\r\n" + 
//				"		\"bankName\":\"zsyh\",\r\n" + 
//				"		\"jieKuanRiQi\": 1503889434000,\r\n" + 
//				"		\"jieKuanJinE\": 600,\r\n" + 
//				"		\"jieKuanTianShu\": 15,\r\n" + 
//				"		\"jieKuanZhuangTai\": 3,\r\n" + 
//				"		\"jieKuanZhuangTaiChangeTime\": 1505174503000,\r\n" + 
//				"		\"huanKuanRiQi\": 1505174503000,\r\n" + 
//				"		\"huanKuanJinE\": 641.86,\r\n" + 
//				"		\"huanKuanYiChang\": 0,\r\n" + 
//				"		\"huanKuanOverdue\": 1,\r\n" + 
//				"		\"huanKuanDeadline\": 1505145600,\r\n" + 
//				"		\"huanKuanXinXi\": \"{\\\"MCHNTORDERID\\\":\\\"11_60785133\\\",\\\"SIGN\\\":\\\"e1ea5145492429bf82ed29ead2471c59\\\",\\\"MCHNTCD\\\":\\\"0002900F0395202\\\",\\\"BANKCARD\\\":\\\"6215582313001610188\\\",\\\"VERSION\\\":\\\"2.0\\\",\\\"RESPONSECODE\\\":\\\"0000\\\",\\\"ORDERID\\\":\\\"001320474139\\\",\\\"RESPONSEMSG\\\":\\\"成功\\\",\\\"AMT\\\":\\\"64186\\\",\\\"TYPE\\\":\\\"02\\\"}\",\r\n" + 
//				"		\"fangKuanShiJian\": 1503889829000,\r\n" + 
//				"		\"fangKuanFangShi\": 2,\r\n" + 
//				"		\"cuiKuanRenId\": null,\r\n" + 
//				"		\"cuiKuanRenName\": null,\r\n" + 
//				"		\"useJiangLi\": 0,\r\n" + 
//				"		\"genBlacklist\": 0,\r\n" + 
//				"		\"inBlacklist\": 1,\r\n" + 
//				"		\"frozen\": 0,\r\n" + 
//				"		\"huaiZhang\": 0,\r\n" + 
//				"		\"bankCard\": \"6215582313001610188\",\r\n" + 
//				"		\"huanKuanShangHuDingDanHao\": \"11_60785133\",\r\n" + 
//				"		\"huanKuanFuYouDingDanHao\": \"001320474139\",\r\n" + 
//				"		\"fangKuanFuyouLiuShuiHao\": \"510200631034\",\r\n" + 
//				"		\"xuZu\": 0,\r\n" + 
//				"		\"deviceName\": \"xiaomi\",\r\n" + 
//				"		\"sysVersion\": null,\r\n" + 
//				"		\"deviceType\": \"mi6\",\r\n" + 
//				"		\"deviceId\": \"12131231312\",\r\n" + 
//				"		\"appName\": null,\r\n" + 
//				"		\"appId\": null\r\n" + 
//				"	}\r\n" + 
//				"}";
//		
//		CallBackLoanInfo callBackLoanInfo = new Gson().fromJson(bodyStr, CallBackLoanInfo.class);
//		LoanInfo loanInfo = callBackLoanInfo.result;
//		
//		DataBaseOpt.getInstance().insertLoanInfo(loanInfo.name, loanInfo.cellphone, loanInfo.idCard, bodyStr, "", 0, 1,System.currentTimeMillis());
//		
//		loanInfo.type = 1;
//		SignTask.getInstance().addSignTask(loanInfo);
		
		
//		CallBackLoanInfo callBackLoanInfo1 = new Gson().fromJson(bodyStr, CallBackLoanInfo.class);
//		LoanInfo loanInfo1 = callBackLoanInfo1.result;
//		
//		DataBaseOpt.getInstance().insertLoanInfo(loanInfo.name, loanInfo.cellphone, loanInfo.idCard, bodyStr, "", 0, 2,System.currentTimeMillis());
//
//		loanInfo1.type = 2;
//		SignTask.getInstance().addSignTask(loanInfo1);
		
//		System.out.println("http request body = " + bodyStr);
		
	}
}

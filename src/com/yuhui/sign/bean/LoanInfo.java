package com.yuhui.sign.bean;

//{
//	"module": "getborrow-by-id",
//	"errorCode": 0,
//	"errorMsg": "",
//	"prompt": false,
//	"result": {
//		"borrowId": 7230,
//		"accountId": 311547,
//		"idCard": "412326199708156027",
//		"cellphone": "17717297178",
//		"name": "张珊",
//		"jieKuanRiQi": 1524992460000,
//		"jieKuanJinE": 900,
//		"jieKuanTianShu": 15,
//		"jieKuanZhuangTai": 2,
//		"jieKuanZhuangTaiChangeTime": 1524992945000,
//		"huanKuanRiQi": 0,
//		"huanKuanJinE": null,
//		"huanKuanYiChang": 0,
//		"huanKuanOverdue": 0,
//		"huanKuanDeadline": 1526227200,
//		"huanKuanXinXi": null,
//		"fangKuanShiJian": 1524992945000,
//		"fangKuanFangShi": 2,
//		"cuiKuanRenId": null,
//		"cuiKuanRenName": null,
//		"useJiangLi": 0,
//		"genBlacklist": 0,
//		"inBlacklist": 0,
//		"frozen": 0,
//		"huaiZhang": 0,
//		"bankCard": "6212261702025259482",
//		"huanKuanShangHuDingDanHao": null,
//		"huanKuanFuYouDingDanHao": null,
//		"fangKuanFuyouLiuShuiHao": "691701634072",
//		"xuZu": 0,
//		"deviceName": "iPhone (6)",
//		"sysVersion": "9.3.5",
//		"deviceType": "iPhone 6",
//		"deviceID": "A0E50CCA-84E7-4CB0-ACA1-B4480B7E66CE",
//		"appName": "急借白卡",
//		"appId": 0,
//		"bankName": "中国工商银行",
//		"email": "464844848@qq.com",
//		"adress": "狗狗你我"
//	}
//}
public class LoanInfo {
	public int type;
	public String name;
	public String cellphone;
	public String idCard;
	public String bankName;
	public String email;
	public String address;
	public long jieKuanRiQi;
	public float jieKuanJinE;
	public int jieKuanTianShu;
	public long huanKuanRiQi;
	public float huanKuanJinE;
	public long fangKuanShiJian;
	public String bankCard;
	public String deviceName;
	public String deviceType;
	public String deviceID;
	
}


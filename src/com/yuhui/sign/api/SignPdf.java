package com.yuhui.sign.api;

import java.util.HashSet;

import org.ebaoquan.rop.thirdparty.com.alibaba.fastjson.JSONArray;
import org.ebaoquan.rop.thirdparty.com.alibaba.fastjson.JSONObject;
import org.ebaoquan.rop.thirdparty.com.google.common.collect.Sets;

import com.junziqian.api.bean.Signatory;
import com.junziqian.api.common.DealType;
import com.junziqian.api.common.IdentityType;
import com.junziqian.api.common.SignLevel;
import com.junziqian.api.request.ApplySignFileRequest;
import com.junziqian.api.response.ApplySignResponse;
import com.yuhui.sign.bean.LoanInfo;
import com.yuhui.sign.download.DownloadInfo;

public class SignPdf implements Runnable {
	
	private LoanInfo loanInfo;
	
	public SignPdf(LoanInfo loanInfo) {
		this.loanInfo = loanInfo;
	}


	private void signPdf(){
        ApplySignFileRequest.Builder builder = new ApplySignFileRequest.Builder();
        builder.withContractName("只用保全"); // 合同名称，必填
        builder.withContractAmount(20000.00); // 合同金额
        //1、本地文件方式
        builder.withFile("./res/xy.pdf");
        builder.withServerCa(1);
        builder.withDealType(DealType.AUTH_SIGN);
        //2、字节流方式
        //File file = new File("D:\\tmp\\买卖合同.pdf");
        //builder.withFile(file.getName(), FileCopyUtils.copyToByteArray(file)); //合同文件

        //目前仅支持无认证、身份证认证、银行卡认证、USBKEY认证
        //builder.withAuthenticationLevel(AuthenticationLevel.NONE.getCode()); //签约时，需要的身份认证级别
        //TreeSet<Integer> set=new TreeSet<Integer>();
        //builder.withFaceThreshold(64);
        //set.add(AuthLevel.FACE.getCode());
        //set.add(AuthLevel.BANKTHREE.getCode());
        //builder.withAuthLevel(set);
        /**
         * 0 只需第一次认证过，后面不用认证
         * 1 每次签约都要认证
         */
        builder.withForceAuthentication(1);
        HashSet<Signatory> signatories = Sets.newHashSet();
        Signatory signatory = new Signatory();
        signatory.setFullName("易凡翔"); //姓名
        signatory.setSignatoryIdentityType(IdentityType.IDCARD); //证件类型
        signatory.setIdentityCard("500240198704146355"); //证件号码
        signatory.setMobile("15320369150");
        signatory.setOrderNum(1);
        //set=new TreeSet<Integer>();
        //set.add(AuthLevel.FACE.getCode());
        //signatory.setAuthLevel(set);
        //[{"page":0,","chaptes":[{"offsetX":0.12,"offsetY":0.23}]},{"page":1,"chaptes":[{"offsetX":0.45,"offsetY":0.67}]}]
        JSONArray chapteJsonArray=new JSONArray();
        
        JSONObject pageJson=new JSONObject();
        pageJson.put("page", 7);
        JSONArray chaptes=new JSONArray();
        pageJson.put("chaptes", chaptes);
        chapteJsonArray.add(pageJson);
        
        JSONObject chapte=new JSONObject();
        chapte.put("offsetX", 0.15);
        chapte.put("offsetY", 0.238);
        chaptes.add(chapte);
        
        chapte=new JSONObject();
        chapte.put("offsetX", 0.15);
        chapte.put("offsetY", 0.35);
        chaptes.add(chapte);
        
        signatory.withChapteJson(chapteJsonArray);
        System.out.println(chapteJsonArray);
        signatories.add(signatory);


        builder.withSignatories(signatories); // 添加签约人
        builder.withSignLevel(SignLevel.GENERAL.getCode()); // 签字类型,这里选择标准图形章
        builder.withRemark("这里是备注信息，不超过500个字符"); //备注
        builder.withPreRecored("前执记录，会计录到日志中！");
        builder.withOrderFlag(7);//1为按顺序（Signatory.orderNum），其它为不按顺序，默认不按顺序
        //builder.withNeedCa(1);//使用CA认证
        /**多合同顺序签约*/
        //SequenceInfo sequenceInfo=new SequenceInfo("XX001",2,2);
        //builder.withSequenceInfo(sequenceInfo);
        ApplySignResponse response = JunziqianClientInit.getClient().applySignFile(builder.build());
        
        DownloadInfo downloadInfo = new DownloadInfo();
        downloadInfo.fileName = response.getApplyNo();
        downloadInfo.info = loanInfo;
        
	}


	@Override
	public void run() {
		
		System.out.println("开始签名--------");
		
		signPdf();
		
		System.out.println("签名结束--------");
	}
	
}

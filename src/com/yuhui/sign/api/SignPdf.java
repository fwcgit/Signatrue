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
import com.yuhui.sign.db.DataBaseOpt;
import com.yuhui.sign.pdf.CreatePdf;
import com.yuhui.sign.utils.FileUtils;

public class SignPdf implements Runnable {
	
	private LoanInfo loanInfo;
	
	public SignPdf(LoanInfo loanInfo) {
		this.loanInfo = loanInfo;
	}


	private void signMMPdf(){
        ApplySignFileRequest.Builder builder = new ApplySignFileRequest.Builder();
        builder.withContractName("手机买卖合同"); // 合同名称，必填
        builder.withContractAmount((double) loanInfo.jieKuanJinE); // 合同金额
        //1、本地文件方式
        builder.withFile(FileUtils.dirOldPath+ "mm"+loanInfo.idCard+".pdf");
        builder.withServerCa(1);
        builder.withDealType(DealType.AUTH_SIGN);
 
        HashSet<Signatory> signatories = Sets.newHashSet();
        
        Signatory signatory = new Signatory();
        signatory.setFullName(loanInfo.name); //姓名
        signatory.setSignatoryIdentityType(IdentityType.IDCARD); //证件类型
        signatory.setIdentityCard(loanInfo.idCard); //证件号码
        signatory.setMobile(loanInfo.cellphone);
        signatory.setOrderNum(2);
        
        JSONArray chapteJsonArray=new JSONArray();
        
        JSONObject pageJson=new JSONObject();
        pageJson.put("page", 1);
        JSONArray chaptes=new JSONArray();
        pageJson.put("chaptes", chaptes);
        chapteJsonArray.add(pageJson);
        
        JSONObject chapte=new JSONObject();
        chapte.put("offsetX", 0.08);
        chapte.put("offsetY", 0.18);
        chaptes.add(chapte);
         
        signatory.withChapteJson(chapteJsonArray);
        signatory.setSignLevel(1);
        signatories.add(signatory);
       
        //-----------------
        chapteJsonArray=new JSONArray();
        
        pageJson=new JSONObject();
        pageJson.put("page", 1);
        chaptes=new JSONArray();
        pageJson.put("chaptes", chaptes);
        chapteJsonArray.add(pageJson);
        
        chapte=new JSONObject();
        chapte.put("offsetX", 0.65);
        chapte.put("offsetY", 0.26);
        chaptes.add(chapte);
        
	  signatory = new Signatory();
	  signatory.setFullName("上海禹徽资产管理有限公司"); //姓名 
	  signatory.setSignatoryIdentityType(IdentityType.BIZLIC); //证件类型 
	  signatory.setIdentityCard("91310120MA1HKP1F0K");//证件号码(营业执照号或统一社会信用代码) 
	  signatory.setEmail("916020238@qq.com");//企业账户注册邮箱
	  signatory.setOrderNum(1);
	  
	  signatory.withChapteJson(chapteJsonArray);
	  signatory.setSignLevel(0);
	  signatories.add(signatory);


        builder.withSignatories(signatories); // 添加签约人
        builder.withSignLevel(SignLevel.GENERAL.getCode()); // 签字类型,这里选择标准图形章
        builder.withRemark("这里是备注信息，不超过500个字符"); //备注
        builder.withPreRecored("前执记录，会计录到日志中！");
        builder.withOrderFlag(1);//1为按顺序（Signatory.orderNum），其它为不按顺序，默认不按顺序
        //builder.withNeedCa(1);//使用CA认证
        /**多合同顺序签约*/
        //SequenceInfo sequenceInfo=new SequenceInfo("XX001",2,2);
        //builder.withSequenceInfo(sequenceInfo);
        
        ApplySignResponse response = JunziqianClientInit.getClient().applySignFile(builder.build());

        if(response.isSuccess()){
            DataBaseOpt.getInstance().updateLoanInfo(response.getApplyNo(),loanInfo.idCard,1,0);
        }
	}
	
	private void signZPPdf(){
        ApplySignFileRequest.Builder builder = new ApplySignFileRequest.Builder();
        builder.withContractName("手机租赁合同"); // 合同名称，必填
        builder.withContractAmount((double) loanInfo.jieKuanJinE); // 合同金额
        //1、本地文件方式
        builder.withFile(FileUtils.dirOldPath+"zp"+loanInfo.idCard+".pdf");
        builder.withServerCa(1);
        builder.withDealType(DealType.AUTH_SIGN);
        
        HashSet<Signatory> signatories = Sets.newHashSet();
        
        Signatory signatory = new Signatory();
        signatory.setFullName(loanInfo.name); //姓名
        signatory.setSignatoryIdentityType(IdentityType.IDCARD); //证件类型
        signatory.setIdentityCard(loanInfo.idCard); //证件号码
        signatory.setMobile(loanInfo.cellphone);
        signatory.setOrderNum(2);

        JSONArray chapteJsonArray=new JSONArray();
        
        JSONObject pageJson=new JSONObject();
        pageJson.put("page", 1);
        JSONArray chaptes=new JSONArray();
        pageJson.put("chaptes", chaptes);
        chapteJsonArray.add(pageJson);
        
        JSONObject chapte=new JSONObject();
        chapte.put("offsetX", 0.08);
        chapte.put("offsetY", 0.81);
        chaptes.add(chapte);
               
        signatory.withChapteJson(chapteJsonArray);
        signatory.setSignLevel(1);
        signatories.add(signatory);
        
        
        //-----------------
        chapteJsonArray=new JSONArray();
        
        pageJson=new JSONObject();
        pageJson.put("page", 1);
        chaptes=new JSONArray();
        pageJson.put("chaptes", chaptes);
        chapteJsonArray.add(pageJson);
        
        chapte=new JSONObject();
        chapte.put("offsetX", 0.7);
        chapte.put("offsetY", 0.91);
        chaptes.add(chapte);
        
	  signatory = new Signatory();
	  signatory.setFullName("上海禹徽资产管理有限公司"); //姓名 
	  signatory.setSignatoryIdentityType(IdentityType.BIZLIC); //证件类型 
	  signatory.setIdentityCard("91310120MA1HKP1F0K");//证件号码(营业执照号或统一社会信用代码) 
	  signatory.setEmail("916020238@qq.com");//企业账户注册邮箱
	  signatory.setOrderNum(1);
	  
	  signatory.withChapteJson(chapteJsonArray);
	  signatory.setSignLevel(0);
	  signatories.add(signatory);


        builder.withSignatories(signatories); // 添加签约人
        builder.withSignLevel(SignLevel.GENERAL.getCode()); // 签字类型,这里选择标准图形章
        builder.withRemark("这里是备注信息，不超过500个字符"); //备注
        builder.withPreRecored("前执记录，会计录到日志中！");
        builder.withOrderFlag(1);//1为按顺序（Signatory.orderNum），其它为不按顺序，默认不按顺序
        //builder.withNeedCa(1);//使用CA认证
        /**多合同顺序签约*/
        //SequenceInfo sequenceInfo=new SequenceInfo("XX001",2,2);
        //builder.withSequenceInfo(sequenceInfo);

        ApplySignResponse response = JunziqianClientInit.getClient().applySignFile(builder.build());
        
        if(response.isSuccess()){
            DataBaseOpt.getInstance().updateLoanInfo(response.getApplyNo(),loanInfo.idCard,2,0);
        }
	}


	@Override
	public void run() {
		
		System.out.println("开始签名--------");
		
		CreatePdf createPdf = new CreatePdf(loanInfo);
		if(loanInfo.type == 1) {
			createPdf.createMMPdfFile();
			signMMPdf();
		}else {
			createPdf.createZPPdfFile();
			signZPPdf();
		}
	
		System.out.println("签名结束--------");
	}
	
}

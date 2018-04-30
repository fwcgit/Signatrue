package com.yuhui.sign.http;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.util.TextUtils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.yuhui.sign.bean.BaseResponse;
import com.yuhui.sign.bean.LoanInfo;
import com.yuhui.sign.db.DataBaseOpt;
import com.yuhui.sign.pdf.CreatePdf;

/**
 * Servlet implementation class GetSignPdf
 */
@WebServlet("/GetSignPdf")
public class GetSignPdf extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetSignPdf() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("404").append(request.getContextPath()).close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("UTF-8");
		
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.errorCode = 1;
		baseResponse.msg = "合同不存在！";
			
		String type = request.getParameter("type");
		String name = request.getParameter("name");
		String idcard = request.getParameter("idcard");
		String cellphone = request.getParameter("cellphone");
		String address = request.getParameter("address");
		String email = request.getParameter("email");
		String bankName = request.getParameter("bankName");
		String bankCard = request.getParameter("bankCard");
		String deviceId = request.getParameter("deviceId");
		String deviceName = request.getParameter("deviceName");
		String deviceType = request.getParameter("deviceType");
		String jieKuanJinE = request.getParameter("jieKuanJinE");
		String jieKuanRiQi = request.getParameter("jieKuanRiQi");
		String jieKuanTianShu = request.getParameter("jieKuanTianShu");
			
		if(type.equals("3")|| type.equals("4")) {

			baseResponse.errorCode = 111;
			if(TextUtils.isEmpty(name)) {
				baseResponse.msg = "name empty";
			}else if(TextUtils.isEmpty(idcard)) {
				baseResponse.msg = "idcard empty";
			}else if(TextUtils.isEmpty(cellphone)) {
				baseResponse.msg = "cellphone empty";
			}else if(TextUtils.isEmpty(address)) {
				baseResponse.msg = "address empty";
			}else if(TextUtils.isEmpty(email)) {
				baseResponse.msg = "email empty";
			}else if(TextUtils.isEmpty(bankName)) {
				baseResponse.msg = "bankName empty";
			}else if(TextUtils.isEmpty(bankCard)) {
				baseResponse.msg = "bankCard empty";
			}else if(TextUtils.isEmpty(deviceId)) {
				baseResponse.msg = "deviceId empty";
			}else if(TextUtils.isEmpty(deviceName)) {
				baseResponse.msg = "deviceName empty";
			}else if(TextUtils.isEmpty(deviceType)) {
				baseResponse.msg = "deviceType empty";
			}else if(TextUtils.isEmpty(jieKuanJinE)) {
				baseResponse.msg = "jieKuanJinE empty";
			}else if(TextUtils.isEmpty(jieKuanRiQi)) {
				baseResponse.msg = "jieKuanRiQi empty";
			}else if(TextUtils.isEmpty(jieKuanTianShu)) {
				baseResponse.msg = "jieKuanTianShu empty";
			}else {
				
				LoanInfo loanInfo = new LoanInfo();
				
				loanInfo.name = name;
				loanInfo.idCard = idcard;
				loanInfo.cellphone = cellphone;
				loanInfo.address = address;
				loanInfo.email = email;
				loanInfo.bankName = bankName;
				loanInfo.bankCard = bankCard;
				loanInfo.deviceID = deviceId;
				loanInfo.deviceName = deviceName;
				loanInfo.deviceType = deviceType;
				loanInfo.jieKuanJinE = Float.valueOf(jieKuanJinE);
				loanInfo.jieKuanRiQi = Long.valueOf(jieKuanRiQi);
				loanInfo.fangKuanShiJian = loanInfo.jieKuanRiQi;
				loanInfo.huanKuanRiQi = loanInfo.fangKuanShiJian + (Integer.valueOf(jieKuanTianShu)-1) * 24 * 60 * 60 * 1000;
				
				
				CreatePdf createPdf = new CreatePdf(loanInfo);
				baseResponse.errorCode = 0;
				baseResponse.msg = "成功";
				
				if(type.equals("3")) {
					baseResponse.data = createPdf.getMMHtml();
				}else if(type.equals("4")) {
					baseResponse.data = createPdf.getZPHtml();
				}
			}
				
		}else {
			
			JsonArray jsonArray = DataBaseOpt.getInstance().queryJson("select * from download where type = '"+type+"' and idcard = '"+idcard+"'");
			if(jsonArray.size() > 0 ) {
				
				JsonObject jsonObject = jsonArray.get(0).getAsJsonObject();
				long time = jsonObject.get("time").getAsLong();
				
				for(int i = 0 ;i < jsonArray.size();i++) {
					JsonObject tempJson = jsonArray.get(i).getAsJsonObject();
					if(time < tempJson.get("time").getAsLong()) {
						jsonObject = tempJson;
					}
				}
				
				if(jsonObject.has("download")) {
					if(jsonObject.get("download").getAsString().equals("1")) {
						String filename = jsonObject.get("filename").getAsString();
						baseResponse.errorCode = 0;
						baseResponse.msg = "成功";
						baseResponse.data = "http://sign.yuhuizichan.com:18981/LoanSign/downloadPdf?"+filename+".pdf";
					}else {
						baseResponse.errorCode = 1;
						baseResponse.msg = "合同不存在！";
					}
				}
			}
		}
		
		System.out.println(type + " ---" + idcard);
		
		String json = new Gson().toJson(baseResponse);
		OutputStream os = response.getOutputStream();
		os.write(json.getBytes("UTF-8"));
		os.flush();
		os.close();
	}

}

package com.yuhui.sign.http;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.junziqian.api.bean.ResultInfo;
import com.yuhui.sign.db.DataBaseOpt;
import com.yuhui.sign.download.DownloadInfo;
import com.yuhui.sign.download.DownloadTask;
import com.yuhui.sign.utils.FileUtils;

/**
 * Servlet implementation class SignaCallbak
 */
@WebServlet("/SignaCallbak")
public class SignaCallback extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public SignaCallback() {
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		OutputStream os = response.getOutputStream();
		
		Enumeration<String> params = request.getParameterNames();
		
		JsonObject jsonObject = new JsonObject();
		
		while(params.hasMoreElements()){
			
			String key = params.nextElement();
			
			jsonObject.addProperty(key, request.getParameter(key));
			
		}
		
		Gson gson = new Gson();
		String jsonStr = jsonObject.toString();
		
		System.err.println("SignaCallback : " + jsonStr);
		
		if(!jsonStr.equals("{}")){
			
			CallBackInfo callBackInfo = gson.fromJson(jsonObject, CallBackInfo.class);
			
			if(callBackInfo.signStatus != 3 ) {
				System.out.println("状态" +callBackInfo.signStatus);
				os.write(gson.toJson(ResultInfo.create().success()).toString().getBytes("UTF-8"));
				os.close();
				return;
			}
			
			DownloadInfo downloadInfo = null;
			
			JsonArray jsonArray = DataBaseOpt.getInstance().queryJson("select * from loaninfo where "
					+ "idcard='"+callBackInfo.identityCard+"' and type=1 and signstatu=0 and applyno="+"'"+callBackInfo.applyNo+"'");
			
			if(jsonArray.size() > 0 ){
				
				JsonObject jsonLoan = lastJson(jsonArray);
				
				DataBaseOpt.getInstance().updateLoaninfoSignstatu(jsonLoan.get("id").getAsString());
				
				if(jsonLoan.has("phone")) {
					
					String phone = jsonLoan.get("phone").getAsString();
					
					downloadInfo = new DownloadInfo();
					downloadInfo.applyNo  = callBackInfo.applyNo;
					downloadInfo.fileName = callBackInfo.applyNo;
					downloadInfo.fullName = callBackInfo.fullName;
					downloadInfo.idcard   = callBackInfo.identityCard;
					downloadInfo.identityType = callBackInfo.identityType;
					downloadInfo.path	  = FileUtils.dirPath+callBackInfo.applyNo+".pdf";
					downloadInfo.phone = phone;
					downloadInfo.type = 1;
					
					DownloadTask.getInstance().produce(downloadInfo);
				}

			}else {
				
				jsonArray = DataBaseOpt.getInstance().queryJson("select * from loaninfo where "
						+ "idcard='"+callBackInfo.identityCard+"' and type=2 and signstatu=0 and applyno="+"'"+callBackInfo.applyNo+"'");
				
				if(jsonArray.size() > 0 ){
					
					JsonObject jsonLoan = lastJson(jsonArray);
					
					DataBaseOpt.getInstance().updateLoaninfoSignstatu(jsonLoan.get("id").getAsString());
					
					if(jsonLoan.has("phone")) {
						
						String phone = jsonLoan.get("phone").getAsString();
						
						downloadInfo = new DownloadInfo();
						downloadInfo.applyNo  = callBackInfo.applyNo;
						downloadInfo.fileName = callBackInfo.applyNo;
						downloadInfo.fullName = callBackInfo.fullName;
						downloadInfo.idcard   = callBackInfo.identityCard;
						downloadInfo.identityType = callBackInfo.identityType;
						downloadInfo.path	  = FileUtils.dirPath+callBackInfo.applyNo+".pdf";
						downloadInfo.phone = phone;
						downloadInfo.type = 2;
						
						DownloadTask.getInstance().produce(downloadInfo);
					}

				}
				
			}
			
			os.write(gson.toJson(ResultInfo.create().success()).toString().getBytes("UTF-8"));
		
		}else{
			
			os.write(gson.toJson(ResultInfo.create().fail()).toString().getBytes("UTF-8"));

		}
		
		os.flush();
		os.close();
	}
	
	private JsonObject lastJson(JsonArray jsonArray) {
		
		JsonObject obj = new JsonObject();
		obj = jsonArray.get(0).getAsJsonObject();
		long time = jsonArray.get(0).getAsJsonObject().get("time").getAsLong();
		
		for(int i = 0 ;i < jsonArray.size();i++) {
			JsonObject tempJson = jsonArray.get(i).getAsJsonObject();
			if(time < tempJson.get("time").getAsLong()) {
				obj = tempJson;
			}
		}
		return obj;
	}
	

	public static void main(String[] args) {
		JsonArray jsonArray = DataBaseOpt.getInstance().queryJson("select phone from loaninfo where "
				+ "idcard='"+"412326199708156027"+"' and type=2 and signstatu=1");
		System.out.println(jsonArray.toString());
	}
}

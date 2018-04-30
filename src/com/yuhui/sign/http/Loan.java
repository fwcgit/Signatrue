package com.yuhui.sign.http;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.util.TextUtils;

import com.google.gson.Gson;
import com.yuhui.sign.api.SignTask;
import com.yuhui.sign.bean.BaseResponse;
import com.yuhui.sign.bean.LoanInfo;
import com.yuhui.sign.db.DataBaseOpt;

/**
 * Servlet implementation class LoanIno
 */
@WebServlet("/LoanIno")
public class Loan extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Loan() {
        super();
        // TODO Auto-generated constructor stub
    }
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		InputStream is = request.getInputStream();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		int readLength = -1;
		
		byte[] buff = new byte[4 * 1024];
		
		while(((readLength = is.read(buff))) > 0) {
			baos.write(buff,0,readLength);
		}
		
		baos.flush();
		baos.close();
		
		String bodyStr = new String(baos.toByteArray(),"UTF-8");
		
		System.out.println("http request body = " + bodyStr);
		
		BaseResponse baseResponse = new BaseResponse();
		
		if(TextUtils.isEmpty(bodyStr)) {
			
			baseResponse.errorCode = 800;
			baseResponse.msg	   = "body not empty";
			
		}else {
			
			baseResponse.errorCode = 0;
			baseResponse.msg	   = "success";
			
			CallBackLoanInfo callBackLoanInfo = new Gson().fromJson(bodyStr, CallBackLoanInfo.class);
			LoanInfo loanInfo = callBackLoanInfo.result;
			loanInfo.huanKuanRiQi = loanInfo.jieKuanRiQi+(loanInfo.jieKuanTianShu-1)*24*60*60*1000;
			
			DataBaseOpt.getInstance().insertLoanInfo(loanInfo.name, loanInfo.cellphone, loanInfo.idCard, bodyStr, "", 0, 1,System.currentTimeMillis());
			
			loanInfo.type = 1;
			
			CallBackLoanInfo callBackLoanInfo1 = new Gson().fromJson(bodyStr, CallBackLoanInfo.class);
			LoanInfo loanInfo1 = callBackLoanInfo1.result;
			loanInfo1.huanKuanRiQi = loanInfo.jieKuanRiQi+(loanInfo.jieKuanTianShu-1)*24*60*60*1000;
			
			DataBaseOpt.getInstance().insertLoanInfo(loanInfo.name, loanInfo.cellphone, loanInfo.idCard, bodyStr, "", 0, 2,System.currentTimeMillis());

			loanInfo1.type = 2;
			
			SignTask.getInstance().addSignTask(loanInfo);
			SignTask.getInstance().addSignTask(loanInfo1);
			
		}
		
		String json = new Gson().toJson(baseResponse);
		PrintWriter writer = response.getWriter();
		writer.write(json);
		
		writer.close();
	}

	
	
}

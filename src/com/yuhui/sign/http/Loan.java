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
		
		BaseResponse baseResponse = new BaseResponse();
		
		if(TextUtils.isEmpty(bodyStr)) {
			
			baseResponse.errorCode = 800;
			baseResponse.msg	   = "body not empty";
			
		}else {
			
			baseResponse.errorCode = 0;
			baseResponse.msg	   = "success";
			
			LoanInfo loanInfo = new Gson().fromJson(bodyStr, LoanInfo.class);
			DataBaseOpt.getInstance().insertLoanInfo(loanInfo.name, loanInfo.phone, loanInfo.idcard, bodyStr, "", 0, System.currentTimeMillis());
			
			SignTask.getInstance().addSignTask(loanInfo);
			
			System.out.println("http request body = " + bodyStr);
		}
		
		String json = new Gson().toJson(baseResponse);
		PrintWriter writer = response.getWriter();
		writer.write(json);
		
		writer.close();
	}

	
	
}

package com.yuhui.sign.http;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.junziqian.api.bean.ResultInfo;

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
			
			os.write(gson.toJson(ResultInfo.create().success()).toString().getBytes("UTF-8"));
		
		}else{
			
			os.write(gson.toJson(ResultInfo.create().fail()).toString().getBytes("UTF-8"));

		}
		
		os.flush();
		os.close();
	}

}

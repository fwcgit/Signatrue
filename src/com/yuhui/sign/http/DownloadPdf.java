package com.yuhui.sign.http;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Download
 */
@WebServlet("/Download")
public class DownloadPdf extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadPdf() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			String name = request.getQueryString();
			
			System.out.println(name);
			
			clientDownload(response,name);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	private void clientDownload(HttpServletResponse response,String name){
		//处理请求  
        //读取要下载的文件  
		File file = new File("D:/pdf/"+name);		
        if(file.exists()){  
            FileInputStream fis = null;
          //获取响应报文输出流对象  
            ServletOutputStream  out = null;
			try {
				fis = new FileInputStream(file);
				 String filename=URLEncoder.encode(file.getName(),"utf-8"); //解决中文文件名下载后乱码的问题  
		            byte[] b = new byte[fis.available()];  
		            fis.read(b);  
		            response.setCharacterEncoding("utf-8");  
		            response.setHeader("Content-Disposition","attachment; filename="+filename+"");
		            //获取响应报文输出流对象  
		            out =response.getOutputStream();  
		            //输出  
		            out.write(b);  
		            out.flush();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}finally {
				if(null != fis){
					try {
						fis.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
				if(null != out) {
					try {
						out.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}  
           
        }else {
        	try {
				response.getOutputStream().close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }     
	}

}

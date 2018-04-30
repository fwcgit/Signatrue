package com.yuhui.sign.pdf;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.util.TextUtils;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.yuhui.sign.bean.LoanInfo;
import com.yuhui.sign.db.DataBaseOpt;
import com.yuhui.sign.utils.FileUtils;

public class CreatePdf {
	
	private LoanInfo loanInfo;
	
	public CreatePdf(LoanInfo loanInfo) {
		super();
		this.loanInfo = loanInfo;
	}
	
	private List<String> readMMOldXy(){
		
		List<String> listLines = new ArrayList<String>();
		
		BufferedReader br = null;
		
		try {
			
			FileInputStream fis = new FileInputStream("./res/mm.txt");
			br = new BufferedReader(new InputStreamReader(fis, "UTF-8"));
			
			String line = null;
			
			while((line = br.readLine()) != null){
				
				if(TextUtils.isEmpty(line)){
					listLines.add("\r\n");
				}
				
				listLines.add(line);
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(null != br){
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return listLines;
	}
	
	private List<String> readMM2OldXy(){
		
		List<String> listLines = new ArrayList<String>();
		
		BufferedReader br = null;
		
		try {
			
			FileInputStream fis = new FileInputStream("./res/mm2.txt");
			br = new BufferedReader(new InputStreamReader(fis, "UTF-8"));
			
			String line = null;
			
			while((line = br.readLine()) != null){
				
				if(TextUtils.isEmpty(line)){
					listLines.add("\r\n");
				}
				
				listLines.add(line);
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(null != br){
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return listLines;
	}
	
	
	private List<String> readZPOldXy(){
		
		List<String> listLines = new ArrayList<String>();
		
		BufferedReader br = null;
		
		try {
			
			FileInputStream fis = new FileInputStream("./res/zp.txt");
			br = new BufferedReader(new InputStreamReader(fis, "UTF-8"));
			
			String line = null;
			
			while((line = br.readLine()) != null){
				
				if(TextUtils.isEmpty(line)){
					listLines.add("\r\n");
				}
				
				listLines.add(line);
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(null != br){
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return listLines;
	}
	
	private List<String> readZP2OldXy(){
		
		List<String> listLines = new ArrayList<String>();
		
		BufferedReader br = null;
		
		try {
			
			FileInputStream fis = new FileInputStream("./res/zp2.txt");
			br = new BufferedReader(new InputStreamReader(fis, "UTF-8"));
			
			String line = null;
			
			while((line = br.readLine()) != null){
				
				if(TextUtils.isEmpty(line)){
					listLines.add("\r\n");
				}
				
				listLines.add(line);
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(null != br){
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return listLines;
	}
	
	// 产生PDF字体
    public static Font getChineseFont(boolean isBold) {
        BaseFont bf = null;
        Font fontChinese = null;
        try {
            bf = BaseFont.createFont("./res/STZHONGS.TTF",
                    BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            fontChinese = new Font(bf, 11, isBold ? Font.BOLD : Font.NORMAL);
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return fontChinese;
    }
	
    public void createMMPdfFile(){
    	Rectangle rect = new Rectangle(PageSize.A4);
		rect.setBackgroundColor(BaseColor.WHITE);
		Document document = new Document(rect, 20, 20, 20, 20);
		
		try {
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(FileUtils.dirOldPath+"mm"+loanInfo.idCard+".pdf"));
			writer.setPdfVersion(PdfWriter.PDF_VERSION_1_2);
			
			//文档属性  
			document.addTitle("手机买卖合同");  
			document.addAuthor("合同");  
			document.addSubject("禹微");  
			document.addKeywords("急借白卡");  
			document.addCreator("合同");  
			
			document.open();
			
			//Paragraph paragraph = new Paragraph();
			
			List<String> listLines = readMMOldXy();
			Font font = getChineseFont(false);
			for(int i = 0 ; i < listLines.size() ; i++){
				String str = listLines.get(i);
				str = str.replaceAll("MF_NAME", loanInfo.name);
				str = str.replaceAll("MF_IDCARD",loanInfo.idCard);
				str = str.replaceAll("MF_ZS", loanInfo.address);
				str = str.replaceAll("MF_PHONE", loanInfo.cellphone);
				str = str.replaceAll("PP", loanInfo.deviceName);
				str = str.replaceAll("XH", loanInfo.deviceType);
				str = str.replaceAll("IMEI", loanInfo.deviceID);
				
				String jiegeStr = String.format("%.2f", loanInfo.jieKuanJinE);
				
				str = str.replaceAll("JIAGE", jiegeStr);
				str = str.replaceAll("DX_PRICE", numberConvertWord(jiegeStr)+"元整");
				str = str.replaceAll("SIGN_DATE", dateConvert(loanInfo.jieKuanRiQi));
				str = str.replaceAll("FK_TIME", dateConvert(loanInfo.fangKuanShiJian));
				str = str.replaceAll("HK_PRICE", loanInfo.jieKuanJinE+"");
				str = str.replaceAll("KAIHUHANG", loanInfo.bankName);
				str = str.replaceAll("BANK_CARD", loanInfo.bankCard);
				str = str.replaceAll("MF_MEIAL", loanInfo.email);

				
				if(i > 0 && i % 90 == 0){
					document.newPage();
				}
				
				document.add(new Paragraph(str,font));
		
			}
			
			document.close();
			
			System.err.println("生成完成。。。。。");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void createZPPdfFile(){
    	Rectangle rect = new Rectangle(PageSize.A4);
		rect.setBackgroundColor(BaseColor.WHITE);
		Document document = new Document(rect, 20, 20, 20, 20);
		
		try {
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(FileUtils.dirOldPath+"zp"+loanInfo.idCard+".pdf"));
			writer.setPdfVersion(PdfWriter.PDF_VERSION_1_2);
			
			//文档属性  
			document.addTitle("手机租赁合同");  
			document.addAuthor("合同");  
			document.addSubject("禹微");  
			document.addKeywords("急借白卡");  
			document.addCreator("合同");  
			
			document.open();
			
			//Paragraph paragraph = new Paragraph();
			
			List<String> listLines = readZPOldXy();
			Font font = getChineseFont(false);
			for(int i = 0 ; i < listLines.size() ; i++){
				String str = listLines.get(i);
				
				if(str.contains("合同编号： HT_ID")) {
					str = str.replaceAll("HT_ID", DataBaseOpt.getInstance().getHeTongNumber());
	    		}
				
				str = str.replaceAll("CZ_NAME", loanInfo.name);
				str = str.replaceAll("CZ_IDCARD", loanInfo.idCard);
				str = str.replaceAll("CZ_ZS", loanInfo.address);
				str = str.replaceAll("CZ_PHONE", loanInfo.cellphone);
				str = str.replaceAll("CZ_EMAIL", loanInfo.email);
				str = str.replaceAll("PP", loanInfo.deviceName);
				str = str.replaceAll("XH", loanInfo.deviceType);
				str = str.replaceAll("IMEI", loanInfo.deviceID);
				str = str.replaceAll("SIGN_DATE", dateConvert(loanInfo.jieKuanRiQi));
				
				float jiage = loanInfo.jieKuanJinE * 0.95f;
				String jiegeStr = String.format("%.2f", jiage);
//				+"(大写:"+numberConvertWord(jiegeStr)+")"
				str = str.replaceAll("CANZHI_PRICE", jiegeStr);
				str = str.replaceAll("RI_PRICE", String.format("%.2f", loanInfo.jieKuanJinE * 0.01f));
				str = str.replaceAll("START_DATE", dateConvert(loanInfo.jieKuanRiQi));
				str = str.replaceAll("END_DATE", dateConvert(loanInfo.huanKuanRiQi));
				str = str.replaceAll("LAST_DATE", dateConvert(loanInfo.huanKuanRiQi+24*60*60*1000));
				str = str.replaceAll("XZ_JIAGE", String.format("%.2f", loanInfo.jieKuanJinE * 0.01f));
							
				if(i > 0 && i % 90 == 0){
					document.newPage();
				}
				
				document.add(new Paragraph(str,font));
		
			}
			
			document.close();
			
			System.err.println("生成完成。。。。。");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public static String numberConvertWord(String number) {
    	String word[] = {"零","壹","贰","叁","肆","伍","陆","柒","捌","玖","拾","佰","仟","万"};
    	String word1[] = {"角","分"};
    	
    	StringBuffer sb = new StringBuffer();
    	
    	if(number.indexOf(".") >= 0) {
    		
    		String[] ns = number.split("\\.");
    		String n1 = ns[0];
    		String n2 = ns[1];
    		
    		for(int i = n1.length()-1,j = 0 ; i >= 0 ;i--,j++) {
        		
        		int unit = i+9;
        		String str = n1.substring(j, j+1);
        		
        		if(!str.equals("0")) {
        			sb.append(word[Integer.valueOf(str)]);
        			if(unit >= 10)
        				sb.append(word[unit]);
        		}
        	}
    		
    		for(int i = n2.length()-1,j = 0 ; i >= 0 ;i--,j++) {
        		
        		String str = n2.substring(j, j+1);
        		
        		if(!str.equals("0")) {
        			sb.append(word[Integer.valueOf(str)]);
        			sb.append(word1[j]);
        		}
        	}
        	
    	}else {
        	for(int i = number.length()-1,j = 0 ; i >= 0 ;i--,j++) {
        		
        		int unit = i+9;
        		String str = number.substring(j, j+1);
        		
        		if(!str.equals("0")) {
        			sb.append(word[Integer.valueOf(str)]);
        			if(unit >= 10)
        				sb.append(word[unit]);
        		}
        	}
    	}
    	

    	return sb.toString();
    }
    
    public String getMMHtml() {
    	
    	List<String> list = readMM2OldXy();
    	StringBuffer sb = new StringBuffer();
    	sb.append("<!DOCTYPE html>");
    	sb.append("<html>");
    	sb.append("<head>");
    	sb.append("<meta charset=\"utf-8\">");
    	sb.append("<title>手机买卖协议</title>");
    	sb.append("</head>");
    	sb.append("<body>");
    	
    	for (String str : list) {
    		
			str = str.replaceAll("MF_NAME", loanInfo.name);
			str = str.replaceAll("MF_IDCARD",loanInfo.idCard);
			str = str.replaceAll("MF_ZS", loanInfo.address);
			str = str.replaceAll("MF_PHONE", loanInfo.cellphone);
			str = str.replaceAll("PP", loanInfo.deviceName);
			str = str.replaceAll("XH", loanInfo.deviceType);
			str = str.replaceAll("IMEI", loanInfo.deviceID);
			
			String jiegeStr = String.format("%.2f", loanInfo.jieKuanJinE);
			
			str = str.replaceAll("JIAGE", jiegeStr);
			str = str.replaceAll("DX_PRICE", numberConvertWord(jiegeStr)+"元整");
			str = str.replaceAll("SIGN_DATE", dateConvert(loanInfo.jieKuanRiQi));
			str = str.replaceAll("FK_TIME", dateConvert(loanInfo.fangKuanShiJian));
			str = str.replaceAll("HK_PRICE", loanInfo.jieKuanJinE+"");
			str = str.replaceAll("KAIHUHANG", loanInfo.bankName);
			str = str.replaceAll("BANK_CARD", loanInfo.bankCard);
			str = str.replaceAll("MF_MEIAL", loanInfo.email);
			
			sb.append("<p>"+str+"</p>");
		}
    	
    	sb.append("</body>");
    	sb.append("</html>");
    	
    	return sb.toString();
    }
    
    public String getZPHtml() {
    	
    	List<String> list = readZP2OldXy();
    	StringBuffer sb = new StringBuffer();
    	sb.append("<!DOCTYPE html>");
    	sb.append("<html>");
    	sb.append("<head>");
    	sb.append("<meta charset=\"utf-8\">");
    	sb.append("<title>手机租赁协议</title>");
    	sb.append("</head>");
    	sb.append("<body>");
    	
    	for (String str : list) {
    		if(str.contains("合同编号： HT_ID") || TextUtils.isEmpty(str)) {
    			continue;
    		}
//    		str = str.replaceAll("HT_ID", DataBaseOpt.getInstance().getHeTongNumber());
			str = str.replaceAll("CZ_NAME", loanInfo.name);
			str = str.replaceAll("CZ_IDCARD", loanInfo.idCard);
			str = str.replaceAll("CZ_ZS", loanInfo.address);
			str = str.replaceAll("CZ_PHONE", loanInfo.cellphone);
			str = str.replaceAll("CZ_EMAIL", loanInfo.email);
			str = str.replaceAll("PP", loanInfo.deviceName);
			str = str.replaceAll("XH", loanInfo.deviceType);
			str = str.replaceAll("IMEI", loanInfo.deviceID);
			str = str.replaceAll("SIGN_DATE", dateConvert(loanInfo.jieKuanRiQi));
			
			float jiage = loanInfo.jieKuanJinE * 0.95f;
			String jiegeStr = String.format("%.2f", jiage);
//			+"(大写:"+numberConvertWord(jiegeStr)+")"
			str = str.replaceAll("CANZHI_PRICE", jiegeStr);
			str = str.replaceAll("RI_PRICE", String.format("%.2f", loanInfo.jieKuanJinE * 0.01f));
			str = str.replaceAll("START_DATE", dateConvert(loanInfo.jieKuanRiQi));
			str = str.replaceAll("END_DATE", dateConvert(loanInfo.huanKuanRiQi));
			str = str.replaceAll("LAST_DATE", dateConvert(loanInfo.huanKuanRiQi+24*60*60*1000));
			str = str.replaceAll("XZ_JIAGE", String.format("%.2f", loanInfo.jieKuanJinE * 0.01f));
			
			sb.append("<p>"+str+"</p>");
		}
    	
    	sb.append("</body>");
    	sb.append("</html>");
    	
    	return sb.toString();
    }
    
    public static String dateConvert(long time) {
    	SimpleDateFormat format = new SimpleDateFormat("yyyy 年 MM 月 dd 日");
    	return format.format(new Date(time));
    }
    
    public static void main(String[] args) {
    
		LoanInfo loanInfo = new LoanInfo();
		loanInfo.name = "线三";
		loanInfo.idCard = "123123213213";
		loanInfo.cellphone = "1234567890";
		loanInfo.address = "sh";
		loanInfo.email = "dsfs@qq.com";
		loanInfo.bankName = "招商银行";
		loanInfo.bankCard = "9988838298329382";
		loanInfo.deviceID = "sdjfljd";
		loanInfo.deviceName = "xiaomi";
		loanInfo.deviceType = "mimimi";
		loanInfo.jieKuanJinE = 1000f;
		loanInfo.jieKuanRiQi = System.currentTimeMillis();
		loanInfo.fangKuanShiJian = loanInfo.jieKuanRiQi;
		loanInfo.huanKuanRiQi = loanInfo.fangKuanShiJian + 15 * 24 * 60 * 60 * 1000;
		
		CreatePdf createPdf = new CreatePdf(loanInfo);
		createPdf.getZPHtml();
	}
}

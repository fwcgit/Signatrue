package com.yuhui.sign.pdf;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
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

public class CreatePdf {
	public void createPdfFile(){
		
		Rectangle rect = new Rectangle(PageSize.A4);
		rect.setBackgroundColor(BaseColor.WHITE);
		Document document = new Document(rect, 20, 20, 20, 20);
		
		try {
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("./res/xy.pdf"));
			writer.setPdfVersion(PdfWriter.PDF_VERSION_1_2);
			
			//文档属性  
			document.addTitle("应急卡借款协议");  
			document.addAuthor("协议");  
			document.addSubject("禹微");  
			document.addKeywords("应急卡");  
			document.addCreator("协议");  
			
			document.open();
			
			//Paragraph paragraph = new Paragraph();
			
			List<String> listLines = readOldXy();
			
			for(int i = 0 ; i < listLines.size() ; i++){
				String str = listLines.get(i);
				str = str.replaceAll("YI_NAME", "张三");
				str = str.replaceAll("YI_ID_NUMBER", "12348927492379427");
				str = str.replaceAll("YI_PHONE", "13167118362");
				str = str.replaceAll("YI_EMAIL", "19379274W@QQ.com");
				
				str = str.replaceAll("RECEIVE_BANK_CARD", "3200932938284792739847924");
				str = str.replaceAll("RECEIVE_NAME", "张三");
				
				str = str.replaceAll("B_MONEY", "600");
				str = str.replaceAll("START_DATE", "2017年10月17日");
				str = str.replaceAll("END_DATE", "2017年10月26日");
				str = str.replaceAll("DAY", "10");
				str = str.replaceAll("YI_MONEY", "600");
				
				Font font = null;
				if(str.startsWith("2.1 借款本金")  || 
					str.startsWith("2.3费用") ||
					str.startsWith("在本协议中") ||
					str.startsWith("2.3.1 居间") ||
					str.startsWith("款特殊情况沟通等")||
					str.startsWith("方支付 居间服务费及借款服务费")||
					str.startsWith("A：手机验证费") ||
					str.startsWith("B：平台运营费") ||
					str.startsWith("4.1.1 一次划转") ||
					str.startsWith("4.2 乙方同意")||
					str.startsWith("4.3 若乙方未")||
					str.startsWith("5.1.1 自应还款日") ||
					str.startsWith("本金的正常利息停止计算")||
					str.startsWith("（1）根据本协议") ||
					str.startsWith("5.4 乙方应严格") ||
					str.startsWith("5.5 乙方应严格") ||
					str.startsWith("议提交衢州仲裁委员会仲裁")||
					str.startsWith("的裁决为终局裁决")||
					str.startsWith("达地址。如")||
					str.startsWith("约定邮箱或手机号码")||
					str.startsWith("仲裁文书电子送达")||
					str.startsWith("仲裁委员会网络仲裁平台")||
					str.startsWith("11.2 本协议各方") ||
					str.startsWith("第三方支付公司上海富友")||
					str.startsWith("账费用由乙方承担")||
					str.startsWith("乙方认可借款本")||
					str.startsWith("款项至丙方账户，由")||
					str.startsWith("乙双方均同意并确认")||
					str.startsWith("自身名义向乙方追讨")){
					
					font = getChineseFont(true);
					
				}else{
					font = getChineseFont(false);
				}
				
				if(i > 0 && i % 60 == 0){
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
	
	private List<String> readOldXy(){
		
		List<String> listLines = new ArrayList<String>();
		
		BufferedReader br = null;
		
		try {
			
			FileInputStream fis = new FileInputStream("./res/xy.txt");
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
	
}

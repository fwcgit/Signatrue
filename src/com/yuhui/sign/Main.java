package com.yuhui.sign;

import com.yuhui.sign.api.SignPdf;
import com.yuhui.sign.pdf.CreatePdf;

//测试环境key和接口地址：
//appKey : fe575856fe6826a2
//appSecrete : fdbf3c35fe575856fe6826a20195f06b
//services_url：http://sandbox.api.junziqian.com/services
//测试环境官网企业账户登陆地址:  http://sandbox.junziqian.com/login/loginPage
//用户名：yuweizichan@www.bccto.me
//密码：abc123
//
//沙箱测试环境需在hosts加五条记录（正式环境不需要加hosts），文件地址为C:\Windows\System32\drivers\etc\hosts
//
//219.153.5.242 sandbox.junziqian.com
//219.153.5.242 sandbox.api.junziqian.com
//219.153.5.242 sandbox.htb.ebaoquan.org
//219.153.5.242 sandbox.ebq.bz
//219.153.5.242 sandbox.account.ebaoquan.org
//企业名称：禹徽资产    营业执照号：120108019554456   邮箱：yuweizichan@www.bccto.me


public class Main {

	static int currY = 50;

	static int time = 3691;
	
	public static void main(String[] args) {
//		new CreatePdf().createPdfFile();
//		new SignPdf().signPdf();
		
		System.out.println(secondConvertMinute());
	}

	private static void scrollChildView(int offset){

        int scrollOffset = offset;

        if(currY + (-offset) < 0 ){
            scrollOffset = -offset + Math.abs(currY + offset);
        }

        scrollBy(0,-scrollOffset);

    }
	
	private static void scrollBy(int x,int y){
		currY = currY+y;
		System.out.println("x = 0 , y = " + currY);
	}
	
    private static String secondConvertMinute(){
        int h = time / 3600;
        int m = time % 3600 / 60;
        int s = time % 60;

        String timeStr = "";

        if(h < 10){
            timeStr = "0"+h;
        }else{
            timeStr = h+"";
        }

        timeStr+=":";

        if(m < 10){
            timeStr += "0"+m;
        }else{
            timeStr += m+"";
        }

        timeStr+=":";

        if(s < 10){
            timeStr += ("0"+s);
        }else{
            timeStr += (s+"");
        }

        return  timeStr;
    }
	
}

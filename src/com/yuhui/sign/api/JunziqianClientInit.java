package com.yuhui.sign.api;

import org.ebaoquan.rop.thirdparty.org.apache.commons.lang3.StringUtils;

import com.junziqian.api.JunziqianClient;

//正式环境key和接口地址：
//appKey：ab95585427499005
//appSecret：a69de458ab95585427499005b7eefc87
//services_url：http://api.junziqian.com/services
//官网企业账户登陆地址：https://www.junziqian.com/login/loginPage
	
/**
 * Created by wlinguo on 5/11/2016.
 */
public class JunziqianClientInit {
//	// 请填入服务地址（根据环境的不同选择不同的服务地址），沙箱环境，正式环境
//	public static final String SERVICE_URL = "http://sandbox.api.junziqian.com/services";
//
//	// 请填入你的APPKey
//	public static final String APP_KEY = "fe575856fe6826a2";
//
//	// 请填入你的APPSecret
//	public static final String APP_SECRET="fdbf3c35fe575856fe6826a20195f06b";
	
	// 请填入服务地址（根据环境的不同选择不同的服务地址），沙箱环境，正式环境
	public static final String SERVICE_URL = "http://api.junziqian.com/services";

	// 请填入你的APPKey
	public static final String APP_KEY = "ab95585427499005";

	// 请填入你的APPSecret
	public static final String APP_SECRET="a69de458ab95585427499005b7eefc87";


	private static JunziqianClient client;

	static {

		if (StringUtils.isBlank(SERVICE_URL)) {
			throw new RuntimeException("SERVICE_URL is null");
		}
		if (StringUtils.isBlank(APP_KEY)) {
			throw new RuntimeException("APP_KEY is null");
		}
		if (StringUtils.isBlank(APP_SECRET)) {
			throw new RuntimeException("APP_SECRET is null");
		}
		client = new JunziqianClient(SERVICE_URL, APP_KEY, APP_SECRET);
	}

	public static JunziqianClient getClient() {
		return client;
	}
}

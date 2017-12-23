package com.yuhui.sign.api;

import org.ebaoquan.rop.thirdparty.org.apache.commons.lang3.StringUtils;

import com.junziqian.api.JunziqianClient;

/**
 * Created by wlinguo on 5/11/2016.
 */
public class JunziqianClientInit {
	// 请填入服务地址（根据环境的不同选择不同的服务地址），沙箱环境，正式环境
	public static final String SERVICE_URL = "http://sandbox.api.junziqian.com/services";

	// 请填入你的APPKey
	public static final String APP_KEY = "fe575856fe6826a2";

	// 请填入你的APPSecret
	public static final String APP_SECRET="fdbf3c35fe575856fe6826a20195f06b";


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

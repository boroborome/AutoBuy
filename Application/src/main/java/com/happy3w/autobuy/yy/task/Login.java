/**
 * 
 */
package com.happy3w.autobuy.yy.task;

import com.happy3w.autobuy.config.SysConfig;
import com.happy3w.autobuy.task.action.IAction;
import com.happy3w.autobuy.util.HttpUtil;
import com.happy3w.autobuy.yy.task.action.YYVerifyImg;

/**
 * YY登录。
 * @version 2016年10月24日上午9:01:40
 * @author happy3w
 */
public class Login extends YYLoginInner {
	public static Login instance;
	private static HttpUtil http  =new HttpUtil();
	public static Login getInstance(SysConfig config)
	{
		if(null==instance){
			YYVerifyImg vc=new YYVerifyImg(config,http ,config.getWebServerUrl());
			instance = new Login(config,vc);
		}
		return instance;
	}
	private Login(SysConfig config,IAction verifycode) {
		super(config, verifycode);
		// TODO Auto-generated constructor stub
	}

}

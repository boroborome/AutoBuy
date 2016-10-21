package com.happy3w.autobuy.task.yy.action;

import com.happy3w.autobuy.config.SysConfig;
import com.happy3w.autobuy.task.action.VerifyImg;
import com.happy3w.autobuy.transfer.TransferUrl;
import com.happy3w.autobuy.util.HttpUtil;

/**
 * YY验证码读取。
 * @version 2016年10月18日上午10:57:25
 * @author happy3w
 */
public class YYVerifyImg extends VerifyImg {

	public YYVerifyImg(SysConfig config,HttpUtil http,String srvUrl)
	{
		super(config, "//*[@id=\"_verifyImg\"]", http, srvUrl+"/vc/upload.php", srvUrl+"/vc/result.php");
	}
	
}

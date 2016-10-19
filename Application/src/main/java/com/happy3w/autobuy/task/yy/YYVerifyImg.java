package com.happy3w.autobuy.task.yy;

import com.happy3w.autobuy.config.SysConfig;
import com.happy3w.autobuy.task.operation.VerifyCode;
import com.happy3w.autobuy.transfer.TransferUrl;
import com.happy3w.autobuy.util.HttpUtil;

public class YYVerifyCode extends VerifyCode {
	public YYVerifyCode(SysConfig config, TransferUrl transfer, HttpUtil http)
	{
		this(config,transfer,http,"//*[@id=\"_verifyImg\"]");
	}

	public YYVerifyCode(SysConfig config, TransferUrl transfer, HttpUtil http, String xpath) {
		super(config, transfer, http, xpath);
	}

}

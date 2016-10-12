/**
 * 
 */
package com.happy3w.autobuy.transfer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.happy3w.autobuy.config.SysConfig;

/**
 * 中转页面请求地址管理。
 * 
 * @version 2016年6月25日 下午3:28:12
 * @author Happy3W Cherry
 *
 */
@Component
public class TransferUrl {
	@Autowired
	private SysConfig config;
	private static TransferUrl context = new TransferUrl();

	public static TransferUrl getInstance() {
		return context;
	}

	public String getUploadUrl() {
		return config.getWebServerUrl() + "vc/upload.php";
	}

	public String getFileParamName() {
		return "file";
	}

	public String getVerifyCodeResultUrl() {
		return config.getWebServerUrl() + "vc/result.php";
	}

	public String getDownloadOrderUrl() {
		return config.getWebServerUrl() + "order/buy.php";
	}

	public String getOrdeFinishUrl() {
		return config.getWebServerUrl() + "order/finish.php";
	}

	public String getIdentifyUrl() {
		return config.getWebServerUrl() + "vc/identify.php";
	}

	public String getIsNewUrl() {
		return config.getWebServerUrl() + "vc/isnew.php";
	}
}

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
	private String service;
	public TransferUrl(String service)
	{
		this.service=service;
	}

	public String getUploadUrl() {
		return this.service+ "vc/upload.php";
	}

	public String getFileParamName() {
		return "file";
	}

	public String getVerifyCodeResultUrl() {
		return this.service+ "vc/result.php";
	}

	public String getDownloadOrderUrl() {
		return this.service + "order/buy.php";
	}

	public String getOrdeFinishUrl() {
		return this.service+ "order/finish.php";
	}

	public String getIdentifyUrl() {
		return this.service+ "vc/identify.php";
	}

	public String getIsNewUrl() {
		return this.service+ "vc/isnew.php";
	}
}

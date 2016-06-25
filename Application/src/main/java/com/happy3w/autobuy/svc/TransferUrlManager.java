/**
 * 
 */
package com.happy3w.autobuy.svc;

/**
 *中转页面请求地址管理。
 * @version 2016年6月25日 下午3:28:12
 * @author Happy3W Cherry
 *
 */
public class TransferUrlManager {
	private static TransferUrlManager context = new TransferUrlManager();
	public static TransferUrlManager getInstance(){
		return context;
	}
	public  String getUploadUrl(String url){
		return url+"upload.php";
	}
	public  String getFileParamName(){
		return "file";
	}
	public static String getVerifyCodeResultUrl(String service)
	{
		return service+"read.php";
	}
}	

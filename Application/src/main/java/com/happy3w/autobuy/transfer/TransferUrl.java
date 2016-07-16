/**
 * 
 */
package com.happy3w.autobuy.transfer;

/**
 *中转页面请求地址管理。
 * @version 2016年6月25日 下午3:28:12
 * @author Happy3W Cherry
 *
 */
public class TransferUrl {
	private static TransferUrl context = new TransferUrl();
	public static TransferUrl getInstance(){
		return context;
	}
	public  String getUploadUrl(String service){
		return service+"vc/upload.php";
	}
	public  String getFileParamName(){
		return "file";
	}
	public static String getVerifyCodeResultUrl(String service)
	{
		return service+"vc/read.php";
	}
	public static String getOrderQryUrl(String service)
	{
		return service+"order/query.php";
	}
	public static String getOrdeFinishUrl(String service) {
		return service+"order/finish.php";
	}
}

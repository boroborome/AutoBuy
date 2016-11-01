/**
 * 
 */
package com.happy3w.autobuy.action.strc;

/**
 * @version 2016年10月25日上午10:02:57
 * @author happy3w
 */
public class VerifyStruct extends ActStruct {
	private String upload;
	private String download;
	private String returnName;
	public String getUpload() {
		return upload;
	}
	public void setUpload(String upload) {
		this.upload = upload;
	}
	public String getDownload() {
		return download;
	}
	public void setDownload(String download) {
		this.download = download;
	}
	public String getReturnName() {
		return returnName;
	}
	public void setReturnName(String returnName) {
		this.returnName = returnName;
	}
	public VerifyStruct(String operation,String className,String paramName,String condition,String target,String upload,String download,String returnName)
	{
		super(operation, className, target, paramName, returnName, condition);
		this.upload=upload;
		this.download=download;
		this.returnName=returnName;
	}
}

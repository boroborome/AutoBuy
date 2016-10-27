/**
 * 
 */
package com.happy3w.autobuy.action;

import org.openqa.selenium.WebDriver;

import com.happy3w.autobuy.model.ActStruct;
import com.happy3w.autobuy.util.WebDriverUtil;

/**
 * 打开网页。
 * @version 2016年9月9日 下午1:13:45
 * @author Happy3W Cherry
 *
 */
public class OpenPage extends BaseAction {

	private String url;
	private String title;
	private long timeout;

	/**
	 * 打开网址，构造器。
	 * @param url 打开网址。
	 * @param title 页面标题，用于验证是否页面已经打开，为空则不验证。
	 * @param timeout 等待时长。
	 */
	public OpenPage(String url, String title, long timeout) {
		this.url = url;
		this.title = title;
		this.timeout = timeout;
	}
	public OpenPage()
	{
		super();
	}
	@Override
	public void setStruct(ActStruct struct)
	{
		super.setStruct(struct);
		this.url=struct.getTarget();
		this.title=struct.getFilter();
	}
	@Override
	public Result[] handle(WebDriver driver, Param param) {
		driver.get(this.url);
		return null;
	}
}

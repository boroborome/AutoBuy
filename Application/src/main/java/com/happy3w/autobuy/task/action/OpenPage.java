/**
 * 
 */
package com.happy3w.autobuy.task.action;

import org.openqa.selenium.WebDriver;

import com.happy3w.autobuy.util.WebDriverUtil;

/**
 * 打开网页。
 * @version 2016年9月9日 下午1:13:45
 * @author Happy3W Cherry
 *
 */
public class OpenPage implements IAction {

	private long timeout;
	private String url;
	private String title;

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

	@Override
	public Result[] handle(WebDriver driver, Param param) {
		WebDriverUtil.get(driver, url, title, timeout);
		return null;
	}
}

/**
 * 
 */
package com.happy3w.autobuy.exe.events;

import org.openqa.selenium.WebDriver;

import com.happy3w.autobuy.util.WebDriverUtil;

/**
 *
 * @version 2016年9月9日 下午1:13:45
 * @author Happy3W Cherry
 *
 */
public class Event_OpenPage implements IEvent {

	private long timeout;
	private String url;
	private String title;

	public Event_OpenPage(String url, String title, long timeout) {
		this.url = url;
		this.title = title;
		this.timeout = timeout;
	}

	@Override
	public EventResult[] handle(WebDriver driver, EventParam param) {
		WebDriverUtil.get(driver, url, title, timeout);
		return null;
	}
}

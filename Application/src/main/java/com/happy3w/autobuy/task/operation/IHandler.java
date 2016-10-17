/**
 * 
 */
package com.happy3w.autobuy.exe.events;

import org.openqa.selenium.WebDriver;

/**
 *
 * @version 2016年9月9日 下午1:23:28
 * @author Happy3W Cherry
 *
 */
public interface IEvent {
	public EventResult[] handle(WebDriver driver, EventParam param);
}

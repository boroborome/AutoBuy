/**
 * 
 */
package com.happy3w.autobuy.action;

import org.openqa.selenium.WebDriver;

/**
 * 操作/活动/事件处理。
 * @version 2016年9月9日 下午1:23:28
 * @author Happy3W Cherry
 *
 */
public interface IAction {
	/**
	 * 处理事务。
	 * @param driver 操作驱动。
	 * @param param  操作参数。
	 * @return
	 */
	public Result[] handle(WebDriver driver, Param param);
}

/**
 * 
 */
package com.happy3w.autobuy.model;

import com.happy3w.autobuy.config.SysConfig;

/**
 * 处理指令。
 * @version 2016年7月14日 下午8:06:58
 * @author Happy3W Cherry
 *
 */
public class Order {
	/**
	 * 指令执行站点。
	 */
	private SysConfig site;
	/**
	 * 指令内容。
	 */
	private UserOrder content;

	public SysConfig getSite() {
		return site;
	}
	public void setSite(SysConfig site) {
		this.site = site;
	}
	public UserOrder getContent() {
		return content;
	}
	public void setContent(UserOrder content) {
		this.content = content;
	}
	
}

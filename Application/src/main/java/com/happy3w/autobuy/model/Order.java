/**
 * 
 */
package com.happy3w.autobuy.model;

import com.happy3w.autobuy.util.config.DataConfig;

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
	private DataConfig site;
	/**
	 * 指令内容。
	 */
	private PurchaseOrder content;

	public DataConfig getSite() {
		return site;
	}
	public void setSite(DataConfig site) {
		this.site = site;
	}
	public PurchaseOrder getContent() {
		return content;
	}
	public void setContent(PurchaseOrder content) {
		this.content = content;
	}
	
}

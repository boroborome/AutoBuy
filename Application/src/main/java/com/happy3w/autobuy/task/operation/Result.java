/**
 * 
 */
package com.happy3w.autobuy.exe.events;

/**
 * 事件操作返回结果。
 * 
 * @version 2016年9月13日 上午8:02:25
 * @author Happy3W Cherry
 *
 */
public class EventResult {
	private String name;
	private String result;

	public EventResult(String name, String result) {
		this.name = name;
		this.result = result;
	}

	public String getName() {
		return this.name;
	}

	public String getResult() {
		return this.result;
	}
}

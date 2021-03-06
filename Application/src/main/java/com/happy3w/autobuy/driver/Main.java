/**
 * 
 */
package com.happy3w.autobuy.driver;

import com.happy3w.autobuy.model.TaskClock;

/**
 * 执行入口。
 * 
 * @version 2016年10月27日上午9:17:30
 * @author happy3w
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// DownloadRunner runner =new
		// DownloadRunner(Context.getInstance().getTransfer(),Context.getInstance().getListeners());
		// Context.getInstance().getPool().schedule(runner,0,10*1000,
		// Context.getInstance().getThrdTimeUnit());
		for (TaskClock task : Context.getInstance().getTaskSchedulor().getTasks()) {
			Context.getInstance().getTriggerSchedulor().handle(task);
		}
		System.out.println("===end====");
	}
}

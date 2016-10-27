/**
 * 
 */
package com.happy3w.autobuy.driver;

import java.util.Timer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.happy3w.autobuy.model.UserOrder;

/**
 * 自动执行任务调度员。
 * 
 * @version 2016年7月21日 下午3:16:26
 * @author Happy3W Cherry
 *
 */
@Component
public class ExeSchedulor implements IDownloadListener {
	/**
	 * 任务定时器。
	 */
	private Timer timer = new Timer();
	public ExeSchedulor() {
	}

	@Override
	public void handle(UserOrder[] orders) {
		for (UserOrder order : orders) {
			timer.schedule(new ExeTask(order), order.getBuytime());
		}
	}
}

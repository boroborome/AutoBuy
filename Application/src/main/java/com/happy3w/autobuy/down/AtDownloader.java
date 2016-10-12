/**
 * 
 */
package com.happy3w.autobuy.down;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.happy3w.autobuy.svc.IOrderProvider;

/**
 * YY site Order Provider.
 * <p>
 * Provider can produce orders from transfer server.
 * </p>
 * 
 * @version 2016年7月21日 下午3:56:36
 * @author Happy3W Cherry
 *
 */
@Component
public class AtDownloader implements IOrderProvider {
	/**
	 * if YYOrderProvider is started, then started is true,else is false.
	 */
	private boolean started;
	/**
	 * the Provider's timer,which can produce the orders.
	 */
	private Timer timer = new Timer();
	/**
	 * time in milliseconds between successive task executions.
	 */
	private long period;
	/**
	 * First time at which task is to be executed.
	 */
	private Date firstTime;
	/**
	 * when timer schedule,the timer thread will execute this task.
	 */
	private TimerTask task;

	@Autowired
	public AtDownloader(TimerTask task, Date firsttime, long period) {
		this.task = task;
		this.firstTime = firsttime;
		this.period = period;
	}

	public long getPeriod() {
		return period;
	}

	public void setPeriod(long period) {
		this.period = period;
	}

	public Date getFirstTime() {
		return firstTime;
	}

	public TimerTask getTask() {
		return task;
	}

	@Override
	public boolean start() {
		timer.scheduleAtFixedRate(task, firstTime, period);
		started = true;
		return started;
	}

	@Override
	public boolean isStarted() {
		return started;
	}

}

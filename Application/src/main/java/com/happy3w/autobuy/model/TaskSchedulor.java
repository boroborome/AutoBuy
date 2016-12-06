/**
 * 
 */
package com.happy3w.autobuy.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @version 2016年11月1日下午1:36:59
 * @author happy3w
 */
public class TaskSchedulor {
	private List<TaskClock> tasks = new ArrayList<TaskClock>();

	public TaskSchedulor() {
		Calendar cl = Calendar.getInstance();
		cl.set(2016, 12, 6, 18, 24, 00);
		Date start = cl.getTime();
		cl.set(2016, 12, 6, 18, 30, 00);
		Date end = cl.getTime();
		tasks.add(new TaskClock("ycode", start, end, 1, TimeUnit.DAYS));
		tasks.add(new TaskClock("buy", start, end, 1, TimeUnit.DAYS));
	}

	public TaskClock[] getTasks() {
		return tasks.toArray(new TaskClock[0]);
	}
}

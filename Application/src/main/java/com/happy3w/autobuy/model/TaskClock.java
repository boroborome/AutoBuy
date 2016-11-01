/**
 * 
 */
package com.happy3w.autobuy.model;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @version 2016年11月1日下午1:38:33
 * @author happy3w
 */
public class TaskClock {
	private String taskid;
	private Date start;
	private Date end;
	private long period;
	private TimeUnit unit;
	public String getTaskid() {
		return taskid;
	}
	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
	public long getPeriod() {
		return period;
	}
	public void setPeriod(long period) {
		this.period = period;
	}
	public TimeUnit getUnit() {
		return unit;
	}
	public void setUnit(TimeUnit unit) {
		this.unit = unit;
	}
	public TaskClock(String taskid,Date start,Date end,long period,TimeUnit unit)
	{
		this.taskid=taskid;
		this.start=start;
		this.end=end;
		this.period=period;
		this.unit=unit;
	}
}

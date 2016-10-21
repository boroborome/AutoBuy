/**
 * 
 */
package com.happy3w.autobuy.task.yy;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;

import com.happy3w.autobuy.config.SysConfig;
import com.happy3w.autobuy.task.action.IAction;
import com.happy3w.autobuy.task.action.Param;
import com.happy3w.autobuy.task.action.Result;

/**
 * 执行任务。
 * @version 2016年10月19日上午10:56:07
 * @author happy3w
 */
public class TaskHandler {
	private List<IAction> events =new ArrayList<IAction>();
	private SysConfig config;
	
	public List<IAction> getEvents() {
		return events;
	}
	public void setEvents(List<IAction> events) {
		this.events = events;
	}
	public SysConfig getConfig() {
		return config;
	}
	public void setConfig(SysConfig config) {
		this.config = config;
	}
	public TaskHandler(SysConfig config)
	{
		this.config=config;
	}
	public void handle(WebDriver driver,Param arg)
	{
		for(IAction handler:events)
		{
			Result[] results=handler.handle(driver, arg);
			if(null!=results&&results.length>0)
			{
				arg.put(results);
			}
			try {
				Thread.sleep(config.getStepSpan());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

/**
 * 
 */
package com.happy3w.autobuy.yy.flow;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;

import com.happy3w.autobuy.config.SysConfig;
import com.happy3w.autobuy.task.action.Param;
import com.happy3w.autobuy.task.base.TaskHandler;
import com.happy3w.autobuy.yy.task.GetYCode;
import com.happy3w.autobuy.yy.task.Login;

/**
 * @version 2016年10月24日上午10:24:04
 * @author happy3w
 */
public class YCode {
	public List<TaskHandler> handlers  =new ArrayList<TaskHandler>();
	public YCode(SysConfig config)
	{
		handlers.add(Login.getInstance(config));
		handlers.add(new GetYCode(config));
	}
	public void handle(WebDriver driver,Param arg){
		for(TaskHandler handler:handlers)
		{
			handler.handle(driver, arg);
		}
	}
}

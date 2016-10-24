/**
 * 
 */
package com.happy3w.autobuy.yy.flow;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;

import com.happy3w.autobuy.config.SysConfig;
import com.happy3w.autobuy.model.AtUser;
import com.happy3w.autobuy.model.PurchaseOrder;
import com.happy3w.autobuy.task.action.Param;
import com.happy3w.autobuy.task.base.TaskHandler;
import com.happy3w.autobuy.yy.task.ChoseProduct;
import com.happy3w.autobuy.yy.task.Confirm;
import com.happy3w.autobuy.yy.task.Login;
import com.happy3w.autobuy.yy.task.Pay;

/**
 * 购买YY产品全流程。
 * @version 2016年10月24日上午8:58:58
 * @author happy3w
 */
public class Buy {
	public List<TaskHandler> handlers  =new ArrayList<TaskHandler>();
	public Buy(SysConfig config){
		handlers.add(Login.getInstance(config));
		handlers.add(new ChoseProduct(config));
		handlers.add(new Confirm(config));
		handlers.add(new Pay(config));
	}
	public void handle(WebDriver driver,Param param)
	{
		for(TaskHandler handler :handlers)
		{
			handler.handle(driver, param);
		}
	}
}

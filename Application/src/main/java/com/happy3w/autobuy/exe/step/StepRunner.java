/**
 * 
 */
package com.happy3w.autobuy.exe.step;

import org.openqa.selenium.WebDriver;

import com.happy3w.autobuy.model.AtUser;
import com.happy3w.autobuy.model.PurchaseOrder;
import com.happy3w.autobuy.task.action.HandlerRegister;
import com.happy3w.autobuy.task.action.IAction;
import com.happy3w.autobuy.task.action.Param;
import com.happy3w.autobuy.task.action.Result;

/**
 *
 * @version 2016年9月9日 下午3:51:28
 * @author Happy3W Cherry
 *
 */
public class StepRunner implements IStepRunner {

	private WebDriver driver;
	private AtUser user;
	private HandlerRegister eventRegister;
	private StepRegister stepRegister;

	public StepRunner(WebDriver driver, AtUser user, StepRegister stepRegister, HandlerRegister eventRegister) {
		this.driver = driver;
		this.user = user;
		this.stepRegister = stepRegister;
		this.eventRegister = eventRegister;
	}

	@Override
	public void process(PurchaseOrder order) {
		String[] steps = stepRegister.getSteps(order.getProduct());
		for (String step : steps) {
			IAction[] events = eventRegister.getEvents(step);
			Param param = new Param(order, user);
			for (IAction event : events) {
				Result[] results = event.handle(driver, param);
				param.put(results);
			}
		}
	}

}

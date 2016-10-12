/**
 * 
 */
package com.happy3w.autobuy.exe.step;

import org.openqa.selenium.WebDriver;

import com.happy3w.autobuy.exe.events.EventParam;
import com.happy3w.autobuy.exe.events.EventRegister;
import com.happy3w.autobuy.exe.events.EventResult;
import com.happy3w.autobuy.exe.events.IEvent;
import com.happy3w.autobuy.model.AtUser;
import com.happy3w.autobuy.model.PurchaseOrder;

/**
 *
 * @version 2016年9月9日 下午3:51:28
 * @author Happy3W Cherry
 *
 */
public class StepRunner implements IStepRunner {

	private WebDriver driver;
	private AtUser user;
	private EventRegister eventRegister;
	private StepRegister stepRegister;

	public StepRunner(WebDriver driver, AtUser user, StepRegister stepRegister, EventRegister eventRegister) {
		this.driver = driver;
		this.user = user;
		this.stepRegister = stepRegister;
		this.eventRegister = eventRegister;
	}

	@Override
	public void process(PurchaseOrder order) {
		String[] steps = stepRegister.getSteps(order.getProduct());
		for (String step : steps) {
			IEvent[] events = eventRegister.getEvents(step);
			EventParam param = new EventParam(order, user);
			for (IEvent event : events) {
				EventResult[] results = event.handle(driver, param);
				param.put(results);
			}
		}
	}

}

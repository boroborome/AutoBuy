/**
 * 
 */
package com.happy3w.autobuy.exe.step;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;

import com.happy3w.autobuy.exe.events.EventRegister;
import com.happy3w.autobuy.model.PurchaseOrder;

/**
 * 单步执行器维护类。
 *
 * @version 2016年9月8日 下午8:51:35
 * @author Happy3W Cherry
 *
 */
public class StepManager {

	private WebDriver driver;
	private UserRegister userRegister;
	private StepRegister stepRegister;
	private EventRegister eventRegister;

	public StepManager(WebDriver driver, UserRegister userRegister, StepRegister stepRegister,
			EventRegister eventRegister) {
		this.driver = driver;
		this.userRegister = userRegister;
		this.stepRegister = stepRegister;
		this.eventRegister = eventRegister;
	}

	/**
	 * 根据订单获取订单执行器。
	 * 
	 * @param order
	 * @return
	 */
	public IStepRunner[] getSteps(PurchaseOrder order) {
		String[] steps = StepRegister.getInstance().getSteps(order.getProduct());
		List<IStepRunner> runners = new ArrayList<IStepRunner>();
		for (String step : steps) {
			runners.add(new StepRunner(driver, userRegister.getUser(order.getProduct()), stepRegister, eventRegister));
		}
		return runners.toArray(new StepRunner[0]);
	}

}

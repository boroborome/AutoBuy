/**
 * 
 */
package com.happy3w.autobuy.exe;

import com.happy3w.autobuy.exe.step.IStepRunner;
import com.happy3w.autobuy.exe.step.StepManager;
import com.happy3w.autobuy.model.PurchaseOrder;

/**
 * 单独立线程执行订。
 * 
 * @version 2016年9月9日 上午9:59:19
 * @author Happy3W Cherry
 *
 */
public class AtExeThrdRunner implements Runnable {

	private PurchaseOrder order;
	private StepManager stepManager;

	public AtExeThrdRunner(PurchaseOrder order, StepManager stepManager) {
		this.order = order;
		this.stepManager = stepManager;
	}

	@Override
	public void run() {
		IStepRunner[] steps = stepManager.getSteps(order);
		for (IStepRunner step : steps) {
			step.process(order);
		}
	}

}

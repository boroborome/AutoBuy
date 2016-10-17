/**
 * 
 */
package com.happy3w.autobuy.exe.step;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.happy3w.autobuy.task.operation.HandlerRegister;

/**
 *
 * @version 2016年9月9日 上午11:01:03
 * @author Happy3W Cherry
 *
 */
public class StepRegister {
	private static StepRegister reg = new StepRegister();

	public static StepRegister getInstance() {
		return reg;
	}

	private Map<String, List<IStepRunner>> steps = new HashMap<String, List<IStepRunner>>();
	private Map<String, List<String>> mapStep = new HashMap<String, List<String>>();
	private HandlerRegister eventReg;

	public StepRegister() {
		List<String> events = new ArrayList<String>();
		mapStep.put(Products.YY_A, events);
		events.add(Steps.YY_LOGIN);
	}

	public String[] getSteps(String product) {
		return mapStep.get(product).toArray(new String[0]);
	}
}

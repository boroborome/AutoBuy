/**
 * 
 */
package com.happy3w.autobuy.driver;

import com.happy3w.autobuy.action.ActionExe;
import com.happy3w.autobuy.action.Param;
import com.happy3w.autobuy.model.ActStruct;
import com.happy3w.autobuy.model.Stage;
import com.happy3w.autobuy.model.TaskCache;
import com.happy3w.autobuy.model.UserOrder;

/**
 * 单独立线程执行。
 * 
 * @version 2016年9月9日 上午9:59:19
 * @author Happy3W Cherry
 *
 */
public class ExeRunner implements Runnable {

	private UserOrder order;

	public ExeRunner(UserOrder order) {
		this.order = order;
	}

	@Override
	public void run() {
		Param param  =new Param();
		param.put(order);
		ActionExe exe  =new ActionExe();
		for(Stage stage : TaskCache.getInstance().get(order.getTask()).getStages())
		{
			param.put(stage.getUser());
			for(ActStruct act:stage.getActions())
			{
				param.put(exe.handle(Context.getInstance().getDriver(), param, act));
			}
		}
	}

}

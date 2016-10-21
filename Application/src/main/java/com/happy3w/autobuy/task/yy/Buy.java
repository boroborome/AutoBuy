package com.happy3w.autobuy.task.yy;

import com.happy3w.autobuy.config.SysConfig;
import com.happy3w.autobuy.model.PurchaseOrder;
import com.happy3w.autobuy.task.action.ClickElement;
import com.happy3w.autobuy.task.action.GetCell;
import com.happy3w.autobuy.task.action.Input;

/**
 * 下单购买。
 * @version 2016年10月21日下午4:00:57
 * @author happy3w
 */
public class Buy extends TaskHandler{

	public Buy(SysConfig config) {
		super(config);
		this.getEvents().add(new Input("//*[@id=\"gmMoney\"]",PurchaseOrder.AMOUNT));
		this.getEvents().add(new Input("//*[@id=\"yCode\"]",GetCell.RETURNNAME));
		this.getEvents().add(new ClickElement("//*[@id=\"otButton\"]"));
	}

}

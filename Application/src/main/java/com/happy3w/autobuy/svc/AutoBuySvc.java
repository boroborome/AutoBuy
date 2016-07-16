package com.happy3w.autobuy.svc;

import com.happy3w.autobuy.model.Order;
import com.happy3w.autobuy.model.PurchaseOrder;
import com.happy3w.autobuy.schedule.Context;
import com.happy3w.autobuy.transfer.OrderManager;
import com.happy3w.autobuy.util.config.DataConfig;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by ysgao on 5/15/16.
 */
@Component
public class AutoBuySvc {
	private final Logger logger = LoggerFactory.getLogger(AutoBuySvc.class);
	@Autowired
    private DataConfig dataConfig;

	public void start() {
		Context.getInstance().init(dataConfig);
		Context.getInstance().getTransfer().schedule();
	}
}

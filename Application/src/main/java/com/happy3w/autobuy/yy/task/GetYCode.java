/**
 * 
 */
package com.happy3w.autobuy.yy.task;

import com.happy3w.autobuy.config.SysConfig;
import com.happy3w.autobuy.task.action.OpenPage;
import com.happy3w.autobuy.task.base.TaskHandler;
import com.happy3w.autobuy.yy.task.action.FilterYCode;

/**
 * 获取ycode验证码。
 * @version 2016年10月21日上午11:41:07
 * @author happy3w
 */
public class GetYCode extends TaskHandler {

	public GetYCode(SysConfig config) {
		super(config);
		this.getEvents().add(new OpenPage("https://www.yyfax.com/user/spread/wdycode.html","我的YCode-友金所",config.getTimeout()));
		this.getEvents().add(FilterYCode.getInstance());
	}
}

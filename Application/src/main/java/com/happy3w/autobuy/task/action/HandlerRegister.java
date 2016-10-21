/**
 * 
 */
package com.happy3w.autobuy.task.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.happy3w.autobuy.config.SysConfig;
import com.happy3w.autobuy.exe.step.Steps;
import com.happy3w.autobuy.model.AtUser;
import com.happy3w.autobuy.task.yy.action.YYVerifyImg;
import com.happy3w.autobuy.transfer.TransferUrl;
import com.happy3w.autobuy.util.HttpUtil;

/**
 * 注册事件。
 * 
 * @version 2016年9月9日 下午1:45:12
 * @author Happy3W Cherry
 *
 */
public class HandlerRegister {
	private Map<String, List<IAction>> mapEvent = new HashMap<String, List<IAction>>();
	private long timeout = 500;
	private HttpUtil http;
	private TransferUrl transfer;
	private SysConfig config;

	@Autowired
	public HandlerRegister(HttpUtil http, TransferUrl transfer, SysConfig config) {
		this.http = http;
		this.transfer = transfer;
		this.config = config;
		init();
	}

	private void init() {
		List<IAction> events = new ArrayList<IAction>();
		mapEvent.put(Steps.YY_LOGIN, events);
		events.add(new OpenPage("https://www.yyfax.com/user/login.html", "我的友金所", config.getTimeout()));
		events.add(new Input("//*[@id=\"accountName\"]", AtUser.USERID));
		events.add(new Input("//*[@id=\"password1\"]", AtUser.PASSWORD));
		events.add(new YYVerifyImg(config,http,config.getWebServerUrl()));
		events.add(new Input("//*[@id=\"verifyCode\"]", Param.VERIFYCODE));
	}

	public IAction[] getEvents(String action) {
		return mapEvent.get(action).toArray(new IAction[0]);
	}

}

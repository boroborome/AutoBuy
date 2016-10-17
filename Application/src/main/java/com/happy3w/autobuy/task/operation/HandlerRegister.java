/**
 * 
 */
package com.happy3w.autobuy.exe.events;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.happy3w.autobuy.config.SysConfig;
import com.happy3w.autobuy.exe.step.Steps;
import com.happy3w.autobuy.model.AtUser;
import com.happy3w.autobuy.transfer.TransferUrl;
import com.happy3w.autobuy.util.HttpUtil;

/**
 * 注册事件。
 * 
 * @version 2016年9月9日 下午1:45:12
 * @author Happy3W Cherry
 *
 */
public class EventRegister {
	private Map<String, List<IEvent>> mapEvent = new HashMap<String, List<IEvent>>();
	private long timeout = 500;
	private HttpUtil http;
	private TransferUrl transfer;
	private SysConfig config;

	@Autowired
	public EventRegister(HttpUtil http, TransferUrl transfer, SysConfig config) {
		this.http = http;
		this.transfer = transfer;
		this.config = config;
		init();
	}

	private void init() {
		List<IEvent> events = new ArrayList<IEvent>();
		mapEvent.put(Steps.YY_LOGIN, events);
		events.add(new Event_OpenPage("https://www.yyfax.com/user/login.html", "我的友金所", config.getTimeout()));
		events.add(new Event_InputVal("//*[@id=\"accountName\"]", AtUser.USERID));
		events.add(new Event_InputVal("//*[@id=\"password1\"]", AtUser.PASSWORD));
		events.add(new Event_ReadImg(config, transfer, http, "//*[@id=\"_verifyImg\"]", EventParam.VERIFYCODE));
		events.add(new Event_InputVal("//*[@id=\"verifyCode\"]", EventParam.VERIFYCODE));
	}

	public IEvent[] getEvents(String action) {
		return mapEvent.get(action).toArray(new IEvent[0]);
	}

}

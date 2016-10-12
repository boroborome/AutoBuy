package com.happy3w.autobuy.svc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.happy3w.autobuy.config.SysConfig;

/**
 * 自动购买服务。
 * 
 * @version 2016年9月8日 上午10:44:55
 * @author Happy3W Cherry
 *
 */
@Service
public class AutoBuySvc {
	private final Logger logger = LoggerFactory.getLogger(AutoBuySvc.class);
	private IOrderProvider provider;
	private IOrderConsumer executor;
	private SysConfig config;
	private boolean started;

	@Autowired
	public AutoBuySvc(IOrderProvider provider, IOrderConsumer executor, SysConfig config) {
		this.provider = provider;
		this.executor = executor;
		this.config = config;
	}

	public void start() {
		provider.start();
		executor.start();
		started = provider.isStarted() && executor.isStarted();

	}

	public boolean isStarted() {
		return started;
	}
}

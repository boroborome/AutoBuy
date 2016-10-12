/**
 * 
 */
package com.happy3w.autobuy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * 数据库配置。
 * 
 * @version 2016年9月3日 上午7:35:27
 * @author Happy3W Cherry
 *
 */
@Component
public class DBConfig {
	private Environment env;

	@Autowired
	public DBConfig(Environment env) {
		this.env = env;
	}

	public String getDbUrl() {
		return env.getProperty("db.url");
	}

	public String getDbUser() {
		return env.getProperty("db.username");
	}

	public String getDbPwd() {
		return env.getProperty("db.password");
	}

	public String getDbDriver() {
		return env.getProperty("db.driverClassName");
	}
}

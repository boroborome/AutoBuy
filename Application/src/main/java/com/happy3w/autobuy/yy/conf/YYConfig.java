/**
 * 
 */
package com.happy3w.autobuy.yy.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

/**
 * 配置文件。
 * 
 * @version 2016年9月1日 下午3:55:55
 * @author Happy3W Cherry
 *
 */
public class YYConfig {
	@Autowired
	Environment env;

	/**
	 * 设置系统环境信息，用于读取properties.
	 * 
	 * @param env
	 */
	public void setEnv(Environment env) {
		this.env = env;
	}

	public String getYyfaxAccount() {
		return env.getProperty("yyfax.account");
	}

	public String getYyfaxPassword() {
		return env.getProperty("yyfax.password");
	}

	public String getYcodeAccount() {
		return env.getProperty("ycode.account");
	}

	public String getYcodePassword() {
		return env.getProperty("ycode.password");
	}

	/**
	 * ycode是优惠码。需要登录获取。
	 */
	private String ycode;

	public String getYcode() {
		return ycode;
	}

	public void setYcode(String ycode) {
		this.ycode = ycode;
	}
}

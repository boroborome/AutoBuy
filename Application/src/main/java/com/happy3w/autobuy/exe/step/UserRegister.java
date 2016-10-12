/**
 * 
 */
package com.happy3w.autobuy.exe.step;

import java.util.HashMap;
import java.util.Map;

import com.happy3w.autobuy.model.AtUser;

/**
 *
 * @version 2016年9月9日 下午4:13:08
 * @author Happy3W Cherry
 *
 */
public class UserRegister {
	private static UserRegister instance = new UserRegister();

	public static UserRegister getInstance() {
		return instance;
	}

	private Map<String, AtUser> map = new HashMap<String, AtUser>();

	public UserRegister() {
		map.put(Products.YY_A, new AtUser("chjj402@sina.com", "yy2900"));
	}

	public AtUser getUser(String product) {
		return map.get(product);
	}
}

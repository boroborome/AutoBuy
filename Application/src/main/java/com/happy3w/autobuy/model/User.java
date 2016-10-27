/**
 * 
 */
package com.happy3w.autobuy.model;

/**
 *
 * @version 2016年9月9日 下午3:12:17
 * @author Happy3W Cherry
 *
 */
public class User {
	public static String USERID = "userid";
	public static String PASSWORD = "password";
	private String userId;
	private String password;

	public User(String userId, String password) {
		this.userId = userId;
		this.password = password;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}

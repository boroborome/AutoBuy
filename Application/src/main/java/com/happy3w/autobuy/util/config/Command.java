/**
 * 
 */
package com.happy3w.autobuy.util.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.env.StandardEnvironment;

/**
 *获取购买指令。
 * @version 2016年6月26日 下午9:38:10
 * @author Happy3W Cherry
 *
 */
@Configuration
@Profile("dev")
@PropertySource("classpath:/config/command.properties")
public class Command {
	private  Properties env;
	private String ycode;
	public Command()
	{
		env=new Properties();
		try {
			env.load(this.getClass().getResourceAsStream("/config/command.properties"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public  String getProduct() {
		return env.getProperty("product");
	}
	public   double getAmout() {
		return Double.valueOf(env.getProperty("amount"));
	}
	public String getYcodeUser()
	{
		return env.getProperty("ycode.user");
	}
	public String getYcodePsw(){
		return env.getProperty("ycode.psw");
	}
	public String getYcode() {
		return ycode;
	}
	public void setYcode(String ycode) {
		this.ycode = ycode;
	}
	
}

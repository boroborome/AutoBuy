package com.happy3w.autobuy.config;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Profile("dev")
@PropertySource("classpath:/config/config_dev.properties")
public class AppConfigDev {
	/**
	 * 系统执行任务开始时间。
	 */
	@Bean
	public Date getFirstTime() {
		SimpleDateFormat date = new SimpleDateFormat("yyyyMMdd HH:mm:ss,SSS");
		try {
			return date.parse("20160101 00:00:00,000");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Date();

	}

	/**
	 * 任务处理周期。
	 * 
	 */
	@Bean
	public long getPeriod() {
		return 1000;
	}
}

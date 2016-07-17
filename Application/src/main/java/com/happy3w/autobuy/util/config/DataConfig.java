package com.happy3w.autobuy.util.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

public class DataConfig {
    @Autowired
    Environment env;
	public void setEnv(Environment env) {
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
        return    env.getProperty("db.driverClassName");
    }

    public String getAuthUrl() {
        return    env.getProperty("auth.url");
    }

    public String getYyfaxAccount() { return env.getProperty("yyfax.account");}

    public String getYyfaxPassword() { return env.getProperty("yyfax.password");}
    
    public String getYcodeAccount(){
    	return env.getProperty("ycode.account");
    }
    public String getYcodePassword(){
    	return env.getProperty("ycode.password");
    }

    public String getWebServerUrl() { return env.getProperty("webServer.url");}
    
    public int getOrderSize(){
    	return Integer.valueOf(env.getProperty("order.size"));
    }
    public int getPageSize() {
        String pageSize = env.getProperty("PreselectSettings.pageSize");
        if (pageSize == null || pageSize.isEmpty()) {
            return 50;
        }
        return Integer.parseInt(pageSize);
    }
    /**
     * ycode是优惠吗。需要登录获取。
     */
    private String ycode;
    
    public String getYcode() {
		return ycode;
	}

	public void setYcode(String ycode) {
		this.ycode = ycode;
	}
	private ThreadConfig thread = new ThreadConfig();
    public ThreadConfig getThread()
    {
    	return thread;
    }
    public class ThreadConfig{
        public int getCoreSize(){
        	return Integer.valueOf(env.getProperty("thread.coresize")).intValue();
        }
        public int getMaxSize()
        {
        	return Integer.valueOf(env.getProperty("thread.maxsize")).intValue();
        }
        public long getKeepTime()
        {
        	return Long.valueOf(env.getProperty("thread.keeptime")).longValue();
        }
    }
}

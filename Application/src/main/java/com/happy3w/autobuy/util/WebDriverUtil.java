/**
 * 
 */
package com.happy3w.autobuy.util;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 *获取webDriver驱动。
 * @version 2016年7月3日 下午4:41:31
 * @author Happy3W Cherry
 *
 */
public class WebDriverUtil {
	public static WebDriver getWebDriver() {
		WebDriver webDriver = null;
		if (webDriver == null) {
			String chromeDriver = System.getProperty("webdriver.chrome.driver");
			if (chromeDriver == null || chromeDriver.isEmpty()) {
				chromeDriver = (System.getProperties().get("os.name").toString().toLowerCase().contains("win")
						? "chromedriver.exe" : "chromedriver");
				File fileDriver = new File(chromeDriver);
				chromeDriver = fileDriver.getAbsolutePath();
				System.setProperty("webdriver.chrome.driver", chromeDriver);
			}

			webDriver = new ChromeDriver();
			// WebDriver wd = new FirefoxDriver();
		}
		return webDriver;
	}
	/**
	 * 打开url页面。
	 * @param url url
	 * @param title 页面标题。如果不为null，则可以验证页面是否已经打开，如已经打开则返回，不用等待timeout,否则一直等到timeout。
	 * @param 页面打开等待最长毫秒数。
	 */
	public static void get(WebDriver wd,String url,String title,long timeout)
	{
		wd.get(url);
		if(null==title||title.length()<1)
		{
			ThreadUtil.sleep(timeout);
			return;
		}
		long size =100;
		for(int i=0;i<timeout/size;i++)
		{
			String curTitle = wd.getTitle();
			if(null!=curTitle&&!curTitle.contains(title))
			{
				break;
			}
			ThreadUtil.sleep(size);
		}
		
	}
}

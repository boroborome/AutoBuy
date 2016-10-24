/**
 * 
 */
package driver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * @version 2016年10月19日下午3:54:58
 * @author happy3w
 */
public class RemoteDriver {
	private static RemoteDriver instance;
	public static RemoteDriver getInstance()
	{
		if(null==instance)
		{
			instance=new RemoteDriver();
		}
		return instance;
	}
	private String urlStr="http://localhost:11991";
	public  WebDriver getDriver(long timeout)
	{
		WebDriver   driver = null;
		URL url = null;
		try {
			url = new URL(urlStr);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			driver = new RemoteWebDriver(url,
			         DesiredCapabilities.chrome());
			driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
			return driver;
	}

}

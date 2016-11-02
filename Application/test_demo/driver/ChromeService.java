/**
 * 
 */
package driver;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.chrome.ChromeDriverService;

/**
 * @version 2016年10月19日下午2:15:25
 * @author happy3w
 */
public class ChromeService {
	private ChromeDriverService service;
	private static ChromeService instance;
	public static ChromeService getInstance()
	{
		if(null==instance)
		{
			instance  =new ChromeService();
		}
		return instance;
	}
	public String getUrl()
	{
		return service.getUrl().toString();
	}
	public void start()
	{
		
		File file = new File("chromedriver.exe");
	    service = new ChromeDriverService.Builder()
	        .usingDriverExecutable(file)
	        .usingAnyFreePort()
	        .build();
	    try {
			service.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void stop()
	{
		service.stop();
	}
}

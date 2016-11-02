package driver;

import java.io.File;

import org.openqa.selenium.WebDriver;

import com.happy3w.autobuy.util.WebDriverUtil;

public class Console {

	public static void main(String[] args) {
		
		//ChromeService.getInstance().start();
		//System.out.println(ChromeService.getInstance().getUrl());
		WebDriver driver = WebDriverUtil.getWebDriver();
		driver.get("http://www.yyfax.com/");
		while(true)
		{
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

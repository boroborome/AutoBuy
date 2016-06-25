/**
 * 
 */
package com.happy3w.autobuy.svc;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.web.client.RestTemplate;

import com.happy3w.autobuy.util.HttpUtil;
import com.happy3w.autobuy.util.ThreadUtil;

/**
 * login to a http url
 * @version 2016年6月25日 下午12:06:54
 * @author Happy3W Chen
 *
 */
public class LoginManager {
	//访问web的驱动。
	private WebDriver webDriver;
	
	private String httpUrl;
	public WebDriver getWebDriver() {
		return webDriver;
	}


	public void setWebDriver(WebDriver webDriver) {
		this.webDriver = webDriver;
	}

	public String getHttpUrl() {
		return httpUrl;
	}


	public void setHttpUrl(String httpUrl) {
		this.httpUrl = httpUrl;
	}

	public boolean login(String account,String password)
	{	
		boolean result=false;
		webDriver.get("https://www.yyfax.com/user/login.html");
        ThreadUtil.sleep(2000);
        WebElement elementName = webDriver.findElement(By.xpath("//*[@id=\"accountName\"]"));
        elementName.sendKeys(account);
        WebElement elementPassword = webDriver.findElement(By.xpath("//*[@id=\"password1\"]"));
        elementPassword.sendKeys(password);
        WebElement elementVerifyCode = webDriver.findElement(By.xpath("//*[@id=\"verifyCode\"]"));
        VerifyCodeManager vc=new VerifyCodeManager();
        vc.setHttpUrl(this.httpUrl);
        elementVerifyCode.sendKeys(vc.getVerifyCode(webDriver));
        WebElement elementLoginButton = webDriver.findElement(By.xpath("//*[@id=\"login\"]"));
        elementLoginButton.click();
		return result;
	}

	 
}

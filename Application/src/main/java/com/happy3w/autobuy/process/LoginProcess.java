/**
 * 
 */
package com.happy3w.autobuy.process;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import com.happy3w.autobuy.util.HttpUtil;
import com.happy3w.autobuy.util.ThreadUtil;
import com.happy3w.autobuy.util.WebDriverUtil;

/**
 * login to a http url
 * @version 2016年6月25日 下午12:06:54
 * @author Happy3W Chen
 *
 */
public class LoginProcess {
	//访问web的驱动。
	private WebDriver webDriver;
	private String httpUrl;
	private VerifyCodeProcess vc;
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

	public VerifyCodeProcess getVc() {
		return vc;
	}


	public void setVc(VerifyCodeProcess vc) {
		this.vc = vc;
	}


	public boolean login(String account,String password)
	{	
		boolean result=false;
        WebDriverUtil.get(webDriver, "https://www.yyfax.com/user/login.html", "我的友金所", 5000);
        WebElement elementName = webDriver.findElement(By.xpath("//*[@id=\"accountName\"]"));
        elementName.sendKeys(account);
        WebElement elementPassword = webDriver.findElement(By.xpath("//*[@id=\"password1\"]"));
        elementPassword.sendKeys(password);
        WebElement elementVerifyCode = webDriver.findElement(By.xpath("//*[@id=\"verifyCode\"]"));
        vc.setHttpUrl(this.httpUrl);
        elementVerifyCode.sendKeys(vc.getVerifyCode(webDriver));
        WebElement elementLoginButton = webDriver.findElement(By.xpath("//*[@id=\"login\"]"));
        elementLoginButton.click();
        result=true;
		return result;
	}
	public boolean loginout()
	{
		WebDriverUtil.get(webDriver, "https://www.yyfax.com/user/logout.htm", null, 500);
		return true;
	}
	public void signature()
	{
		WebDriverUtil.get(webDriver, "https://www.yyfax.com/user/", "", 500);
		WebElement signature = webDriver.findElement(By.xpath("/html/body/div[3]/div[2]/div[4]/div/div[2]/a"));
		signature.click();
	}
	 
}

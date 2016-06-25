package com.happy3w.autobuy.svc;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
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
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import com.happy3w.autobuy.util.HttpUtil;
import com.happy3w.autobuy.util.LogUtil;

/**
 * Created by ysgao on 5/16/16.
 */
public class AutoBuyProcess implements Runnable {
    
	private String webServerUrl;
    private final String account;
    private final String password;

    public AutoBuyProcess(String account, String password, String webServerUrl) {
        this.account = account;
        this.password = password;
        this.webServerUrl = webServerUrl;
    }

    private ChromeDriver webDriver;

    @Override
    public void run() {
        WebDriver wd = getWebDriver();
        //登录。
        LoginManager login = new LoginManager();
        login.setWebDriver(wd);
        login.setHttpUrl(webServerUrl);
        login.login(account, password);
    }

    public WebDriver getWebDriver() {
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
            //        WebDriver wd = new FirefoxDriver();
        }
        return webDriver;
    }
}

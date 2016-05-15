package com.happy3w.autobuy.svc;

import com.happy3w.autobuy.util.config.DataConfig;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

/**
 * Created by ysgao on 5/15/16.
 */
@Component
public class AutoBuySvc {
    private final Logger logger = LoggerFactory.getLogger(AutoBuySvc.class);

    @Autowired
    private DataConfig dataConfig;

    private WebDriver webDriver;

    public WebDriver getWebDriver() {
        if (webDriver == null) {
            String chromeDriver = System.getProperty("webdriver.chrome.driver");
            if (chromeDriver == null || chromeDriver.isEmpty()) {
                chromeDriver = (System.getProperties().get("os.name").toString().toLowerCase().contains("win")
                    ? "chromedrive.exe" : "chromedriver");
                File fileDriver = new File(chromeDriver);
                chromeDriver = fileDriver.getAbsolutePath();
                System.setProperty("webdriver.chrome.driver", chromeDriver);
            }

            webDriver = new ChromeDriver();
            //        WebDriver wd = new FirefoxDriver();
        }
        return webDriver;
    }

    private void sleep(int second) {
        try {
            Thread.sleep(second * 1000);
        } catch (InterruptedException e) {
            logger.error(e.getMessage(), e);
        }
    }

    public void buy() {
        WebDriver wd = getWebDriver();

        wd.get("https://www.yyfax.com/user/login.html");
        sleep(2000);
        WebElement elementName = wd.findElement(By.xpath("//*[@id=\"accountName\"]"));
        elementName.sendKeys(dataConfig.getYyfaxAccount());
        WebElement elementPassword = wd.findElement(By.xpath("//*[@id=\"password1\"]"));
        elementPassword.sendKeys(dataConfig.getYyfaxPassword());
        WebElement elementVerifyImg = wd.findElement(By.xpath("//*[@id=\"_verifyImg\"]"));
//        wd.navigate().
        BufferedImage fullImage = snapshot((TakesScreenshot) wd);
        saveImage(fullImage, "fullImage.jpg");
        Rectangle verifyRect = getVerifyRect(fullImage);
        BufferedImage verifyImage = fullImage.getSubimage(verifyRect.getX(), verifyRect.getY(), verifyRect.getWidth(), verifyRect.getHeight());
        saveImage(verifyImage, "verifyCode.jpg");
//        File imageFile = getVerifyImg(wd.manage().getCookies());


        WebElement elementVerifyCode = wd.findElement(By.xpath("//*[@id=\"verifyCode\"]"));
        elementVerifyCode.sendKeys("1234567");
    }


    private static final Point DirectionX = new Point(1, 0);
    private static final int[] FlagX = {-4144960, -4144960, -4144960, -4079167, -4013374, -4013374, -4013374, -4013374,
            -4013374, -3947581, -3947581, -3881788, -3881788, -3881788};
    private static final Point DirectionY = new Point(0, 1);
    private static final int[] FlagY = {-4144960, -4144960, -4144960, -4144960};
    private Rectangle getVerifyRect(BufferedImage fullImage) {
//        printRectValue(fullImage, 1105, 747, 15);
        for (int x = 0, endX = fullImage.getWidth() - 150; x < endX; ++x) {
            for (int y = 0, endY = fullImage.getHeight() - 50; y < endY; ++y) {
                if (colorEqual(fullImage, x, y, FlagX, DirectionX)
                        && colorEqual(fullImage, x, y + 1, FlagY, DirectionY)) {
                    return new Rectangle(x, y, 50, 150);
                }
            }
        }
        return new Rectangle(1106, 748, 50, 150);
    }


    private boolean colorEqual(BufferedImage image, int x, int y, int[] flag, Point direction) {
        int curX = x, curY = y;
        for (int v : flag) {
            //1106 748
            int color = image.getRGB(curX, curY);
            if (color != v) {
                return false;
            }
            curX += direction.x;
            curY += direction.y;
        }
        return true;
    }

    private BufferedImage snapshot(TakesScreenshot drivername) {
        byte[] imageBytes = drivername.getScreenshotAs(OutputType.BYTES);
        BufferedImage image = null;
        try {
            image = ImageIO.read(new ByteArrayInputStream(imageBytes));
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return image;
    }

    private void saveImage(BufferedImage verifyImage, String fileName) {
        File outputfile = new File(fileName);
        try {
            ImageIO.write(verifyImage, "jpg", outputfile);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }
}

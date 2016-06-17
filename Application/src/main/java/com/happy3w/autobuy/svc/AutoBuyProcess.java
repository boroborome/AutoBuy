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

import com.happy3w.autobuy.svc.upload.UploadContext;
import com.happy3w.autobuy.util.RequestUtil;

/**
 * Created by ysgao on 5/16/16.
 */
public class AutoBuyProcess implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(AutoBuyProcess.class);
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

        wd.get("https://www.yyfax.com/user/login.html");
        sleep(2000);
        WebElement elementName = wd.findElement(By.xpath("//*[@id=\"accountName\"]"));
        elementName.sendKeys(account);
        WebElement elementPassword = wd.findElement(By.xpath("//*[@id=\"password1\"]"));
        elementPassword.sendKeys(password);
        WebElement elementVerifyCode = wd.findElement(By.xpath("//*[@id=\"verifyCode\"]"));
        elementVerifyCode.sendKeys(getVerifyCode(wd));




    }

    private String getVerifyCode(WebDriver wd) {
        WebElement elementVerifyImg = wd.findElement(By.xpath("//*[@id=\"_verifyImg\"]"));
        int time = 5;
        for (; time >= 0; --time) {
            // get verify image
            BufferedImage fullImage = snapshot((TakesScreenshot) wd);
//            Rectangle verifyRect = getVerifyRect(fullImage);
            Point location = elementVerifyImg.getLocation();
            Dimension size = elementVerifyImg.getSize();
            BufferedImage verifyImage = fullImage.getSubimage(location.getX(), location.getY(), size.getWidth(), size.getHeight());

            // translate to asnii
            String verifyCode = translateImage(verifyImage);

            if (verifyCode != null && !verifyCode.isEmpty()) {
                return verifyCode;
            }
            elementVerifyImg.click();
            sleep(2000);
        }

        throw new TimeoutException("Can't get verify code after 5 times trying.");
    }

    private String translateImage(BufferedImage verifyImage) {
        try {
            sendImageToServer(verifyImage);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            return null;
        }

        for (int times = 10; times >= 0; --times) {
            String verifyCode = readVerifyFromServer();
            if (verifyCode != null && !verifyCode.isEmpty()) {
                return verifyCode;
            }
            sleep(5000);
        }
        return null;
    }

    private String readVerifyFromServer() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject("http://www.happy3w.com/autobuy/readVerify.php", String.class);
    }

    private void sendImageToServer(BufferedImage verifyImage) throws IOException {
        Map<String, String> fileMap = new HashMap<String, String>();
		fileMap.put("file", "verifycode.jpg");
		String ret = RequestUtil.formUpload(UploadContext.getInstance().getUploadUrl(this.webServerUrl), null,fileMap,image2InputStream(verifyImage));
		this.logger.debug(ret);
    }

    private static InputStream image2InputStream(BufferedImage image) throws IOException {
        ByteArrayOutputStream bs =new ByteArrayOutputStream();

        ImageOutputStream imOut =ImageIO.createImageOutputStream(bs);

        ImageIO.write(image,"jpg",imOut); //scaledImage1为BufferedImage，jpg为图像的类型

        return new ByteArrayInputStream(bs.toByteArray());
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


    private void sleep(int second) {
        try {
            Thread.sleep(second);
        } catch (InterruptedException e) {
            logger.error(e.getMessage(), e);
        }
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

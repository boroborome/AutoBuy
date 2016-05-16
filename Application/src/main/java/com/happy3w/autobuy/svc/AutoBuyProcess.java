package com.happy3w.autobuy.svc;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.AbstractResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * Created by ysgao on 5/16/16.
 */
public class AutoBuyProcess implements Runnable {
    private final Logger logger = LoggerFactory.getLogger(AutoBuyProcess.class);
    private final String account;
    private final String password;

    public AutoBuyProcess(String account, String password) {
        this.account = account;
        this.password = password;
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
            Rectangle verifyRect = getVerifyRect(fullImage);
            BufferedImage verifyImage = fullImage.getSubimage(verifyRect.getX(), verifyRect.getY(), verifyRect.getWidth(), verifyRect.getHeight());

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

    private static void sendImageToServer(BufferedImage verifyImage) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
//        restTemplate.setRequestFactory(new HttpsClientRequestFactory(null, null));

        AbstractResource resource = new InputStreamResource(image2InputStream(verifyImage));
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<String, Object>();
        param.add("jarFile", resource);
        param.add("fileName", "verifyCode.jpg");

        restTemplate.postForObject("http://www.happy3w.com/autobuy/upload.php", param, String.class);
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

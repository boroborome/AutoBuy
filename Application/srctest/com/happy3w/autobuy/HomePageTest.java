package com.happy3w.autobuy;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.core.io.AbstractResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Set;

/**
 * created by ysgao on 5/3/16.
 */
public class HomePageTest {

    public static void main2(String[] arg) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/ysgao/Projects/HostIE/chromedriver");
        WebDriver wd = new ChromeDriver();
//        WebDriver wd = new FirefoxDriver();
        wd.get("https://www.yyfax.com/user/login.html");
        System.out.println(wd.getTitle());
        Thread.sleep(2000);
        WebElement elementName = wd.findElement(By.xpath("//*[@id=\"accountName\"]"));
        elementName.sendKeys("chjj402@sina.com");
        WebElement elementPassword = wd.findElement(By.xpath("//*[@id=\"password1\"]"));
        elementPassword.sendKeys("yy2900");
        WebElement elementVerifyImg = wd.findElement(By.xpath("//*[@id=\"_verifyImg\"]"));
//        wd.navigate().
        BufferedImage fullImage = snapshot((TakesScreenshot) wd);
        saveImage(fullImage, "fullImage.jpg");
        Rectangle verifyRect = getVerifyRect(fullImage);
        BufferedImage verifyImage = cutImage(fullImage, verifyRect);// elementVerifyImg.getRect());
        saveImage(verifyImage, "verifyCode.jpg");
//        File imageFile = getVerifyImg(wd.manage().getCookies());
        String verifyCode = getVerifyCode(verifyImage);

        translateByWechart(verifyImage);

        WebElement elementVerifyCode = wd.findElement(By.xpath("//*[@id=\"verifyCode\"]"));
        elementVerifyCode.sendKeys("1234567");
        Thread.sleep(1000);

//        element.click();
//        WebElement rightE = wd.findElement(By.xpath("//div[@class='l_right791_cnt']"));
//        WebElement info1 = rightE.findElement(By.xpath("div[2]"));
//        WebElement leader = rightE.findElement(By.xpath("div[4]"));
//        Thread.sleep(2000);
//        wd.quit();
    }

    private static String getVerifyCode(BufferedImage verifyImage) {
        try {
            sendPicture2Server(verifyImage);
            return waitAnswer();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static String waitAnswer() {
        String verifyCode = null;
//        while (verifyCode)
        return null;
    }

    private static void sendPicture2Server(BufferedImage verifyImage) throws IOException {
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

    private static String translateByWechart(BufferedImage verifyImage) {
        WebDriver wd = new ChromeDriver();
        wd.get("https://mp.weixin.qq.com/");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement account = wd.findElement(By.xpath("//*[@id=\"account\"]"));
        account.sendKeys("boroborome@sina.com");
        WebElement password = wd.findElement(By.xpath("//*[@id=\"pwd\"]"));
        password.sendKeys("48%WedMay");
        WebElement btnLogin = wd.findElement(By.xpath("//*[@id=\"loginBt\"]"));
        btnLogin.click();

        return "1234567";
    }

    private static final Point DirectionX = new Point(1, 0);
    private static final int[] FlagX = {-4144960, -4144960, -4144960, -4079167, -4013374, -4013374, -4013374, -4013374,
            -4013374, -3947581, -3947581, -3881788, -3881788, -3881788};
    private static final Point DirectionY = new Point(0, 1);
    private static final int[] FlagY = {-4144960, -4144960, -4144960, -4144960};
    private static Rectangle getVerifyRect(BufferedImage fullImage) {
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

    private static void printRectValue(BufferedImage image, int x, int y, int size) {
        for (int curY = y, endY = y + size; curY < endY ; ++curY) {
            for (int curX = x, endX = x + size; curX < endX; ++curX) {
                System.out.print(image.getRGB(curX, curY));
                System.out.print(' ');
            }
            System.out.println();
        }
    }

    private static boolean colorEqual(BufferedImage image, int x, int y, int[] flag, Point direction) {
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

    private static void saveImage(BufferedImage verifyImage, String fileName) {
        File outputfile = new File(fileName);
        try {
            ImageIO.write(verifyImage, "jpg", outputfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static BufferedImage cutImage(BufferedImage fullImage, Rectangle rect) {
        return fullImage.getSubimage(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
    }

    public static BufferedImage snapshot(TakesScreenshot drivername) {
        byte[] imageBytes = drivername.getScreenshotAs(OutputType.BYTES);
        BufferedImage image = null;
        try {
            image = ImageIO.read(new ByteArrayInputStream(imageBytes));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
//        // this method will take screen shot ,require two parameters ,one is driver name, another is file name
//
//        File scrFile = drivername.getScreenshotAs(OutputType.FILE);
//        // Now you can do whatever you need to do with it, for example copy somewhere
//        try {
//            System.out.println("save snapshot path is:E:/" + filename);
//            FileUtils.copyFile(scrFile, new File(filename));
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            System.out.println("Can't save screenshot");
//            e.printStackTrace();
//        } finally {
//            System.out.println("screen shot finished");
//        }
    }

    private static File getVerifyImg(Set<Cookie> cookies) {
        RestTemplate restTemplate = new RestTemplate();
//        restTemplate.setRequestFactory(new HttpsClientRequestFactory(null, null));

        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.add("Accept", "image/webp,image/*,*/*;q=0.8");
        headers.add("Accept-Encoding", "gzip, deflate, sdch");
        headers.add("Accept-Language", "en-US,en;q=0.8");
//        headers.add("Cache-Control", "max-age=0");
        headers.add("Connection", "keep-alive");
        headers.add("Cookie", cookieStr(cookies));
//        headers.add("Upgrade-Insecure-Requests", "1");
        headers.add("Host", "www.yyfax.com");
        headers.add("Referer", "https://www.yyfax.com/user/login.html");
        headers.add("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.94 Safari/537.36");
//        user.setSystemId("Preselect");

        HttpEntity<Object> requestEntity = new HttpEntity<Object>("", headers);

        FileOutputStream fileStream = null;
        try {
            byte[] result = restTemplate.getForObject("https://www.yyfax.com/user/loginVerify.htm?" + Math.random(), byte[].class, requestEntity);

            File imageFile = new File("virifyCode.jpg");
            fileStream = new FileOutputStream(imageFile);
            fileStream.write(result);
            return imageFile;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (fileStream != null) {
                try {
                    fileStream.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    fileStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static String cookieStr(Set<Cookie> cookies) {
        StringBuilder buf = new StringBuilder();
        for (Cookie c : cookies) {
            buf.append(c.getName()).append('=').append(c.getValue()).append(';');
        }
        buf.setLength(buf.length() - 1);
        return buf.toString();
    }
}

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
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.happy3w.autobuy.transfer.TransferUrl;
import com.happy3w.autobuy.util.HttpUtil;
import com.happy3w.autobuy.util.LogUtil;
import com.happy3w.autobuy.util.ThreadUtil;

/**
 * 处理验证码相关事务。
 * 
 * @version 2016年6月25日 下午12:30:23
 * @author Happy3W cherry
 *
 */
@Component
public class VerifyCodeProcess {
	@Autowired
	private HttpUtil http;
	@Autowired
	private String httpUrl;
	@Autowired
	private TransferUrl transfer;

	public HttpUtil getHttp() {
		return http;
	}

	public void setHttp(HttpUtil http) {
		this.http = http;
	}

	public String getHttpUrl() {
		return httpUrl;
	}

	public void setHttpUrl(String httpUrl) {
		this.httpUrl = httpUrl;
	}

	public TransferUrl getTransfer() {
		return transfer;
	}

	public void setTransfer(TransferUrl transfer) {
		this.transfer = transfer;
	}

	public VerifyCodeProcess() {
		http = new HttpUtil();
		//transfer = new TransferUrl();
	}

	public String getVerifyCode(WebDriver wd) {
		return null;
//		WebElement elementVerifyImg = wd.findElement(By.xpath("//*[@id=\"_verifyImg\"]"));
//		int time = 5;
//		for (; time >= 0; --time) {
//			// get verify image
//			BufferedImage fullImage = snapshot((TakesScreenshot) wd);
//			// Rectangle verifyRect = getVerifyRect(fullImage);
//			Point location = elementVerifyImg.getLocation();
//			Dimension size = elementVerifyImg.getSize();
//			BufferedImage verifyImage = fullImage.getSubimage(location.getX(), location.getY(), size.getWidth(),
//					size.getHeight());
//
//			// translate to asnii
//			String verifyCode = translateImage(verifyImage);
//
//			if (verifyCode != null && !verifyCode.isEmpty()) {
//				return verifyCode;
//			}
//			elementVerifyImg.click();
//			ThreadUtil.sleep(2000);
//		}
//
//		throw new TimeoutException("Can't get verify code after 5 times trying.");
	}

	private String translateImage(BufferedImage verifyImage) {
		try {
			sendImageToServer(verifyImage);
		} catch (IOException e) {
		//	LogUtil.getLogger().error(e.getMessage(), e);
			return null;
		}

		return readVerifyFromServer(5 * 60 * 1000);
	}

	public String readVerifyFromServer(int timeout) {
		String verifyCode = null;
//		int unit = 2000;
//		for (int times = timeout / unit; times >= 0; --times) {
//			RestTemplate restTemplate = new RestTemplate();
//			verifyCode = restTemplate.getForObject(transfer.getVerifyCodeResultUrl(httpUrl), String.class);
//			if (verifyCode != null && !verifyCode.isEmpty()) {
//				break;
//			}
//			ThreadUtil.sleep(unit);
//		}
		return verifyCode;
	}

	private void sendImageToServer(BufferedImage verifyImage) throws IOException {
		Map<String, String> fileMap = new HashMap<String, String>();
		fileMap.put("file", "verifycode.jpg");
//		String ret = http.formUpload(TransferUrl.getInstance().getUploadUrl(this.httpUrl), null, fileMap,
//				image2InputStream(verifyImage));
		//LogUtil.getLogger().debug(ret);
	}

	private static InputStream image2InputStream(BufferedImage image) throws IOException {
		ByteArrayOutputStream bs = new ByteArrayOutputStream();

		ImageOutputStream imOut = ImageIO.createImageOutputStream(bs);

		ImageIO.write(image, "jpg", imOut); // scaledImage1为BufferedImage，jpg为图像的类型

		return new ByteArrayInputStream(bs.toByteArray());
	}

	private BufferedImage snapshot(TakesScreenshot drivername) {
		byte[] imageBytes = drivername.getScreenshotAs(OutputType.BYTES);
		BufferedImage image = null;
		try {
			image = ImageIO.read(new ByteArrayInputStream(imageBytes));
		} catch (IOException e) {
			//LogUtil.getLogger().error(e.getMessage(), e);
		}
		return image;
	}
}

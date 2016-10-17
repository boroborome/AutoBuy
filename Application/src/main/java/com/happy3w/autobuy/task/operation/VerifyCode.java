/**
 * 
 */
package com.happy3w.autobuy.task.operation;

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

import com.happy3w.autobuy.config.SysConfig;
import com.happy3w.autobuy.exp.SysException;
import com.happy3w.autobuy.transfer.TransferUrl;
import com.happy3w.autobuy.util.HttpUtil;
import com.happy3w.autobuy.util.ThreadUtil;

/**
 * 识别验证码。
 * @version 2016年9月9日 下午2:26:23
 * @author Happy3W Cherry
 *
 */
public class VerifyCode implements IHandler {
	private int unit = 1000;
	private TransferUrl transfer;
	private HttpUtil http;
	private String xpath;
	private String returnName;
	private SysConfig config;

	public VerifyCode(SysConfig config, TransferUrl transfer, HttpUtil http, String xpath, String returnName) {
		this.config = config;
		this.transfer = transfer;
		this.http = http;
		this.xpath = xpath;
		this.returnName = returnName;
	}

	@Override
	public Result[] handle(WebDriver driver, Param param) {
		Result result = new Result(returnName, getImgResult(driver));
		return new Result[] { result };

	}

	/**
	 * 获取图片信息。
	 * 
	 * @param wd
	 * @return
	 */
	private String getImgResult(WebDriver wd) {
		WebElement elementVerifyImg = wd.findElement(By.xpath(xpath));
		for (int i = config.getTransferRetryTimes(); i >= 0; --i) {
			// get verify image
			BufferedImage fullImage = snapshot((TakesScreenshot) wd);
			// Rectangle verifyRect = getVerifyRect(fullImage);
			Point location = elementVerifyImg.getLocation();
			Dimension size = elementVerifyImg.getSize();
			BufferedImage verifyImage = fullImage.getSubimage(location.getX(), location.getY(), size.getWidth(),
					size.getHeight());

			String imgResult = translateImage(verifyImage);

			if (imgResult != null && !imgResult.isEmpty()) {
				return imgResult;
			}
			//如果没有读到，则刷新验证码。
			elementVerifyImg.click();
			ThreadUtil.sleep(unit);
		}

		throw new TimeoutException("Can't get verify code after 5 times trying.");
	}

	/**
	 * 翻译图片信息。
	 * 
	 * @param verifyImage
	 * @return
	 */
	private String translateImage(BufferedImage img) {
		try {
			sendImageToServer(img);
		} catch (IOException e) {
			throw new SysException(e);
		}

		return readResultFromServer();
	}

	/**
	 * 发送图片到服务器上，以便服务器寻找识别图片信息的处理端。
	 * 
	 * @param verifyImage
	 * @throws IOException
	 */
	private void sendImageToServer(BufferedImage verifyImage) throws IOException {
		Map<String, String> fileMap = new HashMap<String, String>();
		fileMap.put("file", "verifycode.jpg");
		String ret = http.formUpload(transfer.getUploadUrl(), null, fileMap, image2InputStream(verifyImage));
	}

	/**
	 * 将图片信息变成输入流。
	 * 
	 * @param image
	 * @return
	 * @throws IOException
	 */
	private static InputStream image2InputStream(BufferedImage image) throws IOException {
		ByteArrayOutputStream bs = new ByteArrayOutputStream();

		ImageOutputStream imOut = ImageIO.createImageOutputStream(bs);

		ImageIO.write(image, "jpg", imOut); // scaledImage1为BufferedImage，jpg为图像的类型

		return new ByteArrayInputStream(bs.toByteArray());
	}

	/**
	 * 获取图片信息。
	 * 
	 * @param drivername
	 * @return
	 */
	private BufferedImage snapshot(TakesScreenshot drivername) {
		byte[] imageBytes = drivername.getScreenshotAs(OutputType.BYTES);
		BufferedImage image = null;
		try {
			image = ImageIO.read(new ByteArrayInputStream(imageBytes));
		} catch (IOException e) {
			throw new SysException(e);
		}
		return image;
	}

	/**
	 * 从服务器上读取信息。
	 * 
	 * @param timeout
	 * @return
	 */
	private String readResultFromServer() {
		String result = null;
		for (int i = config.getTransferRetryTimes(); i >= 0; --i) {
			RestTemplate restTemplate = new RestTemplate();
			result = restTemplate.getForObject(transfer.getVerifyCodeResultUrl(), String.class);
			if (result != null && !result.isEmpty()) {
				break;
			}
			ThreadUtil.sleep(unit);
		}
		return result;
	}
}

/**
 * 
 */
package com.happy3w.autobuy.action;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
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
import org.springframework.web.util.UriComponentsBuilder;

import com.happy3w.autobuy.action.strc.ActStruct;
import com.happy3w.autobuy.action.strc.VerifyStruct;
import com.happy3w.autobuy.config.SysConfig;
import com.happy3w.autobuy.exp.SysException;
import com.happy3w.autobuy.model.UserOrder;
import com.happy3w.autobuy.transfer.TransferUrl;
import com.happy3w.autobuy.util.HttpUtil;
import com.happy3w.autobuy.util.ThreadUtil;

/**
 * 识别验证码。
 * @version 2016年9月9日 下午2:26:23
 * @author Happy3W Cherry
 *
 */
@Component
public class VerifyImg extends BaseAction {
	private HttpUtil http;
	private String xpath;
	
	/**
	 * 识别图片上传位置。
	 */
	private String uploadUrl;
	/**
	 * 识别结果下载位置。
	 */
	private String downloadUrl;
	private int times=10;
	private long timeout=2000;
	/**
	 * @param config 配置信息。
	 * @param xpath 验证码图片位置。
	 * @param http 服务器访问工具。
	 * @param uploadUrl 识别图片上传位置。
	 * @param downloadUrl 识别结果下载位置。
	 */
	public VerifyImg(SysConfig config, String xpath, HttpUtil http,String uploadUrl,String downloadUrl ) {
		//this.config = config;
		this.http = http;
		this.xpath = xpath;
		this.uploadUrl=uploadUrl;
		this.downloadUrl=downloadUrl;
	}
	public VerifyImg()
	{
		super();
		http=new HttpUtil();
		
	}
	@Override
	public void setStruct(ActStruct struct)
	{
		super.setStruct(struct);
		VerifyStruct verify  = (VerifyStruct) struct;
		this.xpath=verify.getTarget();
		this.uploadUrl=verify.getUpload();
		this.downloadUrl=verify.getDownload();
	}
	@Override
	public Result[] handle(WebDriver driver, Param param) {
		this.uploadUrl=param.getValue("srv")+"/"+this.uploadUrl;
		this.downloadUrl=param.getValue("srv")+"/"+this.downloadUrl;
		Result result = new Result(this.getStruct().getReturnName(), getImgResult(driver,param.getValue(UserOrder.ORDERID)));
		return new Result[] { result };

	}

	/**
	 * 获取图片信息。
	 * 
	 * @param wd
	 * @param file 
	 * @return
	 */
	private String getImgResult(WebDriver wd, String file) {
		WebElement elementVerifyImg = wd.findElement(By.xpath(xpath));
		for (int i = this.times; i >= 0; --i) {
			// get verify image
			BufferedImage fullImage = snapshot((TakesScreenshot) wd);
			// Rectangle verifyRect = getVerifyRect(fullImage);
			Point location = elementVerifyImg.getLocation();
			Dimension size = elementVerifyImg.getSize();
			BufferedImage verifyImage = fullImage.getSubimage(location.getX(), location.getY(), size.getWidth(),
					size.getHeight());

			String imgResult = translateImage(verifyImage,file);

			if (imgResult != null && !imgResult.isEmpty()) {
				return imgResult;
			}
			//如果没有读到，则刷新验证码。
			elementVerifyImg.click();
			ThreadUtil.sleep(timeout);
		}

		throw new TimeoutException("Can't get verify code after 5 times trying.");
	}

	/**
	 * 翻译图片信息。
	 * @param file 
	 * 
	 * @param verifyImage
	 * @return
	 */
	private String translateImage(BufferedImage img, String file) {
		try {
			sendImageToServer(img,file);
		} catch (IOException e) {
			throw new SysException(e);
		}

		return readResultFromServer(file);
	}

	/**
	 * 发送图片到服务器上，以便服务器寻找识别图片信息的处理端。
	 * 
	 * @param verifyImage
	 * @param file 
	 * @throws IOException
	 */
	private void sendImageToServer(BufferedImage verifyImage, String file) throws IOException {
		Map<String, String> fileMap = new HashMap<String, String>();
		fileMap.put("file", file);
		http.formUpload(uploadUrl, null, fileMap, image2InputStream(verifyImage));
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
	private String readResultFromServer(String file) {
		String result = null;
		for (int i = times; i >= 0; --i) {
			RestTemplate restTemplate = new RestTemplate();
			Map<String,String> map = new HashMap<String,String>();
			map.put("file", file);
			URI uri = UriComponentsBuilder.fromUriString(downloadUrl).queryParam("file", file).build().toUri();
			result = restTemplate.getForObject(uri, String.class);
			if (result != null && !result.isEmpty()) {
				break;
			}
			ThreadUtil.sleep(this.timeout);
		}
		return result;
	}
}

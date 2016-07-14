/**
 * 
 */
package com.happy3w.autobuy.svc;

import java.text.MessageFormat;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.happy3w.autobuy.util.ThreadUtil;
import com.happy3w.autobuy.util.WebDriverUtil;
import com.happy3w.autobuy.util.config.Command;

/**
 * 选择购买哪个产品。
 * 
 * @version 2016年6月26日 上午7:35:20
 * @author Happy3W Cherry
 *
 */
public class ProductManager {
	private WebDriver webDriver;
	private VerifyCodeManager vc;
	/**
	 * 购买指令。
	 */
	private Command command;

	public WebDriver getWebDriver() {
		return webDriver;
	}

	public void setWebDriver(WebDriver webDriver) {
		this.webDriver = webDriver;
	}

	public Command getCommand() {
		return command;
	}

	public void setCommand(Command command) {
		this.command = command;
	}
	
	public VerifyCodeManager getVc() {
		return vc;
	}

	public void setVc(VerifyCodeManager vc) {
		this.vc = vc;
	}

	/**
	 * 下单购买。
	 */
	public void buy()
	{
		openProduct();
		inputMoney();
		pay();
	}
	/**
	 * 打开可购买产品列表。
	 */
	private void openProduct() {
		WebDriverUtil.get(webDriver, "https://www.yyfax.com/financing/yxlc/yxlb.html", "", 2000);
		for (int i = 2; i < 10; i++) {
			String path = MessageFormat.format("/html/body/div[2]/div/div[2]/div[2]/table/tbody/tr[{0}]", i);
			WebElement row = webDriver.findElement(By.xpath(path));
			WebElement product = row.findElement(By.xpath("td[1]/div/span/a"));
			String[] parts = product.getText().split("-");
			if (command.getProduct().equalsIgnoreCase(parts[1])) {
				WebElement enable = row.findElement(By.xpath("td[7]/span"));
				WebElement buy = enable.findElement(By.xpath("a"));
				if (null != buy) {
					String url = buy.getAttribute("href");
					webDriver.get(url);
					return;
				}
			}
		}
	}

	/**
	 * 数据金额，点击购买。
	 */
	private void inputMoney() {
		WebElement money = webDriver.findElement(By.xpath("//*[@id=\"gmMoney\"]"));
		money.sendKeys(String.valueOf(command.getAmout()));
		WebElement ycode = webDriver.findElement(By.xpath("//*[@id=\"yCode\"]"));
		ycode.sendKeys(command.getYcode());
		WebElement btnBuy = webDriver.findElement(By.xpath("//*[@id=\"otButton\"]"));
		btnBuy.click();
	}

	/**
	 * 选择支付方式。
	 * 
	 */
	private void pay() {
		//选择支付方式：认证支付
		WebElement path = webDriver.findElement(By.xpath("//*[@id=\"chooseBuyWayDlg\"]/div/div/div[2]/label[1]"));
		path.click();
		ThreadUtil.sleep(500);
		//点击确认
		WebElement confirmPay = webDriver.findElement(By.xpath("//*[@id=\"btn-gollbuy\"]"));
		confirmPay.click();
		ThreadUtil.sleep(1000);
		//同意协议
		WebElement agree=webDriver.findElement(By.xpath("//*[@id=\"iAgree\"]"));
		agree.click();
		ThreadUtil.sleep(500);
		//点击确定付款
		WebElement btnOk=webDriver.findElement(By.xpath("//*[@id=\"ok\"]"));
		btnOk.click();
		ThreadUtil.sleep(1000);
		//进入银行付款页面。
		WebElement sendVCToPhone=webDriver.findElement(By.xpath("//*[@id=\"getValidCode\"]"));
		sendVCToPhone.click();
		WebElement inputVC = webDriver.findElement(By.xpath("//*[@id=\"phoneCode\"]"));
		inputVC.sendKeys(vc.readVerifyFromServer(2*60*1000));
		WebElement nextStep=webDriver.findElement(By.xpath("//*[@id=\"btnPurchase\"]"));
		nextStep.click();
		ThreadUtil.sleep(3000);
		//支付成功，关闭页面。
		WebElement close = webDriver.findElement(By.xpath("//*[@id=\"info\"]/div/div[2]/div[3]/a"));
		close.click();
	}
	
}

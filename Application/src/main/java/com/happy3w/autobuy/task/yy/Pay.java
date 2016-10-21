/**
 * 
 */
package com.happy3w.autobuy.task.yy;

import com.happy3w.autobuy.config.SysConfig;
import com.happy3w.autobuy.task.action.ClickElement;

/**
 * 下单购买。
 * @version 2016年10月21日下午4:13:52
 * @author happy3w
 */
public class Pay extends TaskHandler {

	public Pay(SysConfig config) {
		super(config);
		//选择支付方式：认证支付。
		this.getEvents().add(new ClickElement("//*[@id=\"chooseBuyWayDlg\"]/div/div/div[2]/label[1]"));
		//点击确认
		this.getEvents().add(new ClickElement("//*[@id=\"btn-gollbuy\"]"));
		//同意协议
		this.getEvents().add(new ClickElement("//*[@id=\"iAgree\"]"));
		//点击确定付款
		this.getEvents().add(new ClickElement("//*[@id=\"ok\"]"));
		//进入银行付款页面。
		this.getEvents().add(new ClickElement("//*[@id=\"getValidCode\"]"));
		//输入手机验证码。
		this.getEvents().add(new ClickElement("//*[@id=\"phoneCode\"]"));
		//下一步
		this.getEvents().add(new ClickElement("//*[@id=\"btnPurchase\"]"));
		//支付成。
		this.getEvents().add(new ClickElement("//*[@id=\"info\"]/div/div[2]/div[3]/a"));
	}

}

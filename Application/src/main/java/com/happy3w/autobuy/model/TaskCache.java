/**
 * 
 */
package com.happy3w.autobuy.model;

import java.util.HashMap;
import java.util.Map;

import com.happy3w.autobuy.action.Click;
import com.happy3w.autobuy.action.ClickCell;
import com.happy3w.autobuy.action.GetCell;
import com.happy3w.autobuy.action.Input;
import com.happy3w.autobuy.action.OpenPage;
import com.happy3w.autobuy.action.VerifyImg;

/**
 * @version 2016年10月25日上午9:34:18
 * @author happy3w
 */
public class TaskCache {
	private Map<String,Task> tasks=  new HashMap<String,Task>();
	private static TaskCache instance;
	public TaskCache()
	{
		Task task  =new Task("YY","YY");
		tasks.put(task.getTaskCode(), task);
		Stage  ycode = new Stage("ycode","ycode","chenjij@yonyou.com","***");
		task.put(ycode);
		ycode.put(new ActStruct("login", OpenPage.class.getName(), "https://www.yyfax.com/user/login.html", null, null, "我的YCode-友金所"));
		ycode.put(new ActStruct("login",Input.class.getName(),"//*[@id=\"accountName\"]",User.USERID,null,null));
		ycode.put(new ActStruct("login",Input.class.getName(),"//*[@id=\"password1\"]",User.PASSWORD,null,null));
		ycode.put(new VerifyStruct("login",VerifyImg.class.getName(),null,null,"//*[@id=\"_verifyImg\"]", "/vc/upload.php", "/vc/result.php", "vc"));
		ycode.put(new ActStruct("login",Input.class.getName(),"//*[@id=\"verifyCode\"]","vc",null,null));
		ycode.put(new ActStruct("login",Click.class.getName(),"//*[@id=\"login\"]",null,null,null));
		ycode.put(new ActStruct("get",OpenPage.class.getName(),"https://www.yyfax.com/user/spread/wdycode.html",null,null,null));
		ycode.put(new CellStruct("get",GetCell.class.getName(),null,"/html/body/div[2]/div[2]/form/div/div[1]/table/tbody", 2, 10, "td[1]", "td[3]",null,"可用","ycode"));
		Stage buy  =new Stage("buy","buy","chjj402@sina.com","***");
		task.put(buy);
		buy.putAll(ycode.getActions("login"));
		buy.put(new ActStruct("chose",OpenPage.class.getName(),"https://www.yyfax.com/financing/yxlc/yxlb.html",null,null,null));
		buy.put(new CellStruct("chose",ClickCell.class.getName(),null,"/html/body/div[2]/div/div[2]/div[2]/table/tbody", 2, 10, "td[7]", "td[1]", UserOrder.PRODUCT, null,null));
		buy.put(new ActStruct("confirm",Input.class.getName(),"//*[@id=\"gmMoney\"]",UserOrder.AMOUNT,null,null));
		buy.put(new ActStruct("confirm",Input.class.getName(),"//*[@id=\"yCode\"]","ycode",null,null));
		buy.put(new ActStruct("confirm",Click.class.getName(),"//*[@id=\"otButton\"]",null,null,null));
		buy.put(new ActStruct("pay",Click.class.getName(),"//*[@id=\"chooseBuyWayDlg\"]/div/div/div[2]/label[1]",null,null,null));
		buy.put(new ActStruct("pay",Click.class.getName(),"//*[@id=\"btn-gollbuy\"]",null,null,null));
		buy.put(new ActStruct("pay",Click.class.getName(),"//*[@id=\"iAgree\"]",null,null,null));
		buy.put(new ActStruct("pay",Click.class.getName(),"//*[@id=\"ok\"]",null,null,null));
		buy.put(new ActStruct("pay",Click.class.getName(),"//*[@id=\"getValidCode\"]",null,null,null));
		buy.put(new ActStruct("pay",Click.class.getName(),"//*[@id=\"phoneCode\"]",null,null,null));
		buy.put(new ActStruct("pay",Click.class.getName(),"//*[@id=\"btnPurchase\"]",null,null,null));
		buy.put(new ActStruct("pay",Click.class.getName(),"//*[@id=\"info\"]/div/div[2]/div[3]/a",null,null,null));
	}
	public static TaskCache getInstance()
	{
		if(null==instance)
		{
			instance = new TaskCache();
		}
		return instance;
	}
	public Task get(String taskCode)
	{
		return tasks.get(taskCode);
	}
}

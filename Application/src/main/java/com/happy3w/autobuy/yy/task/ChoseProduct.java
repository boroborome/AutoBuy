/**
 * 
 */
package com.happy3w.autobuy.yy.task;

import org.openqa.selenium.WebDriver;

import com.happy3w.autobuy.config.SysConfig;
import com.happy3w.autobuy.task.action.OpenPage;
import com.happy3w.autobuy.task.action.Param;
import com.happy3w.autobuy.task.action.TableXPath;
import com.happy3w.autobuy.task.base.TaskHandler;
import com.happy3w.autobuy.yy.task.action.YYFilterProduct;
import com.happy3w.autobuy.task.action.ClickCell;

/**
 * 打开要购买的产品。
 * @version 2016年10月19日上午10:53:49
 * @author happy3w
 */
public class ChoseProduct extends TaskHandler{
	public ChoseProduct(SysConfig config)
	{
		super(config);
		this.getEvents().add(new OpenPage("https://www.yyfax.com/financing/yxlc/yxlb.html","",this.getConfig().getTimeout()));
		this.getEvents().add(YYFilterProduct.getInstance());
	}
}

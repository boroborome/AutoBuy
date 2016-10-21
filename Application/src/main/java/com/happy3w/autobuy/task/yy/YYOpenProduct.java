/**
 * 
 */
package com.happy3w.autobuy.task.yy;

import org.openqa.selenium.WebDriver;

import com.happy3w.autobuy.config.SysConfig;
import com.happy3w.autobuy.task.action.OpenPage;
import com.happy3w.autobuy.task.action.Param;
import com.happy3w.autobuy.task.action.TableXPath;
import com.happy3w.autobuy.task.action.ClickCell;
import com.happy3w.autobuy.task.yy.action.YYFilterProduct;

/**
 * 打开要购买的产品。
 * @version 2016年10月19日上午10:53:49
 * @author happy3w
 */
public class YYOpenProduct extends TaskHandler{
	public YYOpenProduct(SysConfig config)
	{
		super(config);
		this.getEvents().add(new OpenPage("https://www.yyfax.com/financing/yxlc/yxlb.html","",this.getConfig().getTimeout()));
		this.getEvents().add(YYFilterProduct.getInstance());
	}
}

/**
 * 
 */
package com.happy3w.autobuy;

import org.junit.Assert;
import org.junit.Test;

import com.happy3w.autobuy.svc.LoginManager;
import com.happy3w.autobuy.util.SpringUtil;
import com.happy3w.autobuy.util.config.Command;



/**
 *
 * @version 2016年6月26日 上午8:30:53
 * @author Happy3W Cherry
 *
 */
public class SpringTest {
	@Test
	public void testGetBean()
	{
		Command conf= new Command();
		String pro=conf.getProduct();
		Double amount=conf.getAmout();
		Assert.assertEquals("C", pro);
		Assert.assertEquals(100d, amount,1);
	}
}

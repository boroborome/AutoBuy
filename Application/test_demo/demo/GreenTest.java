/**
 * 
 */
package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import testkit.com.happy3w.autoby.BaseTest;

/**
 *
 * @version 2016年7月16日 下午2:36:41
 * @author Happy3W Cherry
 *
 */
public class GreenTest extends BaseTest {
	@Autowired
	public Green green;

	@Test
	public void testGetName() {
		Assert.assertEquals("ok", this.green.getName());
	}
}

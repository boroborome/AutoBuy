/**
 * 
 */
package testkit.com.happy3w.autoby;

import org.mockito.MockitoAnnotations;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeTest;

import com.happy3w.autobuy.config.AppConfig;

/**
 *
 * @version 2016年7月16日 下午12:07:55
 * @author Happy3W Cherry
 *
 */
@ContextConfiguration(classes = { AppConfig.class }) // 加载配置文件
// @ContextConfiguration(locations =
// "classpath:com/happy3w/autobuy/spring/spring.xml")
@ActiveProfiles("dev")
@EnableAutoConfiguration
public class BaseTest extends AbstractTestNGSpringContextTests {

	@BeforeTest(alwaysRun = true)
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

}

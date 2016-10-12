/**
 * 
 */
package com.happy3w.autobuy.exe;

import java.util.ArrayList;
import java.util.List;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.happy3w.autobuy.config.SysConfig;
import com.happy3w.autobuy.exe.events.EventParam;
import com.happy3w.autobuy.exe.events.EventRegister;
import com.happy3w.autobuy.exe.events.Event_InputVal;
import com.happy3w.autobuy.exe.events.Event_OpenPage;
import com.happy3w.autobuy.exe.events.Event_ReadImg;
import com.happy3w.autobuy.exe.events.IEvent;
import com.happy3w.autobuy.exe.step.StepManager;
import com.happy3w.autobuy.exe.step.StepRegister;
import com.happy3w.autobuy.exe.step.Steps;
import com.happy3w.autobuy.exe.step.UserRegister;
import com.happy3w.autobuy.model.AtUser;
import com.happy3w.autobuy.model.PurchaseOrder;
import com.happy3w.autobuy.transfer.TransferUrl;
import com.happy3w.autobuy.util.HttpUtil;

import testkit.com.happy3w.autoby.BaseTest;
import testkit.com.happy3w.autoby.TestUtil;

/**
 * 线程执行。
 * 
 * @version 2016年9月9日 下午4:22:08
 * @author Happy3W Cherry
 *
 */
public class ExeThrdRunnerTest extends BaseTest {
	private AtExeThrdRunner runner;
	private StepManager manager;
	@Autowired
	private UserRegister userRegister;
	@Autowired
	private StepRegister stepRegister;
	@Autowired
	private HttpUtil http;
	@Autowired
	private TransferUrl transfer;
	@Autowired
	private SysConfig config;
	@Mock
	private WebDriver driver;
	@Mock
	private EventRegister eventRegister;
	@Mock
	private Event_ReadImg readImg;

	@BeforeMethod
	public void setup() {
		PurchaseOrder[] orders = TestUtil.getOrders(1, 1);
		eventRegister = new EventRegister(http, transfer, config);
		manager = new StepManager(driver, userRegister, stepRegister, eventRegister);
		Mockito.when(eventRegister.getEvents(Steps.YY_LOGIN)).thenReturn(getEvents());
		Mockito.when(readImg.handle(driver, param);)
		runner = new AtExeThrdRunner(orders[0], manager);

	}

	private IEvent[] getEvents() {
		List<IEvent> events = new ArrayList<IEvent>();
		events.add(new Event_OpenPage("https://www.yyfax.com/user/login.html", "我的友金所", config.getTimeout()));
		events.add(new Event_InputVal("//*[@id=\"accountName\"]", AtUser.USERID));
		events.add(new Event_InputVal("//*[@id=\"password1\"]", AtUser.PASSWORD));
		// Event_ReadImg readImg = Mockito.mock(EventRead)
		events.add(readImg);
		Mockito.when(readImg.handle(driver, param)).events
				.add(new Event_InputVal("//*[@id=\"verifyCode\"]", EventParam.VERIFYCODE));
		return events.toArray(new IEvent[0]);
	}

	@Test
	public void testRun() {
		runner.run();
	}
}

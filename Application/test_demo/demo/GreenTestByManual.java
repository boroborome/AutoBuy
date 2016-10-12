package demo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import com.happy3w.autobuy.config.SysConfig;

public class GreenTestByManual {
	public Green green;

	@Test
	public void testGetName() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.getEnvironment().setDefaultProfiles("dev");
		ctx.register(SysConfig.class);
		ctx.refresh();
		this.green = ctx.getBean(Green.class);
		AssertJUnit.assertEquals("ok", this.green.getName());
	}

}

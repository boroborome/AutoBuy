/**
 * 
 */
package com.happy3w.autobuy.yy.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

/**
 *
 * @version 2016年9月1日 下午4:03:56
 * @author Happy3W Cherry
 *
 */
@Configuration
@Profile("dev")
@PropertySource("classpath:/config/config_dev.properties")
public class YYConfigDev extends YYConfig {

}

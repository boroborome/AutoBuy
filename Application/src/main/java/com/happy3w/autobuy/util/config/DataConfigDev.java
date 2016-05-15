package com.happy3w.autobuy.util.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Profile("dev")
@PropertySource("classpath:/config/config_dev.properties")
public class DataConfigDev extends DataConfig{

}

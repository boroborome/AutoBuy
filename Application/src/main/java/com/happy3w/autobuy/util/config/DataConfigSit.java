package com.happy3w.autobuy.util.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;


@Configuration
@Profile("sit")
@PropertySource("classpath:/config/config_sit.properties")
public class DataConfigSit  extends DataConfig{

}

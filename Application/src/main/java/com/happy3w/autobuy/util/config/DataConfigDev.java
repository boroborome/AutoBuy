package com.happy3w.autobuy.util.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.happy3w.autobuy.deamon.Green;

@Configuration
@Profile("dev")
@PropertySource("classpath:/config/config_dev.properties")
public class DataConfigDev extends DataConfig{
}

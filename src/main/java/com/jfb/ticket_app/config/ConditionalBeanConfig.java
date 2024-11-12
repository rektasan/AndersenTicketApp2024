package com.jfb.ticket_app.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConditionalBeanConfig {

  @Bean
  @ConditionalOnProperty(name = "myCustomBean.enabled", havingValue = "true")
  public ThisIsMyFirstConditionalBean thisIsMyFirstConditionalBean() {
    return new ThisIsMyFirstConditionalBean();
  }

}

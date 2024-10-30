package com.jfb.ticket_app.config;

import io.github.cdimascio.dotenv.Dotenv;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@ComponentScan("com.jfb.ticket_app")
public class AppConfig {

  private final ApplicationContext applicationContext;
  private static final Dotenv DOTENV = Dotenv.load();

  @Autowired
  public AppConfig(ApplicationContext applicationContext) {
    this.applicationContext = applicationContext;
  }

  @Bean
  public DataSource dataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName(DOTENV.get("DB_DRIVER"));
    dataSource.setUrl(DOTENV.get("DB_URL"));
    dataSource.setUsername(DOTENV.get("DB_USER"));
    dataSource.setPassword(DOTENV.get("DB_PASSWORD"));
    return dataSource;
  }

  @Bean
  public JdbcTemplate jdbcTemplate() {
    return new JdbcTemplate(dataSource());
  }

}

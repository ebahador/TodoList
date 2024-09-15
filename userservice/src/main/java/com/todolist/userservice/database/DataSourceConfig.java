package com.todolist.userservice.database;

import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:dbconnection.properties")
public class DataSourceConfig {
  private static final Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);

  @Value("${URL}")
  private String url;

  @Value("${USERNAME}")
  private String username;

  @Value("${PASSWORD}")
  private String password;

  @Bean
  public DataSource dataSource() {
    logger.info("url: {}, user: {}, pass:{}", url, username, password);
    BasicDataSource basicDataSource = new BasicDataSource();
    basicDataSource.setUrl(url);
    basicDataSource.setUsername(username);
    basicDataSource.setPassword(password);
    return basicDataSource;
  }
}

package com.todolist.userservice.database;

import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@PropertySource("classpath:application.properties")
public class DataSourceConfig {
  private static final Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);

  @Value("${spring.datasource.url}")
  private String url;

  @Value("${spring.datasource.username}")
  private String username;

  @Value("${spring.datasource.password}")
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

  @Bean
  public JdbcTemplate jdbcTemplate(DataSource dataSource) {
    return new JdbcTemplate(dataSource);
  }
}

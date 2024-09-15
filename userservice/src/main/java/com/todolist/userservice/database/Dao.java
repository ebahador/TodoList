package com.todolist.userservice.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class Dao {
  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public Dao(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public String getUserNameById(String userId) {
    String sqlQuery = "SELECT fullname FROM user WHERE id=?";
    return jdbcTemplate.queryForObject(sqlQuery, String.class, userId);
  }
}

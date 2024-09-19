package com.todolist.userservice.database;

import com.todolist.userservice.model.user.User;
import java.sql.Timestamp;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class Dao {
  private final JdbcTemplate jdbcTemplate;
  private static final Logger logger = LoggerFactory.getLogger(Dao.class);

  @Autowired
  public Dao(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public String getUserNameById(String userId) {
    String sqlQuery = "SELECT fullname FROM users WHERE id=?";
    return jdbcTemplate.queryForObject(sqlQuery, String.class, userId);
  }

  public void addUserToDb(@NotNull User user) {
    String sqlQuery = "INSERT INTO users (id, fullname, email, last_login) VALUES (?, ?, ?, ?)";
    try {
      jdbcTemplate.update(
          sqlQuery,
          user.getId(),
          user.getFullname(),
          user.getEmail(),
          new Timestamp(user.getLastLogin()));
    } catch (DataAccessException e) {
      logger.error("Failed to insert user into the database", e);
      throw new RuntimeException("Database insert operation failed: " + e.getMessage(), e);
    }
  }

  public boolean isEmailAddressExists(String email) {
    try {
      String sqlQuery = "SELECT COUNT(*) FROM users WHERE email=?";
      Integer count = jdbcTemplate.queryForObject(sqlQuery, Integer.class, email);
      return count != null && count > 0;
    } catch (DataAccessException e) {
      logger.error("Failed to check if email address exists", e);
      throw new RuntimeException("Database check operation failed: " + e.getMessage(), e);
    }
  }
}

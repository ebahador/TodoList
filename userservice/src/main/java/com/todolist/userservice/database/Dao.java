package com.todolist.userservice.database;

import com.todolist.userservice.model.user.User;
import com.todolist.userservice.model.user.dto.UpdateUserRequest;
import com.todolist.userservice.router.utils.StrUtils;
import java.sql.Timestamp;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class Dao {
  private final JdbcTemplate jdbcTemplate;
  private static final Logger logger = LoggerFactory.getLogger(Dao.class);

  @Autowired
  public Dao(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
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

  public boolean emailAddressExists(String email) {
    try {
      String sqlQuery = "SELECT COUNT(*) FROM users WHERE email=?";
      Integer count = jdbcTemplate.queryForObject(sqlQuery, Integer.class, email);
      return count != null && count > 0;
    } catch (DataAccessException e) {
      logger.error("Failed to check if email address exists", e);
      throw new RuntimeException("Database check operation failed: " + e.getMessage(), e);
    }
  }

  public User getUserById(String userId) {
    try {
      String sqlQuery = "Select * FROM users WHERE id=?";
      return jdbcTemplate.queryForObject(sqlQuery, userRowMapper(), userId);
    } catch (Exception e) {
      logger.error("Failed to retrieve user from the database", e);
      throw new RuntimeException("Database retrieval operation failed: " + e.getMessage(), e);
    }
  }

  public List<User> getUsers() {
    try {
      String sqlQuery = "Select * FROM users";
      return jdbcTemplate.query(sqlQuery, userRowMapper());
    } catch (Exception e) {
      logger.error("Failed to get all users", e);
      throw new RuntimeException("Database check operation failed: " + e.getMessage(), e);
    }
  }

  private RowMapper<User> userRowMapper() {
    return (rs, rowNum) ->
        new User.Builder()
            .id(rs.getString("id"))
            .fullname(rs.getString("fullname"))
            .email(rs.getString("email"))
            .lastLogin(StrUtils.timestampToMillis(rs.getString("last_login")))
            .build();
  }

  public boolean userExists(String userId) {
    try {
      String sqlQuery = "SELECT COUNT(*) FROM users WHERE id=?";
      Integer count = jdbcTemplate.queryForObject(sqlQuery, Integer.class, userId);
      return count != null && count > 0;
    } catch (Exception e) {
      logger.error("Failed to check if user exists", e);
      throw new RuntimeException("Database check operation failed: " + e.getMessage(), e);
    }
  }

  public void updateUser(String userId, UpdateUserRequest userRequest) {
    try {
      String sqlQuery = "UPDATE users SET fullname=?, email=? WHERE id=?";
      jdbcTemplate.update(sqlQuery, userRequest.getFullname(), userRequest.getEmail(), userId);
    } catch (Exception e) {
      logger.error("Failed to update user into the database: {}", userRequest, e);
      throw new RuntimeException(e);
    }
  }
}

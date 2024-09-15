package database;

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

  public String getTaskSummaryById(String id) {
    String sqlQuery = "SELECT summary FROM task WHERE id = ?";
    return jdbcTemplate.queryForObject(sqlQuery, String.class, id);
  }
}

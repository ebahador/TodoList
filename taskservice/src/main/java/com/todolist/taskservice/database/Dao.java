package com.todolist.taskservice.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.todolist.taskservice.model.task.Task;
import com.todolist.taskservice.model.task.TaskStatus;
import com.todolist.taskservice.router.utils.StrUtils;

@Component
public class Dao {

  private final JdbcTemplate jdbcTemplate;
  private static final Logger logger = LoggerFactory.getLogger(Dao.class);

  @Autowired
  public Dao(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  // Inside the createNewTask method
  public void createNewTask(Task task) {
    String sql = "INSERT INTO task (id, summary, description, status, creator, priority, assignee, creation_date, deadline) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    jdbcTemplate.update(
        connection -> {
          PreparedStatement ps = connection.prepareStatement(sql);
          ps.setString(1, task.getTaskId());
          ps.setString(2, task.getSummary());
          ps.setString(3, task.getDescription());
          ps.setString(4, statusConverter(task.getStatus()));
          ps.setString(5, task.getCreator());
          ps.setString(6, task.getPriority().orElse(null));
          ps.setString(7, task.getAssignee().orElse(null));

          // Convert creationDate from milliseconds to Timestamp
          Timestamp creationDate = new Timestamp(task.getCreationDate());
          ps.setTimestamp(8, creationDate);

          // Set deadline if available
          if (task.getDeadline().isPresent()) {
            ps.setTimestamp(9, new Timestamp(task.getDeadline().get()));
          } else {
            ps.setNull(9, Types.TIMESTAMP);
          }

          return ps;
        });
  }

  private RowMapper<Task> taskRowMapper() {
    return new RowMapper<Task>() {
      @Override
      public Task mapRow(ResultSet rs, int rowNum) throws SQLException {
        logger.info(rs.getString("creation_date"));
        return new Task.Builder()
            .taskId(rs.getString("id"))
            .summary(rs.getString("summary"))
            .description(rs.getString("description"))
            .status(rs.getString(statusConverter(rs.getInt("status"))))
            .creator(rs.getString("creator"))
            .priority(rs.getString("priority"))
            .assignee(rs.getString("assignee"))
            .creationDate(StrUtils.timestampToMillis(rs.getString("creation_date")))
            .deadline(StrUtils.timestampToMillis(rs.getString("deadline")))
            .build();
      }
    };
  }

  private String statusConverter(int status) {
    return switch (status) {
      case 1 -> TaskStatus.TODO.getValue();
      case 2 -> TaskStatus.IN_PROGRESS.getValue();
      case 3 -> TaskStatus.DONE.getValue();
      default -> "NOT_STARTED";
    };
  }

  public List<Task> getAllTasks() {
    try {
      String sql = "SELECT * FROM task";
      return jdbcTemplate.query(sql, taskRowMapper());
    } catch (Exception e) {
      logger.error("Failed to get all tasks", e);
      throw new RuntimeException("Failed to get all tasks", e);
    }
  }
}

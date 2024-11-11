package com.todolist.taskservice.database;

import com.todolist.taskservice.model.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.sql.Types;

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
    String sql =
        "INSERT INTO task (id, summary, description, status, creator, priority, assignee, creation_date, deadline) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    jdbcTemplate.update(
        connection -> {
          PreparedStatement ps = connection.prepareStatement(sql);
          ps.setString(1, task.getTaskId());
          ps.setString(2, task.getSummary());
          ps.setString(3, task.getDescription());
          ps.setString(4, task.getStatus());
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
}

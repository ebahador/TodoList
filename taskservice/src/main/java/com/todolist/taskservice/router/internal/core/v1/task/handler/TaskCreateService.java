package com.todolist.taskservice.router.internal.core.v1.task.handler;

import com.todolist.taskservice.database.Dao;
import com.todolist.taskservice.model.dto.CreateTaskRequestDto;
import com.todolist.taskservice.model.dto.TaskResponseDto;
import com.todolist.taskservice.model.task.Task;
import com.todolist.taskservice.router.utils.ApiResponse;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TaskCreateService {
  private Logger logger = LoggerFactory.getLogger(TaskCreateService.class);
  private Dao dao;

  @Autowired
  public TaskCreateService(Dao dao) {
    this.dao = dao;
  }

  public ResponseEntity<ApiResponse<TaskResponseDto>> createTask(
      CreateTaskRequestDto createTaskRequest) {
    try {
      if (createTaskRequest == null) {
        ApiResponse<TaskResponseDto> apiResponse =
            new ApiResponse<>("Request body is null.", null, null);
        logger.warn("Request body is null!");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
      }

      if (createTaskRequest.getCreator() == null) {
        logger.warn("Create task creator is null!");
        ApiResponse<TaskResponseDto> apiResponse =
            new ApiResponse<>("Create task creator is null.", null, null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
      }

      if (createTaskRequest.getStatus() == null) {
        logger.warn("Create task status is null!");
        ApiResponse<TaskResponseDto> apiResponse =
            new ApiResponse<>("Create task status is null.", null, null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
      }

      if (createTaskRequest.getDescription() == null) {
        logger.warn("Create task description is null!");
        ApiResponse<TaskResponseDto> apiResponse =
            new ApiResponse<>("Create task description is null.", null, null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
      }

      long deadline = 0L;
      Task task =
          new Task.Builder()
              .taskId(UUID.randomUUID().toString())
              .summary(createTaskRequest.getSummary())
              .description(createTaskRequest.getDescription())
              .creator(createTaskRequest.getCreator())
              .assignee(createTaskRequest.getAssignee().orElse(null))
              .deadline(createTaskRequest.getDeadline().orElse(deadline))
              .status(createTaskRequest.getStatus())
              .priority(createTaskRequest.getPriority().orElse(null))
              .creationDate(System.currentTimeMillis())
              .build();
      logger.info("Create task: " + task);

      dao.createNewTask(task);

      // Build task response
      TaskResponseDto response =
          new TaskResponseDto.Builder()
              .id(task.getTaskId())
              .summary(createTaskRequest.getSummary())
              .description(createTaskRequest.getDescription())
              .creator(createTaskRequest.getCreator())
              .assignee(createTaskRequest.getAssignee().orElse(null))
              .deadline(createTaskRequest.getDeadline().orElse(deadline))
              .status(createTaskRequest.getStatus())
              .priority(createTaskRequest.getPriority().orElse(null))
              .creationDate(System.currentTimeMillis())
              .build();
      ApiResponse<TaskResponseDto> apiResponse = new ApiResponse<>(null, 1, response);
      return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    } catch (Exception e) {
      logger.error("Error creating task", e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(new ApiResponse<>(e.getMessage(), null, null));
    }
  }
}

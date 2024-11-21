package com.todolist.taskservice.router.internal.core.v1.task.handler;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.todolist.taskservice.database.Dao;
import com.todolist.taskservice.model.dto.CreateTaskRequestDto;
import com.todolist.taskservice.model.dto.TaskResponseDto;
import com.todolist.taskservice.model.task.Task;
import com.todolist.taskservice.router.utils.ApiResponse;
import com.todolist.taskservice.webconfig.UserServiceValidators;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class TaskCreateService {
  private final Logger logger = LoggerFactory.getLogger(TaskCreateService.class);
  private final Dao dao;
  private final WebClient webClient;
  private final HttpServletRequest inLineRequest;

  @Autowired
  public TaskCreateService(WebClient webClient, Dao dao, HttpServletRequest inLineRequest) {
    this.webClient = webClient;
    this.dao = dao;
    this.inLineRequest = inLineRequest;
  }

  public ResponseEntity<ApiResponse<TaskResponseDto>> createTask(
      CreateTaskRequestDto createTaskRequest) {
    try {
      String authHeader = inLineRequest.getHeader("Authorization");

      if (createTaskRequest == null) {
        ApiResponse<TaskResponseDto> apiResponse = new ApiResponse<>("Request body is null.", null, null);
        logger.warn("Request body is null!");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
      }

      if (!UserServiceValidators.isUserIdValid(
          createTaskRequest.getCreator(), authHeader, webClient, logger)) {
        logger.warn("User id is invalid or not found!");
        ApiResponse<TaskResponseDto> apiResponse = new ApiResponse<>("User id is invalid or not found.", null, null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
      }

      if (createTaskRequest.getCreator() == null) {
        logger.warn("Create task creator is null!");
        ApiResponse<TaskResponseDto> apiResponse = new ApiResponse<>("Create task creator is null.", null, null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
      }

      // Check if Authorization header is missing or doesn't start with "Bearer"
      if (authHeader == null || !authHeader.startsWith("Bearer ")) {
        logger.warn("Missing or invalid Authorization header");
        ApiResponse<TaskResponseDto> apiResponse = new ApiResponse<>("Missing or invalid Authorization header.", null,
            null);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiResponse);
      }

      if (!UserServiceValidators.isTokenValid(
          authHeader.substring(7).trim(), authHeader, webClient, logger)) {
        logger.warn("Token is invalid or not found!");
        ApiResponse<TaskResponseDto> apiResponse = new ApiResponse<>("Token is invalid. Unauthorized user.", null,
            null);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiResponse);
      }

      if (createTaskRequest.getStatus() == null) {
        logger.warn("Create task status is null!");
        ApiResponse<TaskResponseDto> apiResponse = new ApiResponse<>("Create task status is null.", null, null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
      }

      if (createTaskRequest.getDescription() == null) {
        logger.warn("Create task description is null!");
        ApiResponse<TaskResponseDto> apiResponse = new ApiResponse<>("Create task description is null.", null, null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
      }

      long deadline = 0L;
      Task task = new Task.Builder()
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
      logger.info("Create task: {}", task);

      dao.createNewTask(task);

      // Build task response
      TaskResponseDto response = new TaskResponseDto.Builder()
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

  public ResponseEntity<ApiResponse<List<TaskResponseDto>>> getTasks() {
    try {
      logger.info("Getting tasks");
      List<Task> tasks = dao.getAllTasks();

      List<TaskResponseDto> response = tasks.stream()
          .map(
              task -> new TaskResponseDto.Builder()
                  .id(task.getTaskId())
                  .summary(task.getSummary())
                  .description(task.getDescription())
                  .creator(task.getCreator())
                  .assignee(task.getAssignee().orElse(null))
                  .deadline(task.getDeadline().orElse(0L))
                  .status(task.getStatus())
                  .priority(task.getPriority().orElse(null))
                  .creationDate(task.getCreationDate())
                  .build())
          .toList();

      ApiResponse<List<TaskResponseDto>> apiResponse = new ApiResponse<>(null, response.size(), response);
      return ResponseEntity.ok(apiResponse);
    } catch (Exception e) {
      logger.error("Error getting tasks", e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(new ApiResponse<>(e.getMessage(), null, null));
    }
  }

  public ResponseEntity<ApiResponse<TaskResponseDto>> getTaskById(String id) {

    throw new UnsupportedOperationException("Unimplemented method 'getTaskById'");
  }
}

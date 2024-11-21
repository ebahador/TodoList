package com.todolist.taskservice.router.internal.core.v1.task;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todolist.taskservice.model.dto.CreateTaskRequestDto;
import com.todolist.taskservice.model.dto.TaskResponseDto;
import com.todolist.taskservice.router.internal.core.v1.task.handler.TaskCreateService;
import com.todolist.taskservice.router.utils.ApiResponse;
import com.todolist.taskservice.router.utils.ApiUtils;

@RestController
@RequestMapping(ApiUtils.V1_PATH)
public class TaskController {
  private static Logger logger;
  private final TaskCreateService taskCreateService;

  @Autowired
  public TaskController(TaskCreateService taskCreateService) {
    logger = LoggerFactory.getLogger(TaskController.class);
    this.taskCreateService = taskCreateService;
  }

  @PostMapping("/task")
  public ResponseEntity<ApiResponse<TaskResponseDto>> createTask(
      @RequestBody CreateTaskRequestDto createTaskRequest) {
    logger.info("Creating task: {}", createTaskRequest.toString());
    return taskCreateService.createTask(createTaskRequest);
  }

  @GetMapping("/task")
  public ResponseEntity<ApiResponse<List<TaskResponseDto>>> getTasks() {
    logger.info("Get all task");
    return taskCreateService.getTasks();
  }

  // @GetMapping("/tasks/{id}")
  // public ResponseEntity<ApiResponse<TaskResponseDto>> getTaskById(@PathVariable
  // String id) {
  // logger.info("Get task by id: {}", id);
  // return taskCreateService.getTasksById(id);
  // }
}

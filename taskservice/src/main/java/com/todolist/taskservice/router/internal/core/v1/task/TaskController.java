package com.todolist.taskservice.router.internal.core.v1.task;

import com.todolist.taskservice.model.dto.CreateTaskRequestDto;
import com.todolist.taskservice.model.dto.TaskResponseDto;
import com.todolist.taskservice.router.internal.core.v1.task.handler.TaskCreateService;
import com.todolist.taskservice.router.utils.ApiResponse;
import com.todolist.taskservice.router.utils.ApiUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}

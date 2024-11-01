package router.internal.core.v1.task.controller;

import model.dto.CreateTaskDto;
import model.dto.TaskResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import router.internal.core.v1.task.handler.TaskCreateService;
import router.utils.ApiResponse;
import router.utils.ApiUtils;

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
      @RequestBody CreateTaskDto createTaskRequest) {
    logger.info("Creating task: {}", createTaskRequest);
    return taskCreateService.createTask(createTaskRequest);
  }
}

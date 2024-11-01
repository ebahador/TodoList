package router.internal.core.v1.task.handler;

import database.Dao;
import model.dto.CreateTaskDto;
import model.dto.TaskResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import router.utils.ApiResponse;

import java.util.logging.Logger;

@Service
public class TaskCreateService {
  private Logger logger;
  private Dao dao;

  @Autowired
  public TaskCreateService(Dao dao) {
    this.dao = dao;
  }

  public ResponseEntity<ApiResponse<TaskResponseDto>> createTask(CreateTaskDto createTaskRequest) {

  }
}

package com.todolist.userservice.route.internal.core.v1.user;

import com.todolist.userservice.model.user.CreateUserRequest;
import com.todolist.userservice.model.user.CreateUserResponse;
import com.todolist.userservice.route.ApiResponse;
import com.todolist.userservice.route.internal.core.v1.user.handlers.UserService;
import com.todolist.userservice.route.utils.ApiUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiUtils.V1_PATH)
public class UserController {
  private final UserService userService;
  private static Logger logger;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
    logger = LoggerFactory.getLogger(UserController.class);
  }

  @PostMapping("/users")
  public ResponseEntity<ApiResponse<CreateUserResponse>> createUser(
      @RequestBody CreateUserRequest userRequest) {
    logger.info("Creating new user: {}", userRequest.toString());
    return userService.createUser(userRequest);
  }
}

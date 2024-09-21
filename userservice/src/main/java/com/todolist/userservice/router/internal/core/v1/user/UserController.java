package com.todolist.userservice.router.internal.core.v1.user;

import com.todolist.userservice.model.user.CreateUserRequest;
import com.todolist.userservice.model.user.UserResponse;
import com.todolist.userservice.router.ApiResponse;
import com.todolist.userservice.router.internal.core.v1.user.handlers.UserCreateService;
import com.todolist.userservice.router.internal.core.v1.user.handlers.UserInfoService;
import com.todolist.userservice.router.utils.ApiUtils;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiUtils.V1_PATH)
public class UserController {
  private final UserCreateService userCreateService;
  private final UserInfoService userInfoService;
  private static Logger logger;

  @Autowired
  public UserController(UserCreateService userCreateService, UserInfoService userInfoService) {
    this.userCreateService = userCreateService;
    this.userInfoService = userInfoService;
    logger = LoggerFactory.getLogger(UserController.class);
  }

  @PostMapping("/users")
  public ResponseEntity<ApiResponse<UserResponse>> createUser(
      @RequestBody CreateUserRequest userRequest) {
    logger.info("Creating new user: {}", userRequest.toString());
    return userCreateService.createUser(userRequest);
  }

  @GetMapping("/users")
  public ResponseEntity<ApiResponse<List<UserResponse>>> getAllUsers() {
    logger.info("Retrieving all users");
    return userInfoService.getAllUsers();
  }

  @GetMapping("/users/{id}")
  public ResponseEntity<ApiResponse<UserResponse>> getSingleUserInfo(@PathVariable String id) {
    logger.info("Retrieving user by id: {}", id);
    return userInfoService.getSingleUserById(id);
  }
}

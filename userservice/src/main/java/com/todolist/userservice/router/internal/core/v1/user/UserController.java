package com.todolist.userservice.router.internal.core.v1.user;

import com.todolist.userservice.model.user.dto.CreateUserRequestDto;
import com.todolist.userservice.model.user.dto.UpdateUserRequestDto;
import com.todolist.userservice.model.user.dto.UserResponseDto;
import com.todolist.userservice.router.ApiResponse;
import com.todolist.userservice.router.internal.core.v1.user.handlers.UserCreateService;
import com.todolist.userservice.router.internal.core.v1.user.handlers.UserInfoService;
import com.todolist.userservice.router.internal.core.v1.user.handlers.UserUpdateService;
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
  private final UserUpdateService userUpdateService;

  @Autowired
  public UserController(
      UserCreateService userCreateService,
      UserInfoService userInfoService,
      UserUpdateService userUpdateService) {
    this.userCreateService = userCreateService;
    this.userInfoService = userInfoService;
    logger = LoggerFactory.getLogger(UserController.class);
    this.userUpdateService = userUpdateService;
  }

  @PostMapping("/users")
  public ResponseEntity<ApiResponse<UserResponseDto>> createUser(
      @RequestBody CreateUserRequestDto userRequest) {
    logger.info("Creating new user: {}", userRequest.toString());
    return userCreateService.createUser(userRequest);
  }

  @GetMapping("/users")
  public ResponseEntity<ApiResponse<List<UserResponseDto>>> getAllUsers() {
    logger.info("Retrieving all users");
    return userInfoService.getAllUsers();
  }

  @GetMapping("/users/{id}")
  public ResponseEntity<ApiResponse<UserResponseDto>> getSingleUserInfo(@PathVariable String id) {
    logger.info("Retrieving user by id: {}", id);
    return userInfoService.getSingleUserById(id);
  }

  @PatchMapping("/users/{id}")
  public ResponseEntity<ApiResponse<UserResponseDto>> updateUserInfo(
      @PathVariable String id, @RequestBody UpdateUserRequestDto userRequest) {
    logger.info("Updating user: {}", userRequest.toString());
    return userUpdateService.updateUserById(id, userRequest);
  }
}

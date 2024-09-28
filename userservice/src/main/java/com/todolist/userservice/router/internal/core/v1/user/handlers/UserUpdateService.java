package com.todolist.userservice.router.internal.core.v1.user.handlers;

import com.todolist.userservice.database.Dao;
import com.todolist.userservice.model.user.User;
import com.todolist.userservice.model.user.dto.UpdateUserRequest;
import com.todolist.userservice.model.user.dto.UserResponse;
import com.todolist.userservice.router.ApiResponse;
import com.todolist.userservice.router.utils.StrUtils;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserUpdateService {

  private Dao dao;
  Logger logger = LoggerFactory.getLogger(UserUpdateService.class);

  @Autowired
  public UserUpdateService(Dao dao) {
    this.dao = dao;
  }

  public ResponseEntity<ApiResponse<UserResponse>> updateUserById(
      String id, @NotNull UpdateUserRequest request) {
    UserResponse partialResponse;
    try {
      if (!dao.userExists(id)) {
        logger.error("User with id {} does not exist", id);
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(new ApiResponse<>("User not found", 0, null));
      }
      if (request.getFullname() == null || request.getFullname().isEmpty()) {
        logger.error("One of the parameters are null or empty");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(
                new ApiResponse<>(
                    "None of the parameters in the request cannot be empty", 0, null));
      }

      if (request.getFullname().length() < 3) {
        logger.error("Full name length should be at least 3 characters");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(new ApiResponse<>("Full name length should be at least 3 characters", 0, null));
      }

      if (!StrUtils.validateEmailAddress(request.getEmail())) {
        partialResponse = new UserResponse.Builder().email(request.getEmail()).build();
        ApiResponse<UserResponse> apiResponse =
            new ApiResponse<>("Email address format is invalid", 0, partialResponse);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
      }

      dao.updateUser(id, request);
      logger.info("User info with id {} successfully updated", id);

      User user = dao.getUserById(id);

      UserResponse response =
          new UserResponse.Builder()
              .id(user.getId())
              .fullname(user.getFullname())
              .email(user.getFullname())
              .lastLogin(user.getLastLogin())
              .build();
      ApiResponse<UserResponse> apiResponse = new ApiResponse<>(null, 1, response);
      return ResponseEntity.ok(apiResponse);
    } catch (NullPointerException e) {
      logger.error("Failed to update user with id {} because of Missing Parameter", id, e);
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(new ApiResponse<>("Missing Parameter", null, null));
    } catch (Exception e) {
      logger.error("Failed to update user with id {}", id, e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(new ApiResponse<>(e.getMessage(), null, null));
    }
  }
}

package com.todolist.userservice.route.internal.core.v1.user.handlers;

import com.todolist.userservice.database.Dao;
import com.todolist.userservice.model.user.CreateUserRequest;
import com.todolist.userservice.model.user.CreateUserResponse;
import com.todolist.userservice.model.user.User;
import com.todolist.userservice.route.ApiResponse;
import com.todolist.userservice.route.utils.StrUtils;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final Dao dao;
  private static final Logger logger = LoggerFactory.getLogger(UserService.class);

  @Autowired
  public UserService(Dao dao) {
    this.dao = dao;
  }

  public ResponseEntity<ApiResponse<CreateUserResponse>> createUser(CreateUserRequest userRequest) {
    try {
      CreateUserResponse partialResponse;
      // Check if email or fullname is null or empty
      if (userRequest.getEmail() == null || userRequest.getEmail().isEmpty()) {
        ApiResponse<CreateUserResponse> apiResponse =
            new ApiResponse<>("Email address is null", null);
        logger.warn("Email address is empty");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
      }

      if (userRequest.getFullname() == null || userRequest.getFullname().isEmpty()) {
        ApiResponse<CreateUserResponse> apiResponse = new ApiResponse<>("Fullname is null", null);
        logger.warn("Fullname is null");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
      }

      if (userRequest.getFullname().length() < 3) {
        ApiResponse<CreateUserResponse> apiResponse =
            new ApiResponse<>("Fullname should be more than 3 characters!", null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
      }

      // Check if email already exists
      if (dao.isEmailAddressExists(userRequest.getEmail())) {
        ApiResponse<CreateUserResponse> apiResponse =
            new ApiResponse<>("Email address already exists", null);
        logger.warn("Email address already exists!");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
      }

      if (!StrUtils.validateEmailAddress(userRequest.getEmail())) {
        partialResponse = new CreateUserResponse.Builder().email(userRequest.getEmail()).build();
        ApiResponse<CreateUserResponse> apiResponse =
            new ApiResponse<>("Email address format is invalid", partialResponse);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
      }

      User user =
          new User.Builder()
              .id(UUID.randomUUID().toString())
              .fullname(userRequest.getFullname())
              .email(userRequest.getEmail())
              .lastLogin(System.currentTimeMillis())
              .build();

      logger.info(user.toString());
      dao.addUserToDb(user);

      // Build the response
      CreateUserResponse response =
          new CreateUserResponse.Builder()
              .id(user.getId())
              .fullname(user.getFullname())
              .email(user.getEmail())
              .lastLogin(user.getLastLogin())
              .build();

      ApiResponse<CreateUserResponse> apiResponse =
          new ApiResponse<>("User Created Successfully", response);
      return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    } catch (Exception e) {
      logger.error("Error while creating user", e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(new ApiResponse<>(e.getMessage(), null));
    }
  }
}

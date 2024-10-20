package com.todolist.userservice.router.internal.core.v1.user.handlers;

import com.todolist.userservice.database.Dao;
import com.todolist.userservice.model.user.User;
import com.todolist.userservice.model.user.dto.CreateUserRequestDto;
import com.todolist.userservice.model.user.dto.UserResponseDto;
import com.todolist.userservice.router.ApiResponse;
import com.todolist.userservice.router.utils.StrUtils;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserCreateService {

  private final Dao dao;
  private static final Logger logger = LoggerFactory.getLogger(UserCreateService.class);

  @Autowired
  public UserCreateService(Dao dao) {
    this.dao = dao;
  }

  public ResponseEntity<ApiResponse<UserResponseDto>> createUser(CreateUserRequestDto userRequest) {
    try {
      UserResponseDto partialResponse;
      // Check if email or fullname is null or empty
      if (userRequest.getEmail() == null || userRequest.getEmail().isEmpty()) {
        ApiResponse<UserResponseDto> apiResponse =
            new ApiResponse<>("Email address is null", null, null);
        logger.warn("Email address is empty");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
      }

      if (userRequest.getFullname() == null || userRequest.getFullname().isEmpty()) {
        ApiResponse<UserResponseDto> apiResponse =
            new ApiResponse<>("Fullname is null", null, null);
        logger.warn("Fullname is null");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
      }

      if (userRequest.getFullname().length() < 3) {
        ApiResponse<UserResponseDto> apiResponse =
            new ApiResponse<>("Fullname should be more than 3 characters!", null, null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
      }

      // Check if email already exists
      if (dao.emailAddressExists(userRequest.getEmail())) {
        ApiResponse<UserResponseDto> apiResponse =
            new ApiResponse<>("Email address already exists", null, null);
        logger.warn("Email address already exists!");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
      }

      if (!StrUtils.validateEmailAddress(userRequest.getEmail())) {
        partialResponse = new UserResponseDto.Builder().email(userRequest.getEmail()).build();
        ApiResponse<UserResponseDto> apiResponse =
            new ApiResponse<>("Email address format is invalid", null, partialResponse);
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
      UserResponseDto response =
          new UserResponseDto.Builder()
              .id(user.getId())
              .fullname(user.getFullname())
              .email(user.getEmail())
              .lastLogin(user.getLastLogin())
              .build();

      ApiResponse<UserResponseDto> apiResponse = new ApiResponse<>(null, 1, response);
      return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    } catch (Exception e) {
      logger.error("Error while creating user", e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(new ApiResponse<>(e.getMessage(), null, null));
    }
  }
}

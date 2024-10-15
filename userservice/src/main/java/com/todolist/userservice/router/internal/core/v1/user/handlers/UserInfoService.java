package com.todolist.userservice.router.internal.core.v1.user.handlers;

import com.todolist.userservice.database.Dao;
import com.todolist.userservice.model.user.User;
import com.todolist.userservice.model.user.dto.UserResponseDto;
import com.todolist.userservice.router.ApiResponse;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {
  private Dao dao;
  private Logger logger = LoggerFactory.getLogger(UserInfoService.class);

  @Autowired
  public UserInfoService(Dao dao) {
    this.dao = dao;
  }

  public ResponseEntity<ApiResponse<List<UserResponseDto>>> getAllUsers() {
    try {
      List<User> allUsersList = dao.getUsers();
      logger.debug("allUsersString: {}", allUsersList);

      List<UserResponseDto> userResponsDtos =
          allUsersList.stream()
              .map(
                  user ->
                      new UserResponseDto.Builder()
                          .id(user.getId())
                          .fullname(user.getFullname())
                          .email(user.getEmail())
                          .lastLogin(user.getLastLogin())
                          .build())
              .toList();

      ApiResponse<List<UserResponseDto>> apiResponse =
          new ApiResponse<>(null, userResponsDtos.size(), userResponsDtos);
      return ResponseEntity.ok(apiResponse);
    } catch (Exception e) {
      logger.error("Error while fetching all users", e);
      return ResponseEntity.internalServerError().build();
    }
  }

  public ResponseEntity<ApiResponse<UserResponseDto>> getSingleUserById(String id) {
    try {
      if (!dao.userExists(id)) {
        logger.warn("User not found");
        ApiResponse<UserResponseDto> apiResponse =
            new ApiResponse<>("User does not exist.", 0, null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
      } else {
        User singleUserInfo = dao.getUserById(id);
        logger.debug("singleUserInfo: {}", singleUserInfo);

        UserResponseDto userResponseDto =
            new UserResponseDto.Builder()
                .id(singleUserInfo.getId())
                .fullname(singleUserInfo.getFullname())
                .email(singleUserInfo.getEmail())
                .lastLogin(singleUserInfo.getLastLogin())
                .build();

        ApiResponse<UserResponseDto> apiResponse = new ApiResponse<>(null, 1, userResponseDto);
        return ResponseEntity.ok(apiResponse);
      }
    } catch (Exception e) {
      logger.error("Error while fetching user by id", e);
      return ResponseEntity.internalServerError().build();
    }
  }
}

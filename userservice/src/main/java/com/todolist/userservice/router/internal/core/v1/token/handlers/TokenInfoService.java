package com.todolist.userservice.router.internal.core.v1.token.handlers;

import com.todolist.userservice.database.Dao;
import com.todolist.userservice.model.token.Token;
import com.todolist.userservice.model.token.dto.ApiTokenResponseDto;
import com.todolist.userservice.model.token.dto.CreateApiTokenRequestDto;
import com.todolist.userservice.router.ApiResponse;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TokenInfoService {
  private final Dao dao;
  private Logger logger;

  @Autowired
  public TokenInfoService(Dao dao) {
    this.dao = dao;
    logger = LoggerFactory.getLogger(TokenInfoService.class);
  }

  public ResponseEntity<ApiResponse<ApiTokenResponseDto>> createNewToken(
      CreateApiTokenRequestDto requestDto) {
    try {
      if (requestDto.getUserId() == null || requestDto.getUserId().isEmpty()) {
        logger.info("User id is null or empty: {}", requestDto.getUserId());
        ApiResponse<ApiTokenResponseDto> apiResponse =
            new ApiResponse<>("userId is null or empty.", 0, null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
      }
      if (!dao.userExists(requestDto.getUserId())) {
        logger.info("User does not exist");
        ApiResponse<ApiTokenResponseDto> apiResponse =
            new ApiResponse<>("User does not exist", 0, null);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
      }

      long expiryDateMillis =
          LocalDateTime.now()
              .plusYears(1)
              .atZone(ZoneId.systemDefault())
              .toInstant()
              .toEpochMilli();
      logger.info("Creating new token...");
      Token token =
          new Token.Builder()
              .tokenId(UUID.randomUUID().toString())
              .active(true)
              .creationDate(System.currentTimeMillis())
              .userId(requestDto.getUserId())
              .expiryDate(expiryDateMillis)
              .token(generateSecureToken())
              .build();
      dao.createApiToken(token);
      ApiTokenResponseDto response =
          new ApiTokenResponseDto.Builder()
              .tokenId(token.getTokenId())
              .creationDate(token.getCreationDate())
              .expiryDate(token.getExpiryDate())
              .isActive(token.isActive())
              .userId(requestDto.getUserId())
              .token(token.getToken())
              .build();

      ApiResponse<ApiTokenResponseDto> apiResponse = new ApiResponse<>(null, 1, response);
      return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private String generateSecureToken() {
    SecureRandom secureRandom = new SecureRandom();
    byte[] randomBytes = new byte[24];
    secureRandom.nextBytes(randomBytes);
    return Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes);
  }
}

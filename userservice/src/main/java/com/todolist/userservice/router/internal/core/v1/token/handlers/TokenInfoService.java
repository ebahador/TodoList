package com.todolist.userservice.router.internal.core.v1.token.handlers;

import com.todolist.userservice.database.Dao;
import com.todolist.userservice.model.token.dto.ApiTokenResponseDto;
import com.todolist.userservice.model.token.dto.CreateApiTokenRequestDto;
import com.todolist.userservice.router.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenInfoService {
  private Dao dao;
  private Logger logger;

  @Autowired
  public TokenInfoService(Dao dao) {
    this.dao = dao;
    this.logger = LoggerFactory.getLogger(TokenInfoService.class);
  }

  public static ApiResponse<ApiTokenResponseDto> createNewToken(
      CreateApiTokenRequestDto requestDto) {
    try {

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}

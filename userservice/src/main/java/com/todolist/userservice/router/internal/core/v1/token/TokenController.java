package com.todolist.userservice.router.internal.core.v1.token;

import com.todolist.userservice.database.Dao;
import com.todolist.userservice.model.token.dto.ApiTokenResponseDto;
import com.todolist.userservice.model.token.dto.CreateApiTokenRequestDto;
import com.todolist.userservice.router.ApiResponse;
import com.todolist.userservice.router.internal.core.v1.token.handlers.TokenInfoService;
import com.todolist.userservice.router.utils.ApiUtils;
import com.todolist.userservice.router.utils.StrUtils;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiUtils.V1_PATH)
public class TokenController {
  private static Logger logger;
  private final Dao dao;
  private final TokenInfoService tokenInfoService;

  @Autowired
  public TokenController(TokenInfoService tokenInfoService, Dao dao) {
    this.tokenInfoService = tokenInfoService;
    logger = LoggerFactory.getLogger(TokenController.class);
    this.dao = dao;
  }

  @PostMapping("/token")
  public ResponseEntity<ApiResponse<ApiTokenResponseDto>> createToken(
      @RequestBody CreateApiTokenRequestDto requestDto) {
    logger.info("Creating new API token: {}", requestDto);
    return tokenInfoService.createNewToken(requestDto);
  }

  @RequestMapping(value = "/token/{token}/validate", method = RequestMethod.HEAD)
  public ResponseEntity<Void> validateUser(@PathVariable String token) {
    logger.info("Validating API Token: {}", token);
    if (dao.isTokenValid(token)) {
      String maskedToken = StrUtils.maskToken(token);
      logger.info("API Token {} is valid", maskedToken);
      return ResponseEntity.ok().build();
    }
    String maskedToken = StrUtils.maskToken(token);
    logger.info("API Token {} is not valid", maskedToken);
    return ResponseEntity.notFound().build();
  }
}

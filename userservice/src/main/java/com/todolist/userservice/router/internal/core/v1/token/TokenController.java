package com.todolist.userservice.router.internal.core.v1.token;

import com.todolist.userservice.model.token.dto.ApiTokenResponseDto;
import com.todolist.userservice.model.token.dto.CreateApiTokenRequestDto;
import com.todolist.userservice.router.ApiResponse;
import com.todolist.userservice.router.internal.core.v1.token.handlers.TokenInfoService;
import com.todolist.userservice.router.utils.ApiUtils;
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
public class TokenController {
  private static Logger logger;
  private final TokenInfoService tokenInfoService;

  @Autowired
  public TokenController(TokenInfoService tokenInfoService) {
    this.tokenInfoService = tokenInfoService;
    logger = LoggerFactory.getLogger(TokenController.class);
  }

  @PostMapping("/token")
  public ResponseEntity<ApiResponse<ApiTokenResponseDto>> createToken(
      @RequestBody CreateApiTokenRequestDto requestDto) {
    logger.info("Creating new API token: {}", requestDto);
    return tokenInfoService.createNewToken(requestDto);
  }
}

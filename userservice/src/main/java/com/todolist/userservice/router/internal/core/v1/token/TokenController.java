package com.todolist.userservice.router.internal.core.v1.token;

import com.todolist.userservice.model.token.dto.ApiTokenResponseDto;
import com.todolist.userservice.model.token.dto.CreateApiTokenRequestDto;
import com.todolist.userservice.router.ApiResponse;
import com.todolist.userservice.router.internal.core.v1.token.handlers.TokenGenerateService;
import com.todolist.userservice.router.internal.core.v1.token.handlers.TokenInfoService;
import com.todolist.userservice.router.utils.ApiUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiUtils.V1_PATH)
public class TokenController {
  private static Logger logger;
  private final TokenGenerateService tokenGenerateService;
  private final TokenInfoService tokenInfoService;

  @Autowired
  public TokenController(
      TokenGenerateService tokenGenerateService, TokenInfoService tokenInfoService) {
    this.tokenGenerateService = tokenGenerateService;
    this.tokenInfoService = tokenInfoService;
    logger = LoggerFactory.getLogger(TokenController.class);
  }

  @PostMapping("/token")
  public ApiResponse<ApiTokenResponseDto> createToken(
      @RequestBody CreateApiTokenRequestDto requestDto) {
      logger.info("Creating new API token");
      return TokenInfoService.createNewToken(requestDto);
  }
}

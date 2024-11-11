package com.todolist.taskservice.webconfig;

import com.todolist.taskservice.router.utils.ApiUtils;
import org.slf4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class UserServiceValidators {
  @Bean
  public WebClient webClient() {
    return WebClient.builder().baseUrl("http://127.0.0.1:8081").build();
  }

  public static boolean isUserIdValid(
      String userId, String auth, WebClient webClient, Logger logger) {
    try {
      return Boolean.TRUE.equals(
          webClient
              .head()
              .uri(ApiUtils.V1_PATH + "/users/{userId}/validate", userId)
              .header("Authorization", auth)
              .retrieve()
              .toBodilessEntity()
              .map(response -> response.getStatusCode().is2xxSuccessful())
              .defaultIfEmpty(false)
              .block()); // This makes the call synchronous
    } catch (Exception e) {
      logger.error("Failed to validate userId: {}", userId, e);
      return false;
    }
  }

  public static boolean isTokenValid(
      String token, String auth, WebClient webClient, Logger logger) {
    try {
      return Boolean.TRUE.equals(
          webClient
              .head()
              .uri(ApiUtils.V1_PATH + "/token/{token}/validate", token)
              .header("Authorization", auth)
              .retrieve()
              .toBodilessEntity()
              .map(response -> response.getStatusCode().is2xxSuccessful())
              .defaultIfEmpty(false)
              .block()); // This makes the call synchronous
    } catch (Exception e) {
      logger.error("Failed to validate token: {}", token, e);
      return false;
    }
  }
}

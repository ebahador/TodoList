package com.todolist.userservice.model.token.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.jetbrains.annotations.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiTokenResponseDto {
  private String userId;
  private String tokenId;
  private String token;
  private long expiryDate;
  private long creationDate;
  private boolean isActive;

  private ApiTokenResponseDto() {}

  private ApiTokenResponseDto(@NotNull Builder builder) {
    this.creationDate = builder.creationDate;
    this.userId = builder.userId;
    this.tokenId = builder.tokenId;
    this.expiryDate = builder.expiryDate;
    this.token = builder.token;
    this.isActive = builder.isActive;
  }

  public String getUserId() {
    return userId;
  }

  public String getTokenId() {
    return tokenId;
  }

  public String getToken() {
    return token;
  }

  public long getExpiryDate() {
    return expiryDate;
  }

  public long getCreationDate() {
    return creationDate;
  }

  public boolean isActive() {
    return isActive;
  }

  public static class Builder {
    private String userId;
    private String tokenId;
    private String token;
    private long expiryDate;
    private long creationDate;
    private boolean isActive;

    public Builder() {}

    public Builder(@NotNull ApiTokenResponseDto apiTokenResponseDto) {
      this.userId = apiTokenResponseDto.userId;
      this.tokenId = apiTokenResponseDto.tokenId;
      this.token = apiTokenResponseDto.token;
      this.expiryDate = apiTokenResponseDto.expiryDate;
      this.creationDate = apiTokenResponseDto.creationDate;
      this.isActive = apiTokenResponseDto.isActive;
    }

    public Builder userId(String val) {
      this.userId = val;
      return this;
    }

    public Builder tokenId(String val) {
      this.tokenId = val;
      return this;
    }

    public Builder token(String val) {
      this.token = val;
      return this;
    }

    public Builder expiryDate(long val) {
      this.expiryDate = val;
      return this;
    }

    public Builder creationDate(long val) {
      this.creationDate = val;
      return this;
    }

    public Builder isActive(boolean val) {
      this.isActive = val;
      return this;
    }

    public ApiTokenResponseDto build() {
      return new ApiTokenResponseDto(this);
    }
  }
}

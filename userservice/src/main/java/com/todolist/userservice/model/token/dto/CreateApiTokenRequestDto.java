package com.todolist.userservice.model.token.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.jetbrains.annotations.NotNull;

@JsonDeserialize(builder = CreateApiTokenRequestDto.class)
public class CreateApiTokenRequestDto {
  private String userId;

  private CreateApiTokenRequestDto(@NotNull Builder builder) {
    this.userId = builder.userId;
  }

  private CreateApiTokenRequestDto() {}

  public String getUserId() {
    return userId;
  }

  public static class Builder {
    private String userId;

    public Builder() {}

    public Builder(@NotNull CreateApiTokenRequestDto createApiTokenRequestDto) {
      this.userId = createApiTokenRequestDto.userId;
    }

    public Builder userId(String val) {
      this.userId = val;
      return this;
    }

    public CreateApiTokenRequestDto build() {
      return new CreateApiTokenRequestDto(this);
    }
  }
}

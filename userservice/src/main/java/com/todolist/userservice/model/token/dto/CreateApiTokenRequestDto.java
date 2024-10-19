package com.todolist.userservice.model.token.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import org.jetbrains.annotations.NotNull;

@JsonDeserialize(builder = CreateApiTokenRequestDto.Builder.class)
public class CreateApiTokenRequestDto {
  private final String userId;

  private CreateApiTokenRequestDto(@NotNull Builder builder) {
    this.userId = builder.userId;
  }

  public String getUserId() {
    return this.userId;
  }

  @JsonPOJOBuilder(withPrefix = "")
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

  @Override
  public String toString() {
    return "CreateApiTokenRequestDto{" + "userId='" + userId + '\'' + '}';
  }
}

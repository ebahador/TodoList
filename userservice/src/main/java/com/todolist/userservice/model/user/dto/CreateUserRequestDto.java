package com.todolist.userservice.model.user.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import org.jetbrains.annotations.NotNull;

@JsonDeserialize(builder = CreateUserRequestDto.Builder.class)
public class CreateUserRequestDto {
  private final String id;
  private final String fullname;
  private final String email;
  private final long lastLogin;

  private CreateUserRequestDto(@NotNull Builder builder) {
    this.id = builder.id;
    this.fullname = builder.fullname;
    this.email = builder.email;
    this.lastLogin = builder.lastLogin;
  }

  // Getters
  public String getId() {
    return this.id;
  }

  public String getFullname() {
    return this.fullname;
  }

  public String getEmail() {
    return this.email;
  }

  public long getLastLogin() {
    return this.lastLogin;
  }

  @JsonPOJOBuilder(withPrefix = "")
  public static class Builder {
    private String id;
    private String fullname;
    private String email;
    private long lastLogin;

    public CreateUserRequestDto build() {
      return new CreateUserRequestDto(this);
    }

    public Builder() {}

    public Builder(@NotNull CreateUserRequestDto createUserRequestDto) {
      this.id = createUserRequestDto.id;
      this.fullname = createUserRequestDto.fullname;
      this.email = createUserRequestDto.email;
      this.lastLogin = createUserRequestDto.lastLogin;
    }

    public Builder id(String val) {
      this.id = val;
      return this;
    }

    public Builder fullname(String val) {
      this.fullname = val;
      return this;
    }

    public Builder email(String val) {
      this.email = val;
      return this;
    }

    public Builder lastLogin(long val) {
      this.lastLogin = val;
      return this;
    }
  }

  @Override
  public String toString() {
    return "CreateUserRequest{"
        + "id='"
        + id
        + '\''
        + ", fullname='"
        + fullname
        + '\''
        + ", email='"
        + email
        + '\''
        + ", lastLogin="
        + lastLogin
        + '}';
  }
}

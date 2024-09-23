package com.todolist.userservice.model.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.jetbrains.annotations.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {
  private final String id;
  private final String fullname;
  private final String email;
  private final long lastLogin;

  private UserResponse(@NotNull Builder builder) {
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

  public static class Builder {
    private String id;
    private String fullname;
    private String email;
    private long lastLogin;

    public UserResponse build() {
      return new UserResponse(this);
    }

    public Builder() {}

    public Builder(@NotNull UserResponse userResponse) {
      this.id = userResponse.id;
      this.fullname = userResponse.fullname;
      this.email = userResponse.email;
      this.lastLogin = userResponse.lastLogin;
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
    return "CreateUserResponse{"
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

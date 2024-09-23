package com.todolist.userservice.model.user;

import org.jetbrains.annotations.NotNull;

public class User {
  private final String id;
  private final String fullname;
  private final String email;
  private final long lastLogin;

  private User(@NotNull Builder builder) {
    this.id = builder.id;
    this.fullname = builder.fullname;
    this.email = builder.email;
    this.lastLogin = builder.lastLogin;
  }

  // Getters for the fields
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

    public User build() {
      return new User(this);
    }

    public Builder() {}

    public Builder(@NotNull User user) {
      this.id = user.id;
      this.fullname = user.fullname;
      this.email = user.email;
      this.lastLogin = user.lastLogin;
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
    return "Builder{"
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

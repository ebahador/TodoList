package com.todolist.userservice.model.user.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import org.jetbrains.annotations.NotNull;

@JsonDeserialize(builder = UpdateUserRequest.Builder.class)
public class UpdateUserRequest {
  private String fullname;
  private String email;

  private UpdateUserRequest() {}

  private UpdateUserRequest(Builder builder) {
    this.fullname = builder.fullname;
    this.email = builder.email;
  }

  public String getFullname() {
    return fullname;
  }

  public String getEmail() {
    return email;
  }

  @JsonPOJOBuilder(withPrefix = "")
  public static class Builder {
    private String fullname;
    private String email;

    public Builder() {}

    public Builder(@NotNull UpdateUserRequest updateUserRequest) {
      fullname = updateUserRequest.fullname;
      email = updateUserRequest.email;
    }

    public UpdateUserRequest build() {
      return new UpdateUserRequest(this);
    }

    public Builder fullname(String val) {
      fullname = val;
      return this;
    }

    public Builder email(String val) {
      email = val;
      return this;
    }
  }

  @Override
  public String toString() {
    return "UpdateUserRequest{" + "fullname='" + fullname + '\'' + ", email='" + email + '\'' + '}';
  }
}

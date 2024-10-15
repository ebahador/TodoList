package com.todolist.userservice.model.token;

import org.jetbrains.annotations.NotNull;

public class Token {
  private String userId;
  private String tokenId;
  private String token;
  private long expiryDate;
  private long creationDate;
  private boolean isActive;

  private Token() {}

  private Token(@NotNull Builder builder) {
    this.userId = builder.userId;
    this.tokenId = builder.tokenId;
    this.token = builder.token;
    this.expiryDate = builder.expiryDate;
    this.isActive = builder.isActive;
    this.creationDate = builder.creationDate;
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

    public Builder(@NotNull Token token) {
      this.userId = token.userId;
      this.tokenId = token.tokenId;
      this.token = token.token;
      this.expiryDate = token.expiryDate;
      this.creationDate = token.creationDate;
      this.isActive = token.isActive;
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

    public Builder active(boolean val) {
      this.isActive = val;
      return this;
    }

    public Token build() {
      return new Token(this);
    }
  }

  @Override
  public String toString() {
    return "Token{"
        + "userId='"
        + userId
        + '\''
        + ", tokenId='"
        + tokenId
        + '\''
        + ", token='"
        + token
        + '\''
        + ", expiryDate="
        + expiryDate
        + ", creationDate="
        + creationDate
        + ", isActive="
        + isActive
        + '}';
  }
}

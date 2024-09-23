package com.todolist.userservice.model.token;

public class Token {
  private String userId;
  private String tokenId;
  private String token;
  private long expiryDate;
  private long creationDate;
  private boolean isActive;

  public Token(
      String userId,
      String tokenId,
      String token,
      long expiryDate,
      long creationDate,
      boolean isActive) {
    this.userId = userId;
    this.tokenId = tokenId;
    this.token = token;
    this.expiryDate = expiryDate;
    this.creationDate = creationDate;
    this.isActive = isActive;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getTokenId() {
    return tokenId;
  }

  public void setTokenId(String tokenId) {
    this.tokenId = tokenId;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public long getExpiryDate() {
    return expiryDate;
  }

  public void setExpiryDate(long expiryDate) {
    this.expiryDate = expiryDate;
  }

  public long getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(long creationDate) {
    this.creationDate = creationDate;
  }

  public boolean isActive() {
    return isActive;
  }

  public void setActive(boolean active) {
    isActive = active;
  }
}

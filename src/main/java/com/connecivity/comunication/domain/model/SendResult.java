package com.connecivity.comunication.domain.model;

public record SendResult(boolean success, String providerId, String message, long timestamp) {
  public static SendResult success(String providerId) {
    return new SendResult(true, providerId, "OK", System.currentTimeMillis());
  }

  public static SendResult failure(String providerId, String reason) {
    return new SendResult(false, providerId, reason, System.currentTimeMillis());
  }
}
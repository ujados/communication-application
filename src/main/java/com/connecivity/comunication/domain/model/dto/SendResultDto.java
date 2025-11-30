package com.connecivity.comunication.domain.model.dto;

public record SendResultDto(boolean success, String providerId, String message, long timestamp) {
  public static SendResultDto success(String providerId) {
    return new SendResultDto(true, providerId, "OK", System.currentTimeMillis());
  }

  public static SendResultDto failure(String providerId, String reason) {
    return new SendResultDto(false, providerId, reason, System.currentTimeMillis());
  }
}
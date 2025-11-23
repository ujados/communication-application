package com.connecivity.comunication.domain.model;


import lombok.Builder;

import java.util.Map;

@Builder
public record Message(String id, Channel channel, Recipient recipient, String subject, String body,
                      Map<String, String> metadata) {
}

package com.connecivity.comunication.domain.model.dto;

import java.util.Map;

public record MessageRequest(String id, Channel channel, RecipientDto recipient, String subject, String body,
                             Map<String, String> metadata) {
}

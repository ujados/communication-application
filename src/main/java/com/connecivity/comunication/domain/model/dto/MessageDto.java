package com.connecivity.comunication.domain.model.dto;


import lombok.Builder;

import java.util.Map;

@Builder
public record MessageDto(String id, Channel channel, RecipientDto recipient, String subject, String body,
                         Map<String, String> metadata) {
}

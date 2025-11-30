package com.connecivity.comunication.domain.port;

import com.connecivity.comunication.domain.model.dto.MessageDto;
import com.connecivity.comunication.domain.model.dto.SendResultDto;

import java.util.concurrent.CompletionStage;

/**
 * Abstraction of a message delivery channel.
 * Each implementation knows how to send a specific message (SMTP, SMS, WhatsApp, etc.).
 * Responsible only for the sending infrastructure, without business logic.
 */
@FunctionalInterface
public interface ChannelSender {
  // Method implemented by subclass and uses when not want resilence
  // CompletionStage is new version of old Future
  CompletionStage<SendResultDto> send(MessageDto message);
}
package com.connecivity.comunication.application.services;

import com.connecivity.comunication.domain.model.dto.MessageDto;
import com.connecivity.comunication.domain.model.dto.SendResultDto;

import java.util.concurrent.CompletionStage;

/**
 * Business service responsible for deciding which messages to send, to whom, and through which channel.
 * Uses ChannelSenders to send messages, without knowing the details of each channel.
 * Responsible for the business logic, validations, and sending flow.
 */
public interface MessagingService {
  CompletionStage<SendResultDto> dispatch(MessageDto message);
  void save(MessageDto message);
  void delete(MessageDto message);
}

package com.connecivity.comunication.domain;

import com.connecivity.comunication.domain.model.Message;
import com.connecivity.comunication.domain.model.SendResult;

import java.util.concurrent.CompletionStage;

/**
 * Business service responsible for deciding which messages to send, to whom, and through which channel.
 * Uses ChannelSenders to send messages, without knowing the details of each channel.
 * Responsible for the business logic, validations, and sending flow.
 */
@FunctionalInterface
public interface MessagingService {
  CompletionStage<SendResult> dispatch(Message message);
}

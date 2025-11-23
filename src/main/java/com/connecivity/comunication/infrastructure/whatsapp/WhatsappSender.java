package com.connecivity.comunication.infrastructure.whatsapp;

import com.connecivity.comunication.domain.model.Message;
import com.connecivity.comunication.domain.model.SendResult;
import com.connecivity.comunication.domain.port.ChannelSender;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletionStage;

@Component("whatsappSender")
public class WhatsappSender implements ChannelSender {
  @Override
  public CompletionStage<SendResult> send(Message message) {
    throw new RuntimeException("not implemented jet");
  }
}
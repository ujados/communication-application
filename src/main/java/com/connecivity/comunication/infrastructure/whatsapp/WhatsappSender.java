package com.connecivity.comunication.infrastructure.whatsapp;

import com.connecivity.comunication.domain.model.dto.MessageDto;
import com.connecivity.comunication.domain.model.dto.SendResultDto;
import com.connecivity.comunication.domain.port.ChannelSender;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletionStage;

@Component("whatsappSender")
public class WhatsappSender implements ChannelSender {
  @Override
  public CompletionStage<SendResultDto> send(MessageDto message) {
    throw new RuntimeException("not implemented jet");
  }
}
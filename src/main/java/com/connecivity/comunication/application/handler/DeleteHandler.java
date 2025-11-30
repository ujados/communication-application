package com.connecivity.comunication.application.handler;

import com.connecivity.comunication.application.services.MessagingService;
import com.connecivity.comunication.domain.model.dto.MessageDto;
import com.connecivity.comunication.domain.model.dto.SendResultDto;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletionStage;

@Component
public class DeleteHandler {
  private final MessagingService messagingService;

  public DeleteHandler(MessagingService messagingService) {
    this.messagingService = messagingService;
  }

  public CompletionStage<SendResultDto> handle(MessageDto message){
    messagingService.delete(message);
    return messagingService.dispatch(message);
  }

}

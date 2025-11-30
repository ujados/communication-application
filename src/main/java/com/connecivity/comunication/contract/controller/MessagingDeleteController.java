package com.connecivity.comunication.contract.controller;

import com.connecivity.comunication.application.handler.DeleteHandler;
import com.connecivity.comunication.application.handler.SaveHandler;
import com.connecivity.comunication.domain.model.dto.MessageDto;
import com.connecivity.comunication.domain.model.dto.MessageRequest;
import com.connecivity.comunication.domain.model.dto.SendResultDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletionStage;

@RestController
@RequestMapping("/api/messages")
public class MessagingDeleteController {

  private final DeleteHandler handler;

  public MessagingDeleteController(DeleteHandler handler) {
    this.handler = handler;
  }

  @DeleteMapping
  public CompletionStage<ResponseEntity<SendResultDto>> delete(@RequestBody MessageRequest message) {
    var operationBody = message.body().concat(" - deleted");
    var operationMetadata = message.metadata();
    operationMetadata.put("operation", "delete");

    var messageDto = MessageDto.builder().body(operationBody).id(message.id()).channel(message.channel()).metadata(operationMetadata)
      .subject(message.subject()).recipient(message.recipient()).build();

    return handler.handle(messageDto)
      .thenApply(ResponseEntity::ok);
  }
}

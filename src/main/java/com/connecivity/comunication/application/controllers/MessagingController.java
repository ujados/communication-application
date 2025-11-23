package com.connecivity.comunication.application.controllers;

import com.connecivity.comunication.domain.MessagingService;
import com.connecivity.comunication.domain.model.Message;
import com.connecivity.comunication.domain.model.SendResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletionStage;

@RestController
@RequestMapping("/api/messages")
public class MessagingController {

  private final MessagingService messagingService;

  public MessagingController(MessagingService messagingService) {
    this.messagingService = messagingService;
  }

  @PostMapping
  public CompletionStage<ResponseEntity<SendResult>> send(@RequestBody Message message) {
    return messagingService.dispatch(message)
      .thenApply(ResponseEntity::ok);
  }
}
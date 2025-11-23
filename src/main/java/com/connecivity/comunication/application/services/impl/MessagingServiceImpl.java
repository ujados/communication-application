package com.connecivity.comunication.application.services.impl;

import com.connecivity.comunication.domain.MessagingService;
import com.connecivity.comunication.domain.model.Channel;
import com.connecivity.comunication.domain.model.Message;
import com.connecivity.comunication.domain.model.SendResult;
import com.connecivity.comunication.domain.port.ChannelSender;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@Service
public class MessagingServiceImpl implements MessagingService {

  // This constant are the referent to the implementations channels in the package com.connectivity.messaging.channel
  private static final String EMAIL_SENDER_COMPONENT = "emailSender";
  private static final String SMS_SENDER_COMPONENT = "smsSender";
  private static final String WHATSAPP_SENDER_COMPONENT = "whatsappSender";

  private final Map<String, ChannelSender> senders;

  public MessagingServiceImpl(Map<String, ChannelSender> senders) {
    this.senders = Map.copyOf(senders);
  }

  @Override
  public CompletionStage<SendResult> dispatch(Message message) {
    // Decided channel with pattern matching, can be use strategy patter or other that can evaluate in runtime
    var channelName = switch (message.channel()) {
      case Channel.Email _ -> EMAIL_SENDER_COMPONENT;
      case Channel.Sms _ -> SMS_SENDER_COMPONENT;
      case Channel.Whatsapp _ -> WHATSAPP_SENDER_COMPONENT;
    };

    var channelSender = senders.get(channelName);
    if (channelSender == null) {
      return CompletableFuture.failedStage(new IllegalArgumentException("No sender for " + channelName));
    }

    // We delegate: pure function (ideally the sender is a purely transformative function)
    return channelSender.send(message);
  }

}

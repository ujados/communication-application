package com.connecivity.comunication.infrastructure.sms;

import com.connecivity.comunication.domain.model.Message;
import com.connecivity.comunication.domain.model.SendResult;
import com.connecivity.comunication.domain.port.ChannelSender;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static com.twilio.rest.api.v2010.account.Message.creator;

@Slf4j
@Component("smsSender")
public class SmsSender implements ChannelSender {

  @Value("${sms.phone-number}")
  private String fromNumber;

  @Override
  public CompletionStage<SendResult> send(Message message) {
    return CompletableFuture.supplyAsync(() -> {
      try {
        MessageCreator creator = creator(new PhoneNumber(message.recipient()
                                                           .address()), new PhoneNumber(fromNumber), message.body());
        var twilioResponse = creator.create();
        log.info("SMS sent to {} with SID={}", message.recipient()
          .address(), twilioResponse.getSid());
        return SendResult.success("twilio-sms");
      } catch (Exception e) {
        return SendResult.failure("twilio-sms", e.getMessage());
      }
    });

  }
}
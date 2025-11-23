package com.connecivity.comunication.infrastructure.email;

import com.connecivity.comunication.domain.model.Message;
import com.connecivity.comunication.domain.model.SendResult;
import com.connecivity.comunication.domain.port.ChannelSender;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@Slf4j
@Component("rawEmailSender")
public class EmailSender implements ChannelSender {

  private final JavaMailSender javaMailSender;
  private final TemplateEngine templateEngine;

  public EmailSender(JavaMailSender javaMailSender, TemplateEngine templateEngine) {
    this.javaMailSender = javaMailSender;
    this.templateEngine = templateEngine;

  }

  @Override
  public CompletionStage<SendResult> send(Message message) {
    return CompletableFuture.supplyAsync(() -> {
      try {
        var mime = javaMailSender.createMimeMessage();
        var helper = new MimeMessageHelper(mime, true, "UTF-8");

        helper.setTo(message.recipient()
                       .address());
        helper.setSubject(message.subject());

        var ctx = new Context();
        ctx.setVariable("message", message.body());
        helper.setText(templateEngine.process("email", ctx), true);

        javaMailSender.send(mime);

        log.info("Email successfully sent to {} with subject '{}'", message.recipient()
          .address(), message.subject());

        return SendResult.success("smtp");

      } catch (MessagingException e) {
        log.error("Email sending FAILED: {}", e.getMessage());
        return SendResult.failure("smtp", e.getMessage());
      }
    });
  }
}

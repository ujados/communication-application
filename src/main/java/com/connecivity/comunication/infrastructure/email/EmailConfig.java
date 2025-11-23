package com.connecivity.comunication.infrastructure.email;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class EmailConfig {

  @Value("${email.username}")
  private String email;

  @Value("${email.password}")
  private String password;

  @Value("${email.host}")
  private String host;

  @Value("${email.port}")
  private String port;

  private Properties getEmailProperties() {
    Properties properties = new Properties();
    properties.put("mail.smtp.auth", "true");
    properties.put("mail.smtp.starttls.enable", "true");
    properties.put("mail.smtp.host", host);
    properties.put("mail.smtp.port", port);

    return properties;
  }

  @Bean
  public JavaMailSender javaMailSender() {
    JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
    javaMailSender.setJavaMailProperties(getEmailProperties());
    javaMailSender.setUsername(email);
    javaMailSender.setPassword(password);

    return javaMailSender;
  }
}

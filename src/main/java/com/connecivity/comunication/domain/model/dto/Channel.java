package com.connecivity.comunication.domain.model.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes(
  {@JsonSubTypes.Type(value = Channel.Email.class, name = "email"), @JsonSubTypes.Type(value = Channel.Sms.class,
                                                                                       name = "sms"), @JsonSubTypes.Type(
    value = Channel.Whatsapp.class, name = "whatsapp")})
// sealed interface for enum channels
public sealed interface Channel permits Channel.Email, Channel.Sms, Channel.Whatsapp {
  record Email() implements Channel {
  }

  record Sms() implements Channel {
  }

  record Whatsapp() implements Channel {
  }
}
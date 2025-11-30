package com.connecivity.comunication.domain.repository;

import com.connecivity.comunication.domain.model.dto.MessageDto;

public interface MessagingMockServiceRepository {
  void save (MessageDto message);
  void delete(MessageDto message);
}

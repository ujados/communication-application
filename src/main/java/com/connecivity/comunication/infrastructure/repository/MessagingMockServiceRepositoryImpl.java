package com.connecivity.comunication.infrastructure.repository;

import com.connecivity.comunication.domain.model.dto.MessageDto;
import com.connecivity.comunication.domain.model.entity.Message;
import com.connecivity.comunication.domain.repository.MessagingMockServiceRepository;

public class MessagingMockServiceRepositoryImpl implements MessagingMockServiceRepository {

  private final MessagingMockRepository messagingMockRepository;

  public MessagingMockServiceRepositoryImpl(MessagingMockRepository messagingMockRepository) {
    this.messagingMockRepository = messagingMockRepository;
  }
  @Override
  public void save(MessageDto message) {
    messagingMockRepository.save(new Message (message.id()));
  }

  @Override
  public void delete(MessageDto message) {
    messagingMockRepository.delete(new Message (message.id()));
  }
}

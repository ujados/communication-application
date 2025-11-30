package com.connecivity.comunication.infrastructure.repository;


import com.connecivity.comunication.domain.model.entity.Message;

//esto extendería de JPARepository
public interface MessagingMockRepository {
  default void save(Message message) {
    IO.println("Mock save message with id: " + message.getId());
  }
  default void delete(Message message) {}
}

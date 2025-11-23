package com.connecivity.comunication.infrastructure.shared;

import com.connecivity.comunication.domain.model.Message;
import com.connecivity.comunication.domain.model.SendResult;
import com.connecivity.comunication.domain.port.ChannelSender;
import io.github.resilience4j.decorators.Decorators;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.retry.Retry;

import java.util.concurrent.CompletionStage;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.function.Supplier;

public class ResilientChannelSender implements ChannelSender {

  private final ChannelSender delegate;
  private final Retry retry;
  private final CircuitBreaker circuitBreaker;
  private final ScheduledExecutorService scheduler;

  public ResilientChannelSender(ChannelSender delegate, Retry retry, CircuitBreaker circuitBreaker) {
    this.delegate = delegate;
    this.retry = retry;
    this.circuitBreaker = circuitBreaker;
    this.scheduler = Executors.newSingleThreadScheduledExecutor();
  }

  @Override
  public CompletionStage<SendResult> send(Message message) {
    //Supplier that returns the CompletionStage of the delegate
    Supplier<CompletionStage<SendResult>> supplier = () -> delegate.send(message);

    // `Decorators.ofCompletionStage(...)` is the standard way in Resilience4j to compose policies on `CompletionStage`.
    Supplier<CompletionStage<SendResult>> decorated = Decorators.ofCompletionStage(supplier)
      .withCircuitBreaker(circuitBreaker)
      .withRetry(retry, scheduler)
      .decorate();

    // execute and map any final exception to SendResult.failure(...)
    // The final `.exceptionally(...)` turns any exceptions into `SendResult.failure(...)` to keep the API consistent
    // (avoid having to catch exceptions in the `MessagingService`).
    return decorated.get()
      .exceptionally(throwable -> SendResult.failure(circuitBreaker.getName(), unwrapMessage(throwable)));
  }

  private static String unwrapMessage(Throwable t) {
    // unwrap CompletionException or ExecutionException messages
    Throwable root = t;
    while (root.getCause() != null)
      root = root.getCause();
    return root.getMessage() == null ? root.toString() : root.getMessage();
  }
}

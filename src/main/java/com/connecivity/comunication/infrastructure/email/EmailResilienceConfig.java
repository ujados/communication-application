package com.connecivity.comunication.infrastructure.email;

import com.connecivity.comunication.infrastructure.shared.ResilientChannelSender;
import com.connecivity.comunication.domain.port.ChannelSender;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class EmailResilienceConfig {

  @Bean("emailSender")
  public ChannelSender emailSenderDecorated(ChannelSender rawEmailSender, Retry emailRetry,
                                            CircuitBreaker emailCircuitBreaker) {
    return new ResilientChannelSender(rawEmailSender, emailRetry, emailCircuitBreaker);
  }

  @Bean
  public Retry emailRetry() {
    RetryConfig cfg = RetryConfig.custom()
      .maxAttempts(3)
      .waitDuration(Duration.ofSeconds(2))
      .build();
    return Retry.of("email-retry", cfg);
  }

  @Bean
  public CircuitBreaker emailCircuitBreaker() {
    CircuitBreakerConfig cfg = CircuitBreakerConfig.custom()
      .failureRateThreshold(50)
      .minimumNumberOfCalls(5)
      .waitDurationInOpenState(Duration.ofSeconds(30))
      .slidingWindowSize(10)
      .build();
    return CircuitBreaker.of("email-cb", cfg);
  }

}
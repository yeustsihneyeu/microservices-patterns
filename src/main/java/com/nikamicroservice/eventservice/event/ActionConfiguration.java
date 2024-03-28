package com.nikamicroservice.eventservice.event;

import io.eventuate.sync.AggregateRepository;
import io.eventuate.sync.EventuateAggregateStoreCrud;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ActionConfiguration {

  @Bean
  public AggregateRepository<Action, ActionCommand> aggregateRepository(EventuateAggregateStoreCrud aggregateStore) {
    return new AggregateRepository<>(Action.class, aggregateStore);
  }
}

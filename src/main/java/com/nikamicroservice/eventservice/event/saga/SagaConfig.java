package com.nikamicroservice.eventservice.event.saga;

import io.eventuate.tram.sagas.participant.SagaCommandDispatcher;
import io.eventuate.tram.sagas.participant.SagaCommandDispatcherFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SagaConfig {

    @Bean
    public SagaCommandDispatcher actionCommandDispatcher(
            ActionCommandHandler actionCommandHandler, SagaCommandDispatcherFactory factory) {
        return factory.make("actionCommandDispatcher", actionCommandHandler.commandHandlers());
    }
}

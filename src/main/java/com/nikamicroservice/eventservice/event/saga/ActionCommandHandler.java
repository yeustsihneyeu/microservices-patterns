package com.nikamicroservice.eventservice.event.saga;

import com.nikamicroservice.eventservice.event.ActionCommandService;
import com.nikamicroservice.eventservice.event.command.ActionCommands.ActionApproveCommand;
import com.nikamicroservice.eventservice.event.command.ActionCommands.ActionRejectCommand;
import io.eventuate.tram.commands.consumer.CommandHandlers;
import io.eventuate.tram.commands.consumer.CommandMessage;
import io.eventuate.tram.messaging.common.Message;
import io.eventuate.tram.sagas.participant.SagaCommandHandlersBuilder;
import lombok.RequiredArgsConstructor;
import microservice.SagaCommand;
import org.springframework.stereotype.Component;

import static io.eventuate.tram.commands.consumer.CommandHandlerReplyBuilder.withFailure;
import static io.eventuate.tram.commands.consumer.CommandHandlerReplyBuilder.withSuccess;
import static microservice.SagaCommandUtils.mapTo;


@Component
@RequiredArgsConstructor
public class ActionCommandHandler {

    private final ActionCommandService actionService;

    public CommandHandlers commandHandlers() {
        return SagaCommandHandlersBuilder
                .fromChannel("actionChannel")
                .onMessage(SagaCommand.class, this::handle)
                .build();
    }

    private Message handle(CommandMessage<SagaCommand> commandMessage) {
        final var sagaCommand = commandMessage.getCommand();
        final var data = sagaCommand.data();
        try {
            switch (sagaCommand.commandType()) {
                case REJECT_ACTION -> actionService.reject(mapTo(data, ActionRejectCommand.class));
                case APPROVE_ACTION -> actionService.approve(mapTo(data, ActionApproveCommand.class));
                default -> throw new RuntimeException("unknown saga command type");
            }
            return withSuccess();
        } catch (Exception e) {
            return withFailure();
        }
    }
}

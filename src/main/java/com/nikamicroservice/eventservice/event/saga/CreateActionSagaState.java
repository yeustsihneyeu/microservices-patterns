package com.nikamicroservice.eventservice.event.saga;

import com.nikamicroservice.eventservice.event.command.ActionCommands.ActionApproveCommand;
import com.nikamicroservice.eventservice.event.command.ActionCommands.ActionRejectCommand;
import com.nikamicroservice.eventservice.event.command.TicketCommands.CreateTicket;
import com.nikamicroservice.eventservice.event.command.TicketCommands.DeleteTicket;
import com.nikamicroservice.eventservice.event.web.ActionRequest;
import io.eventuate.tram.commands.consumer.CommandWithDestination;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import microservice.SagaCommand;
import microservice.SagaCommandType;

import static io.eventuate.tram.commands.consumer.CommandWithDestinationBuilder.send;
import static microservice.SagaCommandType.APPROVE_ACTION;
import static microservice.SagaCommandType.DELETE_TICKET;
import static microservice.SagaCommandType.REJECT_ACTION;

@Data
@Slf4j
public class CreateActionSagaState {

    private String actionId;
    private ActionRequest request;

    public CreateActionSagaState() {
    }

    public CreateActionSagaState(String entityId, ActionRequest request) {
        this.actionId = entityId;
        this.request = request;
    }

    public CommandWithDestination makeCreateTicketCommand() {
        log.info("saga - create CreateTicket command");
        CreateTicket createTicket = new CreateTicket(actionId, request);
        return send(new SagaCommand(SagaCommandType.CREATE_TICKET, createTicket))
                .to("ticketChannel")
                .build();
    }

    public CommandWithDestination rejectAction() {
        log.info("saga - reject Action");
        final var rejectCommand = new ActionRejectCommand(actionId);
        return send(new SagaCommand(REJECT_ACTION, rejectCommand))
                .to("actionChannel")
                .build();
    }

    public CommandWithDestination approveAction() {
        log.info("saga - approve Action");
        final var approveCommand = new ActionApproveCommand(actionId);
        return send(new SagaCommand(APPROVE_ACTION, approveCommand))
                .to("actionChannel")
                .build();
    }

    public CommandWithDestination deleteTicketCommand() {
        log.info("saga - delete Ticket");
        final var deleteTicketCommand = new DeleteTicket(actionId);
        return send(new SagaCommand(DELETE_TICKET, deleteTicketCommand))
                .to("ticketChannel")
                .build();
    }
}

package com.nikamicroservice.eventservice.event.saga;


import io.eventuate.tram.commands.common.Success;
import io.eventuate.tram.sagas.orchestration.SagaDefinition;
import io.eventuate.tram.sagas.simpledsl.SimpleSaga;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CreateActionSaga implements SimpleSaga<CreateActionSagaState> {

    private final SagaDefinition<CreateActionSagaState> sagaDefinition;

    public CreateActionSaga() {
        this.sagaDefinition =
                step()
                        .withCompensation(CreateActionSagaState::rejectAction)
                .step()
                        .invokeParticipant(CreateActionSagaState::makeCreateTicketCommand)
                        .onReply(Success.class, (st, s) -> log.info("CREATE TICKET RESPONSE"))
                        .withCompensation(CreateActionSagaState::deleteTicketCommand)
                        .onReply(Success.class, (st, s) -> log.info("DELETE TICKET RESPONSE"))
                .step()
                        .invokeParticipant(CreateActionSagaState::approveAction)
                .build();
    }

    @Override
    public SagaDefinition<CreateActionSagaState> getSagaDefinition() {
        return sagaDefinition;
    }
}

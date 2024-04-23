package com.nikamicroservice.eventservice.event.eventSourcing;

import com.nikamicroservice.eventservice.event.event.ActionEvents.ActionCreatedEvent;
import com.nikamicroservice.eventservice.event.event.ActionEvents.ActionUpdateEvent;
import com.nikamicroservice.eventservice.event.saga.CreateActionSaga;
import com.nikamicroservice.eventservice.event.saga.CreateActionSagaState;
import com.nikamicroservice.eventservice.event.web.ActionRequest;
import io.eventuate.EventHandlerContext;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;
import io.eventuate.tram.sagas.orchestration.SagaInstanceFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@EventSubscriber(id = "actionServiceEventHandler")
@RequiredArgsConstructor
public class ActionServiceEventHandler {

    private final SagaInstanceFactory sagaInstanceFactory;
    private final CreateActionSaga createActionSaga;


    @EventHandlerMethod
    public void createAction(EventHandlerContext<ActionCreatedEvent> context) {
        ActionCreatedEvent event = context.getEvent();
        String name = event.name();
        log.info("save active view, entityId {}", name);

        CreateActionSagaState createActionSagaState =
                new CreateActionSagaState(context.getEntityId(), new ActionRequest(event.name(), event.progress()));
        sagaInstanceFactory.create(createActionSaga, createActionSagaState);
    }

    @EventHandlerMethod
    public void updateAction(EventHandlerContext<ActionUpdateEvent> context) {
        String entityId = context.getEntityId();
        log.info("update active view, entityId {}", entityId);
        ActionUpdateEvent event = context.getEvent();
    }
}

package com.nikamicroservice.eventservice.event;

import io.eventuate.EventHandlerContext;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@EventSubscriber(id = "actionServiceEventHandler")
@RequiredArgsConstructor
public class ActionServiceEventHandler {

    private final ActionViewRepository actionViewRepository;

    @EventHandlerMethod
    public void createAction(EventHandlerContext<ActionCreatedEvent> context) {
        String entityId = context.getEntityId();
        log.info("save active view, entityId {}", entityId);
        ActionCreatedEvent event = context.getEvent();
        actionViewRepository.save(ActionView.create(entityId, event));
    }

    @EventHandlerMethod
    public void updateAction(EventHandlerContext<ActionUpdateEvent> context) {
        String entityId = context.getEntityId();
        log.info("update active view, entityId {}", entityId);
        ActionUpdateEvent event = context.getEvent();
        actionViewRepository.save(ActionView.create(entityId, event));
    }
}

package com.nikamicroservice.eventservice.event;

public record ActionView(
    String id, String name, Progress progress
) {
    public static ActionView create(String entityId, ActionCreatedEvent event) {
        return new ActionView(entityId, event.name(), event.progress());
    }

    public static ActionView create(String entityId, ActionUpdateEvent event) {
        return new ActionView(entityId, event.name(), event.progress());
    }
}

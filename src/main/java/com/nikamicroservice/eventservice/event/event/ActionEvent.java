package com.nikamicroservice.eventservice.event.event;

import io.eventuate.Event;
import io.eventuate.EventEntity;

@EventEntity(entity = "com.nikamicroservice.eventservice.event.aggregate.Action")
public interface ActionEvent extends Event {

}

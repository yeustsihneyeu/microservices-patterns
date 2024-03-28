package com.nikamicroservice.eventservice.event;

import io.eventuate.Event;
import io.eventuate.EventEntity;

@EventEntity(entity = "com.nikamicroservice.eventservice.event.Action")
public interface ActionEvent extends Event {

}

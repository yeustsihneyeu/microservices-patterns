package com.nikamicroservice.eventservice.event.eventSourcing;

import com.nikamicroservice.eventservice.event.aggregate.Progress;
import io.eventuate.Snapshot;

public record ActionSnapshot(String name, Progress progress) implements Snapshot {

}

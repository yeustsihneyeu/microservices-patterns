package com.nikamicroservice.eventservice.event;

import io.eventuate.Snapshot;

public record ActionSnapshot(String name, Progress progress) implements Snapshot {

}

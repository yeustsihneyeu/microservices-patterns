package com.nikamicroservice.eventservice.event;

public record ActionCreatedEvent(String name, Progress progress) implements ActionEvent {

}

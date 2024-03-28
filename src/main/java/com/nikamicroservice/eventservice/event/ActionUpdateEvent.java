package com.nikamicroservice.eventservice.event;

public record ActionUpdateEvent(String name, Progress progress) implements ActionEvent {

}

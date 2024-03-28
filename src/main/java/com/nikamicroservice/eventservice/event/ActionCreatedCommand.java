package com.nikamicroservice.eventservice.event;

public record ActionCreatedCommand(String name) implements ActionCommand {

}

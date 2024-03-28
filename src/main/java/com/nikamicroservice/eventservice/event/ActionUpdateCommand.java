package com.nikamicroservice.eventservice.event;

public record ActionUpdateCommand(String name, Progress progress) implements ActionCommand {

}

package com.nikamicroservice.eventservice.event.command;

import com.nikamicroservice.eventservice.event.aggregate.Progress;

public class ActionCommands {

    public record ActionCreatedCommand(String name) implements ActionCommand { }

    public record ActionRejectCommand(String actionId) implements ActionCommand { }

    public record ActionUpdateCommand(String name, Progress progress) implements ActionCommand { }

    public record ActionApproveCommand(String actionId) implements ActionCommand { }
}

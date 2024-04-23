package com.nikamicroservice.eventservice.event.event;

import com.nikamicroservice.eventservice.event.aggregate.Action.ActionStatus;
import com.nikamicroservice.eventservice.event.aggregate.Progress;

public class ActionEvents {

    public record ActionCreatedEvent(String name, ActionStatus status, Progress progress) implements ActionEvent { }

    public record ActionRejectEvent(ActionStatus status) implements ActionEvent { }

    public record ActionUpdateEvent(String name, Progress progress) implements ActionEvent { }

    public record ActionApproveEvent(ActionStatus status) implements ActionEvent { }
}

package com.nikamicroservice.eventservice.event.aggregate;

import com.nikamicroservice.eventservice.event.command.ActionCommand;
import com.nikamicroservice.eventservice.event.command.ActionCommands.ActionApproveCommand;
import com.nikamicroservice.eventservice.event.command.ActionCommands.ActionCreatedCommand;
import com.nikamicroservice.eventservice.event.command.ActionCommands.ActionRejectCommand;
import com.nikamicroservice.eventservice.event.command.ActionCommands.ActionUpdateCommand;
import com.nikamicroservice.eventservice.event.event.ActionEvents.ActionApproveEvent;
import com.nikamicroservice.eventservice.event.event.ActionEvents.ActionCreatedEvent;
import com.nikamicroservice.eventservice.event.event.ActionEvents.ActionRejectEvent;
import com.nikamicroservice.eventservice.event.event.ActionEvents.ActionUpdateEvent;
import io.eventuate.Event;
import io.eventuate.EventUtil;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;
import lombok.Getter;

import java.util.List;

@Getter
public class Action extends ReflectiveMutableCommandProcessingAggregate<Action, ActionCommand> {

  private String name;
  private ActionStatus status;
  private Progress progress;

  public void apply(ActionCreatedEvent event) {
    this.name = event.name();
    this.progress = event.progress();
    this.status = event.status();
  }

  public List<Event> process(ActionCreatedCommand cmd) {
    return EventUtil.events(new ActionCreatedEvent(cmd.name(), ActionStatus.PENDING, null));
  }

  public void apply(ActionUpdateEvent event) {
    this.progress = event.progress();
  }

  public List<Event> process(ActionUpdateCommand cmd) {
    return EventUtil.events(new ActionUpdateEvent(cmd.name(), cmd.progress()));
  }

  public void apply(ActionRejectEvent event) {
    this.status = event.status();
  }

  public List<Event> process(ActionRejectCommand cmd) {
    return EventUtil.events(new ActionRejectEvent(ActionStatus.REJECTED));
  }

  public void apply(ActionApproveEvent event) {
    this.status = event.status();
  }

  public List<Event> process(ActionApproveCommand cmd) {
    return EventUtil.events(new ActionApproveEvent(ActionStatus.CREATED));
  }

  public ActionRecord toRecord() {
    return new ActionRecord(this.name, this.progress);
  }

  public record ActionRecord(String name, Progress progress) {

  }

  public enum ActionStatus {
    PENDING, CREATED, REJECTED
  }
}

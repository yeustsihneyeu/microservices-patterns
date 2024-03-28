package com.nikamicroservice.eventservice.event;

import io.eventuate.Event;
import io.eventuate.EventUtil;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;
import java.util.List;
import lombok.Getter;

@Getter
public class Action extends ReflectiveMutableCommandProcessingAggregate<Action, ActionCommand> {

  private String name;
  private Progress progress;

  public void apply(ActionCreatedEvent event) {
    this.name = event.name();
    this.progress = event.progress();
  }

  public List<Event> process(ActionCreatedCommand cmd) {
    return EventUtil.events(new ActionCreatedEvent(cmd.name(), null));
  }

  public void apply(ActionUpdateEvent event) {
    this.progress = event.progress();
  }

  public List<Event> process(ActionUpdateCommand cmd) {
    return EventUtil.events(new ActionUpdateEvent(cmd.name(), cmd.progress()));
  }

  public ActionRecord toRecord() {
    return new ActionRecord(this.name, this.progress);
  }

  public record ActionRecord(String name, Progress progress) {

  }
}

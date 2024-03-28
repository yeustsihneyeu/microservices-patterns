package com.nikamicroservice.eventservice.event;

import io.eventuate.Aggregate;
import io.eventuate.Aggregates;
import io.eventuate.Event;
import io.eventuate.EventWithMetadata;
import io.eventuate.MissingApplyEventMethodStrategy;
import io.eventuate.Snapshot;
import io.eventuate.SnapshotStrategy;
import io.eventuate.common.id.Int128;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class ActionSnapshotStrategy implements SnapshotStrategy {

  @Override
  public Class<?> getAggregateClass() {
    return Action.class;
  }

  @Override
  public Optional<Snapshot> possiblySnapshot(
      Aggregate aggregate,
      Optional<Int128> snapshotVersion,
      List<EventWithMetadata> oldEvents,
      List<Event> newEvents
  ) {
    Action action = (Action) aggregate;
    return Optional.of(new ActionSnapshot(action.getName(), action.getProgress()));
  }

  @Override
  public Action recreateAggregate(
      Class<?> clasz,
      Snapshot snapshot,
      MissingApplyEventMethodStrategy missingApplyEventMethodStrategy
  ) {
    ActionSnapshot actionSnapshot = (ActionSnapshot) snapshot;
    Action aggregate = new Action();
    List<Event> events = aggregate.process(new ActionCreatedCommand(actionSnapshot.name()));
    Aggregates.applyEventsToMutableAggregate(aggregate, events, missingApplyEventMethodStrategy);
    return aggregate;
  }

}

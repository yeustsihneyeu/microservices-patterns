package com.nikamicroservice.eventservice.event;


import com.nikamicroservice.eventservice.event.aggregate.Action;
import com.nikamicroservice.eventservice.event.command.ActionCommand;
import com.nikamicroservice.eventservice.event.command.ActionCommands.ActionApproveCommand;
import com.nikamicroservice.eventservice.event.command.ActionCommands.ActionCreatedCommand;
import com.nikamicroservice.eventservice.event.command.ActionCommands.ActionRejectCommand;
import com.nikamicroservice.eventservice.event.command.ActionCommands.ActionUpdateCommand;
import com.nikamicroservice.eventservice.event.web.ActionRequest;
import com.nikamicroservice.eventservice.event.web.ActionResponse;
import io.eventuate.sync.AggregateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActionCommandService {

  private final AggregateRepository<Action, ActionCommand> actionRepository;

  public void create(ActionRequest request) {
    actionRepository.save(new ActionCreatedCommand(request.name()));
  }

  public ActionResponse getActions(String id) {
    final var entity = actionRepository.find(id);
    final var entityIdAndVersion = entity.getEntityIdAndVersion();
    final var action = entity.getEntity().toRecord();
    return new ActionResponse(entityIdAndVersion.getEntityId(), action.name(), action.progress());
  }

  public void update(String id, ActionRequest request) {
    actionRepository.update(id, new ActionUpdateCommand(request.name(), request.progress()));
  }

  public void reject(ActionRejectCommand rejectCommand) {
    actionRepository.update(rejectCommand.actionId(), rejectCommand);
  }

  public void approve(ActionApproveCommand approveCommand) {
    actionRepository.update(approveCommand.actionId(), approveCommand);
  }
}

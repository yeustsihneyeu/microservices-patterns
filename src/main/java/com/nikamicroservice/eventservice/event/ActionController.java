package com.nikamicroservice.eventservice.event;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/actions")
public class ActionController {

  private final ActionCommandService actionCommandService;
  private final ActionViewService actionViewService;

  @GetMapping
  public List<ActionView> getAllActions() {
    return actionViewService.getAllActions();
  }

  @PostMapping
  public void createAction(@RequestBody ActionRequest request) {
    actionCommandService.create(request);
  }

  @GetMapping("/{id}")
  public ActionResponse getActions(@PathVariable String id) {
    return actionCommandService.getActions(id);
  }

  @PutMapping("/{id}")
  public void updateActions(
      @PathVariable String id,
      @RequestBody ActionRequest request
  ) {
    actionCommandService.update(id, request);
  }
}

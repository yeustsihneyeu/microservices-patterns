package com.nikamicroservice.eventservice.event.web;

import com.nikamicroservice.eventservice.event.ActionCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/actions")
public class ActionController {

  private final ActionCommandService actionCommandService;

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

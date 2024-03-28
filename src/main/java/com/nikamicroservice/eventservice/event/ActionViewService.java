package com.nikamicroservice.eventservice.event;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActionViewService {

    private final ActionViewRepository actionViewRepository;

    public List<ActionView> getAllActions() {
        return actionViewRepository.findAll();
    }
}

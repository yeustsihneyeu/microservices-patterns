package com.nikamicroservice.eventservice.event.web;

import com.nikamicroservice.eventservice.event.aggregate.Progress;

public record ActionResponse(String id, String name, Progress progress) {

}

package com.nikamicroservice.eventservice.event.web;

import com.nikamicroservice.eventservice.event.aggregate.Progress;

public record ActionRequest(String name, Progress progress) {

}

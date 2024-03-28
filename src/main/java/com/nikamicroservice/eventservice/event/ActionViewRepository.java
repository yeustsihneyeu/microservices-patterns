package com.nikamicroservice.eventservice.event;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ActionViewRepository extends MongoRepository<ActionView, String> {

}

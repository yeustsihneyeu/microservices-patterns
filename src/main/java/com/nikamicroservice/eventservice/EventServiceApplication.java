package com.nikamicroservice.eventservice;

import io.eventuate.javaclient.spring.common.crud.EventuateCommonCrudConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.nikamicroservice.eventservice")
@Import(EventuateCommonCrudConfiguration.class)
public class EventServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(EventServiceApplication.class, args);
  }

}

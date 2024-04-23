package com.nikamicroservice.eventservice.event.command;

import com.nikamicroservice.eventservice.event.web.ActionRequest;

public class TicketCommands {

    public record CreateTicket(String actionId, ActionRequest request) {}

    public record DeleteTicket(String actionId) {}
}

package com.epam.reportportal.extension.vsts;

import com.epam.reportportal.extension.bugtracking.ExternalSystemStrategy;
import com.epam.reportportal.extension.bugtracking.InternalTicketAssembler;
import com.epam.ta.reportportal.database.entity.ExternalSystem;
import com.epam.ta.reportportal.ws.model.externalsystem.PostFormField;
import com.epam.ta.reportportal.ws.model.externalsystem.PostTicketRQ;
import com.epam.ta.reportportal.ws.model.externalsystem.Ticket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class VstsStrategy implements ExternalSystemStrategy {

    private static final Logger LOGGER = LoggerFactory.getLogger(VstsStrategy.class);

    private InternalTicketAssembler ticketAssembler;

    VstsStrategy(InternalTicketAssembler ticketAssembler) {
        this.ticketAssembler = ticketAssembler;
    }


    @Override
    public boolean checkConnection(ExternalSystem system) {
        return true;
    }

    @Override
    public Optional<Ticket> getTicket(String id, ExternalSystem system) {
        return Optional.empty();
    }

    @Override
    public Ticket submitTicket(PostTicketRQ ticketRQ, ExternalSystem system) {
        return null;
    }

    @Override
    public List<PostFormField> getTicketFields(String issueType, ExternalSystem system) {
        return null;
    }
}

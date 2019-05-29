package com.epam.reportportal.extension.vsts;

import com.epam.reportportal.extension.bugtracking.ExternalSystemStrategy;
import com.epam.reportportal.extension.bugtracking.InternalTicketAssembler;
import com.epam.ta.reportportal.database.entity.ExternalSystem;
import com.epam.ta.reportportal.exception.ReportPortalException;
import com.epam.ta.reportportal.ws.model.ErrorType;
import com.epam.ta.reportportal.ws.model.externalsystem.PostFormField;
import com.epam.ta.reportportal.ws.model.externalsystem.PostTicketRQ;
import com.epam.ta.reportportal.ws.model.externalsystem.Ticket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.epam.reportportal.extension.vsts.VstsConfig.getConfig;

public class VstsStrategy implements ExternalSystemStrategy {

    private static final Logger LOGGER = LoggerFactory.getLogger(VstsStrategy.class);

    @Autowired
    private VstsService vsts;

    @Override
    public boolean checkConnection(ExternalSystem system) {
        try {
            return vsts.checkConnection(getConfig(system.getUrl(), system.getProject(), system.getAccessKey()));
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Optional<Ticket> getTicket(String id, ExternalSystem system) {
        return vsts.getWorkItem(id, getConfig(system.getUrl(), system.getProject(), system.getAccessKey()));
    }

    @Override
    public Ticket submitTicket(PostTicketRQ ticketRQ, ExternalSystem system) {
        return vsts.createWorkItem(ticketRQ, getConfig(system.getUrl(), system.getProject(), system.getAccessKey()));
    }

    @Override
    public List<PostFormField> getTicketFields(String issueType, ExternalSystem system) {
        return vsts.getWorkItemFields(getConfig(system.getUrl(), system.getProject(), system.getAccessKey()));
    }

    @Override
    public List<String> getIssueTypes(ExternalSystem system) {
        try{
            return vsts.getWorkItemTypes(getConfig(system.getUrl(), system.getProject(), system.getAccessKey()));
        } catch (Exception e){
            throw new ReportPortalException(ErrorType.UNABLE_INTERACT_WITH_EXTRERNAL_SYSTEM, "Check connection settings.");
        }
    }
}

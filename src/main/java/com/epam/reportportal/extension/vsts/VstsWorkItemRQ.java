package com.epam.reportportal.extension.vsts;

import com.epam.ta.reportportal.ws.model.externalsystem.PostTicketRQ;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class VstsWorkItemRQ {
    String uri;
    String project;
    String token;
    String witId;
    String executionId;
    String executionName;
    String executionDescription;

    PostTicketRQ ticketRQ;

    VstsWorkItemRQ(String uri, String project, String token,
                   PostTicketRQ ticketRQ, String executionId,
                   String executionName, String executionDescription) {
        this.project = project;
        this.token = token;
        this.uri = uri;
        this.executionId = executionId;
        this.executionName = executionName;
        this.executionDescription = executionDescription;
        this.ticketRQ = ticketRQ;
    }

    VstsWorkItemRQ(String uri, String project, String token, String witId) {
        this.project = project;
        this.token = token;
        this.uri = uri;
        this.witId = witId;
    }
}

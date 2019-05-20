package com.epam.reportportal.extension.vsts;

import com.epam.reportportal.extension.bugtracking.BugTrackingApp;
import com.epam.reportportal.extension.bugtracking.ExternalSystemStrategy;
import com.epam.reportportal.extension.bugtracking.InternalTicketAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;

public class VstsServiceApp extends BugTrackingApp {

    @Autowired
    private ApplicationContext context;

    @Override
    public ExternalSystemStrategy externalSystemStrategy() {
        return new VstsStrategy(context.getBean(InternalTicketAssembler.class));
    }

    public static void main(String[] args) {
        SpringApplication.run(VstsServiceApp.class, args);
    }

}

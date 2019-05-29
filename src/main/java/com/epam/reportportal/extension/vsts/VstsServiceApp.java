package com.epam.reportportal.extension.vsts;

import com.epam.reportportal.extension.bugtracking.BugTrackingApp;
import com.epam.reportportal.extension.bugtracking.ExternalSystemStrategy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

public class VstsServiceApp extends BugTrackingApp {

    @Value("${vsts.host}")
    private String vstsAgentHost;

    @Value("${vsts.port}")
    private String vstsAgentPort;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.rootUri(vstsAgentHost+":"+vstsAgentPort).build();
    }

    @Override
    public ExternalSystemStrategy externalSystemStrategy() {
        return new VstsStrategy();
    }

    public static void main(String[] args) {
        SpringApplication.run(VstsServiceApp.class, args);
    }

}

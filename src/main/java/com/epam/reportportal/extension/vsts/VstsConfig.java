package com.epam.reportportal.extension.vsts;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class VstsConfig {

    String uri;
    String project;
    String token;

    VstsConfig(String uri, String project, String token) {
        this.project = project;
        this.token = token;
        this.uri = uri;
    }

    private static VstsConfig instance;

    public static VstsConfig getConfig(String url, String project, String token){
        if(instance == null){
            instance = new VstsConfig(url, project, token);
        }
        return instance;
    }
}

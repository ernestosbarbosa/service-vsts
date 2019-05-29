package com.epam.reportportal.extension.vsts;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class VstsWorkItemType {
    String name;
    String referenceName;
    String description;
    VstsWorkItemField[] fields;
}

package com.epam.reportportal.extension.vsts;

import com.epam.ta.reportportal.database.DataStorage;
import com.epam.ta.reportportal.database.dao.TestItemRepository;
import com.epam.ta.reportportal.database.entity.item.TestItem;
import com.epam.ta.reportportal.ws.model.externalsystem.PostFormField;
import com.epam.ta.reportportal.ws.model.externalsystem.PostTicketRQ;
import com.epam.ta.reportportal.ws.model.externalsystem.Ticket;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VstsService {

    enum FieldTypes {
        String("String");

        private	String name;

        FieldTypes(String n) {
            name = n;
        }
    }

    @Autowired
    private DataStorage dataStorage;

    @Autowired
    private TestItemRepository testItemRepository;

    @Autowired
    private RestTemplate template;

    public boolean checkConnection(VstsConfig config) {
        return template.postForEntity("/login", new HttpEntity<>(config), Void.class).getStatusCode().is2xxSuccessful();
    }

    public List<String> getWorkItemTypes(VstsConfig config) {
        String response = template.postForObject("/witTypes", new HttpEntity<>(config), String.class);
        VstsWorkItemType[] types = new Gson().fromJson(response, VstsWorkItemType[].class);
        List<String> issueTypes = new ArrayList<>();
        for (VstsWorkItemType t : types) {
            issueTypes.add(t.name);
        }
        return issueTypes;
    }

    public List<PostFormField> getWorkItemFields(VstsConfig config) {
        String response = template.postForObject("/witTypes", new HttpEntity<>(config), String.class);
        VstsWorkItemType[] types = new Gson().fromJson(response, VstsWorkItemType[].class);
        List<PostFormField> witFields = new ArrayList<>();
        for (VstsWorkItemType t : types) {
            for (VstsWorkItemField f : t.fields) {
                PostFormField formField = new PostFormField();
                formField.setId(f.referenceName);
                formField.setFieldName(f.name);
                formField.setFieldType(FieldTypes.String.name);
                formField.setIsRequired(false);
//                formField.setIsRequired(f.alwaysRequired);
                formField.setValue(new ArrayList<>());
                formField.setDefinedValues(null);
                witFields.add(formField);
            }
        }
        return witFields;
    }

    public Ticket createWorkItem(PostTicketRQ ticketRQ, VstsConfig config) {
        TestItem testItem = testItemRepository.findOne(ticketRQ.getTestItemId());
        String response = template.postForObject("/wit", new HttpEntity<>(new VstsWorkItemRQ(config.uri, config.project, config.token, ticketRQ, testItem.getId(), testItem.getName(), testItem.getItemDescription())), String.class);
        return new Gson().fromJson(response, Ticket.class);
    }

    public Optional<Ticket> getWorkItem(String id, VstsConfig config){
        String response = template.postForObject("/wit/"+id, new HttpEntity<>(new VstsWorkItemRQ(config.uri, config.project, config.token, id)), String.class);
        return Optional.ofNullable(new Gson().fromJson(response, Ticket.class));
    }


}

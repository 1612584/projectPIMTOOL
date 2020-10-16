package vn.elca.training.mapper;

import javafx.util.StringConverter;
import vn.elca.training.model.ProjectStatus;

import java.util.HashMap;
import java.util.Map;

public class StatusConverter extends StringConverter<ProjectStatus> {

    /** Cache of ProjectStatus */
    private Map<String, ProjectStatus> productMap = new HashMap<>();

    @Override
    public String toString(ProjectStatus status) {
        productMap.put(status.getStatus(), status);
        return status.getStatus();
    }

    @Override
    public ProjectStatus fromString(String status) {
        return productMap.get(status);
    }
}

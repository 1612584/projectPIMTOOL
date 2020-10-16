package vn.elca.training.model.enums;

public enum ProjectStatus {
    PLA("Planned"),
    NEW("New"),
    INP("In progress"),
    FIN("Finished");

    private String status;

    private ProjectStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }

}

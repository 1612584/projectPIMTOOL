package vn.elca.training.model;

public class ProjectStatus {
    private String status;
    private String statusCode;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public ProjectStatus() {
    }

    public ProjectStatus(String status, String statusCode) {
        this.status = status;
        this.statusCode = statusCode;
    }
}

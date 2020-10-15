package vn.elca.training.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;

import java.awt.*;
import java.time.LocalDate;

public class Project {

    private SimpleLongProperty id = new SimpleLongProperty();;
    private SimpleStringProperty name = new SimpleStringProperty();
    private SimpleStringProperty customer = new SimpleStringProperty();
    private SimpleLongProperty groupId = new SimpleLongProperty();
    private SimpleLongProperty version = new SimpleLongProperty();
    private SimpleIntegerProperty projectNumber = new SimpleIntegerProperty();
    private SimpleStringProperty status = new SimpleStringProperty();
    private SimpleObjectProperty<LocalDate> startDate = new SimpleObjectProperty<>();
    private SimpleObjectProperty<LocalDate> endDate = new SimpleObjectProperty<>();
    private SimpleStringProperty visaList = new SimpleStringProperty();
    private CheckBox select = new CheckBox();

    public Project() {
    }

    public Project(SimpleLongProperty id, SimpleStringProperty name, SimpleStringProperty customer,
                   SimpleLongProperty groupId, SimpleLongProperty version, SimpleIntegerProperty projectNumber,
                   SimpleStringProperty status, SimpleObjectProperty<LocalDate> startDate,
                   SimpleObjectProperty<LocalDate> endDate) {
        this.id = id;
        this.name = name;
        this.customer = customer;
        this.groupId = groupId;
        this.version = version;
        this.projectNumber = projectNumber;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Project(SimpleLongProperty id, SimpleStringProperty name, SimpleStringProperty customer,
                   SimpleLongProperty groupId, SimpleLongProperty version, SimpleIntegerProperty projectNumber,
                   SimpleStringProperty status) {
        this.id = id;
        this.name = name;
        this.customer = customer;
        this.groupId = groupId;
        this.version = version;
        this.projectNumber = projectNumber;
        this.status = status;
    }

    public Project(long id, String name, String customer, long version, long projectNumber, String status) {
        this.id.set(id);
        this.name.set(name);
        this.version.setValue(version);
        this.projectNumber.setValue(projectNumber);
        this.customer.set(customer);
        this.status.set(status);
    }

    public LocalDate getStartDate() {
        return startDate.get();
    }

    public SimpleObjectProperty<LocalDate> startDateProperty() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate.set(startDate);
    }

    public LocalDate getEndDate() {
        return endDate.get();
    }

    public SimpleObjectProperty<LocalDate> endDateProperty() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate.set(endDate);
    }

    public long getId() {
        return id.get();
    }

    public SimpleLongProperty idProperty() {
        return id;
    }

    public void setId(long id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.setValue(name);
    }

    public String getCustomer() {
        return customer.get();
    }

    public SimpleStringProperty customerProperty() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer.set(customer);
    }

    public long getGroupId() {
        return groupId.get();
    }

    public SimpleLongProperty groupIdProperty() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId.set(groupId);
    }

    public long getVersion() {
        return version.get();
    }

    public SimpleLongProperty versionProperty() {
        return version;
    }

    public void setVersion(long version) {
        this.version.set(version);
    }

    public int getProjectNumber() {
        return projectNumber.get();
    }

    public SimpleIntegerProperty projectNumberProperty() {
        return projectNumber;
    }

    public void setProjectNumber(int projectNumber) {
        this.projectNumber.set(projectNumber);
    }

    public String getStatus() {
        return status.get();
    }

    public SimpleStringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public String getVisaList() {
        return visaList.get();
    }

    public SimpleStringProperty visaListProperty() {
        return visaList;
    }

    public void setVisaList(String visaList) {
        this.visaList.set(visaList);
    }

    public CheckBox getSelect() {
        return select;
    }

    public void setSelect(CheckBox select) {
        this.select = select;
    }
}

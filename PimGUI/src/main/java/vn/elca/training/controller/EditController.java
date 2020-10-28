package vn.elca.training.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.StringConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.elca.protobuf.GrpcGroup;
import vn.elca.protobuf.GrpcGroupList;
import vn.elca.protobuf.GrpcProjectDto;
import vn.elca.protobuf.ResponseUpdate;
import vn.elca.training.mapper.Mapper;
import vn.elca.training.mapper.StatusConverter;
import vn.elca.training.model.Project;
import vn.elca.training.model.ProjectStatus;
import vn.elca.training.service.ProjectServiceClient;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class EditController implements Initializable {

    private static final Logger log = LoggerFactory.getLogger(EditController.class);

    private ResourceBundle bundle;

    private Long projectId;

    private Project project;

    @FXML
    private TextField projectNumberTxt;

    @FXML
    private TextField projectNameTxt;

    @FXML
    private TextField customerTxt;

    @FXML
    private ComboBox groupCbb;

    @FXML
    private TextField membersTxt;

    @FXML
    private ComboBox<ProjectStatus> statusInp;

    private ObservableList<ProjectStatus> statuses;

    private ObservableList<Long> groupIds;

    @FXML
    private DatePicker startDate;

    @FXML
    private DatePicker endDate;

    @FXML
    private Button cancelBtn;

    @FXML
    private Button submitBtn;

    private MainController mainController;

    public EditController() {
        this.projectId = null;
        this.editMode = false;
        this.project = new Project();
    }

    public EditController(Long id) {
        this.projectId = id;
        this.editMode = true;
    }



    public MainController getMainController() {
        return mainController;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    private boolean editMode;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.bundle = resources;
        final List<ProjectStatus> projectStatusList = Arrays.asList(  new ProjectStatus("NEW", bundle.getString("status.NEW")),
                new ProjectStatus("PLA", bundle.getString("status.PLA")),
                new ProjectStatus("INP", bundle.getString("status.INP")),
                new ProjectStatus("FIN", bundle.getString("status.FIN")));
        this.statuses = FXCollections.observableArrayList(
              projectStatusList
        );
        this.statusInp.setConverter(new StatusConverter());
        this.statusInp.setItems(this.statuses);

        // getGroup
        GrpcGroupList grpcGroupList = ProjectServiceClient.getGroupList();
        this.groupIds = FXCollections.observableArrayList(
                grpcGroupList
                        .getGroupsList()
                        .stream()
                        .map(GrpcGroup::getId)
                        .collect(Collectors.toList())
        );

        this.setFormatDatePicker(startDate);
        this.setFormatDatePicker(endDate);

        // set GroupCbb
        this.groupCbb.setItems(this.groupIds);

        this.setValidationForm();

        if (editMode) {
            log.debug("id = " + this.projectId);
            GrpcProjectDto projectDto = ProjectServiceClient.getProjectById(projectId);
            this.project = Mapper.toProject(projectDto, this.bundle);
            this.projectNumberTxt.setText(String.valueOf(this.project.getProjectNumber()));
            this.projectNumberTxt.setEditable(false);
            this.projectNameTxt.setText(this.project.getName());
            this.customerTxt.setText(this.project.getCustomer());
            this.membersTxt.setText(this.project.getVisaList());
//            this.statusInp.setValue(
//                    new ProjectStatus(this.project.getStatus(), bundle.getString("status." + projectDto.getStatus()))
//            );
            this.statusInp.getSelectionModel().select(
                    new ProjectStatus(this.project.getStatus(), bundle.getString("status." + projectDto.getStatus()))
            );
            this.startDate.setValue(this.project.getStartDate());
            this.endDate.setValue(this.project.getEndDate());
            this.groupCbb.setValue(this.project.getGroupId());
        }

        // event on click cancel
        this.cancelBtn.setOnAction(event -> {
            log.debug("click on btn cancel");
        });

        this.submitBtn.setOnAction(event -> {
            // TODO: validate form
            project.setName(projectNameTxt.getText());
            project.setVisaList(membersTxt.getText());
            project.setStartDate(startDate.getValue());
            project.setEndDate(endDate.getValue());
            project.setCustomer(customerTxt.getText());
            project.setVisaList(membersTxt.getText());
            if (statusInp.getSelectionModel().getSelectedItem() != null) {
                String statusStr = statusInp.getSelectionModel().getSelectedItem().getStatus();
//               for ( ProjectStatus status : projectStatusList) {
//                   if (status.getStatusCode().equals(statusStr) || status.getStatus().equals(statusStr) ) {
//                       project.setStatus(status.getStatus());
//                       break;
//                   }
//               }
                project.setStatus(statusStr);
            }
            // update project value
            if (groupCbb.getSelectionModel().getSelectedItem() != null) {
                project.setGroupId((long) groupCbb.getSelectionModel().getSelectedItem());
            }
            if (!editMode) {
                this.project.setProjectNumber(Integer.parseInt(projectNumberTxt.getText()));
            }
            // send update to project
            ResponseUpdate response = null;
            if (!editMode) {
                response = ProjectServiceClient.createProject(project);
            } else {
                response = ProjectServiceClient.updateProject(project);
            }
            ActionEvent actionEvent = event;
            if (response.getSuccess() == true) {
                try {
                    mainController.onUpdateSuccess(true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void setFormatDatePicker(DatePicker datePicker) {
        datePicker.setConverter(new StringConverter<LocalDate>()
        {
            private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE;

            @Override
            public String toString(LocalDate localDate) {
                if(localDate == null)
                    return "";
                return dateTimeFormatter.format(localDate);
            }

            @Override
            public LocalDate fromString(String dateString) {
                if(dateString == null || dateString.trim().isEmpty()) {
                    return null;
                }
                return LocalDate.parse(dateString, dateTimeFormatter);
            }
        });
    }

    private void setValidationForm() {
        projectNumberTxt.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                projectNumberTxt.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }
}

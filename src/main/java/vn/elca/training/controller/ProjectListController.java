package vn.elca.training.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.elca.training.mapper.Mapper;
import vn.elca.training.mapper.StatusConverter;
import vn.elca.training.model.Project;
import vn.elca.training.model.ProjectStatus;
import vn.elca.training.service.ProjectServiceClient;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class ProjectListController {

//    private String status;
//
//    private String name;
//
//    private ResourceBundle bundle;
//
//    private static final Logger log = LoggerFactory.getLogger(ProjectListController.class);
//
//    @FXML
//    private ObservableList<Project> projects = FXCollections.observableArrayList();
//
//    @FXML
//    private ComboBox<String> statusBox = new ComboBox<>();
//
//    @FXML
//    private Button searchBtn;
//
//    @FXML
//    private Button resetBtn;
//
//    @FXML
//    private TextField nameInp;
//
//    @FXML
//    private ComboBox<ProjectStatus> statusInp;
//
//    @FXML
//    private TableView projectTable;
//
//    @FXML
//    private TableColumn<Project, Integer> projectNumberColumn;
//
//    private ObservableList<ProjectStatus> statuses;
//
//    @FXML
//    private TableColumn dateColumn;
//
//    private MainController mainController;
//
//    public ProjectListController() {
//        this.name = "";
//        this.status = "";
//    }
//
//    public MainController getMainController() {
//        return mainController;
//    }
//
//    public void setMainController(MainController mainController) {
//        this.mainController = mainController;
//    }
//
//    public ProjectListController(String name, String status) {
//        this.status = status;
//        this.name = name;
//    }
//
//
//
//    public void onSearchSubmit(ActionEvent actionEvent) {
//        String name = nameInp.getText();
//        String status = "";
//        if (statusInp.getSelectionModel().getSelectedItem() != null) {
//            status = statusInp.getSelectionModel().getSelectedItem().getStatus();
//        }
//        this.fetchData(name, status);
//    }
//
//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//        bundle = resources;
//        statuses = FXCollections.observableArrayList(
//                new ProjectStatus("NEW", bundle.getString("status.NEW")),
//                new ProjectStatus("PLA", bundle.getString("status.PLA")),
//                new ProjectStatus("INP", bundle.getString("status.INP")),
//                new ProjectStatus("FIN", bundle.getString("status.FIN"))
//        );
//        fetchData("", "");
//        this.setBtnEvent();
//        statusInp.setConverter(new StatusConverter());
//        statusInp.setItems(statuses);
//        dateColumn.setCellFactory(column -> new TableCell<String , LocalDate>() {
//            @Override
//            protected void updateItem(LocalDate item, boolean empty) {
//                super.updateItem(item, empty);
//                if(empty) {
//                    setText(null);
//                }
//                else {
//                    setText(item.format(DateTimeFormatter.ISO_LOCAL_DATE));
//                }
//            }
//        });
//
//        projectNumberColumn.setCellFactory(tc -> {
//            TableCell<Project, Integer> cell = new TableCell<Project, Integer>() {
//                @Override
//                protected void updateItem(Integer item, boolean empty) {
//                    super.updateItem(item, empty) ;
//                    setText(empty ? null : item.toString());
//                }
//            };
//            cell.setOnMouseClicked(e -> {
//                if (!cell.isEmpty()) {
//                    Integer id = cell.getItem();
//                    Project project = (Project) cell.getTableRow().getItem();
//                    // do something with id...
//                    log.debug("click on project number = "+ id);
//                    mainController.displayEditProject(project.getId());
////                    EditController editController = new EditController(project.getId());
////                    // set observable item for controller
////                    this.right.getChildren().clear();
////                    FXMLLoader loader = new FXMLLoader(MainController.class.getResource(editFXML), bundle);
////                    loader.setController(editController);
////                    try {
////                        editNode = loader.load();
////                        editController.setMainController(this);
////                        this.right.getChildren().add(editNode);
////                    } catch (IOException ioException) {
////                        ioException.printStackTrace();
////                    }
//                }
//            });
//            return cell;
//        });
//        // set event on button
//    }
//
//    public void onResetSearch(ActionEvent actionEvent) {
//        this.nameInp.clear();
//        this.statusInp.valueProperty().set(null);
//        fetchData("","");
//    }
//
//    public void fetchData(String name, String status) {
//        this.projects.clear();
//        this.projects.addAll(
//                Mapper.toProjectList
//                        (ProjectServiceClient.findByNameAndStatus(name, status), bundle)
//        );
//        this.projectTable.setItems(projects);
//    }
//
//    private void setBtnEvent() {
//        this.searchBtn.setOnAction(event -> {
//            onSearchSubmit(event);
//        });
//        this.resetBtn.setOnAction(event -> {
//            onResetSearch(event);
//        });
//    }
//
////    public Parent load(MainController mainController, URL resource, ResourceBundle bundle) throws IOException {
////        FXMLLoader loader = new FXMLLoader();
////        loader.setController(this);
////        Parent parent = loader.load(resource, bundle);
////        this.
////    }
}

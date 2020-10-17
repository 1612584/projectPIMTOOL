package vn.elca.training.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.elca.training.MainApp;
import vn.elca.training.mapper.Mapper;
import vn.elca.training.mapper.StatusConverter;
import vn.elca.training.model.Project;
import vn.elca.training.model.ProjectStatus;
import vn.elca.training.service.ProjectServiceClient;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private final String projectListFxml = "/fxml/projectList.fxml";


    private ResourceBundle bundle;

    private static final Logger log = LoggerFactory.getLogger(MainController.class);

    @FXML
    private ObservableList<Project> projects = FXCollections.observableArrayList();

    @FXML
    private ComboBox<String> statusBox = new ComboBox<>();

    @FXML
    private Button searchBtn;

    @FXML
    private Button resetBtn;

    @FXML
    private TextField nameInp;

    @FXML
    private ComboBox<ProjectStatus> statusInp;

    @FXML
    private TableView projectTable;

    @FXML
    private TableColumn<Project, Integer> projectNumberColumn;

    private ObservableList<ProjectStatus> statuses;

    @FXML
    private GridPane left;

    @FXML
    private GridPane right;

    @FXML
    private HBox header;

    @FXML
    private TableColumn dateColumn;

    final String editFXML = "/fxml/edit.fxml";

    private Parent projectListNode;

    @FXML
    private VBox projectListVBox;

    @FXML
    private TableColumn deleteColumn;

    private Parent editNode;

    private URL location;

    private String name = "";
    private String status = "";

    public MainController() {

    }

    public void onSearchSubmit(ActionEvent actionEvent) {
        this.name = nameInp.getText();
        this.status = "";
        if (statusInp.getSelectionModel().getSelectedItem() != null) {
            this.status  = statusInp.getSelectionModel().getSelectedItem().getStatus();
        }
        this.fetchData(this.name, this.status);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bundle = resources;
        this.location = location;
        // init right pane
//        FXMLLoader rightLoader = new FXMLLoader();
//        Parent projectListNode = null;
//        ProjectListController projectListController = new ProjectListController();
////        projectListController.initialize(getClass().getResource(projectListFxml), bundle);
//        // set controller
//        projectListController.setMainController(this);
////        projectListNode = projectListController.load(this, MainApp.class.getClass().getResource(projectListFxml), bundle);
//        rightLoader.setController(projectListController);
//        try {
//            projectListNode = rightLoader.load(MainApp.class.getClass().getResource(projectListFxml), bundle);
//            projectListController.initialize(MainApp.class.getClass().getResource(projectListFxml), bundle);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        // get right panel

//        this.right.getChildren().add(projectListNode);

        statuses = FXCollections.observableArrayList(
                new ProjectStatus("NEW", bundle.getString("status.NEW")),
                new ProjectStatus("PLA", bundle.getString("status.PLA")),
                new ProjectStatus("INP", bundle.getString("status.INP")),
                new ProjectStatus("FIN", bundle.getString("status.FIN"))
        );
        statusInp.setConverter(new StatusConverter());
        statusInp.setItems(statuses);
        dateColumn.setCellFactory(column -> new TableCell<String , LocalDate>() {
            @Override
            protected void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                if(empty) {
                    setText(null);
                }
                else {
                    setText(item.format(DateTimeFormatter.ISO_LOCAL_DATE));
                }
            }
        });
        projectNumberColumn.setCellFactory(tc -> {
            TableCell<Project, Integer> cell = new TableCell<Project, Integer>() {
                @Override
                protected void updateItem(Integer item, boolean empty) {
                    super.updateItem(item, empty) ;
                    setText(empty ? null : item.toString());
                }
            };
            cell.setOnMouseClicked(e -> {
                if (!cell.isEmpty()) {
                    Integer id = cell.getItem();
                    Project project = (Project) cell.getTableRow().getItem();
                    // do something with id...
                    log.debug("click on project number = "+ id);
                    displayEditProject(project.getId());
                }
            });
            return cell;
        });
        fetchData(this.name, this.status);

        Hyperlink newLink = (Hyperlink) this.left.lookup("#newLink");

        newLink.setOnAction(event -> {
            this.right.getChildren().clear();
            // load create edit.fxml
            try {
                EditController editController = new EditController();
                FXMLLoader loader = new FXMLLoader(MainController.class.getResource(editFXML), bundle);
                loader.setController(editController);
                editNode = loader.load();
                this.right.getChildren().add(editNode);
                editController.setMainController(this);
            } catch (IOException e) {
                e.printStackTrace();
            }
            // set to right
        });

        Button cancelBtn = (Button) this.right.lookup("#cancelBtn");
        if (cancelBtn != null) {
            cancelBtn.setOnAction( event -> {
                log.debug("cancel on main controller");
            });
        }

        //  initialize delete btn
        Callback<TableColumn<Project, Button>, TableCell<Project, Button>> cellFactory
                = //
                new Callback<TableColumn<Project, Button>, TableCell<Project, Button>>() {
                    @Override
                    public TableCell call(final TableColumn<Project, Button> param) {
                        final TableCell<Project, Button> cell = new TableCell<Project, Button>() {

                            final Button btn = new Button("Delete");

                            @Override
                            public void updateItem(Button item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    btn.setOnAction(event -> {
                                        Project person = getTableView().getItems().get(getIndex());
                                        System.out.println(person.getProjectNumber() + " = project number click");
                                    });
                                    setGraphic(btn);
                                    setText(null);
                                }
                            }

                        };
                        cell.setOnMouseClicked( event -> {
                            if (!cell.isEmpty()) {
                                Project project = (Project) cell.getTableRow().getItem();
                            // do something with id...
                            log.debug("click on deletebtn = project number and id " +
                                    project.getProjectNumber() + project.getId());
//                            displayEditProject(project.getId());
                            log.debug("call delete by id = " + project.getId());
                            // fetch data
                            }
                        });
                        return cell;
                    }
                };

        this.deleteColumn.setCellFactory( cellFactory
//                cell -> {
////                    TableCell<Project, Button> tableCell = new TableCell<Project, Button>() {
////                        @Override
////                        protected void updateItem(Button item, boolean empty) {
////                            super.updateItem(item, empty) ;
////                            if (item != null) {
////                                item.setText("Delete");
////                            }
//////                            setText("Delete");
////                        }
////                    };
//                    tableCell.setOnMouseClicked(e -> {
//                        if (!tableCell.isEmpty()) {
//                            Project project = (Project) tableCell.getTableRow().getItem();
//                            // do something with id...
//                            log.debug("click on deletebtn = project number and id "+
//                                    project.getProjectNumber() + project.getId());
////                            displayEditProject(project.getId());
//                            log.debug("call delete by id = " + project.getId());
//                            // fetch data
//                        }
//                    });
//                    return tableCell;
//                }
        );

    }

    public void onUpdateSuccess(boolean success) throws IOException {
        log.debug("update success");
        displayProjectTable();
    }

    public void onResetSearch(ActionEvent actionEvent) {
       this.nameInp.clear();
       this.statusInp.valueProperty().set(null);
       fetchData("","");
    }

    private void fetchData(String name, String status) {
        this.name = name;
        this.status = status;
        this.projects.clear();
        this.projects.addAll(
                Mapper.toProjectList
                        (ProjectServiceClient.findByNameAndStatus(name, status), bundle)
        );
        this.projectTable.setItems(projects);
    }

    public void displayEditProject(long id) {
        EditController editController = new EditController(id);
        // set observable item for controller
        this.right.getChildren().clear();
        FXMLLoader loader = new FXMLLoader(MainController.class.getResource(editFXML), bundle);
        loader.setController(editController);
        try {
            editNode = loader.load();
            editController.setMainController(this);
            this.right.getChildren().add(editNode);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private void displayProjectTable() throws IOException {
        Stage stage = (Stage) this.left.getScene().getWindow();
        Parent newScene = FXMLLoader.load(MainController.class.getClass().getResource("/fxml/main.fxml"), this.bundle);
        Scene scene = new Scene(newScene);
        stage.setScene(scene);
        stage.show();
    }
}

package vn.elca.training;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.elca.training.controller.MainController;
import vn.elca.training.controller.ProjectListController;

import java.util.Locale;
import java.util.ResourceBundle;

public class MainApp extends Application {

    private static final Logger log = LoggerFactory.getLogger(MainApp.class);

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) throws Exception {
        log.info("Starting Hello JavaFX and Maven demonstration application");

        String fxmlFile = "/fxml/main.fxml";

        log.debug("Loading FXML for main view from: {}", fxmlFile);
        Locale locale = new Locale("en_CH");
        ResourceBundle bundle = ResourceBundle.getBundle("bundles.languageBundle", locale);

        FXMLLoader loader = new FXMLLoader();

        Parent rootNode = loader.load(getClass().getResource(fxmlFile),bundle);
        // get main container
//        MainController mainController = new MainController();
//        loader.setController(mainController);

//        FXMLLoader rightLoader = new FXMLLoader();
//        Parent projectListNode = rightLoader.load(getClass().getResource(projectListFxml), bundle);
//        // get right panel
//        ProjectListController projectListController = new ProjectListController();
//        // set controller
//        rightLoader.setController(projectListController);
//        projectListController.setMainController(mainController);
//        GridPane rightPane = (GridPane) rootNode.lookup("#right");
//        rightPane.getChildren().add(projectListNode);
        log.debug("Showing JFX scene");
        Scene scene = new Scene(rootNode);

        stage.setTitle("Project Information Management");
        stage.setScene(scene);
        stage.show();
    }

}

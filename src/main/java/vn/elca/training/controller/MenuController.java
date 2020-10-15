package vn.elca.training.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class MenuController {

    @FXML
    private Hyperlink newLink;

    @FXML
    private Hyperlink projectLink;

    public void onActionNew(ActionEvent actionEvent) throws IOException {
        Stage stage = null;
        Parent newScene = null;
        if (actionEvent.getSource().equals(newLink)) {
            stage = (Stage) this.newLink.getScene().getWindow();
            Locale locale = new Locale("en_CH");
            ResourceBundle bundle = ResourceBundle.getBundle("bundles.languageBundle", locale);
            FXMLLoader loader = new FXMLLoader();
            newScene = loader.load(MainController.class.getClass().getResource("/fxml/edit.fxml"),bundle);
        } else if (actionEvent.getSource().equals(projectLink)) {
            stage = (Stage) this.newLink.getScene().getWindow();
            Locale locale = new Locale("en_CH");
            ResourceBundle bundle = ResourceBundle.getBundle("bundles.languageBundle", locale);
            FXMLLoader loader = new FXMLLoader();
            newScene = loader.load(MainController.class.getClass().getResource("/fxml/main.fxml"),bundle);
        }
        Scene scene = new Scene(newScene);
        stage.setScene(scene);
        stage.show();
    }

}

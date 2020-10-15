package vn.elca.training.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class HeaderController implements Initializable {

    @FXML
    private Hyperlink enLink;

    @FXML
    private Hyperlink frLink;

    public void changeLang(ActionEvent actionEvent) throws IOException {
        Stage stage = null;
        Parent newScene = null;
        this.enLink.setVisited(false);
        this.frLink.setVisited(false);
        if (actionEvent.getSource().equals(enLink)) {
            stage = (Stage) this.enLink.getScene().getWindow();
            Locale locale = new Locale("en_CH");
            ResourceBundle bundle = ResourceBundle.getBundle("bundles.languageBundle", locale);
            newScene = FXMLLoader.load(MainController.class.getClass().getResource("/fxml/main.fxml"), bundle);
            this.enLink.setVisited(true);
        } else if (actionEvent.getSource().equals(frLink)) {
            stage = (Stage) this.frLink.getScene().getWindow();
            Locale locale = new Locale("fr_CH");
            ResourceBundle bundle = ResourceBundle.getBundle("bundles.languageBundle", locale);
            newScene = FXMLLoader.load(MainController.class.getClass().getResource("/fxml/main.fxml"), bundle);
            this.frLink.setVisited(true);
        }
        Scene scene = new Scene(newScene);
        stage.setScene(scene);
        System.out.println("is visited link fr " + this.frLink.isVisited());
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

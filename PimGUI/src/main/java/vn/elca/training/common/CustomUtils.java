package vn.elca.training.common;

import javafx.scene.control.Alert;

public class CustomUtils {
    public static void showDialogWarning(String content, String header, String title) {
        Alert alert = new Alert(Alert.AlertType.WARNING, content);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.show();
    }
}

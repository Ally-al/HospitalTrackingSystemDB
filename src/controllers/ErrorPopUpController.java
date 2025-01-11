package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Label;

import java.io.IOException;

public class ErrorPopUpController {
    @FXML
    private Label messageField;
    public void openPopUp(String message) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/errorPopUp.fxml"));
            Parent root = loader.load();

            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setScene(new Scene(root));

            ErrorPopUpController controller = loader.getController();

            int startIndex = message.indexOf("ОШИБКА:");
            int endIndex = message.indexOf("Где:");

            if (startIndex != -1 && endIndex != -1) {
                String trimmedErrorMessage = message.substring(startIndex + "ОШИБКА:".length(), endIndex).trim();
                controller.messageField.setText(trimmedErrorMessage);
            }
            popupStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package controllers.update;

import app.Main;
import app.tables.Wards;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class UpdateWardController {
    Main mainApp;

    @FXML
    private TextField idField;
    @FXML
    private TextField maxCountField;

    @FXML
    private TextField nameField;

    @FXML
    private Button updateButton;

    @FXML
    void onClickUpdateButton(ActionEvent event) {
        int id = Integer.parseInt(idField.getText());
        String name = nameField.getText();
        int maxCount = Integer.parseInt(maxCountField.getText());
        Wards.updateWard(id, name, maxCount);
        Stage stage = (Stage) updateButton.getScene().getWindow();
        stage.close();
    }
    public void openPopUp(int id, String name, int maxCount) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/update/updateWard.fxml"));
            Parent root = loader.load();

            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setScene(new Scene(root));

            UpdateWardController controller = loader.getController();
            controller.idField.setText(String.valueOf(id));
            controller.nameField.setText(name);
            controller.maxCountField.setText(String.valueOf(maxCount));

            popupStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }
}

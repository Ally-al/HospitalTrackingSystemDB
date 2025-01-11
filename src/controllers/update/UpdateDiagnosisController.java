package controllers.update;

import app.Main;
import app.tables.Diagnosis;
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

public class UpdateDiagnosisController {
    Main mainApp;

    @FXML
    private TextField idField;
    @FXML
    private TextField nameField;

    @FXML
    private Button updateButton;

    @FXML
    void onClickUpdateButton(ActionEvent event) {
        int id = Integer.parseInt(idField.getText());
        String name = nameField.getText();
        Diagnosis.updateDiagnosis(id, name);
        Stage stage = (Stage) updateButton.getScene().getWindow();
        stage.close();
    }
    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

    public void openPopUp(int id, String name) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/update/updateDiagnosis.fxml"));
            Parent root = loader.load();

            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setScene(new Scene(root));

            UpdateDiagnosisController controller = loader.getController();
            controller.idField.setText(String.valueOf(id));
            controller.nameField.setText(name);

            popupStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {

    }
}

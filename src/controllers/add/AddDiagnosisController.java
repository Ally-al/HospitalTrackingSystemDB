package controllers.add;

import app.Main;
import app.tables.Diagnosis;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddDiagnosisController {
    Main mainApp;
    @FXML
    private Button addButton;
    @FXML
    private TextField nameField;
    @FXML
    void onClickAddButton(ActionEvent event) {
        String name = nameField.getText();
        Diagnosis.addDiagnosis(name);
        Stage stage = (Stage) addButton.getScene().getWindow();
        stage.close();
    }
    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }
}

package controllers.add;

import app.Main;
import app.tables.Wards;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddWardController {
    Main mainApp;
    @FXML
    private Button addButton;
    @FXML
    private TextField maxCountField;
    @FXML
    private TextField nameField;
    @FXML
    void onClickAddButton(ActionEvent event) {
        String name = nameField.getText();
        int maxCount = Integer.parseInt(maxCountField.getText());
        Wards.addWard(name, maxCount);
        Stage stage = (Stage) addButton.getScene().getWindow();
        stage.close();
    }
    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }
}

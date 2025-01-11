package controllers.add;

import app.Main;
import app.tables.People;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddPeopleController {
    Main mainApp;
    @FXML
    private Button addButton;
    @FXML
    private TextField diagnosisIdField;
    @FXML
    private TextField fatherNameField;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField wardIdField;
    @FXML
    void onClickAddButton(ActionEvent event) {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String fatherName = fatherNameField.getText();
        int diagnosisId = Integer.parseInt(diagnosisIdField.getText());
        int wardId = Integer.parseInt(wardIdField.getText());
        People.addPeople(firstName, lastName, fatherName, diagnosisId, wardId);
        Stage stage = (Stage) addButton.getScene().getWindow();
        stage.close();
    }
    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }
}

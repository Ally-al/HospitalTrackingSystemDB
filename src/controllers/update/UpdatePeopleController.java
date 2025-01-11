package controllers.update;

import app.Main;
import app.tables.People;
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

public class UpdatePeopleController {
    Main mainApp;

    @FXML
    private TextField idField;
    @FXML
    private TextField diagnosisIdField;

    @FXML
    private TextField fatherNameField;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private Button updateButton;

    @FXML
    private TextField wardIdField;

    @FXML
    void onClickUpdateButton(ActionEvent event) {
        int id = Integer.parseInt(idField.getText());
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String fatherName = fatherNameField.getText();
        int diagnosisId = Integer.parseInt(diagnosisIdField.getText());
        int wardId = Integer.parseInt(wardIdField.getText());
        People.updatePeople(id, firstName, lastName, fatherName, diagnosisId, wardId);
        Stage stage = (Stage) updateButton.getScene().getWindow();
        stage.close();
    }
    public void openPopUp(int id, String firstName, String lastName, String fatherName, int diagnosisId, int wardId) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/update/updatePeople.fxml"));
            Parent root = loader.load();

            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setScene(new Scene(root));

            UpdatePeopleController controller = loader.getController();
            controller.idField.setText(String.valueOf(id));
            controller.firstNameField.setText(firstName);
            controller.lastNameField.setText(lastName);
            controller.fatherNameField.setText(fatherName);
            controller.diagnosisIdField.setText(String.valueOf(diagnosisId));
            controller.wardIdField.setText(String.valueOf(wardId));

            popupStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }
}

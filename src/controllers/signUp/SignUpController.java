package controllers.signUp;

import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

import app.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import app.tables.Users;

public class SignUpController {
    private Main mainApp;
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private Button authSignUpButton;
    @FXML
    private TextField newLoginField;
    @FXML
    private PasswordField newPasswordField;
    @FXML
    void onClickAuthSignUp(ActionEvent event) {
        String login = newLoginField.getText();
        String password = newPasswordField.getText();
        if (Users.isUserExist(login, password)) {
            mainApp.openSignUpPopUp();
        } else {
            Users.signUpUser(login, password);
            mainApp.openSignUpSuccessful();
            mainApp.openSignIn();
        }
    }
    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }
}

package controllers.signIn;

import java.net.URL;
import java.util.ResourceBundle;

import app.Main;
import app.tables.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SignInController {
    private Main mainApp;
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private Button authSignInButton;
    @FXML
    private Button loginSignUpButton;
    @FXML
    private TextField loginField;
    @FXML
    private PasswordField passwordField;
    @FXML
    void onClickLoginSignUpButton(ActionEvent event) {
        mainApp.openSingUp();
    }
    @FXML
    void onClickAuthSignIn(ActionEvent event) {
        String login = loginField.getText();
        Main.userName = login;
        String password = passwordField.getText();
        if (Users.isUserExist(login, password)) {
            mainApp.openMain();
        } else {
            mainApp.openSignInPopUp();
        }
    }
    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }
}

package app;

import controllers.MainController;
import controllers.add.*;
import controllers.signIn.SignInController;
import controllers.signUp.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;


public class Main extends Application {

    private Stage primaryStage;
    public static String userName;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        openSignIn();
        primaryStage.show();
    }
    public void openSignIn() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/signIn/signIn.fxml"));
            primaryStage.setScene(new Scene(loader.load()));
            primaryStage.setTitle("sign in");

            SignInController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openSingUp() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/signUp/signUp.fxml"));
            primaryStage.setScene(new Scene(loader.load()));
            primaryStage.setTitle("sign up");

            SignUpController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openMain() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/main.fxml"));
            primaryStage.setScene(new Scene(loader.load()));
            primaryStage.setTitle("main");

            MainController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openSignUpPopUp() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/signUp/signUpPopUp.fxml"));
            Parent root = loader.load();

            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setTitle("Ошибка!");
            popupStage.setScene(new Scene(root));

            SignUpPopUpController controller = loader.getController();
            controller.setMainApp(this);

            popupStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void openSignUpSuccessful() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/signUp/signUpSuccessful.fxml"));
            Parent root = loader.load();

            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setTitle("");
            popupStage.setScene(new Scene(root));

            SignUpSuccessfulController controller = loader.getController();
            controller.setMainApp(this);

            popupStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void openSignInPopUp() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/signIn/signInPopUp.fxml"));
            Parent root = loader.load();

            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setTitle("Ошибка!");
            popupStage.setScene(new Scene(root));

            SignInController controller = loader.getController();
            controller.setMainApp(this);

            popupStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openAddDiagnosis() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/add/addDiagnosis.fxml"));
            Parent root = loader.load();

            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setScene(new Scene(root));

            AddDiagnosisController controller = loader.getController();
            controller.setMainApp(this);

            popupStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openAddWard() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/add/addWard.fxml"));
            Parent root = loader.load();

            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setScene(new Scene(root));

            AddWardController controller = loader.getController();
            controller.setMainApp(this);

            popupStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void openAddPeople() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/add/addPeople.fxml"));
            Parent root = loader.load();

            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setScene(new Scene(root));

            AddPeopleController controller = loader.getController();
            controller.setMainApp(this);

            popupStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.setProperty("log4j.configurationFile", "log4j2.xml");
        launch(args);
    }
}

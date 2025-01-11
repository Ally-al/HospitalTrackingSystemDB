package controllers;

import app.ExportExcel;
import app.Main;
import app.tables.*;

import controllers.update.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.image.ImageView;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MainController {
    private Main mainApp;
    public String tableName;
    @FXML
    private Button exitButton;
    @FXML
    private Label userNameField;
    @FXML
    private Button addButton;
    @FXML
    private Button deleteButton;
    @FXML
    private MenuButton tableSelection;
    @FXML
    private Button updateButton;
    @FXML
    private VBox tableBox;
    @FXML
    private ImageView image;
    @FXML
    private Button downloadTablesButton;
    @FXML
    private Button downloadLogsButton;
    @FXML
    private Button openDiagramButton;
    @FXML
    private  TableView<People> peopleTableView = new TableView<>();
    @FXML
    private TableView<Diagnosis> diagnosisTableView = new TableView<>();
    @FXML
    private TableView<Wards> wardsTableView = new TableView<>();

    @FXML
    void onClickTableSelection(ActionEvent event) {
        if (event.getSource() instanceof MenuItem selectedItem) {
            String selectedTable = selectedItem.getText();
            switchTable(selectedTable);
        }
    }
    private void switchTable(String selectedTable) {
        tableBox.getChildren().clear();

        if ("пациенты".equals(selectedTable)) {
            tableSelection.setText("пациенты");
            peopleTableView.setItems(People.getData());
            tableBox.getChildren().add(peopleTableView);
            tableName = "пациенты";
        } else if ("диагнозы".equals(selectedTable)) {
            tableSelection.setText("диагнозы");
            diagnosisTableView.setItems(Diagnosis.getData());
            tableBox.getChildren().add(diagnosisTableView);
            tableName = "диагнозы";
        } else if ("палаты".equals(selectedTable)) {
            tableSelection.setText("палаты");
            wardsTableView.setItems(Wards.getData());
            tableBox.getChildren().add(wardsTableView);
            tableName = "палаты";
        }
    }
    @FXML
    void onClickAddButton(ActionEvent event) {
        switch (tableName) {
            case "пациенты" -> mainApp.openAddPeople();
            case "диагнозы" -> mainApp.openAddDiagnosis();
            case "палаты" -> mainApp.openAddWard();
        }
        switchTable(tableName);
    }
    @FXML
    void onClickDeleteButton(ActionEvent event) {
        switch (tableName) {
            case "пациенты" -> deleteSelectedPerson();
            case "диагнозы" -> deleteSelectedDiagnosis();
            case "палаты" -> deleteSelectedWard();
        }
        switchTable(tableName);
    }
    @FXML
    void onClickUpdateButton(ActionEvent event) {
        switch (tableName) {
            case "пациенты" -> {
                UpdatePeopleController upc = new UpdatePeopleController();
                People people = getSelectedPeople();
                int peopleId = Integer.parseInt(people.getId());
                String firstName = people.getFirstName();
                String lastName = people.getLastName();
                String fatherName = people.getFatherName();
                int diagnosisId = Integer.parseInt(people.getDiagnosisId());
                int wardId = Integer.parseInt(people.getWardId());
                upc.openPopUp(peopleId, firstName, lastName, fatherName, diagnosisId, wardId);
            }
            case "диагнозы" -> {
                UpdateDiagnosisController udc = new UpdateDiagnosisController();
                Diagnosis diagnosis = getSelectedDiagnosis();
                int idDiagnosis = Integer.parseInt(diagnosis.getId());
                String nameDiagnosis = diagnosis.getName();
                udc.openPopUp(idDiagnosis, nameDiagnosis);
            }
            case "палаты" -> {
                UpdateWardController uwc = new UpdateWardController();
                Wards ward = getSelectedWard();
                int idWard = Integer.parseInt(ward.getId());
                String nameWard = ward.getName();
                int maxCount = Integer.parseInt(ward.getMaxCount());
                uwc.openPopUp(idWard, nameWard, maxCount);
            }
        }
        switchTable(tableName);
    }
    @FXML
    void onClickDownloadTables(ActionEvent event) {
        ExportExcel.exportAllTables();
        try {
            Desktop.getDesktop().open(new File("tables.xlsx"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void onClickDownloadLogs(ActionEvent event) {
            try {
                Desktop.getDesktop().open(new File("C:\\Users\\Alina\\IdeaProjects\\dbCourse\\sql-log.log"));
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
    @FXML
    void onClickOpenDiagram(ActionEvent event) {
        WardsLoadController.openDiagram();
    }
    @FXML
    void initialize() {
        userNameField.setText(Main.userName);

        TableColumn<People, String> peopleIDCol = new TableColumn<>("id");
        peopleIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<People, String> firstNameCol = new TableColumn<>("first name");
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        TableColumn<People, String> lastNameCol = new TableColumn<>("last name");
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        TableColumn<People, String> fatherNameCol = new TableColumn<>("father name");
        fatherNameCol.setCellValueFactory(new PropertyValueFactory<>("fatherName"));
        TableColumn<People, String> diagnosisIdCol = new TableColumn<>("diagnosis Id");
        diagnosisIdCol.setCellValueFactory(new PropertyValueFactory<>("diagnosisId"));
        TableColumn<People, String> wardIdCol = new TableColumn<>("ward Id");
        wardIdCol.setCellValueFactory(new PropertyValueFactory<>("wardId"));

        TableColumn<Diagnosis, String> diagnosisIDCol = new TableColumn<>("id");
        diagnosisIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Diagnosis, String> diagnosisNameCol = new TableColumn<>("name");
        diagnosisNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));


        TableColumn<Wards, String> wardsIDCol = new TableColumn<>("id");
        wardsIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Wards, String> wardNameCol = new TableColumn<>("name");
        wardNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Wards, String> maxCountCol = new TableColumn<>("max count");
        maxCountCol.setCellValueFactory(new PropertyValueFactory<>("maxCount"));

        peopleTableView.getColumns().addAll(peopleIDCol, firstNameCol, lastNameCol, fatherNameCol, diagnosisIdCol, wardIdCol);
        diagnosisTableView.getColumns().addAll(diagnosisIDCol, diagnosisNameCol);
        wardsTableView.getColumns().addAll(wardsIDCol, wardNameCol, maxCountCol);
    }
    private void deleteSelectedPerson() {
        People selectedPeople = peopleTableView.getSelectionModel().getSelectedItem();
        if (selectedPeople != null) {
            int id = Integer.parseInt(selectedPeople.getId());
            People.deletePeople(id);
        }
    }
    private void deleteSelectedDiagnosis() {
        Diagnosis selectedDiagnosis = diagnosisTableView.getSelectionModel().getSelectedItem();
        if (selectedDiagnosis != null) {
            int id = Integer.parseInt(selectedDiagnosis.getId());
            Diagnosis.deleteDiagnosis(id);
        }
    }
    private void deleteSelectedWard() {
        Wards selectedWard = wardsTableView.getSelectionModel().getSelectedItem();
        if (selectedWard != null) {
            int id = Integer.parseInt(selectedWard.getId());
            Wards.deleteWard(id);
        }
    }
    private People getSelectedPeople() {
        return peopleTableView.getSelectionModel().getSelectedItem();
    }
    private Diagnosis getSelectedDiagnosis() {
        return diagnosisTableView.getSelectionModel().getSelectedItem();
    }
    private Wards getSelectedWard() {
        return wardsTableView.getSelectionModel().getSelectedItem();
    }
    @FXML
    void onClickExitButton(ActionEvent event) {
        mainApp.openSignIn();
    }
    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }
}

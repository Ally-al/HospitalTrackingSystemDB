package app.tables;

import app.Const;
import app.DBHandler;
import controllers.ErrorPopUpController;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class People {
    private static final Logger logger = LogManager.getLogger(People.class);
    private static final ErrorPopUpController errorPopUpController = new ErrorPopUpController();
    private final SimpleStringProperty id;
    private final SimpleStringProperty wardId;
    private final SimpleStringProperty diagnosisId;
    private final SimpleStringProperty firstName;
    private final SimpleStringProperty lastName;
    private final SimpleStringProperty fatherName;

    public People(Integer id, String firstName, String lastName, String fatherName, Integer diagnosisId, Integer wardId) {
        this.id = new SimpleStringProperty(id.toString());
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.fatherName = new SimpleStringProperty(fatherName);
        this.diagnosisId = new SimpleStringProperty(diagnosisId.toString());
        this.wardId = new SimpleStringProperty(wardId.toString());
    }

    public static ObservableList<People> getData() {
        ObservableList<People> data = FXCollections.observableArrayList();
        try (Connection connection = DBHandler.getDbConnection()) {

            String query = "SELECT * FROM " + Const.PEOPLE_TABLE + " ORDER BY " + Const.PEOPLE_ID + " ASC;";

            Statement statement = connection.createStatement();
            logger.info("SQL запрос: " + query);

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String fatherName = resultSet.getString("pather_name");
                int diagnosisId = resultSet.getInt("diagnosis_id");
                int wardId = resultSet.getInt("ward_id");
                data.add(new People(id, firstName, lastName, fatherName, diagnosisId, wardId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static void addPeople(String firstName, String lastName, String fatherName, int diagnosisId, int wardId) {
        String insert = "INSERT INTO " + Const.PEOPLE_TABLE + "(" + Const.PEOPLE_FIRST_NAME +
                ", " + Const.PEOPLE_LAST_NAME +
                ", " + Const.PEOPLE_FATHER_NAME +
                ", " + Const.PEOPLE_DIAGNOSIS_ID +
                ", " + Const.PEOPLE_WARD_ID + ")" + "VALUES(?,?,?,?,?)";
        try (Connection connection = DBHandler.getDbConnection()) {
            PreparedStatement prSt = connection.prepareStatement(insert);
            prSt.setString(1, firstName);
            prSt.setString(2, lastName);
            prSt.setString(3, fatherName);
            prSt.setInt(4, diagnosisId);
            prSt.setInt(5, wardId);
            logger.info("SQL запрос: " + prSt);

            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            errorPopUpController.openPopUp(e.getMessage());
        }
    }
    public static void deletePeople(int id) {
        try (Connection connection = DBHandler.getDbConnection()) {

            String delete = "DELETE FROM " + Const.PEOPLE_TABLE + " WHERE " + Const.PEOPLE_ID + " = ?";
            PreparedStatement prSt = connection.prepareStatement(delete);
            prSt.setInt(1, id);
            logger.info("SQL запрос: " + prSt);

            prSt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void updatePeople(int id, String firstName, String lastName, String fatherName, int diagnosisId, int wardId) {
        String update = "UPDATE " + Const.PEOPLE_TABLE + " SET " +
                Const.PEOPLE_FIRST_NAME + " = ?, " +
                Const.PEOPLE_LAST_NAME + " = ?, " +
                Const.PEOPLE_FATHER_NAME + " = ?, " +
                Const.PEOPLE_DIAGNOSIS_ID + " = ?, " +
                Const.PEOPLE_WARD_ID + " = ? WHERE " +
                Const.PEOPLE_ID + " = ?";
        try (Connection connection = DBHandler.getDbConnection()) {
            PreparedStatement prSt = connection.prepareStatement(update);
            prSt.setString(1, firstName);
            prSt.setString(2, lastName);
            prSt.setString(3, fatherName);
            prSt.setInt(4, diagnosisId);
            prSt.setInt(5, wardId);
            prSt.setInt(6, id);
            logger.info("SQL запрос: " + prSt);

            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            errorPopUpController.openPopUp(e.getMessage());
        }
    }

    public String getId() {
        return id.get();
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getWardId() {
        return wardId.get();
    }

    public SimpleStringProperty wardIdProperty() {
        return wardId;
    }

    public void setWardId(String wardId) {
        this.wardId.set(wardId);
    }

    public String getDiagnosisId() {
        return diagnosisId.get();
    }

    public SimpleStringProperty diagnosisIdProperty() {
        return diagnosisId;
    }

    public void setDiagnosisId(String diagnosisId) {
        this.diagnosisId.set(diagnosisId);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public SimpleStringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public SimpleStringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getFatherName() {
        return fatherName.get();
    }

    public SimpleStringProperty fatherNameProperty() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName.set(fatherName);
    }
}

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

public class Diagnosis {
    private static final Logger logger = LogManager.getLogger(Diagnosis.class);
    private static final ErrorPopUpController errorPopUpController = new ErrorPopUpController();
    private final SimpleStringProperty id;
    private final SimpleStringProperty name;
    public Diagnosis(Integer id, String name) {
        this.id = new SimpleStringProperty(id.toString());
        this.name = new SimpleStringProperty(name);
    }

    public static ObservableList<Diagnosis> getData() {
        ObservableList<Diagnosis> data = FXCollections.observableArrayList();
        try (Connection connection = DBHandler.getDbConnection()) {

            String query = "SELECT * FROM " + Const.DIAGNOSIS_TABLE + " ORDER BY " + Const.DIAGNOSIS_ID + " ASC;";

            Statement statement = connection.createStatement();

            logger.info("SQL запрос: " + query);
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("Name");
                data.add(new Diagnosis(id, name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static void addDiagnosis(String diagnosisName) {
        try (Connection connection = DBHandler.getDbConnection()) {
            String insert = "INSERT INTO " + Const.DIAGNOSIS_TABLE + "(" + Const.DIAGNOSIS_NAME + ")" + "VALUES(?)";
            PreparedStatement prSt = connection.prepareStatement(insert);
            prSt.setString(1, diagnosisName);
            logger.info("SQL запрос: " + prSt);

            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
    public static void deleteDiagnosis(int id) {
        try (Connection connection = DBHandler.getDbConnection()) {

            String delete = "DELETE FROM " + Const.DIAGNOSIS_TABLE + " WHERE " + Const.DIAGNOSIS_ID + " = ?";
            PreparedStatement prSt = connection.prepareStatement(delete);
            prSt.setInt(1, id);
            logger.info("SQL запрос: " + prSt);

            prSt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            errorPopUpController.openPopUp(e.getMessage());
        }
    }
    public static void updateDiagnosis(int id, String name) {
        String update = "UPDATE " + Const.DIAGNOSIS_TABLE + " SET " +
                Const.WARDS_NAME + " = ? WHERE " +
                Const.WARDS_ID + " = ?";
        try (Connection connection = DBHandler.getDbConnection()) {
            PreparedStatement prSt = connection.prepareStatement(update);
            prSt.setString(1, name);
            prSt.setInt(2, id);
            logger.info("SQL запрос: " + prSt);

            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
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

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }
}

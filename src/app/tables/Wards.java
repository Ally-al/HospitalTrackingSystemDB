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

public class Wards {
    private static final Logger logger = LogManager.getLogger(Wards.class);
    private static final ErrorPopUpController errorPopUpController = new ErrorPopUpController();
    private final SimpleStringProperty id;
    private final SimpleStringProperty name;
    private final SimpleStringProperty maxCount;
    public Wards(Integer id, String name, Integer maxCount) {
        this.id = new SimpleStringProperty(id.toString());
        this.name = new SimpleStringProperty(name);
        this.maxCount = new SimpleStringProperty(maxCount.toString());
    }

    public static ObservableList<Wards> getData() {
        ObservableList<Wards> data = FXCollections.observableArrayList();
        try (Connection connection = DBHandler.getDbConnection()) {

            String query = "SELECT * FROM " + Const.WARDS_TABLE + " ORDER BY " + Const.WARDS_ID + " ASC;";

            Statement statement = connection.createStatement();
            logger.info("SQL запрос: " + query);

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int maxCount = resultSet.getInt("max_count");
                data.add(new Wards(id, name, maxCount));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static void addWard(String name, int maxCount) {
        String insert = "INSERT INTO " + Const.WARDS_TABLE + "(" + Const.WARDS_NAME +
                ", " + Const.WARDS_MAX_COUNT + ")" + "VALUES(?,?)";
        try (Connection connection = DBHandler.getDbConnection()) {
            PreparedStatement prSt = connection.prepareStatement(insert);
            prSt.setString(1, name);
            prSt.setInt(2, maxCount);
            logger.info("SQL запрос: " + prSt);

            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void deleteWard(int id) {
        try (Connection connection = DBHandler.getDbConnection()) {

            String delete = "DELETE FROM " + Const.WARDS_TABLE + " WHERE " + Const.WARDS_ID + " = ?";
            PreparedStatement prSt = connection.prepareStatement(delete);
            prSt.setInt(1, id);
            logger.info("SQL запрос: " + prSt);

            prSt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            errorPopUpController.openPopUp(e.getMessage());
        }
    }
    public static void updateWard(int id, String name, int maxCount) {
        String update = "UPDATE " + Const.WARDS_TABLE + " SET " +
                Const.WARDS_NAME + " = ?, " +
                Const.WARDS_MAX_COUNT + " = ? WHERE " +
                Const.WARDS_ID + " = ?";
        try (Connection connection = DBHandler.getDbConnection()) {
            PreparedStatement prSt = connection.prepareStatement(update);
            prSt.setString(1, name);
            prSt.setInt(2, maxCount);
            prSt.setInt(3, id);
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

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getMaxCount() {
        return maxCount.get();
    }

    public SimpleStringProperty maxCountProperty() {
        return maxCount;
    }

    public void setMaxCount(String maxCount) {
        this.maxCount.set(maxCount);
    }
}

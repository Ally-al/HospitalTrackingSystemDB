package controllers;

import app.DBHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
public class WardsLoadController {
    @FXML
    private HBox box;
    public static void openDiagram() {
        CategoryAxis xAxis = new CategoryAxis ();
        NumberAxis yAxis = new NumberAxis();
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);

        xAxis.setLabel("ward id");
        yAxis.setLabel("ward load, %");

        XYChart.Series<String, Number> series = getDiagramData();

        ObservableList<XYChart.Series<String , Number>> data = FXCollections.observableArrayList(series);
        barChart.setData(data);

        try {
            FXMLLoader loader = new FXMLLoader(WardsLoadController.class.getResource("/views/wardsLoad.fxml"));
            Parent root = loader.load();

            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setScene(new Scene(root));

            WardsLoadController controller = loader.getController();
            controller.box.getChildren().add(barChart);
            popupStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static XYChart.Series<String, Number> getDiagramData() {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        try (Connection connection = DBHandler.getDbConnection()) {

            String query = "SELECT * FROM calculate_ward_load() ORDER BY ward_id ASC;";

            Statement statement = connection.createStatement();
            Logger logger = LogManager.getLogger(MainController.class);
            logger.info("SQL запрос: " + query);

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String wardId = resultSet.getString("ward_id");
                double occupancyPercentage = resultSet.getDouble("load_percent");
                series.getData().add(new XYChart.Data<>(wardId, occupancyPercentage));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return series;
    }
}

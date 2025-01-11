package app;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExportExcel {
    private static final Logger logger = LogManager.getLogger(ExportExcel.class);

    public static void exportAllTables() {
        try (FileOutputStream fileOut = new FileOutputStream("tables.xlsx")) {
            Workbook workbook = new SXSSFWorkbook(new XSSFWorkbook());
            exportTable( "diagnosis", workbook);
            exportTable( "people", workbook);
            exportTable( "wards", workbook);

            workbook.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void exportTable(String tableName, Workbook workbook) {
        try (Connection connection = DBHandler.getDbConnection()) {
            String query = "SELECT * FROM " + tableName;
            PreparedStatement prSt = connection.prepareStatement(query);
            logger.info("SQL запрос: " + prSt);

            ResultSet resultSet = prSt.executeQuery();
            Sheet sheet = workbook.createSheet(tableName);
            Row headerRow = sheet.createRow(0);
            ResultSetMetaData rsmd = resultSet.getMetaData();
            int columnCount = rsmd.getColumnCount();

            int rowIdx;
            for (rowIdx = 0; rowIdx < columnCount; rowIdx++) {
                Cell cell = headerRow.createCell(rowIdx);
                cell.setCellValue(rsmd.getColumnName(rowIdx + 1));
            }

            rowIdx = 1;
            while (resultSet.next()) {
                Row row = sheet.createRow(rowIdx++);

                for (int i = 0; i < columnCount; i++) {
                    Cell cell = row.createCell(i);
                    cell.setCellValue(resultSet.getString(i + 1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

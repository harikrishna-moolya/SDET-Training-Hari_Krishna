package data_Driven_Testing;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Excel_Reader {

    public static List<String[]> readExcel(String filePath, String sheetName) {

        List<String[]> data = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            Iterator<Row> rows = sheet.iterator();

            rows.next(); // Skip header row

            while (rows.hasNext()) {
                Row row = rows.next();

                String username = getCellValueAsString(row.getCell(0));
                String password = getCellValueAsString(row.getCell(1));
                String expected = getCellValueAsString(row.getCell(2));

                data.add(new String[]{username, password, expected});
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    private static String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return "";
        }

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf((long) cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            default:
                return "";
        }
    }
}

import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;

public class Data_Driven {


    // 1. SIMPLE DATA PROVIDER

    @DataProvider(name = "simpleData")
    public Object[][] simpleDataProvider() {
        return new Object[][]{
                {"admin", "admin123"},
                {"hari", "hari123"},
                {"testuser", "password999"}
        };
    }

    @Test(dataProvider = "simpleData", groups = "sanity")
    public void testSimpleData(String username, String password) {
        System.out.println("Simple Data → " + username + " : " + password);
    }


    // 2. EXCEL DATA PROVIDER

    @DataProvider(name = "excelData")
    public Object[][] excelDataProvider() {
        return readExcelData("C:\\Users\\Hari Krishna\\Downloads\\login_credentials.xlsx", "HKxl");
    }

    public Object[][] readExcelData(String filePath, String sheetName) {
        try {
            FileInputStream fis = new FileInputStream(filePath);
            XSSFWorkbook workbook = new XSSFWorkbook(fis);

            // Debug: print all sheet names
            System.out.println("Available Sheets:");
            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                System.out.println(" → " + workbook.getSheetName(i));
            }

            Sheet sheet = workbook.getSheet(sheetName);

            // If sheet is null → load first sheet
            if (sheet == null) {
                System.out.println("Sheet '" + sheetName + "' NOT FOUND. Using first sheet instead.");
                sheet = workbook.getSheetAt(0);
            }

            int rows = sheet.getPhysicalNumberOfRows();
            int cols = sheet.getRow(0).getLastCellNum();

            Object[][] data = new Object[rows - 1][cols];

            for (int i = 1; i < rows; i++) {
                Row row = sheet.getRow(i);
                for (int j = 0; j < cols; j++) {
                    Cell cell = row.getCell(j);
                    data[i - 1][j] = (cell == null) ? "" : cell.toString();
                }
            }

            workbook.close();
            return data;

        } catch (Exception e) {
            System.out.println("Excel Read Error: " + e.getMessage());
            return new Object[0][0];   // FIX: Never return null
        }
    }


    // TEST THAT PRINTS XL VALUES

    @Test(dataProvider = "excelData", groups = "sanity")
    public void testExcelData(String username, String password) {
        System.out.println("Excel Row → Username: " + username + ", Password: " + password);
    }


    // 3. XML PARAMS

    @Test(groups = "sanity")
    @Parameters({"browser", "url"})
    public void testXMLParams(
            @Optional("chrome") String browser,
            @Optional("https://google.com") String url
    ) {
        System.out.println("Browser from XML: " + browser);
        System.out.println("URL from XML: " + url);
    }
}

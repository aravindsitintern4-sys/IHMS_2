package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel {

    private static final String FILE_PATH =
            "src/test/resources/OPRegistrationData.xlsx";

    public static Map<String, String> getTestData(String sheetName) {

        Map<String, String> data = new HashMap<>();

        try (FileInputStream fis = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);

            DataFormatter formatter = new DataFormatter(); 

            for (int i = 0; i <= sheet.getLastRowNum(); i++) {

                Row row = sheet.getRow(i);

                if (row == null) continue;

                Cell keyCell = row.getCell(0);
                Cell valueCell = row.getCell(1);

                String key = formatter.formatCellValue(keyCell).trim();
                String value = formatter.formatCellValue(valueCell).trim();

                data.put(key, value);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }
}
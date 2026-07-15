// //  GET TEST DATA FROM EXCEL AND DROPDOWN VALUES FROM JSON

// package utils;

// import java.io.File;
// import java.io.FileInputStream;
// import java.io.IOException;
// // HASH MAP ---> STORES THE DATAS AS KEY - VALUE PAIRS 
// import java.util.HashMap;
// import java.util.Map;


// import org.apache.poi.ss.usermodel.*;
// import org.apache.poi.xssf.usermodel.XSSFWorkbook;

// public class Excel {

//     private static final String FILE_PATH = "src/test/resources/OPRegistrationDataJsonValidation.xlsx";

//     public static Map<String, String> getTestData(String sheetName) {

//         Map<String, String> data = new HashMap<>();
//         try (FileInputStream fis = new FileInputStream(FILE_PATH);
//              Workbook workbook = new XSSFWorkbook(fis)) {

//             Sheet sheet = workbook.getSheet(sheetName);
//             DataFormatter formatter = new DataFormatter(); 

//             for (int i = 0; i <= sheet.getLastRowNum(); i++) {
//                 Row row = sheet.getRow(i);

//                 if (row == null) continue;
//                 Cell keyCell = row.getCell(0);
//                 Cell valueCell = row.getCell(1);

//                 String key = formatter.formatCellValue(keyCell).trim();
//                 String value = formatter.formatCellValue(valueCell).trim();

//                 data.put(key, value);
//             }
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//         return data;
//     }
// }




package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel {

    private static final String FILE_PATH = "src/test/resources/OPRegistrationDataJsonValidation.xlsx";

    /**
     * Reads one person's data from the specified value column.
     * Column 0 = Keys
     * Column 1 = Person 1
     * Column 2 = Person 2
     * Column 3 = Person 3 ...
     */
    public static Map<String, String> getTestData(String sheetName, int valueColumn) {

        Map<String, String> data = new HashMap<>();

        try (FileInputStream fis = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            DataFormatter formatter = new DataFormatter();

            for (int i = 0; i <= sheet.getLastRowNum(); i++) {

                Row row = sheet.getRow(i);
                if (row == null) {
                    continue;
                }

                Cell keyCell = row.getCell(0);
                Cell valueCell = row.getCell(valueColumn);

                if (keyCell == null) {
                    continue;
                }

                String key = formatter.formatCellValue(keyCell).trim();
                String value = valueCell == null ? "" : formatter.formatCellValue(valueCell).trim();

                data.put(key, value);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

    /**
     * Returns the last populated data column.
     * Example:
     * Column 0 = Keys
     * Column 1 = Person 1
     * Column 2 = Person 2
     * Column 3 = Person 3
     *
     * Returns 3.
     */
    public static int getLastDataColumn(String sheetName) {

        try (FileInputStream fis = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);

            int lastColumn = 0;

            for (int i = 0; i <= sheet.getLastRowNum(); i++) {

                Row row = sheet.getRow(i);

                if (row != null) {
                    lastColumn = Math.max(lastColumn, row.getLastCellNum() - 1);
                }
            }

            return lastColumn;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0;
    }
}










// // EXCEL TO EXCEL
// package utils;

// import java.io.FileInputStream;
// import java.io.FileOutputStream;
// import java.io.IOException;
// import java.util.ArrayList;
// import java.util.LinkedHashMap;
// import java.util.List;
// import java.util.Map;

// import org.apache.poi.ss.usermodel.*;
// import org.apache.poi.xssf.usermodel.XSSFWorkbook;

// public class Excel {

//     private static final String FILE_PATH ="src/test/resources/ExcelValidation.xlsx";

//     // READ TEST DATA (Sheet : opRegistrationData)
//     public static Map<String, String> getTestData(String sheetName) {

//         Map<String, String> data = new LinkedHashMap<>();

//         try (FileInputStream fis = new FileInputStream(FILE_PATH);
//              Workbook workbook = new XSSFWorkbook(fis)) {

//             Sheet sheet = workbook.getSheet(sheetName);

//             if (sheet == null) {
//                 throw new RuntimeException("Sheet '" + sheetName + "' not found.");
//             }

//             //  FORMAT EVERY CELL AS A TEXT 
//             DataFormatter formatter = new DataFormatter();

//             for (int i = 0; i <= sheet.getLastRowNum(); i++) {
//                 Row row = sheet.getRow(i);

//                 if (row == null)
//                     continue;

//                 Cell keyCell = row.getCell(0);
//                 Cell valueCell = row.getCell(1);

//                 if (keyCell == null || valueCell == null)
//                     continue;

//                 String key = formatter.formatCellValue(keyCell).trim();
//                 String value = formatter.formatCellValue(valueCell).trim();

//                 if (!key.isEmpty()) {
//                     data.put(key, value);
//                 }
//             }

//         } catch (Exception e) {
//             throw new RuntimeException(e);
//         }
//         return data;
//     }

//     // WRITE DROPDOWN OPTIONS  (SHEET NAME : DropdownOptions)

//     public static void writeDropdownOptions(
//         Map<String, List<String>> dropdownMap) throws IOException {

//         Workbook workbook;

//         try (FileInputStream fis = new FileInputStream(FILE_PATH)) {
//             workbook = new XSSFWorkbook(fis);
//         }

//         // CHECK THE SHEET IS EXIST OR NOT
//         Sheet oldSheet = workbook.getSheet("DropdownOptions");

//         if (oldSheet != null) {
//             workbook.removeSheetAt(workbook.getSheetIndex(oldSheet));
//         }

//         Sheet sheet = workbook.createSheet("DropdownOptions");
//         int rowNum = 0;

//         for (Map.Entry<String, List<String>> entry : dropdownMap.entrySet()) {

//             Row row = sheet.createRow(rowNum++);

//             // LABEL
//             row.createCell(0).setCellValue(entry.getKey());

//             // OPTIONS
//             List<String> options = entry.getValue();

//             for (int i = 0; i < options.size(); i++) {
//                 row.createCell(i + 1).setCellValue(options.get(i));
//             }
//         }
//         for (int i = 0; i < 50; i++) {
//             sheet.autoSizeColumn(i);
//         }
//         try (FileOutputStream fos = new FileOutputStream(FILE_PATH)) {
//             workbook.write(fos);
//         }
//         workbook.close();
//         System.out.println("DropdownOptions sheet updated successfully.");
//     }

//     // READ DROPDOWN OPTIONS  (Sheet : DropdownOptions)
//     public static Map<String, List<String>> getDropdownOptions(String sheetName) {

//         Map<String, List<String>> dropdownMap = new LinkedHashMap<>();

//         try (FileInputStream fis = new FileInputStream(FILE_PATH);
//              Workbook workbook = new XSSFWorkbook(fis)) {

//             Sheet sheet = workbook.getSheet(sheetName);

//             if (sheet == null) {
//                 throw new RuntimeException("Sheet '" + sheetName + "' not found.");
//             }

//             DataFormatter formatter = new DataFormatter();

//             for (int i = 0; i <= sheet.getLastRowNum(); i++) {
//                 Row row = sheet.getRow(i);

//                 if (row == null)
//                     continue;

//                 Cell labelCell = row.getCell(0);    

//                 if (labelCell == null)
//                     continue;

//                 String label = formatter.formatCellValue(labelCell).trim();

//                 if (label.isEmpty())
//                     continue;

//                 List<String> options = new ArrayList<>();
//                 for (int j = 1; j < row.getLastCellNum(); j++) {
//                     Cell optionCell = row.getCell(j);
//                     if (optionCell == null)
//                         continue;
//                     String option = formatter.formatCellValue(optionCell).trim();
//                     if (!option.isEmpty()) {
//                         options.add(option);
//                     }
//                 }
//                 dropdownMap.put(label, options);
//             }

//         } catch (Exception e) {
//             throw new RuntimeException(e);
//         }

//         return dropdownMap;
//     }

// }
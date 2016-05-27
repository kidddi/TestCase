package datadriven;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import utilites.Constans;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by energetic on 26.05.2016.
 */
public class ExcelUtilities {

    private static XSSFWorkbook ExcelWBook;
    private static XSSFSheet ExcelSheet;
    static Logger log = Logger.getLogger(String.valueOf(ExcelUtilities.class));

//    public static void main(String... args){
//        setExcelFile(Constans.path, "Sheet1");
//        getTestData("Invalid_Login");
//    }

    public static void setExcelFile(String path, String sheetName) {
        PropertyConfigurator.configure("log4j.properties");
        try {
            FileInputStream ExcelFile = new FileInputStream(path);

            ExcelWBook = new XSSFWorkbook(ExcelFile);
            ExcelSheet = ExcelWBook.getSheet(sheetName);
            log.info("Sheet added");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String[][] getTestData(String tableNeme) {
        String[][] testData = null;

        try {
            XSSFCell[] boundaryCells = findCells(tableNeme);
            XSSFCell startCell = boundaryCells[0];
            XSSFCell endCell = boundaryCells[1];

            int startRow = startCell.getRowIndex() + 1;
            int endRow = endCell.getRowIndex() - 1;
            int startCol = startCell.getColumnIndex() + 1;
            int endCol = endCell.getColumnIndex() - 1;


            testData = new String[endRow - startRow + 1][endCol - startCol + 1];

            for (int i = startRow; i <= endRow; i++) {
                for (int j = startCol; j <= endCol; j++) {
                    testData[i - startRow][j - startCol] = ExcelSheet.getRow(i).getCell(j).getStringCellValue();
                }
            }
        } catch (Exception e) {
        }
        return testData;
    }

    private static XSSFCell[] findCells(String tableNeme) {
        String pas = "begin";
        XSSFCell[] cells = new XSSFCell[2];
        for (Row row : ExcelSheet) {
            for (Cell cell : row) {
                if (tableNeme.equals(cell.getStringCellValue())) {
                    if (pas.equalsIgnoreCase("begin")) {
                        cells[0] = (XSSFCell)cell;
                        pas = "end";
                    } else {
                        cells[1] = (XSSFCell)cell;

                    }

                }

            }

        }

        return cells;
    }
}

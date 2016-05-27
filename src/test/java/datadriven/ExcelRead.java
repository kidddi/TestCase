package datadriven;

/**
 * Created by energetic on 26.05.2016.
 */

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExcelRead {

    public static void main(String[] args) {


        XSSFWorkbook ExcelWBook;
        XSSFSheet ExcelSheet;
        XSSFCell Cell;


        String path = "D:\\WORK\\TestCase\\src\\utilites\\ExcelRead.xlsx";
        String sheetName = "Sheet1";

        try {
            FileInputStream ExcelFile = new FileInputStream(path);
            ExcelWBook = new XSSFWorkbook(ExcelFile);
            ExcelSheet = ExcelWBook.getSheet(sheetName);
            String s = ExcelSheet.getSheetName();
            System.out.println("Sheet Data is: " + s);
            Cell = ExcelSheet.getRow(0).getCell(0);
            String CellData = Cell.getStringCellValue();

            System.out.println("Cell Data is: " + CellData);
            System.out.println(ExcelSheet.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

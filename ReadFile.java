/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.tdt.edu.vn;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author HP
 */
public class ReadFile {

    List<TimeTable> ListTimeTable;

    public ReadFile(String path) throws IOException {
        ListTimeTable = new ArrayList<>();
        readFile(path);
    }

    private void readFile(String Path) throws IOException {
        InputStream ExcelFileToRead = new FileInputStream(Path);
        XSSFWorkbook wb = new XSSFWorkbook(ExcelFileToRead);

        XSSFSheet s = wb.getSheetAt(0);
        XSSFRow row;
        XSSFCell c;
        String Id = "";
        String FirstName = "";
        String LastName = "";
        String ThuHai = "";
        String ThuBa = "";
        String ThuTu = "";
        String ThuNam = "";
        String ThuSau = "";
        String ThuBay = "";
        int FIRST_ROW_TO_GET = 1;

        for (int i = FIRST_ROW_TO_GET; i < s.getLastRowNum() + 1; i++) {
            row = s.getRow(i);
            if (row == null) {
                continue;
            } else {

                for (int cn = 0; cn < row.getLastCellNum(); cn++) {
                    c = row.getCell(cn, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    if (cn == 0) {
                        Id = c.getStringCellValue();
                    }
                    if (cn == 1) {
                        FirstName = c.getStringCellValue();
                    }
                    if (cn == 2) {
                        LastName = c.getStringCellValue();
                    }
                    if (cn == 3) {
                        ThuHai = c.getStringCellValue();
                    }
                    if (cn == 4) {
                        ThuBa = c.getStringCellValue();
                    }
                    if (cn == 5) {
                        ThuTu = c.getStringCellValue();
                    }
                    if (cn == 6) {
                        ThuNam = c.getStringCellValue();
                    }
                    if (cn == 7) {
                        ThuSau = c.getStringCellValue();
                    }
                    if (cn == 8) {
                        ThuBay = c.getStringCellValue();
                    }

                }
                TimeTable t = new TimeTable(Id, FirstName, LastName, ThuHai, ThuBa, ThuTu, ThuNam, ThuSau, ThuBay);
                ListTimeTable.add(t);
            }
        }
    }
}

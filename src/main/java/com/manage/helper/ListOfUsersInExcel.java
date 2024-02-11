package com.manage.helper;

import com.manage.model.User;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;


public class ListOfUsersInExcel {
    public static String[] HEADERS = {
            "id",
            "username",
            "firstname",
            "lastname",
            "nationalCode",
            "active"
    };
    public static String SHEET_NAME = "user_data";

    public static ByteArrayInputStream dataToExcel(List<User> list) throws IOException {
        //create work book
        Workbook workbook = new XSSFWorkbook();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        //create sheet
        Sheet sheet = workbook.createSheet(SHEET_NAME);
        //create row
        Row row = sheet.createRow(0);
        for (int i = 0; i < HEADERS.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(HEADERS[i]);
        }
        //value row
        int rowIndex = 1;
        for (User u : list) {
            if (u.isEnable()) {
                Row dataRow = sheet.createRow(rowIndex);
                rowIndex++;
                dataRow.createCell(0).setCellValue(u.getId());
                dataRow.createCell(1).setCellValue(u.getUsername());
                dataRow.createCell(2).setCellValue(u.getFirstname());
                dataRow.createCell(3).setCellValue(u.getLastname());
                dataRow.createCell(4).setCellValue(u.getNationalCode());
                dataRow.createCell(5).setCellValue(u.isEnable());
            }
        }
        workbook.write(out);
        return new ByteArrayInputStream(out.toByteArray());
    }
}

package com.mycompany;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Excel2 {
    public void writeData(){
        try{
            MainClass link = new MainClass();
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Students");

            Row rHeader = sheet.createRow(0);
            rHeader.createCell(0).setCellValue(" Matric ");
            rHeader.createCell(1).setCellValue("    Name    ");
            rHeader.createCell(2).setCellValue(" Github Link");

            for (int i = 0; i <= 2; i++) {
                CellStyle style = workbook.createCellStyle();
                Font font = workbook.createFont();
                font.setBold(true);
                style.setVerticalAlignment(VerticalAlignment.CENTER);
            }

            int r = 1;
            for (Data info1 : link.Scrape1()) {
                Row row = sheet.createRow(r);
                Cell column1 = row.createCell(0);
                column1.setCellValue(info1.getColumn1());

                Cell column2 = row.createCell(0);
                column2.setCellValue(info1.getColumn2());

                Cell column3 = row.createCell(0);
                column3.setCellValue(info1.getColumn3());
                r++;
            }

            for (int i=1; i <= 35; i++)
                sheet.autoSizeColumn(i);

            FileOutputStream output = new FileOutputStream(new File ("\"C:\\Users\\AIDIL\\IdeaProjects\\Asg1"));
            workbook.write(output);
            output.close();
            workbook.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


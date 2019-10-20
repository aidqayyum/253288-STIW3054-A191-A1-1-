package com.mycompany;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.ss.usermodel.*;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Excel {
    public void writeExcel(){
        try{

            wiki getwiki = new wiki();
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("List of Students");

            Row rHeader = sheet.createRow(0);
            rHeader.createCell(0).setCellValue("No");
            rHeader.createCell(1).setCellValue("Matric");
            rHeader.createCell(2).setCellValue("Name");

            for ( int i = 0; i<=2; i++) {
                CellStyle style = workbook.createCellStyle();
                Font font = workbook.createFont();
                font.setBold(true);
                style.setVerticalAlignment(VerticalAlignment.CENTER);
                rHeader.getCell(i).setCellStyle(style);
            }

            int r = 1;
            for (Data info : getwiki.Scrape()) {
                Row row = sheet.createRow(r);
                Cell column1 = row.createCell(0);
                column1.setCellValue(info.getColumn1());

                Cell column2 = row.createCell(1);
                column2.setCellValue(info.getColumn2());

                Cell column3 = row.createCell(2);
                column3.setCellValue(info.getColumn3());
                r++;
            }

            for (int i = 1; i<=35; i++){
                sheet.autoSizeColumn(i);

                FileOutputStream output = new FileOutputStream(new File("C:\\Users\\AIDIL\\IdeaProjects\\Asg1"));
                workbook.write(output);
                output.close();
                workbook.close();
            }


        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}

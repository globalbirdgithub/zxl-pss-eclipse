package com.share.pss.service;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

/**
 * POI文件下载测试类
 * 
 * @author MrZhang
 * @date 2018年4月6日 下午1:21:41
 * @version V1.0
 */
public class POITest {
	@Test
	public void write() throws Exception {
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("sheet0");
		for (int i = 0; i < 10; i++) {
			Row row = sheet.createRow(i);
			for (int j = 0; j < 10; j++) {
				Cell cell = row.createCell(j);
				cell.setCellValue(i * j);
			}
		}
		FileOutputStream fileOutputStream = new FileOutputStream("test.xlsx");
		workbook.write(fileOutputStream);
		fileOutputStream.close();
	}
	@Test
	public void read() throws Exception {
		FileInputStream fileInputStream = new FileInputStream("test.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
		XSSFSheet sheet = workbook.getSheetAt(0);
		int rowNum = sheet.getLastRowNum();
		for (int i = 1; i <= rowNum; i++) {
			XSSFRow row = sheet.getRow(i);
			if(row!=null){
				short cellNum = row.getLastCellNum();
				for (int j = 1; j <= cellNum; j++) {
					XSSFCell cell = row.getCell(j);
					if(cell!=null){
						System.out.print(cell.getNumericCellValue()+"\t");
					}
				}
			}
			System.out.println();
		}
	}
}

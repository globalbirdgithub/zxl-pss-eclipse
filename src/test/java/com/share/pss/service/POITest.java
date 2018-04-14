package com.share.pss.service;

import java.io.FileOutputStream;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
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
	public void testPoi() throws Exception {
		Workbook wb = new XSSFWorkbook();
		CreationHelper creationHelper = wb.getCreationHelper();
		CellStyle cellStyle = wb.createCellStyle();
		cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("m/d/yy h:mm"));
		Sheet sheet = wb.createSheet("POIsheet");
		for (int i = 0; i < 10; i++) {
			Row row = sheet.createRow(i);
			for (int j = 0; j < 11; j++) {
				Cell cell = row.createCell(j);
				if (j == 10) {
					cell.setCellValue(new Date());
					cell.setCellStyle(cellStyle);
				}
				cell.setCellValue(i * j);
			}
		}
		FileOutputStream fileOutputStream = new FileOutputStream("test.xlsx");
		wb.write(fileOutputStream);
		fileOutputStream.close();
	}
}

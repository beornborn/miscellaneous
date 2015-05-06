package service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import models.ParentTask;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class ExcelService {

		public static void writeDataForDistribution(double[] head, int[] val) throws BiffException, IOException, RowsExceededException, WriteException, InvalidFormatException{
			InputStream inp = new FileInputStream("data.xls");
			HSSFWorkbook workbook = (HSSFWorkbook) WorkbookFactory.create(inp);
			HSSFSheet sheet = workbook.getSheetAt(0);
			for (int i=0;i<head.length;i++){
				
				HSSFRow row = sheet.createRow(i);
				HSSFCell cell = row.createCell(0);
				cell.setCellValue(head[i]);
			}
			for (int i=0;i<head.length;i++){
				HSSFRow row = sheet.getRow(i);
				HSSFCell cell = row.createCell(1);
				cell.setCellValue(val[i]);
			}
			FileOutputStream fileOut = new FileOutputStream("data.xls");
	        workbook.write(fileOut);
	        fileOut.close();
		}
}

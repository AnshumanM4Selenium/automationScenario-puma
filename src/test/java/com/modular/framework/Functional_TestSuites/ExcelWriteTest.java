package com.modular.framework.Functional_TestSuites;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import com.modular.framework.Generic_Libraries.LoggerHelper;

public class ExcelWriteTest {
  
	static Logger log = LoggerHelper.writeLog(ExcelWriteTest.class);
	
	//TestWrite.xls
  @Test
  public synchronized void writingTest() throws Throwable {
	  
	  FileInputStream fsIP=null;
	  FileOutputStream fos=null;
	  String caseName = null;
	  	
	  try {
		File fle = new File("C:/Users/Anshuman M/Desktop/NewData.xls");  
		//fsIP= new FileInputStream(new File("D:/AutomationProject/WebAutomation_msupply_2/WebAutomation_msupply_2/src/test/resources/TestWrite.xls"));
		//Access the workbook    
		
		fsIP = new FileInputStream(fle);
		HSSFWorkbook wb = new HSSFWorkbook(fsIP);
		//Access the worksheet, so that we can update / modify it. 
		HSSFSheet worksheet = wb.getSheet("Sheet1");
		//String cellConten = worksheet
		//String content = getfirstColoumnData( "D:/AutomationProject/WebAutomation_msupply_2/WebAutomation_msupply_2/src/test/resources/First.xls", "Sheet5");
		int numRows = worksheet.getPhysicalNumberOfRows();
		
		System.out.println("Total rows in the sheet: "+numRows);
		
		for (int i = 0; i < numRows; i++) {
			
			caseName = getColumnData(worksheet,i);
			
			System.out.println("Testcase name: "+ caseName);
			int ri = findRow(worksheet, caseName);
			System.out.println(" ri"+ri);
			//fsIP.close();
			//RetrieveXlsxData.writeExcelData(Sheet5, rowNum, colNum, data);
			
				
				//Get the control of Row in which we want to write the data
				Row r=worksheet.getRow(ri);
				
				//Create a cell where we want to write the data
				Cell c1=r.createCell(r.getLastCellNum());
				log.info(" new cell created in  "+c1.getColumnIndex());
				//Set the data type of cell what we want to write
				c1.setCellType(Cell.CELL_TYPE_STRING);
				log.info("cell value is "+c1.getStringCellValue());
				 //Pass the data in the cell
				c1.setCellValue("Pass");
				//if(fsIP!=null)
					
				if(fsIP!=null){
					fsIP.close();}
				fos=new FileOutputStream(fle);
				
				//Save the workbook
				wb.write(fos);
				
				
				
		}
		//Close the workbook
		wb.close();
	} 
	catch(Exception e)
	{
	System.out.println("\nLog Message::@AfterMethod: Exception caught");
  	e.printStackTrace();
	}finally {
		if(fsIP!=null)
		fsIP.close();
		if(fos!=null)
		fos.close();
	}
	}
	private String getColumnData(HSSFSheet worksheet, int rows) throws Throwable {
 	
	DataFormatter formatter = new DataFormatter();
	int rowCount = worksheet.getLastRowNum();
	String CellData = null;
	/*for (int i = 0; i < rowCount; i++) {
	int noOfColumns = worksheet.getRow(i).getPhysicalNumberOfCells();*/
//	int noOfColumns = worksheet.getRow(0).getPhysicalNumberOfCells();
//	for (Row row : worksheet) {
//		if (noOfColumns <9) {
//			//int totalCols = worksheet.getRow(i).getLastCellNum(); 
//			Cell cell = row.getCell(noOfColumns, Row.RETURN_BLANK_AS_NULL);
//			if (cell == null) {
//
//				cell = row.getCell(0);
//		    	CellData = formatter.formatCellValue(cell);
//				return CellData;
//			} 
//		}
//	}
	
	int noOfColumns = worksheet.getRow(rows).getPhysicalNumberOfCells();
//	for (Row row : worksheet) {
		if (noOfColumns <9) {
			//int totalCols = worksheet.getRow(i).getLastCellNum(); 
			Cell cell = worksheet.getRow(rows).getCell(noOfColumns, Row.RETURN_BLANK_AS_NULL);
			if (cell == null) {

				cell = worksheet.getRow(rows).getCell(0);
		    	CellData = formatter.formatCellValue(cell);
				return CellData;
			} 
		}
//	}
	return CellData;
	}

	private static int findRow(HSSFSheet sheet, String cellContent) {

		int value = 0;
		for (Row row : sheet) {
			for (Cell cell : row) {
				if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
					if (cell.getRichStringCellValue().getString().trim().equals(cellContent)) {
						value = row.getRowNum();
						return  value;
					}
				}
			}
		}               
		return value;
		
	}
	  
 }


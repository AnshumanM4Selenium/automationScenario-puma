package com.modular.framework.Generic_Libraries;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class RetrieveXlsxData 
{
	static XSSFWorkbook wb;
    static String currentPath = System.getProperty("user.dir");
	
	//Location of the xlsx.sheet
	static String filePath;
	//= currentPath +"/src/test/resources/TestData.xlsx";
	
	//Retrieving data from xlsx.sheet
		
		/*To get the data from excel sheet*/
		/**
		 * This method is used to fetch the data from excel sheet as per the arguments passed by user
		 * @param sheetName
		 * @param rowNum
		 * @param colNum
		 * @return String
		 * @throws InvalidFormatException
		 * @throws IOException
		 */
	
		public static String getExcelData(String sheetName, int rowNum,int colNum) throws IOException, Exception
		{		
		/* Get the file location*/	
		FileInputStream fis=new FileInputStream(filePath);
		
		/* Get the control on the workbook*/
		Workbook wb=WorkbookFactory.create(fis);
		
		/*Get the control of Sheet where data is present*/
		Sheet s1=wb.getSheet(sheetName);
		
		/*Get the control of Row in which data is present*/
		Row r=s1.getRow(rowNum);
		
		String data = null;
		/*Get the data from the cell*/
		if(sheetName.equals("Sheet2"))
		{	
		    if(colNum==2||colNum==6||colNum==7)
		    {
			    DataFormatter formatter = new DataFormatter(); 
		        Cell cell = s1.getRow(rowNum).getCell(colNum);
		        data = formatter.formatCellValue(cell);
		        //System.out.println(data);
		    }
		    else
		    {	
		       data=r.getCell(colNum).getStringCellValue();
		       //System.out.println(data);
		    }						
		}
		else if(sheetName.equals("Sheet3"))
		{
			if(colNum==0)
			{
				DataFormatter formatter = new DataFormatter(); 
		        Cell cell = s1.getRow(rowNum).getCell(colNum);
		        data = formatter.formatCellValue(cell);
		        //System.out.println(data);
			}
			else
			{	
			       data=r.getCell(colNum).getStringCellValue();
			       //System.out.println(data);
			}	
		}
		
		return data;
		}
		
		//Read Data from Excel
		
		public static String[] getExcelData(String sTestID) throws Throwable
		{
			String sData[]=null;				
			File file = new File(filePath);
			FileInputStream fis = new FileInputStream(file);
				
				Workbook wb = WorkbookFactory.create(fis);
				Sheet sht =  wb.getSheet("LoginTestData");
				int LastRowExcel = sht.getLastRowNum();
				int LastCloumnExcel=0;
				for(int i=1;i<=LastRowExcel;i++)
				{
					if(sht.getRow(i).getCell(0).getStringCellValue().equals(sTestID))
					{						
						LastCloumnExcel=sht.getRow(i).getLastCellNum();						
						sData = new String[LastCloumnExcel];
						for(int k=0;k<(LastCloumnExcel);k++)
						{
							sData[k]=sht.getRow(i).getCell(k).getStringCellValue();
						}
					}
				}
			return sData;
			
		}

		
		//Write Data from Excel
		
		public static void overwriteExcelData(String sTestID,int Data,int ColoumnNumberToWriteValue) throws Throwable
		{				
			File file = new File(filePath);
			FileInputStream fis = new FileInputStream(file);
				
				Workbook wb = WorkbookFactory.create(fis);
				Sheet sht =  wb.getSheet("LoginTestData");
				int LastRowExcel = sht.getLastRowNum();
				int LastCloumnExcel=0;
				for(int i=1;i<=LastRowExcel;i++)
				{
					if(sht.getRow(i).getCell(0).getStringCellValue().equals(sTestID))
					{						
						//LastCloumnExcel=sht.getRow(i).getLastCellNum();						
						Cell c1=sht.getRow(i).createCell(ColoumnNumberToWriteValue);
						c1.setCellType(c1.CELL_TYPE_STRING);
						String value=Integer.toString(Data);
						c1.setCellValue(value);
						FileOutputStream fos=new FileOutputStream(filePath);
						wb.write(fos);
						wb.close();
					}
				}
		}

		
					
		
		//Write Data from Excel
		
				public static void writeExcelData(String sTestID,String Data) throws Throwable
				{				
					File file = new File(filePath);
					FileInputStream fis = new FileInputStream(file);
						
						Workbook wb = WorkbookFactory.create(fis);
						Sheet sht =  wb.getSheet("LoginTestData");
						int LastRowExcel = sht.getLastRowNum();
						int LastCloumnExcel=0;
						for(int i=1;i<=LastRowExcel;i++)
						{
							if(sht.getRow(i).getCell(0).getStringCellValue().equals(sTestID))
							{						
								LastCloumnExcel=sht.getRow(i).getLastCellNum();						
								Cell c1=sht.getRow(i).createCell(LastCloumnExcel);
								c1.setCellType(c1.CELL_TYPE_STRING);
								c1.setCellValue(Data);
								FileOutputStream fos=new FileOutputStream(filePath);
								wb.write(fos);
								wb.close();
							}
						}
				}
				
		
		//Read the TestCase ID 
		
		public static String getTestCaseID(int RowNumber) throws Throwable
		{
			String sData[]=null;				
			File file = new File(filePath);
			FileInputStream fis = new FileInputStream(file);
				
				Workbook wb = WorkbookFactory.create(fis);
			    Sheet sht =  wb.getSheet("LoginTestData");			
			    return(sht.getRow(RowNumber).getCell(0).getStringCellValue());
			
		}
		
		
		/*To write the data to excel file*/
		/**
		 * This method is used to write any data in the cell provided in excel sheet
		 * @param sheetName
		 * @param rowNum
		 * @param colNum
		 * @param data
		 * @throws InvalidFormatException
		 * @throws IOException
		 * @throws Exception 
		 */
		public static void writeExcelData(String sheetName, int rowNum, int colNum, String data) throws IOException, Exception, InvalidFormatException
		{
		/* Get the file location*/	
		FileInputStream fis=new FileInputStream(filePath);
		
		/* Get the control on the workbook*/
		Workbook wb=WorkbookFactory.create(fis);
		
		/*Get the control of Sheet where we want to write the data*/
		Sheet s1=wb.getSheet(sheetName);
		
		/*Get the control of Row in which we want to write the data*/
		Row r=s1.getRow(rowNum);
		
		/*Create a cell where we want to write the data*/
		Cell c1=r.createCell(colNum);
		
		/*Set the data type of cell what we want to write*/
		c1.setCellType(c1.CELL_TYPE_STRING);
		
		/* Pass the data in the cell*/
		c1.setCellValue(data);
		
		FileOutputStream fos=new FileOutputStream(filePath);
		
		/*Save the workbook*/
		wb.write(fos);
		
		/*Close the workbook*/
		wb.close();
		
		}
		
		
		
		
	     /*To get the last row count of excel sheet*/
		/**
		 * This method is used to count the number of rows used in the excel sheet. It returns the index of used row.
		 * @param sheetName
		 * @return 
		 * @throws InvalidFormatException
		 * @throws IOException
		 * @throws Exception 
		 */
		public static int rowCount(String sheetName) throws IOException, Exception, InvalidFormatException
		{
			/* Get the file location*/	
			FileInputStream fis=new FileInputStream(filePath);
			
			/* Get the control on the workbook*/
			Workbook wb=WorkbookFactory.create(fis);
			
			/*Get the control of Sheet where we want to write the data*/
			Sheet s=wb.getSheet(sheetName);
			
			/*Get the count of used row in excel sheet*/
			int rows=s.getLastRowNum()+1;
			
			return rows;
			
		}
		/**
		 * This method is used to get the value from the cells of rows used in the .xls sheet.
		 * It returns the two dimensional array containing the values in a row.
		 * @param sheetName,File path
		 * @return String [][]
		 * @throws InvalidFormatException
		 * @throws IOException
		 * @throws Exception 
		 */
		
		private static HSSFSheet ExcelWSheet;
		 
		private static HSSFWorkbook ExcelWBook;

		private static HSSFCell Cell;

		private static HSSFRow Row;
		
		/*public int getColumns(){
			
			
			return 0;
		}*/
		public static String[][] getTableArray(String FilePath, String SheetName)    throws Exception
		 
		{   
			
		   FileInputStream ExcelFile = null;
		   String[][] tabArray = null;

		   try{
			   
			   filePath = currentPath + FilePath;
			   
			   System.out.println("  Path of file is :--------"+filePath);
			   ExcelFile = new FileInputStream(filePath);

			   // Access the required test data sheet

			   ExcelWBook = new HSSFWorkbook(ExcelFile);

			   ExcelWSheet = ExcelWBook.getSheet(SheetName);

			   int startCol = 1;

			   int ci=0;

			   int totalRows = ExcelWSheet.getLastRowNum();
			   
			   int totalCols = ExcelWSheet.getRow(0).getLastCellNum();
			   System.out.println(totalRows);
			
			   System.out.println(totalCols);
			   	tabArray=new String[totalRows+1][totalCols];
			   	DataFormatter formatter = new DataFormatter();
				   for (int j=0;j<=totalRows;j++, ci++)

				   {
					   int cj=0;
					   for (int l = 1; l < totalCols; l++,cj++) {
						//tabArray[ci][cj] = getCellData(j, l);
						 Cell = ExcelWSheet.getRow(j).getCell(l);

						 String CellData = formatter.formatCellValue(Cell);
						 System.out.println(CellData);
						 
						 tabArray[ci][cj] = CellData;
						 
						System.out.println(j);
						System.out.println(l);
						System.out.println(tabArray[ci][cj]);
						System.out.println("tabArray["+ci+"]["+cj+"]: "+tabArray[ci][cj]);
					}
					   

				   }
				   System.out.println(tabArray);
			}

			catch (FileNotFoundException e)

			{

				System.out.println("Could not read the Excel sheet");

				e.printStackTrace();

			}

			catch (IOException e)

			{

				System.out.println("Could not read the Excel sheet");

				e.printStackTrace();

			}finally {
				
				ExcelFile.close();
			}

			return tabArray;

		}
		
		public static String getCellData(int RowNum, int ColNum) throws Exception{
			 
			   try{
				  DataFormatter formatter = new DataFormatter();
				  Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);

				  String CellData = formatter.formatCellValue(Cell);
				  //Cell.getStringCellValue();

				  return CellData;

				  }catch (Exception e){

					return"";

					}

				}
		
		public static String getTestCaseName(String sTestCase)throws Exception{
			 
			String value = sTestCase;

			try{

				int posi = value.indexOf("@");

				value = value.substring(0, posi);

				posi = value.lastIndexOf(".");	

				value = value.substring(posi + 1);

				return value;

					}catch (Exception e){

				throw (e);

						}

			}
		
		
	
}

package com.modular.framework.Functional_TestSuites;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import com.google.common.collect.Lists;
import com.modular.framework.Generic_Libraries.CommonFunctions;
import com.modular.framework.Generic_Libraries.LoggerHelper;
import com.modular.framework.Generic_Libraries.WebDriverCommonFunctions;
import com.modular.framework.InitWebdriver.InitDriver;

public class QuoteConsideredLatestTest {
	
		static Logger log = LoggerHelper.writeLog(QuoteConsideredLatestTest.class);
		Integer rowNumber = 0;
		List<String> detailsList;
		File fle = new File(
				"D:/AutomationProject/WebAutomation_msupply_2/WebAutomation_msupply_2/src/test/resources/QuoteConsideredData.xls");
		String caseName = null;
		FileInputStream fsIP = null;
		FileOutputStream fos = null;

		Map<String, String> traceMap;

		@DataProvider(name = "dataTest12")
		public static Object[][] validationC() throws Exception {

			String[][] testDataArr = getTableArray(
					"D:/AutomationProject/WebAutomation_msupply_2/WebAutomation_msupply_2/src/test/resources/QuoteConsideredData.xls",
					"Sheet1");
			return testDataArr;
		}

		@Test(dataProvider = "dataTest12")
		public void fetchQuoteDetails(String testCase, String enquiryid)
				throws Throwable {

			CommonFunctions.LoadPageExpicitWait();
			InitDriver.driver.navigate().refresh();
			Thread.sleep(3000);

			/*
			 * WebElement myEnquiriesLink =
			 * InitDriver.driver.findElement(By.linkText("My Enquiries"));
			 * locateElementToClick(myEnquiriesLink);
			 */
			WebDriverCommonFunctions.element_Click("My_Enquiries_Xpath",
					"My Enquiries link is clicked");
			log.info("My enquiry link is clicked");

			CommonFunctions.LoadPageExpicitWait();

			WebDriverCommonFunctions.element_Click("QuoteConsidered_link_xpath",
					"QuoteConsidered link clicked");

			new Select(InitDriver.driver.findElement(By.name("viewItemPerPage")))
					.selectByValue("number:50");

			CommonFunctions.LoadPageExpicitWait();
			selectEnquiryId(enquiryid);

			// writeDetails(quoteDetails());

			List<WebElement> theadLabels = InitDriver.driver
					.findElements(By
							.xpath("html/body/div[3]/ng-view/div[3]/div/div[3]/div[1]/div[2]/table/thead/tr/th"));
			
			System.out.println("theadLabels size :"+theadLabels.size());
			List<WebElement> tbodyTD = InitDriver.driver.findElements(By
					.xpath("//div[@class='custquoteviewdetails']//tbody//td"));

			List<WebElement> theadLabels2 = InitDriver.driver.findElements(By.xpath("//tr[@id='addBrandTbl']/td/table/thead/tr/th"));
			
			System.out.println("theadLabels2 size :"+theadLabels2.size());
			
			List<WebElement> tbodyTD2 = InitDriver.driver.findElements(By.xpath("//tr[@id='addBrandTbl']/td/table/tbody/tr/td[not(@colspan)]"));
			System.out.println("tbodyTD2 size after append :"+tbodyTD2.size());
			
			List<WebElement> theadList = new ArrayList<>();
			theadList.addAll(theadLabels);
			theadList.addAll(theadLabels2);
			
			
			
			//theadLabels.addAll(theadLabels2);
			System.out.println("theadLabels size after append :"+theadList.size());
			
			
			 for(int i = 0; i < theadList.size(); i++) {
		            System.out.println("Data in "+i+" text box "+theadList.get(i).getText());
		        }

			log.info("TestCase " + testCase);
			log.info("EnquiryId " + enquiryid);
			

			/*for (List<WebElement> partition : Lists.partition(tbodyTD, 7)) {
				

				writingDetailsBack(testCase, enquiryid,
						gettingDeatils(theadLabels, partition));
				
				
				 * count++; if (count <= (tbodyTD.size()/7)) {
				 * 
				 * copyRow(testCase); }
				 
			}*/
			
			for (int i = 0,j = 0; i < Lists.partition(tbodyTD, 7).size(); i++,j++) {
				
				List<WebElement> list1 = Lists.partition(tbodyTD, 7).get(i);
				List<WebElement> litf = new ArrayList<>();
				litf.addAll(list1);
				litf.addAll(Lists.partition(tbodyTD2, 4).get(j));
				
				for(int k = 0; k < litf.size(); k++) {
		            System.out.println("Data in "+k+" text box "+litf.get(k).getText());
		        }
				writingDetailsBack(testCase, enquiryid,
						gettingDeatils(theadList, litf));
			}
			
			

		}
		
		

		

		public int getRowNum(HSSFSheet sheet) {
			int value = 0;
			HSSFRow row;
			int rorNum = sheet.getPhysicalNumberOfRows();
			for (int i = 0; i < rorNum; i++) {

				int c = sheet.getRow(i).getFirstCellNum();
				int r = sheet.getRow(i).getLastCellNum();
				row = sheet.getRow(i);
				System.out.println(row.getCell(i));
				if (c == r) {

					value = row.getRowNum();
					return value;

				} else {
					continue;
				}
			}
			/*
			 * for (int c = row.getFirstCellNum(); c < row.getLastCellNum(); c++) {
			 * Cell cell = row.getCell(c); if (cell != null && cell.getCellType() !=
			 * Cell.CELL_TYPE_BLANK) return false; } return true;
			 */

			return value;

		}

		public void writingDetailsBack(String testData, String enquiryId,
			Map<String, String> map) throws Throwable {

			File filer = new File(
					"C:/Users/Anshuman M/Desktop/TestR1.xls");
			FileInputStream fisr = new FileInputStream(filer);
			FileOutputStream fileOut;
			HSSFWorkbook wrb = new HSSFWorkbook(fisr);
			HSSFSheet sheetr = wrb.getSheet("Sheet1");

//			int numRows = sheetr.getPhysicalNumberOfRows();
			System.out.println("MAP:    "+map);

			System.out.println("ROW :  "+rowNumber);
			/*
			 * if(fisr!=null){ fisr.close(); }
			 */
			Row r = sheetr.createRow(rowNumber);
			
			rowNumber = rowNumber+1;
			
			map.put("TestCases", testData);
			map.put("Enquiry ID", enquiryId);

			Cell c1;

			for (Map.Entry<String, String> entry : map.entrySet()) {
				System.out.println("Key : " + entry.getKey() + " Value : "
						+ entry.getValue());

				switch (entry.getKey()) {

				case "TestCases":

					log.info(r.getLastCellNum());
					c1 = r.createCell(0, Cell.CELL_TYPE_STRING);
					log.info(" new cell created in  " + c1.getColumnIndex());

					// Set the data type of cell what we want to write
					// c1.setCellType(Cell.CELL_TYPE_STRING);

					// Pass the data in the cell
					c1.setCellValue(entry.getValue());
					break;

				case "Enquiry ID":

					log.info(r.getLastCellNum());
					c1 = r.createCell(1, Cell.CELL_TYPE_STRING);
					log.info(" new cell created in  " + c1.getColumnIndex());

					// Set the data type of cell what we want to write
					// c1.setCellType(Cell.CELL_TYPE_STRING);

					// Pass the data in the cell
					c1.setCellValue(entry.getValue());
					break;
				case "Sl No.":

					log.info(r.getLastCellNum());
					c1 = r.createCell(2, Cell.CELL_TYPE_STRING);
					log.info(" new cell created in  " + c1.getColumnIndex());

					// Set the data type of cell what we want to write
					// c1.setCellType(Cell.CELL_TYPE_STRING);

					// Pass the data in the cell
					c1.setCellValue(entry.getValue());
					break;

				case "Material":

					log.info(r.getLastCellNum());
					c1 = r.createCell(3, Cell.CELL_TYPE_STRING);
					log.info(" new cell created in  " + c1.getColumnIndex());

					// Set the data type of cell what we want to write
					// c1.setCellType(Cell.CELL_TYPE_STRING);

					// Pass the data in the cell
					c1.setCellValue(entry.getValue());
					break;

				case "Exp. Brand":

					log.info(r.getLastCellNum());
					c1 = r.createCell(4, Cell.CELL_TYPE_STRING);
					log.info(" new cell created in  " + c1.getColumnIndex());

					// Set the data type of cell what we want to write
					// c1.setCellType(Cell.CELL_TYPE_STRING);

					// Pass the data in the cell
					c1.setCellValue(entry.getValue());
					break;

				case "Specification":

					log.info(r.getLastCellNum());
					c1 = r.createCell(5, Cell.CELL_TYPE_STRING);
					log.info(" new cell created in  " + c1.getColumnIndex());

					// Set the data type of cell what we want to write
					// c1.setCellType(Cell.CELL_TYPE_STRING);

					// Pass the data in the cell
					c1.setCellValue(entry.getValue());
					break;

				case "Category":

					// log.info(r.getLastCellNum());
					c1 = r.createCell(6, Cell.CELL_TYPE_STRING);
					log.info(" new cell created in  " + c1.getColumnIndex());

					// Set the data type of cell what we want to write
					// c1.setCellType(Cell.CELL_TYPE_STRING);

					// Pass the data in the cell
					c1.setCellValue(entry.getValue());
					break;

				case "Qty":

					log.info(r.getLastCellNum());
					c1 = r.createCell(7, Cell.CELL_TYPE_STRING);
					log.info(" new cell created in  " + c1.getColumnIndex());

					// Set the data type of cell what we want to write
					// c1.setCellType(Cell.CELL_TYPE_STRING);

					// Pass the data in the cell
					c1.setCellValue(entry.getValue());
					break;

				case "Remarks":

					log.info(r.getLastCellNum());
					c1 = r.createCell(8, Cell.CELL_TYPE_STRING);
					log.info(" new cell created in  " + c1.getColumnIndex());

					// Set the data type of cell what we want to write
					// c1.setCellType(Cell.CELL_TYPE_STRING);

					// Pass the data in the cell
					c1.setCellValue(entry.getValue());
					break;
				
				case "Brand":

					log.info(r.getLastCellNum());
					c1 = r.createCell(9, Cell.CELL_TYPE_STRING);
					log.info(" new cell created in  " + c1.getColumnIndex());

					// Set the data type of cell what we want to write
					// c1.setCellType(Cell.CELL_TYPE_STRING);

					// Pass the data in the cell
					c1.setCellValue(entry.getValue());
					break;
				
				case "Base Rate (INR)":

					log.info(r.getLastCellNum());
					c1 = r.createCell(10, Cell.CELL_TYPE_STRING);
					log.info(" new cell created in  " + c1.getColumnIndex());

					// Set the data type of cell what we want to write
					// c1.setCellType(Cell.CELL_TYPE_STRING);

					// Pass the data in the cell
					c1.setCellValue(entry.getValue());
					break;
				
				case "Amount (INR)":

					log.info(r.getLastCellNum());
					c1 = r.createCell(11, Cell.CELL_TYPE_STRING);
					log.info(" new cell created in  " + c1.getColumnIndex());

					// Set the data type of cell what we want to write
					// c1.setCellType(Cell.CELL_TYPE_STRING);

					// Pass the data in the cell
					c1.setCellValue(entry.getValue());
					break;
				
				case "VAT (%)":

					log.info(r.getLastCellNum());
					c1 = r.createCell(12, Cell.CELL_TYPE_STRING);
					log.info(" new cell created in  " + c1.getColumnIndex());

					// Set the data type of cell what we want to write
					// c1.setCellType(Cell.CELL_TYPE_STRING);

					// Pass the data in the cell
					c1.setCellValue(entry.getValue());
					break;
				
				default:
					break;
				}

			}

			if (fisr != null) {
				fisr.close();
			}
			fileOut = new FileOutputStream(filer);

			// Save the workbook
			wrb.write(fileOut);

			// Close the workbook
			wrb.close();
		}

		private String getColumnData(HSSFSheet worksheet) throws Throwable {

			DataFormatter formatter = new DataFormatter();
			// int rowCount = worksheet.getLastRowNum();
			int numRows = worksheet.getPhysicalNumberOfRows();
			String CellData = null;

			for (int i = 1; i <= numRows; i++) {
				int noOfColumns = worksheet.getRow(i).getPhysicalNumberOfCells();
				log.info("last cell value "
						+ worksheet.getRow(i).getCell(noOfColumns));
				if (worksheet.getRow(i).getCell(noOfColumns) == null
						&& worksheet.getRow(i).getPhysicalNumberOfCells() < 3) {
					Cell cell = worksheet.getRow(i).getCell((noOfColumns),
							Row.RETURN_BLANK_AS_NULL);
					if (cell == null) {

						cell = worksheet.getRow(i).getCell(0);
						CellData = formatter.formatCellValue(cell);
						return CellData;
					}
				}

				// int totalCols = worksheet.getRow(i).getLastCellNum();

			}

			return CellData;
		}

		private List<String> quoteDetails() {

			detailsList = new ArrayList<String>();
			List<WebElement> tbodyLabels = InitDriver.driver
					.findElements(By
							.xpath("//div[@class='custquoteviewdetails']//tbody/tr/td/div/div//span[@class='ng-binding ng-scope']"));
			// List<WebElement> theadLabels =
			// InitDriver.driver.findElements(By.xpath("html/body/div[3]/ng-view/div[3]/div/div[3]/div[1]/div[2]/table/thead/tr/th"));

			/*
			 * for (int i = 0, k = 2; i < tbodyLabels.size(); i++, k++) {
			 * 
			 * detailsList.add(tbodyLabels.get(i).getText()); }
			 */

			List<WebElement> tbodyLabels1 = InitDriver.driver.findElements(By
					.xpath("//div[@class='custquoteviewdetails']//tbody//td/div"));

			for (int i = 0, l = 0, k = 0; i < tbodyLabels1.size(); i++) {
				if (tbodyLabels1.get(i).getAttribute("class")
						.contains("text-ellipses ng-binding")) {

					detailsList.add(tbodyLabels1.get(i).getText());
				} else {

					detailsList.add(tbodyLabels.get(k).getText());
					k++;
				}
			}
			for (String dt : detailsList) {
				System.out.println(dt);
			}

			return detailsList;
		}

		private void selectEnquiryId(String enquiryid) throws Throwable {

			List<WebElement> enquiryIds = InitDriver.driver
					.findElements(By
							.xpath("//table[@class='custom-enqtable']/tbody/tr/td//div[contains(text(),'Enquiry Number')]/following-sibling::div"));

			List<WebElement> quoteBtns = InitDriver.driver
					.findElements(By
							.xpath("//table[@class='custom-enqtable']/tbody/tr/td//a[contains(text(),'View Quote')]"));

			for (int i = 0; i < enquiryIds.size() && i < quoteBtns.size(); i++) {

				if (enquiryIds.get(i).getText().contains(enquiryid)) {

					Thread.sleep(2000);
					// quoteBtns.get(i).click();
					locateElementToClick(quoteBtns.get(i));
					break;
				}
			}

		}

		private void locateElementToClick(WebElement webElement) throws Throwable {
			CommonFunctions.SearchForElement(webElement);// Common functions-4
			try {
				webElement.click();
			} catch (Exception e) {
				CommonFunctions.SearchForElement_Method_2(webElement, "CLICK");
			}
			CommonFunctions.LoadPageExpicitWait(); // Common functions-5
			CommonFunctions.scrollPageUp(0, -1000); // Common functions-6

		}

		// excel related methods.
		private static HSSFSheet ExcelWSheet;

		private static HSSFWorkbook ExcelWBook;

		private static HSSFCell Cell;

		private static HSSFRow Row;

		private static String[][] getTableArray(String FilePath, String SheetName)
				throws Exception

		{

			FileInputStream ExcelFile = null;
			String[][] tabArray = null;

			try {

				ExcelFile = new FileInputStream(FilePath);

				// Access the required test data sheet

				ExcelWBook = new HSSFWorkbook(ExcelFile);

				ExcelWSheet = ExcelWBook.getSheet(SheetName);

				int startCol = 1;

				int ci = 0;

				int totalRows = ExcelWSheet.getPhysicalNumberOfRows();

				int totalCols = ExcelWSheet.getRow(1).getLastCellNum();
				int totalCols2 = ExcelWSheet.getRow(1).getPhysicalNumberOfCells();
				System.out.println(totalRows);

				System.out.println(totalCols);
				tabArray = new String[totalRows][totalCols];
				DataFormatter formatter = new DataFormatter();
				for (int j = 1; j < totalRows; j++, ci++)

				{
					// totalCols = ExcelWSheet.getRow(j).getPhysicalNumberOfCells();
					// tabArray=new String[totalRows][totalCols];
					int cj = 0;
					for (int l = 0; l < totalCols; l++, cj++) {
						// tabArray[ci][cj] = getCellData(j, l);
						Cell = ExcelWSheet.getRow(j).getCell(l);

						String CellData = formatter.formatCellValue(Cell);
						System.out.println(CellData);

						tabArray[ci][cj] = CellData;

						System.out.println(j);
						System.out.println(l);
						System.out.println(tabArray[ci][cj]);
						System.out.println("tabArray[" + ci + "][" + cj + "]: "
								+ tabArray[ci][cj]);
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

			} finally {

				ExcelFile.close();
			}

			return tabArray;

		}

		private static int findRow(HSSFSheet sheet, String cellContent) {

			int value = 0;
			for (Row row : sheet) {
				for (Cell cell : row) {
					if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
						if (cell.getRichStringCellValue().getString().trim()
								.equals(cellContent)) {
							value = row.getRowNum();
							return value;
						}
					}
				}
			}
			return value;

		}

		private Map<String, String> gettingDeatils(List<WebElement> theadLabels,
				List<WebElement> body) {

			traceMap = new HashMap<>();
			for (WebElement dt : body) {
				System.out.println(dt.getText());
			}
			/*
			 * theadLabels = InitDriver.driver.findElements(By.xpath(
			 * "html/body/div[3]/ng-view/div[3]/div/div[3]/div[1]/div[2]/table/thead/tr/th"
			 * )); tbodyTD = InitDriver.driver.findElements(By.xpath(
			 * "//div[@class='custquoteviewdetails']//tbody//td"));
			 */

			for (Iterator iterator = body.iterator(), iterator2 = theadLabels
					.iterator(); iterator.hasNext(); iterator2.hasNext()) {

				WebElement webElement = (WebElement) iterator.next();
				WebElement webElement2 = (WebElement) iterator2.next();

				traceMap.put(webElement2.getText(), webElement.getText());
			}

			for (String name : traceMap.keySet()) {

				String key = name.toString();
				String value = traceMap.get(name).toString();
				System.out.println(key + " " + value);
			}
			return traceMap;
		}

		public void writeMap(Map<String, String> map) throws Throwable {

			try {
				fsIP = new FileInputStream(fle);
				HSSFWorkbook wb = new HSSFWorkbook(fsIP);
				// Access the worksheet, so that we can update / modify it.
				HSSFSheet worksheet = wb.getSheet("Sheet1");
				int numRows = worksheet.getPhysicalNumberOfRows();
				System.out.println("Total rows in the sheet: " + numRows);

				caseName = getColumnData(worksheet);

				System.out.println("Testcase name: " + caseName);
				int ri = findRow(worksheet, caseName);
				System.out.println(" ri :" + ri);
				if (fsIP != null) {
					fsIP.close();
				}

				// Get the control of Row in which we want to write the data
				Row r = worksheet.getRow(ri);

				// Create a cell where we want to write the data
				Cell c1;

				for (Map.Entry<String, String> entry : map.entrySet()) {
					System.out.println("Key : " + entry.getKey() + " Value : "
							+ entry.getValue());

					switch (entry.getKey()) {
					case "Sl No.":

						log.info(r.getLastCellNum());
						c1 = r.createCell(2, Cell.CELL_TYPE_STRING);
						log.info(" new cell created in  " + c1.getColumnIndex());

						// Set the data type of cell what we want to write
						// c1.setCellType(Cell.CELL_TYPE_STRING);

						// Pass the data in the cell
						c1.setCellValue(entry.getValue());
						break;

					case "Material":

						log.info(r.getLastCellNum());
						c1 = r.createCell(3, Cell.CELL_TYPE_STRING);
						log.info(" new cell created in  " + c1.getColumnIndex());

						// Set the data type of cell what we want to write
						// c1.setCellType(Cell.CELL_TYPE_STRING);

						// Pass the data in the cell
						c1.setCellValue(entry.getValue());
						break;

					case "Exp. Brand":

						log.info(r.getLastCellNum());
						c1 = r.createCell(4, Cell.CELL_TYPE_STRING);
						log.info(" new cell created in  " + c1.getColumnIndex());

						// Set the data type of cell what we want to write
						// c1.setCellType(Cell.CELL_TYPE_STRING);

						// Pass the data in the cell
						c1.setCellValue(entry.getValue());
						break;

					case "Specification":

						log.info(r.getLastCellNum());
						c1 = r.createCell(5, Cell.CELL_TYPE_STRING);
						log.info(" new cell created in  " + c1.getColumnIndex());

						// Set the data type of cell what we want to write
						// c1.setCellType(Cell.CELL_TYPE_STRING);

						// Pass the data in the cell
						c1.setCellValue(entry.getValue());
						break;

					case "Category":

						log.info(r.getLastCellNum());
						c1 = r.createCell(6, Cell.CELL_TYPE_STRING);
						log.info(" new cell created in  " + c1.getColumnIndex());

						// Set the data type of cell what we want to write
						// c1.setCellType(Cell.CELL_TYPE_STRING);

						// Pass the data in the cell
						c1.setCellValue(entry.getValue());
						break;

					case "Qty":

						log.info(r.getLastCellNum());
						c1 = r.createCell(7, Cell.CELL_TYPE_STRING);
						log.info(" new cell created in  " + c1.getColumnIndex());

						// Set the data type of cell what we want to write
						// c1.setCellType(Cell.CELL_TYPE_STRING);

						// Pass the data in the cell
						c1.setCellValue(entry.getValue());
						break;

					case "Remarks":

						log.info(r.getLastCellNum());
						c1 = r.createCell(8, Cell.CELL_TYPE_STRING);
						log.info(" new cell created in  " + c1.getColumnIndex());

						// Set the data type of cell what we want to write
						// c1.setCellType(Cell.CELL_TYPE_STRING);

						// Pass the data in the cell
						c1.setCellValue(entry.getValue());
						break;

					default:
						break;
					}

				}
				/*
				 * for (int i = 2, j = 0; i < list.size(); i++, j++) {
				 * 
				 * log.info(r.getLastCellNum()); c1 = r.createCell(i);
				 * log.info(" new cell created in  "+c1.getColumnIndex());
				 * 
				 * //Set the data type of cell what we want to write
				 * c1.setCellType(Cell.CELL_TYPE_STRING);
				 * 
				 * //Pass the data in the cell c1.setCellValue(list.get(j)); }
				 */

				/*
				 * log.info(r.getLastCellNum());
				 * log.info(" new cell created in  "+c1.getColumnIndex());
				 * 
				 * //Set the data type of cell what we want to write
				 * c1.setCellType(Cell.CELL_TYPE_STRING);
				 * 
				 * //Pass the data in the cell c1.setCellValue("Pass");
				 */

				if (fsIP != null) {
					fsIP.close();
				}
				fos = new FileOutputStream(fle);

				// Save the workbook
				wb.write(fos);

				// Close the workbook
				wb.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (fsIP != null)
					try {
						fsIP.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				if (fos != null)
					try {
						fos.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		}



}

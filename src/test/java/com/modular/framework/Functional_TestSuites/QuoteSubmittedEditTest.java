package com.modular.framework.Functional_TestSuites;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.modular.framework.Generic_Libraries.CommonFunctions;
import com.modular.framework.Generic_Libraries.LoggerHelper;
import com.modular.framework.Generic_Libraries.RetrieveXlsxData;
import com.modular.framework.Generic_Libraries.WebDriverCommonFunctions;
import com.modular.framework.InitWebdriver.InitDriver;

public class QuoteSubmittedEditTest {
	
	static Logger log = LoggerHelper.writeLog(QuoteSubmittedEditTest.class);
	static String Parent;
	static String Child;
	
	
	@DataProvider(name = "dataTest12")
	public static Object[][] validationC() throws Exception {

		String[][] testDataArr = RetrieveXlsxData.getTableArray(
				"D:/WebAutomation_msupply_R/WebAutomation_msupply_2/src/test/resources/QuoteEdit.xls",
				"Sheet1");
		return testDataArr;
	}

	@Test(dataProvider = "dataTest12")
	public void submitQuoteNew(String enquiryid,String brand,String baseRate,String vat,String deliveryType,String creditDays,
			String deliveryCharge,String deliverytime1,String cst,String data ) throws Throwable{
		
		CommonFunctions.LoadPageExpicitWait();
		InitDriver.driver.navigate().refresh();
		Thread.sleep(3000);
		
		/*WebElement myEnquiriesLink = InitDriver.driver.findElement(By.linkText("My Enquiries"));
		locateElementToClick(myEnquiriesLink);*/
		WebDriverCommonFunctions.element_Click("My_Enquiries_Xpath","My Enquiries link is clicked");
		log.info("My enquiry link is clicked");
		
		WebDriverCommonFunctions.element_Click("QuoteSubmitted_link_xpath",
				"QuoteConsidered link clicked");
		CommonFunctions.LoadPageExpicitWait();
		new Select(InitDriver.driver.findElement(By.name("viewItemPerPage"))).selectByValue("number:50");
		//WebDriverCommonFunctions.element_SelectDropDown("View_Item_PerPage", "VISIBLETEXT", 0, displayingInquirie, "Total no. of items per page selected");
		
		// Selecting enquiries id to quote
		
		Thread.sleep(2000);
		selectEnquiryId(enquiryid);
		log.info(" View Enquiry link is clicked for the selected Enquiry");
		
		Thread.sleep(3000);
		//CommonFunctions.scrollDownPage(0, 350);
		/*WebElement quoteBtn = InitDriver.driver.findElement(By.xpath("html/body/div[3]/ng-view/div[7]/button"));
		locateElementToClick(quoteBtn);*/
		WebDriverCommonFunctions.element_Click("EditQuote_Button_xpath", "Edit Quote button clicked");
		
		Thread.sleep(3000);

		log.info("filling the price");
		
		CommonFunctions.scrollPageUp(0,-1000);
		
		supplierInputs(brand, baseRate, vat, deliveryType, creditDays,deliveryCharge, deliverytime1, cst);
		
		InitDriver.driver.navigate().back();
	}

	private void supplierInputs(String brand, String baseRate, String vat, String deliveryType, String creditDays,
		String deliveryCharged, String deliverytime1, String cst) throws Throwable {
		
		log.info("Brand suggested : " + brand);
		log.info("B Rate  : " + baseRate);
		log.info("VAT  : " + vat);
		log.info("Payment option  : " + deliveryType);
		if (deliveryType.contains("On Credit")) {
			log.info("Days to repay credit :" + creditDays);
		}
		
		log.info("Deliver Charges if any :"+ deliveryCharged);
		log.info("Delivery within :"+deliverytime1);
		
		List<WebElement> inputFields = InitDriver.driver
				.findElements(By.xpath("//tr[@id='addBrandTbl']//tbody//input"));
		log.info(".......Itdentifying no. of fields........");
		log.info("Total input fields :" + inputFields.size());
		for (int i = 0; i < inputFields.size(); i++) {

			String ngModel = inputFields.get(i).getAttribute("ng-model");
			log.info(ngModel);
			if (ngModel.contains("product.brand")) {
				if (inputFields.get(i).getAttribute("class").contains("ng-valid ng-empty")) {
					
					inputFields.get(i).sendKeys(brand);
					//locateElementToEnterValues(inputFields.get(i), data1);
				}
				inputFields.get(i).clear();
				inputFields.get(i).sendKeys(brand);
			} else if ((inputFields.get(i).getAttribute("ng-model")).contains("product.getRate")) {
				
				if (inputFields.get(i).getAttribute("class").contains("ng-valid ng-empty")) {
					
					inputFields.get(i).sendKeys(baseRate);
					//locateElementToEnterValues(inputFields.get(i), data1);
				}
				inputFields.get(i).clear();
				inputFields.get(i).sendKeys(baseRate);

			} else if ((inputFields.get(i).getAttribute("ng-model")).contains("product.VAT")) {

				if (inputFields.get(i).getAttribute("class").contains("ng-valid ng-empty")) {
					
					inputFields.get(i).sendKeys(vat);
					//locateElementToEnterValues(inputFields.get(i), data1);
				}
				inputFields.get(i).clear();
				inputFields.get(i).sendKeys(vat);

			} else if (deliveryType.contains("On Credit")
					&& (inputFields.get(i).getAttribute("ng-model").contains("temp.paymentMode"))) {

				if (inputFields.get(i).isEnabled()) {
					inputFields.get(i).click();
				}
				/*WebElement timeDays = InitDriver.driver
						.findElement(By.xpath(".//*[@id='addBrandTbl']/td/table/tbody/tr[2]/td/div/div[2]/label[1]/input[2]"));
				locateElementToEnterValues(timeDays, creditDays);*/
				WebDriverCommonFunctions.element_EnterValuesToTextField("CreditBox_xpath", creditDays, "Days for availing credit lines entered");
				break;

			} else if (deliveryType.contains("On Delivery")
					&& (inputFields.get(i).getAttribute("value").contains("onDelivery"))) {
				
				if (inputFields.get(i).isEnabled()) {
					inputFields.get(i).click();
				}
				
				//locateElementToClick(inputFields.get(i));
				break;
			}
		}

		/*WebElement datePicker = InitDriver.driver.findElement(By.xpath("html/body/div[3]/ng-view/div[3]/div[3]/div[1]/span[1]/img"));
		locateElementToClick(datePicker);*/
		WebDriverCommonFunctions.element_Click("DatePicker_Icon_xpath", "DatePicker icon clicked");
		
		/*WebElement validUpto = InitDriver.driver.findElement(By.xpath("html/body/div[4]/div[1]/table/tbody/tr[5]/td[4]"));
		locateElementToClick(validUpto);*/
		WebDriverCommonFunctions.element_Click("Date_selected_xpath", "Date till the offer is valid selected");
		
		/*WebElement deliveryCharges = InitDriver.driver.findElement(By.id("deliveryCharges"));
		locateElementToEnterValues(deliveryCharges, deliveryCharged);*/
		WebDriverCommonFunctions.element_EnterValuesToTextField("DeliveryCharges_TextBox_xpath", deliveryCharged, "The amount to be charged on delivery");
		
		/*WebElement deliveryTime = InitDriver.driver.findElement(By.id("deliverytime1"));
		locateElementToEnterValues(deliveryTime, deliverytime1);*/
		WebDriverCommonFunctions.element_EnterValuesToTextField("Deliverytime_TextBox_xpath", deliverytime1, "Days in which the product to be delivered is entered");
		
		/*WebElement CST = InitDriver.driver
		.findElement(By.xpath("html/body/div[3]/ng-view/div[3]/div[3]/div[2]/span/select"));
		locateElementToSelect(CST, 2);*/
		WebDriverCommonFunctions.element_SelectDropDown("CST_Dropdown_xpath", "VISIBLETEXT", 0, cst, "CST added "+cst);
		
		//CommonFunctions.scrollDownPage(0, 250);
		//InitDriver.driver.findElement(By.xpath("html/body/div[3]/ng-view/div[3]/div[5]/textarea")).sendKeys("Fill the text area");
		WebDriverCommonFunctions.element_EnterValuesToTextField("Review_Textbox_xpath", "Fill the text area", "Remarks entered");
		
		//CommonFunctions.scrollDownPage(0, 250);
		//InitDriver.driver.findElement(By.xpath("html/body/div[3]/ng-view/div[3]/div[7]/div[1]/div/label")).click();
		WebDriverCommonFunctions.element_Click("TermsConditions_Checkbox_xpath", "Check box selected");
		
		//CommonFunctions.scrollDownPage(0, 250);
		
		WebDriverCommonFunctions.element_Click("Back_Button_xpath", "Back button clicked");
	}

	private void locateElementToSelect(WebElement element, int index) throws Throwable {
		
		CommonFunctions.scrollPageUp(0,-1000);//Common functions-3
		CommonFunctions.SearchForElement(element);//Common functions-4
		new Select(element).selectByIndex(index);
		CommonFunctions.LoadPageExpicitWait(); //Common functions-5
		CommonFunctions.scrollPageUp(0,-1000); //Common functions-6
		
	}

	private void locateElementToEnterValues(WebElement element, String value) throws Throwable {
		
		CommonFunctions.scrollPageUp(0,-1000);//Common functions-3
		CommonFunctions.SearchForElement(element);//Common functions-4
		element.clear();
		element.sendKeys(value);
	}

	private void selectEnquiryId(String enquiryid) throws Throwable {
		
		List<WebElement> enquiryIds = InitDriver.driver.findElements(By.xpath(
				"//table[@class='custom-enqtable']/tbody/tr/td//div[contains(text(),'Enquiry Number')]/following-sibling::div"));

		List<WebElement> quoteBtns = InitDriver.driver.findElements(
				By.xpath("//table[@class='custom-enqtable']/tbody/tr/td//a[contains(text(),'View Quote')]"));

		for (int i = 0; i < enquiryIds.size() && i < quoteBtns.size(); i++) {

			if (enquiryIds.get(i).getText().contains(enquiryid)) {

				//quoteBtns.get(i).click();
				locateElementToClick(quoteBtns.get(i));
				break;
			}
		}

		
	}

	private void locateElementToClick(WebElement webElement) throws Throwable {
		CommonFunctions.SearchForElement(webElement);//Common functions-4
		try
		{
			webElement.click();
		}
		catch(Exception e)
		{
			CommonFunctions.SearchForElement_Method_2(webElement, "CLICK");
		}
		CommonFunctions.LoadPageExpicitWait(); //Common functions-5
		CommonFunctions.scrollPageUp(0,-1000); //Common functions-6
		
	}
	
	@AfterMethod
	public void writeResult(ITestResult result) throws Throwable
	{
		log.info("In @after method");
		
		  log.info(result.getStatus());
		  File fle = new File("D:/WebAutomation_msupply_R/WebAutomation_msupply_2/src/test/resources/QuoteEdit.xls"); 
		  FileInputStream fsIP=null;
		  FileOutputStream fos=null;
		  String caseName = null;
		  //File fle2 = new File("D:/WebAutomation_msupply_R/WebAutomation_msupply_2/src/test/resources/First.xls");
		  try {
			 
			
			//Access the workbook    
			
			if (result.getStatus() == ITestResult.SUCCESS) {
				fsIP = new FileInputStream(fle);
				HSSFWorkbook wb = new HSSFWorkbook(fsIP);
				//Access the worksheet, so that we can update / modify it. 
				HSSFSheet worksheet = wb.getSheet("Sheet1");
				//String cellConten = worksheet
				//String content = getfirstColoumnData( "D:/AutomationProject/WebAutomation_msupply_2/WebAutomation_msupply_2/src/test/resources/First.xls", "Sheet5");
				int numRows = worksheet.getPhysicalNumberOfRows();
				System.out.println("Total rows in the sheet: " + numRows);
				

					caseName = getColumnData(worksheet);

					System.out.println("Testcase name: " + caseName);
					int ri = findRow(worksheet, caseName);
					System.out.println(" ri" + ri);
					fsIP.close();
					//RetrieveXlsxData.writeExcelData(Sheet5, rowNum, colNum, data);

					//Get the control of Row in which we want to write the data
					Row r = worksheet.getRow(ri);

					//Create a cell where we want to write the data
					Cell c1 = r.createCell(r.getLastCellNum());
					log.info(r.getLastCellNum());
					log.info(" new cell created in  "+c1.getColumnIndex());

					//Set the data type of cell what we want to write
					c1.setCellType(Cell.CELL_TYPE_STRING);

					//Pass the data in the cell
					c1.setCellValue("Pass");
					//if(fsIP!=null)
					
					if(fsIP!=null){
						fsIP.close();}
					fos = new FileOutputStream(fle);

					//Save the workbook
					wb.write(fos);
				
				//Close the workbook
				wb.close();
			}
			
			else if(result.getStatus() == ITestResult.FAILURE)
	        {
				fsIP = new FileInputStream(fle);
				HSSFWorkbook wb = new HSSFWorkbook(fsIP);
				//Access the worksheet, so that we can update / modify it. 
				HSSFSheet worksheet = wb.getSheet("Sheet1");
				//String cellConten = worksheet
				//String content = getfirstColoumnData( "D:/AutomationProject/WebAutomation_msupply_2/WebAutomation_msupply_2/src/test/resources/First.xls", "Sheet5");
				int numRows = worksheet.getPhysicalNumberOfRows();
				System.out.println("Total rows in the sheet: " + numRows);
				

					caseName = getColumnData(worksheet);

					System.out.println("Testcase name: " + caseName);
					int ri = findRow(worksheet, caseName);
					System.out.println(" ri" + ri);
					fsIP.close();
					//RetrieveXlsxData.writeExcelData(Sheet5, rowNum, colNum, data);

					//Get the control of Row in which we want to write the data
					Row r = worksheet.getRow(ri);

					//Create a cell where we want to write the data
					Cell c1 = r.createCell(r.getLastCellNum());

					//Set the data type of cell what we want to write
					c1.setCellType(Cell.CELL_TYPE_STRING);

					//Pass the data in the cell
					c1.setCellValue("Fail");
					//if(fsIP!=null)
					if(fsIP!=null){
						fsIP.close();}
					fos = new FileOutputStream(fle);

					//Save the workbook
					wb.write(fos);

				
				//Close the workbook
				wb.close();
	           
	            

	        }
	        else if(result.getStatus() == ITestResult.SKIP)
	        {
	           
	        	fsIP = new FileInputStream(fle);
				HSSFWorkbook wb = new HSSFWorkbook(fsIP);
				//Access the worksheet, so that we can update / modify it. 
				HSSFSheet worksheet = wb.getSheet("Sheet1");
				//String cellConten = worksheet
				//String content = getfirstColoumnData( "D:/AutomationProject/WebAutomation_msupply_2/WebAutomation_msupply_2/src/test/resources/First.xls", "Sheet5");
				int numRows = worksheet.getPhysicalNumberOfRows();
				System.out.println("Total rows in the sheet: " + numRows);
				

					caseName = getColumnData(worksheet);

					System.out.println("Testcase name: " + caseName);
					int ri = findRow(worksheet, caseName);
					System.out.println(" ri" + ri);
					fsIP.close();
					//RetrieveXlsxData.writeExcelData(Sheet5, rowNum, colNum, data);

					//Get the control of Row in which we want to write the data
					Row r = worksheet.getRow(ri);

					//Create a cell where we want to write the data
					Cell c1 = r.createCell(r.getLastCellNum());

					//Set the data type of cell what we want to write
					c1.setCellType(Cell.CELL_TYPE_STRING);

					//Pass the data in the cell
					c1.setCellValue("Skip");
					
					if(fsIP!=null){
						
						fsIP.close();
					}

					fos = new FileOutputStream(fle);

					//Save the workbook
					wb.write(fos);
					
				
				//Close the workbook
				wb.close();
	        }
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
	
		    private String getColumnData(HSSFSheet worksheet) throws Throwable {
		     	
		    	DataFormatter formatter = new DataFormatter();
		    	//int rowCount = worksheet.getLastRowNum();
		    	int numRows = worksheet.getPhysicalNumberOfRows();
		    	String CellData = null;
		    	
		    	
		    	//int noOfColumns = worksheet.getRow(rows).getPhysicalNumberOfCells();
		    	
		    		
		    			for (int i = 0; i <= numRows; i++) {
		    				log.info("last cell value "+worksheet.getRow(i).getCell(10));
		    				if (worksheet.getRow(i).getCell(10) == null && worksheet.getRow(i).getPhysicalNumberOfCells()< 12) {
		    				Cell cell = worksheet.getRow(i).getCell((worksheet.getRow(i).getPhysicalNumberOfCells()), Row.RETURN_BLANK_AS_NULL);
			    			if (cell == null) {

			    				cell = worksheet.getRow(i).getCell(0);
			    		    	CellData = formatter.formatCellValue(cell);
			    				return CellData;
			    			} 
						}
		    			
		    			//int totalCols = worksheet.getRow(i).getLastCellNum(); 
		    			
		    		}

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

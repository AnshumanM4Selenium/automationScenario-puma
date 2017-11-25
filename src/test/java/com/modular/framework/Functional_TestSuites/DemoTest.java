package com.modular.framework.Functional_TestSuites;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.modular.framework.Generic_Libraries.CommonFunctions;
import com.modular.framework.Generic_Libraries.LoggerHelper;
import com.modular.framework.Generic_Libraries.RetrieveXlsxData;
import com.modular.framework.Generic_Libraries.WebDriverCommonFunctions;
import com.modular.framework.InitWebdriver.InitDriver;

public class DemoTest {
	
	static Logger log = LoggerHelper.writeLog(DemoTest.class);
	static String Parent;
	static String Child;
	private SoftAssert softAssert = new SoftAssert();
	@DataProvider(name = "dataTest12")
	public static Object[][] validationC() throws Exception {

		String[][] testDataArr = RetrieveXlsxData.getTableArray(
				"D:/WebAutomation_msupply_R/WebAutomation_msupply_2/src/test/resources/First.xls",
				"Sheet2");
		return testDataArr;
	}

	@Test(dataProvider = "dataTest12")
	public void fillingProfile(String enquiryid,String brand,String baseRate,String vat,String deliveryType,String creditDays,
			String deliveryCharge,String deliverytime1,String cst,String brand1,String baseRate1,String vat1, String data) throws Throwable {

		CommonFunctions.LoadPageExpicitWait();

		InitDriver.driver.navigate().refresh();
		Thread.sleep(3000);
		WebElement myEnquiriesLink = InitDriver.driver.findElement(By.linkText("My Enquiries"));
		
		locateElementToClick(myEnquiriesLink);
		
		
		//InitDriver.driver.findElement(By.linkText("My Enquiries")).click();
		Thread.sleep(1000);
		//InitDriver.driver.findElement(By.xpath("html/body/div[3]/ng-view/div[2]/div[4]/div/div[1]/ul/li[2]/a")).click();
		
		WebElement quotePending = InitDriver.driver.findElement(By.xpath("html/body/div[3]/ng-view/div[2]/div[4]/div/div[1]/ul/li[2]/a"));
		locateElementToClick(quotePending);
		
		new Select(InitDriver.driver.findElement(By.name("viewItemPerPage"))).selectByValue("number:50");
		
		Thread.sleep(2000);
		selectEnquiryId(enquiryid);
		
		Thread.sleep(3000);
		


		log.info("filling the price");
		
		CommonFunctions.scrollPageUp(0,-1000);
		
		supplierInputs(brand, baseRate, vat, deliveryType, creditDays,deliveryCharge, deliverytime1, cst,brand1, baseRate1, vat1);
		
	
		InitDriver.driver.navigate().back();
	}

	public void selectEnquiryId(String enquiryId) throws Throwable {

		List<WebElement> enquiryIds = InitDriver.driver.findElements(By.xpath(
				"//table[@class='custom-enqtable']/tbody/tr/td//div[contains(text(),'Enquiry Number')]/following-sibling::div"));

		List<WebElement> quoteBtns = InitDriver.driver.findElements(
				By.xpath("//table[@class='custom-enqtable']/tbody/tr/td//a[contains(text(),'Quote Now')]"));

		for (int i = 0; i < enquiryIds.size() && i < quoteBtns.size(); i++) {

			if (enquiryIds.get(i).getText().contains(enquiryId)) {

				//quoteBtns.get(i).click();
				locateElementToClick(quoteBtns.get(i));
				break;
			}
		}

	}

	private void supplierInputs(String brand, String baseRate, String vat, String deliveryType, String creditDays,
			String deliveryCharged, String deliverytime1, String cst, String brand1, String baseRate1, String vat1) throws Throwable {
			
			log.info("Brand suggested : " + brand);
			log.info("B Rate  : " + baseRate);
			log.info("VAT  : " + vat);
			log.info("Payment option  : " + deliveryType);
			
			log.info("2nd Brand suggested : " + brand1);
			log.info("2nd B Rate  : " + baseRate1);
			log.info("2nd VAT  : " + vat1);
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
				if (ngModel.contains("product.brand") && i == 0) {
					
					boolean flag = false;
					if (inputFields.get(i).getAttribute("class").contains("ng-valid ng-empty")) {
						
						inputFields.get(i).sendKeys(brand);
						
					}
					
					if (brand == null && brand.isEmpty()) {
						
						softAssert.fail();
					}
					else if (brand.contains("-")) {
					
					
						softAssert.assertTrue(flag, "The brand name should not contain negative values");
					}
					inputFields.get(i).clear();
					inputFields.get(i).sendKeys(brand);
				}else if (ngModel.contains("product.brand") && i == 3) {
					
					boolean flag = false;
					if (brand1 == null && brand1.isEmpty()) {
						softAssert.fail();
					}
					if (brand1.contains("-")) {
						
						
						softAssert.assertTrue(flag, "The brand name should not contain negative values");
					}
					
					inputFields.get(i).clear();
					inputFields.get(i).sendKeys(brand1);
				} 
				else if ((inputFields.get(i).getAttribute("ng-model")).contains("product.getRate") && (i == 1)) {
					String errormsg = null;
					boolean flag = false;
					
					if (baseRate == null && baseRate.isEmpty()) {
						softAssert.fail();
					}
					if (baseRate.length() <= 15) {
						flag = true;
						softAssert.assertTrue(flag, "The base rate entered exceeds than max that can be pescribed");
					}else if (baseRate.contains("-")) {
						
						flag = false;
						
						
						softAssert.assertTrue(flag, "The base rate should not contain negative values");
					}
					//softAssert.assertTrue(flag, "The base rate entered exceeds than max that can be pescribed");
					inputFields.get(i).sendKeys(baseRate);
						//locateElementToEnterValues(inputFields.get(i), data2);
					

				} else if ((inputFields.get(i).getAttribute("ng-model")).contains("product.getRate") && (i == 4)) {

					String errormsg = null;
					boolean flag = false;
					
					/*if (baseRate1 == null && baseRate1.isEmpty()) {
						softAssert.fail();
					}*/
					/*if (baseRate1.length() <= 15) {
						flag = true;
						softAssert.assertTrue(flag, "The base rate entered exceeds than max that can be pescribed");
					}else if (baseRate1.contains("-")) {
						
						flag = false;
						
						errormsg = InitDriver.driver.findElement(By.xpath("html/body/div[3]/ng-view/div[3]/div[7]/div[2]/p[7]")).getText();
						log.info(errormsg);
						Assert.fail();
						softAssert.assertTrue(flag, "The base rate should not contain negative values");
					}*/
					if (baseRate1 != null && !baseRate1.isEmpty()) {
						//softAssert.assertTrue(flag, "The base rate entered exceeds than max that can be pescribed");
						inputFields.get(i).sendKeys(baseRate1);
						//locateElementToEnterValues(inputFields.get(i), data2);
					}else {
						softAssert.fail();
					}
				}
				else if ((inputFields.get(i).getAttribute("ng-model")).contains("product.VAT") && i == 2) {
					
					boolean flag = false;
					String string_form;
					String string_form2;
					int digits;
					
					if (vat == null && vat.isEmpty()) {
						softAssert.fail();
					}
					 if (vat.contains(".")) {
						string_form = vat.substring(0, vat.indexOf('.'));
						int beforeDecimal = Integer.parseInt(string_form);
						
						string_form2 = vat.substring(((vat.indexOf('.'))+1), vat.length());
						int afterDecimal = Integer.parseInt(string_form2);
						if (string_form.length() <= 2 && string_form2.length() <= 1) {
							flag = true;
						} 
						
					}/*else if (vat.contains("-")) {
						
						Assert.assertTrue(flag, "Please enter the valid VAT");
					}*/
					 
					 else {
						 digits = vat.length();
						 if (digits <= 2) {
								flag = true;
							} 
					}
					
					inputFields.get(i).sendKeys(vat);
					
					softAssert.assertTrue(flag, "Please enter the valid VAT");
				} 
				
				else if ((inputFields.get(i).getAttribute("ng-model")).contains("product.VAT") && i == 5) {

					
					boolean flag = false;
					String string_form;
					String string_form2;
					int digits;
					
					if (vat1 == null && vat1.isEmpty()) {
						softAssert.fail();
					}
					 if (vat1.contains(".")) {
						string_form = vat1.substring(0, vat1.indexOf('.'));
						int beforeDecimal = Integer.parseInt(string_form);
						
						string_form2 = vat1.substring(((vat1.indexOf('.'))+1), vat1.length());
						int afterDecimal = Integer.parseInt(string_form2);
						if (string_form.length() <= 2 && string_form2.length() <= 1) {
							flag = true;
						} 
						
					}/*else if (vat.contains("-")) {
						
						Assert.assertTrue(flag, "Please enter the valid VAT");
					}*/
					 
					 else {
						 digits = vat1.length();
						 if (digits <= 2) {
								flag = true;
							} 
					}
					
					inputFields.get(i).sendKeys(vat1);
					//locateElementToEnterValues(inputFields.get(i), data3);
					softAssert.assertTrue(flag, "Please enter the valid VAT");
				
				}
				else if (deliveryType.contains("On Credit")
						&& (inputFields.get(i).getAttribute("ng-model").contains("temp.paymentMode"))) {

					inputFields.get(i).click();
					Thread.sleep(2000);
					//WebDriverCommonFunctions.element_EnterValuesToTextField("CreditBox_xpath", creditDays,"Days for availing credit lines entered");
					inputFields.get(i+1).sendKeys(creditDays);
					/*if (creditDays != null && !creditDays.isEmpty()) {
						WebElement timeDays = InitDriver.driver
												.findElement(By.xpath(".//*[@id='addBrandTbl']/td/table/tbody/tr[2]/td/div/div[2]/label[1]/input[2]"));
										locateElementToEnterValues(timeDays, creditDays);
						WebDriverCommonFunctions.element_EnterValuesToTextField("CreditBox_xpath", creditDays,
								"Days for availing credit lines entered");
					}else {
						softAssert.fail();
					}*/
					break;

				} 
				else if (deliveryType.contains("On Delivery")
						&& (inputFields.get(i).getAttribute("value").contains("onDelivery"))) {

					inputFields.get(i).click();
					//locateElementToClick(inputFields.get(i));
					break;
				}
			}

			/*WebElement datePicker = InitDriver.driver.findElement(By.xpath("html/body/div[3]/ng-view/div[3]/div[3]/div[1]/span[1]/img"));
			locateElementToClick(datePicker);*/
			WebDriverCommonFunctions.element_Click("DatePicker_Icon_xpath", "DatePicker icon clicked");
			
			/*WebElement validUpto = InitDriver.driver.findElement(By.xpath("html/body/div[4]/div[1]/table/tbody/tr[5]/td[4]"));
			locateElementToClick(validUpto);*/
			
			/*DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-mm-yyyy");
			LocalDate localDate = LocalDate.now();
			System.out.println(dtf.format(localDate));*/
			
			WebDriverCommonFunctions.element_Click("Date_selected_xpath", "Date till the offer is valid selected");
			WebDriverCommonFunctions.element_EnterValuesToTextField("DeliveryCharges_TextBox_xpath",
					deliveryCharged, "The amount to be charged on delivery");
			/*if (deliveryCharged != null && !deliveryCharged.isEmpty()) {
				WebElement deliveryCharges = InitDriver.driver.findElement(By.id("deliveryCharges"));
						locateElementToEnterValues(deliveryCharges, deliveryCharged);
				WebDriverCommonFunctions.element_EnterValuesToTextField("DeliveryCharges_TextBox_xpath",
						deliveryCharged, "The amount to be charged on delivery");
			}else {
				softAssert.fail();
			}*/
			/*if (deliverytime1 != null && !deliverytime1.isEmpty()) {
				WebElement deliveryTime = InitDriver.driver.findElement(By.id("deliverytime1"));
						locateElementToEnterValues(deliveryTime, deliverytime1);
				WebDriverCommonFunctions.element_EnterValuesToTextField("Deliverytime_TextBox_xpath", deliverytime1,
						"Days in which the product to be delivered is entered");
			}
			else {
				softAssert.fail();
			}*/
			WebDriverCommonFunctions.element_EnterValuesToTextField("Deliverytime_TextBox_xpath", deliverytime1,
					"Days in which the product to be delivered is entered");
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
			
			
			
			String errormsg = "Please enter Brand, Base Rate and VAT";
			String errormsg1 = "Please enter valid VAT %";
			/*List<WebElement> errorTexts = InitDriver.driver.findElements(By.xpath("html/body/div[3]/ng-view/div[3]/div[7]/div[2]/p[7]"));
			if (errorTexts.size()>0) {
				
				errormsg = errorTexts.get(0).getText();
				log.info(errormsg);
				
			}*/
			
			
			
			
			/*if ((baseRate1 == null && baseRate1.isEmpty() )|| (baseRate == null && baseRate.isEmpty()) || (brand == null && brand.isEmpty() )||(brand1 == null && brand1.isEmpty())|| (vat == null && vat.isEmpty() )|| (vat1 == null && vat1.isEmpty() ) ) {
				log.info("ErrorMessage :  "+errormsg);
			}*/
			
			if (vat.contains("-") || vat1.contains("-")) {
				log.info("ErrorMessage :  "+errormsg1);
			}
			log.info("ErrorMessage :  "+errormsg);
			WebDriverCommonFunctions.element_Click("Submit_button_xpath", "Submit button clicked");
			
			Thread.sleep(3000);
			String errormsg2 = InitDriver.driver.findElement(By.xpath("html/body/div[3]/ng-view/div[3]/div[7]/div[2]")).getText();
			
			log.info("errormsg2 ----"+errormsg2);
			//softAssert.assertAll();
			
			/*if (brand) {
				
			}*/
			/*if (vat.contains("-") || vat1.contains("-")) {
				errormsg = InitDriver.driver.findElement(By.xpath("html/body/div[3]/ng-view/div[3]/div[7]/div[2]/p[7]")).getText();
				log.info(errormsg);
				softAssert.fail();
			}*/
			//Assert.fail();
			
		}
	
	public boolean getSpecialCharacterCount(String s) {
	     if (s == null || s.trim().isEmpty()) {
	         System.out.println("Incorrect format of string");
	         return false;
	     }
	     Pattern p = Pattern.compile("[^A-Za-z0-9]");
	     Matcher m = p.matcher(s);
	    // boolean b = m.matches();
	     boolean b = m.find();
	     if (b == true)
	        System.out.println("There is a special character in my string ");
	     else
	         System.out.println("There is no special char.");
	     return false;
	 }
	
	/*@AfterMethod
	public void writeResult(ITestResult result) throws Throwable
	{
		log.info("In @after method");
		
		  log.info(result.getStatus());
		  File fle = new File("D:/WebAutomation_msupply_R/WebAutomation_msupply_2/src/test/resources/First.xls"); 
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
				HSSFSheet worksheet = wb.getSheet("Sheet2");
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
				HSSFSheet worksheet = wb.getSheet("Sheet2");
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
				HSSFSheet worksheet = wb.getSheet("Sheet2");
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
					c1.setCellValue("Skip");
					//if(fsIP!=null)
					
					if(fsIP!=null){
						fsIP.close();}
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
	}	  */
	private String getColumnData(String path, String sheetName) throws Throwable {
		
		FileInputStream filn = new FileInputStream(new File(path));
		
		HSSFWorkbook wb = new HSSFWorkbook(filn);
    	//Access the worksheet, so that we can update / modify it. 
    	HSSFSheet worksheet = wb.getSheet(sheetName);
    	DataFormatter formatter = new DataFormatter();
    	int rowCount = worksheet.getLastRowNum();
    	String CellData = null;
    	for (int i = 0; i < rowCount; i++) {
			int noOfColumns = worksheet.getRow(i).getPhysicalNumberOfCells();
			
			
				if (noOfColumns <= 9) {
					//int totalCols = worksheet.getRow(i).getLastCellNum(); 
					Cell cell = worksheet.getRow(i).getCell(noOfColumns, Row.RETURN_BLANK_AS_NULL);
					if (cell == null) {

						cell = worksheet.getRow(i).getCell(0);
					    CellData = formatter.formatCellValue(cell);
						return CellData;
					} 
				}
			
		}
		/*for (Row row : worksheet) {
			
			System.out.println();
	        if (row.getRowNum() < startRow) continue;
	        Cell cell = row.getCell(columnIndex, Row.RETURN_BLANK_AS_NULL);
	        if (cell != null) {
	            return false;
	        }
	    }
	    return true;
		return null;*/
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
	
	public static void locateElementToClick(WebElement element) throws Throwable{
		
		//CommonFunctions.scrollPageUp(0,-1000);//Common functions-3
		CommonFunctions.SearchForElement(element);//Common functions-4
		try
		{
		   element.click();
		}
		catch(Exception e)
		{
			CommonFunctions.SearchForElement_Method_2(element, "CLICK");
		}
		CommonFunctions.LoadPageExpicitWait(); //Common functions-5
		CommonFunctions.scrollPageUp(0,-1000); //Common functions-6
		//WebDriverCommonFunctions.element_SelectDropDown(locator, Index, Message);
	}
	
	public static void locateElementToEnterValues(WebElement element,String value) throws Throwable{
		
		CommonFunctions.scrollPageUp(0,-1000);//Common functions-3
		CommonFunctions.SearchForElement(element);//Common functions-4
		element.clear();
		element.sendKeys(value);
	}
	
	public static void locateElementToSelect(WebElement element, int index) throws Throwable{
		CommonFunctions.scrollPageUp(0,-1000);//Common functions-3
		CommonFunctions.SearchForElement(element);//Common functions-4
		new Select(element).selectByIndex(index);
		CommonFunctions.LoadPageExpicitWait(); //Common functions-5
		CommonFunctions.scrollPageUp(0,-1000); //Common functions-6
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


}

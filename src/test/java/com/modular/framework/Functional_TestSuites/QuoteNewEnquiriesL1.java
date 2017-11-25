package com.modular.framework.Functional_TestSuites;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.openqa.selenium.internal.Locatable;

import com.modular.framework.Generic_Libraries.CommonFunctions;
import com.modular.framework.Generic_Libraries.LoggerHelper;
import com.modular.framework.Generic_Libraries.RetrieveXlsxData;
import com.modular.framework.Generic_Libraries.WebDriverCommonFunctions;
import com.modular.framework.InitWebdriver.InitDriver;

public class QuoteNewEnquiriesL1 {
	
	static Logger log = LoggerHelper.writeLog(QuoteNewEnquiriesTest.class);
	static String Parent;
	static String Child;
	int clickAddQuote;
	int indexIdentifier;
	boolean flag = false;
	String errorMsg = null;
	
	@DataProvider(name = "dataTest12")
	public static String[][] validationC() throws Exception {

		String[][] testDataArr = RetrieveXlsxData.getTableArray(
				"D:/WebAutomation_msupply_R/WebAutomation_msupply_2/src/test/resources/prac2.xls",
				"Sheet1");
		return testDataArr;
	}

	@Test(dataProvider = "dataTest12")
	public void submitQuoteNew(String[] strArray) throws Throwable{
		
		List<String> dataList = Arrays.asList(strArray);
		
		log.info("List size :...."+dataList.size());
		
		
		CommonFunctions.LoadPageExpicitWait();
		InitDriver.driver.navigate().refresh();
		Thread.sleep(2000);
		
		
		WebDriverCommonFunctions.element_Click("My_Enquiries_Xpath","My Enquiries link is clicked");
		log.info("My enquiry link is clicked");
	
		CommonFunctions.LoadPageExpicitWait();
		
		Thread.sleep(3000);
		/*WebElement quotePending = InitDriver.driver.findElement(By.xpath("html/body/div[3]/ng-view/div[2]/div[4]/div/div[1]/ul/li[2]/a"));
		locateElementToClick(quotePending);*/
		
		WebDriverCommonFunctions.element_Click("QuotePending_Link", "Quote Pending link clicked");
		
		new Select(InitDriver.driver.findElement(By.name("viewItemPerPage"))).selectByValue("number:50");
		
		Thread.sleep(2000);
		
		log.info("InquiryID is : "+dataList.get(0));
		selectEnquiryId(dataList.get(0));
		
		Thread.sleep(3000);
		
		log.info("filling the price");
		
		CommonFunctions.scrollPageUp(0,-1000);
		
		Thread.sleep(2000);
		
		clickAddQuote = Collections.frequency(dataList, "AddQ");
		log.info("clickAddQuote ........"+clickAddQuote);
		/*WebElement addQuoteB = InitDriver.driver
				.findElement(By.xpath("html/body/div[3]/ng-view/div[3]/div/div[3]/div[1]/div[1]/div[2]/a/span"));
		JavascriptExecutor jse = (JavascriptExecutor)InitDriver.driver;

		jse.executeScript("arguments[0].scrollIntoView()", addQuoteB); */
		
		for (int i = 0; i <= clickAddQuote; i++) {
			
			WebDriverCommonFunctions.element_Click("AddButton_xpath", "Add button clicked");
		}
		
		
		
		/*Coordinates c=((Locatable)element.get(element.size()-1)).getCoordinates();
		c.inViewPort();*/
		
		Thread.sleep(2000);
		supplierInputs2(dataList);
		
	
		WebDriverCommonFunctions.element_Click("DatePicker_Icon_xpath", "DatePicker icon clicked");
		
		
		WebDriverCommonFunctions.element_Click("Date_selected_xpath", "Date till the offer is valid selected");
		
		//******************************************************************CST***********************************************************************************
		indexIdentifier = dataList.indexOf("BreakPoint");
		log.info("CST :"+dataList.get(indexIdentifier+1));
		if (dataList.get(indexIdentifier+1).contains("Yes")|| dataList.get(indexIdentifier+1).contains("No") || dataList.get(indexIdentifier+1).contains("Not Applicable")) {
			WebDriverCommonFunctions.element_SelectDropDown("CST_Dropdown_xpath", "VISIBLETEXT", 0,
					dataList.get(indexIdentifier + 1), "CST selected");
		}else {
			flag = true;
		}
		//**********************************************************************************************************************************************************
		log.info("Days in which the product to be delivered is entered :"+dataList.get(indexIdentifier+2));
		if (dataList.get(indexIdentifier+2)!= null && !dataList.get(indexIdentifier+2).isEmpty()) {
			WebDriverCommonFunctions.element_EnterValuesToTextField("Deliverytime_TextBox_xpath",
					dataList.get(indexIdentifier + 2), "Days in which the product to be delivered is entered");
		}else {
			flag = true;
		}
		//*******************************************************************Shipping Cost**************************************************************************
		log.info("Shipping Cost :"+dataList.get(indexIdentifier+3));
		if (dataList.get(indexIdentifier+3).contains("Amount Specified")|| dataList.get(indexIdentifier+3).contains("Inclusive") || dataList.get(indexIdentifier+3).contains("As Per Actuals") ) {
			if (dataList.get(indexIdentifier + 3).contains("Amount Specified")) {
				WebDriverCommonFunctions.element_SelectDropDown("ShippingCost_Dropdown_xpath", "VISIBLETEXT", 0,
						dataList.get(indexIdentifier + 3), "Shipping Cost " + dataList.get(indexIdentifier + 3));

				WebDriverCommonFunctions.element_EnterValuesToTextField("AmountSpecified_shippingCostExtra_xpath",
						dataList.get(indexIdentifier + 4), "Amount entered shipping cost");
			} else {

				WebDriverCommonFunctions.element_SelectDropDown("ShippingCost_Dropdown_xpath", "VISIBLETEXT", 0,
						dataList.get(indexIdentifier + 3), "Shipping Cost " + dataList.get(indexIdentifier + 3));
			} 
		}else {
			flag = true;
		}
		//*********************************************************************Loading Cost***************************************************************************
		log.info("Loading Cost :"+dataList.get(indexIdentifier+4));
		if (isInteger(dataList.get(indexIdentifier+4)) && (dataList.get(indexIdentifier+5).contains("Amount Specified")|| dataList.get(indexIdentifier+5).contains("Not Applicable") || dataList.get(indexIdentifier+5).contains("Customer Scope"))) {
			if (dataList.get(indexIdentifier + 5).contains("Amount Specified")) {
				WebDriverCommonFunctions.element_SelectDropDown("LoadingCost_Dropdown_xpath", "VISIBLETEXT", 0,
						dataList.get(indexIdentifier + 5), "Loading Cost  " + dataList.get(indexIdentifier + 5));
				WebDriverCommonFunctions.element_EnterValuesToTextField("AmountSpecified_loadingExtra_xpath",
						dataList.get(indexIdentifier + 6), "Loading charged");
			} else if (dataList.get(indexIdentifier + 5).contains("Not Applicable")) {
				WebDriverCommonFunctions.element_SelectDropDown("LoadingCost_Dropdown_xpath", "VALUE", 0,
						"string:NotApplicable", "Loading Cost not applicable ");
			} else {

				WebDriverCommonFunctions.element_SelectDropDown("LoadingCost_Dropdown_xpath", "VISIBLETEXT", 0,
						dataList.get(indexIdentifier + 5), "Loading Cost  " + dataList.get(indexIdentifier + 5));
			} 
		}else if (dataList.get(indexIdentifier+4).contains("Amount Specified")|| dataList.get(indexIdentifier+4).contains("Not Applicable") || dataList.get(indexIdentifier+4).contains("Customer Scope")) {
				
			if (dataList.get(indexIdentifier + 4).contains("Amount Specified")) {
				WebDriverCommonFunctions.element_SelectDropDown("LoadingCost_Dropdown_xpath", "VISIBLETEXT", 0,
						dataList.get(indexIdentifier + 4), "Loading Cost  " + dataList.get(indexIdentifier + 4));
				WebDriverCommonFunctions.element_EnterValuesToTextField("AmountSpecified_loadingExtra_xpath",
						dataList.get(indexIdentifier + 5), "Loading charged");
			} else if (dataList.get(indexIdentifier + 4).contains("Not Applicable")) {
				WebDriverCommonFunctions.element_SelectDropDown("LoadingCost_Dropdown_xpath", "VALUE", 0,
						"string:NotApplicable", "Loading Cost not applicable ");
			} else {

				WebDriverCommonFunctions.element_SelectDropDown("LoadingCost_Dropdown_xpath", "VISIBLETEXT", 0,
						dataList.get(indexIdentifier + 4), "Loading Cost  " + dataList.get(indexIdentifier + 4));
			} 
		
		}else {
			flag = true;
		}
		//***********************************************************************Uploading Cost**********************************************************************
		log.info("Uploading Cost :"+dataList.get(indexIdentifier+5));
		
		if (isInteger(dataList.get(indexIdentifier+4)) && dataList.get(indexIdentifier+5).contains("Amount Specified")&& (dataList.get(indexIdentifier+7).contains("Amount Specified")|| dataList.get(indexIdentifier+7).contains("Not Applicable") || dataList.get(indexIdentifier+7).contains("Customer Scope")) ) {
			
			if (dataList.get(indexIdentifier + 7).contains("Amount Specified")) {
				WebDriverCommonFunctions.element_SelectDropDown("UploadingCost_Dropdown_xpath", "VISIBLETEXT", 0,
						dataList.get(indexIdentifier + 7), "Uploading Cost  " + dataList.get(indexIdentifier + 7));
				WebDriverCommonFunctions.element_EnterValuesToTextField("AmountSpecified_unloadingExtra_xpath",
						dataList.get(indexIdentifier + 8), "Unloading charged");
			} else if (dataList.get(indexIdentifier + 7).contains("Not Applicable")) {
				WebDriverCommonFunctions.element_SelectDropDown("UploadingCost_Dropdown_xpath", "VALUE", 0,
						"string:NotApplicable", "Unloading Cost not applicable ");
			} else {

				WebDriverCommonFunctions.element_SelectDropDown("UploadingCost_Dropdown_xpath", "VISIBLETEXT", 0,
						dataList.get(indexIdentifier + 7), "Unloading Cost  " + dataList.get(indexIdentifier + 7));
			} 
			
			/*if (!dataList.get(indexIdentifier + 5).contains("Not Applicable")) {
				WebDriverCommonFunctions.element_SelectDropDown("UploadingCost_Dropdown_xpath", "VISIBLETEXT", 0,
						dataList.get(indexIdentifier + 5), "Uploading Cost  " + dataList.get(indexIdentifier + 5));
			} else if (dataList.get(indexIdentifier + 5).contains("Not Applicable")) {
				WebDriverCommonFunctions.element_SelectDropDown("UploadingCost_Dropdown_xpath", "VALUE", 0,
						"string:NotApplicable", "Loading Cost not applicable ");
			} */
		}else if (dataList.get(indexIdentifier+6).contains("Amount Specified")|| dataList.get(indexIdentifier+6).contains("Not Applicable") || dataList.get(indexIdentifier+6).contains("Customer Scope")) {
			
			if (dataList.get(indexIdentifier + 6).contains("Amount Specified")) {
				WebDriverCommonFunctions.element_SelectDropDown("UploadingCost_Dropdown_xpath", "VISIBLETEXT", 0,
						dataList.get(indexIdentifier + 6), "Unloading Cost  " + dataList.get(indexIdentifier + 6));
				WebDriverCommonFunctions.element_EnterValuesToTextField("AmountSpecified_unloadingExtra_xpath",
						dataList.get(indexIdentifier + 7), "Unloading charged");
			} else if (dataList.get(indexIdentifier + 6).contains("Not Applicable")) {
				WebDriverCommonFunctions.element_SelectDropDown("UploadingCost_Dropdown_xpath", "VALUE", 0,
						"string:NotApplicable", "Unloading Cost not applicable ");
			} else {

				WebDriverCommonFunctions.element_SelectDropDown("UploadingCost_Dropdown_xpath", "VISIBLETEXT", 0,
						dataList.get(indexIdentifier + 6), "Unloading Cost  " + dataList.get(indexIdentifier + 6));
			} 
		
		
		}else {
			
			flag = true;
		}
		//**************************************************************************************************************************************************************
		WebDriverCommonFunctions.element_EnterValuesToTextField("Review_Textbox_xpath", "Fill the text area within 100 characters", "Remarks entered");
		
		
		WebDriverCommonFunctions.element_Click("TermsConditions_Checkbox_xpath", "Check box selected");
		
		Thread.sleep(1000);
		/*CommonFunctions.scrollDownPage(0, 250);*/
		//InitDriver.driver.navigate().back();
		WebDriverCommonFunctions.element_Click("Submit_button_xpath", "Submit button clicked");
		
		if (flag) {
			errorMsg = WebDriverCommonFunctions.element_GetTextFromLabel("ErrorBlock_xpath");
			log.info("Error message : "+errorMsg);
			InitDriver.driver.navigate().back();
		}else {
			WebDriverCommonFunctions.element_Click("AfterSubmit_Okbutton_xpath", "Quotation submitted successfully");
			//InitDriver.driver.findElement(By.xpath("html/body/div[3]/ng-view/div[4]/div[2]/div/ng-transclude/button")).click();
		}
		
	}

	private void selectEnquiryId(String enquiryid) throws Throwable {
		
		List<WebElement> enquiryIds = InitDriver.driver.findElements(By.xpath(
				"//table[@class='custom-enqtable']/tbody/tr/td//div[contains(text(),'Enquiry Number')]/following-sibling::div"));

		List<WebElement> quoteBtns = InitDriver.driver.findElements(
				By.xpath("//table[@class='custom-enqtable']/tbody/tr/td//a[contains(text(),'Quote Now')]"));

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
		    
		    int lastIndex;
			private void supplierInputs2(List<String> listData) throws Throwable {
				
				String className = "ng-valid ng-empty";
				
				List<WebElement> inputFields = InitDriver.driver
						.findElements(By.xpath("//tr[@id='addBrandTbl']//tbody//input"));
				log.info(".......Itdentifying no. of fields........");
				log.info("Total input fields :" + inputFields.size());
				
				for(int i = (1+clickAddQuote), j = 0; j < inputFields.size(); i++,j++){
					
					if (listData.get(i).contains("On Credit") || listData.get(i).contains("On Delivery")) {
						
						if (listData.get(i).contains("On Credit")) {
							Thread.sleep(2000);
							inputFields.get(j).click();
							Thread.sleep(1000);
							inputFields.get(j+1).sendKeys(listData.get(i+1));
							i++;
							j++;
							j++;
						}else {
							inputFields.get(j).click();
						}	
						
					}
					
					if (inputFields.get(j).getAttribute("class").contains(className)) {
						inputFields.get(j).sendKeys(listData.get(i));
					}
					
					
				}
		
			}
				
		//***************************************************************************************************************************************************		

			public static boolean isInteger(String str) {
			    if (str == null) {
			        return false;
			    }
			    int length = str.length();
			    if (length == 0) {
			        return false;
			    }
			    int i = 0;
			    if (str.charAt(0) == '-') {
			        if (length == 1) {
			            return false;
			        }
			        i = 1;
			    }
			    for (; i < length; i++) {
			        char c = str.charAt(i);
			        if (c < '0' || c > '9') {
			            return false;
			        }
			    }
			    return true;
			}		
				
}		


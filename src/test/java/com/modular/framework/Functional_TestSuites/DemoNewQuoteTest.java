package com.modular.framework.Functional_TestSuites;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.modular.framework.Generic_Libraries.CommonFunctions;
import com.modular.framework.Generic_Libraries.LoggerHelper;
import com.modular.framework.Generic_Libraries.RetrieveXlsxData;
import com.modular.framework.Generic_Libraries.WebDriverCommonFunctions;
import com.modular.framework.InitWebdriver.InitDriver;

public class DemoNewQuoteTest {
	

	
	static Logger log = LoggerHelper.writeLog(QuoteNewEnquiriesTest.class);
	static String Parent;
	static String Child;
	int clickAddQuote;
	
	@DataProvider(name = "dataTest12")
	public static String[][] validationC() throws Exception {

		String[][] testDataArr = RetrieveXlsxData.getTableArray(
				"D:/WebAutomation_msupply_R/WebAutomation_msupply_2/src/test/resources/First.xls",
				"Sheet2");
		return testDataArr;
	}

	@Test(dataProvider = "dataTest12")
	public void submitQuoteNew(String[] strArray) throws Throwable{
		
		List<String> dataList = Arrays.asList(strArray);
		
		log.info("List size :...."+dataList.size());
		CommonFunctions.LoadPageExpicitWait();
		InitDriver.driver.navigate().refresh();
		Thread.sleep(3000);
		
		/*WebElement myEnquiriesLink = InitDriver.driver.findElement(By.linkText("My Enquiries"));
		locateElementToClick(myEnquiriesLink);*/
		WebDriverCommonFunctions.element_Click("My_Enquiries_Xpath","My Enquiries link is clicked");
		log.info("My enquiry link is clicked");
	
		CommonFunctions.LoadPageExpicitWait();
		new Select(InitDriver.driver.findElement(By.name("viewItemPerPage"))).selectByValue("number:50");
		//WebDriverCommonFunctions.element_SelectDropDown("View_Item_PerPage", "VISIBLETEXT", 0, displayingInquirie, "Total no. of items per page selected");
		
		// Selecting enquiries id to quote
		
		Thread.sleep(2000);
		selectEnquiryId(dataList.get(0));
		log.info(" View Enquiry link is clicked for the selected Enquiry");
		
		Thread.sleep(3000);
		//CommonFunctions.scrollDownPage(0, 350);
		/*WebElement quoteBtn = InitDriver.driver.findElement(By.xpath("html/body/div[3]/ng-view/div[7]/button"));
		locateElementToClick(quoteBtn);*/
		WebDriverCommonFunctions.element_Click("Quote_Button", "Quote button clicked");
		
		Thread.sleep(3000);

		log.info("filling the price");
		
		CommonFunctions.scrollPageUp(0,-1000);
		
		
		
		clickAddQuote = Collections.frequency(dataList, "AddQ");
		
		for (int i = 0; i < clickAddQuote; i++) {
			InitDriver.driver
					.findElement(By.xpath("html/body/div[3]/ng-view/div[3]/div[2]/div[1]/div[1]/div[2]/a/span"))
					.click();
		}
		
		//List<WebElement> element=InitDriver.driver.findElements(By.xpath("//tbody/tr[@id='addBrandTbl']/td//th[text()='VAT (%)']"));
		
		/*Coordinates c=((Locatable)element.get(element.size()-1)).getCoordinates();
		c.inViewPort();*/
		Thread.sleep(2000);
		supplierInputs2(dataList);
		
		log.info("The last data in sheet :"+dataList.get(dataList.size()-1));
		WebDriverCommonFunctions.element_Click("DatePicker_Icon_xpath", "DatePicker icon clicked");
		
		/*WebElement validUpto = InitDriver.driver.findElement(By.xpath("html/body/div[4]/div[1]/table/tbody/tr[5]/td[4]"));
		locateElementToClick(validUpto);*/
		WebDriverCommonFunctions.element_Click("Date_selected_xpath", "Date till the offer is valid selected");
		
		/*WebElement deliveryCharges = InitDriver.driver.findElement(By.id("deliveryCharges"));
		locateElementToEnterValues(deliveryCharges, deliveryCharged);*/
		
		log.info("The amount to be charged on delivery :"+dataList.get(dataList.size()-6));
		WebDriverCommonFunctions.element_EnterValuesToTextField("DeliveryCharges_TextBox_xpath", dataList.get(dataList.size()-6), "The amount to be charged on delivery");
		
		/*WebElement deliveryTime = InitDriver.driver.findElement(By.id("deliverytime1"));
		locateElementToEnterValues(deliveryTime, deliverytime1);*/
		
		log.info("Days in which the product to be delivered is entered :"+dataList.get(dataList.size()-5));
		WebDriverCommonFunctions.element_EnterValuesToTextField("Deliverytime_TextBox_xpath", dataList.get(dataList.size()-5), "Days in which the product to be delivered is entered");
		
		/*WebElement CST = InitDriver.driver
		.findElement(By.xpath("html/body/div[3]/ng-view/div[3]/div[3]/div[2]/span/select"));
		locateElementToSelect(CST, 2);*/
		
		log.info("CST :"+dataList.get(dataList.size()-4));
		WebDriverCommonFunctions.element_SelectDropDown("CST_Dropdown_xpath", "VISIBLETEXT", 0, dataList.get(dataList.size()-4), "CST added "+dataList.get(dataList.size()-1));
		
		//CommonFunctions.scrollDownPage(0, 250);
		//InitDriver.driver.findElement(By.xpath("html/body/div[3]/ng-view/div[3]/div[5]/textarea")).sendKeys("Fill the text area");
		WebDriverCommonFunctions.element_EnterValuesToTextField("Review_Textbox_xpath", "Fill the text area within 100 characters", "Remarks entered");
		
		//CommonFunctions.scrollDownPage(0, 250);
		//InitDriver.driver.findElement(By.xpath("html/body/div[3]/ng-view/div[3]/div[7]/div[1]/div/label")).click();
		WebDriverCommonFunctions.element_Click("TermsConditions_Checkbox_xpath", "Check box selected");
		
		Thread.sleep(1000);
		CommonFunctions.scrollDownPage(0, 250);
		
		/*WebDriverCommonFunctions.element_Click("Submit_button_xpath", "Submit button clicked");
		
		InitDriver.driver.findElement(By.xpath("html/body/div[3]/ng-view/div[4]/div[2]/div/ng-transclude/button")).click();*/
	}

	private void selectEnquiryId(String enquiryid) throws Throwable {
		
		List<WebElement> enquiryIds = InitDriver.driver.findElements(By.xpath(
				"//table[@class='custom-enqtable']/tbody/tr/td//div[contains(text(),'Enquiry Number')]/following-sibling::div"));

		List<WebElement> quoteBtns = InitDriver.driver.findElements(
				By.xpath("//table[@class='custom-enqtable']/tbody/tr/td//a[contains(text(),'View Enquiry')]"));

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
							inputFields.get(j).click();
							Thread.sleep(2000);
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

				
				
			


}

package com.modular.framework.Functional_TestSuites;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.modular.framework.Generic_Libraries.BrowserSelection;
import com.modular.framework.Generic_Libraries.CommonFunctions;
import com.modular.framework.Generic_Libraries.Credentials;
import com.modular.framework.Generic_Libraries.LoggerHelper;
import com.modular.framework.Generic_Libraries.RetrieveXlsxData;
import com.modular.framework.Generic_Libraries.WebDriverCommonFunctions;
import com.modular.framework.InitWebdriver.InitDriver;

public class ProfileDetailsTest {
	
	static Logger log = LoggerHelper.writeLog(ProfileDetailsTest.class);
	static String Parent;
	static String Child;
	
	@DataProvider(name = "dataTest12")
	public static Object[][] validationC() throws Exception{
		
	String[][] testDataArr = RetrieveXlsxData.getTableArray("C:/ansuman/WebAutomation_msupply_R/WebAutomation_msupply_2/src/test/resources/First.xls", "Sheet2");
	return testDataArr;
    }
	
	@Test(dataProvider="dataTest12")
	public void fillingProfile(String enquiryid, String brand, String baseRate, String vat, String deliveryType,String creditDays, String data) throws Throwable{
		
		
		CommonFunctions.LoadPageExpicitWait();
		
		/*Actions act = new Actions(InitDriver.driver);
		  
		  WebElement element = InitDriver.driver.findElement(By.xpath("html/body/div[3]/ng-view/div[1]/div/ul/li[4]/a"));*/
		 Thread.sleep(3000);
		  InitDriver.driver.findElement(By.linkText("My Enquiries")).click();
		  Thread.sleep(1000);
		  InitDriver.driver.findElement(By.linkText("Quote Pending")).click();
		 /* Point viewPortLocation = ((Locatable) element).getCoordinates().onScreen();
		  int x = viewPortLocation.getX();
		  int y = viewPortLocation.getY();
		  act.moveToElement(element, x, y);*/
		  
		  new Select(InitDriver.driver.findElement(By.name("viewItemPerPage"))).selectByValue("number:50");
		  //InitDriver.driver.findElement(By.xpath("html/body/div[3]/ng-view/div[2]/div[5]/table/tbody/tr[1]/td[6]/div[2]/a")).click();
		  
		  Thread.sleep(2000);
		  
		  selectEnquiryId(enquiryid);
		 //supplierInputs(data1, data2, data3, data4); 
		  Thread.sleep(3000);
		  
		// InitDriver.driver.switchTo().window(Parent);
		 log.info("filling the price");
		 
		 supplierInputs(brand, baseRate, vat, deliveryType, creditDays);
		 
		 //InitDriver.driver.findElement(By.xpath("html/body/div[3]/ng-view/div[3]/div/div[8]/div[1]/div/label")).click();
	}
	
	public void selectEnquiryId( String enquiryId){
		
		List<WebElement> enquiryIds = InitDriver.driver.findElements(By.xpath("//table[@class='custom-enqtable']/tbody/tr/td//div[contains(text(),'Enquiry Number')]/following-sibling::div"));
		
		List<WebElement> quoteBtns = InitDriver.driver.findElements(By.xpath("//table[@class='custom-enqtable']/tbody/tr/td//a[contains(text(),'Quote Now')]"));
		
		for (int i = 0; i < enquiryIds.size() && i < quoteBtns.size(); i++ ) {
			
			if (enquiryIds.get(i).getText().contains(enquiryId)) {
			
				quoteBtns.get(i).click();
				break;
			}
		}
		
		//supplierInputs(data1, data2, data3, data4);
	}
	
	public void supplierInputs(String data1, String data2, String data3, String data4, String data5) throws Throwable{
		
		log.info("Brand suggested : "+data1);
		log.info("B Rate  : "+data2);
		log.info("VAT  : "+data3);
		log.info("If on credit opted  : "+data4);
		List<WebElement> inputFields = InitDriver.driver.findElements(By.xpath("//tr[@id='addBrandTbl']//tbody//input"));
		log.info("Itdentifying no. of fields");
		log.info("Total input fields :"+inputFields.size());
		for(int i = 0; i < inputFields.size();i++){
			
			
			String ngModel = inputFields.get(i).getAttribute("ng-model");
			log.info(ngModel);
			 if (ngModel.contains("product.brand")) {
					
					inputFields.get(i).sendKeys(data1);
					
				}else if ((inputFields.get(i).getAttribute("ng-model")).contains("product.getRate") ) {
					
					inputFields.get(i).sendKeys(data2);
					
				}else if ((inputFields.get(i).getAttribute("ng-model")).contains("product.VAT") ) {
					
					inputFields.get(i).sendKeys(data3);
					
				}
				else if (data4.contains("On Credit")&& (inputFields.get(i).getAttribute("ng-model").contains("temp.paymentMode"))) {
				
				 inputFields.get(i).click();
				 break;
				
				}/*else if (inputFields.get(i).getAttribute("placeholder").contains("*Credit days")) {
					
					 inputFields.get(i).sendKeys(data5);
					 break;
				}*/
				else if (data4.contains("On Delivery")&& (inputFields.get(i).getAttribute("value").contains("onDelivery"))) {
						
						inputFields.get(i).click();
						 break;
					}
				}
			
		InitDriver.driver.findElement(By.xpath(".//*[@id='addBrandTbl']/td/table/tbody/tr[2]/td/div/div[2]/label[1]/input[2]")).sendKeys(data5);
		}
	
	 
}

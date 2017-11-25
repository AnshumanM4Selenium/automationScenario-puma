package com.modular.framework.Functional_TestSuites;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.modular.framework.Generic_Libraries.CommonFunctions;
import com.modular.framework.Generic_Libraries.LoggerHelper;
import com.modular.framework.Generic_Libraries.RetrieveXlsxData;
import com.modular.framework.Generic_Libraries.WebDriverCommonFunctions;
import com.modular.framework.InitWebdriver.InitDriver;

public class UploadPOScenarioTest {
	
	static Logger log = LoggerHelper.writeLog(UploadPOScenarioTest.class);
	static String Parent;
	static String Child;
	
	@DataProvider(name = "dataTest12")
	public static String[][] validationC() throws Exception {

		String[][] testDataArr = RetrieveXlsxData.getTableArray(
				"D:/WebAutomation_msupply_R/WebAutomation_msupply_2/src/test/resources/InvoiceCreateP.xls",
				"Sheet1");
		return testDataArr;
	}
	
	//(dataProvider = "dataTest12")
	@Test(dataProvider = "dataTest12")
	public void createInvoiceObjTest(String[] strArray) throws Throwable{
		
		
		List<String> dataList = Arrays.asList(strArray);
		log.info("List size :...."+dataList.size());
		
		String[] myStringArray = {dataList.get(0),dataList.get(1),dataList.get(2), dataList.get(3)};
		
		
		
		
		//CommonFunctions.LoadPageExpicitWait();
		InitDriver.driver.navigate().refresh();
		//Thread.sleep(2000);
		//WebDriverCommonFunctions.element_Click("mProcure_Xpath", " mProcure link clicked");
		WebDriverCommonFunctions.element_MouseOver("mProcure_Xpath", "mProcure link clicked");
		Actions act = new Actions(InitDriver.driver);
		act.clickAndHold(InitDriver.driver.findElement(By.xpath("html/body/header/div[2]/div[2]/div/div[3]/div/div/ul/li[2]/a"))).click().build().perform();
		
		WebDriverCommonFunctions.element_Click("CreatePO", "CreatePO link clicked");
		
		
		enterCompanyDetails(dataList.subList(0, 4));
		
		WebDriverCommonFunctions.element_Click("Radiobutton_uploadPO", "Radio button clicked");
		//..................................Step 2 of 5: Provide Item Details................................................
		
		log.info(" .................Provide Item Details..............................");
		InitDriver.driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/ng-view/div[9]/div[2]/div/ng-transclude/button[2]")).click();
		
		WebDriverCommonFunctions.element_Click("AttachPO", "Upload PO");
		
		Thread.sleep(3000);
		Runtime.getRuntime().exec("C:\\Users\\Anshuman M\\Desktop\\AutoIt\\FileUploadChrome1.exe");
		
		WebDriverCommonFunctions.element_Click("UploadButton", "Document uploaded");
		
		// confirm  uploading document
		
		WebDriverCommonFunctions.element_Click("UploadButton_Confirm", "Confirmed");
		
		Thread.sleep(3000);
		
		InitDriver.driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/ng-view/div[8]/div[2]/span")).click();
		
		WebDriverCommonFunctions.element_Click("Billing_Address", "Select Billing address");
		
		InitDriver.driver.findElement(By.xpath("(html/body/div[1]/div[2]/div[2]/ng-view/div[5]/div[2]/div/ng-transclude/div/div[2]/div/div/div/label/span)["+dataList.get(4)+"]")).click();
		WebDriverCommonFunctions.element_Click("SelectBtn_BillingAddress", "Billing address selected");
		
		WebDriverCommonFunctions.element_Click("DeliveryDate_datePicker", "Clicked");
		//InitDriver.driver.findElement(By.xpath("html/body/div[3]/div[1]/table/tbody/tr[6]/td[7]")).click();
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDate localDate = LocalDate.now();
		System.out.println(dtf.format(localDate)); //2016/11/16
		
		InitDriver.driver.findElement(By.xpath("html/body/div[3]/div[1]/table/tbody/tr[6]/td[7]")).click();
		
		
		log.info("..................Select Supplier................");
		WebDriverCommonFunctions.element_Click("SelectSupplier", "Select Supplier");
		WebDriverCommonFunctions.element_Click("AdvanceSearch", "Advance search clicked");
		WebDriverCommonFunctions.element_SelectDropDown("SelectState","VISIBLETEXT", 0, "Karnataka", "State selected");
		
		WebDriverCommonFunctions.element_SelectDropDown("SelectCity", "VISIBLETEXT", 0, "Bangalore", "City selected");
		
		WebDriverCommonFunctions.element_EnterValuesToTextField("SearchByName", "Raj", "Supllier name entered");
		
		
		InitDriver.driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/ng-view/div[3]/div[2]/div/ng-transclude/div/div[2]/div[3]/img")).click();
		
		InitDriver.driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/ng-view/div[3]/div[2]/div/ng-transclude/div/div[2]/div[4]/div/a/div[1]/b")).click();
		Thread.sleep(3000);
		InitDriver.driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/ng-view/form/div[1]/ul[3]/li[4]/div/div[1]")).click();
		
		Thread.sleep(1000);
		
		
		
		paymentAndCreditDetails(dataList.subList(5, dataList.size()));
		WebDriverCommonFunctions.element_Click("SendPOSupplier", "Po sent to supplier");
				
}
	
	/*public static boolean isBeforeDay(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            throw new IllegalArgumentException("The dates must not be null");
        }
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        return isBeforeDay(cal1, cal2);
    }*/
	
	public void enterCompanyDetails(List<String> cData) throws Throwable{
		
		
		WebDriverCommonFunctions.element_SelectDropDown("Company_dropDown", "VISIBLETEXT", 0, cData.get(0), "Company selected");
		
		if (cData.get(1) != null) {
			WebDriverCommonFunctions.element_SelectDropDown("Project_dropDown", "VISIBLETEXT", 0, cData.get(1),"Project selected");
			
			if (WebDriverCommonFunctions.element_isVisible("SubProject_dropDown", "Checking if sub project is present") && cData.get(2) != null ) {
				
				WebDriverCommonFunctions.element_SelectDropDown("SubProject_dropDown", "VISIBLETEXT", 0, "subpro test", "Sub project selected");
			}
			if (WebDriverCommonFunctions.element_isVisible("Milestone_dropDown", "Checking if sub project is present") && cData.get(3) != null) {
				
				WebDriverCommonFunctions.element_SelectDropDown("Milestone_dropDown", "VISIBLETEXT", 0, "vcb", "Milestone selected");
			}
		}
		}
	
	public static boolean dateCompare(Date pastDate, Date currentDate) {

		boolean dateCompare = false;
		SimpleDateFormat formatter = new SimpleDateFormat();
		formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
		formatter.applyPattern("MM/dd/yyyy");
		try {
			Date finalPastDate = formatter.parse(formatter.format(pastDate));
			Date currentDATE = formatter.parse(formatter.format(currentDate));
			// System.out.println("finalPastDate is" + finalPastDate);
			// System.out.println("currentDate is" + currentDATE);

			if (finalPastDate.before(currentDATE)) {
				dateCompare = true;
				// System.out.println("Past Date");
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return dateCompare;
	}
	
	public void paymentAndCreditDetails(List<String> data) throws Throwable{
		
		InitDriver.driver.findElement(By.xpath(".//*[@id='advance']")).clear();
		Thread.sleep(1000);
		InitDriver.driver.findElement(By.xpath(".//*[@id='advance']")).sendKeys(data.get(0));
		System.out.println("..........."+data.get(0)+"..........");
		System.out.println("..........."+data.get(1)+"..........");
		System.out.println("..........."+data.get(2)+"..........");
		if (data.get(1).contains("Single Payment")) {
			WebDriverCommonFunctions.element_SelectDropDown("SelectCreditCycle", "VISIBLETEXT", 0, "Single Payment",
					"Single payment selected");
			WebDriverCommonFunctions.element_EnterValuesToTextField("CreditDaysField", data.get(2),
					"CreditDaysEntered");
		}else {
				
			WebDriverCommonFunctions.element_SelectDropDown("SelectCreditCycle", "VISIBLETEXT", 0, "Multiple Payments",
					"Single payment selected");
			for (int i = 0; i < Integer.parseInt(data.get(2)); i++) {
				
				WebDriverCommonFunctions.element_Click("AddRow", "AddRow button clicked ");
			}
			
			for (int i = 1; i <=(Integer.parseInt(data.get(2))*2); i++) {
				
				InitDriver.driver.findElement(By.xpath("(//table[@class='fixed_headers']/tbody//input)["+i+"]")).sendKeys(data.get(2+i));
			}
			
			
		}
		
	}
		
}

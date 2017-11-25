package com.modular.framework.Functional_TestSuites;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ch.qos.logback.core.joran.action.Action;

import com.modular.framework.Generic_Libraries.CommonFunctions;
import com.modular.framework.Generic_Libraries.Credentials;
import com.modular.framework.Generic_Libraries.FrameworkFunctions;
import com.modular.framework.Generic_Libraries.LoggerHelper;
import com.modular.framework.Generic_Libraries.RetrieveXlsxData;
import com.modular.framework.Generic_Libraries.WebDriverCommonFunctions;
import com.modular.framework.InitWebdriver.InitDriver;
import com.modular.framework.POM.HomePage;

public class fillingSupplierDetails extends InitDriver{
	
	
	static String[][] testDataArr;
	static String filePath = "D:/AutomationProject/WebAutomation_msupply_2/WebAutomation_msupply_2/src/test/resources/TestDataSheet.xls";
	static Logger log = LoggerHelper.writeLog(fillingSupplierDetails.class);
	
	 	@DataProvider(name = "dataTest")
		public static Object[][] validationC() throws Exception{
			
		String[][] testDataArr = RetrieveXlsxData.getTableArray("D:/AutomationProject/WebAutomation_msupply_2/WebAutomation_msupply_2/src/test/resources/TestDataSheet.xls", "Sheet4");
		return testDataArr;
	    }
	    @Test
		public void basicDetails() throws Throwable{
	    	Thread.sleep(3000);
	    	
	    	InitDriver.driver.findElement(By.partialLinkText("My Profile")).click();
	    	Actions act = new Actions(InitDriver.driver);
	    	//act.clickAndHold(InitDriver.driver.findElement(By.linkText("My Profile"))).build().perform();
	    	act.clickAndHold(InitDriver.driver.findElement(By.linkText("Profile"))).click().build().perform();
	    	//act.moveToElement(InitDriver.driver.findElement(By.linkText("Profile"))).build().perform();
	    	//InitDriver.driver.findElement(By.linkText("Profile ")).click();
	    	
	    	WebElement contactPerson = InitDriver.driver.findElement(By.xpath(".//*[@id='basicInfoForm']/div/div[1]/div[3]/div[1]/input"));
	    	
	    	log.info("changing values");
	    	Thread.sleep(3000);
	    	contactPerson.clear();
	    	contactPerson.sendKeys("Shiba");
	    	
	    	WebElement mobile = InitDriver.driver.findElement(By.xpath(".//*[@id='basicInfoForm']/div/div[1]/div[3]/div[4]/input"));
	    	
	    	mobile.clear();
	    	mobile.sendKeys("9901666001");
	    	
	    	
	    	
	    	new Select(InitDriver.driver.findElement(By.xpath(".//*[@id='basicInfoForm']/div/div[2]/div[2]/div[5]/select"))).selectByVisibleText("Retailer");
	    	
	    	InitDriver.driver.findElement(By.cssSelector("input[type='files']")).sendKeys("FilePath");
	    	
	    	WebElement addressL = InitDriver.driver.findElement(By.xpath(".//*[@id='basicInfoForm']/div/div[3]/div[3]/div/div/div[1]/input"));
	    	addressL.clear();
	    	addressL.sendKeys("addressL");
	    	
	    	new Select(InitDriver.driver.findElement(By.xpath(".//*[@id='basicInfoForm']/div/div[3]/div[3]/div/div/div[3]/select"))).selectByVisibleText("Assam");
	    	new Select(InitDriver.driver.findElement(By.xpath(".//*[@id='basicInfoForm']/div/div[3]/div[3]/div/div/div[4]/select"))).selectByVisibleText("Barpeta");
	    	
	    	WebElement pincode = InitDriver.driver.findElement(By.xpath(".//*[@id='basicInfoForm']/div/div[3]/div[3]/div/div/div[5]/div[2]/input"));
	    	
	    	pincode.clear();
	    	pincode.sendKeys("560012");
	    	
	    	WebElement addressl2 = InitDriver.driver.findElement(By.xpath(".//*[@id='basicInfoForm']/div/div[4]/div[2]/div[1]/input"));
	    	addressl2.clear();
	    	addressl2.sendKeys("masert");
	    	
	    	new Select(InitDriver.driver.findElement(By.xpath(".//*[@id='basicInfoForm']/div/div[4]/div[2]/div[3]/select"))).selectByVisibleText("Assam");
	    	new Select(InitDriver.driver.findElement(By.xpath(".//*[@id='basicInfoForm']/div/div[4]/div[2]/div[4]/select"))).selectByVisibleText("Barpeta");
	    	
	    	WebElement pincode2 = InitDriver.driver.findElement(By.xpath(".//*[@id='basicInfoForm']/div/div[4]/div[2]/div[5]/div[2]/input"));
	    	
	    	pincode2.clear();
	    	pincode2.sendKeys("560076");
	    	
	    	InitDriver.driver.findElement(By.xpath(".//*[@id='basicInfoForm']/div/div[6]/button")).click();
	    	
	    	Thread.sleep(3000);
	    	//new Select(InitDriver.driver.findElement(By.xpath(".//*[@id='addFullfillment']/div/div[1]/div[2]/div[1]/select"))).selectByVisibleText("Assam");
	    	/*WebElement panNoElement = InitDriver.driver.findElement(By.xpath(".//*[@id='addFullfillment']/div/div[1]/div[2]/div[2]/input"));
	    	panNoElement.clear();
	    	panNoElement.sendKeys("BSCPP1234A");*/
	    	
	    	WebElement accHolderNam = InitDriver.driver.findElement(By.xpath(".//*[@id='addFullfillment']/div/div[2]/div[2]/div[1]/input"));
	    	accHolderNam.clear();
	    	accHolderNam.sendKeys("Himanshu");
	    	
	    	/*WebElement accNum = InitDriver.driver.findElement(By.xpath(".//*[@id='addFullfillment']/div/div[2]/div[2]/div[2]/input"));
	    	accNum.clear();
	    	accNum.sendKeys("10563427890");
	    	
	    	WebElement reEnter = InitDriver.driver.findElement(By.xpath(".//*[@id='addFullfillment']/div/div[2]/div[2]/div[3]/input"));
	    	reEnter.clear();
	    	reEnter.sendKeys("10563427890");*/
	    	
	    	WebElement ifsc = InitDriver.driver.findElement(By.xpath(".//*[@id='addFullfillment']/div/div[2]/div[3]/div[1]/input"));
	    	ifsc.clear();
	    	ifsc.sendKeys("ICIC0003168");
	    	
	    	log.info("changing account type");
	    	
	    	new Select(InitDriver.driver.findElement(By.xpath(".//*[@id='addFullfillment']/div/div[2]/div[3]/div[2]/select"))).selectByVisibleText("Saving Account");
	    	
	    	//log.info("uploading file");
	    	//InitDriver.driver.findElement(By.xpath(".//*[@id='addFullfillment']/div/div[2]/div[3]/div[3]/div/div/label")).sendKeys("Desktop/2nd April to 8th April.PNG");
	    	InitDriver.driver.findElement(By.xpath(".//*[@id='addFullfillment']/div/div[6]/button")).click();
	    }
	    
	    
	

}

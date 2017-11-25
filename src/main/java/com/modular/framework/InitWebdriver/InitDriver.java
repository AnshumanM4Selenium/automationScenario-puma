package com.modular.framework.InitWebdriver;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.modular.framework.Functional_TestSuites.QuoteNewEnquiriesTest;
import com.modular.framework.Generic_Libraries.BrowserSelection;
import com.modular.framework.Generic_Libraries.CommonFunctions;
import com.modular.framework.Generic_Libraries.Credentials;
import com.modular.framework.Generic_Libraries.LoggerHelper;
import com.modular.framework.Generic_Libraries.RetrieveXlsxData;
import com.modular.framework.Generic_Libraries.WebDriverCommonFunctions;

public class InitDriver {
	
	public static WebDriverCommonFunctions wdcf;
    public static RetrieveXlsxData rXlsx;
    public static WebDriver driver;
    public static org.apache.log4j.Logger log;
    //public static POM.HomePage homePageObj;
    public static SoftAssert softAssert;
    public static boolean SoftAssert_AfterText;
    
    static String Parent;
	static String Child;
   @BeforeTest
    public void beforeTestCofig() throws Throwable
    {
    	/*Object initialization*/
    	wdcf = new WebDriverCommonFunctions();
		rXlsx = new RetrieveXlsxData();
		log = LoggerHelper.writeLog(InitDriver.class);
		log.info("about to click on url");
		driver=BrowserSelection.selectBrowserDriver("chrome");		
		WebDriverCommonFunctions wb=new WebDriverCommonFunctions();
		wb.maximizingWindow();
		InitDriver.wdcf.waitForPageToLoad();
		softAssert = new SoftAssert(); 
		log.info("about to click on url");
		Credentials.url=CommonFunctions.readPropertiesFile("Functional_HomePage");
		InitDriver.driver.get(Credentials.url);
		Thread.sleep(5000);
		
		log.info("Clicked Buyer Login link");
		//Thread.sleep(2000);
		
		
		WebElement buyerLogin = InitDriver.driver.findElement(By.xpath("html/body/header/div[2]/div[1]/div[4]/a"));
		//InitDriver.driver.findElement(By.xpath("html/body/div[3]/ng-view/div[1]/form/input")).sendKeys("anshuman123");
		
		CommonFunctions.SearchForElement(buyerLogin);//Common functions-4
		try
		{
			buyerLogin.click();
		}
		catch(Exception e)
		{
			CommonFunctions.SearchForElement_Method_2(buyerLogin, "CLICK");
		}
		//CommonFunctions.LoadPageExpicitWait(); //Common functions-5
		
		//CommonFunctions.scrollPageUp(0,-1000); //Common functions-6
		
		
		//...............................supplier link scenario..................................
		/*Set<String> handle= InitDriver.driver.getWindowHandles();
		Iterator<String> windows = InitDriver.driver.getWindowHandles().iterator();
		
		Parent=windows.next();
		Child=windows.next();
		InitDriver.driver.switchTo().window(Child);
		Thread.sleep(2000);
		WebElement userIdTBox = InitDriver.driver.findElement(By.xpath("html/body/div[3]/ng-view/div[1]/form/input"));
		
		//CommonFunctions.scrollPageUp(0,-1000);//Common functions-3
		CommonFunctions.SearchForElement(userIdTBox);//Common functions-4
		//userIdTBox.clear();
		userIdTBox.sendKeys("anf123");
		
        WebElement pwd = InitDriver.driver.findElement(By.xpath("html/body/div[3]/ng-view/div[1]/form/div[1]/input"));
		
		//CommonFunctions.scrollPageUp(0,-1000);//Common functions-3
		CommonFunctions.SearchForElement(pwd);//Common functions-4
		pwd.clear();
		pwd.sendKeys("123456");
		//InitDriver.driver.findElement(By.xpath("html/body/div[3]/ng-view/div[1]/form/div[1]/input")).sendKeys("123456");
		//Thread.sleep(4000);
		
		WebElement signIn = InitDriver.driver.findElement(By.xpath("//form//button[text()='Sign In']"));
		CommonFunctions.SearchForElement(signIn);
		signIn.click();*/
		
		/*pwd.sendKeys("123456");
		signIn.click();*/
		
		//.........................  buyer login scenario .....................................................
		
		WebElement userIdTBox = InitDriver.driver.findElement(By.xpath("(//div[@class='user-content pull-left']/form[@name='userLoginForm']//div[@class='input-box-wrapper']/input)[1]"));
		CommonFunctions.SearchForElement(userIdTBox);
		userIdTBox.sendKeys("9141665789");
		
		WebElement pwd = InitDriver.driver.findElement(By.xpath("(//div[@class='user-content pull-left']/form[@name='userLoginForm']//div[@class='input-box-wrapper']/input)[2]"));
		CommonFunctions.SearchForElement(pwd);
		pwd.sendKeys("123456");
		
		WebElement signIn = InitDriver.driver.findElement(By.xpath("//div[@class='user-content pull-left']/form[@name='userLoginForm']//div[@class='user-details-section']//button"));
		CommonFunctions.SearchForElement(signIn);
		signIn.click();
		
    }
   
    
    /*@AfterTest
	public void tearDown() throws Throwable
	{
    	InitDriver.softAssert.assertAll();
    	
//    	ShoppingCartPage.removeCartProducts();
    	//driver.close();
	}*/
}

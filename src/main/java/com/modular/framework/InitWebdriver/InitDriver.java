package com.modular.framework.InitWebdriver;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;


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
		Thread.sleep(2000);
		
		
    }
   
    
    /*@AfterTest
	public void tearDown() throws Throwable
	{
    	InitDriver.softAssert.assertAll();
    	
//    	ShoppingCartPage.removeCartProducts();
    	//driver.close();
	}*/
}

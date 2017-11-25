package com.modular.framework.POM;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


import com.modular.framework.Generic_Libraries.LoggerHelper;
import com.modular.framework.Generic_Libraries.WebDriverCommonFunctions;
import com.modular.framework.InitWebdriver.InitDriver;

public class HomePage 
{
	
	static Logger log = LoggerHelper.writeLog(HomePage.class);
	
	public static void click_CustomerLogin() throws Throwable{
		WebDriverCommonFunctions.element_Click("Customer_Login_Link", "CustomerLogin link clicked");
	}
	
	public static void customerLoginin(String data1, String data2){
		
		log.info("Mobile no. ........"+data1);
		WebElement mobileNum = InitDriver.driver.findElement(By.id("mobile"));
		mobileNum.sendKeys(data1);
		
		log.info("Password ........"+data2);
		WebElement password = InitDriver.driver.findElement(By.id("password"));
		password.sendKeys(data2);
		
		WebElement loginButton = InitDriver.driver.findElement(By.xpath("//button[text()='Login']"));
		loginButton.click();
	}

}

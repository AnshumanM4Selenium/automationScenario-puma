package com.modular.framework.Functional_TestSuites;

import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.modular.framework.Generic_Libraries.CommonFunctions;
import com.modular.framework.Generic_Libraries.Credentials;
import com.modular.framework.Generic_Libraries.LoggerHelper;
import com.modular.framework.InitWebdriver.InitDriver;

public class StucturingIquiryTest {
	
	static Logger log = LoggerHelper.writeLog(StucturingIquiryTest.class);
	
	@Test
	public void stuctureInquiry(String email, String pwd) throws Throwable {
		
		Credentials.url=CommonFunctions.readPropertiesFile("Functional_HomePage");
		InitDriver.driver.get(Credentials.url);
		
		//Selecting a sub category
    	log.info("1st data or Sub category........");
		InitDriver.driver.findElement(By.xpath("//form/div/input[@placeholder='Email Id *']")).sendKeys(email);
		Thread.sleep(1000);
		InitDriver.driver.findElement(By.xpath("//form/div/input[@placeholder='Password *']")).sendKeys(pwd);
		
		InitDriver.driver.findElement(By.xpath("//form/button[text()='Login']")).click();
		Thread.sleep(50000);
		
		Scanner sc = new Scanner(System.in);
		String otp = sc.nextLine();
		InitDriver.driver.findElement(By.xpath("//form/div/input[@placeholder='Enter OTP *']")).sendKeys(otp);
		InitDriver.driver.findElement(By.xpath("//form/button[text()='Verify']")).click();
		InitDriver.driver.findElement(By.xpath("//ul[@class='nav navbar-nav']//li/a[text()='Supplier Panel']")).click();
		
		
  }
}

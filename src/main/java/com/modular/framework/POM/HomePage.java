package com.modular.framework.POM;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.modular.framework.Generic_Libraries.LoggerHelper;
import com.modular.framework.Generic_Libraries.WebDriverCommonFunctions;
import com.modular.framework.InitWebdriver.InitDriver;

public class HomePage 
{
	
	static Logger log = LoggerHelper.writeLog(HomePage.class);
	
	
	
	public static String getPageTitle(){
		
		String actualTitle = InitDriver.driver.getTitle();
		return actualTitle;
		
	}
	
	public static void clickOnHamburger(String locatorIdentifier_part1, String locatorIdentifier_part2, String catgory) throws Throwable{
		
		WebElement element = WebDriverCommonFunctions.selectDynamicLink(locatorIdentifier_part1, locatorIdentifier_part2, catgory);
		
		Actions act = new Actions(InitDriver.driver);
		act.moveToElement(element).build().perform();
		
	}
	
	public static void clickOnSubMenu_Hamburger(String locatorIdentifier_part1, String locatorIdentifier_part2, String catgory) throws Throwable{
		
		WebElement element = WebDriverCommonFunctions.selectDynamicLink(locatorIdentifier_part1, locatorIdentifier_part2, catgory);
		
		Actions act = new Actions(InitDriver.driver);
		act.moveToElement(element).click().build().perform();
		
	}

}

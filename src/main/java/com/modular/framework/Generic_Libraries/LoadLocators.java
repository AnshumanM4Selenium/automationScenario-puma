package com.modular.framework.Generic_Libraries;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.modular.framework.InitWebdriver.InitDriver;

public class LoadLocators {
	
	public static WebElement loadElementByID(String Element) throws Throwable
	{
		WebElement element=InitDriver.driver.findElement(By.id(Element));
		((JavascriptExecutor)InitDriver.driver).executeScript("arguments[0].scrollIntoView(true);", element);
		return element;
	}
	
	public static WebElement loadElementByName(String Element) throws Throwable
	{
		WebElement element=InitDriver.driver.findElement(By.name(Element));
		((JavascriptExecutor)InitDriver.driver).executeScript("arguments[0].scrollIntoView(true);", element);
		return element;
	}
	
	public static WebElement loadElementByClassName(String Element) throws Throwable
	{
		WebElement element=InitDriver.driver.findElement(By.className(Element));
		((JavascriptExecutor)InitDriver.driver).executeScript("arguments[0].scrollIntoView(true);", element);
		return element;
	}
	
	public static WebElement loadElementByTagName(String Element) throws Throwable
	{
		WebElement element=InitDriver.driver.findElement(By.tagName(Element));
		((JavascriptExecutor)InitDriver.driver).executeScript("arguments[0].scrollIntoView(true);", element);
		return element;
	}
	
	public static WebElement loadElementByLinkText(String Element) throws Throwable
	{
		WebElement element=InitDriver.driver.findElement(By.linkText(Element));
		((JavascriptExecutor)InitDriver.driver).executeScript("arguments[0].scrollIntoView(true);", element);
		return element;
	}
	
	public static WebElement loadElementByPartialLinkText(String Element) throws Throwable
	{
		WebElement element=InitDriver.driver.findElement(By.partialLinkText(Element));
		((JavascriptExecutor)InitDriver.driver).executeScript("arguments[0].scrollIntoView(true);", element);
		return element;
	}
	
	public static WebElement loadElementByXpath(String Element) throws Throwable
	{
		WebElement element=InitDriver.driver.findElement(By.xpath(Element));
		((JavascriptExecutor)InitDriver.driver).executeScript("arguments[0].scrollIntoView(true);", element);
		return element;
	}

	public static List<WebElement> loadElementByXpath_findElements(String Element) throws Throwable
	{
		List<WebElement> element=InitDriver.driver.findElements(By.xpath(Element));
		return element;
	}
	
	public static WebElement loadElementByCssSelector(String Element) throws Throwable
	{
		WebElement element=InitDriver.driver.findElement(By.cssSelector(Element));
		((JavascriptExecutor)InitDriver.driver).executeScript("arguments[0].scrollIntoView(true);", element);
		return element;
	}	
}

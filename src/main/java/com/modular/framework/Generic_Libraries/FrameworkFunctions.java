package com.modular.framework.Generic_Libraries;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.modular.framework.InitWebdriver.InitDriver;

public class FrameworkFunctions {
	static Logger log = LoggerHelper.writeLog(FrameworkFunctions.class);
	public static void clickOnAddToListButton(String data){
		
		log.info("The Product to be selected : "+data);
		List<WebElement> productInfos = InitDriver.driver.findElements(By.xpath("//div[@class='ms-product-info']/a"));
		int size1 = productInfos.size();
		int count = 0;
		
		
		List<WebElement> addToListButtons = InitDriver.driver.findElements(By.xpath("//div[@class='ms-product ng-scope']//button"));
		int size2 = addToListButtons.size();
		for (int i = 0, j = 0; i < size1 && j < size2; i++, j++) {
			
			
				
				if ((productInfos.get(i).getAttribute("title")!= "") && (productInfos.get(i).getText().contains(data))) {
					
					addToListButtons.get(j).click();
					break;
				}
				else {
					count++;
					
				}
				
			
			continue;
			
		}
		log.info("No. of products without title : "+count);
	}

}

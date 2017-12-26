package com.modular.framework.POM;

import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;

import com.modular.framework.Generic_Libraries.CommonFunctions;
import com.modular.framework.Generic_Libraries.LoggerHelper;
import com.modular.framework.Generic_Libraries.WebDriverCommonFunctions;

public class ProductListPage {
	
	static Logger log = LoggerHelper.writeLog(ProductListPage.class);
	
	public static void selectProduct(String locatorIdentifier_part1,String locatorIdentifier_part2, String prodNumber,String Action ) throws Throwable{
		
		//WebElement element2 = WebDriverCommonFunctions.element_SelectProduct_ProductListPage(locator,prodNumber);
		WebElement element = WebDriverCommonFunctions.selectDynamicLink(locatorIdentifier_part1, locatorIdentifier_part2, prodNumber);
		CommonFunctions.SearchForElement_Method_2(element, Action);
		
	}
}

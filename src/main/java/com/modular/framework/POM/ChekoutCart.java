package com.modular.framework.POM;

import org.apache.log4j.Logger;

import com.modular.framework.Generic_Libraries.LoggerHelper;
import com.modular.framework.Generic_Libraries.WebDriverCommonFunctions;

public class ChekoutCart {
	
	static Logger log = LoggerHelper.writeLog(ChekoutCart.class);
	
	public static String fretchProductName(String locator) throws Throwable{
		
		return WebDriverCommonFunctions.element_GetTextFromLinkText(locator);
	}
	
	public static String fretchProductPrice(String locator) throws Throwable{
		
		String price = WebDriverCommonFunctions.element_GetTextFromLinkText(locator);
		
		
		return price;
	}
	
	public static int fretchProductQuantity(String locator, String attributeName) throws Throwable{
		
		String value = WebDriverCommonFunctions.element_fromAttribute(locator,attributeName);
		
		int quantity = Integer.parseInt(value);
		return quantity;
	}
}

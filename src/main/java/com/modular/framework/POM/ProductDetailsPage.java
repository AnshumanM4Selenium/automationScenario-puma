package com.modular.framework.POM;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;

import com.modular.framework.Generic_Libraries.CommonFunctions;
import com.modular.framework.Generic_Libraries.LoggerHelper;
import com.modular.framework.Generic_Libraries.WebDriverCommonFunctions;

public class ProductDetailsPage {
	
	static Logger log = LoggerHelper.writeLog(ProductDetailsPage.class);
	
	public static String fretchProductName(String locator) throws Throwable{
		
		return WebDriverCommonFunctions.element_GetTextFromLinkText(locator);
	}
	
	public static String fretchProductPrice(String locator) throws Throwable{
		String price = WebDriverCommonFunctions.element_GetTextFromLinkText(locator);
		
		//String newPrice = price.replace("₹", "").replace(",", "");
		/*Pattern pt = Pattern.compile("[()]"); 
				Matcher match= pt.matcher(price);
				while(match.find())
				{
				    String s= match.group();
				    price=price.replace("₹", "").replace(",", "");
				}*/
		return price;
	}
	
	/*public static int selectQuantity(String locator,String INDEXorVALUEorVISIBLETEXT, int Index, String Value, String Message) throws Throwable{
		
		WebDriverCommonFunctions.element_SelectDropDown(locator, INDEXorVALUEorVISIBLETEXT, Index, Value, Message);
		
		return Integer.parseInt(Value);
	}*/
	
	public static void selectShoeSize(String locator1,String locator2,String locator3,String Value,String Action, String Message) throws Throwable{
		WebDriverCommonFunctions.element_Click(locator1,Message );	
		
		selectProduct(locator2, locator3, Value, Action);
		
		
	}
	
	public static void addToCart(String locator_button,String notify_button,String Message1,String Message2,String locator_email,String locator_SubmitBTN) throws Throwable{
		
		if (WebDriverCommonFunctions.element_isEnabled(locator_button, Message1)) {
			
			WebDriverCommonFunctions.element_Click(locator_button, Message2);
		}
		else {
			WebDriverCommonFunctions.element_Click(notify_button, "Notify button clicked");
			WebDriverCommonFunctions.element_EnterValuesToTextField(locator_email, "kg.anshuman@gmail.com", "notufy me");
			WebDriverCommonFunctions.element_Click(locator_SubmitBTN, "Notify button clicked");
		}
		
	}

	public static int selectQuantity(String locator, String INDEXorVALUEorVISIBLETEXT, int Index, String Value, String Message) throws Throwable {
		WebDriverCommonFunctions.element_SelectDropDown(locator, INDEXorVALUEorVISIBLETEXT, Index, Value, Message);
		return Integer.parseInt(Value);
	}
	
	
	public static void selectProduct(String locatorIdentifier_part1,String locatorIdentifier_part2, String prodNumber,String Action ) throws Throwable{
		
		//WebElement element2 = WebDriverCommonFunctions.element_SelectProduct_ProductListPage(locator,prodNumber);
		WebElement element = WebDriverCommonFunctions.selectDynamicLink(locatorIdentifier_part1, locatorIdentifier_part2, prodNumber);
		CommonFunctions.SearchForElement_Method_2(element, Action);
		
	}
	
	

}

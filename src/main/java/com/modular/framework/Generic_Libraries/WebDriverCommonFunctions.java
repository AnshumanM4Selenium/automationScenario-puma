package com.modular.framework.Generic_Libraries;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.openqa.selenium.JavascriptExecutor;

import com.modular.framework.InitWebdriver.InitDriver;

import net.sourceforge.tess4j.Tesseract;

public class WebDriverCommonFunctions {
	
	 	static Logger log = LoggerHelper.writeLog(WebDriverCommonFunctions.class);

	  //==============================================================================================================================================    
		public static void element_Click(String locator,String Message) throws Throwable
		{
			CommonFunctions.LoadPageExpicitWait(); //Common functions-1
			WebElement element=(WebElement) CommonFunctions.ConstructElementFromExcel(locator);//Common functions-2
			CommonFunctions.scrollPageUp(0,-1000);//Common functions-3
			CommonFunctions.SearchForElement(element);//Common functions-4
			try
			{
			   element.click();
			}
			catch(Exception e)
			{
				CommonFunctions.SearchForElement_Method_2(element, "CLICK");
			}
			CommonFunctions.LoadPageExpicitWait(); //Common functions-5
			CommonFunctions.scrollPageUp(0,-1000); //Common functions-6
			PrintinLogAndHTMLReports(Message); //Common functions-7
		}
		
		//==============================================================================================================================================
		
		public static void element_isEnabled(String locator,String Message) throws Throwable
		{
			CommonFunctions.LoadPageExpicitWait(); //Common functions-1
			WebElement element=(WebElement) CommonFunctions.ConstructElementFromExcel(locator);//Common functions-2
			 element.isEnabled();
			CommonFunctions.LoadPageExpicitWait(); //Common functions-5
			CommonFunctions.scrollPageUp(0,-1000); //Common functions-6
			PrintinLogAndHTMLReports(Message); //Common functions-7
		}
		
		//==============================================================================================================================================
		
		public static boolean element_isVisible(String locator,String Message) throws Throwable
		{
			CommonFunctions.LoadPageExpicitWait(); //Common functions-1
			WebElement element=(WebElement) CommonFunctions.ConstructElementFromExcel(locator);//Common functions-2
			CommonFunctions.scrollPageUp(0,-1000);
			CommonFunctions.SearchForElement(element);
			boolean status=element.isDisplayed();
			CommonFunctions.LoadPageExpicitWait(); //Common functions-5
			//CommonFunctions.scrollPageUp(0,-1000); //Common functions-6
			PrintinLogAndHTMLReports(Message); //Common functions-7
			return status;
		}
		
		//==============================================================================================================================================
		
		/*
		 * Exapmle : WebElement element=WebDriverCommonFunctions.Table_SearchForElement(CommonFunctions.getElementFromExcel("AllOrders_Xpath_1"),CommonFunctions.getElementFromExcel("AllOrders_Xpath_2"),2,2016014000);
		 * 
		 */
		public static WebElement Table_SearchForElement(String Xpath_Part_1,String Xpath_Part_2,int IncrementValue,int NumberToSearchinTable) throws Throwable 
		{
			CommonFunctions.LoadPageExpicitWait(); //Common functions-1
			CommonFunctions.scrollPageUp(0,-1000);//Common functions-3
			WebElement element = null;
			
			int i=1;
			
			while(checkifRowExistsinTable(Xpath_Part_1+i+Xpath_Part_2))
		    {
			  element=InitDriver.driver.findElement(By.xpath(Xpath_Part_1+i+Xpath_Part_2));
			  int NumberinTable=CommonFunctions.getNumber(element.getText());
			  if(NumberToSearchinTable==NumberinTable)
			  {
				  System.out.println("Success");
				  break;
			  }
			  else
			  {
			    i=i+IncrementValue;
			  }
		    }
			System.out.println(i);
			CommonFunctions.LoadPageExpicitWait(); //Common functions-5
			CommonFunctions.scrollPageUp(0,-1000); //Common functions-6
			return element;
			
		}
		
		//==============================================================================================================================================
		
		
		/*
		 * Exapmle : WebElement element=WebDriverCommonFunctions.Table_SearchForElement(CommonFunctions.getElementFromExcel("AllOrders_Xpath_1"),CommonFunctions.getElementFromExcel("AllOrders_Xpath_2"),2,2016014000);
		 * 
		 */
		public static Object Table_SearchForElement_Action(String Xpath_Part_1,String Xpath_Part_2,int IncrementValue,String Action,int LocationCount) throws Throwable 
		{
			CommonFunctions.LoadPageExpicitWait(); //Common functions-1
			
			int i=1;
			int CurrentLocation=1;
			WebElement element = null;
			ArrayList<String> elementText=new ArrayList<String>();

			while(checkifRowExistsinTable(Xpath_Part_1+i+Xpath_Part_2))
		    {		  
				//System.out.println(Xpath_Part_1+i+Xpath_Part_2);
			   element=InitDriver.driver.findElement(By.xpath(Xpath_Part_1+i+Xpath_Part_2));
			   CommonFunctions.scrollPageUp(0,-1000);
			   CommonFunctions.SearchForElement(element);//Common functions-4
			   
			  if(Action.equals("GETTEXT"))
			  {
				  String text= element.getText();
				  elementText.add(text);
				  if(CurrentLocation==LocationCount)
				  {
					  return elementText;
				  }
				  else
				  {
				    i=i+IncrementValue;
				    CurrentLocation=CurrentLocation+1;
				  }			  
			  }
			  if(Action.equals("ELEMENT_IS_DISPLAYED"))
			  {
				    if(!element.isDisplayed())
				    {
				            Print_WithException_inLogAndHTMLReports("Element not displayed.");
				            
				    }
				    else
				    {
				    	PrintinLogAndHTMLReports("Element displayed.");
				    }
				  if(CurrentLocation==LocationCount)
				  {
					  return elementText;
				  }
				  else
				  {
				    i=i+IncrementValue;
				    CurrentLocation=CurrentLocation+1;
				  }			  
			  }
			  if(Action.equals("CHECK_IMAGE_PRESENT"))
			  {			  
				    Boolean ImagePresent = (Boolean) ((JavascriptExecutor)InitDriver.driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", element);
				    if (!ImagePresent)
				    {
				            Print_WithException_inLogAndHTMLReports("Image not displayed.");
				            
				    }
				    else
				    {
				    	PrintinLogAndHTMLReports("Image displayed.");
				    }
				    
				    
				    i=i+IncrementValue;
				    CurrentLocation=CurrentLocation+1;
				    
				  
			  }
			  
			  if(Action.equals("CLICK")) //This will click on single or multiple option
			  {
				     CommonFunctions.SearchForElement_Method_2(element,"CLICK");
				     if(CurrentLocation==LocationCount)
				     {
					    break;
				     }
				     else
				     {
				         i=i+IncrementValue;
				         CurrentLocation=CurrentLocation+1;
				     }
			  }
			  if(Action.equals("GET_TEXT_IMAGE")) //This will click on single or multiple option
			  {
				     File imageFile = WebElementExtender.captureElementPicture(element);
				     Tesseract instance = new Tesseract();
				     String result = instance.doOCR(imageFile);			        
				     elementText.add(result);
				     if(CurrentLocation==LocationCount)
				     {
				    	return elementText; 
					 }
				     else
				     {
				         i=i+IncrementValue;
				         CurrentLocation=CurrentLocation+1;
				     }
				     
				     
			  }
			  if(Action.equals("CheckBoxSelectedCount")) 
			  {
				     elementText.add(element.getAttribute("class"));			     
				     
				     if(CurrentLocation==LocationCount)
				     {
				    	 
				    	return elementText; 
					 }
				     else
				     {
				         i=i+IncrementValue;
				         CurrentLocation=CurrentLocation+1;
				     }	     
				     
			  }
		    }
			
			
			CommonFunctions.LoadPageExpicitWait(); //Common functions-5
			CommonFunctions.scrollPageUp(0,-1000); //Common functions-6
			return element;
			
		}
		
		//==============================================================================================================================================
		
		public static String element_getTextFromImage(String locator,String Message) throws Throwable
		{
			CommonFunctions.LoadPageExpicitWait(); //Common functions-1
			WebElement element=(WebElement) CommonFunctions.ConstructElementFromExcel(locator);//Common functions-2
			CommonFunctions.scrollPageUp(0,-1000);//Common functions-3
			CommonFunctions.SearchForElement(element);//Common functions-4

			File imageFile = WebElementExtender.captureElementPicture(element);
	        Tesseract instance = new Tesseract();
	        String result = instance.doOCR(imageFile);
			
			CommonFunctions.LoadPageExpicitWait(); //Common functions-5
			//CommonFunctions.scrollPageUp(0,-1000); //Common functions-6
			PrintinLogAndHTMLReports(Message); //Common functions-7
			
			return result;
		}
		
		//==============================================================================================================================================
		
		public static void element_MouseOver(String locator,String Message) throws Throwable
		{
			CommonFunctions.LoadPageExpicitWait(); //Common functions-1
			WebElement element=(WebElement) CommonFunctions.ConstructElementFromExcel(locator);//Common functions-2
			CommonFunctions.scrollPageUp(0,-1000);//Common functions-3
			CommonFunctions.SearchForElement(element);//Common functions-4
			InitDriver.wdcf.mouseOverOperation((WebElement)CommonFunctions.ConstructElementFromExcel(locator));
			Thread.sleep(1000);		
		    PrintinLogAndHTMLReports(Message); //Common functions-7
		}
		
		//==============================================================================================================================================
		
		
		//Select Element Based on a Particular Category
		//Example : WebDriverCommonFunctions.element_Selectproduct_Navigation(2,6,"Navigation => Buildingmaterial => Blocks");
		
		public static void element_Selectproduct_Navigation(int MainCategory_Number,int SubCategory_Number,boolean newProduct,String Message) throws Throwable
		{	
			ArrayList<String> elements=new ArrayList<String>();
			elements.add("Navigation_Shop_Xpath");
			elements.add("Main_Category_Xpath");
			elements.add("Navigation_SubCategory_Xpath");
			
			CommonFunctions.LoadPageExpicitWait(); //Common functions-1
			WebElement element1=(WebElement) CommonFunctions.ConstructElementFromExcel((String) elements.get(0));//Common functions-2
			CommonFunctions.scrollPageUp(0,-1000);//Common functions-3
			CommonFunctions.SearchForElement(element1);//Common functions-4
			for(int i=0;i<elements.size();i++)
			{
				if(i==0)
				{
			       InitDriver.wdcf.mouseOverOperation((WebElement)CommonFunctions.ConstructElementFromExcel((String)elements.get(i)));
			       Thread.sleep(1000);
				}
				if(i==1)
				{
					String MainCategoryxpath=CommonFunctions.getElementFromExcel("Main_Category_Xpath");
					MainCategoryxpath=MainCategoryxpath+"["+MainCategory_Number+"]/a";				
					WebElement element=InitDriver.driver.findElement(By.xpath(MainCategoryxpath));
					InitDriver.wdcf.mouseOverOperation(element);				
					Thread.sleep(1000);
				}
				if(i==2)
				{
					String SubCategoryxpath=CommonFunctions.getElementFromExcel("Navigation_SubCategory_Xpath");
					SubCategoryxpath=SubCategoryxpath+"["+MainCategory_Number+"]/li"+"["+SubCategory_Number+"]";	
					Thread.sleep(1000);
					WebElement element=InitDriver.driver.findElement(By.xpath(SubCategoryxpath));
					InitDriver.wdcf.mouseOverOperation(element);	
					
					Thread.sleep(1000);
					WebElement element2=InitDriver.driver.findElement(By.xpath(SubCategoryxpath));
					element2.click();		
					
				}
			
			}		
			CommonFunctions.LoadPageExpicitWait();
			Thread.sleep(1000);	
		    PrintinLogAndHTMLReports(Message); //Common functions-7
		    
		    //Select Product based on the Product number in Excel

		    if(newProduct==true)
		    {
			       String sData[]=RetrieveXlsxData.getExcelData("Product_Number");
			       String Product=CommonFunctions.getElementFromExcel("Product_Xpath");
			       Product=Product+"["+sData[1]+"]";	
			       CommonFunctions.scrollPageUp(0,-1000);
			       WebElement element=InitDriver.driver.findElement(By.xpath(Product));
			       CommonFunctions.SearchForElement(element);
			       element.click();
			       CommonFunctions.LoadPageExpicitWait();
			       CommonFunctions.scrollPageUp(0,-1000);
			       int Count=Integer.parseInt(sData[1]);
			       Count=Count+1;
			       if(Count>12)
				      Count=1;
			       RetrieveXlsxData.overwriteExcelData("Product_Number",Count,1);
		    }
		    else
		    {	    	
		    	   String Product=CommonFunctions.getElementFromExcel("Product_Xpath");
			       Product=Product+"["+1+"]";	
			       CommonFunctions.scrollPageUp(0,-1000);
			       WebElement element=InitDriver.driver.findElement(By.xpath(Product));
			       CommonFunctions.SearchForElement(element);
			       element.click();
			       CommonFunctions.LoadPageExpicitWait();
			       CommonFunctions.scrollPageUp(0,-1000);		       
		    }
	  }
		
		
		//==============================================================================================================================================
		
		
		public static void element_SelectProduct_ProductListPage(boolean newProduct) throws Throwable
		{
			if(newProduct==true)
		    {
			       String sData[]=RetrieveXlsxData.getExcelData("Product_Number");
			       String Product=CommonFunctions.getElementFromExcel("Product_Xpath");
			       Product=Product+"["+sData[1]+"]";	
			       CommonFunctions.scrollPageUp(0,-1000);
			       WebElement element=InitDriver.driver.findElement(By.xpath(Product));
			       CommonFunctions.SearchForElement(element);
			       element.click();
			       CommonFunctions.LoadPageExpicitWait();
			       CommonFunctions.scrollPageUp(0,-1000);
			       int Count=Integer.parseInt(sData[1]);
			       Count=Count+1;
			       if(Count>12)
				      Count=1;
			       RetrieveXlsxData.overwriteExcelData("Product_Number",Count,1);
		    }
		    else
		    {	    	
		    	   String Product=CommonFunctions.getElementFromExcel("Product_Xpath");
			       Product=Product+"["+1+"]";	
			       CommonFunctions.scrollPageUp(0,-1000);
			       WebElement element=InitDriver.driver.findElement(By.xpath(Product));
			       CommonFunctions.SearchForElement(element);
			       element.click();
			       CommonFunctions.LoadPageExpicitWait();
			       CommonFunctions.scrollPageUp(0,-1000);		       
		    }
			
		}
		
		//==============================================================================================================================================
		
		
		public static void element_MouseOver_TillElementClick(ArrayList elements,String Message) throws Throwable
		{	
			CommonFunctions.LoadPageExpicitWait(); //Common functions-1
			WebElement element1=(WebElement) CommonFunctions.ConstructElementFromExcel((String) elements.get(0));//Common functions-2
			CommonFunctions.scrollPageUp(0,-1000);//Common functions-3
			CommonFunctions.SearchForElement(element1);//Common functions-4
			for(int i=0;i<elements.size();i++)
			    InitDriver.wdcf.mouseOverOperation((WebElement)CommonFunctions.ConstructElementFromExcel((String)elements.get(i)));
			
			
			WebElement element=(WebElement) CommonFunctions.ConstructElementFromExcel((String) elements.get(elements.size()-1));//Common functions-2
			element.click();
			
			Thread.sleep(1000);		
		    PrintinLogAndHTMLReports(Message); //Common functions-7
		}
		
		
		//==============================================================================================================================================
		
		public static void element_SelectDropDown(String locator,int Index,String Message) throws Throwable
		{	
			CommonFunctions.LoadPageExpicitWait(); //Common functions-1
			WebElement element=(WebElement) CommonFunctions.ConstructElementFromExcel(locator);//Common functions-2
			CommonFunctions.scrollPageUp(0,-1000);//Common functions-3
			CommonFunctions.SearchForElement(element);//Common functions-4
			select(element,Index);
			CommonFunctions.LoadPageExpicitWait(); //Common functions-5
			CommonFunctions.scrollPageUp(0,-1000); //Common functions-6
			PrintinLogAndHTMLReports(Message); //Common functions-7
		}
		
		//==============================================================================================================================================
		
		public static void element_SelectDropDown(String locator,String INDEXorVALUEorVISIBLETEXT,int Index,String Value,String Message) throws Throwable
		{	
			CommonFunctions.LoadPageExpicitWait(); //Common functions-1
			WebElement element=(WebElement) CommonFunctions.ConstructElementFromExcel(locator);//Common functions-2
			CommonFunctions.scrollPageUp(0,-1000);//Common functions-3
			CommonFunctions.SearchForElement(element);//Common functions-4
			if(INDEXorVALUEorVISIBLETEXT.equals("INDEX"))
				select(element,Index);
			if(INDEXorVALUEorVISIBLETEXT.equals("VALUE"))
				selectValue(element,Value);
			if(INDEXorVALUEorVISIBLETEXT.equals("VISIBLETEXT"))
				selectVisibleText(element,Value);
			
			CommonFunctions.LoadPageExpicitWait(); //Common functions-5
			CommonFunctions.scrollPageUp(0,-1000); //Common functions-6
			PrintinLogAndHTMLReports(Message); //Common functions-7
		}
		
		//==============================================================================================================================================
		
		public static WebElement element_ReturnWebElement(String locator) throws Throwable
		{
			CommonFunctions.LoadPageExpicitWait(); //Common functions-1
			WebElement element=(WebElement) CommonFunctions.ConstructElementFromExcel(locator);//Common functions-2
			CommonFunctions.scrollPageUp(0,-1000);//Common functions-3
			CommonFunctions.SearchForElement(element);//Common functions-4
		    return element;
		}
		
		//==============================================================================================================================================
		
//		public static void element_SelectProductFromNavigation(String Shop,String Category_1,String Category_2,String Category_3,String Message) throws Throwable
//		{   
//			CommonFunctions.LoadPageExpicitWait(); //Common functions-1
//			Scenario1Test.wdcf.mouseOverOperation((WebElement)CommonFunctions.ConstructElementFromExcel(Shop));
//			Thread.sleep(1000);
//			Scenario1Test.wdcf.mouseOverOperation((WebElement)CommonFunctions.ConstructElementFromExcel(Category_1));
//			Thread.sleep(1000);
//			Scenario1Test.wdcf.mouseOverOperation((WebElement)CommonFunctions.ConstructElementFromExcel(Category_2));
//			WebElement element=(WebElement)CommonFunctions.ConstructElementFromExcel(Category_3);
//			element.click();
//			CommonFunctions.LoadPageExpicitWait(); //Common functions-5
//			CommonFunctions.scrollPageUp(0,-1000); //Common functions-6
//			PrintinLogAndHTMLReports(Message); //Common functions-7
//		}
	//	
		
		//==============================================================================================================================================
		
		
		public static void element_ClickLoginButtonHomePage(String Account,String Login,String Message) throws Throwable
		{   
			CommonFunctions.LoadPageExpicitWait(); //Common functions-1
			InitDriver.wdcf.mouseOverOperation((WebElement)CommonFunctions.ConstructElementFromExcel(Account));
			Thread.sleep(1000);		
			WebElement element=(WebElement)CommonFunctions.ConstructElementFromExcel(Login);
			element.click();
			CommonFunctions.LoadPageExpicitWait(); //Common functions-5
			CommonFunctions.scrollPageUp(0,-1000); //Common functions-6
			PrintinLogAndHTMLReports(Message); //Common functions-7
		}
		
		//==============================================================================================================================================
		
		public static void element_EnterValuesToTextField(String locator,String Value,String Message) throws Throwable
		{   
			CommonFunctions.LoadPageExpicitWait(); //Common functions-1
			WebElement element=(WebElement)CommonFunctions.ConstructElementFromExcel(locator);//Common functions-2
			CommonFunctions.scrollPageUp(0,-1000);//Common functions-3
			CommonFunctions.SearchForElement(element);//Common functions-4
			element.clear();
			element.sendKeys(Value);
			PrintinLogAndHTMLReports(Message); //Common functions-7
		}
		
		//==============================================================================================================================================
		
		public static int element_NumberFromLinkText(String locator) throws Throwable
		{   
			CommonFunctions.LoadPageExpicitWait(); //Common functions-1
			WebElement element=(WebElement) CommonFunctions.ConstructElementFromExcel(locator);//Common functions-2
			CommonFunctions.scrollPageUp(0,-1000);//Common functions-3
			CommonFunctions.SearchForElement(element);//Common functions-4
			String ActualText=element.getText();
			int number=CommonFunctions.getNumber(ActualText);
			PrintinLogAndHTMLReports("Number From link Text is"+number);
			return number;
		}
		
		//==============================================================================================================================================
		
		public static String element_GetTextFromLinkText(String locator) throws Throwable
		{   
			CommonFunctions.LoadPageExpicitWait(); //Common functions-1
			WebElement element=(WebElement) CommonFunctions.ConstructElementFromExcel(locator);//Common functions-2
			CommonFunctions.scrollPageUp(0,-1000);//Common functions-3
			CommonFunctions.SearchForElement(element);//Common functions-4
			String ActualText=element.getText();
		    return ActualText;
		}
		
		//==============================================================================================================================================
		
		public static void element_VerifyLinkTextAndAssert(String locator,String Value,String Message) throws Throwable
		{   
			CommonFunctions.LoadPageExpicitWait(); //Common functions-1
			WebElement element=(WebElement) CommonFunctions.ConstructElementFromExcel(locator);//Common functions-2
			CommonFunctions.scrollPageUp(0,-1000);//Common functions-3
			CommonFunctions.SearchForElement(element);//Common functions-4
			String ActualText=element.getText();
			Assert.assertEquals(ActualText,Value);
			PrintinLogAndHTMLReports(Message); //Common functions-7
		}
		
		//==============================================================================================================================================
		
		public static void element_VerifyTextAndAssert(String locator,String Value,String Message) throws Throwable
		{   
			CommonFunctions.LoadPageExpicitWait(); //Common functions-1
			WebElement element=(WebElement) CommonFunctions.ConstructElementFromExcel(locator);//Common functions-2
			CommonFunctions.scrollPageUp(0,-1000);//Common functions-3
			CommonFunctions.SearchForElement(element);//Common functions-4
			String ActualText=element.getText();
			Assert.assertEquals(ActualText,Value);
			PrintinLogAndHTMLReports(Message); //Common functions-7
		}
		
		//==============================================================================================================================================
		
		
		public static void element_VerifyNumberAndAssert(String locator,int Value,String Message) throws Throwable
		{   
			CommonFunctions.LoadPageExpicitWait(); //Common functions-1
			WebElement element=(WebElement) CommonFunctions.ConstructElementFromExcel(locator);//Common functions-2
			CommonFunctions.scrollPageUp(0,-1000);//Common functions-3
			CommonFunctions.SearchForElement(element);//Common functions-4
			String ActualText=element.getText();
			int ActualNumber=CommonFunctions.getNumber(ActualText);
			Assert.assertEquals(ActualNumber,Value);
			PrintinLogAndHTMLReports(Message); //Common functions-7
		}
		
		//==============================================================================================================================================
		
		public static String element_GetTextFromLabel(String locator) throws Throwable
		{   
			CommonFunctions.LoadPageExpicitWait(); //Common functions-1
			WebElement element=(WebElement) CommonFunctions.ConstructElementFromExcel(locator);//Common functions-2
			CommonFunctions.scrollPageUp(0,-1000);//Common functions-3
			CommonFunctions.SearchForElement(element);//Common functions-4
			String ActualText=element.getText();
			return ActualText;
		}
		
		//==============================================================================================================================================
		
		public static List<WebElement> element_Collection(String locator, int size,boolean SizeAssertionRequired, String Message) throws Throwable 
		{

			CommonFunctions.LoadPageExpicitWait(); //Common functions-1
			List<WebElement> element= (List<WebElement>) CommonFunctions.ConstructElementFromExcel(locator);//Common functions-2
			CommonFunctions.scrollPageUp(0,-1000);//Common functions-3
			CommonFunctions.SearchForElements(element);//Common functions-4		
			if(SizeAssertionRequired==true)
			{
			   Assert.assertEquals(element.size(),size);
			   PrintinLogAndHTMLReports(Message); //Common functions-7
			}
			return element;   
		}
		
		//==============================================================================================================================================
		
		public static void element_Collection_Click(String locator, int Index, String Message) throws Throwable 
		{

			CommonFunctions.LoadPageExpicitWait(); //Common functions-1
			List<WebElement> element= (List<WebElement>) CommonFunctions.ConstructElementFromExcel(locator);//Common functions-2
			CommonFunctions.scrollPageUp(0,-1000);//Common functions-3
			CommonFunctions.SearchForElements(element);//Common functions-4
			element.get(Index).click();
			PrintinLogAndHTMLReports(Message); //Common functions-7		
			
		}
		
		//==============================================================================================================================================
		
		public static void element_Clear(String locator,String Message) throws Throwable
		{
			CommonFunctions.LoadPageExpicitWait(); //Common functions-1
			WebElement element=(WebElement) CommonFunctions.ConstructElementFromExcel(locator);//Common functions-2
			CommonFunctions.scrollPageUp(0,-1000);//Common functions-3
			CommonFunctions.SearchForElement(element);//Common functions-4
			element.clear();
			CommonFunctions.LoadPageExpicitWait(); //Common functions-5
			CommonFunctions.scrollPageUp(0,-1000); //Common functions-6
			PrintinLogAndHTMLReports(Message); //Common functions-7
		}
		
		//==============================================================================================================================================
		
		public static void element_SelectAllOptionsDropDownAndVerify(String locator,ArrayList elementsToVerify,String SuccessMessageAfterAssertion) throws Throwable 
		{
			CommonFunctions.LoadPageExpicitWait(); //Common functions-1
			WebElement element= (WebElement) CommonFunctions.ConstructElementFromExcel(locator);//Common functions-2
			CommonFunctions.scrollPageUp(0,-1000);//Common functions-3
			CommonFunctions.SearchForElement(element);//Common functions-4
			String name=element.toString();
			for(int i=0;i<elementsToVerify.size();i++)
				if(name.contains(elementsToVerify.get(i).toString()))
					break;
			PrintinLogAndHTMLReports(SuccessMessageAfterAssertion); //Common function
			
		}
		
		//==============================================================================================================================================
		
		public static void PrintinLogAndHTMLReports(String Message)
		{
			log.info(Message);
			Reporter.log(Message,false);
		}
		
		//==============================================================================================================================================
		
		public static void Assert_IntegerValuesAndPrintinLogAndHTMLReports(int Actual,int Expected,String Message)
		{
			Assert.assertEquals(Actual, Expected);
			log.info(Message);
			Reporter.log(Message,false);
		}
		
		//==============================================================================================================================================
		
		public static void Print_WithException_inLogAndHTMLReports(String Message) throws Throwable
		{
			log.info(Message);
			Reporter.log(Message,false);
			throw new Exception();
		}
		
		//==============================================================================================================================================
		
		public static void navigateBack(int CountofNavigatingBack) throws Throwable
		{
			for(int i=1;i<=CountofNavigatingBack;i++)
			{
			   InitDriver.driver.navigate().back();
			   CommonFunctions.LoadPageExpicitWait();
			}
		}
		
		//==============================================================================================================================================

//		public static void element_EnterPinCode(String locator,String Message) throws Throwable
//		{   
//			CommonFunctions.LoadPageExpicitWait(); //Common functions-1
//			WebElement element=ConstructElementFromExcel(locator);
//			CommonFunctions.scrollPageUp(0,600);
//			SearchForElement(element);
//			element.click();
//			CommonFunctions.LoadPageExpicitWait(); //Common functions-2
//			PrintinLogAndHTMLReports(Message); //Common functions-3
//		}
	//	
	//	
//		public static void elementText_Assert(String locator,String ExpectedValue,String Message) throws Throwable
//		{
//			CommonFunctions.LoadPageExpicitWait();
//			WebElement element=loadElementByXpath(locator);
//			CommonFunctions.scrollPageUp(0,600);
//			WaitForElementPresent(element);
//			String ActualValue=element.getText();
//			CommonFunctions.LoadPageExpicitWait();
//			Assert.assertEquals(ActualValue, ExpectedValue);
//			PrintinLogAndHTMLReports(Message);
//		}
	//	
			
		//==============================================================================================================================================
		
		public void waitForPageToLoad()
		{
			BrowserSelection.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}
		
		//==============================================================================================================================================
		
		/**
		 * This method waits for the web element to be present in UI.The web element Xpath is passed by the user.
		 * @param xpath
		 */
		public void waitForXpathPresent(String xpath)
		{
			WebDriverWait wait=new WebDriverWait(BrowserSelection.driver, 20);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));		
		}
		
		//==============================================================================================================================================
		
		/**
		 * This method waits for the link to be present in UI.The link text is passed by the user.
		 * @param link
		 */
		public void waitForLinkPresent(String link)
		{
			WebDriverWait wait=new WebDriverWait(BrowserSelection.driver, 30);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(link)));		
		}
		//==============================================================================================================================================
		
		/**
		 * This method is used to select any option from the drop down menu based on the visible text in the UI.
		 * @param we
		 * @param option
		 * @throws Throwable 
		 */
		public static void selectValue(WebElement we,String Value) throws Throwable
		{
			Select sel=new Select(we);
			Thread.sleep(3000);
			sel.selectByValue(Value);
		}
		
		//==============================================================================================================================================
		
		/**
		 * This method is used to select any option from the drop down menu based on the visible text in the UI.
		 * @param we
		 * @param option
		 * @throws Throwable 
		 */
		public static void selectVisibleText(WebElement we,String VisibleText) throws Throwable
		{
			Select sel=new Select(we);
			Thread.sleep(3000);
			sel.selectByVisibleText(VisibleText);
		}
		
		//==============================================================================================================================================
		
		/**
		 * This method is used to select any option from the drop down menu based on the position in the UI.
		 * @param we
		 * @param index
		 * @throws Exception 
		 */
		public static void select(WebElement we, int index) throws Exception
		{
			Select sel=new Select(we);
			Thread.sleep(3000);
			sel.selectByIndex(index);
		}
		
		//==============================================================================================================================================
		
		/**
		 * This method is used to accept the invisible pop ups.
		 */
		public static void acceptAlert()
		{
			Alert a=InitDriver.driver.switchTo().alert();
			a.accept();
		}
		
		//==============================================================================================================================================
		
		public static String getTextAlert()
		{
			Alert a=InitDriver.driver.switchTo().alert();
			return (a.getText());
		}
		
		//==============================================================================================================================================
		
		/**
		 * This method is used to dismiss the invisible pop ups.
		 */
		public void cancelAlert()
		{
			Alert a=BrowserSelection.driver.switchTo().alert();
			a.dismiss();
		}
		
		//==============================================================================================================================================
		
		/**
		 * This method is used to perform a mouse over operation on WebElement. 
		 */
		public void mouseOverOperation(WebElement element)
		{
			Actions act = new Actions(InitDriver.driver);
			act.moveToElement(element).perform();	
		}
		
		//==============================================================================================================================================
		
		/**
		 * This method is used to maximizing Window
		 */
		public void maximizingWindow() 
		{
			BrowserSelection.driver.manage().window().maximize();
			
		}

		//==============================================================================================================================================
		
		public static void ExplicitWait() throws Throwable 
		{
			Thread.sleep(30000);
			InitDriver.driver.switchTo().defaultContent();
			
		}
		
		//==============================================================================================================================================
		
		private static boolean checkifRowExistsinTable(String Xpath)
		{
		    	int size=InitDriver.driver.findElements(By.xpath(Xpath)).size();
		    	//System.out.println(size);
		    	if(size>0) return true;
		    	else return false;
		}
		
		//==============================================================================================================================================
		
		public static Iterator<String> windows;
		static String Parent;
		static String Child;
		
		public static void element_Window_SwitchToChild(String Message) 
		{
			windows = InitDriver.driver.getWindowHandles().iterator();		
			Parent=windows.next();
			Child=windows.next();
			InitDriver.driver.switchTo().window(Child);
	        PrintinLogAndHTMLReports(Message);
			
		}
		
		//==============================================================================================================================================
		
		public static void element_Window_SwitchToParent(String Message) 
		{	
			InitDriver.driver.close();
			InitDriver.driver.switchTo().window(Parent);
	        PrintinLogAndHTMLReports(Message);
			
		}

		//==============================================================================================================================================
		
		public static void element_GetTextFromTextField(String locator,String AttributeName,String ExpectedValue,String Message) throws Throwable 
		{
		
			CommonFunctions.LoadPageExpicitWait(); //Common functions-1
			WebElement element=(WebElement) CommonFunctions.ConstructElementFromExcel(locator);//Common functions-2
			CommonFunctions.scrollPageUp(0,-1000);//Common functions-3
			CommonFunctions.SearchForElement(element);//Common functions-4
			String ActualText=element.getAttribute(AttributeName);
			Assert.assertEquals(ActualText,ExpectedValue);
			PrintinLogAndHTMLReports(Message); //Common functions-7
			
			
		}

		//==============================================================================================================================================
		
		public static void element_Present(String Locator,String PresentMessage,String NotPresentMessage) throws Throwable 
		{
			CommonFunctions.LoadPageExpicitWait(); //Common functions-1
			WebElement element=(WebElement) CommonFunctions.ConstructElementFromExcel(Locator);//Common functions-2
			CommonFunctions.SearchForElement(element);
	        if(element.isDisplayed()==true) 
	          PrintinLogAndHTMLReports(PresentMessage);
	        else  
	          Print_WithException_inLogAndHTMLReports(NotPresentMessage);
	        
		}
		
		//==============================================================================================================================================
		
		public static void Print_WithException_SoftAssert_inLogAndHTMLReports(String ActualValue,String Expectedvalue,boolean SoftAssert_AfterText) throws Throwable
		{	
			WebDriverCommonFunctions.PrintinLogAndHTMLReports(": ActualValue=>"+ActualValue+" : Expectedvalue=>"+Expectedvalue);
			InitDriver.softAssert.assertEquals(ActualValue, Expectedvalue);		
			if(SoftAssert_AfterText==false)
				InitDriver.softAssert.assertAll();
 				
		}
		
		//==============================================================================================================================================

		public static void verifyimageActive(WebElement element,String ImageAttributeName) throws Throwable 
		{
				HttpClient client = HttpClientBuilder.create().build();
				HttpGet request = new HttpGet(element.getAttribute(ImageAttributeName));
				HttpResponse response = client.execute(request);
				if (response.getStatusLine().getStatusCode() != 200)
	                WebDriverCommonFunctions.Print_WithException_inLogAndHTMLReports("Image Broken");
				else
					WebDriverCommonFunctions.PrintinLogAndHTMLReports("Image not Broken");
			 
			
		}	
		
		//==============================================================================================================================================	
		
		public static void EnterZipCode() throws Throwable 
		{
			try
			{
			  WebDriverCommonFunctions.element_EnterValuesToTextField("ZipCodePOPUP_Xpath","560064","Pincode Entered");
			  WebDriverCommonFunctions.element_Click("ZipCodePOPUP_GoButton_Xpath", "Clicked on ZipCode Go Button");
			}
			catch(Exception e)
			{
				//do nothing
			}	
		}
		//==============================================================================================================================================	
		
			public static boolean element_isClickable(String Locator) throws Throwable 
			{
				CommonFunctions.LoadPageExpicitWait(); //Common functions-1
				WebElement element=(WebElement) CommonFunctions.ConstructElementFromExcel(Locator);//Common functions-2
				CommonFunctions.scrollPageUp(0,-1000);//Common functions-3
				CommonFunctions.SearchForElement(element);//Common functions-4
				
				WebDriverWait wait = new WebDriverWait(InitDriver.driver, 15);
			    wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Locator)));					
				
			    
			    boolean status=false;
				WebDriverWait we = new WebDriverWait(InitDriver.driver,15);
				if(we.until(ExpectedConditions.elementToBeClickable(By.xpath(Locator))) == null)
				{
					status=false;
				}
				else
				{
					status=true;
				}
				
				return status;
			}
		
}

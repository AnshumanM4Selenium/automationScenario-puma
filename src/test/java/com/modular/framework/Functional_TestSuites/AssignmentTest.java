package com.modular.framework.Functional_TestSuites;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.modular.framework.Generic_Libraries.LoggerHelper;
import com.modular.framework.Generic_Libraries.RetrieveXlsxData;
import com.modular.framework.Generic_Libraries.WebDriverCommonFunctions;
import com.modular.framework.POM.ChekoutCart;
import com.modular.framework.POM.HomePage;
import com.modular.framework.POM.ProductDetailsPage;
import com.modular.framework.POM.ProductListPage;

public class AssignmentTest {
	
	static Logger log = LoggerHelper.writeLog(AssignmentTest.class);
	
	static String productName_prdDetailsPage;
	static String productPrice_prdDetailsPage;
	static int quantitySelected_prdDetailsPage;
	
	
	static String productName_chekOutPage;
	static String productPrice_chekOutPage;
	static int quantitySelected_chekOutPage;
	
	@DataProvider(name = "dataTest12")
	public static String[][] validationC() throws Exception {

		String[][] testDataArr = RetrieveXlsxData.getTableArray(
				"/src/test/resources/InvoiceCreateP.xls",
				"Sheet1");
		return testDataArr;
	}
	
	//(dataProvider = "dataTest12")
	@Test(dataProvider = "dataTest12")
	public void scenarioTest(String[] strArray) throws Throwable{
		
		
		
		List<String> dataList = Arrays.asList(strArray);
		log.info("List size :...."+dataList.size());
		
		
		HomePage.clickOnHamburger("NavBar_part1","NavBar_part2",dataList.get(0));
		HomePage.clickOnSubMenu_Hamburger("NavBar_subMenu_part1", "NavBar_subMenu_part2", dataList.get(1));
		
		log.info("In product List page");
		
		ProductListPage.selectProduct("Products_displayed_1","Products_displayed_2", dataList.get(2), dataList.get(3));
		
		log.info("In product details page");
		log.info("Transfering driver control to Product_details page");
		
		WebDriverCommonFunctions.element_Window_SwitchToChild("Driver control in ProductDetails page");
		
		productName_prdDetailsPage = ProductDetailsPage.fretchProductName("Product_Name").toUpperCase();
		
		log.info("Product name :"+productName_prdDetailsPage);
		
		productPrice_prdDetailsPage = ProductDetailsPage.fretchProductPrice("Product_Price");
		
		log.info("Product price :"+productPrice_prdDetailsPage);
		
		
		
		log.info("Selecting shoe size dynamically");
		
		ProductDetailsPage.selectShoeSize("Select_ShoeSize", "Select_Size1","Select_Size2", dataList.get(5),dataList.get(6), "Shoe size selected dynamically");
		//log.info("Click on +"/"Add to cart button"+/");
		//ProductDetailsPage.addToCart("Select_Size", "AddCart_button", "VISIBLETEXT", 0, "6", "Shoe size selected");
		
		quantitySelected_prdDetailsPage = ProductDetailsPage.selectQuantity("Select_Quantity", "VISIBLETEXT", 0, dataList.get(4), "Quantity selected dynamically");
		
		log.info("Quantity  :"+quantitySelected_prdDetailsPage);
		
		log.info("Clicking on Add to cart button");
		ProductDetailsPage.addToCart("AddCart_button", "Notify_me_button", "Add To cart Button clicked", "Notify button clicked", "Notify_email", "Notify_me_SubmitButton");
		
		productName_chekOutPage = ChekoutCart.fretchProductName("Product_name_checkout").toUpperCase();
		log.info("Product name in chekout page :"+productName_chekOutPage);
		
		productPrice_chekOutPage = ChekoutCart.fretchProductPrice("Product_Price_checkout");
		log.info("Product price :"+productPrice_chekOutPage);
		
		quantitySelected_chekOutPage = ChekoutCart.fretchProductQuantity("Product_quantity_checkout", "value");
		log.info("Product price :"+quantitySelected_chekOutPage);
		
		log.info("Will Assert that the cart has the correct shoe selected in previous step (Based on name of product/quantity/price)");
		
		Assert.assertEquals(productName_prdDetailsPage, productName_chekOutPage);
		Assert.assertEquals(productPrice_prdDetailsPage, productPrice_chekOutPage);
		Assert.assertEquals(quantitySelected_prdDetailsPage, quantitySelected_chekOutPage);
	}

}

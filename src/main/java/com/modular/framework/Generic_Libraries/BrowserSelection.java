package com.modular.framework.Generic_Libraries;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class BrowserSelection {
	
public  static WebDriver driver;
	
	public static WebDriver selectBrowserDriver(String browser)
	{
		if (browser.equalsIgnoreCase("chrome")) 
		{
			
			System.setProperty("webdriver.chrome.driver", "D:/Softwares/Drivers_exe/chromedriver.exe");
			driver = new ChromeDriver();
			
		} 
		else if (browser.equalsIgnoreCase("ie")) 
		{
			
			System.setProperty("webdriver.ie.driver", "D:\\Softwares\\Drivers_exe\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			
		} 
		if(browser.equalsIgnoreCase("firefox"))
		{	
			System.setProperty("webdriver.gecko.driver", "D:/Softwares/Drivers_exe/geckodriver.exe");
			driver = new FirefoxDriver();
		}
		return driver;
	}
}

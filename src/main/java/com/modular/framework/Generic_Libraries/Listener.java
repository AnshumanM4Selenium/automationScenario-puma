package com.modular.framework.Generic_Libraries;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.ITestListener;

import com.modular.framework.InitWebdriver.InitDriver;

public class Listener implements ITestListener{
	
static String currentPath = System.getProperty("user.dir");
	
	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	public void onTestFailure(ITestResult test) 
	{
		System.out.println(currentPath);
		EventFiringWebDriver edriver=new EventFiringWebDriver(InitDriver.driver);
		File srcimg=edriver.getScreenshotAs(OutputType.FILE);
		File destimg=new File(currentPath +"//ScreenShots//"+test.getName()+".png");
		try 
		{
			FileUtils.copyFile(srcimg, destimg);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
	}

	public void onTestSkipped(ITestResult test) 
	{
		
		EventFiringWebDriver edriver=new EventFiringWebDriver(InitDriver.driver);
		File srcimg=edriver.getScreenshotAs(OutputType.FILE);
		File destimg=new File(currentPath +"//ScreenShots//"+test.getName()+".png");
		try 
		{
			FileUtils.copyFile(srcimg, destimg);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	public void onTestStart(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	public void onTestSuccess(ITestResult test) 
	{
		
		EventFiringWebDriver edriver=new EventFiringWebDriver(InitDriver.driver);
		File srcimg=edriver.getScreenshotAs(OutputType.FILE);
		File destimg=new File(currentPath +"//ScreenShots//"+test.getName()+".png");
		try 
		{
			FileUtils.copyFile(srcimg, destimg);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}

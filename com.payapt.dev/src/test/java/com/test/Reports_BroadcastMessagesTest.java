package com.test;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.ExtentReport.ExtentReport;
import com.pages.Reports_BroadcastMessagesPage;

import base.BaseClass;

public class Reports_BroadcastMessagesTest extends BaseClass{

	@Test
	public void BroadcastMessages_FedNow_PB() throws InterruptedException
	{
		ExtentReport.createTest("Broadcast Messages FedNow - Participant Broadcast Test");
		bmreport = new Reports_BroadcastMessagesPage(driver);

		scrollByVisibilityOfElement(driver, bmreport.Reports);

		visibleofele(driver, bmreport.MenuListBroadcastMessages, "Broadcast Messages");
		JSClick(driver, bmreport.MenuListBroadcastMessages, "Navigating to Broadcast Messages");
		
		
		// Get report types from config
		String[] BMTypeFromConfig = prop.getProperty("FednowBMType").split(",");

		for (String BMType: BMTypeFromConfig) {
			ExtentReport.createChildTest(BMType);
			// Step 1: Reopen the dropdown			
			clickelementwithname(bmreport.selectBMdropdown, "Opening BM dropdown");

			// Step 2: Re-fetch the list to avoid stale element
			List<WebElement> bmTypelist = bmreport.reportBMlist;

			boolean matchFound = false;
			for (WebElement webElement : bmTypelist) 
			{
				String BMTypeName = webElement.getText().trim();
				if (BMTypeName.equalsIgnoreCase(BMType.trim())) {
					// Step 3: Click matching element
					webElement.click();

					// Step 4: Call corresponding method
					switch (BMType.trim().toUpperCase()) {
					case "PING":
						bmreport.FednowPBping();
						break;
					default:
						System.out.println("No method defined for: " + BMType);
					}

					// Optional: refresh page if needed
					refresh();
					matchFound = true;
					break;
				}
			}

			if (!matchFound) 
				System.out.println("Report type not found in dropdown: " + BMType);
			ExtentReport.resetToParent();
		}
	}
	@Test
	public void BroadcastMessages_Fedwire_FB() throws InterruptedException
	{
		refresh();
		ExtentReport.createTest("Broadcast Messages Fedwire - Fedwire Broadcast  Test");
		bmreport = new Reports_BroadcastMessagesPage(driver);

		// Get report types from config
		String[] BMTypeFromConfig = prop.getProperty("FedwireBMFWBType").split(",");
		
		for (String BMType: BMTypeFromConfig) {
			ExtentReport.createChildTest(BMType);
			// Step 1: Reopen the dropdown
			clickelementwithname(bmreport.paymentservicedropdown, "Payment service dropdown");
			JSClick(driver,bmreport.fedwireoption, "Fedwire Payment service.");
			Thread.sleep(1000);
			clickelementwithname(bmreport.selectBMdropdown1, "Opening BM dropdown");
			
			// Step 2: Re-fetch the list to avoid stale element
			List<WebElement> bmTypelist = bmreport.reportBMlist;
			
			boolean matchFound = false;
			for (WebElement webElement : bmTypelist) 
			{
				String BMTypeName = webElement.getText().trim();
				if (BMTypeName.equalsIgnoreCase(BMType.trim())) {
					// Step 3: Click matching element
					clickelementwithname(webElement, webElement.getText());					
					// Step 4: Call corresponding method
					switch (BMType.trim().toUpperCase()) {
					case "OPEN":
						bmreport.FedwirePBOPEN();
						break;
					case "CLSD":
						bmreport.FedwirePBCLSD();
						break;
					default:
						System.out.println("No method defined for: " + BMType);
						
					}
					
					// Optional: refresh page if needed
					refresh();
					matchFound = true;
					break;
				}
			}
			
			if (!matchFound) 
				System.out.println("Report type not found in dropdown: " + BMType);
			ExtentReport.resetToParent();
		}
	}
	
	@Test
	public void BroadcastMessages_Fedwire_PB() throws InterruptedException
	{
//		refresh();
		ExtentReport.createTest("Broadcast Messages Fedwire - Participant Broadcast Test");
		bmreport = new Reports_BroadcastMessagesPage(driver);

		clickelementwithname(bmreport.paymentservicedropdown, "Payment service dropdown");
		JSClick(driver,bmreport.fedwireoption, "Fedwire Payment service.");
		clickelementwithname(bmreport.ParticipantBroadcastRadiobtn,"Choosing Participant Broadcast Radiobtn");
		
		// Get report types from config
		String[] BMTypeFromConfig = prop.getProperty("FedwireBMPBType").split(",");

		for (String BMType: BMTypeFromConfig) {
			ExtentReport.createChildTest(BMType);
			// Step 1: Reopen the dropdown			
			clickelementwithname(bmreport.selectBMdropdown, "Opening BM dropdown");

			// Step 2: Re-fetch the list to avoid stale element
			List<WebElement> bmTypelist = bmreport.reportBMlist;

			boolean matchFound = false;
			for (WebElement webElement : bmTypelist) 
			{
				String BMTypeName = webElement.getText().trim();
				if (BMTypeName.equalsIgnoreCase(BMType.trim())) {
					// Step 3: Click matching element
					webElement.click();

					// Step 4: Call corresponding method
					switch (BMType.trim().toUpperCase()) {
					case "PING":
						bmreport.FednwirePBping();
						break;
					default:
						System.out.println("No method defined for: " + BMType);
					}

					// Optional: refresh page if needed
					refresh();
					matchFound = true;
					break;
				}
			}

			if (!matchFound) 
				System.out.println("Report type not found in dropdown: " + BMType);
			ExtentReport.resetToParent();
		}
	}
}

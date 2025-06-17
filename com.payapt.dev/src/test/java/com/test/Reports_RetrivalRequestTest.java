package com.test;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.ExtentReport.ExtentReport;
import com.pages.Reports_AccountReportingPage;
import com.pages.Reports_RetrivalRequestPage;

import base.BaseClass;

public class Reports_RetrivalRequestTest extends BaseClass{

	@Test
	public void RetrivalRequestTest_FedNow() throws InterruptedException
	{
		refresh();
		ExtentReport.createTest("Retrival Request FedNow Test");
		RRreport = new Reports_RetrivalRequestPage(driver);

		scrollByVisibilityOfElement(driver, RRreport.Reports);

		visibleofele(driver,RRreport.MenuListRetrivalRequest, "Retrival Request");
		JSClick(driver, RRreport.MenuListRetrivalRequest, "Navigating to Retrival Request");
		
		// Get report types from config
		String[] MessageTypesFromConfig = prop.getProperty("FednowMessageType").split(",");

		for (String MessageTypeConfig : MessageTypesFromConfig) {
			ExtentReport.createChildTest("Message Type - "+MessageTypeConfig);
			// Step 1: Reopen the dropdown
//			clickelementwithname(RRreport.paymentservicedropdown, "Payment service dropdown");
//			JSClick(driver,RRreport.fednowoption, "Fednow Payment service.");
			
			clickelementwithname(RRreport.SelectmessagesTypeDropdown, "Reopening Report Type dropdown");

			// Step 2: Re-fetch the list to avoid stale element
			List<WebElement> MessageTypelist = RRreport.MessageTypelist;	

			boolean matchFound = false;
			for (WebElement webElement : MessageTypelist) 
			{
				String reportName = webElement.getText().trim();
				if (reportName.equalsIgnoreCase(MessageTypeConfig.trim())) {
					// Step 3: Click matching element
					webElement.click();

					// Step 4: Call corresponding method
					switch (MessageTypeConfig.trim().toUpperCase()) {
					case "VALUE":
						RRreport.FednowRRreport();
						break;
						// Add more if needed
					default:
						System.out.println("No method defined for: " + MessageTypeConfig);
					}

					// Optional: refresh page if needed
					refresh();
					matchFound = true;
					break;
				}
			}

			if (!matchFound) 
				System.out.println("Report type not found in dropdown: " + MessageTypeConfig);
			ExtentReport.resetToParent();
		}
	}
	@Test
	public void RetrivalRequestTest_Fedwire() throws InterruptedException
	{
		refresh();
		ExtentReport.createTest("Retrival Request Fedwire Test");
		RRreport = new Reports_RetrivalRequestPage(driver);
		
		String[] MessageTypesFromConfig = prop.getProperty("FedwireMessageType").split(",");

		for (String MessageTypeConfig : MessageTypesFromConfig) {
			ExtentReport.createChildTest("Message Type - "+MessageTypeConfig);
			// Step 1: Reopen the dropdown
			clickelementwithname(RRreport.paymentservicedropdown, "Payment service dropdown");
			JSClick(driver,RRreport.fedwireoption, "Fedwire Payment service.");
			
			datePicker();
			
			clickelementwithname(RRreport.SelectmessagesTypeDropdown, "Reopening Report Type dropdown");

			// Step 2: Re-fetch the list to avoid stale element
			List<WebElement> MessageTypelist = RRreport.MessageTypelist;	

			boolean matchFound = false;
			for (WebElement webElement : MessageTypelist) 
			{
				String reportName = webElement.getText().trim();
				if (reportName.equalsIgnoreCase(MessageTypeConfig.trim())) {
					// Step 3: Click matching element
					webElement.click();

					// Step 4: Call corresponding method
					switch (MessageTypeConfig.trim().toUpperCase()) {
					case "VALUE":
						RRreport.FedwireRRreport();
						break;
						// Add more if needed
					default:
						System.out.println("No method defined for: " + MessageTypeConfig);
					}

					// Optional: refresh page if needed
					refresh();
					matchFound = true;
					break;
				}
			}

			if (!matchFound) 
				System.out.println("Report type not found in dropdown: " + MessageTypeConfig);
			ExtentReport.resetToParent();
		}

	}

}

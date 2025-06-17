package IndividualFlows;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.ExtentReport.ExtentReport;
import com.pages.Reports_AccountReportingPage;

import base.BaseClass;

public class Reports_AccountReportingFednow extends BaseClass{

	@Test
	public void AccountReportingTest_FedNow() throws InterruptedException
	{
		ExtentReport.createTest("Account Reporting Summary FedNow Test");
		acreport = new Reports_AccountReportingPage(driver);

		scrollByVisibilityOfElement(driver, acreport.Reports);

		visibleofele(driver, acreport.MenuListAccountReporting, "Account Reporting");
		JSClick(driver, acreport.MenuListAccountReporting, "Navigating to Account Reporting");
		
		// Get report types from config
		String[] reportTypesFromConfig = prop.getProperty("FedNowReportType").split(",");

		for (String reportType : reportTypesFromConfig) {
			ExtentReport.createChildTest(reportType);
			// Step 1: Reopen the dropdown
			clickelementwithname(acreport.selectReportType, "Reopening Report Type dropdown");

			// Step 2: Re-fetch the list to avoid stale element
			List<WebElement> reportTypelist = acreport.reportTypelist;

			boolean matchFound = false;
			for (WebElement webElement : reportTypelist) 
			{
				String reportName = webElement.getText().trim();
				if (reportName.toLowerCase().contains(reportType.trim().toLowerCase())) {
					// Step 3: Click matching element
					webElement.click();

					// Step 4: Call corresponding method
					switch (reportType.trim().toUpperCase()) {
					case "ABAR":
						acreport.FednowABARReport();
						break;
					case "AADR":
						acreport.FednowAADRReport();
						break;
					case "AATR":
						acreport.FednowAATRReport();
						break;
						// Add more if needed
					default:
						System.out.println("No method defined for: " + reportType);
					}

					// Optional: refresh page if needed
					refresh();
					matchFound = true;
					break;
				}
			}

			if (!matchFound) 
				System.out.println("Report type not found in dropdown: " + reportType);
			ExtentReport.resetToParent();
		}
		
	}
}

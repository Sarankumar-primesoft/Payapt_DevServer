package IndividualFlows;

import java.io.File;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.ExtentReport.ExtentReport;
import com.ExtentReport.Extentlogger;
import com.pages.ExecutionsSummaryPage;

import IndividualFlows.FednowBatchTest;
import IndividualFlows.FedwireBatchTest;
import base.BaseClass;

public class Fedwire_ExecutionsSummaryTest extends BaseClass{

	public String expectedBatchName;
	
	@Test
	public void FedwireBatchExecutionSummaryResults() throws InterruptedException {
		refresh();
		ExtentReport.createTest("Fedwire Batch Execution Summary Results");
		exectuionpage = new ExecutionsSummaryPage(driver);        

		scrollByVisibilityOfElement(driver, exectuionpage.ExecutionsTab);
		clickelementwithname(exectuionpage.TestExecutionTab, "Navigation to Test Execution Tab");
		JSClick(driver, exectuionpage.batchfiletoggle, "Changing toggle to Batch file");

		visibleofele(driver, exectuionpage.paymentservicedropdown, "Payment service dropdown");
		clickelementwithname(exectuionpage.paymentservicedropdown, "Payment service dropdown");
		clickelementwithname(exectuionpage.fedwireOption, "Fedwire select from list");

		String expectedBatchName = prop.getProperty("FedwireBatchName");
		String actualBatchName = exectuionpage.batchNameClmn.getText().trim();

		for(int i=0; i<5; i++) {
			if (actualBatchName.equals(expectedBatchName)) {
				Extentlogger.pass("Expected Batch Name is Equals to Actual Batch Name. Expected : "+expectedBatchName+", Actual Batch Name : "+actualBatchName, true);
				break;
			} else {
				clickelement(exectuionpage.refreshbtn);
				Thread.sleep(1000); // Small wait after refresh
				actualBatchName = exectuionpage.batchNameClmn.getText().trim();
				System.out.println("Actual Batch Name : "+actualBatchName);
			}
			if (!actualBatchName.equals(expectedBatchName)) {
				Extentlogger.fail("Failed to find expected batch after 5 attempts. Actual Batch Name: " + actualBatchName);
				return; // Exit if we didn't find the right batch
			}
		}

		// Wait for pending test cases to become 0

		long maxWaitTime = 10 * 60 * 1000; // 10 minutes in milliseconds
		boolean pendingZero = false;

		while (System.currentTimeMillis() -FedwireBatchTest.FedwirestartTime < maxWaitTime) {
			// Hover over results column to make tooltip visible
			actions.moveToElement(exectuionpage.resultsclmn).perform();
			Thread.sleep(1000); // Wait for tooltip to appear

			try {
				// Get the tooltip element that contains the pending count
				WebElement tooltipElement = driver.findElement(By.xpath("//div[contains(@class,'MuiTooltip-tooltip')]/span/p[4]"));
				String tooltipText = tooltipElement.getText();
				System.out.println("Tooltip text: " + tooltipText);

				System.out.println(FedwireBatchTest.FedwirestartTime);
				System.out.println((((System.currentTimeMillis() -FedwireBatchTest.FedwirestartTime)/1000)/60));
				// Check if pending count is 0
				if (tooltipText.contains("Test cases Pending: 0")) {
					pendingZero = true;
					long durationMillis = System.currentTimeMillis() - FedwireBatchTest.FedwirestartTime;
					long minutes = (durationMillis / 1000) / 60;
					long seconds = (durationMillis / 1000) % 60;
					Extentlogger.pass("Time Taken : " + minutes + " minute(s) " + seconds + " second(s)");
					Extentlogger.pass("All test cases processed - Pending count is 0");
					List<WebElement> tooltip = exectuionpage.tooltip;
					for (WebElement webElement : tooltip) {
						System.out.println(webElement.getText());
						Extentlogger.info(webElement.getText());
					}
					break;
				}

				// If not zero, click refresh and wait
				clickelement(exectuionpage.refreshbtn);
				Thread.sleep(5000); // Wait 5 seconds before checking again

			} catch (Exception e) {
				Extentlogger.info("Error while checking tooltip: " + e.getMessage());
				// If there's an error, still try to refresh
				clickelement(exectuionpage.refreshbtn);
				Thread.sleep(5000);
			}
		}

		if (!pendingZero) {
			System.out.println(FedwireBatchTest.FedwirestartTime);
			System.out.println((((System.currentTimeMillis() -FedwireBatchTest.FedwirestartTime)/1000)/60));
			Extentlogger.info("Duration: "+(((System.currentTimeMillis() -FedwireBatchTest.FedwirestartTime)/1000)/60));
			
			long durationMillis = System.currentTimeMillis() - FedwireBatchTest.FedwirestartTime;
			long minutes = (durationMillis / 1000) / 60;
			long seconds = (durationMillis / 1000) % 60;
			Extentlogger.pass("Time Taken : " + minutes + " minute(s) " + seconds + " second(s)");
			
			actions.moveToElement(exectuionpage.resultsclmn).perform();
			Thread.sleep(1000); // Wait for tooltip to appear
			List<WebElement> tooltip = exectuionpage.tooltip;
			for (WebElement webElement : tooltip) {
				System.out.println(webElement.getText());
				Extentlogger.info(webElement.getText());
			}
			Extentlogger.fail("Pending test cases did not reach 0 within "+(maxWaitTime/60000)+" minutes");
		}

		clickelementwithname(exectuionpage.downloadicon, "Download btn");
		Thread.sleep(60000);
		Extentlogger.info("Waited for 1 minute to download the executed results.",true);
	}	

}
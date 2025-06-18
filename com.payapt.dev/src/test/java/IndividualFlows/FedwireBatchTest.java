package IndividualFlows;

import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

import com.ExtentReport.ExtentReport;
import com.ExtentReport.Extentlogger;
import com.pages.BatchPage;

import base.BaseClass;

public class FedwireBatchTest extends BaseClass{
	public static long FedwirestartTime = System.currentTimeMillis();
	@Test
	public void Batchcreation() throws InterruptedException
	{
		ExtentReport.createTest("Fedwire Batchs Run");
		batchpage=new BatchPage(driver);

		clickelementwithname(batchpage.ServiceTypeDropdown,"Service Type Drop down");
		
		if(prop.getProperty("BTServiceNameFW").equalsIgnoreCase("Fedwire"))
		{
		clickelementwithname(batchpage.fedwireOption,"Fedwire select from list");
		}
		else {
			System.out.println("Service Type is not listed");
			Extentlogger.fail("Service Type is not listed");
		}

		clickelementwithname(batchpage.tabbatch,"Switch to Batch tab");
		
		batchpage.tabsearch.sendKeys(Keys.CONTROL + "a");
		batchpage.tabsearch.sendKeys(Keys.DELETE);
		
		sendkeys(batchpage.tabsearch,prop.getProperty("FedwireBatchName"));
		Extentlogger.info("Batch Name : "+prop.getProperty("FedwireBatchName"));
		JSClick(driver,batchpage.chkbatchcheckbox1,"Check box select");

		clickelementwithname(batchpage.btnplaybutton, "Run btn");
		
//		visibleofele(driver,batchpage.lblBatchsuccMsg, "Sucess msg");
//		assertEquals(batchpage.lblBatchsuccMsg.getText(), prop.getProperty("BatchRunsuccmsg"),"Batch Run Sucess msg");
		
		
	}
	
}

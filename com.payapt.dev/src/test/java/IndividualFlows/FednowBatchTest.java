package IndividualFlows;

import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

import com.ExtentReport.ExtentReport;
import com.ExtentReport.Extentlogger;
import com.pages.BatchPage;

import base.BaseClass;

public class FednowBatchTest extends BaseClass{

	public static long FednowstartTime = System.currentTimeMillis();
	
	@Test
	public void Batchcreation() throws InterruptedException
	{
		ExtentReport.createTest("Fednow Batchs Run");
		batchpage=new BatchPage(driver);
		 
		clickelementwithname(batchpage.ServiceTypeDropdown,"Service Type Drop down");
		
		if(prop.getProperty("BTServiceNameFN").equalsIgnoreCase("Fednow"))
		{
		clickelementwithname(batchpage.fedNowOption,"Fedwire select from list");
		}
		else {
			System.out.println("Service Type is not listed");
			Extentlogger.fail("Service Type is not listed");
		}
		
		clickelementwithname(batchpage.tabbatch,"Switch to Batch tab");
		
		JSClick(driver,batchpage.batchfiletoggle, "Changing toggle to Batch file");
		
		sendkeys(batchpage.tabsearch,prop.getProperty("FednowBatchName"));
		Extentlogger.info("Batch Name : "+prop.getProperty("FednowBatchName"));
		JSClick(driver,batchpage.chkbatchcheckbox,"Check box select");

		clickelementwithname(batchpage.btnplaybutton, "Run btn");
		
//		visibleofele(driver,batchpage.lblBatchsuccMsg, "Sucess msg");
//		assertEquals(batchpage.lblBatchsuccMsg.getText(), prop.getProperty("BatchRunsuccmsg"),"Batch Run Sucess msg");
		
		
	}
	
}

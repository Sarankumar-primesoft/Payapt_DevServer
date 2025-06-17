package IndividualFlows;

import org.testng.annotations.Test;

import com.ExtentReport.ExtentReport;
import com.pages.Reports_ParticpantFilePage;

import base.BaseClass;

public class Reports_ParticipantFileFednow extends BaseClass{

	@Test
	public void ParticipantFile_FedNow() throws InterruptedException
	{
//		refresh();
		ExtentReport.createTest("Participant File FedNow Service");
		PFreport = new Reports_ParticpantFilePage(driver);
//		ExtentReport.createChildTest("Participant File FedNow Service");
		scrollByVisibilityOfElement(driver, PFreport.Reports);

		visibleofele(driver, PFreport.MenuListParticipantFile, "Fednow Participant file");
		clickelementwithname(PFreport.MenuListParticipantFile, "Navigating to Fednow Participant file");
		Thread.sleep(1000);
		PFreport.FednowParticipantfile();
		
//		ExtentReport.resetToParent();	
	}	
}

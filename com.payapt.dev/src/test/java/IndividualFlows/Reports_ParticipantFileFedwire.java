package IndividualFlows;

import org.testng.annotations.Test;

import com.ExtentReport.ExtentReport;
import com.pages.Reports_ParticpantFilePage;

import base.BaseClass;

public class Reports_ParticipantFileFedwire extends BaseClass{
	@Test
	public void ParticipantFile_Fedwire() throws InterruptedException
	{
		refresh();
		ExtentReport.createTest("Participant File Fedwire Service");
		PFreport = new Reports_ParticpantFilePage(driver);
		
		scrollByVisibilityOfElement(driver, PFreport.Reports);
		visibleofele(driver, PFreport.MenuListParticipantFile, "Fednow Participant file");
		clickelementwithname(PFreport.MenuListParticipantFile, "Navigating to Fednow Participant file");
		
		PFreport.FedwireParticipantfile();	
		Thread.sleep(1000);
		clickelementwithname(PFreport.resultsClosebtn, "Results Close btn");
		ExtentReport.resetToParent();

	}
	

}

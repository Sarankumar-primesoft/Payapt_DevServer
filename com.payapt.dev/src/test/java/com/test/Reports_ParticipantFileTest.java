package com.test;

import org.testng.annotations.Test;

import com.ExtentReport.ExtentReport;
import com.pages.Reports_ParticpantFilePage;

import base.BaseClass;

public class Reports_ParticipantFileTest extends BaseClass{

	@Test
	public void ParticipantFile_FedNow() throws InterruptedException
	{
//		refresh();
		ExtentReport.createTest("FedNow Participant File");
		PFreport = new Reports_ParticpantFilePage(driver);
		ExtentReport.createChildTest("Participant File FedNow Service");
		scrollByVisibilityOfElement(driver, PFreport.Reports);

		visibleofele(driver, PFreport.MenuListParticipantFile, "Fednow Participant file");
		clickelementwithname(PFreport.MenuListParticipantFile, "Navigating to Fednow Participant file");
		Thread.sleep(1000);
		PFreport.FednowParticipantfile();
		
		ExtentReport.resetToParent();	
	}
	@Test
	public void ParticipantFile_Fedwire() throws InterruptedException
	{
		refresh();
		ExtentReport.createChildTest("Participant File Fedwire Service");
		
		PFreport.FedwireParticipantfile();	
		Thread.sleep(1000);
		clickelementwithname(PFreport.resultsClosebtn, "Results Close btn");
		ExtentReport.resetToParent();

	}
	

}

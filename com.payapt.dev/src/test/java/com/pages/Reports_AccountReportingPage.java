package com.pages;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.ExtentReport.ExtentReport;
import com.ExtentReport.Extentlogger;
import base.BaseClass;

public class Reports_AccountReportingPage extends BaseClass {

	@FindBy(xpath = "//span[@class='menu-item-text' and text()='Reports/System Messages']/../../following::div")
	public WebElement Reports;
	@FindBy(xpath = "//span[@class='menu-item-text' and text()='Account Reporting Request']")
	public WebElement MenuListAccountReporting;
	@FindBy(xpath = "(//div[@id='demo-simple-select-autowidth'])[1]")
	public WebElement paymentservicedropdown;

	@FindBy(xpath = "//div[contains(@class,'MuiPaper-root MuiPaper-elevation')]//ul[1]/li[contains(text(),'FedNow')]")
	public WebElement fednowoption;
	@FindBy(xpath = "//div[contains(@class,'MuiPaper-root MuiPaper-elevation')]//ul[1]/li[contains(text(),'Fedwire')]")
	public WebElement fedwireoption;

	@FindBy(xpath = "//div[contains(.,'Select Report Type') and @role='combobox']")
	public WebElement selectReportType;

	@FindBy(xpath = "//div[@id=contains(.,'menu')]/following::ul//following::li")
	public List<WebElement> reportTypelist;

	@FindBy(xpath = "//li[contains(.,'ABAR')]")
	public WebElement ABARreportType;

	@FindBy(xpath = "//li[contains(.,'AATR')]")
	public WebElement AATRreportType;

	@FindBy(xpath = "//button[contains(text(),'Generate')]")
	public WebElement generatebtn;

	@FindBy(xpath = "//div[contains(text(),'Account Report Request Generated Successfully')]/..")
	public WebElement generatedAlert;

	@FindBy(xpath = "//div[@class='ant-modal-body']")
	public WebElement GenerateResultscren;

	@FindBy(css="span[role='progressbar']")
	public WebElement loader;

	@FindBy(xpath="//button[contains(@class,'MuiButtonBase-root MuiIconButton-root')]/*[@data-testid='FileDownloadIcon']")
	public WebElement resultsdownloadbtn;

	@FindBy(xpath = "//button[@aria-label='Close']")
	public WebElement resultsClosebtn;

	@FindBy(xpath = "//div[contains(text(),'No data to download')]")
	public WebElement noDataAlert;

	@FindBy(xpath = "//li[contains(.,'AADR')]")
	public WebElement AADRreportType;
	@FindBy(xpath = "//input[@placeholder='Start date']")
	public WebElement Startdate;
	@FindBy(xpath = "//input[@placeholder='End date']")
	public WebElement Enddate;
	@FindBy(xpath = "//div[contains(text(),'Select Routing Number')]")
	public WebElement routingNumber;
	@FindBy(xpath = "//ul[@role='listbox']/li")
	public List<WebElement> routingNumbervalueinput;
	
	/* Fedwire locators */
		/* ABAR locators */
	@FindBy(xpath = "//div[contains(text(),'Select Flow Type')]")
	public WebElement FlowType;
	
	@FindBy(xpath = "//div[contains(@id,'menu')]//ul//following::li")
	public List<WebElement> FlowTypevalue;

	/* DTLR locators */
	@FindBy(xpath = "//input[contains(@id,'from')]")
	public WebElement seqFromNum;
	
	@FindBy(xpath = "//input[contains(@id,'to')]")
	public WebElement seqToNum;
/* ----------------------------------------------------------------------- */
	
	//CONSTRUCTOR
	public Reports_AccountReportingPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
/*----------------------Start of Fednow Methods--------------------------------------------------------------------*/	
	/* FedNow Flow Methods */
	
	public void FednowABARReport()	
	{

		//		clickelementwithname(acreport.ABARreportType,"Choosing ABAR Report Type");

		clickelementwithname(acreport.generatebtn,"Clicking Generate button");

		visibleofele(driver, acreport.generatedAlert, "Generated alert");

		visibleofele(driver, acreport.GenerateResultscren, "Generated Results Screen");
		acreport.assertLoaderAppears(acreport.loader);
		boolean loaderstatus = acreport.waitForLoaderToDisappear(acreport.loader,30,2);

		if(loaderstatus)
		{
			clickelementwithname(acreport.resultsdownloadbtn, "Results Download btn");
			Extentlogger.pass("Report is Generated, and able to download.",true);
			clickelementwithname(acreport.resultsClosebtn, "Results Close btn");

		}
		else {
			clickelementwithname(acreport.resultsdownloadbtn, "Results Download btn");
			clickelementwithname(acreport.noDataAlert, "Results no data alert");
			invisibilityofelement(driver, acreport.noDataAlert, "No data alert");
			clickelementwithname(acreport.resultsClosebtn, "Results Close btn");
			Extentlogger.fail("Loader did not disappear, Still loading and displays 'no data'.",true);
		}	
	}
	public void FednowAADRReport() throws InterruptedException	
	{

		clickelementwithname(acreport.Startdate,"Start date selected");
		acreport.datePicker();
		Thread.sleep(3000);
		clickelementwithname(acreport.routingNumber,"Opening routing drop down");
		routingnumberselect(routingNumbervalueinput,prop.getProperty("RoutingNumber"));

		clickelementwithname(acreport.generatebtn,"Clicking Generate button");

		visibleofele(driver, acreport.generatedAlert, "Generated alert");

		visibleofele(driver, acreport.GenerateResultscren, "Generated Results Screen");
		acreport.assertLoaderAppears(acreport.loader);
		boolean loaderstatus = acreport.waitForLoaderToDisappear(acreport.loader,60,5);

		if(loaderstatus)
		{
			clickelementwithname(acreport.resultsdownloadbtn, "Results Download btn");
			Extentlogger.pass("Report is Generated, and able to download.",true);
			clickelementwithname(acreport.resultsClosebtn, "Results Close btn");

		}
		else {
			clickelementwithname(acreport.resultsdownloadbtn, "Results Download btn");
			clickelementwithname(acreport.noDataAlert, "Results no data alert");
			invisibilityofelement(driver, acreport.noDataAlert, "No data alert");
			clickelementwithname(acreport.resultsClosebtn, "Results Close btn");
			Extentlogger.fail("Loader did not disappear, Still loading and displays 'no data'.",true);
		}	
	}
	public void FednowAATRReport() throws InterruptedException	
	{

		clickelementwithname(acreport.Startdate,"Start date selected");
		acreport.datePicker();
		clickelementwithname(acreport.routingNumber,"Opening routing drop down");
		routingnumberselect(routingNumbervalueinput,prop.getProperty("RoutingNumber"));

		clickelementwithname(acreport.generatebtn,"Clicking Generate button");

		visibleofele(driver, acreport.generatedAlert, "Generated alert");

		visibleofele(driver, acreport.GenerateResultscren, "Generated Results Screen");
		acreport.assertLoaderAppears(acreport.loader);
		boolean loaderstatus = acreport.waitForLoaderToDisappear(acreport.loader,30,2);

		if(loaderstatus)
		{
			clickelementwithname(acreport.resultsdownloadbtn, "Results Download btn");
			clickelementwithname(acreport.resultsClosebtn, "Results Close btn");

		}
		else {
			clickelementwithname(acreport.resultsdownloadbtn, "Results Download btn");
			clickelementwithname(acreport.noDataAlert, "Results no data alert");
			invisibilityofelement(driver, acreport.noDataAlert, "No data alert");
			clickelementwithname(acreport.resultsClosebtn, "Results Close btn");
			Extentlogger.fail("Loader did not disappear, Still loading and displays 'no data'.");
		}	
	}
	
/*----------------------End of Fednow Methods----------------------------------------------------------------------*/	

/*----------------------Fedwire Methods----------------------------------------------------------------------------*/	
	
	public void FedwireABARReport() throws InterruptedException {

	    String[] ABARFlowType = prop.getProperty("ABARFlowType").split(",");

	    for (int i = 0; i < ABARFlowType.length; i++) 
	    {
	        String FlowType = ABARFlowType[i];
	        ExtentReport.createChildTest("ABAR - "+FlowType);
	        // Open Flow Type dropdown again and re-fetch the elements
	        clickelementwithname(acreport.FlowType, "Opening Flow type drop down");
	        List<WebElement> flowTypevalueonabar = FlowTypevalue; // <-- re-fetch fresh list from DOM

	        for (WebElement webElement : flowTypevalueonabar) {
	            if (webElement.getText().equalsIgnoreCase(FlowType)) {
	                clickelementwithname(webElement, "Selecting Flow type drop down value");
	                break;
	            }
	        }

	        clickelementwithname(acreport.routingNumber, "Opening routing number dropdown");
	        routingnumberselect(routingNumbervalueinput,prop.getProperty("RoutingNumber"));

	        clickelementwithname(acreport.generatebtn, "Clicking Generate button");

	        visibleofele(driver, acreport.generatedAlert, "Generated alert");
	        visibleofele(driver, acreport.GenerateResultscren, "Generated Results Screen");

	        acreport.assertLoaderAppears(acreport.loader);
	        boolean loaderstatus = acreport.waitForLoaderToDisappear(acreport.loader, 30, 2);

	        if (loaderstatus) {
	            clickelementwithname(acreport.resultsdownloadbtn, "Results Download btn");
	            Extentlogger.pass("Report is Generated, and able to download.", true);
	            clickelementwithname(acreport.resultsClosebtn, "Results Close btn");
	        } else {
	            clickelementwithname(acreport.resultsdownloadbtn, "Results Download btn");
	            clickelementwithname(acreport.noDataAlert, "Results no data alert");
	            invisibilityofelement(driver, acreport.noDataAlert, "No data alert");
	            clickelementwithname(acreport.resultsClosebtn, "Results Close btn");
	        }

	        // ✅ Only refresh & reselect Fedwire + ABAR if not the last iteration
	        if (i < ABARFlowType.length - 1) {
	        	// Refresh and re-navigate for next iteration
		        refresh();
		        clickelementwithname(acreport.paymentservicedropdown, "Payment service dropdown");
		        JSClick(driver, acreport.fedwireoption, "Payment service dropdown");
		
		        clickelementwithname(acreport.selectReportType, "Opening Report Type dropdown");
		        clickelementwithname(acreport.ABARreportType, "Selecting ABAR Report Type from dropdown");
	        }
	        ExtentReport.resetToParent();
	    }
	}
	public void FedwireETOTReport(){
					
			clickelementwithname(acreport.routingNumber, "Opening routing number dropdown");
			routingnumberselect(routingNumbervalueinput,prop.getProperty("RoutingNumber"));
			
			clickelementwithname(acreport.generatebtn, "Clicking Generate button");
			
			visibleofele(driver, acreport.generatedAlert, "Generated alert");
			visibleofele(driver, acreport.GenerateResultscren, "Generated Results Screen");
			
			acreport.assertLoaderAppears(acreport.loader);
			boolean loaderstatus = acreport.waitForLoaderToDisappear(acreport.loader, 30, 2);
			
			if (loaderstatus) {
				clickelementwithname(acreport.resultsdownloadbtn, "Results Download btn");
				Extentlogger.pass("Report is Generated, and able to download.", true);
				clickelementwithname(acreport.resultsClosebtn, "Results Close btn");
			} else {
				clickelementwithname(acreport.resultsdownloadbtn, "Results Download btn");
				clickelementwithname(acreport.noDataAlert, "Results no data alert");
				invisibilityofelement(driver, acreport.noDataAlert, "No data alert");
				clickelementwithname(acreport.resultsClosebtn, "Results Close btn");
			}
	}
	public void FedwireDTLRReport(){

		sendkeys(seqFromNum,prop.getProperty("seqFromNum"));
		sendkeys(seqToNum,prop.getProperty("seqToNum"));
		
		clickelementwithname(acreport.routingNumber, "Opening routing number dropdown");
		routingnumberselect(routingNumbervalueinput,prop.getProperty("RoutingNumber"));
		
		clickelementwithname(acreport.generatebtn, "Clicking Generate button");
		
		visibleofele(driver, acreport.generatedAlert, "Generated alert");
		visibleofele(driver, acreport.GenerateResultscren, "Generated Results Screen");
		
		acreport.assertLoaderAppears(acreport.loader);
		boolean loaderstatus = acreport.waitForLoaderToDisappear(acreport.loader, 60, 5);
		
		if (loaderstatus) {
			clickelementwithname(acreport.resultsdownloadbtn, "Results Download btn");
			Extentlogger.pass("Report is Generated, and able to download.", true);
			clickelementwithname(acreport.resultsClosebtn, "Results Close btn");
		} else {
			clickelementwithname(acreport.resultsdownloadbtn, "Results Download btn");
			clickelementwithname(acreport.noDataAlert, "Results no data alert");
			invisibilityofelement(driver, acreport.noDataAlert, "No data alert");
			clickelementwithname(acreport.resultsClosebtn, "Results Close btn");
		}
	}
	public void FedwireDTLSReport(){
		
		sendkeys(seqFromNum,prop.getProperty("seqFromNum"));
		sendkeys(seqToNum,prop.getProperty("seqToNum"));
		
		clickelementwithname(acreport.routingNumber, "Opening routing number dropdown");
		routingnumberselect(routingNumbervalueinput,prop.getProperty("RoutingNumber"));
		
		clickelementwithname(acreport.generatebtn, "Clicking Generate button");
		
		visibleofele(driver, acreport.generatedAlert, "Generated alert");
		visibleofele(driver, acreport.GenerateResultscren, "Generated Results Screen");
		
		acreport.assertLoaderAppears(acreport.loader);
		boolean loaderstatus = acreport.waitForLoaderToDisappear(acreport.loader, 60, 5);
		
		if (loaderstatus) {
			clickelementwithname(acreport.resultsdownloadbtn, "Results Download btn");
			Extentlogger.pass("Report is Generated, and able to download.", true);
			clickelementwithname(acreport.resultsClosebtn, "Results Close btn");
		} else {
			clickelementwithname(acreport.resultsdownloadbtn, "Results Download btn");
			clickelementwithname(acreport.noDataAlert, "Results no data alert");
			invisibilityofelement(driver, acreport.noDataAlert, "No data alert");
			clickelementwithname(acreport.resultsClosebtn, "Results Close btn");
		}
	}

}


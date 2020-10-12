package TestCases;

import java.io.IOException;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import AppModules.Loads;
import AppModules.TestActions;
import AppModules.Users;
import PageObjects.LoadsPage;
import Utils.TestDataImport;
//import junit.framework.Assert;
import Utils.GenericSkins;
import Utils.SetUp;

public class Loads_TC002 extends SetUp {
	@SuppressWarnings("deprecation")
	@Test
	public void main() throws Exception {
		String sActTestCaseID = "Loads_TC002";
		test = extent.createTest(sActTestCaseID);
		getTestCaseExpectedResult(sActTestCaseID);
		sScreenShotTCFolder = createfolder(sScreenShotFolder, sActTestCaseID);
		GenericSkins.iTotalTestStepsFailed=0;
		boolean bResult = false;
		// String expected = "Uploaded Document";
		
		try {
			LoadSystemIndependencyConfig();

			// Launch application
			TestActions.LaunchApplication();

			// Login as Global Admin
			bResult = TestActions.Login(sActTestCaseID);
			//bResult = TestActions.Login_GlobalAdmin();
			if (bResult == true) {
				bResult = Loads.customizeAGgrid(sActTestCaseID);
			}

			if (bResult == true) {
				bResult = false;
				// Add new Load
				bResult = Loads.addNewLoad(sActTestCaseID);
				if (bResult == true) {
					bResult = false;
					// customize AG grid
					// sTestStepData = "Load Date;Shipper;Shipper Contact;Carrier
					// Name;Status;Origin;Destination;Rate;Rate UOM;Commodity";
					// Loads.customizeAGgrid(sActTestCaseID);

					// Search for record in AG grid
					// sTestStepData = "Current Date;CP Shipper;NA;Changepond
					// T;Open;NA;NA;0.25;Bushels;Corn";
					bResult = Loads.LoadsWebTable(4, sActTestCaseID);
					if (bResult == true) {
						
						//Loads.uploadDoc("Origin Ticket");
						//Loads.uploadDoc("Dest. Ticket");
						//upload docs
						Loads.uploadOriginTicket("Origin");
						
						Loads.uploadDestTicket("Destination");
						//edit record
						bResult=Loads.editLoad(sActTestCaseID);
						bResult = Loads.LoadsWebTable(4, sActTestCaseID);
						LoadsPage.Submit().click();
						Loads.GetInvoiceNumber();
						
						if(!(sInvoiceNumber.equalsIgnoreCase("NA")))
						{
							//LoadsPage.eGenerateInvoice().click();
							Thread.sleep(1000);
							LoadsPage.SubmitLoad().click();
							
							
							Thread.sleep(10000);
							bResult = Loads.LoadsWebTable(5, sActTestCaseID);
							if(bResult==true)
							{
								sActualResult="Load submitted successfully";
							}
							
						}
						else
						{
							sActualResult="No Invoice number";
						}
						

					}
					//LoadsPage.eDelete().click();
					

				}
			}

		} catch (Exception error) {
			sActualResult=error.getMessage();
			bResult = false;

		}
		aHeaderNumbers = null;
		aHeaderNames = null;
		aHeaderNumbers = new ArrayList();
		aHeaderNames = new ArrayList();
		TestActions.CloseApplication();
		Assert.assertEquals(sActualResult.toUpperCase().trim(), sTestCaseExpectedResult.toUpperCase().trim());
	
		
		//driver.close();
		//driver.quit();
		/*
		 * if (iTotalTestStepsFailed > 0) { SRegPackTestCaseStatus = "Failed"; } else {
		 * SRegPackTestCaseStatus="Passed"; } System.out.println("expected:" +
		 * sTestCaseExpectedResult); System.out.println("Actual Result:" +
		 * sActualResult);
		 */// Assert.assertEquals(sTestCaseExpectedResult.trim().toUpperCase(),
		// sActualResult.toUpperCase().trim());

	}
}

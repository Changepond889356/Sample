package TestCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import AppModules.Loads;
import AppModules.TestActions;
import PageObjects.LoadsPage;
import Utils.GenericSkins;
import Utils.SetUp;

public class Loads_TC003 extends SetUp {
	
	@Test
	public void scoularLoad_FullSubmit() throws Exception {

		String sActTestCaseID = "Loads_TC003";
		test = extent.createTest(sActTestCaseID);
		getTestCaseExpectedResult(sActTestCaseID);
		sScreenShotTCFolder = createfolder(sScreenShotFolder, sActTestCaseID);
		GenericSkins.iTotalTestStepsFailed=0;
		boolean bResult = false;
		try {
			// Launch application
			TestActions.LaunchApplication();

			// Login as Global Admin
			bResult = TestActions.Login(sActTestCaseID);
			 
			if (bResult == true) {
				bResult = Loads.customizeAGgrid(sActTestCaseID);
			}

			if (bResult == true) {
				bResult = false; 
				// Add new Load
				bResult = Loads.addNewLoad(sActTestCaseID);
				if (bResult == true) {
					bResult = false;
					
					bResult = Loads.LoadsWebTable(6, sActTestCaseID);
					if (bResult == true) {
						Loads.uploadOriginTicket("Origin");
						Loads.uploadDestTicket("Destination");
						bResult=Loads.editLoad(sActTestCaseID);
						bResult = Loads.LoadsWebTable(6, sActTestCaseID);
						
						LoadsPage.DuplicateBtn().click();
						Loads.EnterCopyDetails(sActTestCaseID, "Open");
						Loads.SelectFirstRecord();
						/* Delete Duplicate Record */
						LoadsPage.eDelete().click();
						Thread.sleep(5000);
						Loads.SelectRecord();
						
						/* Generate invoice for non-Scoular load */
						LoadsPage.GenerateBtn().click();
						Loads.GetInvoiceNumber();
						LoadsPage.GenerateInvoice().click();
						Thread.sleep(10000);
						LoadsPage.SubmittedView().click();
						Thread.sleep(5000); 
						bResult = Loads.customizeAGgrid(sActTestCaseID,4);
						Loads.VerifyStatus("Submitted");
						Loads.SelectRecord();
						LoadsPage.DuplicateBtn().click();
						Loads.EnterCopyDetails(sActTestCaseID, "Submitted");
						Loads.SelectRecord();
						LoadsPage.eDelete().click();
						LoadsPage.SubmittedView().click();
						Thread.sleep(3000); 
						
						/* Mark non-Scoular load as Paid */
						Loads.SelectRecord();
						LoadsPage.eEdit().click();
						Loads.PaidInvoice();
						Thread.sleep(5000);
						LoadsPage.PaidView().click();
						Thread.sleep(3000);
						bResult = Loads.customizeAGgrid(sActTestCaseID,4);
						Loads.VerifyStatus("Paid");
						Loads.SelectRecord();
						LoadsPage.DuplicateBtn().click();
						Loads.EnterCopyDetails(sActTestCaseID, "Paid");
						Loads.SelectRecord();
						LoadsPage.eDelete().click();
						
					}
					//LoadsPage.eDelete().click();
					

				}
			}

		} catch (Exception error) {
			bResult = false;

		}
		TestActions.CloseApplication();
		Assert.assertEquals(sActualResult.toUpperCase().trim(), sTestCaseExpectedResult.toUpperCase().trim());
	
	}

}

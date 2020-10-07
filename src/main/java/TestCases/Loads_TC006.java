package TestCases;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import AppModules.Loads;
import AppModules.TestActions;
import PageObjects.LoadsPage;
import Utils.GenericSkins;
import Utils.SetUp;

public class Loads_TC006 extends SetUp {

	@Test
	public void scoularLoad_FullSubmit() throws Exception {

		String sActTestCaseID = "Loads_TC006";
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
			Loads.SelectRecord();
			Copy_File(sTestDataPath + "Loads.xlsx", sTestResultsPath);
			if (bResult == true) {
				bResult = Loads.customizeAGgrid(sActTestCaseID);
			}

			if (bResult == true) {
				bResult = false; 
				// Add new Load
				bResult = Loads.addNewLoad(sActTestCaseID);
				if (bResult == true) {
					bResult = false;
					
					//bResult = Loads.LoadsWebTable(10, sActTestCaseID);
					if (bResult == true) {
						Loads.uploadOriginTicket("Origin");
						Loads.uploadDestTicket("Destination");
						bResult=Loads.editLoad(sActTestCaseID);
						bResult = Loads.LoadsWebTable(10, sActTestCaseID);
						
						// Generate invoice load 
						LoadsPage.Submit().click();
						Loads.GetInvoiceNumber();
						LoadsPage.GenerateInvoice().click();
						Thread.sleep(10000);
						
						bResult = Loads.LoadsWebTable(11, sActTestCaseID);
					}
					bResult = Loads.ViewPDF(sActTestCaseID);
					if(bResult == true) {
						bResult = Loads.ExportSelectedData(sActTestCaseID);
						bResult = TestActions.LogOut();
						//sActualResult="Load able to download from View PDF";
					}
					if (bResult == true) {
						
						// Login as Shipper Admin
						bResult = TestActions.Login_ShipperAdmin(sActTestCaseID);	
						Thread.sleep(10000);
						LoadsPage.SubmittedView().click();
						bResult = Loads.customizeAGgrid(sActTestCaseID,6);
						Loads.VerifyStatus("Submitted");
						Loads.SelectRecord();
						LoadsPage.eEdit().click();
						Loads.ReturnedInvoice();
						LoadsPage.ViewAll().click();
						bResult = Loads.LoadsWebTable(12, sActTestCaseID);
						
						sActualResult = "Load Returned successfully";
					}

				}
			}

		} catch (Exception error) {
			bResult = false;

		}
		aHeaderNumbers = null;
		aHeaderNames = null;
		aHeaderNumbers = new ArrayList();
		aHeaderNames = new ArrayList();
		TestActions.CloseApplication();
		Assert.assertEquals(sActualResult.toUpperCase().trim(), sTestCaseExpectedResult.toUpperCase().trim());
	
	}
}

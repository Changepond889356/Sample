package TestCases;

import java.io.IOException;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import AppModules.Loads;
import AppModules.TestActions;
import PageObjects.LoadsPage;
import Utils.GenericSkins;
import Utils.SetUp;
import Utils.TestDataImport;

public class Loads_TC004 extends SetUp {

	@Test(dataProvider = "getData")
	public void nonScoularLoad(String sAcccountType, String sUserName, String sPassword) throws IOException {
		
		String sActTestCaseID = "Loads_TC004";
		test = extent.createTest(sActTestCaseID + " - " + sAcccountType);
		getTestCaseExpectedResult(sActTestCaseID);
		sScreenShotTCFolder = createfolder(sScreenShotFolder, sActTestCaseID);
		GenericSkins.iTotalTestStepsFailed=0;
		boolean bResult = false;
		try {
			// Launch application
			TestActions.LaunchApplication();

			// Login as Carrier //
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
					
					bResult = Loads.LoadsWebTableForDispatch(7, sActTestCaseID);
					if (bResult == true) {
						Loads.uploadOriginTicket("Origin");
						Loads.uploadDestTicket("Destination");
						bResult=Loads.editLoad(sActTestCaseID);
						//bResult = Loads.LoadsWebTable(7, sActTestCaseID);
						Loads.SelectRecord();
						
						LoadsPage.Submit().click();
						Loads.GetInvoiceNumber();
						//LoadsPage.GenerateInvoice().click();
						//LoadsPage.eGenerateInvoice().click();
						Thread.sleep(1000);
						LoadsPage.SubmitLoad().click();					
						Thread.sleep(10000);
						
						LoadsPage.SubmittedView().click();
						Thread.sleep(5000); 
						bResult = Loads.customizeAGgrid(sActTestCaseID,6);
						Loads.VerifyStatus("Submitted");
						bResult = TestActions.LogOut();
																	
					}					
				}				
			}
			
			if (bResult == true) {
				
				// Login as Shipper Admin
				//bResult = TestActions.Login_ShipperAdmin(sActTestCaseID);	
				bResult = TestActions.Login(sUserName, sPassword);
				Thread.sleep(10000);
				LoadsPage.SubmittedView().click();
				bResult = Loads.customizeAGgrid(sActTestCaseID,6);
				Loads.VerifyStatus("Submitted");
				Loads.SelectRecord();
				LoadsPage.eEdit().click();
				Loads.ApprovedInvoice();
				LoadsPage.ApprovedView().click();
				bResult = Loads.customizeAGgrid(sActTestCaseID,6);
				Loads.VerifyStatus("Approved");
				bResult = TestActions.LogOut();
				sActualResult = "Load Approved successfully";
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
	@DataProvider
	public Object[][] getData() throws Exception {
		Object[][] data = TestDataImport.readExcel(sTestDataPath,"Login.xlsx","MultiLogin");
		return data;
		
	}
}

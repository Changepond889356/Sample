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

public class Loads_TC003 extends SetUp {
	
	@Test(dataProvider = "getData")
	public void scoularLoad_FullSubmit(String sAcccountType, String sUserName, String sPassword) throws Exception {

		String sActTestCaseID = "Loads_TC003";
		test = extent.createTest(sActTestCaseID + " - " + sAcccountType);
		getTestCaseExpectedResult(sActTestCaseID);
		sScreenShotTCFolder = createfolder(sScreenShotFolder, sActTestCaseID);
		GenericSkins.iTotalTestStepsFailed=0;
		boolean bResult = false;
		try {
			// Launch application
			TestActions.LaunchApplication();

			// Login as Global Admin
			bResult = TestActions.Login(sActTestCaseID);
			//bResult = TestActions.Login(sUserName, sPassword);
			 
			if (bResult == true) {
				bResult = Loads.customizeAGgrid(sActTestCaseID);
			}
			
			if (bResult == true) {
				bResult = false; 
				// Add new Load
				bResult = Loads.addNewLoad(sActTestCaseID);
				if (bResult == true) {
					bResult = false;
					
					bResult = Loads.LoadsWebTableForDispatch(6, sActTestCaseID);
					if (bResult == true) {
						Loads.uploadOriginTicket("Origin");
						Loads.uploadDestTicket("Destination");
						bResult=Loads.editLoad(sActTestCaseID);
						//bResult = Loads.LoadsWebTableForDispatch(6, sActTestCaseID);
						Loads.SelectRecord();
						LoadsPage.DuplicateBtn().click();
						Loads.EnterCopyDetails(sActTestCaseID, "Open");
						Loads.SelectFirstRecord();
						/* Delete Duplicate Record */
						LoadsPage.eDelete().click();
						Thread.sleep(3000);
						Loads.SelectRecord();
						
						/* Generate invoice for non-Scoular load */
						/*LoadsPage.GenerateBtn().click();
						Loads.GetInvoiceNumber();
						LoadsPage.GenerateInvoice().click();
						Thread.sleep(10000);*/
						
						LoadsPage.Submit().click();
						Loads.GetInvoiceNumber();
						if(!(sInvoiceNumber.equalsIgnoreCase("NA"))) {
							//LoadsPage.eGenerateInvoice().click();
							Thread.sleep(1000);
							LoadsPage.SubmitLoad().click();
							
							Thread.sleep(8000);
							//bResult = Loads.LoadsWebTable(5, sActTestCaseID);
							
						}
						
						LoadsPage.SubmittedView().click();
						Thread.sleep(5000); 
						
						bResult = Loads.customizeAGgrid(sActTestCaseID,4);
						Loads.VerifyStatus("Submitted");
						bResult = TestActions.LogOut();
						
						if(bResult) {
							bResult = TestActions.Login(sUserName, sPassword);
							LoadsPage.SubmittedView().click();
							Thread.sleep(3000); 
							
							bResult = Loads.customizeAGgrid(sActTestCaseID,4);
							Loads.VerifyStatus("Submitted");
							Loads.SelectRecord();
							
							bResult = Loads.ViewPDF(sActTestCaseID);
							bResult = Loads.ExportSelectedData(sActTestCaseID);
							Loads.SelectRecord();
							
							LoadsPage.DuplicateBtn().click();
							Loads.EnterCopyDetails(sActTestCaseID, "Submitted");
							//Loads.SelectRecord();
							//LoadsPage.eDelete().click();
							LoadsPage.SubmittedView().click();
							Thread.sleep(3000); 
							
							Loads.HideGeoLocationPane();
							/* Mark non-Scoular load as Paid */
							Loads.SelectRecord();
							LoadsPage.eEdit().click();
							Loads.PaidInvoice();
							Thread.sleep(3000);
							LoadsPage.PaidView().click();
							Thread.sleep(3000);
							bResult = Loads.customizeAGgrid(sActTestCaseID,4);
							Loads.VerifyStatus("Paid");
							Loads.SelectRecord();
							LoadsPage.DuplicateBtn().click();
							Loads.EnterCopyDetails(sActTestCaseID, "Paid");
							Loads.SelectRecord();
							//LoadsPage.eDelete().click();
							bResult = TestActions.LogOut();
							sActualResult = "Non-Scoular Load Paid Successfully";
						}
					}
					//LoadsPage.eDelete().click();
					

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
	@DataProvider
	public Object[][] getData() throws Exception {
		Object[][] data = TestDataImport.readExcel(sTestDataPath,"Login.xlsx","MultiLogin");
		return data;
		
	}
}

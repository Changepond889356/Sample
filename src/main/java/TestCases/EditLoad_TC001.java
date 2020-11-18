package TestCases;

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

public class EditLoad_TC001 extends SetUp {
	
	@Test(dataProvider = "getData")
	public void scoularLoad_FullSubmit(String sAcccountType, String sUserName, String sPassword) throws Exception {

		String sActTestCaseID = "EditLoad_TC001";
		test = extent.createTest(sActTestCaseID + " - " + sAcccountType);
		getTestCaseExpectedResult(sActTestCaseID);
		sScreenShotTCFolder = createfolder(sScreenShotFolder, sActTestCaseID);
		GenericSkins.iTotalTestStepsFailed=0;
		boolean bResult = false;
		try {
			// Launch application
			TestActions.LaunchApplication();

			// Login as Global Admin
			//bResult = TestActions.Login(sActTestCaseID);
			bResult = TestActions.Login(sUserName, sPassword);
			 
			if (bResult == true) {
				bResult = Loads.customizeAGgrid(sActTestCaseID);
			}
			
			if (bResult == true) {
				bResult = false; 
				// Add new Load
				bResult = Loads.addNewLoad(sActTestCaseID);
				if (bResult == true) {
					bResult = false;
					
					bResult = Loads.LoadsWebTableForDispatch(19, sActTestCaseID);
					if (bResult == true) {
						Loads.uploadOriginTicket("Origin");
						Loads.uploadDestTicket("Destination");
						bResult=Loads.editLoad(sActTestCaseID);
						//bResult = Loads.LoadsWebTableForDispatch(6, sActTestCaseID);
						
						Loads.SelectRecord();						
						LoadsPage.Submit().click();
						Loads.GetInvoiceNumber();
						if(!(sInvoiceNumber.equalsIgnoreCase("NA"))) {
							Thread.sleep(1000);
							LoadsPage.SubmitLoad().click();
							Thread.sleep(10000);
							//bResult = Loads.LoadsWebTable(5, sActTestCaseID);
						}
						
						LoadsPage.SubmittedView().click();
						Thread.sleep(5000); 
						bResult = Loads.customizeAGgrid(sActTestCaseID,16);
						Loads.VerifyStatus("Submitted");
						bResult = TestActions.LogOut();
						
						if(bResult) {
							bResult = TestActions.Login_GlobalAdmin();
							LoadsPage.SubmittedView().click();
							Thread.sleep(5000); 
							bResult = Loads.customizeAGgrid(sActTestCaseID,16);
							Loads.VerifyStatus("Submitted");
							Loads.SelectRecord();
							
							bResult=Loads.editLoad(sActTestCaseID);

							bResult = TestActions.LogOut();
							sActualResult = "Load Edited Successfully";
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
		Object[][] data = TestDataImport.readExcel(sTestDataPath,"Settings.xlsx","Login");
		return data;
		
	}
}
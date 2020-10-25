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

public class Loads_TC006 extends SetUp {

	@Test(dataProvider = "getData")
	public void scoularLoad_FullSubmit(String sAcccountType, String sUserName, String sPassword) throws Exception {

		String sActTestCaseID = "Loads_TC006";
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
			//Loads.SelectRecord();
			//Copy_File(sTestDataPath + "Loads.xlsx", sTestResultsPath);
			if (bResult == true) {
				bResult = Loads.customizeAGgrid(sActTestCaseID);
			}

			if (bResult == true) {
				bResult = false; 
				// Add new Load
				bResult = Loads.addNewLoad(sActTestCaseID);
				if (bResult == true) {
					bResult = false;
					
					bResult = Loads.LoadsWebTableForDispatch(10, sActTestCaseID);
					if (bResult == true) {
						Loads.uploadOriginTicket("Origin");
						Loads.uploadDestTicket("Destination");
						bResult=Loads.editLoad(sActTestCaseID);
						//bResult = Loads.LoadsWebTableForDispatch(10, sActTestCaseID);
						Loads.SelectRecord();
						
						// Generate invoice load 
						LoadsPage.Submit().click();
						Loads.GetInvoiceNumber();
						//LoadsPage.eGenerateInvoice().click();
						Thread.sleep(1000);
						LoadsPage.SubmitLoad().click();
						Thread.sleep(10000);
						
						//bResult = Loads.LoadsWebTableForDispatch(11, sActTestCaseID);
						if(bResult) {
							bResult = false;
							bResult = TestActions.LogOut();
						}
					}
					
					if (bResult == true) {
						bResult = false;
						bResult = TestActions.Login(sUserName, sPassword);
						Thread.sleep(10000);
						
						LoadsPage.SubmittedView().click();
						Thread.sleep(3000);
						bResult = Loads.customizeAGgrid(sActTestCaseID,6);
						Loads.VerifyStatus("Submitted");
						Loads.SelectRecord();
						
						/*bResult = Loads.ViewPDF(sActTestCaseID);
						if(bResult == true) {
							bResult = false;
							bResult = Loads.ExportSelectedData(sActTestCaseID);
							//sActualResult="Load able to download from View PDF";
						}
						
						Loads.SelectRecord();*/
						LoadsPage.eEdit().click();
						Loads.ReturnedInvoice();
						LoadsPage.ViewAll().click();
						//bResult = Loads.LoadsWebTable(12, sActTestCaseID);
						bResult = TestActions.LogOut();
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
	@DataProvider
	public Object[][] getData() throws Exception {
		Object[][] data = TestDataImport.readExcel(sTestDataPath,"Login.xlsx","MultiLogin");
		return data;
		
	}
}

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

public class Loads_TC005 extends SetUp {
	
	@Test(dataProvider = "getData")
	public void dispatchLoad(String sAcccountType, String sUserName, String sPassword) throws IOException {
		
		String sActTestCaseID = "Loads_TC005";
		test = extent.createTest(sActTestCaseID + " - " + sAcccountType);
		getTestCaseExpectedResult(sActTestCaseID);
		sScreenShotTCFolder = createfolder(sScreenShotFolder, sActTestCaseID);
		GenericSkins.iTotalTestStepsFailed=0;
		
		boolean bResult = false;
		try {
			LoadSystemIndependencyConfig();
			// Launch application
			TestActions.LaunchApplication();

			// Login as Carrier //
			//bResult = TestActions.Login_ShipperAdmin(sActTestCaseID);
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
					
					bResult = Loads.LoadsWebTableForDispatch(8, sActTestCaseID);
					if (bResult == true) {
						LoadsPage.eEdit().click();
						Thread.sleep(5000);
						LoadsPage.DispatchBtn().click();
						Thread.sleep(5000);
						//bResult = Loads.LoadsWebTableForDispatch(9, sActTestCaseID);
						Loads.SelectRecord();
						bResult = TestActions.LogOut();									
					}	
					sActualResult = "Dispatch sent successfully and carrier has control over load.";
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
		Object[][] data = TestDataImport.readExcel(sTestDataPath,"Login.xlsx","MultiLogin2");
		return data;
		
	}
}

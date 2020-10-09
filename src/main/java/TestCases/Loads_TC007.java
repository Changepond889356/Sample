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

public class Loads_TC007 extends SetUp {

	@Test(dataProvider = "getData")
	public void importFile(String sAcccountType, String sUserName, String sPassword) throws IOException {
		String sActTestCaseID = "Loads_TC007";
		test = extent.createTest(sActTestCaseID + " - " + sAcccountType);
		getTestCaseExpectedResult(sActTestCaseID);
		sScreenShotTCFolder = createfolder(sScreenShotFolder, sActTestCaseID);
		GenericSkins.iTotalTestStepsFailed=0;
		boolean bResult = false;
		try {
			// Launch application
			TestActions.LaunchApplication();

			// Login 
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
					
					bResult = Loads.LoadsWebTable(13, sActTestCaseID);
					
					if(bResult == true) {
						bResult = Loads.ImportFile(sActTestCaseID, "ImportSheet");
						
						sActualResult="Import Done Successfully";
					}
					bResult = TestActions.LogOut();
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

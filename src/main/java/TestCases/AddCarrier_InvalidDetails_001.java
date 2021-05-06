package TestCases;

import java.io.IOException;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import AppModules.Carrier;
import AppModules.TestActions;
import AppModules.Users;
import PageObjects.LoadsPage;
import Utils.GenericSkins;
import Utils.SetUp;
import Utils.TestDataImport;

public class AddCarrier_InvalidDetails_001 extends SetUp {
	
	@Test(dataProvider = "getData")
	public void carrierScenario(String sAcccountType, String sUserName, String sPassword) throws IOException {
		
		String sActTestCaseID = "AddCarrier_InvalidDetails_001";
		test = extent.createTest(sActTestCaseID + " - " + sAcccountType);
		getTestCaseExpectedResult(sActTestCaseID);
		sScreenShotTCFolder = createfolder(sScreenShotFolder, sActTestCaseID);
		sGenricAccountType=sAcccountType;
		boolean bResult = false;
		try {
			// Launch application
			TestActions.LaunchApplication();
			
			// Login 
			//bResult = true;
			bResult = TestActions.Login(sUserName, sPassword);
			
			Carrier.SelectCarrierMenu();
			//check if failed search displayed
			bResult = Carrier.AddCarrier(sActTestCaseID, "NULL");
			
			if (bResult == true) {
				sActualResult="Error messages displayed correctly";
			}
			
		} catch(Exception e) {
			sActualResult=e.getMessage();
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

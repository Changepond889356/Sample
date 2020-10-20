package TestCases;

import java.io.IOException;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import AppModules.ShipperContact;
import AppModules.TestActions;
import AppModules.Users;
import Utils.GenericSkins;
import Utils.SetUp;
import Utils.TestDataImport;

public class ShipperContacts_TC001 extends SetUp {
	
	@Test(dataProvider = "getData")
	public void carrierScenario(String sAcccountType, String sUserName, String sPassword) throws IOException {
		
		String sActTestCaseID = "ShipperContacts_TC001";
		test = extent.createTest(sActTestCaseID + " - " + sAcccountType);
		getTestCaseExpectedResult(sActTestCaseID);
		sScreenShotTCFolder = createfolder(sScreenShotFolder, sActTestCaseID);
		
		boolean bResult = false;
		try {
			// Launch application
			TestActions.LaunchApplication();
			bResult = TestActions.Login(sUserName, sPassword);
			
			if(bResult) {
				ShipperContact.SelectShipperContactMenu();
				bResult = ShipperContact.AddShipperContact(sActTestCaseID);
				//bResult = TestActions.LogOut();
				//bResult = true;
			}
			
			if(bResult) {
				bResult = ShipperContact.customizeGrid(sActTestCaseID);
			}
			if(bResult) {
				bResult = ShipperContact.LoadWebTable(1,sActTestCaseID);
			}
			if(bResult) {
				bResult = ShipperContact.LoadWebTable(3,sActTestCaseID);
			}
			if(bResult) {
				bResult = ShipperContact.LoadWebTable(4,sActTestCaseID);
			}
			if(bResult) {
				bResult = ShipperContact.LoadWebTable(2,sActTestCaseID);
			}
			if(bResult) {
				sActualResult = "Shipper Contact Validated Successfully";
				bResult = TestActions.LogOut();				
			}
		} catch (Exception error) {
			sActualResult = error.getMessage();
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

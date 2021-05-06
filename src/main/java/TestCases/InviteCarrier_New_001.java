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

public class InviteCarrier_New_001 extends SetUp {
	
	@Test(dataProvider = "getData")
	public void carrierScenario(String sAcccountType, String sUserName, String sPassword) throws IOException {
		sGenricAccountType = sAcccountType;
		String sActTestCaseID = "InviteCarrier_New_001";
		test = extent.createTest(sActTestCaseID + " - " + sAcccountType);
		getTestCaseExpectedResult(sActTestCaseID);
		sScreenShotTCFolder = createfolder(sScreenShotFolder, sActTestCaseID);
		
		boolean bResult = false;
		try {
			// Launch application
			TestActions.LaunchApplication();
			
			// Login 
			//bResult = true;
			bResult = TestActions.Login(sUserName, sPassword);
			
			Carrier.SelectCarrierMenu();
			bResult = Carrier.AddCarrier(sActTestCaseID, "Current Date");
			bResult = TestActions.LogOut();
			if (bResult == true) {
				bResult = TestActions.Login_GlobalAdmin();
				Carrier.SelectCarrierMenu();
				bResult = Carrier.CustomiseGrid(sActTestCaseID);								
				
				if (bResult == true) {
					bResult=Carrier.LoadWebTable(sActTestCaseID, 2); 
					if(!sAcccountType.equalsIgnoreCase("Global Admin")) {
						bResult = Carrier.AcceptCarrier();
					}				 
					TestActions.LogOut();
				}
				if(bResult==true)
				{
					sActualResult = "Carrier invited successfully";
				}
				
						
			}
			
		} catch(Exception e) {
			
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

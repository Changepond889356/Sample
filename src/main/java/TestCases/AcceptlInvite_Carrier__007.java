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

public class AcceptlInvite_Carrier__007 extends SetUp {
	
	@Test(dataProvider = "getData")
	public void carrierScenario(String sAcccountType, String sUserName, String sPassword) throws IOException {
		
		String sActTestCaseID = "AcceptlInvite_Carrier__007";
		sGenricAccountType=sAcccountType;
		test = extent.createTest(sActTestCaseID + " - " + sAcccountType);
		getTestCaseExpectedResult(sActTestCaseID);
		sScreenShotTCFolder = createfolder(sScreenShotFolder, sActTestCaseID);
		
		boolean bResult = false;
		try {
			// Launch application
			TestActions.LaunchApplication();
			bResult=true;
			if (bResult == true) {
				//Login as Carrier to accept request
				if (bResult == true) {
					bResult = TestActions.Login(sActTestCaseID);
					Carrier.OpenSettings();
					bResult = Carrier.AcceptRequest(sActTestCaseID, 8); 
					if(bResult) {
						bResult = Carrier.VerifyCarrierOnLoadScreen(sActTestCaseID, 8);
					}					
					bResult = TestActions.LogOut();
				}
				
				
				
				System.out.println("bResult final:"+bResult);
				if(bResult==true) {
					sActualResult = "Accepted invitation Successfully";
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

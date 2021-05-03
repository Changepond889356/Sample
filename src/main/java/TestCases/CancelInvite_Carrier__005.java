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

public class CancelInvite_Carrier__005 extends SetUp {
	
	@Test(dataProvider = "getData")
	public void carrierScenario(String sAcccountType, String sUserName, String sPassword) throws IOException {
		sGenricAccountType=sAcccountType;
		String sActTestCaseID = "CancelInvite_Carrier__005";
		test = extent.createTest(sActTestCaseID + " - " + sAcccountType);
		getTestCaseExpectedResult(sActTestCaseID);
		sScreenShotTCFolder = createfolder(sScreenShotFolder, sActTestCaseID);
		
		boolean bResult = false;
		try {
			// Launch application
			TestActions.LaunchApplication();
			bResult=true;
			if (bResult == true) {
				
				//Cancel Req
				if (bResult == true) {
					bResult = TestActions.Login(sUserName, sPassword);
					Carrier.SelectCarrierMenu();
					bResult = Carrier.CustomiseGrid(sActTestCaseID);
					Carrier.LoadWebTable(sActTestCaseID, 6);
					bResult = Carrier.CancelInvite(); 
					bResult = TestActions.LogOut();
				}
				System.out.println("bResult:"+bResult);
				//Login as Carrier to verify the cancel request
				if (bResult == true) {
					bResult = TestActions.Login(sActTestCaseID);
					Carrier.OpenSettings();
					bResult = Carrier.VerifyCancelledRequest(sActTestCaseID, 6); //write code for verify cancel req
					TestActions.LogOut();
				}
				
				System.out.println("bResult final:"+bResult);
				if(bResult==true) {
					sActualResult = "Cancelled Invitation Successfully";
				}
				
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

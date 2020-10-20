package TestCases;

import java.io.IOException;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import AppModules.Carrier;
import AppModules.TestActions;
import Utils.SetUp;
import Utils.TestDataImport;

public class Settings_TC001 extends SetUp {

	@Test(dataProvider = "getData")
	public void changePassword(String sAcccountType, String sUserName, String sPassword) throws IOException {
		
		String sTestCaseID = "Settings_TC001";
		test = extent.createTest(sTestCaseID +"_"+ sAcccountType);
		getTestCaseExpectedResult(sTestCaseID);
		sScreenShotTCFolder = createfolder(sScreenShotFolder, sTestCaseID);
		boolean bResult = false;
		
		try {
			// Launch application
			TestActions.LaunchApplication();
			bResult = TestActions.Login(sUserName, sPassword);
			
			if(bResult) {
				Carrier.OpenSettings();
				bResult = Carrier.ChangePassword(sUserName,sTestCaseID,1);		
				if(bResult) {
					bResult = TestActions.LogOut();
				}
			}
			if(bResult) {
				bResult = Carrier.ChangePassword(sUserName,sTestCaseID,2);		
			}
			if(bResult) {
				Carrier.OpenSettings();
				bResult = Carrier.ChangePassword(sUserName,sTestCaseID,3);		
				if(bResult) {
					bResult = TestActions.LogOut();
					sActualResult = "Password Change Successfully";
				}
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
		Object[][] data = TestDataImport.readExcel(sTestDataPath,"Settings.xlsx","Login");
		return data;
		
	}
}

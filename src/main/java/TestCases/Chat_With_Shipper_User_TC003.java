package TestCases;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import AppModules.Chat;
import AppModules.TestActions;
import Utils.SetUp;
import Utils.TestDataImport;

public class Chat_With_Shipper_User_TC003 extends SetUp {
	
	@Test(dataProvider = "getData")
	public void chatFeatures(String sAcccountType, String sUserName, String sPassword) throws Exception {
		
		String sTestCaseID = "Chat_With_Shipper_User_TC003";
		test = extent.createTest(sAcccountType + "_" + sTestCaseID );
		getTestCaseExpectedResult(sTestCaseID);
		sScreenShotTCFolder = createfolder(sScreenShotFolder, sTestCaseID);
		boolean bResult = false;
		
		try {
			// Launch application
			TestActions.LaunchApplication();

			bResult = TestActions.Login(sUserName, sPassword);
			
			//Chat Invite 
			if (bResult == true) {				
				bResult = Chat.ChatValidation(sTestCaseID, 11); 
			}
			
			//Send Message
			if (bResult == true) {				
				bResult = Chat.ChatValidation(sTestCaseID, 12); 
			}
			
			//Send Image 
			if (bResult == true) {				
				bResult = Chat.ChatValidation(sTestCaseID, 13); 
			}
			
			//Send Document
			if (bResult == true) {				
				bResult = Chat.ChatValidation(sTestCaseID, 14); 
			}
			
			//Close Chat
			if (bResult == true) {				
				bResult = Chat.ChatValidation(sTestCaseID, 15); 
			}
			
			sActualResult = "Chat Validated successfully";
			bResult = TestActions.LogOut();
			
		} catch (Exception error) {
			sActualResult = error.getMessage();
			error.printStackTrace();
			throw error;
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
		Object[][] data = TestDataImport.readExcel(sTestDataPath,"Chat.xlsx","Login");
		return data;
		
	}

}


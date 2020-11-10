package TestCases;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import AppModules.Chat;
import AppModules.TestActions;
import Utils.SetUp;
import Utils.TestDataImport;

public class Group_Chat_TC004 extends SetUp {
	
	@Test(dataProvider = "getData")
	public void chatFeatures(String sAcccountType,String sName, String sUserName, String sPassword) throws Exception {
		
		String sTestCaseID = "Group_Chat_TC004";
		test = extent.createTest(sAcccountType + "_" +sTestCaseID);
		getTestCaseExpectedResult(sTestCaseID);
		sScreenShotTCFolder = createfolder(sScreenShotFolder, sTestCaseID);
		boolean bResult = false;

		try {
			// Launch application
			TestActions.LaunchApplication();

			bResult = TestActions.Login(sUserName, sPassword);
			if (bResult == true) {				
				//Chat Invite 
				bResult = Chat.ChatValidation(sTestCaseID,sName, 25); 				

				if (bResult == true) {				
					//Send Message
					bResult = Chat.ChatValidation(sTestCaseID,sName, 26); 
					if(bResult) {
						bResult = TestActions.LogOut();
					}
				}
			}
			
			bResult = TestActions.Login(sTestCaseID);
			if (bResult == true) {	
				//Verify Message
				bResult = Chat.ChatValidation(sTestCaseID,sName, 27); 

				if (bResult == true) {				
					//Send Image 
					bResult = Chat.ChatValidation(sTestCaseID,sName, 28); 
					if(bResult) {
						bResult = TestActions.LogOut();
					}
				}
			}
			
			bResult = TestActions.Login(sUserName, sPassword);
			if (bResult == true) {	
				//Verify Image
				bResult = Chat.ChatValidation(sTestCaseID,sName, 29); 
								
				if (bResult == true) {				
					//Send Document
					bResult = Chat.ChatValidation(sTestCaseID,sName, 30); 
					if(bResult) {
						bResult = TestActions.LogOut();
					}
				}
			}
			
			bResult = TestActions.Login(sTestCaseID);
			if (bResult == true) {	
				//Verify Document
				bResult = Chat.ChatValidation(sTestCaseID,sName, 31); 

				if (bResult == true) {
					//Quit Chat
					bResult = Chat.ChatValidation(sTestCaseID,sName, 32); 
					if(bResult) {
						bResult = TestActions.LogOut();
					}
				}
			}
			sActualResult = "Chat Validated successfully";
			
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
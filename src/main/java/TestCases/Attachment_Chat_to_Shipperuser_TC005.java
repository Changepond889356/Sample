package TestCases;

import java.io.IOException;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import AppModules.Chat;
import AppModules.TestActions;
import Utils.SetUp;
import Utils.TestDataImport;

public class Attachment_Chat_to_Shipperuser_TC005 extends SetUp {
	
	@Test(dataProvider = "getData")
	public void chatFeatures(String sAcccountType, String sName, String sUserName, String sPassword) throws Exception {
		
		String sTestCaseID = "Attachment_Chat_to_Shipperuser_TC005";
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
				bResult = Chat.ChatValidation(sTestCaseID,sName, 83); 				
			}
			if(bResult==true)
			{
				if (bResult == true) {	
					//send image
					bResult = Chat.ChatValidation(sTestCaseID,sName, 84); 

					if (bResult == true) {				
						//verify Image 
						TestActions.LogOut();
						bResult = TestActions.Login(sTestCaseID);
						
						bResult = Chat.ChatValidation(sTestCaseID,sName, 85);  
						if(bResult) {
							//bResult = TestActions.LogOut();
							TestActions.CloseApplication();
							sActualResult = "Document sent successfully";
						}
					}
				}
				

			}
								
			
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

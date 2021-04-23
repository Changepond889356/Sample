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

public class ReSend_Chat_to_Carrier_TC003
 extends SetUp {
	
	@Test(dataProvider = "getData")
	public void chatFeatures(String sAcccountType, String sName, String sUserName, String sPassword) throws Exception {
		
		String sTestCaseID = "ReSend_Chat_to_Carrier_TC003";
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
				bResult = Chat.ChatValidation(sTestCaseID,sName, 39); 				

				if (bResult == true) {				
					//Send Message
					bResult = Chat.ChatValidation(sTestCaseID,sName, 40); 
					
				}
			}
			if(bResult==true)
			{
				TestActions.LogOut();
				bResult = TestActions.Login(sTestCaseID);
				if (bResult == true) {	
					//Verify Message
					bResult = Chat.ChatValidation(sTestCaseID,sName, 41); 

					if (bResult == true) {				
						//Send Image 
						//bResult = Chat.ChatValidation(sTestCaseID,sName, 36);  
						if(bResult) {
							//bResult = TestActions.LogOut();
							TestActions.CloseApplication();
							sActualResult = "ReSent Chat successfully";
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

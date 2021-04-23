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

public class Leave_Chat_With_Carrrier_TC006 extends SetUp {
	
	@Test(dataProvider = "getData")
	public void chatFeatures(String sAcccountType, String sName, String sUserName, String sPassword) throws Exception {
		
		String sTestCaseID = "Leave_Chat_With_Carrrier_TC006";
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
				bResult = Chat.ChatValidation(sTestCaseID,sName, 48); 				
			}
			if(bResult==true)
			{
				if (bResult == true) {	
					//send message
					bResult = Chat.ChatValidation(sTestCaseID,sName, 49); 

					if (bResult == true) {				
						//verify message as carrier
						TestActions.LogOut();
						bResult = TestActions.Login(sTestCaseID);
						
						bResult = Chat.ChatValidation(sTestCaseID,sName, 50);  
						if(bResult==true) {
							//leave chat
							bResult = Chat.ChatValidation(sTestCaseID,sName, 51);  
							if(bResult==true)
							{
								TestActions.CloseApplication();
								sActualResult = "Left chat successfully";
							}
							
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

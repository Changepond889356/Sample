package TestCases;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import AppModules.Chat;
import AppModules.TestActions;
import Utils.SetUp;
import Utils.TestDataImport;

public class Send_Chat_to_ShipperAdmin_TC002 extends SetUp {

	@Test(dataProvider = "getData")
	public void chatFeatures(String sAcccountType, String sName, String sUserName, String sPassword) throws Exception {

		String sTestCaseID = "Send_Chat_to_ShipperAdmin_TC002";
		test = extent.createTest(sAcccountType + "_" + sTestCaseID);
		getTestCaseExpectedResult(sTestCaseID);
		sScreenShotTCFolder = createfolder(sScreenShotFolder, sTestCaseID);
		boolean bResult = false;

		try {
			// Launch application
			TestActions.LaunchApplication();

			bResult = TestActions.Login(sUserName, sPassword);
			if (bResult == true) {
				// Chat Invite
				bResult = Chat.ChatValidation(sTestCaseID, sName, 55);

				if (bResult == true) {
					// Send Message
					bResult = Chat.ChatValidation(sTestCaseID, sName, 56);
					if (bResult==true) {
						bResult = TestActions.LogOut();
					}
				}
			}

			bResult = TestActions.Login(sTestCaseID);
			if (bResult == true) {
				// Verify Message
				bResult = Chat.ChatValidation(sTestCaseID, sName, 57);

				if (bResult == true) {
					sActualResult = "Sent Chat successfully";
					TestActions.LogOut();
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
		Object[][] data = TestDataImport.readExcel(sTestDataPath, "Chat.xlsx", "Login");
		return data;

	}
}

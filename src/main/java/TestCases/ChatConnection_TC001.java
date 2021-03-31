package TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import AppModules.Deals;
import AppModules.Share;
import AppModules.TestActions;
import Utils.SetUp;

public class ChatConnection_TC001 extends SetUp {

	@Test
	public void ShipperUserChatConnection() throws Exception {
		String sTestCaseID = "ChatConnection_TC001";
		test = extent.createTest(sTestCaseID);
		getTestCaseExpectedResult(sTestCaseID);
		sScreenShotTCFolder = createfolder(sScreenShotFolder, sTestCaseID);
		boolean bResult = false;

		try {
			// Launch application
			TestActions.LaunchApplication();

			// Login as Global Admin
			bResult = TestActions.Login(sTestCaseID);
			if (bResult == true) {
				bResult = Deals.addChatConnectionDeal(sTestCaseID);
				if (bResult == true) {
					// handle newly created deal widget
					bResult = Deals.VerifyChatChannel(sTestCaseID,1);		
					if (bResult == true) {
						TestActions.LogOut();
					}
					bResult = TestActions.Login("ChatConnection_TC001(2)");
					if (bResult == true) {
						bResult = Deals.VerifyChatChannel(sTestCaseID,2);	
						if (bResult == true) {
							TestActions.LogOut();
						}
						bResult = TestActions.Login(sTestCaseID);
						if (bResult == true) {
							bResult = Deals.VerifyChatChannel(sTestCaseID,3);	
							if (bResult == true) {
								bResult = Deals.VerifyChatChannel(sTestCaseID,4);
								if (bResult == true) {
									TestActions.LogOut();
								}
								bResult = TestActions.Login("ChatConnection_TC001(2)");
								if (bResult == true) {
									bResult = Deals.VerifyChatChannel(sTestCaseID,5);
									if (bResult == true) {
										TestActions.LogOut();
									}
								}
							}
						}
					}
				}
				
			}
			sActualResult = "Chat Connection successfully";
		} catch (Exception error) {
			sActualResult = error.getMessage();
		}

		TestActions.CloseApplication();
		Assert.assertEquals(sActualResult.toUpperCase().trim(), sTestCaseExpectedResult.toUpperCase().trim());
		
	}
}


package TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import AppModules.TestActions;
import AppModules.Users;
import Utils.GenericSkins;
import Utils.SetUp;

public class User_TC002 extends SetUp {
	@Test
	public void main() throws Exception {
		String sTestCaseID = "User_TC002";
		test = extent.createTest(sTestCaseID);
		getTestCaseExpectedResult(sTestCaseID);
		// String sTestCaseID = "User_TC002";
		sScreenShotTCFolder = createfolder(sScreenShotFolder, sTestCaseID);
		boolean bResult = false;

		try {
			// Launch application
			TestActions.LaunchApplication();

			// Login as Global Admin
			bResult = TestActions.Login_GlobalAdmin();
			if (bResult == true) {
				// add user
				bResult = Users.addUser(sTestCaseID);
				// Search for user
				bResult = Users.UserWebTable(5, sTestCaseID);
				// cancel invite
				bResult = Users.UserWebTable(6, sTestCaseID);
				// view canceled invite
				bResult = Users.UserWebTable(7, sTestCaseID);

				if (bResult == true) {
					sActualResult = "Invitation Cancelled successfully";
				}

			}

		} catch (Exception error) {
			sActualResult = error.getMessage();
		}

		TestActions.CloseApplication();
		Assert.assertEquals(sActualResult.toUpperCase().trim(), sTestCaseExpectedResult.toUpperCase().trim());
	}
}

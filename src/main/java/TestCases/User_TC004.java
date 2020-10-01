package TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import AppModules.TestActions;
import AppModules.Users;
import PageObjects.UserPage;
import Utils.GenericSkins;
import Utils.SetUp;

public class User_TC004 extends SetUp {
	@Test
	public void main() throws Exception {
		String sTestCaseID = "User_TC004";
		GenericSkins.iTotalTestStepsFailed = 0;
		test = extent.createTest(sTestCaseID);
		boolean bResult = false;
		getTestCaseExpectedResult(sTestCaseID);
		sScreenShotTCFolder = createfolder(sScreenShotFolder, sTestCaseID);

		try {
			LoadSystemIndependencyConfig();
			// Launch application
			TestActions.LaunchApplication();
			// validate forgot password
			bResult = TestActions.resetPassword(sTestCaseID);
			if (bResult == true) {
				// Login wiht new password
				bResult = TestActions.Login(sTestCaseID);
				if (bResult == true) {

					sActualResult = "Password reset successfully";
					TestActions.LogOut();
				}

			}

		} catch (Exception error) {
			sActualResult = error.getMessage();
		}
		TestActions.CloseApplication();
		Assert.assertEquals(sActualResult.toUpperCase().trim(), sTestCaseExpectedResult.toUpperCase().trim());

	}
}

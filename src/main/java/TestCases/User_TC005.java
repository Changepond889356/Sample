package TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import AppModules.TestActions;
import AppModules.Users;
import PageObjects.UserPage;
import Utils.GenericSkins;
import Utils.SetUp;

public class User_TC005 extends SetUp {
	@Test
	public void main() throws Exception {
		String sTestCaseID = "User_TC005";
		test = extent.createTest(sTestCaseID);
		GenericSkins.iTotalTestStepsFailed = 0;
		getTestCaseExpectedResult(sTestCaseID);
		sScreenShotTCFolder = createfolder(sScreenShotFolder, sTestCaseID);

		boolean bResult = false;

		try {
			LoadSystemIndependencyConfig();
			// Launch application
			TestActions.LaunchApplication();

			// Login as Global Admin
			bResult = TestActions.Login_GlobalAdmin();
			if (bResult == true) {
				bResult = Users.UserWebTable(12, sTestCaseID);
				UserPage.eDelete().click();
				UserPage.eConfirmDelete().click();
				Thread.sleep(5000);
				bResult = Users.UserWebTable(13, sTestCaseID);

				if (bResult == true) {
					sActualResult = "User deleted successfully";
					System.out.println("User_TC005 actual result:" + sActualResult);
					TestActions.LogOut();

				}
			}

		} catch (Exception error) {
			sActualResult = error.getMessage();
		}
		TestActions.CloseApplication();
		System.out.println("User_TC005 actual result:" + sActualResult);
		Assert.assertEquals(sActualResult.toUpperCase().trim(), sTestCaseExpectedResult.toUpperCase().trim());

		// ResultComparision();

	}
}

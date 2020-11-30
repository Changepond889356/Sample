package TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import AppModules.TestActions;
import AppModules.Users;
import PageObjects.UserPage;
import Utils.GenericSkins;
import Utils.SetUp;

public class User_TC003 extends SetUp {
	@Test
	public void main() throws Exception {
		
		String sTestCaseID = "User_TC003";
		test = extent.createTest(sTestCaseID);
		getTestCaseExpectedResult(sTestCaseID);
		boolean bResult = false;
		GenericSkins.iTotalTestStepsFailed=0;
		sScreenShotTCFolder = createfolder(sScreenShotFolder, sTestCaseID);
		try {
			// Launch application
			TestActions.LaunchApplication();

			// Login as Global Admin
			bResult = TestActions.Login_GlobalAdmin();
			if (bResult == true) {
				// add user
				bResult = Users.addUser(sTestCaseID);
				// Search for user
				System.out.println("Search for user");
				bResult = Users.UserWebTable(8, sTestCaseID);

				bResult = Users.UserWebTable(9, sTestCaseID);
				if (bResult == true) {
					bResult = TestActions.Registration(sTestCaseID);
					if (bResult == true) {
						bResult = TestActions.Login(sTestCaseID);
						if (bResult == true) {
								sActualResult = "Invitation Accepted successfully";
								TestActions.LogOut();
						}
					}

				}

			}

		} catch (Exception error) {
			sActualResult = error.getMessage();
		}
		//TestActions.CloseApplication();
		Assert.assertEquals(sActualResult.toUpperCase().trim(), sTestCaseExpectedResult.toUpperCase().trim());

	}
}

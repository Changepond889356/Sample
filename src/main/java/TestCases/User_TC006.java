package TestCases;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;
import AppModules.TestActions;
import AppModules.Users;
import Utils.GenericSkins;
import Utils.SetUp;
import Utils.TestDataImport;
//import bsh.This;

public class User_TC006 extends SetUp {
	@Test
	public void main() throws Exception {
		String sTestCaseID = "User_TC006";
		test = extent.createTest(sTestCaseID);
		getTestCaseExpectedResult(sTestCaseID);
		sScreenShotTCFolder = createfolder(sScreenShotFolder, sTestCaseID);
		boolean bResult = false;

		try {
			// Launch application
			TestActions.LaunchApplication();

			// Login as Global Admin
			bResult = TestActions.Login_GlobalAdmin();
			if (bResult == true) {
				// customize columns
				bResult = Users.customizeUsergrid(sTestCaseID);
				if (bResult == true) {
					// add user
					bResult = Users.addUser(sTestCaseID);
					// Search for user
					bResult = Users.UserWebTable(14, sTestCaseID);
					if (bResult == true) {
						// Resend invite
						bResult = Users.UserWebTable(15, sTestCaseID);

						// cancel invite
						bResult = Users.UserWebTable(16, sTestCaseID);
						if (bResult == true) {
							// view canceled invite
							bResult = Users.UserWebTable(17, sTestCaseID);

							if (bResult == true) {
								sActualResult = "Invitation sent successfully";
							}

						}

					}

				}

			}

		} catch (Exception error) {
			sActualResult = error.getMessage();
		}

		aHeaderNumbers = null;
		aHeaderNames = null;
		aHeaderNumbers = new ArrayList();
		aHeaderNames = new ArrayList();

		TestActions.CloseApplication();
		Assert.assertEquals(sActualResult.toUpperCase().trim(), sTestCaseExpectedResult.toUpperCase().trim());
// ResultComparision();

	}
}

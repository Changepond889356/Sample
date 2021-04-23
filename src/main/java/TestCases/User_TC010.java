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

public class User_TC010 extends SetUp {
	@Test
	public void main() throws Exception {
		String sTestCaseID = "User_TC010";
		test = extent.createTest(sTestCaseID);
		getTestCaseExpectedResult(sTestCaseID);
		sScreenShotTCFolder = createfolder(sScreenShotFolder, sTestCaseID);
		boolean bResult = false;

		try {
			// Launch application
			TestActions.LaunchApplication();

			// Login as Global Admin
			// bResult = TestActions.Login_GlobalAdmin();
			bResult = TestActions.Login(sTestCaseID);
			if (bResult == true) {
				// customize columns
				//bResult = Users.customizeUsergrid(sTestCaseID);
				if (bResult == true) {
					// add user
					// required full name
					bResult = Users.addUser(sTestCaseID);
					// Search for user
					// bResult = Users.UserWebTable(27, sTestCaseID);
					if (bResult == true) {
						bResult = false;
						// Either phone or email required
						bResult = Users.addUser(sTestCaseID + "(2)");
						if (bResult == true) {
							// role required
							bResult = Users.addUser(sTestCaseID + "(3)");
							if (bResult == true) { 
								// carrier equired for Role Driver Full
								bResult = Users.addUser(sTestCaseID + "(4)");
								if (bResult == true) {

									// carrier equired for Role Driver limited
									bResult = Users.addUser(sTestCaseID + "(5)");
									if (bResult == true) {
										// carrier equired for Role carrier
										bResult = Users.addUser(sTestCaseID + "(6)");
										if (bResult == true) {

											sActualResult = "Invitation not sent";
										}

									}

								}
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

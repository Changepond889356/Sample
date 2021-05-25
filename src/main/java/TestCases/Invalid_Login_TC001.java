package TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import AppModules.Deals;
import AppModules.Share;
import AppModules.TestActions;
import Utils.*;

public class Invalid_Login_TC001 extends SetUp {
	@Test
	public void main() throws Exception {
		String sTestCaseID = "Invalid_Login_TC001";
		test = extent.createTest(sTestCaseID);
		getTestCaseExpectedResult(sTestCaseID);
		sScreenShotTCFolder = createfolder(sScreenShotFolder, sTestCaseID);
		boolean bResult = false;

		try {
			// Launch application
			TestActions.LaunchApplication();

			// check if unable to login
			bResult = TestActions.Login(sTestCaseID);
			if (bResult == true) {
				// check if unable to login
				bResult = TestActions.Login(sTestCaseID+"(2)");
				if (bResult == true) {
					// check if unable to login
					bResult = TestActions.Login(sTestCaseID+"(3)");
					if (bResult == true) {
						// check if unable to login
						bResult = TestActions.Login(sTestCaseID+"(4)");
						if (bResult == true) {
							sActualResult="Unable to login application";
							
						}
					}
				}
			}

		} catch (Exception error) {
			sActualResult = error.getMessage();
		}

		TestActions.CloseApplication();
		Assert.assertEquals(sActualResult.toUpperCase().trim(), sTestCaseExpectedResult.toUpperCase().trim());
// ResultComparision();

	}
}

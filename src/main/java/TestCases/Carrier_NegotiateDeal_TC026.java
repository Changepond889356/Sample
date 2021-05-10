package TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import AppModules.Deals;
import AppModules.Share;
import AppModules.TestActions;
import Utils.*;

public class Carrier_NegotiateDeal_TC026 extends SetUp {
	@Test
	public void main() throws Exception {
		String sTestCaseID = "Carrier_NegotiateDeal_TC026";
		test = extent.createTest(sTestCaseID);
		getTestCaseExpectedResult(sTestCaseID);
		sScreenShotTCFolder = createfolder(sScreenShotFolder, sTestCaseID);
		boolean bResult = false;

		try {
			// Launch application
			TestActions.LaunchApplication();

			// Login as Global Admin
			bResult = true;
			if (bResult == true) {
				bResult = TestActions.Login("Carrier_NegotiateDeal_TC026(2)");
				bResult = Deals.DealWidget("Carrier_NegotiateDeal_TC026(3)");
				if (bResult == true) {
					bResult = Deals.MuteDeal(sTestCaseID + "(2)");
					if (bResult == false) {
						TestActions.LogOut();
						sActualResult = "Unable to negotiate deal";
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

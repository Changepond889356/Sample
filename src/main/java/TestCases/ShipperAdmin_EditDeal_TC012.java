package TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import AppModules.Deals;
import AppModules.Share;
import AppModules.TestActions;
import Utils.*;

public class ShipperAdmin_EditDeal_TC012 extends SetUp {
	@Test
	public void main() throws Exception {

		String sTestCaseID = "ShipperAdmin_EditDeal_TC012";
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
				// handle newly created deal widget
				bResult = Deals.DealWidget(sTestCaseID);
				// share to contact
				// bResult = Share.ShareDeal(sTestCaseID);
				if (bResult == true) {
					bResult = false;
					bResult = Deals.EditDeal(sTestCaseID);
					if (bResult == true) {
						bResult = false;
						bResult = Deals.DealWidget("ShipperAdmin_EditDeal_TC012(2)");
						if (bResult == true) {
							TestActions.LogOut();
							sActualResult = "Deal has been Edited";
						}
					}

				}

				// sActualResult="Deal shared successfully";
			}

		} catch (Exception error) {
			sActualResult = error.getMessage();
		}

		TestActions.CloseApplication();
		Assert.assertEquals(sActualResult.toUpperCase().trim(), sTestCaseExpectedResult.toUpperCase().trim());
// ResultComparision();

	}
}

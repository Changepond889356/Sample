package TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import AppModules.Deals;
import AppModules.Share;
import AppModules.TestActions;
import Utils.*;

public class ReShareDeal_TC003 extends SetUp {
	@Test
	public void main() throws Exception {

		String sTestCaseID = "ReShareDeal_TC003";
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
				bResult = Deals.addNewDeal(sTestCaseID);
				if (bResult == true) {
					// handle newly created deal widget
					Deals.DealWidget(sTestCaseID);
					// share to contact
					bResult = Share.ShareDeal(sTestCaseID);
					if (bResult == true) {
						bResult = false;
						// again share already shared deal
						bResult = Deals.DealWidget("ReShareDeal_TC003(2)");
						if (bResult == true) {
							// bResult = false;
							// bResult = Deals.EditDeal(sTestCaseID);
							if (bResult == true) {
								bResult = false;
								// Re share to contact
								bResult = Share.ShareDeal("ReShareDeal_TC003(2)");

								if (bResult == true) {
									Thread.sleep(5000);
									TestActions.LogOut();
									sActualResult = "Deal has been Reshared";
								}
							}

						}

					}

					// sActualResult="Deal shared successfully";
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

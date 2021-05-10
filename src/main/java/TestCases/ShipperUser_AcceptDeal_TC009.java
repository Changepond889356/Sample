package TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import AppModules.Deals;
import AppModules.Share;
import AppModules.TestActions;
import Utils.*;

public class ShipperUser_AcceptDeal_TC009 extends SetUp {
	@Test
	public void main() throws Exception {
		String sTestCaseID = "ShipperUser_AcceptDeal_TC009";
		test = extent.createTest(sTestCaseID);
		getTestCaseExpectedResult(sTestCaseID);
		sScreenShotTCFolder = createfolder(sScreenShotFolder, sTestCaseID);
		boolean bResult = false;

		try {
			// Launch application
			TestActions.LaunchApplication();
			bResult = true;
			if (bResult == true) {
				//bResult = Deals.addNewDeal(sTestCaseID);
				if (bResult == true) {
				
					if (bResult == true) {
						// login as carrier
						bResult = TestActions.Login("ShipperUser_AcceptDeal_TC009(2)");
						if (bResult == true) {
							bResult = false;
							bResult = Deals.DealWidget("ShipperUser_AcceptDeal_TC009(2)");
							if (bResult == true) {
								bResult = Deals.AcceptDeal(sTestCaseID);
								if (bResult == true) {
									bResult = false;
									TestActions.LogOut();
									bResult = TestActions.Login("ShipperUser_AcceptDeal_TC009(2)");
									bResult = Deals.DealWidget("ShipperUser_AcceptDeal_TC009(3)");
									if (bResult == true) {
										TestActions.LogOut();
										sActualResult = "Deal has been accepted";
									}

								}

							}
						}
						// check shared deal

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

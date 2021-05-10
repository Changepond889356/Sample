package TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import AppModules.Deals;
import AppModules.Share;
import AppModules.TestActions;
import Utils.*;

public class ShipperUser_DuplicateDeal_TC004 extends SetUp {
	@Test
	public void main() throws Exception {
		System.out.println("start execute");
		String sTestCaseID = "ShipperUser_DuplicateDeal_TC004";
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
				//bResult = Deals.addNewDeal(sTestCaseID);
				if (bResult == true) {
					// handle newly created deal widget
					//Deals.DealWidget(sTestCaseID);
					// share to contact
					//bResult = Share.ShareDeal(sTestCaseID);
					if (bResult == true) {
						bResult = false;
						//TestActions.LogOut();
						// login as carrier user
						bResult = TestActions.Login("ShipperUser_DuplicateDeal_TC004(2)");
						if (bResult == true) {
							bResult = false;
							// check shared deal
							bResult = Deals.DealWidget("ShipperUser_DuplicateDeal_TC004(2)");
							if (bResult == true) {
								bResult = false;
								bResult = Deals.setDuplicate(sTestCaseID);
								if (bResult == true) {// check duplicate deal
									Thread.sleep(5000);
									TestActions.LogOut();
									TestActions.Login("ShipperUser_DuplicateDeal_TC004(2)");
									bResult = Deals.DealWidget("ShipperUser_DuplicateDeal_TC004(3)");
									if(bResult==true)
									{
										sActualResult = "Deal has been duplicated";
										
									}
									
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

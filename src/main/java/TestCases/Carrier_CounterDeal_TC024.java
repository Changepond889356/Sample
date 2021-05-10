package TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import AppModules.Deals;
import AppModules.Share;
import AppModules.TestActions;
import Utils.*;

public class Carrier_CounterDeal_TC024 extends SetUp {
	@Test
	public void main() throws Exception {
		String sTestCaseID = "Carrier_CounterDeal_TC024";
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
						// check shared deal
						TestActions.LogOut();
						bResult=TestActions.Login(sTestCaseID+"(2)");
						if(bResult==true)
						{
							bResult = Deals.DealWidget("Carrier_CounterDeal_TC024(2)");
							if(bResult==true)
							{
								TestActions.LogOut();
								bResult=TestActions.Login(sTestCaseID);
								if(bResult==true)
								{
									bResult = Deals.DealWidget("Carrier_CounterDeal_TC024(3)");
									if (bResult == true) {
										bResult=Deals.counterDeal(sTestCaseID);
										if(bResult==true)
										{
											TestActions.LogOut();
											sActualResult = "Deal has been countered";
										}
										
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

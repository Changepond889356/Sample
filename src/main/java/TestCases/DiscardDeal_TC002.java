package TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import AppModules.Deals;
import AppModules.TestActions;
import Utils.*;

public class DiscardDeal_TC002 extends SetUp {
	@Test
	public void main() throws Exception {
		String sTestCaseID = "DiscardDeal_TC002";
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
				bResult=false;
				bResult = Deals.addNewDeal(sTestCaseID);
				if(bResult==true)
				{
					bResult=false;
					bResult = Deals.DealWidget(sTestCaseID);
					if(bResult==true)
					{
						bResult = Deals.DealWidget("DiscardDeal_TC002(2)");
						if(bResult==false)
						{
							sActualResult="Deal has been discarded";
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

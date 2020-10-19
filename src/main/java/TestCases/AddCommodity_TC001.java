package TestCases;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import AppModules.Commodities;
import AppModules.Shippers;
import AppModules.TestActions;
import AppModules.Users;
import Utils.GenericSkins;
import Utils.SetUp;
import Utils.TestDataImport;
//import bsh.This;

public class AddCommodity_TC001 extends SetUp {
	@Test
	public void main() throws Exception {
		String sTestCaseID = "AddCommodity_TC001";
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
				bResult = Commodities.addCommodities(sTestCaseID);
				if (bResult == true) {
					bResult = Commodities.customizeCommoditygrid(sTestCaseID);
					if (bResult == true) {
						bResult = Commodities.CommoditiesWebTable(1, sTestCaseID);
						if (bResult == true) {
							//sActualResult = "Commodity added successfully";
							bResult = Commodities.CommoditiesWebTable(2, sTestCaseID);
							if (bResult == true) {
								//sActualResult = "Commodity added successfully";
								bResult = Commodities.CommoditiesWebTable(3, sTestCaseID);
								if (bResult == true) {
									sActualResult = "Commodity added and deleted successfully";
								}
							}
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

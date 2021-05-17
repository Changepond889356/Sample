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

public class Invalid_AddCommodity_TC001 extends SetUp {
	@Test
	public void main() throws Exception {
		String sTestCaseID = "Invalid_AddCommodity_TC001";
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
				//add commodity with empty details
				bResult = Commodities.addCommodities(sTestCaseID);
				if (bResult == true) {
					//add commodity with only name
					bResult = Commodities.addCommodities(sTestCaseID+"(2)");
					if (bResult == true) {
						//add commodity with only name and upper limit
						bResult = Commodities.addCommodities(sTestCaseID+"(3)");
						if (bResult == true) {
							//add commodity with only name and lower limit
							bResult = Commodities.addCommodities(sTestCaseID+"(4)");
							//add commodity with name and upper limit<lower limit
							bResult = Commodities.addCommodities(sTestCaseID+"(5)");
							if (bResult == true) {
								//add commodity with out name
								bResult = Commodities.addCommodities(sTestCaseID+"(6)");
								if (bResult == true) {
									sActualResult = "Error messages displayed correcly";
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

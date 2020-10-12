package TestCases;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import AppModules.Loads;
import AppModules.Shippers;
import AppModules.TestActions;
import AppModules.Users;
import Utils.GenericSkins;
import Utils.SetUp;
import Utils.TestDataImport;
//import bsh.This;

public class EditShippers_TC002 extends SetUp {
	@Test
	public void main() throws Exception {
		String sTestCaseID = "EditShippers_TC002";
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
				// customize columns
				bResult = Shippers.customizeShippergrid(sTestCaseID);
				if (bResult == true) {
					bResult = Shippers.addShipper(sTestCaseID);
					if (bResult == true) {
						bResult = Shippers.ShipperWebTable(4, sTestCaseID);
						if (bResult == true) {
							bResult = Loads.customizeAGgrid(sTestCaseID);
							if (bResult == true) {
								bResult = Loads.addNewLoad(sTestCaseID);
								if (bResult == true) {
									bResult = Loads.LoadsWebTable(11, sTestCaseID);
									if (bResult == true) {

										sActualResult = "Shipper edited successfully";
									}
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

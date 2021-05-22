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

public class InActivateShippers_CarrierUser_TC010 extends SetUp {
	@Test
	public void main() throws Exception {
		String sTestCaseID = "InActivateShippers_CarrierUser_TC010";
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
					//add active shipper
					bResult = Shippers.addShipper(sTestCaseID);
					if (bResult == true) {
						//view shipper
						bResult = Shippers.ShipperWebTable(23, sTestCaseID);
						if (bResult == true) {
							//inactive shipper
							bResult = Shippers.ShipperWebTable(24, sTestCaseID);
							if (bResult == true) {
								//

								bResult=TestActions.LogOut();
								bResult=TestActions.Login_GlobalAdmin();
								bResult = Loads.customizeAGgrid(sTestCaseID);
								if (bResult == true) {
									//add load using inactive shipper
									bResult = Loads.addNewLoad(sTestCaseID);
									if (bResult == true) {
										//check if load not created with inactive shipper
										bResult = Loads.LoadsWebTable(22, sTestCaseID);
										if (bResult == false) {
											sActualResult="Shipper inactivated successfully";
											TestActions.LogOut();
										}
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

		aHeaderNumbers = null;
		aHeaderNames = null;
		aHeaderNumbers = new ArrayList();
		aHeaderNames = new ArrayList();
		TestActions.CloseApplication();
		Assert.assertEquals(sActualResult.toUpperCase().trim(), sTestCaseExpectedResult.toUpperCase().trim());
		// ResultComparision();

	}
}

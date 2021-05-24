package TestCases;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import AppModules.Deals;
import AppModules.Loads;
import AppModules.ShipperContact;
import AppModules.TestActions;
import Utils.*;

public class GlobalAdmin_activeShipperContacts_TC003 extends SetUp {
	@Test
	public void main() throws Exception {

		String sActTestCaseID = "GlobalAdmin_activeShipperContacts_TC003";
		test = extent.createTest(sActTestCaseID);
		getTestCaseExpectedResult(sActTestCaseID);
		sScreenShotTCFolder = createfolder(sScreenShotFolder, sActTestCaseID);
		boolean bResult = false;

		try {
			// Launch application
			TestActions.LaunchApplication();
			bResult = TestActions.Login(sActTestCaseID);

			if (bResult) {
				ShipperContact.SelectShipperContactMenu();
				// bResult = ShipperContact.AddShipperContact(sActTestCaseID);
				// bResult = TestActions.LogOut();
				// bResult = true;
				if (bResult) {
					bResult = false;
					bResult = ShipperContact.customizeGrid(sActTestCaseID);
					if (bResult) {
						//view inactive shipper contact
						bResult = ShipperContact.LoadWebTable(8, sActTestCaseID);
						if (bResult == true) {
							bResult = false;
							//activate shipper
							bResult = ShipperContact.LoadWebTable(9, sActTestCaseID);
							if (bResult == true) {

								bResult = false;
								bResult = Loads.customizeAGgrid(sActTestCaseID);
								if (bResult == true) {
									bResult = false;
									// Add new Load
									bResult = Loads.addNewLoad(sActTestCaseID);
									
									if (bResult == true) {
										bResult = false;
										bResult = Loads.LoadsWebTable(27, sActTestCaseID);
										
										if (bResult == true) {
											sActualResult = "Shipper contact activated successfully";
										}

									}
								}

							}
						}
						if (bResult == true) {
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
	}
}

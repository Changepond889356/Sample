package TestCases;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import AppModules.Deals;
import AppModules.Loads;
import AppModules.ShipperContact;
import AppModules.TestActions;
import Utils.*;

public class GlobalAdmin_InactiveShipperContacts_TC002 extends SetUp {
	@Test
	public void main() throws Exception {

		String sActTestCaseID = "GlobalAdmin_InactiveShipperContacts_TC002";
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
						//view active shipper contact
						bResult = ShipperContact.LoadWebTable(6, sActTestCaseID);
						if (bResult == true) {
							bResult = false;
							bResult = ShipperContact.LoadWebTable(7, sActTestCaseID);
							if (bResult == true) {

								bResult = false;
								bResult = Loads.customizeAGgrid(sActTestCaseID);
								if (bResult == true) {
									bResult = false;
									// Add new Load
									bResult = Loads.addNewLoad(sActTestCaseID);
									
									if (bResult == true) {
										bResult = false;
										bResult = Loads.LoadsWebTable(26, sActTestCaseID);
										if(sActualResult.equalsIgnoreCase("Record not found"))
										{
											bResult=true;
										}
										if (bResult == true) {
											sActualResult = "Shipper contact inactivated successfully";
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

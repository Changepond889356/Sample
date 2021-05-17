package TestCases;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import AppModules.Deals;
import AppModules.ShipperContact;
import AppModules.TestActions;
import Utils.*;

public class Invalid_ShipperContacts_TC001 extends SetUp {
	@Test
	public void main() throws Exception {

		String sActTestCaseID = "Invalid_ShipperContacts_TC001";
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
				// check invalid details
				bResult = ShipperContact.AddShipperContact(sActTestCaseID);
				if (bResult == true) {
					sActualResult = "Error messages displayed correctly";
				}
				// bResult = TestActions.LogOut();
				// bResult = true;
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

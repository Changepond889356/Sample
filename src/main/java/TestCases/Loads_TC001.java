package TestCases;

import org.testng.annotations.Test;
import AppModules.Loads;
import AppModules.TestActions;
import AppModules.Users;
import PageObjects.LoadsPage;
import Utils.TestDataImport;
//import junit.framework.Assert;
import Utils.GenericSkins;
import Utils.SetUp;

public class Loads_TC001 extends SetUp {
	@SuppressWarnings("deprecation")
	@Test
	public void main() {
		String sActTestCaseID = "Loads_TC001";
		test = extent.createTest(sActTestCaseID);
		boolean bResult = false;
		// String expected = "Record not found";
		//String sActTestCaseID = "Loads_TC001";
		try {
			String sFileName = "Loads.xlsx";
			String sSheetName = "AGgrid";

			// Launch application
			TestActions.LaunchApplication();

			// Login as Global Admin
			bResult = TestActions.Login_GlobalAdmin();
			if (bResult == true) {

				// Add new Load
				bResult = Loads.addNewLoad(sActTestCaseID);
				if (bResult == true) {
					//customize grid
					Loads.customizeAGgrid(sActTestCaseID);

					// Search for record in AG grid
					// sTestStepData = "Current Date;CP Shipper;NA;Changepond
					// T;Open;NA;NA;0.25;Bushels;Corn";
					Loads.LoadsWebTable(1, sActTestCaseID);
					// Edit record
					Loads.editLoad(sActTestCaseID);
					// Search for edited record in AG grid
					Loads.LoadsWebTable(2, sActTestCaseID);
					// click on delete icon
					LoadsPage.eDelete().click();
					Thread.sleep(5000);
					//bResult = Loads.LoadsWebTable(3, sActTestCaseID);

				}
			}

		} catch (Exception error) {
			sActualResult=error.getMessage();
			bResult = false;

		}
		// close application
		TestActions.CloseApplication();
		// set testcase status
		// Assert.assertEquals(sTestCaseExpectedResult, sActualResult);

	}
}

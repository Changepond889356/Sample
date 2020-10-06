package TestCases;

import java.io.IOException;
import java.util.ArrayList;

import org.testng.Assert;
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
	public void main() throws Exception {
		String sActTestCaseID = "Loads_TC001";
		test = extent.createTest(sActTestCaseID);
		getTestCaseExpectedResult(sActTestCaseID);
		// craete folder wiht TestCaseID inside Screenshot folder
		// Create a folder 'Screenshots' inside current testresults folder
		sScreenShotTCFolder = createfolder(sScreenShotFolder, sActTestCaseID);

		boolean bResult = false;
		// String expected = "Record not found";
		// String sActTestCaseID = "Loads_TC001";
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
					// customize grid
					Loads.customizeAGgrid(sActTestCaseID);

					// Search for record in AG grid
					// sTestStepData = "Current Date;CP Shipper;NA;Changepond
					// T;Open;NA;NA;0.25;Bushels;Corn";
					Loads.LoadsWebTable(1, sActTestCaseID);
					// Edit record
					Loads.editLoad(sActTestCaseID);
					// Search for edited record in AG grid
					bResult = Loads.LoadsWebTable(2, sActTestCaseID);
					// click on delete icon
					if (bResult == true) {
						sActualResult = "Load added successfully";
						LoadsPage.eDelete().click();
						Thread.sleep(5000);

					}
					// bResult = Loads.LoadsWebTable(3, sActTestCaseID);

				}
			}

		} catch (Exception error) {
			sActualResult = error.getMessage();
			bResult = false;

		}
		aHeaderNumbers = null;
		aHeaderNames = null;
		aHeaderNumbers = new ArrayList();
		aHeaderNames = new ArrayList();
		// close application
		TestActions.CloseApplication();
		// set testcase status
		Assert.assertEquals(sActualResult.toUpperCase().trim(), sTestCaseExpectedResult.toUpperCase().trim());

	}
}

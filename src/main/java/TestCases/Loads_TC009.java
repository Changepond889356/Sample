package TestCases;

import java.io.IOException;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import AppModules.Loads;
import AppModules.TestActions;
import Utils.GenericSkins;
import Utils.SetUp;

public class Loads_TC009 extends SetUp {
	
	@Test
	public void importFile() throws IOException {
		String sActTestCaseID = "Loads_TC009";
		test = extent.createTest(sActTestCaseID);
		getTestCaseExpectedResult(sActTestCaseID);
		sScreenShotTCFolder = createfolder(sScreenShotFolder, sActTestCaseID);
		GenericSkins.iTotalTestStepsFailed=0;
		boolean bResult = false;
		try {
			// Launch application
			TestActions.LaunchApplication();

			// Login 
			bResult = TestActions.Login(sActTestCaseID);
			
			if (bResult == true) {
				bResult = Loads.customizeAGgrid(sActTestCaseID);
			}

			if (bResult == true) {
				bResult = false; 
				// Add new Load
				bResult = Loads.addNewLoad(sActTestCaseID);
				if (bResult == true) {
					bResult = false;
					
					bResult = Loads.LoadsWebTableForDispatch(15, sActTestCaseID);
					
					if(bResult == true) {
						bResult = Loads.ImportFile(sActTestCaseID, "ImportSheetShipperUser");
						
						sActualResult="Import Done Successfully";
					}
					bResult = TestActions.LogOut();
				}
			}

		} catch (Exception error) {
			bResult = false;

		}
		aHeaderNumbers = null;
		aHeaderNames = null;
		aHeaderNumbers = new ArrayList();
		aHeaderNames = new ArrayList();
		TestActions.CloseApplication();
		Assert.assertEquals(sActualResult.toUpperCase().trim(), sTestCaseExpectedResult.toUpperCase().trim());
	
	}
}

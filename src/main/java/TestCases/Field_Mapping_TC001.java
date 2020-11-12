package TestCases;

import java.io.IOException;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import AppModules.Loads;
import AppModules.TestActions;
import PageObjects.LoadsPage;
import Utils.GenericSkins;
import Utils.SetUp;

public class Field_Mapping_TC001 extends SetUp {

	@Test
	public void fieldMapping() throws Exception {

			String sTestCaseID = "Field_Mapping_TC001";
			test = extent.createTest(sTestCaseID );
			getTestCaseExpectedResult(sTestCaseID);
			sScreenShotTCFolder = createfolder(sScreenShotFolder, sTestCaseID);
			GenericSkins.iTotalTestStepsFailed=0;
			boolean bResult = false;
			try {
				
				// Launch application
				TestActions.LaunchApplication();

				bResult = TestActions.Login(sTestCaseID);			
				if (bResult == true) {
					// Add new Load
					bResult = Loads.addNewLoadWithFieldMapping(sTestCaseID);				
				}
				if (bResult == true) {
					Loads.customizeAGgrid(sTestCaseID);			
					bResult = Loads.LoadsWebTableForDispatch(18, sTestCaseID);
					
				}
				if (bResult == true) {
					bResult=Loads.editLoad(sTestCaseID);
				}
				if (bResult == true) {
					bResult = Loads.LoadsWebTableForDispatch(18, sTestCaseID);
					LoadsPage.eDelete().click();
					Thread.sleep(5000);
					bResult = TestActions.LogOut();
					sActualResult = "Load Validated Successfully";
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

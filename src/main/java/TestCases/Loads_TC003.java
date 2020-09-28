package TestCases;

import org.testng.annotations.Test;

import AppModules.Loads;
import AppModules.TestActions;
import PageObjects.LoadsPage;
import Utils.SetUp;

public class Loads_TC003 extends SetUp {
	
	@Test
	public void scoularLoad_FullSubmit() {
		String sActTestCaseID = "Loads_TC003";
		test = extent.createTest(sActTestCaseID);
		
		boolean bResult = false;
		try {
			// Launch application
			TestActions.LaunchApplication();

			// Login as Global Admin
			bResult = TestActions.Login(sActTestCaseID);
			
			if (bResult == true) {
				//bResult = false;
				// Add new Load
				bResult = Loads.addNewLoad(sActTestCaseID);
				if (bResult == true) {
					bResult = false;
					
					bResult = Loads.LoadsNewRecord();
					
					if (bResult == true) {
						Loads.uploadOriginTicket("Origin Ticket");
						Loads.uploadDestTicket("Dest. Ticket");
						LoadsPage.eSave().click();
						bResult=Loads.editLoad(sActTestCaseID);
						Loads.LoadsNewRecord();
						LoadsPage.eSave().click();
						LoadsPage.Submit().click();
						Loads.GetInvoiceNumber();
						LoadsPage.SubmitLoad().click();
						//bResult = Loads.LoadsWebTable(5, sActTestCaseID);	
						
					}
					//LoadsPage.eDelete().click();				
				}
			}

		} catch (Exception error) {
			bResult = false;

		}
		driver.quit();
	}

}

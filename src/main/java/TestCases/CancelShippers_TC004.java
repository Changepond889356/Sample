package TestCases;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import AppModules.Shippers;
import AppModules.TestActions;
import AppModules.Users;
import Utils.GenericSkins;
import Utils.SetUp;
import Utils.TestDataImport;
//import bsh.This;

public class CancelShippers_TC004 extends SetUp {
	@Test
	public void main() throws Exception {
		String sTestCaseID = "CancelShippers_TC004";
		test = extent.createTest(sTestCaseID);
		getTestCaseExpectedResult(sTestCaseID);
		sScreenShotTCFolder = createfolder(sScreenShotFolder, sTestCaseID);
		boolean bResult = false;

		try {
			// Launch application 
			TestActions.LaunchApplication();

			// Login as Global Admin
			bResult = TestActions.Login_GlobalAdmin();
			if (bResult == true) {
				// customize columns
				bResult = Shippers.customizeShippergrid(sTestCaseID);
				if(bResult==true)
				{
					//cancel adding shipper
					bResult=Shippers.addShipper(sTestCaseID);
					if(bResult==true)
					{
						//check if new shipper not added
						bResult=Shippers.ShipperWebTable(10, sTestCaseID);
						if(bResult==true)
						{
							sActualResult = "Add shipper cancelled";
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

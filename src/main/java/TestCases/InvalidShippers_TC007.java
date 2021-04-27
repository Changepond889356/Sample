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

public class InvalidShippers_TC007 extends SetUp {
	@Test
	public void main() throws Exception {
		String sTestCaseID = "InvalidShippers_TC007";
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
					//add shipper with empty details
					bResult=Shippers.addShipper(sTestCaseID);
					if(bResult==true)
					{
						bResult=false;
						//empty carrier
						bResult=Shippers.addShipper(sTestCaseID+"(2)");
						if(bResult==true)
						{
							//empty shipper name
							bResult=Shippers.addShipper(sTestCaseID+"(3)");
							if(bResult==true)
							{
								sActualResult="Error messages displayed correctly";
								TestActions.LogOut();
							}
							
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

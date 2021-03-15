package TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import AppModules.Deals;
import AppModules.Share;
import AppModules.TestActions;
import Utils.*;

public class DuplicateDeal_TC002 extends SetUp {
	@Test
	public void main() throws Exception {
		System.out.println("start execute");
		String sTestCaseID = "DuplicateDeal_TC002";
		test = extent.createTest(sTestCaseID);
		getTestCaseExpectedResult(sTestCaseID);
		sScreenShotTCFolder = createfolder(sScreenShotFolder, sTestCaseID);
		boolean bResult = false;

		try {
			// Launch application
			TestActions.LaunchApplication();

			// Login as Global Admin
			bResult = TestActions.Login(sTestCaseID);
			if (bResult == true) {
				bResult = Deals.addNewDeal(sTestCaseID);
				if (bResult == true) {
					// Duplicate deal
					bResult=Deals.DealWidget(sTestCaseID);
					
                     if (bResult == true) {
                    	 bResult=false;
                    	 bResult = Deals.setDuplicate(sTestCaseID);
                    	 if(bResult==true)
                    	 {
                    		 bResult=false;
                    		 Thread.sleep(5000);
                    		 //Logout
                    		 TestActions.LogOut();
                    		 //Login
                    		 bResult = TestActions.Login(sTestCaseID);
                    		 if(bResult==true)
                    		 {
                    			 bResult=false;
                    			 bResult = Deals.DealWidget("DuplicateDeal_TC002(2)");
                        		 if(bResult==true)
                        		 {
                        			 sActualResult="Deal has been duplicated";
                        			 TestActions.LogOut();
                        		 }
                    		 }
                    		
                    		 
                    		 
                    	 }
                    	 
                     }

					// sActualResult="Deal shared successfully";
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

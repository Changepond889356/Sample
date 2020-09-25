package TestCases;

import org.testng.annotations.Test;
import AppModules.TestActions;
import AppModules.Users;
import Utils.GenericSkins;
import Utils.SetUp;
import Utils.TestDataImport;
//import bsh.This;

public class User_TC001 extends SetUp {
	@Test
	public void main() throws Exception {
		
		test = extent.createTest(" User Test ");
		String sTestCaseID = "User_TC001";
		boolean bResult = false;

		try {
			// Launch application
			TestActions.LaunchApplication();

			// Login as Global Admin
			bResult = TestActions.Login_GlobalAdmin();
			if (bResult == true) {
				// add user
				bResult = Users.addUser(sTestCaseID);
				// Search for user
				bResult = Users.UserWebTable(1,sTestCaseID);
				//Resend invite
				bResult = Users.UserWebTable(2,sTestCaseID);
				
				//cancel invite
				bResult = Users.UserWebTable(3,sTestCaseID);
				//view canceled invite
				bResult = Users.UserWebTable(4,sTestCaseID);
				
				if(bResult==true)
				{
					sActualResult = "Invitation sent successfully";
				}

			}

		} catch (Exception error) {
			sActualResult = error.getMessage();
		}
		driver.close();
		driver.quit();
		//ResultComparision();

	}
}

package TestCases;

import org.testng.annotations.Test;

import AppModules.TestActions;
import AppModules.Users;
import Utils.GenericSkins;
import Utils.SetUp;
public class User_TC003 extends SetUp {
	@Test
	public void main() throws Exception {
		test = extent.createTest(" User_TC003");
		String sTestCaseID = "User_TC003";
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
				bResult = Users.UserWebTable(8,sTestCaseID);
				System.out.println("AUTPAth:"+sAUTPath);
				//cancel invite
				bResult = Users.UserWebTable(9,sTestCaseID);
				//view canceled invite
				bResult = Users.UserWebTable(10,sTestCaseID);
				
				if(bResult==true)
				{
					sActualResult = "Invitation Accepted successfully";
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

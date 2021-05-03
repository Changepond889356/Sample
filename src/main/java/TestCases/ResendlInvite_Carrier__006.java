package TestCases;

import java.io.IOException;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import AppModules.Carrier;
import AppModules.TestActions;
import AppModules.Users;
import PageObjects.LoadsPage;
import Utils.GenericSkins;
import Utils.SetUp;
import Utils.TestDataImport;

public class ResendlInvite_Carrier__006 extends SetUp {

	@Test(dataProvider = "getData")
	public void carrierScenario(String sAcccountType, String sUserName, String sPassword) throws IOException {

		String sActTestCaseID = "ResendlInvite_Carrier__006";
		sGenricAccountType = sAcccountType;
		test = extent.createTest(sActTestCaseID + " - " + sAcccountType);
		getTestCaseExpectedResult(sActTestCaseID);
		sScreenShotTCFolder = createfolder(sScreenShotFolder, sActTestCaseID);

		boolean bResult = false;
		try {
			// Launch application
			TestActions.LaunchApplication();

			bResult = true;
			if (bResult == true) { // Resend Request
				if (bResult == true) {
					bResult = false;
					bResult = TestActions.Login(sUserName, sPassword);
					Carrier.SelectCarrierMenu();
					System.out.println("Request Resend");
					bResult = Carrier.AddCarrier(sActTestCaseID, "NA");
					
					bResult = TestActions.LogOut();
				}

				// Login as Carrier to verify request
				if (bResult == true) {
					bResult = TestActions.Login(sActTestCaseID);
					System.out.println("open settings");
					Carrier.OpenSettings();
					System.out.println("verify request");
					bResult = Carrier.VerifyRequest(sActTestCaseID, 7);
					System.out.println("verify request:" + bResult);
				}

				System.out.println("bResult final:" + bResult);
				if (bResult == true) {
					sActualResult = "Resend Invitation Successfully";
				}

			}

		} catch (Exception e) {

		}

		aHeaderNumbers = null;
		aHeaderNames = null;
		aHeaderNumbers = new ArrayList();
		aHeaderNames = new ArrayList();
		TestActions.CloseApplication();
		Assert.assertEquals(sActualResult.toUpperCase().trim(), sTestCaseExpectedResult.toUpperCase().trim());
	}

	@DataProvider
	public Object[][] getData() throws Exception {
		Object[][] data = TestDataImport.readExcel(sTestDataPath, "Login.xlsx", "MultiLogin2");
		return data;

	}
}

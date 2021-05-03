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

public class Verifty_CarrierRequest__004 extends SetUp {

	@Test(dataProvider = "getData")
	public void carrierScenario(String sAcccountType, String sUserName, String sPassword) throws IOException {
		sGenricAccountType=sAcccountType;
		String sActTestCaseID = "Verifty_CarrierRequest__004";
		test = extent.createTest(sActTestCaseID + " - " + sAcccountType);
		getTestCaseExpectedResult(sActTestCaseID);
		sScreenShotTCFolder = createfolder(sScreenShotFolder, sActTestCaseID);

		boolean bResult = false;
		try {
			// Launch application
			TestActions.LaunchApplication();

			// Login
			// bResult = true;
			bResult = TestActions.Login(sUserName, sPassword);

			Carrier.SelectCarrierMenu();
			bResult = Carrier.AddCarrier(sActTestCaseID, "Current Date");
			bResult = TestActions.LogOut();
			if (bResult == true) {
				System.out.println("Login as global admin");
				bResult = TestActions.Login_GlobalAdmin();
				Carrier.SelectCarrierMenu();
				System.out.println("customize carrier grid as global admin");
				bResult = Carrier.CustomiseGrid(sActTestCaseID);

				if (bResult == true) {
					Carrier.LoadWebTable(sActTestCaseID, 5);
					if (!sAcccountType.equalsIgnoreCase("Global Admin")) {
						bResult = Carrier.AcceptCarrier();
						System.out.println("accept carrier");
					}
					bResult = TestActions.LogOut();
				}

				if (bResult == true) {
					bResult = TestActions.Login(sUserName, sPassword);
					Carrier.SelectCarrierMenu();
					System.out.println("add carrier existing");
					bResult = Carrier.AddCarrier(sActTestCaseID, "NA");
					bResult = TestActions.LogOut();
				}

				// Login as Carrier to verify Request
				if (bResult == true) {
					bResult = TestActions.Login(sActTestCaseID);
					System.out.println("open settings");
					Carrier.OpenSettings();
					System.out.println("verify request");
					bResult = Carrier.VerifyRequest(sActTestCaseID, 5);
					System.out.println("verify request:"+bResult);
				}

				if (bResult == true) {
					sActualResult = "Request verified Successfully";
					System.out.println("logout");
					TestActions.LogOut();
				}

			}

		} catch (Exception e) {

		}

		aHeaderNumbers = null;
		aHeaderNames = null;
		aHeaderNumbers = new ArrayList();
		aHeaderNames = new ArrayList();
		TestActions.CloseApplication();
		System.out.println(sActualResult);
		System.out.println(sTestCaseExpectedResult);
		Assert.assertEquals(sActualResult.toUpperCase().trim(), sTestCaseExpectedResult.toUpperCase().trim());
	}

	@DataProvider
	public Object[][] getData() throws Exception {
		Object[][] data = TestDataImport.readExcel(sTestDataPath, "Login.xlsx", "MultiLogin2");
		return data;

	}
}

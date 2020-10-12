package TestCases;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import AppModules.Carrier;
import AppModules.TestActions;
import AppModules.Users;
import PageObjects.LoadsPage;
import Utils.GenericSkins;
import Utils.SetUp;
import Utils.TestDataImport;

public class Carrier_TC001 extends SetUp {
	
	@Test(dataProvider = "getData")
	public void carrierScenario(String sAcccountType, String sUserName, String sPassword) throws IOException {
		
		String sActTestCaseID = "Carrier_TC001";
		test = extent.createTest(sActTestCaseID + " - " + sAcccountType);
		getTestCaseExpectedResult(sActTestCaseID);
		sScreenShotTCFolder = createfolder(sScreenShotFolder, sActTestCaseID);
		GenericSkins.iTotalTestStepsFailed=0;
		boolean bResult = false;
		try {
			// Launch application
			TestActions.LaunchApplication();
			
			// Login 
			bResult = true;
			bResult = TestActions.Login(sUserName, sPassword);
			
			Carrier.SelectCarrierMenu();
			bResult = Carrier.AddCarrier(sActTestCaseID);
			bResult = TestActions.LogOut();
			if (bResult == true) {
				bResult = TestActions.Login_GlobalAdmin();
				Carrier.SelectCarrierMenu();
				bResult = Carrier.CustomiseGrid(sActTestCaseID);								
				
				if (bResult == true) {
					Carrier.LoadWebTable(sActTestCaseID, 1); 
					bResult = Carrier.AcceptCarrier(); 
					bResult = TestActions.LogOut();
				}
				
				if (bResult == true) {
					bResult = TestActions.Login(sUserName, sPassword);
					Carrier.SelectCarrierMenu();
					bResult = Carrier.AddCarrier(sActTestCaseID);
					bResult = TestActions.LogOut();
				}
				
				//Login as Carrier to verify Request
				if (bResult == true) {
					bResult = TestActions.Login(sActTestCaseID);
					Carrier.OpenSettings();
					bResult = Carrier.VerifyRequest(sActTestCaseID, 1);
					bResult = TestActions.LogOut();
				}
				
				//Cancel Req
				if (bResult == true) {
					bResult = TestActions.Login(sUserName, sPassword);
					Carrier.SelectCarrierMenu();
					bResult = Carrier.CustomiseGrid(sActTestCaseID);
					Carrier.LoadWebTable(sActTestCaseID, 1);
					bResult = Carrier.CancelInvite(); 
					bResult = TestActions.LogOut();
				}
				
				//Login as Carrier to verify the cancel request
				if (bResult == true) {
					bResult = TestActions.Login(sActTestCaseID);
					Carrier.OpenSettings();
					bResult = Carrier.VerifyCancelledRequest(sActTestCaseID, 1); //write code for verify cancel req
					bResult = TestActions.LogOut();
				}
				
				//Resend Re
				if (bResult == true) {
					bResult = TestActions.Login(sUserName, sPassword);
					Carrier.SelectCarrierMenu();
					bResult = Carrier.AddCarrier(sActTestCaseID);
					System.out.println("Request Resend Successfully");
					bResult = TestActions.LogOut();
				}
				
				//Login as Carrier to accept request
				if (bResult == true) {
					bResult = TestActions.Login(sActTestCaseID);
					Carrier.OpenSettings();
					bResult = Carrier.AcceptRequest(sActTestCaseID, 1); 
					if(bResult) {
						bResult = Carrier.VerifyCarrierOnLoadScreen(sActTestCaseID, 1);
					}					
					bResult = TestActions.LogOut();
				}
				
				//Login as Global admin to delete user & carrier
				if (bResult == true) {
					bResult = TestActions.Login_GlobalAdmin();
					Carrier.SelectUserMenu();
					bResult = Users.customizeUsergrid(sActTestCaseID);
					if(bResult) {
						bResult = Users.UserWebTable(30, sActTestCaseID);
						Carrier.DeleteUser();						
						System.out.println("User Deleted Successfully");
					}
					
					Carrier.SelectCarrierMenu();
					Carrier.DeleteCarrier();
					System.out.println("Carrier Deleted Successfully");
					bResult = TestActions.LogOut();
				}
				sActualResult = "Carrier Validated Successfully";
			}
			
		} catch(Exception e) {
			
		}
		
	}
	
	@DataProvider
	public Object[][] getData() throws Exception {
		Object[][] data = TestDataImport.readExcel(sTestDataPath,"Login.xlsx","MultiLogin");
		return data;
		
	}
}

package AppModules;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import PageObjects.*;
import Utils.EmailFunctions;
import Utils.GenericSkins;
import Utils.TestDataImport;

public class TestActions extends GenericSkins {

	// ______________________________Method to launch
	// application______________________________________________
	public static WebDriver LaunchApplication() throws Exception {
		try {
			System.out.println("sBrowserName:" + sBrowserName);
			switch (sBrowserName.toUpperCase()) {
			case "CHROME":

				/*
				 * System.setProperty("webdriver.chrome.driver", sBrowsserDriverPath +
				 * "chromedriver.exe"); ChromeOptions options = new ChromeOptions();
				 * options.addArguments("--disable-extensions"); Map<String, Object> prefs = new
				 * HashMap<String, Object>(); prefs.put("credentials_enable_service", false);
				 * prefs.put("profile.password_manager_enabled", false);
				 * options.setExperimentalOption("prefs", prefs); driver = new
				 * ChromeDriver(options); break;
				 */

				System.setProperty("webdriver.chrome.driver", sBrowsserDriverPath + "chromedriver.exe");
				HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
				chromePrefs.put("profile.default_content_settings.popups", 0);
				chromePrefs.put("download.prompt_for_download", "true");
				// chromePrefs.put("download.default_directory",
				// "C://Users//mohammadfaheem.s//Downloads//");
				chromePrefs.put("credentials_enable_service", false);
				chromePrefs.put("profile.password_manager_enabled", false);
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--disable-extensions");
				HashMap<String, Object> chromeOptionsMap = new HashMap<String, Object>();
				options.setExperimentalOption("prefs", chromePrefs);
				options.addArguments("--test-type");
				options.addArguments("--disable-extensions"); // to disable browser extension pop up

				DesiredCapabilities caps = DesiredCapabilities.chrome();
				caps.setCapability(ChromeOptions.CAPABILITY, chromeOptionsMap);
				caps.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				caps.setCapability(ChromeOptions.CAPABILITY, options);
				driver = new ChromeDriver(caps);
				break;

			case "IE":

				System.setProperty("webdriver.ie.driver", sBrowsserDriverPath + "IEDriverServer.exe");
				DesiredCapabilities cap = DesiredCapabilities.internetExplorer();
				cap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				cap.setCapability("ie.ensureCleanSession", true);
				cap.setJavascriptEnabled(true);
				driver = new InternetExplorerDriver(cap);
				break;

			case "FIREFOX":

				System.setProperty("webdriver.gecko.driver", sBrowsserDriverPath + "geckodriver.exe");
				FirefoxProfile profile = new FirefoxProfile();
				DesiredCapabilities capabilities  = DesiredCapabilities.firefox();
				profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/csv,application/xls,application/xlsx, application/vnd.openxmlformats-officedocument.wordprocessingml.document, application/vnd.ms-excel,application/x-msexcel,application/excel,application/x-excel, text/html, text/csv, text/plain, application/msword, application/octet-stream doc xlsx pdf txt");
	            profile.setPreference("browser.helperApps.neverAsk.openFile", "application/csv,application/xls,application/xlsx, application/vnd.openxmlformats-officedocument.wordprocessingml.document, application/vnd.ms-excel,application/x-msexcel,application/excel,application/x-excel, text/html, text/csv, text/plain, application/msword, application/octet-stream doc xlsx pdf txt");
	           	profile.setPreference("browser.download.manager.showWhenStarting", false );
	            profile.setPreference("browser.download.manager.focusWhenStarting", false);
	            profile.setPreference("browser.download.useDownloadDir", true);
	            profile.setPreference("browser.helperApps.alwaysAsk.force", false);
	            profile.setPreference("browser.download.manager.closeWhenDone", true);
	            profile.setPreference("browser.download.manager.showAlertOnComplete", false);
	            profile.setPreference("browser.download.manager.useWindow", false);
	            profile.setPreference("services.sync.prefs.sync.browser.download.manager.showWhenStarting", false);
	            profile.setPreference("pdfjs.disabled", true); 
	            capabilities.setCapability(FirefoxDriver.PROFILE, profile);
	            driver = new FirefoxDriver(capabilities);
				driver.manage().deleteAllCookies();
				break;
			
			case "EDGE":
				System.setProperty("webdriver.edge.driver", sBrowsserDriverPath + "msedgedriver.exe");
				driver = new EdgeDriver();
				break;
			}
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			System.out.println("Application URL;" + sAUTPath);
			driver.get(sAUTPath);
			sParentWindowHandle = driver.getWindowHandle();
			sActualResult = "Application launched";

		} catch (Exception error_message) {
			sActualResult = error_message.getMessage();
		}

		// ResultComparision();
		return driver;

	}

	// Method to login as global admin
	public static boolean Login_GlobalAdmin() {
		boolean bResult = false;
		try {
			LoginPage.eUserName().sendKeys(GlobalAdminUserName);
			Thread.sleep(3000);
			LoginPage.sSubmit().click();
			LoginPage.ePassword().sendKeys(GlobalAdminPassword);
			Thread.sleep(2000);
			LoginPage.eLoginButton().click();
			Thread.sleep(10000);
			Actions action = new Actions(driver);
			action.sendKeys(Keys.F5).build().perform();
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOf(LoadsPage.eMenuLoads()));

			bResult = true;
			if (bResult == true) {
				sActualResult = "Login successful";
			} else {
				sActualResult = "Login not successful";
			}
		} catch (Exception error) {
			bResult = false;
			sActualResult = error.getMessage();

		}
		// if()
		// ResultComparision();
		return bResult;

	}

	// Method to login as global admin
	public static boolean LogOut() {
		boolean bResult = false;
		try {
			Thread.sleep(2000);
			GenericSkins.WaitForElementVisibility(By.xpath("//div[@class='MuiToolbar-root MuiToolbar-regular MuiToolbar-gutters']//button"));
			GenericSkins.WaitForElementTobeClickable(By.xpath("//div[@class='MuiToolbar-root MuiToolbar-regular MuiToolbar-gutters']//button")); //button[@class='MuiButtonBase-root MuiButton-root MuiButton-text css-143qge7']
			driver.findElement(By.xpath("//div[@class='MuiToolbar-root MuiToolbar-regular MuiToolbar-gutters']//button")).click();   //.//span[@class='MuiButton-label']  //div[@data-cy='nav-menu']//button
			Thread.sleep(1000);
			driver.findElement(By.xpath("//ul//div[contains(text(),'Log Out')]")).click();  //.//li[text()='Log Out']   //li[@data-cy='menu-logout']
			System.out.println("Logout Happen");
			Thread.sleep(3000);
			Actions action = new Actions(driver);
			action.sendKeys(Keys.F5).build().perform();
			driver.navigate().refresh();
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOf(LoginPage.eUserName()));
			bResult = true;

		} catch (Exception error) {
			bResult = false;
			sActualResult = error.getMessage();

		}
		// ResultComparision();
		// if()
		return bResult;

	}

	// Method to login
	public static boolean Login(String sActualTestCaseID) throws Exception {
		boolean bResult = false;
		String sFileName = "Login.xlsx";
		String sSheetName = "Login";
		sTestStepID = "Login";
		// Copy Loads.xlsx file from test data folder to current log folder
		Copy_File(sTestDataPath + sFileName, sTestResultsPath);

		TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
		int iRowCnt = 0;
		iRowCnt = TestDataImport.GetRowCount(sSheetName);
		System.out.println("Number of rows:" + iRowCnt);
		String sOperation = "VIEW";
		for (int iRow = 1; iRow <= iRowCnt; iRow++) {

			TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
			String sTestCaseID = TestDataImport.GetCellData(sSheetName, 0, iRow);

			String sUserName = TestDataImport.GetCellData(sSheetName, 1, iRow);
			String sPassword = TestDataImport.GetCellData(sSheetName, 2, iRow);
			sExpectedResult = TestDataImport.GetCellData(sSheetName, 3, iRow);
			// sTestStepData =
			// sLoadDate+";"+sSHipper+";"+sSHipperContact+";"+sCarrier+";"+sStatus+";"+sOrigin+";"+sDestination+";"+sRate+";"+sRateUOM+";"+sCommodity+";";
			if (sTestCaseID.trim().equalsIgnoreCase(sActualTestCaseID)) {
				try {
					LoginPage.eUserName().sendKeys(sUserName);
					Thread.sleep(3000);
					LoginPage.sSubmit().click();
					LoginPage.ePassword().sendKeys(sPassword);
					Thread.sleep(2000);
					LoginPage.eLoginButton().click();
					Thread.sleep(10000);
					Actions action = new Actions(driver);
					action.sendKeys(Keys.F5).build().perform();
					WebDriverWait wait = new WebDriverWait(driver, 30);
					wait.until(ExpectedConditions
							.visibilityOfElementLocated(By.xpath(".//span[@class='MuiButton-label']")));
					try {
						driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
						driver.findElement(By.xpath(".//img[@class='css-1piz8fp']")).click();
					} catch (Exception error_child) {
						driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

					}
					bResult = true;

				} catch (Exception error) {
					bResult = false;
					sActualResult = error.getMessage();

				}
				if (bResult == true) {
					sActualResult = "Login successful";
				} else {
					sActualResult = "Login not successful";
				}
				ResultComparision();
				TestDataImport.setCellData(sSheetName, iRow, 4, sActualResult, "NA");
				TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
				TestDataImport.setCellData(sSheetName, iRow, 5, sTestStepStatus, "NA");

				break;
			}
		}
		if (bResult == true) {
			sActualResult = "Login successful";
		} else {
			sActualResult = "Login not successful";
		}
		return bResult;

	}

	// Method to login
	public static boolean resetPassword(String sActualTestCaseID) throws Exception {
		boolean bResult = false;
		String sFileName = "Login.xlsx";
		String sSheetName = "ResetPassword";
		sTestStepID = "resetPassword";

		// Copy Loads.xlsx file from test data folder to current log folder
		Copy_File(sTestDataPath + sFileName, sTestResultsPath);

		TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
		int iRowCnt = 0;
		iRowCnt = TestDataImport.GetRowCount(sSheetName);
		System.out.println("Number of rows:" + iRowCnt);
		String sOperation = "VIEW";
		for (int iRow = 1; iRow <= iRowCnt; iRow++) {

			TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
			String sTestCaseID = TestDataImport.GetCellData(sSheetName, 0, iRow);

			String sUserName = TestDataImport.GetCellData(sSheetName, 1, iRow);
			String sPassword = TestDataImport.GetCellData(sSheetName, 2, iRow);
			sExpectedResult = TestDataImport.GetCellData(sSheetName, 3, iRow);
			// sTestStepData =
			// sLoadDate+";"+sSHipper+";"+sSHipperContact+";"+sCarrier+";"+sStatus+";"+sOrigin+";"+sDestination+";"+sRate+";"+sRateUOM+";"+sCommodity+";";
			if (sTestCaseID.trim().equalsIgnoreCase(sActualTestCaseID)) {
				try {
					LoginPage.eUserName().sendKeys(sUserName);
					Thread.sleep(3000);
					LoginPage.sSubmit().click();
					LoginPage.eForgotPassword().click();
					Thread.sleep(40000);
					driver.close();
					HashMap<String, String> hm = EmailFunctions.getGmailDataReset(sUserName,
							"subject:Reset Password Notification");

					System.out.println(hm.get("subject"));
					System.out.println("=================");
					String TokenLink = hm.get("link");
					System.out.println("Token Link:" + TokenLink);
					sAUTPath = TokenLink;
					System.out.println("Reset password link:"+sAUTPath);
				    TestActions.LaunchApplication();
					System.out.println("open link");
					System.out.println("new password:"+sPassword);
					LoginPage.eNewPwd().sendKeys(sPassword);
					Thread.sleep(2000);
					LoginPage.eConfirmNewPwd().sendKeys(sPassword);
					Thread.sleep(4000);
					LoginPage.eResetPwd().click();
					WebDriverWait wait = new WebDriverWait(driver, 20);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//div[text()='Enter your email address or phone number to continue login.']")));;
					bResult = true;


				} catch (Exception error) {
					bResult = false;
					sActualResult = error.getMessage();

				}
				if (bResult == true) {
					sActualResult = "Password reset successfully";
				} else {
					sActualResult = "reset not successful";
				}
				ResultComparision();
				TestDataImport.setCellData(sSheetName, iRow, 4, sActualResult, "NA");
				TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
				TestDataImport.setCellData(sSheetName, iRow, 5, sTestStepStatus, "NA");

				break;
			}
		}
		if (bResult == true) {
			sActualResult = "Login successful";
		} else {
			sActualResult = "Login not successful";
		}
		return bResult;

	}

	// Method to login
	public static boolean Registration(String sActualTestCaseID) throws Exception {
		boolean bResult = false;
		String sFileName = "Login.xlsx";
		String sSheetName = "Register";
		sTestStepID = "Registration";

		// Copy Loads.xlsx file from test data folder to current log folder
		Copy_File(sTestDataPath + sFileName, sTestResultsPath);

		TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
		int iRowCnt = 0;
		iRowCnt = TestDataImport.GetRowCount(sSheetName);
		System.out.println("Number of rows:" + iRowCnt);
		String sOperation = "VIEW";
		for (int iRow = 1; iRow <= iRowCnt; iRow++) {

			TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
			String sTestCaseID = TestDataImport.GetCellData(sSheetName, 0, iRow);

			String sUserName = TestDataImport.GetCellData(sSheetName, 1, iRow);
			String sEmail = TestDataImport.GetCellData(sSheetName, 2, iRow);
			String sPassword = TestDataImport.GetCellData(sSheetName, 3, iRow);
			String sConfirmPassword = TestDataImport.GetCellData(sSheetName, 4, iRow);
			// String = TestDataImport.GetCellData(sSheetName, 2, iRow);

			sExpectedResult = TestDataImport.GetCellData(sSheetName, 5, iRow);
			// sTestStepData =
			// sLoadDate+";"+sSHipper+";"+sSHipperContact+";"+sCarrier+";"+sStatus+";"+sOrigin+";"+sDestination+";"+sRate+";"+sRateUOM+";"+sCommodity+";";
			if (sTestCaseID.trim().equalsIgnoreCase(sActualTestCaseID)) {
				try {

					RegisterPage.ePassword().sendKeys(sPassword);
					RegisterPage.eConfirmPassword().sendKeys(sConfirmPassword);
					Thread.sleep(2000);
					RegisterPage.eCreateAccount().click();
					WebDriverWait wait = new WebDriverWait(driver, 20);
					wait.until(ExpectedConditions.visibilityOf(LoginPage.eUserName()));
					bResult = true;

				} catch (Exception error) {
					bResult = false;
					sActualResult = error.getMessage();

				}
				if (bResult == true) {
					sActualResult = "Registered Successfully";
				} else {
					sActualResult = "Register not successful";
				}
				ResultComparision();
				TestDataImport.setCellData(sSheetName, iRow, 6, sActualResult, "NA");
				TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
				TestDataImport.setCellData(sSheetName, iRow, 7, sTestStepStatus, "NA");

				break;
			}
		}
		if (bResult == true) {
			sActualResult = "Registerd Successflly";
		} else {
			sActualResult = "Register not successful";
		}
		return bResult;

	}

	// Method to close application
	public static void CloseApplication() {
		try {
			driver.close();
			driver.quit();

		} catch (Exception er) {

		}
	}

	public static boolean Login_ShipperAdmin(String sActualTestCaseID) throws Exception {
		boolean bResult = false;
		String sFileName = "Login.xlsx";
		String sSheetName = "Shipper Admin";
		sTestStepID = "Login";
		// Copy Loads.xlsx file from test data folder to current log folder
		Copy_File(sTestDataPath + sFileName, sTestResultsPath);

		TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
		int iRowCnt = 0;
		iRowCnt = TestDataImport.GetRowCount(sSheetName);
		System.out.println("Number of rows:" + iRowCnt);
		String sOperation = "VIEW";
		for (int iRow = 1; iRow <= iRowCnt; iRow++) {

			TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
			String sTestCaseID = TestDataImport.GetCellData(sSheetName, 0, iRow);

			String sUserName = TestDataImport.GetCellData(sSheetName, 1, iRow);
			String sPassword = TestDataImport.GetCellData(sSheetName, 2, iRow);
			sExpectedResult = TestDataImport.GetCellData(sSheetName, 3, iRow);
			// sTestStepData =
			// sLoadDate+";"+sSHipper+";"+sSHipperContact+";"+sCarrier+";"+sStatus+";"+sOrigin+";"+sDestination+";"+sRate+";"+sRateUOM+";"+sCommodity+";";
			if (sTestCaseID.trim().equalsIgnoreCase(sActualTestCaseID)) {
				try {
					LoginPage.eUserName().sendKeys(sUserName);
					Thread.sleep(3000);
					LoginPage.sSubmit().click();
					LoginPage.ePassword().sendKeys(sPassword);
					Thread.sleep(2000);
					LoginPage.eLoginButton().click();
					Thread.sleep(10000);
					Actions action = new Actions(driver);
					action.sendKeys(Keys.F5).build().perform();
					WebDriverWait wait = new WebDriverWait(driver, 30);
					wait.until(ExpectedConditions
							.visibilityOfElementLocated(By.xpath(".//span[@class='MuiButton-label']")));
					try {
						driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
						driver.findElement(By.xpath(".//img[@class='css-1piz8fp']")).click();
					} catch (Exception error_child) {
						driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

					}
					bResult = true;

				} catch (Exception error) {
					bResult = false;
					sActualResult = error.getMessage();

				}
				if (bResult == true) {
					sActualResult = "Login successful";
				} else {
					sActualResult = "Login not successful";
				}
				ResultComparision();
				TestDataImport.setCellData(sSheetName, iRow, 4, sActualResult, "NA");
				TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
				TestDataImport.setCellData(sSheetName, iRow, 5, sTestStepStatus, "NA");

				break;
			}
		}
		if (bResult == true) {
			sActualResult = "Login successful";
		} else {
			sActualResult = "Login not successful";
		}
		return bResult;
	}

	public static boolean Login(String sUserName, String sPassword) throws InterruptedException {
		
		LoginPage.eUserName().sendKeys(sUserName);
		Thread.sleep(3000);
		LoginPage.sSubmit().click();
		LoginPage.ePassword().sendKeys(sPassword);
		Thread.sleep(2000);
		LoginPage.eLoginButton().click();
		Thread.sleep(10000);
		Actions action = new Actions(driver);
		action.sendKeys(Keys.F5).build().perform();
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath(".//span[@class='MuiButton-label']")));
		
		return true;
	}

}

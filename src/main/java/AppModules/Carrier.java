package AppModules;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import PageObjects.CarrierPage;
import PageObjects.LoadsPage;
import PageObjects.LoginPage;
import PageObjects.UserPage;
import Utils.GenericSkins;
import Utils.TestDataImport;

public class Carrier extends GenericSkins {

	public static void SelectCarrierMenu() throws InterruptedException {

		GenericSkins.WaitForElementTobeClickable(By.xpath("*//span[contains(text(),'Carriers')]"));
		CarrierPage.CarrierMenu().click();
		Thread.sleep(3000);
	}

	public static boolean AddCarrier(String sActTestCaseID, String sCarrierNameFlag) throws Exception {
		boolean bResult = false;
		String sFileName = "Carrier.xlsx";
		String sSheetName = "Carrier Details";
		sTestStepID = "add carrier";
		Copy_File(sTestDataPath + sFileName, sTestResultsPath);
		// String [] data =
		// TestDataImport.readExcel(sTestDataPath,sFileName,sSheetName,1,
		// sActTestCaseID);
		TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
		int iRowCnt = 0;
		iRowCnt = TestDataImport.GetRowCount(sSheetName);
		JavascriptExecutor js = (JavascriptExecutor) driver;

		DateTimeFormatter dateandtime = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
		LocalDateTime t1 = LocalDateTime.now();
		String sCurrentDateTime = t1.format(dateandtime);

		for (int iRow = 1; iRow <= iRowCnt; iRow++) {
			TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
			String sTestCaseID = TestDataImport.GetCellData(sSheetName, 0, iRow);
			String sCarrierName = TestDataImport.GetCellData(sSheetName, 1, iRow);
			String sMC = TestDataImport.GetCellData(sSheetName, 2, iRow);
			String sDot = TestDataImport.GetCellData(sSheetName, 3, iRow);
			String sCarrierRef = TestDataImport.GetCellData(sSheetName, 4, iRow);
			String sContactName = TestDataImport.GetCellData(sSheetName, 5, iRow);
			String sEmailID = TestDataImport.GetCellData(sSheetName, 6, iRow);
			sExpectedResult = TestDataImport.GetCellData(sSheetName, 7, iRow);
			if (sCarrierNameFlag.trim().equalsIgnoreCase("Current date")) {
				sCarrierName = sCarrierName + sCurrentDateTime.replace("/", "");
				sCarrierName = sCarrierName.replace(":", "");
				sCarrierName = sCarrierName.replace(" ", "");
				sMC = sCurrentDateTime.replace("/", "");

				sMC = sMC.replace(":", "");
				sMC = sMC.replace(" ", "");
				sDot = sMC;
				sGenericCarrierName = sCarrierName;

				sGenericCarrierMC = sMC;
				sGenericCarrierDot = sDot;
				switch (sGenricAccountType.toUpperCase()) {
				case "GLOBAL ADMIN":
					sGenericCarrierNameGA = sCarrierName;
					sGenericCarrierName = sGenericCarrierNameGA;
					sGenericCarrierMCGA = sMC;
					sGenericCarrierDotGA = sDot;
					break;
				case "SHIPPER ADMIN":
					sGenericCarrierNameSA = sCarrierName;
					sGenericCarrierName = sGenericCarrierNameSA;
					sGenericCarrierMCSA = sMC;
					sGenericCarrierDotSA = sDot;
					break;
				case "SHIPPER USER":
					sGenericCarrierNameSU = sCarrierName;
					sGenericCarrierName = sGenericCarrierNameSU;
					sGenericCarrierMCSU = sMC;
					sGenericCarrierDotSU = sDot;
					break;

				}
			} else if (sCarrierNameFlag.trim().equalsIgnoreCase("Null")) {
				sGenericCarrierName = "NA";
			}

			if (sTestCaseID.equalsIgnoreCase(sActTestCaseID)) {
				try {
					CarrierPage.addCarrierBtn().click();
					Thread.sleep(1000);
					if (!(sGenericCarrierName.equalsIgnoreCase("NA"))) {
						System.out.println("sGenricAccountType:" + sGenricAccountType);
						switch (sGenricAccountType.toUpperCase()) {
						case "GLOBAL ADMIN":
							System.out.println("search carrier:" + sGenericCarrierNameGA);
							CarrierPage.CarrierName().sendKeys(sGenericCarrierNameGA);
							sGenericCarrierName = sGenericCarrierNameGA;

							break;
						case "SHIPPER ADMIN":
							System.out.println("search carrier:" + sGenericCarrierNameSA);
							CarrierPage.CarrierName().sendKeys(sGenericCarrierNameSA);
							sGenericCarrierName = sGenericCarrierNameSA;

							break;
						case "SHIPPER USER":
							System.out.println("search carrier:" + sGenericCarrierNameSU);
							CarrierPage.CarrierName().sendKeys(sGenericCarrierNameSU);
							sGenericCarrierName = sGenericCarrierNameSU;

							break;

						}
					}

					CarrierPage.SearchBtn().click();
					Thread.sleep(1000);
					System.out.println("sGenericCarrierName:" + sGenericCarrierName);
					if (sGenericCarrierName.equalsIgnoreCase("NA")) {
						try {
							GenericSkins.WaitForElementVisibility(By.xpath("//*[text()='Your search has failed']"));
							sActualResult = "Your search has failed";
							bResult = true;
						} catch (Exception popuperror) {
							bResult = false;
							sActualResult = "Failed search not displayed";
						}
						break;
					}
					try {
						GenericSkins.WaitForElementVisibility(
								By.xpath("//button//span[contains(text(),'Add New Carrier')]"));
						CarrierPage.AddNewCarrierBtn().click();
						System.out.println("clicked on add new carrier");
						GenericSkins.WaitForElementVisibility(By.xpath("(//div[@id='add-screen']//div//input)[1]"));

						List<WebElement> eCarrierInp = driver
								.findElements(By.xpath("//div[@id='add-screen']//form/div"));
						for (WebElement inpCol : eCarrierInp) {
							String colName = inpCol.getText();
							if (colName.equalsIgnoreCase("MC #")) {
								inpCol.findElement(By.tagName("input")).sendKeys(sGenericCarrierMC);
							}
							if (colName.equalsIgnoreCase("DOT #")) {
								inpCol.findElement(By.tagName("input")).sendKeys(sGenericCarrierDot);
							}
							if (colName.equalsIgnoreCase("Carrier Reference #")) {
								inpCol.findElement(By.tagName("input")).sendKeys(sCarrierRef);
							}
							if (colName.equalsIgnoreCase("Contact Name")) {
								inpCol.findElement(By.tagName("input")).sendKeys(sContactName);
							}
							if (colName.equalsIgnoreCase("Contact Email")) {
								inpCol.findElement(By.tagName("input")).sendKeys(sEmailID);
							}
						}

						WebElement m = driver.findElement(By.xpath("//div[@id='left-button-area']"));
						js.executeScript("arguments[0].scrollIntoView(true);", m);
						GenericSkins.WaitForElementTobeClickable(By.xpath(".//span[./text()='Submit Request']"));
						CarrierPage.SubmitRequestBtn().click();
					} catch (Exception e) {
						GenericSkins.WaitForElementVisibility(By.xpath("//div[@id='carrier-options-radio-group']"));
						driver.findElement(By.xpath("//input[@name='Carrier Select']")).click();
						Thread.sleep(1000);
						CarrierPage.NextBtn().click();
						GenericSkins.WaitForElementVisibility(By.xpath("(//div[@id='add-screen']//div//input)[1]"));
						List<WebElement> eCarrierInp = driver
								.findElements(By.xpath("//div[@id='add-screen']//form/div"));
						for (WebElement inpCol : eCarrierInp) {
							String colName = inpCol.getText();

							if (colName.equalsIgnoreCase("Carrier Reference #")) {
								inpCol.findElement(By.tagName("input")).sendKeys(sCarrierRef);
							}
							if (colName.equalsIgnoreCase("Contact Name")) {
								inpCol.findElement(By.tagName("input")).sendKeys(sContactName);
							}
							if (colName.equalsIgnoreCase("Contact Email")) {
								inpCol.findElement(By.tagName("input")).sendKeys(sEmailID);
							}
						}
						try {
							if (!driver.findElement(By.xpath("//div[@id='checkbox-section']//input")).isSelected()) {
								driver.findElement(By.xpath("//div[@id='checkbox-section']//input")).click();
								WebElement m = driver.findElement(By.xpath("//div[@id='left-button-area']"));
								js.executeScript("arguments[0].scrollIntoView(true);", m);
								GenericSkins.WaitForElementTobeClickable(
										By.xpath("//button//span[contains(text(),'Invite Carrier')]"));
								CarrierPage.InviteRequestBtn().click();
							}
						} catch (Exception ex) {
							WebElement m = driver.findElement(By.xpath("//div[@id='left-button-area']"));
							js.executeScript("arguments[0].scrollIntoView(true);", m);
							CarrierPage.InviteRequestBtn().click();
						}

					}

					GenericSkins.WaitForElementVisibility(By.xpath("//button//span[contains(text(),'Finish')]"));
					CarrierPage.FinishBtn().click();
					Thread.sleep(3000);
					System.out.println("Company Added Successfully");
					sActualResult = "Company Added Successfully";
					bResult = true;
				} catch (Exception e) {
					System.out.println();
					sActualResult = e.getMessage();
					e.printStackTrace();
					bResult = false;
				}
				break;
			}
		}
		ResultComparision();
		if (sActualResult.trim().equalsIgnoreCase(sExpectedResult.trim())) {
			bResult = true;
		} else {
			bResult = false;
		}
		TestDataImport.setCellData(sSheetName, 1, 8, sActualResult, "NA");
		TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
		TestDataImport.setCellData(sSheetName, 1, 9, sTestStepStatus, "NA");
		return bResult;
	}

	public static boolean CustomiseGrid(String sActualTestCaseID) throws Exception {
		boolean bResult = false;

		Thread.sleep(5000);
		String sFileName = "Carrier.xlsx";
		String sSheetName = "CustomizeGrid";
		sTestStepID = "customize carrier grid";

		// Copy Loads.xlsx file from test data folder to current log folder
		Copy_File(sTestDataPath + sFileName, sTestResultsPath);

		TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
		int iRowCnt = 0;
		iRowCnt = TestDataImport.GetRowCount(sSheetName);
		System.out.println("rows:" + iRowCnt);
		for (int iRow = 1; iRow <= iRowCnt; iRow++) {
			TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
			String sTestCaseID = TestDataImport.GetCellData(sSheetName, 0, iRow);
			sTestStepData = TestDataImport.GetCellData(sSheetName, 1, iRow);
			String sOperation = TestDataImport.GetCellData(sSheetName, 2, iRow);
			sExpectedResult = TestDataImport.GetCellData(sSheetName, 3, iRow);
			try {
				System.out.println(sTestCaseID);
				System.out.println(sActualTestCaseID);

				if (sTestCaseID.equalsIgnoreCase(sActualTestCaseID)) {
					// CLick on columns button from right pane
					LoadsPage.eColumnPane().click();
					Thread.sleep(2000);
					sActualResult = "Columns not found";
					List<WebElement> eColumns = driver.findElements(
							By.xpath("//*[@id=\"myGrid\"]/div/div/div[2]/div[2]/div[2]/div[2]/div/div/div"));
					driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
					// uncheck all checkboxes
					System.out.println("number of cols:" + eColumns.size());
					for (WebElement eColumn : eColumns) {
						try {
							if (eColumn.findElement(By.cssSelector(".css-yvbm2a")).findElement(By.tagName("svg"))
									.isDisplayed()) {
								eColumn.findElement(By.cssSelector(".css-yvbm2a")).findElement(By.tagName("div"))
										.click();
							}
						} catch (Exception e) {

						}
						Actions action = new Actions(driver);
						action.sendKeys(Keys.ARROW_DOWN).build().perform();
						// Thread.sleep(100);
					}
					System.out.println("Unchecked Done");
					// CLick on columns button from right pane
					LoadsPage.eColumnPane().click();
					Thread.sleep(500);
					Actions action = new Actions(driver);
					action.sendKeys(Keys.F5).build().perform();
					driver.navigate().refresh();
					Thread.sleep(5000);
					// click on columnspane
					LoadsPage.eColumnPane().click();
					Thread.sleep(2000);
					eColumns = driver.findElements(
							By.xpath("//*[@id=\"myGrid\"]/div/div/div[2]/div[2]/div[2]/div[2]/div/div/div"));

					switch (sOperation.toUpperCase()) {

					case "SELECT":

						String[] sData = sTestStepData.split(";");
						int iSelectedCnt = 0;
						for (int i = 0; i < sData.length; i++) {
							for (WebElement eColumn : eColumns) {
								String sName = eColumn.findElement(By.tagName("span")).getText();
								if (sName.trim().equalsIgnoreCase(sData[i].trim())) {
									try {
										if (eColumn.findElement(By.cssSelector(".css-yvbm2a"))
												.findElement(By.tagName("svg")).isDisplayed()) {

										}

									} catch (Exception e) {
										eColumn.findElement(By.cssSelector(".css-yvbm2a"))
												.findElement(By.tagName("div")).click();
									}
									Actions action2 = new Actions(driver);
									action2.sendKeys(Keys.ARROW_DOWN).build().perform();
									// Thread.sleep(100);
									break;
								}
							}
						}
						System.out.println("Check Done");
						// click on columnspane
						LoadsPage.eColumnPane().click();
						Thread.sleep(1000);
						List<WebElement> eHeaders = driver
								.findElements(By.xpath(".//span[@class='ag-header-cell-text']"));
						for (int i = 0; i < sData.length; i++) {
							int iHeadercnt = 0;
							for (WebElement eHeader : eHeaders) {
								iHeadercnt++;
								if (eHeader.getText().trim().equalsIgnoreCase(sData[i].trim())) {
									aHeaderNumbers.add(iHeadercnt);
								}
							}
						}
						if (sData.length == eHeaders.size()) {
							bResult = true;
							sActualResult = "Webtable customized successfully";
						} else {
							sActualResult = "Webtable not customized successfully";
							bResult = false;
						}
						driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
						break;
					}
					ResultComparision();
					TestDataImport.setCellData(sSheetName, iRow, 4, sActualResult, "NA");
					TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
					TestDataImport.setCellData(sSheetName, iRow, 5, sTestStepStatus, "NA");

					break;
				}

			} catch (Exception error) {
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				bResult = false;
				sActualResult = error.getMessage();
				ResultComparision();
				TestDataImport.setCellData(sSheetName, iRow, 4, sActualResult, "NA");
				TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
				TestDataImport.setCellData(sSheetName, iRow, 5, sTestStepStatus, "NA");

			}

			// break;
		}

		return bResult;
	}

	public static boolean LoadWebTable(String sActTestCaseID, int iDataRow) throws Exception {
		boolean bResult = false;
		String sFileName = "Carrier.xlsx";
		String sSheetName = "View Carrier Details";
		sTestStepID = "Carrier grid";

		// Copy Loads.xlsx file from test data folder to current log folder
		Copy_File(sTestDataPath + sFileName, sTestResultsPath);

		TestDataImport.SetExcelFile(sTestDataPath, sFileName);
		int iRowCnt = 0;
		iRowCnt = TestDataImport.GetRowCount(sSheetName);
		System.out.println("Number of rows:" + iRowCnt);
		for (int iRow = 1; iRow <= iRowCnt; iRow++) {

			TestDataImport.SetExcelFile(sTestDataPath, sFileName);
			String sTestCaseID = TestDataImport.GetCellData(sSheetName, 0, iRow);
			String sCarrierRef = TestDataImport.GetCellData(sSheetName, 4, iRow);
			String sCarrierName = TestDataImport.GetCellData(sSheetName, 1, iRow);
			String sDot = TestDataImport.GetCellData(sSheetName, 2, iRow);
			String sMC = TestDataImport.GetCellData(sSheetName, 3, iRow);

			sMC = sGenericCarrierMC;
			sDot = sGenericCarrierDot;
			if (sCarrierName.equalsIgnoreCase("Last Created")) {
				switch (sGenricAccountType.toUpperCase()) {
				case "GLOBAL ADMIN":
					sGenericCarrierName = sGenericCarrierNameGA;
					sDot = sGenericCarrierDotGA;
					sMC = sGenericCarrierMCGA;
					break;
				case "SHIPPER ADMIN":
					sGenericCarrierName = sGenericCarrierNameSA;
					sDot = sGenericCarrierDotSA;
					sMC = sGenericCarrierMCSA;

					break;
				case "SHIPPER USER":
					sGenericCarrierName = sGenericCarrierNameSU;
					sDot = sGenericCarrierDotSU;
					sMC = sGenericCarrierMCSU;

					break;

				}

			}

			sExpectedResult = TestDataImport.GetCellData(sSheetName, 7, iRow);
			sTestStepData = sGenericCarrierName + ";" + sDot + ";" + sMC + ";"; // ";" + sSHipper +
			System.out.println("sTestStepData:" + sTestStepData);
			if (sTestCaseID.trim().equalsIgnoreCase(sActTestCaseID) && (iDataRow == iRow)) {
				try {
					ArrayList<String> aActualRecordCell = new ArrayList();
					;

					try {
						Thread.sleep(5000);
						driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
						driver.findElement(By.xpath(".//span[text()='Clear Filters']")).click();
						Thread.sleep(2000);
					} catch (Exception error_message) {

					}

					driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
					String sData[] = sTestStepData.split(";");

					sActualResult = "Record not found";
					List<WebElement> eCheckBoxes = driver
							.findElements(By.xpath("//*[@id=\"myGrid\"]/div/div/div[2]/div[1]/div[3]/div[1]/div"));
					List<WebElement> eHeaderFilters = driver.findElements(
							By.xpath("//*[@id=\"myGrid\"]/div/div/div[2]/div[1]/div[1]/div[2]/div/div[2]/div"));
					System.out.println("Number of filters:" + eHeaderFilters.size());
					for (int i = 0; i < sData.length; i++) {
						if (!(sData[i].equalsIgnoreCase("NA"))) {
							eHeaderFilters.get(i).findElement(By.tagName("input")).clear();
							// Thread.sleep(500);
							eHeaderFilters.get(i).findElement(By.tagName("input")).sendKeys(sData[i]);
							Actions acton = new Actions(driver);
							acton.sendKeys(Keys.ENTER).build().perform();
							Thread.sleep(2000);
						}

					}
					bResult = true;
					sActualResult = "Webtable validated successfully";
				} catch (Exception error) {
					bResult = false;
					sActualResult = error.getMessage();
					throw error;

				}
				ResultComparision();
				TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
				TestDataImport.setCellData(sSheetName, iRow, 8, sActualResult, "NA");
				TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
				TestDataImport.setCellData(sSheetName, iRow, 9, sTestStepStatus, "NA");
				break;
			}

		}

		System.out.println("Loads Webtble:" + bResult);
		return bResult;

	}

	public static boolean AcceptCarrier() throws InterruptedException {
		boolean bResult = true;
		sTestStepID = "accept carrier";
		System.out.println("Inside Accept Carrier");
		driver.findElement(By.xpath("//div[@col-id='invite']//span[contains(text(),'Accept')]")).click();
		Thread.sleep(10000);
		System.out.println("clicked on accept");
		try {
			if (driver.findElement(By.xpath("//div[@col-id='invite']//span[contains(text(),'Accept')]"))
					.isDisplayed()) {
				bResult = false;
				sActualResult = "Failed to Accept the Invitation";
			}
		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println("Accepted ");
		}
		ResultComparision();
		return bResult;
	}

	public static void OpenSettings() {
		try {
			sTestStepID = "open settings";
			try {
				System.out.println("open settings");
				boolean bResult = false;
				do {
					driver.findElement(By.xpath(".//*[text()='Accept']")).click();
					Thread.sleep(8000);
					System.out.println("clicked on Accept");
					bResult = true;
				} while (bResult == true);
			} catch (Exception e) {

			}
			driver.findElement(
					By.xpath("//button[@class='MuiButtonBase-root MuiButton-root MuiButton-text css-143qge7']"))
					.click(); // .//span[@class='MuiButton-label']//div[@data-cy='nav-menu']//button
			Thread.sleep(1000);
			driver.findElement(By.xpath("//ul//div[contains(text(),'Settings')]")).click(); // .//li[text()='Log
																							// Out']//li[contains(text(),'Settings')]
			Thread.sleep(5000);

		} catch (Exception error) {
			sActualResult = error.getMessage();

		}
		ResultComparision();
	}

	public static boolean VerifyRequest(String sActTestCaseID, int iDataRow) throws Exception {
		boolean bResult = false;
		String sFileName = "Carrier.xlsx";
		String sSheetName = "View Carrier Details";
		sTestStepID = "verify request";
		try {
			TestDataImport.SetExcelFile(sTestDataPath, sFileName);
			int iRowCnt = 0;
			iRowCnt = TestDataImport.GetRowCount(sSheetName);
			System.out.println("Number of rows:" + iRowCnt);
			for (int iRow = 1; iRow <= iRowCnt; iRow++) {

				TestDataImport.SetExcelFile(sTestDataPath, sFileName);
				String sTestCaseID = TestDataImport.GetCellData(sSheetName, 0, iRow);
				String sCarrierName = TestDataImport.GetCellData(sSheetName, 1, iRow);

				if (sTestCaseID.trim().equalsIgnoreCase(sActTestCaseID) && (iDataRow == iRow)) {

					List<WebElement> totalReq = driver.findElements(By.xpath("//div[@class='css-zx11zz e1a5e5n21']"));
					System.out.println("Total Record : " + totalReq.size());
					for (WebElement rTotalRecord : totalReq) {
						String actReqText = rTotalRecord.getText();
						System.out.println("Text: " + actReqText);
						System.out.println("sGenericCarrierName: " + sGenericCarrierName);
						if (actReqText.equalsIgnoreCase(sGenericCarrierName)) {
							System.out.println("Request Received to Carrier Account:true");
							bResult = true;
							break;
						} else {
							bResult = false;
						}
						System.out.println(bResult);
					}
					break;
				}
			}

			if (bResult == false) {
				System.out.println("Request Not Received to Carrier Account");
			}
			driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/img")).click();
			// driver.findElement(By.xpath("//div[@id='root']//img")).click();
			Thread.sleep(3000);

		} catch (Exception e) {
		}
		ResultComparision();
		System.out.println("verify request:" + bResult);
		return bResult;
	}

	public static boolean CancelInvite() throws InterruptedException {
		boolean bResult = true;
		System.out.println("Inside Cancel Carrier");
		driver.findElement(By.xpath("//div[@col-id='invite']//span[contains(text(),'Cancel Invite')]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(".//span[./text()='Continue']")).click();
		Thread.sleep(5000);
		System.out.println("Invitation Cancelled ");

		return bResult;
	}

	public static boolean VerifyCancelledRequest(String sActTestCaseID, int iDataRow) throws Exception {

		boolean bResult = true;
		String sFileName = "Carrier.xlsx";
		String sSheetName = "View Carrier Details";

		TestDataImport.SetExcelFile(sTestDataPath, sFileName);
		int iRowCnt = 0;
		iRowCnt = TestDataImport.GetRowCount(sSheetName);
		for (int iRow = 1; iRow <= iRowCnt; iRow++) {

			TestDataImport.SetExcelFile(sTestDataPath, sFileName);
			String sTestCaseID = TestDataImport.GetCellData(sSheetName, 0, iRow);
			String sCarrierName = TestDataImport.GetCellData(sSheetName, 1, iRow);
			if (sCarrierName.equalsIgnoreCase("Last Created")) {
				switch (sGenricAccountType.toUpperCase()) {
				case "GLOBAL ADMIN":
					sGenericCarrierName = sGenericCarrierNameGA;
					break;
				case "SHIPPER ADMIN":
					sGenericCarrierName = sGenericCarrierNameSA;
					break;
				case "SHIPPER USER":
					sGenericCarrierName = sGenericCarrierNameSU;
					break;

				}

			}
			if (sTestCaseID.trim().equalsIgnoreCase(sActTestCaseID) && (iDataRow == iRow)) {
				List<WebElement> totalReq = driver.findElements(By.xpath("//div[@class='css-zx11zz e1a5e5n21']"));

				for (WebElement rTotalRecord : totalReq) {
					String actReqText = rTotalRecord.getText();
					if (actReqText.equalsIgnoreCase(sGenericCarrierName)) {
						System.out.println("Request not yet cancelled");
						bResult = false;
						break;
					}
				}
				break;
			}
		}
		if (bResult == true) {
			System.out.println("Request cancelled Successfully");
		}

		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/img")).click();
		Thread.sleep(3000);
		return bResult;
	}

	public static boolean AcceptRequest(String sActTestCaseID, int iDataRow) throws Exception {
		boolean bResult = false;
		String sFileName = "Carrier.xlsx";
		String sSheetName = "View Carrier Details";

		TestDataImport.SetExcelFile(sTestDataPath, sFileName);
		int iRowCnt = 0;
		iRowCnt = TestDataImport.GetRowCount(sSheetName);
		for (int iRow = 1; iRow <= iRowCnt; iRow++) {

			TestDataImport.SetExcelFile(sTestDataPath, sFileName);
			String sTestCaseID = TestDataImport.GetCellData(sSheetName, 0, iRow);
			String sCarrierName = TestDataImport.GetCellData(sSheetName, 1, iRow);
			if (sTestCaseID.trim().equalsIgnoreCase(sActTestCaseID) && (iDataRow == iRow)) {

				CarrierPage.AcceptBtn().click();
				Thread.sleep(5000);

				try {
					CarrierPage.AcceptBtn().click();
					Thread.sleep(5000);
					TestActions.WaitForElementVisibility(By.xpath("//button//span[contains(text(),'Cancel')]"));
					driver.findElement(By.xpath("//button//span[contains(text(),'Cancel')]")).click();
					System.out.println("Request Accepted ");
				} catch (Exception e) {
					System.out.println("Failed to Accepted ");
				}

				try {

					TestActions.WaitForElementVisibility(By.xpath("//button//span[contains(text(),'Cancel')]"));
					driver.findElement(By.xpath("//button//span[contains(text(),'Cancel')]")).click();
					Thread.sleep(2000);
					CarrierPage.AcceptBtn().click();
					System.out.println("clicked on cancel");
				} catch (Exception e) {
					System.out.println("accepted and popup not displayed");
				}

				bResult = true;

			}
		}
		if (bResult) {
			System.out.println("Request Accept Successfully");
		}

		driver.findElement(By.xpath("//div[@id='root']//img")).click();
		Thread.sleep(3000);

		try {
			driver.findElement(By.xpath("//button//span[contains(text(),'Accept')]")).click();
			Thread.sleep(2000);
		} catch (Exception e) {

		}
		try {
			System.out.println("back from settings");
			boolean temp = false;
			do {
				driver.findElement(By.xpath(".//*[text()='Accept']")).click();
				Thread.sleep(4000);
				System.out.println("clicked on accept in while loop");
				bResult = true;
			} while (temp == true);
		} catch (Exception e) {
			System.out.println("cancel not available");
		}

		System.out.println("accept request:" + bResult);
		return bResult;
	}

	public static boolean VerifyCarrierOnLoadScreen(String sActTestCaseID, int iDataRow) throws Exception {
		boolean bResult = false;
		String sFileName = "Carrier.xlsx";
		String sSheetName = "View Carrier Details";

		TestDataImport.SetExcelFile(sTestDataPath, sFileName);
		int iRowCnt = 0;
		iRowCnt = TestDataImport.GetRowCount(sSheetName);
		for (int iRow = 1; iRow <= iRowCnt; iRow++) {

			TestDataImport.SetExcelFile(sTestDataPath, sFileName);
			String sTestCaseID = TestDataImport.GetCellData(sSheetName, 0, iRow);
			String sCarrierName = TestDataImport.GetCellData(sSheetName, 1, iRow);
			try {
				System.out.println("verify load");
				boolean temp = false;
				do {
					driver.findElement(By.xpath(".//*[text()='Accept']")).click();
					Thread.sleep(8000);
					System.out.println("clicked on decline");
					bResult = true;
				} while (temp == true);
			} catch (Exception e) {
				System.out.println("Decline not available");
			}

			if (sTestCaseID.trim().equalsIgnoreCase(sActTestCaseID) && (iDataRow == iRow)) {
				try {
					Thread.sleep(5000);

					LoadsPage.eAddNewLoad().click();
					Thread.sleep(2000);
					LoadsPage.eListCarrier().sendKeys(sGenericCarrierName);
					Actions ac = new Actions(driver);
					ac.sendKeys(Keys.ENTER).build().perform();
					Thread.sleep(2000);
					String aCarrier = driver.findElement(By.xpath("//*[@id=\"carrier_uuid\"]/div/div/div/div/div[1]"))
							.getText();
					if (aCarrier.equalsIgnoreCase(sGenericCarrierName)) {
						bResult = true;
						System.out.println("Newly Added carrier Available for Load Generation");
						sActualResult = "New Carrier avaliable";
					}
					break;

				} catch (Exception childerror) {
					sActualResult = childerror.getMessage();
					bResult = false;
				}
			}
		}
		System.out.println("verify load screen:" + bResult);

		return bResult;
	}

	public static void SelectUserMenu() throws InterruptedException {
		GenericSkins.WaitForElementTobeClickable(By.xpath(".//span[text()='Users']"));
		UserPage.eMenuUsers().click();
		Thread.sleep(3000);
	}

	public static void DeleteUser() throws InterruptedException {
		LoadsPage.eDelete().click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("(.//span[./text()='Delete'])[2]")).click();
		Thread.sleep(3000);
	}

	public static void DeleteCarrier() throws InterruptedException {
		driver.findElement(By.xpath("//div[@col-id='name']//span[@class='ag-selection-checkbox']")).click();
		Thread.sleep(1000);
		LoadsPage.eDelete().click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("(.//span[./text()='Delete'])[2]")).click();
		Thread.sleep(3000);
	}

	public static boolean ChangePassword(String sUserName, String sActTestCaseID, int iDataRow) throws Exception {
		boolean bResult = false;
		String sFileName = "Settings.xlsx";
		String sSheetName = "ChangePassword";

		TestDataImport.SetExcelFile(sTestDataPath, sFileName);
		int iRowCnt = 0;
		iRowCnt = TestDataImport.GetRowCount(sSheetName);
		for (int iRow = 1; iRow <= iRowCnt; iRow++) {

			TestDataImport.SetExcelFile(sTestDataPath, sFileName);
			String sTestCaseID = TestDataImport.GetCellData(sSheetName, 0, iRow);
			String sCurrentPassword = TestDataImport.GetCellData(sSheetName, 1, iRow);
			String sNewPassword = TestDataImport.GetCellData(sSheetName, 2, iRow);
			String sConfirmPassword = TestDataImport.GetCellData(sSheetName, 3, iRow);
			String sOperation = TestDataImport.GetCellData(sSheetName, 4, iRow);
			sExpectedResult = TestDataImport.GetCellData(sSheetName, 5, iRow);

			if (sTestCaseID.trim().equalsIgnoreCase(sActTestCaseID) && (iDataRow == iRow)) {
				int flag = 0;
				switch (sOperation.toUpperCase()) {

				case "CHANGEPASSWORD":
					CarrierPage.changePasswordBtn().click();
					Thread.sleep(3000);
					CarrierPage.CurrentPassword().sendKeys(sCurrentPassword);
					CarrierPage.NewPassword().sendKeys(sNewPassword);
					CarrierPage.ConfirmPassword().sendKeys(sConfirmPassword);
					CarrierPage.SavePasswordBtn().click();
					Thread.sleep(3000);
					flag = 1;
					bResult = true;
					sActualResult = "Password Change Successfully";
					break;

				case "REVERTPASSWORD":
					CarrierPage.changePasswordBtn().click();
					Thread.sleep(3000);
					CarrierPage.CurrentPassword().sendKeys(sCurrentPassword);
					CarrierPage.NewPassword().sendKeys(sNewPassword);
					CarrierPage.ConfirmPassword().sendKeys(sConfirmPassword);
					CarrierPage.SavePasswordBtn().click();
					Thread.sleep(3000);
					flag = 1;
					bResult = true;
					sActualResult = "Password Change Successfully";
					break;

				case "RELOGIN":
					bResult = TestActions.Login(sUserName, sNewPassword);
					if (bResult) {
						sActualResult = "Password Change Successfully";
					}
					break;
				}
				if (flag == 1) {
					driver.findElement(By.xpath("//div[@id='root']//img")).click();
					Thread.sleep(3000);
				}
				ResultComparision();
				TestDataImport.setCellData(sSheetName, iRow, 6, sActualResult, "NA");
				TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
				TestDataImport.setCellData(sSheetName, iRow, 7, sTestStepStatus, "NA");
				break;
			}
		}

		return bResult;
	}

}

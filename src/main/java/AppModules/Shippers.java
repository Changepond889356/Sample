package AppModules;

import Utils.EmailFunctions;
import Utils.GenericSkins;
import Utils.TestDataImport;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import PageObjects.*;

public class Shippers extends GenericSkins {

	// MEthod to add a new user
	public static boolean addShipper(String sActualTestCaseID) throws Exception {

		boolean bResult = false;
		String sFileName = "Shippers.xlsx";
		String sSheetName = "AddShippers";
		sTestStepID = "addshipper";
		System.out.println("Adding new shipper");
		// Copy Loads.xlsx file from test data folder to current log folder
		Copy_File(sTestDataPath + sFileName, sTestResultsPath);

		TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
		int iRowCnt = 0;
		iRowCnt = TestDataImport.GetRowCount(sSheetName);
		// System.out.println("Number of rows:" + iRowCnt);

		for (int iRow = 1; iRow <= iRowCnt; iRow++) {
			TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
			String sTestCaseID = TestDataImport.GetCellData(sSheetName, 0, iRow);
			String sShipperName = TestDataImport.GetCellData(sSheetName, 1, iRow);
			String sEmail = TestDataImport.GetCellData(sSheetName, 2, iRow);
			String sCarrier = TestDataImport.GetCellData(sSheetName, 3, iRow);
			String sOperation = TestDataImport.GetCellData(sSheetName, 4, iRow);
			sExpectedResult = TestDataImport.GetCellData(sSheetName, 5, iRow);
			System.out.println("sCarrier:" + sCarrier);
			System.out.println("sEmail:" + sEmail);
			// System.out.println("class name:"+this.getClass().getName());
			// sTestCaseID = "TestCases."+sTestCaseID;
			if (sTestCaseID.equalsIgnoreCase(sActualTestCaseID)) {
				try {

					ShippersPage.eMenuShippers().click();
					Thread.sleep(3000);
					ShippersPage.ebtnAddShipper().click();
					if (!(sShipperName.equalsIgnoreCase("NA"))) {
						String datetime = new SimpleDateFormat("MMddhhmmss").format(new Date());
						sShipperName = sShipperName + "_" + datetime;
						sGenericShipperName = sShipperName;
						System.out.println("sShipperName:" + sShipperName);
						ShippersPage.eShipperName().sendKeys(sShipperName);
					}
					if (!(sEmail.equalsIgnoreCase("NA"))) {
						ShippersPage.eBillingEmail().sendKeys(sEmail);
					}
					if (!(sCarrier.equalsIgnoreCase("NA"))) {
						ShippersPage.eCarrier().sendKeys(sCarrier);
						Thread.sleep(2000);
						Actions ac = new Actions(driver);
						ac.sendKeys(Keys.ENTER).build().perform();
					}
					// UserPage.eInvite().click();
					ShippersPage.eAdd().click();
					sActualResult = "Shipper added successfully";
					bResult = true;

				} catch (Exception error) {
					sActualResult = error.getMessage();
					// throw error;

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

	// Method to handle Users Webtable
	public static boolean ShipperWebTable(int iUserNum, String sActualTestCaseID) throws Exception {
		boolean bResult = false;
		String sFileName = "Shippers.xlsx";
		String sSheetName = "ShippersGrid";
		sTestStepID = "UserWebTable";
		System.out.println("Search record in shipper webtable");
		// Copy Loads.xlsx file from test data folder to current log folder
		Copy_File(sTestDataPath + sFileName, sTestResultsPath);

		TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
		int iRowCnt = 0;
		iRowCnt = TestDataImport.GetRowCount(sSheetName);
		System.out.println("Number of rows:" + iRowCnt);

		for (int iRow = 1; iRow <= iRowCnt; iRow++) {
			TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
			String sTestCaseID = TestDataImport.GetCellData(sSheetName, 0, iRow);
			String sShipperName = TestDataImport.GetCellData(sSheetName, 1, iRow);
			String sEmail = TestDataImport.GetCellData(sSheetName, 2, iRow);
			String sStatus = TestDataImport.GetCellData(sSheetName, 3, iRow);
			String sCarrier = TestDataImport.GetCellData(sSheetName, 4, iRow);
			String sOperation = TestDataImport.GetCellData(sSheetName, 5, iRow);
			sExpectedResult = TestDataImport.GetCellData(sSheetName, 6, iRow);
			sShipperName = sGenericShipperName;
			// System.out.println("class name:"+this.getClass().getName());
			// sTestCaseID = "TestCases."+sTestCaseID;
			if (sTestCaseID.equalsIgnoreCase(sActualTestCaseID) && (iUserNum == iRow)) {
				try {
					String sActualName = "NA";
					String sACtualEmail = "NA";
					String sActualStatus = "NA";
					String sActualCarrier = "NA";
					WebElement eStatusColumn=null;
					// click User Menu
					ShippersPage.eMenuShippers().click();
					Thread.sleep(3000);
					try {
						// click on clear filter
						driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
						driver.findElement(By.xpath(".//span[text()='Clear Filters']")).click();
						Thread.sleep(3000);
					}

					catch (Exception er) {

					}
					// click on load icon
					driver.findElement(By
							.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div/div[1]/button[1]/span[1]/*[name()='svg']"))
							.click();
					// filter by Name
					WebElement eNameFilter = driver
							.findElement(By.xpath("(.//div[@role='columnheader']/div/div/input)[2]"));

					eNameFilter.clear();
					Thread.sleep(3000);
					eNameFilter.sendKeys(sShipperName);
					Thread.sleep(5000);
					/*
					 * driver.findElement(By.xpath(
					 * "//*[@id=\"myGrid\"]/div/div/div[2]/div[1]/div[1]/div[2]/div/div[2]/div[1]/div[1]/div/div/input"
					 * )) .clear();
					 * //*[@id="myGrid"]/div/div/div[2]/div[1]/div[1]/div[2]/div/div[2]/div[2]/div[1
					 * ]/div/div/input driver.findElement(By.xpath(
					 * "//*[@id=\"myGrid\"]/div/div/div[2]/div[1]/div[1]/div[2]/div/div[2]/div[1]/div[1]/div/div/input"
					 * )) .sendKeys(sName);
					 */
					WebElement eCarrierFilter = driver
							.findElement(By.xpath("(.//div[@role='columnheader']/div/div/input)[5]"));
					eCarrierFilter.clear();
					Thread.sleep(2000);
					eCarrierFilter.sendKeys(sCarrier);
					eCarrierFilter.sendKeys(Keys.ENTER);

					System.out.println("loading...");
					Thread.sleep(3000);

					WebElement usergrid = driver.findElement(By.xpath(".//div[@role='grid']"));
					// take list of checkboxes from grid
					// take list of rows from grid
					List<WebElement> eRows = driver.findElements(
							By.xpath("//*[@id=\"myGrid\"]/div/div/div[2]/div[1]/div[3]/div[2]/div/div/div"));
					System.out.println("Number of rows:" + eRows.size());
					int iRow1 = 0;
					sActualResult = "Record not found";
					for (WebElement eRow : eRows) {
						iRow1++;
						System.out.println("Row number:" + iRow1);
						int icol = 0;
						List<WebElement> ecols = eRow.findElements(By.tagName("div"));
						System.out.println("Numnber of cols:" + ecols.size());
						for (WebElement eCOl : ecols) {
							String sColID = eCOl.getAttribute("col-id");
							if (sColID == null) {
								sColID = "NA";
							}
							System.out.println("sColID:" + sColID);

							icol++;
							switch (sColID.toLowerCase()) {
							case "name":
								sActualName = eCOl.getText();
								if (sActualName.equalsIgnoreCase("") || sActualName.equalsIgnoreCase(null)) {
									sActualName = "NA";
								}
								break;
							case "billing_email":
								sACtualEmail = eCOl.getText();
								if (sACtualEmail.equalsIgnoreCase("") || sACtualEmail.equalsIgnoreCase(null)) {
									sACtualEmail = "NA";
								}
								break;
							case "inactive":
								eStatusColumn=eCOl;
								sActualStatus = eCOl.getText();
								if (sActualStatus.equalsIgnoreCase("") || sActualStatus.equalsIgnoreCase(null)) {
									sActualStatus = "NA";
								}
								break;
							case "carriers":
								sActualCarrier = eCOl.getText();
								if (sActualCarrier.equalsIgnoreCase("") || sActualCarrier.equalsIgnoreCase(null)) {
									sActualCarrier = "NA";
								}
								break;

							}

							System.out.println("sActualName:" + sActualName + " Name:" + sShipperName);
							System.out.println("sACtualEmail:" + sACtualEmail + " Email:" + sEmail);
							System.out.println("sActualStatus:" + sActualStatus + " status:" + sStatus);
							// System.out.println("sActualRole:" + sActualRole + " Role:" + sRole);
							System.out.println("sActualCarrier:" + sActualCarrier + " Carrier:" + sCarrier);
							// System.out.println("sActualRef:" + sActualRef + " Ref:" + sRef);
							// System.out.println("sActualShipper:" + sActualShipper + " Shipper:" +
							// sShipper);

							if (sActualName.trim().equalsIgnoreCase(sShipperName.trim())
									&& sACtualEmail.trim().equalsIgnoreCase(sEmail.trim())
									&& sActualCarrier.trim().equalsIgnoreCase(sCarrier.trim())
									&& sActualStatus.trim().equalsIgnoreCase(sStatus.trim())) {
								System.out.println("Record found");
								System.out.println("sOperation :" + sOperation);
								switch (sOperation.toUpperCase()) {
								case "VIEW":
									bResult = true;
									break;
								case "INACTIVE":
									eStatusColumn.click();
									Thread.sleep(2000);
									driver.findElement(By.xpath(".//div[@ref='eList']/div/div/div/div[text()='Inactive']")).click();
									bResult = true;
									break;
								case "ACTIVE":
									eStatusColumn.click();
									Thread.sleep(2000);
									driver.findElement(By.xpath(".//div[@ref='eList']/div/div/div/div[text()='Active']")).click();
									bResult = true;
									break;
								}
								break;
								
							}
						}
						if (bResult == true) {
							sActualResult = "WebTable Validated successfully";
							break;
						} else {
							sActualResult = "WebTable not Validated successfully";
						}
					}

					// return bResult;
				} catch (Exception error) {
					sActualResult = error.getMessage();
					error.printStackTrace();
					// throw error;

				}
				ResultComparision();
				TestDataImport.setCellData(sSheetName, iRow, 7, sActualResult, "NA");
				TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
				TestDataImport.setCellData(sSheetName, iRow, 8, sTestStepStatus, "NA");
				if (sTestStepStatus.equalsIgnoreCase("Passed")) {
					bResult = true;
				}
				break;

			}
		}
		System.out.println("user webtable:" + bResult);
		return bResult;

	}

	// Method to customize webtable in User Page
	public static boolean customizeShippergrid(String sActualTestCaseID) throws Exception {
		boolean bResult = false;

		boolean bSelected = false;
		String sFileName = "Shippers.xlsx";
		String sSheetName = "CustomizeGrid";
		sTestStepID = "customizeUsergrid";
		System.out.println("Customize columns in shippers webtable");
		// Copy Loads.xlsx file from test data folder to current log folder
		Copy_File(sTestDataPath + sFileName, sTestResultsPath);
		aHeaderNumbers = null;
		aHeaderNames = null;
		aHeaderNumbers = new ArrayList();
		aHeaderNames = new ArrayList();

		TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
		int iRowCnt = 0;
		iRowCnt = TestDataImport.GetRowCount(sSheetName);
		for (int iRow = 1; iRow <= iRowCnt; iRow++) {
			TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
			String sTestCaseID = TestDataImport.GetCellData(sSheetName, 0, iRow);
			sTestStepData = TestDataImport.GetCellData(sSheetName, 1, iRow);
			String sOperation = TestDataImport.GetCellData(sSheetName, 2, iRow);
			sExpectedResult = TestDataImport.GetCellData(sSheetName, 3, iRow);
			if (sTestCaseID.equalsIgnoreCase(sActualTestCaseID)) {
				try {
					if (sTestCaseID.equalsIgnoreCase(sActualTestCaseID)) {
						// Click on user menu
						ShippersPage.eMenuShippers().click();
						System.out.println("Clicked on shippers loads");
						Thread.sleep(5000);
						// CLick on columns button from right pane
						ShippersPage.eColumnPane().click();
						Thread.sleep(2000);
						sActualResult = "Columns not found";
						List<WebElement> eColumns = driver.findElements(
								By.xpath("//*[@id=\"myGrid\"]/div/div/div[2]/div[2]/div[2]/div[2]/div/div/div"));
						// System.out.println("Number of columns:" + eColumns.size());
						driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
						// uncheck all checkboxes

						for (WebElement eColumn : eColumns) {

							String sName = eColumn.findElement(By.tagName("span")).getText();
							WebElement eCheckBox = eColumn.findElement(By.cssSelector(".css-yvbm2a"))
									.findElement(By.tagName("div"));
							try {
								WebElement eCheckboxSelectedsvg = eCheckBox.findElement(By.tagName("svg"));
								// System.out.println("svg displayed:" + eCheckboxSelectedsvg.isDisplayed());
								bSelected = eCheckboxSelectedsvg.isDisplayed();
							} catch (Exception child_error) {
								bSelected = false;
							}
							// System.out.println("checkbox selected1:" + bSelected);
							if (bSelected == true) {
								eCheckBox.click();
								// System.out.println("checkbox unchecked");
								Thread.sleep(500);

							}
							Actions action = new Actions(driver);
							action.sendKeys(Keys.ARROW_DOWN).build().perform();
							Thread.sleep(500);

						}

						//
						// CLick on columns button from right pane
						UserPage.eColumnPane().click();
						Thread.sleep(2000);
						Actions action = new Actions(driver);
						action.sendKeys(Keys.F5).build().perform();
						driver.navigate().refresh();
						Thread.sleep(5000);
						// click on columnspane
						UserPage.eColumnPane().click();
						// driver.findElement(By.xpath(""))
						Thread.sleep(2000);
						eColumns = driver.findElements(
								By.xpath("//*[@id=\"myGrid\"]/div/div/div[2]/div[2]/div[2]/div[2]/div/div/div"));
						// System.out.println("Number of columns:" + eColumns.size());

						switch (sOperation.toUpperCase()) {
						case "ALL":
							for (WebElement eColumn : eColumns) {
								String sName = eColumn.findElement(By.tagName("span")).getText();
								WebElement eCheckBox = eColumn.findElement(By.cssSelector(".css-yvbm2a"))
										.findElement(By.tagName("div"));
								try {
									WebElement eCheckboxSelectedsvg = eCheckBox.findElement(By.tagName("svg"));
									bSelected = eCheckboxSelectedsvg.isDisplayed();
								} catch (Exception child_error) {
									bSelected = false;
								}
								// System.out.println("checkbox selected2:" + bSelected);
								if (bSelected == false) {
									eCheckBox.click();
									// System.out.println("checkbox checked");
									Thread.sleep(100);

								}
								Actions action2 = new Actions(driver);
								action2.sendKeys(Keys.ARROW_DOWN).build().perform();
								Thread.sleep(100);
							}

							bResult = true;
							break;
						case "SELECT":

							String[] sData = sTestStepData.split(";");
							int iSelectedCnt = 0;
							for (int i = 0; i < sData.length; i++) {
								for (WebElement eColumn : eColumns) {
									String sName = eColumn.findElement(By.tagName("span")).getText();
									// System.out.println("sName:" + sName);
									if (sName.trim().equalsIgnoreCase(sData[i].trim())) {
										WebElement eCheckBox = eColumn.findElement(By.cssSelector(".css-yvbm2a"))
												.findElement(By.tagName("div"));
										try {
											WebElement eCheckboxSelectedsvg = eCheckBox.findElement(By.tagName("svg"));
											bSelected = eCheckboxSelectedsvg.isDisplayed();
										} catch (Exception child_error) {
											bSelected = false;
										}
										// System.out.println("checkbox selected2:" + bSelected);
										if (bSelected == false) {
											eCheckBox.click();
											iSelectedCnt++;
											// System.out.println("checkbox checked");

										}
										break;

									}
									Thread.sleep(100);
									Actions action2 = new Actions(driver);
									action2.sendKeys(Keys.ARROW_DOWN).build().perform();
									Thread.sleep(100);
								}
							}
							// click on columnspane

							break;

						}

					}
					ShippersPage.eColumnPane().click();
					Thread.sleep(4000);
					if (bResult == true) {
						// bResult = true;
						sActualResult = "Webtable customized successfully";
					} else {
						sActualResult = "Webtable not customized successfully";
						bResult = false;
					}
					driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
					// UserPage.eColumnPane().click();
					Actions act = new Actions(driver);
					act.sendKeys(Keys.F5).build().perform();

				} catch (Exception error) {
					driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
					bResult = false;
					sActualResult = error.getMessage();

				}
				ResultComparision();
				TestDataImport.setCellData(sSheetName, iRow, 4, sActualResult, "NA");
				TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
				TestDataImport.setCellData(sSheetName, iRow, 5, sTestStepStatus, "NA");

			}

			// System.out.println("class name:"+this.getClass().getName());
			// sTestCaseID = "TestCases."+sTestCaseID;

		}

		return bResult;
	}

}

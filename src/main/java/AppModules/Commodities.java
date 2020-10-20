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

public class Commodities extends GenericSkins {
	
	protected static WebElement eCommodityName=null;
	protected static WebElement eUpperLImit=null;
	protected static WebElement eLowerLimit=null;
	protected static WebElement ePounds=null;

	// MEthod to add a new user
	public static boolean addCommodities(String sActualTestCaseID) throws Exception {

		boolean bResult = false;
		String sFileName = "Commodities.xlsx";
		String sSheetName = "AddCommodities";
		sTestStepID = "addcommodityr";
		System.out.println("Adding new commodity");
		// Copy Loads.xlsx file from test data folder to current log folder
		Copy_File(sTestDataPath + sFileName, sTestResultsPath);

		TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
		int iRowCnt = 0;
		iRowCnt = TestDataImport.GetRowCount(sSheetName);
		// System.out.println("Number of rows:" + iRowCnt);

		for (int iRow = 1; iRow <= iRowCnt; iRow++) {
			TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
			String sTestCaseID = TestDataImport.GetCellData(sSheetName, 0, iRow);
			String sCommodityName = TestDataImport.GetCellData(sSheetName, 1, iRow);
			String sUpperLimit = TestDataImport.GetCellData(sSheetName, 2, iRow);
			String sLowerLimit = TestDataImport.GetCellData(sSheetName, 3, iRow);
			String sPounds = TestDataImport.GetCellData(sSheetName, 4, iRow);
			String sOperation = TestDataImport.GetCellData(sSheetName, 5, iRow);
			sExpectedResult = TestDataImport.GetCellData(sSheetName, 6, iRow);
			// System.out.println("class name:"+this.getClass().getName());
			// sTestCaseID = "TestCases."+sTestCaseID;
			if (sTestCaseID.equalsIgnoreCase(sActualTestCaseID)) {
				try {

					CommoditiesPage.eMenuCommodities().click();
					Thread.sleep(3000);
					CommoditiesPage.ebtnAddCommodity().click();
					if (!(sCommodityName.equalsIgnoreCase("NA"))) {
						String datetime = new SimpleDateFormat("MMddhhmmss").format(new Date());
						sCommodityName = sCommodityName + "_" + datetime;
						sGenericCommodityName = sCommodityName;
						System.out.println("commodity:" + sCommodityName);
						CommoditiesPage.ecommodityName().sendKeys(sCommodityName);
					}
					if (!(sUpperLimit.equalsIgnoreCase("NA"))) {
						CommoditiesPage.eupperLimit().sendKeys(sUpperLimit);
					}
					if (!(sLowerLimit.equalsIgnoreCase("NA"))) {
						CommoditiesPage.elowerLimit().sendKeys(sLowerLimit);
						Thread.sleep(2000);
						Actions ac = new Actions(driver);
						ac.sendKeys(Keys.ENTER).build().perform();
					}
					if (!(sPounds.equalsIgnoreCase("NA"))) {
						CommoditiesPage.epoundsPerBushel().sendKeys(sPounds);
						Thread.sleep(2000);
						Actions ac = new Actions(driver);
						ac.sendKeys(Keys.ENTER).build().perform();
					}
					// UserPage.eInvite().click();
					ShippersPage.eAdd().click();
					sActualResult = "Commodity added successfully";
					bResult = true;

				} catch (Exception error) {
					sActualResult = error.getMessage();
					// throw error;

				}
				ResultComparision();
				TestDataImport.setCellData(sSheetName, iRow, 7, sActualResult, "NA");
				TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
				TestDataImport.setCellData(sSheetName, iRow, 8, sTestStepStatus, "NA");
				break;
			}
		}

		return bResult;

	}


	// MEthod to add a new user
	public static boolean EditCommodities(String sActualTestCaseID) throws Exception {

		boolean bResult = false;
		String sFileName = "Commodities.xlsx";
		String sSheetName = "EditCommodities";
		sTestStepID = "editcommodityr";
		System.out.println("Adding new commodity");
		// Copy Loads.xlsx file from test data folder to current log folder
		Copy_File(sTestDataPath + sFileName, sTestResultsPath);

		TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
		int iRowCnt = 0;
		iRowCnt = TestDataImport.GetRowCount(sSheetName);
		// System.out.println("Number of rows:" + iRowCnt);

		for (int iRow = 1; iRow <= iRowCnt; iRow++) {
			TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
			String sTestCaseID = TestDataImport.GetCellData(sSheetName, 0, iRow);
			String sCommodityName = TestDataImport.GetCellData(sSheetName, 1, iRow);
			String sUpperLimit = TestDataImport.GetCellData(sSheetName, 2, iRow);
			String sLowerLimit = TestDataImport.GetCellData(sSheetName, 3, iRow);
			String sPounds = TestDataImport.GetCellData(sSheetName, 4, iRow);
			String sOperation = TestDataImport.GetCellData(sSheetName, 5, iRow);
			sExpectedResult = TestDataImport.GetCellData(sSheetName, 6, iRow);
			// System.out.println("class name:"+this.getClass().getName());
			// sTestCaseID = "TestCases."+sTestCaseID;
			if (sTestCaseID.equalsIgnoreCase(sActualTestCaseID)) {
				try {

					//CommoditiesPage.eMenuCommodities().click();
					//Thread.sleep(3000);
					//CommoditiesPage.ebtnAddCommodity().click();
					if (!(sCommodityName.equalsIgnoreCase("NA"))) {
						String datetime = new SimpleDateFormat("MMddhhmmss").format(new Date());
						sCommodityName = sCommodityName + "_" + datetime;
						sGenericCommodityName = sCommodityName;
						eCommodityName.click();
						Thread.sleep(2000);
						Actions action3 = new Actions(driver);
						//action3.sendKeys(Keys.CONTROL+"a").build().perform();
						Thread.sleep(1000);
						action3.sendKeys(Keys.DELETE).build().perform();;
						action3.sendKeys(sCommodityName).build().perform();;
						sGenericCommodityName=sCommodityName;
					}
					if (!(sUpperLimit.equalsIgnoreCase("NA"))) {
						eUpperLImit.click();
						Thread.sleep(2000);
					Actions action3 = new Actions(driver);
					//action3.sendKeys(Keys.CONTROL+"a").build().perform();
					Thread.sleep(1000);
					action3.sendKeys(Keys.DELETE).build().perform();;
					action3.sendKeys(sUpperLimit).build().perform();;
					}
					if (!(sLowerLimit.equalsIgnoreCase("NA"))) {
						eLowerLimit.click();
					Actions action3 = new Actions(driver);
					Thread.sleep(2000);
					//action3.sendKeys(Keys.CONTROL+"a").build().perform();
					Thread.sleep(1000);
					action3.sendKeys(Keys.DELETE).build().perform();;
					action3.sendKeys(sLowerLimit).build().perform();;
					}
					if (!(sPounds.equalsIgnoreCase("NA"))) {
						ePounds.click();
					Actions action3 = new Actions(driver);
					Thread.sleep(2000);
					//action3.sendKeys(Keys.CONTROL+"a").build().perform();
					Thread.sleep(1000);
					action3.sendKeys(Keys.DELETE).build().perform();;
					action3.sendKeys(sPounds).build().perform();;
					}
					// UserPage.eInvite().click();
					//ShippersPage.eAdd().click();
					sActualResult = "Commodity edited successfully";
					bResult = true;

				} catch (Exception error) {
					sActualResult = error.getMessage();
					// throw error;

				}
				ResultComparision();
				TestDataImport.setCellData(sSheetName, iRow, 7, sActualResult, "NA");
				TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
				TestDataImport.setCellData(sSheetName, iRow, 8, sTestStepStatus, "NA");
				break;
			}
		}

		return bResult;

	}


	// Method to handle Users Webtable
	public static boolean CommoditiesWebTable(int iUserNum, String sActualTestCaseID) throws Exception {
		boolean bResult = false;
		String sFileName = "Commodities.xlsx";
		String sSheetName = "CommoditiesGrid";
		sTestStepID = "CommoditiesGrid";
		System.out.println("Search record in commodities webtable");
		// Copy Loads.xlsx file from test data folder to current log folder
		Copy_File(sTestDataPath + sFileName, sTestResultsPath);

		TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
		int iRowCnt = 0;
		iRowCnt = TestDataImport.GetRowCount(sSheetName);
		System.out.println("Number of rows:" + iRowCnt);

		for (int iRow = 1; iRow <= iRowCnt; iRow++) {
			TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
			String sTestCaseID = TestDataImport.GetCellData(sSheetName, 0, iRow);
			String sCommodityName = TestDataImport.GetCellData(sSheetName, 1, iRow);
			String sUpperLimit = TestDataImport.GetCellData(sSheetName, 2, iRow);
			String sLowerLimit = TestDataImport.GetCellData(sSheetName, 3, iRow);
			String sPounds = TestDataImport.GetCellData(sSheetName, 4, iRow);
			String sOperation = TestDataImport.GetCellData(sSheetName, 5, iRow);
			sExpectedResult = TestDataImport.GetCellData(sSheetName, 6, iRow);
			if (sCommodityName.equalsIgnoreCase("AutoCom")) {
				sCommodityName = sGenericCommodityName;
			}

			// System.out.println("class name:"+this.getClass().getName());
			// sTestCaseID = "TestCases."+sTestCaseID;
			if (sTestCaseID.equalsIgnoreCase(sActualTestCaseID) && (iUserNum == iRow)) {
				try {
					String sActualName = "NA";
					String aActualUpperlimit = "NA";
					String aActualLowerlimit = "NA";
					String sActualPounds = "NA";
					WebElement eStatusColumn = null;
					// click User Menu
					CommoditiesPage.eMenuCommodities().click();
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
					Thread.sleep(5000);
					// filter by Name
					WebElement eNameFilter = driver
							.findElement(By.xpath("//*[@id=\"myGrid\"]/div/div/div[2]/div[1]/div[1]/div[2]/div/div[2]/div[1]/div[1]/div/div/input"));

					eNameFilter.clear();
					Thread.sleep(1000);
					eNameFilter.sendKeys(sCommodityName);
					Thread.sleep(2000);
					/*
					 * driver.findElement(By.xpath(
					 * "//*[@id=\"myGrid\"]/div/div/div[2]/div[1]/div[1]/div[2]/div/div[2]/div[1]/div[1]/div/div/input"
					 * )) .clear();
					 * //*[@id="myGrid"]/div/div/div[2]/div[1]/div[1]/div[2]/div/div[2]/div[2]/div[1
					 * ]/div/div/input driver.findElement(By.xpath(
					 * "//*[@id=\"myGrid\"]/div/div/div[2]/div[1]/div[1]/div[2]/div/div[2]/div[1]/div[1]/div/div/input"
					 * )) .sendKeys(sName);
					 */
					/*
					 * WebElement eCarrierFilter = driver
					 * .findElement(By.xpath("(.//div[@role='columnheader']/div/div/input)[5]"));
					 * eCarrierFilter.clear(); Thread.sleep(2000);
					 * eCarrierFilter.sendKeys(sCarrier); eCarrierFilter.sendKeys(Keys.ENTER);
					 * 
					 */ System.out.println("loading...");
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
								eCommodityName = eCOl;
								sActualName = eCOl.getText();
								if (sActualName.equalsIgnoreCase("") || sActualName.equalsIgnoreCase(null)) {
									sActualName = "NA";
								}
								break;
							case "upper_limit":
								eUpperLImit = eCOl;
								aActualUpperlimit = eCOl.getText();
								if (aActualUpperlimit.equalsIgnoreCase("")
										|| aActualUpperlimit.equalsIgnoreCase(null)) {
									aActualUpperlimit = "NA";
								}
								break;
							case "lower_limit":
								eLowerLimit = eCOl;
								eStatusColumn = eCOl;
								aActualLowerlimit = eCOl.getText();
								if (aActualLowerlimit.equalsIgnoreCase("")
										|| aActualLowerlimit.equalsIgnoreCase(null)) {
									aActualLowerlimit = "NA";
								}
								break;
							case "bushel_conversion":
								ePounds = eCOl;
								sActualPounds = eCOl.getText();
								if (sActualPounds.equalsIgnoreCase("") || sActualPounds.equalsIgnoreCase(null)) {
									sActualPounds = "NA";
								}
								break;

							}

							if (sCommodityName.trim().equalsIgnoreCase(sActualName.trim())
									&& sUpperLimit.trim().equalsIgnoreCase(aActualUpperlimit.trim())
									&& sLowerLimit.trim().equalsIgnoreCase(aActualLowerlimit.trim())
									&& sPounds.trim().equalsIgnoreCase(sActualPounds.trim())) {
								System.out.println("Record found");
								System.out.println("sOperation :" + sOperation);
								List<WebElement> eCheckBoxes = driver
										.findElements(By.xpath("//*[@id=\"myGrid\"]/div/div/div[2]/div[1]/div[3]/div[1]/div"));

								switch (sOperation.toUpperCase()) {
								case "VIEW":
									bResult = true;
									break;
								case "DELETE":
									driver.findElement(By.xpath("//*[@id=\"myGrid\"]/div/div/div[2]/div[1]/div[3]/div[1]/div/div/div/span[1]/span[2]")).click();
									CommoditiesPage.eDelete().click();
									CommoditiesPage.eConfirmDelete().click();
									bResult = true;
									break;
								case "ACTIVE":
									eStatusColumn.click();
									Thread.sleep(2000);
									driver.findElement(
											By.xpath(".//div[@ref='eList']/div/div/div/div[text()='Active']")).click();
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
		System.out.println("commodities webtable:" + bResult);
		return bResult;

	}

	// Method to customize webtable in User Page
	public static boolean customizeCommoditygrid(String sActualTestCaseID) throws Exception {
		boolean bResult = false;

		boolean bSelected = false;
		String sFileName = "Commodities.xlsx";
		String sSheetName = "CustomizeGrid";
		sTestStepID = "customizecommoditygrid";
		System.out.println("Customize columns in commodities webtable");
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
						// Click on commodities menu
						CommoditiesPage.eMenuCommodities().click();
						System.out.println("Clicked on commodities");
						Thread.sleep(5000);
						// CLick on columns button from right pane
						CommoditiesPage.eColumnPane().click();
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
						CommoditiesPage.eColumnPane().click();
						Thread.sleep(2000);
						Actions action = new Actions(driver);
						action.sendKeys(Keys.F5).build().perform();
						driver.navigate().refresh();
						Thread.sleep(5000);
						// click on columnspane
						CommoditiesPage.eColumnPane().click();
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
							bResult = true;
							break;

						}

					}
					CommoditiesPage.eColumnPane().click();
					Thread.sleep(4000);
					System.out.println("commodity customize grid:" + bResult);
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
					error.printStackTrace();

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

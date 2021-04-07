package AppModules;

import Utils.GenericSkins;
import Utils.TestDataImport;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.groovy.json.internal.Exceptions;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;

import PageObjects.*;

public class Loads extends GenericSkins {

	// MEthod to add a new user
	public static boolean addNewLoad(String sActualTestCaseID) throws Exception {

		boolean bResult = false;
		String sFileName = "Loads.xlsx";
		String sSheetName = "Add Load";

		// Copy Loads.xlsx file from test data folder to current log folder
		Copy_File(sTestDataPath + sFileName, sTestResultsPath);
		
		TestDataImport.SetExcelFile(sTestDataPath, sFileName);
		int iRowCnt = 0;
		iRowCnt = TestDataImport.GetRowCount(sSheetName);
		// System.out.println("Number of rows:"+iRowCnt);
		for (int iRow = 1; iRow <= iRowCnt; iRow++) {

			TestDataImport.SetExcelFile(sTestDataPath, sFileName);
			String sTestCaseID = TestDataImport.GetCellData(sSheetName, 0, iRow);
			String sCarrier = TestDataImport.GetCellData(sSheetName, 1, iRow);
			String sLoadDate = TestDataImport.GetCellData(sSheetName, 2, iRow);
			String sSHipper = TestDataImport.GetCellData(sSheetName, 3, iRow);
			String sSHipperContact = TestDataImport.GetCellData(sSheetName, 4, iRow);
			String sCommodity = TestDataImport.GetCellData(sSheetName, 5, iRow);
			String sRate = TestDataImport.GetCellData(sSheetName, 6, iRow);
			String sRateUOM = TestDataImport.GetCellData(sSheetName, 7, iRow);
			String sOrigin = TestDataImport.GetCellData(sSheetName, 8, iRow);
			String sDestination = TestDataImport.GetCellData(sSheetName, 9, iRow);
			sExpectedResult = TestDataImport.GetCellData(sSheetName, 10, iRow);
			System.out.println("Add Load:" + "sTestCaseID:" + sTestCaseID + "sActualTestCaseID:" + sActualTestCaseID);
			if(sSHipper.equalsIgnoreCase("New Shipper"))
			{
			sSHipper=sGenericShipperName;
			}
			if (sTestCaseID.trim().equalsIgnoreCase(sActualTestCaseID.trim())) {
				System.out.println("inside if");
				try {

					DateTimeFormatter dateandtime = DateTimeFormatter.ofPattern("MM/d/yyyy");
					LocalDateTime t1 = LocalDateTime.now();
					sLoadDate = sLoadDate.replace("Current Date", t1.format(dateandtime));
					String[] currentDate = t1.format(dateandtime).split("/");
					String sDay = currentDate[1];
					LoadsPage.eMenuLoads().click();
					Thread.sleep(2000);
					System.out.println("Clicking on add new load");
					LoadsPage.eAddNewLoad().click();
					Thread.sleep(4000);
					Actions ac = new Actions(driver);
					try {

						LoadsPage.eListCarrier().sendKeys(sCarrier);

						ac.sendKeys(Keys.ENTER).build().perform();

					} catch (Exception error) {

					}

					Thread.sleep(1000);
					LoadsPage.LoadDatePicker(sDay);
					Thread.sleep(1000);
					System.out.println("Set Load Date");
					try {
						if (!(sSHipper.trim().equalsIgnoreCase("NA"))) {
							System.out.println("Insideshipper");
							LoadsPage.eShipper().sendKeys(sSHipper);
							Thread.sleep(1000);
							ac.sendKeys(Keys.ENTER).build().perform();
							Thread.sleep(1000);
							System.out.println("Set shipper");

						}
					} catch(Exception e) {

					}
					try {
						if (!(sSHipperContact.trim().equalsIgnoreCase("NA"))) {

							LoadsPage.eShipperContact().sendKeys(sSHipperContact);
							//Thread.sleep(3000);
							System.out.println("contact Enter");
							ac.sendKeys(Keys.ENTER).build().perform();
							System.out.println(" Enter pressed");
							Thread.sleep(1000);

						}
					} catch(Exception e) {
						System.out.println("Shipper Contact : "+ e);
					}

					if (!(sCommodity.trim().equalsIgnoreCase("NA"))) {
						LoadsPage.eCommodity().sendKeys(sCommodity);
						ac = new Actions(driver);
						ac.sendKeys(Keys.ENTER).build().perform();
						Thread.sleep(1000);

					}
					if (!(sRate.trim().equalsIgnoreCase("NA"))) {
						LoadsPage.eRate().sendKeys(sRate);

					}
					if (!(sOrigin.trim().equalsIgnoreCase("NA"))) {
						String datetime = new SimpleDateFormat("MMddhhmmss").format(new Date());
						sOrigin = sOrigin+"_" + datetime;
						System.out.println("sOrigin "+ sOrigin);
						TestDataImport.writeExcel(sTestDataPath,"Loads.xlsx", "View Load", sOrigin, 6, sTestCaseID);
						TestDataImport.writeExcel(sTestResultsPath,"Loads.xlsx", "View Load", sOrigin, 6, sTestCaseID);
						LoadsPage.eOrigin().sendKeys(sOrigin);
						
					}

					if (!(sDestination.trim().equalsIgnoreCase("NA"))) {
						String datetime = new SimpleDateFormat("MMddhhmmss").format(new Date());
						sDestination = sDestination+"_" + datetime;
						TestDataImport.writeExcel(sTestDataPath,"Loads.xlsx", "View Load", sDestination, 7, sTestCaseID);
						TestDataImport.writeExcel(sTestResultsPath,"Loads.xlsx", "View Load", sDestination, 7, sTestCaseID);						
						LoadsPage.eDestination().sendKeys(sDestination);
						driver.findElement(By.xpath("//*[@id='origin_weight']")).click();
					}

					if (!(sRateUOM.trim().equalsIgnoreCase("NA"))) {
						LoadsPage.eRateUOM().sendKeys(sRateUOM);
						ac = new Actions(driver);
						ac.sendKeys(Keys.ENTER).build().perform();
						Thread.sleep(1000);

					}

					LoadsPage.eSave().click();
					WebDriverWait wait = new WebDriverWait(driver, 30);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
							"//*[@id=\"myGrid\"]/div/div/div[2]/div[1]/div[1]/div[2]/div/div[1]/div[1]/div[3]/div")));
					sActualResult = "Added new load successfully";
					bResult = true;

				} catch (Exception error) {

					sActualResult = error.getMessage();
					// throw error;

				}
				ResultComparision();
				TestDataImport.setCellData(sSheetName, iRow, 11, sActualResult, "NA");
				TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
				TestDataImport.setCellData(sSheetName, iRow, 12, sTestStepStatus, "NA");
				break;
			} else {
				sActualResult = "Testcase not found";
			}

		}
		System.out.println("Add load:" + sActualResult);
		return bResult;
	}

	// MEthod to edit a load
	public static boolean editLoad(String sActualTestCaseID) throws Exception {
		Thread.sleep(1000);
		boolean bResult = false;
		String sFileName = "Loads.xlsx";
		String sSheetName = "Edit Load";

		// Copy Loads.xlsx file from test data folder to current log folder
		Copy_File(sTestDataPath + sFileName, sTestResultsPath);

		TestDataImport.SetExcelFile(sTestDataPath, sFileName);
		int iRowCnt = 0;
		iRowCnt = TestDataImport.GetRowCount(sSheetName);
		System.out.println("Number of rows:" + iRowCnt);
		for (int iRow = 1; iRow <= iRowCnt; iRow++) {

			TestDataImport.SetExcelFile(sTestDataPath, sFileName);
			String sTestCaseID = TestDataImport.GetCellData(sSheetName, 0, iRow);
			String sField = TestDataImport.GetCellData(sSheetName, 1, iRow);
			String sValue = TestDataImport.GetCellData(sSheetName, 2, iRow);
			sExpectedResult = TestDataImport.GetCellData(sSheetName, 3, iRow);
			if (sTestCaseID.trim().equalsIgnoreCase(sActualTestCaseID)) {
				try {
					LoadsPage.eEdit().click();
					Thread.sleep(5000);
					switch (sField.toUpperCase()) {
					case "SHIPPER CONTACT":
						LoadsPage.eShipperContact().sendKeys(sValue);
						Actions action = new Actions(driver);
						Thread.sleep(500);
						action.sendKeys(Keys.ENTER).build().perform();
						Thread.sleep(1000);
						LoadsPage.eSave().click();
						Thread.sleep(2000);
						GenericSkins.WaitForElementVisibility(By.xpath(".//span[contains(text(),'Edit')]"));
						bResult = true;
						break;
					case "DRIVER":
						System.out.println("sendkeys in Driver");
						LoadsPage.eDriver().sendKeys(sValue);
						Actions action1 = new Actions(driver);
						Thread.sleep(500);
						action1.sendKeys(Keys.ENTER).build().perform();
						Thread.sleep(1000);
						LoadsPage.eSave().click();
						Thread.sleep(2000);
						GenericSkins.WaitForElementVisibility(By.xpath(".//span[contains(text(),'Edit')]"));
						bResult = true;
						break;
					case "ORIGIN WEIGHT":
						System.out.println("Send Keys in Origin Weight");
						LoadsPage.eNetOriginWt().sendKeys(sValue);
						Actions action11 = new Actions(driver);
						Thread.sleep(500);
						action11.sendKeys(Keys.TAB).build().perform();
						Thread.sleep(1000);						
						bResult = true;
						break;

					case "DESTINATION WEIGHT":
						System.out.println("Send Keys in Destination Weight");
						LoadsPage.eNetDestWr().sendKeys(sValue);
						Actions action111 = new Actions(driver);
						Thread.sleep(500);
						action111.sendKeys(Keys.TAB).build().perform();
						Thread.sleep(1000);
						bResult = true;
						break;
						
					case "FIELDMAPPING":
						Actions ac = new Actions(driver);
						List<WebElement> fieldmapping = driver.findElements(By.xpath("//*[@id='fields-to-map']/div"));
						for(WebElement fm: fieldmapping) {
							System.out.println("text: " + fm.getText());
							if(fm.getText().contains("Destination Reference #")) {
								LoadsPage.eDestRef().sendKeys(sValue);
								Thread.sleep(500);
								ac.sendKeys(Keys.ENTER).build().perform();
								Thread.sleep(500);
							} else if(fm.getText().contains("Origin Reference #")) {
								LoadsPage.eOriginRef().sendKeys(sValue);
								Thread.sleep(500);
								ac.sendKeys(Keys.ENTER).build().perform();
								Thread.sleep(500);
							} else if(fm.getText().contains("Trans ID")) {
								LoadsPage.eTransID().sendKeys(sValue);
								Thread.sleep(500);
								ac.sendKeys(Keys.ENTER).build().perform();
								Thread.sleep(500);
							}
						}
						Thread.sleep(500);
						LoadsPage.eSave().click();
						Thread.sleep(3000);
						GenericSkins.WaitForElementVisibility(By.xpath(".//span[contains(text(),'Edit')]"));
						bResult = true;
						break;
						
					case "LOAD DATE":
						bResult = false;
						DateTimeFormatter dateandtime = DateTimeFormatter.ofPattern("MM/dd/yyyy");
						LocalDateTime t1 = LocalDateTime.now();
						String[] currentDate = t1.minusDays(1).format(dateandtime).split("/");
						String sDay = currentDate[1];
						LoadsPage.LoadDatePicker(sDay);
						LoadsPage.eSave().click();
						Thread.sleep(1000);
						GenericSkins.WaitForElementVisibility(By.xpath(".//span[contains(text(),'Edit')]"));
						bResult = true;
						break;
						
					case "COMMODITY":
						Thread.sleep(1000);
						if(!sValue.equalsIgnoreCase("NA")) {
							System.out.println("Commodity " + sValue);
							GenericSkins.WaitForElementVisibility(By.xpath("//div[@id='commodity_uuid']//input"));
							Thread.sleep(2000);
							LoadsPage.eCommodity().sendKeys(sValue);
							ac = new Actions(driver);
							ac.sendKeys(Keys.ENTER).build().perform();
						}
						LoadsPage.eSave().click();
						Thread.sleep(2000);
						GenericSkins.WaitForElementVisibility(By.xpath(".//span[contains(text(),'Edit')]"));
						bResult = true;
						break;
						
					case "ORIGIN":
						Thread.sleep(1000);
						if(!sValue.equalsIgnoreCase("NA")) {
							bResult = false;
							driver.findElement(By.xpath("//*[@id=\"origin_uuid\"]")).clear();
							String datetime = new SimpleDateFormat("MMddhhmmss").format(new Date());
							sValue = sValue+"_" + datetime;
							LoadsPage.eOrigin().sendKeys(sValue);
							ac = new Actions(driver);
							ac.sendKeys(Keys.ENTER).build().perform();
						}

						LoadsPage.eSave().click();
						Thread.sleep(2000);
						GenericSkins.WaitForElementVisibility(By.xpath(".//span[contains(text(),'Edit')]"));
						bResult = true;
						break;
						
					case "DESTINATION":
						Thread.sleep(1000);
						if(!sValue.equalsIgnoreCase("NA")) {
							bResult = false;
							driver.findElement(By.xpath("//*[@id='destination_uuid']")).clear();
							LoadsPage.eDestination().sendKeys(sValue);
							ac = new Actions(driver);
							ac.sendKeys(Keys.ENTER).build().perform();
						} 
						LoadsPage.eSave().click();
						Thread.sleep(2000);
						GenericSkins.WaitForElementVisibility(By.xpath(".//span[contains(text(),'Edit')]"));
						bResult = true;
						break;
						 
					case "RATE":
						Thread.sleep(1000);
						if(!sValue.equalsIgnoreCase("NA")) {
							System.out.println("Rate " + sValue);
							driver.findElement(By.xpath(".//div[@id='rate_uom_uuid']/div/div/div/div/div[2]/div/input")).clear();
							Thread.sleep(3000);
							LoadsPage.eRate().sendKeys(sValue);
							ac = new Actions(driver);
							ac.sendKeys(Keys.ENTER).build().perform();
						}
						LoadsPage.eSave().click();
						Thread.sleep(2000);
						GenericSkins.WaitForElementVisibility(By.xpath(".//span[contains(text(),'Edit')]"));
						bResult = true;
						break;
					
					case "SHIPPERCONTACT":
						Thread.sleep(1000);
						if(!sValue.equalsIgnoreCase("NA")) {
							bResult = false;
							LoadsPage.eShipperContact().sendKeys(sValue);
							ac = new Actions(driver);
							ac.sendKeys(Keys.ENTER).build().perform();
						}
						LoadsPage.eSave().click();
						Thread.sleep(2000);
						GenericSkins.WaitForElementVisibility(By.xpath(".//span[contains(text(),'Edit')]"));
						bResult = true;
						break;
						
					case "UOM":
						Thread.sleep(1000);
						if(!sValue.equalsIgnoreCase("NA")) {
							bResult = false;
							LoadsPage.eRateUOM().sendKeys(sValue);
							ac = new Actions(driver);
							ac.sendKeys(Keys.ENTER).build().perform();
						}
						LoadsPage.eSave().click();
						Thread.sleep(2000);
						GenericSkins.WaitForElementVisibility(By.xpath(".//span[contains(text(),'Edit')]"));
						bResult = true;
						break;
						
					case "AMOUNT":
						if(!sValue.equalsIgnoreCase("NA")) {
							bResult = false;
							LoadsPage.eAmount().sendKeys(sValue);
							ac = new Actions(driver);
							ac.sendKeys(Keys.ENTER).build().perform();
						}
						LoadsPage.eSave().click();
						Thread.sleep(2000);
						GenericSkins.WaitForElementVisibility(By.xpath(".//span[contains(text(),'Edit')]"));
						bResult = true;
						break;
					}

				
					if (bResult == true) {
						try {
							driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
							driver.findElement(By.xpath("//span[text()='Yes']")).click();
							Thread.sleep(2000);
							LoadsPage.eSave().click();
							Thread.sleep(2000);

						} catch (Exception er) {
							//System.out.println(er);
							
						}
						sActualResult = "Load Edited Successfully";
					}

				} catch (Exception error) {
					bResult = false;
					sActualResult = error.getMessage();
					
				}
				ResultComparision();
				TestDataImport.setCellData(sSheetName, iRow, 4, sActualResult, "NA");
				TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
				TestDataImport.setCellData(sSheetName, iRow, 5, sTestStepStatus, "NA");

			}
		}

		System.out.println(sActualResult);
		return bResult;
	}

	// Method to customize webtable in Loads Page
	public static boolean customizeAGgrid(String sActualTestCaseID) throws Exception {
		boolean bResult = false;

		boolean bSelected = false;
		String sFileName = "Loads.xlsx";
		String sSheetName = "CustomizeGrid";
		// Copy Loads.xlsx file from test data folder to current log folder
		Copy_File(sTestDataPath + sFileName, sTestResultsPath);

		Loads.HideGeoLocationPane();
		
		TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
		int iRowCnt = 0;
		iRowCnt = TestDataImport.GetRowCount(sSheetName);
		for (int iRow = 1; iRow <= iRowCnt; iRow++) {
			TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
			String sTestCaseID = TestDataImport.GetCellData(sSheetName, 0, iRow);
			sTestStepData = TestDataImport.GetCellData(sSheetName, 1, iRow);
			String sOperation = TestDataImport.GetCellData(sSheetName, 2, iRow);
			sExpectedResult = TestDataImport.GetCellData(sSheetName, 3, iRow);
			aHeaderNumbers = null;
			aHeaderNames = null;
			aHeaderNumbers = new ArrayList();
			aHeaderNames = new ArrayList();
			if (sTestCaseID.equalsIgnoreCase(sActualTestCaseID)) {
				try {
					if (sTestCaseID.equalsIgnoreCase(sActualTestCaseID)) {
						// Click on Loads menu
						LoadsPage.eMenuLoads().click();
						System.out.println("Clicked on menu loads");
						Thread.sleep(5000);
						// Click on All tab
						LoadsPage.eAllTab().click();
						System.out.println("Clicked on all loads");
						Thread.sleep(5000);
						// CLick on columns button from right pane
						LoadsPage.eColumnPane().click();
						Thread.sleep(500);
						sActualResult = "Columns not found";
						
						List<WebElement> gridCol = driver.findElements(By.xpath("(//div[@class='ag-header-container']/div)[1]/div"));
						
						List<WebElement> eColumns = driver.findElements(
								By.xpath("//*[@id=\"myGrid\"]/div/div/div[2]/div[2]/div[2]/div[2]/div/div/div"));
						driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
						// uncheck all checkboxes
						
						int tempcnt =0;
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
							if (bSelected == true) {
								eCheckBox.click();
								tempcnt++;
							}
							if(tempcnt >=gridCol.size()) {
								break;
							}
							Actions action = new Actions(driver);
							action.sendKeys(Keys.ARROW_DOWN).build().perform();
							//Thread.sleep(500);

						}

						//
						// CLick on columns button from right pane
						LoadsPage.eColumnPane().click();
						Thread.sleep(500);
						Actions action = new Actions(driver);
						action.sendKeys(Keys.F5).build().perform();
						driver.navigate().refresh();
						Thread.sleep(5000);
						// click on columnspane
						LoadsPage.eColumnPane().click();
						Thread.sleep(500);
						eColumns = driver.findElements(
								By.xpath("//*[@id=\"myGrid\"]/div/div/div[2]/div[2]/div[2]/div[2]/div/div/div"));
						
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
								if (bSelected == false) {
									eCheckBox.click();
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
									if (sName.trim().equalsIgnoreCase(sData[i].trim())) {
										WebElement eCheckBox = eColumn.findElement(By.cssSelector(".css-yvbm2a"))
												.findElement(By.tagName("div"));
										try {
											WebElement eCheckboxSelectedsvg = eCheckBox.findElement(By.tagName("svg"));
											bSelected = eCheckboxSelectedsvg.isDisplayed();
										} catch (Exception child_error) {
											bSelected = false;
										}
										if (bSelected == false) {
											eCheckBox.click();
											iSelectedCnt++;
											
										}
										break;

									}
									//Thread.sleep(50);
									Actions action2 = new Actions(driver);
									action2.sendKeys(Keys.ARROW_DOWN).build().perform();
									//Thread.sleep(100);
								}
							}
							// click on columnspane
							LoadsPage.eColumnPane().click();
							Thread.sleep(500);
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

					}

				} catch (Exception error) {
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					bResult = false;
					sActualResult = error.getMessage();

				}
				ResultComparision();
				TestDataImport.setCellData(sSheetName, iRow, 4, sActualResult, "NA");
				TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
				TestDataImport.setCellData(sSheetName, iRow, 5, sTestStepStatus, "NA");

				break;
			}

		}

		return bResult;
	}

	// Method to handle Loads Webtable
	public static boolean LoadsWebTable(int iDataRow, String sActualTestCaseID) throws Exception {
		boolean bResult = false;
		String sFileName = "Loads.xlsx";
		String sSheetName = "View Load";
		sTestStepID = "LoadsWebTable";

		// Copy Loads.xlsx file from test data folder to current log folder
		Copy_File(sTestDataPath + sFileName, sTestResultsPath);

		TestDataImport.SetExcelFile(sTestDataPath, sFileName);
		int iRowCnt = 0;
		iRowCnt = TestDataImport.GetRowCount(sSheetName);
		System.out.println("Number of rows:" + iRowCnt);
		String sOperation = "VIEW";
		for (int iRow = 1; iRow <= iRowCnt; iRow++) {

			TestDataImport.SetExcelFile(sTestDataPath, sFileName);
			String sTestCaseID = TestDataImport.GetCellData(sSheetName, 0, iRow);
			String sCarrier = TestDataImport.GetCellData(sSheetName, 4, iRow);
			String sLoadDate = TestDataImport.GetCellData(sSheetName, 1, iRow);
			String sSHipper = TestDataImport.GetCellData(sSheetName, 2, iRow);
			if(sSHipper.equalsIgnoreCase("New Shipper"))
			{
				sSHipper=sGenericShipperName;
			}
			String sSHipperContact = TestDataImport.GetCellData(sSheetName, 3, iRow);
			String sStatus = TestDataImport.GetCellData(sSheetName, 5, iRow);
			String sOrigin = TestDataImport.GetCellData(sSheetName, 6, iRow);
			String sDestination = TestDataImport.GetCellData(sSheetName, 7, iRow);
			String sRate = TestDataImport.GetCellData(sSheetName, 8, iRow);
			String sRateUOM = TestDataImport.GetCellData(sSheetName, 9, iRow);
			String sCommodity = TestDataImport.GetCellData(sSheetName, 10, iRow);
			String sReadytoSubmit = TestDataImport.GetCellData(sSheetName, 11, iRow);
			
			sExpectedResult = TestDataImport.GetCellData(sSheetName, 13, iRow);
			sTestStepData = sLoadDate + ";" + sSHipper + ";" + sSHipperContact + ";" + sCarrier + ";" + sStatus + ";"
					+ sOrigin + ";" ;//+ sDestination + ";" + sRate + ";" + sRateUOM + ";" + sCommodity + ";";
			if (sTestCaseID.trim().equalsIgnoreCase(sActualTestCaseID) && (iDataRow == iRow)) {
				try {
					ArrayList<String> aActualRecordCell = new ArrayList();
					;

					try {
						Thread.sleep(5000);
						driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
						driver.findElement(By.xpath(".//span[text()='Clear Filters']")).click();
						Thread.sleep(5000);
					} catch (Exception error_message) {

					}

					driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
					DateTimeFormatter dateandtime = DateTimeFormatter.ofPattern("MM/dd/yyyy");
					LocalDateTime t1 = LocalDateTime.now();
					sTestStepData = sTestStepData.replace("Current Date", t1.format(dateandtime));

					String sData[] = sTestStepData.split(";");
					
					sActualResult = "Record not found";
					Thread.sleep(5000);
					List<WebElement> eCheckBoxes = driver
							.findElements(By.xpath("//*[@id=\"myGrid\"]/div/div/div[2]/div[1]/div[3]/div[1]/div"));
					List<WebElement> eHeaderFilters = driver.findElements(
							By.xpath("//*[@id=\"myGrid\"]/div/div/div[2]/div[1]/div[1]/div[2]/div/div[2]/div"));
					System.out.println("Number of filters:" + eHeaderFilters.size());
					for (int i = 0; i < aHeaderNumbers.size(); i++) {
						int iFilterNum = aHeaderNumbers.get(i);
						System.out.println("Header Number from arraylist:" + iFilterNum);
						int iHeaderFilterCnt = 0;
						for (WebElement eHeaderFilter : eHeaderFilters) {
							iHeaderFilterCnt++;
							if (iHeaderFilterCnt == iFilterNum) {
								if (!(sData[i].equalsIgnoreCase("NA"))) {
									eHeaderFilter.findElement(By.tagName("input")).clear();
									Thread.sleep(2000);
									eHeaderFilter.findElement(By.tagName("input")).sendKeys(sData[i]);
									Actions acton = new Actions(driver);
									acton.sendKeys(Keys.ENTER).build().perform();
									Thread.sleep(3000);

								}

								break;
							}
						}
					}

					Thread.sleep(5000); 
					eCheckBoxes = driver
							.findElements(By.xpath("//*[@id=\"myGrid\"]/div/div/div[2]/div[1]/div[3]/div[1]/div"));
					List<WebElement> eRows = driver.findElements(
							By.xpath("//*[@id=\"myGrid\"]/div/div/div[2]/div[1]/div[3]/div[2]/div/div/div"));
					int iRow1 = 0;
					for (WebElement eRow : eRows) {
						iRow1++;
						List<WebElement> eCols = eRow.findElements(
								By.xpath("//*[@id=\"myGrid\"]/div/div/div[2]/div[1]/div[3]/div[2]/div/div/div[" + iRow1
										+ "]/div"));
						System.out.println("Number of cols in AG grid:" + eCols.size());
					
						for (WebElement eCol : eCols) {
							String sValue = eCol.getText();
							try {
								driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
								sValue = eCol.findElement(By.tagName("svg")).findElement(By.tagName("path"))
										.getAttribute("fill");
								System.out.println("sValue:" + sValue);
								if (sValue.equals("#B1C82C")) {
									sValue = "GREEN";
									Actions action = new Actions(driver);
									action.moveToElement(eCol).build().perform();
									Thread.sleep(3000);
								} else {
									sValue = "NA";
								}
								driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

							} catch (Exception err) {
								driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

							}

							System.out.println("col text:" + sValue);
							if (sValue.equalsIgnoreCase("") || sValue.equals(null)) {
								sValue = "NA";

							}
							aActualRecordCell.add(sValue);
						}
						int iDisplayedcnt = 0;
						ArrayList<Integer> aMatchedIndex = new ArrayList();
						for (int i = 0; i < sData.length; i++) {

							for (int j = 0; j < aActualRecordCell.size(); j++) {
								
								if (aActualRecordCell.get(j).trim().equalsIgnoreCase(sData[i].trim())) {
									aMatchedIndex.add(j);
									iDisplayedcnt++;
									break;
								}
							}
						}
						// remove cells of this row from arraylist
						for (int i = 0; i < aActualRecordCell.size(); i++) {
							aActualRecordCell.remove(i);
						}

						System.out.println("iDisplayedcnt:" + iDisplayedcnt);
							int iCheckBoxcnt = 0;
							for (WebElement eCheckBox : eCheckBoxes) {
								iCheckBoxcnt++;
								if (iRow1 == iCheckBoxcnt) {
									eCheckBox.findElement(By.cssSelector(".ag-selection-checkbox")).click();
									Thread.sleep(2000);
									eCheckBox.findElement(By.cssSelector(".ag-cell-value")).click();
									Thread.sleep(2000);
									try {
										driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
										LoadsPage.eEdit().isDisplayed();

									} catch (Exception error_child) {
										eCheckBox.findElement(By.cssSelector(".ag-selection-checkbox")).click();
										driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
									}
									break;
								}
								break;
							}
							bResult = true;

							sActualResult = "Webtable validated successfully";
							break;
					
					}
					
				} catch (Exception error) {
					sActualResult = error.getMessage();
					error.printStackTrace();
					throw error;

				}
				ResultComparision();
				TestDataImport.setCellData(sSheetName, iRow, 13, sActualResult, "NA");
				TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
				TestDataImport.setCellData(sSheetName, iRow, 14, sTestStepStatus, "NA");
				break;
			}

		}

		System.out.println("Loads Webtble:" + bResult);
		return bResult;
	}

	public static boolean uploadDoc(String sDocType) {
		boolean bResult = false;
		try {
			Thread.sleep(2000);
			List<WebElement> eDocs = driver.findElements(By.xpath(".//*[@class='css-1vcualq ex37wus3']"));
			String sExpectedResult = "Uploaded Document";
			System.out.println("Number of docs:" + eDocs.size());
			for (WebElement eDoc : eDocs) {
				String sTitle = eDoc.findElement(By.cssSelector(".title")).getText();
				System.out.println("Doc Ttile:" + sTitle);
				if (sTitle.trim().equalsIgnoreCase(sDocType)) {
					eDoc.click();
					String sEXEFile = null;
					switch (sTitle.toUpperCase()) {
					case "ORIGIN TICKET":
						sEXEFile = "Origin";
						break;
					case "DEST. TICKET":
						sEXEFile = "Destination";
						break;

					}
					WindowsHandle(sEXEFile);
					Thread.sleep(15000);
					WebDriverWait wait = new WebDriverWait(driver, 10);
					wait.until(ExpectedConditions.visibilityOf(eDoc.findElement(By.tagName("a"))));
					bResult = true;

					break;
				}
			}
			if (bResult == true) {
				sActualResult = "Uploaded document";
			}

		} catch (Exception error) {
			sActualResult = error.getMessage();

		}

		return bResult;

	}

	// ____________________________Method to handle windows through AutoIT
	// tool______________________________________
	protected static void WindowsHandle(String sFileName) throws Exception {
		try {
			Thread.sleep(2000);
			
			Runtime.getRuntime().exec(sTestDataPath + "Docs//" + sFileName + ".exe");
			sActualResult = "Handled windows";

		} catch (Exception Error_Message) {
			sActualResult = Error_Message.getMessage();

		}
	}


	public static boolean uploadOriginTicket(String sDocType) {
		boolean bResult = false;
		try {
			//Thread.sleep(1000);

			driver.findElement(By.xpath("(//div[@class='images'])[1]")).click();
			WindowsHandle(sDocType);
			Thread.sleep(10000);
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.tagName("a"))));
			bResult = true;

		} catch (Exception error) {
			sActualResult = error.getMessage();
			System.out.println(sActualResult);
		}

		return bResult;

	}

	public static boolean uploadDestTicket(String sDocType) {
		// TODO Auto-generated method stub
		boolean bResult = false;
		try {
			//Thread.sleep(1000);

			driver.findElement(By.xpath("(//div[@class='images'])[2]")).click();
			WindowsHandle(sDocType);
			Thread.sleep(10000);
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.tagName("a"))));
			bResult = true;

		} catch (Exception error) {
			sActualResult = error.getMessage();
			System.out.println(sActualResult);
		}

		return bResult;
	}

	public static void GetInvoiceNumber() {
		// TODO Auto-generated method stub
		try
		{
			Thread.sleep(1000);
			invoiceNumber = driver.findElement(By.xpath("//input[@id='invoice-number']")).getAttribute("value");
			
		}
		catch(Exception e)
		{
			invoiceNumber="NA";
		}
		sInvoiceNumber = invoiceNumber;

		if (sInvoiceNumber.equalsIgnoreCase("") || sInvoiceNumber.equals(null)) {
			sInvoiceNumber = "NA";
			invoiceNumber="NA";
		}
		System.out.println("Invoice Number " + sInvoiceNumber);
	}

	public static void PaidInvoice() throws InterruptedException {
		// TODO Auto-generated method stub
		LoadsPage.status().sendKeys("paid");
		Actions action1 = new Actions(driver);
		//Thread.sleep(2000);
		action1.sendKeys(Keys.ENTER).build().perform();
		Thread.sleep(1000);
		LoadsPage.eSave().click();
		Thread.sleep(2000);
	}
	public static void VerifyStatus(String eStatus) throws InterruptedException {
		// TODO Auto-generated method stub
		Thread.sleep(1000);
		try {
			driver.findElement(By.xpath("//div[@class='react-datepicker-wrapper']//img")).click();}
			catch(Exception error) {
				//System.out.println(error);
			}
		driver.findElement(By.xpath("((//div[@class='ag-header-container']//div[@class='ag-header-row'])[2]/div//input)[2]")).clear();
		driver.findElement(By.xpath("((//div[@class='ag-header-container']//div[@class='ag-header-row'])[2]/div//input)[2]")).sendKeys(invoiceNumber);
		System.out.println("Invoice Number Entered");
		//Thread.sleep(500);
		Actions action1 = new Actions(driver);
		action1.sendKeys(Keys.ENTER).build().perform();
		Thread.sleep(500);
		
		String aStatus = driver.findElement(By.xpath("//div[@class='ag-center-cols-container']//div[@col-id='status']/div")).getText();
		System.out.println("Status : " + aStatus);
		AssertJUnit.assertEquals(eStatus, aStatus);
		
	}
	
	public static boolean customizeAGgrid(String sActualTestCaseID, int rCount) throws Exception {
		boolean bResult = false;

		boolean bSelected = false;
		String sFileName = "Loads.xlsx";
		String sSheetName = "CustomizeGrid";
		Loads.HideGeoLocationPane();
		// Copy Loads.xlsx file from test data folder to current log folder
		Copy_File(sTestDataPath + sFileName, sTestResultsPath);

		TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
		int iRowCnt = 0;
		iRowCnt = TestDataImport.GetRowCount(sSheetName);
		for (int iRow = 1; iRow <= iRowCnt; iRow++) {
			TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
			String sTestCaseID = TestDataImport.GetCellData(sSheetName, 0, iRow);
			sTestStepData = TestDataImport.GetCellData(sSheetName, 1, iRow);
			String sOperation = TestDataImport.GetCellData(sSheetName, 2, iRow);
			sExpectedResult = TestDataImport.GetCellData(sSheetName, 3, iRow);
			if (sTestCaseID.equalsIgnoreCase(sActualTestCaseID) && rCount == iRow) {
				try {
					if (sTestCaseID.equalsIgnoreCase(sActualTestCaseID) && rCount == iRow) {
						Thread.sleep(2000);
						// CLick on columns button from right pane
						LoadsPage.eColumnPane().click();
						Thread.sleep(1000);
						sActualResult = "Columns not found";
						List<WebElement> eColumns = driver.findElements(
								By.xpath("//*[@id=\"myGrid\"]/div/div/div[2]/div[2]/div[2]/div[2]/div/div/div"));
						driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
						// uncheck all checkboxes
						List<WebElement> gridCol = driver.findElements(By.xpath("(//div[@class='ag-header-container']/div)[1]/div"));
						int tempcnt =0;
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
							if (bSelected == true) {
								eCheckBox.click();
								tempcnt++;
							}
							if(tempcnt >=gridCol.size()) {
								break;
							}
							Actions action = new Actions(driver);
							action.sendKeys(Keys.ARROW_DOWN).build().perform();
							//Thread.sleep(50);

						}

						//
						// CLick on columns button from right pane
						LoadsPage.eColumnPane().click();
						Thread.sleep(2000);
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
								if (bSelected == false) {
									eCheckBox.click();
									Thread.sleep(50);

								}
								Actions action2 = new Actions(driver);
								action2.sendKeys(Keys.ARROW_DOWN).build().perform();
								//Thread.sleep(100);
							}

							bResult = true;
							break;
						case "SELECT":

							String[] sData = sTestStepData.split(";");
							int iSelectedCnt = 0;
							for (int i = 0; i < sData.length; i++) {
								for (WebElement eColumn : eColumns) {
									String sName = eColumn.findElement(By.tagName("span")).getText();
									if (sName.trim().equalsIgnoreCase(sData[i].trim())) {
										WebElement eCheckBox = eColumn.findElement(By.cssSelector(".css-yvbm2a"))
												.findElement(By.tagName("div"));
										try {
											WebElement eCheckboxSelectedsvg = eCheckBox.findElement(By.tagName("svg"));
											bSelected = eCheckboxSelectedsvg.isDisplayed();
										} catch (Exception child_error) {
											bSelected = false;
										}
										if (bSelected == false) {
											eCheckBox.click();
											iSelectedCnt++;
										
										}
										break;

									}
									Thread.sleep(50);
									Actions action2 = new Actions(driver);
									action2.sendKeys(Keys.ARROW_DOWN).build().perform();
									//Thread.sleep(100);
								}
							}
							// click on columnspane
							LoadsPage.eColumnPane().click();
							Thread.sleep(2000);
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
							driver.manage().timeouts().implicitlyWait(05, TimeUnit.SECONDS);

							break;

						}

					}

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

		}

		return bResult;
	}

	public static void SelectRecord() throws InterruptedException {
		// TODO Auto-generated method stub
		Thread.sleep(2000);
		try {
			driver.findElement(By.xpath("(//div[@col-id='first_column']/div/span/span)[1]")).click();
			Thread.sleep(1000);
		}catch(Exception e) {
			
		}
		
		if(driver.findElement(By.xpath("//div[@col-id='first_column']/div/span/span[2]")).isSelected()) {
			System.out.println("Record already is selected");
			Thread.sleep(1500);
		} else {
			driver.findElement(By.xpath("//div[@col-id='first_column']/div/span/span[2]")).click();
			System.out.println("Record is selected");
			Thread.sleep(1500);
		}
	}

	public static void EnterCopyDetails(String sActTestCaseID, String iOperation) throws Exception {
		// TODO Auto-generated method stub
		String sFileName = "Loads.xlsx";
		String sSheetName = "Duplicate Load";

		// Copy Loads.xlsx file from test data folder to current log folder
		Copy_File(sTestDataPath + sFileName, sTestResultsPath);

		TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
		int iRowCnt = 0;
		iRowCnt = TestDataImport.GetRowCount(sSheetName);
		for (int iRow = 1; iRow <= iRowCnt; iRow++) {
			TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
			String sTestCaseID = TestDataImport.GetCellData(sSheetName, 0, iRow);
			String sCopy = TestDataImport.GetCellData(sSheetName, 1, iRow);
			String sOperation = TestDataImport.GetCellData(sSheetName, 2, iRow);
			sExpectedResult = TestDataImport.GetCellData(sSheetName, 3, iRow);

			if(sActTestCaseID.equalsIgnoreCase(sTestCaseID) && iOperation.equalsIgnoreCase(sOperation)) {
				//Thread.sleep(1000);
				driver.findElement(By.xpath("//input[@id='Copies']")).clear();
				driver.findElement(By.xpath("//input[@id='Copies']")).sendKeys(sCopy);
				//Thread.sleep(1000);
				LoadsPage.SubmitDuplicateCopy().click();
				Thread.sleep(2000);

				switch(sOperation) {
					
					case "Open":
						System.out.println("Open");
						driver.findElement(By.xpath("//div[@class='react-datepicker-wrapper']//img")).click();
						Thread.sleep(2000);
						List<WebElement> duplicateCount = driver.findElements(By.xpath("//div[@col-id='first_column']/div/span/span[2]"));
						if(duplicateCount.size() == (Integer.parseInt(sCopy) +1)) {
							sActualResult = "Record Dulicated Successfully";
						} else {
							sActualResult = "Record failed Dulicate";
						}
						break;
						
					case "Submitted":
						System.out.println("Submitted");
						Thread.sleep(2000);
						driver.findElement(By.xpath(".//p[./text()='All']")).click();
						Thread.sleep(2000);
						try {
							driver.findElement(By.xpath("//div[@class='react-datepicker-wrapper']//img")).click();}
							catch(Exception error) {
								System.out.println(error);
							}
						Thread.sleep(2000);
						List<WebElement> duplicateCount2 = driver.findElements(By.xpath("//div[@col-id='first_column']/div/span/span[2]"));
						if(duplicateCount2.size() >= Integer.parseInt(sCopy)) {
							sActualResult = "Record Dulicated Successfully";
						} else {
							sActualResult = "Record failed Dulicate";
						}
						break;
						
					case "Paid":
						System.out.println("Paid");
						Thread.sleep(2000);
						driver.findElement(By.xpath(".//p[./text()='All']")).click();
						Thread.sleep(2000);
						
						try {
						driver.findElement(By.xpath("//div[@class='react-datepicker-wrapper']//img")).click();}
						catch(Exception error) {
							System.out.println(error);
						}
						Thread.sleep(2000);
						List<WebElement> duplicateCount3 = driver.findElements(By.xpath("//div[@col-id='first_column']/div/span/span[2]"));
						if(duplicateCount3.size() >= Integer.parseInt(sCopy)) {
							sActualResult = "Record Dulicated Successfully";
						} else {
							sActualResult = "Record failed Dulicate";
						}
						break;
				}
				ResultComparision();
				TestDataImport.setCellData(sSheetName, iRow, 4, sActualResult, "NA");
				TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
				TestDataImport.setCellData(sSheetName, iRow, 5, sTestStepStatus, "NA");
				break;
			}				
		}		
	}

	public static void SelectFirstRecord() throws InterruptedException {
		// TODO Auto-generated method stub
		Thread.sleep(1000);
		if(driver.findElement(By.xpath("(//div[@col-id='first_column']/div/span/span[2])[2]")).isSelected()) {
			System.out.println("Record already is selected");
		} else {
			driver.findElement(By.xpath("(//div[@col-id='first_column']/div/span/span[2])[2]")).click();
			System.out.println("Record is selected");
			Thread.sleep(2000);
		}
	}
	public static void ApprovedInvoice() throws InterruptedException {
		// TODO Auto-generated method stub
		Thread.sleep(3000);
		LoadsPage.status().sendKeys("Approved");
		Actions action1 = new Actions(driver);
		Thread.sleep(2000);
		action1.sendKeys(Keys.ENTER).build().perform();
		Thread.sleep(2000);
		LoadsPage.eSave().click();
		Thread.sleep(5000);
		System.out.println("Invoice Approved");
	}

	public static boolean LoadsWebTableForDispatch(int iDataRow, String sActualTestCaseID) throws Exception {
		boolean bResult = false;
		String sFileName = "Loads.xlsx";
		String sSheetName = "View Load";

		// Copy Loads.xlsx file from test data folder to current log folder
		Copy_File(sTestDataPath + sFileName, sTestResultsPath);

		TestDataImport.SetExcelFile(sTestDataPath, sFileName);
		int iRowCnt = 0;
		iRowCnt = TestDataImport.GetRowCount(sSheetName);
		System.out.println("Number of rows:" + iRowCnt);
		String sOperation = "VIEW";
		for (int iRow = 1; iRow <= iRowCnt; iRow++) {

			TestDataImport.SetExcelFile(sTestDataPath, sFileName);
			String sTestCaseID = TestDataImport.GetCellData(sSheetName, 0, iRow);
			String sCarrier = TestDataImport.GetCellData(sSheetName, 4, iRow);
			String sLoadDate = TestDataImport.GetCellData(sSheetName, 1, iRow);
			String sSHipper = TestDataImport.GetCellData(sSheetName, 2, iRow);
			String sSHipperContact = TestDataImport.GetCellData(sSheetName, 3, iRow);
			String sStatus = TestDataImport.GetCellData(sSheetName, 5, iRow);
			String sOrigin = TestDataImport.GetCellData(sSheetName, 6, iRow);
			String sDestination = TestDataImport.GetCellData(sSheetName, 7, iRow);
			String sRate = TestDataImport.GetCellData(sSheetName, 8, iRow);
			String sRateUOM = TestDataImport.GetCellData(sSheetName, 9, iRow);
			String sCommodity = TestDataImport.GetCellData(sSheetName, 10, iRow);
			String sReadytoSubmit = TestDataImport.GetCellData(sSheetName, 11, iRow);			

			if(sSHipper.equalsIgnoreCase("New Shipper")) {
				sSHipper=sGenericShipperName;
			}
			sExpectedResult = TestDataImport.GetCellData(sSheetName, 13, iRow);
			sTestStepData = sLoadDate + ";" + sSHipperContact + ";"+ sCarrier + ";"  + sStatus + ";"
					+ sOrigin + ";" + sDestination + ";" ;//+ sRate + ";" + sRateUOM + ";" + sCommodity + ";";  //";" + sSHipper ++ sCarrier + ";"
			
			if (sTestCaseID.trim().equalsIgnoreCase(sActualTestCaseID) && (iDataRow == iRow)) {
				try {
					ArrayList<String> aActualRecordCell = new ArrayList();
					;

					try {
						Thread.sleep(1000);
						driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
						driver.findElement(By.xpath(".//span[text()='Clear Filters']")).click();
						Thread.sleep(500);
					} catch (Exception error_message) {

					}

					driver.manage().timeouts().implicitlyWait(05, TimeUnit.SECONDS);
					DateTimeFormatter dateandtime = DateTimeFormatter.ofPattern("MM/dd/yyyy");
					LocalDateTime t1 = LocalDateTime.now();
					sTestStepData = sTestStepData.replace("Current Date", t1.format(dateandtime));

					String sData[] = sTestStepData.split(";");
					
					sActualResult = "Record not found";
					List<WebElement> eCheckBoxes = driver
							.findElements(By.xpath("//*[@id=\"myGrid\"]/div/div/div[2]/div[1]/div[3]/div[1]/div"));
					List<WebElement> eHeaderFilters = driver.findElements(
							By.xpath("//*[@id=\"myGrid\"]/div/div/div[2]/div[1]/div[1]/div[2]/div/div[2]/div"));
					System.out.println("Number of filters:" + eHeaderFilters.size());
					for (int i = 0; i < aHeaderNumbers.size(); i++) {
						int iFilterNum = aHeaderNumbers.get(i);
						System.out.println("Header Number from arraylist:" + iFilterNum);
						int iHeaderFilterCnt = 0;
						for (WebElement eHeaderFilter : eHeaderFilters) {
							iHeaderFilterCnt++;
							if (iHeaderFilterCnt == iFilterNum) {
								if (!(sData[i].equalsIgnoreCase("NA"))) {
									eHeaderFilter.findElement(By.tagName("input")).clear();
									//Thread.sleep(100);
									eHeaderFilter.findElement(By.tagName("input")).sendKeys(sData[i]);
									Actions acton = new Actions(driver);
									acton.sendKeys(Keys.ENTER).build().perform();
									Thread.sleep(500);
								}
								break;
							}
						}
					}
					Thread.sleep(1000); 
					eCheckBoxes = driver
							.findElements(By.xpath("//*[@id=\"myGrid\"]/div/div/div[2]/div[1]/div[3]/div[1]/div"));
					List<WebElement> eRows = driver.findElements(
							By.xpath("//*[@id=\"myGrid\"]/div/div/div[2]/div[1]/div[3]/div[2]/div/div/div"));
					
					int iRow1 = 0;
					for (WebElement eRow : eRows) {
						iRow1++;
						List<WebElement> eCols = eRow.findElements(
								By.xpath("//*[@id=\"myGrid\"]/div/div/div[2]/div[1]/div[3]/div[2]/div/div/div[" + iRow1
										+ "]/div"));
						System.out.println("Number of cols in AG grid:" + eCols.size());
						

						for (WebElement eCol : eCols) {
							String sValue = eCol.getText();
							try {
								driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
								sValue = eCol.findElement(By.tagName("svg")).findElement(By.tagName("path"))
										.getAttribute("fill");
								System.out.println("sValue:" + sValue);
								if (sValue.equals("#B1C82C")) {
									sValue = "GREEN";
									Actions action = new Actions(driver);
									action.moveToElement(eCol).build().perform();
									Thread.sleep(500);
								} else {
									sValue = "NA";
								}
								driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

							} catch (Exception err) {
								driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

							}

							System.out.println("col text:" + sValue);
							if (sValue.equalsIgnoreCase("") || sValue.equals(null)) {
								sValue = "NA";

							}
							aActualRecordCell.add(sValue);
						}
						int iDisplayedcnt = 0;
						ArrayList<Integer> aMatchedIndex = new ArrayList();
						for (int i = 0; i < sData.length; i++) {

							for (int j = 0; j < aActualRecordCell.size(); j++) {
								
								if (aActualRecordCell.get(j).trim().equalsIgnoreCase(sData[i].trim())) {
									aMatchedIndex.add(j);
									iDisplayedcnt++;
									break;
								}
							}
						}
						// remove cells of this row from arraylist
						for (int i = 0; i < aActualRecordCell.size(); i++) {
							aActualRecordCell.remove(i);
						}
						System.out.println("iDisplayedcnt:" + iDisplayedcnt);
							int iCheckBoxcnt = 0;
							for (WebElement eCheckBox : eCheckBoxes) {
								iCheckBoxcnt++;
								if (iRow1 == iCheckBoxcnt) {
									eCheckBox.findElement(By.cssSelector(".ag-selection-checkbox")).click();
									Thread.sleep(200);
									eCheckBox.findElement(By.cssSelector(".ag-cell-value")).click();
									Thread.sleep(200);
									try {
										driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
										LoadsPage.eEdit().isDisplayed();

									} catch (Exception error_child) {
										eCheckBox.findElement(By.cssSelector(".ag-selection-checkbox")).click();
										driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
									}
									break;
								}
								break;
							}
							bResult = true;

							sActualResult = "Webtable validated successfully";
							break;
					}
					
				} catch (Exception error) {
					sActualResult = error.getMessage();
					throw error;

				}
				ResultComparision();
				TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
				TestDataImport.setCellData(sSheetName, iRow, 13, sActualResult, "NA");
				TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
				TestDataImport.setCellData(sSheetName, iRow, 14, sTestStepStatus, "NA");
				break;
			}

		}

	   System.out.println("Loads Webtble:"+bResult);
		return bResult;
	}

	public static boolean ViewPDF(String sActTestCaseID) throws Exception {						
		boolean bResult = false;
		String sFileName = "Loads.xlsx";
		String sSheetName = "ViewPDF";
		TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
		int iRowCnt = 0;
		iRowCnt = TestDataImport.GetRowCount(sSheetName);
		for (int iRow = 1; iRow <= iRowCnt; iRow++) {
			TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
			String sTestCaseID = TestDataImport.GetCellData(sSheetName, 0, iRow);
			String sObject = TestDataImport.GetCellData(sSheetName, 1, iRow);
			String sValue = TestDataImport.GetCellData(sSheetName, 2, iRow);
			sExpectedResult = TestDataImport.GetCellData(sSheetName, 3, iRow);

			if(sActTestCaseID.equalsIgnoreCase(sTestCaseID)) {
				
				//Thread.sleep(1000);
				driver.findElement(By.xpath(".//span[./text()='View PDF']")).click();
				Thread.sleep(1000);

				//Download PDF
				if(sObject.equalsIgnoreCase("DOWNLOAD")) {
					try {
						if(!driver.findElement(By.xpath("//input[@value='download']")).isSelected()) {
							driver.findElement(By.xpath("//input[@value='download']")).click();	
						} 
						driver.findElement(By.xpath("//button/span[contains(text(),'Generate')]")).click();
						Thread.sleep(5000);
						sActualResult = "Download Successfully";
						bResult = true;
					} catch(Exception e) {
						System.out.println("Unable to Download PDF " + e);
						sActualResult = "Unable to Download PDF";
					}					
				}

				//Send Email
				if(sObject.equalsIgnoreCase("EMAIL")) {
					try {
						if(!driver.findElement(By.xpath("//input[@value='email']")).isSelected()) {
							driver.findElement(By.xpath("//input[@value='email']")).click();
						} 
						driver.findElement(By.xpath("//button/span[contains(text(),'Generate')]")).click();
						Thread.sleep(3000);
						sActualResult = "Email sent Successfully";
						bResult = true;
					} catch(Exception e) {
						System.out.println("Unable to Send Email " + e);
						sActualResult = "Unable to Send Email";
					}
				}
				ResultComparision();
				TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
				TestDataImport.setCellData(sSheetName, iRow, 13, sActualResult, "NA");
			}		
		}
		return bResult;
	}

	public static boolean ExportSelectedData(String sActTestCaseID) throws Exception {
		boolean bResult = false;
		String sFileName = "Loads.xlsx";
		String sSheetName = "ViewPDF";
		TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
		int iRowCnt = 0;
		iRowCnt = TestDataImport.GetRowCount(sSheetName);
		for (int iRow = 1; iRow <= iRowCnt; iRow++) {
			TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
			String sTestCaseID = TestDataImport.GetCellData(sSheetName, 0, iRow);
			String sObject = TestDataImport.GetCellData(sSheetName, 1, iRow);
			String sValue = TestDataImport.GetCellData(sSheetName, 2, iRow);
			sExpectedResult = TestDataImport.GetCellData(sSheetName, 3, iRow);

			if(sActTestCaseID.equalsIgnoreCase(sTestCaseID)) {
				
				//Thread.sleep(1000);
				driver.findElement(By.xpath(".//span[./text()='Export Selected Data']")).click();
				Thread.sleep(1000);

				//Download PDF
				if(sObject.equalsIgnoreCase("DOWNLOAD")) {
					try {
						if(!driver.findElement(By.xpath("//input[@value='download']")).isSelected()) {
							driver.findElement(By.xpath("//input[@value='download']")).click();	
						} 
						String formatText = driver.findElement(By.xpath("//div[@class='MuiInputBase-input MuiOutlinedInput-input']/div/div")).getText();
						System.out.println("Text " + formatText);
						if(!formatText.equalsIgnoreCase(sValue)) {
							driver.findElement(By.xpath("//div[@class='MuiInputBase-input MuiOutlinedInput-input']/div/div")).click();
							Actions ac = new Actions(driver);
							ac.sendKeys(Keys.ARROW_DOWN).build().perform();
							ac.sendKeys(Keys.ENTER).build().perform();
						}
						Thread.sleep(500);
						driver.findElement(By.xpath(".//span[./text()='Export Loads']")).click();
						Thread.sleep(3000);
						sActualResult = "Download Successfully";
						bResult = true;
					} catch(Exception e) {
						System.out.println("Unable to Download PDF " + e);
						sActualResult = "Unable to Download PDF";
					}					
				}

				//Send Email
				if(sObject.equalsIgnoreCase("EMAIL")) {
					try {
						if(!driver.findElement(By.xpath("//input[@value='email']")).isSelected()) {
							driver.findElement(By.xpath("//input[@value='email']")).click();
						} 
						String formatText = driver.findElement(By.xpath("//div[@class='MuiInputBase-input MuiOutlinedInput-input']/div/div")).getText();
						if(!formatText.equalsIgnoreCase(sValue)) {
							driver.findElement(By.xpath("//div[@class='MuiInputBase-input MuiOutlinedInput-input']/div/div")).click();
							Actions ac = new Actions(driver);
							ac.sendKeys(Keys.ARROW_DOWN).build().perform();
							ac.sendKeys(Keys.ENTER).build().perform();
						}
						Thread.sleep(500);
						driver.findElement(By.xpath(".//span[./text()='Export Loads']")).click();
						Thread.sleep(3000);
						sActualResult = "Email sent Successfully";
						bResult = true;
					} catch(Exception e) {
						System.out.println("Unable to Send Email " + e);
						sActualResult = "Unable to Send Email";
					}
				}
				ResultComparision();
				TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
				TestDataImport.setCellData(sSheetName, iRow, 5, sActualResult, "NA");
			}		
		}
		return bResult;
	}
	public static void ReturnedInvoice() throws InterruptedException {
		// TODO Auto-generated method stub
		Thread.sleep(3000);
		LoadsPage.status().sendKeys("Returned");
		Actions action1 = new Actions(driver);
		Thread.sleep(2000);
		action1.sendKeys(Keys.ENTER).build().perform();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='return-message']")).sendKeys("Load is returned to Carrier");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button//span[contains(text(),'Send')]")).click();
		Thread.sleep(2000);
		LoadsPage.eSave().click();
		Thread.sleep(4000);
		
	}

	public static boolean ImportFile(String sActTestCaseID,String sDocType) throws Exception {
		boolean bResult = false;
		Thread.sleep(2000);
		driver.findElement(By.xpath(".//span[./text()='Import']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(".//div[contains(text(),'Drop files or click to upload')]")).click();
		WindowsHandle(sDocType);
		Thread.sleep(15000);
		driver.findElement(By.xpath("//button//span[contains(text(),'Upload')]")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//button//span[contains(text(),'Verify')]")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("(.//span[./text()='Import'])[2]")).click();
		Thread.sleep(3000);
		try {
			driver.findElement(By.xpath(".//span[./text()='Finish']")).click();
			bResult = true;
		} catch(Exception ex) {
			 bResult = false;
		}
		Thread.sleep(5000);
		return bResult;
	}

	public static boolean addNewLoadWithFieldMapping(String sActualTestCaseID) throws Exception {
		boolean bResult = false;
		String sFileName = "FieldMapping.xlsx";
		String sSheetName = "FieldMapping";

		// Copy Loads.xlsx file from test data folder to current log folder
		Copy_File(sTestDataPath + sFileName, sTestResultsPath);

		TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
		int iRowCnt = 0;
		iRowCnt = TestDataImport.GetRowCount(sSheetName);
		for (int iRow = 1; iRow <= iRowCnt; iRow++) {

			TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
			String sTestCaseID = TestDataImport.GetCellData(sSheetName, 0, iRow);
			String sCarrier = TestDataImport.GetCellData(sSheetName, 1, iRow);
			String sLoadDate = TestDataImport.GetCellData(sSheetName, 2, iRow);
			String sSHipper = TestDataImport.GetCellData(sSheetName, 3, iRow);
			String sSHipperContact = TestDataImport.GetCellData(sSheetName, 4, iRow);
			String sCommodity = TestDataImport.GetCellData(sSheetName, 5, iRow);
			String sRate = TestDataImport.GetCellData(sSheetName, 6, iRow);
			String sRateUOM = TestDataImport.GetCellData(sSheetName, 7, iRow);
			String sOrigin = TestDataImport.GetCellData(sSheetName, 8, iRow);
			String sDestination = TestDataImport.GetCellData(sSheetName, 9, iRow);
			String sDestRef = TestDataImport.GetCellData(sSheetName, 10, iRow);
			String sOriginRef = TestDataImport.GetCellData(sSheetName, 11, iRow);
			String sTransID = TestDataImport.GetCellData(sSheetName, 12, iRow);
			sExpectedResult = TestDataImport.GetCellData(sSheetName, 13, iRow);
			if(sSHipper.equalsIgnoreCase("New Shipper"))
			{
			sSHipper=sGenericShipperName;
			}
			if (sTestCaseID.trim().equalsIgnoreCase(sActualTestCaseID.trim())) {
				System.out.println("inside if");
				try {

					DateTimeFormatter dateandtime = DateTimeFormatter.ofPattern("MM/d/yyyy");
					LocalDateTime t1 = LocalDateTime.now();
					sLoadDate = sLoadDate.replace("Current Date", t1.format(dateandtime));
					String[] currentDate = t1.format(dateandtime).split("/");
					String sDay = currentDate[1];
					LoadsPage.eMenuLoads().click();
					Thread.sleep(2000);
					System.out.println("Clicking on add new load");
					LoadsPage.eAddNewLoad().click();
					Thread.sleep(4000);
					Actions ac = new Actions(driver);
					try {
						LoadsPage.eListCarrier().sendKeys(sCarrier);
						ac.sendKeys(Keys.ENTER).build().perform();
					} catch (Exception error) {
					}

					Thread.sleep(2000);
					LoadsPage.LoadDatePicker(sDay);
					ac = new Actions(driver);
					ac.sendKeys(Keys.ENTER).build().perform();
					Thread.sleep(2000);
					System.out.println("Set Load Date");
					try {
						if (!(sSHipper.trim().equalsIgnoreCase("NA"))) {
							System.out.println("Insideshipper");
							LoadsPage.eShipper().sendKeys(sSHipper);
							Thread.sleep(3000);
							ac.sendKeys(Keys.ENTER).build().perform();
							Thread.sleep(2000);
						}
					} catch(Exception e) {

					}
					try {
						if (!(sSHipperContact.trim().equalsIgnoreCase("NA"))) {
							LoadsPage.eShipperContact().sendKeys(sSHipperContact);
							System.out.println("contact Enter");
							ac.sendKeys(Keys.ENTER).build().perform();
							Thread.sleep(2000);
						}
					} catch(Exception e) {
						System.out.println("Shipper Contact : "+ e);
					}

					if (!(sCommodity.trim().equalsIgnoreCase("NA"))) {
						LoadsPage.eCommodity().sendKeys(sCommodity);
						ac = new Actions(driver);
						ac.sendKeys(Keys.ENTER).build().perform();
						Thread.sleep(2000);
					}
					if (!(sRate.trim().equalsIgnoreCase("NA"))) {
						LoadsPage.eRate().sendKeys(sRate);
					}
					if (!(sOrigin.trim().equalsIgnoreCase("NA"))) {
						String datetime = new SimpleDateFormat("MMddhhmmss").format(new Date());
						sOrigin = sOrigin+"_" + datetime;
						TestDataImport.writeExcel(sTestDataPath,"Loads.xlsx", "View Load", sOrigin, 6, sTestCaseID);
						//TestDataImport.writeExcel(sTestResultsPath,"Loads.xlsx", "View Load", sOrigin, 6, sTestCaseID);
						LoadsPage.eOrigin().sendKeys(sOrigin);
					}

					if (!(sDestination.trim().equalsIgnoreCase("NA"))) {
						String datetime = new SimpleDateFormat("MMddhhmmss").format(new Date());
						sDestination = sDestination+"_" + datetime;
						//TestDataImport.writeExcel(sTestDataPath,"Loads.xlsx", "View Load", sDestination, 9, sTestCaseID);
						
						LoadsPage.eDestination().sendKeys(sDestination);
						driver.findElement(By.xpath("//*[@id='origin_weight']")).click();
					}

					if (!(sRateUOM.trim().equalsIgnoreCase("NA"))) {
						LoadsPage.eRateUOM().sendKeys(sRateUOM);
						ac = new Actions(driver);
						ac.sendKeys(Keys.ENTER).build().perform();
						Thread.sleep(2000);
					}

					List<WebElement> fieldmapping = driver.findElements(By.xpath("//*[@id='fields-to-map']/div"));
					for(WebElement fm: fieldmapping) {
						System.out.println("text: " + fm.getText());
						if(fm.getText().contains("Destination Reference #")) {
							LoadsPage.eDestRef().sendKeys(sDestRef);
							Thread.sleep(500);
							ac.sendKeys(Keys.ENTER).build().perform();
							Thread.sleep(500);
						} else if(fm.getText().contains("Origin Reference #")) {
							LoadsPage.eOriginRef().sendKeys(sOriginRef);
							Thread.sleep(500);
							ac.sendKeys(Keys.ENTER).build().perform();
							Thread.sleep(500);
						} else if(fm.getText().contains("Trans ID")) {
							LoadsPage.eTransID().sendKeys(sTransID);
							Thread.sleep(500);
							ac.sendKeys(Keys.ENTER).build().perform();
							Thread.sleep(500);
						}
					}
					
					LoadsPage.eSave().click();
					WebDriverWait wait = new WebDriverWait(driver, 30);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
							"//*[@id=\"myGrid\"]/div/div/div[2]/div[1]/div[1]/div[2]/div/div[1]/div[1]/div[3]/div")));
					sActualResult = "Added new load successfully";
					bResult = true;

				} catch (Exception error) {
					sActualResult = error.getMessage();
				}
				ResultComparision();
				TestDataImport.setCellData(sSheetName, iRow, 11, sActualResult, "NA");
				TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
				TestDataImport.setCellData(sSheetName, iRow, 12, sTestStepStatus, "NA");
				break;
			} else {
				sActualResult = "Testcase not found";
			}

		}
		System.out.println("Add load:" + sActualResult);
		return bResult;
	}
	
	public static void HideGeoLocationPane() throws InterruptedException {
		try {
		WebElement ele = driver.findElement(By.xpath("//div[@class='page']/div/div"));
		if(!ele.getCssValue("height").toString().equals("200px")) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].setAttribute('style', 'flex: 0 0 auto; position: relative; outline: none; height: 200px; display: flex;')", ele);
			Thread.sleep(2000);
		}
		} catch(Exception e) {
			System.out.println(e);
		}
	}

}

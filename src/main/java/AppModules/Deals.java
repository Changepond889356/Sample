package AppModules;

import Utils.GenericSkins;
import Utils.TestDataImport;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;

import PageObjects.*;

public class Deals extends GenericSkins {
	
	// MEthod to add a new user
		public static boolean addNewDeal(String sActualTestCaseID) throws Exception {

			boolean bResult = false;
			String sFileName = "Deals.xlsx";
			String sSheetName = "Add Deal";
			sTestStepID = "addNewDeal";

			// Copy Loads.xlsx file from test data folder to current log folder
			Copy_File(sTestDataPath + sFileName, sTestResultsPath);

			TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
			int iRowCnt = 0;
			iRowCnt = TestDataImport.GetRowCount(sSheetName);
			// System.out.println("Number of rows:"+iRowCnt);
			for (int iRow = 1; iRow <= iRowCnt; iRow++) {

				TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
				String sTestCaseID = TestDataImport.GetCellData(sSheetName, 0, iRow);
				String sDealName = TestDataImport.GetCellData(sSheetName, 1, iRow);
				String sCommodity = TestDataImport.GetCellData(sSheetName, 2, iRow);
				String sFromDate = TestDataImport.GetCellData(sSheetName, 3, iRow);
				String sToDate = TestDataImport.GetCellData(sSheetName, 4, iRow);
				String sRate = TestDataImport.GetCellData(sSheetName, 5, iRow);
				String sUOM = TestDataImport.GetCellData(sSheetName, 6, iRow);

				String sEquipmet = TestDataImport.GetCellData(sSheetName, 7, iRow);

				String sNoOfLoads = TestDataImport.GetCellData(sSheetName, 8, iRow);
				String sOrigin = TestDataImport.GetCellData(sSheetName, 9, iRow);
				String sDestination = TestDataImport.GetCellData(sSheetName, 10, iRow);
				sExpectedResult = TestDataImport.GetCellData(sSheetName, 11, iRow);
				System.out.println("Add Deal:" + "sTestCaseID:" + sTestCaseID + "sActualTestCaseID:" + sActualTestCaseID);
				if (sTestCaseID.trim().equalsIgnoreCase(sActualTestCaseID.trim())) {
					System.out.println("inside if");
					try {

						DateTimeFormatter dateandtime = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
						LocalDateTime t1 = LocalDateTime.now();
						String sCurrentDateTime = t1.format(dateandtime);
						DealsPage.eMenuDeals().click();
						Thread.sleep(2000);
						// click on drafts
						DealsPage.eDealsDraft().click();
						System.out.println("Clicking on add new deal");
						DealsPage.eAddDeal().click();
						Thread.sleep(4000);
						// set deal Name
						if (sDealName.trim().equalsIgnoreCase("AutoDeal")) {
							sDealName = sDealName + sCurrentDateTime.replace("/", "");
							sDealName = sDealName.replace(":", "");
							sDealName = sDealName.replace(" ", "");
							sGenericDealName = sDealName;

						}
						System.out.println("Deal name:" + sDealName);
						DealsPage.eDealName().sendKeys(sDealName);
						// set commodity
						DealsPage.eCommodity().click();
						Thread.sleep(2000);
						Actions ac = new Actions(driver);
						ac.sendKeys(sCommodity).build().perform();
						ac.sendKeys(Keys.ENTER).build().perform();
						Thread.sleep(2000);

						dateandtime = DateTimeFormatter.ofPattern("MM/dd/yyyy");
						t1 = LocalDateTime.now();
						sFromDate = sFromDate.replace("Current Date", t1.format(dateandtime));
						sToDate = sFromDate.replace("Current Date", t1.format(dateandtime));
						// DealsPage.eMenuDeals().click();
						Thread.sleep(2000);

						// set from date
						DealsPage.eFromDate().sendKeys(sFromDate);
						DealsPage.eFromDate().sendKeys(Keys.ENTER);
						// set Todate
						Thread.sleep(2000);
						DealsPage.eTodate().sendKeys(sToDate);
						DealsPage.eTodate().sendKeys(Keys.ENTER);
						Thread.sleep(2000);
						// set rate
						System.out.println("sRate:" + sRate);
						DealsPage.eRate().sendKeys(sRate);
						// set UOM
						DealsPage.eUOM().click();
						Thread.sleep(2000);
						ac = new Actions(driver);
						ac.sendKeys(sUOM).build().perform();
						Thread.sleep(3000);
						ac.sendKeys(Keys.ENTER).build().perform();
						Thread.sleep(2000);
						// set equipment type
						DealsPage.eEquipmetType().click();
						Thread.sleep(2000);
						ac = new Actions(driver);
						ac.sendKeys(sEquipmet).build().perform();
						ac.sendKeys(Keys.ENTER).build().perform();
						Thread.sleep(2000);
						// set load
						DealsPage.eNoOfLoads().sendKeys(sNoOfLoads);
						// set origin
						// DealsPage.eOrigin().click();
						// Thread.sleep(2000);
						System.out.println("sOrigin:" + sOrigin);
						ac = new Actions(driver);
						DealsPage.eOrigin().sendKeys(sOrigin);
						DealsPage.eOrigin().clear();
						for (int i = 0; i < sOrigin.length(); i++) {
							ac.sendKeys(sOrigin.substring(i, i + 1)).build().perform();
							Thread.sleep(100);
						}
						// DealsPage.eOrigin().clear();
						Thread.sleep(1000);
						// DealsPage.eOrigin().sendKeys(sOrigin);

						System.out.println("searching origin");
						Thread.sleep(2000);
						ac.sendKeys(Keys.ENTER).build().perform();
						Thread.sleep(2000);
						System.out.println("searched origin");
						// set Dest
						// DealsPage.eDestination().click();
						// Thread.sleep(2000);
						System.out.println("sDestination:" + sDestination);
						ac = new Actions(driver);
						// ac.sendKeys(sDestination).build().perform();
						DealsPage.eDestination().sendKeys(sDestination);
						DealsPage.eDestination().clear();
						for (int i = 0; i < sDestination.length(); i++) {
							ac.sendKeys(sDestination.substring(i, i + 1)).build().perform();
							;
							Thread.sleep(100);
						}

						// DealsPage.eDestination().clear();
						// DealsPage.eDestination().sendKeys(sDestination);
						Thread.sleep(4000);
						ac.sendKeys(Keys.ENTER).build().perform();
						Thread.sleep(2000);

						DealsPage.eNext().click();
						Thread.sleep(4000);
						System.out.println("clicked on Next button");
						DealsPage.eMenuDeals().click();
						Thread.sleep(2000);
						DealsPage.eDealsDraft().click();
						Thread.sleep(3000);
						List<WebElement> eDeals = driver
								.findElements(By.xpath("//*[@id=\"root\"]/div/div[3]/div[2]/div/div/div/div[3]/div/div"));
						System.out.println("No. of deals :" + eDeals.size());
						for (WebElement eDeal : eDeals) {
							String sActualDealName = eDeal.findElement(By.tagName("span")).getAttribute("title");
							System.out.println("DealName:" + sDealName);
							System.out.println("actual DealName:" + sActualDealName);
							if (sActualDealName.trim().equalsIgnoreCase(sDealName.trim())) {
								// bResult=true;
								sActualResult = "Added new Deal successfully";
								bResult = true;

								break;
							} else {
								sActualResult = "Unable to add new Deal";
								bResult = false;

							}
						}

					} catch (Exception error) {

						sActualResult = error.getMessage();
						throw error;
						// throw error;

					}
					ResultComparision();
					TestDataImport.setCellData(sSheetName, iRow, 12, sActualResult, "NA");
					TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
					TestDataImport.setCellData(sSheetName, iRow, 13, sTestStepStatus, "NA");
					break;
				} else {
					sActualResult = "Testcase not found";
				}

			}
			System.out.println("Add load:" + sActualResult);
			return bResult;
		}

	// MEthod to add a new user
	public static boolean EditDeal(String sActualTestCaseID) throws Exception {

		boolean bResult = false;
		String sFileName = "Deals.xlsx";
		String sSheetName = "Edit Deal";
		sTestStepID = "Edit deal";

		// Copy Loads.xlsx file from test data folder to current log folder
		Copy_File(sTestDataPath + sFileName, sTestResultsPath);

		TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
		int iRowCnt = 0;
		iRowCnt = TestDataImport.GetRowCount(sSheetName);
		// System.out.println("Number of rows:"+iRowCnt);
		for (int iRow = 1; iRow <= iRowCnt; iRow++) {

			TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
			String sTestCaseID = TestDataImport.GetCellData(sSheetName, 0, iRow);
			String sDealName = TestDataImport.GetCellData(sSheetName, 1, iRow);
			String sCommodity = TestDataImport.GetCellData(sSheetName, 2, iRow);
			String sFromDate = TestDataImport.GetCellData(sSheetName, 3, iRow);
			String sToDate = TestDataImport.GetCellData(sSheetName, 4, iRow);
			String sRate = TestDataImport.GetCellData(sSheetName, 5, iRow);
			String sUOM = TestDataImport.GetCellData(sSheetName, 6, iRow);

			String sEquipmet = TestDataImport.GetCellData(sSheetName, 7, iRow);

			String sNoOfLoads = TestDataImport.GetCellData(sSheetName, 8, iRow);
			String sOrigin = TestDataImport.GetCellData(sSheetName, 9, iRow);
			String sDestination = TestDataImport.GetCellData(sSheetName, 10, iRow);
			sExpectedResult = TestDataImport.GetCellData(sSheetName, 11, iRow);
			System.out.println("Add Deal:" + "sTestCaseID:" + sTestCaseID + "sActualTestCaseID:" + sActualTestCaseID);
			if (sTestCaseID.trim().equalsIgnoreCase(sActualTestCaseID.trim())) {
				System.out.println("inside if");
				try {

					DateTimeFormatter dateandtime = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
					LocalDateTime t1 = LocalDateTime.now();
					String sCurrentDateTime = t1.format(dateandtime);
					// set deal Name
					if (sDealName.trim().equalsIgnoreCase("AutoDeal")) {
						sDealName = sDealName + sCurrentDateTime.replace("/", "");
						sDealName = sDealName.replace(":", "");
						sDealName = sDealName.replace(" ", "");
						sGenericDealName = sDealName;

					}
					System.out.println("Deal name:" + sDealName);
					//DealsPage.eDealName().clear();
					DealsPage.eDealName().sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
					Thread.sleep(2000);
					DealsPage.eDealName().sendKeys(sDealName);
					// set commodity
					//DealsPage.eCommodity().clear();
					Thread.sleep(2000);
					
					DealsPage.eCommodity();
					Thread.sleep(2000);
					Actions ac = new Actions(driver);
					//ac.sendKeys(Keys.CONTROL+"a").build().perform();
					Thread.sleep(1000);
					ac.sendKeys(sCommodity).build().perform();
					Thread.sleep(1000);
					ac.sendKeys(Keys.ENTER).build().perform();
					Thread.sleep(2000);

					dateandtime = DateTimeFormatter.ofPattern("MM/dd/yyyy");
					t1 = LocalDateTime.now();
					sFromDate = sFromDate.replace("Current Date", t1.format(dateandtime));
					sToDate = sFromDate.replace("Current Date", t1.format(dateandtime));
					// DealsPage.eMenuDeals().click();
					Thread.sleep(2000);

					// set from date
					DealsPage.eFromDate().clear();
					Thread.sleep(1000);
					DealsPage.eFromDate().sendKeys(sFromDate);
					DealsPage.eFromDate().sendKeys(Keys.ENTER);
					// set Todate
					Thread.sleep(2000);
					DealsPage.eTodate().clear();
					Thread.sleep(1000);
					DealsPage.eTodate().sendKeys(sToDate);
					DealsPage.eTodate().sendKeys(Keys.ENTER);
					Thread.sleep(2000);
					// set rate
					System.out.println("sRate:" + sRate);
					//DealsPage.eRate().clear();
					DealsPage.eRate().sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
					Thread.sleep(1000);
					DealsPage.eRate().sendKeys(sRate);
					// set UOM
					DealsPage.eUOM().click();
					Thread.sleep(1000);
					//DealsPage.eUOM().clear();
					
					Thread.sleep(2000);
					ac = new Actions(driver);
					ac.sendKeys(sUOM).build().perform();
					Thread.sleep(3000);
					ac.sendKeys(Keys.ENTER).build().perform();
					Thread.sleep(2000);
					// set equipment type
					DealsPage.eEquipmetType().click();
					Thread.sleep(2000);
					ac = new Actions(driver);
					ac.sendKeys(Keys.BACK_SPACE).build().perform();
					Thread.sleep(1000);
					ac.sendKeys(sEquipmet).build().perform();
					ac.sendKeys(Keys.ENTER).build().perform();
					Thread.sleep(1000);
					// set load
					DealsPage.eNoOfLoads().clear();
					Thread.sleep(1000);
					DealsPage.eNoOfLoads().sendKeys(sNoOfLoads);
					// set origin
					// DealsPage.eOrigin().click();
					// Thread.sleep(2000);
					/*
					 * System.out.println("sOrigin:" + sOrigin); ac = new Actions(driver);
					 * DealsPage.eOrigin(); ac.sendKeys(Keys.CONTROL+"a").build().perform();
					 * Thread.sleep(1000); //DealsPage.eOrigin().clear(); for (int i = 0; i <
					 * sOrigin.length(); i++) { ac.sendKeys(sOrigin.substring(i, i +
					 * 1)).build().perform(); Thread.sleep(100); } // DealsPage.eOrigin().clear();
					 * Thread.sleep(1000); // DealsPage.eOrigin().sendKeys(sOrigin);
					 * 
					 * System.out.println("searching origin"); Thread.sleep(1000);
					 * ac.sendKeys(Keys.ENTER).build().perform(); Thread.sleep(1000);
					 * System.out.println("searched origin"); // set Dest //
					 * DealsPage.eDestination().click(); // Thread.sleep(2000);
					 * System.out.println("sDestination:" + sDestination); ac = new Actions(driver);
					 * // ac.sendKeys(sDestination).build().perform(); DealsPage.eDestination();
					 * //DealsPage.eDestination().clear();
					 * ac.sendKeys(Keys.CONTROL+"a").build().perform(); Thread.sleep(1000); for (int
					 * i = 0; i < sDestination.length(); i++) {
					 * ac.sendKeys(sDestination.substring(i, i + 1)).build().perform(); ;
					 * Thread.sleep(100); }
					 * 
					 * // DealsPage.eDestination().clear(); //
					 * DealsPage.eDestination().sendKeys(sDestination); Thread.sleep(1000);
					 * ac.sendKeys(Keys.ENTER).build().perform(); Thread.sleep(1000);
					 * 
					 */					DealsPage.eNext().click();
					Thread.sleep(2000);
					System.out.println("clicked on Next button");
					DealsPage.eMenuDeals().click();
					Thread.sleep(2000);
					DealsPage.eDealsDraft().click();
					Thread.sleep(3000);
					bResult=true;
					sActualResult="Edited deal successfully";
					} catch (Exception error) {

					sActualResult = error.getMessage();
					throw error;
					// throw error;

				}
				ResultComparision();
				TestDataImport.setCellData(sSheetName, iRow, 12, sActualResult, "NA");
				TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
				TestDataImport.setCellData(sSheetName, iRow, 13, sTestStepStatus, "NA");
				break;
			} else {
				sActualResult = "Testcase not found";
			}

		}
		System.out.println("Add load:" + sActualResult);
		return bResult;
	}

	// MEthod to handle deal widgets
	public static boolean DealWidget(String sActualTestCaseID) throws Exception {
		Thread.sleep(1000);
		boolean bResult = false;
		String sFileName = "Deals.xlsx";
		String sSheetName = "Deal Widget";
		WebElement eShare = null;
		WebElement eDuplicate = null;
		WebElement eWithDraw = null;
		WebElement eEdit = null;
		WebElement eDiscard = null;
		// Copy Loads.xlsx file from test data folder to current log folder
		Copy_File(sTestDataPath + sFileName, sTestResultsPath);

		TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
		int iRowCnt = 0;
		iRowCnt = TestDataImport.GetRowCount(sSheetName);
		System.out.println("Number of rows:" + iRowCnt);
		for (int iRow = 1; iRow <= iRowCnt; iRow++) {

			TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
			String sTestCaseID = TestDataImport.GetCellData(sSheetName, 0, iRow);
			String sDealName = TestDataImport.GetCellData(sSheetName, 1, iRow);
			if (sDealName.trim().equalsIgnoreCase("AutoDeal")) {
				sDealName = sGenericDealName;

			} else if (sDealName.trim().equalsIgnoreCase("AutoDeal(1)")) {
				sDealName = sGenericDealName + " (1)";

			} else if (sDealName.trim().equalsIgnoreCase("AutoDeal(2)")) {
				sDealName = sGenericDealName + " (2)";

			}

			String sTab = TestDataImport.GetCellData(sSheetName, 2, iRow);
			String sOperation = TestDataImport.GetCellData(sSheetName, 3, iRow);
			sExpectedResult = TestDataImport.GetCellData(sSheetName, 4, iRow);
			if (sTestCaseID.trim().equalsIgnoreCase(sActualTestCaseID)) {
				try {
					DealsPage.eMenuDeals().click();
					Thread.sleep(2000);
					switch (sTab.toUpperCase().trim()) {
					case "DRAFT":
						DealsPage.eDealsDraft().click();
						break;
					case "OPPORTUNITY":
						DealsPage.eDealsOpportunity().click();
						break;
					case "WITHDRAW":
						DealsPage.eDealsWithDrawTab().click();
						break;
					}

					Thread.sleep(3000);
					List<WebElement> eDeals = driver
							.findElements(By.xpath("//*[@id=\"root\"]/div/div[3]/div[2]/div/div/div/div[3]/div/div"));
					System.out.println("No. of deals :" + eDeals.size());
					sActualResult = "No deal found";
					for (WebElement eDeal : eDeals) {
						String sActualDealName = "";
						if (sTab.equalsIgnoreCase("Draft")) {
							sActualDealName = eDeal.findElement(By.tagName("span")).getAttribute("title");
						} else if (sTab.equalsIgnoreCase("Opportunity")) {
							List<WebElement> eSpans = eDeal.findElements(By.tagName("span"));
							System.out.println("NO of spans:" + eSpans.size());
							for (WebElement eSpan : eSpans) {
								sActualDealName = eSpan.getAttribute("title");
								System.out.println("Deal title:" + sActualDealName);
								if (!(sActualDealName.equals(null)) && !(sActualDealName.equals(""))) {
									sActualDealName = eSpan.getAttribute("title");
									break;
								}

							}
						} else if (sTab.equalsIgnoreCase("WithDraw")) {
							List<WebElement> eTitles = driver.findElements(By.xpath(".//div[@class=' css-1art6ly']"));
							System.out.println("NO of spans:" + eTitles.size());
							for (WebElement eSpan : eTitles) {
								sActualDealName = eSpan.getAttribute("title");
								System.out.println("Deal title:" + sActualDealName);
								if ((sActualDealName.equals(sDealName))) {
									//sActualDealName = eSpan.getAttribute("title");
									break;
								}

							}

							System.out.println("with draw DealName:" + sDealName);
						}

						System.out.println("DealName:" + sDealName);
						System.out.println("actual DealName:" + sActualDealName);
						System.out.println("sOperation:" + sOperation);
						if (sActualDealName.trim().equalsIgnoreCase(sDealName.trim())) {
							// bResult=true;
							// click on ...
							switch (sTab.toUpperCase().trim()) {
							case "DRAFT":
								eDeal.findElement(By.xpath(".//div[@class='css-cy1kem e2zx7mg0']/*[name()='svg']"))
										.click();
								break;
							case "OPPORTUNITY":
								eDeal.findElement(By.xpath(".//div[@class='css-ofjib6 e2zx7mg0']/*[name()='svg']"))
										.click();
								break;
							case "WITHDRAW":
								eDeal.findElement(By.xpath(".//div[@class='css-cy1kem e2zx7mg0']/*[name()='svg']"))
										.click();
								break;
							}

							System.out.println("clicked on dots");

							Thread.sleep(4000);
							List<WebElement> eOperationsList = driver.findElements(
									By.xpath(".//ul[@class='MuiList-root MuiMenu-list MuiList-padding']/div"));
							System.out.println("operation list size:" + eOperationsList.size());
							for (WebElement eOperation : eOperationsList) {
								String sOperName = eOperation.getText();
								System.out.println("sOperation name:" + sOperName);
								switch (sOperName.trim().toUpperCase()) {
								case "SHARE":
									eShare = eOperation;
									// bResult=true;
									break;
								case "DUPLICATE":
									eDuplicate = eOperation;
									// bResult=true;
									break;
								case "WITHDRAW":
									eWithDraw = eOperation;
									// bResult=true;
									break;
								case "EDIT":
									eEdit = eOperation;
									// bResult=true;
									break;
								case "DISCARD":
									eDiscard = eOperation;
									// bResult=true;
									break;

								}
							}

							switch (sOperation.trim().toUpperCase()) {
							case "SHARE":
								eShare.click();
								bResult = true;
								break;
							case "DUPLICATE":
								eDuplicate.click();
								bResult = true;
								break;

							case "WITHDRAW":
								eWithDraw.click();
								bResult = true;
								break;
							case "EDIT":
								eEdit.click();
								bResult = true;
								break;
							case "DISCARD":
								eDiscard.click();
								bResult = true;
								break;

							case "VIEW":
								bResult = true;
								// eShare = eOperation;
								break;

							}
							// bResult = true;

							break;
						} else {
							sActualResult = "Unable to find Deal";
							bResult = false;

						}

					}
				} catch (Exception error) {
					bResult = false;
					sActualResult = error.getMessage();
					//throw error;
				}
				Thread.sleep(2000);
				System.out.println("Deal widget bResult:" + bResult);
				if (bResult == true) {
					sActualResult = "Widget handled successfully";
				}
				ResultComparision();
				TestDataImport.setCellData(sSheetName, iRow, 5, sActualResult, "NA");
				TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
				TestDataImport.setCellData(sSheetName, iRow, 6, sTestStepStatus, "NA");
				Thread.sleep(3000);
				System.out.println(sActualResult);

				// return bResult;

			}

		}
		System.out.println("Deals||Deals Widget:" + bResult);
		return bResult;
	}

	// MEthod to Set duplicate
	public static boolean setDuplicate(String sActualTestCaseID) throws Exception {
		Thread.sleep(1000);
		boolean bResult = false;
		String sFileName = "Deals.xlsx";
		String sSheetName = "Duplicate";
		WebElement eShare = null;
		WebElement eDuplicate = null;
		// Copy Loads.xlsx file from test data folder to current log folder
		Copy_File(sTestDataPath + sFileName, sTestResultsPath);

		TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
		int iRowCnt = 0;
		iRowCnt = TestDataImport.GetRowCount(sSheetName);
		System.out.println("Number of rows:" + iRowCnt);
		for (int iRow = 1; iRow <= iRowCnt; iRow++) {

			TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
			String sTestCaseID = TestDataImport.GetCellData(sSheetName, 0, iRow);
			String sNoOfCopies = TestDataImport.GetCellData(sSheetName, 1, iRow);
			sExpectedResult = TestDataImport.GetCellData(sSheetName, 2, iRow);
			if (sTestCaseID.trim().equalsIgnoreCase(sActualTestCaseID)) {
				try {
					DealsPage.eCopies().sendKeys(sNoOfCopies);
					Thread.sleep(2000);
					DealsPage.eCopiesSubmit().click();
					Thread.sleep(5000);

					bResult = true;
					sActualResult = "Copies created successfully";
				} catch (Exception error) {
					sActualResult = error.getMessage();
					bResult = false;
				}
				ResultComparision();
				TestDataImport.setCellData(sSheetName, iRow, 2, sActualResult, "NA");
				TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
				TestDataImport.setCellData(sSheetName, iRow, 3, sTestStepStatus, "NA");
				break;
			}

		}
		Thread.sleep(5000);
		System.out.println("Deals||set duplicate:" + bResult);
		return bResult;
	}
}
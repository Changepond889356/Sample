package AppModules;

import Utils.EmailFunctions;
import Utils.GenericSkins;
import Utils.TestDataImport;

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

public class Users extends GenericSkins {

	// MEthod to add a new user
	public static boolean addUser(String sActualTestCaseID) throws Exception {

		boolean bResult = false;
		String sFileName = "Users.xlsx";
		String sSheetName = "AddUser";

		// Copy Loads.xlsx file from test data folder to current log folder
		Copy_File(sTestDataPath + sFileName, sTestResultsPath);

		TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
		int iRowCnt = 0;
		iRowCnt = TestDataImport.GetRowCount(sSheetName);
		System.out.println("Number of rows:" + iRowCnt);

		for (int iRow = 1; iRow <= iRowCnt; iRow++) {
			TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
			String sTestCaseID = TestDataImport.GetCellData(sSheetName, 0, iRow);
			String sFullName = TestDataImport.GetCellData(sSheetName, 1, iRow);
			String sEmail = TestDataImport.GetCellData(sSheetName, 2, iRow);
			String sPhone = TestDataImport.GetCellData(sSheetName, 3, iRow);
			String sRole = TestDataImport.GetCellData(sSheetName, 4, iRow);
			String sCarrier = TestDataImport.GetCellData(sSheetName, 5, iRow);
			String sRef = TestDataImport.GetCellData(sSheetName, 6, iRow);
			String sShipper = TestDataImport.GetCellData(sSheetName, 7, iRow);
			String sOperation = TestDataImport.GetCellData(sSheetName, 8, iRow);
			sExpectedResult = TestDataImport.GetCellData(sSheetName, 9, iRow);
			System.out.println("sRole:" + sRole);
			System.out.println("sCarrier:" + sCarrier);
			System.out.println("sEmail:" + sEmail);
			// System.out.println("class name:"+this.getClass().getName());
			// sTestCaseID = "TestCases."+sTestCaseID;
			if (sTestCaseID.equalsIgnoreCase(sActualTestCaseID)) {
				try {

					UserPage.eMenuUsers().click();
					UserPage.ebtnAddUser().click();
					if (!(sFullName.equalsIgnoreCase("NA"))) {
						UserPage.eFullName().sendKeys(sFullName);
					}
					if (!(sEmail.equalsIgnoreCase("NA"))) {
						UserPage.eEmail().sendKeys(sEmail);
					}
					if (!(sPhone.equalsIgnoreCase("NA"))) {

						UserPage.ePhone().sendKeys(sPhone);

					}
					if (!(sRole.equalsIgnoreCase("NA"))) {
						UserPage.eRole().sendKeys(sRole);
						Thread.sleep(2000);
						Actions ac = new Actions(driver);
						ac.sendKeys(Keys.ENTER).build().perform();

					}
					if (!(sCarrier.equalsIgnoreCase("NA"))) {
						UserPage.eCarrier().sendKeys(sCarrier);
						Thread.sleep(2000);
						Actions ac = new Actions(driver);
						ac.sendKeys(Keys.ENTER).build().perform();
					}
					if (!(sShipper.equalsIgnoreCase("NA"))) {
						UserPage.eShipper().sendKeys(sShipper);
						Thread.sleep(2000);
						Actions ac = new Actions(driver);
						ac.sendKeys(Keys.ENTER).build().perform();
					}

					UserPage.eInvite().click();
					sActualResult = "User Invited successfully";
					bResult = true;

				} catch (Exception error) {
					sActualResult = error.getMessage();
					// throw error;

				}
				TestDataImport.setCellData(sSheetName, iRow, 10, sActualResult, "NA");
				TestDataImport.setCellData(sSheetName, iRow, 11, sTestStepStatus, "NA");
				break;
			}
		}

		return bResult;

	}

	// Method to handle Users Webtable
	public static boolean UserWebTable(int iUserNum, String sActualTestCaseID) throws Exception {
		boolean bResult = false;
		String sFileName = "Users.xlsx";
		String sSheetName = "UsersGrid";

		// Copy Loads.xlsx file from test data folder to current log folder
		Copy_File(sTestDataPath + sFileName, sTestResultsPath);

		TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
		int iRowCnt = 0;
		iRowCnt = TestDataImport.GetRowCount(sSheetName);
		System.out.println("Number of rows:" + iRowCnt);

		for (int iRow = 1; iRow <= iRowCnt; iRow++) {
			TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
			String sTestCaseID = TestDataImport.GetCellData(sSheetName, 0, iRow);
			String sName = TestDataImport.GetCellData(sSheetName, 1, iRow);
			String sEmail = TestDataImport.GetCellData(sSheetName, 2, iRow);
			String sPhone = TestDataImport.GetCellData(sSheetName, 3, iRow);
			String sRole = TestDataImport.GetCellData(sSheetName, 4, iRow);
			String sCarrier = TestDataImport.GetCellData(sSheetName, 5, iRow);
			String sRef = TestDataImport.GetCellData(sSheetName, 6, iRow);
			String sShipper = TestDataImport.GetCellData(sSheetName, 7, iRow);
			String sOperation = TestDataImport.GetCellData(sSheetName, 8, iRow);
			sExpectedResult = TestDataImport.GetCellData(sSheetName, 9, iRow);

			// System.out.println("class name:"+this.getClass().getName());
			// sTestCaseID = "TestCases."+sTestCaseID;
			if (sTestCaseID.equalsIgnoreCase(sActualTestCaseID) && (iUserNum == iRow)) {
				try {
					String sActualName = "NA";
					String sACtualEmail = "NA";
					String sActualPhone = "NA";
					String sActualRole = "NA";
					String sActualCarrier = "NA";
					String sActualRef = "NA";
					String sActualShipper = "NA";
					// click User Menu
					UserPage.eMenuUsers().click();
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
					driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div/div[1]/button[1]/span[1]"))
							.click();
					// filter by Name
					driver.findElement(By.xpath(
							"//*[@id=\"myGrid\"]/div/div/div[2]/div[1]/div[1]/div[2]/div/div[2]/div[1]/div[1]/div/div/input"))
							.clear();
					driver.findElement(By.xpath(
							"//*[@id=\"myGrid\"]/div/div/div[2]/div[1]/div[1]/div[2]/div/div[2]/div[1]/div[1]/div/div/input"))
							.sendKeys(sName);
					WebElement eEmailFilter = driver.findElement(By.xpath(
							"//*[@id=\"myGrid\"]/div/div/div[2]/div[1]/div[1]/div[2]/div/div[2]/div[2]/div[1]/div/div/input"));
					eEmailFilter.clear();
					Thread.sleep(2000);
					eEmailFilter.sendKeys(sEmail);
					eEmailFilter.sendKeys(Keys.ENTER);
					System.out.println("loading...");
					Thread.sleep(8000);

					WebElement usergrid = driver.findElement(By.xpath(".//div[@role='grid']"));
					// take list of checkboxes from grid
					List<WebElement> eCheckBoxes = driver
							.findElements(By.xpath("//*[@id=\"myGrid\"]/div/div/div[2]/div[1]/div[3]/div[1]/div"));
					// take list of rows from grid
					List<WebElement> eRows = driver.findElements(
							By.xpath("//*[@id=\"myGrid\"]/div/div/div[2]/div[1]/div[3]/div[2]/div/div/div"));
					System.out
							.println("Number of rows:" + eRows.size() + " Numbner of checkboxes:" + eCheckBoxes.size());
					int iRow1 = 0;
					sActualResult = "Record not found";
					for (WebElement eRow : eRows) {
						iRow1++;
						System.out.println("Row number:" + iRow1);
						int icol = 0;
						List<WebElement> ecols = eRow.findElements(By.tagName("div"));
						System.out.println("Numnber of cols:" + ecols.size());
						for (WebElement eCOl : ecols) {
							icol++;
							switch (icol) {
							case 1:
								sActualName = eCOl.getText();
								if (sActualName.equalsIgnoreCase("") || sActualName.equalsIgnoreCase(null)) {
									sActualName = "NA";
								}
								break;
							case 2:
								sACtualEmail = eCOl.getText();
								if (sACtualEmail.equalsIgnoreCase("") || sACtualEmail.equalsIgnoreCase(null)) {
									sACtualEmail = "NA";
								}
								break;
							case 3:
								sActualPhone = eCOl.getText();
								if (sActualPhone.equalsIgnoreCase("") || sActualPhone.equalsIgnoreCase(null)) {
									sActualPhone = "NA";
								}
								break;
							case 4:
								sActualRole = eCOl.getText();
								if (sActualRole.equalsIgnoreCase("") || sActualRole.equalsIgnoreCase(null)) {
									sActualRole = "NA";
								}
								break;
							case 5:
								sActualCarrier = eCOl.getText();
								if (sActualCarrier.equalsIgnoreCase("") || sActualCarrier.equalsIgnoreCase(null)) {
									sActualCarrier = "NA";
								}
								break;

							case 6:
								sActualRef = eCOl.getText();
								if (sActualRef.equalsIgnoreCase("") || sActualRef.equalsIgnoreCase(null)) {
									sActualRef = "NA";
								}
								break;
							case 7:
								sActualShipper = eCOl.getText();
								if (sActualShipper.equalsIgnoreCase("") || sActualShipper.equalsIgnoreCase(null)) {
									sActualShipper = "NA";
								}
								break;
							}

							System.out.println("sActualName:" + sActualName + " Name:" + sName);
							System.out.println("sACtualEmail:" + sACtualEmail + " Email:" + sEmail);
							System.out.println("sActualPhone:" + sActualPhone + " Phone:" + sPhone);
							System.out.println("sActualRole:" + sActualRole + " Role:" + sRole);
							System.out.println("sActualCarrier:" + sActualCarrier + " Carrier:" + sCarrier);
							System.out.println("sActualRef:" + sActualRef + " Ref:" + sRef);
							System.out.println("sActualShipper:" + sActualShipper + " Shipper:" + sShipper);

							if (sActualName.trim().equalsIgnoreCase(sName.trim())
									&& sACtualEmail.trim().equalsIgnoreCase(sEmail.trim())
									&& sActualRole.trim().equalsIgnoreCase(sRole.trim())
									&& sActualCarrier.trim().equalsIgnoreCase(sCarrier.trim())
									&& sActualRef.trim().equalsIgnoreCase(sRef.trim())
									&& sActualShipper.trim().equalsIgnoreCase(sShipper.trim())
									&& sActualName.trim().equalsIgnoreCase(sName.trim())) {
								System.out.println("Record found");
								switch (sOperation.toUpperCase()) {
								case "VIEW NEW INVITE":
									int iCHeckBox = 0;
									System.out.println("Number of checkboxes:" + eCheckBoxes.size());
									for (WebElement eCheckBox : eCheckBoxes) {
										iCHeckBox++;
										System.out.println(iCHeckBox);
										System.out.println(iRow1);
										if (iCHeckBox == iRow1) {
											eCheckBox.findElement(By.cssSelector(".ag-selection-checkbox")).click();
											Thread.sleep(2000);
											// driver.findElement(By.xpath(".//span[text()='Delete']")).click();
											WebDriverWait wait = new WebDriverWait(driver, 10);
											wait.until(ExpectedConditions.visibilityOfElementLocated(
													By.xpath(".//span[text()='Cancel Invite']")));

											// Utils.EmailFunctions.getGmailData(sEmail, "");
											// verify email link
											/*
											 * Thread.sleep(20000); HashMap<String, String> hm =
											 * EmailFunctions.getGmailData(sEmail,
											 * "Subject: You're Invited to the Roger App");
											 * 
											 * System.out.println(hm.get("subject"));
											 * System.out.println("================="); String TokenLink =
											 * hm.get("link"); System.out.println("Token Link:" + TokenLink); sAUTPath =
											 * TokenLink; // TestActions.LaunchApplication();
											 */
											bResult = true;
											break;
										}
									}
									break;
								case "CANCEL INVITE":
									WebDriverWait wait = new WebDriverWait(driver, 10);
									wait.until(ExpectedConditions
											.visibilityOfElementLocated(By.xpath(".//span[text()='Cancel Invite']")));
									driver.findElement(By.xpath(".//span[text()='Cancel Invite']")).click();
									Thread.sleep(3000);
									driver.findElement(By.xpath(".//span[text()='Continue']")).click();
									bResult = true;
									break;
								case "RESEND INVITE":
									WebDriverWait wait1 = new WebDriverWait(driver, 10);
									wait1.until(ExpectedConditions
											.visibilityOfElementLocated(By.xpath(".//span[text()='Resend Invite']")));
									driver.findElement(By.xpath(".//span[text()='Resend Invite']")).click();
									System.out.println("clicked on resend invite");
									bResult = true;
									break;

								case "SELECT":
									int iCHeckBox11 = 0;
									System.out.println("Number of checkboxes:" + eCheckBoxes.size());
									for (WebElement eCheckBox : eCheckBoxes) {
										iCHeckBox11++;
										System.out.println(iCHeckBox11);
										System.out.println(iRow1);
										if (iCHeckBox11 == iRow1) {
											eCheckBox.findElement(By.cssSelector(".ag-selection-checkbox")).click();
											Thread.sleep(2000);
											// driver.findElement(By.xpath(".//span[text()='Delete']")).click();
											bResult = true;
											break;
										}
									}
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
					// throw error;

				}
				ResultComparision();
				TestDataImport.setCellData(sSheetName, iRow, 10, sActualResult, "NA");
				TestDataImport.setCellData(sSheetName, iRow, 11, sTestStepStatus, "NA");
				if (sTestStepStatus.equalsIgnoreCase("Passed"))
					;
				{
					bResult = true;
				}

			}
		}

		return bResult;
	}

}

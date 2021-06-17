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

public class Share extends GenericSkins {

	// MEthod to add a new user
	public static boolean ShareDeal(String sActualTestCaseID) throws Exception {

		boolean bResult = false;
		String sFileName = "ShareDeal.xlsx";
		String sSheetName = "Contacts";
		sTestStepID = "Share Deal";

		// Copy Loads.xlsx file from test data folder to current log folder
		Copy_File(sTestDataPath + sFileName, sTestResultsPath);

		TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
		int iRowCnt = 0;
		iRowCnt = TestDataImport.GetRowCount(sSheetName);
		// System.out.println("Number of rows:"+iRowCnt);
		// Thread.sleep(2000);
		for (int iRow = 1; iRow <= iRowCnt; iRow++) {

			TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
			String sTestCaseID = TestDataImport.GetCellData(sSheetName, 0, iRow);
			String sContactName = TestDataImport.GetCellData(sSheetName, 1, iRow);
			String sOperation = TestDataImport.GetCellData(sSheetName, 2, iRow);
			sExpectedResult = TestDataImport.GetCellData(sSheetName, 3, iRow);
			System.out.println("share deal:" + sTestCaseID);
			if (sTestCaseID.trim().equalsIgnoreCase(sActualTestCaseID)) {
				try {
					System.out.println("share deal operation:" + sOperation);
					switch (sOperation.toUpperCase().trim()) {
					case "ONE":
						if (!(sContactName.equalsIgnoreCase("NA"))) {
							SharePage.eSearch().sendKeys(sContactName);
							Thread.sleep(2000);
							/*
							 * Actions ac = new Actions(driver); ac.sendKeys(Keys.ENTER).build().perform();
							 * Thread.sleep(500); ac.sendKeys(Keys.BACK_SPACE).build().perform(); //
							 * SharePage.eSearch().sendKeys(Keys.ENTER);
							 */ Thread.sleep(500);
							SharePage.eContact().click();

						}

						bResult = true;
						Thread.sleep(500);
						break;
					case "ALL":
						driver.findElement(By.xpath(
								"//*[@id=\"root\"]/div/div[3]/div[2]/div/div/div/div/div[1]/div/div/div/label/span"))
								.click();
						Thread.sleep(500);
						bResult = true;
						break;
					case "RESHARE":
						System.out.println("Reshare");
						// driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div[2]/div/div/div/div/div[1]/div/div/div/label/span")).click();
						Thread.sleep(2000);
						List<WebElement> eContacts = driver.findElements(By.xpath(".//li[@class='css-3ddcky']"));
						for (WebElement ele : eContacts) {
							String sContactNameActual = ele.findElement(By.tagName("p")).getText();
							System.out.println("contact name:" + sContactNameActual);
							if (sContactNameActual.trim().equalsIgnoreCase(sContactName.trim())) {
								bResult = true;
								break;
							}
						}
						Thread.sleep(500);
						// bResult=true;
						break;

					}
					SharePage.eShare().click();
					if (sOperation.trim().equalsIgnoreCase("RESHARE")) {
						driver.findElement(By.xpath(".//*[text()='Reshare']")).click();
					}
					Thread.sleep(2000);
					if (bResult == true) {
						sActualResult = "Deal shared successfully";
					} else {
						sActualResult = "unable to share to contact";
					}
				} catch (Exception error) {

					sActualResult = error.getMessage();
					bResult = false;
					// throw error;
					// throw error;

				}
				ResultComparision();
				TestDataImport.setCellData(sSheetName, iRow, 4, sActualResult, "NA");
				TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
				TestDataImport.setCellData(sSheetName, iRow, 5, sTestStepStatus, "NA");
				break;

			}

		}
		System.out.println("share deal:" + sActualResult);
		return bResult;
	}
}
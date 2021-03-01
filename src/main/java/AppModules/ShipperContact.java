package AppModules;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import PageObjects.CarrierPage;
import PageObjects.LoadsPage;
import PageObjects.ShipperContactPage;
import PageObjects.ShippersPage;
import Utils.GenericSkins;
import Utils.TestDataImport;

public class ShipperContact  extends GenericSkins {

	public static void SelectShipperContactMenu() throws InterruptedException {

		GenericSkins.WaitForElementTobeClickable(By.xpath("*//span[contains(text(),'Shipper Contacts')]"));
		ShipperContactPage.ShipperContactMenu().click();
		GenericSkins.WaitForElementTobeClickable(By.xpath("*//span[contains(text(),'Add a Contact')]"));
		Thread.sleep(3000);
	}

	public static boolean AddShipperContact(String sActTestCaseID) throws Exception {
		boolean bResult = false;
		String sFileName = "ShipperContact.xlsx";
		String sSheetName = "Shipper Contact Details";
		Copy_File(sTestDataPath + sFileName, sTestResultsPath);
		TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
		int iRowCnt = 0;
		iRowCnt = TestDataImport.GetRowCount(sSheetName);
		// System.out.println("Number of rows:" + iRowCnt);

		for (int iRow = 1; iRow <= iRowCnt; iRow++) {
			TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
			String sTestCaseID = TestDataImport.GetCellData(sSheetName, 0, iRow);
			String sName = TestDataImport.GetCellData(sSheetName, 1, iRow);
			String sPhone = TestDataImport.GetCellData(sSheetName, 2, iRow);
			String sEmail = TestDataImport.GetCellData(sSheetName, 3, iRow);
			String sCompany = TestDataImport.GetCellData(sSheetName, 4, iRow);
			sExpectedResult = TestDataImport.GetCellData(sSheetName, 5, iRow);

			if (sTestCaseID.equalsIgnoreCase(sActTestCaseID)) {
				try {
					ShipperContactPage.addShipperContactBtn().click();
					Thread.sleep(2000);
					if(!sName.equalsIgnoreCase("NA")) {
						String datetime = new SimpleDateFormat("MMddhhmmss").format(new Date());
						sName = sName + "_" + datetime;
						System.out.println("sName "+ sName);
						TestDataImport.writeExcel(sTestDataPath,"ShipperContact.xlsx", "View Carrier Details", sName, 1, sTestCaseID);
						//TestDataImport.writeExcel(sTestResultsPath,"ShipperContact.xlsx", "View Carrier Details", sName, 1, sTestCaseID);
						ShipperContactPage.eName().sendKeys(sName);
					}
					if(!sPhone.equalsIgnoreCase("NA")) {
						ShipperContactPage.ePhone().sendKeys(sPhone);
					}
					if(!sEmail.equalsIgnoreCase("NA")) {
						ShipperContactPage.eEmail().sendKeys(sEmail);
					}
					try {
						ShipperContactPage.eCompany().sendKeys(sCompany);
						Actions action = new Actions(driver);
						action.sendKeys(Keys.ENTER).build().perform();
						Thread.sleep(1000);		
					}catch(Exception e) {

					}
					ShipperContactPage.eAddBtn().click();
					Thread.sleep(3000);		
					System.out.println("Shipper Contact Added Successfully");
					bResult = true;
				} catch(Exception e) {
					System.out.println();
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

	public static boolean customizeGrid(String sActualTestCaseID) throws Exception {
		boolean bResult = false;

		Thread.sleep(3000);
		String sFileName = "ShipperContact.xlsx";
		String sSheetName = "CustomizeGrid";
		// Copy Loads.xlsx file from test data folder to current log folder
		Copy_File(sTestDataPath + sFileName, sTestResultsPath);

		TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
		int iRowCnt = 0;
		iRowCnt = TestDataImport.GetRowCount(sSheetName);
		for (int iRow = 1; iRow <= iRowCnt; iRow ++) {
			TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
			String sTestCaseID = TestDataImport.GetCellData(sSheetName, 0, iRow);
			sTestStepData = TestDataImport.GetCellData(sSheetName, 1, iRow);
			String sOperation = TestDataImport.GetCellData(sSheetName, 2, iRow);
			sExpectedResult = TestDataImport.GetCellData(sSheetName, 3, iRow);
			try {
				if (sTestCaseID.equalsIgnoreCase(sActualTestCaseID)) {
					// CLick on columns button from right pane
					LoadsPage.eColumnPane().click();
					Thread.sleep(2000);
					sActualResult = "Columns not found";
					List<WebElement> eColumns = driver.findElements(
							By.xpath("//*[@id=\"myGrid\"]/div/div/div[2]/div[2]/div[2]/div[2]/div/div/div"));
					driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
					// uncheck all checkboxes

					for (WebElement eColumn : eColumns) {						
						try {
							if(eColumn.findElement(By.cssSelector(".css-yvbm2a")).findElement(By.tagName("svg")).isDisplayed()) {
								eColumn.findElement(By.cssSelector(".css-yvbm2a")).findElement(By.tagName("div")).click();
							}
						} catch(Exception e) {

						}
						Actions action = new Actions(driver);
						action.sendKeys(Keys.ARROW_DOWN).build().perform();
						//Thread.sleep(100);
					}
					System.out.println("Unchecked Done");
					// CLick on columns button from right pane
					LoadsPage.eColumnPane().click();
					Thread.sleep(2000);
					Actions action = new Actions(driver);
					action.sendKeys(Keys.F5).build().perform();
					driver.navigate().refresh();
					Thread.sleep(5000);
					// click on columnspane
					LoadsPage.eColumnPane().click();
					Thread.sleep(1000);
					eColumns = driver.findElements(
							By.xpath("//*[@id=\"myGrid\"]/div/div/div[2]/div[2]/div[2]/div[2]/div/div/div"));

					switch (sOperation.toUpperCase()) {

					case "SELECT":

						String[] sData = sTestStepData.split(";");
						int iSelectedCnt = 0;
						for (int i = 0; i < sData.length; i++) {
							for (WebElement eColumn : eColumns) {
								String sName = eColumn.findElement(By.tagName("span")).getText();								
								if(sName.trim().equalsIgnoreCase(sData[i].trim())) {
									try {
										eColumn.findElement(By.cssSelector(".css-yvbm2a")).findElement(By.tagName("div")).click();										
									} catch(Exception e) {
									}
									Actions action2 = new Actions(driver);
									action2.sendKeys(Keys.ARROW_DOWN).build().perform();
									//Thread.sleep(100);
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
			break;
		}

		return bResult;
	}

	public static boolean LoadWebTable(int iUserNum, String sActualTestCaseID) throws Exception {
		boolean bResult = false;
		String sFileName = "ShipperContact.xlsx";
		String sSheetName = "View Carrier Details";
		sTestStepID = "UserWebTable";
		System.out.println("Search record in shipper webtable");
		// Copy Loads.xlsx file from test data folder to current log folder
		Copy_File(sTestDataPath + sFileName, sTestResultsPath);

		TestDataImport.SetExcelFile(sTestDataPath, sFileName);
		int iRowCnt = 0;
		iRowCnt = TestDataImport.GetRowCount(sSheetName);
		System.out.println("Number of rows:" + iRowCnt);

		for (int iRow = 1; iRow <= iRowCnt; iRow++) {
			TestDataImport.SetExcelFile(sTestDataPath, sFileName);
			String sTestCaseID = TestDataImport.GetCellData(sSheetName, 0, iRow);
			String sName = TestDataImport.GetCellData(sSheetName, 1, iRow);
			String sEmail = TestDataImport.GetCellData(sSheetName, 2, iRow);
			String sOperation = TestDataImport.GetCellData(sSheetName, 6, iRow);
			String sStatus = TestDataImport.GetCellData(sSheetName, 5, iRow);
			sExpectedResult = TestDataImport.GetCellData(sSheetName, 7, iRow);

			if (sTestCaseID.equalsIgnoreCase(sActualTestCaseID) && (iUserNum == iRow)) {
				try {
					String sActualName = "NA";
					String sACtualEmail = "NA";
					String sActualStatus = "NA";

					try {
						// click on clear filter
						driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
						driver.findElement(By.xpath(".//span[text()='Clear Filters']")).click();
						Thread.sleep(1000);
					}

					catch (Exception er) {

					}
					// click on load icon
					driver.findElement(By
							.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div/div[1]/button[1]/span[1]/*[name()='svg']"))
					.click();

					// filter by Name
					WebElement eNameFilter = driver
							.findElement(By.xpath("(.//div[@role='columnheader']/div/div/input)[1]"));

					eNameFilter.clear();
					Thread.sleep(1000);
					eNameFilter.sendKeys(sName);
					Thread.sleep(3000);

					WebElement usergrid = driver.findElement(By.xpath(".//div[@role='grid']"));

					List<WebElement> eRows = driver.findElements(
							By.xpath("//*[@id=\"myGrid\"]/div/div/div[2]/div[1]/div[3]/div[2]/div/div/div"));
					System.out.println("Number of rows:" + eRows.size());
					int iRow1 = 0;
					sActualResult = "Record not found";
					for (WebElement eRow : eRows) {
						iRow1++;
						System.out.println("Row number:" + iRow1);
						List<WebElement> ecols = eRow.findElements(By.tagName("div"));
						System.out.println("Numnber of cols:" + ecols.size());
						int icol = 0;
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
							case "email":
								sACtualEmail = eCOl.getText();
								if (sACtualEmail.equalsIgnoreCase("") || sACtualEmail.equalsIgnoreCase(null)) {
									sACtualEmail = "NA";
								}
								break;							
							case "inactive":
								sActualStatus = eCOl.getText();
								if (sActualStatus.equalsIgnoreCase("") || sActualStatus.equalsIgnoreCase(null)) {
									sActualStatus = "NA";
								}
								break;
							}
						}
						System.out.println("sActualName:" + sActualName + " Name:" + sName);
						System.out.println("sACtualEmail:" + sACtualEmail + " Email:" + sEmail);
						System.out.println("sActualStatus:" + sActualStatus + " status:" + sStatus);							

						if (sActualName.trim().equalsIgnoreCase(sName.trim())
								&& sActualStatus.trim().equalsIgnoreCase(sStatus.trim())) {

							System.out.println("Record found");
							List<WebElement> nRecords= driver.findElements(By.xpath("//div[@col-id='name']"));
							Actions ac = new Actions(driver);

							switch (sOperation.toUpperCase()) {
							case "ACTIVE":
								bResult = true;
								sActualResult = "Shipper Contact in Active State";
								break;
							case "INACTIVE":	
								driver.findElement(By.xpath("(//div[@col-id='inactive'])["+nRecords.size()+"]")).click();
								Thread.sleep(1000);
								driver.findElement(By.xpath("//div[@class='ag-virtual-list-viewport']//div[text()='Inactive']")).click();
								Thread.sleep(2000);
								if(driver.findElement(By.xpath("(//div[@col-id='inactive'])["+nRecords.size()+"]")).getText().equalsIgnoreCase("ACTIVE")) {
									bResult = false;
									sActualResult = "Shipper Contact in Active State";
									break;
								} else {
									bResult = true;
									sActualResult = "Shipper Contact in InActive State";
								}
								break;
							case "VERIFY":
								bResult = false;
								if(driver.findElement(By.xpath("(//div[@col-id='email'])["+nRecords.size()+"]")).getText().equalsIgnoreCase(sEmail)) {
									bResult = true;
									sActualResult = "Shipper Contact Edited Successfully";
								}
								break;
							case "EDIT":
								bResult = false;
								System.out.println("Total Records : "+nRecords.size());
								driver.findElement(By.xpath("(//div[@col-id='email'])["+nRecords.size()+"]")).click();
								Thread.sleep(1000);
								driver.findElement(By.xpath("(//div[@col-id='email'])["+nRecords.size()+"]//input")).clear();
								driver.findElement(By.xpath("(//div[@col-id='email'])["+nRecords.size()+"]//input")).sendKeys(sEmail);
								//driver.findElement(By.xpath("//div[@class='ag-virtual-list-container']//span[text()='"+sCompany+"']")).click();
								Thread.sleep(1000);
								sActualResult = "Shipper Contact Edited Successfully";

								break;
							}

						}						
					}

				} catch (Exception error) {
					sActualResult = error.getMessage();
					error.printStackTrace();					
				}
				ResultComparision();
				TestDataImport.setCellData(sSheetName, iRow, 8, sActualResult, "NA");
				TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
				TestDataImport.setCellData(sSheetName, iRow, 9, sTestStepStatus, "NA");
				if (sTestStepStatus.equalsIgnoreCase("Passed")) {
					bResult = true;
				}
				break;

			}
		}
		System.out.println("user webtable:" + bResult);
		return bResult;
	}

}

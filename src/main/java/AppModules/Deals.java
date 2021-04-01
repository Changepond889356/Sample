package AppModules;

import Utils.GenericSkins;
import Utils.TestDataImport;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
					// DealsPage.eCommodity().clear();
					Thread.sleep(2000);

					DealsPage.eCommodity();
					Thread.sleep(2000);
					Actions ac = new Actions(driver);
					// ac.sendKeys(Keys.CONTROL+"a").build().perform();
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
					// DealsPage.eUOM().clear();

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
					//DealsPage.eNoOfLoads().clear();
					DealsPage.eNoOfLoads().sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
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
					 */ DealsPage.eNext().click();
					 Thread.sleep(2000);
					 System.out.println("clicked on Next button");
					 DealsPage.eMenuDeals().click();
					 Thread.sleep(2000);
					 DealsPage.eDealsDraft().click();
					 Thread.sleep(3000);
					 bResult = true;
					 sActualResult = "Edited deal successfully";
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
		System.out.println("edit deal:" + sActualResult);
		return bResult;
	}

	// MEthod to counter deal
	public static boolean counterDeal(String sActualTestCaseID) throws Exception {

		boolean bResult = false;
		String sFileName = "Deals.xlsx";
		String sSheetName = "Counter Deal";
		sTestStepID = "counter deal";

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
			sExpectedResult = TestDataImport.GetCellData(sSheetName, 13, iRow);
			String sExpConverCount = TestDataImport.GetCellData(sSheetName, 11, iRow);
			String sExpRatePerUOM = TestDataImport.GetCellData(sSheetName, 12, iRow);
			System.out.println("Add Deal:" + "sTestCaseID:" + sTestCaseID + "sActualTestCaseID:" + sActualTestCaseID);
			if (sTestCaseID.trim().equalsIgnoreCase(sActualTestCaseID.trim())) {
				System.out.println("inside if");
				try {

					CounterPage.eDealChatPlusIcon().click();
					CounterPage.eDealsCounter().click();

					DateTimeFormatter dateandtime = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
					LocalDateTime t1 = LocalDateTime.now();
					String sCurrentDateTime = t1.format(dateandtime);
					/*
					 * // set deal Name if (sDealName.trim().equalsIgnoreCase("AutoDeal")) {
					 * sDealName = sDealName + sCurrentDateTime.replace("/", ""); sDealName =
					 * sDealName.replace(":", ""); sDealName = sDealName.replace(" ", "");
					 * sGenericDealName = sDealName;
					 * 
					 * } System.out.println("Deal name:" + sDealName);
					 * DealsPage.eDealName().clear(); Thread.sleep(2000);
					 * DealsPage.eDealName().sendKeys(sDealName);
					 */ // set commodity
					// DealsPage.eCommodity().clear();
					Thread.sleep(2000);
					dateandtime = DateTimeFormatter.ofPattern("MM/dd/yyyy");
					t1 = LocalDateTime.now();
					sFromDate = sFromDate.replace("Current Date", t1.format(dateandtime));
					sToDate = sFromDate.replace("Current Date", t1.format(dateandtime));
					// DealsPage.eMenuDeals().click();
					Thread.sleep(200);

					// set from date
					CounterPage.eFromDate().clear();
					Thread.sleep(1000);
					CounterPage.eFromDate().sendKeys(sFromDate);
					CounterPage.eFromDate().sendKeys(Keys.ENTER);
					// set Todate
					Thread.sleep(2000);
					CounterPage.eTodate().clear();
					Thread.sleep(1000);
					CounterPage.eTodate().sendKeys(sToDate);
					CounterPage.eTodate().sendKeys(Keys.ENTER);
					Thread.sleep(1000);
					// set equipment type
					CounterPage.eEquipmetType().click();
					Thread.sleep(1000);

					Actions ac = new Actions(driver);
					ac.sendKeys(Keys.BACK_SPACE).build().perform();
					Thread.sleep(1000);
					ac.sendKeys(sEquipmet).build().perform();
					//CounterPage.eEquipmetType().sendKeys(sEquipmet);
					Thread.sleep(2000);
					ac.sendKeys(Keys.ENTER).build().perform();
					Thread.sleep(1000);

					CounterPage.eDealCommodity().click();

					Thread.sleep(2000);

					// ac.sendKeys(Keys.CONTROL+"a").build().perform();
					Thread.sleep(1000);
					ac.sendKeys(sCommodity).build().perform();
					Thread.sleep(1000);
					ac.sendKeys(Keys.ENTER).build().perform();
					Thread.sleep(2000);
					ac = new Actions(driver);
					ac.sendKeys(Keys.BACK_SPACE).build().perform();
					Thread.sleep(1000);

					// set rate
					System.out.println("sRate:" + sRate);
					//CounterPage.eRate().clear();
					CounterPage.eRate().sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
					Thread.sleep(1000);
					CounterPage.eRate().sendKeys(sRate);
					// set UOM
					CounterPage.eUOM().click();
					Thread.sleep(1000);
					// DealsPage.eUOM().clear();

					Thread.sleep(2000);
					ac = new Actions(driver);
					ac.sendKeys(sUOM).build().perform();
					Thread.sleep(3000);
					ac.sendKeys(Keys.ENTER).build().perform();
					Thread.sleep(2000);
					// set load
					//CounterPage.eNoOfLoads().clear();
					CounterPage.eNoOfLoads().sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
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
					 */
					CounterPage.eBtnCounter().click();

					Thread.sleep(2000);
					List<WebElement> eConversationlist = driver
							.findElements(By.xpath(".//div[@class='right-conversation']"));
					System.out.println("eConversationlist" + eConversationlist.size());
					if (eConversationlist.size() == Integer.parseInt(sExpConverCount)) {
						int iCOnvCount = 0;
						String sActFromDate="NA";
						String sActToDate="NA";
						String sActEquipment="NA";
						String sActOrigin="NA";
						String sActDestination="NA";
						String sActLoads="NA";
						String sActCommodity="NA";
						String sActRatePerUOM = "NA";

						for (WebElement eConv : eConversationlist) {
							iCOnvCount++;
							if (iCOnvCount == Integer.parseInt(sExpConverCount)) {
								List<WebElement> eboarditems = eConv
										.findElements(By.className("bot-card-item"));
								System.out.println("eboarditems size: "+eboarditems.size());
								int iBoard = 0;
								for(WebElement eBoard : eboarditems)
								{
									iBoard++;
									switch(iBoard)
									{
									case 1:
										List<WebElement> eP = eBoard
										.findElements(By.tagName("p"));
										int iP=0;
										for(WebElement eleP : eP)
										{
											iP++;

											switch(iP)
											{
											case 1:
												List<WebElement> eS = eleP
												.findElements(By.tagName("span"));
												int iS=0;
												for(WebElement eleS : eS)
												{
													iS++;
													switch(iS)
													{
													case 1:sActFromDate=eleS.getText();
													break;
													case 3:sActToDate=eleS.getText();
													break;

													}
												}
												break;
											case 2:
												sActEquipment=eleP.getText();

												break;
											}
										}

										break;
									case 2:
										sActOrigin=eBoard.getAttribute("title");
										List<WebElement> tagP = eBoard
												.findElements(By.tagName("p"));
										int cntP=0;
										for(WebElement tag : tagP)
										{
											cntP++;
											switch(cntP)
											{
											case 2: 
												sActLoads  = tag.getText();
												break;
											}
										}
										break;
									case 3:
										sActDestination=eBoard.getAttribute("title");
										break;
									case 4:
										eP = eBoard.findElements(By.tagName("p"));
										iP=0;
										for(WebElement eleP : eP)
										{
											iP++;

											switch(iP)
											{
											case 1:
												sActCommodity = eleP.getText();
												break;
											case 2:
												sActRatePerUOM=eleP.getText();

												break;
											}
										}


										break;
									}
								}
							}

						}
						sFromDate = TestDataImport.GetCellData(sSheetName, 3, iRow);
						sToDate = TestDataImport.GetCellData(sSheetName, 4, iRow);
						dateandtime = DateTimeFormatter.ofPattern("MM/dd");
						t1 = LocalDateTime.now();
						sCurrentDateTime = t1.format(dateandtime);

						sFromDate=sFromDate.replace("Current Date", sCurrentDateTime);
						sToDate=sToDate.replace("Current Date", sCurrentDateTime);
						System.out.println("sFromDate:"+sFromDate);
						System.out.println("sToDate:"+sToDate);
						System.out.println("sCommodity:"+sCommodity);
						System.out.println("sEquipmet:"+sEquipmet);
						System.out.println("sOrigin:"+sOrigin);
						System.out.println("sDestination:"+sDestination);
						System.out.println("sNoOfLoads:"+sNoOfLoads);
						System.out.println("sFromDate:"+sFromDate);

						System.out.println("sActFromDate:"+sActFromDate);
						System.out.println("sActztoDate:"+sActToDate);
						System.out.println("sActEquipment:"+sActEquipment);
						System.out.println("sActOrigin:"+sActOrigin);
						System.out.println("sActDestination:"+sActDestination);
						System.out.println("sActLoads:"+sActLoads);
						System.out.println("sActCommodity:"+sActCommodity);
						System.out.println("sActRatePerUOM:"+sExpRatePerUOM);
						System.out.println("sExpRatePerUOM:"+sActRatePerUOM);
						if(sActFromDate.trim().equalsIgnoreCase(sFromDate)
								&& sActCommodity.trim().equalsIgnoreCase(sCommodity)
								&& sActToDate.trim().equalsIgnoreCase(sToDate)
								&& sActEquipment.trim().equalsIgnoreCase(sEquipmet)
								&& sActOrigin.trim().equalsIgnoreCase(sOrigin)
								&& sActDestination.trim().equalsIgnoreCase(sDestination)
								&& sActLoads.trim().equalsIgnoreCase(sNoOfLoads)
								&& sActRatePerUOM.trim().equalsIgnoreCase(sExpRatePerUOM))
						{
							bResult = true;
							sActualResult = "Countered deal successfully";
							break;
						}
						else
						{
							bResult = false;
							sActualResult = "Countered details not correct";
						}

					} else {
						bResult = false;
						sActualResult = "unable to Counter deal";
					}

				} catch (Exception error) {

					sActualResult = error.getMessage();
					throw error;
					// throw error;

				}
				ResultComparision();
				TestDataImport.setCellData(sSheetName, iRow, 14, sActualResult, "NA");
				TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
				TestDataImport.setCellData(sSheetName, iRow, 15, sTestStepStatus, "NA");
				break;
			} else {
				sActualResult = "Testcase not found";
			}

		}
		System.out.println("conter deal:" + sActualResult);
		return bResult;
	}

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
		WebElement eTitle = null;
		WebElement eDots = null;
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
					case "BOOKED":
						DealsPage.eDealsBooked().click();
						Thread.sleep(5000);
						DealsPage.eDealsDraft().click();
						DealsPage.eDealsBooked().click();
						break;
					}

					Thread.sleep(3000);

					List<WebElement> eDeals = driver               
							.findElements(By.xpath(".//div[@class='MuiPaper-root MuiCard-root css-zoo7ea MuiPaper-elevation1 MuiPaper-rounded']"));
					if(eDeals.size()==0)
					{
						eDeals = driver.findElements(By.xpath(".//div[@class='MuiPaper-root MuiCard-root css-3yirmu MuiPaper-elevation1 MuiPaper-rounded']"));
					}
					if(eDeals.size()==0)
					{
						eDeals = driver.findElements(By.xpath(".//div[@class='MuiPaper-root MuiCard-root css-11sd6u5 MuiPaper-elevation1 MuiPaper-rounded']"));
					}
					System.out.println("No. of deals :" + eDeals.size());
					sActualResult = "No deal found";
					for (WebElement eDeal : eDeals) {
						String sActualDealName = "";
						if (sTab.equalsIgnoreCase("Draft")) {
							eTitle = eDeal.findElement(By.tagName("span"));
							sActualDealName = eDeal.findElement(By.tagName("span")).getAttribute("title");
						} else if (sTab.equalsIgnoreCase("Opportunity")) {
							List<WebElement> eSpans = eDeal.findElements(By.tagName("span"));
							System.out.println("NO of spans:" + eSpans.size());
							for (WebElement eSpan : eSpans) {
								sActualDealName = eSpan.getAttribute("title");
								System.out.println("Deal title:" + sActualDealName);
								if (!(sActualDealName.equals(null)) && !(sActualDealName.equals(""))) {
									eTitle = eSpan;
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
									eTitle = eDeal.findElement(By.cssSelector(".css-1loz6yx"));
									// sActualDealName = eSpan.getAttribute("title");
									break;
								}

							}

							System.out.println("with draw DealName:" + sDealName);
						} else if (sTab.equalsIgnoreCase("Booked")) {
							List<WebElement> eTitles = driver.findElements(By.xpath(".//div[@class=' css-1art6ly']"));
							System.out.println("NO of spans:" + eTitles.size());
							for (WebElement eSpan : eTitles) {
								sActualDealName = eSpan.getAttribute("title");
								System.out.println("Deal title:" + sActualDealName);
								if ((sActualDealName.equals(sDealName))) {
									eTitle = eDeal.findElement(By.cssSelector(".css-1loz6yx"));
									// sActualDealName = eSpan.getAttribute("title");
									break;
								}

							}

						}

						System.out.println("DealName:" + sDealName);
						System.out.println("actual DealName:" + sActualDealName);
						System.out.println("sOperation:" + sOperation);
						if (sActualDealName.trim().equalsIgnoreCase(sDealName.trim())) {
							// bResult=true;
							// click on ...
							if (!(sOperation.equalsIgnoreCase("SELECT")) && !(sOperation.equalsIgnoreCase("VIEW"))) {
								switch (sTab.toUpperCase().trim()) {
								case "DRAFT":
									eDots = eDeal.findElement(
											By.xpath(".//div[@class='css-cy1kem e2zx7mg0']/*[name()='svg']"));
									break;
								case "OPPORTUNITY":
									eDots = eDeal.findElement(
											By.xpath(".//div[@class='css-ofjib6 e2zx7mg0']/*[name()='svg']"));
									break;
								case "WITHDRAW":
									// Thread.sleep(2000);
									eDots = eDeal.findElement(
											By.xpath(".//div[@class='css-cy1kem e2zx7mg0']/*[name()='svg']"));
									System.out.println("clicked on dots");
									break;
								case "BOOKED":
									eDots = eDeal.findElement(
											By.xpath(".//div[@class='css-cy1kem e2zx7mg0']/*[name()='svg']"));
									break;
								}

								eDots.click();
								System.out.println("clicked on dots");

							}

							Thread.sleep(2000);
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
							case "SELECT":
								eTitle.click();
								Thread.sleep(4000);
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
					// throw error;
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

	public static boolean ChatDeal(String sActualTestCaseID, int tcRowNum) throws Exception {
		Thread.sleep(1000);
		boolean bResult = false;
		String sFileName = "Chat.xlsx";
		String sSheetName = "Deal_Chat Details";
		// Copy Loads.xlsx file from test data folder to current log folder
		Copy_File(sTestDataPath + sFileName, sTestResultsPath);

		TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
		int iRowCnt = 0,flag =0;
		iRowCnt = TestDataImport.GetRowCount(sSheetName);
		System.out.println("Number of rows:" + iRowCnt);
		for (int iRow = 1; iRow <= iRowCnt; iRow++) {

			TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
			String sTestCaseID = TestDataImport.GetCellData(sSheetName, 0, iRow);
			String sDealName = TestDataImport.GetCellData(sSheetName, 1, iRow);
			String inpMsg = TestDataImport.GetCellData(sSheetName, 2, iRow);
			String sOperation = TestDataImport.GetCellData(sSheetName, 3, iRow);
			sExpectedResult = TestDataImport.GetCellData(sSheetName, 4, iRow);
			if (sDealName.trim().equalsIgnoreCase("AutoDeal")) {
				sDealName = sGenericDealName;
			}
			if(sTestCaseID.trim().equalsIgnoreCase(sActualTestCaseID) && iRow == tcRowNum) {
				try {

					DealsPage.eMenuDeals().click();
					Thread.sleep(3000);

					DealsPage.eDealsOpportunity().click();
					Thread.sleep(5000);
					List<WebElement> eDeals = driver
							.findElements(By.xpath("//*[@id=\"root\"]/div/div[3]/div[2]/div/div/div/div[3]/div/div"));

					switch(sOperation) {
					case "SEND": 

						sActualResult = "No deal found";
						for (WebElement eDeal : eDeals) {
							String sActualDealName = "";

							List<WebElement> eSpans = eDeal.findElements(By.tagName("span"));
							for (WebElement eSpan : eSpans) {
								sActualDealName = eSpan.getAttribute("title");
								System.out.println("Deal title:" + sActualDealName);
								if (sActualDealName.trim().equalsIgnoreCase(sDealName.trim())) {

									eSpan.click();
									Thread.sleep(5000);

									DealsPage.eChatTextField().sendKeys(inpMsg);
									Thread.sleep(1000);
									DealsPage.eChatMsgSendButton().click();
									Thread.sleep(1000);
									GenericSkins.WaitForElementVisibility(By.xpath("//div[@class='counter-message']"));
									flag =1;
									break;
								}
							}
							if(flag == 1) {
								bResult = true;
								sActualResult = "Message send successfully";
								break;
							}
						}	
						break;

					case "VERIFY":
						for (WebElement eDeal : eDeals) {
							String sActualDealName = "";

							List<WebElement> eSpans = eDeal.findElements(By.tagName("span"));
							for (WebElement eSpan : eSpans) {
								sActualDealName = eSpan.getAttribute("title");
								System.out.println("Deal title:" + sActualDealName);
								if (sActualDealName.trim().equalsIgnoreCase(sDealName.trim())) {

									/*if(eSpan.findElement(By.tagName("span")).isDisplayed()) {
										System.out.println("Notification Received");
									}*/
									eSpan.click();
									Thread.sleep(5000);
									GenericSkins.WaitForElementVisibility(By.xpath("//div[@class='counter-message']"));
									List<WebElement> chatList = driver.findElements(By.xpath("//div[@class='counter-message']"));
									String actualChatMsg = chatList.get(chatList.size() -1).getText().toString();
									System.out.println("Actual Chat Msg " + actualChatMsg);
									if(actualChatMsg.equals(inpMsg)) {
										flag =1;
									}	
									sActualResult = "Message not verified";
									break;
								}
							}
							if(flag == 1) {
								bResult = true;
								sActualResult = "Message verified successfully";
								break;
							}
						}	

						break;

					}	
				} catch (Exception error) {
					sActualResult = error.getMessage();
					bResult = false;
				}
				ResultComparision();
				TestDataImport.setCellData(sSheetName, iRow, 5, sActualResult, "NA");
				TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
				TestDataImport.setCellData(sSheetName, iRow, 6, sTestStepStatus, "NA");
				break;
			}

		}
		Thread.sleep(5000);
		System.out.println("Deals||set duplicate:" + bResult);
		return bResult;
	}

	// MEthod to counter deal
	public static boolean AcceptDeal(String sActualTestCaseID) throws Exception {

		boolean bResult = false;
		String sFileName = "Deals.xlsx";
		String sSheetName = "Counter Deal";
		sTestStepID = "Accept deal";

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
			sExpectedResult = TestDataImport.GetCellData(sSheetName, 13, iRow);
			String sExpConverCount = TestDataImport.GetCellData(sSheetName, 11, iRow);
			String sExpRatePerUOM = TestDataImport.GetCellData(sSheetName, 12, iRow);
			System.out.println("Add Deal:" + "sTestCaseID:" + sTestCaseID + "sActualTestCaseID:" + sActualTestCaseID);
			if (sTestCaseID.trim().equalsIgnoreCase(sActualTestCaseID.trim())) {
				System.out.println("inside if");
				try {

					DateTimeFormatter dateandtime = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
					LocalDateTime t1 = LocalDateTime.now();
					String sCurrentDateTime = t1.format(dateandtime);
					/*
					 * // set deal Name if (sDealName.trim().equalsIgnoreCase("AutoDeal")) {
					 * sDealName = sDealName + sCurrentDateTime.replace("/", ""); sDealName =
					 * sDealName.replace(":", ""); sDealName = sDealName.replace(" ", "");
					 * sGenericDealName = sDealName;
					 * 
					 * } System.out.println("Deal name:" + sDealName);
					 * DealsPage.eDealName().clear(); Thread.sleep(2000);
					 * DealsPage.eDealName().sendKeys(sDealName);
					 */ // set commodity
					// DealsPage.eCommodity().clear();
					Thread.sleep(2000);
					List<WebElement> eConversationlist = driver
							.findElements(By.xpath(".//div[@class='left-conversation']"));
					System.out.println("eConversationlist" + eConversationlist.size());
					System.out.println("sExpConverCount" + sExpConverCount);
					if (eConversationlist.size() == Integer.parseInt(sExpConverCount)) {
						int iCOnvCount = 0;
						String sActFromDate = "NA";
						String sActToDate = "NA";
						String sActEquipment = "NA";
						String sActOrigin = "NA";
						String sActDestination = "NA";
						String sActLoads = "NA";
						String sActCommodity = "NA";
						String sActRatePerUOM = "NA";

						for (WebElement eConv : eConversationlist) {
							iCOnvCount++;
							if (iCOnvCount == Integer.parseInt(sExpConverCount)) {
								System.out.println("searchig board items");
								List<WebElement> eboarditems = eConv.findElements(By.className("bot-card-item"));
								System.out.println("eboarditems size: " + eboarditems.size());
								int iBoard = 0;
								for (WebElement eBoard : eboarditems) {
									iBoard++;
									switch (iBoard) {
									case 1:
										List<WebElement> eP = eBoard.findElements(By.tagName("p"));
										int iP = 0;
										for (WebElement eleP : eP) {
											iP++;

											switch (iP) {
											case 1:
												List<WebElement> eS = eleP.findElements(By.tagName("span"));
												int iS = 0;
												for (WebElement eleS : eS) {
													iS++;
													switch (iS) {
													case 1:
														sActFromDate = eleS.getText();
														break;
													case 3:
														sActToDate = eleS.getText();
														break;

													}
												}
												break;
											case 2:
												sActEquipment = eleP.getText();

												break;
											}
										}

										break;
									case 2:
										sActOrigin = eBoard.getAttribute("title");
										List<WebElement> tagP = eBoard.findElements(By.tagName("p"));
										int cntP = 0;
										for (WebElement tag : tagP) {
											cntP++;
											switch (cntP) {
											case 2:
												sActLoads = tag.getText();
												break;
											}
										}
										break;
									case 3:
										sActDestination = eBoard.getAttribute("title");
										break;
									case 4:
										eP = eBoard.findElements(By.tagName("p"));
										iP = 0;
										for (WebElement eleP : eP) {
											iP++;

											switch (iP) {
											case 1:
												sActCommodity = eleP.getText();
												break;
											case 2:
												sActRatePerUOM = eleP.getText();

												break;
											}
										}

										break;
									}
								}
							}

						}
						sFromDate = TestDataImport.GetCellData(sSheetName, 3, iRow);
						sToDate = TestDataImport.GetCellData(sSheetName, 4, iRow);
						dateandtime = DateTimeFormatter.ofPattern("MM/dd");
						t1 = LocalDateTime.now();
						sCurrentDateTime = t1.format(dateandtime);

						sFromDate = sFromDate.replace("Current Date", sCurrentDateTime);
						sToDate = sToDate.replace("Current Date", sCurrentDateTime);
						System.out.println("sFromDate:" + sFromDate);
						System.out.println("sToDate:" + sToDate);
						System.out.println("sCommodity:" + sCommodity);
						System.out.println("sEquipmet:" + sEquipmet);
						System.out.println("sOrigin:" + sOrigin);
						System.out.println("sDestination:" + sDestination);
						System.out.println("sNoOfLoads:" + sNoOfLoads);
						System.out.println("sExpRatePerUOM:" + sExpRatePerUOM);

						System.out.println("sActFromDate:" + sActFromDate);
						System.out.println("sActztoDate:" + sActToDate);
						System.out.println("sActCommodity:" + sActCommodity);
						System.out.println("sActEquipment:" + sActEquipment);
						System.out.println("sActOrigin:" + sActOrigin);
						System.out.println("sActDestination:" + sActDestination);
						System.out.println("sActLoads:" + sActLoads);
						System.out.println("sActRatePerUOM:" + sActRatePerUOM);

						if (sActFromDate.trim().equalsIgnoreCase(sFromDate.trim())
								&& sActCommodity.trim().equalsIgnoreCase(sCommodity.trim())
								&& sActToDate.trim().equalsIgnoreCase(sToDate.trim())
								&& sActEquipment.trim().equalsIgnoreCase(sEquipmet.trim())
								&& sActOrigin.trim().equalsIgnoreCase(sOrigin.trim())
								&& sActDestination.trim().equalsIgnoreCase(sDestination.trim())
								&& sActLoads.trim().equalsIgnoreCase(sNoOfLoads.trim())
								&& sActRatePerUOM.trim().equalsIgnoreCase(sExpRatePerUOM.trim())) {
							CounterPage.eDealChatPlusIcon().click();
							CounterPage.eDealsAccept().click();
							Thread.sleep(1000);
							CounterPage.eBtnAccept().click();
							Thread.sleep(5000);
							WebDriverWait wait = new WebDriverWait(driver, 5);
							wait.until(ExpectedConditions
									.visibilityOfElementLocated(By.xpath(".//div[@class='accept-message']")));
							bResult = true;
							sActualResult = "Accepted deal successfully";
							break;
						} else {
							bResult = false;
							sActualResult = "Accepted details not correct";
						}

					} else {
						bResult = false;
						sActualResult = "unable to accept deal";
					}

				} catch (Exception error) {

					sActualResult = error.getMessage();
					throw error;
					// throw error;

				}
				ResultComparision();
				TestDataImport.setCellData(sSheetName, iRow, 14, sActualResult, "NA");
				TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
				TestDataImport.setCellData(sSheetName, iRow, 15, sTestStepStatus, "NA");
				break;
			} else {
				sActualResult = "Testcase not found";
			}

		}
		System.out.println("accept deal:" + sActualResult);
		return bResult;
	}

	// date filter
	// MEthod to counter deal
	public static boolean datefilter(String sOperation, String sFromDate, String sTodate) throws Exception {
		boolean bResult = false;
		try {
			switch (sOperation.toUpperCase()) {
			case "FILTER":
				DealsPage.ebtnDate().click();
				Thread.sleep(1000);
				//set from date
				System.out.println("set from date");
				DealsPage.eFilterFromDate().click();
				Thread.sleep(1000);
				calendarselect(sFromDate);
				Thread.sleep(10000);
				//set to date
				System.out.println("set to date");
				DealsPage.eFilterToDate().click();
				Thread.sleep(1000);
				calendarselect(sTodate);
				//action.sendKeys(sTodate).build().perform();
				Thread.sleep(1000);
				DealsPage.ebtnDone().click();
				bResult = true;
				break;
			case "RESET":
				DealsPage.ebtnDate().click();
				Thread.sleep(1000);
				DealsPage.ebtnReset().click();
				bResult = true;
			}
		} catch (Exception error) {
			bResult = false;
			sActualResult = error.getMessage();
		}
		return bResult;
	}

	//method to handle  calendar
	public static void calendarselect(String sTestStepData) throws Exception {
		try {
			// if Test data is Current date then assign test data with system current date
			if (sTestStepData.equalsIgnoreCase("CurrentDate")) {
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Calendar cal = Calendar.getInstance();
				sTestStepData = dateFormat.format(cal.getTime()).toString();
			}

			if (!(sTestStepData.equalsIgnoreCase("NA"))) {
				String dateTime = sTestStepData;
				Thread.sleep(2000);
				// split the date into array
				String dd_mm_yyyy[] = dateTime.split("/");
				String sExpectedMOnth = "NA";
				// set month name

				GregorianCalendar cal = new GregorianCalendar();
				int Current_Year = cal.get(Calendar.YEAR);
				System.out.println("Current Year:" + Current_Year);
				// diffrence between current year and test data year
				//int year_diff = Integer.parseInt(dd_mm_yyyy[2]) - Current_Year;
				//System.out.println("Year Diff:" + year_diff);
				Thread.sleep(2000);
				// LIst of date pickers in current page
				List<WebElement> DatePickers = driver.findElements(By.cssSelector(".react-datepicker"));

				// Iterating through each date picker
				for (WebElement DatePicker : DatePickers) {
					boolean bDatePicker = DatePicker.isDisplayed();

					// check if date picker displayed
					if (bDatePicker == true) {
						// set year
						String sActualMonthYear = DatePicker
								.findElement(By.cssSelector(".react-datepicker__current-month")).getText();
						System.out.println("before while:"+sActualMonthYear);
						System.out.println(Integer.parseInt(sActualMonthYear.split(" ")[1]));
						System.out.println(Integer.parseInt(dd_mm_yyyy[2]));
						while (!(Integer.parseInt(sActualMonthYear.split(" ")[1]) == Integer.parseInt(dd_mm_yyyy[2]))) {
							sActualMonthYear = DatePicker
									.findElement(By.cssSelector(".react-datepicker__current-month")).getText();

							System.out.println("month year displayed:" + sActualMonthYear);

							if (Integer.parseInt(sActualMonthYear.split(" ")[1]) > Integer.parseInt(dd_mm_yyyy[2])) {
								driver.findElement(By.xpath(".//button[text()='Previous Month']")).click();
							} else if (Integer.parseInt(sActualMonthYear.split(" ")[1]) < Integer
									.parseInt(dd_mm_yyyy[2])) {
								driver.findElement(By.xpath(".//button[text()='Next Month']")).click();
							}
							sActualMonthYear = DatePicker
									.findElement(By.cssSelector(".react-datepicker__current-month")).getText();
						}
						System.out.println("fetching month year");
						sActualMonthYear = DatePicker.findElement(By.cssSelector(".react-datepicker__current-month"))
								.getText();
						System.out.println(sActualMonthYear);
						int iActualMonth=0;
						switch(sActualMonthYear.split(" ")[0].toUpperCase())
						{
						case "JANUARY":
							iActualMonth=1;
							break;

						case "FEBRUARY":
							iActualMonth=2;
							break;
						case "MARCH":
							iActualMonth=3;
							break;
						case "APRIL":
							iActualMonth=4;
							break;
						case "MAY":
							iActualMonth=5;
							break;
						case "JUNE":
							iActualMonth=6;
							break;
						case "JULY":
							iActualMonth=7;
							break;
						case "AUGUST":
							iActualMonth=8;
							break;
						case "SEPTEMBER":
							iActualMonth=9;
							break;
						case "OCTOBER":
							iActualMonth=10;
							break;
						case "NOVEMBER":
							iActualMonth=11;
							break;
						case "DECEMBER":
							iActualMonth=12;
							break;
						}
						System.out.println(iActualMonth+":"+dd_mm_yyyy[1]);
						if (iActualMonth>Integer.parseInt(dd_mm_yyyy[1])) {
							while (!(iActualMonth==Integer.parseInt(dd_mm_yyyy[1]))) {
								driver.findElement(By.xpath(".//button[text()='Previous Month']")).click();
								Thread.sleep(1000);
								iActualMonth--;
							}

						} else if (iActualMonth<Integer.parseInt(dd_mm_yyyy[1])) {
							while (!(iActualMonth==Integer.parseInt(dd_mm_yyyy[1]))) {
								driver.findElement(By.xpath(".//button[text()='Next Month']")).click();
								Thread.sleep(1000);
								iActualMonth++;
							}

						}

						sActualMonthYear = driver.findElement(By.cssSelector(".react-datepicker__current-month"))
								.getText();
						System.out.println("month year displayed:" + sActualMonthYear);
						List<WebElement> eWeeks = DatePicker.findElements(By.cssSelector(".react-datepicker__week"));
						for (WebElement eWeek : eWeeks) {
							List<WebElement> eDays = eWeek.findElements(By.tagName("div"));
							for (WebElement eDay : eDays) {
								String sClass = eDay.getAttribute("class");
								System.out.println(sClass);
								System.out.println(eDay.getText());
								if (!(sClass.contains("outside-month"))) {
									if (Integer.parseInt(eDay.getText())==Integer.parseInt(dd_mm_yyyy[0])) {
										eDay.click();
										Thread.sleep(2000);
										break;
									}
								}
							}
						}

					}
				}
			}
		} catch (Exception error) {
			System.out.println("error:"+error.getMessage());
			//throw error;
		}
	}

	public static boolean addChatConnectionDeal(String sActualTestCaseID) throws Exception {

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
					Thread.sleep(1000);
					Actions ac = new Actions(driver);
					ac.sendKeys(sCommodity).build().perform();
					ac.sendKeys(Keys.ENTER).build().perform();
					Thread.sleep(1000);

					dateandtime = DateTimeFormatter.ofPattern("MM/dd/yyyy");
					t1 = LocalDateTime.now();
					sFromDate = sFromDate.replace("Current Date", t1.format(dateandtime));
					sToDate = sFromDate.replace("Current Date", t1.format(dateandtime));

					// set from date
					DealsPage.eFromDate().sendKeys(sFromDate);
					DealsPage.eFromDate().sendKeys(Keys.ENTER);
					// set Todate
					Thread.sleep(1000);
					DealsPage.eTodate().sendKeys(sToDate);
					DealsPage.eTodate().sendKeys(Keys.ENTER);
					Thread.sleep(1000);
					// set rate
					System.out.println("sRate:" + sRate);
					DealsPage.eRate().sendKeys(sRate);
					// set UOM
					DealsPage.eUOM().click();
					Thread.sleep(1000);
					ac = new Actions(driver);
					ac.sendKeys(sUOM).build().perform();
					Thread.sleep(2000);
					ac.sendKeys(Keys.ENTER).build().perform();
					Thread.sleep(1000);
					// set equipment type
					DealsPage.eEquipmetType().click();
					Thread.sleep(1000);
					ac = new Actions(driver);
					ac.sendKeys(sEquipmet).build().perform();
					ac.sendKeys(Keys.ENTER).build().perform();
					Thread.sleep(1000);
					// set load
					DealsPage.eNoOfLoads().sendKeys(sNoOfLoads);
					// set origin

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

					System.out.println("searching origin");
					Thread.sleep(2000);
					ac.sendKeys(Keys.ENTER).build().perform();
					Thread.sleep(2000);
					System.out.println("searched origin");

					System.out.println("sDestination:" + sDestination);
					ac = new Actions(driver);
					DealsPage.eDestination().sendKeys(sDestination);
					DealsPage.eDestination().clear();
					for (int i = 0; i < sDestination.length(); i++) {
						ac.sendKeys(sDestination.substring(i, i + 1)).build().perform();
						;
						Thread.sleep(100);
					}

					Thread.sleep(4000);
					ac.sendKeys(Keys.ENTER).build().perform();
					Thread.sleep(2000);

					DealsPage.eNext().click();
					Thread.sleep(6000);
					System.out.println("clicked on Next button");

					DealsPage.eToggleButton().click();
					Thread.sleep(2000); 
					DealsPage.eChatConnectionShare().click();
					Thread.sleep(3000); 
					DealsPage.eDealsOpportunity().click();
					Thread.sleep(5000); 

					List<WebElement> eDeals = driver
							.findElements(By.xpath("//*[@id=\"root\"]/div/div[3]/div[2]/div/div/div/div[3]/div/div"));
					System.out.println("No. of deals :" + eDeals.size());
					int cnt =0;
					for (WebElement eDeal : eDeals) {
						String sActualDealName = "";
						List<WebElement> eSpans = eDeal.findElements(By.tagName("span"));
						System.out.println("NO of spans:" + eSpans.size());
						for (WebElement eSpan : eSpans) {
							sActualDealName = eSpan.getAttribute("title");
							System.out.println("Deal title:" + sActualDealName);
							if (sActualDealName.equals(sDealName)) {
								// bResult=true;
								sActualResult = "Added new Deal successfully";
								bResult = true;
								cnt =1;
								break;
							} else {
								sActualResult = "Unable to add new Deal";
								bResult = false;

							}
						}
						if(cnt ==1) {
							break;
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
		return bResult;
	}

	// MEthod to add a new user
	public static boolean VerifyChatChannel(String sActualTestCaseID, int rNum) throws Exception {

		int flag = 0;
		boolean bResult = false;
		String sFileName = "Chat.xlsx";
		String sSheetName = "Deal_Chat_Connection";
		//sTestStepID = "addNewDeal";

		// Copy Loads.xlsx file from test data folder to current log folder
		Copy_File(sTestDataPath + sFileName, sTestResultsPath);

		TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
		int iRowCnt = 0;
		iRowCnt = TestDataImport.GetRowCount(sSheetName);
		System.out.println("Number of rows:"+iRowCnt);
		for (int iRow = 1; iRow <= iRowCnt; iRow++) {
			TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
			String sTestCaseID = TestDataImport.GetCellData(sSheetName, 0, iRow);
			String sDealName = TestDataImport.GetCellData(sSheetName, 1, iRow);
			String sOperation = TestDataImport.GetCellData(sSheetName, 2, iRow);
			String inpMsg = TestDataImport.GetCellData(sSheetName, 3, iRow);

			sExpectedResult = TestDataImport.GetCellData(sSheetName, 4, iRow);
			System.out.println("Add Deal:" + "sTestCaseID:" + sTestCaseID + "sActualTestCaseID:" + sActualTestCaseID);
			if (sTestCaseID.trim().equalsIgnoreCase(sActualTestCaseID.trim()) && iRow == rNum) {
				System.out.println("Inside if");
				try {

					sDealName = sGenericDealName;


					switch(sOperation) {
					case "VERIFY":

						DealsPage.eDealsOpportunity().click();
						Thread.sleep(3000);
						List<WebElement> eDeals = driver
								.findElements(By.xpath("//*[@id=\"root\"]/div/div[3]/div[2]/div/div/div/div[3]/div/div"));
						System.out.println("No. of deals :" + eDeals.size());
						sActualResult = "No deal found";
						for (WebElement eDeal : eDeals) {
							String sActualDealName = "";
							List<WebElement> eSpans = eDeal.findElements(By.tagName("span"));
							System.out.println("NO of spans:" + eSpans.size());
							for (WebElement eSpan : eSpans) {
								sActualDealName = eSpan.getAttribute("title");
								System.out.println("Deal title:" + sActualDealName);
								if (sActualDealName.equals(sDealName)) {
									eSpan.click();
									Thread.sleep(3000); 

									if(driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div[2]/div/div/div/div/div/div[1]/div[3]//div[contains(text(),'No Channels Found')]")).isDisplayed()) {
										bResult = true;
										flag =1;
										sActualResult = "No Channel Found";
									}								
									break;								
								}
							}
							if(flag == 1) {
								break;
							}
						}
						break;

					case "VERIFY_BANNER":

						DealsPage.eMenuDeals().click();
						Thread.sleep(3000); 
						DealsPage.eDealsOpportunity().click();
						Thread.sleep(3000); 
						List<WebElement> eDeals2 = driver
								.findElements(By.xpath("//*[@id=\"root\"]/div/div[3]/div[2]/div/div/div/div[3]/div/div"));
						System.out.println("No. of deals :" + eDeals2.size());
						for (WebElement eDeal : eDeals2) {
							String sActualDealName = "";
							List<WebElement> eSpans = eDeal.findElements(By.tagName("span"));
							System.out.println("NO of spans:" + eSpans.size());
							for (WebElement eSpan : eSpans) {
								sActualDealName = eSpan.getAttribute("title");
								System.out.println("Deal title:" + sActualDealName);
								if (sActualDealName.equals(sDealName)) {
									eSpan.click();
									Thread.sleep(5000); 
									boolean btnStatus = driver.findElement(By.xpath("//div[@class='page']//button")).isEnabled();
									if(btnStatus) {
										sActualResult = "Get Started Button is Enabled";
									} else {
										driver.findElement(By.xpath("//input[@name='checkbox']")).click();
										Thread.sleep(1000); 
										DealsPage.eGetStartedButton().click();
										Thread.sleep(5000); 
										//GenericSkins.WaitForElementVisibility(By.xpath("//span[contains(text(),'PENDING...')]"));
										bResult = true;
										flag =1;
										sActualResult = "Get Started Button is Disabled";
									}

									break;								
								}
							}
							if(flag == 1) {
								break;
							}
						}

						break;

					case "ACCEPT_CONNECTION":

						DealsPage.eMenuDeals().click();
						Thread.sleep(3000); 
						DealsPage.eDealsOpportunity().click();
						Thread.sleep(3000); 
						List<WebElement> eDeals3 = driver
								.findElements(By.xpath("//*[@id=\"root\"]/div/div[3]/div[2]/div/div/div/div[3]/div/div"));
						System.out.println("No. of deals :" + eDeals3.size());
						for (WebElement eDeal : eDeals3) {
							String sActualDealName = "";
							List<WebElement> eSpans = eDeal.findElements(By.tagName("span"));
							System.out.println("NO of spans:" + eSpans.size());
							for (WebElement eSpan : eSpans) {
								sActualDealName = eSpan.getAttribute("title");
								System.out.println("Deal title:" + sActualDealName);
								if (sActualDealName.equals(sDealName)) {
									eSpan.click();
									Thread.sleep(5000); 
									boolean btnStatus = driver.findElement(By.xpath("//div[@class='page']//button")).isEnabled();
									if(btnStatus) {
										sActualResult = "Confirm Button is Enabled";
									} else {									
										driver.findElement(By.xpath("//input[@name='checkbox']")).click();
										Thread.sleep(1000); 
										DealsPage.eGetStartedButton().click();
										GenericSkins.WaitForElementVisibility(By.xpath("//*[@id='sendbird-message-input']"));
										bResult = true;
										flag =1;
										sActualResult = "Connection Accepted";
									}

									break;								
								}
							}
							if(flag == 1) {
								break;
							}
						}

						break;

					case "SEND_MESSAGE": 
						DealsPage.eChatTextField().sendKeys(inpMsg);
						Thread.sleep(1000);
						DealsPage.eChatMsgSendButton().click();
						Thread.sleep(1000);
						GenericSkins.WaitForElementVisibility(By.xpath("//div[@class='counter-message']"));
						sActualResult = "Message send successfully";
						bResult = true;
						break;

					case "VERIFY_MESSAGE":
						DealsPage.eMenuDeals().click();
						Thread.sleep(3000);

						DealsPage.eDealsOpportunity().click();
						Thread.sleep(5000);
						List<WebElement> eDeals4 = driver
								.findElements(By.xpath("//*[@id=\"root\"]/div/div[3]/div[2]/div/div/div/div[3]/div/div"));
						for (WebElement eDeal : eDeals4) {
							String sActualDealName = "";

							List<WebElement> eSpans = eDeal.findElements(By.tagName("span"));
							for (WebElement eSpan : eSpans) {
								sActualDealName = eSpan.getAttribute("title");
								System.out.println("Deal title:" + sActualDealName);
								if (sActualDealName.trim().equalsIgnoreCase(sDealName.trim())) {

									/*if(eSpan.findElement(By.tagName("span")).isDisplayed()) {
										System.out.println("Notification Received");
									}*/
									eSpan.click();
									Thread.sleep(5000);
									GenericSkins.WaitForElementVisibility(By.xpath("//div[@class='counter-message']"));
									List<WebElement> chatList = driver.findElements(By.xpath("//div[@class='counter-message']"));
									String actualChatMsg = chatList.get(chatList.size() -1).getText().toString();
									System.out.println("Actual Chat Msg " + actualChatMsg);
									if(actualChatMsg.equals(inpMsg)) {
										flag =1;
									}	
									sActualResult = "Message not verified";
									break;
								}
							}
							if(flag == 1) {
								bResult = true;
								sActualResult = "Message verified successfully";
								break;
							}
						}	

						break;
					}
				} catch (Exception error) {
					sActualResult = error.getMessage();
					throw error;
				}
				ResultComparision();
				TestDataImport.setCellData(sSheetName, iRow, 5, sActualResult, "NA");
				TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
				TestDataImport.setCellData(sSheetName, iRow, 6, sTestStepStatus, "NA");
				break;
			}
		}

		return bResult;
	}

	// MEthod to filter location
	public static boolean exportdeal(String sActualTestCaseID) throws Exception {



		boolean bResult = false;
		String sFileName = "Deals.xlsx";
		String sSheetName = "Export";
		sTestStepID = "Export Deal";

		// Copy Loads.xlsx file from test data folder to current log folder
		Copy_File(sTestDataPath + sFileName, sTestResultsPath);

		TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
		int iRowCnt = 0;
		iRowCnt = TestDataImport.GetRowCount(sSheetName);
		// System.out.println("Number of rows:"+iRowCnt);
		for (int iRow = 1; iRow <= iRowCnt; iRow++) {

			TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
			String sTestCaseID = TestDataImport.GetCellData(sSheetName, 0, iRow);
			String sFormat = TestDataImport.GetCellData(sSheetName, 1, iRow);
			String sEmail = TestDataImport.GetCellData(sSheetName, 2, iRow);
			String sOperation = TestDataImport.GetCellData(sSheetName, 3, iRow);
			sExpectedResult = TestDataImport.GetCellData(sSheetName, 4, iRow);
			if (sTestCaseID.trim().equalsIgnoreCase(sActualTestCaseID.trim())) {
				System.out.println("inside if");
				try {
					DealsPage.eMenuDeals().click();
					DealsPage.eDealsBooked().click();
					Thread.sleep(3000);
					DealsPage.eBtnExport().click();
					Thread.sleep(2000);
					//DealsPage.eBtnExportAs().click();
					if(sOperation.equalsIgnoreCase("Download"))
					{
						DealsPage.eRadioBtnDownload().click();
						Thread.sleep(2000);
						System.out.println("set radio button");
						DealsPage.eBtnExportDeal().click();
						System.out.println("clicked on exportdeals");
						WebDriverWait wait = new WebDriverWait(driver,20);
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[text()='Export has been generated!']")));
						//Thread.sleep(5000);
						sActualResult="Deal has been generated";
						bResult=true;
					}
					else
					{
						DealsPage.eRadioBtnSendEmail().click();
						Thread.sleep(2000);
						//DealsPage.eExportEmailAddress().clear();
						DealsPage.eExportEmailAddress().sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
						DealsPage.eExportEmailAddress().sendKeys(sEmail);

						DealsPage.eBtnExportDeal().click();
						//Export request has been sent.
						WebDriverWait wait = new WebDriverWait(driver,20);
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[text()='Export request has been sent.']")));
						//Thread.sleep(5000);
						sActualResult="Export request has been sent.";
						bResult=true;

					}


				} catch (Exception error) {

					sActualResult = error.getMessage();
					System.out.println("export deal error:" + sActualResult);
				}
				ResultComparision();
				TestDataImport.setCellData(sSheetName, iRow, 5, sActualResult, "NA");
				TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
				TestDataImport.setCellData(sSheetName, iRow, 6, sTestStepStatus, "NA");
				break;
			} else {
				sActualResult = "Testcase not found";
			}

		}

		System.out.println("export deal:" + sActualResult);
		return bResult;
	}
	// MEthod to filter location
	public static boolean filterLocation(String sActualTestCaseID) throws Exception {



		boolean bResult = false;
		String sFileName = "Deals.xlsx";
		String sSheetName = "Filter";
		sTestStepID = "filterLocation";

		// Copy Loads.xlsx file from test data folder to current log folder
		Copy_File(sTestDataPath + sFileName, sTestResultsPath);

		TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
		int iRowCnt = 0;
		iRowCnt = TestDataImport.GetRowCount(sSheetName);
		// System.out.println("Number of rows:"+iRowCnt);
		for (int iRow = 1; iRow <= iRowCnt; iRow++) {

			TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
			String sTestCaseID = TestDataImport.GetCellData(sSheetName, 0, iRow);
			String sType = TestDataImport.GetCellData(sSheetName, 1, iRow);
			String sOrigin = TestDataImport.GetCellData(sSheetName, 2, iRow);
			String sDestination = TestDataImport.GetCellData(sSheetName, 3, iRow);
			String sOperation = TestDataImport.GetCellData(sSheetName, 4, iRow);
			sExpectedResult = TestDataImport.GetCellData(sSheetName, 5, iRow);
			if (sTestCaseID.trim().equalsIgnoreCase(sActualTestCaseID.trim())) {
				System.out.println("inside if");
				try {
					DealsPage.eBtnLocation().click();
					if (sOperation.equalsIgnoreCase("Filter")) {
						System.out.println("sType:"+sType);
						switch (sType.toUpperCase()) {
						case "OUTBOUND":
							//date filter
							//datefilter("FILTER", "CurrentDate", "CurrentDate");
							DealsPage.eBtnOutbound().click();
							Thread.sleep(1000);
							DealsPage.eFilterOrigin().click();
							Thread.sleep(1000);
							Actions ac = new Actions(driver);
							System.out.println("sOrigin:" + sOrigin);
							ac = new Actions(driver);
							/*
							 * ac.sendKeys(sOrigin).build().perform();; //DealsPage.eFilterOrigin().clear();
							 * for (int i = 0; i < sOrigin.length(); i++) {
							 * ac.sendKeys(Keys.BACK_SPACE).build().perform(); Thread.sleep(100); }
							 */
							for (int i = 0; i < sOrigin.length(); i++) {
								ac.sendKeys(sOrigin.substring(i, i + 1)).build().perform();
								Thread.sleep(100);
							}
							// DealsPage.eOrigin().clear();
							Thread.sleep(1000);
							System.out.println("searching origin");
							// Thread.sleep(2000);
							ac.sendKeys(Keys.ENTER).build().perform();
							Thread.sleep(2000);
							System.out.println("searched origin");
							break;
						case "RETURN":
							//date filter
							//datefilter("FILTER", "CurrentDate", "CurrentDate");
							System.out.println("clicking on return");
							DealsPage.eBtnReturn().click();
							Thread.sleep(1000);
							// set Dest
							DealsPage.eFilterDestination().click();
							Thread.sleep(2000);
							System.out.println("sDestination:" + sOrigin);
							Actions ac1 = new Actions(driver);
							// ac.sendKeys(sDestination).build().perform();
							for (int i = 0; i < sOrigin.length(); i++) {
								ac1.sendKeys(sOrigin.substring(i, i + 1)).build().perform();
								;
								Thread.sleep(100);
							}


							ac1.sendKeys(Keys.ENTER).build().perform();
							break;
						case "ROUNDTRIP":
							Thread.sleep(1000);
							DealsPage.eBtnRoundTrip().click();
							Thread.sleep(1000);
							DealsPage.eFilterOrigin().click();
							Thread.sleep(1000);
							Actions ac2 = new Actions(driver);
							System.out.println("sOrigin:" + sOrigin);
							ac2 = new Actions(driver);
							/*
							 * ac.sendKeys(sOrigin).build().perform();; //DealsPage.eFilterOrigin().clear();
							 * for (int i = 0; i < sOrigin.length(); i++) {
							 * ac.sendKeys(Keys.BACK_SPACE).build().perform(); Thread.sleep(100); }
							 */
							for (int i = 0; i < sOrigin.length(); i++) {
								ac2.sendKeys(sOrigin.substring(i, i + 1)).build().perform();
								Thread.sleep(100);
							}
							// DealsPage.eOrigin().clear();
							Thread.sleep(1000);
							System.out.println("searching origin");
							// Thread.sleep(2000);
							ac2.sendKeys(Keys.ENTER).build().perform();
							Thread.sleep(2000);
							System.out.println("searched origin");
							// set Dest
							DealsPage.eFilterDestination().click();
							Thread.sleep(5000);
							System.out.println("sDestination:" + sDestination);
							ac2 = new Actions(driver);
							// ac.sendKeys(sDestination).build().perform();
							for (int i = 0; i < sDestination.length(); i++) {
								ac2.sendKeys(sDestination.substring(i, i + 1)).build().perform();
								;
								Thread.sleep(100);
							}

							// DealsPage.eDestination().clear();
							// DealsPage.eDestination().sendKeys(sDestination);
							Thread.sleep(2000);
							ac2.sendKeys(Keys.ENTER).build().perform();
							break;

						}
						Thread.sleep(5000);
						System.out.println("clicking on done");
						DealsPage.ebtnDone().click();
						System.out.println("clicked on done");
						sActualResult = "Location filtered successfully";
						Thread.sleep(5000);
						datefilter("FILTER", "CurrentDate", "CurrentDate");
						//DealsPage.eBtnRoundTrip().click();
					} else {
						DealsPage.eBtnLocReset().click();
						Thread.sleep(5000);
						datefilter("RESET", "NA", "NA");
						sActualResult = "Location reset successfully";
					}

					bResult = true;

				} catch (Exception error) {

					sActualResult = error.getMessage();
					System.out.println("loacation filter error:" + sActualResult);
				}
				ResultComparision();
				TestDataImport.setCellData(sSheetName, iRow, 6, sActualResult, "NA");
				TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
				TestDataImport.setCellData(sSheetName, iRow, 7, sTestStepStatus, "NA");
				break;
			} else {
				sActualResult = "Testcase not found";
			}

		}

		System.out.println("filter locatin:" + sActualResult);
		return bResult;
	}

	// MEthod to counter deal
	public static boolean MuteDeal(String sActualTestCaseID) throws Exception {

		boolean bResult = false;
		String sFileName = "Deals.xlsx";
		String sSheetName = "Counter Deal";
		sTestStepID = "mute deal";

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
			sExpectedResult = TestDataImport.GetCellData(sSheetName, 13, iRow);
			String sExpConverCount = TestDataImport.GetCellData(sSheetName, 11, iRow);
			String sExpRatePerUOM = TestDataImport.GetCellData(sSheetName, 12, iRow);
			System.out.println("accept Deal:" + "sTestCaseID:" + sTestCaseID + "sActualTestCaseID:" + sActualTestCaseID);
			if (sTestCaseID.trim().equalsIgnoreCase(sActualTestCaseID.trim())) {
				System.out.println("inside if");
				try {

					DateTimeFormatter dateandtime = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
					LocalDateTime t1 = LocalDateTime.now();
					String sCurrentDateTime = t1.format(dateandtime);
					/*
					 * // set deal Name if (sDealName.trim().equalsIgnoreCase("AutoDeal")) {
					 * sDealName = sDealName + sCurrentDateTime.replace("/", ""); sDealName =
					 * sDealName.replace(":", ""); sDealName = sDealName.replace(" ", "");
					 * sGenericDealName = sDealName;
					 * 
					 * } System.out.println("Deal name:" + sDealName);
					 * DealsPage.eDealName().clear(); Thread.sleep(2000);
					 * DealsPage.eDealName().sendKeys(sDealName);
					 */ // set commodity
					// DealsPage.eCommodity().clear();
					Thread.sleep(2000);
					List<WebElement> eConversationlist = driver
							.findElements(By.xpath(".//div[@class='left-conversation']"));
					System.out.println("eConversationlist" + eConversationlist.size());
					System.out.println("sExpConverCount" + sExpConverCount);
					if (eConversationlist.size() == Integer.parseInt(sExpConverCount)) {
						int iCOnvCount = 0;
						String sActFromDate = "NA";
						String sActToDate = "NA";
						String sActEquipment = "NA";
						String sActOrigin = "NA";
						String sActDestination = "NA";
						String sActLoads = "NA";
						String sActCommodity = "NA";
						String sActRatePerUOM = "NA";

						for (WebElement eConv : eConversationlist) {
							iCOnvCount++;
							if (iCOnvCount == Integer.parseInt(sExpConverCount)) {
								System.out.println("searchig board items");
								List<WebElement> eboarditems = eConv.findElements(By.className("bot-card-item"));
								System.out.println("eboarditems size: " + eboarditems.size());
								int iBoard = 0;
								for (WebElement eBoard : eboarditems) {
									iBoard++;
									switch (iBoard) {
									case 1:
										List<WebElement> eP = eBoard.findElements(By.tagName("p"));
										int iP = 0;
										for (WebElement eleP : eP) {
											iP++;

											switch (iP) {
											case 1:
												List<WebElement> eS = eleP.findElements(By.tagName("span"));
												int iS = 0;
												for (WebElement eleS : eS) {
													iS++;
													switch (iS) {
													case 1:
														sActFromDate = eleS.getText();
														break;
													case 3:
														sActToDate = eleS.getText();
														break;

													}
												}
												break;
											case 2:
												sActEquipment = eleP.getText();

												break;
											}
										}

										break;
									case 2:
										sActOrigin = eBoard.getAttribute("title");
										List<WebElement> tagP = eBoard.findElements(By.tagName("p"));
										int cntP = 0;
										for (WebElement tag : tagP) {
											cntP++;
											switch (cntP) {
											case 2:
												sActLoads = tag.getText();
												break;
											}
										}
										break;
									case 3:
										sActDestination = eBoard.getAttribute("title");
										break;
									case 4:
										eP = eBoard.findElements(By.tagName("p"));
										iP = 0;
										for (WebElement eleP : eP) {
											iP++;

											switch (iP) {
											case 1:
												sActCommodity = eleP.getText();
												break;
											case 2:
												sActRatePerUOM = eleP.getText();

												break;
											}
										}

										break;
									}
								}
							}

						}
						sFromDate = TestDataImport.GetCellData(sSheetName, 3, iRow);
						sToDate = TestDataImport.GetCellData(sSheetName, 4, iRow);
						dateandtime = DateTimeFormatter.ofPattern("MM/dd");
						t1 = LocalDateTime.now();
						sCurrentDateTime = t1.format(dateandtime);

						sFromDate = sFromDate.replace("Current Date", sCurrentDateTime);
						sToDate = sToDate.replace("Current Date", sCurrentDateTime);
						System.out.println("sFromDate:" + sFromDate);
						System.out.println("sToDate:" + sToDate);
						System.out.println("sCommodity:" + sCommodity);
						System.out.println("sEquipmet:" + sEquipmet);
						System.out.println("sOrigin:" + sOrigin);
						System.out.println("sDestination:" + sDestination);
						System.out.println("sNoOfLoads:" + sNoOfLoads);
						System.out.println("sExpRatePerUOM:" + sExpRatePerUOM);

						System.out.println("sActFromDate:" + sActFromDate);
						System.out.println("sActztoDate:" + sActToDate);
						System.out.println("sActCommodity:" + sActCommodity);
						System.out.println("sActEquipment:" + sActEquipment);
						System.out.println("sActOrigin:" + sActOrigin);
						System.out.println("sActDestination:" + sActDestination);
						System.out.println("sActLoads:" + sActLoads);
						System.out.println("sActRatePerUOM:" + sActRatePerUOM);

						if (sActFromDate.trim().equalsIgnoreCase(sFromDate.trim())
								&& sActCommodity.trim().equalsIgnoreCase(sCommodity.trim())
								&& sActToDate.trim().equalsIgnoreCase(sToDate.trim())
								&& sActEquipment.trim().equalsIgnoreCase(sEquipmet.trim())
								&& sActOrigin.trim().equalsIgnoreCase(sOrigin.trim())
								&& sActDestination.trim().equalsIgnoreCase(sDestination.trim())
								&& sActLoads.trim().equalsIgnoreCase(sNoOfLoads.trim())
								&& sActRatePerUOM.trim().equalsIgnoreCase(sExpRatePerUOM.trim())) {
							CounterPage.eDealChatPlusIcon().click();
							CounterPage.eBtnMute().click();
							//Thread.sleep(1000);
							//CounterPage.eBtnAccept().click();
							Thread.sleep(5000);
							WebDriverWait wait = new WebDriverWait(driver, 5);
							wait.until(ExpectedConditions
									.visibilityOfElementLocated(By.xpath(".//div[@class='accept-message']")));
							bResult = true;
							sActualResult = "Mute deal successfully";
							break;
						} else {
							bResult = false;
							sActualResult = "Deal details not correct";
						}

					} else {
						bResult = false;
						sActualResult = "unable to Mute deal";
					}

				} catch (Exception error) {

					sActualResult = "Unable to mute deal";
					//throw error;
					// throw error;

				}
				ResultComparision();
				TestDataImport.setCellData(sSheetName, iRow, 14, sActualResult, "NA");
				TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
				TestDataImport.setCellData(sSheetName, iRow, 15, sTestStepStatus, "NA");
				break;
			} else {
				sActualResult = "Testcase not found";
			}

		}
		System.out.println("accept deal:" + sActualResult);
		return bResult;
	}

}
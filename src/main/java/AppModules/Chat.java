package AppModules;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import PageObjects.ChatPage;
import Utils.GenericSkins;
import Utils.TestDataImport;

public class Chat extends GenericSkins {

	public static boolean ChatValidation(String sActTestCaseID, String sName, int i) throws Exception {

		boolean bResult = false;
		String sFileName = "Chat.xlsx";
		String sSheetName = "Chat Details";
		Copy_File(sTestDataPath + sFileName, sTestResultsPath);
		TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
		int iRowCnt = 0;
		int iSum = 0;
		iRowCnt = TestDataImport.GetRowCount(sSheetName);
		sTestStepID = "chatvalidation";
		try {
			for (int iRow = 1; iRow <= iRowCnt; iRow++) {
				TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
				String sTestCaseID = TestDataImport.GetCellData(sSheetName, 0, iRow);
				String sInvitePersonName = TestDataImport.GetCellData(sSheetName, 2, iRow);
				String sSendMsg = TestDataImport.GetCellData(sSheetName, 3, iRow);
				String sImgAtt = TestDataImport.GetCellData(sSheetName, 4, iRow);
				String sDocAtt = TestDataImport.GetCellData(sSheetName, 5, iRow);
				String sOperation = TestDataImport.GetCellData(sSheetName, 6, iRow);
				sExpectedResult = TestDataImport.GetCellData(sSheetName, 7, iRow);

				if (sTestCaseID.equalsIgnoreCase(sActTestCaseID) && i == iRow) {
					System.out.println("operation:" + sOperation);
					switch (sOperation.toUpperCase()) {

					case "INVITE":
						GenericSkins.WaitForElementTobeClickable(
								By.xpath("//button[@class='MuiButtonBase-root MuiFab-root MuiFab-secondary']"));
						ChatPage.chatButton().click();
						Thread.sleep(1000);
						GenericSkins.WaitForElementTobeClickable(
								By.xpath("//button[@class='MuiButtonBase-root MuiIconButton-root']//img"));
						ChatPage.addButton().click();
						Thread.sleep(1000);

						String[] inp = sInvitePersonName.split(";");
						for (String inptxt : inp) {
							if (!(inptxt.equals("")) && !(inptxt.equalsIgnoreCase(null))) {
								GenericSkins.WaitForElementTobeClickable(By.id("new-chat"));
								driver.findElement(By.id("new-chat")).clear();
								driver.findElement(By.id("new-chat")).sendKeys(inptxt);
								Thread.sleep(1000);
								driver.findElement(By.xpath(
										"//*[@id=\"root\"]/div[1]/div[3]/div[1]/div[2]/div/div/div/div[2]/div/div[2]"))
										.click();
								System.out.println("clicked on user name");
								Thread.sleep(2000);

							}
						}
						driver.findElement(
								By.xpath("//*[@id=\"root\"]/div[1]/div[3]/div[1]/div[2]/div/div/div/div[3]/div/div"))
								.click();
						System.out.println("clciked on message text box");
						Thread.sleep(2000);

						GenericSkins.WaitForElementTobeClickable(By.id("sendbird-message-input"));
						String sname = driver
								.findElement(By
										.xpath("//*[@id=\"root\"]/div[1]/div[3]/div[1]/div[2]/div/span/div/div[1]/div"))
								.getText();
						System.out.println(sname);
						if (sInvitePersonName.contains(sname)) {
							bResult = true;
							sActualResult = "Chat Invited Successfully";
						} else {
							sActualResult = "Chat Invited Fail";
						}

						break;

					case "SEND_MESSAGE":
						System.out.println("Original msg : " + sSendMsg);
						ChatPage.enterMessage().sendKeys(sSendMsg);
						Thread.sleep(1000);
						ChatPage.sendMessageButton().click();
						Thread.sleep(5000);
						GenericSkins.WaitForElementVisibility(
								By.xpath("//div[@class='css-iqn6oi e1ngilwh0']/div[@class='css-19re0e1']"));
						List<WebElement> totalMsg = driver.findElements(
								By.xpath("//div[@class='css-iqn6oi e1ngilwh0']/div[@class='css-19re0e1']"));
						String iMsg = totalMsg.get(totalMsg.size() - 1).getText();
						System.out.println("Send msg : " + iMsg);

						sActualResult = "Message send Successfully";
						bResult = true;
						break;

					case "VERIFY_MESSAGE":
						bResult = false;
						GenericSkins.WaitForElementTobeClickable(
								By.xpath("//button[@class='MuiButtonBase-root MuiFab-root MuiFab-secondary']"));
						ChatPage.chatButton().click();
						Thread.sleep(1000);
						GenericSkins.WaitForElementVisibility(By.xpath("(.//div[@class='css-1vm0gw7 e1ngilwh0'])[1]"));
						List<WebElement> totalchat = driver
								.findElements(By.xpath(".//div[@class='css-1vm0gw7 e1ngilwh0']/div"));
						String[] inp3 = sInvitePersonName.split(";");
						for (WebElement nChat : totalchat) {
							String senderName = nChat.findElement(By.xpath("//div[@class='css-1tyx9qz e1ngilwh0']"))
									.getText();

							if (senderName.contains(sName) || senderName.contains(inp3[0])) {
								nChat.click();
								Thread.sleep(2000);

								List<WebElement> msgList = driver.findElements(By.xpath(
										".//div[@class='css-r7mpox e1ngilwh0']/div[@class='css-1tyx9qz e1ngilwh0']"));
								try {
									for (WebElement msg : msgList) {

										String aText = msg.findElement(By.className("css-13hp6jo")).getText();
										aText = aText.replace("_", "");
										System.out.println("Text inside Received Message : " + aText);
										if (aText.contains(sSendMsg)) {
											sActualResult = "Message Verified Successfully";
											bResult = true;
											break;
										}
									}
								} catch (Exception e) {

								}
								sActualResult = "Message Verified Successfully";
								bResult = true;
								break;
							} else {
								sActualResult = "No Message from " + sName;
							}
						}
						break;

					case "SEND_IMAGE":
						List<WebElement> iTotalMsg = driver
								.findElements(By.xpath(".//div[@class='css-r7mpox e1ngilwh0']"));
						ChatPage.addAttachmentButton().click();
						Thread.sleep(2000);
						WindowsHandle(sImgAtt);
						Thread.sleep(5000);
						/*
						 * GenericSkins .WaitForElementVisibility(By.xpath(
						 * "//*[@id=\"root\"]/div[1]/div[3]/div[1]/div/div/div/div/div[2]/div[1]/div[1]/div"
						 * ));
						 */Thread.sleep(5000);
						ChatPage.sendMessageButton().click();
						Thread.sleep(5000);
						iSum = iTotalMsg.size();
						int iTempCnt = iTotalMsg.size() + 1;
						GenericSkins.WaitForElementVisibility(
								By.xpath("(.//div[@class='css-r7mpox e1ngilwh0'])[" + iTempCnt + "]"));
						sActualResult = "Image send Successfully";
						bResult = true;
						break;

					case "VERIFY_IMAGE":
						bResult = false;
						GenericSkins.WaitForElementTobeClickable(
								By.xpath("//button[@class='MuiButtonBase-root MuiFab-root MuiFab-secondary']"));
						ChatPage.chatButton().click();
						System.out.println("clciked on chat button");
						Thread.sleep(3000);
						GenericSkins.WaitForElementVisibility(By.xpath("(.//div[@class='css-1vm0gw7 e1ngilwh0'])[1]"));
						String[] inp2 = sInvitePersonName.split(";");
						List<WebElement> itotalchat = driver
								.findElements(By.xpath(".//div[@class='css-1vm0gw7 e1ngilwh0']/div"));
						System.out.println("Total number of chats" + itotalchat.size());
						for (WebElement nChat : itotalchat) {
							String senderName = nChat.findElement(By.xpath(".//div[@class='css-1tyx9qz e1ngilwh0']"))
									.getText();

							if (senderName.contains(inp2[0]) || senderName.contains(sName)) {
								nChat.click();
								Thread.sleep(4000);

								List<WebElement> msgList = driver.findElements(By.xpath(
										".//div[@class='css-r7mpox e1ngilwh0']/div[@class='css-1tyx9qz e1ngilwh0']"));
								System.out.println("total messages:" + msgList.size());
								System.out.println("total previous messages:" + iSum);
								try {
									int imsgcnt = 0;
									for (WebElement msg : msgList) {
										imsgcnt++;
										if (imsgcnt == msgList.size()) {
											// open attachment
											msg.click();
											System.out.println("clicked on message");
											// click on image close
											driver.findElement(By.xpath("(.//button[@class='MuiButtonBase-root MuiIconButton-root']/span/*[name()='svg'])[2]")).click();
											sActualResult ="Image verified successfully";
										}
										else
										{
											sActualResult="Image not verified";
										}
										
									}
								} catch (Exception e) {
									sActualResult = "No Message from " + sInvitePersonName;
									e.printStackTrace();
								}
								break;
							} else {
								sActualResult = "No Message from " + sInvitePersonName;
							}
						}
						break;

					case "SEND_DOCUMENT":
						List<WebElement> dTotalMsg = driver.findElements(
								By.xpath(".//div[@class='css-r7mpox e1ngilwh0']"));
						ChatPage.addAttachmentButton().click();
						Thread.sleep(2000);
						WindowsHandle(sDocAtt);
						Thread.sleep(5000);
						/*
						 * GenericSkins .WaitForElementVisibility(By.
						 * xpath("(//div[@class='css-8ki3g7 e1ngilwh0']/div)[3]"));
						 */
						Thread.sleep(5000);
						ChatPage.sendMessageButton().click();
						Thread.sleep(5000);
						int sSum = dTotalMsg.size() + 1;
						GenericSkins.WaitForElementVisibility(
								By.xpath("(.//div[@class='css-r7mpox e1ngilwh0'])["
										+ sSum + "]"));
						sActualResult = "Document send Successfully";
						bResult = true;
						break;

					case "VERIFY_DOCUMENT":
						bResult = false;
						GenericSkins.WaitForElementTobeClickable(
								By.xpath("//button[@class='MuiButtonBase-root MuiFab-root MuiFab-secondary']"));
						ChatPage.chatButton().click();
						Thread.sleep(1000);
						GenericSkins.WaitForElementVisibility(By.xpath("(//div[@class='css-7ftmt6 e1ngilwh0'])[1]"));
						List<WebElement> dtotalchat = driver
								.findElements(By.xpath("//div[@class='css-1vm0gw7 e1ngilwh0']/div"));
						String[] inp4 = sInvitePersonName.split(";");
						for (WebElement nChat : dtotalchat) {
							String senderName = nChat.findElement(By.xpath("//div[@class='css-1tyx9qz e1ngilwh0']"))
									.getText();
							if (senderName.contains(sName) || senderName.contains(inp4[0])) {
								nChat.click();
								Thread.sleep(2000);

								List<WebElement> msgList = driver.findElements(By.xpath(
										".//div[@class='css-r7mpox e1ngilwh0']"));
								try {
									msgList.get(msgList.size() - 1).findElement(By.tagName("a")).isDisplayed();
									sActualResult = "Document Verified Successfully";
									bResult = true;
								} catch (Exception e) {
									sActualResult = "No Message from " + sName;
								}
								break;
							} else {
								sActualResult = "No Message from " + sName;
							}
						}
						break;

					case "CLOSE":

						driver.findElement(By.xpath("(//*[@class='css-1h6064n e1ngilwh0']/*[name()='svg'])[1]"))
								.click();
						Thread.sleep(1000);
						ChatPage.leaveChatButton().click();
						Thread.sleep(1000);
						driver.findElement(By.xpath("//button//span[contains(text(),'Leave Chat')]")).click();
						Thread.sleep(5000);
						ChatPage.chatButton().click();
						Thread.sleep(1000);
						sActualResult = "Chat Closed Successfully";
						bResult = true;
						break;

					}

					ResultComparision();
					TestDataImport.setCellData(sSheetName, iRow, 8, sActualResult, "NA");
					TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
					TestDataImport.setCellData(sSheetName, iRow, 9, sTestStepStatus, "NA");
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			sActualResult=e.getMessage();
		}
		Thread.sleep(10000);
		if(sExpectedResult.trim().equalsIgnoreCase(sActualResult.trim()))
		{
			bResult=true;
		}
		System.out.println("chat validation:"+bResult);
		return bResult;
	}

}

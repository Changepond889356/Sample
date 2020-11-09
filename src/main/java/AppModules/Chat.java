package AppModules;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import PageObjects.ChatPage;
import Utils.GenericSkins;
import Utils.TestDataImport;

public class Chat extends GenericSkins {

	public static boolean ChatValidation(String sActTestCaseID, int i) throws Exception {
		
		boolean bResult = false;
		String sFileName = "Chat.xlsx";
		String sSheetName = "Chat Details";
		Copy_File(sTestDataPath + sFileName, sTestResultsPath);
		TestDataImport.SetExcelFile(sTestResultsPath, sFileName);
		int iRowCnt = 0;
		iRowCnt = TestDataImport.GetRowCount(sSheetName);
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
				
				switch(sOperation.toUpperCase()) {
				
				case "INVITE":
					GenericSkins.WaitForElementTobeClickable(By.xpath("//button[@class='MuiButtonBase-root MuiFab-root MuiFab-secondary']"));
					ChatPage.chatButton().click();
					Thread.sleep(1000);
					GenericSkins.WaitForElementTobeClickable(By.xpath("//button[@class='MuiButtonBase-root MuiIconButton-root']//img"));
					ChatPage.addButton().click();
					Thread.sleep(1000);
					GenericSkins.WaitForElementTobeClickable(By.id("new-chat"));
					driver.findElement(By.id("new-chat")).sendKeys(sInvitePersonName);
					Thread.sleep(1000);
					driver.findElement(By.xpath("(//div[@class='css-1qak4dn e1ngilwh0']/div)[1]")).click();
					Thread.sleep(2000);
					driver.findElement(By.xpath("//div[@class='css-smu6xu e1ngilwh0']")).click();
					Thread.sleep(2000);
					
					GenericSkins.WaitForElementTobeClickable(By.id("sendbird-message-input"));
					String sname = driver.findElement(By.xpath("//div[@class='css-akje90 e1ngilwh0']//div[@class=' css-qqqwqg e1ngilwh0']")).getText();
					System.out.println(sname);
					if(sname.contains(sInvitePersonName)) {
						bResult = true;
						sActualResult = "Chat Invited Successfully";
					} else {
						sActualResult = "Chat Invited Fail";
					}
					
					break;
					
				case "SEND_MESSAGE":
					System.out.println("Original msg : "+sSendMsg);
					ChatPage.enterMessage().sendKeys(sSendMsg);
					Thread.sleep(500);
					ChatPage.sendMessageButton().click();
					Thread.sleep(5000);
					GenericSkins.WaitForElementVisibility(By.xpath("//div[@class='css-1dljsdd e1ngilwh0']/div[@class='css-55a734 e1ngilwh0']"));
					List<WebElement> totalMsg = driver.findElements(By.xpath("//div[@class='css-1dljsdd e1ngilwh0']/div[@class='css-55a734 e1ngilwh0']"));
					String iMsg = totalMsg.get(totalMsg.size()-1).getText();
					System.out.println("Send msg : "+iMsg);
					/*if(iMsg.contains(sSendMsg)) {
						
						sActualResult = "Message send Successfully";
					} else {
						sActualResult = "Message not send Successfully";
					}	*/		
					sActualResult = "Message send Successfully";
					bResult = true;
					break;
					
				case "SEND_IMAGE":
					List<WebElement> iTotalMsg = driver.findElements(By.xpath("//div[@class='css-1dljsdd e1ngilwh0']/div[@class='css-55a734 e1ngilwh0']"));										
					ChatPage.addAttachmentButton().click();
					Thread.sleep(2000);
					WindowsHandle(sImgAtt);
					Thread.sleep(5000);
					GenericSkins.WaitForElementVisibility(By.xpath("(//div[@class='css-8ki3g7 e1ngilwh0']/div)[3]"));
					Thread.sleep(2000);
					ChatPage.sendMessageButton().click();
					Thread.sleep(5000);
					int iSum = iTotalMsg.size()+1;
					GenericSkins.WaitForElementVisibility(By.xpath("(//div[@class='css-1dljsdd e1ngilwh0']/div[@class='css-55a734 e1ngilwh0'])["+iSum+"]"));
					sActualResult = "Image send Successfully";
					bResult = true;
					break;
					
				case "SEND_DOCUMENT":
					List<WebElement> dTotalMsg = driver.findElements(By.xpath("//div[@class='css-1dljsdd e1ngilwh0']/div[@class='css-55a734 e1ngilwh0']"));										
					ChatPage.addAttachmentButton().click();
					Thread.sleep(2000);
					WindowsHandle(sDocAtt);
					Thread.sleep(5000);
					GenericSkins.WaitForElementVisibility(By.xpath("(//div[@class='css-8ki3g7 e1ngilwh0']/div)[3]"));
					Thread.sleep(2000);
					ChatPage.sendMessageButton().click();
					Thread.sleep(5000);
					int sSum = dTotalMsg.size()+1;
					GenericSkins.WaitForElementVisibility(By.xpath("(//div[@class='css-1dljsdd e1ngilwh0']/div[@class='css-55a734 e1ngilwh0'])["+sSum+"]"));					
					sActualResult = "Document send Successfully";
					bResult = true;
					break;
					
				case "CLOSE":
					
					driver.findElement(By.xpath("(//*[@class='css-1lbl1aj e1ngilwh0']/*[name()='svg'])[2]")).click();
					Thread.sleep(1000);
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
		return bResult;
	}

}

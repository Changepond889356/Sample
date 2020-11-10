package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Utils.GenericSkins;

public class ChatPage extends GenericSkins {

	static WebElement element;
	
	public static WebElement chatButton() {
		try {
			element = driver.findElement(By.xpath("//button[@class='MuiButtonBase-root MuiFab-root MuiFab-secondary']"));
		} catch (Exception ele) {
			sActualResult=ele.getMessage();
			System.out.println("Chat Button : " +sActualResult);
		}
		return element;
	}

	public static WebElement addButton() {
		try {
			element = driver.findElement(By.xpath("//button[@class='MuiButtonBase-root MuiIconButton-root']//img"));
		} catch (Exception ele) {
			sActualResult=ele.getMessage();
			System.out.println("ADD Button : " +sActualResult);
		}
		return element;
	}

	public static WebElement sendMessageButton() {
		try {
			element = driver.findElement(By.xpath("(//div[@class='css-8ki3g7 e1ngilwh0']/div)[3]"));
		} catch (Exception ele) {
			sActualResult=ele.getMessage();
			System.out.println("SendMessage Button : " +sActualResult);
		}
		return element;
	}
	public static WebElement enterMessage() {
		try {
			element = driver.findElement(By.id("sendbird-message-input"));
		} catch (Exception ele) {
			sActualResult=ele.getMessage();
			System.out.println("Enter Message : " +sActualResult);
		}
		return element;
	}
	public static WebElement addAttachmentButton() {
		try {
			element = driver.findElement(By.xpath("(//div[@class='css-8ki3g7 e1ngilwh0']/div)[2]"));
		} catch (Exception ele) {
			sActualResult=ele.getMessage();
			System.out.println("SendMessage Button : " +sActualResult);
		}
		return element;
	}
	public static WebElement leaveChatButton() {
		try {
			element = driver.findElement(By.xpath("//div[contains(text(),'Leave Chat')]"));
		} catch (Exception ele) {
			sActualResult=ele.getMessage();
			System.out.println("Chat Button : " +sActualResult);
		}
		return element;
	}

}

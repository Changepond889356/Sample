package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import Utils.GenericSkins;

public class SharePage extends GenericSkins {

	static WebElement element;

	public static WebElement eSearch() {
		try {
			element = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div[2]/div/div/div/div/div[2]/div/div/li/div/div/textarea[1]"));
		} catch (Exception ele) {

		}
		return element;
	}

	
	public static WebElement eContact() {
		try {
			Thread.sleep(3000);
			element = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div[2]/div/div/div/div/div[3]/div/div/div/div/ul/li/div"));
		
		} catch (Exception ele) {
			sActualResult = ele.getMessage();

		}
		return element;
	}
	public static WebElement eShare() {
		try {
			Thread.sleep(3000);
			element = driver.findElement(By.xpath(".//span[text()='Share']"));
		
		} catch (Exception ele) {
			sActualResult = ele.getMessage();

		}
		return element;
	}
	

}

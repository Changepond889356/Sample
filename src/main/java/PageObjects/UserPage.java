package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import Utils.GenericSkins;

public class UserPage extends GenericSkins {

	static WebElement element;

	public static WebElement eMenuUsers() {
		try {
			element = driver.findElement(By.xpath(".//span[text()='Users']"));
		} catch (Exception ele) {

		}
		return element;
	}

	public static WebElement eInvite() {
		try {
			element = driver.findElement(By.xpath(".//span[text()='Invite']"));
		} catch (Exception ele) {

		}
		return element;
	}

	public static WebElement ebtnAddUser() {
		try {
			element = driver.findElement(By.xpath(".//span[text()='Add a User']"));
		} catch (Exception ele) {

		}
		return element;
	}

	public static WebElement eFullName() {
		try {
			element = driver.findElement(By.xpath("//*[@id=\"name\"]"));
		} catch (Exception ele) {

		}
		return element;
	}

	public static WebElement eEmail() {
		try {
			element = driver.findElement(By.xpath("//*[@id=\"email\"]"));
		} catch (Exception ele) {

		}
		return element;
	}

	public static WebElement ePhone() {
		try {
			element = driver.findElement(By.xpath("//*[@id=\"phone\"]"));
		} catch (Exception ele) {

		}
		return element;
	}

	public static WebElement eRole() {
		try {
			element = driver.findElement(By.xpath("//*[@id='role']/div/div/div/div/div[2]/div/input"));
			element.click();
			Thread.sleep(2000);
			element = driver.findElement(By.xpath("//*[@id='role']/div/div/div/div/div[2]/div/input"));
			// element = driver.findElement(By.xpat"));
		} catch (Exception ele) {
			sActualResult = ele.getMessage();

		}
		return element;
	}

	public static WebElement eCarrier() {
		try {
			Thread.sleep(3000);
			element = driver.findElement(By.xpath("//*[@id='carrier']/div/div/div/div/div[2]/div/input"));
			element.click();
			Thread.sleep(2000);
			element = driver.findElement(By.xpath("//*[@id='carrier']/div/div/div/div/div[2]/div/input"));
			// element = driver.findElement(By.xpat"));
		} catch (Exception ele) {
			sActualResult = ele.getMessage();

		}
		return element;
	}
	public static WebElement eShipper() {
		try {
			Thread.sleep(3000);
			element = driver.findElement(By.xpath("//*[@id='shipper']/div/div/div/div/div[2]/div/input"));
			element.click();
			Thread.sleep(2000);
			element = driver.findElement(By.xpath("//*[@id='shipper']/div/div/div/div/div[2]/div/input"));
			// element = driver.findElement(By.xpat"));
		} catch (Exception ele) {
			sActualResult = ele.getMessage();

		}
		return element;
	}
	
	public static WebElement eDelete() {
		try {
			Thread.sleep(3000);
			element = driver.findElement(By.xpath(".//span[text()='Delete']"));
		
		} catch (Exception ele) {
			sActualResult = ele.getMessage();

		}
		return element;
	}

	public static WebElement eConfirmDelete() {
		try {
			Thread.sleep(3000);
			element = driver.findElement(By.xpath("(.//span[text()='Delete'])[2]"));
		
		} catch (Exception ele) {
			sActualResult = ele.getMessage();

		}
		return element;
	}
	

}

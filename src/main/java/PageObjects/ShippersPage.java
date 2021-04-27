package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import Utils.GenericSkins;

public class ShippersPage extends GenericSkins {

	static WebElement element;

	public static WebElement eMenuShippers() {
		try {
			element = driver.findElement(By.xpath(".//span[text()='Shippers']"));
		} catch (Exception ele) {

		}
		return element;
	}

	public static WebElement eAdd() {
		try {
			element = driver.findElement(By.xpath(".//span[text()='Add']"));
		} catch (Exception ele) {

		}
		return element;
	}

	public static WebElement eCancel() {
		try {
			element = driver.findElement(By.xpath(".//span[text()='Cancel']"));
		} catch (Exception ele) {

		}
		return element;
	}

	public static WebElement ebtnAddShipper() {
		try {
			element = driver.findElement(By.xpath(".//span[text()='Add a Shipper']"));
		} catch (Exception ele) {

		}
		return element;
	}

	public static WebElement eShipperName() {
		try {
			element = driver.findElement(By.xpath("//*[@id=\"shipper\"]"));
		} catch (Exception ele) {

		}
		return element;
	}

	public static WebElement eBillingEmail() {
		try {
			element = driver.findElement(By.xpath("//*[@id=\"billing_email\"]"));
		} catch (Exception ele) {

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
	public static WebElement eColumnPane() {
		try {
			Thread.sleep(3000);
			element = driver.findElement(By.xpath(".//span[text()='Columns']"));
		
		} catch (Exception ele) {
			sActualResult = ele.getMessage();

		}
		return element;
	}
	

}

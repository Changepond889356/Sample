package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Utils.GenericSkins;

public class ShipperContactPage  extends GenericSkins {

	static WebElement element;
	public static WebElement addShipperContactBtn() {
		try {
			element = driver.findElement(By.xpath("*//span[contains(text(),'Add a Contact')]"));
		} catch (Exception ele) {
			sActualResult=ele.getMessage();
			element=null;
			System.out.println("CarrierBtn : " +ele.getMessage());
		}
		return element;
	}
	public static WebElement ShipperContactMenu() {
		try {
			element = driver.findElement(By.xpath("*//span[contains(text(),'Shipper Contacts')]"));
		} catch (Exception ele) {
			sActualResult=ele.getMessage();
			element=null;
			System.out.println("CarrierBtn : " +ele.getMessage());
		}
		return element;
	}
	public static WebElement eName() {
		try {			
			element = driver.findElement(By.xpath("//input[@id='name']"));
		} catch (Exception ele) {
			sActualResult=ele.getMessage();
			element=null;			
		}
		return element;
	}
	public static WebElement ePhone() {
		try {			
			element = driver.findElement(By.xpath("//input[@id='phone']"));
		} catch (Exception ele) {
			sActualResult=ele.getMessage();
			element=null;			
		}
		return element;
	}
	public static WebElement eEmail() {
		try {			
			element = driver.findElement(By.xpath("//input[@id='email']"));
		} catch (Exception ele) {
			sActualResult=ele.getMessage();
			element=null;			
		}
		return element;
	}
	public static WebElement eCompany() {
		try {			
			element = driver.findElement(By.xpath("//div[@id='shipper_uuid']"));
			element.click();
			element = driver.findElement(By.xpath("//div[@id='shipper_uuid']//input"));
		} catch (Exception ele) {
			sActualResult=ele.getMessage();
			element=null;			
		}
		return element;
	}
	public static WebElement eAddBtn() {
		try {			
			element = driver.findElement(By.xpath("*//span[./text()='Add']"));
		} catch (Exception ele) {
			sActualResult=ele.getMessage();
			element=null;	
			System.out.println("eAddBtn " + ele.getMessage());
		}
		return element;
	}
}

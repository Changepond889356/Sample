package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Utils.GenericSkins;

public class CarrierPage extends GenericSkins {
	
	static WebElement element;
	
	public static WebElement CarrierMenu() {
		try {
			element = driver.findElement(By.xpath("//a[@data-cy='carriers']"));
		} catch (Exception ele) {
			sActualResult=ele.getMessage();
			element=null;
			System.out.println("CarrierMenu : " +ele.getMessage());
		}
		return element;
	}

	public static WebElement addCarrierBtn() {
		try {
			element = driver.findElement(By.xpath("//button[@data-cy='add-model-button']//span[contains(text(),'Add a Carrier')]"));
		} catch (Exception ele) {
			sActualResult=ele.getMessage();
			element=null;
			System.out.println("CarrierBtn : " +ele.getMessage());
		}
		return element;
	}

	public static WebElement CarrierName() {
		try {
			element = driver.findElement(By.xpath("//input[@id='outlined-basic']"));
		} catch (Exception ele) {
			sActualResult=ele.getMessage();
			element=null;
			System.out.println("CarrierName : " +ele.getMessage());
		}
		return element;
	}
	public static WebElement SearchBtn() {
		try {
			element = driver.findElement(By.xpath("//button//span[contains(text(),'Search')]"));
		} catch (Exception ele) {
			sActualResult=ele.getMessage();
			element=null;
			System.out.println("Search Btn : " +ele.getMessage());
		}
		return element;
	}

	public static WebElement AddNewCarrierBtn() {
		try {
			element = driver.findElement(By.xpath("//button//span[contains(text(),'Add New Carrier')]"));
		} catch (Exception ele) {
			sActualResult=ele.getMessage();
			element=null;
			System.out.println("AddNewCarrierBtn : " +ele.getMessage());
		}
		return element;
	}

	public static WebElement SubmitRequestBtn() {
		try {
			element = driver.findElement(By.xpath("//button//span[contains(text(),'Submit Request')]"));
		} catch (Exception ele) {
			sActualResult=ele.getMessage();
			element=null;
			System.out.println("SubmitRequestBtn : " +ele.getMessage());
		}
		return element;
	}

	public static WebElement FinishBtn() {
		try {
			element = driver.findElement(By.xpath("//button//span[contains(text(),'Finish')]"));
		} catch (Exception ele) {
			sActualResult=ele.getMessage();
			element=null;
			System.out.println("FinishBtn : " +ele.getMessage());
		}
		return element;
	}

	public static WebElement NextBtn() {
		try {
			element = driver.findElement(By.xpath("//button//span[contains(text(),'Next')]"));
		} catch (Exception ele) {
			sActualResult=ele.getMessage();
			element=null;
			System.out.println("Next Btn : " +ele.getMessage());
		}
		return element;
	}
	public static WebElement InviteRequestBtn() {
		try {
			element = driver.findElement(By.xpath("//button//span[contains(text(),'Invite Carrier')]"));
		} catch (Exception ele) {
			sActualResult=ele.getMessage();
			element=null;
			System.out.println("Invite Request Btn : " +ele.getMessage());
		}
		return element;
	}

	public static WebElement AcceptBtn() {
		try {
			element = driver.findElement(By.xpath("//button[@data-cy='add-model-button']//span[contains(text(),'Accept')]"));
		} catch (Exception ele) {
			sActualResult=ele.getMessage();
			element=null;
			System.out.println("Accept Btn : " +ele.getMessage());
		}
		return element;
	}
}

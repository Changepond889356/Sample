package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Utils.GenericSkins;

public class CounterPage extends GenericSkins{
	static WebElement element = null;
	public static WebElement eDealChatPlusIcon() throws Exception {
		try {
			element = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div[2]/div/div/div/div/div/div[2]/div[2]/div[2]/div[1]/div[1]/img"));
			//element.click();
			//System.out.println("clicked on ");
			
			//element = driver.findElement(By.xpath("//div[@id='commodity_uuid']/div/div/div/div/div[2]/div/input"));
		} catch (Exception ele) {
			sActualResult = ele.getMessage();
			element = null;
			//throw ele;
		}
		return element;
	}
	public static WebElement eDealsCounter() throws Exception {
		try {
			element = driver.findElement(By.xpath(".//ul[@class='MuiList-root MuiMenu-list MuiList-padding']/div[2]/span"));
			
			//element = driver.findElement(By.xpath("//div[@id='commodity_uuid']/div/div/div/div/div[2]/div/input"));
		} catch (Exception ele) {
			sActualResult = ele.getMessage();
			element = null;
			//throw ele;
		}
		return element;
	}
	public static WebElement eDealsAccept() throws Exception {
		try {
			element = driver.findElement(By.xpath(".//ul[@class='MuiList-root MuiMenu-list MuiList-padding']/div[3]/span"));
			
			//element = driver.findElement(By.xpath("//div[@id='commodity_uuid']/div/div/div/div/div[2]/div/input"));
		} catch (Exception ele) {
			sActualResult = ele.getMessage();
			element = null;
			//throw ele;
		}
		return element;
	}
	
	public static WebElement eBtnCounter() throws Exception {
		try {
			element = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div[2]/div/div/div/div/div/div[3]/div/div[2]/form/div/div/div[2]/div/div/button"));
			//Thread.sleep(1000);
			//element = driver.findElement(By.xpath("//div[@id='commodity_uuid']/div/div/div/div/div[2]/div/input"));
		} catch (Exception ele) {
			sActualResult = ele.getMessage();
			element = null;
			//throw ele;
		}
		return element;
	}
	public static WebElement eBtnAccept() throws Exception {
		try {
			element = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div[2]/div/div/div/div/div/div[3]/div/div[2]/form/div/div/div[2]/div/div/button/span[1]"));
			//Thread.sleep(1000);
			//element = driver.findElement(By.xpath("//div[@id='commodity_uuid']/div/div/div/div/div[2]/div/input"));
		} catch (Exception ele) {
			sActualResult = ele.getMessage();
			element = null;
			//throw ele;
		}
		return element;
	}
	public static WebElement eRate() {
		try {
			element = driver.findElement(By.xpath("//*[@id=\"rate\"]"));
			//element.click();
			Thread.sleep(1000);
			//element = driver.findElement(By.xpath("//*[@id=\"react-select-2-input\"]"));
		} catch (Exception ele) {
			
		}
		return element;
	}
	public static WebElement eUOM() {
		try {
			element = driver.findElement(By.xpath("//*[@id=\"rate_uom_uuid\"]/div/div/div"));
			//element.click();
			//Thread.sleep(1000);
			//element = driver.findElement(By.xpath("//*[@id=\"react-select-2-input\"]"));
		} catch (Exception ele) {
			
		}
		return element;
	}

	public static WebElement eFromDate() {
		try {
			// element =
			// driver.findElement(By.xpath("(.//div[@class='react-datepicker-wrapper'])[1]"));
			// element.click();
			element = driver
					.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div[2]/div/div/div/div/div/div[3]/div/div[2]/form/div/div/div[1]/div/div[1]/div/div[1]/div/div/div/input"));

			// element = driver.findElement(By.xpath("//*[@id=\"react-select-2-input\"]"));
		} catch (Exception ele) {
			sActualResult = ele.getMessage();
			element = null;
			throw ele;
			// element = null;
		}
		return element;
	}

	public static WebElement eTodate() {
		try {
			// element =
			// driver.findElement(By.xpath("(.//div[@class='react-datepicker-wrapper'])[1]"));
			// element.click();
			element = driver
					.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div[2]/div/div/div/div/div/div[3]/div/div[2]/form/div/div/div[1]/div/div[2]/div/div[1]/div/div/div/input"));

			// element = driver.findElement(By.xpath("//*[@id=\"react-select-2-input\"]"));
		} catch (Exception ele) {
			sActualResult = ele.getMessage();
			element = null;
			throw ele;
			// element = null;
		}
		return element;
	}
	public static WebElement eDealCommodity() {
		try {
			element = driver.findElement(By.xpath("//*[@id=\"commodity_uuid\"]/div/div/div"));
		} catch (Exception ele) {
			sActualResult = ele.getMessage();
			element = null;

		}
		return element;
	}


	public static WebElement eEquipmetType() {
		try {
			element = driver.findElement(By.xpath("//*[@id=\"equipment_uuid\"]/div/div/div"));
			// element = driver.findElement(By.xpath("//*[@id=\"react-select-2-input\"]"));
		} catch (Exception ele) {
			sActualResult = ele.getMessage();
			element = null;
			element = null;
		}
		return element;
	}


	public static WebElement eNoOfLoads() {
		try {
			element = driver.findElement(By.xpath("//*[@id=\"load_number\"]"));
			// element = driver.findElement(By.xpath("//*[@id=\"react-select-2-input\"]"));
		} catch (Exception ele) {
			sActualResult = ele.getMessage();
			element = null;
			element = null;
		}
		return element;
	}
	public static WebElement eSource() {
		try {
			element = driver.findElement(By.xpath("//*[@id=\"origin\"]/div[1]/div[1]"));
			// element = driver.findElement(By.xpath("//*[@id=\"react-select-2-input\"]"));
		} catch (Exception ele) {
			sActualResult = ele.getMessage();
			element = null;
			element = null;
		}
		return element;
	}
	public static WebElement eDestination() {
		try {
			element = driver.findElement(By.xpath("//*[@id=\"destination\"]/div[1]/div[1]"));
			// element = driver.findElement(By.xpath("//*[@id=\"react-select-2-input\"]"));
		} catch (Exception ele) {
			sActualResult = ele.getMessage();
			element = null;
			element = null;
		}
		return element;
	}




}

package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import Utils.GenericSkins;

public class LoadsPage extends GenericSkins {

	static WebElement element;

	public static WebElement eMenuLoads() {
		try {
			element = driver.findElement(By.xpath(".//span[text()='Loads']"));
		} catch (Exception ele) {
			sActualResult=ele.getMessage();
			element=null;

		}
		return element;
	}

	public static WebElement eAddNewLoad() {
		try {
			element = driver.findElement(
					By.xpath(".//span[text()='Add New Load']"));
		} catch (Exception ele) {
			sActualResult=ele.getMessage();
			element=null;

		}
		return element;
	}

	public static WebElement eAllTab() {
		try {
			element = driver.findElement(By.xpath(".//span[@class='MuiButton-label']/p[text()='All']"));
		} catch (Exception ele) {
			sActualResult=ele.getMessage();
			element=null;

		}
		return element;
	}

	public static WebElement eColumnPane() {
		try {
			element = driver.findElement(By.xpath(".//span[text()='Columns']"));
		} catch (Exception ele) {
			sActualResult=ele.getMessage();
			element=null;

		}
		return element;
	}

	public static WebElement eListCarrier() {
		try {
			element = driver.findElement(By.xpath("//*[@id=\"carrier_uuid\"]/div/div/div/div"));
			element.click();
			Thread.sleep(1000);
			element = driver.findElement(By.xpath("//*[@id=\"react-select-2-input\"]"));
		} catch (Exception ele) {
			sActualResult=ele.getMessage();
			element=null;
			element = null;
		}
		return element;
	}

	public static WebElement eAddNewLoadDate() {
		try {
			//element = driver.findElement(By.xpath("(.//div[@class='react-datepicker-wrapper'])[1]"));
			//element.click();
			element = driver.findElement(By.xpath(
					"(.//div[@class='react-datepicker-wrapper'])[1]/div[1]/div/div/input"));
			
			// element = driver.findElement(By.xpath("//*[@id=\"react-select-2-input\"]"));
		} catch (Exception ele) {
			sActualResult=ele.getMessage();
			element=null;
			throw ele;
			//element = null;
		}
		return element;
	}

	public static WebElement eShipper() {
		try {
			element = driver.findElement(By.xpath(".//div[@id='shipper_uuid']/div/div/div/div/div[2]/div/input"));
			element.click();
			Thread.sleep(1000);
			element = driver.findElement(By.xpath(".//div[@id='shipper_uuid']/div/div/div/div/div[2]/div/input"));
		} catch (Exception ele) {
			sActualResult=ele.getMessage();
			element=null;
			element = null;
		}
		return element;
	}

	public static WebElement eCommodity() {
		try {
			element = driver.findElement(By.xpath("//div[@id='commodity_uuid']/div/div/div/div/div[2]/div/input"));
			element.click();
			Thread.sleep(1000);
			element = driver.findElement(By.xpath("//div[@id='commodity_uuid']/div/div/div/div/div[2]/div/input"));
		} catch (Exception ele) {
			sActualResult=ele.getMessage();
			element=null;
			element = null;
		}
		return element;
	}
	public static WebElement eShipperContact() {
		try {
			
			element = driver.findElement(By.xpath(".//input[@id='contact_uuid']"));
			element.click();
			Thread.sleep(1000);
			element = driver.findElement(By.xpath(".//input[@id='contact_uuid']"));
		} catch (Exception ele) {
			
			sActualResult=ele.getMessage();
			element=null;;
		}
		return element;
	}

	public static WebElement eRate() {
		try {
			element = driver.findElement(By.xpath("//*[@id=\"rate\"]"));
			// element = driver.findElement(By.xpath("//*[@id=\"react-select-2-input\"]"));
		} catch (Exception ele) {
			sActualResult=ele.getMessage();
			element=null;
			element = null;
		}
		return element;
	}

	public static WebElement eSave() {
		try {
			element = driver.findElement(By.xpath(".//span[text()='Save']"));
			// element = driver.findElement(By.xpath("//*[@id=\"react-select-2-input\"]"));
		} catch (Exception ele) {
			sActualResult=ele.getMessage();
			element=null;
			element = null;
		}
		return element;
	}
	public static WebElement eEdit() {
		try {
			element = driver.findElement(By.xpath(".//span[contains(text(),'Edit')]"));
			// element = driver.findElement(By.xpath("//*[@id=\"react-select-2-input\"]"));
		} catch (Exception ele) {
			sActualResult=ele.getMessage();
			element=null;
			element = null;
		}
		return element;
	}

	public static WebElement eDelete() {
		try {
			element = driver.findElement(By.xpath(".//span[contains(text(),'Delete')]"));
			// element = driver.findElement(By.xpath("//*[@id=\"react-select-2-input\"]"));
		} catch (Exception ele) {
			sActualResult=ele.getMessage();
			element=null;
			element = null;
		}
		return element;
	}
	public static WebElement eOrigin() {
		try {
			element = driver.findElement(By.xpath("//*[@id=\"origin_uuid\"]"));
			// element = driver.findElement(By.xpath("//*[@id=\"react-select-2-input\"]"));
		} catch (Exception ele) {
			sActualResult=ele.getMessage();
			element=null;
			element = null;
		}
		return element;
	}

	public static WebElement eDestination() {
		try {
			element = driver.findElement(By.xpath("//*[@id=\"destination_uuid\"]"));
			// element = driver.findElement(By.xpath("//*[@id=\"react-select-2-input\"]"));
		} catch (Exception ele) {
			sActualResult=ele.getMessage();
			element=null;
			element = null;
		}
		return element;
	}

	public static WebElement eRateUOM() {
		try {
			element = driver.findElement(By.xpath(".//div[@id='rate_uom_uuid']/div/div/div/div/div[2]/div/input"));
			element.click();
			Thread.sleep(2000);
			element = driver.findElement(By.xpath(".//div[@id='rate_uom_uuid']/div/div/div/div/div[2]/div/input"));
			// element = driver.findElement(By.xpath("//*[@id=\"react-select-2-input\"]"));
		} catch (Exception ele) {
			sActualResult=ele.getMessage();
			element=null;
			element = null;
		}
		return element;
	}

	public static WebElement eDriver()
	{
		try
		{
			element = driver.findElement(By.xpath("//*[@id='driver_uuid']/div/div/div/div/div[2]/div/input"));
			element.click();
		}
		catch(Exception ele)
		{
			sActualResult=ele.getMessage();
		}
		return element;
	}

public static WebElement eNetOriginWt()
{
	try
	{
		element = driver.findElement(By.xpath("//*[@id='origin_weight']"));
	}
	catch(Exception ele)
	{
		sActualResult=ele.getMessage();
	}
	return element;
}
public static WebElement eNetDestWr()
{
	try
	{
		element = driver.findElement(By.xpath("//*[@id='destination_weight']"));
	}
	catch(Exception ele)
	{
		sActualResult=ele.getMessage();
	}
	return element;
}

}

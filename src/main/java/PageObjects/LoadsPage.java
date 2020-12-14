package PageObjects;

import java.util.List;

import org.openqa.selenium.Alert;
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
			element = driver.findElement(By.xpath("//div[@id='carrier_uuid']"));
			element.click();
			Thread.sleep(1000);
			element = driver.findElement(By.xpath("//div[@id='carrier_uuid']//input"));
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
	public static WebElement eShipperContact() throws InterruptedException {
		try {
			
			element = driver.findElement(By.xpath("//input[@id='contact_uuid']"));  //.//input[@id='contact_uuid']
			element.click();
			Thread.sleep(1000);
			element = driver.findElement(By.xpath("//input[@id='contact_uuid']"));
		} catch (Exception ele) {
			element = driver.findElement(By.xpath("//div[@id='contact_uuid']//input"));  //.//input[@id='contact_uuid']
			element.click();
			Thread.sleep(1000);
			element = driver.findElement(By.xpath("//div[@id='contact_uuid']//input"));
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
			System.out.println("Save " + ele.getMessage());
		}
		return element;
	}
	public static WebElement eEdit() throws InterruptedException {
		try {
			element = driver.findElement(By.xpath(".//span[contains(text(),'Edit')]"));
			// element = driver.findElement(By.xpath("//*[@id=\"react-select-2-input\"]"));
		} catch (Exception ele) {
			sActualResult=ele.getMessage();
			element=null;
			System.out.println("Edit - " +ele.getMessage() );
		}
		Thread.sleep(4000);
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

public static WebElement Submit() {
	// TODO Auto-generated method stub
	try
	{
		element = driver.findElement(By.xpath(".//span[./text()='Submit Load(s)']"));
	}
	catch(Exception ele)
	{
		sActualResult=ele.getMessage();
	}
	return element;
}

	public static WebElement SubmitLoad() {
		// TODO Auto-generated method stub
		try
		{
			element = driver.findElement(By.xpath("(//span[contains(text(),'Submit')])[5]"));
		}
		catch(Exception ele)
		{
			sActualResult=ele.getMessage();
			System.out.println("Submit load "+ ele.getMessage());
		}
		return element;
	}

	public static WebElement GenerateInvoice() {
		// TODO Auto-generated method stub
		try
		{
			element = driver.findElement(By.xpath("(//button//span[contains(text(),'Generate')])[2]"));
			
		}
		catch(Exception ele)
		{
			sActualResult=ele.getMessage();
		}
		return element;
	}
	public static WebElement GenerateBtn() {
		// TODO Auto-generated method stub
		try
		{
			element = driver.findElement(By.xpath("//button[@data-cy='generate-invoice-button']"));
		}
		catch(Exception ele)
		{
			sActualResult=ele.getMessage();
		}
		return element;
	}
	public static WebElement status() {
		try {
			element = driver.findElement(By.xpath("//div[@id='status']//input"));
			element.click();
		} catch(Exception ele) {
			sActualResult=ele.getMessage();
		}
		return element;
	}

	public static WebElement SubmittedView() {
		// TODO Auto-generated method stub
		try {
			element = driver.findElement(By.xpath(".//p[./text()='Submitted']"));
		} catch(Exception ele) {
			sActualResult=ele.getMessage();
		}
		return element;
	}
	public static WebElement PaidView() {
		// TODO Auto-generated method stub
		try {
			element = driver.findElement(By.xpath(".//p[./text()='Paid']"));
		} catch(Exception ele) {
			sActualResult=ele.getMessage();
		}
		return element;
	}
	public static WebElement DuplicateBtn() {
		// TODO Auto-generated method stub
		try {
			element = driver.findElement(By.xpath(".//span[./text()='Duplicate']"));
		} catch(Exception ele) {
			sActualResult=ele.getMessage();
			System.out.println("Duplicate Btn : " + ele.getMessage());
		}
		return element;
	}
	public static WebElement SubmitDuplicateCopy() {
		// TODO Auto-generated method stub
		try {
			element = driver.findElement(By.xpath(".//span[./text()='Submit']"));
		} catch(Exception ele) {
			sActualResult=ele.getMessage();
		}
		return element;
	}
	public static WebElement eGenerateInvoice() {
		// TODO Auto-generated method stub
		try {
			element = driver.findElement(By.xpath("/html/body/div[3]/div[3]/div/div/div[2]/span/div/*[name()='svg']"));
		} catch (Exception ele) {
			sActualResult = ele.getMessage();
		}
		return element;
	}

	public static WebElement ApprovedView() throws InterruptedException {
		try {
			element = driver.findElement(By.xpath(".//p[./text()='Approved']"));
		} catch(Exception ele) {
			sActualResult=ele.getMessage();
			System.out.println("Approved View " + ele.getMessage());
		}
		Thread.sleep(4000);
		return element;
	}
	public static WebElement DispatchBtn() {
		try {
			element = driver.findElement(By.xpath(".//span[./text()='Dispatch']"));
		} catch(Exception ele) {
			sActualResult=ele.getMessage();
		}
		return element;
	}
	public static WebElement ViewAll() throws InterruptedException {
		try {
			element = driver.findElement(By.xpath(".//p[./text()='All']"));
		} catch(Exception ele) {
			sActualResult=ele.getMessage();
		}
		Thread.sleep(4000);
		return element;
	}
	
	public static void LoadDatePicker(String sDay) throws InterruptedException {
		driver.findElement(By.xpath(
				"(.//div[@class='react-datepicker-wrapper'])[1]/div[1]/div/div/input")).click();
		Thread.sleep(500);
		List<WebElement> daylist = driver.findElements(By.xpath("//div[@class='react-datepicker__month']//div"));
		for(WebElement day: daylist) {
			if(day.getText().equalsIgnoreCase(sDay)) {
				day.click();
				Thread.sleep(1000);
				break;
			}
		}
	}

	public static WebElement eDestRef() throws InterruptedException {
		try {
			element = driver.findElement(By.xpath("(//*[@id='fields-to-map']//input)[1]"));
		} catch(Exception ele) {
			sActualResult=ele.getMessage();
		}
		Thread.sleep(4000);
		return element;
	}
	public static WebElement eOriginRef() throws InterruptedException {
		try {
			element = driver.findElement(By.xpath("(//*[@id='fields-to-map']//input)[2]"));
		} catch(Exception ele) {
			sActualResult=ele.getMessage();
		}
		Thread.sleep(4000);
		return element;
	}
	public static WebElement eTransID() throws InterruptedException {
		try {
			element = driver.findElement(By.xpath("(//*[@id='fields-to-map']//input)[3]"));
		} catch(Exception ele) {
			sActualResult=ele.getMessage();
		}
		Thread.sleep(4000);
		return element;
	}
	public static WebElement eAmount() {
		try {
			element = driver.findElement(By.xpath(".//div[@id='rate_uom_uuid']/div/div/div/div/div[2]/div/input"));
			element.click();
			Thread.sleep(2000);
			element = driver.findElement(By.xpath(".//div[@id='rate_uom_uuid']/div/div/div/div/div[2]/div/input"));
			// element = driver.findElement(By.xpath("//*[@id=\"react-select-2-input\"]"));
		} catch (Exception ele) {
			sActualResult=ele.getMessage();
			element=null;
		}
		return element;
	}
}

package PageObjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import Utils.GenericSkins;

public class DealsPage extends GenericSkins {

	static WebElement element;

	public static WebElement eMenuDeals() {
		try {
			element = driver.findElement(By.xpath(".//span[text()='deals']"));
		} catch (Exception ele) {
			sActualResult = ele.getMessage();
			element = null;

		}
		return element;
	}

	public static WebElement eAddDeal() {
		try {
			element = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div[2]/div/div/div/div[2]/div/*[name()='svg']"));
		} catch (Exception ele) {
			sActualResult = ele.getMessage();
			element = null;

		}
		return element;
	}

	public static WebElement eDealName() {
		try {
			element = driver.findElement(By.xpath("//*[@id=\"name\"]"));
		} catch (Exception ele) {
			sActualResult = ele.getMessage();
			element = null;

		}
		return element;
	}

	public static WebElement eDealCommodity() {
		try {
			element = driver.findElement(By.xpath("//*[@id=\"commodity_uuid\"]/div/div/div/div"));
		} catch (Exception ele) {
			sActualResult = ele.getMessage();
			element = null;

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

	public static WebElement eFromDate() {
		try {
			// element =
			// driver.findElement(By.xpath("(.//div[@class='react-datepicker-wrapper'])[1]"));
			// element.click();
			element = driver
					.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div[2]/div/div/div/div/form/div[2]/div/div[2]/div/div[2]/div/div[1]/div/div/div/input"));

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
					.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div[2]/div/div/div/div/form/div[2]/div/div[2]/div/div[3]/div/div[1]/div/div/div/input"));

			// element = driver.findElement(By.xpath("//*[@id=\"react-select-2-input\"]"));
		} catch (Exception ele) {
			sActualResult = ele.getMessage();
			element = null;
			throw ele;
			// element = null;
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
			sActualResult = ele.getMessage();
			element = null;
			element = null;
		}
		return element;
	}

	public static WebElement eCommodity() throws Exception {
		try {
			element = driver.findElement(By.xpath("//*[@id=\"commodity_uuid\"]/div/div/div/*[name()='svg']"));
			element.click();
			System.out.println("clicked on commodities");
			Thread.sleep(1000);
			//element = driver.findElement(By.xpath("//div[@id='commodity_uuid']/div/div/div/div/div[2]/div/input"));
		} catch (Exception ele) {
			sActualResult = ele.getMessage();
			element = null;
			//throw ele;
		}
		return element;
	}

	public static WebElement eShipperContact() {
		try {
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			element = driver.findElement(By.xpath(".//input[@id='contact_uuid']"));
			element.click();
			Thread.sleep(1000);
			element = driver.findElement(By.xpath(".//input[@id='contact_uuid']"));
		} catch (Exception ele) {

			try {
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				element = driver.findElement(By.xpath("//div[@id='contact_uuid']/div/div/div/div/div[2]/div/input"));
				element.click();
				Thread.sleep(1000);
				element = driver.findElement(By.xpath("//div[@id='contact_uuid']/div/div/div/div/div[2]/div/input"));
			} catch (Exception e) {

				sActualResult = e.getMessage();
				element = null;
				;
			}

		}
		return element;
	}

	public static WebElement eUOM() {
		try {
			element = driver.findElement(By.xpath("//*[@id=\"rate_uom_uuid\"]/div/div/div/div"));
			// element = driver.findElement(By.xpath("//*[@id=\"react-select-2-input\"]"));
		} catch (Exception ele) {
			sActualResult = ele.getMessage();
			element = null;
			element = null;
		}
		return element;
	}

	public static WebElement eEquipmetType() {
		try {
			element = driver.findElement(By.xpath("//*[@id=\"equipment_uuid\"]/div/div/div/div"));
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


	public static WebElement eDealsDraft() {
		try {
			element = driver.findElement(By.xpath("//*[@id=\"view-toggle\"]/button[1]/span[1]"));
			// element = driver.findElement(By.xpath("//*[@id=\"react-select-2-input\"]"));
		} catch (Exception ele) {
			sActualResult = ele.getMessage();
			element = null;
			
		}
		return element;
	}
	public static WebElement eDealsOpportunity() {
		try {
			element = driver.findElement(By.xpath("//*[@id=\"view-toggle\"]/button[2]/span[1]"));
			// element = driver.findElement(By.xpath("//*[@id=\"react-select-2-input\"]"));
		} catch (Exception ele) {
			sActualResult = ele.getMessage();
			element = null;
			
		}
		return element;
	}
	public static WebElement eDealsWithDrawTab() {
		try {
			element = driver.findElement(By.xpath("//*[@id=\"view-toggle\"]/button[4]/span[1]"));
			// element = driver.findElement(By.xpath("//*[@id=\"react-select-2-input\"]"));
		} catch (Exception ele) {
			sActualResult = ele.getMessage();
			element = null;
			
		}
		return element;
	}
	public static WebElement eDealChatPlusIcon() throws Exception {
		try {
			element = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div[2]/div/div/div/div/div/div[2]/div[2]/div[2]/div[1]/div[1]/img"));
			element.click();
			System.out.println("clicked on commodities");
			Thread.sleep(1000);
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
			element = driver.findElement(By.xpath(".//span{text()='Counter'}"));
			element.click();
			System.out.println("clicked on commodities");
			Thread.sleep(1000);
			//element = driver.findElement(By.xpath("//div[@id='commodity_uuid']/div/div/div/div/div[2]/div/input"));
		} catch (Exception ele) {
			sActualResult = ele.getMessage();
			element = null;
			//throw ele;
		}
		return element;
	}



	public static WebElement eSave() {
		try {
			element = driver.findElement(By.xpath(".//span[text()='Save']"));
			// element = driver.findElement(By.xpath("//*[@id=\"react-select-2-input\"]"));
		} catch (Exception ele) {
			sActualResult = ele.getMessage();
			element = null;
			element = null;
		}
		return element;
	}

	public static WebElement eNext() {
		try {
			element = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div[2]/div/div/div/div/form/div[1]/div[2]/button"));
			// element = driver.findElement(By.xpath("//*[@id=\"react-select-2-input\"]"));
		} catch (Exception ele) {
			sActualResult = ele.getMessage();
			element = null;
			element = null;
		}
		return element;
	}
	
	public static WebElement eCopies() {
		try {
			element = driver.findElement(By.xpath("//*[@id=\"copies\"]"));
			// element = driver.findElement(By.xpath("//*[@id=\"react-select-2-input\"]"));
		} catch (Exception ele) {
			sActualResult = ele.getMessage();
			element = null;
		}
		return element;
	}
	public static WebElement eCopiesSubmit() {
		try {
			element = driver.findElement(By.xpath(".//*[text()='Submit']"));
			// element = driver.findElement(By.xpath("//*[@id=\"react-select-2-input\"]"));
		} catch (Exception ele) {
			sActualResult = ele.getMessage();
			element = null;
		}
		return element;
	}

	public static WebElement eDelete() {
		try {
			element = driver.findElement(By.xpath(".//span[contains(text(),'Delete')]"));
			// element = driver.findElement(By.xpath("//*[@id=\"react-select-2-input\"]"));
		} catch (Exception ele) {
			sActualResult = ele.getMessage();
			element = null;
			element = null;
		}
		return element;
	}

	public static WebElement eOrigin() {
		try {
			element = driver.findElement(By.xpath("//*[@id=\"origin\"]/div[1]"));
            element.click();
            Thread.sleep(2000);
			element = driver.findElement(By.xpath("//*[@id=\"react-select-3-input\"]"));
		} catch (Exception ele) {
			sActualResult = ele.getMessage();
			element = null;
			element = null;
		}
		return element;
	}

	public static WebElement eDestination() {
		try {
			element = driver.findElement(By.xpath("//*[@id=\"destination\"]/div[1]"));
			element.click();
			element = driver.findElement(By.xpath("//*[@id=\"react-select-4-input\"]"));
		} catch (Exception ele) {
			sActualResult = ele.getMessage();
			element = null;
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
			sActualResult = ele.getMessage();
			element = null;
			element = null;
		}
		return element;
	}

	public static WebElement eDriver() {
		try {
			element = driver.findElement(By.xpath("//*[@id='driver_uuid']/div/div/div/div/div[2]/div/input"));
			element.click();
		} catch (Exception ele) {
			sActualResult = ele.getMessage();
		}
		return element;
	}

	public static WebElement eNetOriginWt() {
		try {
			element = driver.findElement(By.xpath("//*[@id='origin_weight']"));
		} catch (Exception ele) {
			sActualResult = ele.getMessage();
		}
		return element;
	}

	public static WebElement eNetDestWr() {
		try {
			element = driver.findElement(By.xpath("//*[@id='destination_weight']"));
		} catch (Exception ele) {
			sActualResult = ele.getMessage();
		}
		return element;
	}

	public static WebElement eChatTextField() {
		try {
			element = driver.findElement(By.xpath("//*[@id='sendbird-message-input']"));
		} catch (Exception ele) {
			sActualResult = ele.getMessage();
		}
		return element;
	}
	public static WebElement eChatMsgSendButton() {
		try {
			element = driver.findElement(By.xpath("//*[@id='root']/div/div[3]/div[2]/div/div/div/div/div/div[2]/div[2]/div[2]/div[1]/div[2]/div/div[2]/div[2]"));
			element.click();
		} catch (Exception ele) {
			sActualResult = ele.getMessage();
		}
		return element;
	}
}

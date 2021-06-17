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

			element = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div[2]/div/div/div/div[1]/div[2]/div/*[name()='svg']"));
		
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
			element = driver.findElement(By.xpath("//*[@id=\"origin_uuid\"]"));
            element.click();
            System.out.println("clicked on origin");
            Thread.sleep(1000);
			//element = driver.findElement(By.xpath("//*[@id=\"react-select-3-input\"]"));
		} catch (Exception ele) {
			sActualResult = ele.getMessage();
			//element = null;
			element = null;
		}
		return element;
	}

	public static WebElement eDestination() {
		try {
			element = driver.findElement(By.xpath("//*[@id=\"destination_uuid\"]"));
			element.click();
			//element = driver.findElement(By.xpath("//*[@id=\"react-select-4-input\"]"));
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
			element = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div[2]/div/div/div/div[2]/div[2]/div[2]/div[1]/div[2]/div/div[2]/div[2]"));
			element.click();
		} catch (Exception ele) {
			sActualResult = ele.getMessage();
		}
		return element;
	}
	//date button
		public static WebElement ebtnDate() {
			try { 
                
element = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div[2]/div/div/div/div[1]/div[2]/div/div/div[2]/span/span"));
			} catch (Exception ele) {
				sActualResult = ele.getMessage();
			}
			return element;
		}
		public static WebElement ebtnDone() {
			try {
				element = driver.findElement(By.xpath(".//span[text()='Done']"));
			} catch (Exception ele) {
				sActualResult = ele.getMessage();
			}
			return element;
		}
		public static WebElement ebtnReset() {
			try {
				Thread.sleep(5000);
				element = driver.findElement(By.xpath(".//span[text()='Reset']"));
			} catch (Exception ele) {
				sActualResult = ele.getMessage();
			}
			return element;
		}
		public static WebElement eFilterFromDate() {
			try {

				element = driver.findElement(By.xpath("//*[@id=\"date-popover\"]/div[3]/div[1]/div[1]/div/div/div/input"));
			
			} catch (Exception ele) {
				sActualResult = ele.getMessage();
			}
			return element;
		}
		public static WebElement eFilterToDate() {
			try {
				element = driver.findElement(By.xpath("//*[@id=\"date-popover\"]/div[3]/div[1]/div[3]/div/div/div/input"));
				} catch (Exception ele) {
				sActualResult = ele.getMessage();
			}
			return element;
		}
		public static WebElement eDealsBooked() {
			try {
				element = driver.findElement(By.xpath("//*[@id=\"view-toggle\"]/button[3]/span[1]"));
				// element = driver.findElement(By.xpath("//*[@id=\"react-select-2-input\"]"));
			} catch (Exception ele) {
				sActualResult = ele.getMessage();
				element = null;
				
			}
			return element;
		}
		public static WebElement eToggleButton() {
			try {
				element = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div[2]/div/div/div/div/div[1]/div/div/div"));
			} catch (Exception ele) {
				sActualResult = ele.getMessage();
			}
			return element;
		}
		
		public static WebElement eChatConnectionShare() {
			try {
				element = driver.findElement(By.xpath("//button//span[contains(text(),'Share')]"));
				element.click();
			} catch (Exception ele) {
				sActualResult = ele.getMessage();
			}
			return element;
		}
		
		public static WebElement eGetStartedButton() {
			try {
				element = driver.findElement(By.xpath("//div[@class='page']//button"));
				element.click();
			} catch (Exception ele) {
				sActualResult = ele.getMessage();
			}
			return element;
		}
		
		public static WebElement eRadioBtnSendEmail() {
			try {
				element = driver.findElement(By.xpath(".//input[@value='email']"));
			} catch (Exception ele) {
				sActualResult = ele.getMessage();
			}
			return element;
		}
		public static WebElement eExportEmailAddress() {
			try {
				element = driver.findElement(By.xpath("//*[@id=\"email\"]"));
			} catch (Exception ele) {
				sActualResult = ele.getMessage();
			}
			return element;
		}

		public static WebElement eCheckBoxDateRange() {
			try {
				element = driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/div/div[2]/span/div"));
			} catch (Exception ele) {
				sActualResult = ele.getMessage();
			}
			return element;
		}
		public static WebElement eBtnExportDeal() {
			try {
				element = driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/div/div[2]/div[4]/button[2]"));
			} catch (Exception ele) {
				sActualResult = ele.getMessage();
			}
			return element;
		}
		public static WebElement eBtnExport() {
			try {
				element = driver.findElement(By.xpath("//button[@class='MuiButtonBase-root MuiIconButton-root css-14zyz1b MuiIconButton-sizeSmall']"));
			} catch (Exception ele) {
				sActualResult = ele.getMessage();
			}
			return element;
		}
		public static WebElement eBtnExportAs() {
			try {
				element = driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/div/div[2]/div[2]/div/div/div"));
			} catch (Exception ele) {
				sActualResult = ele.getMessage();
			}
			return element;
		}
		public static WebElement eRadioBtnDownload() {
			try {
				element = driver.findElement(By.xpath(".//input[@value='download']"));
			} catch (Exception ele) {
				sActualResult = ele.getMessage();
			}
			return element;
		}
		public static WebElement eBtnLocation() {
			try {

				element = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div[2]/div/div/div/div[1]/div[2]/div/div/div[1]"));
			} catch (Exception ele) {
				sActualResult = ele.getMessage();
			}
			return element;
		}
		public static WebElement eBtnOutbound() {
			try {
				Thread.sleep(2000);
				element = driver.findElement(By.xpath("(.//*[@class='css-7zw0v7']/*[name()='svg'])[1]"));
			} catch (Exception ele) {
				sActualResult = ele.getMessage();
			}
			return element;
		}

		public static WebElement eFilterOrigin() {
			try {
				element = driver.findElement(By.xpath("//*[@id=\"location-popover\"]/div[3]/div/div[1]/div[2]/div[1]/div/div[1]/div/div/div"));
			} catch (Exception ele) {
				sActualResult = ele.getMessage();
			}
			return element;
		}
		public static WebElement eBtnReturn() {
			try {
				Thread.sleep(2000);
				element = driver.findElement(By.xpath("(.//*[@class='css-7zw0v7']/*[name()='svg'])[2]"));
			} catch (Exception ele) {
				sActualResult = ele.getMessage();
			}
			return element;
		}
		public static WebElement eFilterDestination() {
			try {
				element = driver.findElement(By.xpath("//*[@id=\"location-popover\"]/div[3]/div/div[1]/div[2]/div[2]/div/div[1]/div/div/div"));
			} catch (Exception ele) {
				sActualResult = ele.getMessage();
			}
			return element;
		}
		public static WebElement eBtnRoundTrip() {
			try {
				Thread.sleep(2000);
				element = driver.findElement(By.xpath("(.//*[@class='css-7zw0v7']/*[name()='svg'])[3]"));
			} catch (Exception ele) {
				sActualResult = ele.getMessage();
			}
			return element;
		}
		public static WebElement eBtnLocReset() {
			try {
				Thread.sleep(2000);
				element = driver.findElement(By.xpath(".//span[text()='Reset']"));
			} catch (Exception ele) {
				sActualResult = ele.getMessage();
			}
			return element;
		}
		public static WebElement eBtnDone() {
			try {
				element = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div[2]/div/div/div/div[2]/div/div/div[1]/div[2]/div/ul/div[2]/div/button[2]"));
			} catch (Exception ele) {
				sActualResult = ele.getMessage();
			}
			return element;
		}
		
		
		
}

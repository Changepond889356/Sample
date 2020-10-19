package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import Utils.GenericSkins;

public class CommoditiesPage extends GenericSkins {

	static WebElement element;

	public static WebElement eMenuCommodities() {
		try {
			element = driver.findElement(By.xpath(".//span[text()='Commodities']"));
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

	public static WebElement ebtnAddCommodity() {
		try {
			element = driver.findElement(By.xpath(".//span[text()='Add Commodity']"));
		} catch (Exception ele) {

		}
		return element;
	}

	public static WebElement ecommodityName() {
		try {
			element = driver.findElement(By.xpath("//*[@id=\"commodity\"]"));
		} catch (Exception ele) {

		}
		return element;
	}

	public static WebElement eupperLimit() {
		try {
			element = driver.findElement(By.xpath("//*[@id=\"upperLimit\"]"));
		} catch (Exception ele) {

		}
		return element;
	}

	

	public static WebElement elowerLimit() {
		try {
			Thread.sleep(3000);
			element = driver.findElement(By.xpath("//*[@id='lowerLimit']"));
			// element = driver.findElement(By.xpat"));
		} catch (Exception ele) {
			sActualResult = ele.getMessage();

		}
		return element;
	}

	public static WebElement epoundsPerBushel() {
		try {
			Thread.sleep(3000);
			element = driver.findElement(By.xpath("//*[@id='poundsPerBushel']"));
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

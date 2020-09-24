package PageObjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Utils.GenericSkins;
public class LoginPage extends GenericSkins {
	
	static WebElement element;
	public static WebElement eUserName()
	{
		try
		{
			element = driver.findElement(By.xpath("//*[@id=\"user\"]"));
		}
		catch(Exception ele)
		{
			sActualResult=ele.getMessage();
		}
		return element;
	}
	
	public static WebElement sSubmit()
	{
		try
		{
			element = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div[1]/div/form/div[2]/button"));
		}
		catch(Exception ele)
		{
			sActualResult=ele.getMessage();
		}
		return element;
	}
	public static WebElement ePassword()
	{
		try
		{
			element = driver.findElement(By.xpath("//*[@id=\"verification\"]"));
		}
		catch(Exception ele)
		{
			
		}
		return element;
	}
	public static WebElement eLoginButton()
	{
		try
		{
			element = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div[1]/div/form/div[3]/button/span[1]"));
		}
		catch(Exception ele)
		{
			
		}
		return element;
	}
}

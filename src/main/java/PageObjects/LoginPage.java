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
	public static WebElement eResetPwd()
	{
		try
		{
			element = driver.findElement(By.xpath(".//span[text()='Reset Password']"));
		}
		catch(Exception ele)
		{
			sActualResult=ele.getMessage();
		}
		return element;
	}
	public static WebElement eNewPwd()
	{
		try
		{
			element = driver.findElement(By.xpath(".//input[@id='password']"));
		}
		catch(Exception ele)
		{
			sActualResult=ele.getMessage();
		}
		return element;
	}
	public static WebElement eConfirmNewPwd()
	{
		try
		{
			element = driver.findElement(By.xpath(".//input[@id='confirmPassword']"));
		}
		catch(Exception ele)
		{
			sActualResult=ele.getMessage();
		}
		return element;
	}
	public static WebElement eForgotPassword()
	{
		try
		{
			element = driver.findElement(By.xpath(".//span[text()='forgot password']"));
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

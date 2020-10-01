package PageObjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Utils.GenericSkins;
public class RegisterPage extends GenericSkins {
	
	static WebElement element;
	public static WebElement eFullName()
	{
		try
		{
			element = driver.findElement(By.xpath(".//input[@name='name']"));
		}
		catch(Exception ele)
		{
			sActualResult=ele.getMessage();
		}
		return element;
	}
	
	public static WebElement eEmail()
	{
		try
		{
			element = driver.findElement(By.xpath(".//input[@name='email']"));
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
			element = driver.findElement(By.xpath("//*[@name=\"password\"]"));
		}
		catch(Exception ele)
		{
			
		}
		return element;
	}
	public static WebElement eConfirmPassword()
	{
		try
		{
			element = driver.findElement(By.xpath("//*[@name=\"confirmPassword\"]"));
		}
		catch(Exception ele)
		{
			
		}
		return element;
	}
	public static WebElement eCreateAccount()
	{
		try
		{
			element = driver.findElement(By.xpath(".//div[text()='Create Account']"));
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

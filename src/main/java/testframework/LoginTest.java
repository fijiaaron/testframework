package testframework;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest
{
	WebDriver driver;
	
	@BeforeMethod
	public void setup()
	{
		System.out.println("setting path to driver");
		new DriverFactory().setChromeDriver();
		
		System.out.println("Opening browser...");
		driver = new ChromeDriver();
		
	    driver.get("https://the-internet.herokuapp.com/login");
	}
	
	@AfterMethod
	public void teardown()
	{
		driver.quit();
	}

	@Test
	public void successfulLogin()
	{	
	    WebElement username = driver.findElement(By.id("username"));
	    username.sendKeys("tomsmith");
	    
	    WebElement password = driver.findElement(By.id("password"));
	    password.sendKeys("SuperSecretPassword!");
	    
	    WebElement loginButton = driver.findElement(By.cssSelector("#login button"));
	    loginButton.click();
	    
	    String message1 = driver.findElement(By.id("flash")).getText();
	    System.out.println(message1);
	    
	    String title = driver.getTitle();
	    assertEquals(title, "The Internet");
	    
	    String url = driver.getCurrentUrl();
	    System.out.println(url);
	    
	    assertEquals(message1, "You logged into a secure area!\n"+ "×");
	    
	    driver.findElement(By.linkText("Logout")).click();
	    String message2 = driver.findElement(By.id("flash")).getText();
	    System.out.println(message2);
	    
	    url = driver.getCurrentUrl();
	    System.out.println(url);
	    
	    assertEquals(message2, "You logged out of the secure area!\n"+ "×");
	}
	
	
	@Test
	public void unsuccessfulLogin()
	{
	    WebElement username = driver.findElement(By.id("username"));
	    username.sendKeys("tomsmith");
	    
	    WebElement password = driver.findElement(By.id("password"));
	    password.sendKeys("SuperWrongPassword!");
	    
	    WebElement loginButton = driver.findElement(By.cssSelector("#login button"));
	    loginButton.click();
	    
	    String message1 = driver.findElement(By.id("flash")).getText();
	    System.out.println(message1);
	    
	    assertEquals(message1, "Your password is invalid!\n"+ "×");	
	}
}

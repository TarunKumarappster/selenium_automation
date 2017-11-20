package TestNG_tutorials;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
//import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

//@Listeners(TestNG_tutorials.TestNGListener.class)

public class DataDrivenFramework {
	
	WebDriver driver;
	
	@BeforeClass
	@Parameters("browser")
	public void startbrowser(String browsername)
	{
		if(browsername.equalsIgnoreCase("firefox"))
		{
		System.setProperty("webdriver.firefox.marionette", "/usr/local/bin/geckodriver");
		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		capabilities.setCapability("marionette", true);
		driver = new FirefoxDriver(capabilities);
		System.out.println("BeforeClass");
		}
		else if(browsername.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
			driver = new ChromeDriver();

		}
	}
	
	@Test
	public void StartApp()
	{
		
		driver.get("https://www.facebook.com/");
		//String currenturl = driver.getCurrentUrl();
		//Assert.assertTrue(currenturl.contains("facebook"));
	}
	@Test(dataProvider="logincredentials")
	public void LoginApp(String username, String password)
	{
		WebElement element = driver.findElement(By.xpath("//*[@id=\"email\"]"));
		element.sendKeys(username);
		driver.findElement(By.xpath("//*[@id=\"pass\"]")).sendKeys(password);
		driver.findElement(By.xpath("//*[@id=\"loginbutton\"]")).click();	
//		Boolean status = driver.findElement(By.xpath("//*[@id=\"userNavigationLabel\"]")).isDisplayed();
//		Assert.assertTrue(status);
	}
//	@Test(dependsOnMethods="LoginApp")
//	public void Logout() throws InterruptedException
//	{
//		driver.findElement(By.xpath("//*[@id=\"userNavigationLabel\"]")).click();
//		Thread.sleep(3000);
//		driver.findElement(By.xpath("//*[contains(@id,'js_')]/div/div/ul/li[12]")).click();
//		
//	}
	@DataProvider(name="logincredentials")
	public Object[][] passData()
	{
		Object [][] data = new Object[2][2];
		data[0][0]= "taruntyranusbu@gmail.com";
		data[0][1]= "sirrakeshsir";
		data[1][0]= "testing@gmail.com";
		data[1][1]= "testing";
		return data;
	}
	
	@AfterClass
	public void Closebrowser()
	{
		driver.close();
		System.out.println("AfterClass");
	}
}

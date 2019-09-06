package Sanity;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class HMC {

	public String baseUrl = "https://skyweb15.skytrac.ca";
	String driverPath = "C:\\chromedriver\\chromedriver.exe";
	public WebDriver driver ;


	@BeforeTest
	public void verifyHomepageTitle() {
		System.out.println("launching chrome browser"); 
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();
		driver.get(baseUrl);
		driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
		String expectedTitle = "Login";
		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle, expectedTitle);
	}

	@Test(priority = 0)
	public void login() throws InterruptedException  {
		driver.findElement(By.name("ctl00$MainContentPlaceHolder$skyWebLogin$UserName")).sendKeys("RCAS_PM");
		driver.findElement(By.name("ctl00$MainContentPlaceHolder$skyWebLogin$Password")).sendKeys("Skytrac3#");
		driver.findElement(By.name("ctl00$MainContentPlaceHolder$skyWebLogin$LoginButton")).click();
		Thread.sleep(2000);
		driver.findElement(By.partialLinkText("HARDWARE MANAGEMENT")).click();
		driver.findElement(By.partialLinkText("HARDWARE CONFIGURATION")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("ctl00_MainContentPlaceHolder_HMCMain_Wizard1_HMCSelectUnitControl_unitRadTextBox_text")).sendKeys("1643");
		driver.findElement(By.id("ctl00_MainContentPlaceHolder_HMCMain_Wizard1_HMCSelectUnitControl_findButton")).click();
		Thread.sleep(2000);
		WebElement radio1 = driver.findElement(By.id("ctl00_MainContentPlaceHolder_HMCMain_Wizard1_HMCSelectUnitControl_unitRadGrid_ctl01_ctl04_selectRadioButton"));

		radio1.click();
		Thread.sleep(2000);
		driver.findElement(By.id("ctl00_MainContentPlaceHolder_HMCMain_Wizard1_SideBarContainer_queryFWButton")).click();
		Thread.sleep(10000);
		
		String firmware = driver.findElement(By.xpath("//*[@id=\"ctl00_MainContentPlaceHolder_HMCMain_Wizard1_SideBarContainer_FWLabel\"]")).getAttribute("innerText");
		System.out.println("value="+firmware);
		
		String expectedfirmware = "06.04";

		if (expectedfirmware.matches(firmware)){
			System.out.println("Firmware updated sucessfully");
		}
		else{
			Assert.fail("Firmware not updated sucessfully");
		
			Thread.sleep(3000);
		}
	}
}

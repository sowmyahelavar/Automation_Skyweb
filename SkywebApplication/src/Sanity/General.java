package Sanity;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class General {

	public String baseUrl = "https://skyweb9.skytrac.ca";
	String driverPath = "E:\\chromedriver.exe"; 
	public static WebDriver driver ;
	ExtentReports extent;
	ExtentTest logger;
	ExtentHtmlReporter htmlReporter;

	@BeforeClass
	public void setup(){
		htmlReporter = new ExtentHtmlReporter("LoginTestCases.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
	}

	@BeforeTest
	public void verifyHomepageTitle() {
		extent.createTest("teszt", "fkf");
	//	System.out.println("launching chrome browser"); 
	//  logger.log(Status.INFO,"gjkg");
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();
		
		driver.get(baseUrl);
		logger.log(Status.PASS,"gjkg");
		driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
		String expectedTitle = "Logi";
		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle, expectedTitle);
		logger.log(Status.PASS,"gjkg");
	}

	//Test case 1491-Valid Login using Super user, add super user username and password
	@Test(priority = 0)
	public void valid() throws InterruptedException  {
		driver.findElement(By.name("ctl00$MainContentPlaceHolder$skyWebLogin$UserName")).sendKeys("RCAS_PM");
		driver.findElement(By.name("ctl00$MainContentPlaceHolder$skyWebLogin$Password")).sendKeys("Skytrac3#");
		driver.findElement(By.name("ctl00$MainContentPlaceHolder$skyWebLogin$LoginButton")).click();
		Thread.sleep(2000);
		String expectedTitle = "Home";
		String actualTitle = driver.getTitle();
		if (expectedTitle.matches(actualTitle)){
			System.out.println("Logged in sucessfully with Super user");
		}
		else{
			Assert.fail("Login failed");

			Thread.sleep(3000);
		}
		driver.findElement(By.partialLinkText("FLIGHT FOLLOWING")).click();

		Thread.sleep(25000);
		driver.switchTo().frame("SkyTrac_Controls_Window_InnerFrame_52");

		driver.findElement(By.id("Ok")).click();
		Thread.sleep(3000);
		driver.navigate().back();
		Thread.sleep(2000); 
		driver.findElement(By.partialLinkText("[LOG OUT]")).click();
		Thread.sleep(2000); 
	}

	//Test case 1151, Empty username and password
	@Test(priority = 1)
	public void Invalid1() throws InterruptedException  {

		for(int i=1; i<=10; i++) {
			driver.findElement(By.name("ctl00$MainContentPlaceHolder$skyWebLogin$UserName")).sendKeys("");
			driver.findElement(By.name("ctl00$MainContentPlaceHolder$skyWebLogin$Password")).sendKeys("");
			driver.findElement(By.name("ctl00$MainContentPlaceHolder$skyWebLogin$LoginButton")).click();
			driver.navigate().refresh();
		}

	}

	//Test case 1151, User locked out
	@Test(priority = 2)
	public void Invalid() throws InterruptedException  {

		driver.findElement(By.name("ctl00$MainContentPlaceHolder$skyWebLogin$UserName")).sendKeys("rcas_mithun");
		driver.findElement(By.name("ctl00$MainContentPlaceHolder$skyWebLogin$Password")).sendKeys("Skytrac4$");
		driver.findElement(By.name("ctl00$MainContentPlaceHolder$skyWebLogin$LoginButton")).click();
		for(int i=1; i<=10; i++) {
			driver.findElement(By.id("ctl00_MainContentPlaceHolder_skyWebLogin_Password")).clear();
			Thread.sleep(2000); 
			driver.findElement(By.id("ctl00_MainContentPlaceHolder_skyWebLogin_Password")).sendKeys("skytrac");
			driver.findElement(By.name("ctl00$MainContentPlaceHolder$skyWebLogin$LoginButton")).click();

		}
		driver.navigate().refresh();
		driver.findElement(By.name("ctl00$MainContentPlaceHolder$skyWebLogin$UserName")).clear();
		driver.findElement(By.name("ctl00$MainContentPlaceHolder$skyWebLogin$UserName")).sendKeys("RCAS_PM");
		driver.findElement(By.name("ctl00$MainContentPlaceHolder$skyWebLogin$Password")).sendKeys("Skytrac3#");
		driver.findElement(By.name("ctl00$MainContentPlaceHolder$skyWebLogin$LoginButton")).click();
		driver.findElement(By.partialLinkText("ACCOUNT MANAGEMENT")).click();
		driver.findElement(By.partialLinkText("PROGRAM MANAGEMENT")).click();
		Thread.sleep(3000);
		Select drp = new Select(driver.findElement(By.id("ctl00_MainContentPlaceHolder_AccountListBox1")));
		drp.selectByValue("rcas_mithun");
		Thread.sleep(5000);
		WebElement chk = driver.findElement(By.id("ctl00_MainContentPlaceHolder_isUserLockedOut"));
		chk.click();
		Thread.sleep(2000); 
		driver.findElement(By.partialLinkText("[LOG OUT]")).click();
	}
}



package Sanity;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class accountmanagement {


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
//	@Test(priority = 0)
//	public void login() throws InterruptedException  {
//		driver.findElement(By.name("ctl00$MainContentPlaceHolder$skyWebLogin$UserName")).sendKeys("RCAS_PM");
//		driver.findElement(By.name("ctl00$MainContentPlaceHolder$skyWebLogin$Password")).sendKeys("Skytrac3#");
//		driver.findElement(By.name("ctl00$MainContentPlaceHolder$skyWebLogin$LoginButton")).click();
//		driver.findElement(By.partialLinkText("ACCOUNT MANAGEMENT")).click();
//		driver.findElement(By.partialLinkText("PROGRAM MANAGEMENT")).click();
//		Thread.sleep(3000);
//	}
//
//	@Test(priority = 1)
//	public void newuser() throws InterruptedException  {
//		WebElement element = driver.findElement(By.linkText("Options"));
//		Thread.sleep(3000);
//		Actions action = new Actions(driver);
//		action.moveToElement(element).build().perform();
//		driver.findElement(By.linkText("Add Account")).click();
//		Thread.sleep(3000);
//
//		driver.findElement(By.id("ctl00_MainContentPlaceHolder_txtAccountUserName")).sendKeys("rcas_user150");
//		driver.findElement(By.id("ctl00_MainContentPlaceHolder_txtAcctPassword1")).sendKeys("Skytrac3#");
//		driver.findElement(By.id("ctl00_MainContentPlaceHolder_txtAcctRetypePassword")).sendKeys("Skytrac3#");
//		driver.findElement(By.id("ctl00_MainContentPlaceHolder_txtAcctInitials")).sendKeys("UU");
//		driver.findElement(By.id("ctl00_MainContentPlaceHolder_txtAccountName")).sendKeys("user150");
//		driver.findElement(By.id("ctl00_MainContentPlaceHolder_txtAccountEmailAddress")).sendKeys("user150@rcas.co.in");
//		Select drpgrp = new Select(driver.findElement(By.id("ctl00_MainContentPlaceHolder_grpAccountBox")));
//		drpgrp.selectByIndex(1);
//		driver.findElement(By.id("ctl00_MainContentPlaceHolder_acctSaveBtn")).click();
//
//		driver.findElement(By.id("ctl00_MainContentPlaceHolder_okButton")).click();
//		driver.findElement(By.partialLinkText("[LOG OUT]")).click();
//	}
//
//	@Test(priority = 2)
//	public void loginwithnewuser() throws InterruptedException  {
//		driver.findElement(By.name("ctl00$MainContentPlaceHolder$skyWebLogin$UserName")).sendKeys("rcas_user150");
//		driver.findElement(By.name("ctl00$MainContentPlaceHolder$skyWebLogin$Password")).sendKeys("Skytrac3#");
//		driver.findElement(By.name("ctl00$MainContentPlaceHolder$skyWebLogin$LoginButton")).click();
//		Thread.sleep(3000);
//		//		 driver.findElement(By.cssSelector(span:contains("rcas_user150")); 
//		WebElement strnew=driver.findElement(By.xpath("//*[@id=\"master-login\"]/span/span"));
//		System.out.println("value="+strnew);
//		Thread.sleep(3000);
//		System.out.println("value="+driver.findElement(By.xpath("/html/body/form/header/div[2]/div[3]/div[2]/section/span/span")).getAttribute("innerHTML"));
//		if(driver.findElement(By.xpath("/html/body/form/header/div[2]/div[3]/div[2]/section/span/span")).getText().equalsIgnoreCase("rcas_user150"))
//
//		{
//			System.out.println("Login successful.");
//		}
//
//		else
//		{
//			System.out.println("Login unsuccessful.");
//		}
//		Thread.sleep(3000);
//		driver.findElement(By.partialLinkText("[LOG OUT]")).click();
//	}
//
//	@Test(priority = 3)
//	public void changepwdr() throws InterruptedException {
//
//		driver.findElement(By.name("ctl00$MainContentPlaceHolder$skyWebLogin$UserName")).sendKeys("RCAS_PM");
//		driver.findElement(By.name("ctl00$MainContentPlaceHolder$skyWebLogin$Password")).sendKeys("Skytrac3#");
//		driver.findElement(By.name("ctl00$MainContentPlaceHolder$skyWebLogin$LoginButton")).click();
//		driver.findElement(By.partialLinkText("ACCOUNT MANAGEMENT")).click();
//		driver.findElement(By.partialLinkText("PROGRAM MANAGEMENT")).click();
//		Thread.sleep(3000);
//
//		Select drp = new Select(driver.findElement(By.id("ctl00_MainContentPlaceHolder_AccountListBox1")));
//		drp.selectByValue("rcas_user150");
//		Thread.sleep(5000);
//		WebElement element = driver.findElement(By.linkText("Options"));
//		Thread.sleep(3000);
//		Actions action = new Actions(driver);
//		action.moveToElement(element).build().perform();
//		driver.findElement(By.linkText("Change Password")).click();
//		Thread.sleep(3000);
//		driver.findElement(By.id("ctl00_MainContentPlaceHolder_txtChPw1")).sendKeys("Skytrac4$");
//		driver.findElement(By.id("ctl00_MainContentPlaceHolder_txtChPw2")).sendKeys("Skytrac4$");
//		driver.findElement(By.id("ctl00_MainContentPlaceHolder_chPwdSaveBtn1")).click();
//		Thread.sleep(3000);
//		driver.findElement(By.id("ctl00_MainContentPlaceHolder_okButton")).click();
//		driver.findElement(By.partialLinkText("[LOG OUT]")).click();
//	}
//
//	@Test(priority = 4)
//	public void loginwithcngpwd() throws InterruptedException  {
//		driver.findElement(By.name("ctl00$MainContentPlaceHolder$skyWebLogin$UserName")).sendKeys("rcas_user150");
//		driver.findElement(By.name("ctl00$MainContentPlaceHolder$skyWebLogin$Password")).sendKeys("Skytrac4$");
//		driver.findElement(By.name("ctl00$MainContentPlaceHolder$skyWebLogin$LoginButton")).click();
//
//		Thread.sleep(3000);
//		System.out.println("value="+driver.findElement(By.xpath("/html/body/form/header/div[2]/div[3]/div[2]/section/span/span")).getAttribute("innerHTML"));
//		if(driver.findElement(By.xpath("/html/body/form/header/div[2]/div[3]/div[2]/section/span/span")).getText().equalsIgnoreCase("rcas_user150"))
//
//		{
//			System.out.println("Login successful with the new password.");
//		}
//
//		else
//		{
//			System.out.println("Login unsuccessful.");
//		}
//		driver.findElement(By.partialLinkText("[LOG OUT]")).click();
//	}
//
//	@Test(priority = 5)
//	public void deleteacc() throws InterruptedException  {
//		driver.findElement(By.name("ctl00$MainContentPlaceHolder$skyWebLogin$UserName")).sendKeys("RCAS_PM");
//		driver.findElement(By.name("ctl00$MainContentPlaceHolder$skyWebLogin$Password")).sendKeys("Skytrac3#");
//		driver.findElement(By.name("ctl00$MainContentPlaceHolder$skyWebLogin$LoginButton")).click();
//		driver.findElement(By.partialLinkText("ACCOUNT MANAGEMENT")).click();
//		driver.findElement(By.partialLinkText("PROGRAM MANAGEMENT")).click();
//		Thread.sleep(3000);
//		Select drp = new Select(driver.findElement(By.id("ctl00_MainContentPlaceHolder_AccountListBox1")));
//		drp.selectByValue("rcas_user150");
//		Thread.sleep(5000);
//		WebElement element = driver.findElement(By.linkText("Options"));
//		Thread.sleep(3000);
//		Actions action = new Actions(driver);
//		action.moveToElement(element).build().perform();
//		driver.findElement(By.linkText("Delete Account")).click();
//		Thread.sleep(5000);
//		driver.findElement(By.id("ctl00_MainContentPlaceHolder_accountDeleteConfirmed")).click();
//		Thread.sleep(3000);
//		driver.findElement(By.id("ctl00_MainContentPlaceHolder_okButton")).click();
//		driver.findElement(By.partialLinkText("[LOG OUT]")).click();
//	}
//
//
//	@Test(priority = 6)
//	public void loginwithdetacc() throws InterruptedException  {
//		driver.findElement(By.name("ctl00$MainContentPlaceHolder$skyWebLogin$UserName")).sendKeys("rcas_user150");
//		driver.findElement(By.name("ctl00$MainContentPlaceHolder$skyWebLogin$Password")).sendKeys("Skytrac4$");
//		driver.findElement(By.name("ctl00$MainContentPlaceHolder$skyWebLogin$LoginButton")).click();
//
//		String actual_msg=driver.findElement(By.xpath("//*[@id=\"ctl00_MainContentPlaceHolder_skyWebLogin\"]/tbody/tr/td/table/tbody/tr[3]/td/p[1]")).getText();
//		System.out.println("value="+actual_msg);
//		// Store message in variable
//		String expect="Username not found.\nPlease try again.";
//
//		// Here Assert is a class and assertEquals is a method which will compare two values if// both matches it will run fine but in case if does not match then if will throw an 
//		//exception and fail testcases
//
//		// Verify error message
//		Assert.assertEquals(actual_msg, expect);
//		
//
//	}
//
//
//		@Test(priority = 7)
//		public void accdetails() throws InterruptedException  {
//			driver.findElement(By.name("ctl00$MainContentPlaceHolder$skyWebLogin$UserName")).clear();
//			driver.findElement(By.name("ctl00$MainContentPlaceHolder$skyWebLogin$UserName")).sendKeys("RCAS_PM");
//			driver.findElement(By.name("ctl00$MainContentPlaceHolder$skyWebLogin$Password")).sendKeys("Skytrac3#");
//			driver.findElement(By.name("ctl00$MainContentPlaceHolder$skyWebLogin$LoginButton")).click();
//			driver.findElement(By.partialLinkText("ACCOUNT MANAGEMENT")).click();
//			driver.findElement(By.partialLinkText("PROGRAM MANAGEMENT")).click();
//			Thread.sleep(3000);
//			Select drp = new Select(driver.findElement(By.id("ctl00_MainContentPlaceHolder_AccountListBox1")));
//			drp.selectByValue("rcas_mithun");
//			Thread.sleep(4000);
//			driver.findElement(By.xpath("//*[@id=\"ctl00_MainContentPlaceHolder_editButton\"]")).click();
//			Thread.sleep(3000);
//			driver.findElement(By.id("ctl00_MainContentPlaceHolder_lblRName")).clear();
//			driver.findElement(By.id("ctl00_MainContentPlaceHolder_lblRName")).sendKeys("Mithun");
//			driver.findElement(By.id("ctl00_MainContentPlaceHolder_saveButton")).click();
//			Thread.sleep(3000);
//			driver.findElement(By.xpath("//*[@id=\"ctl00_MainContentPlaceHolder_okButton\"]")).click();
//			driver.findElement(By.partialLinkText("HOME")).click();
//			driver.findElement(By.partialLinkText("ACCOUNT MANAGEMENT")).click();
//			driver.findElement(By.partialLinkText("PROGRAM MANAGEMENT")).click();
//			Thread.sleep(3000);
//			Select drp1 = new Select(driver.findElement(By.id("ctl00_MainContentPlaceHolder_AccountListBox1")));
//			drp1.selectByValue("rcas_mithun");
//			Thread.sleep(4000);
//			driver.findElement(By.xpath("//*[@id=\"ctl00_MainContentPlaceHolder_editButton\"]")).click();
//			Thread.sleep(3000);
//			String actualmsg=driver.findElement(By.xpath("//*[@id=\"ctl00_MainContentPlaceHolder_lblRName\"]")).getAttribute("value");
//			System.out.println("value="+actualmsg);
//			
//			String expect="Mithun";
//			Assert.assertEquals(actualmsg, expect);
//			driver.findElement(By.id("ctl00_MainContentPlaceHolder_resetButton")).click();
//			Thread.sleep(3000);
//		}	
		

//		@Test(priority = 0)
//		public void userpreference() throws InterruptedException  {
//			driver.findElement(By.name("ctl00$MainContentPlaceHolder$skyWebLogin$UserName")).sendKeys("RCAS_PM");
//			driver.findElement(By.name("ctl00$MainContentPlaceHolder$skyWebLogin$Password")).sendKeys("Skytrac3#");
//			driver.findElement(By.name("ctl00$MainContentPlaceHolder$skyWebLogin$LoginButton")).click();
//			driver.findElement(By.partialLinkText("ACCOUNT MANAGEMENT")).click();
//			driver.findElement(By.partialLinkText("PROGRAM MANAGEMENT")).click();
//			Thread.sleep(3000);
//			
//			Select drpup = new Select(driver.findElement(By.id("ctl00_MainContentPlaceHolder_AccountListBox1")));
//			drpup.selectByValue("rcas_mithun");
//			Thread.sleep(3000);
//			((JavascriptExecutor) driver)
//	         .executeScript("window.scrollTo(0, document.body.scrollHeight)");
//			 Thread.sleep(2000);
//			driver.findElement(By.xpath("//*[@id=\"ctl00_MainContentPlaceHolder_editPrefBtn\"]")).click();
//			
//			Select drpup1 = new Select(driver.findElement(By.id("ctl00_MainContentPlaceHolder_CoordinateDrpList")));
//			drpup1.selectByValue("DegreesMinutes");
//			String drpupco = driver.findElement(By.id("ctl00_MainContentPlaceHolder_CoordinateDrpList")).getAttribute("value");
//			System.out.println("value="+drpupco);
//			Select drpup2 = new Select(driver.findElement(By.id("ctl00_MainContentPlaceHolder_timeDrpList")));
//			drpup2.selectByValue("UTC");
//			String drpuptime = driver.findElement(By.id("ctl00_MainContentPlaceHolder_timeDrpList")).getAttribute("value");
//			System.out.println("value="+drpuptime);
//			Select drpup3 = new Select(driver.findElement(By.id("ctl00_MainContentPlaceHolder_speedDrpList")));
//			drpup3.selectByValue("KilometersPerHour");
//			String drpupspeed = driver.findElement(By.id("ctl00_MainContentPlaceHolder_speedDrpList")).getAttribute("value");
//			System.out.println("value="+drpupspeed);
//			Select drpup4 = new Select(driver.findElement(By.id("ctl00_MainContentPlaceHolder_distanceDrpList")));
//			drpup4.selectByValue("Kilometers");
//			String drpupdist = driver.findElement(By.id("ctl00_MainContentPlaceHolder_distanceDrpList")).getAttribute("value");
//			System.out.println("value="+drpupdist);
//			Select drpup5 = new Select(driver.findElement(By.id("ctl00_MainContentPlaceHolder_altitudeDrpList")));
//			drpup5.selectByValue("Meters");
//			String drpupalt = driver.findElement(By.id("ctl00_MainContentPlaceHolder_altitudeDrpList")).getAttribute("value");
//			System.out.println("value="+drpupalt);
//			
//			driver.findElement(By.id("ctl00_MainContentPlaceHolder_savePrefBtn")).click();
//			 Thread.sleep(2000);
//			driver.findElement(By.xpath("//*[@id=\"ctl00_MainContentPlaceHolder_okButton\"]")).click();
//			
//			driver.findElement(By.partialLinkText("[LOG OUT]")).click();
//			
//			driver.findElement(By.name("ctl00$MainContentPlaceHolder$skyWebLogin$UserName")).sendKeys("rcas_mithun");
//			driver.findElement(By.name("ctl00$MainContentPlaceHolder$skyWebLogin$Password")).sendKeys("Skytrac3#");
//			driver.findElement(By.name("ctl00$MainContentPlaceHolder$skyWebLogin$LoginButton")).click();
//			
//			driver.findElement(By.partialLinkText("FLIGHT FOLLOWING")).click();
//			
//			driver.switchTo().frame("SkyTrac_Controls_Window_InnerFrame_52"); 
//			driver.findElement(By.id("Ok")).click();
//			driver.switchTo().defaultContent();
//			
//			WebElement elementup = driver.findElement(By.xpath("//*[@id=\"RadMenubar_m4\"]/span"));
//			Thread.sleep(3000);
//			Actions action = new Actions(driver);
//			action.moveToElement(elementup).build().perform();
//			driver.findElement(By.linkText("GPS Display Options...")).click();	
//			
//			driver.switchTo().frame("SkyTrac_Controls_Window_InnerFrame_398");
//			
//			Select select = new Select(driver.findElement(By.id("CoordinatesSelect")));
//			WebElement option = select.getFirstSelectedOption();
//			String defaultItem = option.getText();
//			System.out.println(defaultItem);
//			
//			Select select1 = new Select(driver.findElement(By.id("TimeSelect")));
//			WebElement option1 = select1.getFirstSelectedOption();
//			String defaultItem1 = option1.getText();
//			System.out.println(defaultItem1);
//			
//			Select select2 = new Select(driver.findElement(By.id("SpeedSelect")));
//			WebElement option2 = select2.getFirstSelectedOption();
//			String defaultItem2 = option2.getText();
//			System.out.println(defaultItem2);
//			
//			Select select3 = new Select(driver.findElement(By.id("DistanceSelect")));
//			WebElement option3 = select3.getFirstSelectedOption();
//			String defaultItem3 = option3.getText();
//			System.out.println(defaultItem3);
//			
//			Select select4 = new Select(driver.findElement(By.id("AltitudeSelect")));
//			WebElement option4 = select4.getFirstSelectedOption();
//			String defaultItem4 = option4.getText();
//			System.out.println(defaultItem4);
//			
//			driver.findElement(By.id("Button5")).click();
//			driver.findElement(By.id("Button1")).click();
//			driver.switchTo().defaultContent();
//			Thread.sleep(2000);
//			driver.navigate().back();
//			Thread.sleep(2000);
//			driver.findElement(By.partialLinkText("[LOG OUT]")).click();
//			Thread.sleep(3000);
//		}


		@Test(priority = 1)
		public void resources() throws InterruptedException  {
		driver.findElement(By.name("ctl00$MainContentPlaceHolder$skyWebLogin$UserName")).sendKeys("RCAS_PM");
		driver.findElement(By.name("ctl00$MainContentPlaceHolder$skyWebLogin$Password")).sendKeys("Skytrac3#");
		driver.findElement(By.name("ctl00$MainContentPlaceHolder$skyWebLogin$LoginButton")).click();
			driver.findElement(By.partialLinkText("ACCOUNT MANAGEMENT")).click();
			driver.findElement(By.partialLinkText("PROGRAM MANAGEMENT")).click();
			Thread.sleep(3000);
			Select drpre = new Select(driver.findElement(By.id("ctl00_MainContentPlaceHolder_AccountListBox1")));
			drpre.selectByValue("rcas_mithun");
			Thread.sleep(3000);
			WebElement element = driver.findElement(By.xpath("//*[@id=\"ctl00_MainContentPlaceHolder_groupMenu_m0\"]/span"));
			Thread.sleep(3000);
			Actions action = new Actions(driver);
			action.moveToElement(element).build().perform();
			driver.findElement(By.linkText("Create Group")).click();
			Thread.sleep(3000);
			driver.findElement(By.id("ctl00_MainContentPlaceHolder_txtGroupName1")).sendKeys("RCAS");
			if ( !driver.findElement(By.id("ctl00_MainContentPlaceHolder_grpResourceBox1_1")).isSelected() )
			{
			     driver.findElement(By.id("ctl00_MainContentPlaceHolder_grpResourceBox1_1")).click();
			}
			
			driver.findElement(By.id("ctl00_MainContentPlaceHolder_createGroupSaveButton")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id=\"ctl00_MainContentPlaceHolder_okButton\"]")).click();
		}
		
		}


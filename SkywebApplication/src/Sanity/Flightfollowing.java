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
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Flightfollowing {
	private static final String GeneralFrame = null;
	public String baseUrl = "https://skyweb9.skytrac.ca";
	String driverPath = "C:\\chromedriver\\chromedriver.exe";
	public WebDriver driver ;

	@BeforeTest
	public void verifyHomepageTitle() {
		System.out.println("launching chrome browser"); 
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();
		driver.get(baseUrl);
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
		String expectedTitle = "Login";
		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle, expectedTitle);
	}
	
	///Test case 1202
	@Test(priority = 0)
	@Parameters({"UserNameFF","PasswordFF"})
	public void testParameterWithXML(String UserNameFF,String PasswordFF) throws InterruptedException  {
		driver.findElement(By.name("ctl00$MainContentPlaceHolder$skyWebLogin$UserName")).sendKeys(UserNameFF);
		driver.findElement(By.name("ctl00$MainContentPlaceHolder$skyWebLogin$Password")).sendKeys(PasswordFF);
		driver.findElement(By.name("ctl00$MainContentPlaceHolder$skyWebLogin$LoginButton")).click();

		Thread.sleep(2000);
		driver.findElement(By.partialLinkText("FLIGHT FOLLOWING")).click();
		///Thread.sleep(2000);

		///@SuppressWarnings("unused")
		///String WinHandleBefore = driver.getWindowHandle();
		///for(String winHandle : driver.getWindowHandles()) {
		///driver.switchTo().window(winHandle);
		Thread.sleep(8000);
		driver.switchTo().frame("SkyTrac_Controls_Window_InnerFrame_52"); 
		Select s1 = new Select(driver.findElement(By.id("LoadSelectorSelect")));
		s1.selectByIndex(0);
		//driver.findElement(By.id("SingleRadio")).click();
		Thread.sleep(2000);
		WebElement radio1 = driver.findElement(By.id("AllRadio"));
		radio1.click();
		Thread.sleep(2000);
	Select s2 = new Select(driver.findElement(By.id("SingleSelect")));
	s2.selectByIndex(1);

		Thread.sleep(2000);
		Select s3 = new Select(driver.findElement(By.id("CurrentHistoricalSelect")));
		s3.selectByIndex(0);
		
		WebElement radioc = driver.findElement(By.id("Last4DaysCheckbox"));
		radioc.click();

		Thread.sleep(2000);
		driver.findElement(By.id("RadDatePicker1_CalendarPopupButton")).click();

		int count = driver.findElements(By.className("radCalDefault_Office2007")).size();
		System.out.println(count);



		driver.findElement(By.id("Ok")).click();
		driver.switchTo().defaultContent();
		Thread.sleep(5000);
	}
	
	//Test case 1494
//	@Test(priority = 1)
//	public void UnitVisibility() throws InterruptedException {
//	        
//		WebElement element = driver.findElement(By.xpath("/html/body/form/div[4]/div[6]/ul/li[3]/a/span"));
//		Thread.sleep(3000);                     
//		Actions action = new Actions(driver);
//		action.moveToElement(element).build().perform();
//		WebElement element1 = driver.findElement(By.xpath("/html/body/form/div[4]/div[6]/ul/li[3]/div/ul/li[1]/a/span"));
//		action.moveToElement(element1).build().perform();  
//		driver.findElement(By.linkText("Select...")).click();
//		Thread.sleep(3000);
//		
//		driver.switchTo().frame("SkyTrac_Controls_Window_InnerFrame_398");
//		driver.findElement(By.id("Button5")).click();
//		driver.findElement(By.id("Button1")).click();
//		driver.switchTo().defaultContent();
//		Thread.sleep(3000);
//	}
	
	//Test case 1495
//	@Test(priority = 2)
//	public void Unitdisplay() throws InterruptedException {
//		WebElement element2 = driver.findElement(By.xpath("//*[@id=\"RadMenubar_m4\"]/span"));
//		Thread.sleep(3000);
//		Actions action = new Actions(driver);
//		action.moveToElement(element2).build().perform();
//		driver.findElement(By.linkText("Aircraft...")).click();	
//		driver.switchTo().frame("SkyTrac_Controls_Window_InnerFrame_399");
//		driver.findElement(By.xpath("//*[@id=\"RadTabStrip1_ctl01\"]/span/span")).click(); 
//		Select sc = new Select(driver.findElement(By.id("Caption-Size-Select")));
//		sc.selectByIndex(2);
//		driver.findElement(By.id("Button5")).click();
//		driver.findElement(By.id("Button1")).click();
//		driver.switchTo().defaultContent();
		
//	/	String strimg=driver.findElement(By.xpath("//*[@id=\"SkyTrac_Controls_51\"]/div/div/div/div[1]/div[3]/div/div[3]/div[43]/img")).getText();
///		System.out.println("value="+strimg);
//	}
	
	
	//Test case 1496
//	@Test(priority = 3)
//	@Parameters({"Name","Ident","Region","LatitudeText1","LongitudeText1"})
//	public void Georeference(String Name,String Ident,String Region,String LatitudeText1,String LongitudeText1) throws InterruptedException {
//	
//		WebElement element3 = driver.findElement(By.xpath("//*[@id=\"RadMenubar_m3\"]/span"));
//		Thread.sleep(3000);
//		Actions action = new Actions(driver);
//		action.moveToElement(element3).build().perform();
//		driver.findElement(By.linkText("Georeferences...")).click();	
//		driver.switchTo().frame("SkyTrac_Controls_Window_InnerFrame_400");
//		
//		driver.findElement(By.id("AddButton")).click();
//		Thread.sleep(3000);
//		driver.switchTo().defaultContent();
//		driver.switchTo().frame("SkyTrac_Controls_Window_InnerFrame_401");  
//
//		driver.findElement(By.id("NameText")).sendKeys(Name);
//		driver.findElement(By.id("IdentText")).sendKeys(Ident);
//		driver.findElement(By.id("RegionText")).sendKeys(Region);
//		driver.findElement(By.id("LatitudeText")).sendKeys(LatitudeText1);
//		driver.findElement(By.id("LongitudeText")).sendKeys(LongitudeText1);
//		Thread.sleep(2000);
//		driver.findElement(By.id("OkButton")).click();
//		Thread.sleep(8000);
//		driver.switchTo().defaultContent();
//		driver.switchTo().frame("SkyTrac_Controls_Window_InnerFrame_400");
//		
//		Actions actions = new Actions(driver);
//		WebElement elementLocator = driver.findElement(By.id("GeoReferencesControl1_RadGrid1_ctl00__0"));
//		actions.doubleClick(elementLocator).perform();
//		
//		driver.switchTo().defaultContent();
//		driver.switchTo().frame("SkyTrac_Controls_Window_InnerFrame_402"); 
//		
//		driver.findElement(By.id("NameText")).clear();
//				driver.findElement(By.id("NameText")).sendKeys("Geo1");
//				driver.findElement(By.id("RegionText")).clear();	
//				driver.findElement(By.id("RegionText")).sendKeys("Vegas");
//				driver.findElement(By.id("LatitudeText")).clear();
//				driver.findElement(By.id("LatitudeText")).sendKeys("32.9893079");
//				driver.findElement(By.id("LongitudeText")).clear();
//				driver.findElement(By.id("LongitudeText")).sendKeys("-110.5325622");
//				driver.findElement(By.id("OkButton")).click();
//				driver.switchTo().defaultContent();
//				driver.switchTo().frame("SkyTrac_Controls_Window_InnerFrame_400"); 
//				driver.findElement(By.id("OkButton")).click();
//				driver.switchTo().defaultContent();
//				
//				
//				WebElement element4 = driver.findElement(By.xpath("//*[@id=\"RadMenubar_m3\"]/span")); 
//				Thread.sleep(3000);
//				Actions action1 = new Actions(driver);
//				action.moveToElement(element4).build().perform();
//				driver.findElement(By.linkText("Destination...")).click();	
//				driver.switchTo().frame("SkyTrac_Controls_Window_InnerFrame_403");
//				
//				Select sunit = new Select(driver.findElement(By.id("CallSignSelect")));
//				sunit.selectByValue("<STS_1643>");
//				
//				driver.findElement(By.id("SelectButton")).click();
//				driver.switchTo().defaultContent();
//				driver.switchTo().frame("SkyTrac_Controls_Window_InnerFrame_404");
//				
//			    int count = 0;
//			    String[] exp = {"Geo1 (Vegas)"};
//			    WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"SkyTrac_Controls_0\"]/select"));  
//			    Select select = new Select(dropdown);
//
//			    List<WebElement> options = select.getOptions();
//			    for (WebElement we : options) {
//			        for (int i = 0; i < exp.length; i++) {
//			        	System.out.println(we.getText());
//			            if (we.getText().equals(exp[i])) {
//			                count++;
//			            }
//			        }
//			    }
//			    if (count == exp.length) {
//			        System.out.println("Active georeference is listed");
//			    } else {
//			        System.out.println("Active georeference is not listed");
//			    }
//			    driver.findElement(By.id("CancelButton")).click();
//			    driver.switchTo().defaultContent();
//			    driver.switchTo().frame("SkyTrac_Controls_Window_InnerFrame_403");
//			    driver.findElement(By.id("ClearButton")).click();
//			    driver.findElement(By.id("Donebutton")).click();
//			    driver.switchTo().defaultContent();
//			}
//	
	
	//Test case 1497
//	@Test(priority = 4)
//	@Parameters({"NameText1","DistanceText1","LatitudeTextgeo","LongitudeTextgeo"})
//	public void Geofence(String NameText1,String DistanceText1,String LatitudeTextgeo,String LongitudeTextgeo) throws InterruptedException  {
//	
//		WebElement element3 = driver.findElement(By.xpath("//*[@id=\"RadMenubar_m3\"]/span"));
//		Thread.sleep(3000);
//		Actions action = new Actions(driver);
//		action.moveToElement(element3).build().perform();
//		driver.findElement(By.linkText("Geofences...")).click();	
//		driver.switchTo().frame("SkyTrac_Controls_Window_InnerFrame_405");
//		
//		driver.findElement(By.id("AddButton")).click();
//		Thread.sleep(3000);
//		driver.switchTo().defaultContent();
//		driver.switchTo().frame("SkyTrac_Controls_Window_InnerFrame_406");
//		
//		driver.findElement(By.id("NameText")).sendKeys(NameText1);
//		driver.findElement(By.id("DistanceText")).sendKeys(DistanceText1);
//		driver.findElement(By.id("LatitudeText")).sendKeys(LatitudeTextgeo);
//		driver.findElement(By.id("LongitudeText")).sendKeys(LongitudeTextgeo);
//		Thread.sleep(2000);
//		driver.findElement(By.id("OkButton")).click();
//		Thread.sleep(5000);
//		driver.switchTo().defaultContent();
//		driver.switchTo().frame("SkyTrac_Controls_Window_InnerFrame_405");
//		driver.findElement(By.id("OkButton")).click();
//		driver.switchTo().defaultContent();
//		driver.navigate().back();
//		driver.findElement(By.partialLinkText("[LOG OUT]")).click();
//	}
	
	@Test(priority = 5)
	public void terrainandobstacle() throws InterruptedException  {
	driver.findElement(By.name("ctl00$MainContentPlaceHolder$skyWebLogin$UserName")).sendKeys("RCAS_PM");
	driver.findElement(By.name("ctl00$MainContentPlaceHolder$skyWebLogin$Password")).sendKeys("Skytrac3#");
	driver.findElement(By.name("ctl00$MainContentPlaceHolder$skyWebLogin$LoginButton")).click();
	driver.findElement(By.partialLinkText("ACCOUNT MANAGEMENT")).click();
	driver.findElement(By.partialLinkText("PROGRAM MANAGEMENT")).click();
	Thread.sleep(3000);	
	WebElement chk = driver.findElement(By.id("ctl00_MainContentPlaceHolder_AccountRolesControl1_FlightFollowingCheckbox"));

	if(chk.isSelected()) {
		System.out.println("Flight following is disabled");	
	WebElement chk1 = driver.findElement(By.id("ctl00_MainContentPlaceHolder_AccountRolesControl1_FlightFollowingTODCheckBox"));
	chk1.isSelected();
	}
	else {
		System.out.println("Terrain and obstacle data is disabled");	
	}	
	Select drpdown = new Select(driver.findElement(By.id("ctl00_MainContentPlaceHolder_AccountListBox1")));
	drpdown.selectByValue("rcas_mithun");
	Thread.sleep(3000);
	((JavascriptExecutor) driver)
    .executeScript("window.scrollTo(0, document.body.scrollHeight)");
	 Thread.sleep(2000);
	 driver.findElement(By.xpath("//*[@id=\"ctl00_MainContentPlaceHolder_AccountRolesControl1_EditRolesBtn\"]")).click();
	 Thread.sleep(2000);
	 WebElement chk3 = driver.findElement(By.id("ctl00_MainContentPlaceHolder_AccountRolesControl1_FlightFollowingCheckbox"));
	 if(chk3.isSelected()) {
		 driver.findElement(By.xpath("//*[@id=\"ctl00_MainContentPlaceHolder_AccountRolesControl1_FlightFollowingTODCheckBox\"]")).click();
		 ((JavascriptExecutor) driver)
		    .executeScript("window.scrollTo(0, document.body.scrollHeight)");
		 driver.findElement(By.xpath("//*[@id=\"ctl00_MainContentPlaceHolder_AccountRolesControl1_SaveRolesBtn\"]")).click();
		 Thread.sleep(2000);
		 driver.findElement(By.xpath("//*[@id=\"ctl00_MainContentPlaceHolder_AccountRolesControl1_ConfirmPaidFeatureWarningConfirmed\"]")).click();
		 Thread.sleep(2000);
		 driver.findElement(By.xpath("//*[@id=\"ctl00_MainContentPlaceHolder_AccountRolesControl1_MessageBoxOkButton\"]")).click();
	 }
	 else {
		 chk3.click();
		 Thread.sleep(2000);
		 driver.findElement(By.xpath("//*[@id=\"ctl00_MainContentPlaceHolder_AccountRolesControl1_ConfirmPaidFeatureWarningConfirmed\"]")).click();
	 }
	 driver.findElement(By.partialLinkText("[LOG OUT]")).click();
	 Thread.sleep(2000);
	}
	
	@Test(priority = 6)
	public void terrainandobstacleff() throws InterruptedException  {
	driver.findElement(By.name("ctl00$MainContentPlaceHolder$skyWebLogin$UserName")).sendKeys("rcas_mithun");
	driver.findElement(By.name("ctl00$MainContentPlaceHolder$skyWebLogin$Password")).sendKeys("Skytrac3#");
	driver.findElement(By.name("ctl00$MainContentPlaceHolder$skyWebLogin$LoginButton")).click();
	
	driver.findElement(By.partialLinkText("FLIGHT FOLLOWING")).click();
	
	driver.switchTo().frame("SkyTrac_Controls_Window_InnerFrame_52"); 
	driver.findElement(By.id("Ok")).click();
	driver.switchTo().defaultContent();
	Thread.sleep(2000);
	driver.findElement(By.xpath("//*[@id=\"SkyTrac_Controls_13\"]/div[2]/div[3]/table/tbody/tr[2]/td[2]/div")).click();
}
}	
	






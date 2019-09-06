package Sanity;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Admin {

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
	@Parameters({"UserName","Password"})
	public void testParameterWithXML(String UserName,String Password)  {
		driver.findElement(By.name("ctl00$MainContentPlaceHolder$skyWebLogin$UserName")).sendKeys(UserName);
		driver.findElement(By.name("ctl00$MainContentPlaceHolder$skyWebLogin$Password")).sendKeys(Password);
		driver.findElement(By.name("ctl00$MainContentPlaceHolder$skyWebLogin$LoginButton")).click();
		driver.findElement(By.partialLinkText("ADMINISTRATION")).click();
	}
	
	@Test(priority = 1)
	@Parameters({"Label","Radius","Latitude","Longitude"})
	public void Geofence(String Label,String Radius,String Latitude,String Longitude) throws InterruptedException  {
		
		driver.findElement(By.partialLinkText("Geofences")).click();
		driver.findElement(By.id("AddButton")).click();
		driver.switchTo().frame("SkyTrac_Controls_Window_InnerFrame_0");  

		driver.findElement(By.id("NameText")).sendKeys(Label);
		driver.findElement(By.id("DistanceText")).sendKeys(Radius);
		driver.findElement(By.id("LatitudeText")).sendKeys(Latitude);
		driver.findElement(By.id("LongitudeText")).sendKeys(Longitude);
		Thread.sleep(2000);
		driver.findElement(By.id("OkButton")).click();
		Thread.sleep(5000);

		//Switch to default and delete the record

		driver.switchTo().defaultContent();
		driver.findElement(By.id("ctl00_MainContentPlaceHolder_GeofenceControl_RadGrid1_ctl00")).click();

		driver.findElement(By.xpath("//*[@id=\"ctl00_MainContentPlaceHolder_GeofenceControl_RadGrid1_ctl00__0\"]")).click();

		driver.findElement(By.id("DeleteButton")).click();
		Alert alertg = driver.switchTo().alert();	

		String alertMsggeo = driver.switchTo().alert().getText();
		System.out.println(alertMsggeo);
		alertg.accept();	
		driver.navigate().back();
	}
	

	@Test(priority = 2)
	public void GeofenceNotification() throws InterruptedException {
	
		driver.findElement(By.id("ctl00_MainContentPlaceHolder_adminControl_skyWizard_chooseUnit_unitRadTextBox_text")).sendKeys("STS_1643");
		driver.findElement(By.id("ctl00_MainContentPlaceHolder_adminControl_skyWizard_chooseUnit_findButton")).click();
		Thread.sleep(2000);

		WebElement radio1 = driver.findElement(By.id("ctl00_MainContentPlaceHolder_adminControl_skyWizard_chooseUnit_unitRadGrid_ctl01_ctl04_selectRadioButton"));
		
		radio1.click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@id='ctl00_MainContentPlaceHolder_adminControl_skyWizard_SideBarContainer_SideBarList_ctl18_SideBarButton']")).click();

		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@id='ctl00_MainContentPlaceHolder_GeofenceEventControl_skyWizard_EmailControl_emailRadGrid_ctl01_ctl03_ctl01_InitInsertButton']")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("ctl00_MainContentPlaceHolder_GeofenceEventControl_skyWizard_EmailControl_emailRadGrid_ctl01_ctl02_ctl01_EmailAddressTextBox")).sendKeys("user@rcas.co.in");

		Thread.sleep(2000);       
		driver.findElement(By.id("ctl00_MainContentPlaceHolder_GeofenceEventControl_skyWizard_EmailControl_emailRadGrid_ctl01_ctl02_ctl01_PerformInsertButton")).click(); 
		Thread.sleep(2000); 
		driver.findElement(By.id("ctl00_MainContentPlaceHolder_GeofenceEventControl_skyWizard_EmailControl_emailRadGrid_ctl01_ctl04_EditButton")).click();
		Thread.sleep(2000); 
		
		driver.findElement(By.xpath("//*[@id=\"ctl00_MainContentPlaceHolder_GeofenceEventControl_skyWizard_EmailControl_emailRadGrid_ctl01_ctl04_EmailAddressTextBox\"]")).clear();
		Thread.sleep(2000); 
		driver.findElement(By.xpath("//*[@id=\"ctl00_MainContentPlaceHolder_GeofenceEventControl_skyWizard_EmailControl_emailRadGrid_ctl01_ctl04_EmailAddressTextBox\"]")).sendKeys("user1@rcas.co.in");
		String editmail = driver.findElement(By.id("ctl00_MainContentPlaceHolder_GeofenceEventControl_skyWizard_EmailControl_emailRadGrid_ctl01_ctl04_EmailAddressTextBox")).getAttribute("value");
		System.out.println("value="+editmail);
		driver.findElement(By.id("ctl00_MainContentPlaceHolder_GeofenceEventControl_skyWizard_EmailControl_emailRadGrid_ctl01_ctl04_UpdateButton")).click();
		Thread.sleep(2000); 
		String afteredit = driver.findElement(By.id("ctl00_MainContentPlaceHolder_GeofenceEventControl_skyWizard_EmailControl_emailRadGrid_ctl01_ctl04_EmailAddressLabel")).getAttribute("innerText");
		System.out.println("value="+afteredit);
		if (editmail.matches(afteredit)){
			System.out.println("The edited record has been updated sucessfully");
		}
		else{
			Assert.fail("The edited record has not been updated sucessfully");
		
			Thread.sleep(3000);
		}

		driver.findElement(By.id("ctl00_MainContentPlaceHolder_GeofenceEventControl_skyWizard_EmailControl_emailRadGrid_ctl01_ctl04_DeleteButton")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("ctl00_MainContentPlaceHolder_GeofenceEventControl_skyWizard_EmailControl_emailDeleteConfirmed")).click();
		Thread.sleep(2000);
		driver.navigate().back();
	}
	
	@Test(priority = 3)
	public void ETANotification() throws InterruptedException {
		driver.findElement(By.id("ctl00_MainContentPlaceHolder_adminControl_skyWizard_chooseUnit_unitRadTextBox_text")).sendKeys("STS_1643");
		driver.findElement(By.id("ctl00_MainContentPlaceHolder_adminControl_skyWizard_chooseUnit_findButton")).click();
		Thread.sleep(2000);
		WebElement radio15 = driver.findElement(By.id("ctl00_MainContentPlaceHolder_adminControl_skyWizard_chooseUnit_unitRadGrid_ctl01_ctl04_selectRadioButton"));
		radio15.click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@id=\'ctl00_MainContentPlaceHolder_adminControl_skyWizard_SideBarContainer_SideBarList_ctl20_SideBarButton\']")).click();
		
		Thread.sleep(2000);
		driver.findElement(By.id("ctl00_MainContentPlaceHolder_ETAEventControl_skyWizard_EmailControl_emailRadGrid_ctl01_ctl03_ctl01_InitInsertButton")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("ctl00_MainContentPlaceHolder_ETAEventControl_skyWizard_EmailControl_emailRadGrid_ctl01_ctl02_ctl01_EmailAddressTextBox")).sendKeys("user@rcas.co.in");

		Thread.sleep(2000);
		driver.findElement(By.id("ctl00_MainContentPlaceHolder_ETAEventControl_skyWizard_EmailControl_emailRadGrid_ctl01_ctl02_ctl01_PerformInsertButton")).click(); 
		Thread.sleep(2000); 
		driver.findElement(By.id("ctl00_MainContentPlaceHolder_ETAEventControl_skyWizard_EmailControl_emailRadGrid_ctl01_ctl04_EditButton")).click();
		Thread.sleep(2000); 
		
		driver.findElement(By.xpath("//*[@id=\"ctl00_MainContentPlaceHolder_ETAEventControl_skyWizard_EmailControl_emailRadGrid_ctl01_ctl04_EmailAddressTextBox\"]")).clear();
		Thread.sleep(2000); 
		driver.findElement(By.xpath("//*[@id=\"ctl00_MainContentPlaceHolder_ETAEventControl_skyWizard_EmailControl_emailRadGrid_ctl01_ctl04_EmailAddressTextBox\"]")).sendKeys("user2@rcas.co.in");
		String editmaileta = driver.findElement(By.id("ctl00_MainContentPlaceHolder_ETAEventControl_skyWizard_EmailControl_emailRadGrid_ctl01_ctl04_EmailAddressTextBox")).getAttribute("value");
		System.out.println("value="+editmaileta);
		driver.findElement(By.id("ctl00_MainContentPlaceHolder_ETAEventControl_skyWizard_EmailControl_emailRadGrid_ctl01_ctl04_UpdateButton")).click();
		Thread.sleep(2000); 
		String afterediteta = driver.findElement(By.id("ctl00_MainContentPlaceHolder_ETAEventControl_skyWizard_EmailControl_emailRadGrid_ctl01_ctl04_EmailAddressLabel")).getAttribute("innerText");
		System.out.println("value="+afterediteta);

		if (editmaileta.matches(afterediteta)){
			System.out.println("The ETA record has been updated sucessfully");
		}
		else{
			Assert.fail("The ETA record has not been updated sucessfully");
		
			Thread.sleep(3000);
		}

		driver.findElement(By.id("ctl00_MainContentPlaceHolder_ETAEventControl_skyWizard_EmailControl_emailRadGrid_ctl01_ctl04_DeleteButton")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("ctl00_MainContentPlaceHolder_ETAEventControl_skyWizard_EmailControl_etaEmailDeleteConfirmed")).click();
		Thread.sleep(2000);
		driver.navigate().back();
		
	}
	
	@Test(priority = 4)
	public void FlightwatchNotification() throws InterruptedException {
		driver.findElement(By.id("ctl00_MainContentPlaceHolder_adminControl_skyWizard_chooseUnit_unitRadTextBox_text")).sendKeys("STS_1643");
		driver.findElement(By.id("ctl00_MainContentPlaceHolder_adminControl_skyWizard_chooseUnit_findButton")).click();
		Thread.sleep(2000);
		WebElement radiofw = driver.findElement(By.id("ctl00_MainContentPlaceHolder_adminControl_skyWizard_chooseUnit_unitRadGrid_ctl01_ctl04_selectRadioButton"));
		radiofw.click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"ctl00_MainContentPlaceHolder_adminControl_skyWizard_SideBarContainer_SideBarList_ctl19_SideBarButton\"]")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("ctl00_MainContentPlaceHolder_FlightWatchEventControl_skyWizard_EmailControl_emailRadGrid_ctl01_ctl03_ctl01_InitInsertButton")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("ctl00_MainContentPlaceHolder_FlightWatchEventControl_skyWizard_EmailControl_emailRadGrid_ctl01_ctl02_ctl01_EmailAddressTextBox")).sendKeys("user@rcas.co.in");

		Thread.sleep(2000);
		driver.findElement(By.id("ctl00_MainContentPlaceHolder_FlightWatchEventControl_skyWizard_EmailControl_emailRadGrid_ctl01_ctl02_ctl01_PerformInsertButton")).click(); 
		Thread.sleep(2000); 
		driver.findElement(By.id("ctl00_MainContentPlaceHolder_FlightWatchEventControl_skyWizard_EmailControl_emailRadGrid_ctl01_ctl04_EditButton")).click();
		Thread.sleep(2000); 
		
		driver.findElement(By.xpath("//*[@id=\"ctl00_MainContentPlaceHolder_FlightWatchEventControl_skyWizard_EmailControl_emailRadGrid_ctl01_ctl04_EmailAddressTextBox\"]")).clear();
		Thread.sleep(2000); 
		driver.findElement(By.xpath("//*[@id=\"ctl00_MainContentPlaceHolder_FlightWatchEventControl_skyWizard_EmailControl_emailRadGrid_ctl01_ctl04_EmailAddressTextBox\"]")).sendKeys("user12@rcas.co.in");
		String editmailfw = driver.findElement(By.id("ctl00_MainContentPlaceHolder_FlightWatchEventControl_skyWizard_EmailControl_emailRadGrid_ctl01_ctl04_EmailAddressTextBox")).getAttribute("value");
		System.out.println("value="+editmailfw);
		driver.findElement(By.id("ctl00_MainContentPlaceHolder_FlightWatchEventControl_skyWizard_EmailControl_emailRadGrid_ctl01_ctl04_UpdateButton")).click();
		Thread.sleep(2000); 
		String aftereditfw = driver.findElement(By.id("ctl00_MainContentPlaceHolder_FlightWatchEventControl_skyWizard_EmailControl_emailRadGrid_ctl01_ctl04_EmailAddressLabel")).getAttribute("innerText");
		System.out.println("value="+aftereditfw);

		if (editmailfw.matches(aftereditfw)){
			System.out.println("The Flightwatch record has been updated sucessfully");
		}
		else{
			Assert.fail("The Flightwatch record has not been updated sucessfully");
		
			Thread.sleep(3000);
		}

		driver.findElement(By.id("ctl00_MainContentPlaceHolder_FlightWatchEventControl_skyWizard_EmailControl_emailRadGrid_ctl01_ctl04_DeleteButton")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("ctl00_MainContentPlaceHolder_FlightWatchEventControl_skyWizard_EmailControl_emailDeleteConfirmed")).click();
		Thread.sleep(2000);
		driver.navigate().back();
		
	}
	
	
	
	
	
	@Test(priority =10 )
	@Parameters({"Name","Ident","Region","LatitudeText1","LongitudeText1"})
	public void Georeference(String Name,String Ident,String Region,String LatitudeText1,String LongitudeText1) throws InterruptedException {

		driver.findElement(By.partialLinkText("Georeferences")).click();
		driver.findElement(By.id("AddButton")).click();
		driver.switchTo().frame("SkyTrac_Controls_Window_InnerFrame_0"); 

		driver.findElement(By.id("NameText")).sendKeys(Name);
		driver.findElement(By.id("IdentText")).sendKeys(Ident);
		driver.findElement(By.id("RegionText")).sendKeys(Region);
		driver.findElement(By.id("LatitudeText")).sendKeys(LatitudeText1);
		driver.findElement(By.id("LongitudeText")).sendKeys(LongitudeText1);
		Thread.sleep(2000);
		driver.findElement(By.id("OkButton")).click();
		Thread.sleep(8000);
		driver.switchTo().defaultContent();
		Thread.sleep(8000);
		driver.findElement(By.id("ctl00_MainContentPlaceHolder_GeoReferencesControl1_RadGrid1_ctl00")).click();

		driver.findElement(By.xpath("//*[@id=\"ctl00_MainContentPlaceHolder_GeoReferencesControl1_RadGrid1_ctl00__0\"]")).click();

		driver.findElement(By.id("DeleteButton")).click();
		Alert alert1 = driver.switchTo().alert();	

		String alertMessage = driver.switchTo().alert().getText();
		System.out.println(alertMessage);
		Thread.sleep(2000);
		alert1.accept();	
		Thread.sleep(2000);
		
		driver.findElement(By.id("AddButton")).click();
		driver.switchTo().frame("SkyTrac_Controls_Window_InnerFrame_0"); 

		driver.findElement(By.id("NameText")).sendKeys(Name);
		driver.findElement(By.id("IdentText")).sendKeys(Ident);
		driver.findElement(By.id("RegionText")).sendKeys(Region);
		driver.findElement(By.id("LatitudeText")).sendKeys(LatitudeText1);
		driver.findElement(By.id("LongitudeText")).sendKeys(LongitudeText1);
		Thread.sleep(2000);
		driver.findElement(By.id("OkButton")).click();
		Thread.sleep(8000);
		driver.switchTo().defaultContent();
		Thread.sleep(8000);
		driver.findElement(By.id("ctl00_MainContentPlaceHolder_GeoReferencesControl1_RadGrid1_ctl00")).click();
		
		driver.findElement(By.partialLinkText("HOME")).click();
		Thread.sleep(2000);
		driver.findElement(By.partialLinkText("FLIGHT FOLLOWING")).click();
		Thread.sleep(6000);
		driver.switchTo().frame("SkyTrac_Controls_Window_InnerFrame_52"); 
		driver.findElement(By.id("Ok")).click();
		driver.switchTo().defaultContent();
		Thread.sleep(5000);
		WebElement element4 = driver.findElement(By.xpath("//*[@id=\"RadMenubar_m3\"]/span"));
		Thread.sleep(3000);
		Actions action8 = new Actions(driver);
		action8.moveToElement(element4).build().perform();
		driver.findElement(By.linkText("Destination...")).click();	
		Thread.sleep(3000);
		driver.switchTo().frame("SkyTrac_Controls_Window_InnerFrame_398");
		
		Select sunit = new Select(driver.findElement(By.id("CallSignSelect")));
		sunit.selectByValue("<STS_1643>");
		
		driver.findElement(By.id("SelectButton")).click();
		driver.switchTo().defaultContent();
		driver.switchTo().frame("SkyTrac_Controls_Window_InnerFrame_399");
		int count = 0;
	    String[] exp = {"Georef (Denmark)"};
	    WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"SkyTrac_Controls_0\"]/select"));  
	    Select select = new Select(dropdown);

	    List<WebElement> options = select.getOptions();
	    for (WebElement we : options) {
	        for (int i = 0; i < exp.length; i++) {
	        	System.out.println(we.getText());
	            if (we.getText().equals(exp[i])) {
	                count++;
	            }
	        }
	    }
	    if (count == exp.length) {
	        System.out.println("Active georeference is listed");
	    } else {
	        System.out.println("Active georeference is not listed");
	    }
	    driver.findElement(By.id("CancelButton")).click();
	    driver.switchTo().defaultContent();
	    driver.switchTo().frame("SkyTrac_Controls_Window_InnerFrame_398");
	    driver.findElement(By.id("ClearButton")).click();
	    driver.findElement(By.id("Donebutton")).click();
	    driver.switchTo().defaultContent();
		
		Thread.sleep(2000);
	}
	
	
	@Test(priority = 5)
	public void EventNotification() throws InterruptedException {
		driver.findElement(By.id("ctl00_MainContentPlaceHolder_adminControl_skyWizard_chooseUnit_unitRadTextBox_text")).sendKeys("STS_1643");
		driver.findElement(By.id("ctl00_MainContentPlaceHolder_adminControl_skyWizard_chooseUnit_findButton")).click();
		Thread.sleep(2000);
		WebElement radio1 = driver.findElement(By.id("ctl00_MainContentPlaceHolder_adminControl_skyWizard_chooseUnit_unitRadGrid_ctl01_ctl04_selectRadioButton"));
		radio1.click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"ctl00_MainContentPlaceHolder_adminControl_skyWizard_SideBarContainer_SideBarList_ctl17_SideBarButton\"]")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("ctl00_MainContentPlaceHolder_ctl01_EmailGridControl_eventNotificationEmailRadGrid_ctl01_ctl03_ctl01_InitInsertButton")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("ctl00_MainContentPlaceHolder_ctl01_EmailGridControl_eventNotificationEmailRadGrid_ctl01_ctl02_ctl01_ContactNameTextBox")).sendKeys("user");
		Thread.sleep(2000);
		Select s4 = new Select(driver.findElement(By.id("ctl00_MainContentPlaceHolder_ctl01_EmailGridControl_eventNotificationEmailRadGrid_ctl01_ctl02_ctl01_DDLNotificationMethod")));
		s4.selectByIndex(1);
		Thread.sleep(2000);
		driver.findElement(By.id("ctl00_MainContentPlaceHolder_ctl01_EmailGridControl_eventNotificationEmailRadGrid_ctl01_ctl02_ctl01_EmailstrTextBox")).sendKeys("user@rcas.co.in");
		Thread.sleep(2000);
		driver.findElement(By.id("ctl00_MainContentPlaceHolder_ctl01_EmailGridControl_eventNotificationEmailRadGrid_ctl01_ctl02_ctl01_PerformInsertButton")).click();
		Thread.sleep(2000);       
		driver.findElement(By.id("ctl00_MainContentPlaceHolder_ctl01_EmailGridControl_eventNotificationEmailRadGrid_ctl01_ctl04_EditButton")).click();
		
		driver.findElement(By.xpath("//*[@id=\"ctl00_MainContentPlaceHolder_ctl01_EmailGridControl_eventNotificationEmailRadGrid_ctl01_ctl04_EmailstrTextBox\"]")).clear();
		Thread.sleep(2000); 
		driver.findElement(By.xpath("//*[@id=\"ctl00_MainContentPlaceHolder_ctl01_EmailGridControl_eventNotificationEmailRadGrid_ctl01_ctl04_EmailstrTextBox\"]")).sendKeys("user23@rcas.co.in");
		Thread.sleep(2000); 
		driver.findElement(By.id("ctl00_MainContentPlaceHolder_ctl01_EmailGridControl_eventNotificationEmailRadGrid_ctl01_ctl04_UpdateButton")).click();
		Thread.sleep(2000); 
		driver.findElement(By.id("ctl00_MainContentPlaceHolder_ctl01_EmailGridControl_eventNotificationEmailRadGrid_ctl01_ctl04_DeleteButton")).click();
		Thread.sleep(2000); 
		driver.findElement(By.id("ctl00_MainContentPlaceHolder_ctl01_EmailGridControl_emailDeleteConfirmed")).click();
		driver.navigate().back();
		Thread.sleep(2000);
	}

	@Test(priority = 6)
	public void DVITextMessages() throws InterruptedException {
		driver.findElement(By.partialLinkText("DVI Text Messages")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("ctl00_MainContentPlaceHolder_DviTextMessagesMain_ManageMessageGroups_addRecordButton")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("ctl00_MainContentPlaceHolder_DviTextMessagesMain_AddMessageGroup_dviMsgNameTextBox_text")).sendKeys("NewGroup12");
		driver.findElement(By.id("ctl00_MainContentPlaceHolder_DviTextMessagesMain_AddMessageGroup_saveAndAddNewGroupButton")).click();
		Thread.sleep(2000);
		WebElement radioDVI = driver.findElement(By.id("ctl00_MainContentPlaceHolder_DviTextMessagesMain_ManageMessageGroups_dviMessageGroupRadGrid_ctl00_ctl06_selectDviMsgGroup"));
		radioDVI.click();
		Thread.sleep(2000);
		driver.findElement(By.id("ctl00_MainContentPlaceHolder_DviTextMessagesMain_ManageMessageGroups_deleteRecordButton")).click();
		Alert alertDVI = driver.switchTo().alert();	

		String alertMsgdvi = driver.switchTo().alert().getText();
		System.out.println(alertMsgdvi);
		Thread.sleep(2000);
		alertDVI.accept();	
		Thread.sleep(2000);
		driver.navigate().back();
		Thread.sleep(2000);
	}
	
	@Test(priority = 7)
	public void DVIPhonenumber() throws InterruptedException {
		driver.findElement(By.partialLinkText("DVI Phone Numbers")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("ctl00_MainContentPlaceHolder_DviPhoneNumbersMain_ManagePhoneGroups_addRecordButton")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("ctl00_MainContentPlaceHolder_DviPhoneNumbersMain_AddPhoneGroup_dviPhoneNameTextBox_text")).sendKeys("NewGroup14");

		driver.findElement(By.id("ctl00_MainContentPlaceHolder_DviPhoneNumbersMain_AddPhoneGroup_dviPhoneRadGrid_ctl00_ctl04_EditButton")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("ctl00_MainContentPlaceHolder_DviPhoneNumbersMain_AddPhoneGroup_dviPhoneRadGrid_ctl00_ctl05_dviPhoneNumberTextBoxControl")).sendKeys("3536223189");
		driver.findElement(By.id("ctl00_MainContentPlaceHolder_DviPhoneNumbersMain_AddPhoneGroup_dviPhoneRadGrid_ctl00_ctl05_dviBtnUpdateControl")).click();
		Thread.sleep(4000);
		driver.findElement(By.id("ctl00_MainContentPlaceHolder_DviPhoneNumbersMain_AddPhoneGroup_saveAndAddNewGroupButton")).click();

		Alert alertDVIph = driver.switchTo().alert();	

		String alertMsgdviph = driver.switchTo().alert().getText();
		System.out.println(alertMsgdviph);
		Thread.sleep(2000);
		alertDVIph.accept();	
		Thread.sleep(5000);
		WebElement radioDVIph = driver.findElement(By.id("ctl00_MainContentPlaceHolder_DviPhoneNumbersMain_ManagePhoneGroups_dviPhoneGroupRadGrid_ctl00_ctl06_selectDviPhoneGroup"));
		radioDVIph.click();
		Thread.sleep(5000);
		driver.findElement(By.id("ctl00_MainContentPlaceHolder_DviPhoneNumbersMain_ManagePhoneGroups_deleteRecordButton")).click();
		Alert alertDVIph1 = driver.switchTo().alert();	

		String alertphdvi = driver.switchTo().alert().getText();
		System.out.println(alertphdvi);
		Thread.sleep(2000);
		alertDVIph1.accept();	
		Thread.sleep(2000);
		driver.navigate().back();
		Thread.sleep(2000);
	}

	@Test(priority = 8)
	public void Overdue() throws InterruptedException {
		driver.findElement(By.partialLinkText("Overdue Notification(s)")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("ctl00_MainContentPlaceHolder_OverdueNotificationManager_ConfigureNotificationSettings_AddRecordButton")).click();
		Thread.sleep(4000);
		driver.findElement(By.id("ctl00_MainContentPlaceHolder_OverdueNotificationManager_AddOrEditNotificationSetting_IdLabelTextBox")).sendKeys("One");
		driver.findElement(By.id("ctl00_MainContentPlaceHolder_OverdueNotificationManager_AddOrEditNotificationSetting_OverdueNotificationsEmailsDataGrid_AddEmailButton")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("ctl00_MainContentPlaceHolder_OverdueNotificationManager_AddOrEditNotificationSetting_OverdueNotificationsEmailsDataGrid_EmailTextBox")).sendKeys("user1@rcas.co.in");
		driver.findElement(By.id("ctl00_MainContentPlaceHolder_OverdueNotificationManager_AddOrEditNotificationSetting_OverdueNotificationsEmailsDataGrid_SaveEmailButton")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("ctl00_MainContentPlaceHolder_OverdueNotificationManager_AddOrEditNotificationSetting_SaveNewButton")).click();
		Thread.sleep(2000);
		WebElement radiooverdue = driver.findElement(By.id("ctl00_MainContentPlaceHolder_OverdueNotificationManager_ConfigureNotificationSettings_OverdueNotificationsListDataGrid_OverdueNotificationsListRadGrid_ctl00_ctl04_SelectOverdueNotificationSetting"));
		radiooverdue.click();
		Thread.sleep(2000);
		driver.findElement(By.id("ctl00_MainContentPlaceHolder_OverdueNotificationManager_ConfigureNotificationSettings_EditRecordButton")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("ctl00_MainContentPlaceHolder_OverdueNotificationManager_AddOrEditNotificationSetting_EmailSubjectTextBox")).sendKeys(" text to test");
		Thread.sleep(2000);
		driver.findElement(By.id("ctl00_MainContentPlaceHolder_OverdueNotificationManager_AddOrEditNotificationSetting_SaveNewButton")).click();
		Thread.sleep(2000);
		WebElement radiood = driver.findElement(By.id("ctl00_MainContentPlaceHolder_OverdueNotificationManager_ConfigureNotificationSettings_OverdueNotificationsListDataGrid_OverdueNotificationsListRadGrid_ctl00_ctl04_SelectOverdueNotificationSetting"));
		radiood.click();
		Thread.sleep(5000);
		driver.findElement(By.id("ctl00_MainContentPlaceHolder_OverdueNotificationManager_ConfigureNotificationSettings_DeleteRecordButton")).click();
		Alert alertDVIph1 = driver.switchTo().alert();	

		String alertod = driver.switchTo().alert().getText();
		System.out.println(alertod);
		Thread.sleep(2000);
		alertDVIph1.accept();	
		Thread.sleep(2000);
		driver.navigate().back();
		Thread.sleep(2000);
	}
	
	
	@Test(priority = 9)
	public void Cockpit() throws InterruptedException {
		driver.findElement(By.id("ctl00_MainContentPlaceHolder_adminControl_skyWizard_chooseUnit_unitRadTextBox_text")).sendKeys("STS_1643");
		driver.findElement(By.id("ctl00_MainContentPlaceHolder_adminControl_skyWizard_chooseUnit_findButton")).click();
		Thread.sleep(2000);
		WebElement radio1 = driver.findElement(By.id("ctl00_MainContentPlaceHolder_adminControl_skyWizard_chooseUnit_unitRadGrid_ctl01_ctl04_selectRadioButton"));
		radio1.click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"ctl00_MainContentPlaceHolder_adminControl_skyWizard_SideBarContainer_SideBarList_ctl16_SideBarButton\"]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"ctl00_MainContentPlaceHolder_CDPControl1_skyWizard_SideBarContainer_SideBarList_ctl01_SideBarButton\"]")).click();
		driver.findElement(By.id("ctl00_MainContentPlaceHolder_CDPControl1_skyWizard_cockpitInterfacePropertiesControl_cockpitLabel")).sendKeys("123456");
		driver.findElement(By.id("ctl00_MainContentPlaceHolder_CDPControl1_skyWizard_cockpitInterfacePropertiesControl_saveButton")).click();
		driver.findElement(By.id("ctl00_MainContentPlaceHolder_CDPControl1_skyWizard_cockpitInterfaceListControl_editButton0")).click();
		driver.findElement(By.id("ctl00_MainContentPlaceHolder_CDPControl1_skyWizard_cockpitInterfacePropertiesControl_cockpitLabel")).clear();
		driver.findElement(By.id("ctl00_MainContentPlaceHolder_CDPControl1_skyWizard_cockpitInterfacePropertiesControl_cockpitLabel")).sendKeys("55555");
		String editidentifier = driver.findElement(By.id("ctl00_MainContentPlaceHolder_CDPControl1_skyWizard_cockpitInterfacePropertiesControl_cockpitLabel")).getAttribute("value");
		System.out.println("value="+editidentifier);
		
		driver.findElement(By.id("ctl00_MainContentPlaceHolder_CDPControl1_skyWizard_cockpitInterfacePropertiesControl_saveButton")).click();
		String chgvalue = driver.findElement(By.id("ctl00_MainContentPlaceHolder_CDPControl1_skyWizard_cockpitInterfaceListControl_unitLabel0")).getAttribute("innerText");
		System.out.println("value="+chgvalue);

		if (editidentifier.matches(chgvalue)){
			System.out.println("The Identifier has been updated sucessfully");
		}
		else{
			Assert.fail("The Identifier has not been updated sucessfully");
		
			Thread.sleep(3000);
		}
		
		
		//Test case 1167
		driver.findElement(By.xpath("//*[@id=\"ctl00_MainContentPlaceHolder_CDPControl1_skyWizard_SideBarContainer_SideBarList_ctl02_SideBarButton\"]")).click();
		WebElement radiocdp = driver.findElement(By.id("ctl00_MainContentPlaceHolder_CDPControl1_skyWizard_EditQuickMailControl_unitRadGrid_ctl01_ctl04_selectRadioButton"));
		radiocdp.click();
		Thread.sleep(3000);
		driver.findElement(By.id("ctl00_MainContentPlaceHolder_CDPControl1_skyWizard_EditQuickMailControl_txtAddress_text")).sendKeys("user@rcas.co.in");
		driver.findElement(By.id("ctl00_MainContentPlaceHolder_CDPControl1_skyWizard_EditQuickMailControl_updateButton")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"ctl00_MainContentPlaceHolder_CDPControl1_skyWizard_SideBarContainer_SideBarList_ctl03_SideBarButton\"]")).click();
		WebElement radiocdu = driver.findElement(By.id("ctl00_MainContentPlaceHolder_CDPControl1_skyWizard_AddressBookControl_unitRadGrid_ctl01_ctl04_selectRadioButton"));
		radiocdu.click();
		Thread.sleep(3000);
		driver.findElement(By.id("ctl00_MainContentPlaceHolder_CDPControl1_skyWizard_AddressBookControl_txtAlias_text")).sendKeys("12345");
		driver.findElement(By.id("ctl00_MainContentPlaceHolder_CDPControl1_skyWizard_AddressBookControl_txtPhoneNumber_text")).sendKeys("123456789");
		driver.findElement(By.id("ctl00_MainContentPlaceHolder_CDPControl1_skyWizard_AddressBookControl_cmdModify")).click();
		Alert alertcdu1 = driver.switchTo().alert();	
		
				String alertcdu = driver.switchTo().alert().getText();
				System.out.println(alertcdu);
				Thread.sleep(2000);
				alertcdu1.accept();	
				Thread.sleep(2000);
				driver.navigate().back();
				Thread.sleep(2000);
	}
		
	
	
}

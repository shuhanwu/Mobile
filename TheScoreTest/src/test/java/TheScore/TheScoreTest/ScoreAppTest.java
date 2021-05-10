package TheScore.TheScoreTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.NoSuchElementException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;

public class ScoreAppTest {
	
    public WebDriverWait                wait;
    public WebElement 					element;
    
    public AndroidDriver<?> 			driver;
    
    public String 						deviceName = "emulator-5554";
    public String 						platformName = "Android";
    public String 						platformVersion = "11.0";
    public String 						appPackage = "com.fivemobile.thescore";
    public String 						appActivity = "com.fivemobile.thescore.ui.MainActivity";
    public String 						leagueNameStr = "NBA Basketball";

    //Elements By
    By btnGetStarted   	= By.xpath("//android.widget.TextView[@text='Get Started']");
    By btnContinue 		= By.xpath("//android.widget.TextView[@text='Continue']");
    By btnMaybeLater   	= By.xpath("//android.widget.TextView[@text='Maybe Later']");
    By favCanada      	= By.xpath("//android.widget.TextView[@text='Canada']");
    By btnDone   		= By.xpath("//android.widget.TextView[@text='Done']");
    
    By tailoredContents  = By.xpath("//android.widget.TextView[@text='Tailored Contents']");

    
    By btnNews  	= By.xpath("//android.widget.TextView[@text='News']");
    By btnScores  	= By.xpath("//android.widget.TextView[@text='Scores']");

    By btnLeagues   	= By.xpath("//android.widget.TextView[@text='Leagues']");
    By sectionLEAGUES   = By.xpath("//android.widget.TextView[@text='LEAGUES']");


  @Test
  public void TestLeague() throws InterruptedException {
      
	  //App launch set up
	try
	{
	  		wait.until(ExpectedConditions.visibilityOfElementLocated(btnGetStarted)).click();
	  		Thread.sleep(3);
	  		wait.until(ExpectedConditions.visibilityOfElementLocated(btnContinue)).click();	
	  		Thread.sleep(5);
	  		wait.ignoring(NoSuchElementException.class);
	  		wait.until(ExpectedConditions.visibilityOfElementLocated(btnMaybeLater)).click();
	  		Thread.sleep(3);
	  		wait.until(ExpectedConditions.visibilityOfElementLocated(favCanada)).click();
	  		Thread.sleep(5);
	  		wait.until(ExpectedConditions.visibilityOfElementLocated(btnContinue)).click();
	  		Thread.sleep(5);
	  		wait.until(ExpectedConditions.visibilityOfElementLocated(btnDone)).click();
	  		Thread.sleep(5);
	}
	catch (NoSuchElementException ignored) 
	{
	}	

    By leagueName  	= By.xpath("//android.widget.TextView[@text='" + leagueNameStr + "']");
    By titleLeague  	= By.xpath("//android.widget.TextView[@text='" + leagueNameStr.toUpperCase() + "']");
    
	WebElement homepageScore = wait.until(ExpectedConditions.presenceOfElementLocated(btnScores));
	Assert.assertEquals(homepageScore.getText(), "Scores");

	//click league page btnLeagues
	wait.until(ExpectedConditions.visibilityOfElementLocated(btnLeagues)).click();
	Thread.sleep(3);
	WebElement pageLeagues = wait.until(ExpectedConditions.presenceOfElementLocated(sectionLEAGUES));
	Assert.assertEquals(pageLeagues.getText(), "LEAGUES");

	wait.until(ExpectedConditions.visibilityOfElementLocated(leagueName)).click();
	Thread.sleep(3);
	WebElement sectionLeagues = wait.until(ExpectedConditions.presenceOfElementLocated(titleLeague));
	Assert.assertEquals(sectionLeagues.getText(), leagueNameStr.toUpperCase());
	
	assert sectionLeagues.getText().equals(leagueNameStr.toUpperCase()):"Actual League is : "+ sectionLeagues.getText()+" did not match with expected League:" + leagueNameStr.toUpperCase();
 	
	//navigate back
	driver.navigate().back();
	Thread.sleep(3);
	  		  	
	WebElement homepageNews = wait.until(ExpectedConditions.presenceOfElementLocated(btnNews));
	Assert.assertEquals(homepageNews.getText(), "News"); 	
	
	assert homepageNews.getText().equals("News"):"Actual Homepage display : "+ homepageNews.getText()+" did not match with expected News";
	  	
}

@BeforeTest
  public void beforeTest() throws IOException, InterruptedException {
	
	//========================
	//Read excel data sheet to get parameters
    
    String excelFilePath = System.getProperty("user.dir") + "\\src\\test\\java\\TheScore\\TheScoreTest\\TheScoreData.xlsx";
    	
    File file = new File(excelFilePath);
    FileInputStream inputStream = new FileInputStream(file);  
    Workbook workBook = null;
        
    String fileExtensionName = excelFilePath.substring(excelFilePath.indexOf("."));
 
    if ( fileExtensionName.equals(".xlsx"))
    {
          workBook = new XSSFWorkbook(inputStream);
    } 
    else if (  fileExtensionName.equals(".xls") )
    {
		  workBook = new HSSFWorkbook(inputStream);
    }
        
    Sheet sheet = (Sheet) workBook.getSheet("Sheet1");
    Row row = sheet.getRow(0);
    int colNum = row.getLastCellNum();
    row = sheet.getRow(1);
    for (int i=0; i<colNum; i++)
	{
			switch (i) {
				case 0: deviceName 			= row.getCell(i).toString();
				case 1: platformName 		= row.getCell(i).toString();
				case 2: platformVersion 	= row.getCell(i).toString();
				case 3: appPackage 			= row.getCell(i).toString();
				case 4: appActivity 		= row.getCell(i).toString();
				case 5: leagueNameStr 		= row.getCell(i).toString();
			}
	};  	


	//========================
	//Set up desired capabilities and pass the Android app-activity and app-package to Appium
	DesiredCapabilities caps = new DesiredCapabilities();
	caps.setCapability("deviceName", deviceName);
	caps.setCapability("platformName", platformName);
	caps.setCapability("platformVersion", platformVersion);
	caps.setCapability("appPackage", appPackage);
	caps.setCapability("appActivity", appActivity);
	caps.setCapability("skipUnlock", "true");
	caps.setCapability("noReset", "false");
	      
	driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
	      
	wait = (WebDriverWait) new WebDriverWait(driver, 20).ignoring(NoSuchElementException.class);	      
	Thread.sleep(10000);
  }

  @AfterTest
  public void afterTest() {
	  driver.quit();
  }

}

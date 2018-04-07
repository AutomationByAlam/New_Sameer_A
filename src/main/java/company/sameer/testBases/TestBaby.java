package company.sameer.testBases;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import company.sameer.allHelper.WaitHelper;
import company.sameer.excelReader.Excel_reader;




public class TestBaby {

	
	public static final Logger logger = Logger.getLogger(TestBaby.class.getName());
	public static WebDriver driver;
	public static Properties OR;
	

	public File f1;
	public FileInputStream file;
	
	public static ExtentReports extent;
	public static ExtentTest test;
	public ITestResult result;
	

	//public Excel_reader excelreader;
	
	static {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		extent = new ExtentReports(System.getProperty("user.dir") + "/src/main/java/company/sameer/report/test" + formater.format(calendar.getTime()) + ".html", false);
	}
	
	@BeforeTest
	public void launchBrowser() throws IOException{
		loadPropertiesFile();
		Config config = new Config(OR);
		getBrowser("chrome");
		WaitHelper waitHelper = new WaitHelper(driver);
		waitHelper.setImplicitWait(config.getImplicitWait(), TimeUnit.SECONDS);
		waitHelper.setPageLoadTimeout(config.getPageLoadTimeOut(), TimeUnit.SECONDS);
}
	

	@BeforeMethod()
	public void beforeMethod(Method result) {
		
		Reporter.log(result.getName()+"    Test started");
		test = extent.startTest(result.getName());
		test.log(LogStatus.INFO, result.getName() + " test Started");
	}
	

	public void loadPropertiesFile() throws IOException {
		
		String log4jConfPath = "log4j.properties";
		PropertyConfigurator.configure(log4jConfPath);
		OR = new Properties();
		f1 = new File(System.getProperty("user.dir")+"/src/main/java/company/sameer/config/config.properties");
		file = new FileInputStream(f1);
		OR.load(file);
		logger.info("loading config.properties");
		
		f1 = new File(System.getProperty("user.dir")+"/src/main/java/company/sameer/config/config.properties");
		file = new FileInputStream(f1);
		OR.load(file);
		logger.info("loading or.properties");
		
	
			}

	


	public static void highlightMe(WebDriver driver, WebElement element) throws InterruptedException {
		// Creating JavaScriptExecuter Interface
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// Execute javascript
		js.executeScript("arguments[0].style.border='4px solid yellow'", element);
		Thread.sleep(3000);
		js.executeScript("arguments[0].style.border=''", element);
	}

	
	public void getBrowser(String browser) throws IOException {
		
		if(System.getProperty("os.name").contains("Window")){
			if(browser.equalsIgnoreCase("firefox")){
				//https://github.com/mozilla/geckodriver/releases
				System.out.println(System.getProperty("user.dir"));
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"/drivers/geckodriver.exe");
				driver = new FirefoxDriver();
			}
			else if(browser.equalsIgnoreCase("chrome")){
				//https://chromedriver.storage.googleapis.com/index.html
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/drivers1/chromedriver.exe");
				driver = new ChromeDriver();
			}
		}
		else if(System.getProperty("os.name").contains("Mac")){
			System.out.println(System.getProperty("os.name"));
			if(browser.equalsIgnoreCase("firefox")){
				System.out.println(System.getProperty("user.dir"));
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"/drivers/geckodriver");
				driver = new FirefoxDriver();
			}
			else if(browser.equalsIgnoreCase("chrome")){
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/drivers/chromedriver");
				driver = new ChromeDriver();
			}
		}
	}
	
//	@Test
//	public void BornToFail()
//	{
//		Assert.assertTrue(false);
//	}
//	

	@AfterMethod()
	public void afterMethod(ITestResult result) throws IOException {
		getresult(result);
	}


public void getresult(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.SUCCESS) {

			test.log(LogStatus.PASS, result.getName() + " test is pass");
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(LogStatus.SKIP, result.getName() + " test is skipped and skip reason is:-" + result.getThrowable());
		} else if (result.getStatus() == ITestResult.FAILURE) {
			test.log(LogStatus.FAIL, result.getName() + " test is failed" + result.getThrowable());
			String screen = getScreenShot(result.getName());
			test.log(LogStatus.FAIL, test.addScreenCapture(screen));
		} else if (result.getStatus() == ITestResult.STARTED) {
			test.log(LogStatus.INFO, result.getName() + " test is started");
		}
	}

public String getScreenShot(String imageName) throws IOException{
	
	if(imageName.equals("")){
		imageName = "blank";
	}
	File image = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	String imagelocation = System.getProperty("user.dir")+"/src/main/java/company/sameer/screenshot/";
	Calendar calendar = Calendar.getInstance();
	SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
	String actualImageName = imagelocation+imageName+"_"+formater.format(calendar.getTime())+".png";
	File destFile = new File(actualImageName);
	FileUtils.copyFile(image, destFile);
	

    Reporter.log("Sign In Successful | " );  
 // This will help us to link the screen shot in testNG report
 			Reporter.log("<a href='" + destFile.getAbsolutePath() + "'> <img src='" + destFile.getAbsolutePath() + "' height='100' width='100'/> </a>");
 		
	return actualImageName;
}


public WebElement waitForElement(WebDriver driver,long time,WebElement element){
	WebDriverWait wait = new WebDriverWait(driver, time);
	return wait.until(ExpectedConditions.elementToBeClickable(element));
}

public WebElement waitForElementWithPollingInterval(WebDriver driver,long time,WebElement element){
	WebDriverWait wait = new WebDriverWait(driver, time);
	wait.pollingEvery(5, TimeUnit.SECONDS);
	wait.ignoring(NoSuchElementException.class);
	return wait.until(ExpectedConditions.elementToBeClickable(element));
}

public void impliciteWait(long time){
	driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
}


public WebElement getLocator(String locator) throws Exception {
	//System.out.println(locator);
    String[] split = locator.split(":");
	String locatorType = split[0];
	String locatorValue = split[1];
	System.out.println("locatorType:-"+locatorType);
	System.out.println("locatorValue:-"+locatorValue);
	if (locatorType.toLowerCase().equals("id"))
		return driver.findElement(By.id(locatorValue));
	else if (locatorType.toLowerCase().equals("name"))
		return driver.findElement(By.name(locatorValue));
	else if ((locatorType.toLowerCase().equals("classname"))|| (locatorType.toLowerCase().equals("class")))
		return driver.findElement(By.className(locatorValue));
	else if ((locatorType.toLowerCase().equals("tagname"))
			|| (locatorType.toLowerCase().equals("tag")))
		return driver.findElement(By.className(locatorValue));
	else if ((locatorType.toLowerCase().equals("linktext"))
			|| (locatorType.toLowerCase().equals("link")))
		return driver.findElement(By.linkText(locatorValue));
	else if (locatorType.toLowerCase().equals("partiallinktext"))
		return driver.findElement(By.partialLinkText(locatorValue));
	else if ((locatorType.toLowerCase().equals("cssselector"))
			|| (locatorType.toLowerCase().equals("css")))
		return driver.findElement(By.cssSelector(locatorValue));
	else if (locatorType.toLowerCase().equals("xpath"))
		return driver.findElement(By.xpath(locatorValue));
	else
		throw new Exception("Unknown locator type '" + locatorType + "'");
}

public  List<WebElement> getLocators(String locator) throws Exception {
    String[] split = locator.split(":");
	String locatorType = split[0];
	String locatorValue = split[1];
	System.out.println("locatorType:-"+locatorType);
	System.out.println("locatorValue:-"+locatorValue);

	if (locatorType.toLowerCase().equals("id"))
		return driver.findElements(By.id(locatorValue));
	else if (locatorType.toLowerCase().equals("name"))
		return driver.findElements(By.name(locatorValue));
	else if ((locatorType.toLowerCase().equals("classname"))
			|| (locatorType.toLowerCase().equals("class")))
		return driver.findElements(By.className(locatorValue));
	else if ((locatorType.toLowerCase().equals("tagname"))
			|| (locatorType.toLowerCase().equals("tag")))
		return driver.findElements(By.className(locatorValue));
	else if ((locatorType.toLowerCase().equals("linktext"))
			|| (locatorType.toLowerCase().equals("link")))
		return driver.findElements(By.linkText(locatorValue));
	else if (locatorType.toLowerCase().equals("partiallinktext"))
		return driver.findElements(By.partialLinkText(locatorValue));
	else if ((locatorType.toLowerCase().equals("cssselector"))
			|| (locatorType.toLowerCase().equals("css")))
		return driver.findElements(By.cssSelector(locatorValue));
	else if (locatorType.toLowerCase().equals("xpath"))
		return driver.findElements(By.xpath(locatorValue));
	else
		throw new Exception("Unknown locator type '" + locatorType + "'");
}

public WebElement getWebElement(String locator) throws Exception{
	return getLocator(OR.getProperty(locator));
}

public List<WebElement> getWebElements(String locator) throws Exception{
	return getLocators(OR.getProperty(locator));
}



public String[][] getData(String excelName, String sheetName){
	System.out.println(System.getProperty("user.dir"));
	String excellocation = System.getProperty("user.dir")+"/src/main/java/company/sameer/data/"+excelName;
	System.out.println(excellocation);
	Excel_reader excelreader = new Excel_reader();
	return excelreader.getExcelData(excellocation, sheetName);
	//return null;
}


	@AfterClass(alwaysRun = true)
	public void endTest() {
		driver.quit();
		extent.endTest(test);
		extent.flush();
	}
	
/*@Test
public void a()
{
	}
*/
	public static void main(String[] args) throws Exception {
		TestBaby test = new TestBaby();
		test.loadPropertiesFile();
	//	test.getBrowser("chrome");
		System.out.println(test.OR.getProperty("Username"));
		
		
//	test.getWebElement("Username");
		//test.getWebElements("submitbutton");
		
		//test.getLocator(test.OR.getProperty("Username"));

	}
	
}

package company.sameer.pageObject;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import company.sameer.allHelper.DropDownHelper;
import company.sameer.allHelper.JavaScriptHelper;
import company.sameer.allHelper.LoggerHelper;
import company.sameer.allHelper.WaitHelper;
import company.sameer.testBases.Config;
import company.sameer.testBases.TestBaby;

public class FaceLogin {

	
	WebDriver driver;
	JavaScriptHelper jse;
	private final Logger log = LoggerHelper.getLogger(FaceLogin.class);
	
	WaitHelper waitHelp;
	
	@FindBy(xpath=".//*[@id='email']")
	WebElement email;
	
	public void enterEmail(String az)
	{
		System.out.println("print value of az "+ az);
		log.info("Entering name");
		try {
			jse.highlightMe(this.email);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		email.sendKeys(az);
		Reporter.log("enter name");
	}
	
	@FindBy(id="pass")
	WebElement pass1;
	
	public void enterpass(String pass)
	{
		
		log.info("Entering pass");
	}
	

	@FindBy(how=How.XPATH, using=".//*[@id='loginbutton']")
	WebElement sbt;
	

	public void loginButton()
	{
		log.info("Click on Login");
		sbt.click();
		
	}
	


	@FindBy(name="firstname")
	WebElement firstName;
	
	public void enterFirst(String firstName)
	{
		log.info("Entering firstName");
		try {
			jse.highlightMe(this.firstName);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.firstName.sendKeys(firstName);
	}
	
	@FindBy(name="lastname")
	WebElement surName;
	
	public void entersurName(String surName)
	{
		log.info("Entering surNAME");
		this.surName.sendKeys(surName);
	}

	@FindBy(name="reg_email__")
	WebElement mobOrEmail;
	
	public void enternobOrEmail(String mobOrEmail)
	{
		log.info("Entering mobOeemail");
		this.mobOrEmail.sendKeys(mobOrEmail);
	}
	
	@FindBys( {
		   @FindBy(name = "websubmit"),
		   @FindBy(xpath= ".//*[text()='Create Account']")
		} )
		private WebElement elementsWithBoth_class1ANDclass2;
	
	@FindAll({
		   @FindBy(name = "websubmit1"),
		   @FindBy(xpath= ".//*[text()='Create Account']")
		})
		private WebElement elementsWithEither_class1ORclass2;
	
	public void findBysAndALL()
	{
		int c=0;
	//	elementsWithBoth_class1ANDclass2.click();
		log.info("BYS AND ALL 1st  " + c++);
	
		System.out.println("elementsWithEither_class1ORclass2   "+  elementsWithEither_class1ORclass2 +"elementsWithBoth_class1ANDclass2  "+elementsWithBoth_class1ANDclass2);
		elementsWithBoth_class1ANDclass2.click();
		log.info("BYS AND ALL 2nd " + c++);
		
	}
	
	

	@FindBy(name="reg_passwd__")
	WebElement newPass;
	
	public void enterNewPass(String newPass)
	{
		log.info("Entering new pass");
		this.newPass.sendKeys(newPass);
	}
	
	
	@FindBy(how=How.XPATH, using=".//*[text()='Female']")
	WebElement mrsClick;
	
	public void clickonMrs()
	{
		log.info("click on Mrs");
		this.mrsClick.click();
	}
	
	

	@FindBy(how=How.XPATH, using=".//*[text()='Male']")
	WebElement mrClick;
	

	public void clickonMr()
	{
		log.info("click on Mr");
		this.mrClick.click();

		Reporter.log("clickonMr");
	}
	

	@FindBy(how=How.XPATH, using="//*[@id='day']/option")
	List<WebElement> days;
	 
	public void selectDays(String day)
	{
		log.info("Selecting day"+day);
		
		Iterator<WebElement> itr = days.iterator();
		while (itr.hasNext()) {
			WebElement c = itr.next();
			String text = c.getText().trim().toString();
			if (text.equals(day)) {
				System.out.println(day);
				c.click();
				break;
			}
		}

		Reporter.log("Selecting day"+day);
	}

	
	@FindBy(how=How.XPATH, using="//*[@id='month']")
	WebElement selectMonth;
	 
	public void selectmonth(String month)
	{
		log.info("Selecting month  "+month);
		DropDownHelper ddh =new DropDownHelper(driver);
		ddh.SelectUsingVisibleText(selectMonth, month);
		

		log.info("Selected month  "+month);
		List<String> a = ddh.getAllDropDownValues(selectMonth);
		
		Iterator<String> itr = a.iterator();
		while (itr.hasNext()) {
			String c = itr.next();
			System.out.println("DropdownValue "+c );
		}

	}
	
	
	
	public FaceLogin(WebDriver driver)
	{
		this.driver =driver;
		PageFactory.initElements(driver, this);
		WaitHelper wh = new WaitHelper(driver);
		wh.waitForElement(driver, email, new Config(TestBaby.OR).getExplicitWait());
		jse=new JavaScriptHelper(driver);
	}

	public void loginToApplication(String userName, String password) throws InterruptedException {
 enterEmail(userName);
 enterpass(password);
enterFirst("alam");
entersurName("sayed");
enternobOrEmail("alam1905@gmail.com");
enterNewPass("345666");
clickonMr();

setDay("25");
setMonth("Oct");
setYear("1990");

selectDays("12");
selectmonth("Aug");
findBysAndALL();

Thread.sleep(5000);

	}
	
	public void setDay(String day) {
		List<WebElement> days = driver.findElements(By.xpath("//*[@id='day']/option"));
		Iterator<WebElement> itr = days.iterator();
		while (itr.hasNext()) {
			WebElement c = itr.next();
			String text = c.getText().trim().toString();
			if (text.equals(day)) {
				System.out.println(day);
				c.click();
				break;
			}
		}
	}

	public void setMonth(String month) {

		List<WebElement> days = driver.findElements(By.xpath("//*[@id='month']/option"));
		Iterator<WebElement> itr = days.iterator();
		while (itr.hasNext()) {
			WebElement click = itr.next();
			String text = click.getText().trim();
			if (text.equals(month)) {
				click.click();
				break;
			}
		}
	}

	public void setYear(String year) {

		List<WebElement> days = driver.findElements(By.xpath("//*[@id='year']/option"));
		Iterator<WebElement> itr = days.iterator();
		while (itr.hasNext()) {
			WebElement click = itr.next();
			String text = click.getText().trim();
			if (text.equals(year)) {
				click.click();
				break;
			}
		}

	}


	
	
	
	

	



}

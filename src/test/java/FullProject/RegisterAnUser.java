package FullProject;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.CoursePage;
import pageObjects.HomePage;
import pageObjects.RegisterPage;
import resources.SetupConfiguration;

public class RegisterAnUser extends SetupConfiguration {

	public RegisterAnUser() throws IOException {
		super();
		actualResult = null;
		expectedResult = null;
		log = LogManager.getLogger(RegisterAnUser.class.getName());
	}

	@BeforeMethod
	public void setup() throws Exception {
		driver = initializeDriver();
		log.info("Driver Initialized");
		getToURL();
		log.info("Home Page Opened");
		// Asserts
		actualResult = driver.getTitle();
		expectedResult = "QA Click Academy | Selenium,Jmeter,SoapUI,Appium,Database testing,QA Training Academy";
		Assert.assertEquals(actualResult, expectedResult, "The title isn't the same");
		log.info("Title comparison done");
	}

	@Test(dataProvider = "getRegisterData")
	public void registerUser(String user, String email, String password) throws InterruptedException {
		// Go to register page
		homeObject = new HomePage(driver);
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfAllElements(HomePage.mainFrame));
		Assert.assertTrue(homeObject.titleVisibility());
		homeObject.clickOnRegisterButton();
		log.info("Sign In Link Clicked");
		// User data
		registerObject = new RegisterPage(driver);
		registerObject.setUsername(user);
		registerObject.setEmail(email);
		registerObject.setPassword(password);
		log.info("Required fields filled");
		// Required Conditions
		registerObject.clickOnCheckbox();
		log.info("Clicked on terms and conditions ckeckbox");
		registerObject.submitData();
		log.info("Clicked on Sign in submit button");
//		Thread.sleep(3000); 
		courseObject=new CoursePage(driver);
		String userIcon = courseObject.validatedUserRegistration();
		Assert.assertEquals(user, userIcon);
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
		log.info("Driver closed");
	}

	public WebDriver driver;
	public WebDriverWait wait;
	protected String actualResult;
	protected String expectedResult;
	public static Logger log;

	// Page Objects Statement
	protected HomePage homeObject;
	protected RegisterPage registerObject;
	protected CoursePage courseObject;
}

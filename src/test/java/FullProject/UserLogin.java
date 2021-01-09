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
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.RecoverPasswordPage;
import resources.SetupConfiguration;

public class UserLogin extends SetupConfiguration {

	public UserLogin() throws IOException {
		super();
		actualResult = null;
		expectedResult = null;
		log = LogManager.getLogger(UserLogin.class.getName());
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

	@Test(dataProvider = "getLoginData")
	public void loginUser(String email, String pass) throws InterruptedException {
		// Go to Login page
		homeObject = new HomePage(driver);
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfAllElements(HomePage.mainFrame));
		Assert.assertTrue(homeObject.titleVisibility());
		homeObject.clickOnLoginButton();
		log.info("Login Link Clicked");
		// Check Login Page title
		actualResult = driver.getTitle();
		expectedResult = "Rahul Shetty Academy";
		Assert.assertEquals(actualResult, expectedResult);
		log.info("Login page title comparison done");
		// Login Data
		loginObject = new LoginPage(driver);
		loginObject.setEmail(email);
		loginObject.setPassword(pass);
		log.info("Email and password fields filled");
		loginObject.clickOnLogin();
		log.info("Login submit clicked");
	}

	@Test
	public void forgotPassword() throws InterruptedException {
		// Go to Login page
		homeObject = new HomePage(driver);
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfAllElements(HomePage.mainFrame));
		Assert.assertTrue(homeObject.titleVisibility());
		homeObject.clickOnLoginButton();
		log.info("Login Link Clicked");
		// Check Login Page title
		actualResult = driver.getTitle();
		expectedResult = "Rahul Shetty Academy";
		Assert.assertEquals(actualResult, expectedResult);
		log.info("Login page title comparison done");
		//Login Data
		loginObject = new LoginPage(driver);
		loginObject.clickOnForgotPassword();
		log.info("Clicked on forgot password");
		// Forgot password 
		forgotPassword=new RecoverPasswordPage(driver);
		wait.until(ExpectedConditions.visibilityOfAllElements(RecoverPasswordPage.mainBar));
		Assert.assertTrue(forgotPassword.titleVisibility());
		forgotPassword.setEmail("testemail@hotmail.com");
		log.info("Email field filled");
		forgotPassword.submitData();
		log.info("Submit data clicked");
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
		log.info("driver closed");
	}

	public WebDriver driver;
	public WebDriverWait wait;
	public String actualResult;
	public String expectedResult;
	public static Logger log;

	// Page Objects Statement
	public HomePage homeObject;
	public LoginPage loginObject;
	public RecoverPasswordPage forgotPassword;
}

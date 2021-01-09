package stepDefinitions;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import FullProject.RegisterAnUser;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.junit.Cucumber;
import pageObjects.CoursePage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.RegisterPage;

@RunWith(Cucumber.class)

public class stepDefinition extends RegisterAnUser {

	public stepDefinition() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Given("^Initialize Chrome Browser$")
	public void initialize_Chrome_Browser() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		driver = initializeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		RegisterAnUser.log.info("Driver Initialized");
	}

	@Given("^Navigate to \"([^\"]*)\" site$")
	public void navigate_to_site(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		driver.get(arg1);
		RegisterAnUser.log.info("Landing Page Opened");
	}

	@Given("^Validate landing page title$")
	public void validate_landing_page_title() throws Throwable {
		// Asserts
		actualResult = driver.getTitle();
		expectedResult = "QA Click Academy | Selenium,Jmeter,SoapUI,Appium,Database testing,QA Training Academy";
		Assert.assertEquals(actualResult, expectedResult, "The title isn't the same");
		log.info("Title comparison done");
	}

	@When("^I clicked on \"([^\"]*)\" button$")
	public void i_clicked_on_something_button(String strArg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		homeObject = new HomePage(driver);
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfAllElements(HomePage.mainFrame));
		Assert.assertTrue(homeObject.titleVisibility());
		if (homeObject.popupVisibility()) {
			homeObject.clickOnPopupNoThanksMessage();
		}
		if (strArg1.equals("register")) {
			homeObject.clickOnRegisterButton();
			log.info("Sign In Link Clicked");
		}
		if (strArg1.equals("login")) {
			homeObject.clickOnLoginButton();
			log.info("Log In Link Clicked");
		}
	}

	@When("^Fill name (.+) email (.+) and password (.+)$")
	public void fill_name_email_and_password(String username, String email, String password) throws Throwable {
		registerObject = new RegisterPage(driver);
		registerObject.setUsername(username);
		registerObject.setEmail(email);
		registerObject.setPassword(password);
		log.info("Required fields filled");
	}

	@When("^Fill email (.+) and password (.+)$")
	public void fill_name_email_and_password(String email, String password) throws Throwable {
		LoginPage loginObject = new LoginPage(driver);
		loginObject.setEmail(email);
		loginObject.setPassword(password);
		log.info("Required fields filled");
	}

	@When("^Click on terms and conditions checkbox$")
	public void click_on_terms_and_conditions_checkbox() throws Throwable {
		registerObject.clickOnCheckbox();
		log.info("Clicked on terms and conditions ckeckbox");
	}

	@When("^Click on \"([^\"]*)\" button$")
	public void click_on_something_button(String strArg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		if (strArg1.equals("Sign In")) {
			registerObject.submitData();
			log.info("Clicked on Sign in submit button");
		}
		if (strArg1.equals("Login")) {
			LoginPage loginObject = new LoginPage(driver);
			loginObject.clickOnLogin();
			log.info("Clicked on Log in submit button");
		}

	}

	@Then("^Validated user registration (.+)$")
	public void validated_user_registration(String username) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		if(RegisterPage.alertMessage.isDisplayed()) {
			System.out.println("The user is already registered");
		}else {
			Thread.sleep(5000);
			courseObject = new CoursePage(driver);
			String userIcon = courseObject.validatedUserRegistration();
			Assert.assertEquals(username, userIcon);
		}

	}

	@Then("^close the browser$")
	public void close_the_browser() throws Throwable {
		driver.quit();
	}

}

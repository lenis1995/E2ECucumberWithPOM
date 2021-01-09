package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// -----------------------------------------------------------------------------------
	// Objects Statement

	@FindBy(tagName = "body")
	public static WebElement mainFrame;

	@FindBy(id = "user_name")
	private WebElement username;

	@FindBy(id = "user_email")
	private WebElement email;

	@FindBy(id = "user_password")
	private WebElement password;

	@FindBy(id = "user_password_confirmation")
	private WebElement confirmPass;

	@FindBy(id = "user_agreed_to_terms")
	private WebElement requiredCheckbox;

	@FindBy(name = "commit")
	private WebElement submit;

	@FindBy(css = "div.alert.alert-danger.alert-registration-page")
	public static WebElement alertMessage;

	// -----------------------------------------------------------------------------------
	// Methods statement

	public void setUsername(String user) {
		username.sendKeys(user);
	}

	public void setEmail(String mail) {
		email.sendKeys(mail);
	}

	public void setPassword(String pass) {
		password.sendKeys(pass);
		confirmPass.sendKeys(pass);
	}

	public void clickOnCheckbox() {
		requiredCheckbox.click();
	}

	public void submitData() {
		submit.submit();
	}

	private WebDriver driver;
}

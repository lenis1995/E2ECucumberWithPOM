package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// -----------------------------------------------------------------------------------
	// Objects Statement

	@FindBy(id = "user_email")
	private WebElement email;

	@FindBy(id = "user_password")
	private WebElement password;

	@FindBy(name = "commit")
	private WebElement loginButton;

	@FindBy(css = "a.link-below-button")
	private WebElement forgotPassword;

	public void setEmail(String mail) {
		email.sendKeys(mail);
	}

	public void setPassword(String pass) {
		password.sendKeys(pass);
	}

	public void clickOnLogin() {
		loginButton.submit();
	}

	public void clickOnForgotPassword() {
		forgotPassword.click();
	}
	private WebDriver driver;
}

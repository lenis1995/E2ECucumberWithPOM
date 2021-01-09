package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// -----------------------------------------------------------------------------------
	// Objects Statement

	@FindBy(linkText = "Register")
	private WebElement registerButton;
	
	@FindBy(linkText = "Login")
	private WebElement loginButton;

	@FindBy(id = "homepage")
	public static WebElement mainFrame;
	
	@FindBy(css="section#content>div>div>h2")
	private WebElement homeTitle;
	
	@FindBy(css="div.listbuilder-popup-scale")
	private WebElement popupMessage;
	
	@FindBy(xpath="//button[text()='NO THANKS']")
	private WebElement nothanksButton;

	// -----------------------------------------------------------------------------------
	// Methods statement
	public void clickOnRegisterButton() {
		registerButton.click();
	}
	public void clickOnLoginButton() {
		loginButton.click();
	}
	public boolean titleVisibility() {
		return homeTitle.isDisplayed();	
	}
	public boolean popupVisibility() {
		return popupMessage.isDisplayed();	
	}
	public void clickOnPopupNoThanksMessage(){
		nothanksButton.click();
	}

	private WebDriver driver;
}

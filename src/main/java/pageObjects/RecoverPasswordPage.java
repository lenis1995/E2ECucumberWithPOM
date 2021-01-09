package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RecoverPasswordPage {

	public RecoverPasswordPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// -----------------------------------------------------------------------------------
	// Objects Statement

	@FindBy(css = "div.content-box>h1")
	private WebElement resetTitle;

	@FindBy(id = "user_email")
	private WebElement email;

	@FindBy(css = "input[class='btn btn-primary btn-md']")
	private WebElement sendInstructions;

	@FindBy(css = "div.header-upper")
	public static WebElement mainBar;

	// -----------------------------------------------------------------------------------
	// Methods statement
	
	public boolean titleVisibility() {
		return resetTitle.isDisplayed();	
	}
	public void setEmail(String mail) {
		email.sendKeys(mail);
	}
	public void submitData() {
		sendInstructions.submit();
	}

	private WebDriver driver;
}

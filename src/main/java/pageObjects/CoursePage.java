package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CoursePage {

	public CoursePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// -----------------------------------------------------------------------------------
	// Objects Statement

	@FindBy(css = "span.navbar-current-user")
	public static WebElement userIcon;

	// -----------------------------------------------------------------------------------
	// Methods statement
	public String validatedUserRegistration() {
		return userIcon.getText();
	}
	
	private WebDriver driver;
}

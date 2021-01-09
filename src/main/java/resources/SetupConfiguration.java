package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.*;

public class SetupConfiguration {

	public SetupConfiguration() throws IOException {
		prop = new Properties();
		filePath = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\resources\\setting.properties");
		prop.load(filePath);
	}

	private void setProperties(String prop, String driverName) {
		System.setProperty("webdriver." + prop + ".driver",
				System.getProperty("user.dir") + "\\drivers\\" + driverName + ".exe");
	}

	public WebDriver initializeDriver() throws Exception {
//		String browserName = System.getProperty("browser"); // TO MAVEN PARAMETER EXECUTION
//		String headlessMode = System.getProperty("headless");
		String browserName = prop.getProperty("browser");   // TO PROPERTIES FILE
		driver = null;

		if (browserName.equals("chrome")) {
			setProperties("chrome", "chromedriver");
			chromeOptions = new ChromeOptions();
		  /*if (headlessMode.equalsIgnoreCase("ON")) {
				chromeOptions.addArguments("headless");
				driver = new ChromeDriver(chromeOptions);
			} else {
				driver = new ChromeDriver();
			} */
			driver = new ChromeDriver();
		} else if (browserName.equals("firefox")) {
			setProperties("gecko", "geckodriver");
			firefoxOptions = new FirefoxOptions();
			/*if (headlessMode.equalsIgnoreCase("ON")) {
				firefoxOptions.addArguments("headless");
			    driver = new FirefoxDriver(firefoxOptions);
			} else {
				driver = new FirefoxDriver();
			}*/

			driver = new FirefoxDriver();
		} else if (browserName.equals("ie")) {
			setProperties("ie", "IEDriverServer");
			driver = new InternetExplorerDriver();
		} else {
			throw new Exception("Incorrect Browser");
		}

		return driver;
	}

	public void getToURL() {
		String URL = prop.getProperty("URL");

		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(URL);
	}

	@DataProvider(name = "getRegisterData")
	public Object[][] getRegisterData() {
		Object[][] data = new Object[2][3];

		// Row 0
		data[0][0] = "Juan Jose";
		data[0][1] = "tussiRagnark@gmail.com";
		data[0][2] = "Tussi123";

		// Row 1
		data[1][0] = "Sebas Carta";
		data[1][1] = "zackDBal@gmail.com";
		data[1][2] = "ZackD123";

		return data;
	}

	private Object[][] removeCol(Object[][] array, int colRemove) {

		int row = array.length;
		int col = array[0].length;

		Object[][] newArray = new Object[row][col - 1]; // new Array will have one column less

		for (int i = 0; i < row; i++) {
			for (int j = 0, currColumn = 0; j < col; j++) {
				if (j != colRemove) {
					newArray[i][currColumn++] = array[i][j];
				}
			}
		}

		return newArray;
	}

	@DataProvider(name = "getLoginData")
	public Object[][] getLoginData() {
		return removeCol(getRegisterData(), 0);
	}

	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String screenPath = System.getProperty("user.dir") + "\\reports\\screenshots\\" + testCaseName + ".png";
		FileUtils.copyFile(src, new File(screenPath));

		return screenPath;
	}

	private Properties prop;
	private FileInputStream filePath;
	protected WebDriver driver;
	// Driver Options
	private FirefoxOptions firefoxOptions;
	private ChromeOptions chromeOptions;
}

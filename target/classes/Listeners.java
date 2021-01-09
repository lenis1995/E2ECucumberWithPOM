package resources;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.*;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Listeners extends SetupConfiguration implements ITestListener {

	public Listeners() throws IOException {
		super();
		extent = ExtentReporterNG.getExtentReporter();
		extentTest=new ThreadLocal<ExtentTest>();
		// TODO Auto-generated constructor stub
	}

	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		String testName = result.getMethod().getMethodName();
		test = extent.createTest(testName);
		extentTest.set(test);
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		extentTest.get().log(Status.PASS, "Test Successfully Passed");
	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		count = 1;
		String failedMethod = result.getMethod().getMethodName();
		String screenshotName = failedMethod + " " + count;
		WebDriver driver = null;
		extentTest.get().fail(result.getThrowable());
//		test.fail(result.getThrowable().getCause());
		extentTest.get().fail(result.getThrowable().getLocalizedMessage());
//		test.fail(result.getThrowable().getMessage());
		extentTest.get().error(result.getInstanceName());
//		test.error(result.getTestName().toUpperCase());
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver")
					.get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			String testName=result.getMethod().getMethodName();
			String screenPath=getScreenshot(screenshotName, driver);
			extentTest.get().addScreenCaptureFromPath(screenPath,testName + ": Current Failure");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		count++;
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extent.flush();
	}

	private ThreadLocal<ExtentTest> extentTest;
	private ExtentReports extent;
	private ExtentTest test;
	private int count;
}

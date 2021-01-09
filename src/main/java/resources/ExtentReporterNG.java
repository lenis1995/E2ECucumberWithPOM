package resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

	public static ExtentReports getExtentReporter() {

		// ExtentReports , ExtentSparkReporter

		// REPORTER STATEMENT
		String path = System.getProperty("user.dir") + "\\reports\\extent-reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Test Results");
		// REPORT
		report = new ExtentReports();
		report.attachReporter(reporter);
		report.setSystemInfo("Alejandro Lenis", "Tester");
		
		return report;
	}

	public static ExtentReports report;
}

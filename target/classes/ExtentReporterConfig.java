package resource;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporterConfig {
	
	static String BaseDir = System.getProperty("user.dir") + "//Reports//index.html";
	static ExtentSparkReporter reporter = new ExtentSparkReporter(BaseDir);
	static ExtentReports extent = new ExtentReports();

	public static ExtentReports config() {
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Test Results");
		reporter.config().setTheme(Theme.DARK);
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Abhay Gangwar");
		extent.setSystemInfo("Environment", "REPLICA");
		return extent;	
	}
}

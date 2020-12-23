package enterprise.mobility;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import resource.Cls_Base;
import resource.ExtentReporterConfig;


public final class Listeners extends Cls_Base implements ITestListener {
	
	ExtentReports extent = ExtentReporterConfig.config();
	ExtentTest test;
	ThreadLocal<ExtentTest> extentThread = new ThreadLocal<ExtentTest>();
	
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		test = extent.createTest(result.getMethod().getMethodName());
		extentThread.set(test);
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		extentThread.get().log(Status.PASS, "Test case execution passed");

	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		extentThread.get().fail(result.getThrowable());
		WebDriver driver = null;
		String FailedMethodName = result.getMethod().getMethodName();
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			getScreenshotMethod(FailedMethodName, driver, test);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {

	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extent.flush();
	}
	
	public void logCAFNumber(String CAF)
	{
		extentThread.get().log(Status.INFO, "CAF number for current order is: "+CAF);
	}

}

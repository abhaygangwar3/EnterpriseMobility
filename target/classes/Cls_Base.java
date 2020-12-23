package resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.aventstack.extentreports.ExtentTest;



public class Cls_Base {
	public WebDriver driver;
	public Properties prop;
	public String Path = System.getProperty("user.dir")+"//src//main//java//resource//InputData.properties";
	public WebDriver BrowserInitialise() throws IOException
	{
//		This is to fetch the input data from properties file.
		prop =  PropertyFileReader();
//		String BrowserName = System.getProperty("browser");
		String BrowserName = prop.getProperty("browser");
		if(BrowserName.contains("chrome"))
		{
//			Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe /t");
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\webdriver_path\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			if(BrowserName.contains("headless"))
				options.setHeadless(true);
			driver = new ChromeDriver(options);
		}
		else if(BrowserName.equalsIgnoreCase("firefox"))
		{
//			Runtime.getRuntime().exec("taskkill /F /IM geckodriver.exe");
			System.setProperty("webdriver.firefox.bin","C:\\Users\\abhay.gangwar\\AppData\\Local\\Mozilla Firefox\\firefox.exe");
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\webdriver_path\\geckodriver.exe");    
			driver = new FirefoxDriver();
		}
		else if(BrowserName.equalsIgnoreCase("ie"))
		{
//			Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer.exe");
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"\\webdriver_path\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		return driver;
	}
	
	public Properties PropertyFileReader() throws IOException
	{
		prop = new Properties();
		FileInputStream fis = new FileInputStream(Path);
		prop.load(fis);
		return prop;
	}
	
	public void PropertyFileWriter(Properties prop) throws IOException
	{
		FileOutputStream fos = new FileOutputStream(Path);
		prop.store(fos, "Order ID and CAF Number updated");
	}
	
	public void getScreenshotMethod(String failedMethodName, WebDriver driver, ExtentTest test) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destPath = System.getProperty("user.dir")+"//Reports//"+failedMethodName+".png";
		FileUtils.copyFile(source, new File(destPath));
		test.addScreenCaptureFromPath(destPath, failedMethodName);
	}
}

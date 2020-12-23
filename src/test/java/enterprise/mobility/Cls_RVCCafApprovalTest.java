package enterprise.mobility;

import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.CAFProductSelection;
import pageObjects.FioriLandingPage;
import pageObjects.OIMAuthenticationPage;
import resource.Cls_Base;

public class Cls_RVCCafApprovalTest extends Cls_Base{
	
	public static Logger log = (Logger) LogManager.getLogger(Cls_RVCCafApprovalTest.class.getName());
	WebDriver driver;
	CAFProductSelection cp;
	Properties prop;
	FioriLandingPage lp;
	
	@BeforeTest
	public void testInit() throws IOException
	{
		driver = BrowserInitialise();
		log.debug("Browser initialised");
		prop = PropertyFileReader();
		driver.get(prop.getProperty("url"));
		log.debug("OIM page opened");
	}
	
	@Test(priority = 1)
	public void OIMAuthentication()
	{
		OIMAuthenticationPage oim = new OIMAuthenticationPage(driver);
		oim.getUsername().sendKeys(prop.getProperty("fioriUsername"));
		log.info("Username entered");
		oim.getPassword().sendKeys(prop.getProperty("fioriPassword"));
		log.info("Password entered");
		lp = oim.submitCredentials();
		log.info("Credentials submitted");
	}
	
	@Test(priority = 2)
	public void CAFOptionSearch() throws IOException, InterruptedException
	{
		log.debug("SAP Fiori opened");
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(lp.getSearchButton()));
		lp.getSearchButton().click();
		lp.getSearchBox().sendKeys("CAF");
		Thread.sleep(2000);
		cp = lp.getFirstOption();
		log.debug("CAF option selected and opened");
	}
}

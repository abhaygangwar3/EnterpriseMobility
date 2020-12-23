package enterprise.mobility;

import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.AuthorisedSignatorySelectionPage;
import pageObjects.CAFDisplayAdditionalInfoPage;
import pageObjects.CAFDisplayDetailsPage;
import pageObjects.CAFLocationSelectPage;
import pageObjects.CAFProductSelection;
import pageObjects.CommercialDetailsPage;
import pageObjects.FileUploadsPage;
import pageObjects.FioriLandingPage;
import pageObjects.OIMAuthenticationPage;
import resource.Cls_Base;

public class Cls_OrderPlacementAtDataEntryStageTest extends Cls_Base {
	public static Logger log = (Logger) LogManager.getLogger(Cls_OrderPlacementAtDataEntryStageTest.class.getName());
	WebDriver driver;
	CAFProductSelection cp;
	Properties prop;
	FioriLandingPage lp;
	CAFLocationSelectPage cl;
	AuthorisedSignatorySelectionPage as;
	CommercialDetailsPage cdp;
	CAFDisplayAdditionalInfoPage cdai;
	CAFDisplayDetailsPage cddp;
	FileUploadsPage fup;
	
	public static String cafNumber;
	public static String orderID;
	
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
	
	@Test(priority = 3)
	public void SelectingCAFType()
	{
		List<WebElement> allOptions = cp.getAvailableCAFOptions();
		for(WebElement opt:allOptions)
		{
			if(opt.getText().equalsIgnoreCase("Mobility"))
				opt.click();
		}
		
	}
	
	@Test(priority = 4)
	public void LocationIDSearch() throws InterruptedException
	{
		cl = cp.getOkOption();
		log.info("Mobility product selected");
		List<WebElement> activationTypes = cl.getAvailableActivationTypes();
		Thread.sleep(1000);
		cl.getActTypeDropDownButton().click();
		Thread.sleep(1000);
		for(WebElement act:activationTypes)
		{
			if(act.getText().equalsIgnoreCase(prop.getProperty("ActivationType")))
			{
				act.click();
				break;
			}
		}
		log.debug("Activation type selected");
//		String locID = System.getProperty("LocationID");
		cl.getLocationIDSearchBox().sendKeys(prop.getProperty("LocationID"));
//		cl.getLocationIDSearchBox().sendKeys(locID);
		cl.searchLocation().click();
		log.debug("Location searched");
		try
		{
			as = cl.firstLocationSearched();
		}
		catch(Exception e)
		{
			log.error("Location not fetched");
		}
		log.info("Location selected");
		cdp = as.getFirstAvailableASPresent();
		cdp.getNewCommercialDetailsOption().click();
		Random rand = new Random();
		String cfNumber = "CF"+Integer.toString(rand.nextInt(1000000000));
		log.debug("CF Number entered");
		cdp.getCFTextBox().sendKeys(cfNumber);
		cdp.setValidFromCalendarDate();
		cdp.setValidToCalendarDate();
		cdp.getNumberOfConnections().sendKeys("10");
		cdp.getNumberOfConnections().sendKeys(Keys.TAB);
		cdp.getNumberOfConnections().sendKeys(Keys.TAB);
//		List<WebElement> offerIDs = cdp.getAvailableOffers();
//		for(WebElement offer:offerIDs)
//		{
//			if(offer.getText().contains(prop.getProperty("OfferID")))
//			{
//				offer.click();
//				break;
//			}
//		}
		String offerCode = prop.getProperty("OfferID");
		Thread.sleep(1000);
		cdp.getOfferTextBox().click();
		Thread.sleep(2000);
		cdp.getOfferTextBox().sendKeys(offerCode);
		Thread.sleep(3000);
		cdp.getOffer().click();
		cdp.getOKSelected().click();
		cddp = new CAFDisplayDetailsPage(driver);
//		WebDriverWait wait1 = new WebDriverWait(driver, 20);
//		wait1.until(ExpectedConditions.invisibilityOf(cddp.processor()));
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(cddp.getPageTitle()));
		Assert.assertTrue(cddp.getPageTitle().getText().equalsIgnoreCase("CAF Display"));
	}
	
	@Test(priority = 5, dependsOnMethods = {"LocationIDSearch"})
	public void CAFEntry() throws Exception
	{
		Random rand = new Random();
		Thread.sleep(6000);
		String circle = cddp.getLocationCircle();
		cafNumber = circle+Integer.toString(rand.nextInt(100000000));
		prop.setProperty("CAFNumber", cafNumber);
		cddp.getCAFNumberPlaceHolder().sendKeys(cafNumber);
		Thread.sleep(1000);
		cddp.getCAFNumberPlaceHolder().sendKeys(Keys.TAB);
		log.info(cafNumber+" entered for this order");
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(cddp.getRefreshButton()));
		Thread.sleep(2000);
		cddp.setPOSAgentTab();
		cddp.selectBillingLocationID();
		cddp.selectSummaryLocationID();
		log.debug("All relevant details entered in details tab");
		cdai = cddp.selectAdditionalInfoTab();
		Thread.sleep(2000);
		if(cdai.validateDetailsIfAlreadyExisting().length() == 0)
		{
			cdai.getASFatherFName().sendKeys("Abhay");
			cdai.getASFatherLName().sendKeys("Gangwar");
			cdai.setRelationship();
			cdai.setGender();
			cdai.getDOB().sendKeys("19.08.1993");
			cdai.getLatitudeBox().sendKeys("19.102769");
			cdai.getLongitudeBox().sendKeys("73.009001");
			cdai.setASDate();
			cdai.setTime();
		}
		fup = cdai.FileUploadTab();
		fup.getCustomerApplicationForm().click();
		Thread.sleep(3000);
		Runtime.getRuntime().exec("C:\\Users\\abhay.gangwar\\.jenkins\\mobility\\AutoIT\\exe files\\CustomerApplicationForm.exe");
		WebDriverWait w = new WebDriverWait(driver, 50);
		w.until(ExpectedConditions.elementToBeClickable(fup.getcommercialFormUpload()));
		fup.getcommercialFormUpload().click();
		Thread.sleep(3000);
		Runtime.getRuntime().exec("C:\\Users\\abhay.gangwar\\.jenkins\\mobility\\AutoIT\\exe files\\CommercialForm.exe");
		Thread.sleep(15000);
		fup.getpurchaseOrderUpload().click();
		Thread.sleep(3000);
		Runtime.getRuntime().exec("C:\\Users\\abhay.gangwar\\.jenkins\\mobility\\AutoIT\\exe files\\PurchaseOrder.exe");
		Thread.sleep(15000);
		fup.getNormalUOLUpload().click();
		Thread.sleep(3000);
		Runtime.getRuntime().exec("C:\\Users\\abhay.gangwar\\.jenkins\\mobility\\AutoIT\\exe files\\NormalUOL.exe");
		Thread.sleep(15000);
		try
		{
			if(fup.getErrorPopUpUOLUpload().isDisplayed())
			{
				log.error(fup.getErrorPopUpUOLUpload().getText());
				Assert.assertTrue(false);
				return;
			}
		}
		catch(Exception e)
		{
			fup.getCheckList().click();
			Thread.sleep(3000);
			fup.getcheckListSubmitButton().click();
			Thread.sleep(2000);
			fup.getOrderSubmitButton().click();
			WebDriverWait ow = new WebDriverWait(driver, 50);
			ow.until(ExpectedConditions.visibilityOf(fup.getOrderConfirmationPopUp()));
			String oc = fup.getOrderConfirmationPopUp().getText();
			String[] orderIDString = oc.split(":");
			orderID = orderIDString[1];
			prop.setProperty("OrderID", orderID);
			PropertyFileWriter(prop);
			Listeners listen = new Listeners();
			listen.logCAFNumber(cafNumber);
//			Cls_XL_Ops.XL_Output_WithSheetNameNextRow("Output", 1, cafNumber);
//			Cls_XL_Ops.XL_Output_WithSheetNameNextRow("Output", 2, orderID);
//			String timeStamp = new SimpleDateFormat("dd-MM-yy").format(Calendar.getInstance().getTime());
//			Cls_XL_Ops.XL_Output_WithSheetNameNextRow("Output", 3, timeStamp);
			
		}
//		WebDriverWait wait1 = new WebDriverWait(driver, 20);
//		wait1.until(ExpectedConditions.visibilityOf(fup.getCheckList()));
//		fup.getCheckList().click();
//		Thread.sleep(10000);
		
	}
	
	@AfterTest
	public void testTerminate()
	{
		driver.quit();
		log.info("Driver closed");
	}
	
	
}

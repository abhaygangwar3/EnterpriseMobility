package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CAFDisplayDetailsPage {
	
	WebDriver driver;
	public CAFDisplayDetailsPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "div#__page0-title span")
	private WebElement pageTitle;
	@FindBy(css = "input#__xmlview2--idCAFNumber-inner")
	private WebElement CAFNumberPlaveHolder;
	@FindBy(css = "span#__xmlview2--idInvoiceoffId-arrow")
	private WebElement billingInvoiceLocationDropDown;
	@FindBy(xpath = ".//ul[@id='__list20']/li[2]")
	private WebElement billingInvoiceLocationSelect;
	@FindBy(css = "span#__xmlview2--cbSummLocId-arrow")
	private WebElement summaryLocationIdDropDown;
	@FindBy(xpath = ".//ul[@id='__list21']/li[2]")
	private WebElement summaryLocationIdSelect;
	@FindBy(css = "div#__xmlview2--idCafRefresh-inner")
	private WebElement refreshButton;
	@FindBy(css = "div#__xmlview2--idAddinfo-text")
	private WebElement additionalInfoTabSelect;
	@FindBy(css = "input#__xmlview2--rsiteid1-inner")
	private WebElement POSAgentTab;
	@FindBy(css = "input#iduser-inner")
	private WebElement POSIDDialogueBox;
	@FindBy(css = "span#searchid-content")
	private WebElement POSIDSearch;
	@FindBy(css = "tbody#idLocationablepos-tblBody tr td span.sapMText")
	private WebElement POSIDSelect;
	
	public WebElement getPageTitle()
	{
		return pageTitle;
	}
	public WebElement getCAFNumberPlaceHolder() 
	{
		return CAFNumberPlaveHolder;
	}
	public void selectBillingLocationID()
	{
		billingInvoiceLocationDropDown.click();
		billingInvoiceLocationSelect.click();
	}
	public void selectSummaryLocationID()
	{
		summaryLocationIdDropDown.click();
		summaryLocationIdSelect.click();
	}
	public String getLocationCircle()
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String script = "return document.getElementById(\"__xmlview2--idCLCircleLc-inner\").value;";
		String CircleString = (String) js.executeScript(script);
		String[] cir = CircleString.split("/");
		return cir[0];
	}
	public WebElement getRefreshButton()
	{
		return refreshButton;
	}
	public CAFDisplayAdditionalInfoPage selectAdditionalInfoTab()
	{
		additionalInfoTabSelect.click();
		return new CAFDisplayAdditionalInfoPage(driver);
	}
	public void setPOSAgentTab() throws InterruptedException
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String script = "return document.getElementById(\"__xmlview2--rsiteid1-inner\").value;";
		String posAgent = (String) js.executeScript(script);
		if(posAgent.isEmpty())
		{
			POSAgentTab.click();
			Thread.sleep(2000);
			POSIDDialogueBox.sendKeys("37300159");
			POSIDSearch.click();
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOf(POSIDSelect));
			Thread.sleep(2000);
			POSIDSelect.click();
			Thread.sleep(2000);
		}
	}
	
}

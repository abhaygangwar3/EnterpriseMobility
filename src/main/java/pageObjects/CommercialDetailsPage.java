package pageObjects;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CommercialDetailsPage {

	WebDriver driver;

	public CommercialDetailsPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = ".//div[text()='New Commercial Details']")
	private WebElement newCommercialDetailsOption;
	@FindBy(css = "input#__xmlview1--idNewPoOrder-inner")
	private WebElement cfTextBox;
	@FindBy(css = "span#__xmlview1--IdNewValidFromCO-icon")
	private WebElement validFromCalendarOption;
	@FindBy(css = "div[class*='sapUiCalDayToday'][id*='ValidFrom']")
	private WebElement todayDateValidFrom;
	@FindBy(css = "input#__xmlview1--IdNewValidToCO-inner")
	private WebElement validToCalendarTextBoxOption;
	@FindBy(css = "input#__xmlview1--IdNewValidFromCO-inner")
	private WebElement validFromDate;
	@FindBy(css = "input#__xmlview1--idNewNumberofConnectionCO-inner")
	private WebElement numberOfConnectionsTextBox;
	@FindBy(css = "input#__xmlview1--IdOfferIdCOnew-inner")
	private WebElement offerTextBox;
	@FindBy(css = "ul#__list8-listUl li.sapMLIBTypeActive")
	private WebElement offerCodeSelect;
	@FindBy(css = "div#__xmlview1--idokNewComDetails-inner")
	private WebElement Ok;

	public WebElement getNewCommercialDetailsOption() {
		return newCommercialDetailsOption;
	}

	public WebElement getCFTextBox() {
		return cfTextBox;
	}

	public void setValidFromCalendarDate() {
		validFromCalendarOption.click();
		todayDateValidFrom.click();
	}

	public void setValidToCalendarDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		Calendar c = Calendar.getInstance();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String script = "return document.getElementById(\"__xmlview1--IdNewValidFromCO-inner\").value;";
		String oldDate = (String) js.executeScript(script);
		try {
			// Setting the date to the given date
			c.setTime(sdf.parse(oldDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.add(Calendar.DATE, 10);
		String validToDate = sdf.format(c.getTime());
		validToCalendarTextBoxOption.sendKeys(validToDate);
	}
	
	public WebElement getNumberOfConnections()
	{
		return numberOfConnectionsTextBox;
	}
	
	public WebElement getOfferTextBox()
	{
		return offerTextBox;
	}
	
	public WebElement getOffer()
	{
		return offerCodeSelect;
	}
	
	public WebElement getOKSelected()
	{
		return Ok;
	}

}

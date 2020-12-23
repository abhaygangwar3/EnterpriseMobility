package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CAFLocationSelectPage {
	
	WebDriver driver;
	public CAFLocationSelectPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "ul[id='__list2'] li")
	private List<WebElement> activationTypeOptions;
	@FindBy(css = "input#__xmlview1--idFGHQLocationIdCAF-inner")
	private WebElement locationIDSearchBox;
	@FindBy(xpath = ".//span[text()='Search']")
	private WebElement searchButton;
	@FindBy(css = "tbody#__xmlview1--idLocationTableCAF-tblBody tr")
	private WebElement firstLocationSelection;
	@FindBy(css = "td#__item20-__xmlview1--idLocationTableCAF-0_cell3 span")
	private WebElement locationSearchResult;
	@FindBy(xpath = ".//span[@id='__xmlview1--idActType-arrow']")
	private WebElement actTypeDropDown;
	
	public WebElement getActTypeDropDownButton()
	{
		return actTypeDropDown;
	}
	public List<WebElement> getAvailableActivationTypes()
	{
		return activationTypeOptions;
	}
	public WebElement getLocationIDSearchBox() 
	{
		return locationIDSearchBox;
	}
	public WebElement searchLocation() 
	{
		return searchButton;
	}
	public AuthorisedSignatorySelectionPage firstLocationSearched() throws InterruptedException
	{
		Thread.sleep(3000);
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(locationSearchResult));
		locationSearchResult.click();
		Thread.sleep(3000);
		return new AuthorisedSignatorySelectionPage(driver);
	}

}

package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FioriLandingPage{
	WebDriver driver;
	
	@FindBy(css = "a[title='Search']")
	private WebElement searchButton;
	@FindBy(css = "input#searchInputShell-inner")
	private WebElement searchBox;
	@FindBy(xpath = ".//tbody[@id='searchInputShell-popup-table-tblBody']/tr[1]")
	private WebElement firstSearchOption;
	
	public FioriLandingPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	public WebElement getSearchButton()
	{
		return searchButton;
	}
	public WebElement getSearchBox()
	{
		return searchBox;
	}
	public CAFProductSelection getFirstOption()
	{
		firstSearchOption.click();
		return new CAFProductSelection(driver);
	}
}

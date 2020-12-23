package pageObjects;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class CAFDisplayAdditionalInfoPage {
	
	WebDriver driver;
	public CAFDisplayAdditionalInfoPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "input#__xmlview2--idfatherFName-inner")
	private WebElement ASFatherFName;
	@FindBy(css = "input#__xmlview2--idfatherLName-inner")
	private WebElement ASFatherLName;
	@FindBy(css = "button#__xmlview2--idRelType-arrow")
	private WebElement RelTypeDropDown;
	@FindBy(xpath = ".//ul[@id='__list22']/li")
	private WebElement RelTypeSelectFirstOption;
	@FindBy(css = "button#__xmlview2--idASGender-arrow")
	private WebElement GenderDropDown;
	@FindBy(xpath = ".//ul[@id='__list23']/li")
	private WebElement GenderSelectFirstOption;
	@FindBy(css = "input#__xmlview2--IdDOB-inner")
	private WebElement DOBBox;
	@FindBy(css = "button#__xmlview2--idNationality-arrow")
	private WebElement NationalityDropDown;
	@FindBy(xpath = ".//ul[@id='__list24']/li")
	private List<WebElement> NationalityOptions;
	@FindBy(css = "input#__xmlview2--idLatitude-inner")
	private WebElement latitudeBox;
	@FindBy(css = "input#__xmlview2--idLongitude-inner")
	private WebElement longitudeBox;
	@FindBy(css = "span#__xmlview2--IdDate-icon")
	private WebElement ASDateDD;
	@FindBy(css = "div#__xmlview2--IdDate-cal div[class*='sapUiCalDayToday']")
	private WebElement ASDateOK;
	@FindBy(css = "input#__xmlview2--idTime-inner")
	private WebElement timeDD;
	@FindBy(xpath = ".//a[@class='dwb dwb0 dwb-e']")
	private WebElement timeSelect;
	@FindBy(css = "div#__xmlview2--tbdownload-text")
	private WebElement fileUploads;
	
	
	public WebElement getASFatherFName()
	{
		return ASFatherFName;
	}
	public String validateDetailsIfAlreadyExisting() 
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String script = "return document.getElementById(\"__xmlview2--idfatherFName-inner\").value;";
		String ASFatherName = (String) js.executeScript(script);
		return ASFatherName;
	}
	public WebElement getASFatherLName()
	{
		return ASFatherLName;
	}
	public void setRelationship()
	{
		RelTypeDropDown.click();
		RelTypeSelectFirstOption.click();
	}
	public void setGender()
	{
		GenderDropDown.click();
		GenderSelectFirstOption.click();
	}
	public WebElement getDOB()
	{
		return DOBBox;
	}
	public WebElement getLatitudeBox()
	{
		return latitudeBox;
	}
	public WebElement getLongitudeBox()
	{
		return longitudeBox;
	}
	public void setASDate()
	{
		ASDateDD.click();
		ASDateOK.click();
	}
	public void setTime() throws InterruptedException
	{
		timeDD.click();
		Thread.sleep(1000);
		timeSelect.click();
	}
	public FileUploadsPage FileUploadTab()
	{
		fileUploads.click();
		return new FileUploadsPage(driver);
	}
	

}

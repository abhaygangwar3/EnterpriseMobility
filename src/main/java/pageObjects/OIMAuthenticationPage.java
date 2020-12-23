package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class OIMAuthenticationPage {
	
	WebDriver driver;
	public OIMAuthenticationPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "input[name='username']")
	private WebElement userNameBox;
	@FindBy(css = "input[name='password']")
	private WebElement passwordBox;
	@FindBy(css = "input[type='submit']")
	private WebElement submitOption;
	
	public WebElement getUsername()
	{
		return userNameBox;
	}
	public WebElement getPassword() 
	{
		return passwordBox;
	}
	public FioriLandingPage submitCredentials()
	{
		submitOption.click();
		return new FioriLandingPage(driver);
	}

}

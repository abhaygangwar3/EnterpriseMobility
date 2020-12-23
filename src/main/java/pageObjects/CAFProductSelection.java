package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CAFProductSelection {
	
	WebDriver driver;
	public CAFProductSelection(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = ".//div[@id='Rdg1']/div/div")
	private List<WebElement> cafOptions;
	@FindBy(xpath = ".//span[text()='Ok']")
	private WebElement ok;
	
	public List<WebElement> getAvailableCAFOptions()
	{
		return cafOptions;
	}
	public CAFLocationSelectPage getOkOption() 
	{
		ok.click();
		return new CAFLocationSelectPage(driver);
	}

}

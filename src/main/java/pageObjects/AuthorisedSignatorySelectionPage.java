package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AuthorisedSignatorySelectionPage {
	
	WebDriver driver;
	public AuthorisedSignatorySelectionPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "tbody#__xmlview1--idAUTTable-tblBody tr")
	private WebElement firstAvailableAS;

	
	public CommercialDetailsPage getFirstAvailableASPresent() throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(firstAvailableAS));
		firstAvailableAS.click();
		Thread.sleep(3000);
		return new CommercialDetailsPage(driver);
	}

}

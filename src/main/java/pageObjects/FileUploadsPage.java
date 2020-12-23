package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class FileUploadsPage {
	
	WebDriver driver;
	public FileUploadsPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "div#__xmlview2--idCafform")
	private WebElement customerApplicationFormUpload;
	@FindBy(css = "div#__xmlview2--idCommericialform")
	private WebElement commercialFormUpload;
	@FindBy(css = "div#__xmlview2--idPo")
	private WebElement purchaseOrderUpload;
	@FindBy(css = "div#__xmlview2--idUserOrderList")
	private WebElement normalUOLUpload;
	@FindBy(css = "span[class*='sapMMsgBoxText']")
	private WebElement errorPopUpUOLUpload;
	@FindBy(css = "div[class*='sapMMessageToast sapUiSelectable']")
	private WebElement FileUploadSuccessMessage;
	@FindBy(css ="span#__xmlview2--idRefType-arrow")
	private WebElement paymentRefTypeDD;
	@FindBy(xpath = ".//ul[@id='__list29']/li[text()='Cash']")
	private WebElement cashPayment;
	@FindBy(css = "button#__xmlview2--checkListId")
	private WebElement highlightedCheckListButton;
	@FindBy(css = "button#uploadId1")
	private WebElement checkListSubmitButton;
	@FindBy(css = "button#__xmlview2--btnSaveCAF")
	private WebElement orderSubmitButton;
	@FindBy(css = "section.sapMDialogSection div div span")
	private WebElement orderCompletionPopUp;
	
	public WebElement getCustomerApplicationForm()
	{
		return customerApplicationFormUpload;
	}
	public WebElement getcommercialFormUpload() 
	{
		return commercialFormUpload;
	}
	public WebElement getpurchaseOrderUpload() 
	{
		return purchaseOrderUpload;
	}
	public WebElement getNormalUOLUpload() 
	{
		return normalUOLUpload;
	}
	public WebElement getErrorPopUpUOLUpload()
	{
		return errorPopUpUOLUpload;
	}
	public WebElement getSuccessUploadMessage()
	{
		return FileUploadSuccessMessage;
	}
	public void setPaymentReferenceTypeDD() throws InterruptedException
	{
		paymentRefTypeDD.click();
		Thread.sleep(2000);
		cashPayment.click();
	}
	public WebElement getCheckList()
	{
		return highlightedCheckListButton;
	}
	public WebElement getcheckListSubmitButton() {
		return checkListSubmitButton;
	}
	public WebElement getOrderSubmitButton() {
		return orderSubmitButton;
	}
	public WebElement getOrderConfirmationPopUp()
	{
		return orderCompletionPopUp;
	}

}

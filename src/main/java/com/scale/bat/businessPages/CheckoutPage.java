package com.scale.bat.businessPages;

import static org.junit.Assert.assertTrue;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.scale.bat.framework.utility.Actions;
import com.scale.bat.framework.utility.ConfigurationReader;
import com.scale.bat.framework.utility.Log;
import cucumber.api.Scenario;

public class CheckoutPage extends Actions{
	private Logger log = Log.getLogger(CheckoutPage.class);

	private WebDriver driver;
	private String checkoutLabel = "Checkout";
	private String confirmationLabel = "Confirmation";
	private String paymentLabel = "Payment by invoice";
	private String deliveryLabel = "Confirm delivery method";
	private String defaultDeliveryText= "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sic, et quidem diligentius saepiusque ista loquemur inter nos agemusque communiter.";
	private String defaultPaymentText= "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sic, et quidem diligentius saepiusque ista loquemur inter nos agemusque communiter.";
	private ConfigurationReader configReaderObj;
	
	public CheckoutPage(WebDriver driver, Scenario scenario) {
		this.driver = driver;
		this.scenario = scenario;
		PageFactory.initElements(driver, this);
		this.wait = new WebDriverWait(this.driver, 30);
		configReaderObj = new ConfigurationReader();
	}
	
	
	@FindBy(xpath = "//*[@id='main-content']/form/div/div[2]/button/text()")
	private WebElement nextButton_Address;
	
	@FindBy(xpath = "//*[@id='main-content']/form/div/div[2]/button")
	private WebElement nextButton_Delivery;
		
	@FindBy(xpath = "//*[@id='main-content']/form/div/div[1]/div[2]/input")
	private WebElement Po_Payement;
	
	@FindBy(xpath = "//*[@id='tos-agreement']")
	private WebElement termsAndConditions;
	
	@FindBy(xpath = "//*[@id='main-content']/div[4]/div/form/div[2]/button")
	private WebElement placeOrder;
	
	@FindBy(xpath = "//h1[text()='Checkout']")
	private WebElement checkOutHeading;
	
	@FindBy(xpath = "//h2[text()='Payment by invoice']")
	private WebElement paymentByInvoice;
	
	@FindBy(xpath = "//p[text()='Currently the option available for payments is offline via invoice.']")
	private WebElement paymentByInvoiceMessage;
	
	@FindBy(xpath = "//p[@class='govuk-heading-m']")
	private WebElement checkOutpaymentSupplierName;
	
	@FindBy(xpath = "//p[@class='govuk-body'][2]")
	private WebElement checkOutpaymentTotalValue;
	
	@FindBy(xpath = "//label[@class='govuk-label govuk-label--s']/following-sibling::input")
	private WebElement checkOutpaymentPOTextbox;
	
	@FindBy(xpath = "///h2[text()='Order summary']")
	private WebElement checkOutpaymentOrderSummaryHeading;
	
	@FindBy(xpath = "//table[@class='govuk-table bat-prices-summary__price-table']/tbody/tr[1]/td[2]")
	private WebElement checkOutpaymentProductsTotal;
	
	@FindBy(xpath = "//table[@class='govuk-table bat-prices-summary__price-table']/tbody/tr[2]/td[2]")
	private WebElement checkOutpaymentDeliveryTotal;
	
	@FindBy(xpath = "//table[@class='govuk-table bat-prices-summary__price-table']/tbody/tr[3]/td[2]")
	private WebElement checkOutpaymentVAT;
	
	@FindBy(xpath = "//table[@class='govuk-table bat-prices-summary__price-table']/tbody/tr[4]/td[2]")
	private WebElement checkOutpaymentProuctTotalValue;
	
	
	
	
	
	
	
	
		
		
	public void completeCheckOutProcess() {
		log.assertLog(isElementPresent(checkoutLabel), "Element is not present - Checkout process has not started");
		clickButton("Next");
		log.assertLog(isElementPresent(deliveryLabel), "delivery label not found- Checkout process has not started");
		assertTrue("Text not same", getText(defaultDeliveryText).equalsIgnoreCase(defaultDeliveryText));
		clickButton("Next");
		log.info("SCA-1103 and aditional validation covered for default text in delivery");
		log.assertLog(isElementPresent(paymentLabel), "Element is not present - Checkout process has not started");
		log.info("SCA-1106 validation passed");	
		clickButton("Next");
		clickElement(termsAndConditions);
		clickElement(placeOrder);		
	}
	
	
	public void checkTitelBasket() {
		waitForSeconds(1);
		//assertTrue(basketTitle.equals(getText(basketPageTitle)));
		waitForSeconds(1);
	}
	
	

}

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
	
	public static String poNumbertestsupplier039;
	public static String poNumbertestsupplier040;
	
	public CheckoutPage(WebDriver driver, Scenario scenario) {
		super.driver = driver;
		this.scenario = scenario;
		PageFactory.initElements(driver, this);
		this.wait = new WebDriverWait(super.driver, 30);
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
	
	// Locators Checkout Payment page
	@FindBy(xpath = "//h1[text()='Checkout']")
	private WebElement checkOutHeading;
	
	@FindBy(xpath = "//h2[text()='Payment by invoice']")
	private WebElement paymentByInvoice;
	
	@FindBy(xpath = "//h2[text()='Confirmation']")
	private WebElement confirmationHeading;
		
	@FindBy(xpath = "//p[text()='Currently the option available for payments is offline via invoice.']")
	private WebElement paymentByInvoiceMessage;
	
	@FindBy(xpath = "//p[@class='govuk-heading-m']")
	private WebElement checkOutpaymentSupplierName;
	
	@FindBy(xpath = "//p[@class='govuk-body'][2]")
	private WebElement checkOutpaymentTotalValue;
	
	@FindBy(xpath = "//label[@class='govuk-label govuk-label--s']/following-sibling::input")
	private WebElement checkOutpaymentPOTextbox;
	
	@FindBy(xpath = "//h2[text()='Order summary']")
	private WebElement checkOutpaymentOrderSummaryHeading;
		
	@FindBy(xpath = "//table[@class='govuk-table bat-prices-summary__price-table']/tbody/tr[1]/td[2]")
	private WebElement checkOutpaymentProductsTotal;
	
	@FindBy(xpath = "//table[@class='govuk-table bat-prices-summary__price-table']/tbody/tr[2]/td[2]")
	private WebElement checkOutpaymentDeliveryTotal;
	
	@FindBy(xpath = "//table[@class='govuk-table bat-prices-summary__price-table']/tbody/tr[3]/td[2]")
	private WebElement checkOutpaymentVAT;
	
	@FindBy(xpath = "//table[@class='govuk-table bat-prices-summary__price-table']/tbody/tr[4]/td[2]")
	private WebElement checkOutpaymentProductTotalValue;
	
	// Locators Checkout Confirmation page
	
	//Supplier1 (testsupplier039) Product 1
	@FindBy(xpath = "//h3[text()='testsupplier039']")
	private WebElement supplier1Name;

	@FindBy(xpath = "//div[@class='bat-basket-supplier'][1]/div[2]/div/div[2]/a")
	private WebElement supplier1P1Name;

	@FindBy(xpath = "//div[@class='bat-basket-supplier'][1]/div[2]/div/div[2]/a/span[2]")
	private WebElement supplier1P1SKU;
	
	@FindBy(xpath = "//div[@class='bat-basket-supplier'][1]/div[2]/div/div[3]")
	private WebElement supplier1P1Quantityty;

	@FindBy(xpath = "//div[@class='bat-basket-supplier'][1]/div[2]/div/div[4]")
	private WebElement supplier1P1PriceEach;

	@FindBy(xpath = "//div[@class='bat-basket-supplier'][1]/div[2]/div/div[5]")
	private WebElement supplier1P1PriceTotal;
	
	//################################################################
	
	//Supplier1 (testsupplier039) Product2

	@FindBy(xpath = "//div[@class='bat-basket-supplier'][1]/div[3]/div/div[2]/a")
	private WebElement supplier1P2Name;

	@FindBy(xpath = "//div[@class='bat-basket-supplier'][1]/div[3]/div/div[2]/a/span[2]")
	private WebElement supplier1P2SKU;

	@FindBy(xpath = "//div[@class='bat-basket-supplier'][1]/div[3]/div/div[3]")
	private WebElement supplier1P2Quantityty;

	@FindBy(xpath = "//div[@class='bat-basket-supplier'][2]/div[3]/div/div[4]")
	private WebElement supplier1P2PriceEach;

	@FindBy(xpath = "//div[@class='bat-basket-supplier'][2]/div[3]/div/div[5]")
	private WebElement supplier1P2PriceTotal;
	
	// Supplier1 (Delivery Method, Payment method, and PO Number)
	
	@FindBy(xpath = "//div[@class='bat-basket-supplier'][1]/div[4]//div[1]/ul/li[1]")
	private WebElement supplier1P1P2DeliveryMethod;
	
	@FindBy(xpath = "//div[@class='bat-basket-supplier'][1]/div[4]//div[1]/ul/li[2]")
	private WebElement supplier1P1P2PaymentMethod;
	
	@FindBy(xpath = "//div[@class='bat-basket-supplier'][1]/div[4]//div[1]/ul/li[3]")
	private WebElement supplier1P1P2PONumber;
	
	
	// Supplier1 (Products total, Delivery total, VAT and Total)
	
	@FindBy(xpath = "//div[@class='bat-basket-supplier'][1]/div[4]/div[2]/ul/li[1]/span[2]")
	private WebElement supplier1P1P2ProductsTotal;
	
	@FindBy(xpath = "//div[@class='bat-basket-supplier'][1]/div[4]/div[2]/ul/li[2]/span[2]")
	private WebElement supplier1P1P2DeliveryTotal;
	
	@FindBy(xpath = "//div[@class='bat-basket-supplier'][1]/div[4]/div[2]/ul/li[3]/span[2]")
	private WebElement supplier1P1P2VAT;
	
	@FindBy(xpath = "//div[@class='bat-basket-supplier'][1]/div[4]/div[2]/ul/li[4]/span[2]")
	private WebElement supplier1P1P2Total;
	

	// ###################################  Supplier2 ####################
	
	//Supplier2 (testsupplier040) Product 2
	@FindBy(xpath = "//h3[text()='testsupplier040']")
	private WebElement supplier2Name;

	@FindBy(xpath = "//div[@class='bat-basket-supplier'][2]/div[2]/div/div[2]/a")
	private WebElement supplier2P1Name;

	@FindBy(xpath = "//div[@class='bat-basket-supplier'][2]/div[2]/div/div[2]/a/span[2]")
	private WebElement supplier2P1SKU;
	
	@FindBy(xpath = "//div[@class='bat-basket-supplier'][2]/div[2]/div/div[3]")
	private WebElement supplier2P1Quantityty;

	@FindBy(xpath = "//div[@class='bat-basket-supplier'][2]/div[2]/div/div[4]")
	private WebElement supplier2P1PriceEach;

	@FindBy(xpath = "//div[@class='bat-basket-supplier'][2]/div[2]/div/div[5]")
	private WebElement supplier2P1PriceTotal;
	
	//################################################################
	
	//Supplier2 (testsupplier040) Product2

	@FindBy(xpath = "//div[@class='bat-basket-supplier'][2]/div[3]/div/div[2]/a")
	private WebElement supplier2P2Name;

	@FindBy(xpath = "//div[@class='bat-basket-supplier'][2]/div[3]/div/div[2]/a/span[2]")
	private WebElement supplier2P2SKU;

	@FindBy(xpath = "//div[@class='bat-basket-supplier'][2]/div[3]/div/div[3]")
	private WebElement supplier2P2Quantityty;

	@FindBy(xpath = "//div[@class='bat-basket-supplier'][2]/div[3]/div/div[4]")
	private WebElement supplier2P2PriceEach;

	@FindBy(xpath = "//div[@class='bat-basket-supplier'][2]/div[3]/div/div[5]")
	private WebElement supplier2P2PriceTotal;
	
	// Supplier1 (Delivery Method, Payment method, and PO Number)
	
	@FindBy(xpath = "//div[@class='bat-basket-supplier'][2]/div[4]//div[1]/ul/li[1]")
	private WebElement supplier2P1P2DeliveryMethod;
	
	@FindBy(xpath = "//div[@class='bat-basket-supplier'][2]/div[4]//div[1]/ul/li[2]")
	private WebElement supplier2P1P2PaymentMethod;
	
	@FindBy(xpath = "//div[@class='bat-basket-supplier'][2]/div[4]//div[1]/ul/li[3]")
	private WebElement supplier2P1P2PONumber;
	
	
	// Supplier1 (Products total, Delivery total, VAT and Total)
	
	@FindBy(xpath = "//div[@class='bat-basket-supplier'][2]/div[4]/div[2]/ul/li[1]/span[2]")
	private WebElement supplier2P1P2ProductsTotal;
	
	@FindBy(xpath = "//div[@class='bat-basket-supplier'][2]/div[4]/div[2]/ul/li[2]/span[2]")
	private WebElement supplier2P1P2DeliveryTotal;
	
	@FindBy(xpath = "//div[@class='bat-basket-supplier'][2]/div[4]/div[2]/ul/li[3]/span[2]")
	private WebElement supplier2P1P2VAT;
	
	@FindBy(xpath = "//div[@class='bat-basket-supplier'][2]/div[4]/div[2]/ul/li[4]/span[2]")
	private WebElement supplier2P1P2Total;
	
	// Grand Total
	
	@FindBy(xpath = "//div[@class='bat-basket-supplier'][1]/parent::div/div[3]/ul/li[1]/span[2]")
	private WebElement supplier2P1P2grandAllProductTotal;
	
	@FindBy(xpath = "//div[@class='bat-basket-supplier'][1]/parent::div/div[3]/ul/li[2]/span[2]")
	private WebElement supplier2P1P2grandDeliveryTotal;
	
	@FindBy(xpath = "//div[@class='bat-basket-supplier'][1]/parent::div/div[3]/ul/li[3]/span[2]")
	private WebElement supplier2P1P2grandVAT;
	
	@FindBy(xpath = "//div[@class='bat-basket-supplier'][1]/parent::div/div[3]/ul/li[4]/span[2]")
	private WebElement supplier2P1P2grandTotal;
	
	//Terms and condition
	
	@FindBy(xpath = "//a[text()='View the Terms & Conditions for the purchase (opens in a new window or tab)']")
	private WebElement ViewTermsAndConditionsForThePurchaseLink;
	
	@FindBy(xpath = "//a[text()='View the Terms & Conditions for the purchase (opens in a new window or tab)']")
	private WebElement IAgreeToTheTermsAndConditionsText;
	
	@FindBy(xpath = "//input[@class='govuk-checkboxes__input']")
	private WebElement IAgreeToTheTermsAndConditionsCheckbox;
	
	public String IAgreeToTheTermsAndConditionsCheckboxStr="//input[@class='govuk-checkboxes__input']";
	
	
	
		
		
		
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
		assertTrue(getText(checkOutHeading).equals("Checkout"));
		assertTrue(getText(paymentByInvoice).equals("Payment by invoice"));
		waitForSeconds(1);
	}
	
	
	public void validateTitleCheckoutConfirmation() {
		waitForSeconds(1);
		assertTrue(getText(checkOutHeading).equals("Checkout"));
		assertTrue(getText(confirmationHeading).equals("Confirmation"));
	}
	
	public void validateTermsAndConditionLinkAndText() {
		waitForSeconds(1);
		assertTrue(isElementPresentByXpath(ViewTermsAndConditionsForThePurchaseLink));
		assertTrue(isElementPresentByXpath(IAgreeToTheTermsAndConditionsText));
	}
	
	public void clickOnTermsAndConditionCheckbox() {
		waitForSeconds(1);
		clickElementWithJavaScript(IAgreeToTheTermsAndConditionsCheckboxStr);
		assertTrue(isElementPresentByXpath(IAgreeToTheTermsAndConditionsCheckbox));
	}
	
		
	public void validateCheckoutPaymentOptionText() {
		waitForSeconds(1);
		assertTrue(getText(paymentByInvoiceMessage).equals("Currently the option available for payments is offline via invoice."));
	}
	
	
	public void validateSuppNameOrderSummaryTotalValueExVATOnCheckoutPayment(String SupplierName) {
		waitForSeconds(1);
		//Check Supplier Name
		assertTrue(getText(checkOutpaymentSupplierName).split("\\r?\\n")[1].equals(SupplierName));
		
		//Check OrderSummary Heading
		assertTrue(getText(checkOutpaymentOrderSummaryHeading).equals("Order summary"));
		
		// Calculation Start
		//Order summary Products Total
		assertTrue(getText(checkOutpaymentProductsTotal).equals(BuyersUIBasketPage.productDetailsCheckout.get("grandBasketProductTotal")));
				
		//Order summary Delivery Total
		assertTrue(getText(checkOutpaymentDeliveryTotal).equals(BuyersUIBasketPage.productDetailsCheckout.get("grandBasketDeliveryTotal")));
		
		//Order summary VAT
		assertTrue(getText(checkOutpaymentVAT).equals(BuyersUIBasketPage.productDetailsCheckout.get("grandBasketVatTotal")));
		
		//Order summary Product Overall Total Value
		assertTrue(getText(checkOutpaymentProductTotalValue).equals(BuyersUIBasketPage.productDetailsCheckout.get("grandBasketTotal")));
					
		waitForSeconds(1);
	}
	
	
	public void enterPONumber(String supplierName) {
		waitForSeconds(1);
		if(supplierName.equals("testsupplier039")) {
			
			poNumbertestsupplier039="PONumber"+"_"+supplierName;
			enterText(checkOutpaymentPOTextbox,poNumbertestsupplier039);
			
		}else if(supplierName.equals("testsupplier040")){
			
			poNumbertestsupplier040="PONumber"+"_"+supplierName;
			enterText(checkOutpaymentPOTextbox,poNumbertestsupplier040);
		}
		
		waitForSeconds(1);
	}
	
	
	public void validateSupp1And2ProductsDetailsOnCheckoutConfirmationPage() {
		
		// Supplier1 P1
		waitForSeconds(1);
		//System.out.println(getText(supplier1Name));
		// Check the Supplier "testsupplier039" Product 1 Details i.e. Product Name,
		// SKU, Price each, Price Total, and QTY
		assertTrue(getText(supplier1Name).equals(BuyersUIBasketPage.productDetailsCheckout.get("supplier1Name")));
		assertTrue(getText(supplier1P1Name).split("\\r?\\n")[1].equals(BuyersUIBasketPage.productDetailsCheckout.get("supplier1P1Name")));
		assertTrue(getText(supplier1P1SKU).equals(BuyersUIBasketPage.productDetailsCheckout.get("supplier1P1SKU").split(": ")[1]));
		assertTrue(getText(supplier1P1PriceEach).equals(BuyersUIBasketPage.productDetailsCheckout.get("supplier1P1PriceEach")));
		assertTrue(getText(supplier1P1PriceTotal).equals(BuyersUIBasketPage.productDetailsCheckout.get("supplier1P1Total").split("Total: ")[1]));
		assertTrue(getText(supplier1P1Quantityty).equals("Quantity: 1"));
		
		// Supplier1 P2
		//System.out.println(getText(supplier1P2Quantityty));
    	// Check the Supplier "testsupplier039" Product 1 Details i.e. Product Name,
		// SKU, Price each, Price Total, and QTY
		assertTrue(getText(supplier1P2Name).split("\\r?\\n")[1].equals(BuyersUIBasketPage.productDetailsCheckout.get("supplier1P2Name")));
		assertTrue(getText(supplier1P2SKU).equals(BuyersUIBasketPage.productDetailsCheckout.get("supplier1P2SKU").split(": ")[1]));
		assertTrue(getText(supplier1P2PriceEach).equals(BuyersUIBasketPage.productDetailsCheckout.get("supplier1P2PriceEach")));
		assertTrue(getText(supplier1P2PriceTotal).equals(BuyersUIBasketPage.productDetailsCheckout.get("supplier1P2Total").split("Total: ")[1]));
		assertTrue(getText(supplier1P2Quantityty).equals("Quantity: 1"));
		
		
		// Supplier1 Delivery Method, Payment method and PO Number
		//System.out.println(getText(supplier1P1P2DeliveryMethod));
		assertTrue(getText(supplier1P1P2DeliveryMethod).equals("Delivery Method: Standard UK Mainland"));
		assertTrue(getText(supplier1P1P2PaymentMethod).equals("Payment method: Payment by Invoice"));
		assertTrue(getText(supplier1P1P2PONumber).equals("PO Number: "+poNumbertestsupplier039));
		
		//Supplier1 OverAll Total P1 and P2
		assertTrue(getText(supplier1P1P2ProductsTotal).equals(BuyersUIBasketPage.productDetailsCheckout.get("productTotalSupp1")));
		assertTrue(getText(supplier1P1P2DeliveryTotal).equals(BuyersUIBasketPage.productDetailsCheckout.get("deliveryTotalSupp1")));
		assertTrue(getText(supplier1P1P2VAT).equals(BuyersUIBasketPage.productDetailsCheckout.get("vatTotalSupp1")));
		assertTrue(getText(supplier1P1P2Total).equals(BuyersUIBasketPage.productDetailsCheckout.get("grandTotalSupp1")));
		
		
		// ######### Supplier 2
		
		// Supplier2 P1
		// Check the Supplier "testsupplier040" Product 1 Details i.e. Product Name,
		// SKU, Price each, Price Total, and QTY
		assertTrue(getText(supplier2Name).equals(BuyersUIBasketPage.productDetailsCheckout.get("supplier2Name")));
		assertTrue(getText(supplier2P1Name).split("\\r?\\n")[1].equals(BuyersUIBasketPage.productDetailsCheckout.get("supplier2P1Name")));
		assertTrue(getText(supplier2P1SKU).equals(BuyersUIBasketPage.productDetailsCheckout.get("supplier2P1SKU").split(": ")[1]));
		assertTrue(getText(supplier2P1PriceEach).equals(BuyersUIBasketPage.productDetailsCheckout.get("supplier2P1PriceEach")));
		assertTrue(getText(supplier2P1PriceTotal).equals(BuyersUIBasketPage.productDetailsCheckout.get("supplier2P1Total").split("Total: ")[1]));
		assertTrue(getText(supplier2P1Quantityty).equals("Quantity: 1"));
		
		// Supplier2 P2
    	// Check the Supplier2 "testsupplier040" Product 2 Details i.e. Product Name,
		// SKU, Price each, Price Total, and QTY
		assertTrue(getText(supplier2P2Name).split("\\r?\\n")[1].equals(BuyersUIBasketPage.productDetailsCheckout.get("supplier2P2Name")));
		assertTrue(getText(supplier2P2SKU).equals(BuyersUIBasketPage.productDetailsCheckout.get("supplier2P2SKU").split(": ")[1]));
		assertTrue(getText(supplier2P2PriceEach).equals(BuyersUIBasketPage.productDetailsCheckout.get("supplier2P2PriceEach")));
		assertTrue(getText(supplier2P2PriceTotal).equals(BuyersUIBasketPage.productDetailsCheckout.get("supplier2P2Total").split("Total: ")[1]));
		assertTrue(getText(supplier2P2Quantityty).equals("Quantity: 1"));
		
		
		// Supplier1 Delivery Method, Payment method and PO Number
		assertTrue(getText(supplier2P1P2DeliveryMethod).equals("Delivery Method: Standard UK Mainland"));
		assertTrue(getText(supplier2P1P2PaymentMethod).equals("Payment method: Payment by Invoice"));
		assertTrue(getText(supplier2P1P2PONumber).equals("PO Number: "+poNumbertestsupplier040));
		
		//Supplier1 OverAll Total P1 and P2
		assertTrue(getText(supplier2P1P2ProductsTotal).equals(BuyersUIBasketPage.productDetailsCheckout.get("productTotalSupp2")));
		assertTrue(getText(supplier2P1P2DeliveryTotal).equals(BuyersUIBasketPage.productDetailsCheckout.get("deliveryTotalSupp2")));
		assertTrue(getText(supplier2P1P2VAT).equals(BuyersUIBasketPage.productDetailsCheckout.get("vatTotalSupp2")));
		assertTrue(getText(supplier2P1P2Total).equals(BuyersUIBasketPage.productDetailsCheckout.get("grandTotalSupp2")));
		
		//Grand Total Supp 1 and 2 
		assertTrue(getText(supplier2P1P2grandAllProductTotal).equals(BuyersUIBasketPage.productDetailsCheckout.get("grandBasketProductTotal")));
		assertTrue(getText(supplier2P1P2grandDeliveryTotal).equals(BuyersUIBasketPage.productDetailsCheckout.get("grandBasketDeliveryTotal")));
		assertTrue(getText(supplier2P1P2grandVAT).equals(BuyersUIBasketPage.productDetailsCheckout.get("grandBasketVatTotal")));
		assertTrue(getText(supplier2P1P2grandTotal).equals(BuyersUIBasketPage.productDetailsCheckout.get("grandBasketTotal")));
			
	}
	

}

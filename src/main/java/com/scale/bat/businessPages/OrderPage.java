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

public class OrderPage extends Actions{
	
	private Logger log = Log.getLogger(CheckoutPage.class);
	private WebDriver driver;
	private ConfigurationReader configReaderObj;
	
	public OrderPage(WebDriver driver, Scenario scenario) {
		super.driver = driver;
		this.scenario = scenario;
		PageFactory.initElements(driver, this);
		this.wait = new WebDriverWait(super.driver, 30);
		configReaderObj = new ConfigurationReader();
	}
	
	@FindBy(xpath = "//h1[text()='My orders']")
	private WebElement myOrders;
	
	@FindBy(xpath = "//*[text()='The order is being placed']")
	private WebElement intermediaryOrderPageMessage;
	
	// Supplier Product details locator
	// Locators Order page
	
	//Supplier
	@FindBy(xpath = "//h3[@class='govuk-heading-s']")
	private WebElement supplierName;

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

	@FindBy(xpath = "//div[@class='bat-basket-supplier'][1]/div[3]/div/div[4]")
	private WebElement supplier1P2PriceEach;

	@FindBy(xpath = "//div[@class='bat-basket-supplier'][1]/div[3]/div/div[5]")
	private WebElement supplier1P2PriceTotal;
	
	// Supplier1 (Status, Purchase for organisation, Payment method and PO Number)
	
	@FindBy(xpath = "//div[@class='bat-basket-supplier'][1]/div[4]//div[1]/ul/li[1]")
	private WebElement orderStatus;
	
	@FindBy(xpath = "//div[@class='bat-basket-supplier'][1]/div[4]//div[1]/ul/li[2]")
	private WebElement purchaseForOrganisation;
	
	@FindBy(xpath = "//div[@class='bat-basket-supplier'][1]/div[4]//div[1]/ul/li[3]")
	private WebElement paymentMethod;
	
	@FindBy(xpath = "//div[@class='bat-basket-supplier'][1]/div[4]//div[1]/ul/li[4]")
	private WebElement poNumber;
	
	
	// Supplier1 (Products total, Delivery total, VAT and Total)
	
	@FindBy(xpath = "//div[@class='bat-basket-supplier'][1]/div[4]/div[2]/ul/li[1]/span[2]")
	private WebElement supplier1P1P2ProductsTotal;
	
	@FindBy(xpath = "//div[@class='bat-basket-supplier'][1]/div[4]/div[2]/ul/li[2]/span[2]")
	private WebElement supplier1P1P2DeliveryTotal;
	
	@FindBy(xpath = "//div[@class='bat-basket-supplier'][1]/div[4]/div[2]/ul/li[3]/span[2]")
	private WebElement supplier1P1P2VAT;
	
	@FindBy(xpath = "//div[@class='bat-basket-supplier'][1]/div[4]/div[2]/ul/li[4]/span[2]")
	private WebElement supplier1P1P2Total;
	
	//Open Order
	@FindBy(xpath = "//tbody[@class='govuk-table__body']/tr[1]/td[1]/a")
	private WebElement openOrder1;
	
	@FindBy(xpath = "//tbody[@class='govuk-table__body']/tr[2]/td[1]/a")
	private WebElement openOrder2;
	
	@FindBy(xpath = "//a[text()='Back']")
	private WebElement backLink;
	
	
	
	
	public void validateMyOrderPage() {
		waitForSeconds(1);
		assertTrue(getText(myOrders).equals("My orders"));
		waitForSeconds(1);
	}
	
	public void backToMyOrderPage() {
		clickElement(backLink);
		waitForSeconds(1);
	}
	
	public String openOrderDetails(String OrderNo) {
		
		String supplierNameOrderPage = "";
		if(OrderNo.equals("1")) {
			
			clickElement(openOrder1);
			waitForSeconds(1);
	        supplierNameOrderPage=getText(supplierName);
		}else if(OrderNo.equals("2")) {
			
			clickElement(openOrder2);
			waitForSeconds(1);
			supplierNameOrderPage=getText(supplierName);
		}
		
		return supplierNameOrderPage;
		
	}
	
	public void validateIntermediaryOrderPageMessage(String message) {
		waitForSeconds(1);
		assertTrue(getText(intermediaryOrderPageMessage).equals(message));
		waitForSeconds(10);
	}
	
	
	
	public void validateSupplier1P1P2OrderDetails() {
		
		// Supplier1 P1
		waitForSeconds(1);
		System.out.println(getText(supplierName));
		// Check the Supplier "testsupplier039" Product 1 Details i.e. Product Name,
		// SKU, Price each, Price Total, and QTY
		assertTrue(getText(supplierName).equals(BuyersUIBasketPage.productDetailsCheckout.get("supplier1Name")));
		assertTrue(getText(supplier1P1Name).split("\\r?\\n")[1].equals(BuyersUIBasketPage.productDetailsCheckout.get("supplier1P1Name")));
		assertTrue(getText(supplier1P1SKU).equals(BuyersUIBasketPage.productDetailsCheckout.get("supplier1P1SKU").split(": ")[1]));
		assertTrue(getText(supplier1P1PriceEach).equals(BuyersUIBasketPage.productDetailsCheckout.get("supplier1P1PriceEach")));
		assertTrue(getText(supplier1P1PriceTotal).equals(BuyersUIBasketPage.productDetailsCheckout.get("supplier1P1Total").split("Total: ")[1]));
		assertTrue(getText(supplier1P1Quantityty).equals("Quantity: 1"));
		
		// Supplier1 P2
		System.out.println(getText(supplier1P2Quantityty));
    	// Check the Supplier "testsupplier039" Product 1 Details i.e. Product Name,
		// SKU, Price each, Price Total, and QTY
		assertTrue(getText(supplier1P2Name).split("\\r?\\n")[1].equals(BuyersUIBasketPage.productDetailsCheckout.get("supplier1P2Name")));
		assertTrue(getText(supplier1P2SKU).equals(BuyersUIBasketPage.productDetailsCheckout.get("supplier1P2SKU").split(": ")[1]));
		assertTrue(getText(supplier1P2PriceEach).equals(BuyersUIBasketPage.productDetailsCheckout.get("supplier1P2PriceEach")));
		assertTrue(getText(supplier1P2PriceTotal).equals(BuyersUIBasketPage.productDetailsCheckout.get("supplier1P2Total").split("Total: ")[1]));
		assertTrue(getText(supplier1P2Quantityty).equals("Quantity: 1"));
		
		
		// Supplier1 Delivery Method, Payment method and PO Number
		System.out.println(getText(orderStatus));
		assertTrue(getText(orderStatus).equals("Status: Placed"));
		assertTrue(getText(paymentMethod).equals("Payment method: Payment by Invoice"));
		assertTrue(getText(poNumber).equals("PO Number: "+CheckoutPage.poNumbertestsupplier039));
		
		//Supplier1 OverAll Total P1 and P2
		assertTrue(getText(supplier1P1P2ProductsTotal).equals(BuyersUIBasketPage.productDetailsCheckout.get("productTotalSupp1")));
		assertTrue(getText(supplier1P1P2DeliveryTotal).equals(BuyersUIBasketPage.productDetailsCheckout.get("deliveryTotalSupp1")));
		assertTrue(getText(supplier1P1P2VAT).equals(BuyersUIBasketPage.productDetailsCheckout.get("vatTotalSupp1")));
		assertTrue(getText(supplier1P1P2Total).equals(BuyersUIBasketPage.productDetailsCheckout.get("grandTotalSupp1")));
						
	}
	
	
	public void validateSupplier2P1P2OrderDetails() {
		
		// Supplier2 P1
		waitForSeconds(1);
		System.out.println(getText(supplierName));
		// Check the Supplier2 "testsupplier039" Product 1 Details i.e. Product Name, SKU, Price each, Price Total, and QTY
		assertTrue(getText(supplierName).equals(BuyersUIBasketPage.productDetailsCheckout.get("supplier2Name")));
		assertTrue(getText(supplier1P1Name).split("\\r?\\n")[1].equals(BuyersUIBasketPage.productDetailsCheckout.get("supplier2P1Name")));
		assertTrue(getText(supplier1P1SKU).equals(BuyersUIBasketPage.productDetailsCheckout.get("supplier2P1SKU").split(": ")[1]));
		assertTrue(getText(supplier1P1PriceEach).equals(BuyersUIBasketPage.productDetailsCheckout.get("supplier2P1PriceEach")));
		assertTrue(getText(supplier1P1PriceTotal).equals(BuyersUIBasketPage.productDetailsCheckout.get("supplier2P1Total").split("Total: ")[1]));
		assertTrue(getText(supplier1P1Quantityty).equals("Quantity: 1"));
		
		// Supplier2 P2
		System.out.println(getText(supplier1P2Quantityty));
    	// Check the Supplier "testsupplier039" Product 1 Details i.e. Product Name, SKU, Price each, Price Total, and QTY
		assertTrue(getText(supplier1P2Name).split("\\r?\\n")[1].equals(BuyersUIBasketPage.productDetailsCheckout.get("supplier2P2Name")));
		assertTrue(getText(supplier1P2SKU).equals(BuyersUIBasketPage.productDetailsCheckout.get("supplier2P2SKU").split(": ")[1]));
		assertTrue(getText(supplier1P2PriceEach).equals(BuyersUIBasketPage.productDetailsCheckout.get("supplier2P2PriceEach")));
		assertTrue(getText(supplier1P2PriceTotal).equals(BuyersUIBasketPage.productDetailsCheckout.get("supplier2P2Total").split("Total: ")[1]));
		assertTrue(getText(supplier1P2Quantityty).equals("Quantity: 1"));
		
		
		// Supplier2 Delivery Method, Payment method and PO Number
		System.out.println(getText(orderStatus));
		assertTrue(getText(orderStatus).equals("Status: Placed"));
		assertTrue(getText(paymentMethod).equals("Payment method: Payment by Invoice"));
		assertTrue(getText(poNumber).equals("PO Number: "+CheckoutPage.poNumbertestsupplier040));
		
		//Supplier2 OverAll Total P1 and P2
		assertTrue(getText(supplier1P1P2ProductsTotal).equals(BuyersUIBasketPage.productDetailsCheckout.get("productTotalSupp2")));
		assertTrue(getText(supplier1P1P2DeliveryTotal).equals(BuyersUIBasketPage.productDetailsCheckout.get("deliveryTotalSupp2")));
		assertTrue(getText(supplier1P1P2VAT).equals(BuyersUIBasketPage.productDetailsCheckout.get("vatTotalSupp2")));
		assertTrue(getText(supplier1P1P2Total).equals(BuyersUIBasketPage.productDetailsCheckout.get("grandTotalSupp2")));
						
	}

}

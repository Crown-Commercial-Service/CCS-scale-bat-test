package com.scale.bat.businessPages;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.math3.geometry.euclidean.twod.Line;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.scale.bat.framework.utility.Actions;
import com.scale.bat.framework.utility.ConfigurationReader;
import com.scale.bat.framework.utility.DateTimeUtils;
import com.scale.bat.framework.utility.JsonParser;
import com.scale.bat.framework.utility.Log;
import com.scale.bat.framework.utility.TakeScreenShotAndAddToWordDoc;
import com.scale.bat.framework.utility.API.APIBase;
import com.scale.bat.businessPages.WishListServiceStepDefs;

import cucumber.api.Scenario;
import io.restassured.response.Response;

public class BuyersUIBasketPage extends Actions {

	private Logger log = Log.getLogger(BuyersUIPDPPage.class);
	private ConfigurationReader configReaderObj;
	APIBase apiBase = new APIBase();
	public static Map<String, String> productDetailsCheckout = new HashMap<String, String>();
	// WishListServiceStepDefs wishListServiceStepDefs=new
	// WishListServiceStepDefs();

	public BuyersUIBasketPage(WebDriver driver, Scenario scenario) {
		super.driver = driver;
		this.scenario = scenario;

		PageFactory.initElements(driver, this);
		this.wait = new WebDriverWait(super.driver, 30);
		configReaderObj = new ConfigurationReader();
	}

	@FindBy(xpath = "//*[@id='main-content']/div/div/div/div[1]/ul/li[1]/div/div/table/tbody/tr/td[5]")
	private WebElement priceOnBasket1;

	@FindBy(xpath = "")
	private WebElement manufaturerBasket;

	@FindBy(xpath = "//*[@id='main-content']/div/div/div/div[1]/ul/li[1]/div/form/div/div[2]/a")
	private WebElement productNameBasket;

	@FindBy(xpath = "//*[@class='bat-basket-item__title__sku']")
	private WebElement skuBasket;

	@FindBy(xpath = "//*[@class='bat-basket-item__title__mpn']")
	private WebElement mpnBasket;

	@FindBy(xpath = "//*[@class='bat-basket__all-supplier-totals bat-basket-items-by-supplier__item__supplier__footer']/ul/li[1]/span[2]")
	private WebElement productsTotal;

	@FindBy(xpath = "//*[@class='bat-basket__all-supplier-totals bat-basket-items-by-supplier__item__supplier__footer']/ul/li[2]/span[2]")
	private WebElement deliveryTotal;

	@FindBy(xpath = "//*[@class='bat-basket__all-supplier-totals bat-basket-items-by-supplier__item__supplier__footer']/ul/li[3]/span[2]")
	private WebElement vatTotal;

	@FindBy(xpath = "//*[@class='bat-basket__all-supplier-totals bat-basket-items-by-supplier__item__supplier__footer']/ul/li[4]/span[2]")
	private WebElement grandTotal;

	@FindBy(xpath = "//*[@class='govuk-notification-banner__heading']")
	private WebElement productAddToBasketMsg;

	@FindBy(xpath = "//input[@name='quantity']")
	private WebElement qtyBasket;

	@FindBy(xpath = "//input[@name='quantity']")
	private WebElement productQuantity;

	@FindBy(xpath = "//*[@class='govuk-notification-banner__content']/p")
	private WebElement allProductsWereAddedToYourBasket;

	@FindBy(xpath = "//*[@class='govuk-warning-text__text']")
	private WebElement YouCannotAddTheSelectedProductToTheBasketItsOutOfStockMsg;

	// My Basket Page Locators
	// PRODUCT 1

	@FindBy(xpath = "//ul[@class='govuk-list bat-basket-items-by-supplier']/li[1]/div/ul/li/div/form/div/div[2]/a")
	private WebElement productName;

	@FindBy(xpath = "//*[@class='bat-basket-item__title']/a/following-sibling::span[1]")
	private WebElement mpn;

	@FindBy(xpath = "//*[@class='bat-basket-item__unit-price']")
	private WebElement productUnitPrice;

	@FindBy(xpath = "//*[@class='bat-basket-item__total']")
	private WebElement productTotalPrice;

	@FindBy(xpath = "//div[@class='bat-basket__footer__right']/ul/li[2]/span[2]")
	private WebElement getDeviveryCharge;

	// PRODUCT 2
	@FindBy(xpath = "//ul[@class='govuk-list bat-basket-items-by-supplier']/li[2]/div/ul/li/div/form/div/div[2]/a")
	private WebElement productName2;

	@FindBy(xpath = "//ul[@class='govuk-list bat-basket-items-by-supplier']/li[2]/div/ul/li/div/form/div/div[2]/span[1]")
	private WebElement mpn2;

	@FindBy(xpath = "//ul[@class='govuk-list bat-basket-items-by-supplier']/li[2]/div/ul/li/div/form/div/div[2]/following-sibling::div[1]")
	private WebElement productPrice2;

	@FindBy(xpath = "//button[@class='govuk-button govuk-button--secondary govuk-!-width-one-quarter']")
	private WebElement basketUpdateButton;
		
	@FindBy(xpath = "//*[@id='main-content']/div[2]/div/div/form[1]/button")
	private WebElement basketUpdateDeliveryAndInvoiceChoicesBtn;

	// PRODUCT 3
	@FindBy(xpath = "//ul[@class='govuk-list bat-basket-items-by-supplier']/li[2]/div/ul/li[2]/div/form/div/div[2]/a")
	private WebElement productName3;

	@FindBy(xpath = "//ul[@class='govuk-list bat-basket-items-by-supplier']/li[2]/div/ul/li[2]/div/form/div/div[2]/span[1]")
	private WebElement mpn3;

	@FindBy(xpath = "//ul[@class='govuk-list bat-basket-items-by-supplier']/li[2]/div/ul/li[2]/div/form/div/div[2]/following-sibling::div[1]")
	private WebElement productPrice3;

	// Basket page
	@FindBy(xpath = "//*[@class='govuk-notification-banner__heading']")
	private WebElement basketPageMsgAfterClearBasket;

	String checkProductPresentOrNot = "//*[@id='main-content']/div/div/div/div[1]/ul/li[1]/div/form/div/div[2]/a";

	@FindBy(xpath = "//*[@class='govuk-heading-m']")
	private WebElement basketPageYourbasketIsEmptyMsg;

	String checkClearBasketBtn = "//*[@name='_csrf']/following-sibling::button";

	String deliveryMethodDropDown = "//select[@id='shipping_method_id']";

	@FindBy(xpath = "//select[@id='shipping_method_id']")
	private WebElement webElementDeliveryMethodDropDown;
	
	
	@FindBy(xpath = "//select[@name='shippingAddressId']")
	private WebElement deliveryAddressDropDown;

	@FindBy(xpath = "//div[@id='shipping_method_id-hint']")
	private WebElement basketWarningMsgOfNextDayDelivery;

	private String basketWarningMsgOfNextDayDeliveryString = "//div[@id='shipping_method_id-hint']";

	@FindBy(xpath = "//a[@href='/basket']")
	private WebElement basketLink;

	@FindBy(xpath = "//h2[@class='govuk-notification-banner__title']")
	private WebElement notificationBlueBanner;

	@FindBy(xpath = "//span[@class='govuk-details__summary-text']")
	private WebElement notificationBlueBannerSummaryText;

	@FindBy(xpath = "//div[@class='govuk-details__text']/ul/li")
	private WebElement notificationBlueBannerProductDetail;

	@FindBy(xpath = "//input[@name='quantity']")
	private WebElement productQuantityOnBasket;

	@FindBy(xpath = "//*[@class='govuk-list bat-basket-items-by-supplier']/li[1]/div/div/ul/li[1]/span[2]")
	private WebElement supplier1ProductsTotal;

	@FindBy(xpath = "//*[@class='govuk-list bat-basket-items-by-supplier']/li/div/ul/li[1]/div/form/div/div[5]/div/label/following-sibling::input")
	private WebElement supplier1P1Qty;

	@FindBy(xpath = "//*[@class='govuk-list bat-basket-items-by-supplier']/li/div/ul/li[2]/div/form/div/div[5]/div/label/following-sibling::input")
	private WebElement supplier1P2Qty;

	@FindBy(xpath = "//*[@class='govuk-list bat-basket-items-by-supplier']/li/div/ul/li[1]/div/form/div/div[3]")
	private WebElement supplier1P1Price;

	@FindBy(xpath = "//*[@class='govuk-list bat-basket-items-by-supplier']/li/div/ul/li[2]/div/form/div/div[3]")
	private WebElement supplier1P2Price;

	@FindBy(xpath = "//*[@class='govuk-list bat-basket-items-by-supplier']/li[1]/div/div/ul/li[2]/span[2]")
	private WebElement supplier1DeliveryTotal;

	@FindBy(xpath = "//*[@class='govuk-list bat-basket-items-by-supplier']/li[1]/div/div/ul/li[3]/span[2]")
	private WebElement supplier1VatTotal;

	@FindBy(xpath = "//*[@class='govuk-list bat-basket-items-by-supplier']/li[1]/div/div/ul/li[4]/span[2]")
	private WebElement supplier1GrandTotal;

	// Supplier2 details
	@FindBy(xpath = "//*[@class='govuk-list bat-basket-items-by-supplier']/li[2]/div/div/ul/li[1]/span[2]")
	private WebElement supplier2ProductsTotal;

	@FindBy(xpath = "//*[@class='govuk-list bat-basket-items-by-supplier']/li[2]/div/div/ul/li[2]/span[2]")
	private WebElement supplier2DeliveryTotal;

	@FindBy(xpath = "//*[@class='govuk-list bat-basket-items-by-supplier']/li[2]/div/div/ul/li[3]/span[2]")
	private WebElement supplier2VatTotal;

	@FindBy(xpath = "//*[@class='govuk-list bat-basket-items-by-supplier']/li[2]/div/div/ul/li[4]/span[2]")
	private WebElement supplier2GrandTotal;

	@FindBy(xpath = "//*[@class='govuk-list bat-basket-items-by-supplier']/li[2]/div/ul/li[1]/div/form/div/div[4]/div/label/following-sibling::input")
	private WebElement supplier2P1Qty;

	@FindBy(xpath = "//*[@class='govuk-list bat-basket-items-by-supplier']/li[2]/div/ul/li[2]/div/form/div/div[4]/div/label/following-sibling::input")
	private WebElement supplier2P2Qty;

	@FindBy(xpath = "//*[@class='govuk-list bat-basket-items-by-supplier']/li[2]/div/ul/li[1]/div/form/div/div[3]")
	private WebElement supplier2P1Price;

	@FindBy(xpath = "//*[@class='govuk-list bat-basket-items-by-supplier']/li[2]/div/ul/li[2]/div/form/div/div[3]")
	private WebElement supplier2P2Price;
	
	@FindBy(xpath = "//h2[@class='govuk-heading-m']")
	private WebElement paymentCheckOutTextUI;
	
	//Payment Checkout page Order Summary Locator
	@FindBy(xpath = "//table[@class='govuk-table bat-prices-summary__price-table']/tbody/tr[1]/td[2]")
	private WebElement paymentCheckOutOrderSummaryProducts;
	
	@FindBy(xpath = "//table[@class='govuk-table bat-prices-summary__price-table']/tbody/tr[2]/td[2]")
	private WebElement paymentCheckOutOrderSummaryDeliveryTotal;
	
	@FindBy(xpath = "//table[@class='govuk-table bat-prices-summary__price-table']/tbody/tr[3]/td[2]")
	private WebElement paymentCheckOutOrderSummaryVAT;
	
	@FindBy(xpath = "//table[@class='govuk-table bat-prices-summary__price-table']/tbody/tr[4]/td[2]")
	private WebElement paymentCheckOutOrderSummaryTotalValue;
	
	@FindBy(xpath = "//input[@name='poNumber184']")
	private WebElement paymentCheckOutPoNumberTextbox;
	
	String PoNumberWithCurrenntday;
	
	//Checkout Summary Page
	@FindBy(xpath = "//h1[@class='govuk-heading-l']")
	private WebElement checkOutOrderSummaryPageHeader;
	
	@FindBy(xpath = "//div[@class='bat-checkout-summary__total govuk-!-margin-top-9 govuk-!-margin-bottom-6']/ul/li[1]/span[2]")
	private WebElement checkOutOrderSummaryAllProductsTotal;
	
	@FindBy(xpath = "//div[@class='bat-checkout-summary__total govuk-!-margin-top-9 govuk-!-margin-bottom-6']/ul/li[2]/span[2]")
	private WebElement checkOutOrderSummaryAllProductsDeliveryTotal;
	
	@FindBy(xpath = "//div[@class='bat-checkout-summary__total govuk-!-margin-top-9 govuk-!-margin-bottom-6']/ul/li[3]/span[2]")
	private WebElement checkOutOrderSummaryAllProductsVAT;
	
	@FindBy(xpath = "//div[@class='bat-checkout-summary__total govuk-!-margin-top-9 govuk-!-margin-bottom-6']/ul/li[4]/span[2]")
	private WebElement checkOutOrderSummaryAllProductsGrandTotal;
	
	@FindBy(xpath = "//input[@class='govuk-checkboxes__input']")
	private WebElement checksOnTermsAndConditions;
	
	String checksOnTermsAndConditionsStr="//input[@class='govuk-checkboxes__input']";
	
	//SCA-144 Checkout Locators 2 Supp and 2 Products
	//Supplier1 (testsupplier039) Product 1
	@FindBy(xpath = "//h3[text()='testsupplier039']")
	private WebElement supplier1Name;
	
	@FindBy(xpath = "//h3[text()='testsupplier039']/following-sibling::ul/li[1]/div/form/div/div[1]/following-sibling::div/a")
	private WebElement supplier1P1Name;
	
	@FindBy(xpath = "//h3[text()='testsupplier039']/following-sibling::ul/li[1]/div/form/div/div[1]/following-sibling::div/a/following-sibling::span[1]")
	private WebElement supplier1P1MPN;
	
	@FindBy(xpath = "//h3[text()='testsupplier039']/following-sibling::ul/li[1]/div/form/div/div[1]/following-sibling::div/a/following-sibling::span[2]")
	private WebElement supplier1P1SKU;
	
	@FindBy(xpath = "//h3[text()='testsupplier039']/following-sibling::ul/li[1]/div/form/div/div[1]/following-sibling::div[1]/following-sibling::div[1]")
	private WebElement supplier1P1PriceEach;
	
	@FindBy(xpath = "//h3[text()='testsupplier039']/following-sibling::ul/li[1]/div/form/div/div[1]/following-sibling::div[1]/following-sibling::div[2]")
	private WebElement supplier1P1PriceTotal;
	
	@FindBy(xpath = "//h3[text()='testsupplier039']/following-sibling::ul/li[1]/div/form/div/div[1]/following-sibling::div[1]/following-sibling::div[3]/div/label/following-sibling::input")
	private WebElement supplier1P1Quantityty;
	
	// Supplier1 (testsupplier039) Product 2

	@FindBy(xpath = "//h3[text()='testsupplier039']/following-sibling::ul/li[2]/div/form/div/div[1]/following-sibling::div/a")
	private WebElement supplier1P2Name;

	@FindBy(xpath = "//h3[text()='testsupplier039']/following-sibling::ul/li[2]/div/form/div/div[1]/following-sibling::div/a/following-sibling::span[1]")
	private WebElement supplier1P2MPN;

	@FindBy(xpath = "//h3[text()='testsupplier039']/following-sibling::ul/li[2]/div/form/div/div[1]/following-sibling::div/a/following-sibling::span[2]")
	private WebElement supplier1P2SKU;

	@FindBy(xpath = "//h3[text()='testsupplier039']/following-sibling::ul/li[2]/div/form/div/div[1]/following-sibling::div[1]/following-sibling::div[1]")
	private WebElement supplier1P2PriceEach;

	@FindBy(xpath = "//h3[text()='testsupplier039']/following-sibling::ul/li[2]/div/form/div/div[1]/following-sibling::div[1]/following-sibling::div[2]")
	private WebElement supplier1P2PriceTotal;

	@FindBy(xpath = "//h3[text()='testsupplier039']/following-sibling::ul/li[2]/div/form/div/div[1]/following-sibling::div[1]/following-sibling::div[3]/div/label/following-sibling::input")
	private WebElement supplier1P2Quantityty;

	// (Supplier1 P1 P2 Products Total:)
	@FindBy(xpath = "//h3[text()='testsupplier039']/following-sibling::div/ul/li[1]/span[2]")
	private WebElement supplier1P1P2ProduuctTotal;

	@FindBy(xpath = "//h3[text()='testsupplier039']/following-sibling::div/ul/li[2]/span[2]")
	private WebElement supplier1P1P2DeliveryTotal;

	@FindBy(xpath = "//h3[text()='testsupplier039']/following-sibling::div/ul/li[3]/span[2]")
	private WebElement supplier1P1P2VAT;

	@FindBy(xpath = "//h3[text()='testsupplier039']/following-sibling::div/ul/li[4]/span[2]")
	private WebElement supplier1P1P2GrandTotal;
	
	
	// ########################
	
	//SCA-144 Checkout Locators 2 Supp and 2 Products
		//Supplier2 (testsupplier040) Product 1
		@FindBy(xpath = "//h3[text()='testsupplier040']")
		private WebElement supplier2Name;
		
		@FindBy(xpath = "//h3[text()='testsupplier040']/following-sibling::ul/li[1]/div/form/div/div[1]/following-sibling::div/a")
		private WebElement supplier2P1Name;
		
		@FindBy(xpath = "//h3[text()='testsupplier040']/following-sibling::ul/li[1]/div/form/div/div[1]/following-sibling::div/a/following-sibling::span[1]")
		private WebElement supplier2P1MPN;
		
		@FindBy(xpath = "//h3[text()='testsupplier040']/following-sibling::ul/li[1]/div/form/div/div[1]/following-sibling::div/a/following-sibling::span[2]")
		private WebElement supplier2P1SKU;
		
		@FindBy(xpath = "//h3[text()='testsupplier040']/following-sibling::ul/li[1]/div/form/div/div[1]/following-sibling::div[1]/following-sibling::div[1]")
		private WebElement supplier2P1PriceEach;
		
		@FindBy(xpath = "//h3[text()='testsupplier040']/following-sibling::ul/li[1]/div/form/div/div[1]/following-sibling::div[1]/following-sibling::div[2]")
		private WebElement supplier2P1PriceTotal;
		
		@FindBy(xpath = "//h3[text()='testsupplier040']/following-sibling::ul/li[1]/div/form/div/div[1]/following-sibling::div[1]/following-sibling::div[3]/div/label/following-sibling::input")
		private WebElement supplier2P1Quantityty;
		
		// Supplier2 (testsupplier040) Product 2

		@FindBy(xpath = "//h3[text()='testsupplier040']/following-sibling::ul/li[2]/div/form/div/div[1]/following-sibling::div/a")
		private WebElement supplier2P2Name;

		@FindBy(xpath = "//h3[text()='testsupplier040']/following-sibling::ul/li[2]/div/form/div/div[1]/following-sibling::div/a/following-sibling::span[1]")
		private WebElement supplier2P2MPN;

		@FindBy(xpath = "//h3[text()='testsupplier040']/following-sibling::ul/li[2]/div/form/div/div[1]/following-sibling::div/a/following-sibling::span[2]")
		private WebElement supplier2P2SKU;

		@FindBy(xpath = "//h3[text()='testsupplier040']/following-sibling::ul/li[2]/div/form/div/div[1]/following-sibling::div[1]/following-sibling::div[1]")
		private WebElement supplier2P2PriceEach;

		@FindBy(xpath = "//h3[text()='testsupplier040']/following-sibling::ul/li[2]/div/form/div/div[1]/following-sibling::div[1]/following-sibling::div[2]")
		private WebElement supplier2P2PriceTotal;

		@FindBy(xpath = "//h3[text()='testsupplier040']/following-sibling::ul/li[2]/div/form/div/div[1]/following-sibling::div[1]/following-sibling::div[3]/div/label/following-sibling::input")
		private WebElement supplier2P2Quantityty;

		// (Supplier2 P1 P2 Products Total:)
		@FindBy(xpath = "//h3[text()='testsupplier040']/following-sibling::div/ul/li[1]/span[2]")
		private WebElement supplier2P1P2ProduuctTotal;

		@FindBy(xpath = "//h3[text()='testsupplier040']/following-sibling::div/ul/li[2]/span[2]")
		private WebElement supplier2P1P2DeliveryTotal;

		@FindBy(xpath = "//h3[text()='testsupplier040']/following-sibling::div/ul/li[3]/span[2]")
		private WebElement supplier2P1P2VAT;

		@FindBy(xpath = "//h3[text()='testsupplier040']/following-sibling::div/ul/li[4]/span[2]")
		private WebElement supplier2P1P2GrandTotal;
		
		//Basket Page Title
		@FindBy(xpath = "//h1[text()='Basket']")
		private WebElement basketPageTitle;
		
		//Basket Page Breadcrumbs = Home
		@FindBy(xpath = "//a[text()='Home']")
		private WebElement basketPageBreadcrumbsHome;
		
		//Basket Page Breadcrumbs = Basket
		@FindBy(xpath = "//li[text()='Basket']")
		private WebElement basketPageBreadcrumBasket;
		
		//Add delivery note Locators
		@FindBy(xpath = ".//label[contains(text(),'    Delivery note (optional)')]")
		private WebElement deliveryNoteTextLabel;
			
		@FindBy(xpath = ".//label[contains(text(),'    Delivery note (optional)')]/following-sibling::textarea")
		private WebElement deliveryNoteTextareaIsVisible;
			
		@FindBy(xpath = ".//*[contains(text(),'Add a delivery note')]")
		private WebElement deliveryNotePageHeader;
		
		String deliveryNote = "Add a delivery note";
		
		String editdeliveryNote ="Edit the delivery note";
		
		@FindBy(xpath = ".//*[contains(text(),'You have 150 characters remaining')]")
		private WebElement deliveryNoteCharacterLimitMsg;
		
		@FindBy(xpath = ".//div[@id='special-instructions-info']")
		private WebElement errorMsgDeliveryNote;
		
		@FindBy(xpath = ".//*[contains(text(),'Edit the delivery note')]")
		private WebElement editTheDeliveryNoteLink;
		
		String cancelButton="Cancel";
		
		String saveButton="Save";
		
		@FindBy(xpath = ".//*[@class='govuk-body govuk-!-margin-bottom-3 bat-basket__delivery-note']")
		private WebElement deliveryNoteText;
		
		
				
	
	// Declare Variables
	String productsTotalSplit[];
	String productsTotalSplitActual;
	String deliveryTotalSplit[];
	String deliveryTotalSplitActual;
	

	// public void verifyProductDetailsOnBasketPage(Map<String, Object> pDetails) {
	public void verifyProductDetailsOnBasketPage(String path) {
		waitForSeconds(2);

		JSONObject jObj = new JSONObject(new JsonParser().convertJsonToString(path));
		// Retrieving the array
		JSONArray jsonArray = (JSONArray) jObj.getJSONArray("data");
		for (int i = 0; i < jsonArray.length(); i++) {

			JSONObject obj = jsonArray.getJSONObject(i);
			JSONObject attributes = obj.getJSONObject("attributes");
			int j = i + 1;
			// Product Price UI
			assertTrue(getTextXpath(
					"//*[@id='main-content']/div/div/div/div[1]/ul/li[" + j + "]/div/div/table/tbody/tr/td[5]")
							.replaceAll("[^a-zA-Z0-9]", "")
							.contains(attributes.getString("display_price").replaceAll("[^a-zA-Z0-9]", "")));

			// Product Name Validation
			assertTrue(getTextXpath("//*[@id='main-content']/div/div/div/div[1]/ul/li[" + j + "]/div/form/div/div[2]/a")
					.contains(attributes.getString("name")));

			// SKU Validation
			assertTrue(getTextXpath(
					"//*[@id='main-content']/div/div/div/div[1]/ul/li[" + j + "]/div/div/table/tbody/tr/td[2]")
							.contains(attributes.getString("SKU")));

			// MPN
			assertTrue(
					getTextXpath("//*[@id='main-content']/div/div/div/div[1]/ul/li[" + j + "]/div/form/div/div[2]/span")
							.contains(attributes.getString("mpn_number")));

			log.info("Validation completed on Basket Page UI");

		}

	}

	public void verifyMultipleProductsDetailsOnBasketPage() {
		waitForSeconds(2);

		int productNamelength = getText(productName).length();
		String productName1 = getText(productName).substring(17, productNamelength);
		// System.out.println("product1NameUI = " + productName + " jsonproductName = "
		// + apiBase.getvaluefromresponse("data[1].attributes.name",
		// WishListServiceStepDefs.jsonAllProductsResponse));
		int mpnlength = getText(mpn).length();
		String mpn1 = getText(mpn).substring(5, mpnlength);

		// Product1 Price UI
		// assertTrue(getText(productPrice).equals(apiBase.getvaluefromresponse("data[1].attributes.display_price",
		// WishListServiceStepDefs.jsonAllProductsResponse)));

		// Product1 Name Validation
		assertTrue(productName1.equals(apiBase.getvaluefromresponse("data[1].attributes.name",
				WishListServiceStepDefs.jsonAllProductsResponse)));

		// Product1 MPN
		assertTrue(mpn1.equals(apiBase.getvaluefromresponse("data[1].attributes.mpn_number",
				WishListServiceStepDefs.jsonAllProductsResponse)));

		// Second Product Validation
		int product2Namelength = getText(productName2).length();
		String product2Name = getText(productName2).substring(17, product2Namelength);

		int product2mpnlength = getText(mpn2).length();
		String product2mpn = getText(mpn2).substring(5, product2mpnlength);

		String[] productPrice2Split = getText(productPrice2).split(" ");
		String productPrice2UIActual = productPrice2Split[0];
		// Product2 Price UI
		assertTrue(productPrice2UIActual.equals(apiBase.getvaluefromresponse("data[2].attributes.display_price",
				WishListServiceStepDefs.jsonAllProductsResponse)));

		// Product2 Name Validation
		assertTrue(product2Name.equals(apiBase.getvaluefromresponse("data[2].attributes.name",
				WishListServiceStepDefs.jsonAllProductsResponse)));

		// Product2 MPN
		assertTrue(product2mpn.equals(apiBase.getvaluefromresponse("data[2].attributes.mpn_number",
				WishListServiceStepDefs.jsonAllProductsResponse)));

		log.info("Validation completed on Basket Page UI");

	}

	public void verifyMultipleProductsDetailsOnBasketPageAfterAddTheseItemsButton() {
		waitForSeconds(2);

		int productNamelength = getText(productName).length();
		String productName1 = getText(productName).substring(17, productNamelength);
		// System.out.println("productNameUI = " + productName + " jsonproductName = " +
		// apiBase.getvaluefromresponse("data[0].attributes.name",
		// WishListServiceStepDefs.jsonAllProductsResponse));

		int mpnlength = getText(mpn).length();
		String mpn1 = getText(mpn).substring(5, mpnlength);

		String[] productPriceSplit = getText(productUnitPrice).split(" ");
		String productPriceUIActual = productPriceSplit[0];

		// Product1 Price UI
		assertTrue(productPriceUIActual.equals(apiBase.getvaluefromresponse("data[0].attributes.display_price",
				WishListServiceStepDefs.jsonAllProductsResponse)));

		// Product1 Name Validation
		assertTrue(productName1.equals(apiBase.getvaluefromresponse("data[0].attributes.name",
				WishListServiceStepDefs.jsonAllProductsResponse)));

		// MPN1
		assertTrue(mpn1.equals(apiBase.getvaluefromresponse("data[0].attributes.mpn_number",
				WishListServiceStepDefs.jsonAllProductsResponse)));

		// PRODUCT2 Validation
		int product1Namelength = getText(productName2).length();
		String product1Name = getText(productName2).substring(17, product1Namelength);

		int product1mpnlength = getText(mpn2).length();
		String product1mpn = getText(mpn2).substring(5, product1mpnlength);

		String[] productPrice2Split = getText(productPrice2).split(" ");
		String productPriceUIActual2 = productPrice2Split[0];

		// Product2 Price UI
		assertTrue(productPriceUIActual2.equals(apiBase.getvaluefromresponse("data[1].attributes.display_price",
				WishListServiceStepDefs.jsonAllProductsResponse)));

		// Product2 Name Validation
		assertTrue(product1Name.equals(apiBase.getvaluefromresponse("data[1].attributes.name",
				WishListServiceStepDefs.jsonAllProductsResponse)));

		// Product2 MPN
		assertTrue(product1mpn.equals(apiBase.getvaluefromresponse("data[1].attributes.mpn_number",
				WishListServiceStepDefs.jsonAllProductsResponse)));

		// PRODUCT3 Validation
		int product2Namelength = getText(productName3).length();
		String product2Name = getText(productName3).substring(17, product2Namelength);

		int product2mpnlength = getText(mpn3).length();
		String product2mpn = getText(mpn3).substring(5, product2mpnlength);

		String[] productPrice3Split = getText(productPrice3).split(" ");
		String productPriceUIActual3 = productPrice3Split[0];

		// Product3 Price UI
		assertTrue(productPriceUIActual3.equals(apiBase.getvaluefromresponse("data[2].attributes.display_price",
				WishListServiceStepDefs.jsonAllProductsResponse)));

		// Product3 Name Validation
		assertTrue(product2Name.equals(apiBase.getvaluefromresponse("data[2].attributes.name",
				WishListServiceStepDefs.jsonAllProductsResponse)));

		// Product3 MPN
		assertTrue(product2mpn.equals(apiBase.getvaluefromresponse("data[2].attributes.mpn_number",
				WishListServiceStepDefs.jsonAllProductsResponse)));

		log.info("Validation completed on Basket Page UI");

	}

	public void verifyProductDetailsOnBasketPage() {
		waitForSeconds(2);

		int productNamelength = getText(productName).length();
		String productNames = getText(productName).substring(17, productNamelength);
		// System.out.println("productNameUI = " + productName + " jsonproductName = " +
		// apiBase.getvaluefromresponse("data[0].attributes.name",
		// WishListServiceStepDefs.jsonAllProductsResponse));
		String[] productPrice = getText(productUnitPrice).split("each");
		String productPriceSplit = productPrice[0].replaceAll("\\s", "");
		int mpnlength = getText(mpn).length();
		String mpns = getText(mpn).substring(5, mpnlength);

		//System.out.println("JSON Body Validation: " + WishListServiceStepDefs.jsonAllProductsResponse.getBody().asString());
		// Product Price UI
		assertTrue(productPriceSplit.equals(apiBase.getvaluefromresponse("data[0].attributes.display_price",
				WishListServiceStepDefs.jsonAllProductsResponse)));

		// Product Name Validation
		assertTrue(productNames.equals(apiBase.getvaluefromresponse("data[0].attributes.name",
				WishListServiceStepDefs.jsonAllProductsResponse)));

		// MPN
		assertTrue(mpns.equals(apiBase.getvaluefromresponse("data[0].attributes.mpn_number",
				WishListServiceStepDefs.jsonAllProductsResponse)));

		log.info("Product is successfully added and validated on Basket Page");

		// TakeScreenShotAndAddToWordDoc.captureScreenShotNew();

	}

	public void verifyProductMessageOnBasketPage() throws IOException {
		waitForSeconds(4);
		// Product Price UI
		assertTrue(getText(basketPageMsgAfterClearBasket).equals("All products were removed from the basket."));
		boolean checkProductNotPresent = isElementPresentByXpath(checkProductPresentOrNot);

		if (checkProductNotPresent == false) {
			assertTrue("All Products are cleared from basket", true);
		} else {

			assertTrue("All Products are Not cleared from basket", false);
		}

		// TakeScreenShotAndAddToWordDoc.captureScreenShotNew();
		// TakeScreenShotAndAddToWordDoc.writeScreenShot();
		// TakeScreenShotAndAddToWordDoc.writeDoc();
		waitForSeconds(2);
	}

	public void verifyBlueBanneredBox(Map<String, Object> pDetails) throws IOException {
		waitForSeconds(2);
		assertTrue(getText(notificationBlueBanner).equals("Important"));
		assertTrue(getText(notificationBlueBannerSummaryText).equals(configReaderObj.get("notificationBlueBannerSummaryTextMsg")));
		clickElementXpath(notificationBlueBannerSummaryText);
		assertTrue(getText(notificationBlueBannerProductDetail).equals(pDetails.get("ProductName").toString()+ " from sftpsupplier2021 had it's stock reduced from 5 to the maximum stock available from suppliers. The suppliers providing the product were updated."));
	}

	public void verifyProductReducedQuantityOnBasketPage() throws IOException {
		waitForSeconds(2);
		assertTrue(getAttributeValue(productQuantityOnBasket).equals("4"));

	}

	public void verifyBasketGenericMessageOnBasketPage(String genericMsg) throws IOException {
		waitForSeconds(2);
		assertTrue(getText(basketPageMsgAfterClearBasket).equals(genericMsg));
		isElementPresentByXpath(basketPageMsgAfterClearBasket);
		waitForSeconds(2);
	}

	public void verifyBasketLinkCount(String basketCount) throws IOException {
		waitForSeconds(2);
		// Verify Basket Link qty
		assertTrue(getText(basketLink).equals("Basket: " + basketCount));
		log.info("Validated basket link product quantity " + getText(basketLink) + " successfully");
	}

	public void validateWarningMessageIsHiddenOnBasketPage() {

		boolean mgsNotVisible = isElementPresentByXpath(basketWarningMsgOfNextDayDeliveryString);
		if (mgsNotVisible == false) {
			assertFalse("Warning message is hidden on basket when 'Next Day Delivery' option is available",
					mgsNotVisible);
			log.info("Warning message is hidden on basket when 'Next Day Delivery' option is available");
		} else {
			assertFalse(getText(basketWarningMsgOfNextDayDelivery) + " is visible", mgsNotVisible);
		}
	}

	public boolean validateWarningMessageIsPresentOrNot() {

		boolean mgsNotVisible = isElementPresent("Your basket is empty");

		return mgsNotVisible;
	}

	public void validateNextDayDeliveryIsGreyedOutOnBasketPage() {

		boolean nexBusinessDayIsGreyedOut = isElementPresentByXpath(basketWarningMsgOfNextDayDeliveryString);
		assertTrue("'Next Business Day (Orders after Midday)' option is greyed out ", nexBusinessDayIsGreyedOut);
		log.info("'Next Business Day (Orders after Midday)' option is greyed out in Delivery method dropdown");

	}

	public void validateWarningMessageIsVisiblenOnBasketPage() {

		assertTrue(getText(basketWarningMsgOfNextDayDelivery).equals(configReaderObj.get("warningMessageNextDay")));
		log.info("Warning message: " + getText(basketWarningMsgOfNextDayDelivery) + " is validated");
	}

	public void verifyProductMessageAndClearBasketButttonOnBasketPage(String msg) {
		waitForSeconds(2);

		assertTrue(getText(basketPageYourbasketIsEmptyMsg).equals(msg));
		boolean checkClearBasketBtnNotPresent = isElementPresentByXpath(checkClearBasketBtn);

		if (checkClearBasketBtnNotPresent == false) {
			assertTrue("Clear Basket button is not visible on basket page", true);
		} else {

			assertTrue("Clear Basket button is visible on basket page", false);
		}
	}

	public void verifyTheDefaultDeliveryOption(String defaultDeliveryOption) {
		assertEquals(getDropDownDefaultSelectedOption(deliveryMethodDropDown), defaultDeliveryOption);
		log.info("Validated default value selected in Delivery Method drop down is " + defaultDeliveryOption
				+ " on Basket Page UI");
	}

	public void verifyAllTheOptionsInDeliveryOptionDropDown() {

		List<WebElement> allValues = getAllValuesOfDropDown(deliveryMethodDropDown);
		// Get the length
		System.out.println(allValues.size());
		// Creating arraylist
		ArrayList<String> listDeliveryOptions = new ArrayList<String>();
		// Adding object in arraylist
		listDeliveryOptions.add("Standard UK Mainland (3-5 days)");
		listDeliveryOptions.add("Standard UK Non Mainland (3-5 days)");
		listDeliveryOptions.add("Next Business Day (Orders before Midday)");

		// Loop to print one by one
		for (int j = 0; j < allValues.size(); j++) {
			System.out.println(allValues.get(j).getText().equals(listDeliveryOptions.get(j)));
			assertEquals(allValues.get(j).getText(), listDeliveryOptions.get(j));

		}

		log.info("Validated all Delivery Method on Basket Page UI");
	}

	public void verifyMessageProductAddToBasketMsg() {

		assertEquals(getText(productAddToBasketMsg), "Product added to your basket");
	}

	public void verifyProductUpdatedQuantityOnBasket() {

		assertEquals(getAttributeValue(productQuantity), "2");
	}

	public void VerifyMessageItsOutOfStock() {
		String contents = getText(YouCannotAddTheSelectedProductToTheBasketItsOutOfStockMsg);
		String[] lines = contents.split("\\r?\\n");
		System.out.println("OUT Of Stock SPLIT 0 " + lines[0]);
		System.out.println("OUT Of Stock SPLIT 1 " + lines[1]);
		System.out.println("OUT Of Stock MGS " + getText(YouCannotAddTheSelectedProductToTheBasketItsOutOfStockMsg));

		assertEquals(lines[1], "You cannot add the selected product to the basket. It's out of stock.");
	}

	public void verifyProductPriceDetails(Map<String, Object> pDetails) {
		waitForSeconds(2);

		System.out.println("JSON PRODUCT PRICE : " + pDetails.get("Price").toString());
		// System.out.println("UI PRODUCT PRICE :
		// "+getText(productPrice).replaceAll("[^a-zA-Z0-9]",""));
		System.out.println("UI PRODUCT PRICE : " + getText(productUnitPrice).replaceAll("�", ""));
		assertTrue(getText(productUnitPrice).replaceAll("�", "").contains(pDetails.get("Price").toString()));
		/*
		 * assertEquals(getText(mpnNumber), pDetails.get("MPN").toString());
		 * assertEquals(getText(manufaturer),
		 * pDetails.get("ManufacturerName").toString());
		 */
		log.info("Validation completed for Price on buyer UI");
	}

	public void updateTheProductQuantity(String quantity) {
		waitForSeconds(2);
		enterText(productQuantity, quantity);
		log.info("User enters the product quantity");
	}

	public void selectTheDeliveryMethod(String deliveryMethod) {
		waitForSeconds(1);
		selectItemFromDropDown(webElementDeliveryMethodDropDown, deliveryMethod);
		clickElement(basketUpdateButton);
		waitForSeconds(1);
	}
	
	public void selectTheAddress(String address) {
		waitForSeconds(1);
		selectItemFromDropDown(deliveryAddressDropDown, address);
		clickElement(basketUpdateDeliveryAndInvoiceChoicesBtn);
		waitForSeconds(1);
	}
	
	public void checkTitelBasket(String basketTitle) {
		waitForSeconds(1);
		assertTrue(basketTitle.equals(getText(basketPageTitle)));
		waitForSeconds(1);
	}
	
	public void checkBreadcrumbsBasketPage(String Home, String Basket) {
		waitForSeconds(1);
		assertTrue(Home.equals(getText(basketPageBreadcrumbsHome)));
		assertTrue(Basket.equals(getText(basketPageBreadcrumBasket)));
		waitForSeconds(1);
	}

	public void verifyProductDeliveryCostDetails(Map<String, Object> pDetails) {
		waitForSeconds(1);

		String[] productTotalPriceUISplit;
		String productTotalPriceUIActual;
		String[] productUnitPriceUISplit;
		String productUnitPriceUIActual;
		String[] getDeviveryChargeSplit;
		String deliveryTotalActualValue;

		// 1) Check the Delivery charge for Delivery method "Standard UK Non Mainland
		// (3-5 days)"
		selectItemFromDropDown(webElementDeliveryMethodDropDown, "Standard UK Non Mainland (3-5 days)");
		clickElement(basketUpdateButton);
		waitForSeconds(2);
		getDeviveryChargeSplit = getText(getDeviveryCharge).split("ex");
		deliveryTotalActualValue = getDeviveryChargeSplit[0].replace("�", "").replaceAll("\\s", "");

		// System.out.println("JSON PRODUCT Standard UK Non Mainland (3-5 days) : "+
		// pDetails.get("StandardChargeProductUKNonMainland").toString());
		// System.out.println("UI PRODUCT Standard UK Non Mainland (3-5 days) : "+
		// deliveryTotalActualValue);

		// Validate the Delivery Total
		assertTrue(deliveryTotalActualValue
				.equals(pDetails.get("StandardChargeProductUKNonMainland").toString().replaceAll("\\s", "")));
		// Remove the unnecessary text "MPN: " from the string
		String[] splitMPN = getText(mpnBasket).split(" ");
		String actMPN = splitMPN[1];
		// Remove the unnecessary text "SKU: " from the string
		String[] splitSKU = getText(skuBasket).split(" ");
		String actSKU = splitSKU[1];

		assertTrue(actMPN.equals(pDetails.get("MPN").toString()));
		assertTrue(actSKU.equals(pDetails.get("SKU").toString()));
		assertTrue(getAttributeValue(qtyBasket).equals("2"));

		// Double the price as per quantity 2 for validation
		String productUnitPriceStr = pDetails.get("Price").toString();
		double productUnitPriceDouble = Double.parseDouble(productUnitPriceStr);
		double productTotalPriceDouble = productUnitPriceDouble * 2;
		String productTotalPriceString = String.valueOf(productTotalPriceDouble);
		// System.out.println("JSON productTotalPriceString : "+
		// productTotalPriceString);

		// Remove the unnecessary text "ex. VAT" from the string Product total
		productsTotalSplit = getText(productsTotal).split("e");
		productsTotalSplitActual = productsTotalSplit[0];
		// Remove the unnecessary text "ex. VAT" from the string Delivery total
		deliveryTotalSplit = getText(deliveryTotal).split("e");
		deliveryTotalSplitActual = deliveryTotalSplit[0];

		// Remove the unnecessary text from TotalPrice string
		productTotalPriceUISplit = getText(productTotalPrice).split(":");
		productTotalPriceUIActual = productTotalPriceUISplit[1].replaceAll("\\s", "");

		// Remove the unnecessary text from UnitPrice string
		productUnitPriceUISplit = getText(productUnitPrice).split("each");
		productUnitPriceUIActual = productUnitPriceUISplit[0].replaceAll("\\s", "");

		// Validate the UnitPrice, Total, Products total, and Delivery Total
		assertTrue(productTotalPriceUIActual.replaceAll("[^a-zA-Z0-9]", "")
				.equals(productTotalPriceString.concat("0").replaceAll("[^a-zA-Z0-9]", "")));
		assertTrue(productUnitPriceUIActual.replaceAll("[^a-zA-Z0-9]", "")
				.equals(pDetails.get("Price").toString().concat("0").replaceAll("[^a-zA-Z0-9]", "")));
		assertTrue(productsTotalSplitActual.replaceAll("[^a-zA-Z0-9]", "")
				.equals(productTotalPriceString.concat("0").replaceAll("[^a-zA-Z0-9]", "")));
		assertTrue(deliveryTotalSplitActual.replaceAll("[^a-zA-Z0-9]", "").contains(
				pDetails.get("StandardChargeProductUKNonMainland").toString().replaceAll("[^a-zA-Z0-9]", "")));

		// 2) Check the Delivery charge for Delivery method "Next Business Day (Oders
		// after Midday)"
		selectItemFromDropDown(webElementDeliveryMethodDropDown, "Next Business Day (Orders after Midday)");
		clickElement(basketUpdateButton);
		waitForSeconds(2);
		getDeviveryChargeSplit = getText(getDeviveryCharge).split("ex");
		deliveryTotalActualValue = getDeviveryChargeSplit[0].replace("�", "").replaceAll("\\s", "");

		// System.out.println("JSON PRODUCT Next Business Day (Orders after Midday) : "+
		// pDetails.get("nextDayChargeProduct").toString());
		// System.out.println("UI PRODUCT Next Business Day (Orders after Midday) : "+
		// deliveryTotalActualValue);

		// Validate the Delivery Total
		assertTrue(deliveryTotalActualValue.equals(pDetails.get("nextDayChargeProduct").toString()));

		// Remove the unnecessary text "ex. VAT" from the string Product total
		productsTotalSplit = getText(productsTotal).split("e");
		productsTotalSplitActual = productsTotalSplit[0];
		// Remove the unnecessary text "ex. VAT" from the string Delivery total
		deliveryTotalSplit = getText(deliveryTotal).split("e");
		deliveryTotalSplitActual = deliveryTotalSplit[0];

		// Remove the unnecessary text from TotalPrice string
		productTotalPriceUISplit = getText(productTotalPrice).split(":");
		productTotalPriceUIActual = productTotalPriceUISplit[1].replaceAll("\\s", "");

		// Remove the unnecessary text from UnitPrice string
		productUnitPriceUISplit = getText(productUnitPrice).split("each");
		productUnitPriceUIActual = productUnitPriceUISplit[0].replaceAll("\\s", "");

		// Validate the UnitPrice, Total, Products total, and Delivery Total
		assertTrue(productTotalPriceUIActual.replaceAll("[^a-zA-Z0-9]", "")
				.equals(productTotalPriceString.concat("0").replaceAll("[^a-zA-Z0-9]", "")));
		assertTrue(productUnitPriceUIActual.replaceAll("[^a-zA-Z0-9]", "")
				.equals(pDetails.get("Price").toString().concat("0").replaceAll("[^a-zA-Z0-9]", "")));

		assertTrue(productsTotalSplitActual.replaceAll("[^a-zA-Z0-9]", "")
				.equals(productTotalPriceString.concat("0").replaceAll("[^a-zA-Z0-9]", "")));
		assertTrue(deliveryTotalSplitActual.replaceAll("[^a-zA-Z0-9]", "").contains(
				pDetails.get("StandardChargeProductUKNonMainland").toString().replaceAll("[^a-zA-Z0-9]", "")));

		// 3) Check the Delivery charge for Delivery method "Standard UK Mainland (3-5
		// days)"
		selectItemFromDropDown(webElementDeliveryMethodDropDown, "Standard UK Mainland (3-5 days)");
		clickElement(basketUpdateButton);
		waitForSeconds(2);
		getDeviveryChargeSplit = getText(getDeviveryCharge).split("ex");
		deliveryTotalActualValue = getDeviveryChargeSplit[0].replace("�", "").replaceAll("\\s", "");

		// System.out.println("JSON PRODUCT StandardChargeProductUKMainland : "+
		// pDetails.get("StandardChargeProductUKMainland").toString());
		// System.out.println("UI PRODUCT StandardChargeProductUKMainland :
		// "+getText(getDeviveryCharge).replace("�", ""));

		// Validate the Delivery Total
		assertTrue(deliveryTotalActualValue.equals(pDetails.get("StandardChargeProductUKMainland").toString()));

		// Remove the unnecessary text "ex. VAT" from the string Product total
		productsTotalSplit = getText(productsTotal).split("e");
		productsTotalSplitActual = productsTotalSplit[0];
		// Remove the unnecessary text "ex. VAT" from the string Delivery total
		deliveryTotalSplit = getText(deliveryTotal).split("e");
		deliveryTotalSplitActual = deliveryTotalSplit[0];

		// Remove the unnecessary text from TotalPrice string
		productTotalPriceUISplit = getText(productTotalPrice).split(":");
		productTotalPriceUIActual = productTotalPriceUISplit[1].replaceAll("\\s", "");

		// Remove the unnecessary text from UnitPrice string
		productUnitPriceUISplit = getText(productUnitPrice).split("each");
		productUnitPriceUIActual = productUnitPriceUISplit[0].replaceAll("\\s", "");

		// Validate the UnitPrice, Total, Products total, and Delivery Total
		assertTrue(productTotalPriceUIActual.replaceAll("[^a-zA-Z0-9]", "")
				.equals(productTotalPriceString.concat("0").replaceAll("[^a-zA-Z0-9]", "")));
		assertTrue(productUnitPriceUIActual.replaceAll("[^a-zA-Z0-9]", "")
				.equals(pDetails.get("Price").toString().concat("0").replaceAll("[^a-zA-Z0-9]", "")));

		assertTrue(productsTotalSplitActual.replaceAll("[^a-zA-Z0-9]", "")
				.equals(productTotalPriceString.concat("0").replaceAll("[^a-zA-Z0-9]", "")));
		assertTrue(deliveryTotalSplitActual.replaceAll("[^a-zA-Z0-9]", "").contains(
				pDetails.get("StandardChargeProductUKNonMainland").toString().replaceAll("[^a-zA-Z0-9]", "")));

		log.info("Validation completed for All Delivery Method Delivery charges on buyer UI");
	}

	public void verifyProductDetailsOmMyListPage(Map<String, Object> pDetails) {
		waitForSeconds(2);

		System.out.println("JSON PRODUCT PRICE : " + pDetails.get("Price").toString());
		System.out.println("UI PRODUCT PRICE : " + getText(productUnitPrice).replaceAll("[^a-zA-Z0-9]", ""));
		assertTrue(getText(productUnitPrice).replaceAll("[^a-zA-Z0-9]", "")
				.contains(pDetails.get("Price").toString().replaceAll("[^a-zA-Z0-9]", "")));
		/*
		 * assertEquals(getText(mpnNumber), pDetails.get("MPN").toString());
		 * assertEquals(getText(manufaturer),
		 * pDetails.get("ManufacturerName").toString());
		 */
		log.info("Validation completed for Price on buyer UI");
	}

	public void verifyMessageAllProductsWereAddedToYourBasket() {

		assertEquals(getText(allProductsWereAddedToYourBasket), "All products were added to your basket");
	}

	public void validateSupplier1Product1and2VATAndOtherDetailsForStandardUKMainland(JSONObject jObj1,
			JSONObject jObj2) {
		waitForSeconds(2);

		// Get Supplier1 Product1 Price value from Json
		String Sup1P1Price = jObj1.get("Price").toString();
		double intSup1P1Price = Double.parseDouble(Sup1P1Price);

		// Get Supplier1 Product1 StandardChargeProductUKMainland value from Json
		String Sup1P1StandUKMainlandDeliveryCharges = jObj1.get("StandardChargeProductUKMainland").toString();
		double intSup1P1StandUKMainlandDeliveryCharges = Double.parseDouble(Sup1P1StandUKMainlandDeliveryCharges);

		// Calculate Supplier1 Product1 VAT
		double Sup1P1ProductVAT = intSup1P1Price * 20 / 100;

		// Calculate Supplier1 Product1 DeliveryCharge
		double Sup1P1ProductDeliveryChargesVAT = intSup1P1StandUKMainlandDeliveryCharges * 20 / 100;

		// Get Supplier1 Product2 Price value from Json
		String Sup1P2Price = jObj2.get("Price").toString();
		double intSup1P2Price = Double.parseDouble(Sup1P2Price);

		// Get Supplier1 Product2 StandardChargeProductUKMainland value from Json
		String Sup1P2StandUKMainlandDeliveryCharges = jObj2.get("StandardChargeProductUKMainland").toString();
		double intSup1P2StandUKMainlandDeliveryCharges = Double.parseDouble(Sup1P2StandUKMainlandDeliveryCharges);

		// Calculate Supplier1 Product2 VAT
		double Sup1P2ProductVAT = intSup1P2Price * 0 / 100;

		// Calculate Supplier1 Product2 DeliveryCharge
		double Sup1P2ProductDeliveryChargesVAT = intSup1P2StandUKMainlandDeliveryCharges * 20 / 100;

		// Calculate Products total, Delivery total and VAT from Json
		double productsTotalDbl = intSup1P1Price + intSup1P2Price;
		System.out.println(productsTotalDbl);
		double deliveryTotalDbl = intSup1P1StandUKMainlandDeliveryCharges + intSup1P2StandUKMainlandDeliveryCharges;
		System.out.println(deliveryTotalDbl);
		double vatTotalDbl = Sup1P1ProductVAT + Sup1P1ProductDeliveryChargesVAT + Sup1P2ProductVAT
				+ Sup1P2ProductDeliveryChargesVAT;
		System.out.println(vatTotalDbl);
		double grandTotalDbl = productsTotalDbl + deliveryTotalDbl + vatTotalDbl;
		System.out.println(grandTotalDbl);

		// Now get the value of Supplier1 productsTotal, deliveryTotal, vatTotal and
		// GrandTotal from Buyers UI
		assertTrue(getText(supplier1ProductsTotal).equals("�" + String.valueOf(productsTotalDbl) + "0 ex. VAT"));
		assertTrue(getText(supplier1DeliveryTotal).equals("�" + String.valueOf(deliveryTotalDbl) + "0 ex. VAT"));
		assertTrue(getText(supplier1VatTotal).equals("�" + String.valueOf(vatTotalDbl).substring(0, 4)));
		assertTrue(getText(supplier1GrandTotal).equals("�" + String.valueOf(grandTotalDbl) + "0 inc. VAT"));

		// Now get the value of footer productsTotal, deliveryTotal, vatTotal and
		// GrandTotal from Buyers UI
		assertTrue(getText(productsTotal).equals("�" + String.valueOf(productsTotalDbl) + "0 ex. VAT"));
		assertTrue(getText(deliveryTotal).equals("�" + String.valueOf(deliveryTotalDbl) + "0 ex. VAT"));
		assertTrue(getText(vatTotal).equals("�" + String.valueOf(vatTotalDbl).substring(0, 4)));
		assertTrue(getText(grandTotal).equals("�" + String.valueOf(grandTotalDbl) + "0 inc. VAT"));

		// Product qty Check (Plz Not add qty more than 1)
		assertTrue(getAttributeValue(supplier1P1Qty).equals("1"));
		assertTrue(getAttributeValue(supplier1P2Qty).equals("1"));

		// We added below if condition twice because while adding the product to Basket
		// we don't know which product will come in first position P1 or P2. Its position will keep on changing, 
		// every time we add into the basket
		
		// Product1 Price for qty 1
		String[] supplier1P1PriceSplit = getText(supplier1P1Price).split(" ");
				
		if(supplier1P1PriceSplit[0].equals("�2.00")) {
			assertTrue(supplier1P1PriceSplit[0].equals("�" + jObj1.get("Price").toString()));
			
		}else{
			assertTrue(supplier1P1PriceSplit[0].equals("�" + jObj2.get("Price").toString()));
		}
		
		// Product2 Price for qty 1
		String[] supplier1P2PriceSplit = getText(supplier1P2Price).split(" ");
		
		if(supplier1P2PriceSplit[0].equals("�2.00")) {
			assertTrue(supplier1P1PriceSplit[0].equals("�" + jObj1.get("Price").toString()));
			
		}else{
				
			assertTrue(supplier1P2PriceSplit[0].equals("�" + jObj2.get("Price").toString()));
		}
	
	}
	
	
	public void validateSupplier1Product1DetailsForStandardUKMainland(JSONObject jObj1) {
		waitForSeconds(2);

		// Get Supplier1 Product1 Price value from Json
		String Sup1P1Price = jObj1.get("Price").toString();
		double intSup1P1Price = Double.parseDouble(Sup1P1Price);

		// Get Supplier1 Product1 StandardChargeProductUKMainland value from Json
		String Sup1P1StandUKMainlandDeliveryCharges = jObj1.get("StandardChargeProductUKMainland").toString();
		double intSup1P1StandUKMainlandDeliveryCharges = Double.parseDouble(Sup1P1StandUKMainlandDeliveryCharges);

		// Calculate Supplier1 Product1 VAT
		double Sup1P1ProductVAT = intSup1P1Price * 20 / 100;

		// Calculate Supplier1 Product1 DeliveryCharge
		double Sup1P1ProductDeliveryChargesVAT = intSup1P1StandUKMainlandDeliveryCharges * 20 / 100;

		// Calculate Products total, Delivery total and VAT from Json
		double productsTotalDbl = intSup1P1Price;
		//System.out.println(productsTotalDbl);
		double deliveryTotalDbl = intSup1P1StandUKMainlandDeliveryCharges;
		//System.out.println(deliveryTotalDbl);
		double vatTotalDbl = Sup1P1ProductVAT + Sup1P1ProductDeliveryChargesVAT;
		//System.out.println(vatTotalDbl);
		double grandTotalDbl = productsTotalDbl + deliveryTotalDbl + vatTotalDbl;
		//System.out.println(grandTotalDbl);

		// Now get the value of Supplier1 productsTotal, deliveryTotal, vatTotal and
		// GrandTotal from Buyers UI
		assertTrue(getText(supplier1ProductsTotal).equals("�" + String.valueOf(productsTotalDbl) + "0 ex. VAT"));
		assertTrue(getText(supplier1DeliveryTotal).equals("�" + String.valueOf(deliveryTotalDbl) + "0 ex. VAT"));
		assertTrue(getText(supplier1VatTotal).equals("�" + String.valueOf(vatTotalDbl).substring(0, 4)));
		assertTrue(getText(supplier1GrandTotal).equals("�" + String.valueOf(grandTotalDbl) + "0 inc. VAT"));

		// Now get the value of footer productsTotal, deliveryTotal, vatTotal and
		// GrandTotal from Buyers UI
		assertTrue(getText(productsTotal).equals("�" + String.valueOf(productsTotalDbl) + "0 ex. VAT"));
		assertTrue(getText(deliveryTotal).equals("�" + String.valueOf(deliveryTotalDbl) + "0 ex. VAT"));
		assertTrue(getText(vatTotal).equals("�" + String.valueOf(vatTotalDbl).substring(0, 4)));
		assertTrue(getText(grandTotal).equals("�" + String.valueOf(grandTotalDbl) + "0 inc. VAT"));

		// Product qty Check (Plz Not add qty more than 1)
		assertTrue(getAttributeValue(supplier1P1Qty).equals("1"));
		
		// Product Price for qty 1
		String[] supplier1P1PriceSplit = getText(supplier1P1Price).split(" ");
		assertTrue(supplier1P1PriceSplit[0].equals("�" + jObj1.get("Price").toString()));
		
		productDetailsCheckout.put("productTotal", getText(productsTotal));
		productDetailsCheckout.put("deliveryTotal", getText(deliveryTotal));
		productDetailsCheckout.put("vatTotal", getText(vatTotal));
		productDetailsCheckout.put("grandTotal", getText(grandTotal));

	}


	public void validateIndicativeQuoteForStandardUKMainland(JSONObject jObj1, JSONObject jObj2, JSONObject jObj3) {
		waitForSeconds(2);

		// Get Supplier2 Product1 Price value from Json
		String Sup1P1Price = jObj1.get("Price").toString();
		double intSup1P1Price = Double.parseDouble(Sup1P1Price);

		// Get Supplier2 Product1 StandardChargeProductUKMainland value from Json
		String Sup1P1StandUKMainlandDeliveryCharges = jObj1.get("StandardChargeProductUKMainland").toString();
		double intSup1P1StandUKMainlandDeliveryCharges = Double.parseDouble(Sup1P1StandUKMainlandDeliveryCharges);

		// Calculate Supplier2 Product1 VAT
		double Sup1P1ProductVAT = intSup1P1Price * 20 / 100;

		// Calculate Supplier2 Product1 DeliveryCharge
		double Sup1P1ProductDeliveryChargesVAT = intSup1P1StandUKMainlandDeliveryCharges * 20 / 100;

		// Get Supplier2 Product2 Price value from Json
		String Sup1P2Price = jObj2.get("Price").toString();
		double intSup1P2Price = Double.parseDouble(Sup1P2Price);

		// Get Supplier2 Product2 StandardChargeProductUKMainland value from Json
		String Sup1P2StandUKMainlandDeliveryCharges = jObj2.get("StandardChargeProductUKMainland").toString();
		double intSup1P2StandUKMainlandDeliveryCharges = Double.parseDouble(Sup1P2StandUKMainlandDeliveryCharges);

		// Calculate Supplier2 Product2 VAT
		double Sup1P2ProductVAT = intSup1P2Price * 0 / 100;

		// Calculate Supplier2 Product2 DeliveryCharge
		double Sup1P2ProductDeliveryChargesVAT = intSup1P2StandUKMainlandDeliveryCharges * 20 / 100;

		// Supplier2 "testsupplier039" Calculate Products total, Delivery total and VAT from Json
		double productsTotalDbl = intSup1P1Price + intSup1P2Price;
		System.out.println(productsTotalDbl);
		double deliveryTotalDbl = intSup1P1StandUKMainlandDeliveryCharges + intSup1P2StandUKMainlandDeliveryCharges;
		System.out.println(deliveryTotalDbl);
		double vatTotalDbl = Sup1P1ProductVAT + Sup1P1ProductDeliveryChargesVAT + Sup1P2ProductVAT	+ Sup1P2ProductDeliveryChargesVAT;
		System.out.println(vatTotalDbl);
		double grandTotalDbl = productsTotalDbl + deliveryTotalDbl + vatTotalDbl;
		System.out.println(grandTotalDbl);

		// Now get the value of Supplier2 productsTotal, deliveryTotal, vatTotal and
		// GrandTotal from Buyers UI
		assertTrue(getText(supplier2ProductsTotal).equals("�" + String.valueOf(productsTotalDbl) + "0 ex. VAT"));
		assertTrue(getText(supplier2DeliveryTotal).equals("�" + String.valueOf(deliveryTotalDbl) + "0 ex. VAT"));
		assertTrue(getText(supplier2VatTotal).equals("�" + String.valueOf(vatTotalDbl).substring(0, 4)));
		assertTrue(getText(supplier2GrandTotal).equals("�" + String.valueOf(grandTotalDbl) + "0 inc. VAT"));
		// Product qty Check (Plz Not add qty more than 1)
		assertTrue(getAttributeValue(supplier2P1Qty).equals("1"));
		assertTrue(getAttributeValue(supplier2P2Qty).equals("1"));

		// Product Price for qty 1
		String[] supplier2P1PriceSplit = getText(supplier2P1Price).split(" ");
		assertTrue(supplier2P1PriceSplit[0].equals("�" + jObj1.get("Price").toString()));

		String[] supplier2P2PriceSplit = getText(supplier2P2Price).split(" ");
		assertTrue(supplier2P2PriceSplit[0].equals("�" + jObj2.get("Price").toString()));

	
		// supplier1 "testsupplier040" Product 1
		// Get Supplier1 Product1 Price value from Json
		String Sup2P1Price = jObj3.get("Price").toString();
		double intSup2P1Price = Double.parseDouble(Sup2P1Price);

		// Get Supplier1 Product1 StandardChargeProductUKMainland value from Json
		String Sup2P1StandUKMainlandDeliveryCharges = jObj3.get("StandardChargeProductUKMainland").toString();
		double intSup2P1StandUKMainlandDeliveryCharges = Double.parseDouble(Sup2P1StandUKMainlandDeliveryCharges);

		// Calculate Supplier1 Product1 VAT
		double Sup2P1ProductVAT = intSup2P1Price * 20 / 100;
		System.out.println(Sup2P1ProductVAT);

		// Calculate Supplier1 Product1 DeliveryCharge
		double Sup2P1ProductDeliveryChargesVAT = intSup2P1StandUKMainlandDeliveryCharges * 20 / 100;

		System.out.println(Sup2P1ProductDeliveryChargesVAT);
		
		
		// Supplier2 "testsupplier039" Calculate Products total, Delivery total and VAT from Json
		double productsTotalDblSup1 = intSup2P1Price;
		System.out.println(productsTotalDblSup1);
		double deliveryTotalDblSup1 = intSup2P1StandUKMainlandDeliveryCharges;
		System.out.println(deliveryTotalDblSup1);
		double vatTotalDblSup1 = Sup2P1ProductVAT + Sup2P1ProductDeliveryChargesVAT;
		System.out.println(vatTotalDblSup1);
		double grandTotalDblSup1 = productsTotalDblSup1 + deliveryTotalDblSup1 + vatTotalDblSup1;
		System.out.println(grandTotalDblSup1);
		
		// Now get the value of Supplier1 productsTotal, deliveryTotal, vatTotal and
		assertTrue(getText(supplier1ProductsTotal).equals("�" + String.valueOf(productsTotalDblSup1) + "0 ex. VAT"));
		assertTrue(getText(supplier1DeliveryTotal).equals("�" + String.valueOf(deliveryTotalDblSup1) + "0 ex. VAT"));
		assertTrue(getText(supplier1VatTotal).equals("�" + String.valueOf(vatTotalDblSup1)+"0" ));
		assertTrue(getText(supplier1GrandTotal).equals("�" + String.valueOf(grandTotalDblSup1) + "0 inc. VAT"));
	
			

		// Calculate Grand Total Products total, Delivery total and VAT from Json
		double productsTotalDblGrandTotal = intSup1P1Price + intSup1P2Price + intSup2P1Price;
		System.out.println(productsTotalDblGrandTotal);
		double deliveryTotalDblGrandTotal = intSup1P1StandUKMainlandDeliveryCharges
				+ intSup1P2StandUKMainlandDeliveryCharges + intSup2P1StandUKMainlandDeliveryCharges;
		System.out.println(deliveryTotalDblGrandTotal);
		double vatTotalDblGrandTotal = Sup1P1ProductVAT + Sup1P1ProductDeliveryChargesVAT + Sup1P2ProductVAT
				+ Sup1P2ProductDeliveryChargesVAT + Sup2P1ProductVAT + Sup2P1ProductDeliveryChargesVAT;
		System.out.println(vatTotalDblGrandTotal);
		double grandTotalDblGrandTotal = productsTotalDblGrandTotal + deliveryTotalDblGrandTotal
				+ vatTotalDblGrandTotal;
		System.out.println(grandTotalDblGrandTotal);

		// Grand Total
		// Now get the value of footer productsTotal, deliveryTotal, vatTotal and
		// GrandTotal from Buyers UI
		assertTrue(getText(productsTotal).equals("�" + String.valueOf(productsTotalDblGrandTotal) + "0 ex. VAT"));
		assertTrue(getText(deliveryTotal).equals("�" + String.valueOf(deliveryTotalDblGrandTotal) + "0 ex. VAT"));
		assertTrue(getText(vatTotal).equals("�" + String.valueOf(vatTotalDblGrandTotal)+"0"));
		assertTrue(getText(grandTotal).equals("�" + String.valueOf(grandTotalDblGrandTotal) + "0 inc. VAT"));

		 
	}

	public void validateSupplier1Product1and2VATAndOtherDetailsForNextBusinessDay(JSONObject jObj1, JSONObject jObj2) {
		waitForSeconds(2);

		// Get Supplier1 Product1 Price value from Json
		String Sup1P1Price = jObj1.get("Price").toString();
		double intSup1P1Price = Double.parseDouble(Sup1P1Price);

		// Get Supplier1 Product1 StandardChargeProductUKMainland value from Json
		String Sup1P1NextDayChargeProduct = jObj1.get("nextDayChargeProduct").toString();
		double intSup1P1NextDayChargeProduct = Double.parseDouble(Sup1P1NextDayChargeProduct);

		// Calculate Supplier1 Product1 VAT
		double Sup1P1ProductVAT = intSup1P1Price * 20 / 100;

		// Calculate Supplier1 Product1 DeliveryCharge
		double Sup1P1ProductDeliveryChargesVAT = intSup1P1NextDayChargeProduct * 20 / 100;

		// Get Supplier1 Product2 Price value from Json
		String Sup1P2Price = jObj2.get("Price").toString();
		double intSup1P2Price = Double.parseDouble(Sup1P2Price);

		// Get Supplier1 Product2 StandardChargeProductUKMainland value from Json
		String Sup1P2NextDayChargeProduct = jObj2.get("nextDayChargeProduct").toString();
		double intSup1P2NextDayChargeProduct = Double.parseDouble(Sup1P2NextDayChargeProduct);

		// Calculate Supplier1 Product2 VAT
		double Sup1P2ProductVAT = intSup1P2Price * 20 / 100;

		// Calculate Supplier1 Product2 DeliveryCharge
		double Sup1P2ProductDeliveryChargesVAT = intSup1P2NextDayChargeProduct * 20 / 100;

		// Calculate Products total, Delivery total and VAT from Json
		double productsTotalDbl = intSup1P1Price + intSup1P2Price;
		System.out.println(productsTotalDbl);
		double deliveryTotalDbl = intSup1P1NextDayChargeProduct + intSup1P2NextDayChargeProduct;
		System.out.println(deliveryTotalDbl);
		double vatTotalDbl = Sup1P1ProductVAT + Sup1P1ProductDeliveryChargesVAT + Sup1P2ProductVAT
				+ Sup1P2ProductDeliveryChargesVAT;
		System.out.println(vatTotalDbl);
		double grandTotalDbl = productsTotalDbl + deliveryTotalDbl + vatTotalDbl;
		System.out.println(grandTotalDbl);

		// Now get the value of Supplier1 productsTotal, deliveryTotal, vatTotal and
		// GrandTotal from Buyers UI
		assertTrue(getText(supplier1ProductsTotal).equals("�" + String.valueOf(productsTotalDbl) + "0 ex. VAT"));
		assertTrue(getText(supplier1DeliveryTotal).equals("�" + String.valueOf(deliveryTotalDbl) + "0 ex. VAT"));
		assertTrue(getText(supplier1VatTotal).equals("�" + String.valueOf(vatTotalDbl) + "0"));
		assertTrue(getText(supplier1GrandTotal).equals("�" + String.valueOf(grandTotalDbl) + "0 inc. VAT"));

		// Now get the value of footer productsTotal, deliveryTotal, vatTotal and
		// GrandTotal from Buyers UI
		assertTrue(getText(productsTotal).equals("�" + String.valueOf(productsTotalDbl) + "0 ex. VAT"));
		assertTrue(getText(deliveryTotal).equals("�" + String.valueOf(deliveryTotalDbl) + "0 ex. VAT"));
		assertTrue(getText(vatTotal).equals("�" + String.valueOf(vatTotalDbl) + "0"));
		assertTrue(getText(grandTotal).equals("�" + String.valueOf(grandTotalDbl) + "0 inc. VAT"));

	}

	public void validateSupplier1AndSupplier2ProductswithVATAndOtherDetailsForStandardUKMainland(JSONObject jObjSupp1Produt1VAT20,JSONObject jObjSupp1Produt2VAT0, JSONObject jObjSupp2Produt1VAT20, JSONObject jObjSupp2Produt2VAT0) {
		waitForSeconds(2);

		// Get Supplier1 Product1 Price value from Json VAT20%
		String Sup1P1Price = jObjSupp1Produt1VAT20.get("Price").toString();
		double intSup1P1Price = Double.parseDouble(Sup1P1Price);

		// Get Supplier1 Product1 StandardChargeProductUKMainland value from Json
		String Sup1P1StandardChargeProductUKMainland = jObjSupp1Produt1VAT20.get("StandardChargeProductUKMainland")
				.toString();
		double intSup1P1StandardChargeProductUKMainland = Double.parseDouble(Sup1P1StandardChargeProductUKMainland);

		// Calculate Supplier1 Product1 VAT
		double Sup1P1ProductVAT = intSup1P1Price * 20 / 100;

		// Calculate Supplier1 Product1 DeliveryCharge
		double Sup1P1ProductDeliveryChargesVAT = intSup1P1StandardChargeProductUKMainland * 20 / 100;

		System.out.println(getText(supplier1Name));
		System.out.println(getText(supplier1P1Name).split("\\r?\\n")[1]);
		System.out.println(getText(supplier1P1MPN));
		System.out.println(getText(supplier1P1SKU));
		System.out.println(getText(supplier1P1PriceEach));
		System.out.println(getText(supplier1P1PriceTotal));
		System.out.println(getAttributeValue(supplier1P1Quantityty, "value"));

		// Check the Supplier "testsupplier039" Product 1 Details i.e. Product Name,
		// MPN, SKU, Price each, Price Total, and QTY
		assertTrue(getText(supplier1Name).split("\\r?\\n")[1].equals(jObjSupp1Produt1VAT20.get("SupplierName").toString()));
		assertTrue(getText(supplier1P1Name).split("\\r?\\n")[1].equals(jObjSupp1Produt1VAT20.get("ProductName").toString()));
		assertTrue(getText(supplier1P1MPN).equals("MPN: " + jObjSupp1Produt1VAT20.get("MPN").toString()));
		assertTrue(getText(supplier1P1SKU).equals("SKU: " + jObjSupp1Produt1VAT20.get("SKU").toString()));
		assertTrue(getText(supplier1P1PriceEach).equals("�" + jObjSupp1Produt1VAT20.get("Price").toString() + " each"));
		assertTrue(getText(supplier1P1PriceTotal).equals("Total: �" + jObjSupp1Produt1VAT20.get("Price").toString()));
		assertTrue(getAttributeValue(supplier1P1Quantityty, "value").equals("1"));

		// ` Supplier 1 product 2 Details Validation
		// ####### Get Supplier1 Product2 Price value from Json ###### VAT0%
		String Sup1P2Price = jObjSupp1Produt2VAT0.get("Price").toString();
		double intSup1P2Price = Double.parseDouble(Sup1P2Price);

		// Get Supplier1 Product2 StandardChargeProductUKMainland value from Json
		String Sup1P2StandardChargeProductUKMainland = jObjSupp1Produt2VAT0.get("StandardChargeProductUKMainland")
				.toString();
		double intSup1P2StandardChargeProductUKMainland = Double.parseDouble(Sup1P2StandardChargeProductUKMainland);

		// Calculate Supplier1 Product2 VAT
		double Sup1P2ProductVAT = intSup1P2Price * 0 / 100;

		// Calculate Supplier1 Product2 DeliveryCharge
		double Sup1P1Sup1P2ProductDeliveryChargesVATTotal = (intSup1P1StandardChargeProductUKMainland
				+ intSup1P2StandardChargeProductUKMainland) * 20 / 100;

		System.out.println(getText(supplier1P2Name).split("\\r?\\n")[1]);
		System.out.println(getText(supplier1P2MPN));
		System.out.println(getText(supplier1P2SKU));
		System.out.println(getText(supplier1P2PriceEach));
		System.out.println(getText(supplier1P2PriceTotal));
		System.out.println(getAttributeValue(supplier1P2Quantityty, "value"));

		// Check the Supplier "testsupplier039" Product 2 Details i.e. Product Name,
		// MPN, SKU, Price each, Price Total, and QTY
		assertTrue(getText(supplier1P2Name).split("\\r?\\n")[1].equals(jObjSupp1Produt2VAT0.get("ProductName").toString()));
		assertTrue(getText(supplier1P2MPN).equals("MPN: " + jObjSupp1Produt2VAT0.get("MPN").toString()));
		assertTrue(getText(supplier1P2SKU).equals("SKU: " + jObjSupp1Produt2VAT0.get("SKU").toString()));
		assertTrue(getText(supplier1P2PriceEach).equals("�" + jObjSupp1Produt2VAT0.get("Price").toString() + " each"));
		assertTrue(getText(supplier1P2PriceTotal).equals("Total: �" + jObjSupp1Produt2VAT0.get("Price").toString()));
		assertTrue(getAttributeValue(supplier1P2Quantityty, "value").equals("1"));

		// Calculate Products total, Delivery total and VAT from Json
		double productsTotalDbl = intSup1P1Price + intSup1P2Price;
		System.out.println(productsTotalDbl);
		double deliveryTotalDbl = intSup1P1StandardChargeProductUKMainland + intSup1P2StandardChargeProductUKMainland;
		System.out.println(deliveryTotalDbl);
		double vatTotalDbl = Sup1P1ProductVAT + Sup1P2ProductVAT + Sup1P1Sup1P2ProductDeliveryChargesVATTotal;
		System.out.println(vatTotalDbl);
		double grandTotalDbl = productsTotalDbl + deliveryTotalDbl + vatTotalDbl;
		System.out.println(grandTotalDbl);

		// Now get the value of Supplier1 productsTotal, deliveryTotal, vatTotal and
		// GrandTotal from Buyers UI
		assertTrue(getText(supplier1ProductsTotal).equals("�" + String.valueOf(productsTotalDbl) + "0 ex. VAT"));
		assertTrue(getText(supplier1DeliveryTotal).equals("�" + String.valueOf(deliveryTotalDbl) + "0 ex. VAT"));
		assertTrue(getText(supplier1VatTotal).equals("�" + String.valueOf(vatTotalDbl).substring(0, 3) + "0"));
		assertTrue(getText(supplier1GrandTotal).equals("�" + String.valueOf(grandTotalDbl) + "0 inc. VAT"));

		// ############## Supplier 2 ##################################

		// Get Supplier2 Product1 Price value from Json VAT20%
		String Sup2P1Price = jObjSupp2Produt1VAT20.get("Price").toString();
		double intSup2P1Price = Double.parseDouble(Sup2P1Price);

		// Get Supplier2 Product1 StandardChargeProductUKMainland value from Json
		String Sup2P1StandardChargeProductUKMainland = jObjSupp2Produt1VAT20.get("StandardChargeProductUKMainland")
				.toString();
		double intSup2P1StandardChargeProductUKMainland = Double.parseDouble(Sup2P1StandardChargeProductUKMainland);

		// Calculate Supplier2 Product1 VAT
		double Sup2P1ProductVAT = intSup2P1Price * 20 / 100;

		// Calculate Supplier2 Product1 DeliveryCharge
		double Sup2P1ProductDeliveryChargesVAT = intSup2P1StandardChargeProductUKMainland * 20 / 100;

		System.out.println(getText(supplier2Name));
		System.out.println(getText(supplier2P1Name).split("\\r?\\n")[1]);
		System.out.println(getText(supplier2P1MPN));
		System.out.println(getText(supplier2P1SKU));
		System.out.println(getText(supplier2P1PriceEach));
		System.out.println(getText(supplier2P1PriceTotal));
		System.out.println(getAttributeValue(supplier2P1Quantityty, "value"));

		// Check the Supplier "testsupplier040" Product 1 Details i.e. Product Name,
		// MPN, SKU, Price each, Price Total, and QTY
		assertTrue(getText(supplier2Name).split("\\r?\\n")[1].equals(jObjSupp2Produt1VAT20.get("SupplierName").toString()));
		assertTrue(getText(supplier2P1Name).split("\\r?\\n")[1].equals(jObjSupp2Produt1VAT20.get("ProductName").toString()));
		assertTrue(getText(supplier2P1MPN).equals("MPN: " + jObjSupp2Produt1VAT20.get("MPN").toString()));
		assertTrue(getText(supplier2P1SKU).equals("SKU: " + jObjSupp2Produt1VAT20.get("SKU").toString()));
		assertTrue(getText(supplier2P1PriceEach).equals("�" + jObjSupp2Produt1VAT20.get("Price").toString() + " each"));
		assertTrue(getText(supplier2P1PriceTotal).equals("Total: �" + jObjSupp2Produt1VAT20.get("Price").toString()));
		assertTrue(getAttributeValue(supplier2P1Quantityty, "value").equals("1"));

		// ` Supplier 2 product 2 Details Validation
		// ####### Get Supplier2 Product2 Price value from Json ###### VAT0%
		String Sup2P2Price = jObjSupp2Produt2VAT0.get("Price").toString();
		double intSup2P2Price = Double.parseDouble(Sup2P2Price);

		// Get Supplier2 Product2 StandardChargeProductUKMainland value from Json
		String Sup2P2StandardChargeProductUKMainland = jObjSupp2Produt2VAT0.get("StandardChargeProductUKMainland")
				.toString();
		double intSup2P2StandardChargeProductUKMainland = Double.parseDouble(Sup2P2StandardChargeProductUKMainland);

		// Calculate Supplier2 Product2 VAT
		double Sup2P2ProductVAT = intSup2P2Price * 0 / 100;

		// Calculate Supplier2 Product2 DeliveryCharge
		double Sup2P1Sup1P2ProductDeliveryChargesVATTotal = (intSup2P1StandardChargeProductUKMainland
				+ intSup2P2StandardChargeProductUKMainland) * 20 / 100;

		System.out.println(getText(supplier2P2Name).split("\\r?\\n")[1]);
		System.out.println(getText(supplier2P2MPN));
		System.out.println(getText(supplier2P2SKU));
		System.out.println(getText(supplier2P2PriceEach));
		System.out.println(getText(supplier2P2PriceTotal));
		System.out.println(getAttributeValue(supplier2P2Quantityty, "value"));

		// Check the Supplier "testsupplier039" Product 2 Details i.e. Product Name,
		// MPN, SKU, Price each, Price Total, and QTY
		assertTrue(getText(supplier2P2Name).split("\\r?\\n")[1].equals(jObjSupp2Produt2VAT0.get("ProductName").toString()));
		assertTrue(getText(supplier2P2MPN).equals("MPN: " + jObjSupp2Produt2VAT0.get("MPN").toString()));
		assertTrue(getText(supplier2P2SKU).equals("SKU: " + jObjSupp2Produt2VAT0.get("SKU").toString()));
		assertTrue(getText(supplier2P2PriceEach).equals("�" + jObjSupp2Produt2VAT0.get("Price").toString() + " each"));
		assertTrue(getText(supplier2P2PriceTotal).equals("Total: �" + jObjSupp2Produt2VAT0.get("Price").toString()));
		assertTrue(getAttributeValue(supplier2P2Quantityty, "value").equals("1"));

		// Calculate Products total, Delivery total and VAT from Json
		double productsTotalDblSup2 = intSup2P1Price + intSup2P2Price;
		System.out.println(productsTotalDblSup2);
		double deliveryTotalDblSup2 = intSup2P1StandardChargeProductUKMainland
				+ intSup2P2StandardChargeProductUKMainland;
		System.out.println(deliveryTotalDblSup2);
		double vatTotalDblSup2 = Sup2P1ProductVAT + Sup2P2ProductVAT + Sup2P1Sup1P2ProductDeliveryChargesVATTotal;
		System.out.println(vatTotalDblSup2);
		double grandTotalDblSup2 = productsTotalDblSup2 + deliveryTotalDblSup2 + vatTotalDblSup2;
		System.out.println(grandTotalDblSup2);

		// Now get the value of Supplier1 productsTotal, deliveryTotal, vatTotal and
		// GrandTotal from Buyers UI
		assertTrue(getText(supplier2ProductsTotal).equals("�" + String.valueOf(productsTotalDblSup2) + "0 ex. VAT"));
		assertTrue(getText(supplier2DeliveryTotal).equals("�" + String.valueOf(deliveryTotalDblSup2) + "0 ex. VAT"));
		assertTrue(getText(supplier2VatTotal).equals("�" + String.valueOf(vatTotalDblSup2).substring(0, 3) + "0"));
		assertTrue(getText(supplier2GrandTotal).equals("�" + String.valueOf(grandTotalDblSup2) + "0 inc. VAT"));

		double grandProductsTotalDbl =productsTotalDbl + productsTotalDblSup2;
		double grandDeliveryTotalDbl =deliveryTotalDbl + deliveryTotalDblSup2;
		double grandVatTotalDbl =vatTotalDbl + vatTotalDblSup2;
		double grandGrandTotalDbl =grandTotalDbl + grandTotalDblSup2;
		
			
		// Now get the value of footer productsTotal, deliveryTotal, vatTotal and
		// GrandTotal from Buyers UI
		assertTrue(getText(productsTotal).equals("�" + String.valueOf(grandProductsTotalDbl) + "0 ex. VAT"));
		assertTrue(getText(deliveryTotal).equals("�" + String.valueOf(grandDeliveryTotalDbl) + "0 ex. VAT"));
		assertTrue(getText(vatTotal).equals("�" + String.valueOf(grandVatTotalDbl).substring(0,3) + "0"));
		assertTrue(getText(grandTotal).equals("�" + String.valueOf(grandGrandTotalDbl) + "0 inc. VAT"));
		
		
		//Add Products details in map "productDetailsCheckout" to check the details in Checkout and Order
		//Supplier1 P1
		productDetailsCheckout.put("supplier1Name", jObjSupp1Produt1VAT20.get("SupplierName").toString());
		productDetailsCheckout.put("supplier1P1Name", jObjSupp1Produt1VAT20.get("ProductName").toString());
		productDetailsCheckout.put("supplier1P1MPN", "MPN: " + jObjSupp1Produt1VAT20.get("MPN").toString());
		productDetailsCheckout.put("supplier1P1SKU", "SKU: " + jObjSupp1Produt1VAT20.get("SKU").toString());
		productDetailsCheckout.put("supplier1P1PriceEach", "�" + jObjSupp1Produt1VAT20.get("Price").toString() + " each");
		productDetailsCheckout.put("supplier1P1Total", "Total: �" + jObjSupp1Produt1VAT20.get("Price").toString());
		productDetailsCheckout.put("supplier1P1Qty","1");
		
		//Supplier1 P1
		productDetailsCheckout.put("supplier1P2Name", jObjSupp1Produt2VAT0.get("ProductName").toString());
		productDetailsCheckout.put("supplier1P2MPN", "MPN: " + jObjSupp1Produt2VAT0.get("MPN").toString());
		productDetailsCheckout.put("supplier1P2SKU", "SKU: " + jObjSupp1Produt2VAT0.get("SKU").toString());
		productDetailsCheckout.put("supplier1P2PriceEach", "�" + jObjSupp1Produt2VAT0.get("Price").toString() + " each");
		productDetailsCheckout.put("supplier1P2Total", "Total: �" + jObjSupp1Produt2VAT0.get("Price").toString());
		productDetailsCheckout.put("supplier1P2Qty", "1");
		
		//Supplier1 OverAll Total
		productDetailsCheckout.put("productTotalSupp1", "�" + String.valueOf(productsTotalDbl) + "0 ex. VAT");
		productDetailsCheckout.put("deliveryTotalSupp1", "�" + String.valueOf(deliveryTotalDbl) + "0 ex. VAT");
		productDetailsCheckout.put("vatTotalSupp1", "�" + String.valueOf(vatTotalDbl).substring(0, 3) + "0");
		productDetailsCheckout.put("grandTotalSupp1", "�" + String.valueOf(grandTotalDbl) + "0 inc. VAT");
		
		//####################### Supplier2 P1 ############################ 
		
		//Supplier2 P1
		productDetailsCheckout.put("supplier2Name", jObjSupp2Produt1VAT20.get("SupplierName").toString());
		productDetailsCheckout.put("supplier2P1Name", jObjSupp2Produt1VAT20.get("ProductName").toString());
		productDetailsCheckout.put("supplier2P1MPN", "MPN: " + jObjSupp2Produt1VAT20.get("MPN").toString());
		productDetailsCheckout.put("supplier2P1SKU", "SKU: " + jObjSupp2Produt1VAT20.get("SKU").toString());
		productDetailsCheckout.put("supplier2P1PriceEach", "�" + jObjSupp2Produt1VAT20.get("Price").toString() + " each");
		productDetailsCheckout.put("supplier2P1Total", "Total: �" + jObjSupp2Produt1VAT20.get("Price").toString());
		productDetailsCheckout.put("supplier2P1Qty","1");
		
		//Supplier2 P1
		productDetailsCheckout.put("supplier2P2Name", jObjSupp2Produt2VAT0.get("ProductName").toString());
		productDetailsCheckout.put("supplier2P2MPN", "MPN: " + jObjSupp2Produt2VAT0.get("MPN").toString());
		productDetailsCheckout.put("supplier2P2SKU", "SKU: " + jObjSupp2Produt2VAT0.get("SKU").toString());
		productDetailsCheckout.put("supplier2P2PriceEach", "�" + jObjSupp2Produt2VAT0.get("Price").toString() + " each");
		productDetailsCheckout.put("supplier2P2Total", "Total: �" + jObjSupp2Produt2VAT0.get("Price").toString());
		productDetailsCheckout.put("supplier2P2Qty", "1");
		
		//Supplier2 OverAll Total
		productDetailsCheckout.put("productTotalSupp2", "�" + String.valueOf(productsTotalDblSup2) + "0 ex. VAT");
		productDetailsCheckout.put("deliveryTotalSupp2", "�" + String.valueOf(deliveryTotalDblSup2) + "0 ex. VAT");
		productDetailsCheckout.put("vatTotalSupp2", "�" + String.valueOf(vatTotalDblSup2).substring(0, 3) + "0");
		productDetailsCheckout.put("grandTotalSupp2", "�" + String.valueOf(grandTotalDblSup2) + "0 inc. VAT");
		
		
		//Supplier1 Supplier2 Grand OverAll Total
		productDetailsCheckout.put("grandBasketProductTotal", "�" + String.valueOf(grandProductsTotalDbl) + "0 ex. VAT");
		productDetailsCheckout.put("grandBasketDeliveryTotal", "�" + String.valueOf(grandDeliveryTotalDbl) + "0 ex. VAT");
		productDetailsCheckout.put("grandBasketVatTotal", "�" + String.valueOf(grandVatTotalDbl).substring(0,3) + "0");
		productDetailsCheckout.put("grandBasketTotal", "�" + String.valueOf(grandGrandTotalDbl) + "0 inc. VAT");
		
	}
	
	
	public void validateSupplier1Product1and2VATAndOtherDetailsForStandardUKNonMainland(JSONObject jObj1,
			JSONObject jObj2) {
		waitForSeconds(2);

		// Get Supplier1 Product1 Price value from Json
		String Sup1P1Price = jObj1.get("Price").toString();
		double intSup1P1Price = Double.parseDouble(Sup1P1Price);

		// Get Supplier1 Product1 StandardChargeProductUKMainland value from Json
		String Sup1P1NextDayChargeProduct = jObj1.get("StandardChargeProductUKNonMainland").toString();
		double intSup1P1NextDayChargeProduct = Double.parseDouble(Sup1P1NextDayChargeProduct);

		// Calculate Supplier1 Product1 VAT
		double Sup1P1ProductVAT = intSup1P1Price * 0 / 100;
		String Sup1P1ProductVATStr = Double. toString(Sup1P1ProductVAT);

		// Calculate Supplier1 Product1 DeliveryCharge
		double Sup1P1ProductDeliveryChargesVAT = intSup1P1NextDayChargeProduct * 20 / 100;
		
		double Sup1Product1VATTotal=Sup1P1ProductVAT+Sup1P1ProductDeliveryChargesVAT;
		String Sup1Product1VATTotalStr=Double. toString(Sup1Product1VATTotal);
		
		
		double supplier1Product1Total= intSup1P1Price+intSup1P1NextDayChargeProduct+Sup1P1ProductDeliveryChargesVAT+Sup1P1ProductVAT;
		String supplier1Product1TotalStr = Double. toString(supplier1Product1Total);
		
		// Get Supplier2 Product1 Price value from Json
		String Sup1P2Price = jObj2.get("Price").toString();
		double intSup1P2Price = Double.parseDouble(Sup1P2Price);

		// Get Supplier1 Product2 StandardChargeProductUKMainland value from Json
		String Sup1P2NextDayChargeProduct = jObj2.get("StandardChargeProductUKNonMainland").toString();
		double intSup1P2NextDayChargeProduct = Double.parseDouble(Sup1P2NextDayChargeProduct);

		// Calculate Supplier1 Product2 VAT
		double Sup1P2ProductVAT = intSup1P2Price * 20 / 100;

		// Calculate Supplier1 Product2 DeliveryCharge
		double Sup1P2ProductDeliveryChargesVAT = intSup1P2NextDayChargeProduct * 20 / 100;
		
		double Sup1Product2VATTotal=Sup1P2ProductVAT+Sup1P2ProductDeliveryChargesVAT;
		String Sup1Product2VATTotalStr=Double. toString(Sup1Product2VATTotal);
				
		double supplier1Product2Total= intSup1P2Price+intSup1P2NextDayChargeProduct+Sup1P2ProductDeliveryChargesVAT+Sup1P2ProductVAT;
		String supplier1Product2TotalStr = Double.toString(supplier1Product2Total);
		
		
		
		
		// Calculate Products total, Delivery total and VAT from Json
		double productsTotalDbl = intSup1P1Price + intSup1P2Price;
		System.out.println(productsTotalDbl);
		double deliveryTotalDbl = intSup1P1NextDayChargeProduct + intSup1P2NextDayChargeProduct;
		System.out.println(deliveryTotalDbl);
		double vatTotalDbl = Sup1P1ProductVAT + Sup1P1ProductDeliveryChargesVAT + Sup1P2ProductVAT
				+ Sup1P2ProductDeliveryChargesVAT;
		System.out.println(vatTotalDbl);
		double grandTotalDbl = productsTotalDbl + deliveryTotalDbl + vatTotalDbl;
		System.out.println(grandTotalDbl);
		
		
		// Now get the value of Supplier1 Product1 productsTotal, deliveryTotal, vatTotal and Total
		assertTrue(getText(supplier1ProductsTotal).equals("�" + String.valueOf(Sup1P1Price) + " ex. VAT"));
		assertTrue(getText(supplier1DeliveryTotal).equals("�" + String.valueOf(Sup1P1NextDayChargeProduct) + " ex. VAT"));
		assertTrue(getText(supplier1VatTotal).equals("�" + String.valueOf(Sup1Product1VATTotalStr) + "0"));
		assertTrue(getText(supplier1GrandTotal).equals("�" + String.valueOf(supplier1Product1TotalStr) + "0 inc. VAT"));

		// Now get the value of Supplier1 Product2 productsTotal, deliveryTotal, vatTotal and Total
		assertTrue(getText(supplier2ProductsTotal).equals("�" + String.valueOf(Sup1P2Price) + " ex. VAT"));
		assertTrue(getText(supplier2DeliveryTotal).equals("�" + String.valueOf(Sup1P2NextDayChargeProduct) + " ex. VAT"));
		assertTrue(getText(supplier2VatTotal).equals("�" + String.valueOf(Sup1Product2VATTotalStr) + "0"));
		assertTrue(getText(supplier2GrandTotal).equals("�" + String.valueOf(supplier1Product2TotalStr) + "0 inc. VAT"));

		// Now get the value of footer productsTotal, deliveryTotal, vatTotal and
		// GrandTotal from Buyers UI
		assertTrue(getText(productsTotal).equals("�" + String.valueOf(productsTotalDbl) + "0 ex. VAT"));
		assertTrue(getText(deliveryTotal).equals("�" + String.valueOf(deliveryTotalDbl) + "0 ex. VAT"));
		assertTrue(getText(vatTotal).equals("�" + String.valueOf(vatTotalDbl) + "0"));
		assertTrue(getText(grandTotal).equals("�" + String.valueOf(grandTotalDbl) + "0 inc. VAT"));

	}
	
	
	
	
	public void verifyPaymentCheckOutPage() {

		assertEquals(getText(paymentCheckOutTextUI), "Payment by invoice");
		log.info("Validated Payment checkout page");
		
	}
	
	
	public void verifyOrderSummaryInPaymentCheckoutPage() {

		assertEquals(getText(paymentCheckOutOrderSummaryProducts), productDetailsCheckout.get("productTotal"));
		assertEquals(getText(paymentCheckOutOrderSummaryDeliveryTotal), productDetailsCheckout.get("deliveryTotal"));
		assertEquals(getText(paymentCheckOutOrderSummaryVAT), productDetailsCheckout.get("vatTotal"));
		assertEquals(getText(paymentCheckOutOrderSummaryTotalValue), productDetailsCheckout.get("grandTotal"));
		log.info("Order summary details validated sucessfully");
		
	}
	
	public void enterPONumber() {

		String Currenntday = new DateTimeUtils().dateWithSpecificFormatt("dd/MM/yyyy HH:mm:ss");
		PoNumberWithCurrenntday = "PONumber".concat("-").concat(Currenntday);
		enterText(paymentCheckOutPoNumberTextbox, PoNumberWithCurrenntday);
		log.info("PO Numner is entered as: " + PoNumberWithCurrenntday);
		
	}
	
	
	public void verifyCheckOutSummaryPage() {

		assertEquals(getText(checkOutOrderSummaryPageHeader), "Checkout");
		log.info("Validated Payment checkout summary page");
		
	}
	
	public void verifyProductDetailsOnCheckoutSummaryPage() {

		assertEquals(getText(checkOutOrderSummaryAllProductsTotal), productDetailsCheckout.get("productTotal"));
		assertEquals(getText(checkOutOrderSummaryAllProductsDeliveryTotal), productDetailsCheckout.get("deliveryTotal"));
		assertEquals(getText(checkOutOrderSummaryAllProductsVAT), productDetailsCheckout.get("vatTotal"));
		assertEquals(getText(checkOutOrderSummaryAllProductsGrandTotal), productDetailsCheckout.get("grandTotal"));
		log.info("Product details validated sucessfully on checkout summary page");
		
	}
	
	public void checksOnTermsAndConditions() {
		
		clickElementWithJavaScript(checksOnTermsAndConditionsStr);
		log.info("Sucessfully clicked on Terms And Conditions checkbox");
		
	}
	
	public String clickDeliveryNote() {
		return deliveryNote;
	}
	
	public String editDeliveryNote() {
		return editdeliveryNote;
	}
	
	
	
	public void verifyProductDetailsOnOrderPage() {

		log.info("Product details validated sucessfully on Order page");
		assertEquals(getText(checkOutOrderSummaryAllProductsTotal), productDetailsCheckout.get("productTotal"));
		/*assertEquals(getText(checkOutOrderSummaryAllProductsTotal), productDetailsCheckout.get("productTotal"));
		assertEquals(getText(checkOutOrderSummaryAllProductsDeliveryTotal), productDetailsCheckout.get("deliveryTotal"));
		assertEquals(getText(checkOutOrderSummaryAllProductsVAT), productDetailsCheckout.get("vatTotal"));
		assertEquals(getText(checkOutOrderSummaryAllProductsGrandTotal), productDetailsCheckout.get("grandTotal"));
		log.info("Product details validated sucessfully on checkout summary page");*/
		
	}
	
	public void vlidateAddDeliveryNotePageHeader(String deliveryNoteHeader) {

		assertEquals(getText(deliveryNotePageHeader), deliveryNoteHeader);
		log.info("'Add a delivery note' page Header is validated sucessfully");
	}
	
	public void vlidateDeliveryNoteTextboxIsVisible(String deliveryNoteTextboxLabel,String deliveryNoteHeader) {

		
		assertEquals(getText(deliveryNoteTextLabel), deliveryNoteTextboxLabel);
		assertTrue("Delivery Note Textarea filed is present", isElementPresentByXpath(deliveryNoteTextareaIsVisible));
		log.info("Delivery Note Textbox with label And Header validated sucessfully on Add a delivery note page");
	}
	
	
   public void vlidateCharacterLimitMsg(String characterLimitMsg) {

	   assertEquals(getText(deliveryNoteCharacterLimitMsg), characterLimitMsg);
	   log.info("Message 'You have 150 characters remaining' is validated sucessfully");
	}
   
   public void clearDeliveryNote() {

	   enterText(deliveryNoteTextareaIsVisible, "");
	   log.info("DeliveryNote Text clears sucessfully");
	}
   
   public void vlidateCharacterLimitMsgAfterEnteredText(String charLength, String characterLimitMsg) {
	   
	   if(charLength.equals("146")) {
		   
		   enterText(deliveryNoteTextareaIsVisible,configReaderObj.get("deliveryTextAreaInput146Char"));
		   assertEquals(getText(errorMsgDeliveryNote), characterLimitMsg);
		   log.info("Message 'You have 4 characters remaining' is validated sucessfully");
		   
	   }else if(charLength.equals("174")) {
		   
		   enterText(deliveryNoteTextareaIsVisible,configReaderObj.get("deliveryTextAreaInput174Char"));
		   assertEquals(getText(errorMsgDeliveryNote), characterLimitMsg);
		   log.info("Message 'You have X characters too many' is validated sucessfully");
		   
	   }else if(charLength.equals("13")) {
		   
		   enterText(deliveryNoteTextareaIsVisible,configReaderObj.get("deliveryTextAreaInput13Char"));
		   assertEquals(getText(errorMsgDeliveryNote), characterLimitMsg);
		   log.info("Message 'You have X characters too many' is validated sucessfully");
		   
	   }else if(charLength.equals("23")) {
		   
		   enterText(deliveryNoteTextareaIsVisible,configReaderObj.get("deliveryTextAreaInput23Char"));
		   assertEquals(getText(errorMsgDeliveryNote), characterLimitMsg);
		   log.info("Message 'You have X characters too many' is validated sucessfully");
	   }
	   
	   
	}
   
   
   public void clickOnCancleButton() {
		clickElement(cancelButton);
		log.info("Sucessfully clicked on Cancel button");
	
	}
   
   
   public void clickOnSaveButton() {
		clickElement(saveButton);
		log.info("Sucessfully clicked on Save button");
	
	}
   
   public void validateAddDeliveryNoteLink() {
	   assertTrue("Delivery Note Textarea filed is present", isElementPresentByXpath(deliveryNotePageHeader));
	   log.info("Sucessfully clicked on Cancel button");
	
	}
   
   public void validateEditTheDeliveryNoteLinkLink() {
	   assertTrue("EditTheDeliveryNote link is present", isElementPresentByXpath(editTheDeliveryNoteLink));
	   log.info("EditTheDeliveryNote link is present");
	
	}
   
   public void validateDeliveryNoteTextInBasketPage(String deliveryNote) {
	   assertEquals(getText(deliveryNoteText), deliveryNote);
	   log.info("Delivery Note Text is validated successfully");
	}
	

}

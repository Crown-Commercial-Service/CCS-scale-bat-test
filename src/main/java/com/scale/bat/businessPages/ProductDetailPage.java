package com.scale.bat.businessPages;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Map;
import java.util.Random;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.scale.bat.framework.utility.Actions;
import com.scale.bat.framework.utility.Log;

import cucumber.api.Scenario;

public class ProductDetailPage extends Actions {
	private Logger log = Log.getLogger(ProductDetailPage.class);
	
	public static String localreducedStockString;

	@FindBy(xpath = "//*[@id='product_unspsc_field']/input")
	private WebElement unspscTextField;

	private String stock = "Stock";

	@FindBy(xpath = "//*[@id='item_count_on_hand']")
	private WebElement StockTextField;

	@FindBy(xpath = "//*[@id='product_master_sku_field']/input")
	private WebElement skuUnitTextField;
	
	
	@FindBy(xpath = "//*[@id='product_price_field']/input")
	private WebElement productPrice;

	private String updateButton = "Update";

	//private String successfulMessage = "//*[@id='main-part']/div[2]/div[1]/div";
	private String successfulMessage = "//*[@class='alert alert-success mx-2']";
	
	@FindBy(xpath = "//*[@id='content']/form/div/div/a")
	private WebElement cancelButton;

//	@FindBy(xpath = "//*[@id='product_discontinue_on_field']/input")
	@FindBy(xpath = "//*[@id='product_discontinue_on']")
	private WebElement discontinueOn;

	@FindBy(xpath = "//*[@id='product_available_on']")
	private WebElement publishedFromDate;

	private String errorMessage = "//*[@id='errorExplanation']";
	
	@FindBy(xpath = "//input[@name='product[delivery_charges_attributes][next_day_available]'][2]")
	private WebElement nextDayDeliveryCheckbox;

	public ProductDetailPage(WebDriver driver, Scenario scenario) {
		super.driver = driver;
		this.scenario = scenario;
		PageFactory.initElements(driver, this);
		this.wait = new WebDriverWait(this.driver, 30);
	}

//	public void changeQuantity() {
//		clickByLinkText(stock);
//		String qty = getAttributeValue(StockTextField);
//		log.info("Stock Qty before update: " + qty);
//		scenarioContext.setKeyValue("OldQty", qty);
//		clearTextBox(StockTextField);
//		String updatedQty = String.valueOf(randomBunmber(10));
//		enterText(StockTextField, updatedQty);
//		scenarioContext.setKeyValue("UpdatedQty", updatedQty);
//	}
//
//	public void cancelTheUpdatedQuantity() {
//		clickByLinkText(stock);
//		waitForSeconds(2);
//		log.info("Update qty was " + scenarioContext.getContext("UpdatedQty")
//				+ " and after clicking cancel button qty is " + scenarioContext.getContext("OldQty"));
//		assertTrue("Qty should be same after clicking cancel button",
//				getAttributeValue(StockTextField).equalsIgnoreCase(scenarioContext.getContext("OldQty")));
//	}

	public void updateStockQuantity() {
		clickByLinkText(stock);
		clearTextBox(StockTextField);
		String qty = String.valueOf(randomBunmber(10));
		enterText(StockTextField, qty);
		clickButton(updateButton);
		existsElement(successfulMessage);
	}
	
	public void removeTheNextDayOption() {
		clickElementXpath(nextDayDeliveryCheckbox);
		clickButton(updateButton);
		existsElement(successfulMessage);
	}
	
	
	
	
	public void reduceStockQuantity(Map<String, Object> pDetails) {
		clickByLinkText(stock);
		clearTextBox(StockTextField);
		
		String localVolumeStock = pDetails.get("LocalStockVolume").toString();
		int localVolumeStockInt = Integer.parseInt(localVolumeStock);
		int localreducedStockInt = localVolumeStockInt-1;
		localreducedStockString = Integer.toString(localreducedStockInt);
		enterText(StockTextField, localreducedStockString);
		clickButton(updateButton);
		existsElement(successfulMessage);
	}
	
	
	public void updateStockQuantityAsPerJson(Map<String, Object> pDetails) {
		clickByLinkText(stock);
		clearTextBox(StockTextField);
		String localVolumeStock = pDetails.get("LocalStockVolume").toString();
		enterText(StockTextField, localVolumeStock);
		clickButton(updateButton);
		existsElement(successfulMessage);
	}

	public void checkWarningMessageOnStockField(String inputType) {
		clickByLinkText(stock);
		clearTextBox(StockTextField);
		String qty = "";
		switch (inputType.toLowerCase()) {
		case "decimal":
			qty = String.valueOf(new Random().nextFloat());
			break;
		case "alphanumeric":
			qty = randomBunmber(9) + "e";
			break;
		case "negative":
			qty = "-" + randomBunmber(10);
			break;

		default:
			log.info("Wrong input type. Please Check spellings or input provided in BDD");
			break;
		}
		enterText(StockTextField, qty);
		clickButton(updateButton);
		assertFalse(isElementPresent(successfulMessage));
	}

	public void updateProductDetails(String selectedDetail, String updatedValue, boolean empty) {
		if (empty) {
			updatedValue = "";
		}
		switch (selectedDetail.toLowerCase()) {
		case "unspsc":
			clearTextBox(unspscTextField);
			enterText(unspscTextField, updatedValue);
			break;
		case "sku unit":
			clearTextBox(skuUnitTextField);
			enterText(skuUnitTextField, updatedValue);
			break;
		default:
			log.info("Wrong input type. Please Check spellings or input provided in BDD");
			break;
		}

		clickButton(updateButton);
	}

	public String getProductDetails(String selectedDetail) {
		switch (selectedDetail.toLowerCase()) {
		case "unspsc":
			return getText(unspscTextField);
		case "sku unit":
			return getText(skuUnitTextField);
		case "discontinue on":
			return getAttributeValue(discontinueOn);
		case "published from date":
			return getAttributeValue(publishedFromDate);
		default:
			log.info("Wrong input type. Please Check spellings or input provided in BDD");
			break;
		}
		return null;
	}

	public void validateSuccessfulMessage() {
		assertTrue("Message is not displayed!! Please check the script", existsElement(successfulMessage));
	}

	public void validateErrorMessage() {
		assertTrue("Message is not displayed!! Please check the script", existsElement(errorMessage));
	}
	
	public void updateProduct(Map<String, Object> pDetails) {
		clearTextBox(productPrice);
		enterText(productPrice, pDetails.get("Price").toString());
		
		clearTextBox(unspscTextField);
		enterText(unspscTextField, pDetails.get("UNSPSC").toString());
		
		clearTextBox(skuUnitTextField);
		enterText(skuUnitTextField, pDetails.get("SKU").toString());
		
		clickButton(updateButton);
		assertTrue("Product is not updated successfully..", isElementPresent("successfully updated!"));

	}

}

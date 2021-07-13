package com.scale.bat.businessPages;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.scale.bat.framework.utility.Actions;
import com.scale.bat.framework.utility.ConfigurationReader;
import com.scale.bat.framework.utility.JsonParser;
import com.scale.bat.framework.utility.Log;
import cucumber.api.Scenario;

public class BuyersUIMyListPage extends Actions  {
	
	private Logger log = Log.getLogger(BuyersUIPDPPage.class);
	private ConfigurationReader configReaderObj;
	
	public BuyersUIMyListPage(WebDriver driver, Scenario scenario) {
		super.driver = driver;
		this.scenario = scenario;
		PageFactory.initElements(driver, this);
		this.wait = new WebDriverWait(super.driver, 30);
		configReaderObj = new ConfigurationReader();
	}
	

		@FindBy(xpath ="//*[@id='main-content']/div[1]/div/ul/li[1]/div/div[3]/div/span")
		private WebElement priceOnMyList;
		
		@FindBy(xpath = "//*[@id='main-content']/div[1]/div/ul/li[1]/div/div[2]/div/span[1]")
		private WebElement manufaturerMyList;
		
		@FindBy(xpath ="//*[@id='main-content']/div[1]/div/ul/li[1]/div/div[2]/div/a")
		private WebElement productNameMyList;
		
		@FindBy(xpath ="//*[@id='main-content']/div[1]/div/ul/li[1]/div/div[2]/div/span[2]")
		private WebElement skuMyList;
		
		@FindBy(xpath ="//*[@class='govuk-button govuk-button bat-wished-product__button']")
		private WebElement addToBasketButton;
		
		@FindBy(xpath ="//*[@class='govuk-button govuk-button--secondary']")
		private WebElement clearMyBasketAndAddTheseItemsbutton;
		
		@FindBy(xpath ="//*[@class='govuk-button govuk-button--primary']")
		private WebElement addTheseItemsToCurrentBasketbutton;
		
		@FindBy(xpath ="//*[@class='govuk-warning-text__text']")
		private WebElement warningMessage;
		
		@FindBy(xpath ="//ul[@class='govuk-list govuk-error-summary__list']/li")
		private WebElement warningMessageExceedNumberOfStock;
		
		@FindBy(xpath ="//p[@class='govuk-body']")
		private WebElement disclaimerMessage;
				
		@FindBy(xpath ="//p[@class='govuk-body govuk-!-govuk-!-margin-bottom-6']")
		private WebElement informationText;
		
		@FindBy(xpath ="//*[@class='govuk-input govuk-input--width-4']")
		private WebElement quantityTextBoxAddToList;
		
		@FindBy(xpath ="//*[@class='govuk-input govuk-input--width-2']")
		private WebElement quantityTextBoxAddToListNew;
		
		@FindBy(xpath ="//*[@class='govuk-warning-text__text']")
		private WebElement OOSQtyMessage;
		
		@FindBy(xpath ="//*[@class='bat-wished-product__title__insufficient-stock']")
		private WebElement qtyReduceMessage;
		
		private String quantityTextBoxAddToListString="//*[@class='govuk-input govuk-input--width-4']";
		private String backLink="//a[@class='govuk-back-link']";
		private String manufacturerName ="//*[@id='main-content']/div[1]/div/ul/li[1]/div/div/div[2]/div/span[1]";
		private String sku ="//*[@id='main-content']/div[1]/div/ul/li[1]/div/div/div[2]/div/span[2]";
		private String price ="//*[@id='main-content']/div[1]/div/ul/li[1]/div/div/div[3]/div/span[1]";
		private String updateLink="Update";
		private String quantity= "Quantity";
		
		
	
	
//	public void verifyProductDetailsOnMyListPage(Map<String, Object> pDetails, String path) {
		public void verifyProductDetailsOnMyListPage(String path) {
		waitForSeconds(2);
		
		JSONObject jObj = new JSONObject(new JsonParser().convertJsonToString(path));
		//Retrieving the array
        JSONArray jsonArray = (JSONArray) jObj.getJSONArray("data");
        for(int i=0;i<jsonArray.length();i++) {
          		
        	JSONObject obj=jsonArray.getJSONObject(i);
        	JSONObject attributes=obj.getJSONObject("attributes");
        	int j=i+1;
        	
        	System.out.println("productNameUI = " + getTextXpath("//*[@id='main-content']/div[1]/div/ul/li["+j+"]/div/div[2]/div/a") + " jsonproductName = " + attributes.getString("name"));
        	
        	System.out.println("ManiftrUI = " + getTextXpath("//*[@id='main-content']/div[1]/div/ul/li["+j+"]/div/div[2]/div/span[1]") + " jsonMfr = " + attributes.getString("manufacturer"));
        	
        	System.out.println("skuUI = " + getTextXpath("//*[@id='main-content']/div[1]/div/ul/li["+j+"]/div/div[2]/div/span[2]") + " jsonsku = " + attributes.getString("SKU"));
        	
        	//Product Name Validation
        	assertTrue(getTextXpath("//*[@id='main-content']/div[1]/div/ul/li["+j+"]/div/div[2]/div/a").contains(attributes.getString("name")));
        	        	
        	//Manufacturer Name Validation
        	assertTrue(getTextXpath("//*[@id='main-content']/div[1]/div/ul/li["+j+"]/div/div[2]/div/span[1]").contains(attributes.getString("manufacturer")));
        	//SKU Validation
        	assertTrue(getTextXpath("//*[@id='main-content']/div[1]/div/ul/li["+j+"]/div/div[2]/div/span[2]").contains(attributes.getString("SKU")));
        	
        	/*assertTrue(getText(productNameMyList).contains(attributes.getString("name")));
        	assertTrue(getText(manufaturerMyList).contains(attributes.getString("manufacturer")));
        	assertTrue(getText(skuMyList).contains(attributes.getString("SKU")));*/
        	log.info("Validation completed on MyList Page UI");
        	//assertTrue(getText(priceOnMyList).contains(attributes.getString("display_price")));
        	
	}
		
	}
		
		
        
	public void clickOnAddtoBasketButton() {
		
		clickElement(addToBasketButton);
		
	}
	
	public void clickOnClearMyBasketAndAddTheseItemsbutton() {
		
		clickElement(clearMyBasketAndAddTheseItemsbutton);
		
	}
	
	public void clickOnAddTheseItemsToCurrentBasketbutton() {
		
		clickElement(addTheseItemsToCurrentBasketbutton);
		
	}
	
	
	public void verifyProductDetailsOnMyListPage(Map<String, Object> pDetails) {
	
		String manufacturerNamejsn=pDetails.get("ManufacturerName").toString();
		String SKUjsn=pDetails.get("SKU").toString();
		String Pricejsn=pDetails.get("Price").toString();
	
		assertTrue(getTextXpath(manufacturerName).equalsIgnoreCase(manufacturerNamejsn));
		log.info("Validation of 'Manufacturer Name' "+ manufacturerNamejsn+ " in My list Page is successful");
		
		assertTrue(getTextXpath(sku).equalsIgnoreCase(SKUjsn));
		log.info("Validation of 'SKU' "+ SKUjsn + " in My list Page is successful");
		
		assertTrue(getTextXpath(price).contains(Pricejsn));
		log.info("Validation of 'Price' "+ Pricejsn+ " in My list Page is successful");
		
	}
	
	public void updatetheProductQuantity(Map<String, Object> pDetails) {
		
		String manufacturerNamejsn=pDetails.get("ManufacturerName").toString();
		
		String localStockjsn=pDetails.get("LocalStockVolume").toString();
		int totalLocalStockint=Integer.parseInt(localStockjsn);
		int updatedLocalStock=totalLocalStockint+120;
		String updatedLocalStockString= String.valueOf(updatedLocalStock);
		
		enterText(quantity,updatedLocalStockString);
		clickElement(updateLink);
		waitForSeconds(3);
	}
	
	public void validateWarningMessageUnableToSupplyItems(String warningMsg) {
		
		assertTrue(getText(warningMessage).contains(warningMsg));
		log.info("Warning message: "+ getText(warningMessage)+ " is displayed successful");
		
	}
	
	public void validateDisclaimerMessageOnMyList() {
		
		assertTrue(getText(disclaimerMessage).equals(configReaderObj.get("disclaimerMessage")));
		log.info("Buyer is able to see the disclaimer message: "+ configReaderObj.get("disclaimerMessage") + " is displayed successful");
		
	}
	
	public void validateInformationTextMyList() {
		
		assertTrue(getText(informationText).equals(configReaderObj.get("informationText")));
		log.info("Disclaimer message: "+ configReaderObj.get("disclaimerMessage") + " is displayed successful");
		
	}
	
	public void validateBackLinkIsNotVisibleOnMyList() {
		
		boolean backLinkBoolean = isElementPresentByXpath(backLink);
		assertFalse("Validated Back link is not visible", backLinkBoolean);
		log.info("Validated Back link is not visible");
		
	}
	
	public void enterTheProductFullQuantity(Map<String, Object> pDetails) {
		
		String localVolumeStock = pDetails.get("LocalStockVolume").toString();
		int localVolumeStockInt = Integer.parseInt(localVolumeStock);
		int localVolumeStockIntOOS = localVolumeStockInt+100;
		String localVolumeStockStrOOS = Integer.toString(localVolumeStockIntOOS);
		enterText(quantityTextBoxAddToListNew,localVolumeStockStrOOS);
		boolean quantityBoolean =isElementPresentByXpath(quantityTextBoxAddToListNew);
		assertTrue("Buyer is able to enter the total stock of the product ", quantityBoolean);
		log.info("Buyer is able to enter the total stock of the product");
		
	}
	
	public void enterTheProductFullQtyOnQtyTextOnItemAddedToYourBasketPage(Map<String, Object> pDetails) {

		String localVolumeStock = pDetails.get("LocalStockVolume").toString();
		int localVolumeStockInt = Integer.parseInt(localVolumeStock);
		int localVolumeStockIntOOS = localVolumeStockInt + 100;
		String localVolumeStockStrOOS = Integer.toString(localVolumeStockIntOOS);
		enterText(quantityTextBoxAddToList, localVolumeStockStrOOS);
		boolean quantityBoolean = isElementPresentByXpath(quantityTextBoxAddToList);
		assertTrue("Buyer is able to enter the total stock of the product ", quantityBoolean);
		log.info("Buyer is able to enter the total stock of the product");

	}
	
	public void validateUnableToSupplyQtyMessaseInAddToList() {
		assertTrue(getText(OOSQtyMessage).contains("Unable to supply"));
		log.info("Validated Out Of stock quantity message");
		
	}
	
	public void validateWarningAndQuantityMessageInMyList() {
		
		String[] OOSQtyMessageUI = getText(OOSQtyMessage).split("\\r?\\n");
		String actualOOSQtyMessageUI = OOSQtyMessageUI[1];
		assertTrue(actualOOSQtyMessageUI.equals(configReaderObj.get("warningMsgReduceQty")));
		assertTrue(getText(qtyReduceMessage).contains("Quantity available: "));
		log.info("Validated warning message of reduced quantity im My List page ");
		
	}
	
	public void validateReduceStockMessageWhenClickAddTheseItemsToCurrentBasketInMyList(Map<String, Object> pDetails) {
		
		String[] OOSQtyMessageUI = getText(OOSQtyMessage).split(",");
		String[] actualOOSQtyMessageUI1 = OOSQtyMessageUI[0].split("\\r?\\n");
		String actualOOSQtyMessageUI2 = actualOOSQtyMessageUI1[1];
		String actualOOSQtyMessageUI3 = actualOOSQtyMessageUI1[2];
		String actualOOSQtyMessageUI4 = actualOOSQtyMessageUI1[3];
		assertTrue(actualOOSQtyMessageUI2.equals(configReaderObj.get("warningMsgReduceQtyWhenClickAddTheseItemBtn1")) || actualOOSQtyMessageUI2.equals(configReaderObj.get("warningMsgExceedQtyWhenClickAddTheseItemBtn01")));
		assertTrue(actualOOSQtyMessageUI3.equals(pDetails.get("ProductName").toString()));
		assertTrue(actualOOSQtyMessageUI4.equals(configReaderObj.get("warningMsgReduceQtyWhenClickAddTheseItemBtn2")));
		log.info("Validated warning message of reduced quantity when click AddTheseItemBtn im My List page ");
		
	}
	
	
	public void validateWarningMessageCannotAddTheSelectedProduct() {
		
		//System.out.println("UI Warning message: " + getText(warningMessageExceedNumberOfStock));
		assertEquals(getText(warningMessageExceedNumberOfStock), configReaderObj.get("warningMessageExceedNumberOfStock"));
		log.info("Warning message is displayed successful : "+ getText(warningMessageExceedNumberOfStock));
		
	}

}

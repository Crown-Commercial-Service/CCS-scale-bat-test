package com.scale.bat.stepdefs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.net.MalformedURLException;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.scale.bat.businessPages.BuyersUIpage;
import com.scale.bat.context.ScenarioContext;
import com.scale.bat.context.TestContext;
import com.scale.bat.framework.utility.ConfigurationReader;
import com.scale.bat.framework.utility.DateTimeUtils;
import com.scale.bat.framework.utility.JsonParser;
import com.scale.bat.framework.utility.Log;
import com.scale.bat.framework.utility.PageObjectManager;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ProductCatalogueListPageStepDef {
	private PageObjectManager objectManager;
	public ScenarioContext scenarioContext;
	private Logger log = Log.getLogger(BuyersUIpage.class);
	private ConfigurationReader configReaderObj;

	public ProductCatalogueListPageStepDef(TestContext testContextObj, ScenarioContext context) {
		testContextObj.getDriver();
		objectManager = testContextObj.getObjectManager();
		this.scenarioContext = context;
		configReaderObj = new ConfigurationReader();
	}

	@And("User clicks on show link to view products")
	public void user_clicks_on_show_link_to_view_products() {
		objectManager.getProductCatalogueListPage().showProducts();
	}

	
	@Given("Check if the product is present in a catalogue if yes then delete")
	public void check_if_the_product_is_present_in_a_catalogue_if_yes_then_delete() {
	    
		objectManager.getProductCatalogueListPage().checkProductPresentInCatalogueIfYesThendelete();
	}


	@When("User creates a product")
	public void user_creates_a_product() {
		JSONObject jObj = new JSONObject(new JsonParser().convertJsonToString(scenarioContext.ScenarioDataFilePath))
				.getJSONObject("ProductCreation").getJSONObject("FromUi");
		objectManager.getProductCataloguePage().createNewProduct(jObj);
		scenarioContext.productDetails = jObj.toMap();
		log.info(scenarioContext.productDetails);
		log.info("Product is created!!");
	}
	
	
	@Given("User reads the {string} details")
	public void user_reads_the_details(String string) {
	    
		/*JSONObject jObj = new JSONObject(new JsonParser().convertJsonToString(scenarioContext.SCA161FilePath))
				.getJSONObject("FromUi");
		scenarioContext.productDetails = jObj.toMap();
		log.info(scenarioContext.productDetails);
		log.info("Reads the supplier details from JSON!!");*/
		
		/*JSONObject jObj = new JSONObject(new JsonParser().convertJsonToString(scenarioContext.SCA161FilePathNew))
				.getJSONObject("id");*/
		
		JSONObject jObj = new JSONObject(new JsonParser().convertJsonToString(scenarioContext.SCA161OneProductFilePathNew));
		
		//Retrieving the array
        JSONArray jsonArray = (JSONArray) jObj.getJSONArray("data");
        for(int i=0;i<jsonArray.length();i++) {
        	       	
        	//JSONObject attributes=new JSONObject(jsonArray.getJSONObject(i).getJSONObject("attributes"));
        	//String name=attributes.getString("name");
        	
        	//JSONObject attributes=new JSONObject(jsonArray.getJSONObject(i).getJSONObject("attributes"));
        	//System.out.println(name);
        	
        	JSONObject obj=jsonArray.getJSONObject(i);
        	JSONObject attributes=obj.getJSONObject("attributes");
        	String productName=attributes.getString("name");
        	System.out.println("Name "+ i + " => " + productName);
        	
        	String mpn=attributes.getString("mpn_number");
        	System.out.println("mpn "+ i + " => " + mpn);
        	       	
        	String price=attributes.getString("display_price");
        	System.out.println("price "+ i + " => " + price);
        	
        	String manufacturer=attributes.getString("manufacturer");
        	System.out.println("manufacturer "+ i + " => " + manufacturer);
        	
        	
        	
        }
        

		/*scenarioContext.productDetails = jObj.toMap();
		log.info(scenarioContext.productDetails);
		log.info("Reads the supplier details from JSON!!");*/
		
		
		
		
		/*JSONObject jObj = new JSONObject(new JsonParser().convertJsonToString(scenarioContext.ScenarioDataFilePath));

		JSONObject fromUiObj = jObj.getJSONObject("ProductCreation").getJSONObject("FromUi");
		String UpdatedPrice = new DateTimeUtils().dateWithSpecificFormatt("dd");
		fromUiObj.put("Price", UpdatedPrice);
		fromUiObj.put("UNSPSC", fromUiObj.getString("UNSPSC") + UpdatedPrice);
		fromUiObj.put("SKU", fromUiObj.getString("SKU") + UpdatedPrice);

		jObj.put("FromUi", fromUiObj);

		Map<String, Object> pDetails = fromUiObj.toMap();
		objectManager.getproductDetailsPage().updateProduct(pDetails);
		scenarioContext.productDetails = pDetails;
		scenarioContext.jsonParser.writeJsonFile(jObj, scenarioContext.ScenarioDataFilePath);

		log.info(scenarioContext.productDetails);
		log.info("Product is Updated!!");*/
		
	}

	@When("User adds  the products to My list")
	public void user_adds_the_products_to_My_list() {
	    
		
	}

	/*@When("User delete a product")
	public void user_delete_a_product() {
		JSONObject jObj = new JSONObject(new JsonParser().convertJsonToString(scenarioContext.ScenarioDataFilePath));
		JSONObject details =jObj.getJSONObject("ProductCreation").getJSONObject("FromUi");
		objectManager.getProductCataloguePage().deleteProduct();
		scenarioContext.productDetails = details.toMap();
		
		jObj.put("SKU", "SKU01");
		scenarioContext.jsonParser.writeJsonFile(jObj, scenarioContext.ScenarioDataFilePath);
		log.info(scenarioContext.productDetails);
		log.info("Product is Deleted!!");
	}*/
	
	
	@When("User delete a product")
	public void user_delete_a_product() {
		JSONObject jObj = new JSONObject(new JsonParser().convertJsonToString(scenarioContext.ScenarioDataFilePath));
		JSONObject details =jObj.getJSONObject("ProductCreation").getJSONObject("FromUi");
		objectManager.getProductCataloguePage().deleteProduct();
		scenarioContext.productDetails = details.toMap();
		//jObj.put("SKU", "SKU01");
		//scenarioContext.jsonParser.writeJsonFile(jObj, scenarioContext.ScenarioDataFilePath);
		log.info(scenarioContext.productDetails);
		log.info("Product is Deleted!!");
	}

	@When("User updates a product")
	public void user_updates_a_product() {
		JSONObject jObj = new JSONObject(new JsonParser().convertJsonToString(scenarioContext.ScenarioDataFilePath));

		JSONObject fromUiObj = jObj.getJSONObject("ProductCreation").getJSONObject("FromUi");
		String UpdatedPrice = new DateTimeUtils().dateWithSpecificFormatt("dd");
		fromUiObj.put("Price", UpdatedPrice);
		fromUiObj.put("UNSPSC", fromUiObj.getString("UNSPSC") + UpdatedPrice);
		fromUiObj.put("SKU", fromUiObj.getString("SKU") + UpdatedPrice);

		jObj.put("FromUi", fromUiObj);

		Map<String, Object> pDetails = fromUiObj.toMap();
		objectManager.getproductDetailsPage().updateProduct(pDetails);
		scenarioContext.productDetails = pDetails;
		scenarioContext.jsonParser.writeJsonFile(jObj, scenarioContext.ScenarioDataFilePath);

		log.info(scenarioContext.productDetails);
		log.info("Product is Updated!!");

	}
	
	
	@When("User update the product details")
	public void user_update_the_product_details() {
	    
		JSONObject jObj = new JSONObject(new JsonParser().convertJsonToString(scenarioContext.ScenarioDataFilePath));
		JSONObject fromUiObj = jObj.getJSONObject("ProductCreation").getJSONObject("FromUi");
		String Currenntday = new DateTimeUtils().dateWithSpecificFormatt("dd");
		String Price1 = fromUiObj.getString("Price");
		double Price = Double.parseDouble(Price1);
		double UpdatedPriceDbl=Price-2;
		String UpdatedPrice=Double.toString(UpdatedPriceDbl);
		
		fromUiObj.put("Price", UpdatedPrice);
		fromUiObj.put("UNSPSC", fromUiObj.getString("UNSPSC") + Currenntday);
		fromUiObj.put("SKU", fromUiObj.getString("SKU") + Currenntday);

		jObj.put("FromUi", fromUiObj);

		Map<String, Object> pDetails = fromUiObj.toMap();
		objectManager.getproductDetailsPage().updateProduct(pDetails);
		scenarioContext.productDetails = pDetails;
		scenarioContext.jsonParser.writeJsonFile(jObj, scenarioContext.ScenarioDataFilePath);

		log.info(scenarioContext.productDetails);
		log.info("Product is Updated!!");

	}

	

	// Hardcoded value is provided for now. Hard code value will be removed later
	@Then("User filter the catalogue list page using filter \"([^\"]*)\"")
	public void filter_product_catalogue_list_page(String filtertype) {
		String SupplierName=configReaderObj.adminPanelSupplierName(filtertype);
		scenarioContext.setKeyValue(filtertype, SupplierName);
		//properties.getProperty("ccs.admin.panel.username.userrolename."+filtertype.toLowerCase();
		objectManager.getProductCatalogueListPage().filterData(filtertype, scenarioContext.getContext(filtertype));
	}

	@Then("Title of each catalogue should have supplier and catalogue name")
	public void verify_title_of_each_catalogue() {
		objectManager.getProductCatalogueListPage().verifyContent(scenarioContext.getContext("supplier"));
	}

	@And("User clicks on edit button to view product details")
	public void user_clicks_on_edit_button_to_view_products() {
		objectManager.getProductCatalogueListPage().editProduct();
	}
	
	@When("User removes the Next Day Delivery Available option")
	public void user_removes_the_Next_Day_Delivery_Available_option() {
	    
		objectManager.getproductDetailsPage().removeTheNextDayOption();
	}
	
	@When("User adds the Next Day Delivery option")
	public void user_adds_the_Next_Day_Delivery_option() {
	    
		objectManager.getProductCataloguePage().addsTheNextDayOption(scenarioContext.productDetails);
	}

	@And("Updated quantity will be replaced by previous value on clicking cancel button")
	public void update_qty_will_be_replaced_by_previous_value() {
//		objectManager.getproductDetailsPage().changeQuantity();
//		objectManager.getproductDetailsPage().cancelTheUpdatedQuantity();

	}

	@And("User updates the stock quantity of a product")
	public void user_updates_the_stock_quantity_of_a_product() {
		objectManager.getproductDetailsPage().updateStockQuantity();
	}
	
	@When("User reduces the stock of the product")
	public void user_reduces_the_stock_of_the_product() {
	    objectManager.getproductDetailsPage().reduceStockQuantity(scenarioContext.productDetails);
	}
	
	@When("User enters the Increased product price in Product Price textbox.")
	public void user_enters_the_Increased_product_price_in_Product_Price_textbox() {
		JSONObject jObj1 = new JSONObject(new JsonParser().convertJsonToString(scenarioContext.ScenarioDataFilePathSCA332Checkout))
				.getJSONObject("Product2");
		 objectManager.getproductDetailsPage().updateProductPrice(jObj1);
		 
		 
	}
	
		
	@When("User enters the actual product price in product price textbox")
	public void user_enters_the_actual_product_price_in_product_price_textbox() {
	    
		JSONObject jObj1 = new JSONObject(new JsonParser().convertJsonToString(scenarioContext.ScenarioDataFilePathSCA332Checkout))
				.getJSONObject("Product2");
		 objectManager.getproductDetailsPage().enterActualProductPrice(jObj1);
	}
	
	@When("User Clicks on Update button")
	public void user_Clicks_on_Update_button() {
	
		objectManager.getproductDetailsPage().updateButton();
	}
	
	
	@When("User update the stock with actual previous stock")
	public void user_update_the_stock_with_actual_previous_stock() {
	   
		objectManager.getproductDetailsPage().updateStockQuantityAsPerJson(scenarioContext.productDetails);
	}
	
	@When("User updates the product with MPN {string} stock")
	public void user_updates_the_product_with_MPN_stock(String mpn) {
	    
		if (mpn.equals("187749-001")) {

			JSONObject jObj1 = new JSONObject(new JsonParser().convertJsonToString(scenarioContext.ScenarioDataFilePathSCA332Checkout))
					.getJSONObject("Product1");

			objectManager.getproductDetailsPage().updateActualStockCheckoutProduct(jObj1);
			 
		} else if (mpn.equals("195654-002")) {

			JSONObject jObj1 = new JSONObject(new JsonParser().convertJsonToString(scenarioContext.ScenarioDataFilePathSCA332Checkout))
					.getJSONObject("Product2");
			objectManager.getproductDetailsPage().updateActualStockCheckoutProduct(jObj1);

		}
	}


	@Then("Title of each catalogue should not have other supplier and catalogue name")
	public void Title_of_each_catalogue_should_not_have_other_supplier_and_catalogue_name() {
		objectManager.getProductCatalogueListPage().verifyContent(scenarioContext.getContext("supplier"));
	}

	@Then("On updating \"([^\"]*)\" stock quantity an warning message will be displayed")
	public void on_updating_stock_quantity_an_warning_message_will_be_displayed(String inputTyp) {
		objectManager.getproductDetailsPage().checkWarningMessageOnStockField(inputTyp);
	}
	
	//Filter StepDefination
	
	@Given("User counts the given filter value {string} in the PCLP table {string} applying filter {string}")
	public void user_counts_the_given_filter_value_in_the_PCLP_table_applying_filter(String filterValue, String before, String filter) throws MalformedURLException, InterruptedException{
	    
		objectManager.getProductCatalogueListPage().checkTotalRowsCountOfSelectedCAR(filterValue, before, filter);
	}
	
	@Given("User selects the value {string} from the filter {string}")
	public void user_selects_the_value_from_the_filter(String value, String filter) throws Exception{
		
		objectManager.getProductCatalogueListPage().filterData(filter, value);
	}
	
	
	@When("User clicks on Search button")
	public void user_clicks_on_Search_button() throws Exception{
	 
		objectManager.getProductCatalogueListPage().searchButton();
	}
	
	
	@Then("Verify the filter {string} with result value {string}")
	public void verify_the_filter_with_result_value(String fillter, String filterValue) {
	   
		objectManager.getProductCatalogueListPage().verifyFilterValue(fillter, filterValue);
	}
	
	@Given("User validates the supplier{int} product total delivery total VAT and grand Total in basket for Standard UK Mainland")
	public void user_validates_the_supplier_product_total_delivery_total_VAT_and_grand_Total_in_basket_for_Standard_UK_Mainland(Integer int1) {
	    
		JSONObject jObj1 = new JSONObject(new JsonParser().convertJsonToString(scenarioContext.ScenarioDataFilePathSCA223Sup1))
				.getJSONObject("Product1");
		
		JSONObject jObj2 = new JSONObject(new JsonParser().convertJsonToString(scenarioContext.ScenarioDataFilePathSCA223Sup1))
				.getJSONObject("Product2").getJSONObject("FromUi");
		
		objectManager.getBuyersUIBasketpage().validateSupplier1Product1and2VATAndOtherDetailsForStandardUKMainland(jObj1,jObj2);
	}
	
	@Then("User validates the total delivery total VAT and grand Total of supplier{int} in basket for {string}")
	public void user_validates_the_total_delivery_total_VAT_and_grand_Total_of_supplier_in_basket_for(Integer int1, String string) {
	    
		JSONObject jObj1 = new JSONObject(new JsonParser().convertJsonToString(scenarioContext.ScenarioDataFilePathSCA332Checkout))
				.getJSONObject("Product1");
		
		objectManager.getBuyersUIBasketpage().validateSupplier1Product1DetailsForStandardUKMainland(jObj1);
	}
	
	
	@Then("User validates the products in Indicative quote is added with the product already present in the basket")
	public void user_validates_the_products_in_Indicative_quote_is_added_with_the_product_already_present_in_the_basket() {
	    
		JSONObject jObj1 = new JSONObject(new JsonParser().convertJsonToString(scenarioContext.ScenarioDataFilePathSCA223Sup1))
				.getJSONObject("Product1");
		
		JSONObject jObj2 = new JSONObject(new JsonParser().convertJsonToString(scenarioContext.ScenarioDataFilePathSCA223Sup1))
				.getJSONObject("Product2").getJSONObject("FromUi");
		
		JSONObject jObj3 = new JSONObject(new JsonParser().convertJsonToString(scenarioContext.ScenarioDataFilePathSCA223Sup2))
				.getJSONObject("Product1");
		
		
		objectManager.getBuyersUIBasketpage().validateIndicativeQuoteForStandardUKMainland(jObj1,jObj2,jObj3);
		
		
	}
	
	
	@Given("User validates the supplier{int} product total delivery total VAT and grand Total in basket for Next Business Day")
	public void user_validates_the_supplier_product_total_delivery_total_VAT_and_grand_Total_in_basket_for_Next_Business_Day(Integer int1) {
	
		JSONObject jObj1 = new JSONObject(new JsonParser().convertJsonToString(scenarioContext.ScenarioDataFilePathSCA223Sup1))
				.getJSONObject("Product1");
		
		JSONObject jObj2 = new JSONObject(new JsonParser().convertJsonToString(scenarioContext.ScenarioDataFilePathSCA223Sup1))
				.getJSONObject("Product3").getJSONObject("FromUi");
		
		objectManager.getBuyersUIBasketpage().validateSupplier1Product1and2VATAndOtherDetailsForNextBusinessDay(jObj1,jObj2);

	}
	
	
	@Given("User validates the supplier{int} product total delivery total VAT and grand Total in basket for Standard UK Non Mainland")
	public void user_validates_the_supplier_product_total_delivery_total_VAT_and_grand_Total_in_basket_for_Standard_UK_Non_Mainland(Integer int1) {
	    
		JSONObject jObj1 = new JSONObject(new JsonParser().convertJsonToString(scenarioContext.ScenarioDataFilePathSCA223Sup1))
				.getJSONObject("Product2").getJSONObject("FromUi");
		
		JSONObject jObj2 = new JSONObject(new JsonParser().convertJsonToString(scenarioContext.ScenarioDataFilePathSCA223Sup2))
				.getJSONObject("Product1");
		
		objectManager.getBuyersUIBasketpage().validateSupplier1Product1and2VATAndOtherDetailsForStandardUKNonMainland(jObj1,jObj2);
		
	}
	
	

	@Given("User validates products details with VAT{int} and VAT{int} for supplier{int} and supplier{int} in basket page")
	public void user_validates_products_details_with_VAT_and_VAT_for_supplier_and_supplier_in_basket_page(Integer int1,
			Integer int2, Integer int3, Integer int4) {
		
		//Supplier1 Product1 VAT20
		JSONObject jObjSupp1Produt1VAT20 = new JSONObject(new JsonParser().convertJsonToString(scenarioContext.ScenarioTestDataSCA144CheckoutComplete))
				.getJSONObject("Supp1Product1VAT20");
		
		//Supplier1 Product2 VAT0
		JSONObject jObjSupp1Produt2VAT0 = new JSONObject(new JsonParser().convertJsonToString(scenarioContext.ScenarioTestDataSCA144CheckoutComplete))
				.getJSONObject("Supp1Product2VAT0");
		
		//Supplier2 Product1 VAT20
		JSONObject jObjSupp2Produt1VAT20 = new JSONObject(new JsonParser().convertJsonToString(scenarioContext.ScenarioTestDataSCA144CheckoutComplete))
				.getJSONObject("Supp2Product1VAT20");
		
		//Supplier2 Product2 VAT0
		JSONObject jObjSupp2Produt2VAT0 = new JSONObject(new JsonParser().convertJsonToString(scenarioContext.ScenarioTestDataSCA144CheckoutComplete))
				.getJSONObject("Supp2Product2VAT0");
		
		objectManager.getBuyersUIBasketpage().validateSupplier1AndSupplier2ProductswithVATAndOtherDetailsForStandardUKMainland(jObjSupp1Produt1VAT20,jObjSupp1Produt2VAT0,jObjSupp2Produt1VAT20,jObjSupp2Produt2VAT0);
		
				
	}
	
	
	@Then("User navigates to checkout payments page")
	public void user_navigates_to_checkout_payments_page() {
	 
		objectManager.getBuyersUIBasketpage().verifyPaymentCheckOutPage();
		
	}

	@Then("User validates the Order summary of supplier{int}")
	public void user_validates_the_Order_summary_of_supplier(Integer int1) {
	   
		objectManager.getBuyersUIBasketpage().verifyOrderSummaryInPaymentCheckoutPage();
		
	}

	@Then("User enters the PO number in the PO number textbox")
	public void user_enters_the_PO_number_in_the_PO_number_textbox() {
	    
		objectManager.getBuyersUIBasketpage().enterPONumber();
	}

	@Then("User navigates to checkout summary page")
	public void user_navigates_to_checkout_summary_page() {
	    
		objectManager.getBuyersUIBasketpage().verifyCheckOutSummaryPage();
	}

	@Then("User validates the product details on checkout summary page")
	public void user_validates_the_product_details_on_checkout_summary_page() {
	    
		objectManager.getBuyersUIBasketpage().verifyProductDetailsOnCheckoutSummaryPage();
	}

	@Then("User click on Terms & Conditions checkbox")
	public void user_click_on_Terms_Conditions_checkbox() {
	    
		objectManager.getBuyersUIBasketpage().checksOnTermsAndConditions();
	}

	@Then("User validates the order")
	public void user_validates_the_order() {
	    
		objectManager.getBuyersUIBasketpage().verifyProductDetailsOnOrderPage();
	}
	

	@Given("User navigates to {string} page")
	public void user_navigates_to_page(String deliveryNoteHeader) {
	 
		objectManager.getBuyersUIBasketpage().vlidateAddDeliveryNotePageHeader(deliveryNoteHeader);
	}

	@Given("User is able is able to see text field with label {string} below {string} header")
	public void user_is_able_is_able_to_see_text_field_with_label_below_header(String deliveryNoteTextboxLabel, String deliveryNoteHeader) {
		objectManager.getBuyersUIBasketpage().vlidateDeliveryNoteTextboxIsVisible(deliveryNoteTextboxLabel,deliveryNoteHeader);
	}

	@Given("User is able to see the message {string} below text field")
	public void user_is_able_to_see_the_message_below_text_field(String characterLimitMsg) {
		objectManager.getBuyersUIBasketpage().vlidateCharacterLimitMsg(characterLimitMsg);
	    
	}
	
	@Given("User enters {string} character length text in Delivery note text field and validates message {string}")
	public void user_enters_character_length_text_in_Delivery_note_text_field_and_validates_message(String charLength, String characterLimitMsg) {
		objectManager.getBuyersUIBasketpage().vlidateCharacterLimitMsgAfterEnteredText(charLength,characterLimitMsg);
	}
	
	@Given("User clears the Delivery note")
	public void user_clears_the_Delivery_note() {
	    
		objectManager.getBuyersUIBasketpage().clearDeliveryNote();
	}
	
	@Given("User clicks on Cancel button")
	public void user_clicks_on_Cancel_button() {
		objectManager.getBuyersUIBasketpage().clickOnCancleButton();
	}
	
	@Given("User clicks on Save button")
	public void user_clicks_on_Save_button() {
		objectManager.getBuyersUIBasketpage().clickOnSaveButton();
	}

	@Given("User navigates back to Basket page")
	public void user_navigates_back_to_Basket_page() {
	    
	}

	@Given("User Validates the {string} link on basket page")
	public void user_Validates_the_link_on_basket_page(String string) {
		objectManager.getBuyersUIBasketpage().validateAddDeliveryNoteLink();
		
	}
	
	@Given("User validates the Delivery note text {string} in basket page")
	public void user_validates_the_Delivery_note_text_in_basket_page(String deliveryNoteText) {
		objectManager.getBuyersUIBasketpage().validateDeliveryNoteTextInBasketPage(deliveryNoteText);
	}

	
	@Given("User validates the {string} link is visible")
	public void user_validates_the_link_is_visible(String string) {
		objectManager.getBuyersUIBasketpage().validateEditTheDeliveryNoteLinkLink();
	}





}

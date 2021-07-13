package com.scale.bat.stepdefs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.openqa.selenium.WebElement;

import com.scale.bat.businessPages.BuyersUIpage;
import com.scale.bat.context.ScenarioContext;
import com.scale.bat.context.TestContext;
import com.scale.bat.framework.utility.JsonParser;
import com.scale.bat.framework.utility.Log;
import com.scale.bat.framework.utility.PageObjectManager;
import com.scale.bat.framework.utility.TakeScreenShotAndAddToWordDoc;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class BuyerUiStepDefs {
	private Logger log = Log.getLogger(BuyersUIpage.class);

	private PageObjectManager objectManager;
	public ScenarioContext scenarioContext;

	public BuyerUiStepDefs(TestContext testContextObj, ScenarioContext scenarioContext) {
		testContextObj.getDriver();
		objectManager = testContextObj.getObjectManager();
		this.scenarioContext = scenarioContext;
	}

	@When("User add {int} product in basket")
	public void user_add_product_in_basket(int numberOfProducts) {
		objectManager.getBuyersUIpage().addProductToBasket(numberOfProducts);
	}
	
	@And("User removes all the products from the basket")
	public void user_removes_all_the_products_from_the_basket() {
	    
		//objectManager.getBuyersUIpage().removeProductsFromTheBasket();
		
		boolean mgsNotVisible=objectManager.getBuyersUIBasketpage().validateWarningMessageIsPresentOrNot();
	
		if(mgsNotVisible==true) {
			
		}else {
			
			objectManager.getBuyersUIpage().clickElement(objectManager.getBuyersUIpage().getclearBasketLink());
		}
		
		
	}

	@When("User add {int} products to compare")
	public void user_add_product_to_compare(int numberOfProducts) {
		objectManager.getBuyersUIpage().compareProducts(numberOfProducts);
	}

	@Then("{string} is shown to the buyer")
	public void is_shown_to_the_buyer(String pageName) {
		objectManager.getBuyersUIpage().isBuyerNavigatedToGivenPage(pageName);
	}

	@And("User clicks on {string} in buyers UI")
	public void user_clicks_on_button_on_buyers_UI(String button) throws InterruptedException {
		switch (button) {
		case "Continue Shopping":
			objectManager.getBuyersUIpage().clickElement(objectManager.getBuyersUIpage().getContinueShoppingElement());
			Thread.sleep(2000);
			break;
		case "Proceed to basket":
			objectManager.getBuyersUIpage().clickElement(objectManager.getBuyersUIpage().getProceedToBasketElement());
			Thread.sleep(1000);
			break;
		case "browser back button":
			objectManager.getBuyersUIpage().navigateBackfromBrowser();
			break;
		case "continue shopping link":
			objectManager.getBuyersUIpage()
					.clickByLinkText(objectManager.getBuyersUIpage().getContinueShoppingLinkOnBasketPage());
			Thread.sleep(1000);
			break;
		case "Delete button":
			objectManager.getBuyersUIpage().clickElement(objectManager.getBuyersUIpage().deleteButtonOnBasketElement());
			break;
		case "PLP image":
			objectManager.getBuyersUIpage().navigateToPDPPage();
			break;
		case "Basket Link":
			objectManager.getBuyersUIpage().clickElement(objectManager.getBuyersUIpage().getBasketLink());
			Thread.sleep(2000);
			//TakeScreenShotAndAddToWordDoc.captureScreenShotNew();
			break;
		case "Add to basket":
			objectManager.getBuyersUIpage().addElementToBasket();
			break;
		case "My Account link":
			objectManager.getBuyersUIpage().clickElement(objectManager.getBuyersUIpage().getMyAccountLink());
			log.info("User clicks on 'My Account link' ");
			break;
		case "Clear basket":
			//TakeScreenShotAndAddToWordDoc.captureScreenShotNew();
			objectManager.getBuyersUIpage().clickElement(objectManager.getBuyersUIpage().getclearBasketLink());
			Thread.sleep(2000);
			break;
		case "Add to list":
			objectManager.getBuyersUIpage().clickElement(objectManager.getBuyersUIpage().getaddToList());
			assertEquals(objectManager.getBuyersUIpage().getaddToListMsg(), "The product was successfully added to the wish list");
			Thread.sleep(2000);
			break;
			
		case "View list":
			objectManager.getBuyersUIpage().clickElement(objectManager.getBuyersUIpage().getviewListLink());
			Thread.sleep(2000);
			break;
			
		case "Compare button":
			objectManager.getBuyersUIpage().clickElement(objectManager.getBuyersUIpage().getcompareButton());
			assertEquals(objectManager.getBuyersUIpage().getproductAddedToComparePageMsg(), "Product added for comparison");
			Thread.sleep(2000);
			break;
			
		case "Add to list Compare Page":
			objectManager.getBuyersUIpage().clickElement(objectManager.getBuyersUIpage().getaddToListBtnComparePage());
			assertEquals(objectManager.getBuyersUIpage().getaddToListMsg(), "The product was successfully added to the wish list");
			Thread.sleep(2000);
			break;
			
		case "back link":
			objectManager.getBuyersUIpage().clickElement(objectManager.getBuyersUIpage().getbackLink());
			Thread.sleep(2000);
			break;
			
		case "Remove link":
			objectManager.getBuyersUIpage().clickElement(objectManager.getBuyersUIpage().getremoveLink());
			assertEquals(objectManager.getBuyersUIpage().getaddToListMsg(), "Product removed from comparison");
			Thread.sleep(2000);
			break;
			
		case "My list visit button":
			objectManager.getBuyersUIpage().clickElement(objectManager.getBuyersUIpage().getMyAccountMyListVisitButton());
			Thread.sleep(2000);
			break;
			
		case "My list delete button":
			objectManager.getBuyersUIpage().clickElement(objectManager.getBuyersUIpage().getMyListDeleteProductButton());
			assertEquals(objectManager.getBuyersUIpage().getaddToListMsg(), "The selected product was deleted from the wish list");
			Thread.sleep(2000);
			break;
			
		case "Quantity Update button":
			objectManager.getBuyersUIpage().clickElement(objectManager.getBuyersUIpage().quantityUpdateButtonBasket());
			Thread.sleep(2000);
			break;
			
		case "Quote":
			objectManager.getBuyersUIpage().clickElement(objectManager.getBuyersUIpage().getQuoteLink());
			break;
		case "Raise quote":
			objectManager.getBuyersUIpage().clickElement(objectManager.getBuyersUIQuotespage().getRaiseQuoteLink());
			break;
		case "Manage quotes Visit link":
			objectManager.getBuyersUIpage().clickElement(objectManager.getBuyersUIpage().getManageQuotesVisitElement());
			break;
		case "Manage quote Search button":
				objectManager.getBuyersUIpage().clickElement(objectManager.getBuyersUIpage().manageQuoteSearchButton());
			break;
			
		case "Quote refrence link":
			objectManager.getBuyersUIpage().clickElement(objectManager.getBuyersUIQuotespage().getFirstRowQuoteNoLink());
		break;
		
		case "Update Link Add to list":
			objectManager.getBuyersUIpage().clickElement(objectManager.getBuyersUIpage().getUpdateLinkAddToList());
		break;
		
		case "Update Link":
			objectManager.getBuyersUIpage().clickElement(objectManager.getBuyersUIpage().getUpdateLinkAddToList());
		break;
		
		case "Clear my basket and add these items":
			objectManager.getBuyersUIpage().clickElement(objectManager.getBuyersUIQuotespage().getClearMyTextBtnt());
		break;
		
		case "Add to basket button on Indicative quote":
			objectManager.getBuyersUIpage().clickElement(objectManager.getBuyersUIQuotespage().addtoBasketBtnOnIndicative());
		break;
		
		case "Checkout":
			objectManager.getBuyersUIpage().clickElement(objectManager.getBuyersUIpage().getCheckout());
		break;
		
		case "Save and continue":
			objectManager.getBuyersUIpage().clickElement(objectManager.getBuyersUIQuotespage().saveAndContinueBtnOnPayentCheckout());
		break;
		
		case "Place order":
			objectManager.getBuyersUIpage().clickElement(objectManager.getBuyersUIQuotespage().placeOrderCheckout());
		break;
		
		case "Add a delivery note":
			objectManager.getBuyersUIpage().clickElement(objectManager.getBuyersUIBasketpage().clickDeliveryNote());
			Thread.sleep(2000);
		break;
		
		case "Edit the delivery note":
			objectManager.getBuyersUIpage().clickElement(objectManager.getBuyersUIBasketpage().editDeliveryNote());
			Thread.sleep(2000);
		break;
		
		
		
		}
	}

	@And("User navigates to PLP of buyers UI")
	public void user_navigates_to_PLP_of_buyers_UI() {
		objectManager.getBuyersUIpage().navigateToPLPPage();
	}

	@Then("{string} button should not be visible")
	public void button_should_not_be_visible(String buttonOrLinkText) {
		switch (buttonOrLinkText) {
		case "Add to Basket":
			assertFalse(
					objectManager.getBuyersUIpage().isElementPresent(objectManager.getBuyersUIpage().getAddToBasket()));
			break;
		case "Basket":
			assertFalse(
					objectManager.getBuyersUIpage().isElementPresent(objectManager.getBuyersUIpage().getBasketLink()));
			break;
		}
	}

	@When("User search a product with SKU number")
	public void user_search_a_product_with_SKU_number() {
		
		JSONObject jObj = new JSONObject(new JsonParser().convertJsonToString(scenarioContext.ScenarioDataFilePath))
				.getJSONObject("ProductCreation").getJSONObject("FromUi");
		scenarioContext.productDetails = jObj.toMap();
		
		log.info(scenarioContext.productDetails);
		objectManager.getBuyersUIpage().searchProduct(scenarioContext.productDetails.get("SKU").toString());
	}
	
	@When("User clicks on Add to basket button")
	public void user_clicks_on_Add_to_basket_button() {
	    
		objectManager.getBuyersUIpage().addToBasketButton();
	}
	
	@And("User clears the basket")
	public void user_clears_the_basket() {
	    
		objectManager.getBuyersUIpage().clearBasket();
			
	}
	
	@And("User clicks on My List Visit button")
	public void user_clicks_on_My_List_Visit_button() {
	    
		objectManager.getBuyersUIpage().clickOnMyListVisitButton();
	}
	
	@Given("User validates the disclaimer if product is present in Wish List page")
	public void user_validates_the_disclaimer_if_product_is_present_in_Wish_List_page() {
	    
		objectManager.getBuyersUIMyListpage().validateDisclaimerMessageOnMyList();
		
	}
	
	@Given("User validates information text in My list if no items in the wish list")
	public void user_validates_information_text_in_My_list_if_no_items_in_the_wish_list() {
	    
		objectManager.getBuyersUIMyListpage().validateInformationTextMyList();
	}

	@Given("User validates Back button is hidden")
	public void user_validates_Back_button_is_hidden() {
	    
		objectManager.getBuyersUIMyListpage().validateBackLinkIsNotVisibleOnMyList();
		
	}
	

	@And("User clears the Mylist")
	public void user_clears_the_Mylist() {
	    
		objectManager.getBuyersUIpage().clearMyList();
	}
	
	@And("User validates the products detail in My List page")
	public void user_validates_the_products_detail_in_My_List_page() {
	    
		//objectManager.getBuyersUIMyListpage().verifyProductDetailsOnMyListPage(scenarioContext.productDetails,scenarioContext.SCA161OneProductFilePathNew);
		objectManager.getBuyersUIMyListpage().verifyProductDetailsOnMyListPage(scenarioContext.SCA161OneProductFilePathNew);
	}
	
	
	@And("User Validates the more than one products details in My List page")
	public void user_Validates_the_more_than_one_products_details_in_My_List_page() {
		
		objectManager.getBuyersUIMyListpage().verifyProductDetailsOnMyListPage(scenarioContext.SCA161TwoProductsFilePathNew);
	    
	}

	@And("User Clicks on AddToBasket button of the selected product on My List page")
	public void user_Clicks_on_AddToBasket_button_of_the_selected_product_on_My_List_page() {
	    
		objectManager.getBuyersUIMyListpage().clickOnAddtoBasketButton();
		
	}
	
	@And("User Clicks on Clear my basket and add these items button")
	public void user_Clicks_on_Clear_my_basket_and_add_these_items_button() {
	    
		objectManager.getBuyersUIMyListpage().clickOnClearMyBasketAndAddTheseItemsbutton();
		
	}
	
	@And("User Clicks on Add these items to current basket button")
	public void user_Clicks_on_Add_these_items_to_current_basket_button() {
	    
		objectManager.getBuyersUIMyListpage().clickOnAddTheseItemsToCurrentBasketbutton();
	}


	@And("User Validated the product details on basket page")
	public void user_Validated_the_product_details_on_basket_page() {
		
		
		objectManager.getBuyersUIBasketpage().verifyProductDetailsOnBasketPage(scenarioContext.SCA161OneProductFilePathNew);
	    
	}
	
	
	
	@And("User Validated a product details on basket page")
	public void user_Validated_a_product_details_on_basket_page() {
	    
		objectManager.getBuyersUIBasketpage().verifyProductDetailsOnBasketPage();
	}
	
	@Given("User validates the default delivery option should be pre-populated as {string}")
	public void user_validates_the_default_delivery_option_should_be_pre_populated_as(String defaultDeliveryOption) {
	    
		objectManager.getBuyersUIBasketpage().verifyTheDefaultDeliveryOption(defaultDeliveryOption);
	}

	@Given("User validates the Delivery method drop down options")
	public void user_validates_the_Delivery_method_drop_down_options() {
	    
		objectManager.getBuyersUIBasketpage().verifyAllTheOptionsInDeliveryOptionDropDown();
		
	}
	
	@Given("User validates the message below Delivery method text")
	public void user_validates_the_message_below_Delivery_method_text() {
		objectManager.getBuyersUIBasketpage().validateWarningMessageIsVisiblenOnBasketPage();
	}
	
	
	@Given("User validates the message after Clear basket button clicked")
	public void user_validates_the_message_after_Clear_basket_button_clicked() throws IOException {
	    
		objectManager.getBuyersUIBasketpage().verifyProductMessageOnBasketPage();
	}
	
	@Then("User validates warning message in a blue bannered box and clicking on dropdown gives products details")
	public void user_validates_warning_message_in_a_blue_bannered_box_and_clicking_on_dropdown_gives_products_details() throws IOException {
	    
		objectManager.getBuyersUIBasketpage().verifyBlueBanneredBox(scenarioContext.productDetails);
	}
	
	@Then("User validates the reduced product quantity on basket page")
	public void user_validates_the_reduced_product_quantity_on_basket_page() throws IOException {
	    
		objectManager.getBuyersUIBasketpage().verifyProductReducedQuantityOnBasketPage();
	}

	
	@When("User validates the generic message {string}")
	public void user_validates_the_generic_message(String genericMsg) throws IOException {
	    
		objectManager.getBuyersUIBasketpage().verifyBasketGenericMessageOnBasketPage(genericMsg);
	}
	
	
	@When("User validates the basket count {string}")
	public void user_validates_the_basket_count(String basketCount) throws IOException {
		
		objectManager.getBuyersUIBasketpage().verifyBasketLinkCount(basketCount);
	}


	@When("User validates {string} delivery method will greyed out in delivery dropdown")
	public void user_validates_delivery_method_will_greyed_out_in_delivery_dropdown(String string) {
	    
		objectManager.getBuyersUIBasketpage().validateNextDayDeliveryIsGreyedOutOnBasketPage();
	}

	@When("User validates warning message should be visible when {string} delivery method is greyed out")
	public void user_validates_warning_message_should_be_visible_when_delivery_method_is_greyed_out(String string) {
	    
		objectManager.getBuyersUIBasketpage().validateWarningMessageIsVisiblenOnBasketPage();
	}
	
	@When("User validates warning message should be hidden when all delivery methods are available on Basket page")
	public void user_validates_warning_message_should_be_hidden_when_all_delivery_methods_are_available_on_Basket_page() {
	    
		objectManager.getBuyersUIBasketpage().validateWarningMessageIsHiddenOnBasketPage();
	}
	

	@Given("User validates the Clear basket button and {string} message on basket page")
	public void user_validates_the_Clear_basket_button_and_message_on_basket_page(String Msg) {
    
		objectManager.getBuyersUIBasketpage().verifyProductMessageAndClearBasketButttonOnBasketPage(Msg);
	}

	
	@And("User Validated multiple products details on basket page")
	public void user_Validated_multiple_products_details_on_basket_page() {
	    
		objectManager.getBuyersUIBasketpage().verifyMultipleProductsDetailsOnBasketPage();
	}
	
	@And("User Validates products details after clicking on Add these items to current basket button")
	public void user_Validates_products_details_after_clicking_on_Add_these_items_to_current_basket_button() {
	    
		objectManager.getBuyersUIBasketpage().verifyMultipleProductsDetailsOnBasketPageAfterAddTheseItemsButton();
	}
	
	
	@And("User Validated the more than one products details on basket page")
	public void user_Validated_the_more_than_one_products_details_on_basket_page() {
	    
		objectManager.getBuyersUIBasketpage().verifyProductDetailsOnBasketPage();
	}
	
	
	@And("User validates the product message Product added to your basket")
	public void user_validates_the_product_message_Product_added_to_your_basket() {
	 
		objectManager.getBuyersUIBasketpage().verifyMessageProductAddToBasketMsg();
	}
	
	@When("User validates the product quantity on basket page")
	public void user_validates_the_product_quantity_on_basket_page() {
	    
		objectManager.getBuyersUIBasketpage().verifyProductUpdatedQuantityOnBasket();
	}

	
	@And("User validates the product message You cannot add the selected product to the basket Its out of stock")
	public void user_validates_the_product_message_You_cannot_add_the_selected_product_to_the_basket_Its_out_of_stock() {
	 
		objectManager.getBuyersUIBasketpage().VerifyMessageItsOutOfStock();
	}
	
	@And("User validates the product message All products were added to your basket")
	public void user_validates_the_product_message_All_products_were_added_to_your_basket() {
	    
		objectManager.getBuyersUIBasketpage().verifyMessageAllProductsWereAddedToYourBasket();
	}
	
	@When("User validates the product price on basket page")
	public void user_validates_the_product_price_on_basket_page() {
	    
		objectManager.getBuyersUIBasketpage().verifyProductPriceDetails(scenarioContext.productDetails);
	}
	
	@When("User validates the delivery cost as per delivery method selected")
	public void user_validates_the_delivery_cost_as_per_delivery_method_selected() {
	    
		objectManager.getBuyersUIBasketpage().verifyProductDeliveryCostDetails(scenarioContext.productDetails);
	}
	
	@Given("User checks {string} title on basket page")
	public void user_checks_title_on_basket_page(String basketPageTitle) {
		
		objectManager.getBuyersUIBasketpage().checkTitelBasket(basketPageTitle);
	}
	
	@Given("User validates the breadcrumbs {string} and {string} in basket page")
	public void user_validates_the_breadcrumbs_and_in_basket_page(String Home, String Basket) {
		
		objectManager.getBuyersUIBasketpage().checkBreadcrumbsBasketPage(Home,Basket);
	}

	
	@Given("User select the Delivery option {string}")
	public void user_select_the_Delivery_option(String deliveryMethod) {
	   
		objectManager.getBuyersUIBasketpage().selectTheDeliveryMethod(deliveryMethod);
	}
	
	@Given("User selects the address {string}")
	public void user_selects_the_address(String address) {
	    
		objectManager.getBuyersUIBasketpage().selectTheAddress(address);
	}
	
	@When("User update the product quantity by {string}.")
	public void user_update_the_product_quantity_by(String quantity) {
	   
		objectManager.getBuyersUIBasketpage().updateTheProductQuantity(quantity);
	}


	@Then("verify the product details in buyers UI")
	public void verify_the_product_details_in_buyers_UI() {
		objectManager.getBuyersUIpage().navigateToPDPPage();
		objectManager.getBuyersUIPDPPage().verifyProductDetails(scenarioContext.productDetails);
	}

	@Then("verify the product details should not be available on buyers UI")
	public void verify_the_product_details_unavialable_on_buyers_UI() {
		//objectManager.getBuyersUIpage().isElementPresent("0 results");
		objectManager.getBuyersUIpage().isElementPresent("No results found.");
		
	}

	@Then("User Validate last updated product by supplier {string} is showing in PDP price table on buyers UI")
	public void user_Validate_last_updated_product_by_supplier_is_showing_in_PDP_price_table_on_buyers_UI(
			String supplierName) {

		objectManager.getBuyersUIPDPPage().verifyLastUpdatedProductDetails(scenarioContext.productDetails,
				supplierName);
	}
	
	
	@When("User validates the products details on Mylist page")
	public void user_validates_the_products_details_on_Mylist_page() {
	    
		objectManager.getBuyersUIMyListpage().verifyProductDetailsOnMyListPage(scenarioContext.productDetails);
	}
	
	@When("User upadates the product quantity more than available stock of the product")
	public void user_upadates_the_product_quantity_more_than_available_stock_of_the_product() {
	  
		objectManager.getBuyersUIMyListpage().updatetheProductQuantity(scenarioContext.productDetails);
	}
	
	@When("User validates the warning message {string} items")
	public void user_validates_the_warning_message_items(String warningMsg) {
	   
		objectManager.getBuyersUIMyListpage().validateWarningMessageUnableToSupplyItems(warningMsg);
	}
	

	@When("User validates the error message when product has exceed the number of units in stock for the product")
	public void user_validates_the_error_message_when_product_has_exceed_the_number_of_units_in_stock_for_the_product() {
	    
		objectManager.getBuyersUIMyListpage().validateWarningMessageCannotAddTheSelectedProduct();
	}
	
	
	@Given("User should not be able to view MY account link")
	public void user_should_not_be_able_to_view_MY_account_link() {
	    
		objectManager.getBuyersUIpage().validateMyAccountLinkIsVisible();
	}

	@When("User validates the {string} button is not visible")
	public void user_validates_the_button_is_not_visible(String string) {
	    
		objectManager.getBuyersUIpage().validateAddToListButtonIsVisible();
	}

	@When("User adjust the products quantity on the Added to wishlist page")
	public void user_adjust_the_products_quantity_on_the_Added_to_wishlist_page() {
	    
		objectManager.getBuyersUIMyListpage().enterTheProductFullQuantity(scenarioContext.productDetails);
	}
	
	@When("User adjust the products quantity on {string} page")
	public void user_adjust_the_products_quantity_on_page(String string) {
	    
		objectManager.getBuyersUIMyListpage().enterTheProductFullQtyOnQtyTextOnItemAddedToYourBasketPage(scenarioContext.productDetails);
	}
	
	@When("User validates the OOS message")
	public void user_validates_the_OOS_message() {
	    
		objectManager.getBuyersUIMyListpage().validateUnableToSupplyQtyMessaseInAddToList();
	}

	@Then("User validates the warning and quantity available message in MyList")
	public void user_validates_the_warning_and_quantity_available_message_in_MyList() {
	 
		objectManager.getBuyersUIMyListpage().validateWarningAndQuantityMessageInMyList();
	}
	
	
	@When("User validated the warning message when all stocks of product were added to basket")
	public void user_validated_the_warning_message_when_all_stocks_of_product_were_added_to_basket() {
	    
		objectManager.getBuyersUIMyListpage().validateReduceStockMessageWhenClickAddTheseItemsToCurrentBasketInMyList(scenarioContext.productDetails);
	}

}

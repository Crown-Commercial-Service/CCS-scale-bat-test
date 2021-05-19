package com.scale.bat.stepdefs;

import static org.junit.Assert.assertEquals;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.scale.bat.businessPages.BuyersUIQuotesPage;
import com.scale.bat.businessPages.WishListServiceStepDefs;
import com.scale.bat.context.ScenarioContext;
import com.scale.bat.context.TestContext;
import com.scale.bat.framework.utility.DateTimeUtils;
import com.scale.bat.framework.utility.Log;
import com.scale.bat.framework.utility.PageObjectManager;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class BuyersUIQuotesPageStepdefs {
	
	private Logger log = Log.getLogger(BuyersUIQuotesPage.class);

	private PageObjectManager objectManager;
	public ScenarioContext scenarioContext;

	public BuyersUIQuotesPageStepdefs(TestContext testContextObj, ScenarioContext scenarioContext) {
		testContextObj.getDriver();
		objectManager = testContextObj.getObjectManager();
		this.scenarioContext = scenarioContext;
	}
	
	
	@And("User enter Quote name as {string}")
	public void user_enter_quote_name(String quoteName) {
	
		objectManager.getBuyersUIQuotespage().enterQuoteNameInQuoteTextbox(quoteName);
	
	}
	
	@Given("User validated the Firm and Indicative quote label")
	public void user_validated_the_Firm_and_Indicative_quote_label() {
	    
		objectManager.getBuyersUIQuotespage().validateFirmAndIndicativeQuoteLabel();
	}
	
	@When("User get the supplier name and total amount of the products on Buyers UI")
	public void user_get_the_supplier_name_and_total_amount_of_the_products_on_Buyers_UI() {
	   
		objectManager.getBuyersUIQuotespage().getSupplierNameAndTotalAmountOnBasketPage();
	}


	@And("User create {string} quote")
	public void user_create_quote(String QuoteType) {
		switch (QuoteType) {
		case "Firm":
			objectManager.getBuyersUIpage()
					.clickElementWithJavaScript(objectManager.getBuyersUIQuotespage().getFirmQuoteElement());
			break;
		case "Indicative":
			objectManager.getBuyersUIpage()
					.clickElementWithJavaScript(objectManager.getBuyersUIQuotespage().getIndicativeQuoteElement());
			break;
		default:
			log.info("Quote type is entered as " + QuoteType);
		}
	}


	@Then("User get message {string} on screen")
	public void validate_message(String Message) {
		
		objectManager.getBuyersUIQuotespage().validateNewQuoteCreatedMsg(Message);
	}
	
	
	@Then("User validates the new Quote refrence no")
	public void user_validates_the_new_Quote_refrence_no() {
	    
		objectManager.getBuyersUIQuotespage().getNewQuoteRefrenceNo();
	}

	@Then("User enters the new Quote refrence in Quote reference textbox in buyers UI")
	public void user_enters_the_new_Quote_refrence_in_Quote_reference_textbox_in_buyers_UI() {
		
		objectManager.getBuyersUIQuotespage().enterQuoteNoInQuoteReferenceTextbox();
	}

	@Then("User enters the new Quote refrence partially in Quote reference textbox in buyers UI")
	public void user_enters_the_new_Quote_refrence_partially_in_Quote_reference_textbox_in_buyers_UI() {
		
		objectManager.getBuyersUIQuotespage().enterQuoteNoPartiallyInQuoteReferenceTextbox();
	}
	
	@Then("User validates the Quote table column headers displayed on BuyerUI")
	public void user_validates_the_Quote_table_column_headers_displayed_on_BuyerUI() {
	    
		objectManager.getBuyersUIQuotespage().validateQuoteTableColumnHeaders();
	}

	@Then("User validates the new {string} Quote details in quotes table with default status as {string} in Buyers UI")
	public void user_validates_the_new_Quote_details_in_quotes_table_with_default_status_as_in_Buyers_UI(String quoteType, String statusAccepted) {
	    
		objectManager.getBuyersUIQuotespage().validateNewQuoteDetails(quoteType, statusAccepted);
	}
	
	@Then("User validates the text {string} and the status {string} on indicative quotes page")
	public void user_validates_the_text_and_the_status_on_indicative_quotes_page(String message, String statusAccepted) {
	    
		objectManager.getBuyersUIQuotespage().validateIndicativeQuoteMessageAndStatus(message, statusAccepted);
	}

	@Given("User clicks on {string} in Admin UI")
	public void user_clicks_on_in_Admin_UI(String LinkButton) {
	    
		switch (LinkButton) {
		case "Quote Link":
			objectManager.getBuyersUIpage()
					.clickElementWithJavaScript(objectManager.getBuyersUIQuotespage().getQuoteLinkAdminPanel());
			break;
			
		case "Search Button":
			objectManager.getBuyersUIpage()
					.clickElement(objectManager.getBuyersUIQuotespage().getSearchButtonAdminPanel());
			break;
			
		case "Reject Link":
			objectManager.getBuyersUIpage()
					.clickElement(objectManager.getBuyersUIQuotespage().getRejectLinkAdminUI());
			break;
			
		case "Reject Button":
			objectManager.getBuyersUIpage()
					.clickElementWithJavaScript(objectManager.getBuyersUIQuotespage().getRejectButtonAdminUI());
			break;
		case "Supplier Link":
			objectManager.getBuyersUIpage().clickElementWithJavaScript(objectManager.getBuyersUIQuotespage().getSupplierLinkAdminUI());

		default:
			log.info("Quote type is entered as " + LinkButton);
			
		}
	}

	@Given("User enters the Quote refrence no. on Quote Reference textbox in Admin UI")
	public void user_enters_the_Quote_refrence_no_on_Quote_Reference_textbox_in_Admin_UI() {
	    
		objectManager.getBuyersUIQuotespage().enterQuoteNoInQuoteReferenceTextboxAdminUI();
	}

	@Given("User validates the new Quote details in quotes table with default status as {string} in Admin UI")
	public void user_validates_the_new_Quote_details_in_quotes_table_with_default_status_as_in_Admin_UI(String quoteStatus) {
	    
		objectManager.getBuyersUIQuotespage().validateNewQuoteDetailsOnAdminUI(quoteStatus);
	}
	
	@Then("User validates the Clear my Basket and add these items button & a text message is visible on above that button")
	public void user_validates_the_Clear_my_Basket_and_add_these_items_button_a_text_message_is_visible_on_above_that_button() {
	    
		objectManager.getBuyersUIQuotespage().validateClearMyBasketAndAddThisItemButton();
	}
	
	@Then("User Validates the message {string}")
	public void user_Validates_the_message(String successMessage) {
	    
		objectManager.getBuyersUIQuotespage().validateSuccessMessageInBasket(successMessage);
	}
	
	@Then("User validets the error message of insufficient stock on quotes page")
	public void user_validets_the_error_message_of_insufficient_stock_on_quotes_page() {
	    
		objectManager.getBuyersUIQuotespage().validateTheErrorMessageOfInsufficientStockOnQuotes();
	}
	
	@Then("User validates the Add to basket button")
	public void user_validates_the_Add_to_basket_button() {
	    
		objectManager.getBuyersUIQuotespage().validateAddToBasketButton();
	}

	@Given("User validates the {string} page")
	public void user_validates_the_page(String rejectQuotePageTitle) {
		
		objectManager.getBuyersUIQuotespage().validateRejectQuotePage(rejectQuotePageTitle);
	    
	}
	
	@Given("User validates the {string} page in Admin UI")
	public void user_validates_the_page_in_Admin_UI(String manageQuotePageTitle) {
	    
		objectManager.getBuyersUIQuotespage().validateManageQuotePage(manageQuotePageTitle);
	}

	@Given("User validates Reject Quotes page has reject reason textbox with {string} and {string} button")
	public void user_validates_Reject_Quotes_page_has_reject_reason_textbox_with_and_button(String rejectButton, String cancleButton) {
	    
		objectManager.getBuyersUIQuotespage().validateRejectQuotePageTextBoxRejectAndCancleButton(rejectButton, cancleButton);
	}

	@Given("User enters the reject reason {string} for the quotes")
	public void user_enters_the_reject_reason_for_the_quotes(String rejectReason) {
	    
		objectManager.getBuyersUIQuotespage().enterRejectReasonInTextBox(rejectReason);
	}


	@Given("User validates quote staus as {string} and reject reason on Manage Quotes page")
	public void user_validates_quote_staus_as_and_reject_reason_on_Manage_Quotes_page(String rejectStatus) {
	    
		objectManager.getBuyersUIQuotespage().validateStatusAndRejectReasonOnManageQuote(rejectStatus);
	}

	@Given("User validates quote staus as {string} on Quotes page in Admin UI")
	public void user_validates_quote_staus_as_on_Quotes_page_in_Admin_UI(String statusRejected) {
	    
		objectManager.getBuyersUIQuotespage().validateNewQuoteRejectedStatusOnQuotesPage(statusRejected);
	}

	@Then("User enters the {string} in Quote reference textbox in buyers UI")
	public void user_enters_the_given_Quote_refrence_in_Quote_reference_textbox_in_buyers_UI(String quoteRef) {
		
		objectManager.getBuyersUIQuotespage().enterGivenQuoteNoInQuoteReferenceTextbox(quoteRef);
	}
	
	@Then("No results found message displayed for quotes in buyers UI")
	public void no_results_found_message_displayed_for_quotes_in_buyers_UI() {
	    
		objectManager.getBuyersUIQuotespage().validateNoResultFoundQuoteBuyerUI();
	}
	
	
	@Then("User enters the new Quote refrence partially in Quote reference textbox in Admin UI")
	public void user_enters_the_new_Quote_refrence_partially_in_Quote_reference_textbox_in_Admin_UI() {

		objectManager.getBuyersUIQuotespage().enterQuoteNoPartiallyInQuoteReferenceTextboxAdminUI();
												
	}

	@When("User enters the Quote refrence as {string} on Quote Reference textbox in Admin UI")
	public void user_enters_the_Quote_refrence_as_on_Quote_Reference_textbox_in_Admin_UI(String quoteRef) {
	  
	    objectManager.getBuyersUIQuotespage().enterGivenQuoteNoInQuoteReferenceTextboxAdminUI(quoteRef);

	}
	
	@Then("No results found message displayed for quotes in Admin UI")
	public void no_results_found_message_displayed_for_quotes_in_Admin_UI() {
		objectManager.getBuyersUIQuotespage().validateNoResultFoundQuoteAdminUI();
	}
	
	
	@Given("User validates all quotes are displayed in quotes table")
	public void user_validates_all_quotes_are_displayed_in_quotes_table() {
		
		objectManager.getBuyersUIQuotespage().validateAllQuoteOnBuyersUI(WishListServiceStepDefs.allQuotesQtyApi, WishListServiceStepDefs.jsonResponse);
	}
	
	
	@When("User validates all quotes are displayed in quotes table on admin UI")
	public void user_validates_all_quotes_are_displayed_in_quotes_table_on_admin_UI() {
		
		objectManager.getBuyersUIQuotespage().validateAllQuoteOnAdminUI(WishListServiceStepDefs.allQuotesQtyApi, WishListServiceStepDefs.jsonResponse);
	}


	
	
}

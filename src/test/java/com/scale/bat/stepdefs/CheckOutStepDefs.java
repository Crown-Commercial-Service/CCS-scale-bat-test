package com.scale.bat.stepdefs;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.scale.bat.context.ScenarioContext;
import com.scale.bat.context.TestContext;
import com.scale.bat.framework.utility.JsonParser;
import com.scale.bat.framework.utility.Log;
import com.scale.bat.framework.utility.PageObjectManager;

import cucumber.api.java.en.Given;

public class CheckOutStepDefs {
	private Logger log = Log.getLogger(CheckOutStepDefs.class);

	private PageObjectManager objectManager;
	public ScenarioContext scenarioContext;

	public CheckOutStepDefs(TestContext testContextObj, ScenarioContext scenarioContext) {
		testContextObj.getDriver();
		objectManager = testContextObj.getObjectManager();
		this.scenarioContext = scenarioContext;
	}

	@Given("User completes the checkOut proccess")
	public void user_completes_the_checkOut_proccess() {
//		objectManager.getBuyersUIpage().navigateToCheckoutProcess();
		log.info("Started CheckOut Process");
	}
		
	@Given("User navigates to checkout payment page a validates the checkout payment text")
	public void user_navigates_to_checkout_payment_page_a_validates_the_checkout_payment_text() {
	    
		objectManager.getCheckoutpage().checkTitelBasket();
	}
	

	@Given("User validates the payment option available text in checkout payment page")
	public void user_validates_the_payment_option_available_text_in_checkout_payment_page() {
		
		objectManager.getCheckoutpage().validateCheckoutPaymentOptionText();	
	}

	
	@Given("User validates the Order summary and supplier name {string} with Total value excluding VAT details")
	public void user_validates_the_Order_summary_and_supplier_name_with_Total_value_excluding_VAT_details(String SupplierName) {
		
		objectManager.getCheckoutpage().validateSuppNameOrderSummaryTotalValueExVATOnCheckoutPayment(SupplierName);
	}

	@Given("User enters the PO number for the for supplier {string}")
	public void user_enters_the_PO_number_for_the_for_supplier(String supplierName) {
	    
		objectManager.getCheckoutpage().enterPONumber(supplierName);
	}

	@Given("User validates its correctly navigates to checkout confirmation page")
	public void user_validates_its_correctly_navigates_to_checkout_confirmation_page() {
		
		objectManager.getCheckoutpage().validateTitleCheckoutConfirmation();   
	}

	@Given("User validates the Delivery and Invoice address with Delivery note in checkout confirmation page")
	public void user_validates_the_Delivery_and_Invoice_address_with_Delivery_note_in_checkout_confirmation_page() {
	    
		
	}

	@Given("User validates products details with VAT{int} and VAT{int} for supplier{int} and supplier{int} in checkout confirmation page")
	public void user_validates_products_details_with_VAT_and_VAT_for_supplier_and_supplier_in_checkout_confirmation_page(Integer int1, Integer int2, Integer int3, Integer int4) {
	    
		objectManager.getCheckoutpage().validateSupp1And2ProductsDetailsOnCheckoutConfirmationPage();   
				
	}

	@Given("User validates the {string} checkbox and {string} link")
	public void user_validates_the_checkbox_and_link(String string, String string2) {
	    
		objectManager.getCheckoutpage().validateTermsAndConditionLinkAndText(); 
	}

	@Given("User clicks on {string} checkbox in checkout confirmation page")
	public void user_clicks_on_checkbox_in_checkout_confirmation_page(String string) {
	    
		objectManager.getCheckoutpage().clickOnTermsAndConditionCheckbox();
	}
	
	
}

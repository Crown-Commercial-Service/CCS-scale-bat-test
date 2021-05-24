package com.scale.bat.stepdefs;

import org.apache.log4j.Logger;

import com.scale.bat.context.TestContext;
import com.scale.bat.framework.utility.Log;
import com.scale.bat.framework.utility.PageObjectManager;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;

public class CCSHomePageStefDef {
	private Logger log = Log.getLogger(CCSHomePageStefDef.class);

	private PageObjectManager objectManager;

	public CCSHomePageStefDef(TestContext testContextObj) {
		objectManager = testContextObj.getObjectManager();
	}

	@And("User click on \"([^\"]*)\" link on main sidebar")
	public void user_click_on_link_on_main_sidebar(String linkText) {
		switch (linkText.toLowerCase()) {
		case "productcatalogues":
			objectManager.getCCSHomePage().navigateToProductCatalogues();
			//objectManager.getScreeShot().takeSnapShot1();
			break;
		case "orders":
			objectManager.getCCSHomePage().navigateToOrders();
			objectManager.getScreeShot().takeSnapShot1();
			break;
		case " returns":
			objectManager.getCCSHomePage().navigateToReturns();
			break;
		case "promotions":
			objectManager.getCCSHomePage().navigateToPromotions();
			break;
		case "users":
			objectManager.getCCSHomePage().navigateToUsers();
			break;
		case "configurations":
			objectManager.getCCSHomePage().navigateToConfigurations();
			break;
		case "suppliers":
			objectManager.getCCSHomePage().navigateToVendors();
			log.info("User clicks on supplier tab on admin UI");
			break;
		case "reports":
			objectManager.getCCSHomePage().navigateToReports();
			break;
			
		case "Suppliers":
			objectManager.getCCSHomePage().navigateToSupplier();
			break;
			
		default:
			log.info("Check the BDD for proper spellings or wrong link text");
		}

	}
	
	@Given("User enters the {string} on Name textbox")
	public void user_enters_the_on_Name_textbox(String supplierName) {
	    
		objectManager.getCCSHomePage().enterSupplierName(supplierName);
		log.info("User enters the user name " + supplierName + " on textbox");
	}

	@Given("User clicks on {string} button")
	public void user_clicks_on_button(String string) {
	    
		objectManager.getCCSHomePage().clickOnFilterResultButton();
		log.info("User clicks on "+ string );
	}

	@Given("User clicks on edit link")
	public void user_clicks_on_edit_link() {
	    
		objectManager.getCCSHomePage().clickOnEditButton();
		log.info("User clicks on edit link" );
	}

	@Given("User removes the email from the Notification email textbox")
	public void user_removes_the_email_from_the_Notification_email_textbox() {
	    
		objectManager.getCCSHomePage().removeTextFromNotificationEmailTextbox();
		log.info("User removes the email from notification email textbox" );
	}

	@Given("User clicks on Update Button")
	public void user_clicks_on_Update_Button() {
	    
		objectManager.getCCSHomePage().clickOnUpdateButton();
		log.info("User click on update button" );
	}

	@Given("User should not be able to update the supplier details and a message {string} should display")
	public void user_should_not_be_able_to_update_the_supplier_details_and_a_message_should_display(String message) {
	    
		objectManager.getCCSHomePage().validateMessageForBlankNotificationEmailTextbox();
		log.info("User is able to see a message email field cannot be blank" );
	}

	@Given("User enters the valid email {string} in Notification email textbox")
	public void user_enters_the_valid_email_in_Notification_email_textbox(String emailId) {
		
		objectManager.getCCSHomePage().enterTextFromNotificationEmailTextbox(emailId);
		log.info("User enters the valid email "+ emailId + " on notification email textbox");
	}

	@Given("User should be able to update the supplier details and a message {string}CogNewTestSupplier{double}{string} should display")
	public void user_should_be_able_to_update_the_supplier_details_and_a_message_CogNewTestSupplier_should_display(String string, Double double1, String string2) {
	    
		objectManager.getCCSHomePage().validateSuccessMessageForNotificationEmailTextbox();
		log.info("Email is updated successfully");
	}

	@Given("enters the invalid email {string} in Notification email textbox")
	public void enters_the_invalid_email_in_Notification_email_textbox(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Given("should not be able to update the supplier details and a message {string} should display")
	public void should_not_be_able_to_update_the_supplier_details_and_a_message_should_display(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	
}

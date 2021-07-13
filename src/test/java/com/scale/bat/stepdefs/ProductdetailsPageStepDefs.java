package com.scale.bat.stepdefs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.scale.bat.context.ScenarioContext;
import com.scale.bat.context.TestContext;
import com.scale.bat.framework.utility.DateTimeUtils;
import com.scale.bat.framework.utility.JsonParser;
import com.scale.bat.framework.utility.Log;
import com.scale.bat.framework.utility.PageObjectManager;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ProductdetailsPageStepDefs {
	private Logger log = Log.getLogger(ProductdetailsPageStepDefs.class);
	private PageObjectManager objectManager;
	public ScenarioContext scenarioContext;

	public ProductdetailsPageStepDefs(TestContext testContextObj, ScenarioContext context) {
		testContextObj.getDriver();
		objectManager = testContextObj.getObjectManager();
		this.scenarioContext = context;
	}

	@And("User \"([^\"]*)\" the \"([^\"]*)\" of a product")
	public void user_updates_selected_detail_of_a_product(String action, String selectedDetail) {
		boolean empty = true;
		if (action.equalsIgnoreCase("updates")) {
			empty = false;
		}
		objectManager.getproductDetailsPage().updateProductDetails(selectedDetail, TestContext.randomString(5), empty);
	}

	@When("User clicks on \"([^\"]*)\" button on product details page")
	public void user_clicks_on_button_on_product_details_page(String string) {
		
		objectManager.getproductDetailsPage().clickButton(string);
		/*objectManager.getproductDetailsPage().isElementPresent("Unpublished successfully");
		assertTrue(objectManager.getproductDetailsPage().isElementPresent("Unpublished successfully"));*/
		objectManager.getproductDetailsPage().isElementPresent("successfully");
		assertTrue(objectManager.getproductDetailsPage().isElementPresent("successfully"));
		
		JSONObject jObj = new JSONObject(new JsonParser().convertJsonToString(scenarioContext.ScenarioDataFilePath));
		JSONObject details =jObj.getJSONObject("ProductCreation").getJSONObject("FromUi");
		scenarioContext.productDetails = details.toMap();
		
	}

	@Then("\"([^\"]*)\" field is empty once the product is published")
	public void field_is_empty_once_the_product_is_published(String field) {
		String valueFromAdminPanel = objectManager.getproductDetailsPage().getProductDetails(field);
		log.info("Value of " + field + " is: " + valueFromAdminPanel);
		assertTrue("Field is not empty!!!! ", "".equals(valueFromAdminPanel));
	}

	@Then("\"([^\"]*)\" field is updated with today's date")
	public void field_is_updated_with_today_date(String field) {
		String valueFromAdminPanel = objectManager.getproductDetailsPage().getProductDetails(field);
		log.info("Value of " + field + " is: " + valueFromAdminPanel);
		log.info(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		assertTrue("Date is not same!!",
				valueFromAdminPanel.equalsIgnoreCase(new SimpleDateFormat("yyyy-MM-dd").format(new Date())));
	}

	@Then("Product should be in {string} state")
	public void product_should_be_in_state(String string) {
		//throw new cucumber.api.PendingException();
	}

	@And("A successful message should be displayed")
	public void successful_message() {
		objectManager.getproductDetailsPage().validateSuccessfulMessage();
	}
	
	@Then("A successful message should display after updating the {string}")
	public void a_successful_message_should_display_after_updating_the(String string) {
	    
		objectManager.getproductDetailsPage().validateSuccessfulMessageAferUpdatingUnspsc();
	}

	@And("An error message should be displayed")
	public void error_message() {
		objectManager.getproductDetailsPage().validateErrorMessage();
	}
}

package com.scale.bat.stepdefs;

import org.apache.log4j.Logger;

import com.scale.bat.context.ScenarioContext;
import com.scale.bat.context.TestContext;
import com.scale.bat.framework.utility.Log;
import com.scale.bat.framework.utility.PageObjectManager;

import cucumber.api.java.en.Given;

public class OrderPageStepdefs {
	
	private Logger log = Log.getLogger(CheckOutStepDefs.class);

	private PageObjectManager objectManager;
	public ScenarioContext scenarioContext;

	public OrderPageStepdefs(TestContext testContextObj, ScenarioContext scenarioContext) {
		testContextObj.getDriver();
		objectManager = testContextObj.getObjectManager();
		this.scenarioContext = scenarioContext;
	}
	
	
	@Given("User redirected to an intermediary page displaying a message{string}")
	public void user_redirected_to_an_intermediary_page_displaying_a_message(String message) {
		
		objectManager.getOrderPage().validateIntermediaryOrderPageMessage(message);
	}
	
	@Given("User validates the order page")
	public void user_validates_the_order_page() {
		objectManager.getOrderPage().validateMyOrderPage();
	}
}

package com.scale.bat.stepdefs;

import static org.junit.Assert.assertTrue;

import org.apache.log4j.Logger;

import com.scale.bat.businessPages.BuyersUIBasketPage;
import com.scale.bat.context.ScenarioContext;
import com.scale.bat.context.TestContext;
import com.scale.bat.framework.utility.Actions;
import com.scale.bat.framework.utility.Log;
import com.scale.bat.framework.utility.PageObjectManager;

import cucumber.api.java.en.Given;

public class OrderPageStepdefs extends Actions{
	
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
	
	
	@Given("User open and validates the OrderOne")
	public void user_open_and_validates_the_OrderOne() {
	    
		String supplierNameOrderPage=objectManager.getOrderPage().openOrderDetails("1");
		
		if(supplierNameOrderPage.equals("testsupplier039")) {
			
			objectManager.getOrderPage().validateSupplier1P1P2OrderDetails();
			
		}else if(supplierNameOrderPage.equals("testsupplier040")) {
			
			objectManager.getOrderPage().validateSupplier2P1P2OrderDetails();
		}
	}
	
	
	@Given("User navigates back to Order page")
	public void user_navigates_back_to_Order_page() {
	    
		objectManager.getOrderPage().backToMyOrderPage();
	}


	@Given("User open and validates the OrderTwo")
	public void user_open_and_validates_the_OrderTwo() {
		
		String supplierNameOrderPage=objectManager.getOrderPage().openOrderDetails("2");
		
		if(supplierNameOrderPage.equals("testsupplier039")) {
			
			objectManager.getOrderPage().validateSupplier1P1P2OrderDetails();
			
		}else if(supplierNameOrderPage.equals("testsupplier040")) {
			
			objectManager.getOrderPage().validateSupplier2P1P2OrderDetails();
		}
	    
	}


	@Given("User validates the order page")
	public void user_validates_the_order_page() {
		objectManager.getOrderPage().validateMyOrderPage();
	}
	
	
	
	
}

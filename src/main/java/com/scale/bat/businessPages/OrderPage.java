package com.scale.bat.businessPages;

import static org.junit.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.scale.bat.framework.utility.Actions;
import com.scale.bat.framework.utility.ConfigurationReader;
import com.scale.bat.framework.utility.Log;

import cucumber.api.Scenario;

public class OrderPage extends Actions{
	
	private Logger log = Log.getLogger(CheckoutPage.class);
	private WebDriver driver;
	private ConfigurationReader configReaderObj;
	
	public OrderPage(WebDriver driver, Scenario scenario) {
		super.driver = driver;
		this.scenario = scenario;
		PageFactory.initElements(driver, this);
		this.wait = new WebDriverWait(super.driver, 30);
		configReaderObj = new ConfigurationReader();
	}
	
	@FindBy(xpath = "//h1[text()='My orders']")
	private WebElement myOrders;
	
	@FindBy(xpath = "//*[text()='The order is being placed']")
	private WebElement intermediaryOrderPageMessage;
	
	
	
	public void validateMyOrderPage() {
		waitForSeconds(1);
		assertTrue(getText(myOrders).equals("My orders"));
		waitForSeconds(1);
	}
	
	public void validateIntermediaryOrderPageMessage(String message) {
		waitForSeconds(1);
		assertTrue(getText(intermediaryOrderPageMessage).equals(message));
		waitForSeconds(10);
	}

}

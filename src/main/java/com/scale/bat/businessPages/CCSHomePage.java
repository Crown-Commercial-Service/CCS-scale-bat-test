package com.scale.bat.businessPages;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.scale.bat.framework.utility.Actions;
import cucumber.api.Scenario;

public class CCSHomePage extends Actions {
	private WebDriver driver;
// Need to add returns more vairables for this page
	private String mainSideBar_Orders = "Orders";
	private String mainSideBar_ProductCatalogues = "Product Catalogues";
	private String mainSideBar_Returns = " Returns";
	private String mainSideBar_Promotions = " Promotions";
	private String mainSideBar_Users = "Users";
	private String mainSideBar_Configurations = " Configurations";
	private String mainSideBar_Vendors = "Suppliers";
	private String mainSideBar_Reports = "Reports";
		
	@FindBy(xpath = "/html/body/header/nav/div[2]/ul/li")
	private WebElement userName;
	
	@FindBy(xpath = "/html/body/header/nav/div[2]/ul/li")
	private WebElement mainSideBarSupplier;
	
	@FindBy(xpath = "/html/body/header/nav/div[2]/ul/li/div/ul/li[1]/a")
	private WebElement logOut;
	
	@FindBy(xpath = "//*[@class='form-control js-quick-search-target']")
	private WebElement supplierFilterNameTextbox;
	
	@FindBy(xpath = "//div[@data-hook='admin_vendors_index_search_buttons']/button")
	private WebElement filterResultButton;
	
	@FindBy(xpath = "//*[@class='table sortable']/tbody/tr/td[4]/a")
	private WebElement editButton;
	
	@FindBy(xpath = "//div[@id='vendor_notification_email_field']/input")
	private WebElement notificationEmailTextbox;
	
	@FindBy(xpath = "//button[@class='btn btn-primary btn-success']")
	private WebElement updateButton;
	
	public CCSHomePage(WebDriver driver, Scenario scenario) {
		this.driver = driver;
		this.scenario = scenario;
		PageFactory.initElements(driver, this);
		this.wait = new WebDriverWait(this.driver, 30);
	}

	public void navigateToOrders() {
		clickElement(mainSideBar_Orders);
	}

	public void navigateToVendors() {
		clickElement(mainSideBar_Vendors);
	}

	public void navigateToProductCatalogues() {
		
		clickElement(mainSideBar_ProductCatalogues);
	}
	
	public void navigateToReturns() {
		clickElement(mainSideBar_Returns);
	}

	public void navigateToPromotions() {
		clickElement(mainSideBar_Promotions);
	}

	public void navigateToUsers() {
		clickElement(mainSideBar_Users);
	}
	
	public void navigateToConfigurations() {
		clickElement(mainSideBar_Configurations);
	}

	public void navigateToReports() {
		clickElement(mainSideBar_Reports);
	}
	
	public void navigateToSupplier() {
		clickElement(mainSideBar_Vendors);
	}
	
	public void enterSupplierName(String nameSupplier) {
		enterText(supplierFilterNameTextbox,nameSupplier);
		
	}
	
	public void clickOnFilterResultButton() {
		clickElement(filterResultButton);
		
	}
	
	public void clickOnEditButton() {
		clickElement(editButton);
	}
	
	public void removeTextFromNotificationEmailTextbox() {
		enterText(notificationEmailTextbox,"");
	}
	
	public void clickOnUpdateButton() {
		clickElement(updateButton);
	}
	
	public void validateMessageForBlankNotificationEmailTextbox() {
		assertFalse(isElementPresent("has been successfully updated!", driver));
	}
	
	public void enterTextFromNotificationEmailTextbox(String emailID) {
		enterText(notificationEmailTextbox,emailID);
	}
	
	public void validateSuccessMessageForNotificationEmailTextbox() {
		assertTrue(isElementPresent("has been successfully updated!", driver));
	}
	
	public void logOff() {
		clickElement(userName);
		clickElement(logOut);
	}
	
	public void logOffAndClose() {
		waitForSeconds(1);
		clickElement(userName);
		waitForSeconds(1);
		clickElement(logOut);
	}
	
	
	
}


package com.scale.bat.businessPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.scale.bat.framework.utility.Actions;
import com.scale.bat.framework.utility.ConfigurationReader;
import cucumber.api.Scenario;

public class LogInAdminPanel extends Actions{
    private WebDriver driver;
    private ConfigurationReader configReaderObj;

    @FindBy(xpath = "//*[@id='spree_user_email']")
    private WebElement enterUsername;

    @FindBy(xpath = "//*[@id='spree_user_password']")
    private WebElement enterPassword;
  
    @FindBy(xpath = "//*[@class='btn btn-primary btn-block']")
    private WebElement loginButton;

    public LogInAdminPanel(WebDriver driver, Scenario scenario) {
        this.driver = driver;
        this.scenario = scenario;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(this.driver, 30);
        configReaderObj = new ConfigurationReader();
    }
    
    public void loginToSupplierAdminPanel(String role) {
    	enterText(enterUsername, configReaderObj.adminPanelUserName(role));
    	enterText(enterPassword, configReaderObj.adminPanelPassword(role));
    	clickElement(loginButton);

    }
    
    public void loginToAPISupplierAdminPanel() {
    	enterText(enterUsername, configReaderObj.apiUserName("supplier"));
    	enterText(enterPassword, configReaderObj.apiUserPassword("supplier"));
    	clickElement(loginButton);

    }
    
    public void loginToCheckOutSupplierAdminPanel(String role) {
    	enterText(enterUsername, configReaderObj.adminPanelCheckoutUserName(role));
    	enterText(enterPassword, configReaderObj.adminPanelCheckoutUserPassword(role));
    	clickElement(loginButton);

    }

}

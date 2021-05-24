package com.scale.bat.context;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.net.MalformedURLException;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.assertthat.selenium_shutterbug.core.PageSnapshot;
import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.assertthat.selenium_shutterbug.utils.web.ScrollStrategy;
import com.scale.bat.framework.utility.BrowserFactory;
import com.scale.bat.framework.utility.ConfigurationReader;
import com.scale.bat.framework.utility.JSONUtility;
import com.scale.bat.framework.utility.Log;
import com.scale.bat.framework.utility.PageObjectManager;
import com.scale.bat.framework.utility.TakeScreenShotAndAddToWordDoc;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.AfterStep;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class TestContext extends BrowserFactory {

	private Logger log = Log.getLogger(TestContext.class);
	private WebDriver driver;
	private BrowserFactory browserFactory;
	private PageObjectManager objectManager;
	public Scenario scenario;
	public ScenarioContext scenarioContext;
	private JSONUtility jsonUtilityObj;
	public List<String> navigationButtonList;
	public String allPageScreenshotFlag;
	public ConfigurationReader configReader;
	protected WebDriverWait wait;
	
	
	
	@Before
	public void setUp(Scenario scenario) throws MalformedURLException {
		log.info("=================" + scenario.getName() + " execution starts" + "===================");
		this.scenario = scenario;
		scenarioContext = new ScenarioContext();
		jsonUtilityObj =new JSONUtility();
		configReader = new ConfigurationReader();
		allPageScreenshotFlag = configReader.get("allPageScreenshot");
		long threadId = Thread.currentThread().getId();
		String processName = ManagementFactory.getRuntimeMXBean().getName();
		log.info("Started in thread: " + threadId + ", in JVM: " + processName);
		log.info("Successfully lunched the chrome browser");
	}

	@Given("User logged in as \"([^\"]*)\" in admin panel")
	public void user_logged_as_in_admin_panel(String userRole) throws MalformedURLException {
		this.driver = initiateDriver(configReader.getBrowserName(),scenario);
		launchURL(configReader.adminPanelUrl());
		objectManager = new PageObjectManager(driver, scenario);
		objectManager.getLogInAdminPanel().loginToSupplierAdminPanel(userRole);
		log.info(userRole + " logged in to admin panel");
	}
	
	@Given("User enter the admin url and logged in as a {string}")
	public void user_enter_the_admin_url_and_logged_in_as_a(String userRole) throws MalformedURLException{
	   
		launchURL(configReader.adminPanelUrl());
		objectManager.getLogInAdminPanel().loginToSupplierAdminPanel(userRole);
		log.info(userRole + " logged in to admin panel");
	}
	
	@When("User enters the Admin UI url")
	public void user_enters_the_Admin_UI_url() {
		launchURL(configReader.adminPanelUrl());
		log.info(" User enters the Admin UI url");
	}
	
	@Given("User login as {string} in admin panel")
	public void user_login_as_in_admin_panel(String userRole) {
		
		objectManager = new PageObjectManager(driver, scenario);
		objectManager.getLogInAdminPanel().loginToSupplierAdminPanel(userRole);
		log.info(userRole + " logged in to admin panel");
	}


	@And("User log off and close the application")
	public void user_log_off_from_the_application() {
		objectManager.getCCSHomePage().logOffAndClose();
		driver.quit();
		driver = null;
	}
	
	
	@When("User log off and close the buyer UI")
	public void user_log_off_and_close_the_buyer_UI() {
	    
		driver.quit();
		driver = null;
	}
	
	@When("User close the current browser")
	public void user_close_the_current_browser() {
	   
		driver.close();
	}


	// @And("Authorisation dialoxg box is handled")
	// public void authorisation_dialoxg_box_is_handled() throws IOException,
	// AWTException, InterruptedException {
	// Path root = FileSystems.getDefault().getPath("").toAbsolutePath();
	// String autoItScriptForBrowser = "";
	// if (configReader.getBrowserName().toLowerCase().equals("chrome")) {
	// autoItScriptForBrowser = "ChromeScript.au3";
	// } else {
	// autoItScriptForBrowser = "FireFoxScript.au3";
	// }
	// Path autoItScriptPath = Paths.get(root.toString(), "TestData",
	// autoItScriptForBrowser);
	// String Command = "powershell.exe " + autoItScriptPath.toString();
	// log.info("Poweshell command :" + Command);
	// Runtime.getRuntime().exec(Command);
	// }

	@And("Authorisation dialoxg box is handled")
	public void authorisation_dialoxg_box_is_handled() throws IOException, AWTException, InterruptedException {
//		if ((configReader.get("browserName")).equals("BROWSERSTACK")) {
//			launchURL(configReader.get("ccs.admin.panel.urlforAuth"));
//		} else {
			// waitForSeconds();
			Thread.sleep(500);
			Robot robot = new Robot();
			Thread.sleep(100);
			robot.keyPress(KeyEvent.VK_S);
			Thread.sleep(100);
			robot.keyPress(KeyEvent.VK_C);
			Thread.sleep(100);
			robot.keyPress(KeyEvent.VK_A);
			Thread.sleep(100);
			robot.keyPress(KeyEvent.VK_L);
			Thread.sleep(100);
			robot.keyPress(KeyEvent.VK_E);
			Thread.sleep(100);
			robot.keyPress(KeyEvent.VK_A);
			Thread.sleep(100);
			robot.keyPress(KeyEvent.VK_D);
			Thread.sleep(100);
			robot.keyPress(KeyEvent.VK_M);
			Thread.sleep(100);
			robot.keyPress(KeyEvent.VK_I);
			Thread.sleep(100);
			robot.keyPress(KeyEvent.VK_N);
			Thread.sleep(100);
			robot.keyPress(KeyEvent.VK_TAB);
			Thread.sleep(100);

			robot.keyPress(KeyEvent.VK_S);
			Thread.sleep(100);
			robot.keyPress(KeyEvent.VK_P);
			Thread.sleep(100);
			robot.keyPress(KeyEvent.VK_A);
			Thread.sleep(100);
			robot.keyPress(KeyEvent.VK_R);
			Thread.sleep(100);
			robot.keyPress(KeyEvent.VK_K);
			Thread.sleep(100);
			robot.keyPress(KeyEvent.VK_SHIFT);
			robot.keyPress(KeyEvent.VK_S);
			robot.keyRelease(KeyEvent.VK_SHIFT);
			Thread.sleep(100);
			robot.keyPress(KeyEvent.VK_C);
			Thread.sleep(100);
			robot.keyPress(KeyEvent.VK_A);
			Thread.sleep(100);
			robot.keyPress(KeyEvent.VK_L);
			Thread.sleep(100);
			robot.keyPress(KeyEvent.VK_E);
			Thread.sleep(100);
			robot.keyPress(KeyEvent.VK_SHIFT);
			robot.keyPress(KeyEvent.VK_A);
			robot.keyRelease(KeyEvent.VK_SHIFT);
			Thread.sleep(100);
			robot.keyPress(KeyEvent.VK_I);
			Thread.sleep(100);
			robot.keyPress(KeyEvent.VK_R);
			Thread.sleep(100);
			robot.keyPress(KeyEvent.VK_SHIFT);
			robot.keyPress(KeyEvent.VK_3);
			robot.keyRelease(KeyEvent.VK_SHIFT);
			Thread.sleep(100);
			Thread.sleep(100);
			robot.keyPress(KeyEvent.VK_TAB);
			Thread.sleep(100);
			robot.keyPress(KeyEvent.VK_ENTER);
			Thread.sleep(100);
		}
	

	@Given("User navigates to BuyerUI")
	public void user_navigates_to_BuyerUI() throws IOException, AWTException, InterruptedException {
		this.driver = initiateDriver(configReader.getBrowserName(),scenario);
		launchURL(configReader.buyerUIUrl());
		objectManager = new PageObjectManager(this.driver, scenario);
		//System.out.println("Buyer UI 1 Driver Instance: " + driver);
		//TakeScreenShotAndAddToWordDoc.createWordFile();
		//TakeScreenShotAndAddToWordDoc.captureScreenShotNew();
	}
	
	@Given("User login to AdminUI as API User")
	public void user_login_to_AdminUI_as_API_User() throws MalformedURLException {
	    
		//objectManager.getBuyersUIpage().loginByuerUi(configReader.apiUserName("supplier"), configReader.apiUserPassword("supplier"));
		this.driver = initiateDriver(configReader.getBrowserName(),scenario);
		launchURL(configReader.adminPanelUrl());
		objectManager = new PageObjectManager(driver, scenario);
		objectManager.getLogInAdminPanel().loginToAPISupplierAdminPanel();
		log.info("logged in to admin panel as API User");
		
	}


	@Given("User login to buyerUI")
	public void user_login_to_BuyerUI() {
		
		objectManager.getBuyersUIpage().checkMenuButtonOnMobile();
		objectManager.getBuyersUIpage().loginByuerUi(configReader.buyerUserName(), configReader.buyerpassword());
		
		// To zoom out 3 times
				/*for(int i=0; i<1; i++){
					driver.findElement(By.tagName("html")).sendKeys(Keys.chord(Keys.CONTROL,Keys.SUBTRACT));
				}
				Robot robot = null;
				try {
					robot = new Robot();
				} catch (AWTException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				for (int i = 0; i < 1; i++) {
					robot.keyPress(KeyEvent.VK_CONTROL);
					robot.keyPress(KeyEvent.VK_SUBTRACT);
					robot.keyRelease(KeyEvent.VK_SUBTRACT);
					robot.keyRelease(KeyEvent.VK_CONTROL);
				}*/
		
	}
	
	
	@Given("User login to buyerUI with API User")
	public void user_login_to_buyerUI_with_API_User() {
	    
		objectManager.getBuyersUIpage().checkMenuButtonOnMobile();
		objectManager.getBuyersUIpage().loginByuerUi(configReader.apiUserName("supplier"), configReader.apiUserPassword("supplier"));
	}

	@Then("User enters the buyers UI")
	public void user_enters_the_buyers_UI() {
		launchURL(configReader.buyerUIUrl());
	}

	
	@After
	public void cleanUp() throws Exception {
		if (configReader.get("browserName").equalsIgnoreCase("chrome_profile")
				|| configReader.get("browserName").equalsIgnoreCase("CHROME_HEADLESS")) {
			browserFactory.deleteDirectory();
		}

		log.info("=================" + scenario.getName() + " execution ends" + "===================\n");

		if (driver != null) {
			takeSnapShot();
			driver.quit();
			driver = null;
		}

		if (jsonUtilityObj != null) {
			jsonUtilityObj = null;
		}

		if (scenarioContext != null) {
			scenarioContext.clearContext();
		}

	}

	public PageObjectManager getObjectManager() {
		return objectManager;
	}

	public WebDriver getDriver() {
		return this.driver;
	}

	
	public void takeSnapShot() {
		// Code to take full page screenshot
		ByteArrayOutputStream imageStream = new ByteArrayOutputStream();
		scenario.write("URL - " + driver.getCurrentUrl());
		PageSnapshot snapshot = Shutterbug.shootPage(driver, ScrollStrategy.BOTH_DIRECTIONS, true);
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0,0)");

		try {
			ImageIO.write(snapshot.getImage(), "png", imageStream);
			imageStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		byte[] source = imageStream.toByteArray();
		scenario.embed(source, "image/png");
	}

	public JSONUtility getJsonUtilityObj() {
		return jsonUtilityObj;
	}
	
	
	
	@Given("^User has environment setup for ([^\"]*)$")
	public void user_has_environment_setup_for(String scenarioID) throws Throwable {
	    scenarioContext.setContext(jsonUtilityObj.convertJSONtoMAP(scenarioID));
	    scenario.write("validating response when " + scenarioContext.getContext("scenarioID"));
	}

}

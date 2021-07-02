package com.scale.bat.framework.utility;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.sun.glass.events.KeyEvent;

import cucumber.api.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserFactory {
	Scenario scenario;
	private Logger log = Log.getLogger(BrowserFactory.class);
	private WebDriver driver;
	private ConfigurationReader configReader;
	public static final String URL = "";
	String destination = null;
	DesiredCapabilities caps = null;
	public static final String USERNAME = "";
	public static final String AUTOMATE_KEY = "";
	public static final String browserStackURL = "https://" + USERNAME + ":" + AUTOMATE_KEY
			+ "@hub-cloud.browserstack.com/wd/hub";

	public WebDriver initiateDriver(String browserName, Scenario scenario) throws MalformedURLException {
		configReader = new ConfigurationReader();
		log.info("Opening " + browserName + "browser");
		this.scenario = scenario;

		switch (browserName.toUpperCase()) {
		case "FIREFOX":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			break;
		case "CHROME":
			WebDriverManager.chromedriver().setup();
			ChromeOptions option = new ChromeOptions();
			option.addArguments("--ignore-certificate-errors");
			option.addArguments("--ignore-ssl-errors=yes");
			driver = new ChromeDriver(option);
			driver.manage().window().maximize();
			log.info("Open the Chrome Browser");
			/*for(int i=0; i<5; i++){
		        Robot robot = null;
				try {
					robot = new Robot();
				} catch (AWTException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        robot.keyPress(KeyEvent.VK_CONTROL);
		        robot.keyPress(KeyEvent.VK_MINUS);
		        robot.keyRelease(KeyEvent.VK_CONTROL);
		        robot.keyRelease(KeyEvent.VK_MINUS);
		        }*/
			/*for(int i=0; i<2; i++){
				   driver.findElement(By.tagName("html")).sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
				  }*/
			
			break;
		case "IE":
			WebDriverManager.iedriver().setup();
			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			capabilities.setCapability("ignoreZoomSetting", true);
			capabilities.setCapability("ignoreProtectedModeSettings" , true);
			driver = new InternetExplorerDriver(capabilities);
			driver.manage().window().maximize();
			break;
		case "EDGE":
			WebDriverManager.edgedriver().setup();
			DesiredCapabilities capabilities1 = DesiredCapabilities.edge();
			capabilities1.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			EdgeDriverService service = EdgeDriverService.createDefaultService();
			driver = new EdgeDriver(service);
			driver.manage().window().maximize();
			break;
		case "SAFARI":
			driver = new SafariDriver();
			driver.manage().window().maximize();
			break;
		case "GRID_FIREFOX":
			caps = DesiredCapabilities.firefox();
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), caps);
			break;
		case "GRID_CHROME":
			caps = DesiredCapabilities.chrome();
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), caps);
			break;
		case "CHROME_HEADLESS":
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			// destination = actualPath + "/Library/Application
			// Support/Google/Chrome/"+randomString(8);
			// copyFiles(actualPath + "/Library/Application
			// Support/Google/Chrome/profile1",destination);
			// options.addArguments("user-data-dir=" + destination);
			// options.addArguments("user-data-dir=" + actualPath + "/Library/Application
			// Support/Google/Chrome");
			options.addArguments("--no-sandbox");
			options.addArguments("--headless");
			options.setExperimentalOption("useAutomationExtension", false);
			// options.addArguments("disable-infobars");
			// options.addArguments("--disable-extensions");
			// options.addArguments("--disable-gpu");
			options.addArguments("--disable-dev-shm-usage");
			options.addArguments("window-size=1920,1080");
			driver = new ChromeDriver(options);
			driver.manage().window().maximize();
			break;
		
		case "BROWSERSTACK":
			HashMap<String, String> bsLocalArgs = new HashMap<String, String>();
			bsLocalArgs.put("key", AUTOMATE_KEY);
			caps = new DesiredCapabilities();
			
			/*Local bsLocal = new Local();
			try {
				bsLocal.start(bsLocalArgs);
				log.info(bsLocal.isRunning());
				bsLocalArgs.put("forcelocal", "true");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			
				
			
			//Windows
			
			//BS Windows CHROME
			caps.setCapability("os", "Windows");
			caps.setCapability("os_version", "10");
			caps.setCapability("browser", "Chrome");
			caps.setCapability("browser_version", "latest");
			caps.setCapability("resolution", "1280x800");
			caps.setCapability("browserstack.local", "false");
			caps.setCapability("browserstack.selenium_version", "3.14.0");			
			caps.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			log.info("Open the Chrome Browser");
			
			
			
			
			//BS Windows IE
			
			/*caps.setCapability("browserName", "IE");
			caps.setCapability("browserVersion", "11.0");
			HashMap<String, Object> browserstackOptions = new HashMap<String, Object>();
			browserstackOptions.put("os", "Windows");
			browserstackOptions.put("osVersion", "10");
			browserstackOptions.put("local", "false");
			browserstackOptions.put("seleniumVersion", "3.5.2");
			caps.setCapability("bstack:options", browserstackOptions);
			caps.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);*/
			
			
			//BS Windows EDGE
			/*caps.setCapability("browserName", "Edge");
			caps.setCapability("browserVersion", "latest-beta");
			HashMap<String, Object> browserstackOptions = new HashMap<String, Object>();
			browserstackOptions.put("os", "Windows");
			browserstackOptions.put("osVersion", "10");
			browserstackOptions.put("local", "false");
			browserstackOptions.put("seleniumVersion", "3.5.2");
			caps.setCapability("bstack:options", browserstackOptions);
			caps.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);*/
			
			
			//BS Windows FIREFOX
			/*caps.setCapability("browserName", "Firefox");
			caps.setCapability("browserVersion", "latest-beta");
			HashMap<String, Object> browserstackOptions = new HashMap<String, Object>();
			browserstackOptions.put("os", "Windows");
			browserstackOptions.put("osVersion", "10");
			browserstackOptions.put("local", "false");
			browserstackOptions.put("seleniumVersion", "3.5.2");
			caps.setCapability("bstack:options", browserstackOptions);
			caps.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);*/

				
			
			//********************* MAC *********************************
			
						
			//BS MAC SAFARI (Mojave)
			/*caps.setCapability("browserName", "Safari");
			caps.setCapability("browserVersion", "12.1");
			HashMap<String, Object> browserstackOptions = new HashMap<String, Object>();
			browserstackOptions.put("os", "OS X");
			browserstackOptions.put("osVersion", "Mojave");
			browserstackOptions.put("local", "false");
			browserstackOptions.put("seleniumVersion", "3.14.0");
			caps.setCapability("bstack:options", browserstackOptions);
			caps.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);*/
			
			//BS MAC CHROME (Catalin)
			/*caps.setCapability("browserName", "Chrome");
			caps.setCapability("browserVersion", "latest-beta");
			HashMap<String, Object> browserstackOptions = new HashMap<String, Object>();
			browserstackOptions.put("os", "OS X");
			browserstackOptions.put("osVersion", "Catalina");
			browserstackOptions.put("local", "false");
			browserstackOptions.put("seleniumVersion", "3.14.0");
			caps.setCapability("bstack:options", browserstackOptions);*/
			
			
			//BS MAC FIREFOX (Catalin)
			/*caps.setCapability("browserName", "Firefox");
			caps.setCapability("browserVersion", "latest-beta");
			HashMap<String, Object> browserstackOptions = new HashMap<String, Object>();
			browserstackOptions.put("os", "OS X");
			browserstackOptions.put("osVersion", "Catalina");
			browserstackOptions.put("local", "false");
			browserstackOptions.put("seleniumVersion", "3.5.2");
			caps.setCapability("bstack:options", browserstackOptions);
			caps.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);*/
			
			
			//Mac Os
			//BS MAC SAFARI (Catalina) NOT Working
			/*caps.setCapability("browserName", "Safari");
			caps.setCapability("browserVersion", "13.1");
			HashMap<String, Object> browserstackOptions = new HashMap<String, Object>();
			browserstackOptions.put("os", "OS X");
			browserstackOptions.put("osVersion", "Catalina");
			browserstackOptions.put("local", "false");
			browserstackOptions.put("seleniumVersion", "3.14.0");
			caps.setCapability("bstack:options", browserstackOptions);
			caps.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);*/
			
			
			
					
			//************ ANDROID ***********************************
			
			/*caps.setCapability("browser", "Chrome");
			caps.setCapability("browser_version", "11.0");
			caps.setCapability("os_version", "7.0");
			caps.setCapability("device", "Samsung Galaxy S8");
			caps.setCapability("real_mobile", "true");
			caps.setCapability("browserstack.local", "false");*/
		
			//Android Samsung Galaxy S20 Chrome
			/*caps.setCapability("os_version", "10.0");
			caps.setCapability("device", "Samsung Galaxy S20");
			caps.setCapability("real_mobile", "true");
			caps.setCapability("browserstack.local", "false");*/
			
			//********************* IOS ******************************
			
			//Iphone8
		/*	caps.setCapability("os_version", "11");
			caps.setCapability("device", "iPhone 8 Plus");
			caps.setCapability("real_mobile", "true");
			caps.setCapability("browserstack.local", "false");*/
			
			//Iphone11
			/*caps.setCapability("os_version", "13");
			caps.setCapability("device", "iPhone 11");
			caps.setCapability("real_mobile", "true");
			caps.setCapability("browserstack.local", "false");
			caps.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);*/
			
			//***************************************************************
			
			

			
			//caps.setCapability("browserstack.local", "true");
			caps.setCapability("name", scenario.getName());
			caps.acceptInsecureCerts();
			caps.setAcceptInsecureCerts(true);
			driver = new RemoteWebDriver(new URL(browserStackURL), caps);
			//driver.manage().window().maximize();
			

			
		}
		return driver;
	}

	public void launchURL(String url) {
		//driver.get(url);
		driver.navigate().to(url);
		log.info(URL + " launched");
	}

	public void launchURL(String portalName, String portalExtension) {
		driver.get(configReader.get(portalName) + portalExtension);
		log.info(URL + " launched");
	}

	public static String randomString(int count) {
		String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
			int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
			builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}
		return builder.toString();
	}

	public static void copyFiles(String source, String destination) {
		File src = new File(source);
		File dest = new File(destination);
		try {
			FileUtils.copyDirectory(src, dest);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void deleteDirectory() {
		try {
			if (!destination.isEmpty() || !destination.equalsIgnoreCase("")) {
				FileUtils.deleteDirectory(new File(destination));
			}
		} catch (Exception e) {
			log.info(e.getMessage());
		}
	}

	public void launchURL() {
		driver.get(configReader.getApplicationURL());
	}
}

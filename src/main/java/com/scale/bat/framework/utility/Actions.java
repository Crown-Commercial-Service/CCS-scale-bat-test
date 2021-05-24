package com.scale.bat.framework.utility;

import java.util.List;
import java.util.Random;
import cucumber.api.Scenario;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Actions {
	private Logger log = Log.getLogger(Actions.class);
	protected Scenario scenario;
	protected WebDriver driver;
	protected WebDriverWait wait;

	public void clickElement(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}
	
	public void clickElementWithJavaScript(String xpath){
        WebElement element = driver.findElement(By.xpath(xpath));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", element);
    }

	public void enterText(WebElement element, String text) {
		waitForSeconds(1);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.clear();
		element.sendKeys(text);
		// element.sendKeys(Keys.TAB);
	}

	public void navigateBackfromBrowser() {
		driver.navigate().back();
	}

	public void enterText(String fieldName, String value) {
		String XPATH = ".//*[contains(text(),'" + fieldName + "')]//..//input";
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPATH)));
		clearTextBox(element);
		element.sendKeys(value);
		waitForSeconds(1);
		element.sendKeys(Keys.TAB);
		log.info("Entered " + value + " into " + fieldName);

	}

	public void enterText(String fieldName, int value) {
		waitForLoad();
		String XPATH = ".//*[contains(text(),'" + fieldName + "')]//..//input";
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPATH)));
		List<WebElement> elementList = driver.findElements(By.xpath(XPATH));
		(elementList.get(elementList.size() - 1)).sendKeys(String.valueOf(value));
		log.info("Entered " + value + " into " + fieldName);
	}

	public String getAttributeValue(WebElement element) {
		//wait.until(ExpectedConditions.elementToBeClickable(element));
		return element.getAttribute("value");
	}
	
	public String getAttributeValue(WebElement element, String attributeValue) {
		//wait.until(ExpectedConditions.elementToBeClickable(element));
		return element.getAttribute(attributeValue);
	}

	public void clickByLinkText(String elementName) {
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.linkText(elementName)));
		element.click();
		log.info("Clicked on " + elementName + " element");
		scenario.write("User Clicked on " + elementName + " option");
	}

	public String getText(String fieldName) {
		String XPATH = ".//*[contains(text(),'" + fieldName + "')]//..//input";
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPATH)));
		return element.getText();
	}
	
	public String getTextXpath(String fieldName) {
		String XPATH = fieldName;
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPATH)));
		return element.getText();
	}
	

	public String getText(WebElement fieldName) {
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(fieldName));
		return element.getText();
	}
	
	public String getDropDownDefaultSelectedOption(String xpath) {
		Select archiveList = new Select(driver.findElement(By.xpath(xpath)));
		String selectedValue = archiveList.getFirstSelectedOption().getText();
		return selectedValue;
	}
	
	public List<WebElement> getAllValuesOfDropDown(String xpath) {
		Select dropdown = new Select(driver.findElement(By.xpath(xpath)));
	    //Get all options
	    List<WebElement> allDropDownValues = dropdown.getOptions();
		return allDropDownValues;
	}
	
	
	

	public List<WebElement> getWebElements(String elementClassName) {
		wait.until(ExpectedConditions.elementToBeClickable(By.className(elementClassName)));
		List<WebElement> elementList = driver.findElements(By.className(elementClassName));

		return elementList;
	}

	public WebElement getWebElementByXpath(String elementClassName) {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(elementClassName)));
		WebElement elementList = driver.findElement(By.xpath(elementClassName));
		return elementList;
	}
	
	public void selectItemFromDropDown(WebElement element, String itemName) {
		//wait.until(ExpectedConditions.elementToBeClickable(element));
		Select dropDown = new Select(element);
		dropDown.selectByVisibleText(itemName);
	}

	public void selectItemFromDropDownByIndex(WebElement element, int index) {
		wait.until(ExpectedConditions.elementToBeClickable(element));
		Select dropDown = new Select(element);
		dropDown.selectByIndex(index);
	}

	public void waitForElementToBeClickable(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void waitForElementToBeVisible(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public boolean existsElement(String xPath) {
		waitForLoad();
		WebElement element = driver.findElement(By.xpath(xPath));
		//log.info("Header: " + element.getText());
		return true;
	}

	public boolean isElementEnabled(String webElementclass) {
		waitForLoad();
		WebElement element = driver.findElement(By.className(webElementclass));
		boolean isElementEnabled = element.isEnabled();
		return isElementEnabled;
	}

	public boolean isElementDisabled(String webElementclass) {
		waitForLoad();
		WebElement element = driver.findElement(By.className(webElementclass));
		boolean isElementEnabled = !element.isEnabled();
		return isElementEnabled;
	}

	public void waitForLoad() {
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(pageLoadCondition);
	}
	
	
	public void waitForLoad(WebDriver driver) {
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(pageLoadCondition);
	}

	public void clickElement(String elementName) {
		waitForSeconds(2);
		String XPATH = ".//*[contains(text(),'" + elementName + "')]";
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPATH)));
		element.click();
		//log.info("Clicked on " + elementName + " element");
		scenario.write("User Clicked on " + elementName + " option");

	}

	public void clickButton(String buttonName) {
		waitForSeconds(2);
		String XPATH = "//button[contains(text(),'" + buttonName + "')]";
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPATH)));
		// ((JavascriptExecutor)
		// driver).executeScript("arguments[0].scrollIntoView(true);", element);
		// waitForSeconds(1);
		element.click();
		log.info("Clicked on " + buttonName + " button");
		scenario.write(" User Clicked on " + buttonName + " button");
	}

	public void clearTextBox(WebElement element) {
		element.clear();
	}

	public void waitForSeconds(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (Exception e) {

		}
	}

	// public void validateText(String expected, String elementName) {
	// if (elementName.equalsIgnoreCase("Bank charges")) {
	// String xpathBankCharges = "//*[contains(text(),'" + elementName +
	// "')]/parent::div/child::div/div";
	// WebElement element =
	// wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathBankCharges)));
	// String actual = element.getText();
	// Assert.assertEquals(actual, expected);
	// } else {
	// String XPATH = "//*[contains(text(),'" + elementName +
	// "')]/following::span[1]";
	// WebElement element =
	// wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPATH)));
	// String actual = element.getText();
	// Assert.assertEquals(actual, expected);
	// }
	//
	// }

	public void clickElementWithDiv(String elementName) {
		String XPATH = ".//div[contains(text(),'" + elementName + "')]";
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPATH)));
		element.click();
	}

	public boolean isButtonEnabled(String fieldName) {
		waitForLoad();
		String XPATH = ".//*[contains(text(),'" + fieldName + "')]";
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPATH)));
		WebElement element = driver.findElement(By.xpath(XPATH));
		boolean isButtonEnabled = element.isEnabled();
		return isButtonEnabled;
	}

	public boolean isButtonDisabled(String fieldName) {
		waitForLoad();
		String XPATH = ".//*[contains(text(),'" + fieldName + "')]";
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPATH)));
		WebElement element = driver.findElement(By.xpath(XPATH));
		boolean isElementDisabled = !element.isEnabled();
		return isElementDisabled;
	}

	public boolean isElementPresent(String fieldName) {
		waitForLoad();
		String XPATH = ".//*[contains(text(),'" + fieldName + "')]";
		try {
			driver.findElement(By.xpath(XPATH));
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	
	public boolean isElementPresent(String fieldName, WebDriver driver ) {
		waitForLoad(driver);
		String XPATH = ".//*[contains(text(),'" + fieldName + "')]";
		try {
			driver.findElement(By.xpath(XPATH));
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	
	

	public boolean isElementPresentByXpath(String xpath) {
		waitForLoad();
		try {
			driver.findElement(By.xpath(xpath));
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public int randomBunmber(int bound) {
		return new Random().nextInt(bound);
	}

	public void clickActionsLinkOfCustomer(String customerCIN, String linkName, WebDriver webDriver) {
		waitForSeconds(5);
		WebElement linkElement = (new WebDriverWait(webDriver, 50))
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'" + customerCIN
						+ "')]/following::div/a[contains(@title,'" + linkName + "')]")));
		linkElement.click();
	}

	public void clickActionsLinkOfUser(String CPID, String linkIcon, WebDriver webDriver) {
		WebElement linkElement = (new WebDriverWait(webDriver, 50)).until(ExpectedConditions.elementToBeClickable(By
				.xpath("//*[contains(text(),'" + CPID + "')]/following::div/a[contains(@title,'" + linkIcon + "')]")));
		linkElement.click();
	}

	public void clickTab(String tabID) {
		String XPATH = "//a[contains(@id,'" + tabID + "')]";
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPATH)));
		element.click();
	}

	public void moveToelement(WebElement element) {
		org.openqa.selenium.interactions.Actions actions = new org.openqa.selenium.interactions.Actions(driver);
		actions.moveToElement(element).build().perform();
	}

	public void waitForAlert() {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.alertIsPresent());
	}
	
	public void waitForAlert(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.alertIsPresent());
	}
	
	
		public int getElementsSizeByXpath(String xpath, WebDriver driver) {
	    	int elementSize=0;
	        try {
	             elementSize=driver.findElements(By.xpath(xpath)).size();
	            return elementSize;
	        } catch (NoSuchElementException e) {
	            return elementSize;
	        }catch (Exception e) {
	        	return elementSize;
	        }
	    }
		
		
		public int getElementsSizeParameterizedXpath(String xpath, WebDriver driver) {
	    	int elementSize1=0;
	       	String XPATH = "//*[@id='content']/table/tbody/tr/td[text()="+"'"+xpath+"'"+"]";
	    	
	        try {
	             elementSize1=driver.findElements(By.xpath(XPATH)).size();
	            return elementSize1;
	        } catch (NoSuchElementException e) {
	            return elementSize1;
	        }
	    }
		
		public int getElementsSizeParameterizedXpath(String value1,String value2, WebDriver driver) {
	    	int elementSize1=0;
	    	String XPATH = "//*[@id='content']/table/tbody/tr/td[text()="+"'"+value1+"'"+"]"+"/following-sibling::td[text()="+"'"+value2+"'"+"]";
	    	
	        try {
	             elementSize1=driver.findElements(By.xpath(XPATH)).size();
	            return elementSize1;
	        } catch (NoSuchElementException e) {
	            return elementSize1;
	        }
	    }
		
		public int getElementsSizeParameterizedXpathContains(String value1,String value2, WebDriver driver) {
	    	int elementSize1=0;
	    	String XPATH = "//*[@id='content']/table/tbody/tr/td[text()="+"'"+value1+"'"+"]"+"/following-sibling::td[contains (text(),"+"'"+value2+"'"+")]";
	    	
	        try {
	             elementSize1=driver.findElements(By.xpath(XPATH)).size();
	            return elementSize1;
	        } catch (NoSuchElementException e) {
	            return elementSize1;
	        }
	    }
		
		
		public void selectItemFromDropDownByJavascriptExecutor(WebDriver driver, WebElement supplierFilter, String value) {
			 //try {
				  ((JavascriptExecutor) driver).executeScript(
							"var select = arguments[0]; for(var i = 0; i < select.options.length; i++){ if(select.options[i].text == arguments[1]){ select.options[i].selected = true; } }",
							supplierFilter, value);
				 
			/*} catch (Exception e) {
				
			}*/
			 
			}
		 
		 public void clickElementXpath(WebElement element) {
		        wait.until(ExpectedConditions.elementToBeClickable(element));
		        element.click();
		        log.info("Clicked on element");
		        scenario.write("User Clicked on element");
		    }
		 
		 public int getElementsSizeParameterizedXpathPCP(String xpath, WebDriver driver) {
		    	int elementSize1=0;
		    	String XPATH = "//*[@id='content']/table/tbody/tr/td/span[text()="+"'"+xpath+"'"+"]";
		       		
		        try {
		             elementSize1=driver.findElements(By.xpath(XPATH)).size();
		            return elementSize1;
		        } catch (NoSuchElementException e) {
		            return elementSize1;
		        }
		    }
		 
		 public int getElementsSizeParameterizedXpathInPCP(String xpath, WebDriver driver) {
		    	int elementSize1=0;
		       	String XPATH = "//*[@id='content']/table/tbody/tr/td[contains(text(),"+"'"+xpath+"')"+"]";
		    	
		        try {
		             elementSize1=driver.findElements(By.xpath(XPATH)).size();
		            return elementSize1;
		        } catch (NoSuchElementException e) {
		            return elementSize1;
		        }
		    }
		 
		 public int getElementsSizeParameterizedXpathContainsPCP(String xpath, WebDriver driver) {
		    	int elementSize1=0;
		    	String XPATH = "//*[@id='content']/table/tbody/tr/td/a[contains(text(),"+"'"+xpath+"'"+")]";
		       		
		        try {
		             elementSize1=driver.findElements(By.xpath(XPATH)).size();
		            return elementSize1;
		        } catch (NoSuchElementException e) {
		            return elementSize1;
		        }
		    }
		 
	
}
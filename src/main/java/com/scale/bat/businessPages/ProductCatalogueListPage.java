package com.scale.bat.businessPages;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.scale.bat.framework.utility.Actions;
import com.scale.bat.framework.utility.Log;

import cucumber.api.Scenario;
import junit.framework.Assert;

public class ProductCatalogueListPage extends Actions {

	private Logger log = Log.getLogger(ProductCatalogueListPage.class);
	private WebDriver driver;

	@FindBy(xpath = "//*[@class='actions']/a")
	private WebElement show;

	@FindBy(xpath = "//*[@id='q_vendor_name_eq']")
	private WebElement supplierFilter;
	
	@FindBy(xpath = "//*[@id='select2-drop']/div/input")
	//@FindBy(xpath = "//*[@id='s2id_q_vendor_name_eq']")
	private WebElement supplierFilterTextBox;

//	@FindBy(xpath = "//select[@id='q_vendor_name_eq']")
//	private WebElement supplierFilter;

	//@FindBy(xpath = "//*[@id='s2id_q_lot_commercial_agreement_ref_eq']")
	@FindBy(xpath = "//*[@id='s2id_q_lot_commercial_agreement_ref_eq']/following-sibling::select")
	private WebElement commercialAgreementRef;

	@FindBy(xpath = "//*[@id='q_unpublished']")
	private WebElement unpublishedCheckBox;

	@FindBy(xpath = "//*[@id='q_published']")
	private WebElement publishedCheckBox;

	@FindBy(xpath = "//*[@id='content']/table/tbody/tr/td[7]")
	private WebElement editLink;

	//@FindBy(xpath = "//*[@id='main-part']/div[1]/div/h1")
	@FindBy(xpath = "//h1[@class='contextual-title px-0 mb-0 col']")
	//@FindBy(xpath = "//*[@id='content']/table/tbody/tr/td[1]")
	private WebElement contentHeader;

	private String search = "Search";
	
	private String edit = "Edit";
	
	//Hardik 
	
	@FindBy(xpath = "//*[@class='next_page page-item']/a")
	private WebElement nextButton;
	
	@FindBy(xpath = "//*[@id='q_mpn_number_cont']")
	private WebElement Mpn;
	
	@FindBy(xpath = "//*[@id='q_variants_including_master_sku_cont']")
	private WebElement SKU;
	
	@FindBy(xpath = "//select[@id='q_vendor_name_eq']")
	private WebElement supplierFilterClick;
	
	@FindBy(xpath = "//select[@id='q_lot_commercial_agreement_ref_eq']")
	private WebElement commercialAgreementRefClick;
	
	@FindBy(xpath = "//*[@id='q_name_cont']")
	private WebElement productName;
	
	@FindBy(xpath = "//button[@class='btn btn-primary '][text()='Search']")
	private WebElement searchxp;
	
	
	@FindBy(xpath = "//*[@id='q_deleted_at_null']")
	private WebElement deleteCheckBox;
	
	@FindBy(xpath = "//*[@id='content']/table/tbody/tr/td[7]/a[2]")
	private WebElement deleteFirstProduct;
	
	
	private String totalPaginationPageCountPLP="//ul[@class='pagination d-inline-flex ']/li";
	private String lastPageButton="//li[@class='last next page-item']/a";
	private String totalPaginationPageCount="//*[@id='content']/div[3]/div[1]/ul/li";
	
	private String beforeRowCount="";
	private String afterRowCount="";
	
	SoftAssert softassert = new SoftAssert();
	

	/*
	 * ProductCatalogueListPage when user login and clicks on product catalogues on
	 * main side bar
	 */
	public ProductCatalogueListPage(WebDriver driver, Scenario scenario) {
		this.driver = driver;
		//super.driver = driver;
		this.scenario = scenario;
		PageFactory.initElements(driver, this);
		this.wait = new WebDriverWait(this.driver, 30);
		
		// New Line
		//this.wait = new WebDriverWait(super.driver, 30);
		//this.driver=super.driver;
	}
	
	

	public void showProducts() {
		waitForSeconds(7);
		clickElement(show);
		assertTrue("Header is different!! Please check vendor",getText(contentHeader).toLowerCase().contains("RM6147".toLowerCase()));
		log.info("Supplier name is present on product catalogue header");
	}
	
	
	public void checkProductPresentInCatalogueIfYesThendelete() {
		waitForSeconds(2);
		boolean element = isElementPresent("Add One", driver);
		
		System.out.println(element);
		if(isElementPresent("Add One", driver)) {
			
			assertTrue("Product is not created successfully..", isElementPresent("Add One", driver));
			log.info("Product is not present in the Supplier Catalouge");
			
		}else {
			try {
				
				waitForSeconds(1);
				clickElement(deleteFirstProduct);
				waitForAlert(driver);
				driver.switchTo().alert().accept();
				waitForSeconds(2);
				assertTrue("Product is not deleted successfully..", isElementPresent("Product has been deleted", driver));
				//assertEquals("Product is not deleted successfully..", isElementPresent("Product has been deleted", driver));
				//verify("Product is not deleted successfully..");
				
			} catch (Error e) {
				//assertFalse("Message : Product has been deleted but not shown the message", (isElementPresent("Product has been deleted", driver)));
				//assertNotSame(string1, string3);
				softassert.fail("Message : Product has been deleted but not shown the message");
				
				//("Message : Product has been deleted but not shown the message", "Product has been deleted");
				//fail("Message : Product has been deleted but not shown the message");
				System.out.println("Message : Product has been deleted is not shown");
				log.info(e.toString());
			}
			
			
		}
		
		
		
	}

	public void editProduct() {
		clickByLinkText(edit);
	}

	/*
	 * User filter data single time. Incase data is filtered using check box,
	 * provide empty string
	 */
	public void filterData(String filter, String value) {
		String[] spiltValue;
		
		switch (filter.toLowerCase()) {
		case "supplier":
//			((JavascriptExecutor) driver).executeScript(
//					"var select = arguments[0]; for(var i = 0; i < select.options.length; i++){ if(select.options[i].text == arguments[1]){ select.options[i].selected = true; } }",
//					supplierFilter, value);
			/*supplierFilter.click();
			enterText(supplierFilterTextBox, value);
			try {
				new Robot().keyPress(KeyEvent.VK_ENTER);
			} catch (AWTException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			
			selectItemFromDropDownByJavascriptExecutor(driver,supplierFilter, value);
			
			break;
		case "commercial agreement reference":
			//selectItemFromDropDown(commercialAgreementRef, value);
			selectItemFromDropDownByJavascriptExecutor(driver,commercialAgreementRefClick, value);
			
			
			break;
		case "unpublished":
			clickElement(unpublishedCheckBox);
			break;
		case "published":
			clickElement(publishedCheckBox);
			break;
		case "publishedunpublished":
			clickElement(publishedCheckBox);
			clickElement(unpublishedCheckBox);
			break;	
		case "publishedcar":
			spiltValue = value.split("&");
			selectItemFromDropDownByJavascriptExecutor(driver,commercialAgreementRefClick, spiltValue[1]);
			waitForSeconds(1);
			clickElement(publishedCheckBox);
			waitForSeconds(1);
			break;
		
		case "unpublishedcar":
			spiltValue = value.split("&");
			selectItemFromDropDownByJavascriptExecutor(driver,commercialAgreementRefClick, spiltValue[1]);
			System.out.println("Selected CAR filter");	
			waitForSeconds(1);
			clickElement(unpublishedCheckBox);
			waitForSeconds(1);
			break;
			
		case "supplierpublished":
			
			spiltValue = value.split("&");
			waitForSeconds(2);
			selectItemFromDropDownByJavascriptExecutor(driver,supplierFilterClick, spiltValue[0]);
			System.out.println("Selected Supplier filter");
			waitForSeconds(1);
			clickElement(publishedCheckBox);
			
			break;
			
		case "mpn":
			enterText(Mpn, value);
			break;
			
		case "sku":
			enterText(SKU, value);
			break;
			
		case "suppliercar":
			
			spiltValue = value.split("&");
			waitForSeconds(2);
			selectItemFromDropDownByJavascriptExecutor(driver,supplierFilterClick, spiltValue[0]);
			System.out.println("Selected Supplier filter");
			waitForSeconds(1);
			selectItemFromDropDownByJavascriptExecutor(driver,commercialAgreementRefClick, spiltValue[1]);
			break;
			
		case "carunpublishedpublished":
			
			spiltValue = value.split("&");
			waitForSeconds(2);
			selectItemFromDropDownByJavascriptExecutor(driver,commercialAgreementRefClick, spiltValue[0]);
			waitForSeconds(1);
			clickElement(publishedCheckBox);
			clickElement(unpublishedCheckBox);
			break;
		
		case "productname":
			enterText(productName, value);
			break;
			
			
		default:
			log.info(filter + " filter used. Check the BDD for proper spellings or wrong link text");
		}
		clickButton(search);
	}

	public void verifyContent(String Text) {
		log.info("Text from UI - "+getText(contentHeader) +". Content to check - "+ Text);
		assertTrue("Header is different!! Please check vendor",getText(contentHeader).toLowerCase().contains(Text.toLowerCase()));
		
	}
	
	
	public void checkTotalRowsCountOfSelectedCAR(String filterValue, String beforeOrAfterInstruction, String filter) throws MalformedURLException, InterruptedException {
		
		 int rowcount=0;
		 int actualPageCount=0;
		 int totalPageCount=0;
		 int sumCount=0;
		 String filterValueFor="";
		 String beforeRowCountKey="BeforeRowCount";
		 
		 	 if(getElementsSizeByXpath(totalPaginationPageCountPLP, driver)>0) {
			 totalPageCount=getElementsSizeByXpath(totalPaginationPageCountPLP, driver);
			 actualPageCount= (totalPageCount-2);
			 			 
			 for(int i=1;i<=actualPageCount;i++) {
				 rowcount=0;
				 rowcount=getAppliedFilterTotalRowsCount(filter, filterValue);
				 sumCount =sumCount+rowcount;
				 if(actualPageCount==i) {
					
				 }else {
					 nextButton(); 
				 }
			 	}
		 }else {
			 
			 rowcount=getAppliedFilterTotalRowsCount(filter, filterValue);
			 sumCount=rowcount;
		 }
		 	
		 	 if(beforeOrAfterInstruction.equalsIgnoreCase("before")) {
		 		beforeRowCount=Integer.toString(sumCount);
			 	//System.out.println("Before Applying Filter Total Rows Count: " + sumCount);
			  	
		 	 }else {
		 		 
		 		afterRowCount=Integer.toString(sumCount);
			 	//System.out.println("After Applying Filter Total Rows Count: " + sumCount);
			}
		 
	}
	
	
	
	public int getAppliedFilterTotalRowsCount(String filterName, String filterValue) throws MalformedURLException, InterruptedException {
		
		int rowcount=0;
		int rowcount1=0;
		int rowcount2=0;
		
		
		switch (filterName.toLowerCase()) {
		case "supplier":
				
			rowcount=getElementsSizeParameterizedXpath(filterValue, driver);
			
			break;
		case "commercial agreement reference":
				
			rowcount=getElementsSizeParameterizedXpath(filterValue, driver);
			break;
		case "unpublished":
			filterValue="unpublished ";
			rowcount=getElementsSizeParameterizedXpath(filterValue, driver);
			break;
		case "published":
			filterValue="published ";
			rowcount=getElementsSizeParameterizedXpath(filterValue, driver);
			break;
			
		case "publishedunpublished":
			
			String[] splitFilterValue=filterValue.split("&");
			rowcount1=getElementsSizeParameterizedXpath(splitFilterValue[0]+" ", driver);
			rowcount2=getElementsSizeParameterizedXpath(splitFilterValue[1]+" ", driver);
			rowcount=rowcount1+rowcount2;
			break;
			
		case "publishedcar":
			
			String[] splitFilterValue1=filterValue.split("&");
			rowcount=getElementsSizeParameterizedXpath(splitFilterValue1[1],splitFilterValue1[0]+" ", driver);
			break;
			
		case "unpublishedcar":
			
			String[] splitFilterValue2=filterValue.split("&");
			rowcount=getElementsSizeParameterizedXpath(splitFilterValue2[1],splitFilterValue2[0]+" ", driver);
			break;
				
		case "supplierpublished":
			
			String[] splitFilterValue3=filterValue.split("&");
			rowcount=getElementsSizeParameterizedXpath(splitFilterValue3[0],splitFilterValue3[1]+" ", driver);
			break;		
			
		case "suppliercar":
			
			String[] splitFilterValue4=filterValue.split("&");
			rowcount=getElementsSizeParameterizedXpath(splitFilterValue4[0],splitFilterValue4[1], driver);
			break;
			
		case "carunpublishedpublished":
			
			String[] splitFilterValue5=filterValue.split("&");
			rowcount=getElementsSizeParameterizedXpathContains(splitFilterValue5[0],splitFilterValue5[1]+" ", driver);
			break;
			
		default:
			log.info(filterName+" filter used. Check the BDD for proper spellings or wrong link text");
			}
		return rowcount;
		}
	
	
	public void verifyFilterValue(String filter, String Value) {
		log.info("Before applying filter Count - "+ beforeRowCount + "After applying filter Count - "+ afterRowCount);
		assertTrue("Filter Value is Incorrect!! Please check filter " + filter + "with value " + Value,beforeRowCount.equalsIgnoreCase(afterRowCount));
		//System.out.println("Befor Filter Count: "+ beforeRowCount);
		//System.out.println("After Filter Count: "+ afterRowCount);
		//System.out.println("Test Case Pass/Fail : " + beforeRowCount.equalsIgnoreCase(afterRowCount));
		
	}
	
	//#Product Catalouge Page
		public void checkTotalRowCountsOfSelectedFilterInPCP(String filterValue, String beforeOrAfterInstruction, String filter) throws MalformedURLException, InterruptedException {
			
			 int rowcount=0;
			 int actualPageCount=0;
			 int totalPageCount=0;
			 int sumCount=0;
			 String filterValueFor="";
			 String beforeRowCountKey="BeforeRowCount";
			 
			 	 
			 	 searchButton();
			 	 Thread.sleep(2000);
			 	if(getElementsSizeByXpath(lastPageButton, driver)>0) {
			 	 totalPageCount=getElementsSizeByXpath(totalPaginationPageCount, driver);
			 	 actualPageCount= (totalPageCount-2);
			 	 
				 for(int i=1;i<=actualPageCount;i++) {
					 String pageNo="";
					 pageNo=Integer.toString(i);
					 if(actualPageCount>=i) {
						 waitForSeconds(1);
						 rowcount=0;
							rowcount=getAppliedFilterTotalRowsCountinPCP(filter, filterValue, beforeOrAfterInstruction);
							sumCount =sumCount+rowcount;
							
							if(actualPageCount==i) {
								
							}else {
								nextButton();
							}
							
					 }
					 
					 		 
				 }
				 
							 
				 
			 }else {
				 
				 rowcount=getAppliedFilterTotalRowsCountinPCP(filter, filterValue, beforeOrAfterInstruction);
				 sumCount=rowcount;
			 }
			 	
			 	 if(beforeOrAfterInstruction.equalsIgnoreCase("before")) {
			 		beforeRowCount=Integer.toString(sumCount);
				 	System.out.println("Before Applying Filter Total Rows Count: " + sumCount);
				  	
			 	 }else {
			 		 
			 		afterRowCount=Integer.toString(sumCount);
				 	System.out.println("After Applying Filter Total Rows Count: " + sumCount);
				}
			 
		}
	
		
		//#Product Catalouge Page 
				public int getAppliedFilterTotalRowsCountinPCP(String filterName, String filterValue, String beforeOrAfterInstruction) throws MalformedURLException, InterruptedException {
					
					int rowcount=0;
					int rowcount1=0;
					int rowcount2=0;
					int rowcount3=0;
								
					switch (filterName.toLowerCase()) {
					
					case "unpublished":
						filterValue="Unpublished";
						rowcount=getElementsSizeParameterizedXpathPCP(filterValue, driver);
						break;
						
					case "published":
						filterValue="Published";
						rowcount=getElementsSizeParameterizedXpathPCP(filterValue, driver);
						break;
						
					case "publishedunpublished":
						
						String[] splitFilterValue=filterValue.split("&");
						rowcount1=getElementsSizeParameterizedXpathPCP(splitFilterValue[0], driver);
						rowcount2=getElementsSizeParameterizedXpathPCP(splitFilterValue[1], driver);
						rowcount=rowcount1+rowcount2;
						break;
						
					case "mpn":
						
						rowcount=getElementsSizeParameterizedXpathInPCP(filterValue, driver);
						break;
						
					case "sku":
						
						rowcount=getElementsSizeParameterizedXpathInPCP(filterValue, driver);
						break;
						
					case "publisheddeleted":
						
						rowcount=getElementsSizeParameterizedXpath(filterValue, driver);
						break;
						
					case "productname":
						
						rowcount=getElementsSizeParameterizedXpathContainsPCP(filterValue, driver);
						break;
						
					case "publisheddelete":
						
							
						String[] splitFilterValue1=filterValue.split("&");
						
						if(beforeOrAfterInstruction.equalsIgnoreCase("before") ) {
							
							//Published
							clickElement(publishedCheckBox);
							waitForSeconds(3);
							clickElementXpath(searchxp);
							waitForSeconds(3);
							rowcount=getElementsSizeParameterizedXpathPCP(splitFilterValue1[0], driver);
							waitForSeconds(3);
							clickElement(publishedCheckBox);
							waitForSeconds(3);
							clickElementXpath(searchxp);
							waitForSeconds(3);
							
							
						}else {
						
							waitForSeconds(3);
						//Published
						clickElement(publishedCheckBox);
						clickElement(deleteCheckBox);
						waitForSeconds(3);
						clickElementXpath(searchxp);
						waitForSeconds(3);
						rowcount1=getElementsSizeParameterizedXpathPCP(splitFilterValue1[0], driver);
						rowcount2=getElementsSizeParameterizedXpath(splitFilterValue1[1]+" ", driver);
						rowcount3=getElementsSizeParameterizedXpathPCP("Unpublished", driver);
						clickElement(publishedCheckBox);
						clickElement(deleteCheckBox);
						waitForSeconds(3);
						clickElementXpath(searchxp);
						waitForSeconds(3);
						rowcount=rowcount1+rowcount2+rowcount3;
						
						}
						
						
						break;
						
						
					case "unpublisheddelete":
						
						
						String[] splitFilterValue2=filterValue.split("&");
						
						if(beforeOrAfterInstruction.equalsIgnoreCase("before") ) {
							
							//Published
							clickElement(deleteCheckBox);
							waitForSeconds(3);
							clickElementXpath(searchxp);
							waitForSeconds(3);
							rowcount1=getElementsSizeParameterizedXpathPCP(splitFilterValue2[0], driver);
							rowcount2=getElementsSizeParameterizedXpath(splitFilterValue2[1], driver);
							rowcount=rowcount1+rowcount2;
							waitForSeconds(3);
							clickElement(deleteCheckBox);
							waitForSeconds(3);
							clickElementXpath(searchxp);
							waitForSeconds(3);
							
							
						}else {
						
							waitForSeconds(3);
						//Published
						clickElement(unpublishedCheckBox);
						clickElement(deleteCheckBox);
						waitForSeconds(3);
						clickElementXpath(searchxp);
						waitForSeconds(3);
						rowcount1=getElementsSizeParameterizedXpathPCP(splitFilterValue2[0], driver);
						rowcount2=getElementsSizeParameterizedXpath(splitFilterValue2[1]+" ", driver);
						rowcount3=getElementsSizeParameterizedXpathPCP("Published", driver);
						clickElement(unpublishedCheckBox);
						clickElement(deleteCheckBox);
						waitForSeconds(3);
						clickElementXpath(searchxp);
						waitForSeconds(3);
						rowcount=rowcount1+rowcount2+rowcount3;
						
						}
						
						
						break;
						
						
						
						
					default:
						log.info(filterName+" filter used. Check the BDD for proper spellings or wrong link text");
						}
					return rowcount;
					}
	
	public void nextButton() {
		clickElement(nextButton);
		
	}
	
	public void searchButton() {
		clickElementXpath(searchxp);
		assertTrue("Successfuly clicked on search button ",isElementPresentByXpath(searchxp));
		log.info("Successfuly clicked on search button");
	}

	

}

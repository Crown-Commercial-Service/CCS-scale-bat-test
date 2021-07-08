package com.scale.bat.businessPages;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.scale.bat.framework.utility.Actions;
import com.scale.bat.framework.utility.ConfigurationReader;
import com.scale.bat.framework.utility.DateTimeUtils;
import com.scale.bat.framework.utility.Log;
import com.scale.bat.framework.utility.API.APIBase;

import cucumber.api.Scenario;
import io.restassured.response.Response;

public class BuyersUIQuotesPage extends Actions{
	
	
	private Logger log = Log.getLogger(BuyersUIPDPPage.class);
	private ConfigurationReader configReaderObj;
	APIBase apiBase=new APIBase();
	
	public BuyersUIQuotesPage(WebDriver driver, Scenario scenario) {
		super.driver = driver;
		this.scenario = scenario;
		PageFactory.initElements(driver, this);
		this.wait = new WebDriverWait(super.driver, 30);
		configReaderObj = new ConfigurationReader();
	}
	
	/**
	 * Class level variable decleration
	 */
	public static String firstRowQuoteNo;
	public static String quoteNameWithCurrenntday;
	public static String supplierNameBasketPage;
	public static String totalPriceBasketPage;
	public static String rejectreasonWithCurrenntday;
	public static String actualTotalPriceBasketPage;
	
	/**
	 * Class level Objects decleration
	 */
	@FindBy(xpath = "//*[@id='name']")
	 private WebElement quoteName;
	
	@FindBy(xpath = "//table[@class='govuk-table bat-quotes__table']/tbody/tr[1]/td[1]/a")
	 private WebElement getFirstRowQuoteNo;
	
	@FindBy(xpath = "//table[@class='govuk-table bat-quotes__table']/tbody/tr[1]/td[2]")
	 private WebElement getFirstRowQuoteName;
	
	@FindBy(xpath = "//table[@class='govuk-table bat-quotes__table']/tbody/tr[1]/td[3]")
	 private WebElement getFirstRowdateQuoteRaised;
	
	@FindBy(xpath = "//table[@class='govuk-table bat-quotes__table']/tbody/tr[1]/td[4]")
	 private WebElement getFirstRowSupplierName;
	
	@FindBy(xpath = "//table[@class='govuk-table bat-quotes__table']/tbody/tr[1]/td[5]")
	 private WebElement getFirstRowType;
	
	@FindBy(xpath = "//table[@class='govuk-table bat-quotes__table']/tbody/tr[1]/td[6]")
	 private WebElement getFirstRowExpiryDate;
	
	@FindBy(xpath = "//table[@class='govuk-table bat-quotes__table']/tbody/tr[1]/td[7]")
	 private WebElement getFirstRowTotalValueIncVAT;
	
	@FindBy(xpath = "//table[@class='govuk-table bat-quotes__table']/tbody/tr[1]/td[8]")
	 private WebElement getFirstRowStatus;
	
	@FindBy(xpath = "//h3[@class='govuk-heading-s bat-basket-items-by-supplier__item__supplier__name']")
	 private WebElement getSupplierNameBasketPage;
	
	//@FindBy(xpath = "//div[@class='bat-basket__footer']/div[2]/ul/li[3]/span[2]")
	@FindBy(xpath = "//*[@id='main-content']/div[2]/div/div/div/div[2]/ul/li[4]/span[2]")
	 private WebElement getTotalPriceBasketPage;
	
	@FindBy(xpath = "//input[@value='firm']/following-sibling::div")
	 private WebElement getFirmQuoteLabel;
	
	@FindBy(xpath = "//input[@value='indicative']/following-sibling::div")
	 private WebElement getIncicativeQuoteLabel;
	
	@FindBy(xpath = "//ul[@class='govuk-list govuk-body-s govuk-!-margin-bottom-6 govuk-!-margin-right-3']/li[2]")
	 private WebElement getIncicativeStatus;
	
	@FindBy(xpath = "//ul[@class='govuk-list govuk-body-s govuk-!-margin-bottom-6 govuk-!-margin-right-3']/li[4]")
	 private WebElement getIncicativeMessage;
	
	//Table data in Admin UI
	
	@FindBy(xpath = "//table[@class='table']/tbody/tr[1]/td[1]/a")
	 private WebElement getFirstRowQuoteNoAdminUI;
	
	@FindBy(xpath = "//table[@class='table']/tbody/tr[1]/td[2]")
	 private WebElement getDateQuoteRaisedAdminUI;
	
	@FindBy(xpath = "//table[@class='table']/tbody/tr[1]/td[4]")
	 private WebElement getTotalValueIncVATAdminUI;
	
	@FindBy(xpath = "//table[@class='table']/tbody/tr[1]/td[5]")
	 private WebElement getExpiryDateAdminUI;
	
	@FindBy(xpath = "//table[@class='table']/tbody/tr[1]/td[6]")
	 private WebElement getQuoteStatusAdminUI;
	
	@FindBy(xpath = "//table[@class='table']/tbody/tr[1]/td[7]/a")
	 private WebElement getQuoteStatusRejectedI;
	
	@FindBy(xpath = "//h1[@class='contextual-title px-0 mb-0 col']")
	 private WebElement rejectQuotePageTitleAdminUI;
	
	@FindBy(xpath = "//table[@class='table']/following-sibling::div/div[1]")
	 private WebElement rejectQuoteStatusInAdminUI;
	
	
	@FindBy(xpath = "//textarea[@name='scale_quote[rejection_reason]']")
	 private WebElement rejectReasonTextboxRejectQuotePageAdminUIWebElement;
	
	private String rejectReasonTextboxRejectQuotePageAdminUI = "//textarea[@name='scale_quote[rejection_reason]']";
	private String rejectButtonRejectQuotePageAdminUI = "//span[@class='translation_missing']";
	private String cancleButtonRejectQuotePageAdminUI = "//a[@class='btn btn-outline-secondary']";
	private String message="//p[@class='govuk-notification-banner__heading']";
	private String errMsgXpath = "//*[@id=\"main-content\"]/div/div/p";
	private String errMsgQuoteNotFoundSupplierView = "//*[@class='alert alert-info no-objects-found']";
	private String getAllQuoteOnBuyersUI = "//table[@class='govuk-table bat-quotes__table']/tbody/tr/td[1]/a";
	
	private String getAllQuoteOnAdminUI = "//table[@class='table']/tbody/tr/td[1]/a";
	
	
	@FindBy(xpath = "//nav[@class='pagination ']")
	 private WebElement paginationCountQuotesPage;
	
	@FindBy(xpath = "//li[@class='page active page-item']/a")
	 private WebElement paginationCountQuotesPageAdminUI;
	
	@FindBy(xpath = "//li[@class='pagination__item pagination__item--next']/a")
	 private WebElement nextLinkQuotesPage;
	
	@FindBy(xpath = "//a[@aria-label='Next page']")
	 private WebElement nextLinkQuotesPageAdminUI;
	
	@FindBy(xpath = "//a[@aria-label='Last page']")
	 private WebElement lastPageNavigatorAdminUI;
	
	@FindBy(xpath = "//a[@aria-label='First page']")
	 private WebElement firstPageNavigatorAdminUI;
	
	
	//Other Object
	@FindBy(xpath = "//*[@name='number']")
	 private WebElement quoteReference;
	
	/*@FindBy(xpath = "//a[@href='/admin/quotes']/span")
	 private WebElement quoteLinkAdminPanel;*/
	private String quoteLinkAdminPanel="//a[@href='/admin/quotes']";
	
	private String supplierLinkAdminPanel = "//a[@href='/admin/vendor_settings']";
	
	@FindBy(xpath = "//button[@class='btn btn-primary ']")
	 private WebElement searchButtonAdminPanel;
	
	@FindBy(xpath = "//input[@class='form-control js-filterable']")
	 private WebElement quoteReferenceAdminUI;
	
	@FindBy(xpath = "//*[@id='q_number_cont']")
	private WebElement quoteRefAdminUI ; 
	
	@FindBy(xpath = ".//button[contains(text(),'Clear my basket and add these items')]")
	private WebElement clearMyTextBtn;
	
	private String clearMyTextBtnStr = ".//button[contains(text(),'Clear my basket and add these items')]";
	
	@FindBy(xpath = "//button[@class='govuk-button govuk-button--primary']")
	private WebElement addtoBasketBtnOnIndicative;
	
	private String addtoBasketBtnOnIndicativeStr = "//button[@class='govuk-button govuk-button--primary']";
	
	@FindBy(xpath = ".//p[@id='convert-firm-quote-to-basket-button']")
	private WebElement clearMyBasketTextMsg;
	
	@FindBy(xpath = "//div[@class='govuk-notification-banner__content']")
	private WebElement successMsg;
	
	@FindBy(xpath = "//div[@class='govuk-error-summary__body']/ul/li")
	private WebElement errorMsgInsufficientStockQuotes;
	
	@FindBy(xpath = "//button[@class='govuk-button bat-checkout-next']")
	private WebElement saveAndContinueBtnOnPayentCheckout;
	
	@FindBy(xpath = "//button[@class='govuk-button bat-checkout-summary__actions__action']")
	private WebElement placeOrderCheckout;
	
	
	
	private String manageQuoteColumHeaderSize = "//table[@class='govuk-table bat-quotes__table']/thead/tr/th";
	private String firmQuote = "//*[@id='kind']";
	private String indicativeQuote = "//*[@id='kind-2']";
	private String raiseQuote = "Raise quote";
	
	
	
	
	public void validateNewQuoteCreatedMsg(String Message) {
		
		assertEquals(getTextXpath(message), Message);
		log.info("New Quote created message : " + message + " is validated");
	}
	
	public void enterQuoteNameInQuoteTextbox(String QuoteName) {
		
		String Currenntday = new DateTimeUtils().dateWithSpecificFormatt("dd/MM/yyyy HH:mm:ss");
		quoteNameWithCurrenntday = QuoteName.concat("-").concat(Currenntday);
		enterText(quoteName, quoteNameWithCurrenntday);
		log.info("Quote name is entered as " + quoteNameWithCurrenntday);
		
	}
	
	public void getSupplierNameAndTotalAmountOnBasketPage() {
		
		supplierNameBasketPage = getText(getSupplierNameBasketPage);
		totalPriceBasketPage = getText(getTotalPriceBasketPage);
		totalPriceBasketPage = totalPriceBasketPage.replace(" inc. VAT", "");
		totalPriceBasketPage = totalPriceBasketPage.trim();
		log.info(" Supplier name " +supplierNameBasketPage+ " and Total price " +totalPriceBasketPage+ " in basket page ");
		
	}
	
	public void validateFirmAndIndicativeQuoteLabel() {
		
		String firmQuoteLabelActual = "To raise a Purchase Order using an external system or process, or to seek offline approval and then return to The Purchasing Platform to convert to an order later. Firm quotes are valid for 30 calendar days, suppliers are made aware of a pending order and will be holding the price on your behalf. The expectation is that firm quotes will generate purchase orders in all but the most exceptional of cases, when you must return to this platform and cancel your quote. If you are simply seeking indicative pricing, please request an indicative quote in the first instance.";
		String indicativeQuoteLabelActual = "For budgetary approval prior to re-quote, or information only. Indicative quotes are not valid for purchase. You will need to return to The Purchasing Platform, and rerun a firm quote or create a direct purchase order (if available in your user profile and allowed by your organisation’s procurement policy) to purchase.";
		
		String firmQuoteLabelUI = getText(getFirmQuoteLabel);
		String indicativeQuoteLabelUI = getText(getIncicativeQuoteLabel);
		assertEquals(firmQuoteLabelActual, firmQuoteLabelUI);
		assertEquals(indicativeQuoteLabelActual, indicativeQuoteLabelUI);
		
		log.info(" Validate Firm and Indicative quote label in quote page ");
		
	}
	
	public void getNewQuoteRefrenceNo() {
		
		firstRowQuoteNo=getText(getFirstRowQuoteNo);
		log.info("New Quote refrence no : " + firstRowQuoteNo + " is validated");
	}
	
	public void enterQuoteNoInQuoteReferenceTextbox() {
		
		enterText(quoteReference, firstRowQuoteNo);
		
	}

	public void enterQuoteNoPartiallyInQuoteReferenceTextbox() {
		
		String partialQuoteNo = firstRowQuoteNo.substring(1, 7) ;
		enterText(quoteReference, partialQuoteNo);
		log.info("Partial Quote refrence no : " + partialQuoteNo + " is entered in Quote reference search field");

	}	
	
	public void validateQuoteTableColumnHeaders() {
		
		int allValues = getElementsSizeByXpath(manageQuoteColumHeaderSize,driver);
		//Get the length
	    System.out.println(allValues);
	    //Creating arraylist with the column headers  
	    ArrayList<String> manageQuotePageTableHeaders=new ArrayList<String>();
	    //Adding object in arraylist 
	    manageQuotePageTableHeaders.add("Quote reference");
	    manageQuotePageTableHeaders.add("Quote name");    
	    manageQuotePageTableHeaders.add("Date quote raised");
	    manageQuotePageTableHeaders.add("Supplier name");
	    manageQuotePageTableHeaders.add("Type");
	    manageQuotePageTableHeaders.add("Expiry date");
	    manageQuotePageTableHeaders.add("Total value inc. VAT");
	    manageQuotePageTableHeaders.add("Status");

	    // Loop to print and validate Manage quote table headers one by one
	    for (int j = 1; j <= allValues; j++) {
	    	System.out.println(getTextXpath("//table[@class='govuk-table bat-quotes__table']/thead/tr/th["+j+"]"));
	    	System.out.println(manageQuotePageTableHeaders.get(j-1));
	    	System.out.println(getTextXpath("//table[@class='govuk-table bat-quotes__table']/thead/tr/th["+j+"]").equals(manageQuotePageTableHeaders.get(j-1)));
	    	assertEquals(getTextXpath("//table[@class='govuk-table bat-quotes__table']/thead/tr/th["+j+"]"), manageQuotePageTableHeaders.get(j-1));
	
	    }
	
	    log.info("Validated Manage Quotes Column Header on Manage quotes Page");
		
	}
	
	
	public void validateNewQuoteDetails(String quoteType, String statusAccepted) {
		
		String Currenntday = new DateTimeUtils().dateWithSpecificFormatt("dd/MM/yyyy");
		String supplierNameUI = getText(getFirstRowSupplierName);
		String[] splitSupplierName = supplierNameUI.split("\\r?\\n");
		String actualSupplierNameUI=splitSupplierName[0];
		
		String[] splitTotalPriceBasketPage = totalPriceBasketPage.split("\\s+");
		actualTotalPriceBasketPage=splitTotalPriceBasketPage[0];
		assertEquals(getText(getFirstRowQuoteNo), firstRowQuoteNo);
		assertEquals(getText(getFirstRowQuoteName), quoteNameWithCurrenntday);
	//	assertEquals(getText(getFirstRowdateQuoteRaised), Currenntday);
		assertEquals(actualSupplierNameUI, supplierNameBasketPage);
		assertEquals(getText(getFirstRowType), quoteType);
		//assertEquals(getText(getFirstRowExpiryDate), );
		assertEquals(getText(getFirstRowStatus), statusAccepted);
	//	if(quoteType.equals("Firm")) {
	//		assertEquals(getText(getFirstRowTotalValueIncVAT), actualTotalPriceBasketPage);
	//	}
				
	    log.info("Validated Manage Quotes Column Header on Manage quotes Page");
		
	}
	
	
	public void validateIndicativeQuoteMessageAndStatus(String message, String statusAccepted) {
		
		String indicativeStatusUI = getText(getIncicativeStatus);
		String[] splitindicativeStatusUI = indicativeStatusUI.split("Status: ");
		String actualIndicativeStatusUI=splitindicativeStatusUI[1];
		
		assertEquals(actualIndicativeStatusUI, statusAccepted);
		assertEquals(getText(getIncicativeMessage), message);
	   log.info("Validated Indicative page Status and Message : "+ message);
		
	}
	
	
	public void validateNewQuoteDetailsOnAdminUI(String statusAccepted) {
		
		String Currenntday = new DateTimeUtils().dateWithSpecificFormatt("dd/MM/yyyy");
		
		assertEquals(getText(getFirstRowQuoteNoAdminUI), firstRowQuoteNo);
		assertEquals(getText(getDateQuoteRaisedAdminUI), Currenntday);
		assertEquals(getText(getTotalValueIncVATAdminUI), actualTotalPriceBasketPage);
		//assertEquals(getText(getExpiryDateAdminUI), );
		assertEquals(getText(getQuoteStatusAdminUI), statusAccepted);
		assertEquals(getText(getQuoteStatusRejectedI), "Reject");
		
	    log.info("Validated Manage Quotes refrence "+getFirstRowQuoteNoAdminUI+" details on Admin UI");
		
	}
	
	public void validateClearMyBasketAndAddThisItemButton() {
		System.out.println(getText(clearMyBasketTextMsg));
		assertEquals(getText(clearMyBasketTextMsg), configReaderObj.get("clearMyTextBtnTextMsg"));
		assertTrue("Clear my basket and add these items button is present on firm quotes ", existsElement(clearMyTextBtnStr));
		log.info("Validated 'Clear my basket and add these items' button and text message is present on buyers UI firm quotes");
		
	}
	
	public void validateAddToBasketButton() {
		
		assertTrue("Validated 'Add to basket' button is present on buyers UI indicative quotes ", existsElement(addtoBasketBtnOnIndicativeStr));
		log.info("Validated 'Add to basket' button is present on buyers UI indicative quotes");
		
	}
	
	public void validateSuccessMessageInBasket(String successMessage) {
		
		assertEquals(getText(successMsg), successMessage);
		log.info("Validated success message: "+ successMessage);
		
	}
	
  public void validateTheErrorMessageOfInsufficientStockOnQuotes() {
	  
		System.out.println(getText(errorMsgInsufficientStockQuotes));
		assertEquals(getText(errorMsgInsufficientStockQuotes), configReaderObj.get("errorMsgInsufficientStockQuotePage"));
		log.info("Validated success error message on quotes page: "+ configReaderObj.get("errorMsgInsufficientStockQuotePage"));
		
	}
	
	public void validateRejectQuotePage(String rejectQuotePageTitle) {
		
		String rejectQuotePageTitleWithQuoteNo = rejectQuotePageTitle.concat(" ").concat(firstRowQuoteNo);
		assertEquals(getText(rejectQuotePageTitleAdminUI), rejectQuotePageTitleWithQuoteNo);
		log.info("Validated Reject Quotes page title : "+rejectQuotePageTitleAdminUI);
		
	}
	
	public void validateManageQuotePage(String manageQuotePageTitle) {
		
		String manageQuotePageTitleWithQuoteNo = manageQuotePageTitle.concat(" / ").concat(firstRowQuoteNo);
		assertEquals(getText(rejectQuotePageTitleAdminUI), manageQuotePageTitleWithQuoteNo);
		log.info("Validated Manage Quotes page title : "+rejectQuotePageTitleAdminUI);
		
	}
	
	public void validateRejectQuotePageTextBoxRejectAndCancleButton(String rejectButton, String cancleButton) {
		
		assertTrue("Reject Reason textbox exist ", existsElement(rejectReasonTextboxRejectQuotePageAdminUI));
		assertTrue("Reject Reason textbox exist ", existsElement(rejectButtonRejectQuotePageAdminUI));
		assertTrue("Reject Reason textbox exist ", existsElement(cancleButtonRejectQuotePageAdminUI));
		log.info("Validated Reject Quotes page Reject reason Textbox, Reject and cancle Button ");
		
	}
	
	public void enterRejectReasonInTextBox(String rejectreason) {
		String Currenntday = new DateTimeUtils().dateWithSpecificFormatt("dd/MM/yyyy HH:mm:ss");
		rejectreasonWithCurrenntday = rejectreason.concat("-").concat(Currenntday);
		enterText(rejectReasonTextboxRejectQuotePageAdminUIWebElement, rejectreasonWithCurrenntday);
		log.info("Quote name is entered as " + rejectreasonWithCurrenntday);
		
	}
	
	public void validateStatusAndRejectReasonOnManageQuote(String rejectStatus) {
		
		 String rejectQuoteStatusInAdminUIStr = getText(rejectQuoteStatusInAdminUI);
		 String[] splitRejectQuoteStatusInAdminUIStr = rejectQuoteStatusInAdminUIStr.split("\\r?\\n");
		 String[] splitRejectQuoteStatusInAdminUIStatus = splitRejectQuoteStatusInAdminUIStr[1].split("\\s+");
		 String actualSplitRejectQuoteStatusInAdminUIStatus = splitRejectQuoteStatusInAdminUIStatus[1];
		 
		 String[] splitRejectQuoteStatusInAdminUIRejectReason = splitRejectQuoteStatusInAdminUIStr[3].split("Reject reason: ");
		 String actualSplitRejectQuoteStatusInAdminUIRejectReason = splitRejectQuoteStatusInAdminUIRejectReason[1];
		 
		assertEquals(actualSplitRejectQuoteStatusInAdminUIStatus, rejectStatus);
		assertEquals(actualSplitRejectQuoteStatusInAdminUIRejectReason, rejectreasonWithCurrenntday);
		log.info("Validated reject quote Status "+actualSplitRejectQuoteStatusInAdminUIStatus+ " and Reason "+actualSplitRejectQuoteStatusInAdminUIRejectReason +" on Manage Quote page");
		
	}
	
	public void validateNewQuoteRejectedStatusOnQuotesPage(String statusRejected) {
		
		assertEquals(getText(getQuoteStatusAdminUI), statusRejected);
		log.info("Validated Quotes status as "+statusRejected+" in Quotes page");
		
	}
	
	public void enterQuoteNoInQuoteReferenceTextboxAdminUI() {
		
		enterText(quoteReferenceAdminUI, firstRowQuoteNo);
		
	}
	
	public String getFirmQuoteElement() {
		return firmQuote;
	}
	
	public String getIndicativeQuoteElement() {
		return indicativeQuote;
	}
	
	public String getRaiseQuoteLink() {
		return raiseQuote ;
	}
	
	public WebElement getFirstRowQuoteNoLink() {
		return getFirstRowQuoteNo ;
	}
	
	public String getQuoteLinkAdminPanel() {
		return quoteLinkAdminPanel;
	}
	
	public WebElement getSearchButtonAdminPanel() {
		return searchButtonAdminPanel;
	}
	
	public WebElement getRejectLinkAdminUI() {
		return getQuoteStatusRejectedI;
	}
	
	public String getRejectButtonAdminUI() {
		return rejectButtonRejectQuotePageAdminUI;
	}

	public void enterGivenQuoteNoInQuoteReferenceTextbox(String quoteRef) {
		
		enterText(quoteReference, quoteRef);
		
	}	
	
	public void validateNoResultFoundQuoteBuyerUI() {
		// write code to validate No Result found message on search quote
		String actualErrorMsg = getTextXpath(errMsgXpath);
		String expectedErrorMsg = "No results found.";
		assertEquals(actualErrorMsg,expectedErrorMsg );
		log.info("Error message : " + expectedErrorMsg + " is validated");
	}

	public void enterQuoteNoPartiallyInQuoteReferenceTextboxAdminUI() {
		
		String partialQuoteNo = firstRowQuoteNo.substring(1, 7) ;
		enterText(quoteRefAdminUI, partialQuoteNo);
		log.info("Partial Quote refrence no : " + partialQuoteNo + " is entered in Quote reference search field");

	}	
	
	public void enterGivenQuoteNoInQuoteReferenceTextboxAdminUI(String quoteRef) {
		
		enterText(quoteRefAdminUI, quoteRef);
		log.info("Invalid Quote refrence no : " + quoteRef + " is entered in Quote reference search field in Admin UI");
	}

	public void validateNoResultFoundQuoteAdminUI() {
		// write code to validate No Result found message on search quote
		String actualErrorMsg = getTextXpath(errMsgQuoteNotFoundSupplierView);
		String expectedErrorMsg = "No Quote found";
		assertEquals(actualErrorMsg,expectedErrorMsg );
		log.info("Error message : " + expectedErrorMsg + " is validated on Admin UI");
	}
	
	public void validateAllQuoteOnBuyersUI(int AllQuotesCount,Response jsonResponse ) {
		//Below code is to get the total page on Quotes page		
		int allQuotesQtyApi = apiBase.getvaluefromresponseAsInterger("meta.total_count",jsonResponse);
		String paginationCountQuotes = getAttributeValue(paginationCountQuotesPage,"arial-label");
		String[] paginationCountQuotesSplit = paginationCountQuotes.split("of");
		String paginationCountLast = paginationCountQuotesSplit[1].replaceAll("[^a-zA-Z0-9]", " ").replaceAll("\\s", "");
		int paginationCountLastInt = Integer.parseInt(paginationCountLast);
		//Below For loop is to navigates on the available pages
		int count=0;
		int allQuotesCount=0;
		int totalCount=0;
		for(int i=0;i<paginationCountLastInt;i++) {
			
			if(count>=1) {
			clickElement(nextLinkQuotesPage);
			}
			
			allQuotesCount = getElementsSizeByXpath(getAllQuoteOnBuyersUI,driver);
			totalCount=allQuotesCount+totalCount;
			count++;
			//Below For loop is to validate the all the Quotes no from UI to API
			for(int j=0;j<allQuotesCount;j++ ) {
				
				String allQuotesQty = apiBase.getvaluefromresponse("data["+j+"].attributes.number",jsonResponse);
				String getQuoteNo = getTextXpath("//table[@class='govuk-table bat-quotes__table']/tbody/tr["+(j+1)+"]/td/a");
				if(allQuotesQty.equals(getQuoteNo)){
					
				}else {
					assertFalse("All Quotes are not displayed on Quotes table", false);
				}
			}
			count++;
			
		}
		
		assertEquals("All Quotes are displayed on Quotes table on Buyers UI with total count: "+String.valueOf(totalCount),"All Quotes are displayed on Quotes table on Buyers UI with total count: "+String.valueOf(allQuotesQtyApi));
		log.info("All Quotes are displayed on Quotes table");
		System.out.println("All Page Count On UI: " + paginationCountQuotes);
	}
	
	
	public void validateAllQuoteOnAdminUI(int AllQuotesCount,Response jsonResponse ) {
		int allQuotesQtyApi = apiBase.getvaluefromresponseAsInterger("meta.total_count",jsonResponse);
		clickElement(lastPageNavigatorAdminUI);
		String paginationCountLast=getText(paginationCountQuotesPageAdminUI);
		clickElement(firstPageNavigatorAdminUI);
		int paginationCountLastInt = Integer.parseInt(paginationCountLast);
		int count=0;
		int allQuotesCount=0;
		int totalCount=0;
		for(int i=0;i<paginationCountLastInt;i++) {
			
			if(count>=1) {
			clickElement(nextLinkQuotesPageAdminUI);
			}
			
			allQuotesCount= getElementsSizeByXpath(getAllQuoteOnAdminUI,driver);
			totalCount=allQuotesCount+totalCount;
			count++;
		}
		String totalCountStringUI = String.valueOf(totalCount);
		assertEquals("All Quotes are displayed on Quotes table in Admin UI with total count: "+totalCountStringUI,"All Quotes are displayed on Quotes table in Admin UI with total count: "+String.valueOf(allQuotesQtyApi));
		log.info("All Quotes are displayed on Quotes table and total count is :"+totalCountStringUI);
	}
	

	public String getSupplierLinkAdminUI() {
		return supplierLinkAdminPanel;
	}
	
	public WebElement getClearMyTextBtnt() {
		return clearMyTextBtn;
	}
	
	
	public WebElement addtoBasketBtnOnIndicative() {
		return addtoBasketBtnOnIndicative;
	}
	
	
	public WebElement saveAndContinueBtnOnPayentCheckout() {
		return saveAndContinueBtnOnPayentCheckout;
	}
	
	public WebElement placeOrderCheckout() {
		return placeOrderCheckout;
	}
	
	
}

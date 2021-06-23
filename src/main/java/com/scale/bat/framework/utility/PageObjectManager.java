package com.scale.bat.framework.utility;

import org.openqa.selenium.WebDriver;

import com.scale.bat.businessPages.BuyersUIBasketPage;
import com.scale.bat.businessPages.BuyersUIMyListPage;
import com.scale.bat.businessPages.BuyersUIPDPPage;
import com.scale.bat.businessPages.BuyersUIQuotesPage;
import com.scale.bat.businessPages.BuyersUIpage;
import com.scale.bat.businessPages.CCSHomePage;
import com.scale.bat.businessPages.CheckoutPage;
import com.scale.bat.businessPages.LogInAdminPanel;
import com.scale.bat.businessPages.ProductCatalogueListPage;
import com.scale.bat.businessPages.ProductCataloguePage;
import com.scale.bat.businessPages.ProductDetailPage;
import com.scale.bat.businessPages.SupplierPage;
import com.scale.bat.context.TestContext;
import com.scale.bat.framework.utility.API.Auth;

import cucumber.api.Scenario;

public class PageObjectManager {
	private WebDriver driver;
	private Scenario scenario;
	private LogInAdminPanel logInAdminPanel;
	private CCSHomePage ccsHomePage;
	private ProductCatalogueListPage productCatalogueListPage;
	private ProductCataloguePage productCataloguePage;
	private ProductDetailPage productDetailsPage;
	private BuyersUIpage buyersUIpage;
	private BuyersUIPDPPage buyersUIPDPPage;
	private SupplierPage supplierPage;
	private TakeScreenShot tekeScreenShot;
	private BuyersUIPDPPage buyersUIPage;
	private BuyersUIMyListPage buyersUIMyListPage;
	private BuyersUIBasketPage buyersUIBasketPage;
	private BuyersUIQuotesPage buyersUIQuotesPage;
	private CheckoutPage checkoutPage;
	private Auth auth;
	
	public PageObjectManager(WebDriver driver, Scenario scenario) {
		this.driver = driver;
		this.scenario = scenario;
	}
	
	public LogInAdminPanel getLogInAdminPanel() {
		return logInAdminPanel == null ? logInAdminPanel = new LogInAdminPanel(driver,scenario) : logInAdminPanel;
	}
	
	public CCSHomePage getCCSHomePage() {
		return ccsHomePage == null ? ccsHomePage = new CCSHomePage(driver,scenario) : ccsHomePage;
	}
	
	public ProductCatalogueListPage getProductCatalogueListPage() {
		return productCatalogueListPage == null ? productCatalogueListPage = new ProductCatalogueListPage(driver,scenario) : productCatalogueListPage;
	}
	
	public ProductCataloguePage getProductCataloguePage() {
		return productCataloguePage == null ? productCataloguePage = new ProductCataloguePage(driver,scenario) : productCataloguePage;
	}
	
	public ProductDetailPage getproductDetailsPage() {
		return productDetailsPage == null ? productDetailsPage = new ProductDetailPage(driver,scenario) : productDetailsPage;
	}
	
	public BuyersUIpage getBuyersUIpage() {
		return buyersUIpage == null ? buyersUIpage = new BuyersUIpage(driver,scenario) : buyersUIpage;
	}
	
	public BuyersUIMyListPage getBuyersUIMyListpage() {
		return buyersUIMyListPage == null ? buyersUIMyListPage = new BuyersUIMyListPage(driver,scenario) : buyersUIMyListPage;
	}
	
	public BuyersUIBasketPage getBuyersUIBasketpage() {
		return buyersUIBasketPage == null ? buyersUIBasketPage = new BuyersUIBasketPage(driver,scenario) : buyersUIBasketPage;
	}
	
	public BuyersUIPDPPage getBuyersUIPDPPage() {
		return buyersUIPDPPage == null ? buyersUIPDPPage = new BuyersUIPDPPage(driver,scenario) : buyersUIPDPPage;
	}
	
	public SupplierPage getSupplierPage() {
		return supplierPage == null ? supplierPage = new SupplierPage(driver,scenario) : supplierPage;
	}
	
	public TakeScreenShot getScreeShot() {
		return tekeScreenShot == null ? tekeScreenShot = new TakeScreenShot(driver,scenario) : tekeScreenShot;
	}
	
	//API
	/*public Auth getAuth() {
		return auth == null ? auth = new Auth(driver,scenario) : auth;
	}*/
	
	public BuyersUIQuotesPage getBuyersUIQuotespage() {
		
		return buyersUIQuotesPage == null ? buyersUIQuotesPage = new BuyersUIQuotesPage(driver,scenario) : buyersUIQuotesPage;
	}
	
	
	public CheckoutPage getCheckoutpage() {
		return checkoutPage == null ? checkoutPage = new CheckoutPage(driver,scenario) : checkoutPage;
	}
	
}
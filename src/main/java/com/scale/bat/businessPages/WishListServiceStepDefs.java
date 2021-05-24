package com.scale.bat.businessPages;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.scale.bat.businessPages.BuyersUIpage;
import com.scale.bat.context.ScenarioContext;
import com.scale.bat.context.TestContext;
import com.scale.bat.framework.utility.ConfigurationReader;
import com.scale.bat.framework.utility.JsonParser;
import com.scale.bat.framework.utility.Log;
import com.scale.bat.framework.utility.PageObjectManager;
import com.scale.bat.framework.utility.API.APIBase;
import com.scale.bat.framework.utility.API.Auth;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class WishListServiceStepDefs {
	
	public Auth auth=new Auth();
	public APIBase apibase=new APIBase();
	private Logger log = Log.getLogger(WishListServiceStepDefs.class);
	public String ordertoken;
	RequestSpecification requestspec;
	String strcontentype;
	public String access_hash;
	public int statusCode;
	public static Response jsonResponse;
	public static Response jsonAllProductsResponse=null;
	String firstProduct;
	String firstProductVariantID;
	String secondProductVariantID;
	String thirdProductVariantID;
	public static int allQuotesQtyApi;
	String Supp1FirstProductVariantID;
	String Supp1SecondProductVariantID;
	String Supp1ThirdProductVariantID;
	String Supp2FirstProductVariantID;
	String Supp2SecondProductVariantID;
	
	
	
	
	@Given ("user get the Auth token")
	public void user_get_the_Auth_token() {
		
		ordertoken=auth.Authenticaion();
		System.out.println("1 ordertoken: " + ordertoken);
	}

		
	@Then("set the header")
	public void set_the_header() {
	    
	}

	@Then("user access the webservice of GetWishList")
	public void user_access_the_webservice_of_GetWishList() {
		jsonResponse=apibase.getRequest("/api/v2/storefront/wishlists/default?include=wished_products,wished_products.variant,wished_products.variant.product,wished_products.variant.product.manufacturer");
		access_hash = apibase.getvaluefromresponse("data.attributes.access_hash",jsonResponse);
        //System.out.println("access_hash: "+ access_hash);
     
	}
	
		
	@Given("user add the WishList")
	public void user_add_the_WishList() throws FileNotFoundException {
		
		String strjson ="{\"wished_product\":{\"variant_id\":"+firstProductVariantID+",\"quantity\":1,\"remark\":\"test\"}}";
		apibase.Requestpost("/api/v2/storefront/wishlists/{access_hash}/wished_products", strjson, access_hash);
		log.info("Product is added to My List page successfully through API");
	}

	@Then("user retrive the value from the response")
	public void user_retrive_the_value_from_the_response() {
	    
	}
		
	@Given("deletes the products from the WishList")
	public void deletes_the_products_from_the_WishList() {
		
		apibase.Requestdel("/api/v2/storefront/wishlists/{access_hash}", access_hash);
	}
	
	
	@And("user gets all the available products list")
	public void user_gets_all_the_available_products_list() {
	    
		jsonResponse = apibase.getRequest("/api/v2/storefront/products?filter[in_stock]=true&sort_by=available_on");
		String anc = jsonResponse.getBody().asString();
		//System.out.println(anc);
		jsonAllProductsResponse=jsonResponse;
		statusCode = apibase.getStatusCode(jsonResponse);
	} 
	
	@Given("User gets products IDs for supplier{int} and supplier{int}")
	public void user_gets_products_IDs_for_supplier_and_supplier(Integer int1, Integer int2) {
	    
		//Get Supplier1 Product1 Product ID
		jsonResponse = apibase.getRequest("/api/v2/storefront/products?filter[in_stock]=true&sort_by=available_on&filter[name]=242795-B21&filter[manufacturers]=15");
		//String anc = jsonResponse.getBody().asString();
		//System.out.println(anc);
		String Sup1firstProduct = apibase.getvaluefromresponse("data[0].id");
		//System.out.println("Supplier1 Product1 ID: "+ Sup1firstProduct);
		//Get Variant ID
		jsonResponse = apibase.getRequest("/api/v2/storefront/products/"+Sup1firstProduct+"?include=default_variant,default_variant.vendor,variants,variants.option_values,documents,variants.delivery_charges,variants.vendor,variants.catalog,option_types,product_properties,images,manufacturer");
		statusCode = apibase.getStatusCode(jsonResponse);
		Supp1FirstProductVariantID = apibase.getvaluefromresponse("data.relationships.default_variant.data.id");
		
		
		//Get Supplier1 Product2 Product ID
		jsonResponse = apibase.getRequest("/api/v2/storefront/products?filter[in_stock]=true&sort_by=available_on&filter[name]=198425-005&filter[manufacturers]=15");
		String Sup1SecondProduct = apibase.getvaluefromresponse("data[0].id");
		//Get Variant ID
		jsonResponse = apibase.getRequest("/api/v2/storefront/products/"+Sup1SecondProduct+"?include=default_variant,default_variant.vendor,variants,variants.option_values,documents,variants.delivery_charges,variants.vendor,variants.catalog,option_types,product_properties,images,manufacturer");
		statusCode = apibase.getStatusCode(jsonResponse);
		Supp1SecondProductVariantID = apibase.getvaluefromresponse("data.relationships.default_variant.data.id");
		
		
		//Get Supplier3 Product3 Product ID
		jsonResponse = apibase.getRequest("/api/v2/storefront/products?filter[in_stock]=true&sort_by=available_on&filter[name]=194753-001&filter[manufacturers]=15");
		String Sup1ThirdProduct = apibase.getvaluefromresponse("data[0].id");
		//Get Variant ID
		jsonResponse = apibase.getRequest("/api/v2/storefront/products/"+Sup1ThirdProduct+"?include=default_variant,default_variant.vendor,variants,variants.option_values,documents,variants.delivery_charges,variants.vendor,variants.catalog,option_types,product_properties,images,manufacturer");
		statusCode = apibase.getStatusCode(jsonResponse);
		Supp1ThirdProductVariantID = apibase.getvaluefromresponse("data.relationships.default_variant.data.id");
			
		
		//Get Supplier2 Product1 Product ID
		jsonResponse = apibase.getRequest("/api/v2/storefront/products?filter[in_stock]=true&sort_by=available_on&filter[name]=189649-001&filter[manufacturers]=29");
		String Sup2FirstProduct = apibase.getvaluefromresponse("data[0].id");
		//Get Variant ID
		jsonResponse = apibase.getRequest("/api/v2/storefront/products/"+Sup2FirstProduct+"?include=default_variant,default_variant.vendor,variants,variants.option_values,documents,variants.delivery_charges,variants.vendor,variants.catalog,option_types,product_properties,images,manufacturer");
		statusCode = apibase.getStatusCode(jsonResponse);
		Supp2FirstProductVariantID = apibase.getvaluefromresponse("data.relationships.default_variant.data.id");
		
		
		//Get Supplier2 Product2 Product ID
		jsonResponse = apibase.getRequest("/api/v2/storefront/products?filter[in_stock]=true&sort_by=available_on&filter[name]=199506-001&filter[manufacturers]=15");
		String Sup2SecondProduct = apibase.getvaluefromresponse("data[0].id");
		//Get Variant ID
		jsonResponse = apibase.getRequest("/api/v2/storefront/products/"+Sup2SecondProduct+"?include=default_variant,default_variant.vendor,variants,variants.option_values,documents,variants.delivery_charges,variants.vendor,variants.catalog,option_types,product_properties,images,manufacturer");
		statusCode = apibase.getStatusCode(jsonResponse);
		Supp2SecondProductVariantID = apibase.getvaluefromresponse("data.relationships.default_variant.data.id");
		
		
	}

	@Given("identify products which needs to be add in the list.")
	public void identify_products_which_needs_to_be_add_in_the_list() {
	    
		firstProduct = apibase.getvaluefromresponse("data[0].id");
	}

	@Given("get the products variant ids")
	public void get_the_products_variant_ids() {
		
		jsonResponse = apibase.getRequest("/api/v2/storefront/products/"+firstProduct+"?include=default_variant,default_variant.vendor,variants,variants.option_values,documents,variants.delivery_charges,variants.vendor,variants.catalog,option_types,product_properties,images,manufacturer");
		statusCode = apibase.getStatusCode(jsonResponse);
		firstProductVariantID = apibase.getvaluefromresponse("data.relationships.default_variant.data.id");
		
	}


	@Given("user clears the basket")
	public void user_clears_the_basket() {
	   
		apibase.Requestpatch("/api/v2/storefront/cart/empty");
	}

	@Given("user adds a product to basket")
	public void user_adds_a_product_to_basket() {
		
		String strjson ="{\"variant_id\":"+firstProductVariantID+",\"quantity\": 1}";
		apibase.Requestpost("/api/v2/storefront/cart/add_item", strjson);
	    
	}

	@Given("user gets the multile products varient ids")
	public void user_gets_the_multile_products_varient_ids() {
	   
		jsonResponse = apibase.getRequest("/api/v2/storefront/products/"+apibase.getvaluefromresponse("data[1].id", jsonAllProductsResponse)+"?include=default_variant,default_variant.vendor,variants,variants.option_values,documents,variants.delivery_charges,variants.vendor,variants.catalog,option_types,product_properties,images,manufacturer");
			
		secondProductVariantID = apibase.getvaluefromresponse("data.relationships.default_variant.data.id");
			
		jsonResponse = apibase.getRequest("/api/v2/storefront/products/"+apibase.getvaluefromresponse("data[2].id", jsonAllProductsResponse)+"?include=default_variant,default_variant.vendor,variants,variants.option_values,documents,variants.delivery_charges,variants.vendor,variants.catalog,option_types,product_properties,images,manufacturer");
		thirdProductVariantID = apibase.getvaluefromresponse("data.relationships.default_variant.data.id");
	}

	@Given("user adds the multiple products to WishList")
	public void user_adds_the_multiple_products_to_WishList() {
		
		String strjsonProduct1 ="{\"wished_product\":{\"variant_id\":"+secondProductVariantID+",\"quantity\":1,\"remark\":\"test\"}}";
		Response strjsonProduct1Status = apibase.Requestpost("/api/v2/storefront/wishlists/{access_hash}/wished_products", strjsonProduct1, access_hash);
		
		String strjsonProduct2 ="{\"wished_product\":{\"variant_id\":"+thirdProductVariantID+",\"quantity\":1,\"remark\":\"test\"}}";
		Response strjsonProduct2Status=apibase.Requestpost("/api/v2/storefront/wishlists/{access_hash}/wished_products", strjsonProduct2, access_hash);
		System.out.println();
	}


	@Given("User get the total no of quotes available in manage quotes page on buyers UI")
	public void user_get_the_total_no_of_quotes_available_in_manage_quotes_page_on_buyers_UI() {
	    
		jsonResponse = apibase.getRequest("/api/v2/storefront/account/quotes?include=vendor");
		String anc = jsonResponse.getBody().asString();
		System.out.println(anc);
		allQuotesQtyApi= apibase.getvaluefromresponseAsInterger("meta.total_count");
		System.out.println(allQuotesQtyApi);
	}
	
	@Given("User adds two products from supplier{int} in to the basket with one having VAT{int} and second having VAT{int} percentage")
	public void user_adds_two_products_from_supplier_in_to_the_basket_with_one_having_VAT_and_second_having_VAT_percentage(Integer int1, Integer int2, Integer int3) {
	    
		//Add Supplier1 Product1 with VAT 20%
		String strjson ="{\"variant_id\":"+Supp1FirstProductVariantID+",\"quantity\": 1}";
		apibase.Requestpost("/api/v2/storefront/cart/add_item", strjson);
		
		//Add Supplier1 Product2 with VAT 0%
		String strjson1 ="{\"variant_id\":"+Supp1SecondProductVariantID+",\"quantity\": 1}";
		apibase.Requestpost("/api/v2/storefront/cart/add_item", strjson1);
		
	}
	
	@Given("User adds two products from supplier{int} in to the basket with both having VAT{int} percentage")
	public void user_adds_two_products_from_supplier_in_to_the_basket_with_both_having_VAT_percentage(Integer int1, Integer int2) {
	    
		//Add Supplier1 Product1 with VAT 20%
		String strjson ="{\"variant_id\":"+Supp1FirstProductVariantID+",\"quantity\": 1}";
		apibase.Requestpost("/api/v2/storefront/cart/add_item", strjson);
				
		//Add Supplier1 Product3 with VAT 20%
		String strjson1 ="{\"variant_id\":"+Supp1ThirdProductVariantID+",\"quantity\": 1}";
		apibase.Requestpost("/api/v2/storefront/cart/add_item", strjson1);

	}
	
	@Given("User adds product{int} with VAT{int} from supplier{int} and product{int} with VAT{int} of supplier{int} in to the basket")
	public void user_adds_product_with_VAT_from_supplier_and_product_with_VAT_of_supplier_in_to_the_basket(Integer int1, Integer int2, Integer int3, Integer int4, Integer int5, Integer int6) {
	    
		//Add Supplier1 Product1 with VAT 20%
		String strjson ="{\"variant_id\":"+Supp1SecondProductVariantID+",\"quantity\": 1}";
		apibase.Requestpost("/api/v2/storefront/cart/add_item", strjson);
						
		//Add Supplier2 Product3 with VAT 20%
		String strjson1 ="{\"variant_id\":"+Supp2FirstProductVariantID+",\"quantity\": 1}";
		apibase.Requestpost("/api/v2/storefront/cart/add_item", strjson1);

	}
	
	
	@Then("User adds the supplier{int} product with VAT{int} into the basket")
	public void user_adds_the_supplier_product_with_VAT_into_the_basket(Integer int1, Integer int2) {
	    
		//Add Supplier2 Product3 with VAT 20%
		String strjson1 ="{\"variant_id\":"+Supp2FirstProductVariantID+",\"quantity\": 1}";
		apibase.Requestpost("/api/v2/storefront/cart/add_item", strjson1);
	}



	

}

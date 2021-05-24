package com.scale.bat.framework.utility.API;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import com.scale.bat.framework.utility.ConfigurationReader;

import cucumber.api.Scenario;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Auth extends APIBase {

    private ConfigurationReader configreader = new ConfigurationReader();
    public String ordertoken;
    public String access_hash;
    private WebDriver driver;
      
    public String Authenticaion() {
        
        String username = configreader.apiUserName("supplier");  //.get("ccs.admin.panel.apiusername.userrole.supplier");
        String password = configreader.apiUserPassword("supplier"); //.get("ccs.admin.panel.apipassword.userrole.supplier");     
        String obj = "grant_type=password&username="+username+"&password="+password;
        //System.out.println(obj);
       
        Response response = null;
        response = RestAssured.given().spec(setBaseURI()).contentType("application/x-www-form-urlencoded").body(obj).when().post("/spree_oauth/token");
        // response.prettyPrint();
        //System.out.println("response"+response.statusCode());
        ordertoken = response.then().extract().path("access_token");
        return ordertoken;
    }

}
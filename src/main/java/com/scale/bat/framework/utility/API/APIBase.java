package com.scale.bat.framework.utility.API;

import cucumber.api.Scenario;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import com.scale.bat.context.ScenarioContext;
import com.scale.bat.framework.utility.ConfigurationReader;
import com.scale.bat.framework.utility.Log;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import java.io.File;
import java.io.FileInputStream;;

public class APIBase extends ConfigurationReader {

    public Response response;
    private RequestSpecification request;
    private ResponseSpecification responsespec;
    public ValidatableResponse vresponse;
    private Scenario scenario;
    private ScenarioContext scenarioContext;
    ConfigurationReader configread = new ConfigurationReader();
    private Logger log = Log.getLogger(APIBase.class);
    public String access_hash;

    public RequestSpecification setBaseURI() {
    	
    	//System.out.println("inside");
    	// 1 point
    	//setting basic url link
        RequestSpecification  requestspec = new RequestSpecBuilder().setBaseUri(configread.get("BaseURL")).build();
        return requestspec;
    }
    // 2 point (Setting Request/Response application/json
    public String setContentType() {
        String strcontentype = configread.get("content_type");
        return strcontentype;
    }

    public Response getRequest(String URL) {
        response=null;
        response = RestAssured.given().spec(setBaseURI()).header("Authorization", "Bearer "+new Auth().Authenticaion()).contentType(setContentType()).get(URL);
        //log.info(response.prettyPrint().toString());
        return response;
    }
    
    
    public Response Requestpost(String URL, File filepath,String ordertoken){
            response=null;
            String token=null;
            response = RestAssured.given().spec(setBaseURI()).header("Authorization", "Bearer "+new Auth().Authenticaion()).contentType(setContentType())
                    .when().post(URL);
            scenario.write(response.toString());
        return response;
    }
    
    public Response Requestpost(String URL, String jsonstring){
        response=null;
        String token=null;
        response = RestAssured.given().spec(setBaseURI()).header("Authorization", "Bearer "+new Auth().Authenticaion()).contentType(setContentType()).body(jsonstring).when().post(URL);
        //response = RestAssured.given().spec(setBaseURI()).header("Authorization", "ordertoken "+new Auth().Authenticaion()).contentType(setContentType()).body(jsonstring).when().post(URL);
        System.out.println("RequestDelete response:== " + response.getStatusCode());
        response.prettyPrint();
        return response;
    }
    
    public Response Requestpost(String URL, String jsonstring, String param){
    	
        response=RestAssured.given().spec(setBaseURI()).header("Authorization", "Bearer "+new Auth().Authenticaion()).contentType(setContentType()).pathParams("access_hash",param).body(jsonstring).when().post(URL);
       //scenario.write(response.toString());
      System.out.println("RequestDelete response:== " + response.getStatusCode());
        return response;
   }

    
    public Response Requestdel(String URL, File filepath){
        response=null;
        response=RestAssured.given().spec(setBaseURI()).contentType(setContentType()).body(filepath).header("Authorization", "Bearer "+new Auth().Authenticaion())
                .when().delete(URL);
        scenario.write(response.toString());
        return response;
    }
    
    public Response Requestdel(String URL, String param){
        response=null;
        response=RestAssured.given().spec(setBaseURI()).header("Authorization", "Bearer "+new Auth().Authenticaion())
        		.contentType(setContentType()).pathParams("access_hash",param).when().delete(URL);
        //scenario.write(response.toString());
          //System.out.println("RequestDelete response:== " + response.getStatusCode());
        return response;
    }
    
    
    public Response Requestpatch(String URL) {
        response=null;
        response = RestAssured.given().spec(setBaseURI()).header("Authorization", "Bearer "+new Auth().Authenticaion()).contentType(setContentType()).patch(URL);
        //log.info(response.prettyPrint().toString());
        System.out.println("RequestPatch response:== " + response.getStatusCode());
        return response;
    }

    public String getvaluefromresponse(String path){
        String  strpathval = response.then().extract().path(path);
        return  strpathval;
    }
    
    public int getvaluefromresponseAsInterger(String path){
        int  strpathval = response.then().extract().path(path);
        return  strpathval;
    }
    
    public int getvaluefromresponseAsInterger(String path,Response response){
        int  strpathval = response.then().extract().path(path);
        return  strpathval;
    }
    
    public String getvaluefromresponse(String path, Response response){
        String  strpathval = response.then().extract().path(path);
        return  strpathval;
    }

    public int getStatusCode(){
        int responsecode =  response.then().extract().statusCode();
        return responsecode;
    }
    
    public int getStatusCode(Response response){
        int responsecode =  response.then().extract().statusCode();
        return responsecode;
    }

}
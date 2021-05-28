package com.scale.bat.framework.utility.API;

import cucumber.api.Scenario;
import io.restassured.response.Response;
import com.scale.bat.context.ScenarioContext;
import com.scale.bat.framework.utility.API.APIBase;
import com.scale.bat.framework.utility.ConfigurationReader;
import org.json.JSONObject;
import org.junit.Assert;
import static junit.framework.TestCase.fail;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
public class CommonValidations extends APIBase{
    protected Scenario scenario;
    protected ScenarioContext scenarioContext;
    protected APIBase apiUtil;
    protected JSONObject jsonObject;
    protected Response response;
    protected ConfigurationReader configurationReader = new ConfigurationReader();

   
    //Method to test basic authentication for username and Password


    private void responseTypeValidator(Response response) {
        if (response.contentType().contains("json") || response.contentType().contains("Json")) {
            jsonObject = new JSONObject(response.jsonPath().prettyPrint());
            //System.out.println((response.jsonPath().prettyPrint()));
        } else {
            scenario.write("Response type is not json, please verify the result in console");
            fail("Content type is not json");
        }
        scenario.write("Response for the above request is " + jsonObject.toString());
    }

    public void getResponse() {
        response = apiUtil.getRequest(scenarioContext.getContext("Endpoint"));
        //System.out.println("Response code is "+ response.prettyPrint());
        responseTypeValidator(response);
    }


    public void validateResponseCode() {
        scenario.write("Asserting the response code :- " + "Expected response code is" + scenarioContext.getContext("ExpectedStatus"));
        Assert.assertEquals(Integer.parseInt(scenarioContext.getContext("ExpectedStatus")), response.getStatusCode());
    }


    public void validateResponse_400() {
        scenario.write("Schema validation for response 400");
      //  response.then().assertThat().body(matchesJsonSchemaInClasspath("data/schema/valid400.json"));
        scenario.write("Asserting the presence of response message");
        Assert.assertTrue(jsonObject.has("message"));
        scenario.write("Asserting the message body, body should contain message " + scenarioContext.getContext("expectedMessage"));
        Assert.assertTrue(jsonObject.get("message").toString().contains(scenarioContext.getContext("expectedMessage")));
    }

    public void validateResponse_400(String schemaName) {
        scenario.write("Schema validation for " + schemaName + " - response 400");
      //  response.then().assertThat().body(matchesJsonSchemaInClasspath("data/schema/" + schemaName + ".json"));
        scenario.write("Asserting the presence of response message");
        Assert.assertTrue(jsonObject.has("message"));
        scenario.write("Asserting the message body, body should contain message " + scenarioContext.getContext("expectedMessage"));
        Assert.assertTrue(jsonObject.get("message").toString().contains(scenarioContext.getContext("expectedMessage")));
    }

    public void validateResponse_403() {
        scenario.write("Schema validation for response 403");
        response.then().assertThat().body(matchesJsonSchemaInClasspath("data/schema/valid403.json"));
        scenario.write("Asserting the presence of response message");
        Assert.assertTrue(jsonObject.has("message"));
        scenario.write("Asserting the message body, body should contain message " + scenarioContext.getContext("expectedMessage"));
        Assert.assertTrue(jsonObject.get("message").toString().contains(scenarioContext.getContext("expectedMessage")));
    }

    public void validateResponse_403(String schemaName) {
        scenario.write("Schema validation for " + schemaName + " - response 403");
       response.then().assertThat().body(matchesJsonSchemaInClasspath("data/schema/" + schemaName + ".json"));
        scenario.write("Asserting the presence of response message");
        Assert.assertTrue(jsonObject.has("message"));
        scenario.write("Asserting the message body, body should contain message " + scenarioContext.getContext("expectedMessage"));
        Assert.assertTrue(jsonObject.get("message").toString().contains(scenarioContext.getContext("expectedMessage")));
    }

    public void validateResponse_500() {
        scenario.write("Schema validation for response 500");
      response.then().assertThat().body(matchesJsonSchemaInClasspath("data/schema/valid500.json"));
        scenario.write("Asserting the presence of response message");
        Assert.assertTrue(jsonObject.has("message"));
        scenario.write("Asserting the message body, body should contain message " + scenarioContext.getContext("expectedMessage"));
        Assert.assertTrue(jsonObject.get("message").toString().contains(scenarioContext.getContext("expectedMessage")));
    }

    public void validateResponse_500(String schemaName) {
        scenario.write("Schema validation for " + schemaName + " - response 500");
       // response.then().assertThat().body(matchesJsonSchemaInClasspath("data/schema/" + schemaName + ".json"));
        scenario.write("Asserting the presence of response message");
        Assert.assertTrue(jsonObject.has("message"));
        scenario.write("Asserting the message body, body should contain message " + scenarioContext.getContext("expectedMessage"));
        Assert.assertTrue(jsonObject.get("message").toString().contains(scenarioContext.getContext("expectedMessage")));
        System.out.println("Success");
    }


}
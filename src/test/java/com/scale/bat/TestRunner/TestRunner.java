package com.scale.bat.TestRunner;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        strict = false,
        features = {"src/test/resources/FeatureFiles"},
        glue={"com.scale.bat.stepdefs","com.scale.bat.webservice", "com.scale.bat.context","com.scale.bat.businessPages"},
        monochrome = false,	
        plugin = { "pretty", "html:target/cucumber-html-reports", "json:target/cucumber-html-reports/cucumber.json"}
        //, tags = "@confidence, @testE2E",dryRun = false)
        , tags = "@confidence231",dryRun = false )

//plugin = {"json:Reports/cucumber-html-reports/cucumber.json"}
public class TestRunner {
	
}
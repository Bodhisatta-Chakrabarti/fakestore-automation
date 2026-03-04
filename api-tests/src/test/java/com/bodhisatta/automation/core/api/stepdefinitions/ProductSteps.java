package com.bodhisatta.automation.core.api.stepdefinitions;

import com.bodhisatta.automation.core.api.BaseAPI;
import com.bodhisatta.automation.core.api.utils.reporting.AllureAttachmentUtil;
import com.bodhisatta.automation.core.api.utils.validation.ResponseValidator;
import com.bodhisatta.automation.core.config.ConfigManager;
import com.bodhisatta.automation.core.utils.SchemaValidator;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ProductSteps extends BaseAPI {

    private Response response;

    @When("I send a GET request to {string}")
    public void sendGetRequest(String endpoint)
    {
        //response=request.get(endpoint);
        response= RestAssured.given().baseUri(ConfigManager.get("base.url"))
                .header("Content-Type", "application/json").when().get(endpoint).then().extract()
                        .response();
        AllureAttachmentUtil.attachJson("Response Body", response.asPrettyString());
        AllureAttachmentUtil.attachText("Status Code", String.valueOf(response.getStatusCode()));
    }

    @Then("the response status should be {int}")
    public void verifyStatusCode(int statusCode)
    {
        //Assert.assertEquals(response.getStatusCode(), statusCode);
        ResponseValidator.validateStatusCode(response, statusCode);
    }

    @Then("the response should match schema {string}")
    public void validateSchema(String schemaFile)
    {
        SchemaValidator.validateSchema(response, schemaFile);
    }
}

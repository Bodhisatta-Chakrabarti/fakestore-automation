package com.bodhisatta.automation.core.api.stepdefinitions;

import com.bodhisatta.automation.core.api.BaseAPI;
import com.bodhisatta.automation.core.api.constants.ApiEndpoints;
import com.bodhisatta.automation.core.api.testdata.ProductDataBuilder;
import com.bodhisatta.automation.core.api.utils.reporting.AllureAttachmentUtil;
import com.bodhisatta.automation.core.api.utils.validation.ResponseValidator;
import com.bodhisatta.automation.core.config.ConfigManager;
import com.bodhisatta.automation.core.utils.SchemaValidator;
import com.bodhisatta.automation.core.utils.logging.LogManagerUtil;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class ProductSteps extends BaseAPI {

    private static final Logger logger= LogManagerUtil.getLogger(ProductSteps.class);

    private Response response;

    @When("I send a GET request to {string}")
    public void sendGetRequest(String endpoint)
    {
        //response=request.get(endpoint);

        logger.info("Sending GET request to endpoint: {}", endpoint);

        response= RestAssured.given().baseUri(ConfigManager.get("base.url"))
                .header("User-Agent", "Mozilla/5.0").header("Accept", "application/json")
                .header("Content-Type", "application/json").when().get(endpoint).then().extract()
                        .response();
//        System.out.println("ENV: " + System.getProperty("env"));
//        System.out.println("Base URL: " + ConfigManager.get("base.url"));
        logger.info("Response status code: {}", response.getStatusCode());

        AllureAttachmentUtil.attachJson("Response Body", response.asPrettyString());
        AllureAttachmentUtil.attachText("Status Code", String.valueOf(response.getStatusCode()));
    }

    @When("I create a new product")
    public void createProduct()
    {
        Map<String, Object> payload= ProductDataBuilder.createProductPayload();

        response=request.body(payload).post(ApiEndpoints.PRODUCTS);
    }

    @When("I create a product without title")
    public void createProductWithoutTitle()
    {
        Map<String, Object> payload= ProductDataBuilder.createProductWithoutTitle();

        response=request.body(payload).post(ApiEndpoints.PRODUCTS);
    }

    @When("I create a product with invalid price")
    public void createProductWithInvalidPrice()
    {
        Map<String, Object> payload= ProductDataBuilder.createProductWithInvalidPrice();

        response=request.body(payload).post(ApiEndpoints.PRODUCTS);
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

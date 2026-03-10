package com.bodhisatta.automation.core.api.stepdefinitions;

import com.bodhisatta.automation.core.api.BaseAPI;
import com.bodhisatta.automation.core.api.constants.ApiEndpoints;
import com.bodhisatta.automation.core.api.testdata.ProductDataBuilder;
import com.bodhisatta.automation.core.api.utils.logging.ApiLogger;
import com.bodhisatta.automation.core.api.utils.reporting.AllureAttachmentUtil;
import com.bodhisatta.automation.core.api.utils.testdata.JsonDataReader;
import com.bodhisatta.automation.core.api.utils.validation.ResponseValidator;
import com.bodhisatta.automation.core.config.ConfigManager;
import com.bodhisatta.automation.core.utils.SchemaValidator;
import com.bodhisatta.automation.core.utils.logging.LogManagerUtil;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.logging.log4j.Logger;

import java.util.List;
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
        Map<String, Object> payload=new ProductDataBuilder().build();

        ApiLogger.logRequest("/products", payload);

        response=request.body(payload).post(ApiEndpoints.PRODUCTS);

        ApiLogger.logResponse(response.getStatusCode(), response.getBody().asPrettyString());
    }

    @When("I create a product without title")
    public void createProductWithoutTitle()
    {
        Map<String, Object> payload=new ProductDataBuilder().withoutTitle().build();

        response=request.body(payload).post(ApiEndpoints.PRODUCTS);
    }

    @When("I create a product with invalid price")
    public void createProductWithInvalidPrice()
    {
        Map<String, Object> payload=new ProductDataBuilder().withPrice(-10).build();

        ApiLogger.logRequest("/products", payload);

        response=request.body(payload).post(ApiEndpoints.PRODUCTS);

        ApiLogger.logResponse(response.getStatusCode(), response.getBody().asPrettyString());
    }

    @When("I create products from test data {string}")
    public void createProductsFromTestData(String fileName)
    {
        List<Map<String, Object>> products= JsonDataReader.getTestData(fileName);

        for (Map<String, Object> product:products)
        {
            ApiLogger.logRequest("/products", product);

            response=request.body(product).post(ApiEndpoints.PRODUCTS);

            ApiLogger.logResponse(response.getStatusCode(), response.getBody().asPrettyString());
        }
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

package com.bodhisatta.automation.core.api.stepdefinitions;

import com.bodhisatta.automation.core.api.BaseAPI;
import com.bodhisatta.automation.core.api.constants.ApiEndpoints;
import com.bodhisatta.automation.core.api.testdata.ProductDataBuilder;
import com.bodhisatta.automation.core.api.utils.logging.ApiLogger;
import com.bodhisatta.automation.core.api.utils.reporting.AllureAttachmentUtil;
import com.bodhisatta.automation.core.api.utils.testdata.JsonDataReader;
import com.bodhisatta.automation.core.api.utils.validation.ResponseValidator;
import com.bodhisatta.automation.core.config.ConfigManager;
import com.bodhisatta.automation.core.database.DatabaseValidator;
import com.bodhisatta.automation.core.database.QueryExecutor;
import com.bodhisatta.automation.core.utils.SchemaValidator;
import com.bodhisatta.automation.core.utils.logging.LogManagerUtil;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductSteps extends BaseAPI {

    private static final Logger logger= LogManagerUtil.getLogger(ProductSteps.class);

    private Response response;

    private String createdProductTitle;
    private double createdProductPrice;

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

        //QueryExecutor.executeUpdate("INSERT INTO products(title,price) VALUES('" + title + "',1200)");

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

    @When("I create a product from {string} with index {int}")
    public void createProductFromTestData(String fileName, int index)
    {
        Map<String, Object> product=JsonDataReader.getTestDataByIndex(fileName, index);

        ApiLogger.logRequest("/products", product);

        response=request.body(product).post(ApiEndpoints.PRODUCTS);

        ApiLogger.logResponse(response.getStatusCode(), response.getBody().asPrettyString());
    }

    @When("I create a product with title {string}")
    public void createProduct(String title)
    {
        createdProductTitle=title;
        createdProductPrice=1200;

        Map<String, Object> product=new HashMap<>();
        product.put("title", createdProductTitle);
        product.put("price", createdProductPrice);

        response=RestAssured.given().contentType("application/json").body(product).post("/products");

        QueryExecutor.executeUpdate("INSERT INTO products(title,price) VALUES('" + createdProductTitle + "'," + createdProductPrice + ")");

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

    @Then("product data should match database")
    public void product_data_should_match_database()
    {
        Map<String, Object> dbProduct= DatabaseValidator.getProductByTitle(createdProductTitle);

        Assert.assertEquals(dbProduct.get("title"), createdProductTitle, "Product title mismatch!");

        Assert.assertEquals((double) dbProduct.get("price"), createdProductPrice, "Product price mismatch!");
    }
}

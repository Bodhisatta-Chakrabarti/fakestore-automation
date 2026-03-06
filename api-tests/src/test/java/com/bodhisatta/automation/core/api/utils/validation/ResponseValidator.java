package com.bodhisatta.automation.core.api.utils.validation;

import io.qameta.allure.Step;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.testng.Assert;

public class ResponseValidator {

    @Step("Validate status code is {expectedStatus}")
    public static void validateStatusCode(Response response, int expectedStatus)
    {
        Assert.assertEquals(response.getStatusCode(), expectedStatus, "Unexpected status code!");
        //Assert.assertEquals(response.getStatusCode(), 500);
    }

    @Step("Validate response time is under {maxResponseTime} ms")
    public static void validateResponseTime(Response response, long maxResponseTime)
    {
        long actualTime=response.getTime();
        Assert.assertTrue(actualTime<=maxResponseTime, "Response time exceeded! Actual: " + actualTime);
    }

    @Step("Validate header {headerName} equals {expectedValue}")
    public static void validateHeader(Response response, String headerName, String expectedValue)
    {
        Assert.assertEquals(response.getHeader(headerName), expectedValue, "Header mismatch for: " + headerName);
    }

    @Step("Validate JSON field {jsonPath} equals {expectedValue}")
    public static void validateStatusCode(Response response, String jsonPath, Object expectedValue)
    {
        Object actualValue=response.jsonPath().get(jsonPath);
        Assert.assertEquals(actualValue, expectedValue, "JSON field mismatch for! " + jsonPath);
    }

    @Step("Validate response matches schema: {schemaPath}")
    public static void validateSchema(Response response, String schemaPath)
    {
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(schemaPath));
    }
}

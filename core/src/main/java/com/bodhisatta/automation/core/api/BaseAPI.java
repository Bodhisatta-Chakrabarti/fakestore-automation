package com.bodhisatta.automation.core.api;

import com.bodhisatta.automation.core.config.ConfigManager;
import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;

public class BaseAPI {

    protected RequestSpecification request;

    public BaseAPI()
    {
        RestAssured.baseURI= ConfigManager.get("base.url");

        request=RestAssured.given().header("Content-Type", "application/json").log()
                .ifValidationFails(LogDetail.ALL);
    }

}

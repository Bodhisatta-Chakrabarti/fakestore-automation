package com.bodhisatta.automation.core.api;

import com.bodhisatta.automation.core.config.ConfigManager;
import com.bodhisatta.automation.core.utils.api.ApILoggingFilter;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class BaseAPI {

    protected RequestSpecification request;

    public BaseAPI()
    {
        RestAssured.baseURI= ConfigManager.get("base.url");

//        request=RestAssured.given().header("Content-Type", "application/json").log()
//                .ifValidationFails(LogDetail.ALL);

        request= RestAssured.given().baseUri(ConfigManager.get("base.url")).contentType(ContentType.JSON).filter(new ApILoggingFilter());
    }

}

package com.bodhisatta.automation.core.utils;

import io.restassured.response.Response;

public class ResponseUtils {

    public static String getJsonValue(Response response, String path)
    {
        return response.jsonPath().getString(path);
    }

    public static int getJsonInt(Response response, String path)
    {
        return response.jsonPath().getInt(path);
    }
}

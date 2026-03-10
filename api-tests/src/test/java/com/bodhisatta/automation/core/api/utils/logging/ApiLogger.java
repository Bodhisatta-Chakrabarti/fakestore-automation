package com.bodhisatta.automation.core.api.utils.logging;

import io.qameta.allure.Allure;

public class ApiLogger {

    public static void logRequest(String endpoint, Object body)
    {
        String requestLog="Endpoint:\n" + endpoint + "\n\n Request Body:\n" + body;

        Allure.addAttachment("API Request", requestLog);
    }

    public static void logResponse(int statusCode, String body)
    {
        String responseLog="Status Code:\n" + statusCode + "\n\n Response Body:\n" + body;

        Allure.addAttachment("API Request", responseLog);
    }
}

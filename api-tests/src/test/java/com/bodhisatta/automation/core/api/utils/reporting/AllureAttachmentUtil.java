package com.bodhisatta.automation.core.api.utils.reporting;

import io.qameta.allure.Allure;

public class AllureAttachmentUtil {

//    @Attachment(value = "{name}", type = "application/json")
    public static void attachJson(String name, String json)
    {
        Allure.addAttachment(name, "application/json", json, ".json");
    }

//    @Attachment(value = "{name}", type = "text/plain")
    public static void attachText(String name, String text)
    {
        Allure.addAttachment(name, "text/plain", text);
    }
}

package com.bodhisatta.automation.core.api.testdata;

import java.util.HashMap;
import java.util.Map;

public class ProductDataBuilder {

    public static Map<String, Object> createProductPayload()
    {
        Map<String, Object> product=new HashMap<>();

        product.put("title", "Test Product");
        product.put("price", 10.5);
        product.put("description", "Automation Test Product");
        product.put("image", "https://i.pravatar.cc");
        product.put("category", "electronics");

        return product;
    }

    public static Map<String, Object> createProductWithoutTitle()
    {
        Map<String, Object> product=createProductPayload();
        product.remove("title");

        return product;
    }

    public static Map<String, Object> createProductWithInvalidPrice()
    {
        Map<String, Object> product=createProductPayload();
        product.put("price", -10);

        return product;
    }

}

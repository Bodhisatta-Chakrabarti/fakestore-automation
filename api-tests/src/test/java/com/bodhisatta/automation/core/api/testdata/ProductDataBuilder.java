package com.bodhisatta.automation.core.api.testdata;

import com.bodhisatta.automation.core.api.utils.testdata.FakerUtils;

import java.util.HashMap;
import java.util.Map;

public class ProductDataBuilder {

    private Map<String, Object> product;

    public ProductDataBuilder()
    {
        product=new HashMap<>();

        product.put("title", FakerUtils.getProductName());
        product.put("price", FakerUtils.getPrice());
        product.put("description", FakerUtils.getDescription());
        product.put("image", FakerUtils.getImageUrl());
        product.put("category", FakerUtils.getCategory());

    }

    public ProductDataBuilder withTitle(String title)
    {
        product.put("title", title);
        return this;
    }

    public ProductDataBuilder withPrice(double price)
    {
        product.put("price", price);
        return this;
    }

    public ProductDataBuilder withoutTitle()
    {
        product.remove("title");
        return this;
    }

    public Map<String, Object> build()
    {
        return product;
    }

}

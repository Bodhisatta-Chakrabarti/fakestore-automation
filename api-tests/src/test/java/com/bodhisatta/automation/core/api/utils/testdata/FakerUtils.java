package com.bodhisatta.automation.core.api.utils.testdata;

import com.github.javafaker.Faker;

public class FakerUtils {

    private static final Faker faker=new Faker();

    public static String getProductName()
    {
        return faker.commerce().productName();
    }

    public static String getDescription()
    {
        return faker.lorem().sentence();
    }

    public static String getCategory()
    {
        return faker.commerce().department();
    }

    public static double getPrice()
    {
        return Double.parseDouble(faker.commerce().price());
    }

    public static String getImageUrl()
    {
        return "https://i.pravatar.cc";
    }
}

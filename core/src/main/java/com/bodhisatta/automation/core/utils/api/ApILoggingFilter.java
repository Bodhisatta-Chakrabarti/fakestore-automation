package com.bodhisatta.automation.core.utils.api;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class ApILoggingFilter implements Filter {

    @Override
    public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext ctx) {

        System.out.println("REQUEST METHOD: " + requestSpec.getMethod());
        System.out.println("REQUEST URI: " + requestSpec.getURI());
        System.out.println("REQUEST BODY: " + requestSpec.getBody());

        Response response=ctx.next(requestSpec, responseSpec);

        System.out.println("RESPONSE STATUS: " + response.getStatusCode());
        System.out.println("RESPONSE BODY: " + response.getBody().asPrettyString());

        return response;
    }
}

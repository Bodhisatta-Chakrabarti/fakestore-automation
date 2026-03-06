package com.bodhisatta.automation.core.utils.retry;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    private int retryCount=0;
    private static final int MAX_RETRY=2;

    @Override
    public boolean retry(ITestResult iTestResult) {

        if(retryCount<MAX_RETRY)
        {
            retryCount++;
            System.out.println("Retrying test: " + iTestResult.getName() + " attempt " + retryCount);
            return true;
        }

        return false;
    }
}

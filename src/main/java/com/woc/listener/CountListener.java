package com.woc.listener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.concurrent.atomic.AtomicInteger;

public class CountListener implements ITestListener {

    private AtomicInteger successCount = new AtomicInteger();

    int count = 0;//this variable is not thread safe,
    // and there would be concurrency issue when we run our test-suit in parallel mode
    //to overcome from this concurrency issue, we can use AtomicInteger class which is thread safe.

    @Override
    public void onTestSuccess(ITestResult result) {
        count++;
       successCount.incrementAndGet();
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("total success test count = " + successCount.get());
        System.out.println("count = " + count);
    }
}

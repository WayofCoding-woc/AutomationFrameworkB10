package com.woc.listener;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.woc.config.ExtentReportManager;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentReportListener implements ITestListener {

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("---------------hi------------");
        ExtentTest test = ExtentReportManager.extentReports.createTest(result.getMethod().getMethodName(), " test passed");
        test.log(Status.INFO, MarkupHelper.createLabel(result.getMethod().getMethodName() + " test passed", ExtentColor.GREEN));
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTest test = ExtentReportManager.extentReports.createTest(result.getMethod().getMethodName(), " test failed");
        test.log(Status.ERROR, MarkupHelper.createLabel(result.getMethod().getMethodName() + " test failed", ExtentColor.RED));
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTest test = ExtentReportManager.extentReports.createTest(result.getMethod().getMethodName(), " test skipped");
        test.log(Status.WARNING, MarkupHelper.createLabel(result.getMethod().getMethodName() + " test skipped", ExtentColor.GREY));
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentReportManager.extentReports.flush();
    }
}

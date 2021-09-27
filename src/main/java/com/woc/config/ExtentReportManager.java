package com.woc.config;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;

public class ExtentReportManager {
    public static ExtentReports extentReports = getInstance();//thread safe singleton object

    private static ExtentReports getInstance(){
        extentReports = new ExtentReports();
        extentReports.setSystemInfo("OS", System.getProperty("os.name"));
        extentReports.setSystemInfo("Browser", "chrome");

        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("reports" + File.separator + "extent-report.html");
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setDocumentTitle("Batch 10 - Extent Report");
        htmlReporter.config().setReportName("--------REST API TEST Report--------");
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        htmlReporter.config().setTheme(Theme.DARK);

        extentReports.attachReporter(htmlReporter);

        return extentReports;
    }
}

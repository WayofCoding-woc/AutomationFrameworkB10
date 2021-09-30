package com.woc.config.selenium;

import com.woc.util.PropertyUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;

public class BrowserManager {

    static{
        System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, "driver" + File.separator + "chromedriver");
    }

    protected WebDriver getDriver(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.setHeadless(Boolean.parseBoolean(PropertyUtil.getProperty("headless.mode")));

        WebDriver webDriver = new ChromeDriver(options);
        webDriver.manage().window().maximize();
        //webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        return webDriver;
    }

    protected void closeDriver(WebDriver webDriver){
        if(null != webDriver){
            webDriver.close();
        }
    }

}

package com.woc.config.selenium;

import com.woc.util.PropertyUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.GeckoDriverService;

import java.io.File;

public class BrowserManager {

    private static final String BROWSER_NAME = "browser.name";
    private static final String HEADLESS_MODE = "headless.mode";
    private static final String CHROME = "chrome";
    private static final String FIREFOX = "firefox";

    static{
        System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, "driver" + File.separator + "chromedriver");
        System.setProperty(GeckoDriverService.GECKO_DRIVER_EXE_PROPERTY, "driver" + File.separator + "geckodriver");
    }

    protected WebDriver getDriver(){
        WebDriver webDriver = getWebDriver();

        webDriver.manage().window().maximize();
        //webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        return webDriver;
    }

    private WebDriver getWebDriver() {
        if(PropertyUtil.getProperty(BROWSER_NAME).equals(CHROME)){
            return getChromeDriver();
        }else if(PropertyUtil.getProperty(BROWSER_NAME).equals(FIREFOX)){
            return getFireFoxDriver();
        }
        throw new IllegalStateException("check the property settings for key - " + BROWSER_NAME);
    }

    private ChromeDriver getChromeDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.setHeadless(Boolean.parseBoolean(PropertyUtil.getProperty(HEADLESS_MODE)));

        ChromeDriver chromeDriver = new ChromeDriver(options);
        return chromeDriver;
    }

    private FirefoxDriver getFireFoxDriver() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setCapability("marionette", true);
        firefoxOptions.setHeadless(Boolean.parseBoolean(PropertyUtil.getProperty(HEADLESS_MODE)));

        FirefoxDriver firefoxDriver = new FirefoxDriver(firefoxOptions);
        return firefoxDriver;
    }

    protected void closeDriver(WebDriver webDriver){
        if(null != webDriver){
            webDriver.close();
        }
    }

}

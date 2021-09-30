package com.woc.selenium.gs.test;

import com.woc.config.selenium.BrowserManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class GoogleSearchTestWithSingleInput extends BrowserManager {

    private static final By GOOGLE_SEARCH_TEXT_BOX_SELECTOR = By.xpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[1]/div/div[2]/input");
    private WebDriver driver;

    @BeforeClass
    public void initializeDriver(){
        driver = getDriver();
    }

    @Test
    public void googlePageTest() throws InterruptedException {
        String url = "https://www.google.co.in/";
        driver.get(url);
        WebElement searchTexBox = driver.findElement(GOOGLE_SEARCH_TEXT_BOX_SELECTOR);
        searchTexBox.sendKeys("Hello World Java Program Example");
        searchTexBox.sendKeys(Keys.ENTER);
        TimeUnit.SECONDS.sleep(1);
    }

    @AfterClass
    public void closeDriver(){
        driver.close();
    }

}

package com.woc.selenium.am.test;

import com.woc.config.selenium.BrowserManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public class BasePage extends BrowserManager {

    protected static final String USER_NAME = "woc";
    protected static final String PASSWORD = "pass";

    private static final By LOGIN_TEXT_BOX_SELECTOR = By.id("loginId");
    private static final By PASSWORD_TEXT_BOX_SELECTOR = By.id("password");
    private static final By LOGIN_BUTTON_SELECTOR = By.xpath("/html/body/div[2]/form/table/tbody/tr[3]/td/input");

    protected String baseUrl = "http://localhost:7060";

    protected WebDriver login(String loginId, String password){
        WebDriver driver = getDriver();
        driver.get(baseUrl);
        sleep(1);
        WebElement loginIdElement = driver.findElement(LOGIN_TEXT_BOX_SELECTOR);
        WebElement passwordElement = driver.findElement(PASSWORD_TEXT_BOX_SELECTOR);
        WebElement loginButton = driver.findElement(LOGIN_BUTTON_SELECTOR);

        loginIdElement.sendKeys(loginId);
        passwordElement.sendKeys(password);
        loginButton.click();

        return driver;
    }

    protected void sleep(int sec){
        try {
            TimeUnit.SECONDS.sleep(sec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

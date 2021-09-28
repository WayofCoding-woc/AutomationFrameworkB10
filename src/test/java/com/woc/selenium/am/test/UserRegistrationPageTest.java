package com.woc.selenium.am.test;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserRegistrationPageTest extends BasePage {
    //private static final By USER_REGISTRATION_MENU_SELECTOR = By.linkText("User Registration");
    private static final By USER_REGISTRATION_MENU_SELECTOR = By.xpath("//*[@id=\"user_registration_id\"]/a");
    public static final String USER_REGISTRATION = "User Registration";
    public static final String UNABLE_TO_LOAD_USER_REGISTRATION_PAGE = "Unable to load user registration page";
    public static final By LOGIN_ID_SELECTOR = By.id("loginId");
    public static final By PASSWORD_SELECTOR = By.id("password");
    public static final By CONFIRM_PASSWORD_SELECTOR = By.id("confirm_password");
    public static final By USER_NAME_SELECTOR = By.id("user_name");
    public static final By EMAIL_SELECTOR = By.id("email");
    public static final By MOBILE_SELECTOR = By.id("mobile");
    public static final By DOJ_SELECTOR = By.id("doj");
    public static final By ROLE_SELECTOR = By.id("role");
    public static final By ACTIVE_CHECKBOX_SELECTOR = By.id("active");
    public static final By SUBMIT_BTN_SELECTOR = By.cssSelector("body > div:nth-child(2) > form > table > tbody > tr:nth-child(10) > td > input[type=submit]");

    @Test
    public void navigateToUserRegistrationPageTest(){
        WebDriver driver = login(USER_NAME, PASSWORD);
        sleep(1);
        WebElement userRegistrationMenu = driver.findElement(USER_REGISTRATION_MENU_SELECTOR);
        userRegistrationMenu.click();
        Assert.assertTrue(driver.getPageSource().contains(USER_REGISTRATION), UNABLE_TO_LOAD_USER_REGISTRATION_PAGE);
        sleep(3);
        closeDriver(driver);
    }

    @Test(dependsOnMethods = "navigateToUserRegistrationPageTest")
    public void userRegistrationTest(){
        WebDriver driver = login(USER_NAME, PASSWORD);
        WebElement userRegistrationMenu = driver.findElement(USER_REGISTRATION_MENU_SELECTOR);
        userRegistrationMenu.click();

        WebElement loginId = driver.findElement(LOGIN_ID_SELECTOR);
        loginId.sendKeys("chetan3");

        driver.findElement(PASSWORD_SELECTOR).sendKeys("pwd");
        driver.findElement(CONFIRM_PASSWORD_SELECTOR).sendKeys("pwd");
        driver.findElement(USER_NAME_SELECTOR).sendKeys("chetan3");
        driver.findElement(EMAIL_SELECTOR).sendKeys("chetan3@gmail.com");
        driver.findElement(MOBILE_SELECTOR).sendKeys("9562345873");
        driver.findElement(DOJ_SELECTOR).sendKeys("21/09/2021");
        new Select(driver.findElement(ROLE_SELECTOR)).selectByValue("user");
        driver.findElement(ACTIVE_CHECKBOX_SELECTOR).click();
        sleep(1);

        ((JavascriptExecutor)driver).executeScript("window.scrollBy(0, 500)");

        sleep(2);

        driver.findElement(SUBMIT_BTN_SELECTOR).click();

        sleep(3);
        closeDriver(driver);

    }

}

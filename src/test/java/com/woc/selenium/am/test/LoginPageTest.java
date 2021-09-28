package com.woc.selenium.am.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginPageTest extends BasePage{

    public static final String WE_ARE_UNABLE_TO_LOAD_THE_SIGN_IN_PAGE = "We are unable to load the sign-in page";
    public static final String WE_ARE_UNABLE_TO_DO_THE_LOGIN = "We are unable to do the login";
    public static final String WELCOME_TO_ASSET_MANAGEMENT_PORTAL = "Welcome! to Asset Management Portal";
    public static final String SIGN_IN = "Sign In";

    @Test
    public void loginHomePageTest(){
        WebDriver driver = getDriver();
        driver.get(baseUrl);
        boolean signInTextPresent = driver.getPageSource().contains(SIGN_IN);
        Assert.assertTrue(signInTextPresent, WE_ARE_UNABLE_TO_LOAD_THE_SIGN_IN_PAGE);
        closeDriver(driver);
    }

    @Test(dependsOnMethods = "loginHomePageTest")
    public void loginFunctionalityTest(){
        WebDriver driver = login(USER_NAME, PASSWORD);
        sleep(1);
        boolean postLoginHomePageTextPresent = driver.getPageSource().contains(WELCOME_TO_ASSET_MANAGEMENT_PORTAL);
        Assert.assertTrue(postLoginHomePageTextPresent, WE_ARE_UNABLE_TO_DO_THE_LOGIN);
        closeDriver(driver);
    }

    @Test
    public void viewUserProfileTest(){
        WebDriver driver = login(USER_NAME, PASSWORD);
        //explicit wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement viewProfileLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("View Your Profile & Assets")));
        viewProfileLink.click();
        sleep(3);
        driver.close();
    }

}

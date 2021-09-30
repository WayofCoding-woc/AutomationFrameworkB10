package com.woc.bdd.cucumber.test;

import com.woc.reqres.usercrud.User;
import com.woc.util.PropertyUtil;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class CreateUserTest {
    User inputUser;
    Response response;

    @Given("^Set the base url$")
    public void setBaseUrl(){
        System.out.println("setBaseUrl triggered");

        RestAssured.baseURI = PropertyUtil.getProperty("reqres.base.url");
    }

    @When("^Enter the Name \"(.*)\" and Job \"(.*)\" hit the post request$")
    public void createUser(String name, String job){
        System.out.println("createUser triggered, name="+name+", job="+job);

        inputUser = new User();
        inputUser.setName(name);
        inputUser.setJob(job);

        response = given()
                .basePath("/users")
                .contentType(ContentType.JSON)
                .body(inputUser)
                .post();
    }

    @Then("^verify the response$")
    public void verifyResponse(){
        System.out.println("verifyResponse triggered");

        User createdUser = response.then()
                .statusCode(201)
                .extract()
                .response()
                .thenReturn()
                .as(User.class);

        System.out.println("createdUser="+createdUser);

        Assert.assertEquals(createdUser.getName(), inputUser.getName());
        Assert.assertEquals(createdUser.getJob(), inputUser.getJob());
        Assert.assertNotNull(createdUser.getCreatedAt(), "Creation time should not be null");
        Assert.assertNotNull(createdUser.getId(), "created user's id should not be null");
    }
}

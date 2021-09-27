package com.woc.api;

import com.woc.reqres.multiuser.Datum;
import com.woc.reqres.multiuser.UserResponse;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

public class MultipleUserDataTest {

    @Test
    public void getMultipleUsers(){
        Response response = RestAssured
                .given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .thenReturn();

        Assert.assertEquals(response.getStatusCode(), 200);
        UserResponse userResponse = response.getBody().as(UserResponse.class);
        List<Datum> users = userResponse.getData();
        Assert.assertNotNull(users);

        List<String> distinctFirstName = users.stream().map(Datum::getFirstName).distinct().collect(Collectors.toList());
        System.out.println("distinctFirstName = " + distinctFirstName);
        List<String> distinctEmails = users.stream().map(Datum::getEmail).distinct().collect(Collectors.toList());
        System.out.println("distinctEmails = " + distinctEmails);
    }
}

package com.woc.api;

import com.google.gson.Gson;
import com.woc.reqres.Data;
import com.woc.reqres.UserResponse;
import com.woc.reqres.usercrud.User;
import com.woc.util.PropertyUtil;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTest {
    private static final Logger logger = LogManager.getLogger(UserTest.class.getName());

    @BeforeClass
    public void init(){
        RestAssured.baseURI =  PropertyUtil.getProperty("reqres.base.url");
    }

    @Test
    public void getSingleUserData(){
        RestAssured
                .given()
                .when()
                .get("users/2")
                .then()
                .log()
                .all();
    }

    @Test
    public void getMultipleUserData(){
        for(int i=2; i<=10; i++) {
            RestAssured.given().when().get("users/"+i).then().log().all();
        }
    }

    @Test
    public void getUserDataTest2(){
        Response response = RestAssured
                .given()
                .when()
                .get("users/2")
                .thenReturn();

        String responseData = response.getBody().asString();
        logger.info("responseData = " + responseData);

        Gson gson = new Gson();
        UserResponse userResponse = gson.fromJson(responseData, UserResponse.class);
        logger.info("userResponse = " + userResponse);

        Data userData = userResponse.getData();
        logger.info("userData.getLastName()="+userData.getLastName());
    }

    @Test
    public void getUserDataTest3(){
        UserResponse userResponse = RestAssured
                .given()
                .when()
                .get("users/2")
                .thenReturn()
                .as(UserResponse.class);

        logger.debug("userResponse = " + userResponse);

        Data userData = userResponse.getData();
        logger.debug("userData.getLastName()="+userData.getLastName());
    }

    @Test
    public void getUserDataTest4(){
        Response response = RestAssured
                .given()
                .when()
                .get("/users/2")
                .thenReturn();

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 201, "unable to fetch the user data");
        UserResponse userResponse = response.as(UserResponse.class);

        Assert.assertNotNull(userResponse, "User data must not be null");

        logger.warn("userResponse = " + userResponse);

        Data userData = userResponse.getData();
        logger.debug("userData.getLastName()="+userData.getLastName());
    }

    @Test
    public void createUserTest() {
        User inputUser = new User();
        inputUser.setName("Chetan");
        inputUser.setJob("SDET");

        logger.info("inputUser = " + inputUser);

        User createdUser = RestAssured
                .given()
                .when()
                .contentType(ContentType.JSON)
                .body(inputUser)
                .post("api/users")
                .then()
                .statusCode(201)
                .extract()
                .as(User.class);

        logger.info("createdUser = " + createdUser);

        Assert.assertNotNull(createdUser);
        Assert.assertNotNull(createdUser.getId());
        Assert.assertNotNull(createdUser.getCreatedAt());

    }

    @Test
    public void updateUserTest() {
        User inputUser = new User();
        inputUser.setName("Chetan Kumar");
        inputUser.setJob("Tech Lead in Automation");

        logger.info("inputUser = " + inputUser);

        User updatedUser = RestAssured
                .given()
               /*
               //if any api need http basic auth then we can pass the username and password like below
               .auth()
                .basic("username", "password")*/

                /*
                //if any api need oauth then we can pass the token here as a header
                // for e.g token is asfdljasldfjalskdfjlasjdfljasdlfjasljdf
                .header("Authorization", "Bearer asfdljasldfjalskdfjlasjdfljasdlfjasljdf")
                */

                .when()
                .contentType(ContentType.JSON)
                .body(inputUser)
                .put("api/users/"+2)
                .then()
                .statusCode(200)
                .extract()
                .as(User.class);

        logger.info("updatedUser = " + updatedUser);

        Assert.assertNotNull(updatedUser);
        Assert.assertNotNull(updatedUser.getUpdatedAt());

    }

    @Test
    public void updateUserTestBasicAuth() {
        User inputUser = new User();
        inputUser.setName("Chetan Kumar");
        inputUser.setJob("Tech Lead in Automation");

        logger.info("inputUser = " + inputUser);

        User updatedUser = RestAssured
                .given()
                /*
                //if any api need http basic auth then we can pass the username and password like below */
                .auth()
                 .basic(PropertyUtil.getProperty("reqres.username"), PropertyUtil.getProperty("reqres.password"))

                .when()
                .contentType(ContentType.JSON)
                .body(inputUser)
                .put("api/users/"+2)
                .then()
                .statusCode(200)
                .extract()
                .as(User.class);

        logger.info("updatedUser = " + updatedUser);

        Assert.assertNotNull(updatedUser);
        Assert.assertNotNull(updatedUser.getUpdatedAt());

    }

    @Test
    public void updateUserTestOAuth() {
        User inputUser = new User();
        inputUser.setName("Chetan Kumar");
        inputUser.setJob("Tech Lead in Automation");

        logger.info("inputUser = " + inputUser);

        User updatedUser = RestAssured
                .given()
                /*
                //if any api need oauth then we can pass the token here as a header
                // for e.g token is asfdljasldfjalskdfjlasjdfljasdlfjasljdf*/
                .header("Authorization", "Bearer "+PropertyUtil.getProperty("reqres.oauth.token"))
                .when()
                .contentType(ContentType.JSON)
                .body(inputUser)
                .put("api/users/"+2)
                .then()
                .statusCode(200)
                .extract()
                .as(User.class);

        logger.info("updatedUser = " + updatedUser);

        Assert.assertNotNull(updatedUser);
        Assert.assertNotNull(updatedUser.getUpdatedAt());

    }

    @Test
    public void deleteUserTest() {
        RestAssured
                .given()
                .when()
                .delete("api/users/"+2)
                .then()
                .statusCode(204);

    }

}

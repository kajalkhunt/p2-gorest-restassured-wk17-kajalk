package com.gorest.crudtest;

import com.gorest.model.UserPojo;
import com.gorest.utils.TestUtils;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class UserCRUDTest extends TestUtils {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {

        RestAssured.baseURI = "https://gorest.co.in/public/v2/users";
        response = given()
                .when()
                .get("")
                .then().statusCode(200); //method type of this is validatable response
    }

    @Test
public void verifyUserCreatedSuccessfully(){

        UserPojo userPojo = new UserPojo();

        userPojo.setName("Sara Khan");
        userPojo.setEmail("SKhan" + getRandomValue() + "@gmail.com");
        userPojo.setGender("Female" );
        userPojo.setStatus("active");

        Response response = given().log().all()
                .header("Content-Type","application/json")
                .header("Authorization","Bearer f98a43d7ab3ecb19b83e0018faa82de6764f2cb240053ee8c6e1fe188d756d0a")// add the token here from postman
                .when()
                .body(userPojo)
                .post();//change here
        response.prettyPrint();
        response.then().log().all().statusCode(201);

    }
    @Test
    public void verifyUserUpdateSuccessfully() {
        UserPojo userPojo = new UserPojo();

        userPojo.setName("Sara Ali Khan");
        userPojo.setEmail("SAliKhan" + getRandomValue() + "@gmail.com");
        userPojo.setGender("Female");
        userPojo.setStatus("active");

        Response response = given()

                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer f98a43d7ab3ecb19b83e0018faa82de6764f2cb240053ee8c6e1fe188d756d0a")
                .body(userPojo)
                .pathParam("id", 11425) //passing parameter to .get()
                .when()
                .patch("/{id}");
        response.then().statusCode(200);
        response.prettyPrint();
    }
    @Test
    public void VerifyUserDeleteSuccessfully() {

        UserPojo userPojo = new UserPojo();

        Response response = given()

                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer f98a43d7ab3ecb19b83e0018faa82de6764f2cb240053ee8c6e1fe188d756d0a")
                .body(userPojo)
                .pathParam("id", 11425) //passing parameter to .get()
                .when()
                .delete("/{id}");
        response.then().statusCode(204);
        response.prettyPrint();
    }


}

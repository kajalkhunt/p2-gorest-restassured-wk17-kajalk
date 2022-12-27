package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class UserExtractionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2/";
//        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/users?page=1&per_page=20")
                .then().statusCode(200);
    }

    //1. Extract the All Ids
    @Test
    public void test001() {
        List<Integer> allIds = response.extract().path("id");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : " + allIds);
        System.out.println("------------------End of Test---------------------------");

    }

    //2. Extract the all Names
    @Test
    public void test002() {
        List<String> allNames = response.extract().path("name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : " + allNames);
        System.out.println("------------------End of Test---------------------------");
    }

    //3. Extract the name of 5th object
    @Test
    public void test003() {
        String nameOfFifthObject = response.extract().path("name[4]");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Name of fifth object is : " + nameOfFifthObject);
        System.out.println("------------------End of Test---------------------------");
    }

    //4. Extract the names of all object whose status = inactive
    @Test
    public void test004() {
        List<String> inactive = response.extract().path("findAll{it.status == 'inactive'}.name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : " + inactive);
        System.out.println("------------------End of Test---------------------------");
    }

    //5. Extract the gender of all the object whose status = active
    @Test
    public void test005() {
        List<String> active = response.extract().path("findAll{it.status =='active'}.gender");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : " + active);
        System.out.println("------------------End of Test---------------------------");
    }

    //6. Print the names of the object whose gender = female
    @Test
    public void test006() {
        List<String> female = response.extract().path("findAll{it.status =='active'}.gender");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : " + female);
        System.out.println("------------------End of Test---------------------------");
    }

    //7. Get all the emails of the object where status = inactive
    @Test
    public void test007() {
        List<HashMap<?, ?>> allEmails = response.extract().path("findAll{it.status == 'inactive'}.email");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of email of all whose status is inactive : " + allEmails);
        System.out.println("------------------End of Test---------------------------");
    }

    //8. Get the ids of the object where gender = male
    @Test
    public void test008() {

            List<HashMap<?, ?>> allIDs = response.extract().path("findAll{it.gender == 'male'}.id");
            System.out.println("------------------StartingTest---------------------------");
            System.out.println("List of IDs of all whose gender is male : " + allIDs);
            System.out.println("------------------End of Test---------------------------");
    }

    //9. Get all the status
    @Test
    public void test009() {
        List<HashMap<?, ?>> allStatus = response.extract().path("status");
            System.out.println("------------------StartingTest---------------------------");
            System.out.println("List of all status is : " + allStatus);
            System.out.println("------------------End of Test---------------------------");
    }

    //10. Get email of the object where name = Ganesh Kaniyar
    @Test
    public void test0010() {
            List<HashMap<?, ?>> email = response.extract().path("findAll{it.name == 'Ganesh Kaniyar'}.email");
            System.out.println("------------------StartingTest---------------------------");
            System.out.println("Email of Ganesh Kaniyar is : " + email);
            System.out.println("------------------End of Test---------------------------");
        }
//11. Get gender of id = 5312
        @Test
        public void test011() {
            List<HashMap<?, ?>> gender = response.extract().path("findAll{it.id == 5312}.gender");
            System.out.println("------------------StartingTest---------------------------");
            System.out.println("Gender of id 5312 is : " + gender);
            System.out.println("------------------End of Test---------------------------");

    }

}

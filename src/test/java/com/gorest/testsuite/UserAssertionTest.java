package com.gorest.testsuite;


import com.gorest.testbase.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.core.IsEqual.equalTo;

public class UserAssertionTest extends TestBase {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {

        RestAssured.baseURI = "https://gorest.co.in/public/v2/";
//        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/users?page=1&per_page=20")
                .then().statusCode(200); //method type of this is validatable response

    }

    //1. Verify the if the total record is 20
    @Test
    public void test1() {
        response.body("total.size().toString()", equalTo("20"));
    }

    //2. Verify the if the name of id = 5313 is equal to ”Ganesh Kaniyar”
    @Test
    public void test2() {
        response.body("[8].name", equalTo("Ganesh Kaniyar"));
    }

    //3. Check the single ‘Name’ in the Array list (Subhashini Talwar)
    @Test
    public void test3() {
        response.body("name", hasItem("Ganesh Kaniyar"));
    }

    //4. Check the multiple ‘Names’ in the ArrayList (Mrs. Menaka Bharadwaj, Msgr. Bodhan
    //Guha, Karthik Dubashi IV)
    @Test
    public void test4() {
        response.body("name", hasItems("Rameshwar Varman","Raj Patil","Aashritha Bhattathiri"));
    }

    //5. Verify the email of userid = 5312 is equal “adiga_aanjaneya_rep@jast.org”
    @Test
    public void test5() {
        response.body("email", hasItem("ganesh_kaniyar@white.org"));
    }

//6. Verify the status is “Active” of user name is “Shanti Bhat V”
@Test
public void test6() {
    response.body("[6].status", equalTo("active"));
    response.body("[6].name", equalTo("Ganesh Kaniyar"));
}
//7. Verify the Gender = male of user name is “Niro Prajapat”
@Test
public void test7() {
    response.body("gender[6]", equalTo("male"));
    response.body("name[6]", equalTo("Ganesh Kaniyar"));
}

}

package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.core.IsEqual;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PostsAssertionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {

        RestAssured.baseURI = "https://gorest.co.in/public/v2/";
//        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/posts?page=1&per_page=25")
                .then().statusCode(200); //method type of this is validatable response
    }

    @Test
    public void test1() {
        //1. Verify the if the total record is 25
        response.body("total.size().toString()", equalTo("25"));
    }

    //2. Verify the if the title of id = 2730 is equal to ”Ad ipsa coruscus ipsam eos demitto centum.”
    @Test
    public void test2() {
        response.body("title[2]", IsEqual.equalTo("Ad ipsa coruscus ipsam eos demitto centum."));
    }

    //3. Check the single user_id in the Array list (5522)
    @Test
    public void test3() {
        response.body("user_id[4].toString()", equalTo("5522"));
    }

    //4. Check the multiple ids in the ArrayList (2693,2683,2674)
    @Test
    public void test4() {

        response.body("id", hasItems(2662,2670,2645));
    }
//5. Verify the body of userid = 2678 is equal “Carus eaque voluptatem. Calcar
//spectaculum coniuratio. Abstergo consequatur deleo. Amiculum advenio dolorem.
//Sollers conservo adiuvo. Concedo campana capitulus. Adfectus tibi truculenter.
//Canto temptatio adimpleo. Ter degenero animus. Adeo optio crapula. Abduco et
//antiquus. Chirographum baiulus spoliatio. Suscipit fuga deleo. Comburo aequus
//cuppedia. Crur cuppedia voluptates. Argentum adduco vindico. Denique undique
//adflicto. Assentator umquam pel."
@Test
public void test5() {
    response.body("body[0]", equalTo("Praesentium patrocinor sophismata. Deprecator aeneus acervus. Supellex qui aperiam. Succedo suffoco canis. Approbo consequatur debeo. Victus vir nobis. Varietas super amo. Terreo baiulus desino. Adipisci caterva concedo. Torqueo abutor dens. Claudeo dicta tantillus. Cohors campana delectatio. Iure sortitus abutor. Accedo deprimo cenaculum. Summisse supra curtus. Necessitatibus delinquo cunabula. Patrocinor tepesco amplitudo. Vulticulus solutio solium. Succedo vorax baiulus."));
    response.body("user_id[0]",equalTo(5437));
}

}

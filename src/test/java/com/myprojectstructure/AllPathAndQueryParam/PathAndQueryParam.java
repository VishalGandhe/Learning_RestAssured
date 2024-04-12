package com.myprojectstructure.AllPathAndQueryParam;

import org.testng.annotations.Test;

//import java.util.HashMap;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class PathAndQueryParam {

  //  https://reqres.in/api/users?page=2&id=7

    @Test(priority = 1)
    public void testPathAndQueryParam(){

        given()
                .pathParams("mypath", "users") //path parameter
                .queryParam("page", 2) // Query parameter
                .queryParam("id", 12)

                .when()
                .get("https://reqres.in/api/{mypath}")


                .then()
                .statusCode(200)
                .log().all();

    }
}

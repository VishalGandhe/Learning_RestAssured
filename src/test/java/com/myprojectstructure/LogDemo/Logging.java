package com.myprojectstructure.LogDemo;

import org.testng.annotations.Test;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static io.restassured.internal.assertion.CookieMatcher.getCookies;
import static org.hamcrest.Matchers.*;


public class Logging {

    @Test
    public void testLogs(){

        given()

                .when()
         //       .get("https://reqres.in/api/users?page=2")
                .get("https://www.google.com/")

                .then()
               // .log().body()
             //   .log().cookies()
                .log().headers();




    }

}

package com.myprojectstructure.Cookies;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static io.restassured.internal.assertion.CookieMatcher.getCookies;
import static org.hamcrest.Matchers.*;

import io.restassured.response.Response;
import org.testng.annotations.Test;

public class CookiesDemo {

    @Test(priority = 1)
    public void testCookies(){

        given()
                .when()
                .get("https://www.google.com/")

                .then()
                .cookie("AEC","AQTF6HypOlJTZa92_QcXBcLhIq8TTWgqJH6ilwdj2PY4sYO6bw6_dp6TaQ")
                .log().all();


    }

    @Test(priority = 2)
    public void testGetAllCookiesOnPage(){

        Response res= (Response) given()
                .when()
                .get("https://www.google.com/");

        // Get single cookies info
//     String cookie_value = res.getCookie("AEC");
//     System.out.println("Cookie value is ----> "+ cookie_value);

        // Get all Cookies Info

        Map<String,String> cookies_values=res.getCookies();

        for(String k:cookies_values.keySet()){

            System.out.println(k+"  "+cookies_values);


        }



    }
}

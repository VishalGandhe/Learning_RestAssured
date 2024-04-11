package com.myprojectstructure.DiffrentWayToCreatePOSTmethod;

import com.myprojectstructure.AllHTTPSRequest.HTTPS;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

//1) using HashMap
//2) using Org.json
//3) using POJO (Plain Old Java Object)
//4) using external json file


public class WaysToCreatePOST_request {

    int id;
    // 1.POST Request Body using HashMap
  @Test(priority = 1)
    void testPostUsingHashMap(){

        HashMap data = new HashMap();

        data.put("name", "Vishal");
        data.put("Job", "Automation");
        given()
                   .contentType("application/json")
                   .body(data)
                .when()
                   .post("https://reqres.in/api/users")


                   .then()
                   .statusCode(201)
                   .body("name",equalTo("Vishal"))
                   .body("Job",equalTo("Automation"))

              //     .header("contentType","application/json")
                   .log().all();
    }
    // 2.POST Request Body using Org.JSON
  @Test(priority = 2)
    void testPostUsingOrgJson(){

        JSONObject data = new JSONObject();

        data.put("name", "Vishal");
        data.put("Job", "Automation");

    //    String courceArray []= {"C", "C++"};
    //    data.put("cource", "coureArray");

        given()
                .contentType("application/json")
                .body(data.toString())
                .when()
                .post("https://reqres.in/api/users")


                .then()
                .statusCode(201)
                .body("name",equalTo("Vishal"))
                .body("Job",equalTo("Automation"))

                //     .header("contentType","application/json")
                .log().all();
    }

        // 3.POST Request Body using POJO Class
     @Test(priority = 3)
   public void testPostUsingPOJOClass(){

        HTTPS.POJO_POSTRequest data = new HTTPS.POJO_POSTRequest();

        data.setName("Vishal");
        data.setJob("Automation");
        given()
                .contentType("application/json")
                .body(data)

                .when()
                .post("https://reqres.in/api/users")

                .then()
                .statusCode(201)
                .body("name",equalTo("Vishal"))
                .body("Job",equalTo("Automation"))
                .log().all();
    }

    // 4.POST Request Body using External JSON file
    @Test(priority = 5)
    public void testPostUsingExternalJSONFile() throws FileNotFoundException {

        File file = new File(".\\body.json");

        FileReader fileReader = new FileReader(file);

        JSONTokener jt = new JSONTokener(fileReader);
        JSONObject paylod = new JSONObject(jt);


        given()
                .contentType("application/json")
                .body(paylod.toString())

                .when()
                .post("https://reqres.in/api/users")

                .then()
                .statusCode(201)
                .body("name",equalTo("Vishal"))
                .body("Job",equalTo("Student"))
                .log().all();
    }


    @Test(priority = 5)
    public void deleteRequest(){

        given()
                .when()
                .delete("https://reqres.in/api/users"+ id)
                .then()
                .statusCode(204);


    }
}

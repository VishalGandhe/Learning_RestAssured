package com.myprojectstructure.AllHTTPSRequest;

import org.testng.annotations.Test;

//import java.util.HashMap;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class HTTPS {

    int id;

    @Test(priority = 1)
    public void getUsers() {


        given()

                .when()
                .get("https://reqres.in/api/users?page=2")

                .then()
                .statusCode(200)
                .body("page", equalTo(2))
                .log().all();
    }

    @Test(priority = 2)
    public void createUser() {

        //Craeted data using datamap
        HashMap data = new HashMap();
        data.put("name", "ankit");
        data.put("job", "student");

        id = given()
                .contentType("application/json")
                .body(data)


                .when()
                .post("https://reqres.in/api/users")
                //json to path send back response int format capture response after post and stored it in variable
                //to send back the response
                .jsonPath().getInt("id");

//                .then()
//                .statusCode(201)
//                .log().all();

    }

    @Test(priority = 3, dependsOnMethods = ("createUser"))
    public void UpdateUser() {


        //Craeted data using datamap
        HashMap data = new HashMap();
        data.put("name", "john");
        data.put("job", "engineer");

        given()
                .contentType("application/json")
                .body(data)

                .when()
                .put("https://reqres.in/api/users/" + id)



                .then()
                .statusCode(200)
                .log().all();
    }

    @Test(priority = 4)
    public void deleteUser(){

        given()

                .when()
                .delete("https://reqres.in/api/users/" + id)

                .then()
                .log().all();
    }

    public static class POJO_POSTRequest {
        String name;
        String job;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getJob() {
            return job;
        }

        public void setJob(String job) {
            this.job = job;
        }

    }
}







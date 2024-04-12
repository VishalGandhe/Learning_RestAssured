package com.myprojectstructure.JSONParsing;

import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static io.restassured.internal.assertion.CookieMatcher.getCookies;
import static org.hamcrest.Matchers.*;

public class ParsingJSONResponseData {

    @Test(priority = 1)
    public void testJSONResponse(){

        //Approch1

       /* given()
                .contentType("ContentType.JSON")

                .when()
                .get("https://dummy.restapiexample.com/api/v1/employees")

                .then()
                .header("Content-Encoding", "gzip")
                .body("data[18].employee_name",equalTo("Bradley Greer"));*/

        //Approch2

                Response res=given()
                .contentType("ContentType.JSON")

                .when()
                .get("https://dummy.restapiexample.com/api/v1/employees");

               Assert.assertEquals(res.getStatusCode(),200);
               Assert.assertEquals(res.header("Content-Encoding"),"gzip");

               String empName= res.jsonPath().get("data[18].employee_name").toString();
               Assert.assertEquals(empName,"Bradley Greer");



    }

    @Test(priority = 2)
    public void testJSONBodyData(){


        Response res=given()
                .contentType("ContentType.JSON")

                .when()
                .get("https://dummy.restapiexample.com/api/v1/employees");

       /* Assert.assertEquals(res.getStatusCode(),200);
        Assert.assertEquals(res.header("Content-Encoding"),"gzip");

        String empName= res.jsonPath().get("data[18].employee_name").toString();
        Assert.assertEquals(empName,"Bradley Greer");*/

        // Using JSON Object Class

        JSONObject jo= new JSONObject(res.asString()); // Converting response into JSON object

        // To print all employee from Data
     /*   for(int i=0; i<jo.getJSONArray("data").length();i++){

          String  allempName= jo.getJSONArray("data").getJSONObject(i).get("employee_name").toString();
            System.out.println(allempName);

        }*/

        // Search for all employee from JSON data
        boolean status = false;
        for(int i=0; i<jo.getJSONArray("data").length();i++){
            String  allempName= jo.getJSONArray("data").getJSONObject(i).get("employee_name").toString();

            if(allempName.equals("Colleen Hurst")){
            status= true;
            break;

        }
        }

        Assert.assertEquals(status,true);


        // Validation for total of all employee salary

        double totalempSalary = 0;

        for(int i=0; i<jo.getJSONArray("data").length();i++){
            String empsalary= jo.getJSONArray("data").getJSONObject(i).get("employee_salary").toString();


            totalempSalary= totalempSalary+Double.parseDouble(empsalary);
            }
        System.out.println("Total of all employee salary is ===>" + totalempSalary);
        Assert.assertEquals(totalempSalary,6644770.0);
    }
}

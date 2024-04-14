package com.myprojectstructure.uploadAndownload;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.File;
import  static io.restassured.response.ValidatableResponse.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import  static io.restassured.matcher.RestAssuredMatchers.*;

public class FileUploadAndDownload {
    @Test(priority = 1)
    public void testSingleFileUpload()
    {
        File myfile = new File("C:\\Users\\Shreeya\\Downloads\\Resume\\testfile.txt");

        given()
                .multiPart("file", myfile)
                .contentType("multipart/form-data")

        .when()
                .post("https://v2.convertapi.com/upload")

                .then()
                .statusCode(200)
                .body("FileName", equalTo("testfile.txt"))
                .log().all();

    }

  //  @Test(groups = 3)
    public void testMultipleFileUpload()
    {
        File myfile1 = new File("C:\\Users\\Shreeya\\Downloads\\Resume\\testfile.txt");
        File myfile2 = new File("C:\\Users\\Shreeya\\Downloads\\Resume\\testimage.jpg");

        given()
                .multiPart("files", myfile2)
                .multiPart("files", myfile2)
                .contentType("multipart/form-data")

                .when()
                .post("https://v2.convertapi.com/upload")

                .then()
                .statusCode(200)
                .body("[0].FileName", equalTo("testfile.txt"))
                .body("[1].FileName", equalTo("testimage.jpg"))
                .log().all();

    }

   // @Test(groups = 4)
    public void testMultipleFileUpload2()


    {
        // Use below method w=if u want upload more than 2 files

        File myfile1 = new File("C:\\Users\\Shreeya\\Downloads\\Resume\\testfile.txt");
        File myfile2 = new File("C:\\Users\\Shreeya\\Downloads\\Resume\\testimage.jpg");

        File fileArr []={myfile1, myfile2};

        given()
                .multiPart("file", fileArr)
                .multiPart("file", fileArr)
                .contentType("multipart/form-data")

                .when()
                .post("https://v2.convertapi.com/upload")

                .then()
                .statusCode(200)
                .body("[0].FileName", equalTo("testfile.txt"))
                .body("[1].FileName", equalTo("testimage.jpg"))
                .log().all();

    }
    @Test(priority = 2)
    public void testDownloadFile()
    {
        given()

                .when()
                .get("https://v2.convertapi.com/d/4b8jew1br9jzzz4u2po39uli84v0sa09")

                .then()
                .statusCode(200)
                .log().body();

    }

    @Test(priority = 3)
    public void testDeleteFile()
    {
        given()

                .when()
                .delete("https://v2.convertapi.com/d/4b8jew1br9jzzz4u2po39uli84v0sa09")

                .then()
                .statusCode(200)
                .log().body();

    }
}



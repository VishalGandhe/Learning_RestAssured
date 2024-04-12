package Headers;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static io.restassured.internal.assertion.CookieMatcher.getCookies;
import static org.hamcrest.Matchers.*;
public class HeaderVerification {

    @Test(priority=1)
    public void testHeader(){

        given()

                .when()
                .get("https://www.google.com/")

                .then()
                .header("Content-Type", "text/html; charset=ISO-8859-1")
                .and()
                .header("Content-Encoding", "gzip")
                .and()
                .header("Server", "gws")
                .log().all();
    }

    @Test(priority=2)
    public void testSingleAndAllHeaderValues(){

      Response res= given()

                .when()
                .get("https://www.google.com/");

      // Get Single Header Value
//        String header_value= res.getHeader("Content-Type");
//        System.out.println("Header value is ===>" + header_value);

       Headers myheaders =res.getHeaders();

        for (Header hd:myheaders){
            System.out.println(hd.getName()+"   "+ hd.getValue());

        }

    }
}



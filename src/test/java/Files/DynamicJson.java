package Files;
import  static io.restassured.RestAssured.*;
import  static      io.restassured.matcher.RestAssuredMatchers.*;
import  static      org.hamcrest.Matchers.*;

import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

public class DynamicJson {

    @Test
    public void AddBook()
    {
        baseURI="http://216.10.245.166";

            String response=
given()
        .header("Content-Type","application/json")
        .body(PayLoad.AddBookBody())


.when()
        .post("/Library/Addbook.php")



.then()
        .assertThat().statusCode(200)
        .extract().response().asString();

        JsonPath js=new JsonPath(response);
        String responseID=js.getString("ID");
        System.out.println(responseID);
    }
}

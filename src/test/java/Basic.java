import Files.PayLoad;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import  static io.restassured.RestAssured.*;
import  static      io.restassured.matcher.RestAssuredMatchers.*;
import  static      org.hamcrest.Matchers.*;

public class Basic {
    public static void main(String[] args) {

baseURI ="https://rahulshettyacademy.com";

//////////////////// /////////post request///////////////////////////////////////////////////////////////////////////////////////////////////
        String postResponse=
given()
        .log().all()
        .queryParam("key","qaclick123")
        .header("Content-Type","application/json")
        .body(PayLoad.callBody())


.when()
        .post("/maps/api/place/add/json")



.then()
        .log().all()
        .assertThat().statusCode(200)
        .body("scope",equalTo("APP"))
        .header("Server","Apache/2.4.52 (Ubuntu)")
        .extract().response().asString();
        JsonPath js=new JsonPath(postResponse);
        String placeID=js.getString("place_id");
        System.out.println(placeID);
       String scopeResponse= js.getString("scope");
        Assert.assertEquals(scopeResponse,"APP");

//////////////////// /////////Update request///////////////////////////////////////////////////////////////////////////////////////////////////
String newAddress="70 Summer walk, USA";
String putResponse=
given()
        .log().all()
        .queryParam("key","qaclick123")
        .header("Content-Type","application/json")
        .body("{\n" +
                "\"place_id\":\""+placeID+"\",\n" +
                "\"address\":\"70 Summer walk, USA\",\n" +
                "\"key\":\"qaclick123\"\n" +
                "}\n")

.when()
        .put("/maps/api/place/update/json")


.then()
        .log().all()
        .assertThat().statusCode(200)
        .extract().response().asString();
        JsonPath js1=new JsonPath(putResponse);
        String successfullyMessageAfterChangeAddress =js1.getString("msg");
        Assert.assertEquals(successfullyMessageAfterChangeAddress
        ,"Address successfully updated");

//////////////////// /////////Get request///////////////////////////////////////////////////////////////////////////////////////////////////

        String getResponse=
given()
        .queryParam("key","qaclick123")
        .queryParam("place_id",placeID)


.when()
        .get("/maps/api/place/get/json")


.then()
        .log().all()
        .extract().response().asString();
         JsonPath js2=new JsonPath(getResponse);
         String currentAddress=js2.getString("address");
         Assert.assertEquals(currentAddress,newAddress);


    }
}

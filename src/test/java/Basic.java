import Files.PayLoad;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class Basic {
    @Test
    public void endToEndApis() {
        baseURI = "https://rahulshettyacademy.com";
// post Request/////////////////////////////////////////////////////////////////////////////////////////////////////////////
        String FirstRequest = given().log().all().queryParam("key", "qaclick123")
                .header("Content-Type", "application/json")
                .body(PayLoad.BodyForPostData())


                .when().post("/maps/api/place/add/json")


                .then().assertThat().statusCode(200)
                .body("scope", equalTo("APP"))
                .header("Server", "Apache/2.4.52 (Ubuntu)")
                .extract().response().asString();

        JsonPath js = new JsonPath(FirstRequest);

        String Status = js.getString("status");
        String Scope = js.getString("scope");
        String placeID = js.getString("place_id");
        System.out.println(Status);
        System.out.println(Scope);
        System.out.println(placeID);
        Assert.assertEquals(Status, "OK");
// update Request/////////////////////////////////////////////////////////////////////////////////////////////////////////////

        String secondRequestPutData = given().queryParam("key", "qaclick123")
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "\"place_id\":\"" + placeID + "\",\n" +
                        "\"address\":\"70 Summer walk, USA\",\n" +
                        "\"key\":\"qaclick123\"\n" +
                        "}")

                .when().put("/maps/api/place/update/json")

                .then().log().all().assertThat().statusCode(200)
                .extract().response().asString();
        JsonPath js2 = new JsonPath(secondRequestPutData);
        String MSGResponse = js2.getString("msg");

        Assert.assertEquals(MSGResponse, "Address successfully updated");

// get request  /////////////////////////////////////////////////////////////////////////////////////////////////////////////
        given().queryParam("key", "qaclick123")
                .queryParam("place_id", placeID)

                .when().get("/maps/api/place/get/json")

                .then().assertThat().statusCode(200)
                .extract().response().asString();
// Delete request  /////////////////////////////////////////////////////////////////////////////////////////////////////////////


        String DeleteRequest = given().queryParam("key", "qaclick123")
                .body("{\n" +
                        "    \"place_id\":\"" + placeID + "\"\n" +
                        "}")


                .when().delete("/maps/api/place/delete/json")


                .then().assertThat().statusCode(200)
                .extract().response().asString();
        JsonPath js3 = new JsonPath(DeleteRequest);
        String statusCode = js3.getString("status");
        Assert.assertEquals(statusCode, "OK");
    }
}

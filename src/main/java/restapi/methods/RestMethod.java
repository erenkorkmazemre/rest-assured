package restapi.methods;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONObject;
import org.junit.Assert;
import org.testng.asserts.SoftAssert;
import restapi.Util;
import restapi.config.RandomString;
import restapi.objects.MockInfo;

import java.security.SecureRandom;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static io.restassured.RestAssured.given;

public class RestMethod {

    Util util = new Util();

    String baseURI = util.getURL();
    String baseURI2 = util.getURL();
    String token = util.getToken();
    Response getResponse = null;
    Response postResponse = null;
    SoftAssert softAssert = new SoftAssert();

    public void listUserId() {


        getResponse = given()
                .contentType("application/json; charset=UTF-8")
                .when()
                .get("/users")
                .then()
                .log().body()
                .statusCode(200)
                .extract().response();
    }

    public void listUserIdFrom() {


        getResponse = given()
                .contentType("application/json; charset=UTF-8")
                .when()
                .get("1/users")
                .then()
                .log().body()
                .statusCode(200)
                .extract().response();
    }

    public void checkUserDigit() {

        List<Object> idList = getResponse.jsonPath().getList("data.id");
        for (Object id : idList) {
            Assert.assertEquals(id.toString().length(), 6);
        }
    }

    public void checkUserDigitFrom() {
        List<Object> idList = getResponse.jsonPath().getList("id");
        System.out.printf("ID:" + idList);
        Assert.assertEquals(idList.size(), 10);
    }

    public void postUser(JSONObject userData, int expectedStatusCode) {


        postResponse = given()
                .contentType("application/json; charset=UTF-8")
                .header("Authorization", "Bearer " + token)
                .body(userData.toString())
                .when()
                .post("/users")
                .then()
                .log().body()
                .statusCode(expectedStatusCode)
                .extract().response();
    }

    public void postUserLogin(JSONObject userData, int expectedStatusCode) {
        RestAssured.baseURI = "URI"; // TO-DO
        baseURI = "URI";

        postResponse = given()
                .contentType("application/json; charset=UTF-8")
                .body(userData.toString())
                .when()
                .post("")
                .then()
                .log().body()
                .statusCode(expectedStatusCode)
                .extract().response();
        util.setToken(postResponse.jsonPath().get("accessToken"));
        softAssert.assertEquals(System.getProperty("ACCESS_TOKEN"), "EREN");
        softAssert.assertSame("not token", "token", "Expected and Current URL are not same");
        Assert.assertEquals("not token", "token", "Expected and Current URL are not same");
    }

    public void postMockData(int expectedStatusCode) {

        String randomStringUpper = RandomStringUtils.randomAlphanumeric(20).toUpperCase();
        String randomStringLower = RandomStringUtils.randomAlphanumeric(20).toLowerCase();

        MockInfo mockInfo = new MockInfo(randomStringUpper, randomStringLower);

        RestAssured.baseURI = "http://localhost:8080/api/v1/students";

        JSONObject json = new JSONObject(mockInfo);
        System.out.println(json);
        System.out.println(mockInfo.toString());

        postResponse = given()
                .contentType("application/json; charset=UTF-8")
                .body(json.toString())
                .when()
                .post("/")
                .then()
                .log().body()
                .statusCode(expectedStatusCode)
                .extract().response();

    }

    public void postMockJsonData(JSONObject userData, int expectedStatusCode) {

        RestAssured.baseURI = "http://localhost:8080/api/v1/students";

        postResponse = given()
                .contentType("application/json; charset=UTF-8")
                .body(userData.toString())
                .when()
                .post("/")
                .then()
                .log().body()
                .statusCode(expectedStatusCode)
                .extract().response();

    }

    public void checkCreatedUser(JSONObject userData) {

        Assert.assertEquals(userData.get("name"), postResponse.getBody().jsonPath().get("data.name"));
        Assert.assertEquals(userData.get("email"), postResponse.getBody().jsonPath().get("data.email"));
        Assert.assertEquals(userData.get("gender"), postResponse.getBody().jsonPath().get("data.gender"));
        Assert.assertEquals(userData.get("status"), postResponse.getBody().jsonPath().get("data.status"));
    }

    public void checkResponseBody(String expectedMessage) {
        Assert.assertEquals(expectedMessage, postResponse.getBody().jsonPath().get("data[0].message"));
    }

}

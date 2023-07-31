package steps.api;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.json.JSONObject;
import restapi.ReadData;
import restapi.methods.RestMethod;

public class RestCaseStudy {

    RestMethod restMethod = new RestMethod();
    ReadData readData = new ReadData();

    @Given("Send GET request to list all users")
    public void sendGETRequestToListAllUsers() {
        restMethod.listUserId();
    }

    @Given("Send GET request to list all users from ")
    public void sendGETRequestToListAllUsersFrom() {
        restMethod.listUserIdFrom();
    }

    @Then("Check all users with proper digits")
    public void checkAllUsersHaveProperDigits() {
        restMethod.checkUserDigit();
    }

    @Then("Check all users with proper digits from ")
    public void checkAllUsersHaveProperDigitsFrom() {
        restMethod.checkUserDigitFrom();
    }

    @And("^Send POST request to add a (.*) with expected code (.*)$")
    public void sendPOSTRequestToAddANewUser(String user, int expectedStatusCode) {
        restMethod.postUser(readData.getTestData(user), expectedStatusCode);
    }

    @And("^Send POST request to login a (.*) with expected code (.*)$")
    public void sendPOSTRequestToLogin(String user, int expectedStatusCode) {
        restMethod.postUserLogin(readData.getTestData(user), expectedStatusCode);
    }

    @And("^Create mock random data with expected code (.*)$")
    public void sendPOSTRequestForMockData(int expectedStatusCode) {
        restMethod.postMockData(expectedStatusCode);
    }

    @And("^Create mock json (.*) data with expected code (.*)$")
    public void sendPOSTRequestForMockJsonData(String userData, int expectedStatusCode) {
        restMethod.postMockJsonData(readData.getTestData(userData), expectedStatusCode);
    }

    @Then("^Check new (.*) is created correctly$")
    public void checkNewUserIsCreatedCorrectly(String user) {
        restMethod.checkCreatedUser(readData.getTestData(user));
    }

    @Then("^Check the response (.*)$")
    public void checkTheResponse(String expectedMessage) {
        restMethod.checkResponseBody(expectedMessage);
    }
}

package com.keepit;

import com.keepit.Helper.Helper;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;


public class ApiNegativeTests {
    // Responses
    String responseMessage = "Anonymous access denied - suggest authentication.";
    String responseMessageWrongId = "Account not found.";
    String responseMessageWrongEndpoind = "Requested resource not found";
    String responseMessageWrongCred = "User authentication error:\n" +
            " Username or password is incorrect.\n";

    // Authrization
    String email = "automation@keepitqa.com";
    String password = "E#*b2wGIbFHz";

    // URI path
    String endPoint = "users/";
    String incorrectUserId = RandomStringUtils.randomAlphabetic(6);
    String incorrectEndPoint = RandomStringUtils.randomAlphabetic(7);

    Response response;

    /* Non auth user
     Verify endpoint for list of users*/

    // Verify error message
    @Test
    public void testResponseMessage() {
        response = Helper.getRequestNoAuth(endPoint);
        Assert.assertEquals(response.asString().trim(), responseMessage.trim());
    }

    /* Non auth user
     Verify endpoint with wrong user Id */

    // Verify Status code 401
    @Test
    public void testWrongUserIdStatusCode() {
        Helper.getRequestNoAuth(endPoint, incorrectUserId)
                .then()
                .statusCode(401);
    }

    // Verify error message
    @Test
    public void testWrongUserIdResponseMessage() {
        response = Helper.getRequestNoAuth(endPoint, incorrectUserId);
        Assert.assertEquals(response.asString().trim(), responseMessage.trim());
    }

     /* No Auth user
     Verify wrong endpoint behavior */

    // Verify Status code 404 for wrong
    @Test
    public void testWrongEndPointNoAuthStatusCode() {
        Helper.getRequestNoAuth(incorrectEndPoint)
                .then()
                .statusCode(404);
    }

    // Verify error message for wrong end point
    @Test
    public void testWrongEndPointNoAuthResponseMessage() {
        response = Helper.getRequestNoAuth(incorrectEndPoint);
        Assert.assertEquals(response.asString().trim(), responseMessageWrongEndpoind.trim());
    }

    /* Auth user (with right cred)
     Verify endpoint with wrong user Id */

    // Verify Status code 404 for user with wrong Id
    @Test
    public void testWrongAuthUserIdStatusCode() {
        Helper.getRequestAuth(endPoint, incorrectUserId)
                .then()
                .statusCode(404);
    }

    // Verify error message for user with wrong Id
    @Test
    public void testWrongAuthUserIdResponseMessage() {
        response = Helper.getRequestAuth(endPoint, incorrectUserId);
        Assert.assertEquals(response.asString().trim(), responseMessageWrongId.trim());
    }

    /* Auth user (with right cred)
     Verify wrong endpoint behavior */

    // Verify Status code 404 for wrong
    @Test
    public void testWrongEndPointStatusCode() {
        Helper.getRequestAuth(incorrectEndPoint)
                .then()
                .statusCode(404);
    }

    // Verify error message for wrong end point
    @Test
    public void testWrongEndPointResponseMessage() {
        response = Helper.getRequestAuth(incorrectEndPoint);
        Assert.assertEquals(response.asString().trim(), responseMessageWrongEndpoind.trim());
    }

    // Login with incorrect password
    @Test
    public void testIncorrectPasswordLogin() {
        String wrongPass = "wrongPass";

        response = Helper.getRequestAuth(endPoint, email, wrongPass);

        Assert.assertEquals(response.statusCode(), 401);
        Assert.assertEquals(response.asString().trim(), responseMessageWrongCred.trim());
    }

    // login with incorrect email
    @Test
    public void testIncorrectEmailLogin() {
        String wrongEmail = "wrongEmail";

        response = Helper.getRequestAuth(endPoint, wrongEmail, password);

        Assert.assertEquals(response.statusCode(), 401);
        Assert.assertEquals(response.asString().trim(), responseMessageWrongCred.trim());
    }
}

package com.keepit.Helper;

import com.keepit.User.UserNotFound;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class Helper {
    static String uri = "https://ws-test.keepit.com/";
    static String email = "automation@keepitqa.com";
    static String password = "E#*b2wGIbFHz";

    public static RequestSpecification login()
    {
        return given()
                .auth()
                .preemptive()
                .basic(email, password)
                .when();
    }
    // get Request No Auth User
    public static Response getRequestAuth(String endPoint){
        return login()
                .get(uri + endPoint);
    }

    public static Response getRequestAuth(String endPoint, String userId){
        return login()
                .get(uri + endPoint + userId);
    }

    public static Response getRequestAuth(String endPoint, String email, String password){
        return given()
                .auth()
                .preemptive()
                .basic(email, password)
                .when()
                .get(uri + endPoint);
    }

    // get Request No Auth User
    public static Response getRequestNoAuth(String endPoint){
        return given()
                .get(uri + endPoint);
    }

    public static Response getRequestNoAuth(String endPoint, String userId){
        return given()
                .get(uri + endPoint + userId);
    }

    public static UserNotFound userNotFoundBodyContent(String endPoint, String incorrectUserId){
            Response response = Helper.getRequestAuth(endPoint, incorrectUserId);
            return new UserNotFound(response.statusCode(), response.asString().trim());
    }
}

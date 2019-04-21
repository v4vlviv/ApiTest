package com.keepit;

import com.keepit.Helper.Helper;
import com.keepit.Id.Id;
import com.keepit.User.User;
import com.keepit.User.UserNotFound;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.*;

import static io.restassured.internal.matcher.xml.XmlXsdMatcher.matchesXsdInClasspath;
import static org.hamcrest.Matchers.equalTo;

public class ApiPositiveTests {

    String userId = "zhc4v6-5ev7di-9hhhlm";

    // URI path
    String endPoint = "users/";
    String responseMessageWrongId = "Account not found.";

    // Validate xml schema for users
    @Test
    public void testUsersIdListSchemaXml() {
        Helper.getRequestAuth(endPoint)
                .then()
                .body(matchesXsdInClasspath("usersIdList.xsd"));
    }

    // Validate xml schema for user
    @Test
    public void testUserSchemaXml() {
        Helper.getRequestAuth(endPoint, userId)
                .then()
                .body(matchesXsdInClasspath("user.xsd"));
    }

    // Validate xml schema for user from (JobApplicationTest_QAAutomationTester.pdf file)
    // This schema has difference from response schema
    @Test
    public void testUsersSchemaXml() {
        Helper.getRequestAuth(endPoint, userId)
                .then()
                .body(matchesXsdInClasspath("userSchema.xsd"));
    }

    // Non auth user

    // Verify Status code 200
    @Test
    public void testLoginStatusCode() {
        Helper.getRequestAuth(endPoint)
                .then()
                .statusCode(200);
    }

    // Verify user Id in the userId list
    @Test
    public void testBodyContent() {
        Helper.getRequestAuth(endPoint)
                .then()
                .assertThat()
                .body("user.id", equalTo(userId));
    }

    // Verify user body content
    @Test
    public void testUserBodyContent() {
        User user = Helper.getRequestAuth(endPoint,userId).as(User.class);
        User userTest = new User("80ltks-yhfls5-24zyf2", true, "7dwqnq-5cvrcm-1z3ehj",
                "2019-02-28T13:07:49Z", true);

        Assert.assertEquals(user.toString(), userTest.toString());
    }

    // Verify user body content
    @Test
    public void testUserNotFoundBodyContent() {
        String incorrectUserId = RandomStringUtils.randomAlphabetic(6);
        // Expected
        UserNotFound userNotFoundTest = new UserNotFound(404, responseMessageWrongId);
        // Actual
        UserNotFound userNotFound = Helper.userNotFoundBodyContent(endPoint, incorrectUserId);

        Assert.assertEquals(userNotFound.toString(), userNotFoundTest.toString());
    }

    // Verify user body content
    @Test
    public void testIdBodyContent() {
        Id id = Helper.getRequestAuth(endPoint).as(Id.class);
        Id idTest = new Id("zhc4v6-5ev7di-9hhhlm");

        Assert.assertEquals(id.toString(), idTest.toString());
    }

}

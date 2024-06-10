package restassured;

import helpers.PasswordStringGenerator;
import interfaces.TestHelper;
import io.restassured.http.ContentType;
import models.NewUserModel;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class SecurityTest implements TestHelper {
    @Test
    public void registrationXSSTest() {
        NewUserModel newUserModel = new NewUserModel("<script>alert('test')</script>",
                PasswordStringGenerator.generateRandomPassword());
        given().body(newUserModel)
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL+REGISTRATION_PATH)
                .then()
                .assertThat()
                .statusCode(400);
    }
}

package restassured;
import static org.hamcrest.Matchers.containsString;
import helpers.EmailGenerator;
import helpers.PasswordStringGenerator;
import interfaces.TestHelper;
import io.qameta.allure.Description;
import io.restassured.http.ContentType;
import models.NewUserModel;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class RegistrationTests implements TestHelper {
    @Test
    public void registrationPositive(){
        NewUserRegistrationMethod.newUserRegistration(EmailGenerator.generateEmail(5,3,2),
                PasswordStringGenerator.generateRandomPassword());
    }

    @Test
    @Description("Registration negative")
    public void registrationNegative(){
        NewUserModel newUserModel = new NewUserModel(
                PasswordStringGenerator.generateRandomPassword(),
                PasswordStringGenerator.generateRandomPassword());
        given()
                .body(newUserModel)
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL+REGISTRATION_PATH)
                .then()
                .statusCode(400).assertThat()
                .body("message.username", containsString("email"));
    }
    @Test
    @Description("Registration negative")
    public void registrationNegativeDuplicateUser(){
        String email = EmailGenerator.generateEmail(5,3,2);

        NewUserRegistrationMethod.newUserRegistration(email,
                PasswordStringGenerator.generateRandomPassword());

        NewUserModel newUserModel = new NewUserModel(email,
                PasswordStringGenerator.generateRandomPassword());
        given()
                .body(newUserModel)
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL+REGISTRATION_PATH)
                .then().assertThat()
                .statusCode(409);
    }
}

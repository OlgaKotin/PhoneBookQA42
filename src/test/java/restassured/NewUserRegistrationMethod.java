package restassured;
import io.restassured.http.ContentType;
import models.NewUserModel;
import static interfaces.TestHelper.*;
import static io.restassured.RestAssured.given;

public class NewUserRegistrationMethod {
//    public static void main(String[] args) {
//        newUserRegistration(EmailGenerator.generateEmail(5,3,2),
//                PasswordStringGenerator.generateRandomPassword());
//    }
    public static void newUserRegistration(String email, String password){
        NewUserModel newUserModel = new NewUserModel(email, password);
        String newToken = given()
                .body(newUserModel)
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL+REGISTRATION_PATH)
                .then().assertThat()
                .statusCode(200)
                .extract().path("token");
        System.out.println("New token: "+newToken);
    }
}

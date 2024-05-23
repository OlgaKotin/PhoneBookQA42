package restassured;

import helpers.PropertiesReaderXML;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import models.AuthenticationRequestModel;
import models.AuthenticationResponseModel;
import static interfaces.TestHelper.*;
import static io.restassured.RestAssured.given;

public class LoginMethod {
    public static void login() {
        RestAssured.baseURI = BASE_URL+LOGIN_PATH;
        AuthenticationRequestModel request = AuthenticationRequestModel
                .username(PropertiesReaderXML.getProperty("myuser", XML_FILE_PATH))
                .password(PropertiesReaderXML.getProperty("mypassword", XML_FILE_PATH));
        AuthenticationResponseModel response = given()
                .body(request)
                .contentType(ContentType.JSON)
                .when()
                .post().then().log().all()
                .assertThat()
                .statusCode(200)
                .extract().as(AuthenticationResponseModel.class);
        response.getToken();
    }
}

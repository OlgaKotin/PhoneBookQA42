package restassured;

import config.TestData;
import helpers.EmailGenerator;
import helpers.PasswordStringGenerator;
import helpers.PropertiesReaderXML;
import helpers.PropertiesWriterXML;
import interfaces.TestHelper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import models.AuthenticationRequestModel;
import models.AuthenticationResponseModel;
import models.ErrorModel;
import models.NewUserModel;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

import static io.restassured.RestAssured.given;

public class LoginTest implements TestHelper {
    @Test
    public void registrationPositive(){
        NewUserModel newUserModel = new NewUserModel(EmailGenerator.generateEmail(5,5,3),
                PasswordStringGenerator.generateRandomPassword());
        String token = given().body(newUserModel).contentType(ContentType.JSON)
                .when()
                .post(BASE_URL+REGISTRATION_PATH)
                .then()
                .assertThat()
                .statusCode(200)
                .extract().path("token");
        System.out.println("Token: "+token);
    }

    @Test
    public void loginPositiveTest() throws FileNotFoundException {
        RestAssured.baseURI = BASE_URL+LOGIN_PATH;
        AuthenticationRequestModel authenticationRequestModel =
                AuthenticationRequestModel
                        .username(PropertiesReaderXML.getProperty("myuser", XML_FILE_PATH))
                        .password(PropertiesReaderXML.getProperty("mypassword", XML_FILE_PATH));
        AuthenticationResponseModel response = given()
                .body(authenticationRequestModel)
                .contentType(ContentType.JSON)
                .when()
                .post().then().log().all()
                .assertThat()
                .statusCode(200)
                .extract().as(AuthenticationResponseModel.class);
        String token = response.getToken();
        PropertiesWriterXML propertiesWriterXML = new PropertiesWriterXML();
        propertiesWriterXML.setProperty(TOKEN, response.getToken(), false, XML_FILE_PATH);
    }
    @Test(dataProvider = "loginData", dataProviderClass = TestData.class)
    public void loginNegative(String username, String password){
        AuthenticationRequestModel authenticationRequestModel =
                AuthenticationRequestModel
                        .username(username)
                        .password(password);
        ErrorModel errorModel = given()
                .body(authenticationRequestModel)
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL+LOGIN_PATH)
                .then().log().all()
                .assertThat()
                .statusCode(401)
                .extract().as(ErrorModel.class);
        System.out.println("++++ MESSAGE : "+errorModel.getError()+" : "+errorModel.getMessage());
    }


/*
    @Test
    public void registrationPositive(){
        NewUserModel newUserModel = new NewUserModel(EmailGenerator.generateEmail(5,5,3),
                PasswordStringGenerator.generateRandomPassword());
        String token = given().body(newUserModel).contentType(ContentType.JSON)
                .when()
                .post(BASE_URL+REGISTRATION_PATH)
                .then()
                .assertThat()
                .statusCode(200)
                .extract().path("token");
        System.out.println("Token: "+token);
    }


    @Test
    public void loginPositiveTest(){
     LoginMethod.login();
    }
    @Test(dataProvider = "loginData", dataProviderClass = TestData.class)
    public void loginNegative(String username, String password){
        AuthenticationRequestModel authenticationRequestModel =
                AuthenticationRequestModel
                        .username(username)
                        .password(password);
        ErrorModel errorModel = given()
                .body(authenticationRequestModel)
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL+LOGIN_PATH)
                .then().log().all()
                .assertThat()
                .statusCode(401)
                .extract().as(ErrorModel.class);
        System.out.println("++++ MESSAGE : "+errorModel.getError()+" : "+errorModel.getMessage());
    } */

}
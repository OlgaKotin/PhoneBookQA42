package restassured;

import helpers.*;
import io.restassured.http.ContentType;
import models.Contact;

import static interfaces.TestHelper.*;
import static io.restassured.RestAssured.given;

public class AddNewContactMethod {
    public static void addNewContact(String name, String lastName, String email,
                                     String phone, String address, String token, int code){
        Contact contact = new Contact(name, lastName, email, phone,address, "desc");
        given()
                .header(AUTHORIZATION_HEADER, token)
                .body(contact)
                .contentType(ContentType.JSON)
                .when().post(BASE_URL+ADD_CONTACT)
                .then().assertThat().statusCode(code);
    }
}

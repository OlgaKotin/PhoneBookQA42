package tests;

import db.DataBaseConnection;
import db.DatabaseReader;
import helpers.ContactGenerator;
import helpers.EmailGenerator;
import helpers.IDExtractor;
import helpers.PropertiesReaderXML;
import interfaces.TestHelper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import models.Contact;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class UpdateDeleteContactUsingDatabaseTest implements TestHelper {
    Contact contact;
    String id;

    @BeforeMethod
    public void precondition() throws SQLException {
        RestAssured.baseURI = BASE_URL + ADD_CONTACT;
        contact = ContactGenerator.createCorrectContact();

        String message = given().header(AUTHORIZATION_HEADER, PropertiesReaderXML.getProperty("token", XML_FILE_PATH))
                .body(contact)
                .contentType(ContentType.JSON)
                .when().post().then().assertThat().statusCode(200).extract().path("message");
        System.out.println("Message: "+message);
        id = IDExtractor.getId(message);

        DataBaseConnection dbConnection = new DataBaseConnection();
        dbConnection.contactDatabaseRecorder(id, contact);
    }

    @Test
    public void updateContactTest() throws SQLException {
        contact.setId(id);
        contact.setEmail(EmailGenerator.generateEmail(5,3,2));
        given().header(AUTHORIZATION_HEADER, PropertiesReaderXML.getProperty("token", XML_FILE_PATH))
                .body(contact)
                .contentType(ContentType.JSON)
                .when().put().then().assertThat().statusCode(200);
        Contact changedContact = DatabaseReader.readContactFromDatabase(id);
        Assert.assertNotEquals(changedContact.getEmail(), contact.getEmail());
    }

}

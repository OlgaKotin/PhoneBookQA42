package okhttp;

import helpers.PropertiesReaderXML;
import helpers.PropertiesWriterXML;
import interfaces.TestHelper;
import models.*;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class DeleteAllContacts implements TestHelper {
    @BeforeTest
    public void loginAndGetToken() throws IOException {
        AuthenticationRequestModel requestModel =
                AuthenticationRequestModel
                        .username(PropertiesReaderXML.getProperty("myuser", XML_FILE_PATH))
                        .password(PropertiesReaderXML.getProperty("mypassword", XML_FILE_PATH));
        RequestBody requestBody = RequestBody.create(GSON.toJson(requestModel), JSON);
        Request request =new Request.Builder().url(BASE_URL+LOGIN_PATH)
                .post(requestBody).build();
        Response response = CLIENT.newCall(request).execute();
        if(response.isSuccessful()){
            AuthenticationResponseModel responseModel =
                    GSON.fromJson(response.body().string(), AuthenticationResponseModel.class);
            PropertiesWriterXML propertiesWriterXML = new PropertiesWriterXML();
            propertiesWriterXML.setProperty(TOKEN,responseModel.getToken(),false, XML_FILE_PATH);
            Assert.assertTrue(response.isSuccessful());
            System.out.println("Token is successfully saved");
        } else {
            System.out.println("Status code: " +response.code());
            ErrorModel errorModel = GSON.fromJson(response.body().string(), ErrorModel.class);
            System.out.println("Error status: " +errorModel.getStatus());
        }
    }
    
public void deleteAllContacts (String token) throws IOException {
    Request request = new Request.Builder()
            .url(BASE_URL+DELETE_ALL_CONTACTS)
            .addHeader(AUTHORIZATION_HEADER, token)
            .delete()
            .build();
    Response response = CLIENT.newCall(request).execute();
    System.out.println("Code: " + response.code());
    ContactResponseModel contactResponseModel =
            GSON.fromJson(response.body().string(),
                    ContactResponseModel.class);
    System.out.println("Message: "+contactResponseModel.getMessage());
}

    @Test
    public void deleteAllContactsPositive() throws IOException {
        deleteAllContacts(PropertiesReaderXML.getProperty("token", XML_FILE_PATH));
    }
    @Test
    public void deleteAllContactsFromEmptyList() throws IOException {
    deleteAllContacts(PropertiesReaderXML.getProperty("token", XML_FILE_PATH));
    deleteAllContacts(PropertiesReaderXML.getProperty("token", XML_FILE_PATH));
    }

    @Test
    public void deleteAllContactsNegative() throws IOException {
        Request request = new Request.Builder()
                .url(BASE_URL+DELETE_ALL_CONTACTS)
                .addHeader(AUTHORIZATION_HEADER, "tdgdhdh.hhfhfhfjd2453g")
                .delete()
                .build();
        Response response = CLIENT.newCall(request).execute();
        ErrorModel errorModel = GSON.fromJson(response.body().string(), ErrorModel.class);
        System.out.println(errorModel.getStatus());
        Assert.assertEquals(errorModel.getStatus(), 401);
    }
}

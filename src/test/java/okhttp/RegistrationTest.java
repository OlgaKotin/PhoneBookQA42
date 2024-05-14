package okhttp;

import helpers.EmailGenerator;
import helpers.PasswordStringGenerator;
import helpers.PropertiesReaderXML;
import interfaces.TestHelper;
import models.AuthenticationResponseModel;
import models.ErrorModel;
import models.NewUserModel;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class RegistrationTest implements TestHelper {

    public void unsuccessfulRegistration(String email, String password, int errorCode) throws IOException {
        NewUserModel newUserModel = new NewUserModel(email, password);
        RequestBody requestBody = RequestBody.create(GSON.toJson(newUserModel), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL+REGISTRATION_PATH)
                .post(requestBody)
                .build();
        Response response = CLIENT.newCall(request).execute();
        String res = response.body().string();

        if(!response.isSuccessful()){
            System.out.println("Status code: " +response.code());
            Assert.assertEquals(response.code(), errorCode);
        } else {
            System.out.println("Wrong test: "+response.code());
        }
    }

    @Test
    public void registrationTestPositive() throws IOException {
        NewUserModel newUserModel = new NewUserModel(EmailGenerator.generateEmail(5,4,2),
                PasswordStringGenerator.generateRandomPassword());
        RequestBody requestBody = RequestBody.create(GSON.toJson(newUserModel), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL+REGISTRATION_PATH)
                .post(requestBody)
                .build();
        Response response = CLIENT.newCall(request).execute();
        String res = response.body().string();

        if(response.isSuccessful()){
            AuthenticationResponseModel responseModel =
                    GSON.fromJson(res, AuthenticationResponseModel.class);
            String resultToken = responseModel.getToken();
            Assert.assertTrue(response.isSuccessful());
        } else {
            ErrorModel errorModel = GSON.fromJson(res, ErrorModel.class);
            System.out.println(response.code());
        }
    }
    @Test
    public void registrationTestNegativeWrongEmail() throws IOException {
        unsuccessfulRegistration("wrong_email",
                PasswordStringGenerator.generateRandomPassword(), 400);
    }
    @Test
    public void registrationTestNegativeWrongPassword() throws IOException {
        unsuccessfulRegistration(EmailGenerator.generateEmail(5,4,2),
                "1234", 400);
    }
    @Test
    public void registrationTestNegativeDuplicateUser() throws IOException {
        unsuccessfulRegistration(PropertiesReaderXML.getProperty("myuser", XML_FILE_PATH),
                PropertiesReaderXML.getProperty("mypassword", XML_FILE_PATH), 409);
    }
}

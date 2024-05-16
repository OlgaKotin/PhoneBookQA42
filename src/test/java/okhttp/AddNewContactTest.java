package okhttp;

import com.google.gson.Gson;
import helpers.*;
import interfaces.TestHelper;
import models.Contact;
import models.ContactResponseModel;
import models.ErrorModel;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class AddNewContactTest implements TestHelper {
    public void addNewContactPositiveTest(String name, String lastName, String phone,
                                          String email, String address) throws IOException {
        Contact contact = new Contact(name, lastName, phone, email, address, "desc");
        RequestBody requestBody = RequestBody.create(GSON.toJson(contact), JSON);
        Request request = new Request.Builder().url(BASE_URL+ADD_CONTACT)
                .addHeader(AUTHORIZATION_HEADER, PropertiesReaderXML.getProperty("token", TestHelper.XML_FILE_PATH))
                .post(requestBody)
                .build();
        Response response = CLIENT.newCall(request).execute();
        ContactResponseModel contactResponseModel =
                GSON.fromJson(response.body().string(), ContactResponseModel.class);
        System.out.println("Message: "+contactResponseModel.getMessage());
        String id = IDExtractor.getId(contactResponseModel.getMessage());
        Assert.assertTrue(response.isSuccessful());
    }
    public void addNewContactNegativeTest(String name, String lastName, String phone,
                                          String email, String address, int errorCode) throws IOException {
        Contact contact = new Contact(name, lastName, phone, email, address, "desc");
        RequestBody requestBody = RequestBody.create(GSON.toJson(contact), JSON);
        Request request = new Request.Builder().url(BASE_URL+ADD_CONTACT)
                .addHeader(AUTHORIZATION_HEADER, PropertiesReaderXML.getProperty("token", TestHelper.XML_FILE_PATH))
                .post(requestBody)
                .build();
        Response response = CLIENT.newCall(request).execute();
        ErrorModel errorModel = GSON.fromJson(response.body().string(), ErrorModel.class);
        Assert.assertEquals(response.code(), errorCode);
    }
    @Test
    public void addNewContactPositive() throws IOException {
        addNewContactPositiveTest(NameAndLastNameGenerator.generateName(),
                NameAndLastNameGenerator.generateLastName(),
                PhoneNumberGenerator.generatePhoneNumber(),
                EmailGenerator.generateEmail(5,3,2),
                AddressGenerator.generateAddress());
    }
    @Test
    public void addNewContactNegativeWrongName1() throws IOException {
        addNewContactNegativeTest("", NameAndLastNameGenerator.generateLastName(),
                PhoneNumberGenerator.generatePhoneNumber(),
                EmailGenerator.generateEmail(5,3,2),
                AddressGenerator.generateAddress(), 400);
    }
    @Test
    public void addNewContactNegativeWrongName2() throws IOException {
        addNewContactNegativeTest(null, NameAndLastNameGenerator.generateLastName(),
                PhoneNumberGenerator.generatePhoneNumber(),
                EmailGenerator.generateEmail(5,3,2),
                AddressGenerator.generateAddress(), 400);
    }
    @Test
    public void addNewContactNegativeWrongLastName1() throws IOException {
        addNewContactNegativeTest(NameAndLastNameGenerator.generateName(),
                "", PhoneNumberGenerator.generatePhoneNumber(),
                EmailGenerator.generateEmail(5,3,2),
                AddressGenerator.generateAddress(), 400);
    }
    @Test
    public void addNewContactNegativeWrongLastName2() throws IOException {
        addNewContactNegativeTest(NameAndLastNameGenerator.generateName(),
                null, PhoneNumberGenerator.generatePhoneNumber(),
                EmailGenerator.generateEmail(5,3,2),
                AddressGenerator.generateAddress(), 400);
    }
    @Test
    public void addNewContactNegativeWrongPhone1() throws IOException {
        addNewContactNegativeTest(NameAndLastNameGenerator.generateName(),
                NameAndLastNameGenerator.generateLastName(), "123456789",
                EmailGenerator.generateEmail(5,3,2),
                AddressGenerator.generateAddress(), 400);
    }
    @Test
    public void addNewContactNegativeWrongPhone2() throws IOException {
        addNewContactNegativeTest(NameAndLastNameGenerator.generateName(),
                NameAndLastNameGenerator.generateLastName(), "wrongphone!",
                EmailGenerator.generateEmail(5,3,2),
                AddressGenerator.generateAddress(), 400);
    }
    @Test
    public void addNewContactNegativeWrongPhone3() throws IOException {
        addNewContactNegativeTest(NameAndLastNameGenerator.generateName(),
                NameAndLastNameGenerator.generateLastName(), "1234567891234567",
                EmailGenerator.generateEmail(5,3,2),
                AddressGenerator.generateAddress(), 400);
    }
    @Test
    public void addNewContactNegativeWrongPhone4() throws IOException {
        addNewContactNegativeTest(NameAndLastNameGenerator.generateName(),
                NameAndLastNameGenerator.generateLastName(), "",
                EmailGenerator.generateEmail(5,3,2),
                AddressGenerator.generateAddress(), 400);
    }
    @Test
    public void addNewContactNegativeWrongPhone5() throws IOException {
        addNewContactNegativeTest(NameAndLastNameGenerator.generateName(),
                NameAndLastNameGenerator.generateLastName(), null,
                EmailGenerator.generateEmail(5,3,2),
                AddressGenerator.generateAddress(), 400);
    }
    @Test
    public void addNewContactNegativeWrongEmail1() throws IOException {
        addNewContactNegativeTest(NameAndLastNameGenerator.generateName(),
                NameAndLastNameGenerator.generateLastName(),
                PhoneNumberGenerator.generatePhoneNumber(), "wrong.email",
                AddressGenerator.generateAddress(), 400);
    }
    @Test
    public void addNewContactNegativeWrongEmail2() throws IOException {
        addNewContactNegativeTest(NameAndLastNameGenerator.generateName(),
                NameAndLastNameGenerator.generateLastName(),
                PhoneNumberGenerator.generatePhoneNumber(), "wrong@email@com",
                AddressGenerator.generateAddress(), 400);
    }
    @Test
    public void addNewContactNegativeWrongEmail3() throws IOException {
        addNewContactNegativeTest(NameAndLastNameGenerator.generateName(),
                NameAndLastNameGenerator.generateLastName(),
                PhoneNumberGenerator.generatePhoneNumber(), "wrongemail",
                AddressGenerator.generateAddress(), 400);
    }
    @Test
    public void addNewContactNegativeWrongEmail4() throws IOException {
        addNewContactNegativeTest(NameAndLastNameGenerator.generateName(),
                NameAndLastNameGenerator.generateLastName(),
                PhoneNumberGenerator.generatePhoneNumber(), "",
                AddressGenerator.generateAddress(), 400);
    }
    @Test
    public void addNewContactNegativeWrongEmail5() throws IOException {
        addNewContactNegativeTest(NameAndLastNameGenerator.generateName(),
                NameAndLastNameGenerator.generateLastName(),
                PhoneNumberGenerator.generatePhoneNumber(), "@mail.com",
                AddressGenerator.generateAddress(), 400);
    }
    @Test
    public void addNewContactNegativeWrongEmail6() throws IOException {
        addNewContactNegativeTest(NameAndLastNameGenerator.generateName(),
                NameAndLastNameGenerator.generateLastName(),
                PhoneNumberGenerator.generatePhoneNumber(), "address@.com",
                AddressGenerator.generateAddress(), 400);
    }
    @Test
    public void addNewContactNegativeWrongEmail7() throws IOException {
        addNewContactNegativeTest(NameAndLastNameGenerator.generateName(),
                NameAndLastNameGenerator.generateLastName(),
                PhoneNumberGenerator.generatePhoneNumber(), null,
                AddressGenerator.generateAddress(), 400);
    }
    @Test
    public void addNewContactNegativeWrongEmail8() throws IOException {
        addNewContactNegativeTest(NameAndLastNameGenerator.generateName(),
                NameAndLastNameGenerator.generateLastName(),
                PhoneNumberGenerator.generatePhoneNumber(), "тест",
                AddressGenerator.generateAddress(), 400);
    }
    @Test
    public void addNewContactNegativeWrongAddress1() throws IOException {
        addNewContactNegativeTest(NameAndLastNameGenerator.generateName(),
                NameAndLastNameGenerator.generateLastName(),
                PhoneNumberGenerator.generatePhoneNumber(),
                EmailGenerator.generateEmail(5,3,2),
                "", 400);
    }
    @Test
    public void addNewContactNegativeWrongAddress2() throws IOException {
        addNewContactNegativeTest(NameAndLastNameGenerator.generateName(),
                NameAndLastNameGenerator.generateLastName(),
                PhoneNumberGenerator.generatePhoneNumber(),
                EmailGenerator.generateEmail(5,3,2),
                null, 400);
    }
    @Test
    public void addNewContactNegativeUnauthorized() throws IOException {
        Contact contact = new Contact(NameAndLastNameGenerator.generateName(),
                NameAndLastNameGenerator.generateLastName(),
                PhoneNumberGenerator.generatePhoneNumber(),
                EmailGenerator.generateEmail(5,3,2),
                AddressGenerator.generateAddress(), "desc");
        RequestBody requestBody = RequestBody.create(GSON.toJson(contact), JSON);
        Request request = new Request.Builder().url(BASE_URL+ADD_CONTACT)
                .addHeader(AUTHORIZATION_HEADER, "teydyd.tdshsj.hdhdh")
                .post(requestBody)
                .build();
        Response response = CLIENT.newCall(request).execute();
        ErrorModel errorModel = GSON.fromJson(response.body().string(), ErrorModel.class);
        Assert.assertEquals(response.code(), 401);
    }
    @Test
    public void addNewContactNegativeDuplicatePhone() throws IOException {
        String phone = PhoneNumberGenerator.generatePhoneNumber();

        addNewContactPositiveTest(NameAndLastNameGenerator.generateName(),
                NameAndLastNameGenerator.generateLastName(),
                phone, EmailGenerator.generateEmail(5,3,2),
                AddressGenerator.generateAddress());

        addNewContactNegativeTest(NameAndLastNameGenerator.generateName(),
                NameAndLastNameGenerator.generateLastName(),
                phone, EmailGenerator.generateEmail(5,3,2),
                AddressGenerator.generateAddress(), 409);
    }
    @Test
    public void addNewContactNegativeDuplicateEmail() throws IOException {
        String email = EmailGenerator.generateEmail(5,3,2);

        addNewContactPositiveTest(NameAndLastNameGenerator.generateName(),
                NameAndLastNameGenerator.generateLastName(),
                PhoneNumberGenerator.generatePhoneNumber(), email,
                AddressGenerator.generateAddress());

        addNewContactNegativeTest(NameAndLastNameGenerator.generateName(),
                NameAndLastNameGenerator.generateLastName(),
                PhoneNumberGenerator.generatePhoneNumber(), email,
                AddressGenerator.generateAddress(), 409);
    }
}

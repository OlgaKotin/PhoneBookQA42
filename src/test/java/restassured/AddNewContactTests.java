package restassured;

import helpers.*;
import interfaces.TestHelper;
import io.restassured.http.ContentType;
import models.Contact;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class AddNewContactTests implements TestHelper {
    @Test
    public void addNewContactPositiveTest(){
        AddNewContactMethod.addNewContact(NameAndLastNameGenerator.generateName(),
                NameAndLastNameGenerator.generateLastName(),
                EmailGenerator.generateEmail(5,3,2),
                PhoneNumberGenerator.generatePhoneNumber(),
                AddressGenerator.generateAddress(),
                PropertiesReaderXML.getProperty("token", XML_FILE_PATH),
                200);
    }

    @Test
    public void addNewContactNegativeTestUnauthorizedUser(){
        AddNewContactMethod.addNewContact(NameAndLastNameGenerator.generateName(),
                NameAndLastNameGenerator.generateLastName(),
                EmailGenerator.generateEmail(5,3,2),
                PhoneNumberGenerator.generatePhoneNumber(),
                AddressGenerator.generateAddress(),
                PhoneNumberGenerator.generatePhoneNumber(),
                401);
    }
    @Test
    public void addNewContactNegativeTestDuplicateContactEmail(){
        String email = EmailGenerator.generateEmail(5,3,2);
        AddNewContactMethod.addNewContact(NameAndLastNameGenerator.generateName(),
                NameAndLastNameGenerator.generateLastName(),
                email,
                PhoneNumberGenerator.generatePhoneNumber(),
                AddressGenerator.generateAddress(),
                PropertiesReaderXML.getProperty("token", XML_FILE_PATH),
                200);
        AddNewContactMethod.addNewContact(NameAndLastNameGenerator.generateName(),
                NameAndLastNameGenerator.generateLastName(),
                email,
                PhoneNumberGenerator.generatePhoneNumber(),
                AddressGenerator.generateAddress(),
                PropertiesReaderXML.getProperty("token", XML_FILE_PATH),
                409);
    }
    @Test
    public void addNewContactNegativeTestDuplicateContactPhone(){
        String phone = PhoneNumberGenerator.generatePhoneNumber();
        AddNewContactMethod.addNewContact(NameAndLastNameGenerator.generateName(),
                NameAndLastNameGenerator.generateLastName(),
                EmailGenerator.generateEmail(5,3,2),
                phone,
                AddressGenerator.generateAddress(),
                PropertiesReaderXML.getProperty("token", XML_FILE_PATH),
                200);
        AddNewContactMethod.addNewContact(NameAndLastNameGenerator.generateName(),
                NameAndLastNameGenerator.generateLastName(),
                EmailGenerator.generateEmail(5,3,2),
                phone,
                AddressGenerator.generateAddress(),
                PropertiesReaderXML.getProperty("token", XML_FILE_PATH),
                409);
    }
    @Test
    public void addNewContactNegativeTestDuplicateContactEmailAndPhone(){
        String email = EmailGenerator.generateEmail(5,3,2);
        String phone = PhoneNumberGenerator.generatePhoneNumber();
        AddNewContactMethod.addNewContact(NameAndLastNameGenerator.generateName(),
                NameAndLastNameGenerator.generateLastName(),
                email,
                phone,
                AddressGenerator.generateAddress(),
                PropertiesReaderXML.getProperty("token", XML_FILE_PATH),
                200);
        AddNewContactMethod.addNewContact(NameAndLastNameGenerator.generateName(),
                NameAndLastNameGenerator.generateLastName(),
                email,
                phone,
                AddressGenerator.generateAddress(),
                PropertiesReaderXML.getProperty("token", XML_FILE_PATH),
                409);
    }
    @Test
    public void addNewContactNegativeTestWrongName1(){
        AddNewContactMethod.addNewContact("",
                NameAndLastNameGenerator.generateLastName(),
                EmailGenerator.generateEmail(5,3,2),
                PhoneNumberGenerator.generatePhoneNumber(),
                AddressGenerator.generateAddress(),
                PropertiesReaderXML.getProperty("token", XML_FILE_PATH),
                400);
    }
    @Test
    public void addNewContactNegativeTestWrongName2(){
        AddNewContactMethod.addNewContact(null,
                NameAndLastNameGenerator.generateLastName(),
                EmailGenerator.generateEmail(5,3,2),
                PhoneNumberGenerator.generatePhoneNumber(),
                AddressGenerator.generateAddress(),
                PropertiesReaderXML.getProperty("token", XML_FILE_PATH),
                400);
    }
    @Test
    public void addNewContactNegativeTestWrongLastName1(){
        AddNewContactMethod.addNewContact(NameAndLastNameGenerator.generateName(),
                "",
                EmailGenerator.generateEmail(5,3,2),
                PhoneNumberGenerator.generatePhoneNumber(),
                AddressGenerator.generateAddress(),
                PropertiesReaderXML.getProperty("token", XML_FILE_PATH),
                400);
    }
    @Test
    public void addNewContactNegativeTestWrongLastName2(){
        AddNewContactMethod.addNewContact(NameAndLastNameGenerator.generateName(),
                null,
                EmailGenerator.generateEmail(5,3,2),
                PhoneNumberGenerator.generatePhoneNumber(),
                AddressGenerator.generateAddress(),
                PropertiesReaderXML.getProperty("token", XML_FILE_PATH),
                400);
    }
    @Test
    public void addNewContactNegativeTestWrongEmail1(){
        AddNewContactMethod.addNewContact(NameAndLastNameGenerator.generateName(),
                NameAndLastNameGenerator.generateLastName(),
                "",
                PhoneNumberGenerator.generatePhoneNumber(),
                AddressGenerator.generateAddress(),
                PropertiesReaderXML.getProperty("token", XML_FILE_PATH),
                400);
    }
    @Test
    public void addNewContactNegativeTestWrongEmail2(){
        AddNewContactMethod.addNewContact(NameAndLastNameGenerator.generateName(),
                NameAndLastNameGenerator.generateLastName(),
                null,
                PhoneNumberGenerator.generatePhoneNumber(),
                AddressGenerator.generateAddress(),
                PropertiesReaderXML.getProperty("token", XML_FILE_PATH),
                400);
    }
    @Test
    public void addNewContactNegativeTestWrongEmail3(){
        AddNewContactMethod.addNewContact(NameAndLastNameGenerator.generateName(),
                NameAndLastNameGenerator.generateLastName(),
                "wrong@email@com",
                PhoneNumberGenerator.generatePhoneNumber(),
                AddressGenerator.generateAddress(),
                PropertiesReaderXML.getProperty("token", XML_FILE_PATH),
                400);
    }
    @Test
    public void addNewContactNegativeTestWrongEmail4(){
        AddNewContactMethod.addNewContact(NameAndLastNameGenerator.generateName(),
                NameAndLastNameGenerator.generateLastName(),
                "wrongemailcom",
                PhoneNumberGenerator.generatePhoneNumber(),
                AddressGenerator.generateAddress(),
                PropertiesReaderXML.getProperty("token", XML_FILE_PATH),
                400);
    }
    @Test
    public void addNewContactNegativeTestWrongEmail5(){
        AddNewContactMethod.addNewContact(NameAndLastNameGenerator.generateName(),
                NameAndLastNameGenerator.generateLastName(),
                "@email.com",
                PhoneNumberGenerator.generatePhoneNumber(),
                AddressGenerator.generateAddress(),
                PropertiesReaderXML.getProperty("token", XML_FILE_PATH),
                400);
    }
    @Test
    public void addNewContactNegativeTestWrongEmail6(){
        AddNewContactMethod.addNewContact(NameAndLastNameGenerator.generateName(),
                NameAndLastNameGenerator.generateLastName(),
                "wrong@",
                PhoneNumberGenerator.generatePhoneNumber(),
                AddressGenerator.generateAddress(),
                PropertiesReaderXML.getProperty("token", XML_FILE_PATH),
                400);
    }
    @Test
    public void addNewContactNegativeTestWrongEmail7(){
        AddNewContactMethod.addNewContact(NameAndLastNameGenerator.generateName(),
                NameAndLastNameGenerator.generateLastName(),
                "тест@меил.ком",
                PhoneNumberGenerator.generatePhoneNumber(),
                AddressGenerator.generateAddress(),
                PropertiesReaderXML.getProperty("token", XML_FILE_PATH),
                400);
    }
    @Test
    public void addNewContactNegativeTestWrongEmail8(){
        AddNewContactMethod.addNewContact(NameAndLastNameGenerator.generateName(),
                NameAndLastNameGenerator.generateLastName(),
                "тест",
                PhoneNumberGenerator.generatePhoneNumber(),
                AddressGenerator.generateAddress(),
                PropertiesReaderXML.getProperty("token", XML_FILE_PATH),
                400);
    }
    @Test
    public void addNewContactNegativeTestWrongPhone1(){
        AddNewContactMethod.addNewContact(NameAndLastNameGenerator.generateName(),
                NameAndLastNameGenerator.generateLastName(),
                EmailGenerator.generateEmail(5,3,2),
                "",
                AddressGenerator.generateAddress(),
                PropertiesReaderXML.getProperty("token", XML_FILE_PATH),
                400);
    }
    @Test
    public void addNewContactNegativeTestWrongPhone2(){
        AddNewContactMethod.addNewContact(NameAndLastNameGenerator.generateName(),
                NameAndLastNameGenerator.generateLastName(),
                EmailGenerator.generateEmail(5,3,2),
                null,
                AddressGenerator.generateAddress(),
                PropertiesReaderXML.getProperty("token", XML_FILE_PATH),
                400);
    }
    @Test
    public void addNewContactNegativeTestWrongPhone3(){
        AddNewContactMethod.addNewContact(NameAndLastNameGenerator.generateName(),
                NameAndLastNameGenerator.generateLastName(),
                EmailGenerator.generateEmail(5,3,2),
                "wrongphone1234",
                AddressGenerator.generateAddress(),
                PropertiesReaderXML.getProperty("token", XML_FILE_PATH),
                400);
    }
    @Test
    public void addNewContactNegativeTestWrongPhone4(){
        AddNewContactMethod.addNewContact(NameAndLastNameGenerator.generateName(),
                NameAndLastNameGenerator.generateLastName(),
                EmailGenerator.generateEmail(5,3,2),
                "123456789",
                AddressGenerator.generateAddress(),
                PropertiesReaderXML.getProperty("token", XML_FILE_PATH),
                400);
    }
    @Test
    public void addNewContactNegativeTestWrongPhone5(){
        AddNewContactMethod.addNewContact(NameAndLastNameGenerator.generateName(),
                NameAndLastNameGenerator.generateLastName(),
                EmailGenerator.generateEmail(5,3,2),
                "1234567890123456",
                AddressGenerator.generateAddress(),
                PropertiesReaderXML.getProperty("token", XML_FILE_PATH),
                400);
    }
    @Test
    public void addNewContactNegativeTestWrongPhone6(){
        AddNewContactMethod.addNewContact(NameAndLastNameGenerator.generateName(),
                NameAndLastNameGenerator.generateLastName(),
                EmailGenerator.generateEmail(5,3,2),
                "1234567890!_@",
                AddressGenerator.generateAddress(),
                PropertiesReaderXML.getProperty("token", XML_FILE_PATH),
                400);
    }
    @Test
    public void addNewContactNegativeTestWrongAddress1(){
        AddNewContactMethod.addNewContact(NameAndLastNameGenerator.generateName(),
                NameAndLastNameGenerator.generateLastName(),
                EmailGenerator.generateEmail(5,3,2),
                PhoneNumberGenerator.generatePhoneNumber(),
                "",
                PropertiesReaderXML.getProperty("token", XML_FILE_PATH),
                400);
    }
    @Test
    public void addNewContactNegativeTestWrongAddress2(){
        AddNewContactMethod.addNewContact(NameAndLastNameGenerator.generateName(),
                NameAndLastNameGenerator.generateLastName(),
                EmailGenerator.generateEmail(5,3,2),
                PhoneNumberGenerator.generatePhoneNumber(),
                null,
                PropertiesReaderXML.getProperty("token", XML_FILE_PATH),
                400);
    }
}

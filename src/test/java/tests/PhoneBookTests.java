package tests;

import Models.Contact;
import config.BaseTest;
import helpers.*;
import io.qameta.allure.Allure;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class PhoneBookTests extends BaseTest {
    @Test
    public void loginWithOutPasswordTestPositive() throws InterruptedException {
        Allure.description("Login without password. Positive test.");
        Allure.step("Main page creation...");
        MainPage mainPage = new MainPage(getDriver());
        Allure.step("Open Login page");
        LoginPage loginPage = BasePage.openTopMenuItem(TopMenuItems.LOGIN);

        Alert alert = loginPage.fillEmailField("gfhdhd@mail.com").clickByRegistrationButton();
        String expectedAlertText = "Wrong";
        boolean isAlertHandled = AlertHandler.handleAlert(alert, expectedAlertText);
        TakeScreen.takeScreenShot(getDriver(), "LoginWithoutPassword");
        Assert.assertTrue(isAlertHandled);
    }

    @Test
    public void registrationOfAnAlreadyRegisteredUser() {
        MainPage mainPage = new MainPage(getDriver());
        LoginPage loginPage = BasePage.openTopMenuItem(TopMenuItems.LOGIN);
        Alert alert = loginPage
                .fillEmailField("loginfb42@mail.com")
                .fillPasswordField("Ytpyfrjvrf0!")
                .clickByRegistrationButton();
        String expectedAlertText = "User already exist";
        boolean result = AlertHandler.handleAlert(alert, expectedAlertText);
        Assert.assertTrue(result);
    }

    @Test
    public void successfulLogin() {
        MainPage mainPage = new MainPage(getDriver());
        LoginPage loginPage = BasePage.openTopMenuItem(TopMenuItems.LOGIN);
        loginPage
                .fillEmailField("loginfb42@mail.com")
                .fillPasswordField("Ytpyfrjvrf0!")
                .clickByLoginButton();
        boolean res = ContactsPage
                .isElementPersist(getDriver().findElement(By.xpath("//button")));
        Assert.assertTrue(res);
    }

    @Test
    public void loginOfAnExistingUserAddContact() {
        MainPage mainPage = new MainPage(getDriver());
        LoginPage loginPage = BasePage.openTopMenuItem(TopMenuItems.LOGIN);
        loginPage
                .fillEmailField("loginfb42@mail.com")
                .fillPasswordField("Ytpyfrjvrf0!")
                .clickByLoginButton();
        AddPage addPage = BasePage.openTopMenuItem(TopMenuItems.ADD);
        Contact contact = new Contact(NameAndLastNameGenerator.generateName(),
                NameAndLastNameGenerator.generateLastName(),
                PhoneNumberGenerator.generatePhoneNumber(),
                EmailGenerator.generateEmail(6,4,2),
                AddressGenerator.generateAddress(), "?");

    }

    @Test
    public void successfulRegistration() {
        Allure.description("Successful registration test.");
        MainPage mainPage = new MainPage(getDriver());
        LoginPage loginPage = BasePage.openTopMenuItem(TopMenuItems.LOGIN);
        Alert alert = loginPage.fillEmailField(EmailGenerator.generateEmail(5, 5, 3))
                .fillPasswordField(PasswordStringGenerator.generateRandomPassword())
                .clickByRegistrationButton();
        if (alert == null) {
            ContactsPage contactsPage = new ContactsPage(getDriver());
            Assert.assertTrue(contactsPage.isElementPersist(getDriver()
                    .findElement(By.xpath("//button[contains(text(),'Sign Out')]"))));
        } else {
            TakeScreen.takeScreenShot(getDriver(), "Successful Registration");
        }

    }

}

package config;

import helpers.EmailGenerator;
import org.testng.annotations.DataProvider;

public class TestData {

    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return new Object[][] {
            {EmailGenerator.generateEmail(5,2,2),"fakepassword"},
            {"fakeUser2","fakepassword"}
        };
    }
}
